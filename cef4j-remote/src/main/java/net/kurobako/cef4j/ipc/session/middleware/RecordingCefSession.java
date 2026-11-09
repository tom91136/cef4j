package net.kurobako.cef4j.ipc.session.middleware;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.LongFunction;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link CefSession} middleware that records logical API operations independently of the underlying transport.
 *
 * <p>Trace payloads are replayable protocol data and may contain URLs, cookies, headers, JavaScript, form values, and
 * authentication material. Store them accordingly. A {@link TracePayloadFilter} can redact data, but transformed traces
 * are replayable only if the transformation preserves values needed by strict request matching and decoders.
 */
// The arguments of append are the fields of a trace entry, which the writer stamps with the sequence and the time of
// the entry; the entry type carries those two fields as well, so a parameter object would have to invent the values
// the writer adds.
@SuppressWarnings("bonsai:data-clump")
public final class RecordingCefSession implements CefSession {
    private static final Logger LOG = LoggerFactory.getLogger(RecordingCefSession.class);

    private final CefSession delegate;
    private final SessionTraceWriter trace;
    private final TracePayloadFilter filter;
    private final AtomicLong nextOperationId = new AtomicLong(1);
    private final AtomicBoolean closed = new AtomicBoolean();
    private final AtomicBoolean traceHealthy = new AtomicBoolean(true);

    public static RecordingCefSession toFile(CefSession delegate, Path file) throws IOException {
        return new RecordingCefSession(delegate, SessionTrace.writer(file), TracePayloadFilter.identity());
    }

    public static RecordingCefSession toFile(
            CefSession delegate, Path file, SessionTraceCodec codec, TracePayloadFilter filter) throws IOException {
        return new RecordingCefSession(
                delegate, SessionTrace.writer(file, SessionTrace.defaultMetadata(), codec), filter);
    }

    /** Middleware factory for a single recorded session. Opening failures surface when the middleware is applied. */
    public static CefSessionMiddleware middleware(Path file) {
        return middleware(file, SessionTrace.defaultCodec(), TracePayloadFilter.identity());
    }

    /** Middleware factory for a single filtered recording written with the supplied codec. */
    public static CefSessionMiddleware middleware(Path file, SessionTraceCodec codec, TracePayloadFilter filter) {
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
    public static CefSessionMiddleware rotatingMiddleware(LongFunction<Path> traceFile) {
        return rotatingMiddleware(traceFile, SessionTrace.defaultCodec(), TracePayloadFilter.identity());
    }

    /** Middleware factory that uses the supplied codec and filter for every numbered session trace. */
    public static CefSessionMiddleware rotatingMiddleware(
            LongFunction<Path> traceFile, SessionTraceCodec codec, TracePayloadFilter filter) {
        Objects.requireNonNull(traceFile, "traceFile");
        Objects.requireNonNull(codec, "codec");
        Objects.requireNonNull(filter, "filter");
        final AtomicLong generations = new AtomicLong();
        return delegate -> {
            final long generation = generations.incrementAndGet();
            final Path file = Objects.requireNonNull(traceFile.apply(generation), "traceFile result");
            try {
                return toFile(delegate, file, codec, filter);
            } catch (IOException failure) {
                throw new UncheckedIOException("failed to open cef4j API trace " + file, failure);
            }
        };
    }

    public RecordingCefSession(CefSession delegate, SessionTraceWriter trace) {
        this(delegate, trace, TracePayloadFilter.identity());
    }

    public RecordingCefSession(CefSession delegate, SessionTraceWriter trace, TracePayloadFilter filter) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
        this.trace = Objects.requireNonNull(trace, "trace");
        this.filter = Objects.requireNonNull(filter, "filter");
    }

    @Override
    public <R extends CefMessageView> CompletableFuture<R> request(
            int messageId, CefMessageEncoder request, CefMessageDecoder<R> decoder) {
        Objects.requireNonNull(request, "request");
        Objects.requireNonNull(decoder, "decoder");
        final long operationId = nextOperationId.getAndIncrement();
        append(
                SessionTrace.Kind.REQUEST,
                operationId,
                messageId,
                MessagePayloads.encode(messageId, request),
                null,
                null);
        final AtomicBoolean receivedResponse = new AtomicBoolean();
        final CefMessageDecoder<R> recordingDecoder = payload -> {
            final byte[] bytes = copy(payload);
            append(SessionTrace.Kind.RESPONSE, operationId, messageId, bytes, null, null);
            receivedResponse.set(true);
            return decoder.decode(payload);
        };
        CompletableFuture<R> result;
        try {
            result = delegate.request(messageId, request, recordingDecoder);
        } catch (RuntimeException failure) {
            recordFailure(operationId, messageId, failure);
            throw failure;
        }
        return result.whenComplete((ignored, failure) -> {
            if (failure != null && !receivedResponse.get()) recordFailure(operationId, messageId, failure);
        });
    }

