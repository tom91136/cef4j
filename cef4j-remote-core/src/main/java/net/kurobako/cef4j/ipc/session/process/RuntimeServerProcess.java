package net.kurobako.cef4j.ipc.session.process;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
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
 *   <li>{@link #close} requests backend-independent graceful shutdown over inherited stdin, waits up to 5s, then
 *       forcibly terminates an unresponsive process tree.
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
    private static final String GRACEFUL_SHUTDOWN_CAPABILITY = "graceful-shutdown";
    private static final byte[] SHUTDOWN_COMMAND = "CEF4J_SHUTDOWN\n".getBytes(StandardCharsets.US_ASCII);
    private static final int BOOTSTRAP_DIAGNOSTIC_LINES = 40;
    private static final String EXTRA_ARGUMENTS_PROPERTY = "cef4j.runtime.server.extraArgs";

    private final Process process;
    private final String transport;
    private final String frameTransport;
    private final String endpoint;
    private final RuntimeServerHandshake handshake;
    private final Deque<String> recentOutput;
    private final AtomicBoolean closed = new AtomicBoolean();

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

        ProcessBuilder pb = new ProcessBuilder(serverCommand(
                binary, transport, bindEndpoint, frameTransport, System.getProperty(EXTRA_ARGUMENTS_PROPERTY)));
        pb.environment().putAll(environment);
        alignTempEnvironment(
                pb.environment(),
                Paths.get(System.getProperty("java.io.tmpdir")).toRealPath(),
                System.getProperty("os.name", ""));
        pb.redirectErrorStream(false);
        Process p = pb.start();

        CompletableFuture<RuntimeServerHandshake> handshakeFuture = new CompletableFuture<>();
        Deque<String> recentOutput = new ConcurrentLinkedDeque<>();
        startReader(p.getInputStream(), "runtime-server-stdout-" + p.pid(), line -> {
            rememberOutput(recentOutput, "stdout: " + line);
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
            STDOUT_LOG.trace("{}", line);
        });
        startReader(p.getErrorStream(), "runtime-server-stderr-" + p.pid(), line -> {
            rememberOutput(recentOutput, "stderr: " + line);
            logStderr(line);
        });

        watchForExit(p, bindEndpoint, handshakeFuture, recentOutput);

        try {
            RuntimeServerHandshake handshake = handshakeFuture.get(bootstrapTimeout.toMillis(), TimeUnit.MILLISECONDS);
            if (!transport.equals(handshake.transport()) || !frameTransport.equals(handshake.frameTransport())) {
                terminateProcessTree(p);
                cleanupProcessFiles(p.pid());
                cleanupUdsSocket(bindEndpoint);
                throw new IOException("runtime server selected unexpected providers: " + handshake);
            }
            return new RuntimeServerProcess(p, handshake, recentOutput);
        } catch (TimeoutException e) {
            terminateProcessTree(p);
            cleanupProcessFiles(p.pid());
            cleanupUdsSocket(bindEndpoint);
            throw new IOException(
                    "runtime server did not publish its handshake within " + bootstrapTimeout
                            + diagnosticSuffix(recentOutput),
                    e);
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

    static void alignTempEnvironment(Map<String, String> environment, Path tempDirectory, String osName) {
        String value = tempDirectory.toString();
        if (osName.toLowerCase(Locale.ROOT).contains("win")) {
            environment.put("TEMP", value);
            environment.put("TMP", value);
        } else {
            environment.put("TMPDIR", value);
        }
    }

    /**
     * Builds the runtime-server command, including optional comma-separated CEF switches from
     * {@code cef4j.runtime.server.extraArgs}. This is primarily useful for deployment-specific Chromium switches; the
     * runtime server otherwise preserves CEF's defaults.
     */
    static List<String> serverCommand(
            Path binary, String transport, String bindEndpoint, String frameTransport, @Nullable String extraArgs) {
        List<String> command = new ArrayList<>(List.of(
                binary.toString(),
                "--transport",
                transport,
                "--bind",
                bindEndpoint,
                "--frame-transport",
                frameTransport));
        if (extraArgs != null && !extraArgs.isBlank()) {
            Stream.of(extraArgs.split(","))
                    .map(String::trim)
                    .filter(argument -> !argument.isEmpty())
                    .forEach(command::add);
        }
        return command;
    }

    static ChildOutputLevel stderrLevel(String line) {
        if (isRoutineLifecycleLine(line)) {
            return ChildOutputLevel.TRACE;
        }
        if (line.contains("zmq_send failed: Host unreachable")
                || line.contains("zmq_send failed: Context was terminated")
                || line.contains(":INFO:")) {
            return ChildOutputLevel.DEBUG;
        }
        return ChildOutputLevel.WARN;
    }

    private static boolean isRoutineLifecycleLine(String line) {
        return line.startsWith("[cef4j-runtime-server] shutdown: closing ")
                || line.equals("[cef4j-runtime-server] shutdown: final browser closed")
                || line.equals("[cef4j-runtime-server] shutdown: quitting CEF message loop")
                || line.equals("[cef4j-runtime-server] shutdown: parent command received")
                || line.equals("[cef4j-runtime-server] shutdown: CEF message loop returned")
                || line.equals("[cef4j-runtime-server] shutdown: stopping IPC transport")
                || line.equals("[cef4j-runtime-server] shutdown: IPC transport stopped")
                || line.equals("[cef4j-runtime-server] shutdown: cef_shutdown complete")
                || line.equals("[cef4j-runtime-server] cef_initialize: begin")
                || line.equals("[cef4j-runtime-server] cef_initialize: complete")
                || line.equals("[cef4j-runtime-server] macOS application bootstrap: begin")
                || line.equals("[cef4j-runtime-server] macOS application bootstrap: complete")
                || line.startsWith("[cef4j-runtime-server] shared-frame paint reached ")
                || line.equals("[cef4j-runtime-server] CEF context initialized; publishing endpoint");
    }

    private static void logStderr(String line) {
        switch (stderrLevel(line)) {
            case TRACE:
                STDERR_LOG.trace("{}", line);
                break;
            case DEBUG:
                STDERR_LOG.debug("{}", line);
                break;
            case WARN:
                STDERR_LOG.warn("{}", line);
                break;
        }
    }

    enum ChildOutputLevel {
        TRACE,
        DEBUG,
        WARN
    }

    /** Registers a deliberately detached watcher that owns process-exit cleanup and bootstrap failure reporting. */
    @SuppressWarnings("FutureReturnValueIgnored")
    private static void watchForExit(
            Process process,
            String bindEndpoint,
            CompletableFuture<RuntimeServerHandshake> handshakeFuture,
            Deque<String> recentOutput) {
        process.onExit().thenAccept(exited -> {
            cleanupProcessFiles(exited.pid());
            cleanupUdsSocket(bindEndpoint);
            if (!handshakeFuture.isDone()) {
                handshakeFuture.completeExceptionally(new IOException("runtime server exited before handshake (exit="
                        + exited.exitValue() + ")" + diagnosticSuffix(recentOutput)));
            }
        });
    }

    private static void rememberOutput(Deque<String> output, String line) {
        output.addLast(line);
        while (output.size() > BOOTSTRAP_DIAGNOSTIC_LINES) output.pollFirst();
    }

    private static String diagnosticSuffix(Deque<String> output) {
        if (output.isEmpty()) return "";
        return output.stream()
                .collect(Collectors.joining(
                        System.lineSeparator(),
                        System.lineSeparator() + "recent runtime-server output:" + System.lineSeparator(),
                        ""));
    }

    private RuntimeServerProcess(Process process, RuntimeServerHandshake handshake, Deque<String> recentOutput) {
        this.process = process;
        this.handshake = handshake;
        this.transport = handshake.transport();
        this.frameTransport = handshake.frameTransport();
        this.endpoint = handshake.endpoint();
        this.recentOutput = recentOutput;
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
        return supervise(CefTransports.connect(transport, endpoint, process::isAlive));
    }

    /** Connects an authenticated WebSocket generation, optionally trusting a caller-supplied TLS context. */
    @Nonnull
    public CefTransport connectWebSocket(Optional<String> bearerToken, Optional<SSLContext> sslContext)
            throws CefTransportException {
        if (!"websocket".equals(transport)) {
            throw new CefTransportException("runtime server selected " + transport + ", not websocket");
        }
        return supervise(WebSocketTransport.connect(endpoint, bearerToken, sslContext));
    }

    private CefTransport supervise(CefTransport delegate) {
        return new ProcessSupervisedTransport(delegate, process);
    }

    public boolean isAlive() {
        return process.isAlive();
    }

    public long pid() {
        return process.pid();
    }

    @Nonnull
    public String diagnosticSummary() {
        String output = recentOutput.isEmpty() ? "none" : recentOutput.stream().collect(Collectors.joining(" | "));
        return "runtime-server{pid=" + pid() + ", alive=" + isAlive() + ", recent=" + output + "}";
    }

    /** Completes with the process exit code, whether exit was expected or a crash. */
    @Nonnull
    public CompletableFuture<Integer> onExit() {
        return process.onExit().thenApply(Process::exitValue);
    }

    /**
     * Adds deterministic child-process death notification to any selected transport. Socket-level disconnect detection
     * remains useful for remote servers, but a locally spawned server has a stronger signal available:
     * {@link Process#onExit()}. This also avoids depending on platform-specific heartbeat/monitor timing.
     */
    private static final class ProcessSupervisedTransport implements CefTransport {
        private final CefTransport delegate;
        private final Process process;
        private final CompletableFuture<Void> exitWatcher;
        private final AtomicBoolean closed = new AtomicBoolean();
        private final AtomicBoolean disconnected = new AtomicBoolean();
        private final AtomicBoolean notified = new AtomicBoolean();

        @Nullable
        private volatile Runnable disconnectHandler;

        private ProcessSupervisedTransport(CefTransport delegate, Process process) {
            this.delegate = delegate;
            this.process = process;
            delegate.onDisconnect(this::markDisconnected);
            exitWatcher = process.onExit().thenRun(this::markDisconnected);
        }

        @Override
        public void send(ByteBuffer frame) throws CefTransportException {
            if (!process.isAlive()) {
                markDisconnected();
                throw new CefTransportException("runtime server process has exited");
            }
            delegate.send(frame);
        }

        @Override
        public void onReceive(Consumer<ByteBuffer> handler) {
            delegate.onReceive(handler);
        }

        @Override
        public void onDisconnect(Runnable handler) {
            disconnectHandler = Objects.requireNonNull(handler, "handler");
            fireDisconnectIfReady();
        }

        @Override
        public boolean isConnected() {
            return !closed.get() && !disconnected.get() && process.isAlive() && delegate.isConnected();
        }

        @Override
        public boolean isRuntimeServerClient() {
            return delegate.isRuntimeServerClient();
        }

        @Override
        public void close() {
            if (closed.compareAndSet(false, true)) {
                exitWatcher.cancel(false);
                delegate.close();
            }
        }

        private void markDisconnected() {
            if (closed.get()) return;
            disconnected.set(true);
            fireDisconnectIfReady();
        }

        private void fireDisconnectIfReady() {
            Runnable handler = disconnectHandler;
            if (disconnected.get() && !closed.get() && handler != null && notified.compareAndSet(false, true)) {
                handler.run();
            }
        }
    }

    /** Immediately terminates this server generation. Primarily useful for recovery tests and operator controls. */
    public void kill() {
        terminateProcessTree(process);
    }

    @Override
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        List<ProcessHandle> descendants = descendantsOf(process);
        if (!process.isAlive()) {
            destroy(descendants, true);
            cleanupProcessFiles(process.pid());
            cleanupUdsSocket(endpoint);
            return;
        }
        boolean gracefulRequested = requestGracefulShutdown();
        try {
            long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(CLOSE_GRACE_MS);
            boolean processAlive = !process.waitFor(CLOSE_GRACE_MS, TimeUnit.MILLISECONDS);
            long remaining = Math.max(0L, deadline - System.nanoTime());
            boolean descendantsExited = awaitExit(descendants, TimeUnit.NANOSECONDS.toMillis(remaining));
            if (processAlive || !descendantsExited) {
                LOG.warn(
                        "runtime server process tree pid={} did not exit within {}ms after {} shutdown; forcing termination",
                        process.pid(),
                        CLOSE_GRACE_MS,
                        gracefulRequested ? "graceful" : "failed graceful");
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

    private boolean requestGracefulShutdown() {
        if (!handshake.hasCapability(GRACEFUL_SHUTDOWN_CAPABILITY)) {
            process.destroy();
            destroy(descendantsOf(process), false);
            return false;
        }
        try {
            process.getOutputStream().write(SHUTDOWN_COMMAND);
            process.getOutputStream().flush();
            return true;
        } catch (IOException e) {
            LOG.debug("cannot request graceful shutdown from runtime server pid={}: {}", process.pid(), e.toString());
            return false;
        }
    }

    private static void terminateProcessTree(Process process) {
        List<ProcessHandle> descendants = descendantsOf(process);
        destroy(descendants, true);
        process.destroyForcibly();
        try {
            process.waitFor(CLOSE_GRACE_MS, TimeUnit.MILLISECONDS);
            if (!awaitExit(descendants, CLOSE_GRACE_MS)) {
                LOG.warn("runtime server pid={} still has live descendants after kill", process.pid());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
