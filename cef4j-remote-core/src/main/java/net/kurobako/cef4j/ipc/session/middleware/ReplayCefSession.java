package net.kurobako.cef4j.ipc.session.middleware;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Strict, request-driven replay of a {@link SessionTrace}. No CEF runtime, native library, or transport is involved.
 * Register the same handlers as the recorded program, call {@link #start()}, then execute the recorded API flow.
 */
public final class ReplayCefSession implements CefSession {
    private static final Logger LOG = LoggerFactory.getLogger(ReplayCefSession.class);

    private final Object lock = new Object();
    private final List<SessionTrace.Entry> entries;
    private final ReplayMode mode;

    @Nullable
    private final ScheduledExecutorService scheduler;

    private final Map<Long, Pending<?>> pending = new HashMap<>();
    private final Map<Long, EventBinding<?>> subscriptions = new HashMap<>();
    private final Map<Integer, InterceptBinding<?>> intercepts = new HashMap<>();
    private final List<Runnable> closeHandlers = new ArrayList<>();

    private int cursor;
    private boolean started;
    private boolean closed;
    private boolean timedDeliveryScheduled;
    private long playbackStartNanos;

    @Nullable
    private ScheduledFuture<?> timedTask;

    public static ReplayCefSession fromFile(@Nonnull Path file, @Nonnull ReplayMode mode) throws IOException {
        return new ReplayCefSession(SessionTrace.read(file), mode);
    }

    public static ReplayCefSession fromFile(
            @Nonnull Path file, @Nonnull SessionTraceCodec codec, @Nonnull ReplayMode mode) throws IOException {
        return new ReplayCefSession(SessionTrace.read(file, codec), mode);
    }

    public ReplayCefSession(@Nonnull SessionTrace.Recording recording, @Nonnull ReplayMode mode) {
        this.entries = Objects.requireNonNull(recording, "recording").entries();
        this.mode = Objects.requireNonNull(mode, "mode");
        this.scheduler = mode == ReplayMode.TIMED
                ? Executors.newSingleThreadScheduledExecutor(runnable -> {
                    Thread thread = new Thread(runnable, "cef4j-api-replay");
                    thread.setDaemon(true);
                    return thread;
                })
                : null;
    }

    /** Begin delivery. Handler registration may be performed before this call. Idempotent. */
    public void start() {
        synchronized (lock) {
            if (started) return;
            requireOpen();
            started = true;
            playbackStartNanos = System.nanoTime();
            continuePlaybackLocked();
        }
    }

    /**
     * Deliver one non-action entry in {@link ReplayMode#MANUAL}. Returns false at an action barrier or end of trace.
     */
    public boolean advance() {
        synchronized (lock) {
            requireOpen();
            if (mode != ReplayMode.MANUAL) throw new IllegalStateException("advance() requires MANUAL replay mode");
            if (!started) start();
            if (cursor >= entries.size() || isAction(entries.get(cursor).kind)) return false;
            processDeliveryLocked(entries.get(cursor++));
            return true;
        }
    }

    /** Fails if replay stopped before consuming the complete recording. */
    public void verifyComplete() {
        synchronized (lock) {
            if (cursor != entries.size()) {
                SessionTrace.Entry next = entries.get(cursor);
                throw mismatch(next, "trace ended with " + (entries.size() - cursor) + " unconsumed entries");
            }
            if (!pending.isEmpty())
                throw new ReplayMismatchException("trace ended with pending requests " + pending.keySet());
        }
    }

    @Override
    @Nonnull
    public <R extends CefMessageView> CompletableFuture<R> request(
            @Nonnull CefMessageEncoder request, @Nonnull CefMessageDecoder<R> decoder) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(decoder, "decoder");
        synchronized (lock) {
            try {
                requireOpen();
                prepareForActionLocked();
                SessionTrace.Entry entry = expectActionLocked(SessionTrace.Kind.REQUEST, request.messageId());
                comparePayload(entry, encode(request), "request");
                CompletableFuture<R> result = new CompletableFuture<>();
                pending.put(entry.operationId, new Pending<>(result, decoder, request.messageId()));
                continuePlaybackLocked();
                return result;
            } catch (RuntimeException failure) {
                return failed(failure);
            }
        }
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration on(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
        return subscribe(SessionTrace.Kind.SUBSCRIBE, messageId, decoder, handler);
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration onLatest(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
        return subscribe(SessionTrace.Kind.SUBSCRIBE_LATEST, messageId, decoder, handler);
    }

    private <E extends CefMessageView> HandlerRegistration subscribe(
            SessionTrace.Kind kind, int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
        synchronized (lock) {
            requireOpen();
            prepareForActionLocked();
            SessionTrace.Entry entry = expectActionLocked(kind, messageId);
            EventBinding<E> binding = new EventBinding<>(decoder, handler);
            subscriptions.put(entry.operationId, binding);
            continuePlaybackLocked();
            return new ReplayRegistration(entry.operationId, messageId, false);
        }
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration intercept(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull InterceptHandler<E> handler) {
        synchronized (lock) {
            requireOpen();
            prepareForActionLocked();
            SessionTrace.Entry entry = expectActionLocked(SessionTrace.Kind.INTERCEPT_REGISTER, messageId);
            intercepts.put(messageId, new InterceptBinding<>(decoder, handler));
            continuePlaybackLocked();
            return new ReplayRegistration(entry.operationId, messageId, true);
        }
    }

    @Override
    @Nonnull
    public HandlerRegistration onClose(@Nonnull Runnable handler) {
        Objects.requireNonNull(handler, "handler");
        synchronized (lock) {
            if (!closed) {
                closeHandlers.add(handler);
                return () -> {
                    synchronized (lock) {
                        closeHandlers.remove(handler);
                    }
                };
            }
        }
        handler.run();
        return () -> {};
    }

    @Override
    public void close() {
        List<Runnable> handlers;
        RuntimeException mismatch = null;
        synchronized (lock) {
            if (closed) return;
            try {
                prepareForActionLocked();
                if (cursor < entries.size()) expectActionLocked(SessionTrace.Kind.CLOSE, 0);
            } catch (RuntimeException failure) {
                mismatch = failure;
            } finally {
                closed = true;
                for (Pending<?> value : pending.values()) {
                    value.future.completeExceptionally(new IllegalStateException("replay session closed"));
                }
                pending.clear();
                subscriptions.clear();
                intercepts.clear();
                if (timedTask != null) timedTask.cancel(false);
                if (scheduler != null) scheduler.shutdownNow();
                handlers = new ArrayList<>(closeHandlers);
                closeHandlers.clear();
            }
        }
        handlers.forEach(this::runCloseHandler);
        if (mismatch != null) throw mismatch;
    }

    private void runCloseHandler(Runnable handler) {
        try {
            handler.run();
        } catch (RuntimeException failure) {
            LOG.warn("replay close handler failed", failure);
        }
    }

    private void unregister(long registrationId, int messageId, boolean intercept) {
        synchronized (lock) {
            if (closed) return;
            prepareForActionLocked();
            SessionTrace.Kind kind = intercept ? SessionTrace.Kind.INTERCEPT_UNREGISTER : SessionTrace.Kind.UNSUBSCRIBE;
            SessionTrace.Entry entry = expectActionLocked(kind, messageId);
            if (entry.operationId != registrationId) {
                throw mismatch(
                        entry, "registration id " + registrationId + " does not match recorded " + entry.operationId);
            }
            if (intercept) intercepts.remove(messageId);
            else subscriptions.remove(registrationId);
            continuePlaybackLocked();
        }
    }

    private void prepareForActionLocked() {
        if (started && mode == ReplayMode.IMMEDIATE) pumpImmediateLocked();
    }

    private SessionTrace.Entry expectActionLocked(SessionTrace.Kind kind, int messageId) {
        if (cursor >= entries.size()) {
            throw new ReplayMismatchException("expected " + kind + " messageId=" + messageId + " after end of trace");
        }
        SessionTrace.Entry entry = entries.get(cursor);
        if (entry.kind != kind || entry.messageId != messageId) {
            throw mismatch(
                    entry,
                    "expected " + kind + " messageId=" + messageId + " but recording has " + entry.kind + " messageId="
                            + entry.messageId);
        }
        cursor++;
        return entry;
    }

    private void continuePlaybackLocked() {
        if (!started) return;
        if (mode == ReplayMode.IMMEDIATE) pumpImmediateLocked();
        else if (mode == ReplayMode.TIMED) scheduleTimedLocked();
    }

    private void pumpImmediateLocked() {
        while (cursor < entries.size() && !isAction(entries.get(cursor).kind)) {
            processDeliveryLocked(entries.get(cursor++));
        }
    }

    private void scheduleTimedLocked() {
        if (timedDeliveryScheduled || cursor >= entries.size() || isAction(entries.get(cursor).kind)) return;
        SessionTrace.Entry entry = entries.get(cursor);
        long due = playbackStartNanos + entry.elapsedNanos;
        long delay = Math.max(0, due - System.nanoTime());
        timedDeliveryScheduled = true;
        ScheduledExecutorService activeScheduler = Objects.requireNonNull(scheduler, "timed replay scheduler");
        timedTask = activeScheduler.schedule(
                () -> {
                    synchronized (lock) {
                        timedDeliveryScheduled = false;
                        if (closed || cursor >= entries.size() || entries.get(cursor) != entry) return;
                        cursor++;
                        try {
                            processDeliveryLocked(entry);
                            scheduleTimedLocked();
                        } catch (RuntimeException failure) {
                            failAllLocked(failure);
                        }
                    }
                },
                delay,
                TimeUnit.NANOSECONDS);
    }

    @SuppressWarnings("unchecked")
    private void processDeliveryLocked(SessionTrace.Entry entry) {
        switch (entry.kind) {
            case RESPONSE:
                Pending<CefMessageView> response = (Pending<CefMessageView>) pending.remove(entry.operationId);
                if (response == null) throw mismatch(entry, "response has no pending request");
                if (response.messageId != entry.messageId) throw mismatch(entry, "response message id changed");
                response.complete(requiredPayload(entry));
                return;
            case FAILURE:
                Pending<?> failed = pending.remove(entry.operationId);
                if (failed == null) throw mismatch(entry, "failure has no pending request");
                failed.future.completeExceptionally(new ReplayedSessionException(
                        entry.detailType == null ? "recorded failure" : entry.detailType, entry.detailMessage));
                return;
            case EVENT:
                EventBinding<CefMessageView> event =
                        (EventBinding<CefMessageView>) subscriptions.get(entry.operationId);
                if (event == null) throw mismatch(entry, "event has no active recorded subscription");
                event.dispatch(requiredPayload(entry));
                return;
            case INTERCEPT:
                dispatchInterceptLocked(entry);
                return;
            default:
                throw mismatch(entry, "expected a replay delivery, found " + entry.kind);
        }
    }

    @SuppressWarnings("unchecked")
    private void dispatchInterceptLocked(SessionTrace.Entry incoming) {
        InterceptBinding<CefMessageView> binding =
                (InterceptBinding<CefMessageView>) intercepts.get(incoming.messageId);
        if (binding == null) throw mismatch(incoming, "intercept has no active handler");
        CefMessageEncoder actual;
        try {
            actual = binding.invoke(requiredPayload(incoming));
        } catch (RuntimeException failure) {
            if (cursor >= entries.size()) throw mismatch(incoming, "recorded intercept failure is missing");
            SessionTrace.Entry expectedFailure = entries.get(cursor++);
            if (expectedFailure.kind != SessionTrace.Kind.FAILURE
                    || expectedFailure.operationId != incoming.operationId) {
                throw mismatch(expectedFailure, "expected failure for intercept operation " + incoming.operationId);
            }
            if (expectedFailure.detailType != null
                    && !expectedFailure.detailType.equals(failure.getClass().getName())) {
                throw mismatch(
                        expectedFailure,
                        "intercept threw " + failure.getClass().getName() + ", recorded " + expectedFailure.detailType);
            }
            return;
        }
        if (cursor >= entries.size()) throw mismatch(incoming, "intercept response is missing");
        SessionTrace.Entry expected = entries.get(cursor++);
        if (expected.kind != SessionTrace.Kind.INTERCEPT_RESPONSE || expected.operationId != incoming.operationId) {
            throw mismatch(expected, "expected response for intercept operation " + incoming.operationId);
        }
        if (actual == null) {
            if (expected.payload() != null)
                throw mismatch(expected, "handler returned default action, recording did not");
        } else {
            if (actual.messageId() != expected.messageId) {
                throw mismatch(expected, "intercept response messageId=" + actual.messageId() + " does not match");
            }
            comparePayload(expected, encode(actual), "intercept response");
        }
    }

    private static boolean isAction(SessionTrace.Kind kind) {
        switch (kind) {
            case REQUEST:
            case SUBSCRIBE:
            case SUBSCRIBE_LATEST:
            case UNSUBSCRIBE:
            case INTERCEPT_REGISTER:
            case INTERCEPT_UNREGISTER:
            case CLOSE:
                return true;
            default:
                return false;
        }
    }

    private void failAllLocked(RuntimeException failure) {
        for (Pending<?> value : pending.values()) value.future.completeExceptionally(failure);
        pending.clear();
        LOG.warn("cef4j API replay stopped at entry {}", cursor, failure);
    }

    private void requireOpen() {
        if (closed) throw new IllegalStateException("replay session closed");
    }

    private static byte[] requiredPayload(SessionTrace.Entry entry) {
        byte[] payload = entry.payload();
        if (payload == null) throw mismatch(entry, "entry requires a payload");
        return payload;
    }

    private static void comparePayload(SessionTrace.Entry entry, byte[] actual, String label) {
        byte[] expected = entry.payload();
        if (expected == null) throw mismatch(entry, label + " recording has no payload");
        if (Arrays.equals(expected, actual)) return;
        int shared = Math.min(expected.length, actual.length);
        int difference = 0;
        while (difference < shared && expected[difference] == actual[difference]) difference++;
        throw mismatch(
                entry,
                label + " payload differs at byte " + difference + " (recorded=" + expected.length + " bytes, actual="
                        + actual.length + " bytes)");
    }

    private static ReplayMismatchException mismatch(SessionTrace.Entry entry, String message) {
        return new ReplayMismatchException(
                "API replay mismatch at entry #" + entry.sequence + " (" + entry.kind + "): " + message);
    }

    private static byte[] encode(CefMessageEncoder encoder) {
        ByteBuffer payload = ByteBuffer.allocate(encoder.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        encoder.encodeInto(payload);
        if (payload.position() != encoder.encodedSize()) {
            throw new IllegalArgumentException("encoder for message " + encoder.messageId() + " wrote "
                    + payload.position() + " bytes, expected " + encoder.encodedSize());
        }
        return payload.array();
    }

    private static <T> CompletableFuture<T> failed(Throwable failure) {
        CompletableFuture<T> result = new CompletableFuture<>();
        result.completeExceptionally(failure);
        return result;
    }

    private static final class Pending<R extends CefMessageView> {
        final CompletableFuture<R> future;
        final CefMessageDecoder<R> decoder;
        final int messageId;

        Pending(CompletableFuture<R> future, CefMessageDecoder<R> decoder, int messageId) {
            this.future = future;
            this.decoder = decoder;
            this.messageId = messageId;
        }

        void complete(byte[] payload) {
            try {
                future.complete(decoder.decode(ByteBuffer.wrap(payload).order(ByteOrder.LITTLE_ENDIAN)));
            } catch (RuntimeException failure) {
                future.completeExceptionally(failure);
            }
        }
    }

    private static final class EventBinding<E extends CefMessageView> {
        final CefMessageDecoder<E> decoder;
        final Consumer<E> handler;

        EventBinding(CefMessageDecoder<E> decoder, Consumer<E> handler) {
            this.decoder = decoder;
            this.handler = handler;
        }

        void dispatch(byte[] payload) {
            try {
                handler.accept(decoder.decode(ByteBuffer.wrap(payload).order(ByteOrder.LITTLE_ENDIAN)));
            } catch (RuntimeException failure) {
                LOG.warn("replayed event handler threw", failure);
            }
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
        CefMessageEncoder invoke(byte[] payload) {
            E event = decoder.decode(ByteBuffer.wrap(payload).order(ByteOrder.LITTLE_ENDIAN));
            return handler.onIntercept(event);
        }
    }

    private final class ReplayRegistration implements HandlerRegistration {
        private final long registrationId;
        private final int messageId;
        private final boolean intercept;
        private boolean registered = true;

        ReplayRegistration(long registrationId, int messageId, boolean intercept) {
            this.registrationId = registrationId;
            this.messageId = messageId;
            this.intercept = intercept;
        }

        @Override
        public void unregister() {
            synchronized (lock) {
                if (!registered) return;
                registered = false;
            }
            ReplayCefSession.this.unregister(registrationId, messageId, intercept);
        }
    }
}
