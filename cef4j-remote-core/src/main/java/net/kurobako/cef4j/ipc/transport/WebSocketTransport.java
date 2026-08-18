package net.kurobako.cef4j.ipc.transport;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** WebSocket transport backed by the Java 11 HTTP client. Each binary message is one cef4j frame. */
public final class WebSocketTransport implements CefTransport, WebSocket.Listener {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketTransport.class);
    private static final int MAX_FRAME_SIZE = 64 * 1024 * 1024;
    private static final long IO_TIMEOUT_MS = 30_000;

    private final String endpoint;
    private final Object sendLock = new Object();
    private final Object receiveLock = new Object();
    private final Object fragmentLock = new Object();
    private final ArrayDeque<byte[]> pending = new ArrayDeque<>();
    private final ByteArrayOutputStream fragments = new ByteArrayOutputStream();
    private final AtomicBoolean disconnectNotified = new AtomicBoolean();

    @Nullable
    private volatile WebSocket socket;

    @Nullable
    private volatile Consumer<ByteBuffer> receiveHandler;

    @Nullable
    private volatile Runnable disconnectHandler;

    private volatile boolean closed;
    private volatile boolean disconnected;

    @Nonnull
    public static WebSocketTransport connect(@Nonnull String endpoint) throws CefTransportException {
        return connect(endpoint, Optional.empty(), Optional.empty());
    }

    /** Connects with optional bearer authentication and an explicit TLS context for {@code wss://}. */
    @Nonnull
    public static WebSocketTransport connect(
            @Nonnull String endpoint, Optional<String> bearerToken, Optional<SSLContext> sslContext)
            throws CefTransportException {
        URI uri;
        try {
            uri = URI.create(endpoint);
        } catch (IllegalArgumentException e) {
            throw new CefTransportException(endpoint + ": invalid WebSocket endpoint", e);
        }
        if (!"ws".equalsIgnoreCase(uri.getScheme()) && !"wss".equalsIgnoreCase(uri.getScheme())) {
            throw new CefTransportException(endpoint + ": expected a ws:// or wss:// endpoint");
        }
        WebSocketTransport transport = new WebSocketTransport(endpoint);
        try {
            if (bearerToken.isPresent()
                    && (bearerToken.get().isEmpty()
                            || bearerToken.get().contains("\r")
                            || bearerToken.get().contains("\n"))) {
                throw new CefTransportException("invalid WebSocket bearer token");
            }
            HttpClient.Builder clientBuilder = HttpClient.newBuilder();
            sslContext.ifPresent(clientBuilder::sslContext);
            WebSocket.Builder socketBuilder = clientBuilder.build().newWebSocketBuilder();
            bearerToken.ifPresent(token -> socketBuilder.header("Authorization", "Bearer " + token));
            transport.socket = socketBuilder.buildAsync(uri, transport).join();
            return transport;
        } catch (RuntimeException e) {
            transport.closed = true;
            throw new CefTransportException(endpoint + ": connect failed", e);
        }
    }

    private WebSocketTransport(String endpoint) {
        this.endpoint = endpoint;
    }

    @Nonnull
    public String endpoint() {
        return endpoint;
    }

    @Override
    public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException(endpoint + ": send on closed transport");
        if (disconnected) throw new CefTransportException(endpoint + ": peer disconnected");
        byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        if (copy.length > MAX_FRAME_SIZE) throw new CefTransportException(endpoint + ": frame is too large");
        synchronized (sendLock) {
            WebSocket current = socket;
            if (current == null) throw new CefTransportException(endpoint + ": transport is not connected");
            try {
                current.sendBinary(ByteBuffer.wrap(copy), true).get(IO_TIMEOUT_MS, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                markDisconnected();
                throw new CefTransportException(endpoint + ": send interrupted", e);
            } catch (ExecutionException | TimeoutException e) {
                markDisconnected();
                current.abort();
                throw new CefTransportException(endpoint + ": send failed", e);
            }
        }
    }

    @Override
    public void onReceive(@Nonnull Consumer<ByteBuffer> handler) {
        synchronized (receiveLock) {
            receiveHandler = handler;
            byte[] frame;
            while ((frame = pending.poll()) != null) dispatch(handler, frame);
        }
    }

    @Override
    public void onDisconnect(@Nonnull Runnable handler) {
        disconnectHandler = handler;
        fireDisconnectIfReady();
    }

    @Override
    public boolean isConnected() {
        return socket != null && !closed && !disconnected;
    }

    @Override
    public boolean isRuntimeServerClient() {
        return true;
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        WebSocket current = socket;
        if (current == null) return;
        try {
            current.sendClose(WebSocket.NORMAL_CLOSURE, "").get(IO_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            current.abort();
        } catch (ExecutionException | TimeoutException e) {
            LOG.debug("close on {} failed", endpoint, e);
            current.abort();
        }
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
    }

    @Override
    public CompletableFuture<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
        synchronized (fragmentLock) {
            if (data.remaining() > MAX_FRAME_SIZE - fragments.size()) {
                disconnected = true;
                webSocket.abort();
                fireDisconnectIfReady();
                return CompletableFuture.completedFuture(null);
            }
            byte[] part = new byte[data.remaining()];
            data.get(part);
            fragments.write(part, 0, part.length);
            if (last) {
                byte[] frame = fragments.toByteArray();
                fragments.reset();
                accept(frame);
            }
        }
        webSocket.request(1);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        disconnected = true;
        webSocket.abort();
        fireDisconnectIfReady();
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        return webSocket.sendPong(message);
    }

    @Override
    public CompletableFuture<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        markDisconnected();
        return closed ? CompletableFuture.completedFuture(null) : webSocket.sendClose(statusCode, reason);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        if (!closed) LOG.debug("WebSocket on {} failed", endpoint, error);
        markDisconnected();
    }

    private void accept(byte[] frame) {
        synchronized (receiveLock) {
            Consumer<ByteBuffer> handler = receiveHandler;
            if (handler == null) pending.add(frame);
            else dispatch(handler, frame);
        }
    }

    private void dispatch(Consumer<ByteBuffer> handler, byte[] frame) {
        try {
            handler.accept(ByteBuffer.wrap(frame));
        } catch (RuntimeException e) {
            LOG.warn("receive handler on {} threw", endpoint, e);
        }
    }

    private void markDisconnected() {
        if (closed || disconnected) return;
        disconnected = true;
        fireDisconnectIfReady();
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
}
