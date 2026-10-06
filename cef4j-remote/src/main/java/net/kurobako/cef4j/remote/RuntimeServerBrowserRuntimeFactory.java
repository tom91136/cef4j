package net.kurobako.cef4j.remote;

import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.middleware.CefSessionMiddleware;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;

/** Spawns a packaged CEF runtime server by provider name, without binding to transport implementations. */
public final class RuntimeServerBrowserRuntimeFactory implements RemoteBrowserRuntimeFactory {
    private static final AtomicInteger STARTUP_THREAD_IDS = new AtomicInteger();
    private static final Executor STARTUP_THREADS =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), task -> {
                Thread thread = new Thread(task, "cef4j-runtime-start-" + STARTUP_THREAD_IDS.incrementAndGet());
                thread.setDaemon(true);
                return thread;
            });

    private final Path binary;
    private final String controlTransport;
    private final String endpoint;
    private final String frameTransport;
    private final Duration timeout;
    private final Map<String, String> environment;
    private final CefSessionMiddleware sessionMiddleware;
    private final Executor startupExecutor;

    public RuntimeServerBrowserRuntimeFactory(
            @Nonnull Path binary,
            @Nonnull String controlTransport,
            @Nonnull String endpoint,
            @Nonnull String frameTransport,
            @Nonnull Duration timeout,
            @Nonnull Map<String, String> environment) {
        this(
                binary,
                controlTransport,
                endpoint,
                frameTransport,
                timeout,
                environment,
                CefSessionMiddleware.identity(),
                STARTUP_THREADS);
    }

    public RuntimeServerBrowserRuntimeFactory(
            @Nonnull Path binary,
            @Nonnull String controlTransport,
            @Nonnull String endpoint,
            @Nonnull String frameTransport,
            @Nonnull Duration timeout,
            @Nonnull Map<String, String> environment,
            @Nonnull CefSessionMiddleware sessionMiddleware) {
        this(
                binary,
                controlTransport,
                endpoint,
                frameTransport,
                timeout,
                environment,
                sessionMiddleware,
                STARTUP_THREADS);
    }

    public RuntimeServerBrowserRuntimeFactory(
            @Nonnull Path binary,
            @Nonnull String controlTransport,
            @Nonnull String endpoint,
            @Nonnull String frameTransport,
            @Nonnull Duration timeout,
            @Nonnull Map<String, String> environment,
            @Nonnull CefSessionMiddleware sessionMiddleware,
            @Nonnull Executor startupExecutor) {
        this.binary = Objects.requireNonNull(binary, "binary");
        this.controlTransport = Objects.requireNonNull(controlTransport, "controlTransport");
        this.endpoint = Objects.requireNonNull(endpoint, "endpoint");
        this.frameTransport = Objects.requireNonNull(frameTransport, "frameTransport");
        this.timeout = Objects.requireNonNull(timeout, "timeout");
        this.environment = new HashMap<>(Objects.requireNonNull(environment, "environment"));
        this.sessionMiddleware = Objects.requireNonNull(sessionMiddleware, "sessionMiddleware");
        this.startupExecutor = Objects.requireNonNull(startupExecutor, "startupExecutor");
    }

    @Override
    @Nonnull
    public CompletableFuture<? extends RemoteBrowserRuntime> create() {
        CompletableFuture<OwnedRuntime> result = new CompletableFuture<>();
        try {
            startupExecutor.execute(() -> {
                OwnedRuntime runtime;
                try {
                    runtime = createWithRetry();
                } catch (RuntimeException failure) {
                    result.completeExceptionally(failure);
                    return;
                }
                if (!result.complete(runtime)) runtime.close();
            });
        } catch (RuntimeException failure) {
            result.completeExceptionally(failure);
        }
        return result;
    }

    private OwnedRuntime createWithRetry() {
        Exception firstFailure = null;
        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                return createOnce();
            } catch (Exception failure) {
                if (firstFailure == null) firstFailure = failure;
                else failure.addSuppressed(firstFailure);
                if (attempt == 1) {
                    throw new IllegalStateException("failed to start cef4j runtime server", failure);
                }
            }
        }
        throw new AssertionError("unreachable");
    }

    private OwnedRuntime createOnce() throws Exception {
        RuntimeServerProcess server = null;
        CefSession session = null;
        try {
            server = RuntimeServerProcess.spawn(
                    binary, controlTransport, endpoint, frameTransport, timeout, environment);
            int serverApi = server.handshake().cefApiVersion();
            if (!RemoteApiCompatibility.supports(serverApi)) {
                throw new IllegalStateException("Remote CEF API mismatch: client="
                        + RemoteApiCompatibility.cefApiVersion() + ", server=" + serverApi);
            }
            CefSession undecorated = new CefSessionImpl(server.connect(), timeout);
            try {
                session = sessionMiddleware.wrap(undecorated);
            } catch (RuntimeException failure) {
                undecorated.close();
                throw failure;
            }
            CompletableFuture<RemoteHandle> browser = new CompletableFuture<>();
            CefSession.HandlerRegistration registration = session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    event -> browser.complete(event.browser()));
            // CefSessionImpl's runtime-server handshake creates the bootstrap browser only after the receive
            // handler is installed. onLatest also replays the event if creation wins this registration race.
            // Sending CreateBrowserRequest here would create an unowned second browser for every runtime.
            RemoteHandle handle = browser.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            registration.close();
            return new OwnedRuntime(session, handle, server);
        } catch (Exception failure) {
            if (session != null) session.close();
            if (server != null) server.close();
            throw failure;
        }
    }

    private static final class OwnedRuntime implements RemoteBrowserRuntime {
        private final CefSession session;
        private final RemoteHandle browser;
        private final RuntimeServerProcess server;

        private OwnedRuntime(CefSession session, RemoteHandle browser, RuntimeServerProcess server) {
            this.session = session;
            this.browser = browser;
            this.server = server;
        }

        @Override
        public CefSession session() {
            return session;
        }

        @Override
        public RemoteHandle browser() {
            return browser;
        }

        @Override
        public void close() {
            session.close();
            server.close();
        }
    }
}
