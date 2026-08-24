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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
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
 * Cross-platform, file-backed shared-frame transport. The runtime server publishes ordinary temporary files so Java 11
 * can map them on Linux, macOS, and Windows without a platform bridge.
 *
 * <p>One transport per browser. The server alternates two mapped-file slots so normal producer/consumer overlap does
 * not contend on one region. Each slot has a sequence-lock header; if the server wraps around while the JVM is behind,
 * the stale frame is dropped rather than delivered partially overwritten. Mappings are replaced when resize-driven
 * capacity changes rotate the paths.
 */
public final class SharedFileFrameTransport implements FrameTransport {

    private static final Logger LOG = LoggerFactory.getLogger(SharedFileFrameTransport.class);
    private static final AtomicInteger INSTANCE = new AtomicInteger();
    private static final int SHM_HEADER_BYTES = Long.BYTES;
    private static final VarHandle SHM_SEQUENCE =
            MethodHandles.byteBufferViewVarHandle(long[].class, ByteOrder.nativeOrder());

    @Nullable
    private final RemoteHandle browser; // null = accept all browsers' paints

    private final AtomicInteger sequence = new AtomicInteger();

    private final AtomicReference<OsrPaintEvent> pendingPaint = new AtomicReference<>();

    private final AtomicBoolean deliveryScheduled = new AtomicBoolean();

    private final ExecutorService deliveryExecutor = Executors.newSingleThreadExecutor(task -> {
        Thread thread = new Thread(task, "cef4j-frame-" + INSTANCE.incrementAndGet());
        thread.setDaemon(true);
        return thread;
    });

    @Nullable
    private volatile FrameConsumer consumer;

    private final Object mappingLock = new Object();

    /** Serialises frame delivery so a consumer installed mid-paint is never invoked concurrently. */
    private final Object deliveryLock = new Object();

    @Nullable
    private String mappedShmName;

    @Nullable
    private RandomAccessFile mappedFile;

    @Nullable
    private FileChannel mappedChannel;

    @Nullable
    private MappedBufferCleaner.Mapping mappedMapping;

    private long mappedSize;

    private volatile boolean closed = false;

