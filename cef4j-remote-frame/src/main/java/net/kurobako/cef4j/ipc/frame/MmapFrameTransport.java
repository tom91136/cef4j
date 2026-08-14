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
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.protocol.gen.OsrPaintEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSession.HandlerRegistration;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Compatibility name for the file-backed shared-frame transport. New code should use {@link SharedFileFrameTransport}.
 * The runtime server publishes ordinary temporary files so Java 11 can map them on Linux, macOS, and Windows without a
 * platform bridge.
 *
 * <p>One transport per browser. The server alternates two mapped-file slots so normal producer/consumer overlap does
 * not contend on one region. Each slot has a sequence-lock header; if the server wraps around while the JVM is behind,
 * the stale frame is dropped rather than delivered partially overwritten. Mappings are replaced when resize-driven
 * capacity changes rotate the paths.
 *
 * @deprecated use {@link SharedFileFrameTransport}; retained for source compatibility.
 */
@Deprecated
public final class MmapFrameTransport implements FrameTransport {

    private static final Logger LOG = LoggerFactory.getLogger(MmapFrameTransport.class);
    private static final int SHM_HEADER_BYTES = Long.BYTES;
    private static final VarHandle SHM_SEQUENCE =
            MethodHandles.byteBufferViewVarHandle(long[].class, ByteOrder.nativeOrder());

    @Nullable
    private final RemoteHandle browser; // null = accept all browsers' paints

    private final AtomicInteger sequence = new AtomicInteger();

    private final AtomicReference<OsrPaintEvent> pendingPaint = new AtomicReference<>();

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
     * Subscribe to paint events for *every* browser in the session. Use when you need to register before the server has
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
        // A recovered session may receive its first paint between session construction and frame-consumer binding.
        // Ask the session to replay that latest event, then retain it locally until onFrame installs a consumer.
        this.registration = session.onLatest(OsrPaintEvent.MESSAGE_ID, OsrPaintEvent.DECODER, this::onPaint);
    }

    private String browserIdForLog() {
        return browser == null ? "*" : Integer.toString(browser.id());
    }

    private void onPaint(OsrPaintEvent ev) {
        if (closed) return;
        if (browser != null && ev.browser().id() != browser.id()) return;
        FrameConsumer c = consumer;
        if (c == null) {
            pendingPaint.set(ev);
            c = consumer;
            if (c == null || !pendingPaint.compareAndSet(ev, null)) return;
        }
        deliver(ev, c);
    }

    private void deliver(OsrPaintEvent ev, FrameConsumer c) {
        ByteBuffer view = ensureMapping(ev);
        if (view == null) return;
        ByteBuffer pixels = copyStableFrame(ev, view);
        if (pixels == null) return;
        FrameMetadata meta = new FrameMetadata(
                sequence.incrementAndGet(),
                ev.frameSequence(),
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
     * Copies one coherent snapshot from the server's double-buffered region. The server stores an odd sequence while
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
     * Ensures the shared file named in the event is mapped and returns a buffer view. Re-maps when alternating slots or
     * resize-driven generations change the path. Returns {@code null} if the file could not be opened.
     */
    @Nullable
    private ByteBuffer ensureMapping(OsrPaintEvent ev) {
        synchronized (mappingLock) {
            if (closed) return null;
            String name = ev.shmName();
            if (!name.equals(mappedShmName)) {
                disposeMappingLocked();
                Path sharedPath = Paths.get(name).toAbsolutePath().normalize();
                Path leaf = sharedPath.getFileName();
                if (leaf == null || !leaf.toString().matches("cef4j-paint-[0-9]+-[0-9]+-[0-9]+-[01]\\.frame")) {
                    LOG.warn("rejecting invalid shared-frame path={} for browser={}", name, browserIdForLog());
                    return null;
                }
                try {
                    Path tempRoot =
                            Paths.get(System.getProperty("java.io.tmpdir")).toRealPath();
                    Path realPath = sharedPath.toRealPath();
                    if (!tempRoot.equals(realPath.getParent())) {
                        LOG.warn("rejecting shared-frame path outside java.io.tmpdir: {}", realPath);
                        return null;
                    }
                    RandomAccessFile raf = new RandomAccessFile(realPath.toFile(), "r");
                    FileChannel ch = raf.getChannel();
                    long size = ch.size();
                    ByteBuffer mapped = ch.map(FileChannel.MapMode.READ_ONLY, 0, size);
                    this.mappedFile = raf;
                    this.mappedChannel = ch;
                    this.mappedBuffer = mapped;
                    this.mappedSize = size;
                    this.mappedShmName = name;
                } catch (IOException e) {
                    LOG.warn("failed to open shared-frame file {} for browser={}", sharedPath, browserIdForLog(), e);
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
        ByteBuffer buffer = mappedBuffer;
        FileChannel ch = mappedChannel;
        RandomAccessFile raf = mappedFile;
        mappedBuffer = null;
        mappedChannel = null;
        mappedFile = null;
        mappedShmName = null;
        mappedSize = 0;
        // Closing a FileChannel does not unmap its MappedByteBuffer. That leaves the shared-frame file locked on
        // Windows until a later GC, preventing both deterministic client cleanup and server-side slot rotation.
        // Unsafe.invokeCleaner is available from Java 9 onward and requires no client JNI library. Reduced runtimes
        // that omit jdk.unsupported retain the platform's normal GC-based unmapping fallback.
        if (buffer != null && !MappedBufferCleaner.clean(buffer)) {
            LOG.debug("explicit shared-frame unmap unavailable for browser={}", browserIdForLog());
        }
        try {
            if (ch != null) ch.close();
        } catch (IOException ignored) {
            // Best-effort teardown: the mapping has already been detached from this transport.
        }
        try {
            if (raf != null) raf.close();
        } catch (IOException ignored) {
            // Best-effort teardown: the mapping has already been detached from this transport.
        }
    }

    @Override
    public void onFrame(@Nullable FrameConsumer consumer) {
        this.consumer = consumer;
        if (consumer != null) {
            OsrPaintEvent pending = pendingPaint.getAndSet(null);
            if (pending != null && !closed) deliver(pending, consumer);
        } else {
            pendingPaint.set(null);
        }
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        consumer = null;
        pendingPaint.set(null);
        if (registration != null) registration.unregister();
        synchronized (mappingLock) {
            disposeMappingLocked();
        }
    }
}
