package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import javafx.concurrent.Worker;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.cef4j.Cef;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(30)
class CefWebViewV117PlusInputTest {

    @BeforeAll
    static void setup(@TempDir Path tempDir) throws Exception {
        assumeDisplayServer();
        Cef.LaunchArgs launch = Cef.osrLaunchArgs();
        launch.settings().cachePath = Files.createDirectories(tempDir.resolve("cef-cache"))
                .toAbsolutePath()
                .toString();
        Cef.INSTANCE.initialise(launch.settings(), launch.args());
        startJavaFx();
    }

    @AfterEach
    void cleanup() throws Exception {
        closeAllWindows();
    }

    @AfterAll
    static void shutdownCef() {
        Cef.INSTANCE.terminate();
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

        onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_ENTERED, 80, 80));
        assertThat(waitUntilFiring(
                        () -> "inside".equals(title(view)),
                        3_000,
                        () -> onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_MOVED, 80, 80))))
                .isTrue();

        assertThat(waitUntilFiring(
                        () -> "outside".equals(title(view)),
                        3_000,
                        () -> onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_EXITED, 805, 80))))
                .isTrue();
    }

    private static CefWebView createAttachedView() throws Exception {
        return onFxThread(() -> {
            CefWebView view = new CefWebView();
            Stage stage = new Stage();
            stage.setScene(new Scene(new StackPane(view), 800, 600));
            stage.show();
            return view;
        });
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

    @FunctionalInterface
    interface ThrowingRunnable {
        void run() throws Exception;
    }

    private static boolean waitUntilFiring(BooleanSupplier condition, long timeoutMillis, ThrowingRunnable action)
            throws Exception {
        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        while (System.nanoTime() < deadline) {
            action.run();
            long pollEnd = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(100);
            while (System.nanoTime() < pollEnd) {
                if (condition.getAsBoolean()) return true;
                Thread.sleep(10);
            }
        }
        return condition.getAsBoolean();
    }

    private static Worker.State workerState(CefWebView view) {
        return onFxThreadUnchecked(() -> view.getEngine().getLoadWorker().getState());
    }

    private static String title(CefWebView view) {
        return onFxThreadUnchecked(() -> view.getEngine().getTitle());
    }

    private static <T> T onFxThreadUnchecked(java.util.concurrent.Callable<T> task) {
        try {
            return onFxThread(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