    @Override
    public <E extends CefMessageView> HandlerRegistration on(
            int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
        return subscribe(SessionTrace.Kind.SUBSCRIBE, messageId, decoder, handler);
    }

    @Override
    public <E extends CefMessageView> HandlerRegistration onLatest(
            int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
        return subscribe(SessionTrace.Kind.SUBSCRIBE_LATEST, messageId, decoder, handler);
    }

    private <E extends CefMessageView> HandlerRegistration subscribe(
            SessionTrace.Kind kind, int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
        final long subscriptionId = nextOperationId.getAndIncrement();
        append(kind, subscriptionId, messageId, null, null, null);
        final CefMessageDecoder<E> recordingDecoder = payload -> {
            append(SessionTrace.Kind.EVENT, subscriptionId, messageId, copy(payload), null, null);
            return decoder.decode(payload);
        };
        final HandlerRegistration registration = kind == SessionTrace.Kind.SUBSCRIBE_LATEST
                ? delegate.onLatest(messageId, recordingDecoder, handler)
                : delegate.on(messageId, recordingDecoder, handler);
        final AtomicBoolean registered = new AtomicBoolean(true);
        return () -> {
            if (!registered.compareAndSet(true, false)) return;
            append(SessionTrace.Kind.UNSUBSCRIBE, subscriptionId, messageId, null, null, null);
            registration.unregister();
        };
    }

    @Override
    public <E extends CefMessageView> HandlerRegistration intercept(
            int messageId, CefMessageDecoder<E> decoder, InterceptHandler<E> handler) {
        final long registrationId = nextOperationId.getAndIncrement();
        append(SessionTrace.Kind.INTERCEPT_REGISTER, registrationId, messageId, null, null, null);
        // Wrap the decoded view with its recorded call id; the delegate decodes and invokes the handler on the same
        // reader thread, so the exact incoming payload and its synchronous reply remain paired.
        final HandlerRegistration recordingRegistration = delegate.intercept(
                messageId,
                payload -> {
                    final long callId = nextOperationId.getAndIncrement();
                    append(
                            SessionTrace.Kind.INTERCEPT,
                            callId,
                            messageId,
                            copy(payload),
                            Long.toString(registrationId),
                            null);
                    try {
                        final E event = decoder.decode(payload);
                        return new InterceptDelivery<>(callId, event);
                    } catch (RuntimeException failure) {
                        recordFailure(callId, messageId, failure);
                        throw failure;
                    }
                },
                delivery -> {
                    try {
                        final Optional<Reply> response = handler.onIntercept(delivery.event);
                        append(
                                SessionTrace.Kind.INTERCEPT_RESPONSE,
                                delivery.callId,
                                response.map(Reply::messageId).orElse(messageId),
                                response.map(reply -> MessagePayloads.encode(reply.messageId(), reply.payload()))
                                        .orElse(null),
                                Long.toString(registrationId),
                                null);
                        return response;
                    } catch (RuntimeException failure) {
                        recordFailure(delivery.callId, messageId, failure);
                        throw failure;
                    }
                });
        final AtomicBoolean registered = new AtomicBoolean(true);
        return () -> {
            if (!registered.compareAndSet(true, false)) return;
            append(SessionTrace.Kind.INTERCEPT_UNREGISTER, registrationId, messageId, null, null, null);
            recordingRegistration.unregister();
        };
    }

    @Override
    public HandlerRegistration onClose(Runnable handler) {
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
        final Throwable cause = unwrap(failure);
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
            byte @Nullable [] payload,
            @Nullable String detailType,
            @Nullable String detailMessage) {
        if (!traceHealthy.get()) return;
        try {
            final byte[] stored = payload == null ? null : filter.filter(kind, messageId, payload.clone());
            trace.append(kind, operationId, messageId, stored, detailType, detailMessage);
        } catch (IOException | RuntimeException failure) {
            if (traceHealthy.compareAndSet(true, false)) {
                LOG.error("cef4j API trace recording stopped after an I/O/filter failure", failure);
            }
        }
    }

    private static byte[] copy(ByteBuffer payload) {
        final ByteBuffer view = payload.duplicate();
        final byte[] bytes = new byte[view.remaining()];
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
    }
}
