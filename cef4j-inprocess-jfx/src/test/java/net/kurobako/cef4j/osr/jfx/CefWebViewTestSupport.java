package net.kurobako.cef4j.osr.jfx;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.UncaughtExceptionTracker;
import org.jspecify.annotations.Nullable;

final class CefWebViewTestSupport {
    private static final UncaughtExceptionTracker JAVA_FX_FAILURES = new UncaughtExceptionTracker();
    private static final Duration FX_TASK_TIMEOUT = Duration.ofSeconds(10);
    // Matches the shared browser contract; cold CEF startup on hosted macOS Intel runners can exceed 20 seconds.
    static final Duration CEF_STARTUP_TIMEOUT = Duration.ofSeconds(40);

    private CefWebViewTestSupport() {}

    static void assumeDisplayServer() {
        if (OS.isMacOS()) {
            assumeTrue(!java.awt.GraphicsEnvironment.isHeadless(), "Requires a display server");
        } else {
            assumeTrue(
                    System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null,
                    "Requires a display server; run under xvfb-run");
        }
    }

    static void startJavaFx() throws Exception {
        CountDownLatch fxLatch = new CountDownLatch(1);
        Runnable configure = () -> {
            Thread.currentThread().setUncaughtExceptionHandler(JAVA_FX_FAILURES);
            Platform.setImplicitExit(false);
            fxLatch.countDown();
        };
        try {
            Platform.startup(configure);
        } catch (IllegalStateException alreadyRunning) {
            Platform.runLater(configure);
        }
        if (!fxLatch.await(10, TimeUnit.SECONDS)) {
            throw new TimeoutException("Timed out starting JavaFX");
        }
    }

    static void closeAllWindows() throws Exception {
        Thread applicationThread = javaFxApplicationThread();
        if (applicationThread == null || !applicationThread.isAlive()) return;
        try {
            List<CompletableFuture<Void>> releases = onFxThread(() -> {
                List<CompletableFuture<Void>> pending = new ArrayList<>();
                for (Window window : new ArrayList<>(Window.getWindows())) {
                    if (window.getScene() != null) {
                        collectCefViews(window.getScene().getRoot(), pending);
                    }
                    if (window.isShowing()) window.hide();
                }
                return pending;
            });
            if (releases != null) {
                for (CompletableFuture<Void> release : releases) {
                    release.get(10, TimeUnit.SECONDS);
                }
            }
        } catch (IllegalStateException ignored) {
            return;
        }
    }

    static void drainJavaFx() throws Exception {
        // XXX: Browser close can enqueue JavaFX pulse work after its future completes; remove this marker when release
        // completion is specified to run after all related JavaFX work and the teardown race test remains stable.
        Thread applicationThread = javaFxApplicationThread();
        if (applicationThread == null || !applicationThread.isAlive()) return;
        onFxThread(() -> {});
    }

    static void shutdownJavaFx() throws Exception {
        Platform.exit();
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10);
        Thread applicationThread;
        while ((applicationThread = javaFxApplicationThread()) != null
                && applicationThread.isAlive()
                && System.nanoTime() < deadline) {
            applicationThread.join(100);
        }
        applicationThread = javaFxApplicationThread();
        if (applicationThread != null && applicationThread.isAlive()) {
            throw new IllegalStateException("JavaFX application thread did not stop after Platform.exit()");
        }
        JAVA_FX_FAILURES.throwIfPresent();
    }

    static void shutdownCefWebView() throws Exception {
        Thread applicationThread = javaFxApplicationThread();
        if (applicationThread == null || !applicationThread.isAlive()) return;
        closeAllWindows();
        drainJavaFx();
        onFxThread(CefWebView::terminate);
        shutdownJavaFx();
    }

    @Nullable
    private static Thread javaFxApplicationThread() {
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if ("JavaFX Application Thread".equals(thread.getName())) return thread;
        }
        return null;
    }

    private static void collectCefViews(javafx.scene.Node node, List<CompletableFuture<Void>> releases) {
        if (node instanceof CefWebView) {
            releases.add(((CefWebView) node).releaseAsync());
        }
        if (node instanceof javafx.scene.Parent) {
            for (javafx.scene.Node child : ((javafx.scene.Parent) node).getChildrenUnmodifiable()) {
                collectCefViews(child, releases);
            }
        }
    }

    @Nullable
    static <T> T onFxThread(Callable<T> task) throws Exception {
        return onFxThread(task, FX_TASK_TIMEOUT);
    }

    @Nullable
    static <T> T onFxThread(Callable<T> task, Duration timeout) throws Exception {
        if (Platform.isFxApplicationThread()) {
            return task.call();
        }
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<@Nullable T> result = new AtomicReference<>();
        AtomicReference<@Nullable Throwable> error = new AtomicReference<>();
        Platform.runLater(() -> {
            try {
                result.set(task.call());
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }
        });
        if (!latch.await(timeout.toMillis(), TimeUnit.MILLISECONDS)) {
            throw new TimeoutException("Timed out waiting for JavaFX task");
        }
        if (error.get() != null) {
            throw new RuntimeException(error.get());
        }
        JAVA_FX_FAILURES.throwIfPresent();
        return result.get();
    }

    static void onFxThread(Runnable task) throws Exception {
        onFxThread(task, FX_TASK_TIMEOUT);
    }

    static void onFxThread(Runnable task, Duration timeout) throws Exception {
        onFxThread(
                () -> {
                    task.run();
                    return null;
                },
                timeout);
    }

    @Nullable
    static <T> T onFxThreadUnchecked(Callable<T> task) {
        try {
            return onFxThread(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static boolean waitUntil(BooleanSupplier condition, long timeoutMillis) throws Exception {
        try {
            TestDeadline.after(java.time.Duration.ofMillis(timeoutMillis))
                    .until(condition, java.time.Duration.ofMillis(20), "test condition");
            return true;
        } catch (TimeoutException timedOut) {
            return condition.getAsBoolean();
        }
    }

    static boolean waitForRenderedColor(CefWebView view, double x, double y, Color expected, long timeoutMillis)
            throws Exception {
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setViewport(new Rectangle2D(x, y, 1, 1));
        return waitUntil(
                () -> expected.equals(onFxThreadUnchecked(
                        () -> view.snapshot(parameters, null).getPixelReader().getColor(0, 0))),
                timeoutMillis);
    }
}
