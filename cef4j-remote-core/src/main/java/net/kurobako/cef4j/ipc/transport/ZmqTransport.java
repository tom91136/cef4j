package net.kurobako.cef4j.ipc.transport;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;
import org.zeromq.ZMonitor;

/**
 * JeroMQ-backed {@link CefTransport} using DEALER sockets. Construct via {@link #bind} or {@link #connect}.
 *
 * <p>Internals: a single worker thread owns the main DEALER socket (ZMQ sockets are not thread-safe). External
 * {@link #send} callers enqueue bytes onto a {@link BlockingQueue}; the worker drains outbound bytes on a short bounded
 * poll. DEALER is used instead of PAIR for consistent routing and backpressure behaviour across JeroMQ platforms.
 * Frames remain in the transport's application queue until the socket worker accepts them.
 *
 * <p>Disconnect detection uses a lightweight event hook on the main socket. The JeroMQ I/O thread only enqueues event
 * types; the socket owner consumes them. ZMTP heartbeats are enabled so peer crashes are surfaced even when the TCP FIN
 * is lost.
 */
public final class ZmqTransport implements CefTransport {

    private static final Logger LOG = LoggerFactory.getLogger(ZmqTransport.class);
    private static final int POLL_TIMEOUT_MS = 10;
    private static final int HEARTBEAT_INTERVAL_MS = 1_000;
    private static final int HEARTBEAT_TIMEOUT_MS = 360_000;
    private static final int HANDSHAKE_TIMEOUT_MS = 30_000;
    private static final int CLOSE_JOIN_TIMEOUT_MS = 3000;
    private static final int MAX_QUEUED_FRAMES = 4096;
    private static final AtomicInteger INSTANCE = new AtomicInteger();

    private static final class SharedContext {
        private static final Object LOCK = new Object();

        @Nullable
        private static ZContext context;

        private static int references;
        private static long generation;

        private SharedContext() {}

        private static Lease acquire() {
            synchronized (LOCK) {
                if (context == null) {
                    context = new ZContext(1);
                    generation++;
                }
                references++;
                return new Lease(context);
            }
        }

        private static long generation() {
            synchronized (LOCK) {
                return generation;
            }
        }

        private static void release(ZContext acquired) {
            synchronized (LOCK) {
                if (context != acquired || references <= 0) {
                    throw new IllegalStateException("JeroMQ context lease mismatch");
                }
                references--;
                if (references == 0) {
                    context = null;
                    acquired.close();
                }
            }
        }

        private static final class Lease implements AutoCloseable {
            private final ZContext context;
            private boolean closed;

            private Lease(ZContext context) {
                this.context = context;
            }

            private ZContext context() {
                return context;
            }

            @Override
            public void close() {
                if (closed) return;
                closed = true;
                release(context);
            }
        }
    }

    private volatile String endpoint;
    private final boolean runtimeServerClient;
    private final BooleanSupplier reconnectContinuity;
    private final int handshakeTimeoutMs;
    private final long handshakeTimeoutNanos;
    private final ConcurrentLinkedQueue<ZMonitor.Event> monitorEvents = new ConcurrentLinkedQueue<>();
    private final BlockingQueue<byte[]> outbound = new LinkedBlockingQueue<>(MAX_QUEUED_FRAMES);
    private final ArrayDeque<byte[]> pending = new ArrayDeque<>();
    private final Thread worker;

    @Nullable
    private volatile Consumer<ByteBuffer> receiveHandler;

    @Nullable
    private volatile Runnable disconnectHandler;

    private volatile boolean closed = false;
    private volatile boolean disconnected = false;
    private boolean tcpConnected = false;
    private boolean zmtpHandshaken = false;
    private boolean peerEstablished = false;
    private long handshakeDeadlineNanos = 0;
    private long reconnectDeadlineNanos = 0;
    private final AtomicBoolean disconnectNotified = new AtomicBoolean();
    private int sentFrames = 0;
    private int receivedFrames = 0;
    private boolean sendBlocked = false;

