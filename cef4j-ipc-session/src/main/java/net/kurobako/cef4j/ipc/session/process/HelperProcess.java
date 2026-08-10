package net.kurobako.cef4j.ipc.session.process;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spawns and supervises the cef4j IPC helper executable. Bootstrap protocol:
 *
 * <ol>
 *   <li>Process is started with {@code <binary> --bind <bindEndpoint>}. {@code bindEndpoint} is the ZMQ endpoint the
 *       helper should bind its PAIR socket to; passing {@code tcp://127.0.0.1:0} requests an OS-assigned port.
 *   <li>Helper binds, then writes a single line {@code ENDPOINT=<resolved-endpoint>\n} to stdout.
 *   <li>{@link #spawn} blocks reading that line, then returns. The helper continues running; subsequent stdout/stderr
 *       lines are drained to slf4j ({@code helper-stdout} / {@code helper-stderr} loggers).
 *   <li>{@link #close} sends SIGTERM, waits up to 5s, then SIGKILL.
 * </ol>
 *
 * <p>Crash detection during normal operation surfaces through the transport: if the helper dies, the JVM-side
 * {@link net.kurobako.cef4j.ipc.transport.CefTransport}'s {@code onDisconnect} fires.
 */
public final class HelperProcess implements Closeable {

    private static final Logger LOG = LoggerFactory.getLogger(HelperProcess.class);
    private static final Logger STDOUT_LOG = LoggerFactory.getLogger("helper-stdout");
    private static final Logger STDERR_LOG = LoggerFactory.getLogger("helper-stderr");

    private static final Pattern ENDPOINT_LINE = Pattern.compile("^ENDPOINT=(.+)$");
    private static final Duration DEFAULT_BOOTSTRAP_TIMEOUT = Duration.ofSeconds(10);
    private static final long CLOSE_GRACE_MS = 5000;

    private final Process process;
    private final String endpoint;

    public static HelperProcess spawn(@Nonnull Path binary, @Nonnull String bindEndpoint) throws IOException {
        return spawn(binary, bindEndpoint, DEFAULT_BOOTSTRAP_TIMEOUT);
    }

    public static HelperProcess spawn(
            @Nonnull Path binary, @Nonnull String bindEndpoint, @Nonnull Duration bootstrapTimeout) throws IOException {
        Objects.requireNonNull(binary, "binary");
        Objects.requireNonNull(bindEndpoint, "bindEndpoint");

        ProcessBuilder pb = new ProcessBuilder(binary.toString(), "--bind", bindEndpoint);
        pb.redirectErrorStream(false);
        Process p = pb.start();

        CompletableFuture<String> endpointFuture = new CompletableFuture<>();
        startReader(p.getInputStream(), "helper-stdout-" + p.pid(), line -> {
            if (!endpointFuture.isDone()) {
                Matcher m = ENDPOINT_LINE.matcher(line);
                if (m.matches()) {
                    endpointFuture.complete(m.group(1));
                    return;
                }
            }
            STDOUT_LOG.info("{}", line);
        });
        startReader(p.getErrorStream(), "helper-stderr-" + p.pid(), line -> STDERR_LOG.info("{}", line));

        // Concurrently, watch for early process exit so spawn doesn't block forever if the helper dies during boot.
        // The CompletableFuture is intentionally not retained; we want fire-and-forget completion.
        @SuppressWarnings("FutureReturnValueIgnored")
        java.util.concurrent.CompletableFuture<Void> unused = p.onExit().thenAccept(exited -> {
            cleanupPaintShm(exited.pid());
            if (!endpointFuture.isDone()) {
                endpointFuture.completeExceptionally(
                        new IOException("helper exited before writing endpoint (exit=" + exited.exitValue() + ")"));
            }
        });

        try {
            String endpoint = endpointFuture.get(bootstrapTimeout.toMillis(), TimeUnit.MILLISECONDS);
            return new HelperProcess(p, endpoint);
        } catch (TimeoutException e) {
            p.destroyForcibly();
            cleanupPaintShm(p.pid());
            throw new IOException("helper did not write ENDPOINT= within " + bootstrapTimeout, e);
        } catch (InterruptedException e) {
            p.destroyForcibly();
            cleanupPaintShm(p.pid());
            Thread.currentThread().interrupt();
            throw new IOException("interrupted while awaiting helper bootstrap", e);
        } catch (ExecutionException e) {
            p.destroyForcibly();
            cleanupPaintShm(p.pid());
            Throwable cause = e.getCause();
            throw (cause instanceof IOException) ? (IOException) cause : new IOException(cause);
        }
    }

    private HelperProcess(Process process, String endpoint) {
        this.process = process;
        this.endpoint = endpoint;
    }

    @Nonnull
    public String endpoint() {
        return endpoint;
    }

    public boolean isAlive() {
        return process.isAlive();
    }

    public long pid() {
        return process.pid();
    }

    @Override
    public void close() {
        if (!process.isAlive()) {
            cleanupPaintShm(process.pid());
            return;
        }
        process.destroy();
        try {
            if (!process.waitFor(CLOSE_GRACE_MS, TimeUnit.MILLISECONDS)) {
                LOG.warn("helper pid={} did not exit within {}ms; sending SIGKILL", process.pid(), CLOSE_GRACE_MS);
                process.destroyForcibly();
                process.waitFor(CLOSE_GRACE_MS, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            process.destroyForcibly();
        } finally {
            cleanupPaintShm(process.pid());
        }
    }

    /**
     * SIGTERM/SIGKILL bypasses the helper's C++ destructors, so named POSIX shm objects would otherwise accumulate in
     * /dev/shm. Names include the helper PID; deleting only that exact prefix after process exit cannot affect another
     * running helper.
     */
    private static void cleanupPaintShm(long pid) {
        Path shmRoot = Paths.get("/dev/shm");
        if (!Files.isDirectory(shmRoot)) return;
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(shmRoot, "cef4j-paint-" + pid + "-*")) {
            for (Path entry : entries) Files.deleteIfExists(entry);
        } catch (IOException e) {
            LOG.debug("failed to clean paint shm for helper pid={}: {}", pid, e.toString());
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
                                LOG.warn("helper output handler threw on '{}'", line, e);
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
