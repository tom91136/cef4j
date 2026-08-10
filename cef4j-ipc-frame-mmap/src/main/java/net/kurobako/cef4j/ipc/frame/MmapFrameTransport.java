package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.protocol.gen.OsrPaintEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSession.HandlerRegistration;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Memory-mapped frame transport: subscribes to {@link OsrPaintEvent} on a {@link CefSession}, opens the helper's POSIX
 * shm regions (Linux exposes shm at {@code /dev/shm/<name>}), and copies a sequence-validated snapshot before invoking
 * the consumer.
 *
 * <p>One transport per browser. The helper alternates two shm slots so normal producer/consumer overlap does not
 * contend on one region. Each slot has a sequence-lock header; if the helper wraps around while the JVM is behind, the
 * stale frame is dropped rather than delivered partially overwritten. Mappings are replaced when resize-driven capacity
 * changes rotate the shm names.
 *
 * <p>Linux-only for now (relies on {@code /dev/shm}). Windows port later via {@code FileMapping}; the wire shape
 * already abstracts the name as a string.
 */
public final class MmapFrameTransport implements FrameTransport {

    private static final Logger LOG = LoggerFactory.getLogger(MmapFrameTransport.class);
    private static final int SHM_HEADER_BYTES = Long.BYTES;
    private static final VarHandle SHM_SEQUENCE =
            MethodHandles.byteBufferViewVarHandle(long[].class, ByteOrder.nativeOrder());

    @Nullable
    private final RemoteHandle browser; // null = accept all browsers' paints

    private final AtomicInteger sequence = new AtomicInteger();

    @Nullable
    private volatile FrameConsumer consumer;

    private final Object mappingLock = new Object();

    @Nullable
    private String mappedShmName;

    @Nullable
    private RandomAccessFile mappedFile;

    @Nullable
    private FileChannel mappedChannel;

    @Nullable
    private ByteBuffer mappedBuffer;

    private long mappedSize;

    private volatile boolean closed = false;

