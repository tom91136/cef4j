package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeRequest;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RemoteNavigationProbe;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

public final class RemoteCefBrowserBackend implements BrowserBackend {

    @Override
    @Nonnull
    public String name() {
        return BrowserBackend.IPC_NAME;
    }

    @Override
    public boolean isAvailable() {
        RuntimeServerTestEnvironment.require();
        return true;
    }

    @Override
    @Nonnull
    public Set<Capability> capabilities() {
        return java.util.Collections.singleton(Capability.VIEWPORT_RESIZE);
    }

    @Override
    @Nonnull
    public BrowserSession openSession(@Nonnull SessionConfig config) {
        try {
            return new IpcSession(config);
        } catch (Exception e) {
            throw new RuntimeException("failed to open IPC browser session", e);
        }
    }

    private static final class IpcSession implements BrowserSession {

        private final RuntimeServerProcess server;
        private final CefTransport transport;
        private final CefSession session;
        private final RemoteHandle browserHandle;
        private final Browser browser;
        private final SharedFileFrameTransport frameTransport;
        private final RemoteNavigationProbe navigation;
        private final ArrayBlockingQueue<PaintInfo> paintQueue = new ArrayBlockingQueue<>(1);
        private final Duration navigationTimeout;

        IpcSession(SessionConfig config) throws Exception {
            RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();
            this.navigationTimeout = config.startupTimeout();
            RuntimeServerProcess nextServer = null;
            CefTransport nextTransport = null;
            CefSession nextSession = null;
            SharedFileFrameTransport nextFrameTransport = null;
            RemoteNavigationProbe nextNavigation = null;
            RemoteHandle nextBrowserHandle;
            Browser nextBrowser;
            try {
                nextServer = launchServer(environment.binary(), environment.resources(), config.startupTimeout());
                nextTransport = nextServer.connect();
                nextSession = new CefSessionImpl(nextTransport, Duration.ofSeconds(30));
                nextFrameTransport = SharedFileFrameTransport.bindAll(nextSession);
                nextFrameTransport.onFrame((w, h, pixels, meta) -> {
                    PaintInfo paint = new PaintInfo(w, h, pixels.remaining());
                    while (!paintQueue.offer(paint)) paintQueue.poll();
                });

                CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
                CefSession.HandlerRegistration registration = nextSession.onLatest(
                        LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                        LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                        event -> handleFuture.complete(event.browser()));
                try {
                    nextBrowserHandle = handleFuture.get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
                } finally {
                    registration.close();
                }
                nextBrowser = new Browser(nextSession, nextBrowserHandle);
                RemoteHandle expectedBrowser = nextBrowserHandle;
                nextNavigation = new RemoteNavigationProbe(nextSession, () -> expectedBrowser);
                nextSession
                        .request(
                                new SetViewportSizeRequest(nextBrowserHandle, config.width(), config.height()),
                                SetViewportSizeResponse.DECODER)
                        .get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
            } catch (Exception failure) {
                closeAfterFailure(nextNavigation, failure);
                closeAfterFailure(nextFrameTransport, failure);
                closeAfterFailure(nextSession, failure);
                closeAfterFailure(nextTransport, failure);
                closeAfterFailure(nextServer, failure);
                throw failure;
            }
            this.server = nextServer;
            this.transport = nextTransport;
            this.session = nextSession;
            this.frameTransport = nextFrameTransport;
            this.browserHandle = nextBrowserHandle;
            this.browser = nextBrowser;
            this.navigation = nextNavigation;

            try {
                if (!config.initialUrl().isEmpty()) {
                    loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
                }
            } catch (Exception failure) {
                try {
                    close();
                } catch (RuntimeException cleanupFailure) {
                    failure.addSuppressed(cleanupFailure);
                }
                throw failure;
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            return navigation.load(
                    url, navigationTimeout, () -> browser.getMainFrame().thenCompose(frame -> frame.loadUrl(url)));
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return browser.getMainFrame()
                    .thenCompose(frame -> session.request(
                                    new EvaluateJavascriptRequest(frame.handle(), script, false),
                                    EvaluateJavascriptResponse.DECODER)
                            .thenApply(resp -> net.kurobako.cef4j.ipc.session.JsResult.fromWire(
                                            resp.valueKind(),
                                            resp.boolValue(),
                                            resp.intValue(),
                                            resp.doubleValue(),
                                            resp.stringValue(),
                                            resp.errorMessage())
                                    .coerceToString()));
        }

        @Override
        @Nonnull
        public PaintInfo awaitFirstPaint(@Nonnull Duration timeout) throws InterruptedException, TimeoutException {
            PaintInfo p = paintQueue.poll(timeout.toMillis(), TimeUnit.MILLISECONDS);
            if (p == null) throw new TimeoutException("no paint within " + timeout);
            return p;
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> resizeViewport(int width, int height) {
            return session.request(
                            new SetViewportSizeRequest(browserHandle, width, height), SetViewportSizeResponse.DECODER)
                    .thenApply(ignored -> null);
        }

        @Override
        public void close() {
            RuntimeException failure = null;
            failure = RemoteCefBrowserBackend.close(failure, navigation);
            failure = RemoteCefBrowserBackend.close(failure, frameTransport);
            failure = RemoteCefBrowserBackend.close(failure, session);
            failure = RemoteCefBrowserBackend.close(failure, transport);
            failure = RemoteCefBrowserBackend.close(failure, server);
            if (failure != null) throw failure;
        }
    }

    private static void closeAfterFailure(@javax.annotation.Nullable AutoCloseable resource, Exception original) {
        if (resource == null) return;
        try {
            resource.close();
        } catch (Exception cleanupFailure) {
            original.addSuppressed(cleanupFailure);
        }
    }

    @javax.annotation.Nullable
    private static RuntimeException close(@javax.annotation.Nullable RuntimeException failure, AutoCloseable resource) {
        try {
            resource.close();
        } catch (Exception cleanupFailure) {
            RuntimeException next = cleanupFailure instanceof RuntimeException
                    ? (RuntimeException) cleanupFailure
                    : new IllegalStateException("resource cleanup failed", cleanupFailure);
            if (failure == null) return next;
            failure.addSuppressed(next);
        }
        return failure;
    }

    public static RuntimeServerProcess launchServer(
            @Nonnull Path serverBinary, @Nonnull Path cefResources, @Nonnull Duration timeout) throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary, "zmq", "tcp://127.0.0.1:0", "shared-file", timeout, runtimeEnvironment(cefResources));
    }

