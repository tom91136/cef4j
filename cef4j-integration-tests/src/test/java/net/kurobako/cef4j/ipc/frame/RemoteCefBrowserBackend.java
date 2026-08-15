package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeRequest;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeResponse;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

/**
 * IPC-backed {@link BrowserBackend} for the cross-backend test SPI. Spawns a fresh server subprocess per session,
 * connects via ZMQ, opens the auto-bootstrapped browser, and exposes the codegen-generated facade methods
 * ({@link Browser#getMainFrame}, {@link Frame#loadUrl}, {@link EvaluateJavascriptRequest}) behind the small
 * {@link BrowserSession} surface.
 *
 * <p>Discovered by {@code BrowserBackend.discover()} via the corresponding ServiceLoader file. Lives in test scope so
 * it doesn't bleed into production classpaths.
 *
 * <p>Availability: requires the server binary path and CEF resources path passed as system properties (same vars the
 * {@code MmapFrameTransportIntegrationTest} uses); on systems without a built server the backend reports unavailable
 * and parameterised tests skip the {@code ipc} row gracefully.
 */
public final class RemoteCefBrowserBackend implements BrowserBackend {

    @Override
    @Nonnull
    public String name() {
        return BrowserBackend.IPC_NAME;
    }

    @Override
    public boolean isAvailable() {
        String bin = System.getProperty("cef4j.runtime.server.binary");
        String res = System.getProperty("cef4j.runtime.server.resources");
        if (bin == null || res == null) return false;
        return Files.isExecutable(Paths.get(bin)) && Files.isDirectory(Paths.get(res));
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

    /**
     * The session glues together: server process + ZMQ transport + CEF session + codegen Browser/Frame facades +
     * SharedFileFrameTransport. Each test gets its own server, so we don't need to worry about cross-test leakage.
     */
    private static final class IpcSession implements BrowserSession {

        private final RuntimeServerProcess server;
        private final ZmqTransport transport;
        private final CefSession session;
        private final RemoteHandle browserHandle;
        private final Browser browser;
        private final SharedFileFrameTransport frameTransport;
        private final LinkedBlockingQueue<V8ContextCreatedEvent> contextQueue = new LinkedBlockingQueue<>();
        private final AtomicReference<PaintInfo> latestPaint = new AtomicReference<>();
        private final LinkedBlockingQueue<PaintInfo> paintQueue = new LinkedBlockingQueue<>();

        IpcSession(SessionConfig config) throws Exception {
            String bin = System.getProperty("cef4j.runtime.server.binary");
            String res = System.getProperty("cef4j.runtime.server.resources");
            this.server = launchServer(Paths.get(bin), Paths.get(res), config.startupTimeout());
            this.transport = ZmqTransport.connect(server.endpoint());
            this.session = new CefSessionImpl(transport, Duration.ofSeconds(30));

            // Bind the frame transport BEFORE the server hands us the browser so we never miss the first paint.
            this.frameTransport = SharedFileFrameTransport.bindAll(session);
            session.on(V8ContextCreatedEvent.MESSAGE_ID, V8ContextCreatedEvent.DECODER, contextQueue::offer);
            frameTransport.onFrame((w, h, pixels, meta) -> {
                PaintInfo p = new PaintInfo(w, h, pixels.remaining());
                latestPaint.set(p);
                paintQueue.offer(p);
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

            // Optional initial navigation; tests that want a deterministic page can pass a non-empty URL.
            if (!config.initialUrl().isEmpty()) {
                loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            // Frame.loadUrl acknowledges that navigation was queued. BrowserSession's
            // stronger contract requires the new document to be usable, so wait for
            // the renderer's matching V8 context before completing.
            contextQueue.clear();
            return browser.getMainFrame()
                    .thenCompose(frame -> frame.loadUrl(url))
                    .thenCompose(ignored -> CompletableFuture.runAsync(() -> awaitContext(url)));
        }

        private void awaitContext(String url) {
            long deadline = System.nanoTime() + Duration.ofSeconds(20).toNanos();
            try {
                while (System.nanoTime() < deadline) {
                    long remaining = Math.max(1L, deadline - System.nanoTime());
                    V8ContextCreatedEvent event = contextQueue.poll(remaining, TimeUnit.NANOSECONDS);
                    if (event == null) break;
                    if (url.equals(event.frameUrl())) return;
                }
                throw new java.util.concurrent.CompletionException(
                        new TimeoutException("no V8 context for navigation to " + url));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new java.util.concurrent.CompletionException(e);
            }
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
        public PaintInfo awaitFirstPaint(@Nonnull Duration timeout) throws InterruptedException {
            PaintInfo p = paintQueue.poll(timeout.toMillis(), TimeUnit.MILLISECONDS);
            if (p == null) {
                PaintInfo fallback = latestPaint.get();
                if (fallback != null) return fallback;
                throw new InterruptedException("no paint within " + timeout);
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
            try {
                frameTransport.close();
            } catch (RuntimeException ignored) {
                // Continue closing the remaining independently owned test resources.
            }
            try {
                session.close();
            } catch (Exception ignored) {
                // Continue closing the remaining independently owned test resources.
            }
            try {
                transport.close();
            } catch (RuntimeException ignored) {
                // Continue closing the remaining independently owned test resources.
            }
            try {
                server.close();
            } catch (RuntimeException ignored) {
                // Test teardown is best effort after all other resources have been released.
            }
        }
    }

    /** Launches the packaged runtime server directly with only platform-specific native search paths on its process. */
    public static RuntimeServerProcess launchServer(
            @Nonnull Path serverBinary, @Nonnull Path cefResources, @Nonnull Duration timeout) throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary, "zmq", "tcp://127.0.0.1:0", "shared-file", timeout, runtimeEnvironment(cefResources));
    }

    public static Map<String, String> runtimeEnvironment(@Nonnull Path cefResources) {
        Map<String, String> environment = new LinkedHashMap<>();
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        if (os.contains("win")) {
            environment.put("CEF_RESOURCES_DIR", cefResources.toString());
            environment.put("PATH", cefResources + java.io.File.pathSeparator + System.getenv("PATH"));
        } else if (os.contains("mac")) {
            environment.put(
                    "CEF_FRAMEWORK_DIR", frameworkDirectory(cefResources).toString());
        } else {
            environment.put("CEF_RESOURCES_DIR", cefResources.toString());
            String inherited = System.getenv("LD_LIBRARY_PATH");
            environment.put(
                    "LD_LIBRARY_PATH",
                    cefResources
                            + (inherited == null || inherited.isEmpty() ? "" : java.io.File.pathSeparator + inherited));
        }
        return environment;
    }

    static Path frameworkDirectory(Path runtimeDirectory) {
        Path bundled = runtimeDirectory
                .resolve("cef4j-runtime-server.app")
                .resolve("Contents")
                .resolve("Frameworks")
                .resolve("Chromium Embedded Framework.framework");
        if (Files.isDirectory(bundled)) return bundled;
        return runtimeDirectory.resolve("Frameworks").resolve("Chromium Embedded Framework.framework");
    }
}
