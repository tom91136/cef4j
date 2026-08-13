package net.kurobako.cef4j.ipc.frame;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;

/** Chrome-compatible multipart MJPEG endpoint fed by any raw-frame transport. */
public final class MjpegHttpServer implements AutoCloseable {
    private static final String BOUNDARY = "cef4j-frame";
    private static final byte[] END = ("--" + BOUNDARY + "--\r\n").getBytes(StandardCharsets.US_ASCII);

    private final HttpServer server;
    private final String path;

    @Nullable
    private final String bearerToken;

    private final Set<Client> clients = ConcurrentHashMap.newKeySet();
    private final AtomicReference<FrameTransport> source = new AtomicReference<>();
    private final AtomicReference<byte[]> latestPart = new AtomicReference<>();
    private final EncodedFramePipeline pipeline;
    private final ExecutorService httpExecutor;

    private MjpegHttpServer(Configuration configuration) throws IOException {
        this.path = normalizePath(configuration.path);
        this.bearerToken = configuration.bearerToken;
        validateExposure(configuration);
        if (configuration.sslContext == null) {
            server = HttpServer.create(configuration.bindAddress, configuration.backlog);
        } else {
            HttpsServer https = HttpsServer.create(configuration.bindAddress, configuration.backlog);
            https.setHttpsConfigurator(new HttpsConfigurator(configuration.sslContext));
            server = https;
        }
        httpExecutor = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r, "cef4j-mjpeg-http");
            thread.setDaemon(true);
            return thread;
        });
        server.setExecutor(httpExecutor);
        server.createContext(path, this::serve);
        FrameCodec codec =
                new JpegFrameCodecProvider().newEncoder(Map.of("quality", Float.toString(configuration.quality)));
        pipeline = new EncodedFramePipeline(codec, this::publish);
        server.start();
    }

    @Nonnull
    public static MjpegHttpServer start(@Nonnull Configuration configuration) throws IOException {
        return new MjpegHttpServer(Objects.requireNonNull(configuration, "configuration"));
    }

    /** Atomically swaps frame sources, useful when a supervised runtime server starts a new generation. */
    public void attach(@Nonnull FrameTransport next) {
        Objects.requireNonNull(next, "next");
        next.onRawFrame(pipeline::submit);
        FrameTransport previous = source.getAndSet(next);
        if (previous != null && previous != next) previous.close();
    }

    @Nonnull
    public URI endpoint() {
        InetSocketAddress bound = server.getAddress();
        String scheme = server instanceof HttpsServer ? "https" : "http";
        String host = bound.getAddress().isAnyLocalAddress() ? "127.0.0.1" : bound.getHostString();
        return URI.create(scheme + "://" + host + ":" + bound.getPort() + path);
    }

    private void serve(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            exchange.close();
            return;
        }
        if (!authorized(exchange.getRequestHeaders())) {
            exchange.getResponseHeaders().set("WWW-Authenticate", "Bearer");
            exchange.sendResponseHeaders(401, -1);
            exchange.close();
            return;
        }
        Headers response = exchange.getResponseHeaders();
        response.set("Content-Type", "multipart/x-mixed-replace; boundary=" + BOUNDARY);
        response.set("Cache-Control", "no-store, no-cache, must-revalidate");
        response.set("Pragma", "no-cache");
        response.set("X-Content-Type-Options", "nosniff");
        exchange.sendResponseHeaders(200, 0);
        Client client = new Client(exchange.getResponseBody());
        clients.add(client);
        byte[] current = latestPart.get();
        if (current != null) client.offer(current);
        try {
            client.run();
        } finally {
            clients.remove(client);
            client.close();
            exchange.close();
        }
    }

    private boolean authorized(Headers headers) {
        return bearerToken == null || ("Bearer " + bearerToken).equals(headers.getFirst("Authorization"));
    }

    private void publish(EncodedFrame frame) {
        ByteBuffer payload = frame.payload();
        byte[] jpeg = new byte[payload.remaining()];
        payload.get(jpeg);
        byte[] prefix = ("--" + BOUNDARY + "\r\nContent-Type: image/jpeg\r\nContent-Length: " + jpeg.length
                        + "\r\nX-Cef4j-Sequence: " + frame.sequence() + "\r\n\r\n")
                .getBytes(StandardCharsets.US_ASCII);
        byte[] part = new byte[prefix.length + jpeg.length + 2];
        System.arraycopy(prefix, 0, part, 0, prefix.length);
        System.arraycopy(jpeg, 0, part, prefix.length, jpeg.length);
        part[part.length - 2] = '\r';
        part[part.length - 1] = '\n';
        latestPart.set(part);
        for (Client client : clients) client.offer(part);
    }

    @Override
    public void close() {
        FrameTransport attached = source.getAndSet(null);
        if (attached != null) attached.close();
        pipeline.close();
        for (Client client : clients) client.close();
        clients.clear();
        server.stop(0);
        httpExecutor.shutdownNow();
    }

    private static String normalizePath(String value) {
        Objects.requireNonNull(value, "path");
        if (!value.startsWith("/") || value.contains("..") || value.contains("?") || value.contains("#")) {
            throw new IllegalArgumentException("MJPEG path must be an absolute path without traversal or query");
        }
        return value;
    }

    private static void validateExposure(Configuration configuration) {
        InetAddress address = configuration.bindAddress.getAddress();
        boolean loopback = address != null && address.isLoopbackAddress();
        if (!loopback && !configuration.allowRemote) {
            throw new IllegalArgumentException("non-loopback MJPEG bind requires allowRemote=true");
        }
        if (!loopback && (configuration.sslContext == null || configuration.bearerToken == null)) {
            throw new IllegalArgumentException("remote MJPEG requires both TLS and bearer authentication");
        }
    }

    private static final class Client {
        private final OutputStream output;
        private final ArrayBlockingQueue<byte[]> latest = new ArrayBlockingQueue<>(1);
        private volatile boolean closed;

        private Client(OutputStream output) {
            this.output = output;
        }

        synchronized void offer(byte[] frame) {
            if (closed) return;
            latest.clear();
            if (!latest.offer(frame)) throw new IllegalStateException("failed to replace queued MJPEG frame");
        }

        void run() throws IOException {
            while (!closed) {
                try {
                    byte[] frame = latest.take();
                    output.write(frame);
                    output.flush();
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        synchronized void close() {
            if (closed) return;
            closed = true;
            latest.clear();
            if (!latest.offer(END)) throw new IllegalStateException("failed to enqueue MJPEG end marker");
            try {
                output.close();
            } catch (IOException ignored) {
                // Peer disconnects are normal for streaming HTTP responses.
            }
        }
    }

    public static final class Configuration {
        private final InetSocketAddress bindAddress;
        private final String path;
        private final int backlog;
        private final float quality;
        private final boolean allowRemote;

        @Nullable
        private final SSLContext sslContext;

        @Nullable
        private final String bearerToken;

        public Configuration(
                @Nonnull InetSocketAddress bindAddress,
                @Nonnull String path,
                int backlog,
                float quality,
                boolean allowRemote,
                @Nullable SSLContext sslContext,
                @Nullable String bearerToken) {
            this.bindAddress = Objects.requireNonNull(bindAddress, "bindAddress");
            this.path = Objects.requireNonNull(path, "path");
            if (backlog < 0) throw new IllegalArgumentException("backlog must not be negative");
            if (!(quality > 0.0f && quality <= 1.0f)) throw new IllegalArgumentException("quality must be in (0, 1]");
            if (bearerToken != null && bearerToken.isEmpty())
                throw new IllegalArgumentException("bearerToken is empty");
            this.backlog = backlog;
            this.quality = quality;
            this.allowRemote = allowRemote;
            this.sslContext = sslContext;
            this.bearerToken = bearerToken;
        }

        @Nonnull
        public static Configuration loopback(int port) {
            return new Configuration(
                    new InetSocketAddress(InetAddress.getLoopbackAddress(), port),
                    "/cef4j.mjpeg",
                    16,
                    0.80f,
                    false,
                    null,
                    null);
        }
    }
}
