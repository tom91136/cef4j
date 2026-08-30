package net.kurobako.cef4j.osr.jfx;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
import javax.annotation.Nullable;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.test.TestDeadline;

final class CefWebViewTestSupport {
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

    static int cefApiVersion() {
        String apiVersion = System.getProperty("cef.api.version");
        if (apiVersion != null && !apiVersion.isBlank()) {
            return Integer.parseInt(apiVersion.trim());
        }
        String cefVersion = System.getProperty("cef.version");
        if (cefVersion != null && !cefVersion.isBlank()) {
            int plus = cefVersion.indexOf('+');
            String major = plus >= 0 ? cefVersion.substring(0, plus) : cefVersion;
            int dot = major.indexOf('.');
            if (dot >= 0) major = major.substring(0, dot);
            return Integer.parseInt(major);
        }
        return 146;
    }

    static void startJavaFx() throws Exception {
        CountDownLatch fxLatch = new CountDownLatch(1);
        Runnable configure = () -> {
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
        if (Platform.isFxApplicationThread()) {
            return task.call();
        }
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<T> result = new AtomicReference<>();
        AtomicReference<Throwable> error = new AtomicReference<>();
        Platform.runLater(() -> {
            try {
                result.set(task.call());
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }
        });
        if (!latch.await(10, TimeUnit.SECONDS)) {
            throw new TimeoutException("Timed out waiting for JavaFX task");
        }
        if (error.get() != null) {
            throw new RuntimeException(error.get());
        }
        return result.get();
    }

    static void onFxThread(Runnable task) throws Exception {
        onFxThread(() -> {
            task.run();
            return null;
        });
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
