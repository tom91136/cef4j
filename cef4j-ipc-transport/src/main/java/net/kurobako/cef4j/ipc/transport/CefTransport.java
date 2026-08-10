package net.kurobako.cef4j.ipc.transport;

import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

/**
 * Byte-frame channel between the JVM and the native CEF helper. Opaque to envelope/wire format - the session layer (in
 * {@code cef4j-ipc-session}) imposes that on top.
 *
 * <p>Lifecycle:
 *
 * <ol>
 *   <li>Construct via an implementation-specific factory ({@code ZmqTransport.bind(...)},
 *       {@code LoopbackTransport.create()}, etc.).
 *   <li>Register handlers via {@link #onReceive} and {@link #onDisconnect}. Frames that arrive before {@code onReceive}
 *       is set are buffered by the implementation and delivered when the handler is registered; no frames are dropped.
 *   <li>Send via {@link #send}; receive via the registered handler.
 *   <li>{@link #close} releases resources and is idempotent. {@link #onDisconnect} fires only for <em>remote</em>
 *       disconnects (peer crash, network drop), not for a local {@code close()}.
 * </ol>
 *
 * <p>Threading: {@code send} is thread-safe. The {@code onReceive} callback fires on the transport's internal reader
 * thread; consumers that retain the buffer past callback return must copy.
 */
public interface CefTransport extends Closeable {

    /**
     * Send a frame. Implementations serialise concurrent calls.
     *
     * @param frame the bytes to send. Read from {@code position} to {@code limit}; the buffer's position is advanced to
     *     {@code limit} on return. Buffer is not retained past this call.
     * @throws CefTransportException if the transport is closed, disconnected, or the frame cannot be sent.
     */
    void send(@Nonnull ByteBuffer frame) throws CefTransportException;

    /**
     * Register the receive handler. Single subscriber; calling twice replaces the previous handler. The buffer passed
     * to {@code handler} is owned by the transport and valid only for the duration of the callback.
     */
    void onReceive(@Nonnull Consumer<ByteBuffer> handler);

    /**
     * Register the remote-disconnect handler. Single subscriber; fires at most once per transport lifetime. Not fired
     * for local {@link #close} calls.
     */
    void onDisconnect(@Nonnull Runnable handler);

    /** Returns {@code true} if the transport is currently connected and capable of sending. */
    boolean isConnected();

    /** Close the transport, releasing any underlying socket/thread resources. Idempotent. */
    @Override
    void close();
}
