package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.ipc.transport.CefTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Default {@link CefSession} on top of any {@link CefTransport}. */
public final class CefSessionImpl implements CefSession {

    private static final Logger LOG = LoggerFactory.getLogger(CefSessionImpl.class);
    private static final int RUNTIME_SESSION_READY_MESSAGE_ID = 0;
    private static final int RUNTIME_SESSION_READY_CORR_ID = 0;
    private static final long RUNTIME_SESSION_READY_RETRY_NANOS = TimeUnit.MILLISECONDS.toNanos(250);
    private static final Duration RUNTIME_SESSION_READY_TIMEOUT = Duration.ofMinutes(5);

    private final CefTransport transport;
    private final Duration defaultTimeout;
    private final ScheduledExecutorService timer;
    private final boolean ownTimer;

    private final IntIdAllocator correlationIds = new IntIdAllocator();
    private final AtomicLong nextEventSequence = new AtomicLong(0);
    private final ConcurrentHashMap<Integer, Pending<?>> pending = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, CopyOnWriteArrayList<EventBinding<?>>> eventHandlers =
            new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, LatestEvent> latestEvents = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, InterceptBinding<?>> interceptHandlers = new ConcurrentHashMap<>();
    private final Object registrationLock = new Object();
    private final CopyOnWriteArrayList<Runnable> closeHandlers = new CopyOnWriteArrayList<>();
    private final java.util.concurrent.atomic.AtomicBoolean closeNotified =
            new java.util.concurrent.atomic.AtomicBoolean();
    private final java.util.concurrent.atomic.AtomicBoolean closeStarted =
            new java.util.concurrent.atomic.AtomicBoolean();
    private final java.util.concurrent.atomic.AtomicBoolean disconnectHandled =
            new java.util.concurrent.atomic.AtomicBoolean();
    private final CompletableFuture<Void> runtimeSessionReady = new CompletableFuture<>();

    private volatile boolean closed = false;

    public CefSessionImpl(@Nonnull CefTransport transport) {
        this(transport, Duration.ofSeconds(30));
    }

    public CefSessionImpl(@Nonnull CefTransport transport, @Nonnull Duration defaultTimeout) {
        this(transport, defaultTimeout, defaultTimer(), true);
    }

    public CefSessionImpl(
            @Nonnull CefTransport transport,
            @Nonnull Duration defaultTimeout,
            @Nonnull ScheduledExecutorService timer) {
        this(transport, defaultTimeout, timer, false);
    }

    private CefSessionImpl(
            CefTransport transport, Duration defaultTimeout, ScheduledExecutorService timer, boolean ownTimer) {
        this.transport = transport;
        this.defaultTimeout = defaultTimeout;
        this.timer = timer;
        this.ownTimer = ownTimer;
        transport.onReceive(this::handleFrame);
        transport.onDisconnect(this::handleDisconnect);
        if (transport.isRuntimeServerClient()) establishRuntimeSession();
    }

