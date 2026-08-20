package net.kurobako.cef4j.ipc.transport;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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
    private static final int HEARTBEAT_TIMEOUT_MS = 10_000;
    private static final int HANDSHAKE_TIMEOUT_MS = 10_000;
    private static final long HANDSHAKE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(HANDSHAKE_TIMEOUT_MS);
    private static final int CLOSE_JOIN_TIMEOUT_MS = 3000;
    private static final int MAX_QUEUED_FRAMES = 4096;
    private static final AtomicInteger INSTANCE = new AtomicInteger();

    /**
     * JeroMQ recommends one context per process. Each worker owns a shadow so its socket lifecycle remains isolated,
     * while rapid transport restart does not repeatedly create and tear down JeroMQ's I/O infrastructure.
     */
    private static final class SharedContext {
        private static final ZContext INSTANCE = new ZContext(1);
    }

    private volatile String endpoint;
    private final boolean runtimeServerClient;
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
    private volatile boolean peerReady = false;
    private boolean tcpConnected = false;
    private boolean zmtpHandshaken = false;
    private long handshakeDeadlineNanos = 0;
    private final AtomicBoolean disconnectNotified = new AtomicBoolean();
    // Worker-owned diagnostics; only emitted at DEBUG and useful for distinguishing an established-but-stalled pipe
    // from a worker/socket failure.
    private int sentFrames = 0;
    private int receivedFrames = 0;
    private boolean sendBlocked = false;

    /** Bind to the given endpoint (e.g. {@code tcp://127.0.0.1:0} for OS-assigned port). */
    public static ZmqTransport bind(@Nonnull String endpoint) {
        return new ZmqTransport(true, endpoint);
    }

    /** Connect to a previously bound endpoint. */
    public static ZmqTransport connect(@Nonnull String endpoint) {
        return new ZmqTransport(false, endpoint);
    }

    /** Resolved endpoint (for {@link #bind} this is the OS-assigned port; for {@link #connect} it is the input). */
    public String endpoint() {
        return endpoint;
    }

    private ZmqTransport(boolean isBind, String requestedEndpoint) {
        this.runtimeServerClient = !isBind;
        this.endpoint = requestedEndpoint;
        int id = INSTANCE.incrementAndGet();
        CompletableFuture<String> setup = new CompletableFuture<>();
        this.worker = new Thread(() -> workerLoop(isBind, requestedEndpoint, setup), "zmq-worker-" + id);
        worker.setDaemon(true);
        worker.start();
        try {
            this.endpoint = setup.join();
        } catch (CompletionException e) {
            closed = true;
            joinQuietly(worker);
            throw new IllegalStateException("Unable to initialize ZeroMQ transport " + requestedEndpoint, e.getCause());
        }
    }

    private void workerLoop(boolean isBind, String requestedEndpoint, CompletableFuture<String> setup) {
        // Create, use and close both the context shadow and socket on this thread. JeroMQ sockets are thread-confined,
        // so the transport never hands a live socket between its construction and worker threads.
        ZContext ctx = SharedContext.INSTANCE.shadow();
        ZMQ.Socket main = ctx.createSocket(SocketType.DEALER);
        try {
            main.setLinger(0);
            main.setImmediate(true);
            // ZMTP heartbeats surface silent remote peer death. Allow a saturated or temporarily suspended host enough
            // time to resume: a two-second timeout produced false disconnects during concurrent native CI builds.
            // Local runtime servers still have immediate Process.onExit supervision independent of this timeout.
            main.setHeartbeatIvl(HEARTBEAT_INTERVAL_MS);
            main.setHeartbeatTimeout(HEARTBEAT_TIMEOUT_MS);
            // Bound and connected sockets both need a finite native handshake interval. Without it, JeroMQ can
            // occasionally leave a rapidly replaced DEALER pipe half-open indefinitely, so the first queued frame
            // never reaches the peer. The client-side monitor recovery below remains a second line of defence.
            main.setHandshakeIvl(HANDSHAKE_TIMEOUT_MS);

            int eventMask = ZMQ.EVENT_CONNECTED
                    | ZMQ.EVENT_ACCEPTED
                    | ZMQ.EVENT_DISCONNECTED
                    | ZMQ.EVENT_HANDSHAKE_PROTOCOL
                    | ZMQ.HANDSHAKE_SUCCEEDED;
            // The event callback runs on JeroMQ's I/O thread. It only transfers immutable event values to the socket
            // owner; application callbacks and socket operations stay on this worker.
            if (!main.setEventHook(event -> monitorEvents.add(event.getEvent()), eventMask)) {
                throw new IllegalStateException("Unable to monitor ZeroMQ transport " + requestedEndpoint);
            }

            if (isBind) {
                main.bind(requestedEndpoint);
                endpoint = main.getLastEndpoint();
            } else {
                main.connect(requestedEndpoint);
            }
        } catch (RuntimeException e) {
            setup.completeExceptionally(e);
            ctx.close();
            return;
        }
        ZMQ.Poller poller = ctx.createPoller(1);
        poller.register(main, ZMQ.Poller.POLLIN);
        setup.complete(endpoint);
        LOG.debug("worker on {} started", endpoint);
        try {
            while (!closed) {
                int n = poller.poll(POLL_TIMEOUT_MS);
                if (n < 0) break;
                if (poller.pollin(0)) drainIncoming(main);
                if (drainMonitor()) break;
                restartStalledHandshake(main);
                // Only this worker touches the socket. send() callers may run on arbitrary
                // application threads and communicate solely through the thread-safe queue.
                if (!outbound.isEmpty()) drainOutbound(main);
                dispatchPendingIfReady();
            }
        } catch (ZMQException e) {
            LOG.debug("worker on {} exiting due to {}", endpoint, e.toString());
        } finally {
            if (!closed) {
                // The worker died without a DISCONNECTED monitor event (fatal poll/socket error). Surface the
                // disconnect so pending sessions fail instead of hanging on a dead pipe.
                disconnected = true;
                fireDisconnectIfReady();
            }
            poller.close();
            // ZeroMQ sockets are thread-confined. The worker is the sole socket owner, so it explicitly closes the
            // socket before closing the context.
            try {
                main.setLinger(0);
                main.close();
            } catch (RuntimeException e) {
                LOG.debug("socket close on {} threw {}", endpoint, e.toString());
            }
            try {
                ctx.close();
            } catch (RuntimeException e) {
                LOG.debug("ctx close on {} threw {}", endpoint, e.toString());
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
        // close() is also legal from a receive/disconnect callback, which runs on the worker. In that case the loop's
        // finally block performs ownership-correct cleanup as soon as the callback returns.
        if (Thread.currentThread() != worker) joinQuietly(worker);
    }

    private boolean drainMonitor() {
        ZMonitor.Event event;
        while ((event = monitorEvents.poll()) != null) {
            LOG.debug("monitor on {} received {}", endpoint, event);
            if (event == ZMonitor.Event.CONNECTED || event == ZMonitor.Event.ACCEPTED) {
                tcpConnected = true;
                peerReady = true;
                disconnected = false;
                if (!zmtpHandshaken && handshakeDeadlineNanos == 0) {
                    handshakeDeadlineNanos = System.nanoTime() + HANDSHAKE_TIMEOUT_NANOS;
                }
            } else if (event == ZMonitor.Event.HANDSHAKE_PROTOCOL || event == ZMonitor.Event.HANDSHAKE_SUCCEEDED) {
                zmtpHandshaken = true;
                handshakeDeadlineNanos = 0;
            } else if (event == ZMonitor.Event.DISCONNECTED) {
                tcpConnected = false;
                zmtpHandshaken = false;
                handshakeDeadlineNanos = 0;
                if (!peerReady) continue;
                disconnected = true;
                fireDisconnectIfReady();
            }
        }
        return false;
    }

    private void restartStalledHandshake(ZMQ.Socket main) {
        if (!runtimeServerClient || zmtpHandshaken || !tcpConnected || handshakeDeadlineNanos == 0) return;
        if (receivedFrames > 0) return;
        if (System.nanoTime() < handshakeDeadlineNanos) return;
        restartConnection(main, "handshake timeout");
    }

    private void restartConnection(ZMQ.Socket main, String reason) {
        LOG.debug("restarting {} after {}", endpoint, reason);
        peerReady = false;
        zmtpHandshaken = false;
        main.disconnect(endpoint);
        main.connect(endpoint);
        tcpConnected = false;
        handshakeDeadlineNanos = 0;
    }

    private void drainIncoming(ZMQ.Socket main) {
        byte[] frame;
        while ((frame = main.recv(ZMQ.DONTWAIT)) != null) {
            receivedFrames++;
            zmtpHandshaken = true;
            handshakeDeadlineNanos = 0;
            if (receivedFrames == 1) LOG.debug("first frame received on {}", endpoint);
            pending.add(frame);
        }
    }

    private void drainOutbound(ZMQ.Socket main) {
        byte[] out;
        while ((out = outbound.peek()) != null) {
            try {
                // Never block the socket-owner thread: it must continue polling inbound frames and shutdown signals.
                // A false return means the high-water mark is full; leave the head queued and retry after the bounded
                // poll.
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
