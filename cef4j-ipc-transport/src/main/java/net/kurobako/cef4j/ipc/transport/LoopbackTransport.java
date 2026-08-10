package net.kurobako.cef4j.ipc.transport;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * In-memory paired {@link CefTransport}. Used by tests to exercise transport semantics without binding sockets, and by
 * the recording/replay layer as the substrate for deterministic playback.
 *
 * <p>Use {@link #create} to obtain a connected pair; sending on one side delivers a copy to the other side's receive
 * handler on a dedicated reader thread.
 */
public final class LoopbackTransport implements CefTransport {

    private static final byte[] EOF = new byte[0];
    private static final AtomicInteger PAIR_ID = new AtomicInteger();

    private final String name;
    private final BlockingQueue<byte[]> inbound = new LinkedBlockingQueue<>();
    private final CountDownLatch handlerReady = new CountDownLatch(1);
    private final Thread reader;

    @Nullable
    private volatile LoopbackTransport peer;

    @Nullable
    private volatile Consumer<ByteBuffer> receiveHandler;

    @Nullable
    private volatile Runnable disconnectHandler;

    private volatile boolean closed = false;
    private volatile boolean peerClosed = false;

    /** Creates a connected pair. */
    public static Pair create() {
        int id = PAIR_ID.incrementAndGet();
        LoopbackTransport a = new LoopbackTransport("loopback-" + id + "-a");
        LoopbackTransport b = new LoopbackTransport("loopback-" + id + "-b");
        a.peer = b;
        b.peer = a;
        return new Pair(a, b);
    }

    /** Holder for the two ends of a loopback channel. */
    public static final class Pair {
        public final LoopbackTransport a;
        public final LoopbackTransport b;

        Pair(LoopbackTransport a, LoopbackTransport b) {
            this.a = a;
            this.b = b;
        }
    }

    private LoopbackTransport(String name) {
        this.name = name;
        this.reader = new Thread(this::readerLoop, name);
        this.reader.setDaemon(true);
        this.reader.start();
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException(name + ": send on closed transport");
        LoopbackTransport p = peer;
        if (p == null || peerClosed || p.closed) {
            throw new CefTransportException(name + ": peer disconnected");
        }
        byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        p.inbound.add(copy);
    }

    @Override
    public void onReceive(@Nonnull Consumer<ByteBuffer> handler) {
        this.receiveHandler = handler;
        handlerReady.countDown();
    }

    @Override
    public void onDisconnect(@Nonnull Runnable handler) {
        this.disconnectHandler = handler;
    }

    @Override
    public boolean isConnected() {
        return !closed && !peerClosed;
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        inbound.add(EOF);
        handlerReady.countDown();
        LoopbackTransport p = peer;
        if (p != null && !p.closed) p.notifyPeerClosed();
    }

    private void notifyPeerClosed() {
        peerClosed = true;
        Runnable r = disconnectHandler;
        if (r != null) {
            try {
                r.run();
            } catch (RuntimeException ignored) {
                // The disconnect handler is best-effort. Swallowing matches the shape that ZmqTransport will
                // also need (callbacks fired from internal threads should not propagate into the transport core).
            }
        }
    }

    private void readerLoop() {
        try {
            handlerReady.await();
            while (true) {
                byte[] frame = inbound.take();
                if (frame == EOF) return;
                Consumer<ByteBuffer> h = receiveHandler;
                if (h != null) h.accept(ByteBuffer.wrap(frame));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