    private void establishRuntimeSession() {
        ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(
                buf, Envelope.Kind.REQUEST, 0, RUNTIME_SESSION_READY_CORR_ID, RUNTIME_SESSION_READY_MESSAGE_ID, 0);
        buf.flip();
        // Request timeouts begin only after startup. The transport may legitimately spend several minutes completing
        // its native handshake, so the TID_UI readiness barrier needs an independent budget rather than consuming the
        // caller's per-request timeout before a session exists.
        long deadline = System.nanoTime() + RUNTIME_SESSION_READY_TIMEOUT.toNanos();
        try {
            while (!runtimeSessionReady.isDone()) {
                // SessionReady is an idempotent bootstrap barrier. Retransmit it until acknowledged so a transport
                // connection becoming writable concurrently with the first send cannot strand startup forever.
                transport.send(buf.duplicate());
                long remaining = deadline - System.nanoTime();
                if (remaining <= 0) throw new TimeoutException();
                try {
                    runtimeSessionReady.get(
                            Math.min(remaining, RUNTIME_SESSION_READY_RETRY_NANOS), TimeUnit.NANOSECONDS);
                } catch (TimeoutException retry) {
                    if (deadline - System.nanoTime() <= 0) throw retry;
                }
            }
        } catch (CefTransportException e) {
            if (ownTimer) timer.shutdownNow();
            throw new IllegalStateException("failed to establish runtime server session", e);
        } catch (java.util.concurrent.ExecutionException e) {
            transport.close();
            if (ownTimer) timer.shutdownNow();
            throw new IllegalStateException(
                    "runtime server did not acknowledge session readiness", e.getCause() == null ? e : e.getCause());
        } catch (TimeoutException e) {
            transport.close();
            if (ownTimer) timer.shutdownNow();
            throw new IllegalStateException("runtime server did not acknowledge session readiness", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            transport.close();
            if (ownTimer) timer.shutdownNow();
            throw new IllegalStateException("interrupted while establishing runtime server session", e);
        }
    }

    private static ScheduledExecutorService defaultTimer() {
        return Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "cef-session-timer");
            t.setDaemon(true);
            return t;
        });
    }

    @Override
    @Nonnull
    public <R extends CefMessageView> CompletableFuture<R> request(
            @Nonnull CefMessageEncoder enc, @Nonnull CefMessageDecoder<R> dec) {
        RequestFuture<R> future = new RequestFuture<>();
        if (closed) {
            future.completeExceptionally(new CefTransportException("session closed"));
            return future;
        }
        Pending<R> p = new Pending<>(future, dec, enc.messageId());
        int corrId = correlationIds.allocate(candidate -> pending.putIfAbsent(candidate, p) == null);
        if (closed) {
            pending.remove(corrId, p);
            future.completeExceptionally(new CefTransportException("session closed"));
            return future;
        }
        ScheduledFuture<?> timeoutTask;
        try {
            timeoutTask = timer.schedule(
                    () -> {
                        if (pending.remove(corrId, p)) {
                            p.future.completeExceptionally(new TimeoutException("request msgId=" + enc.messageId()
                                    + " corrId=" + corrId + " timed out after " + defaultTimeout));
                        }
                    },
                    defaultTimeout.toMillis(),
                    TimeUnit.MILLISECONDS);
            p.timeoutTask = timeoutTask;
        } catch (RuntimeException schedulingFailure) {
            pending.remove(corrId, p);
            future.completeExceptionally(schedulingFailure);
            return future;
        }
        future.onCancel = () -> {
            if (pending.remove(corrId, p)) cancelQuietly(p.timeoutTask);
        };
        if (future.isCancelled()) future.onCancel.run();
        if (closed) {
            pending.remove(corrId, p);
            timeoutTask.cancel(false);
            future.completeExceptionally(new CefTransportException("session closed"));
            return future;
        }

        try {
            ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE + enc.encodedSize())
                    .order(ByteOrder.LITTLE_ENDIAN);
            Envelope.writeHeader(buf, Envelope.Kind.REQUEST, 0, corrId, enc.messageId(), enc.encodedSize());
            enc.encodeInto(buf);
            buf.flip();
            transport.send(buf);
        } catch (CefTransportException e) {
            pending.remove(corrId, p);
            timeoutTask.cancel(false);
            future.completeExceptionally(e);
        } catch (RuntimeException encodeFailure) {
            pending.remove(corrId, p);
            timeoutTask.cancel(false);
            future.completeExceptionally(encodeFailure);
        }
        return future;
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration on(
            int messageId, @Nonnull CefMessageDecoder<E> dec, @Nonnull Consumer<E> handler) {
        EventBinding<E> binding = new EventBinding<>(dec, handler);
        synchronized (registrationLock) {
            requireOpen();
            eventHandlers
                    .computeIfAbsent(messageId, k -> new CopyOnWriteArrayList<>())
                    .add(binding);
        }
        return () -> {
            CopyOnWriteArrayList<EventBinding<?>> list = eventHandlers.get(messageId);
            if (list != null && list.remove(binding) && list.isEmpty()) eventHandlers.remove(messageId, list);
        };
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration onLatest(
            int messageId, @Nonnull CefMessageDecoder<E> dec, @Nonnull Consumer<E> handler) {
        EventBinding<E> binding = new EventBinding<>(dec, handler);
        synchronized (registrationLock) {
            requireOpen();
            eventHandlers
                    .computeIfAbsent(messageId, k -> new CopyOnWriteArrayList<>())
                    .add(binding);
        }
        LatestEvent latest = latestEvents.get(messageId);
        if (latest != null) binding.dispatch(latest.sequence, latest.payload());
        return () -> {
            CopyOnWriteArrayList<EventBinding<?>> list = eventHandlers.get(messageId);
            if (list != null && list.remove(binding) && list.isEmpty()) eventHandlers.remove(messageId, list);
        };
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration intercept(
            int messageId, @Nonnull CefMessageDecoder<E> dec, @Nonnull InterceptHandler<E> handler) {
        InterceptBinding<E> binding = new InterceptBinding<>(dec, handler);
        InterceptBinding<?> previous;
        synchronized (registrationLock) {
            requireOpen();
            previous = interceptHandlers.put(messageId, binding);
        }
        if (previous != null) {
            LOG.warn("intercept handler for messageId={} replaced", messageId);
        }
        return () -> interceptHandlers.remove(messageId, binding);
    }

    @Override
    @Nonnull
    public HandlerRegistration onClose(@Nonnull Runnable handler) {
        java.util.Objects.requireNonNull(handler, "handler");
        if (closed) {
            handler.run();
            return () -> {};
        }
        closeHandlers.add(handler);
        if (closed && closeHandlers.remove(handler)) handler.run();
        return () -> closeHandlers.remove(handler);
    }

    @Override
    public void close() {
        if (!closeStarted.compareAndSet(false, true)) return;
        disconnectHandled.set(true);
        synchronized (registrationLock) {
            closed = true;
            eventHandlers.clear();
            interceptHandlers.clear();
        }
        failAllPending(new CefTransportException("session closed"));
        notifyClosed();
        latestEvents.clear();
        transport.close();
        if (ownTimer) timer.shutdownNow();
    }

    private void failAllPending(Throwable cause) {
        for (Map.Entry<Integer, Pending<?>> e : pending.entrySet()) {
            Pending<?> p = e.getValue();
            cancelQuietly(p.timeoutTask);
            p.future.completeExceptionally(cause);
        }
        pending.clear();
    }

    private static void cancelQuietly(@Nullable ScheduledFuture<?> task) {
        if (task != null) task.cancel(false);
    }

    private void handleFrame(ByteBuffer raw) {
        ByteBuffer buf = raw.duplicate();
        Envelope.Header h;
        try {
            h = Envelope.readHeader(buf);
        } catch (RuntimeException e) {
            LOG.warn("malformed envelope, dropping frame", e);
            return;
        }
        ByteBuffer payload = buf.slice();
        switch (h.kind) {
            case RESPONSE:
                handleResponse(h, payload);
                break;
            case EVENT:
                handleEvent(h, payload);
                break;
            case INTERCEPT:
                handleIntercept(h, payload);
                break;
            case ERROR:
                handleError(h, payload);
                break;
            case REQUEST:
            case INTERCEPT_RESPONSE:
            default:
                LOG.warn("unexpected inbound kind={} corrId={} messageId={}", h.kind, h.corrId, h.messageId);
        }
    }

    /**
     * Server signalled a structured error for our pending corrId — typically RECEIVER_GONE because the handle was
     * already released. Payload layout: int32 code, int32 utf8MessageLength, utf8 bytes. Completes the pending future
     * with {@link CefRemoteException} so the caller's {@code .get()} surfaces a real failure instead of a
     * default-zeroed value.
     */
    private void handleError(Envelope.Header h, ByteBuffer payload) {
        if (h.messageId == RUNTIME_SESSION_READY_MESSAGE_ID && h.corrId == RUNTIME_SESSION_READY_CORR_ID) {
            completeStructuredError(runtimeSessionReady, payload);
            return;
        }
        Pending<?> raw = pending.get(h.corrId);
        if (raw == null) {
            LOG.debug("orphan error corrId={} (ignored)", h.corrId);
            return;
        }
        if (raw.messageId != h.messageId) {
            LOG.warn(
                    "mismatched error corrId={} expected messageId={} but received {} (ignored)",
                    h.corrId,
                    raw.messageId,
                    h.messageId);
            return;
        }
        if (!pending.remove(h.corrId, raw)) return;
        cancelQuietly(raw.timeoutTask);
        completeStructuredError(raw.future, payload);
    }

    private static void completeStructuredError(CompletableFuture<?> future, ByteBuffer payload) {
        try {
            ByteBuffer source = payload.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            WireDecoder.requireRemaining(source, Integer.BYTES, "error.code");
            int code = source.getInt();
            int messageLength = WireDecoder.length(source, "error.message");
            byte[] message = new byte[messageLength];
            source.get(message);
            WireDecoder.requireFullyConsumed(source, "structured error");
            future.completeExceptionally(
                    new CefRemoteException(code, new String(message, java.nio.charset.StandardCharsets.UTF_8)));
        } catch (RuntimeException malformed) {
            future.completeExceptionally(malformed);
        }
    }

    @SuppressWarnings("unchecked")
    private <R extends CefMessageView> void handleResponse(Envelope.Header h, ByteBuffer payload) {
        if (h.messageId == RUNTIME_SESSION_READY_MESSAGE_ID && h.corrId == RUNTIME_SESSION_READY_CORR_ID) {
            runtimeSessionReady.complete(null);
            return;
        }
        Pending<?> raw = pending.get(h.corrId);
        if (raw == null) {
            LOG.debug("orphan response corrId={} (ignored)", h.corrId);
            return;
        }
        if (raw.messageId != h.messageId) {
            LOG.warn(
                    "mismatched response corrId={} expected messageId={} but received {} (ignored)",
                    h.corrId,
                    raw.messageId,
                    h.messageId);
            return;
        }
        if (!pending.remove(h.corrId, raw)) return;
        Pending<R> p = (Pending<R>) raw;
        cancelQuietly(p.timeoutTask);
        try {
            R view = p.decoder.decode(payload);
            p.future.complete(view);
        } catch (RuntimeException e) {
            p.future.completeExceptionally(e);
        }
    }

    private void handleEvent(Envelope.Header h, ByteBuffer payload) {
        ByteBuffer snapshot = payload.duplicate();
        byte[] bytes = new byte[snapshot.remaining()];
        snapshot.get(bytes);
        long sequence = nextEventSequence.incrementAndGet();
        latestEvents.put(h.messageId, new LatestEvent(sequence, bytes));
        CopyOnWriteArrayList<EventBinding<?>> list = eventHandlers.get(h.messageId);
        if (list == null || list.isEmpty()) {
            LOG.debug("no event handlers for messageId={}", h.messageId);
            return;
        }
        for (EventBinding<?> binding : list) {
            binding.dispatch(sequence, payload.duplicate());
        }
    }

    private void handleIntercept(Envelope.Header h, ByteBuffer payload) {
        InterceptBinding<?> binding = interceptHandlers.get(h.messageId);
        CefMessageEncoder responseEnc = null;
        if (binding != null) {
            try {
                responseEnc = binding.invoke(payload);
            } catch (RuntimeException e) {
                LOG.warn("intercept handler for messageId={} threw; sending empty response", h.messageId, e);
            }
        } else {
            LOG.debug("no intercept handler for messageId={}; sending empty response", h.messageId);
        }
        sendInterceptResponse(h.corrId, h.messageId, responseEnc);
    }

    private void sendInterceptResponse(int corrId, int incomingMessageId, @Nullable CefMessageEncoder enc) {
        if (closed) {
            LOG.debug("session closed before intercept response could be sent");
            return;
        }
        int payloadLen = (enc == null) ? 0 : enc.encodedSize();
        int messageId = (enc == null) ? incomingMessageId : enc.messageId();
        ByteBuffer buf = ByteBuffer.allocate(Envelope.HEADER_SIZE + payloadLen).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(buf, Envelope.Kind.INTERCEPT_RESPONSE, 0, corrId, messageId, payloadLen);
        if (enc != null) enc.encodeInto(buf);
        buf.flip();
        try {
            transport.send(buf);
        } catch (CefTransportException e) {
            if (closed) {
                LOG.debug("session closed while sending intercept response", e);
            } else {
                LOG.warn("failed to send intercept response", e);
            }
        }
    }

    private void handleDisconnect() {
        if (!disconnectHandled.compareAndSet(false, true)) return;
        synchronized (registrationLock) {
            closed = true;
            eventHandlers.clear();
            interceptHandlers.clear();
        }
        failAllPending(new CefTransportException("transport disconnected"));
        latestEvents.clear();
        notifyClosed();
        if (ownTimer) timer.shutdownNow();
    }

    private void notifyClosed() {
        if (!closeNotified.compareAndSet(false, true)) return;
        for (Runnable handler : closeHandlers) {
            try {
                handler.run();
            } catch (RuntimeException failure) {
                LOG.warn("session close handler threw", failure);
            }
        }
        closeHandlers.clear();
    }

    private void requireOpen() {
        if (closed) throw new IllegalStateException("session closed");
    }

    private static final class Pending<R extends CefMessageView> {
        final CompletableFuture<R> future;
        final CefMessageDecoder<R> decoder;
        final int messageId;

        @Nullable
        volatile ScheduledFuture<?> timeoutTask;

        Pending(CompletableFuture<R> future, CefMessageDecoder<R> decoder, int messageId) {
            this.future = future;
            this.decoder = decoder;
            this.messageId = messageId;
        }
    }

    private static final class RequestFuture<R> extends CompletableFuture<R> {
        volatile Runnable onCancel = () -> {};

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            boolean cancelled = super.cancel(mayInterruptIfRunning);
            if (cancelled) onCancel.run();
            return cancelled;
        }
    }

    private static final class EventBinding<E extends CefMessageView> {
        final CefMessageDecoder<E> decoder;
        final Consumer<E> handler;
        final AtomicLong deliveredSequence = new AtomicLong(0);

        EventBinding(CefMessageDecoder<E> decoder, Consumer<E> handler) {
            this.decoder = decoder;
            this.handler = handler;
        }

        void dispatch(long sequence, ByteBuffer payload) {
            long delivered;
            do {
                delivered = deliveredSequence.get();
                if (sequence <= delivered) return;
            } while (!deliveredSequence.compareAndSet(delivered, sequence));
            try {
                E view = decoder.decode(payload);
                handler.accept(view);
            } catch (RuntimeException e) {
                LOG.warn("event handler threw", e);
            }
        }
    }

    private static final class LatestEvent {
        final long sequence;
        final byte[] bytes;

        LatestEvent(long sequence, byte[] bytes) {
            this.sequence = sequence;
            this.bytes = bytes;
        }

        ByteBuffer payload() {
            return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        }
    }

    private static final class InterceptBinding<E extends CefMessageView> {
        final CefMessageDecoder<E> decoder;
        final InterceptHandler<E> handler;

        InterceptBinding(CefMessageDecoder<E> decoder, InterceptHandler<E> handler) {
            this.decoder = decoder;
            this.handler = handler;
        }

        @Nullable
        CefMessageEncoder invoke(ByteBuffer payload) {
            E view = decoder.decode(payload);
            return handler.onIntercept(view);
        }
    }
}
