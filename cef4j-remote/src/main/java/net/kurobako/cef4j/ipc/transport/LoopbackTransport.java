package net.kurobako.cef4j.ipc.transport;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import org.jspecify.annotations.Nullable;

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

    @Nullable
    private volatile LoopbackTransport peer;

    @Nullable
    private volatile Consumer<ByteBuffer> receiveHandler;

    @Nullable
    private volatile Runnable disconnectHandler;

    private volatile boolean closed = false;
    private volatile boolean peerClosed = false;
    private final AtomicBoolean disconnectNotified = new AtomicBoolean();

    /** Creates a connected pair. */
    public static Pair create() {
        final int id = PAIR_ID.incrementAndGet();
        final LoopbackTransport a = new LoopbackTransport("loopback-" + id + "-a");
        final LoopbackTransport b = new LoopbackTransport("loopback-" + id + "-b");
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
        final Thread reader = new Thread(this::readerLoop, name);
        reader.setDaemon(true);
        reader.start();
    }

    @Override
    public void send(ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException(name + ": send on closed transport");
        final LoopbackTransport p = peer;
        if (p == null || peerClosed || p.closed) {
            throw new CefTransportException(name + ": peer disconnected");
        }
        final byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        p.inbound.add(copy);
    }

    @Override
    public void onReceive(Consumer<ByteBuffer> handler) {
        this.receiveHandler = handler;
        handlerReady.countDown();
    }

    @Override
    public void onDisconnect(Runnable handler) {
        disconnectHandler = handler;
        if (peerClosed) notifyDisconnect();
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
        final LoopbackTransport p = peer;
        if (p != null && !p.closed) p.notifyPeerClosed();
    }

    private void notifyPeerClosed() {
        peerClosed = true;
        notifyDisconnect();
    }

    private void notifyDisconnect() {
        final Runnable r = disconnectHandler;
        if (r != null && disconnectNotified.compareAndSet(false, true)) {
            try {
                r.run();
            } catch (RuntimeException ignored) {
                // XXX: A user callback cannot be allowed to break the transport reader thread.
            }
        }
    }

    private void readerLoop() {
        try {
            handlerReady.await();
            byte[] frame;
            while ((frame = inbound.take()) != EOF) {
                final Consumer<ByteBuffer> h = receiveHandler;
                if (h != null) h.accept(ByteBuffer.wrap(frame));
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
