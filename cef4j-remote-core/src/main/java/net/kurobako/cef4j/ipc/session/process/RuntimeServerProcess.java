package net.kurobako.cef4j.ipc.session.process;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.ipc.transport.CefTransportException;
import net.kurobako.cef4j.ipc.transport.CefTransports;
import net.kurobako.cef4j.ipc.transport.WebSocketTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spawns and supervises the packaged cef4j runtime server. Bootstrap protocol:
 *
 * <ol>
 *   <li>Process is started with {@code <binary> --transport <name> --bind <bindEndpoint> --frame-transport <name>}.
 *   <li>The server binds, then writes one versioned {@code CEF4J_RUNTIME_SERVER ...} handshake line to stdout.
 *   <li>{@link #spawn} blocks reading that line, then returns. Subsequent output is drained to the runtime-server
 *       loggers.
 *   <li>{@link #close} sends SIGTERM, waits up to 5s, then SIGKILL.
 * </ol>
 *
 * <p>Crash detection during normal operation surfaces through the transport: if the server dies, the JVM-side
 * {@link net.kurobako.cef4j.ipc.transport.CefTransport}'s {@code onDisconnect} fires.
 */
public final class RuntimeServerProcess implements Closeable {

    private static final Logger LOG = LoggerFactory.getLogger(RuntimeServerProcess.class);
    private static final Logger STDOUT_LOG = LoggerFactory.getLogger("runtime-server-stdout");
    private static final Logger STDERR_LOG = LoggerFactory.getLogger("runtime-server-stderr");

    private static final Duration DEFAULT_BOOTSTRAP_TIMEOUT = Duration.ofSeconds(10);
    private static final long CLOSE_GRACE_MS = 5000;

    private final Process process;
    private final String transport;
    private final String frameTransport;
    private final String endpoint;
    private final RuntimeServerHandshake handshake;

    public static RuntimeServerProcess spawn(@Nonnull Path binary, @Nonnull String bindEndpoint) throws IOException {
        return spawn(binary, "zmq", bindEndpoint, "shared-file", DEFAULT_BOOTSTRAP_TIMEOUT);
    }

    public static RuntimeServerProcess spawn(
            @Nonnull Path binary, @Nonnull String bindEndpoint, @Nonnull Duration bootstrapTimeout) throws IOException {
        return spawn(binary, "zmq", bindEndpoint, "shared-file", bootstrapTimeout);
    }

    public static RuntimeServerProcess spawn(
            @Nonnull Path binary, @Nonnull String transport, @Nonnull String bindEndpoint) throws IOException {
        return spawn(binary, transport, bindEndpoint, "shared-file", DEFAULT_BOOTSTRAP_TIMEOUT);
    }

    public static RuntimeServerProcess spawn(
            @Nonnull Path binary,
            @Nonnull String transport,
            @Nonnull String bindEndpoint,
            @Nonnull String frameTransport)
            throws IOException {
        return spawn(binary, transport, bindEndpoint, frameTransport, DEFAULT_BOOTSTRAP_TIMEOUT);
    }

    public static RuntimeServerProcess spawn(
            @Nonnull Path binary,
            @Nonnull String transport,
            @Nonnull String bindEndpoint,
            @Nonnull Duration bootstrapTimeout)
            throws IOException {
        return spawn(binary, transport, bindEndpoint, "shared-file", bootstrapTimeout);
    }

    public static RuntimeServerProcess spawn(
            @Nonnull Path binary,
            @Nonnull String transport,
            @Nonnull String bindEndpoint,
            @Nonnull String frameTransport,
            @Nonnull Duration bootstrapTimeout)
            throws IOException {
        return spawn(binary, transport, bindEndpoint, frameTransport, bootstrapTimeout, Map.of());
    }

    /** Spawns a runtime server with explicit environment additions, useful for portable CEF distributions. */
    public static RuntimeServerProcess spawn(
            @Nonnull Path binary,
            @Nonnull String transport,
            @Nonnull String bindEndpoint,
            @Nonnull String frameTransport,
            @Nonnull Duration bootstrapTimeout,
            @Nonnull Map<String, String> environment)
            throws IOException {
        Objects.requireNonNull(binary, "binary");
        Objects.requireNonNull(transport, "transport");
        Objects.requireNonNull(bindEndpoint, "bindEndpoint");
        Objects.requireNonNull(frameTransport, "frameTransport");
        Objects.requireNonNull(bootstrapTimeout, "bootstrapTimeout");
        Objects.requireNonNull(environment, "environment");

        ProcessBuilder pb = new ProcessBuilder(
                binary.toString(),
                "--transport",
                transport,
                "--bind",
                bindEndpoint,
                "--frame-transport",
                frameTransport);
        pb.environment().putAll(environment);
        pb.redirectErrorStream(false);
        Process p = pb.start();

        CompletableFuture<RuntimeServerHandshake> handshakeFuture = new CompletableFuture<>();
        startReader(p.getInputStream(), "runtime-server-stdout-" + p.pid(), line -> {
            if (!handshakeFuture.isDone()) {
                if (line.startsWith(RuntimeServerHandshake.PREFIX)) {
                    try {
                        handshakeFuture.complete(RuntimeServerHandshake.parse(line));
                    } catch (IllegalArgumentException invalidHandshake) {
                        handshakeFuture.completeExceptionally(
                                new IOException("invalid runtime server handshake", invalidHandshake));
                    }
                    return;
                }
            }
            STDOUT_LOG.info("{}", line);
        });
        startReader(p.getErrorStream(), "runtime-server-stderr-" + p.pid(), line -> STDERR_LOG.info("{}", line));

        watchForExit(p, bindEndpoint, handshakeFuture);

        try {
            RuntimeServerHandshake handshake = handshakeFuture.get(bootstrapTimeout.toMillis(), TimeUnit.MILLISECONDS);
            if (!transport.equals(handshake.transport()) || !frameTransport.equals(handshake.frameTransport())) {
                terminateProcessTree(p);
                cleanupProcessFiles(p.pid());
                cleanupUdsSocket(bindEndpoint);
                throw new IOException("runtime server selected unexpected providers: " + handshake);
            }
            return new RuntimeServerProcess(p, handshake);
        } catch (TimeoutException e) {
            terminateProcessTree(p);
            cleanupProcessFiles(p.pid());
            cleanupUdsSocket(bindEndpoint);
            throw new IOException("runtime server did not publish its handshake within " + bootstrapTimeout, e);
        } catch (InterruptedException e) {
            terminateProcessTree(p);
            cleanupProcessFiles(p.pid());
            cleanupUdsSocket(bindEndpoint);
            Thread.currentThread().interrupt();
            throw new IOException("interrupted while awaiting runtime server bootstrap", e);
        } catch (ExecutionException e) {
            terminateProcessTree(p);
            cleanupProcessFiles(p.pid());
            cleanupUdsSocket(bindEndpoint);
            Throwable cause = e.getCause();
            throw (cause instanceof IOException) ? (IOException) cause : new IOException(cause);
        }
    }

    /** Registers a deliberately detached watcher that owns process-exit cleanup and bootstrap failure reporting. */
    @SuppressWarnings("FutureReturnValueIgnored")
    private static void watchForExit(
            Process process, String bindEndpoint, CompletableFuture<RuntimeServerHandshake> handshakeFuture) {
        process.onExit().thenAccept(exited -> {
            cleanupProcessFiles(exited.pid());
            cleanupUdsSocket(bindEndpoint);
            if (!handshakeFuture.isDone()) {
                handshakeFuture.completeExceptionally(
                        new IOException("runtime server exited before handshake (exit=" + exited.exitValue() + ")"));
            }
        });
    }

    private RuntimeServerProcess(Process process, RuntimeServerHandshake handshake) {
        this.process = process;
        this.handshake = handshake;
        this.transport = handshake.transport();
        this.frameTransport = handshake.frameTransport();
        this.endpoint = handshake.endpoint();
    }

    @Nonnull
    public RuntimeServerHandshake handshake() {
        return handshake;
    }

    @Nonnull
    public String transport() {
        return transport;
    }

    @Nonnull
    public String frameTransport() {
        return frameTransport;
    }

    @Nonnull
    public String endpoint() {
        return endpoint;
    }

    /** Connects using the provider selected by the runtime server. */
    @Nonnull
    public CefTransport connect() throws CefTransportException {
        return CefTransports.connect(transport, endpoint);
    }

    /** Connects an authenticated WebSocket generation, optionally trusting a caller-supplied TLS context. */
    @Nonnull
    public CefTransport connectWebSocket(@Nullable String bearerToken, @Nullable SSLContext sslContext)
            throws CefTransportException {
        if (!"websocket".equals(transport)) {
            throw new CefTransportException("runtime server selected " + transport + ", not websocket");
        }
        return WebSocketTransport.connect(endpoint, bearerToken, sslContext);
    }

    public boolean isAlive() {
        return process.isAlive();
    }

    public long pid() {
        return process.pid();
    }

    /** Completes with the process exit code, whether exit was expected or a crash. */
    @Nonnull
    public CompletableFuture<Integer> onExit() {
        return process.onExit().thenApply(Process::exitValue);
    }

    /** Immediately terminates this server generation. Primarily useful for recovery tests and operator controls. */
    public void kill() {
        terminateProcessTree(process);
    }

    @Override
    public void close() {
        List<ProcessHandle> descendants = descendantsOf(process);
        if (!process.isAlive()) {
            destroy(descendants, true);
            cleanupProcessFiles(process.pid());
            cleanupUdsSocket(endpoint);
            return;
        }
        process.destroy();
        destroy(descendants, false);
        try {
            long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(CLOSE_GRACE_MS);
            boolean processAlive = !process.waitFor(CLOSE_GRACE_MS, TimeUnit.MILLISECONDS);
            long remaining = Math.max(0L, deadline - System.nanoTime());
            boolean descendantsExited = awaitExit(descendants, TimeUnit.NANOSECONDS.toMillis(remaining));
            if (processAlive || !descendantsExited) {
                LOG.warn(
                        "runtime server process tree pid={} did not exit within {}ms; forcing termination",
                        process.pid(),
                        CLOSE_GRACE_MS);
                process.destroyForcibly();
                destroy(descendants, true);
                process.waitFor(CLOSE_GRACE_MS, TimeUnit.MILLISECONDS);
                if (!awaitExit(descendants, CLOSE_GRACE_MS)) {
                    LOG.warn(
                            "runtime server pid={} still has live descendants after forced termination", process.pid());
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            process.destroyForcibly();
            destroy(descendants, true);
        } finally {
            // A Windows launcher is commonly cmd.exe with the actual runtime server as a child. Capture descendants
            // before stopping the launcher and always sweep them so repeated tests cannot leak CEF subprocesses.
            destroy(descendants, true);
            cleanupProcessFiles(process.pid());
            cleanupUdsSocket(endpoint);
        }
    }

    private static void terminateProcessTree(Process process) {
        List<ProcessHandle> descendants = descendantsOf(process);
        destroy(descendants, true);
        process.destroyForcibly();
    }

    private static List<ProcessHandle> descendantsOf(Process process) {
        try (Stream<ProcessHandle> descendants = process.descendants()) {
            return descendants.collect(Collectors.toList());
        } catch (UnsupportedOperationException | SecurityException e) {
            LOG.debug("cannot enumerate descendants of runtime server pid={}: {}", process.pid(), e.toString());
            return List.of();
        }
    }

    private static void destroy(List<ProcessHandle> processes, boolean forcibly) {
        // Captured ProcessHandles remain usable if the hierarchy changes, so sweep every known subprocess.
        for (ProcessHandle process : processes) {
            if (!process.isAlive()) continue;
            try {
                if (forcibly) process.destroyForcibly();
                else process.destroy();
            } catch (UnsupportedOperationException | SecurityException e) {
                LOG.debug("cannot terminate runtime server descendant pid={}: {}", process.pid(), e.toString());
            }
        }
    }

    private static boolean awaitExit(List<ProcessHandle> processes, long timeoutMillis) throws InterruptedException {
        CompletableFuture<?>[] exits = processes.stream()
                .filter(ProcessHandle::isAlive)
                .map(ProcessHandle::onExit)
                .toArray(CompletableFuture<?>[]::new);
        if (exits.length == 0) return true;
        try {
            CompletableFuture.allOf(exits).get(timeoutMillis, TimeUnit.MILLISECONDS);
            return true;
        } catch (ExecutionException e) {
            LOG.debug("failed while awaiting runtime server descendants", e);
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * SIGTERM/forced termination can bypass server destructors, so shared frame files may remain in the temporary
     * directory. Names include the exact server PID, preventing one generation from deleting another's mappings.
     */
    private static void cleanupProcessFiles(long pid) {
        Path tempRoot = Paths.get(System.getProperty("java.io.tmpdir"));
        if (!Files.isDirectory(tempRoot)) return;
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(tempRoot, "cef4j-paint-" + pid + "-*.frame")) {
            for (Path entry : entries) Files.deleteIfExists(entry);
        } catch (IOException e) {
            // Windows cannot delete a file while an outstanding mapping view remains. It will be retried when that
            // frame transport closes; a new generation uses a different PID and never reuses this path.
            LOG.debug("failed to clean shared frame files for runtime server pid={}: {}", pid, e.toString());
        }
        Path cache = tempRoot.resolve("cef4j-runtime-server-" + pid).normalize();
        if (!Objects.equals(cache.getParent(), tempRoot.normalize()) || !Files.isDirectory(cache)) return;
        try (Stream<Path> entries = Files.walk(cache)) {
            entries.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e) {
                    LOG.debug("failed to clean runtime server cache entry {}: {}", path, e.toString());
                }
            });
        } catch (IOException | UncheckedIOException e) {
            LOG.debug("failed to traverse runtime server cache {}: {}", cache, e.toString());
        }
    }

    private static void cleanupUdsSocket(String endpoint) {
        if (!endpoint.startsWith("unix://")) return;
        try {
            Files.deleteIfExists(Paths.get(endpoint.substring("unix://".length())));
        } catch (IOException e) {
            LOG.debug("failed to clean runtime server UDS socket {}: {}", endpoint, e.toString());
        }
    }

    private static void startReader(InputStream in, String threadName, Consumer<String> onLine) {
        Thread t = new Thread(
                () -> {
                    try (BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = r.readLine()) != null) {
                            try {
                                onLine.accept(line);
                            } catch (RuntimeException e) {
                                LOG.warn("runtime server output handler threw on '{}'", line, e);
                            }
                        }
                    } catch (IOException ignored) {
                        // stream closed; reader exits
                    }
                },
                threadName);
        t.setDaemon(true);
        t.start();
    }
}
