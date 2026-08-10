package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.onFxThread;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.startJavaFx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

/**
 * In-process {@link BrowserBackend} that wraps the existing {@link CefWebView}. Lives in the same package as
 * {@code CefWebView} so it can reach {@code framesPainted} (package-private paint counter) without exposing it
 * publicly.
 *
 * <p>Each session opens a new {@link Stage} containing a fresh {@code CefWebView}. CEF itself is a process-wide
 * singleton — the first call to {@link CefWebView#initialise} establishes it; subsequent sessions reuse the existing
 * init. JFX runtime is started lazily by {@link CefWebViewTestSupport#startJavaFx()}.
 *
 * <p>Availability: skips on macOS where {@code Platform.startup()} is incompatible with JUnit driving (see
 * {@code CefWebViewRenderTest} class javadoc). Also requires a display server (DISPLAY env or xvfb) on Linux.
 */
public final class NativeBrowserBackend implements BrowserBackend {

    /**
     * Track CEF init across sessions in this JVM. {@link CefWebView#initialise} is idempotent but we still want to skip
     * the SettingsBuilder churn on subsequent sessions.
     */
    private static volatile boolean cefInitialised;

    @Override
    @Nonnull
    public String name() {
        return BrowserBackend.NATIVE_NAME;
    }

    @Override
    public boolean isAvailable() {
        if (OS.isMacOS()) return false; // Platform.startup() is incompatible with JUnit on macOS
        // Linux/Windows: rely on a running display server. xvfb-run wraps headless CI runs.
        return System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null;
    }

    @Override
    @Nonnull
    public BrowserSession openSession(@Nonnull SessionConfig config) {
        try {
            ensureCefInitialised();
            startJavaFx();
            return new NativeSession(config);
        } catch (Exception e) {
            throw new RuntimeException("failed to open native browser session", e);
        }
    }

    private static synchronized void ensureCefInitialised() throws IOException {
        if (cefInitialised) return;
        Path tmp = Files.createTempDirectory("cef4j-native-backend-cache");
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = tmp.toAbsolutePath().toString();
        CefWebView.initialise(settings, List.of(), null);
        cefInitialised = true;
    }

    private static final class NativeSession implements BrowserSession {

        private final CefWebView webView;
        private final Stage stage;

        NativeSession(SessionConfig config) throws Exception {
            int w = config.width();
            int h = config.height();
            Object[] holder = new Object[2];
            onFxThread(() -> {
                CefWebView v = new CefWebView();
                Stage s = new Stage();
                s.setScene(new Scene(new StackPane(v), w, h));
                s.show();
                holder[0] = v;
                holder[1] = s;
            });
            this.webView = (CefWebView) holder[0];
            this.stage = (Stage) holder[1];
            if (!config.initialUrl().isEmpty()) {
                loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            CompletableFuture<Void> done = new CompletableFuture<>();
            try {
                onFxThread(() -> {
                    Worker<Void> worker = webView.getEngine().getLoadWorker();
                    worker.stateProperty().addListener((obs, old, ns) -> {
                        if (ns == Worker.State.SUCCEEDED) done.complete(null);
                        else if (ns == Worker.State.FAILED || ns == Worker.State.CANCELLED) {
                            done.completeExceptionally(
                                    new RuntimeException("load reached terminal state " + ns + " for " + url));
                        }
                    });
                    webView.load(url);
                });
            } catch (Exception e) {
                done.completeExceptionally(e);
            }
            return done;
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            // CefWebEngine.evaluateScriptAsync returns CompletableFuture<String> — JSON-stringified value.
            // Same coercion expectations as the IPC backend.
            return webView.getEngine().evaluateScriptAsync(script);
        }

        @Override
        @Nonnull
        public PaintInfo awaitFirstPaint(@Nonnull Duration timeout) throws InterruptedException {
            // CefWebView increments framesPainted (package-private) on each non-popup paint. Poll.
            long deadline = System.nanoTime() + timeout.toNanos();
            while (webView.framesPainted.sum() == 0) {
                if (System.nanoTime() > deadline) {
                    throw new InterruptedException("no native paint within " + timeout);
                }
                Thread.sleep(50);
            }
            // Stage scene dimensions are what we asked for at construction; CefWebView sizes its frame buffer
            // to match so byteCount = w*h*4 BGRA matches the IPC backend's reporting.
            int w = (int) stage.getScene().getWidth();
            int h = (int) stage.getScene().getHeight();
            return new PaintInfo(w, h, (long) w * h * 4);
        }

        @Override
        public void close() {
            try {
                onFxThread(() -> {
                    if (stage.isShowing()) stage.close();
                    webView.release();
                });
            } catch (Exception ignored) {
            }
        }
    }
}
