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
import java.security.MessageDigest;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Chrome-compatible multipart MJPEG endpoint fed by any raw-frame transport. */
@SuppressWarnings("FutureReturnValueIgnored")
public final class MjpegHttpServer implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(MjpegHttpServer.class);
    private static final String BOUNDARY = "cef4j-frame";
    private static final byte[] END = ("--" + BOUNDARY + "--\r\n").getBytes(StandardCharsets.US_ASCII);

    private final HttpServer server;
    private final String path;

    private final Optional<String> bearerToken;
    private final long clientStallTimeoutNanos;

    private final Set<Client> clients = ConcurrentHashMap.newKeySet();
    private final Object attachLock = new Object();
    private final Object sourceLock = new Object();
    private final AtomicBoolean closed = new AtomicBoolean();
    private final AtomicReference<FrameTransport> source = new AtomicReference<>();
    private final AtomicReference<byte[]> latestPart = new AtomicReference<>();
    private final Supplier<FrameCodec> codecFactory;

    @Nullable
    private EncodedFramePipeline pipeline;

    private long sourceGeneration;

    @Nullable
    private final ExecutorService ownedHttpExecutor;

    private final ScheduledExecutorService watchdog;

    private MjpegHttpServer(Configuration configuration) throws IOException {
        this(configuration, codecFactory(configuration), null);
    }

    MjpegHttpServer(Configuration configuration, Supplier<FrameCodec> codecFactory) throws IOException {
        this(configuration, codecFactory, null);
    }

    private MjpegHttpServer(
            Configuration configuration, Supplier<FrameCodec> codecFactory, @Nullable Executor suppliedExecutor)
            throws IOException {
        this.path = normalizePath(configuration.path);
        this.bearerToken = configuration.bearerToken;
        this.clientStallTimeoutNanos = configuration.clientStallTimeout.toNanos();
        this.codecFactory = Objects.requireNonNull(codecFactory, "codecFactory");
        validateExposure(configuration);
        if (configuration.sslContext.isEmpty()) {
            server = HttpServer.create(configuration.bindAddress, configuration.backlog);
        } else {
            HttpsServer https = HttpsServer.create(configuration.bindAddress, configuration.backlog);
            https.setHttpsConfigurator(new HttpsConfigurator(configuration.sslContext.get()));
            server = https;
        }
        Executor executor;
        if (suppliedExecutor == null) {
            ownedHttpExecutor = newHttpExecutor();
            executor = ownedHttpExecutor;
        } else {
            ownedHttpExecutor = null;
            executor = suppliedExecutor;
        }
        watchdog = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "cef4j-mjpeg-watchdog");
            thread.setDaemon(true);
            return thread;
        });
        long watchdogInterval = Math.min(TimeUnit.SECONDS.toNanos(5), Math.max(1, clientStallTimeoutNanos / 2));
        try {
            watchdog.scheduleWithFixedDelay(
                    this::evictStalledClients, watchdogInterval, watchdogInterval, TimeUnit.NANOSECONDS);
            server.setExecutor(executor);
            server.createContext(path, this::serve);
            server.start();
        } catch (RuntimeException failure) {
            server.stop(0);
            watchdog.shutdownNow();
            if (ownedHttpExecutor != null) ownedHttpExecutor.shutdownNow();
            throw failure;
        }
    }

    @Nonnull
    public static MjpegHttpServer start(@Nonnull Configuration configuration) throws IOException {
        return new MjpegHttpServer(Objects.requireNonNull(configuration, "configuration"));
    }

    @Nonnull
    public static MjpegHttpServer start(@Nonnull Configuration configuration, @Nonnull Executor executor)
            throws IOException {
        Objects.requireNonNull(configuration, "configuration");
        return new MjpegHttpServer(
                configuration, codecFactory(configuration), Objects.requireNonNull(executor, "executor"));
    }

    /** Atomically swaps frame sources, useful when a supervised runtime server starts a new generation. */
    public void attach(@Nonnull FrameTransport next) {
        Objects.requireNonNull(next, "next");
        synchronized (attachLock) {
            if (closed.get()) throw new IllegalStateException("MJPEG server is closed");
            long generation;
            synchronized (sourceLock) {
                generation = sourceGeneration + 1;
            }
            EncodedFramePipeline nextPipeline;
            try {
                nextPipeline = new EncodedFramePipeline(
                        Objects.requireNonNull(codecFactory.get(), "codec factory returned null"),
                        frame -> publish(generation, frame));
            } catch (RuntimeException failure) {
                try {
                    next.close();
                } catch (RuntimeException closeFailure) {
                    failure.addSuppressed(closeFailure);
                }
                throw failure;
            }
            try {
                next.onRawFrame(nextPipeline::submit);
            } catch (RuntimeException failure) {
                try {
                    nextPipeline.close();
                } catch (RuntimeException closeFailure) {
                    failure.addSuppressed(closeFailure);
                }
                try {
                    next.close();
                } catch (RuntimeException closeFailure) {
                    failure.addSuppressed(closeFailure);
                }
                throw failure;
            }
            FrameTransport previous;
            EncodedFramePipeline previousPipeline;
            synchronized (sourceLock) {
                sourceGeneration = generation;
                previous = source.getAndSet(next);
                previousPipeline = pipeline;
                pipeline = nextPipeline;
                latestPart.set(null);
                for (Client client : clients) client.clearPending();
            }
            RuntimeException closeFailure = null;
            if (previous != null && previous != next) {
                try {
                    previous.close();
                } catch (RuntimeException failure) {
                    closeFailure = failure;
                }
            }
            if (previousPipeline != null) {
                try {
                    previousPipeline.close();
                } catch (RuntimeException failure) {
                    if (closeFailure == null) closeFailure = failure;
                    else closeFailure.addSuppressed(failure);
                }
            }
            if (closeFailure != null) throw closeFailure;
        }
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
        if (bearerToken.isEmpty()) return true;
        String expected = "Bearer " + bearerToken.get();
        String provided = headers.getFirst("Authorization");
        if (provided == null) return false;
        // Constant-time comparison so the token is not recoverable from request timing.
        return MessageDigest.isEqual(
                expected.getBytes(StandardCharsets.UTF_8), provided.getBytes(StandardCharsets.UTF_8));
    }

    private void evictStalledClients() {
        long now = System.nanoTime();
        for (Client client : clients) {
            if (!client.isStalled(now, clientStallTimeoutNanos)) continue;
            LOG.debug("closing MJPEG client that has stopped making write progress");
            client.close();
        }
    }

    private void publish(long generation, EncodedFrame frame) {
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
        synchronized (sourceLock) {
            if (closed.get() || generation != sourceGeneration) return;
            latestPart.set(part);
            for (Client client : clients) client.offer(part);
        }
    }

    @Override
    public void close() {
        synchronized (attachLock) {
            if (!closed.compareAndSet(false, true)) return;
            FrameTransport attached;
            EncodedFramePipeline attachedPipeline;
            synchronized (sourceLock) {
                sourceGeneration++;
                attached = source.getAndSet(null);
                attachedPipeline = pipeline;
                pipeline = null;
            }
            RuntimeException closeFailure = null;
            if (attached != null) {
                try {
                    attached.close();
                } catch (RuntimeException failure) {
                    closeFailure = failure;
                }
            }
            if (attachedPipeline != null) {
                try {
                    attachedPipeline.close();
                } catch (RuntimeException failure) {
                    if (closeFailure == null) closeFailure = failure;
                    else closeFailure.addSuppressed(failure);
                }
            }
            for (Client client : clients) client.close();
            clients.clear();
            server.stop(0);
            watchdog.shutdownNow();
            if (ownedHttpExecutor != null) ownedHttpExecutor.shutdownNow();
            if (closeFailure != null) throw closeFailure;
        }
    }

    private static String normalizePath(String value) {
        Objects.requireNonNull(value, "path");
        if (!value.startsWith("/") || value.contains("..") || value.contains("?") || value.contains("#")) {
            throw new IllegalArgumentException("MJPEG path must be an absolute path without traversal or query");
        }
        return value;
    }

    private static Supplier<FrameCodec> codecFactory(Configuration configuration) {
        return () -> new JpegFrameCodecProvider().newEncoder(Map.of("quality", Float.toString(configuration.quality)));
    }

    private static ExecutorService newHttpExecutor() {
        AtomicInteger sequence = new AtomicInteger();
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), task -> {
            Thread thread = new Thread(task, "cef4j-mjpeg-http-" + sequence.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        });
    }

    private static void validateExposure(Configuration configuration) {
        InetAddress address = configuration.bindAddress.getAddress();
        boolean loopback = address != null && address.isLoopbackAddress();
        if (!loopback && !configuration.allowRemote) {
            throw new IllegalArgumentException("non-loopback MJPEG bind requires allowRemote=true");
        }
        if (!loopback && (configuration.sslContext.isEmpty() || configuration.bearerToken.isEmpty())) {
            throw new IllegalArgumentException("remote MJPEG requires both TLS and bearer authentication");
        }
    }

    private static final class Client {
        private final OutputStream output;
        private final ArrayBlockingQueue<byte[]> latest = new ArrayBlockingQueue<>(1);
        private volatile boolean closed;
        private volatile long writeStartedNanos;

        private Client(OutputStream output) {
            this.output = output;
        }

        synchronized void offer(byte[] frame) {
            if (closed) return;
            latest.clear();
            if (!latest.offer(frame)) throw new IllegalStateException("failed to replace queued MJPEG frame");
        }

        synchronized void clearPending() {
            if (!closed) latest.clear();
        }

        boolean isStalled(long now, long timeoutNanos) {
            long started = writeStartedNanos;
            return !closed && started != 0 && now - started > timeoutNanos;
        }

        void run() throws IOException {
            while (true) {
                byte[] frame;
                try {
                    frame = latest.take();
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    return;
                }
                writeStartedNanos = System.nanoTime();
                try {
                    output.write(frame);
                    output.flush();
                } finally {
                    writeStartedNanos = 0;
                }
                if (frame == END) return;
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

        private final Optional<SSLContext> sslContext;

        private final Optional<String> bearerToken;
        private final Duration clientStallTimeout;

        public Configuration(
                @Nonnull InetSocketAddress bindAddress,
                @Nonnull String path,
                int backlog,
                float quality,
                boolean allowRemote,
                Optional<SSLContext> sslContext,
                Optional<String> bearerToken) {
            this(bearerToken, Duration.ofSeconds(30), bindAddress, path, backlog, quality, allowRemote, sslContext);
        }

        private Configuration(
                Optional<String> bearerToken,
                Duration clientStallTimeout,
                InetSocketAddress bindAddress,
                String path,
                int backlog,
                float quality,
                boolean allowRemote,
                Optional<SSLContext> sslContext) {
            this.bindAddress = Objects.requireNonNull(bindAddress, "bindAddress");
            this.path = Objects.requireNonNull(path, "path");
            if (backlog < 0) throw new IllegalArgumentException("backlog must not be negative");
            if (!(quality > 0.0f && quality <= 1.0f)) throw new IllegalArgumentException("quality must be in (0, 1]");
            if (bearerToken.isPresent() && bearerToken.get().isEmpty())
                throw new IllegalArgumentException("bearerToken is empty");
            if (clientStallTimeout.isZero() || clientStallTimeout.isNegative())
                throw new IllegalArgumentException("clientStallTimeout must be positive");
            this.backlog = backlog;
            this.quality = quality;
            this.allowRemote = allowRemote;
            this.sslContext = sslContext;
            this.bearerToken = bearerToken;
            this.clientStallTimeout = clientStallTimeout;
        }

        @Nonnull
        public Configuration withClientStallTimeout(@Nonnull Duration timeout) {
            return new Configuration(
                    bearerToken,
                    Objects.requireNonNull(timeout, "timeout"),
                    bindAddress,
                    path,
                    backlog,
                    quality,
                    allowRemote,
                    sslContext);
        }

        @Nonnull
        public static Configuration loopback(int port) {
            return new Configuration(
                    new InetSocketAddress(InetAddress.getLoopbackAddress(), port),
                    "/cef4j.mjpeg",
                    16,
                    0.80f,
                    false,
                    Optional.empty(),
                    Optional.empty());
        }
    }
}
