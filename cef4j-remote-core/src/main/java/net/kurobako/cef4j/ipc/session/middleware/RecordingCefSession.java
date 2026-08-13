package net.kurobako.cef4j.ipc.session.middleware;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.LongFunction;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link CefSession} middleware that records logical API operations independently of the underlying transport.
 *
 * <p>Trace payloads are replayable protocol data and may contain URLs, cookies, headers, JavaScript, form values, and
 * authentication material. Store them accordingly. A {@link TracePayloadFilter} can redact data, but transformed traces
 * are replayable only if the transformation preserves values needed by strict request matching and decoders.
 */
public final class RecordingCefSession implements CefSession {
    private static final Logger LOG = LoggerFactory.getLogger(RecordingCefSession.class);

    private final CefSession delegate;
    private final SessionTraceWriter trace;
    private final TracePayloadFilter filter;
    private final AtomicLong nextOperationId = new AtomicLong(1);
    private final AtomicBoolean closed = new AtomicBoolean();
    private final AtomicBoolean traceHealthy = new AtomicBoolean(true);

    public static RecordingCefSession toFile(@Nonnull CefSession delegate, @Nonnull Path file) throws IOException {
        return new RecordingCefSession(delegate, SessionTrace.writer(file), TracePayloadFilter.identity());
    }

    public static RecordingCefSession toFile(
            @Nonnull CefSession delegate, @Nonnull Path file, @Nonnull TracePayloadFilter filter) throws IOException {
        return toFile(delegate, file, SessionTrace.defaultCodec(), filter);
    }

    public static RecordingCefSession toFile(
            @Nonnull CefSession delegate, @Nonnull Path file, @Nonnull SessionTraceCodec codec) throws IOException {
        return toFile(delegate, file, codec, TracePayloadFilter.identity());
    }

    public static RecordingCefSession toFile(
            @Nonnull CefSession delegate,
            @Nonnull Path file,
            @Nonnull SessionTraceCodec codec,
            @Nonnull TracePayloadFilter filter)
            throws IOException {
        return new RecordingCefSession(
                delegate, SessionTrace.writer(file, SessionTrace.defaultMetadata(), codec), filter);
    }

    /** Middleware factory for a single recorded session. Opening failures surface when the middleware is applied. */
    public static CefSessionMiddleware middleware(@Nonnull Path file) {
        return middleware(file, TracePayloadFilter.identity());
    }

    /** Middleware factory for a single filtered recording. */
    public static CefSessionMiddleware middleware(@Nonnull Path file, @Nonnull TracePayloadFilter filter) {
        return middleware(file, SessionTrace.defaultCodec(), filter);
    }

    /** Middleware factory for a single recording written with the supplied codec. */
    public static CefSessionMiddleware middleware(@Nonnull Path file, @Nonnull SessionTraceCodec codec) {
        return middleware(file, codec, TracePayloadFilter.identity());
    }

    /** Middleware factory for a single filtered recording written with the supplied codec. */
    public static CefSessionMiddleware middleware(
            @Nonnull Path file, @Nonnull SessionTraceCodec codec, @Nonnull TracePayloadFilter filter) {
        Objects.requireNonNull(file, "file");
        Objects.requireNonNull(codec, "codec");
        Objects.requireNonNull(filter, "filter");
        return delegate -> {
            try {
                return toFile(delegate, file, codec, filter);
            } catch (IOException failure) {
                throw new UncheckedIOException("failed to open cef4j API trace " + file, failure);
            }
        };
    }

    /** Middleware factory that opens a distinct trace for every wrapped session, numbered from one. */
    public static CefSessionMiddleware rotatingMiddleware(@Nonnull LongFunction<Path> traceFile) {
        return rotatingMiddleware(traceFile, TracePayloadFilter.identity());
    }

    /** Middleware factory that opens a distinct filtered trace for every wrapped session, numbered from one. */
    public static CefSessionMiddleware rotatingMiddleware(
            @Nonnull LongFunction<Path> traceFile, @Nonnull TracePayloadFilter filter) {
        return rotatingMiddleware(traceFile, SessionTrace.defaultCodec(), filter);
    }

