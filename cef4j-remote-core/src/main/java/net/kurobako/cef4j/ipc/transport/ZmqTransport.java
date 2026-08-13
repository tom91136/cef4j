package net.kurobako.cef4j.ipc.transport;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
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
 * JeroMQ-backed {@link CefTransport} using PAIR sockets. Construct via {@link #bind} or {@link #connect}.
 *
 * <p>Internals: a single worker thread owns the main socket (ZMQ sockets are not thread-safe). External {@link #send}
 * callers enqueue bytes onto a {@link BlockingQueue} and signal the worker via an in-process PAIR pipe; the worker
 * polls both the main socket and the wake pipe, draining outbound bytes after each wake.
 *
 * <p>Disconnect detection uses {@link ZMonitor} on the main socket; ZMTP heartbeats are enabled so peer crashes are
 * surfaced even when the TCP FIN is lost.
 */
public final class ZmqTransport implements CefTransport {

    private static final Logger LOG = LoggerFactory.getLogger(ZmqTransport.class);
    private static final byte[] EMPTY = new byte[0];
    private static final int POLL_TIMEOUT_MS = 200;
    private static final int MONITOR_TIMEOUT_MS = 500;
    private static final int CLOSE_JOIN_TIMEOUT_MS = 3000;
    private static final int CLOSE_BUDGET_MS = 5000;
    private static final AtomicInteger INSTANCE = new AtomicInteger();

    private final String endpoint;
    private final boolean runtimeServerClient;
    private final ZContext ctx;
    private final ZMQ.Socket main;
    private final ZMQ.Socket inprocWorker;
    private final ZMQ.Socket inprocSender;
    private final BlockingQueue<byte[]> outbound = new LinkedBlockingQueue<>();
    private final ArrayDeque<byte[]> pending = new ArrayDeque<>();
    private final Object wakeLock = new Object();
    private final Thread worker;
    private final Thread monitorThread;
    private final ZMonitor monitor;

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
        this.ctx = new ZContext();
        this.main = ctx.createSocket(SocketType.PAIR);
        main.setLinger(0);
        // ZMTP heartbeats so silent peer death (kill -9) is surfaced via ZMonitor.
        main.setHeartbeatIvl(500);
        main.setHeartbeatTimeout(2000);

        String wakeAddr = "inproc://zmq-wake-" + UUID.randomUUID();
        this.inprocWorker = ctx.createSocket(SocketType.PAIR);
        this.inprocSender = ctx.createSocket(SocketType.PAIR);
        // Default ZMQ_LINGER is -1 (infinite). Without overriding, ctx.close() blocks waiting for queued
        // wake messages to be received by the soon-to-be-closed peer. Set to 0 for immediate teardown.
        inprocWorker.setLinger(0);
        inprocSender.setLinger(0);
        inprocWorker.bind(wakeAddr);
        inprocSender.connect(wakeAddr);

        this.monitor = new ZMonitor(ctx, main);
        monitor.add(ZMonitor.Event.CONNECTED);
        monitor.add(ZMonitor.Event.ACCEPTED);
        monitor.add(ZMonitor.Event.CONNECT_RETRIED);
        monitor.add(ZMonitor.Event.DISCONNECTED);
        monitor.start();

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

        this.monitorThread = new Thread(this::monitorLoop, "zmq-monitor-" + id);
        monitorThread.setDaemon(true);
        monitorThread.start();
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException(endpoint + ": send on closed transport");
        if (disconnected) throw new CefTransportException(endpoint + ": peer disconnected");
        byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        outbound.add(copy);
        wake();
    }

    @Override
    public void onReceive(@Nonnull Consumer<ByteBuffer> handler) {
        this.receiveHandler = handler;
        wake();
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
        // Both monitor.close() and ctx.close() can deadlock when jeromq's iothread has died
        // mid-flight (a documented bug pattern under heavy churn). The deadlocks block on inproc
        // mailbox acks that never arrive. Run the whole shutdown on a daemon side-thread with a
        // hard budget — if it doesn't finish in time we abandon the leaked sockets/context and
        // let the JVM reclaim them on exit. The tests that exercise close are short-lived so a
        // leaked context is harmless; the alternative (forever-hang) wedges the surefire fork.
        Thread shutdown = new Thread(
                () -> {
                    // Let the two Java owner threads leave their bounded polls before closing the context. Closing
                    // the context while an IO thread is still acknowledging termination can make JeroMQ write to an
                    // already-closed signal channel and print an uncaught teardown exception.
                    joinQuietly(worker);
                    joinQuietly(monitorThread);
                    try {
                        monitor.close();
                    } catch (RuntimeException e) {
                        LOG.debug("monitor close on {} threw {}", endpoint, e.toString());
                    }
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

    private void wake() {
        synchronized (wakeLock) {
            if (closed) return;
            try {
                inprocSender.send(EMPTY, ZMQ.DONTWAIT);
            } catch (RuntimeException ignored) {
                // wake is best-effort; if the socket is gone the worker will exit on its own
            }
        }
    }

    private void workerLoop() {
        ZMQ.Poller poller = ctx.createPoller(2);
        poller.register(main, ZMQ.Poller.POLLIN);
        poller.register(inprocWorker, ZMQ.Poller.POLLIN);
        try {
            while (!closed) {
                int n = poller.poll(POLL_TIMEOUT_MS);
                if (n < 0) break;
                if (poller.pollin(0)) drainIncoming();
                if (poller.pollin(1)) {
                    drainWakeSignals();
                }
                // Also check after the bounded poll: the monitor's wake may race creation of the
                // inproc pipe and be dropped, but queued application frames must still be flushed.
                if (peerReady && !outbound.isEmpty()) drainOutbound();
                dispatchPendingIfReady();
            }
        } catch (ZMQException e) {
            LOG.debug("worker on {} exiting due to {}", endpoint, e.toString());
        } finally {
            poller.close();
        }
    }

    private void drainIncoming() {
        byte[] frame;
        while ((frame = main.recv(ZMQ.DONTWAIT)) != null) {
            pending.add(frame);
        }
    }

    private void drainWakeSignals() {
        while (inprocWorker.recv(ZMQ.DONTWAIT) != null) {
            // discard
        }
    }

    private void drainOutbound() {
        byte[] out;
        while ((out = outbound.poll()) != null) {
            try {
                main.send(out, 0);
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

    private void monitorLoop() {
        try {
            while (true) {
                ZMonitor.ZEvent ev = monitor.nextEvent(MONITOR_TIMEOUT_MS);
                if (ev == null) {
                    if (closed) return;
                    continue;
                }
                if (ev.type == ZMonitor.Event.CONNECTED || ev.type == ZMonitor.Event.ACCEPTED) {
                    // A PAIR send before the ZMTP connection exists is not reliably queued by every
                    // JeroMQ/platform combination. Retain outbound messages until the monitor confirms
                    // the peer, then wake the socket-owner thread to flush them.
                    peerReady = true;
                    wake();
                } else if (ev.type == ZMonitor.Event.DISCONNECTED
                        || (ev.type == ZMonitor.Event.CONNECT_RETRIED && peerReady)) {
                    if (closed) return; // suppress event triggered by local close
                    disconnected = true;
                    fireDisconnectIfReady();
                    return;
                }
            }
        } catch (RuntimeException e) {
            LOG.debug("monitor on {} exiting due to {}", endpoint, e.toString());
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