    /**
     * Subscribe to paint events for the given browser. The session retains the subscription; the returned transport
     * owns it and unsubscribes on {@link #close()}.
     */
    public static SharedFileFrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser) {
        SharedFileFrameTransport t = new SharedFileFrameTransport(browser);
        t.subscribe(session);
        return t;
    }

    /**
     * Subscribe to paint events for *every* browser in the session. Use when you need to register before the server has
     * assigned a handle (the typical bootstrap race: paint can fire before {@code LifeSpanHandlerOnAfterCreatedEvent}
     * arrives, so a late {@link #bind} misses it). The consumer will be called for every browser that paints; for
     * multi-browser sessions wrap this in your own dispatch.
     */
    public static SharedFileFrameTransport bindAll(@Nonnull CefSession session) {
        SharedFileFrameTransport t = new SharedFileFrameTransport(null);
        t.subscribe(session);
        return t;
    }

    private SharedFileFrameTransport(@Nullable RemoteHandle browser) {
        this.browser = browser;
    }

    @Nullable
    private HandlerRegistration registration;

    private void subscribe(CefSession session) {
        this.registration = session.onLatest(OsrPaintEvent.MESSAGE_ID, OsrPaintEvent.DECODER, this::onPaint);
    }

    private String browserIdForLog() {
        return browser == null ? "*" : Integer.toString(browser.id());
    }

    private void onPaint(OsrPaintEvent ev) {
        if (closed) return;
        if (browser != null && ev.browser().id() != browser.id()) return;
        pendingPaint.set(ev);
        scheduleDelivery();
    }

    private void scheduleDelivery() {
        if (closed || consumer == null || !deliveryScheduled.compareAndSet(false, true)) return;
        try {
            deliveryExecutor.execute(this::drainPendingPaint);
        } catch (RejectedExecutionException closedExecutor) {
            deliveryScheduled.set(false);
        }
    }

    private void drainPendingPaint() {
        try {
            while (!closed) {
                FrameConsumer c = consumer;
                if (c == null) return;
                OsrPaintEvent ev = pendingPaint.getAndSet(null);
                if (ev == null) return;
                deliver(ev, c);
            }
        } finally {
            deliveryScheduled.set(false);
            if (!closed && consumer != null && pendingPaint.get() != null) scheduleDelivery();
        }
    }

    private void deliver(OsrPaintEvent ev, FrameConsumer c) {
        synchronized (deliveryLock) {
            if (closed) return;
            ByteBuffer pixels;
            synchronized (mappingLock) {
                ByteBuffer view = ensureMappingLocked(ev);
                if (view == null) return;
                // XXX: Explicit unmap can race Unsafe.copyMemory and crash the JVM; remove when mapping lifetime is
                // reference-counted independently of close().
                pixels = copyStableFrame(ev, view);
            }
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
    private ByteBuffer ensureMappingLocked(OsrPaintEvent ev) {
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
                Path tempRoot = Paths.get(System.getProperty("java.io.tmpdir")).toRealPath();
                Path realPath = sharedPath.toRealPath();
                if (!tempRoot.equals(realPath.getParent())) {
                    LOG.warn("rejecting shared-frame path outside java.io.tmpdir: {}", realPath);
                    return null;
                }
                RandomAccessFile raf = new RandomAccessFile(realPath.toFile(), "r");
                boolean opened = false;
                try {
                    FileChannel ch = raf.getChannel();
                    long size = ch.size();
                    if (size <= 0) {
                        LOG.warn("shared-frame file {} is empty for browser={}", realPath, browserIdForLog());
                        return null;
                    }
                    MappedBufferCleaner.Mapping mapping = MappedBufferCleaner.map(ch, size);
                    this.mappedFile = raf;
                    this.mappedChannel = ch;
                    this.mappedMapping = mapping;
                    this.mappedSize = size;
                    this.mappedShmName = name;
                    opened = true;
                } finally {
                    if (!opened) {
                        try {
                            raf.close();
                        } catch (IOException closeFailure) {
                            LOG.debug("failed to close rejected shared-frame file {}", realPath, closeFailure);
                        }
                    }
                }
            } catch (IOException | RuntimeException e) {
                LOG.warn("failed to open shared-frame file {} for browser={}", sharedPath, browserIdForLog(), e);
                return null;
            }
        }
        long expectedBytes = (long) ev.width() * ev.height() * 4L;
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
        return mappedMapping == null ? null : mappedMapping.buffer();
    }

    private void disposeMappingLocked() {
        MappedBufferCleaner.Mapping mapping = mappedMapping;
        FileChannel ch = mappedChannel;
        RandomAccessFile raf = mappedFile;
        mappedMapping = null;
        mappedChannel = null;
        mappedFile = null;
        mappedShmName = null;
        mappedSize = 0;
        // XXX: Remove the Unsafe fallback when the minimum supported Java version provides scoped mapped memory.
        if (mapping != null && !mapping.close()) {
            LOG.debug("explicit shared-frame unmap unavailable for browser={}", browserIdForLog());
        }
        try {
            if (ch != null) ch.close();
        } catch (IOException closeFailure) {
            LOG.debug("failed to close shared-frame channel for browser={}", browserIdForLog(), closeFailure);
        }
        try {
            if (raf != null) raf.close();
        } catch (IOException closeFailure) {
            LOG.debug("failed to close shared-frame file for browser={}", browserIdForLog(), closeFailure);
        }
    }

    @Override
    public void onFrame(@Nullable FrameConsumer consumer) {
        this.consumer = consumer;
        if (consumer != null) {
            scheduleDelivery();
        } else {
            pendingPaint.set(null);
        }
    }

    @Override
    public void close() {
        synchronized (deliveryLock) {
            if (closed) return;
            closed = true;
            consumer = null;
            pendingPaint.set(null);
            if (registration != null) registration.unregister();
            synchronized (mappingLock) {
                disposeMappingLocked();
            }
        }
        deliveryExecutor.shutdownNow();
    }
}