    /** Bind to the given endpoint (e.g. {@code tcp://127.0.0.1:0} for OS-assigned port). */
    public static ZmqTransport bind(@Nonnull String endpoint) {
        return new ZmqTransport(true, endpoint, HANDSHAKE_TIMEOUT_MS, () -> false);
    }

    static ZmqTransport bind(String endpoint, BooleanSupplier reconnectContinuity) {
        return new ZmqTransport(true, endpoint, HANDSHAKE_TIMEOUT_MS, reconnectContinuity);
    }

    /** Connect to a previously bound endpoint. */
    public static ZmqTransport connect(@Nonnull String endpoint) {
        return connect(endpoint, HANDSHAKE_TIMEOUT_MS);
    }

    @Nonnull
    public static ZmqTransport connect(@Nonnull String endpoint, @Nonnull BooleanSupplier reconnectContinuity) {
        return new ZmqTransport(false, endpoint, HANDSHAKE_TIMEOUT_MS, reconnectContinuity);
    }

    static ZmqTransport connect(String endpoint, int handshakeTimeoutMs) {
        return new ZmqTransport(false, endpoint, handshakeTimeoutMs, () -> false);
    }

    static ZmqTransport connect(String endpoint, int handshakeTimeoutMs, BooleanSupplier reconnectContinuity) {
        if (handshakeTimeoutMs <= 0) throw new IllegalArgumentException("handshakeTimeoutMs must be positive");
        return new ZmqTransport(false, endpoint, handshakeTimeoutMs, reconnectContinuity);
    }

    static long sharedContextGeneration() {
        return SharedContext.generation();
    }

    /** Resolved endpoint (for {@link #bind} this is the OS-assigned port; for {@link #connect} it is the input). */
    public String endpoint() {
        return endpoint;
    }

