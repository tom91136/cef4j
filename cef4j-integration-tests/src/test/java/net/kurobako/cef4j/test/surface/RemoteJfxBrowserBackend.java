package net.kurobako.cef4j.test.surface;

import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.remote.jfx.RemoteWebView;
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
        CountDownLatch started = new CountDownLatch(1);
        Platform.startup(() -> {
            Platform.setImplicitExit(false);
            FX_STARTED.set(true);
            started.countDown();
        });
        if (!started.await(15, TimeUnit.SECONDS)) throw new IllegalStateException("JavaFX startup timed out");
    }

    private static void onFxThread(Runnable action) throws Exception {
        if (Platform.isFxApplicationThread()) {
            action.run();
            return;
        }
        CompletableFuture<Void> done = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                action.run();
                done.complete(null);
            } catch (Throwable failure) {
                done.completeExceptionally(failure);
            }
        });
        done.get(15, TimeUnit.SECONDS);
    }

    private static final class Session implements BrowserSession {
        private final RemoteSurfaceSupport.RuntimeFixture runtime;
        private final RemoteSurfaceSupport.NavigationProbe navigation;
        private final RemoteSurfaceSupport.FrameProbe frames = new RemoteSurfaceSupport.FrameProbe();
        private final RemoteWebView view;
        private final StackPane root;
        private final Stage stage;
        private volatile int width;
        private volatile int height;

        Session(SessionConfig config) throws Exception {
            this.width = config.width();
            this.height = config.height();
            AtomicReference<RemoteWebView> viewRef = new AtomicReference<>();
            AtomicReference<StackPane> rootRef = new AtomicReference<>();
            AtomicReference<Stage> stageRef = new AtomicReference<>();
            onFxThread(() -> {
                RemoteWebView nextView = new RemoteWebView(frames::bind);
                StackPane nextRoot = new StackPane(nextView);
                nextRoot.setPrefSize(width, height);
                Stage nextStage = new Stage();
                nextStage.initStyle(StageStyle.UNDECORATED);
                nextStage.setScene(new Scene(nextRoot, width, height));
                viewRef.set(nextView);
                rootRef.set(nextRoot);
                stageRef.set(nextStage);
            });
            this.view = Objects.requireNonNull(viewRef.get(), "remote JavaFX view");
            this.root = Objects.requireNonNull(rootRef.get(), "remote JavaFX root");
            this.stage = Objects.requireNonNull(stageRef.get(), "remote JavaFX stage");
            this.runtime = RemoteSurfaceSupport.open(config.startupTimeout());
            this.navigation = new RemoteSurfaceSupport.NavigationProbe(runtime.session);
            try {
                // Attach without another UI-queue round trip: browser-created is a one-shot event.
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
        public PaintInfo awaitFirstPaint(@Nonnull Duration timeout) throws InterruptedException {
            return frames.await(width, height, timeout);
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> resizeViewport(int width, int height) {
            CompletableFuture<Void> result = new CompletableFuture<>();
            try {
                onFxThread(() -> {
                    root.setPrefSize(width, height);
                    stage.setWidth(width);
                    stage.setHeight(height);
                    this.width = width;
                    this.height = height;
                    result.complete(null);
                });
            } catch (Exception e) {
                result.completeExceptionally(e);
            }
            return result;
        }

        @Override
        public void close() {
            try {
                onFxThread(() -> {
                    view.release();
                    stage.close();
                });
            } catch (Exception ignored) {
                // Continue closing the server-side resources.
            }
            navigation.close();
            runtime.close();
        }
    }
}
