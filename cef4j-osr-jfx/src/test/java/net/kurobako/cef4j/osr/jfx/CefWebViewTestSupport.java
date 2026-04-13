package net.kurobako.cef4j.osr.jfx;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;
import javafx.application.Platform;
import javafx.stage.Window;

final class CefWebViewTestSupport {
    private CefWebViewTestSupport() {}

    static void assumeDisplayServer() {
        assumeTrue(
                System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null,
                "Requires a display server; run under xvfb-run");
    }

    static void startJavaFx() throws Exception {
        CountDownLatch fxLatch = new CountDownLatch(1);
        try {
            Platform.startup(fxLatch::countDown);
        } catch (IllegalStateException alreadyRunning) {
            fxLatch.countDown();
        }
        if (!fxLatch.await(10, TimeUnit.SECONDS)) {
            throw new TimeoutException("Timed out starting JavaFX");
        }
    }

    static void closeAllWindows() throws Exception {
        onFxThread(() -> {
            for (Window window : new ArrayList<>(Window.getWindows())) {
                if (window.isShowing()) window.hide();
            }
            return null;
        });
    }

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

    static boolean waitUntil(BooleanSupplier condition, long timeoutMillis) throws Exception {
        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        while (System.nanoTime() < deadline) {
            if (condition.getAsBoolean()) return true;
            Thread.sleep(20);
        }
        return condition.getAsBoolean();
    }
}