    private ZmqTransport(
            boolean isBind, String requestedEndpoint, int handshakeTimeoutMs, BooleanSupplier reconnectContinuity) {
        this.runtimeServerClient = !isBind;
        this.reconnectContinuity = java.util.Objects.requireNonNull(reconnectContinuity, "reconnectContinuity");
        this.handshakeTimeoutMs = handshakeTimeoutMs;
        this.handshakeTimeoutNanos = TimeUnit.MILLISECONDS.toNanos(handshakeTimeoutMs);
        this.endpoint = requestedEndpoint;
        int id = INSTANCE.incrementAndGet();
        CompletableFuture<String> setup = new CompletableFuture<>();
        this.worker = new Thread(() -> workerLoop(isBind, requestedEndpoint, setup), "zmq-worker-" + id);
        worker.setDaemon(true);
        worker.start();
        try {
            this.endpoint = setup.get(handshakeTimeoutMs, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            closed = true;
            joinQuietly(worker);
            throw new IllegalStateException("Unable to initialize ZeroMQ transport " + requestedEndpoint, e.getCause());
        } catch (TimeoutException e) {
            closed = true;
            worker.interrupt();
            joinQuietly(worker);
            throw new IllegalStateException(
                    "ZeroMQ transport setup timed out after " + handshakeTimeoutMs + "ms for " + requestedEndpoint, e);
        } catch (InterruptedException e) {
            closed = true;
            worker.interrupt();
            joinQuietly(worker);
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while initializing ZeroMQ transport " + requestedEndpoint, e);
        }
    }

    private void workerLoop(boolean isBind, String requestedEndpoint, CompletableFuture<String> setup) {
        SharedContext.Lease lease = SharedContext.acquire();
        ZMQ.Socket main = null;
        ZMQ.Poller poller = null;
        try {
            ZContext ctx = lease.context();
            main = ctx.createSocket(SocketType.DEALER);
            configureLiveness(main, handshakeTimeoutMs);

            int eventMask = ZMQ.EVENT_CONNECTED
                    | ZMQ.EVENT_ACCEPTED
                    | ZMQ.EVENT_DISCONNECTED
                    | ZMQ.EVENT_HANDSHAKE_PROTOCOL
                    | ZMQ.HANDSHAKE_SUCCEEDED;
            if (!main.setEventHook(event -> monitorEvents.add(event.getEvent()), eventMask)) {
                throw new IllegalStateException("Unable to monitor ZeroMQ transport " + requestedEndpoint);
            }

            if (isBind) {
                main.bind(requestedEndpoint);
                endpoint = main.getLastEndpoint();
            } else {
                main.connect(requestedEndpoint);
            }

            poller = ctx.createPoller(1);
            poller.register(main, ZMQ.Poller.POLLIN);
            setup.complete(endpoint);
            LOG.debug("worker on {} started", endpoint);
            while (!closed) {
                int n = poller.poll(POLL_TIMEOUT_MS);
                if (n < 0) break;
                if (drainMonitor()) break;
                if (poller.pollin(0) && drainIncoming(main)) break;
                restartStalledHandshake(main);
                if (!outbound.isEmpty()) drainOutbound(main);
                dispatchPendingIfReady();
            }
        } catch (ZMQException e) {
            if (!setup.completeExceptionally(e)) {
                LOG.debug("worker on {} exiting due to {}", endpoint, e.toString());
            }
        } catch (RuntimeException e) {
            if (!setup.completeExceptionally(e)) {
                LOG.warn("worker on {} failed", endpoint, e);
            }
        } finally {
            if (setup.isDone() && !setup.isCompletedExceptionally() && !closed) {
                disconnected = true;
                fireDisconnectIfReady();
            }
            try {
                if (poller != null) poller.close();
            } finally {
                try {
                    if (main != null) {
                        main.setLinger(0);
                        main.close();
                    }
                } catch (RuntimeException e) {
                    LOG.debug("socket close on {} threw {}", endpoint, e.toString());
                } finally {
                    lease.close();
                }
            }
            LOG.debug(
                    "worker on {} stopped (closed={}, disconnected={}, sent={}, received={})",
                    endpoint,
                    closed,
                    disconnected,
                    sentFrames,
                    receivedFrames);
        }
    }

    static void configureLiveness(ZMQ.Socket socket) {
        configureLiveness(socket, HANDSHAKE_TIMEOUT_MS);
    }

    private static void configureLiveness(ZMQ.Socket socket, int handshakeTimeoutMs) {
        socket.setLinger(0);
        socket.setImmediate(true);
        socket.setHeartbeatIvl(HEARTBEAT_INTERVAL_MS);
        socket.setHeartbeatTimeout(HEARTBEAT_TIMEOUT_MS);
        socket.setHandshakeIvl(handshakeTimeoutMs);
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException(endpoint + ": send on closed transport");
        if (disconnected) throw new CefTransportException(endpoint + ": peer disconnected");
        byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        if (!outbound.offer(copy)) {
            throw new CefTransportException(endpoint + ": outbound queue full (max " + MAX_QUEUED_FRAMES + " frames)");
        }
    }

    @Override
    public void onReceive(@Nonnull Consumer<ByteBuffer> handler) {
        this.receiveHandler = handler;
    }

    @Override
    public void onDisconnect(@Nonnull Runnable handler) {
        this.disconnectHandler = handler;
        fireDisconnectIfReady();
    }

    @Override
    public boolean isConnected() {
        return !closed && !disconnected;
    }

    @Override
    public boolean isRuntimeServerClient() {
        return runtimeServerClient;
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        if (Thread.currentThread() != worker) joinQuietly(worker);
    }

    private boolean drainMonitor() {
        ZMonitor.Event event;
        while ((event = monitorEvents.poll()) != null) {
            LOG.debug("monitor on {} received {}", endpoint, event);
            if (event == ZMonitor.Event.CONNECTED || event == ZMonitor.Event.ACCEPTED) {
                tcpConnected = true;
                if (!zmtpHandshaken && handshakeDeadlineNanos == 0) {
                    handshakeDeadlineNanos = System.nanoTime() + handshakeTimeoutNanos;
                }
            } else if (event == ZMonitor.Event.HANDSHAKE_SUCCEEDED) {
                if (!acceptReconnection()) continue;
                zmtpHandshaken = true;
                peerEstablished = true;
                reconnectDeadlineNanos = 0;
                handshakeDeadlineNanos = 0;
            } else if (event == ZMonitor.Event.HANDSHAKE_PROTOCOL) {
                zmtpHandshaken = false;
                if (peerEstablished) beginReconnectGrace();
            } else if (event == ZMonitor.Event.DISCONNECTED) {
                tcpConnected = false;
                zmtpHandshaken = false;
                handshakeDeadlineNanos = 0;
                if (runtimeServerClient && !peerEstablished) {
                    continue;
                }
                beginReconnectGrace();
            }
        }
        return disconnected || reconnectExpired();
    }

    private void beginReconnectGrace() {
        if (!reconnectContinuity.getAsBoolean()) {
            markTerminalDisconnected();
            return;
        }
        if (reconnectDeadlineNanos == 0) {
            reconnectDeadlineNanos = System.nanoTime() + handshakeTimeoutNanos;
        }
    }

    private boolean acceptReconnection() {
        if (reconnectDeadlineNanos == 0) return true;
        if (reconnectContinuity.getAsBoolean()) return true;
        markTerminalDisconnected();
        return false;
    }

    private boolean reconnectExpired() {
        if (reconnectDeadlineNanos == 0) return false;
        if (reconnectContinuity.getAsBoolean() && System.nanoTime() < reconnectDeadlineNanos) return false;
        markTerminalDisconnected();
        return true;
    }

    private void markTerminalDisconnected() {
        reconnectDeadlineNanos = 0;
        disconnected = true;
        fireDisconnectIfReady();
    }

    private void restartStalledHandshake(ZMQ.Socket main) {
        if (!runtimeServerClient || zmtpHandshaken || !tcpConnected || handshakeDeadlineNanos == 0) return;
        if (System.nanoTime() < handshakeDeadlineNanos) return;
        restartConnection(main, "handshake timeout");
    }

    private void restartConnection(ZMQ.Socket main, String reason) {
        LOG.debug("restarting {} after {}", endpoint, reason);
        zmtpHandshaken = false;
        main.disconnect(endpoint);
        main.connect(endpoint);
        tcpConnected = false;
        handshakeDeadlineNanos = 0;
    }

    private boolean drainIncoming(ZMQ.Socket main) {
        byte[] frame;
        while ((frame = main.recv(ZMQ.DONTWAIT)) != null) {
            if (!acceptReconnection()) return true;
            receivedFrames++;
            zmtpHandshaken = true;
            peerEstablished = true;
            reconnectDeadlineNanos = 0;
            handshakeDeadlineNanos = 0;
            if (receivedFrames == 1) LOG.debug("first frame received on {}", endpoint);
            pending.add(frame);
        }
        return false;
    }

    private void drainOutbound(ZMQ.Socket main) {
        byte[] out;
        while ((out = outbound.peek()) != null) {
            try {
                if (!main.send(out, ZMQ.DONTWAIT)) {
                    if (!sendBlocked) LOG.debug("send on {} waiting for a writable pipe", endpoint);
                    sendBlocked = true;
                    return;
                }
                sentFrames++;
                if (sentFrames == 1 || sendBlocked) LOG.debug("frame {} accepted for send on {}", sentFrames, endpoint);
                sendBlocked = false;
                outbound.poll();
            } catch (ZMQException e) {
                LOG.debug("send on {} failed: {}", endpoint, e.toString());
                return;
            }
        }
    }

    private void dispatchPendingIfReady() {
        Consumer<ByteBuffer> h = receiveHandler;
        if (h == null || pending.isEmpty()) return;
        byte[] f;
        while ((f = pending.poll()) != null) {
            try {
                h.accept(ByteBuffer.wrap(f));
            } catch (RuntimeException e) {
                LOG.warn("receive handler on {} threw", endpoint, e);
            }
        }
    }

    private void fireDisconnectIfReady() {
        Runnable handler = disconnectHandler;
        if (disconnected && !closed && handler != null && disconnectNotified.compareAndSet(false, true)) {
            try {
                handler.run();
            } catch (RuntimeException e) {
                LOG.warn("disconnect handler on {} threw", endpoint, e);
            }
        }
    }

    private static void joinQuietly(Thread t) {
        try {
            t.join(CLOSE_JOIN_TIMEOUT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