    /**
     * Subscribe to paint events for the given browser. The session retains the subscription; the returned transport
     * owns it and unsubscribes on {@link #close()}.
     */
    public static MmapFrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser) {
        MmapFrameTransport t = new MmapFrameTransport(browser);
        t.subscribe(session);
        return t;
    }

    /**
     * Subscribe to paint events for *every* browser in the session. Use when you need to register before the helper has
     * assigned a handle (the typical bootstrap race: paint can fire before {@code LifeSpanHandlerOnAfterCreatedEvent}
     * arrives, so a late {@link #bind} misses it). The consumer will be called for every browser that paints; for
     * multi-browser sessions wrap this in your own dispatch.
     */
    public static MmapFrameTransport bindAll(@Nonnull CefSession session) {
        MmapFrameTransport t = new MmapFrameTransport(null);
        t.subscribe(session);
        return t;
    }

    private MmapFrameTransport(@Nullable RemoteHandle browser) {
        this.browser = browser;
    }

    // Two-step construction so we can stash the subscription handle on the instance for clean teardown.
    @Nullable
    private HandlerRegistration registration;

    private void subscribe(CefSession session) {
        this.registration = session.on(OsrPaintEvent.MESSAGE_ID, OsrPaintEvent.DECODER, this::onPaint);
    }

    private String browserIdForLog() {
        return browser == null ? "*" : Integer.toString(browser.id());
    }

    private void onPaint(OsrPaintEvent ev) {
        if (closed) return;
        if (browser != null && ev.browser().id() != browser.id()) return;
        FrameConsumer c = consumer;
        if (c == null) return;
        ByteBuffer view = ensureMapping(ev);
        if (view == null) return;
        ByteBuffer pixels = copyStableFrame(ev, view);
        if (pixels == null) return;
        FrameMetadata meta = new FrameMetadata(
                sequence.incrementAndGet(),
                System.nanoTime(),
                PixelFormat.BGRA,
                Collections.singletonList(new Rect(ev.dirtyX(), ev.dirtyY(), ev.dirtyWidth(), ev.dirtyHeight())));
        try {
            c.accept(ev.width(), ev.height(), pixels, meta);
        } catch (RuntimeException re) {
            LOG.warn("frame consumer threw on browser={} seq={}", browserIdForLog(), meta.sequenceId(), re);
        }
    }

    /**
     * Copies one coherent snapshot from the helper's double-buffered region. The helper stores an odd sequence while
     * writing and the event's even sequence after publishing. A mismatch before or after the copy means this event's
     * slot wrapped while the JVM was behind; dropping that stale frame is preferable to delivering torn pixels.
     */
    @Nullable
    private ByteBuffer copyStableFrame(OsrPaintEvent ev, ByteBuffer mapping) {
        long before = (long) SHM_SEQUENCE.getAcquire(mapping, 0);
        if (before != ev.frameSequence() || (before & 1L) != 0L) return null;

        ByteBuffer src = mapping.duplicate();
        src.position(SHM_HEADER_BYTES);
        src.limit(SHM_HEADER_BYTES + ev.byteCount());
        ByteBuffer snapshot = ByteBuffer.allocateDirect(ev.byteCount());
        snapshot.put(src).flip();

        long after = (long) SHM_SEQUENCE.getAcquire(mapping, 0);
        if (after != before) return null;
        return snapshot.asReadOnlyBuffer();
    }

    /**
     * Ensures the shm region named in the event is mmapped and returns a buffer view. Re-maps when alternating slots or
     * resize-driven generations change the shm name. Returns {@code null} if the shm could not be opened.
     */
    @Nullable
    private ByteBuffer ensureMapping(OsrPaintEvent ev) {
        synchronized (mappingLock) {
            if (closed) return null;
            String name = ev.shmName();
            if (!name.equals(mappedShmName)) {
                disposeMappingLocked();
                String leaf = name.startsWith("/") ? name.substring(1) : name;
                if (!leaf.matches("cef4j-paint-[0-9]+-[0-9]+-[0-9]+-[01]")) {
                    LOG.warn("rejecting invalid paint shm name={} for browser={}", name, browserIdForLog());
                    return null;
                }
                Path shmPath = Paths.get("/dev/shm", leaf);
                try {
                    RandomAccessFile raf = new RandomAccessFile(shmPath.toFile(), "r");
                    FileChannel ch = raf.getChannel();
                    long size = ch.size();
                    ByteBuffer mapped = ch.map(FileChannel.MapMode.READ_ONLY, 0, size);
                    this.mappedFile = raf;
                    this.mappedChannel = ch;
                    this.mappedBuffer = mapped;
                    this.mappedSize = size;
                    this.mappedShmName = name;
                } catch (IOException e) {
                    LOG.warn("failed to open shm {} for browser={}", shmPath, browserIdForLog(), e);
                    return null;
                }
            }
            long expectedBytes = (long) ev.width() * ev.height() * 4L;
            // Defensive: reject malformed dimensions/counts before allocating or slicing.
            if (ev.width() <= 0
                    || ev.height() <= 0
                    || ev.byteCount() <= 0
                    || expectedBytes != ev.byteCount()
                    || ev.byteCount() > mappedSize - SHM_HEADER_BYTES) {
                LOG.warn(
                        "invalid paint dimensions={}x{} byteCount={} mapped size={} for browser={}",
                        ev.width(),
                        ev.height(),
                        ev.byteCount(),
                        mappedSize,
                        browserIdForLog());
                return null;
            }
            return mappedBuffer;
        }
    }

    private void disposeMappingLocked() {
        // ByteBuffer mapping is auto-cleaned on GC; explicit unmap requires sun.misc.Unsafe. Closing the channel
        // releases the file handle. Helper's shm_unlink already happened at allocation, so kernel reclaims the
        // shm object on last fd close (when GC eventually finalises the mapped buffer).
        FileChannel ch = mappedChannel;
        RandomAccessFile raf = mappedFile;
        mappedBuffer = null;
        mappedChannel = null;
        mappedFile = null;
        mappedShmName = null;
        mappedSize = 0;
        try {
            if (ch != null) ch.close();
        } catch (IOException ignored) {
        }
        try {
            if (raf != null) raf.close();
        } catch (IOException ignored) {
        }
    }

    @Override
    public void onFrame(FrameConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        consumer = null;
        if (registration != null) registration.unregister();
        synchronized (mappingLock) {
            disposeMappingLocked();
        }
    }
}