    /** Middleware factory that uses the supplied codec for every numbered session trace. */
    public static CefSessionMiddleware rotatingMiddleware(
            @Nonnull LongFunction<Path> traceFile, @Nonnull SessionTraceCodec codec) {
        return rotatingMiddleware(traceFile, codec, TracePayloadFilter.identity());
    }

    /** Middleware factory that uses the supplied codec and filter for every numbered session trace. */
    public static CefSessionMiddleware rotatingMiddleware(
            @Nonnull LongFunction<Path> traceFile,
            @Nonnull SessionTraceCodec codec,
            @Nonnull TracePayloadFilter filter) {
        Objects.requireNonNull(traceFile, "traceFile");
        Objects.requireNonNull(codec, "codec");
        Objects.requireNonNull(filter, "filter");
        AtomicLong generations = new AtomicLong();
        return delegate -> {
            long generation = generations.incrementAndGet();
            Path file = Objects.requireNonNull(traceFile.apply(generation), "traceFile result");
            try {
                return toFile(delegate, file, codec, filter);
            } catch (IOException failure) {
                throw new UncheckedIOException("failed to open cef4j API trace " + file, failure);
            }
        };
    }

    public RecordingCefSession(@Nonnull CefSession delegate, @Nonnull SessionTraceWriter trace) {
        this(delegate, trace, TracePayloadFilter.identity());
    }

