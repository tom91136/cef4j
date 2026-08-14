package net.kurobako.cef4j.ipc.transport;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
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
 * {@code ZMQ_IMMEDIATE} keeps application frames in the Java-side outbound queue until the socket has an established
 * ZMTP pipe.
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
    private static final int CLOSE_JOIN_TIMEOUT_MS = 3000;
    private static final int CLOSE_BUDGET_MS = 5000;
    private static final AtomicInteger INSTANCE = new AtomicInteger();

    /**
     * One ZeroMQ context per JVM is the intended usage model. Each transport gets a shadow context so closing it still
     * closes exactly its own sockets without repeatedly terminating and recreating JeroMQ's shared I/O thread.
     */
    private static final class SharedContext {
        private static final ZContext INSTANCE = new ZContext();
    }

    private final String endpoint;
    private final boolean runtimeServerClient;
    private final ZContext ctx;
    private final ZMQ.Socket main;
    private final ConcurrentLinkedQueue<ZMonitor.Event> monitorEvents = new ConcurrentLinkedQueue<>();
    private final BlockingQueue<byte[]> outbound = new LinkedBlockingQueue<>();
    private final ArrayDeque<byte[]> pending = new ArrayDeque<>();
    private final Thread worker;

    @Nullable
    private volatile Consumer<ByteBuffer> receiveHandler;

    @Nullable
    private volatile Runnable disconnectHandler;

    private volatile boolean closed = false;
    private volatile boolean disconnected = false;
    private volatile boolean peerReady = false;
    private final AtomicBoolean disconnectNotified = new AtomicBoolean();

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
        int id = INSTANCE.incrementAndGet();
        this.ctx = SharedContext.INSTANCE.shadow();
        this.main = ctx.createSocket(SocketType.DEALER);
        main.setLinger(0);
        // Without IMMEDIATE, ZeroMQ may accept a send into a not-yet-connected pipe and later discard it if that pipe
        // is replaced during handshake/reconnect. A false DONTWAIT result is exactly the backpressure signal the
        // Java-side queue needs in order to retain and retry the frame.
        main.setImmediate(true);
        // ZMTP heartbeats surface silent remote peer death. Allow a saturated or temporarily suspended host enough
        // time to resume: a two-second timeout produced false disconnects during concurrent native CI builds. Local
        // runtime servers still have immediate Process.onExit supervision independent of this network timeout.
        main.setHeartbeatIvl(HEARTBEAT_INTERVAL_MS);
        main.setHeartbeatTimeout(HEARTBEAT_TIMEOUT_MS);

        int eventMask = ZMQ.EVENT_CONNECTED | ZMQ.EVENT_ACCEPTED | ZMQ.EVENT_CONNECT_RETRIED | ZMQ.EVENT_DISCONNECTED;
        // JeroMQ invokes this on its I/O thread. Never call application code or touch the socket here: enqueue the
        // immutable event type and let the transport worker own all resulting state transitions.
        if (!main.setEventHook(event -> monitorEvents.add(event.getEvent()), eventMask)) {
            main.close();
            ctx.close();
            throw new IllegalStateException("Unable to monitor ZeroMQ transport " + requestedEndpoint);
        }

        if (isBind) {
            main.bind(requestedEndpoint);
            this.endpoint = main.getLastEndpoint();
        } else {
            main.connect(requestedEndpoint);
            this.endpoint = requestedEndpoint;
        }

        this.worker = new Thread(this::workerLoop, "zmq-worker-" + id);
        worker.setDaemon(true);
        worker.start();
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException(endpoint + ": send on closed transport");
        if (disconnected) throw new CefTransportException(endpoint + ": peer disconnected");
        byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        outbound.add(copy);
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
        // JeroMQ shutdown can deadlock if its I/O thread has died mid-flight. Run the transport's shadow-context
        // shutdown on a daemon side-thread with a hard budget. The process-wide parent context remains live for other
        // transports; abandoning a stuck shadow is preferable to wedging the application during close.
        Thread shutdown = new Thread(
                () -> {
                    // Let the Java owner thread leave its bounded poll before closing this transport's shadow.
                    joinQuietly(worker);
                    try {
                        ctx.close();
                    } catch (RuntimeException e) {
                        LOG.debug("ctx close on {} threw {}", endpoint, e.toString());
                    }
                },
                "zmq-shutdown-" + endpoint);
        shutdown.setDaemon(true);
        shutdown.start();
        try {
            shutdown.join(CLOSE_BUDGET_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (shutdown.isAlive()) {
            LOG.debug("close on {} did not return within {}ms; abandoning", endpoint, CLOSE_BUDGET_MS);
        }
    }

    private void workerLoop() {
        ZMQ.Poller poller = ctx.createPoller(1);
        poller.register(main, ZMQ.Poller.POLLIN);
        try {
            while (!closed) {
                int n = poller.poll(POLL_TIMEOUT_MS);
                if (n < 0) break;
                if (poller.pollin(0)) drainIncoming();
                if (drainMonitor()) break;
                // Only this worker touches the socket. send() callers may run on arbitrary
                // application threads and communicate solely through the thread-safe queue.
                if (!outbound.isEmpty()) drainOutbound();
                dispatchPendingIfReady();
            }
        } catch (ZMQException e) {
            LOG.debug("worker on {} exiting due to {}", endpoint, e.toString());
        } finally {
            poller.close();
        }
    }

    private boolean drainMonitor() {
        ZMonitor.Event event;
        while ((event = monitorEvents.poll()) != null) {
            if (event == ZMonitor.Event.CONNECTED || event == ZMonitor.Event.ACCEPTED) {
                peerReady = true;
            } else if (event == ZMonitor.Event.DISCONNECTED || (event == ZMonitor.Event.CONNECT_RETRIED && peerReady)) {
                if (closed) return true; // suppress event triggered by local close
                disconnected = true;
                fireDisconnectIfReady();
                return true;
            }
        }
        return false;
    }

    private void drainIncoming() {
        byte[] frame;
        while ((frame = main.recv(ZMQ.DONTWAIT)) != null) {
            pending.add(frame);
        }
    }

    private void drainOutbound() {
        byte[] out;
        while ((out = outbound.peek()) != null) {
            try {
                // Never block the socket-owner thread: it must continue polling inbound frames and shutdown signals.
                // A false return means the high-water mark is full; leave the head queued and retry after the bounded
                // poll.
                if (!main.send(out, ZMQ.DONTWAIT)) return;
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
