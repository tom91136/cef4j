package net.kurobako.cef4j.ipc.session;

import java.io.Closeable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Correlation, routing, and intercept layer above {@link net.kurobako.cef4j.ipc.transport.CefTransport}. Hides the
 * envelope wire format from callers; codegen-emitted message types are the unit of communication.
 *
 * <p>This transport-neutral boundary and its lifecycle constraints are documented in the project {@code README.md}.
 */
public interface CefSession extends Closeable {

    /**
     * Send a request and resolve the future when the matching {@code RESPONSE} arrives. The future fails with a
     * {@link java.util.concurrent.TimeoutException} cause if no response arrives within the session's configured
     * default timeout, and with a {@link net.kurobako.cef4j.ipc.transport.CefTransportException} cause if the transport
     * disconnects or the session is closed.
     */
    @Nonnull
    <R extends CefMessageView> CompletableFuture<R> request(
            @Nonnull CefMessageEncoder request, @Nonnull CefMessageDecoder<R> decoder);

    /**
     * Subscribe to events of a specific {@code messageId}. Multiple subscribers per id are allowed; all are invoked in
     * registration order. Callbacks fire on the transport's reader thread; subscribers must not block.
     */
    @Nonnull
    <E extends CefMessageView> HandlerRegistration on(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler);

    /**
     * Subscribe and immediately replay the most recently received event for this message id, if any. Replay is
     * session-wide, not partitioned by browser or frame. Use it only for session-global state announcements or when the
     * handler deliberately accepts every source; scoped and edge-triggered events should use {@link #on}.
     */
    @Nonnull
    default <E extends CefMessageView> HandlerRegistration onLatest(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull Consumer<E> handler) {
        return on(messageId, decoder, handler);
    }

    /**
     * Register an intercept handler for a specific {@code messageId}. At most one handler per id; later registrations
     * replace earlier ones (a warning is logged). The handler runs on the transport's reader thread; the server is
     * synchronously parked waiting for the reply, so handlers must complete promptly.
     */
    @Nonnull
    <E extends CefMessageView> HandlerRegistration intercept(
            int messageId, @Nonnull CefMessageDecoder<E> decoder, @Nonnull InterceptHandler<E> handler);

    /** Register a callback invoked once when this session closes locally or loses its transport. */
    @Nonnull
    default HandlerRegistration onClose(@Nonnull Runnable handler) {
        return () -> {};
    }

    @Override
    void close();

    /** Returned from {@link #on}/{@link #intercept}; call {@link #unregister} to stop delivery. */
    interface HandlerRegistration extends AutoCloseable {
        void unregister();

        @Override
        default void close() {
            unregister();
        }
    }

    /** Synchronous-return intercept handler; return {@code null} to signal "default action" (empty response). */
    @FunctionalInterface
    interface InterceptHandler<E extends CefMessageView> {
        // null result means "no intercept" (default action)
        @SuppressWarnings("NullableForbidden")
        @Nullable
        CefMessageEncoder onIntercept(@Nonnull E event);
    }
}
