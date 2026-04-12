package net.kurobako.cef4j.osr.jfx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.gen.CefSettings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefWebViewInputTest {

    private static volatile boolean fxStarted;

    @BeforeAll
    static void setup() throws Exception {
        assumeTrue(!java.awt.GraphicsEnvironment.isHeadless(), "Requires a display (headless environment detected)");

        // On macOS with -XstartOnFirstThread, cef_initialize() must complete before
        // Platform.startup() claims the AppKit main thread, otherwise it deadlocks.
        if (net.kurobako.cef4j.OS.isMacOS()) {
            net.kurobako.cef4j.SystemBootstrap.load();
            net.kurobako.cef4j.gen.CefSettings.Mutable pre = new net.kurobako.cef4j.gen.CefSettings.Mutable();
            pre.windowlessRenderingEnabled = 1;
            pre.externalMessagePump = 1;
            pre.multiThreadedMessageLoop = 0;
            pre.noSandbox = 1;
            net.kurobako.cef4j.Cef.INSTANCE.initialise(pre, java.util.List.of("--no-sandbox"));
        }

        // Start JavaFX before CefWebView.initialise() so that loading CefWebView.class (which
        // extends Node) does not trigger Node.<clinit> before the toolkit is ready.
        CountDownLatch fxLatch = new CountDownLatch(1);
        try {
            Platform.startup(fxLatch::countDown);
        } catch (IllegalStateException alreadyRunning) {
            fxLatch.countDown();
        } catch (RuntimeException | Error e) {
            assumeTrue(false, "JavaFX not available in this environment: " + e.getMessage());
            return;
        }
        assumeTrue(fxLatch.await(10, TimeUnit.SECONDS), "Timed out starting JavaFX");
        fxStarted = true;

        CefSettings.Mutable settings = new CefSettings.Mutable();
        CefWebView.initialise(settings);
    }

    @AfterEach
    void cleanup() throws Exception {
        if (!fxStarted) return;
        onFxThread(() -> {
            for (Window w : new ArrayList<>(Window.getWindows())) {
                if (w.isShowing()) w.hide();
            }
        });
    }

    @Test
    void mouseExitEventsReachThePage() throws Exception {
        CefWebView view = createAttachedView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body style='margin:0;height:100vh'>"
                        + "<script>"
                        + "document.title = 'start';"
                        + "document.addEventListener('mousemove', function() { document.title = 'inside'; });"
                        + "document.addEventListener('mouseleave', function() { document.title = 'outside'; });"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitUntil(() -> workerState(view) == Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntil(() -> "start".equals(title(view)), 5_000))
                .as("page script installed")
                .isTrue();

        onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_MOVED, 40, 40));
        onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_MOVED, 80, 80));
        assertThat(waitUntil(() -> "inside".equals(title(view)), 5_000)).isTrue();

        onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_EXITED, 805, 80));
        assertThat(waitUntil(() -> "outside".equals(title(view)), 5_000)).isTrue();
    }

    private static CefWebView createAttachedView() throws Exception {
        CompletableFuture<CefWebView> ready = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                CefWebView view = new CefWebView();
                Stage stage = new Stage();
                stage.setScene(new Scene(new StackPane(view), 800, 600));
                stage.show();
                ready.complete(view);
            } catch (Throwable t) {
                ready.completeExceptionally(t);
            }
        });
        if (OS.isMacOS()) {
            long deadline = System.currentTimeMillis() + 10_000;
            while (!ready.isDone() && System.currentTimeMillis() < deadline) {
                Cef.INSTANCE.doMessageLoopWork();
                Thread.sleep(5);
            }
            if (!ready.isDone()) throw new java.util.concurrent.TimeoutException("Timed out waiting for view");
            return ready.get();
        }
        return ready.get(10, TimeUnit.SECONDS);
    }

    private static void fireMouse(CefWebView view, javafx.event.EventType<MouseEvent> eventType, double x, double y) {
        Point2D screenPoint = view.localToScreen(x, y);
        if (screenPoint == null) throw new IllegalStateException("View is not on screen");
        view.fireEvent(new MouseEvent(
                eventType,
                x,
                y,
                screenPoint.getX(),
                screenPoint.getY(),
                MouseButton.NONE,
                0,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                new PickResult(view, x, y)));
    }

    private static boolean waitUntil(BooleanSupplier condition, long timeoutMillis) throws Exception {
        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        while (System.nanoTime() < deadline) {
            if (condition.getAsBoolean()) return true;
            if (OS.isMacOS() && Cef.INSTANCE.getState() == Cef.State.INITIALISED) {
                Cef.INSTANCE.doMessageLoopWork();
                Thread.sleep(5);
            } else {
                Thread.sleep(20);
            }
        }
        return condition.getAsBoolean();
    }

    private static Worker.State workerState(CefWebView view) {
        try {
            return onFxThread(() -> view.getEngine().getLoadWorker().getState());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String title(CefWebView view) {
        try {
            return onFxThread(() -> view.getEngine().getTitle());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T onFxThread(java.util.concurrent.Callable<T> task) throws Exception {
        if (Platform.isFxApplicationThread()) {
            return task.call();
        }
        CountDownLatch latch = new CountDownLatch(1);
        java.util.concurrent.atomic.AtomicReference<T> result = new java.util.concurrent.atomic.AtomicReference<>();
        java.util.concurrent.atomic.AtomicReference<Throwable> error =
                new java.util.concurrent.atomic.AtomicReference<>();
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
            throw new java.util.concurrent.TimeoutException("Timed out waiting for JavaFX task");
        }
        if (error.get() != null) {
            throw new RuntimeException(error.get());
        }
        return result.get();
    }

    private static void onFxThread(Runnable task) throws Exception {
        onFxThread(() -> {
            task.run();
            return null;
        });
    }
}
