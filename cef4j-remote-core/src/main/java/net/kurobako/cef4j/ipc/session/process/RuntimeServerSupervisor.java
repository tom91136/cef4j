package net.kurobako.cef4j.ipc.session.process;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.middleware.CefSessionMiddleware;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pure-Java lifecycle owner for a restartable runtime server. Each successful restart creates a new generation and a
 * new {@link CefSession}; handles and generated facade instances from an older generation remain attached to their
 * closed session and therefore fail safely instead of being replayed against unrelated native objects.
 */
@SuppressWarnings("FutureReturnValueIgnored")
public final class RuntimeServerSupervisor implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(RuntimeServerSupervisor.class);

    public enum State {
        NEW,
        STARTING,
        RUNNING,
        BACKING_OFF,
        FAILED,
        CLOSED
    }

    @FunctionalInterface
    public interface ConnectionListener {
        void onConnection(@Nonnull Connection connection);
    }

    private final Configuration configuration;
    private final ScheduledExecutorService executor;
    private final AtomicReference<Connection> current = new AtomicReference<>();
    private final AtomicReference<State> state = new AtomicReference<>(State.NEW);
    private final AtomicLong generations = new AtomicLong();
    private final CopyOnWriteArrayList<ListenerRegistration> listeners = new CopyOnWriteArrayList<>();
    private final CompletableFuture<Connection> firstConnection = new CompletableFuture<>();
    private volatile boolean closed;
    private int consecutiveFailures;

    public RuntimeServerSupervisor(@Nonnull Configuration configuration) {
        this.configuration = Objects.requireNonNull(configuration, "configuration");
        this.executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "cef4j-runtime-supervisor");
            thread.setDaemon(true);
            return thread;
        });
    }

    /** Starts supervision once and resolves when the first server generation is connected. */
    @Nonnull
    public synchronized CompletableFuture<Connection> start() {
        if (closed) {
            CompletableFuture<Connection> failed = new CompletableFuture<>();
            failed.completeExceptionally(new IllegalStateException("supervisor is closed"));
            return failed;
        }
        if (state.compareAndSet(State.NEW, State.STARTING)) executor.execute(this::spawnGeneration);
        // Do not expose the internally completed future: callers could otherwise complete or cancel supervision state.
        return firstConnection.copy();
    }

    /** Receives every new generation, including the current one when already connected. */
    @Nonnull
    public AutoCloseable onConnection(@Nonnull ConnectionListener listener) {
        ListenerRegistration registration = new ListenerRegistration(Objects.requireNonNull(listener, "listener"));
        listeners.add(registration);
        Connection connected = current.get();
        if (connected != null) registration.deliver(connected);
        return () -> listeners.remove(registration);
    }

    @Nonnull
    public Optional<Connection> current() {
        return Optional.ofNullable(current.get());
    }

    @Nonnull
    public State state() {
        return Objects.requireNonNull(state.get());
    }

    /** Forces the current generation to exit; the normal crash path starts its replacement. */
    public void restart() {
        Connection connected = current.get();
        if (connected != null) connected.process.kill();
    }

    private void spawnGeneration() {
        if (closed) return;
        state.set(State.STARTING);
        RuntimeServerProcess spawned = null;
        CefTransport connectedTransport = null;
        CefSession connectedSession = null;
        Connection installed = null;
        try {
            spawned = RuntimeServerProcess.spawn(
                    configuration.binary,
                    configuration.transport,
                    configuration.bindEndpoint,
                    configuration.frameTransport,
                    configuration.bootstrapTimeout,
                    configuration.environment);
            connectedTransport = "websocket".equals(spawned.transport())
                    ? spawned.connectWebSocket(configuration.bearerToken, configuration.sslContext)
                    : spawned.connect();
            CefSession undecorated = new CefSessionImpl(connectedTransport, configuration.requestTimeout);
            connectedTransport = null;
            CefSession session;
            try {
                session = Objects.requireNonNull(
                        configuration.sessionMiddleware.wrap(undecorated), "session middleware returned null");
            } catch (RuntimeException failure) {
                undecorated.close();
                throw failure;
            }
            connectedSession = session;
            Connection connection = new Connection(generations.incrementAndGet(), spawned, session);
            installed = connection;
            spawned = null;
            connectedSession = null;
            if (closed) {
                connection.close();
                return;
            }
            current.set(connection);
            connection.closeRegistration = connection.session.onClose(
                    () -> executor.execute(() -> generationUnavailable(
                            connection, new IOException("runtime server control transport disconnected"))));
            state.set(State.RUNNING);
            if (closed) {
                // close() raced the install; release the connection and leave the supervisor closed.
                state.set(State.CLOSED);
                current.compareAndSet(connection, null);
                connection.close();
                return;
            }
            firstConnection.complete(connection);
            for (ListenerRegistration listener : listeners) {
                try {
                    listener.deliver(connection);
                } catch (RuntimeException failure) {
                    LOG.warn(
                            "runtime-server connection listener threw for generation={}",
                            connection.generation,
                            failure);
                }
            }
            connection
                    .process
                    .onExit()
                    .whenComplete((exitCode, failure) ->
                            executor.execute(() -> generationExited(connection, exitCode, failure)));
            scheduleStabilityReset(connection);
        } catch (IOException | RuntimeException failure) {
            if (installed != null) {
                current.compareAndSet(installed, null);
                installed.close();
            }
            if (connectedSession != null) connectedSession.close();
            if (connectedTransport != null) connectedTransport.close();
            if (spawned != null) spawned.close();
            scheduleRetry(failure);
        }
    }

    private static final class ListenerRegistration {
        private final ConnectionListener listener;
        private final AtomicLong deliveredGeneration = new AtomicLong();

        private ListenerRegistration(ConnectionListener listener) {
            this.listener = listener;
        }

        private void deliver(Connection connection) {
            long generation = connection.generation;
            while (true) {
                long delivered = deliveredGeneration.get();
                if (delivered >= generation) return;
                if (deliveredGeneration.compareAndSet(delivered, generation)) {
                    listener.onConnection(connection);
                    return;
                }
            }
        }
    }

    private void generationExited(Connection connection, @Nullable Integer exitCode, @Nullable Throwable failure) {
        Throwable cause = failure != null
                ? failure
                : new IOException("runtime server generation " + connection.generation + " exited with " + exitCode);
        generationUnavailable(connection, cause);
    }

    private void generationUnavailable(Connection connection, Throwable cause) {
        if (!current.compareAndSet(connection, null)) return;
        connection.close();
        if (!closed) scheduleRetry(cause);
    }

    private void scheduleRetry(Throwable failure) {
        if (closed) return;
        consecutiveFailures++;
        if (configuration.maxConsecutiveFailures >= 0 && consecutiveFailures > configuration.maxConsecutiveFailures) {
            state.set(State.FAILED);
            firstConnection.completeExceptionally(failure);
            LOG.error("runtime server recovery exhausted after {} failures", consecutiveFailures, failure);
            return;
        }
        long multiplier = 1L << Math.min(consecutiveFailures - 1, 10);
        long delayMillis = Math.min(
                configuration.maxRestartDelay.toMillis(),
                configuration.initialRestartDelay.multipliedBy(multiplier).toMillis());
        state.set(State.BACKING_OFF);
        LOG.warn("runtime server unavailable; retrying in {} ms: {}", delayMillis, failure.toString());
        LOG.debug("runtime server recovery failure", failure);
        executor.schedule(this::spawnGeneration, delayMillis, TimeUnit.MILLISECONDS);
    }

    /** Resets crash-loop accounting only after one generation survives the configured maximum backoff interval. */
    private void scheduleStabilityReset(Connection connection) {
        executor.schedule(
                () -> {
                    if (!closed && current.get() == connection) consecutiveFailures = 0;
                },
                configuration.maxRestartDelay.toNanos(),
                TimeUnit.NANOSECONDS);
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        state.set(State.CLOSED);
        Connection connection = current.getAndSet(null);
        if (connection != null) connection.close();
        if (!firstConnection.isDone()) firstConnection.cancel(false);
        executor.shutdownNow();
    }

    public static final class Connection implements AutoCloseable {
        private final long generation;
        private final RuntimeServerProcess process;
        private final CefSession session;
        private final java.util.concurrent.atomic.AtomicBoolean closed =
                new java.util.concurrent.atomic.AtomicBoolean();

        @Nullable
        private CefSession.HandlerRegistration closeRegistration;

        private Connection(long generation, RuntimeServerProcess process, CefSession session) {
            this.generation = generation;
            this.process = process;
            this.session = session;
        }

        public long generation() {
            return generation;
        }

        public long pid() {
            return process.pid();
        }

        @Nonnull
        public CefSession session() {
            return session;
        }

        @Nonnull
        public RuntimeServerHandshake handshake() {
            return process.handshake();
        }

        @Override
        public void close() {
            if (!closed.compareAndSet(false, true)) return;
            CefSession.HandlerRegistration registration = closeRegistration;
            if (registration != null) registration.unregister();
            session.close();
            process.close();
        }
    }

    public static final class Configuration {
        private final Path binary;
        private final String transport;
        private final String bindEndpoint;
        private final String frameTransport;
        private final Duration bootstrapTimeout;
        private final Duration requestTimeout;
        private final Duration initialRestartDelay;
        private final Duration maxRestartDelay;
        private final int maxConsecutiveFailures;
        private final Map<String, String> environment;
        private final CefSessionMiddleware sessionMiddleware;

        private final Optional<String> bearerToken;

        private final Optional<SSLContext> sslContext;

        public Configuration(
                @Nonnull Path binary,
                @Nonnull String transport,
                @Nonnull String bindEndpoint,
                @Nonnull String frameTransport,
                @Nonnull Duration bootstrapTimeout,
                @Nonnull Duration requestTimeout,
                @Nonnull Duration initialRestartDelay,
                @Nonnull Duration maxRestartDelay,
                int maxConsecutiveFailures,
                @Nonnull Map<String, String> environment) {
            this(
                    binary,
                    transport,
                    bindEndpoint,
                    frameTransport,
                    bootstrapTimeout,
                    requestTimeout,
                    initialRestartDelay,
                    maxRestartDelay,
                    maxConsecutiveFailures,
                    environment,
                    Optional.empty(),
                    Optional.empty());
        }

        public Configuration(
                @Nonnull Path binary,
                @Nonnull String transport,
                @Nonnull String bindEndpoint,
                @Nonnull String frameTransport,
                @Nonnull Duration bootstrapTimeout,
                @Nonnull Duration requestTimeout,
                @Nonnull Duration initialRestartDelay,
                @Nonnull Duration maxRestartDelay,
                int maxConsecutiveFailures,
                @Nonnull Map<String, String> environment,
                Optional<String> bearerToken,
                Optional<SSLContext> sslContext) {
            this(
                    binary,
                    transport,
                    bindEndpoint,
                    frameTransport,
                    bootstrapTimeout,
                    requestTimeout,
                    initialRestartDelay,
                    maxRestartDelay,
                    maxConsecutiveFailures,
                    environment,
                    bearerToken,
                    sslContext,
                    CefSessionMiddleware.identity());
        }

        private Configuration(
                Path binary,
                String transport,
                String bindEndpoint,
                String frameTransport,
                Duration bootstrapTimeout,
                Duration requestTimeout,
                Duration initialRestartDelay,
                Duration maxRestartDelay,
                int maxConsecutiveFailures,
                Map<String, String> environment,
                Optional<String> bearerToken,
                Optional<SSLContext> sslContext,
                CefSessionMiddleware sessionMiddleware) {
            this.binary = Objects.requireNonNull(binary, "binary");
            this.transport = Objects.requireNonNull(transport, "transport");
            this.bindEndpoint = Objects.requireNonNull(bindEndpoint, "bindEndpoint");
            this.frameTransport = Objects.requireNonNull(frameTransport, "frameTransport");
            this.bootstrapTimeout = positive(bootstrapTimeout, "bootstrapTimeout");
            this.requestTimeout = positive(requestTimeout, "requestTimeout");
            this.initialRestartDelay = positive(initialRestartDelay, "initialRestartDelay");
            this.maxRestartDelay = positive(maxRestartDelay, "maxRestartDelay");
            if (maxRestartDelay.compareTo(initialRestartDelay) < 0) {
                throw new IllegalArgumentException("maxRestartDelay must not be shorter than initialRestartDelay");
            }
            if (maxConsecutiveFailures < -1)
                throw new IllegalArgumentException("maxConsecutiveFailures must be -1 or greater");
            if (bearerToken.isPresent() && bearerToken.get().isEmpty())
                throw new IllegalArgumentException("bearerToken is empty");
            LinkedHashMap<String, String> serverEnvironment = new LinkedHashMap<>(environment);
            if (bearerToken.isPresent()) {
                String configured = serverEnvironment.put("CEF4J_WEBSOCKET_BEARER_TOKEN", bearerToken.get());
                if (configured != null && !configured.equals(bearerToken.get())) {
                    throw new IllegalArgumentException(
                            "bearerToken disagrees with CEF4J_WEBSOCKET_BEARER_TOKEN environment value");
                }
            }
            this.maxConsecutiveFailures = maxConsecutiveFailures;
            this.environment = Map.copyOf(serverEnvironment);
            this.bearerToken = bearerToken;
            this.sslContext = sslContext;
            this.sessionMiddleware = Objects.requireNonNull(sessionMiddleware, "sessionMiddleware");
        }

        /** Return an equivalent configuration that decorates every newly connected server generation. */
        @Nonnull
        public Configuration withSessionMiddleware(@Nonnull CefSessionMiddleware middleware) {
            return new Configuration(
                    binary,
                    transport,
                    bindEndpoint,
                    frameTransport,
                    bootstrapTimeout,
                    requestTimeout,
                    initialRestartDelay,
                    maxRestartDelay,
                    maxConsecutiveFailures,
                    environment,
                    bearerToken,
                    sslContext,
                    middleware);
        }

        @Nonnull
        public static Configuration defaults(@Nonnull Path binary) {
            return new Configuration(
                    binary,
                    "zmq",
                    "tcp://127.0.0.1:0",
                    "shared-file",
                    Duration.ofSeconds(10),
                    Duration.ofSeconds(30),
                    Duration.ofMillis(100),
                    Duration.ofSeconds(5),
                    -1,
                    Map.of());
        }

        private static Duration positive(Duration value, String name) {
            Objects.requireNonNull(value, name);
            if (value.isZero() || value.isNegative()) throw new IllegalArgumentException(name + " must be positive");
            return value;
        }
    }
}
