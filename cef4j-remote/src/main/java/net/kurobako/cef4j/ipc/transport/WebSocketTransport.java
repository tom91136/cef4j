package net.kurobako.cef4j.ipc.transport;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.net.ssl.SSLContext;
import org.jspecify.annotations.Nullable;
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
    private final PendingFrames pending = new PendingFrames();
    private final ByteArrayOutputStream fragments = new ByteArrayOutputStream();
    private final AtomicBoolean disconnectNotified = new AtomicBoolean();

    @Nullable
    private volatile WebSocket socket;

    @Nullable
    private volatile Consumer<ByteBuffer> receiveHandler;

    @Nullable
    private volatile Runnable disconnectHandler;

    private volatile boolean closed = false;
    private volatile boolean disconnected = false;

    public static final class Options {
        public static final Options DEFAULT = new Options(null, null);

        private final @Nullable String bearerToken;
        private final @Nullable SSLContext sslContext;

        private Options(@Nullable String bearerToken, @Nullable SSLContext sslContext) {
            this.bearerToken = bearerToken;
            this.sslContext = sslContext;
        }

        public static Options defaults() {
            return DEFAULT;
        }

        public Options withBearerToken(String bearerToken) {
            return new Options(Objects.requireNonNull(bearerToken, "bearerToken"), sslContext);
        }

        public Options withSslContext(SSLContext sslContext) {
            return new Options(bearerToken, Objects.requireNonNull(sslContext, "sslContext"));
        }

        public Optional<String> bearerToken() {
            return Optional.ofNullable(bearerToken);
        }

        public Optional<SSLContext> sslContext() {
            return Optional.ofNullable(sslContext);
        }
    }

    public static WebSocketTransport connect(String endpoint) throws CefTransportException {
        return connect(endpoint, Options.defaults());
    }

    /** Connects with optional bearer authentication and an explicit TLS context for {@code wss://}. */
    public static WebSocketTransport connect(String endpoint, Options options) throws CefTransportException {
        Objects.requireNonNull(endpoint, "endpoint");
        Objects.requireNonNull(options, "options");
        return connectImpl(endpoint, options.bearerToken, options.sslContext);
    }

    private static WebSocketTransport connectImpl(
            String endpoint, @Nullable String bearerToken, @Nullable SSLContext sslContext)
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
        final WebSocketTransport transport = new WebSocketTransport(endpoint);
        try {
            if (bearerToken != null
                    && (bearerToken.isEmpty() || bearerToken.contains("\r") || bearerToken.contains("\n"))) {
                throw new CefTransportException("invalid WebSocket bearer token");
            }
            final HttpClient.Builder clientBuilder =
                    HttpClient.newBuilder().connectTimeout(Duration.ofMillis(IO_TIMEOUT_MS));
            if (sslContext != null) {
                clientBuilder.sslContext(sslContext);
            }
            final WebSocket.Builder socketBuilder = clientBuilder.build().newWebSocketBuilder();
            if (bearerToken != null) {
                socketBuilder.header("Authorization", "Bearer " + bearerToken);
            }
            final CompletableFuture<WebSocket> connecting = socketBuilder.buildAsync(uri, transport);
            final CompletableFuture<WebSocket> ownedConnection = connecting.whenComplete((socket, ignored) -> {
                transport.socket = socket;
                if (socket != null && transport.closed) socket.abort();
            });
            try {
                transport.socket = ownedConnection.get(IO_TIMEOUT_MS, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException failure) {
                transport.closed = true;
                final WebSocket socket = transport.socket;
                if (socket != null) socket.abort();
                connecting.cancel(true);
                ownedConnection.cancel(true);
                throw failure;
            }
            return transport;
        } catch (InterruptedException e) {
            transport.closed = true;
            Thread.currentThread().interrupt();
            throw new CefTransportException(endpoint + ": connect interrupted", e);
        } catch (ExecutionException | TimeoutException | RuntimeException e) {
            transport.closed = true;
            throw new CefTransportException(endpoint + ": connect failed", e);
        }
    }

    private WebSocketTransport(String endpoint) {
        this.endpoint = endpoint;
    }

    public String endpoint() {
        return endpoint;
    }

    @Override
    public void send(ByteBuffer frame) throws CefTransportException {
        if (closed) throw new CefTransportException(endpoint + ": send on closed transport");
        if (disconnected) throw new CefTransportException(endpoint + ": peer disconnected");
        final byte[] copy = new byte[frame.remaining()];
        frame.get(copy);
        if (copy.length > MAX_FRAME_SIZE) throw new CefTransportException(endpoint + ": frame is too large");
        synchronized (sendLock) {
            final WebSocket current = socket;
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
    public void onReceive(Consumer<ByteBuffer> handler) {
        synchronized (receiveLock) {
            receiveHandler = handler;
            pending.drain(frame -> dispatch(handler, frame));
        }
    }

    @Override
    public void onDisconnect(Runnable handler) {
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
        final WebSocket current = socket;
        if (current == null) return;
        try {
            current.sendClose(WebSocket.NORMAL_CLOSURE, "").get(IO_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
            current.abort();
        } catch (ExecutionException | TimeoutException e) {
            LOG.debug("close on {} failed", endpoint, e);
            current.abort();
        }
    }

    @Override
    public CompletableFuture<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
        synchronized (fragmentLock) {
            if (data.remaining() > MAX_FRAME_SIZE - fragments.size()) return abortConnection(webSocket);
            final byte[] part = new byte[data.remaining()];
            data.get(part);
            fragments.write(part, 0, part.length);
            if (last) {
                final byte[] frame = fragments.toByteArray();
                fragments.reset();
                accept(frame);
            }
        }
        webSocket.request(1);
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        return abortConnection(webSocket);
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
        boolean overflow = false;
        synchronized (receiveLock) {
            final Consumer<ByteBuffer> handler = receiveHandler;
            if (handler == null && !pending.offer(frame)) {
                overflow = true;
            } else if (handler != null) dispatch(handler, frame);
        }
        if (overflow) {
            LOG.warn("pending receive queue on {} is full", endpoint);
            disconnected = true;
            final WebSocket current = socket;
            if (current != null) current.abort();
            fireDisconnectIfReady();
        }
    }

    private void dispatch(Consumer<ByteBuffer> handler, byte[] frame) {
        try {
            handler.accept(ByteBuffer.wrap(frame));
        } catch (RuntimeException e) {
            LOG.warn("receive handler on {} threw", endpoint, e);
        }
    }

    /** Aborts the connection, which the peer of it has to be told about. */
    private CompletableFuture<?> abortConnection(WebSocket webSocket) {
        disconnected = true;
        webSocket.abort();
        fireDisconnectIfReady();
        return CompletableFuture.completedFuture(null);
    }

    private void markDisconnected() {
        if (closed || disconnected) return;
        disconnected = true;
        fireDisconnectIfReady();
    }

    private void fireDisconnectIfReady() {
        final Runnable handler = disconnectHandler;
        if (disconnected && !closed && handler != null && disconnectNotified.compareAndSet(false, true)) {
            try {
                handler.run();
            } catch (RuntimeException e) {
                LOG.warn("disconnect handler on {} threw", endpoint, e);
            }
        }
    }
}
