package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.onFxThread;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.startJavaFx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.TestTempDirs;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

/** In-process JavaFX implementation of the shared browser test SPI. */
public final class NativeBrowserBackend implements BrowserBackend {

    private static volatile boolean cefInitialised;

    @Override
    @Nonnull
    public String name() {
        return BrowserBackend.NATIVE_NAME;
    }

    @Override
    public boolean isAvailable() {
        return !OS.isLinux() || System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null;
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
            startJavaFx();
            ensureCefInitialised();
            return new NativeSession(config);
        } catch (Exception e) {
            throw new RuntimeException("failed to open native browser session", e);
        }
    }

    private static synchronized void ensureCefInitialised() throws IOException {
        if (cefInitialised) return;
        Path tmp = Files.createTempDirectory("cef4j-native-backend-cache");
        TestTempDirs.cleanupAtExit(tmp);
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        settings.cachePath = tmp.toAbsolutePath().toString();
        settings.rootCachePath = tmp.toAbsolutePath().toString();
        CefWebView.initialise(settings, net.kurobako.cef4j.test.CefTestLaunch.extraArgs(), Optional.empty());
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
                s.initStyle(StageStyle.UNDECORATED);
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
            return webView.getEngine().evaluateScriptAsync(script);
        }

        @Override
        @Nonnull
        public PaintInfo awaitFirstPaint(@Nonnull Duration timeout) throws InterruptedException, TimeoutException {
            TestDeadline.after(timeout)
                    .until(() -> webView.framesPainted.sum() > 0, Duration.ofMillis(50), "await native paint");
            int w = (int) stage.getScene().getWidth();
            int h = (int) stage.getScene().getHeight();
            return new PaintInfo(w, h, (long) w * h * 4);
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> resizeViewport(int width, int height) {
            CompletableFuture<Void> resized = new CompletableFuture<>();
            try {
                onFxThread(() -> {
                    stage.setWidth(width);
                    stage.setHeight(height);
                    resized.complete(null);
                });
            } catch (Exception e) {
                resized.completeExceptionally(e);
            }
            return resized;
        }

        @Override
        public void close() {
            try {
                AtomicReference<CompletableFuture<Void>> released = new AtomicReference<>();
                onFxThread(() -> {
                    if (stage.isShowing()) stage.close();
                    released.set(webView.releaseAsync());
                });
                if (released.get() != null) released.get().get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new IllegalStateException("native JavaFX browser did not close cleanly", e);
            }
        }
    }
}
