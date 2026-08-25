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
import java.util.concurrent.atomic.AtomicReference;
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
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.RemoteNavigationProbe;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

/** Packaged-runtime implementation of the shared browser test SPI. */
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
        private final ZmqTransport transport;
        private final CefSession session;
        private final RemoteHandle browserHandle;
        private final Browser browser;
        private final SharedFileFrameTransport frameTransport;
        private final RemoteNavigationProbe navigation;
        private final AtomicReference<PaintInfo> latestPaint = new AtomicReference<>();
        private final ArrayBlockingQueue<PaintInfo> paintQueue = new ArrayBlockingQueue<>(1);

        IpcSession(SessionConfig config) throws Exception {
            RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();
            this.server = launchServer(environment.binary(), environment.resources(), config.startupTimeout());
            this.transport = ZmqTransport.connect(server.endpoint());
            this.session = new CefSessionImpl(transport, Duration.ofSeconds(30));
            this.navigation = new RemoteNavigationProbe(session);

            this.frameTransport = SharedFileFrameTransport.bindAll(session);
            frameTransport.onFrame((w, h, pixels, meta) -> {
                PaintInfo p = new PaintInfo(w, h, pixels.remaining());
                latestPaint.set(p);
                while (!paintQueue.offer(p)) paintQueue.poll();
            });

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });
            this.browserHandle = handleFuture.get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
            this.browser = new Browser(session, browserHandle);

            session.request(
                            new SetViewportSizeRequest(browserHandle, config.width(), config.height()),
                            SetViewportSizeResponse.DECODER)
                    .get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);

            if (!config.initialUrl().isEmpty()) {
                loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            return navigation.load(url, () -> browser.getMainFrame().thenCompose(frame -> frame.loadUrl(url)));
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return browser.getMainFrame()
                    .thenCompose(frame -> session.request(
                                    new EvaluateJavascriptRequest(frame.handle(), script, /*retainHandle=*/ false),
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
            if (p == null) {
                PaintInfo fallback = latestPaint.get();
                if (fallback != null) return fallback;
                throw new TimeoutException("no paint within " + timeout);
            }
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
            navigation.close();
            try {
                frameTransport.close();
            } catch (RuntimeException ignored) {
                // Cleanup continues with independently owned resources.
            }
            try {
                session.close();
            } catch (Exception ignored) {
                // Cleanup continues with independently owned resources.
            }
            try {
                transport.close();
            } catch (RuntimeException ignored) {
                // Cleanup continues with independently owned resources.
            }
            try {
                server.close();
            } catch (RuntimeException ignored) {
                // Cleanup is best effort in the disposable test process.
            }
        }
    }

    /** Launches the packaged runtime server directly with only platform-specific native search paths on its process. */
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
