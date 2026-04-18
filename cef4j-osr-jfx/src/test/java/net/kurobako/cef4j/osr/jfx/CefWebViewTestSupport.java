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
import net.kurobako.cef4j.OS;

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
        try {
            onFxThread(() -> {
                for (Window window : new ArrayList<>(Window.getWindows())) {
                    if (window.isShowing()) window.hide();
                }
                return null;
            });
        } catch (IllegalStateException e) {
            // Toolkit not initialized — @BeforeAll was skipped, nothing to clean up
        }
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
