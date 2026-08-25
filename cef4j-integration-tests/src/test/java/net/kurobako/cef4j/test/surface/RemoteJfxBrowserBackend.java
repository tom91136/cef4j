package net.kurobako.cef4j.test.surface;

import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.remote.jfx.RemoteWebView;
import net.kurobako.cef4j.test.RemoteNavigationProbe;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

/** Shared-contract adapter that drives the real RemoteWebView JavaFX component. */
final class RemoteJfxBrowserBackend implements BrowserBackend {
    private static final AtomicBoolean FX_STARTED = new AtomicBoolean();

    @Override
    @Nonnull
    public String name() {
        return "remote-jfx";
    }

    @Override
    public boolean isAvailable() {
        return RemoteSurfaceSupport.available();
    }

    @Override
    @Nonnull
    public Set<Capability> capabilities() {
        return Collections.singleton(Capability.VIEWPORT_RESIZE);
    }

    @Override
    @Nonnull
    public BrowserSession openSession(@Nonnull SessionConfig config) {
        try {
            startJavaFx();
            return new Session(config);
        } catch (Exception e) {
            throw new RuntimeException("failed to open RemoteWebView contract session", e);
        }
    }

    private static synchronized void startJavaFx() throws Exception {
        if (FX_STARTED.get()) return;
        CompletableFuture<Void> started = new CompletableFuture<>();
        Runnable configure = () -> {
            try {
                Platform.setImplicitExit(false);
                FX_STARTED.set(true);
                started.complete(null);
            } catch (Throwable failure) {
                started.completeExceptionally(failure);
            }
        };
        try {
            Platform.startup(configure);
        } catch (IllegalStateException alreadyStarted) {
            Platform.runLater(configure);
        }
        TestDeadline.after(Duration.ofSeconds(15)).await(started, "start JavaFX");
    }

    private static void onFxThread(Runnable action) throws Exception {
        TestDeadline.after(Duration.ofSeconds(15))
                .runOn(Platform.isFxApplicationThread(), Platform::runLater, action, "run JavaFX test action");
    }

    private static final class Session implements BrowserSession {
        private final RemoteSurfaceSupport.RuntimeFixture runtime;
        private final RemoteNavigationProbe navigation;
        private final RemoteSurfaceSupport.FrameProbe frames = new RemoteSurfaceSupport.FrameProbe();
        private final RemoteWebView view;
        private final Stage stage;
        private volatile int width;
        private volatile int height;

        Session(SessionConfig config) throws Exception {
            this.width = config.width();
            this.height = config.height();
            AtomicReference<RemoteWebView> viewRef = new AtomicReference<>();
            AtomicReference<Stage> stageRef = new AtomicReference<>();
            onFxThread(() -> {
                RemoteWebView nextView = new RemoteWebView((session, browser) -> frames.bind(session));
                // XXX: Remove the pre-attach resize when JavaFX no longer defers the first macOS stage pulse past
                // bootstrap paint for the minimum supported CEF release.
                nextView.setManaged(false);
                nextView.resize(width, height);
                StackPane nextRoot = new StackPane(nextView);
                nextRoot.setPrefSize(width, height);
                Stage nextStage = new Stage();
                nextStage.initStyle(StageStyle.UNDECORATED);
                nextStage.setScene(new Scene(nextRoot, width, height));
                viewRef.set(nextView);
                stageRef.set(nextStage);
            });
            this.view = Objects.requireNonNull(viewRef.get(), "remote JavaFX view");
            this.stage = Objects.requireNonNull(stageRef.get(), "remote JavaFX stage");
            this.runtime = RemoteSurfaceSupport.open(config.startupTimeout());
            this.navigation = new RemoteNavigationProbe(runtime.session);
            try {
                view.attach(runtime.session);
                onFxThread(() -> {
                    stage.show();
                });
                view.awaitBrowserHandle(config.startupTimeout());
                if (!config.initialUrl().isEmpty()) {
                    loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
                }
            } catch (Exception e) {
                navigation.close();
                runtime.close();
                throw e;
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            return navigation.load(url, () -> view.loadUrl(url));
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return view.evaluateJavascript(script);
        }

        @Override
        @Nonnull
        public PaintInfo awaitFirstPaint(@Nonnull Duration timeout)
                throws InterruptedException, java.util.concurrent.TimeoutException {
            return frames.await(width, height, timeout);
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> resizeViewport(int width, int height) {
            try {
                onFxThread(() -> {
                    view.resize(width, height);
                    this.width = width;
                    this.height = height;
                });
                awaitViewSize(view, width, height);
            } catch (Exception e) {
                return CompletableFuture.failedFuture(e);
            }
            return view.resizeViewport(width, height);
        }

        @Override
        public void close() {
            try {
                onFxThread(() -> {
                    view.release();
                    stage.close();
                });
            } catch (Exception ignored) {
                // Cleanup continues with independently owned resources.
            }
            navigation.close();
            runtime.close();
        }
    }

    private static void awaitViewSize(RemoteWebView view, int width, int height) throws Exception {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(15);
        double[] actual = new double[2];
        while (System.nanoTime() < deadline) {
            onFxThread(() -> {
                actual[0] = view.getWidth();
                actual[1] = view.getHeight();
            });
            if ((int) actual[0] == width && (int) actual[1] == height) return;
            Thread.sleep(10);
        }
        throw new TimeoutException(
                "JavaFX view did not resize to " + width + "x" + height + "; last was " + actual[0] + "x" + actual[1]);
    }
}
