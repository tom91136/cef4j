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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.TestExecutor;
import net.kurobako.cef4j.test.TestTempDirs;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

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
        private final Duration navigationTimeout;
        private final NativePaintProbe paints = new NativePaintProbe();
        private final TestExecutor navigationWaiter = TestExecutor.single("cef4j-native-jfx-navigation");
        private final AtomicReference<PendingNavigation> pendingNavigation = new AtomicReference<>();
        private volatile int width;
        private volatile int height;

        NativeSession(SessionConfig config) throws Exception {
            int w = config.width();
            int h = config.height();
            Object[] holder = new Object[2];
            onFxThread(() -> {
                CefWebView v = null;
                Stage s = null;
                try {
                    v = new CefWebView() {
                        @Override
                        protected void onViewPainted(int width, int height) {
                            paints.accept(width, height);
                        }
                    };
                    s = new Stage();
                    s.initStyle(StageStyle.UNDECORATED);
                    s.setScene(new Scene(new StackPane(v), w, h));
                    s.show();
                    holder[0] = v;
                    holder[1] = s;
                } catch (RuntimeException | Error failure) {
                    if (s != null) s.close();
                    if (v != null) v.release();
                    throw failure;
                }
            });
            this.webView = (CefWebView) holder[0];
            this.stage = (Stage) holder[1];
            this.navigationTimeout = config.startupTimeout();
            this.width = w;
            this.height = h;
            try {
                if (!config.initialUrl().isEmpty()) {
                    loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
                }
            } catch (Exception failure) {
                rollback();
                throw failure;
            }
        }

        @Override
        @Nonnull
        @SuppressWarnings("FutureReturnValueIgnored")
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            PendingNavigation next = new PendingNavigation();
            if (!pendingNavigation.compareAndSet(null, next)) {
                return CompletableFuture.failedFuture(new IllegalStateException("a navigation is already pending"));
            }
            try {
                onFxThread(() -> {
                    Worker<Void> worker = webView.getEngine().getLoadWorker();
                    next.worker = worker;
                    next.paintBaseline = webView.framesPainted.sum();
                    ChangeListener<Worker.State> listener = new ChangeListener<Worker.State>() {
                        @Override
                        public void changed(
                                ObservableValue<? extends Worker.State> observable,
                                Worker.State previous,
                                Worker.State current) {
                            if (current == Worker.State.SUCCEEDED) {
                                worker.stateProperty().removeListener(this);
                                awaitNavigationReady(next);
                            } else if (current == Worker.State.FAILED || current == Worker.State.CANCELLED) {
                                worker.stateProperty().removeListener(this);
                                next.result.completeExceptionally(
                                        new RuntimeException("load reached terminal state " + current + " for " + url));
                            }
                        }
                    };
                    next.listener = listener;
                    worker.stateProperty().addListener(listener);
                    try {
                        webView.load(url);
                    } catch (RuntimeException failure) {
                        worker.stateProperty().removeListener(listener);
                        throw failure;
                    }
                });
            } catch (Exception e) {
                next.result.completeExceptionally(e);
            }
            next.result.whenComplete((ignored, failure) -> {
                pendingNavigation.compareAndSet(next, null);
                removeListener(next);
            });
            return next.result;
        }

        @SuppressWarnings("FutureReturnValueIgnored")
        private void awaitNavigationReady(PendingNavigation navigation) {
            CompletableFuture.runAsync(
                    () -> {
                        try {
                            TestDeadline.after(navigationTimeout)
                                    .until(
                                            () -> navigation.result.isCancelled()
                                                    || isNavigationReady(navigation.paintBaseline),
                                            Duration.ofMillis(50),
                                            "await native navigation readiness");
                            if (!navigation.result.isCancelled()) navigation.result.complete(null);
                        } catch (Throwable failure) {
                            navigation.result.completeExceptionally(failure);
                        }
                    },
                    navigationWaiter);
        }

        private boolean isNavigationReady(long paintBaseline) {
            CefBrowser browser = webView.getBrowser();
            return browser != null
                    && browser.isValid()
                    && webView.framesPainted.sum() > paintBaseline
                    && browser.getMainFrame()
                            .filter(frame -> frame.isValid() && frame.getUrl().isPresent())
                            .isPresent();
        }

        private static void removeListener(PendingNavigation navigation) {
            Worker<Void> worker = navigation.worker;
            ChangeListener<Worker.State> listener = navigation.listener;
            if (worker == null || listener == null) return;
            Runnable remove = () -> worker.stateProperty().removeListener(listener);
            if (javafx.application.Platform.isFxApplicationThread()) remove.run();
            else {
                try {
                    javafx.application.Platform.runLater(remove);
                } catch (RuntimeException ignored) {
                    // XXX: JavaFX is already stopping, so the property cannot dispatch this listener again.
                }
            }
        }

        private void rollback() {
            try {
                onFxThread(() -> {
                    if (stage.isShowing()) stage.close();
                    webView.release();
                });
            } catch (Exception ignored) {
                // XXX: Preserve the construction failure; these resources belong to the terminating test process.
            } finally {
                navigationWaiter.close();
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return webView.getEngine().evaluateScriptAsync(script);
        }

        @Override
        @Nonnull
        public PaintInfo awaitNextPaint(@Nonnull Duration timeout) throws InterruptedException, TimeoutException {
            return paints.await(width, height, timeout);
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> resizeViewport(int width, int height) {
            CompletableFuture<Void> resized = new CompletableFuture<>();
            try {
                onFxThread(() -> {
                    stage.setWidth(width);
                    stage.setHeight(height);
                    this.width = width;
                    this.height = height;
                    resized.complete(null);
                });
            } catch (Exception e) {
                resized.completeExceptionally(e);
            }
            return resized;
        }

        @Override
        public void close() {
            PendingNavigation navigation = pendingNavigation.getAndSet(null);
            if (navigation != null) navigation.result.cancel(true);
            try {
                AtomicReference<CompletableFuture<Void>> released = new AtomicReference<>();
                onFxThread(() -> {
                    if (stage.isShowing()) stage.close();
                    released.set(webView.releaseAsync());
                });
                if (released.get() != null) released.get().get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new IllegalStateException("native JavaFX browser did not close cleanly", e);
            } finally {
                navigationWaiter.close();
            }
        }

        private static final class PendingNavigation {
            private final CompletableFuture<Void> result = new CompletableFuture<>();
            private volatile long paintBaseline;
            private volatile @javax.annotation.Nullable Worker<Void> worker;
            private volatile @javax.annotation.Nullable ChangeListener<Worker.State> listener;
        }
    }
}
