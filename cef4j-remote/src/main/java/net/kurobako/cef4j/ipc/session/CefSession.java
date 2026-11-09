package net.kurobako.cef4j.ipc.session;

import java.io.Closeable;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * Correlation, routing, and intercept layer above {@link net.kurobako.cef4j.ipc.transport.CefTransport}. Hides the
 * envelope wire format from callers; codegen-emitted message types are the unit of communication.
 *
 * <p>This transport-neutral boundary and its lifecycle constraints are documented in the project {@code README.md}.
 */
// The three parameters of on and onLatest are the subscription the API takes, which the middleware of a session
// forwards; a parameter object would change the signature of every subscription in the build to name the same three
// values.
@SuppressWarnings("baumkuchen:data-clump")
public interface CefSession extends Closeable {

    /**
     * Send a request under {@code messageId} and resolve the future when the matching {@code RESPONSE} arrives. The
     * future fails with a {@link java.util.concurrent.TimeoutException} cause if no response arrives within the
     * session's configured default timeout, and with a {@link net.kurobako.cef4j.ipc.transport.CefTransportException}
     * cause if the transport disconnects or the session is closed.
     */
    <R extends CefMessageView> CompletableFuture<R> request(
            int messageId, CefMessageEncoder request, CefMessageDecoder<R> decoder);

    /**
     * Subscribe to events of a specific {@code messageId}. Multiple subscribers per id are allowed; all are invoked in
     * registration order. Callbacks fire on the transport's reader thread; subscribers must not block.
     */
    <E extends CefMessageView> HandlerRegistration on(int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler);

    /**
     * Subscribe and immediately replay the most recently received event for this message id, if any. Replay is
     * session-wide, not partitioned by browser or frame. Use it only for session-global state announcements or when the
     * handler deliberately accepts every source; scoped and edge-triggered events should use {@link #on}.
     */
    default <E extends CefMessageView> HandlerRegistration onLatest(
            int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
        return on(messageId, decoder, handler);
    }

    /**
     * Register an intercept handler for a specific {@code messageId}. At most one handler per id; later registrations
     * replace earlier ones (a warning is logged). The handler runs on the transport's reader thread; the server is
     * synchronously parked waiting for the reply, so handlers must complete promptly.
     */
    <E extends CefMessageView> HandlerRegistration intercept(
            int messageId, CefMessageDecoder<E> decoder, InterceptHandler<E> handler);

    /** Register a callback invoked once when this session closes locally or loses its transport. */
    default HandlerRegistration onClose(Runnable handler) {
        return () -> {};
    }

    @Override
    void close();

    /** Returned from {@link #on}/{@link #intercept}; call {@link #unregister} to stop delivery. */
    interface HandlerRegistration extends AutoCloseable {
        static HandlerRegistration combine(HandlerRegistration... registrations) {
            final HandlerRegistration[] owned = registrations.clone();
            for (HandlerRegistration registration : owned) Objects.requireNonNull(registration, "registration");
            final AtomicBoolean closed = new AtomicBoolean();
            return () -> {
                if (!closed.compareAndSet(false, true)) return;
                RuntimeException failure = null;
                for (int index = owned.length - 1; index >= 0; index--) {
                    try {
                        owned[index].close();
                    } catch (RuntimeException closeFailure) {
                        if (failure == null) failure = closeFailure;
                        else failure.addSuppressed(closeFailure);
                    }
                }
                if (failure != null) throw failure;
            };
        }

        void unregister();

        @Override
        default void close() {
            unregister();
        }
    }

    /** An intercept answer: the payload and the message id it travels under. */
    final class Reply {
        private final int messageId;
        private final CefMessageEncoder payload;

        public Reply(int messageId, CefMessageEncoder payload) {
            this.messageId = messageId;
            this.payload = Objects.requireNonNull(payload, "payload");
        }

        public int messageId() {
            return messageId;
        }

        public CefMessageEncoder payload() {
            return payload;
        }
    }

    /** Synchronous-return intercept handler; return empty to signal "default action" (empty response). */
    @FunctionalInterface
    interface InterceptHandler<E extends CefMessageView> {
        Optional<Reply> onIntercept(E event);
    }
}