    public static Map<String, String> runtimeEnvironment(@Nonnull Path cefRuntime) {
        Map<String, String> environment = new LinkedHashMap<>();
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        if (os.contains("win")) {
            Path release = releaseDirectory(cefRuntime);
            environment.put("CEF_RESOURCES_DIR", resourcesDirectory(cefRuntime).toString());
            environment.put("PATH", release + java.io.File.pathSeparator + System.getenv("PATH"));
        } else if (os.contains("mac")) {
            environment.put("CEF_FRAMEWORK_DIR", frameworkDirectory(cefRuntime).toString());
        } else {
            Path release = releaseDirectory(cefRuntime);
            environment.put("CEF_RESOURCES_DIR", resourcesDirectory(cefRuntime).toString());
            String inherited = System.getenv("LD_LIBRARY_PATH");
            environment.put(
                    "LD_LIBRARY_PATH",
                    release + (inherited == null || inherited.isEmpty() ? "" : java.io.File.pathSeparator + inherited));
        }
        return environment;
    }

    static Path releaseDirectory(Path runtimeDirectory) {
        Path release = runtimeDirectory.resolve("Release");
        return Files.isDirectory(release) ? release : runtimeDirectory;
    }

    static Path resourcesDirectory(Path runtimeDirectory) {
        Path resources = runtimeDirectory.resolve("Resources");
        return Files.isDirectory(resources) ? resources : runtimeDirectory;
    }

    static Path frameworkDirectory(Path runtimeDirectory) {
        Path external = runtimeDirectory.resolve("Release").resolve("Chromium Embedded Framework.framework");
        if (Files.isDirectory(external)) return external;
        Path direct = runtimeDirectory.resolve("Chromium Embedded Framework.framework");
        if (Files.isDirectory(direct)) return direct;
        Path bundled = runtimeDirectory
                .resolve("cef4j-runtime-server.app")
                .resolve("Contents")
                .resolve("Frameworks")
                .resolve("Chromium Embedded Framework.framework");
        if (Files.isDirectory(bundled)) return bundled;
        return runtimeDirectory.resolve("Frameworks").resolve("Chromium Embedded Framework.framework");
    }
}
