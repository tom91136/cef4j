package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.HelperProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

/**
 * IPC-backed {@link BrowserBackend} for the cross-backend test SPI. Spawns a fresh helper subprocess per session,
 * connects via ZMQ, opens the auto-bootstrapped browser, and exposes the codegen-generated facade methods
 * ({@link Browser#getMainFrame}, {@link Frame#loadUrl}, {@link EvaluateJavascriptRequest}) behind the small
 * {@link BrowserSession} surface.
 *
 * <p>Discovered by {@code BrowserBackend.discover()} via the corresponding ServiceLoader file. Lives in test scope so
 * it doesn't bleed into production classpaths.
 *
 * <p>Availability: requires the helper binary path and CEF resources path passed as system properties (same vars the
 * {@code MmapFrameTransportIntegrationTest} uses); on systems without a built helper or {@code /dev/shm} the backend
 * reports unavailable and parameterised tests skip the {@code ipc} row gracefully.
 */
public final class IpcBrowserBackend implements BrowserBackend {

    @Override
    @Nonnull
    public String name() {
        return BrowserBackend.IPC_NAME;
    }

    @Override
    public boolean isAvailable() {
        String bin = System.getProperty("cef4j.ipc.helper.binary");
        String res = System.getProperty("cef4j.ipc.cef.resources");
        if (bin == null || res == null) return false;
        return Files.isExecutable(Paths.get(bin))
                && Files.isDirectory(Paths.get(res))
                && Files.isDirectory(Paths.get("/dev/shm"));
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
     * The session glues together: helper process + ZMQ transport + CEF session + codegen Browser/Frame facades +
     * MmapFrameTransport. Each test gets its own helper, so we don't need to worry about cross-test leakage.
     */
    private static final class IpcSession implements BrowserSession {

        private final HelperProcess helper;
        private final ZmqTransport transport;
        private final CefSession session;
        private final RemoteHandle browserHandle;
        private final Browser browser;
        private final MmapFrameTransport frameTransport;
        private final AtomicReference<PaintInfo> latestPaint = new AtomicReference<>();
        private final LinkedBlockingQueue<PaintInfo> paintQueue = new LinkedBlockingQueue<>();

        IpcSession(SessionConfig config) throws Exception {
            String bin = System.getProperty("cef4j.ipc.helper.binary");
            String res = System.getProperty("cef4j.ipc.cef.resources");
            this.helper = launchHelper(Paths.get(bin), Paths.get(res), config.startupTimeout());
            this.transport = ZmqTransport.connect(helper.endpoint());
            this.session = new CefSessionImpl(transport, Duration.ofSeconds(15));

            // Bind the frame transport BEFORE the helper hands us the browser so we never miss the first paint.
            this.frameTransport = MmapFrameTransport.bindAll(session);
            frameTransport.onFrame((w, h, pixels, meta) -> {
                PaintInfo p = new PaintInfo(w, h, pixels.remaining());
                latestPaint.set(p);
                paintQueue.offer(p);
            });

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.on(LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                if (!handleFuture.isDone()) handleFuture.complete(e.browser());
            });
            this.browserHandle = handleFuture.get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
            this.browser = new Browser(session, browserHandle);

            // Optional initial navigation; tests that want a deterministic page can pass a non-empty URL.
            if (!config.initialUrl().isEmpty()) {
                loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            return browser.getMainFrame().thenCompose(frame -> frame.loadUrl(url));
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return browser.getMainFrame().thenCompose(frame -> session.request(
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
        public void close() {
            try {
                frameTransport.close();
            } catch (RuntimeException ignored) {
            }
            try {
                session.close();
            } catch (Exception ignored) {
            }
            try {
                transport.close();
            } catch (RuntimeException ignored) {
            }
            try {
                helper.close();
            } catch (RuntimeException ignored) {
            }
        }
    }

    /**
     * Spawn a helper subprocess with the env vars CEF needs (CEF_RESOURCES_DIR for the GPU/locale paks, LD_LIBRARY_PATH
     * for libcef.so) plumbed in via a tiny shell wrapper. Shared between {@link IpcBrowserBackend} and the in-module
     * integration tests so the launch shape stays consistent — diverging here would mean "tests pass under one launcher
     * but the SPI uses another", which has burned us before.
     */
    static HelperProcess launchHelper(@Nonnull Path helperBinary, @Nonnull Path cefResources, @Nonnull Duration timeout)
            throws IOException {
        Path tmpDir = Files.createTempDirectory("cef4j-helper-launcher");
        Path script = tmpDir.resolve("helper-launch.sh");
        String content = "#!/bin/sh\n"
                + "export CEF_RESOURCES_DIR=\"" + cefResources + "\"\n"
                + "export LD_LIBRARY_PATH=\"" + cefResources + ":${LD_LIBRARY_PATH:-}\"\n"
                + "exec \"" + helperBinary + "\" \"$@\"\n";
        Files.writeString(script, content);
        script.toFile().setExecutable(true);
        return HelperProcess.spawn(script, "tcp://127.0.0.1:0", timeout);
    }
}