    public RecordingCefSession(
            @Nonnull CefSession delegate, @Nonnull SessionTraceWriter trace, @Nonnull TracePayloadFilter filter) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
        this.trace = Objects.requireNonNull(trace, "trace");
        this.filter = Objects.requireNonNull(filter, "filter");
    }

    @Override
    @Nonnull
    public <R extends CefMessageView> CompletableFuture<R> request(
            @Nonnull CefMessageEncoder request, @Nonnull CefMessageDecoder<R> decoder) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(decoder, "decoder");
        long operationId = nextOperationId.getAndIncrement();
        append(SessionTrace.Kind.REQUEST, operationId, request.messageId(), encode(request), null, null);
        AtomicBoolean receivedResponse = new AtomicBoolean();
        CefMessageDecoder<R> recordingDecoder = payload -> {
            byte[] bytes = copy(payload);
            append(SessionTrace.Kind.RESPONSE, operationId, request.messageId(), bytes, null, null);
            receivedResponse.set(true);
            return decoder.decode(payload);
        };
        CompletableFuture<R> result;
        try {
            result = delegate.request(request, recordingDecoder);
        } catch (RuntimeException failure) {
            recordFailure(operationId, request.messageId(), failure);
            throw failure;
        }
        return result.whenComplete((ignored, failure) -> {
            if (failure != null && !receivedResponse.get()) recordFailure(operationId, request.messageId(), failure);
        });
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration on(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
        return subscribe(SessionTrace.Kind.SUBSCRIBE, false, messageId, decoder, handler);
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration onLatest(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
        return subscribe(SessionTrace.Kind.SUBSCRIBE_LATEST, true, messageId, decoder, handler);
    }

    private <E extends CefMessageView> HandlerRegistration subscribe(
            SessionTrace.Kind kind, boolean latest, int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
        long subscriptionId = nextOperationId.getAndIncrement();
        append(kind, subscriptionId, messageId, null, null, null);
        CefMessageDecoder<E> recordingDecoder = payload -> {
            append(SessionTrace.Kind.EVENT, subscriptionId, messageId, copy(payload), null, null);
            return decoder.decode(payload);
        };
        HandlerRegistration registration = latest
                ? delegate.onLatest(messageId, recordingDecoder, handler)
                : delegate.on(messageId, recordingDecoder, handler);
        AtomicBoolean registered = new AtomicBoolean(true);
        return () -> {
            if (!registered.compareAndSet(true, false)) return;
            append(SessionTrace.Kind.UNSUBSCRIBE, subscriptionId, messageId, null, null, null);
            registration.unregister();
        };
    }

    @Override
    @Nonnull
    public <E extends CefMessageView> HandlerRegistration intercept(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull InterceptHandler<E> handler) {
        long registrationId = nextOperationId.getAndIncrement();
        append(SessionTrace.Kind.INTERCEPT_REGISTER, registrationId, messageId, null, null, null);
        // Wrap the decoded view with its recorded call id; the delegate decodes and invokes the handler on the same
        // reader thread, so the exact incoming payload and its synchronous reply remain paired.
        HandlerRegistration recordingRegistration = delegate.intercept(
                messageId,
                payload -> {
                    long callId = nextOperationId.getAndIncrement();
                    append(
                            SessionTrace.Kind.INTERCEPT,
                            callId,
                            messageId,
                            copy(payload),
                            Long.toString(registrationId),
                            null);
                    try {
                        E event = decoder.decode(payload);
                        return new InterceptDelivery<>(callId, event);
                    } catch (RuntimeException failure) {
                        recordFailure(callId, messageId, failure);
                        throw failure;
                    }
                },
                delivery -> {
                    try {
                        CefMessageEncoder response = handler.onIntercept(delivery.event);
                        append(
                                SessionTrace.Kind.INTERCEPT_RESPONSE,
                                delivery.callId,
                                response == null ? messageId : response.messageId(),
                                response == null ? null : encode(response),
                                Long.toString(registrationId),
                                null);
                        return response;
                    } catch (RuntimeException failure) {
                        recordFailure(delivery.callId, messageId, failure);
                        throw failure;
                    }
                });
        AtomicBoolean registered = new AtomicBoolean(true);
        return () -> {
            if (!registered.compareAndSet(true, false)) return;
            append(SessionTrace.Kind.INTERCEPT_UNREGISTER, registrationId, messageId, null, null, null);
            recordingRegistration.unregister();
        };
    }

    @Override
    @Nonnull
    public HandlerRegistration onClose(@Nonnull Runnable handler) {
        return delegate.onClose(handler);
    }

    @Override
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        append(SessionTrace.Kind.CLOSE, 0, 0, null, null, null);
        try {
            delegate.close();
        } finally {
            try {
                trace.close();
            } catch (IOException failure) {
                LOG.warn("failed to close cef4j API trace", failure);
            }
        }
    }

    private void recordFailure(long operationId, int messageId, Throwable failure) {
        Throwable cause = unwrap(failure);
        append(
                SessionTrace.Kind.FAILURE,
                operationId,
                messageId,
                null,
                cause.getClass().getName(),
                cause.getMessage());
    }

    private void append(
            SessionTrace.Kind kind,
            long operationId,
            int messageId,
            @Nullable byte[] payload,
            @Nullable String detailType,
            @Nullable String detailMessage) {
        if (!traceHealthy.get()) return;
        try {
            byte[] stored = payload == null ? null : filter.filter(kind, messageId, payload.clone());
            trace.append(kind, operationId, messageId, stored, detailType, detailMessage);
        } catch (IOException | RuntimeException failure) {
            if (traceHealthy.compareAndSet(true, false)) {
                LOG.error("cef4j API trace recording stopped after an I/O/filter failure", failure);
            }
        }
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

    private static byte[] copy(ByteBuffer payload) {
        ByteBuffer view = payload.duplicate();
        byte[] bytes = new byte[view.remaining()];
        view.get(bytes);
        return bytes;
    }

    private static Throwable unwrap(Throwable failure) {
        Throwable current = failure;
        while ((current instanceof java.util.concurrent.CompletionException
                        || current instanceof java.util.concurrent.ExecutionException)
                && current.getCause() != null) {
            current = current.getCause();
        }
        return current;
    }

    private static final class InterceptDelivery<E extends CefMessageView> implements CefMessageView {
        final long callId;
        final E event;

        InterceptDelivery(long callId, E event) {
            this.callId = callId;
            this.event = event;
        }

        @Override
        public int messageId() {
            return event.messageId();
        }
    }
}
