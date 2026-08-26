package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import javafx.concurrent.Worker;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.annotation.Nullable;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.test.DisplayLock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

@Timeout(30)
@ExtendWith(DisplayLock.class)
class CefWebViewV117PlusInputTest {
    private static final Color PAGE_BACKGROUND = Color.web("#123456");

    @BeforeAll
    static void setup(@TempDir Path tempDir) throws Exception {
        assumeDisplayServer();
        Cef.LaunchArgs launch = Cef.osrLaunchArgs();
        launch.settings().noSandbox = 1;
        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));
        launch.settings().cachePath = cacheDir.toAbsolutePath().toString();
        launch.settings().rootCachePath = cacheDir.toAbsolutePath().toString();
        java.util.List<String> args = new java.util.ArrayList<>(launch.args());
        args.addAll(net.kurobako.cef4j.test.CefTestLaunch.extraArgs());
        startJavaFx();
        Cef.INSTANCE.initialise(launch.settings(), args);
    }

    @AfterEach
    void cleanup() throws Exception {
        closeAllWindows();
    }

    @AfterAll
    static void shutdownCef() throws Exception {
        closeAllWindows();
        drainJavaFx();
        Cef.INSTANCE.terminate();
        shutdownJavaFx();
    }

    @Test
    void mouseExitEventsReachThePage() throws Exception {
        CefWebView view = createAttachedView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body style='margin:0;height:100vh;background:#123456'>"
                        + "<script>"
                        + "document.addEventListener('mousemove', function() { document.title = 'inside'; });"
                        + "document.addEventListener('mouseleave', function() { document.title = 'outside'; });"
                        + "document.title = 'start';"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitUntil(() -> workerState(view) == Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntil(() -> "start".equals(title(view)), 3_000)).isTrue();
        assertThat(waitForRenderedColor(view, 80, 80, PAGE_BACKGROUND, 10_000))
                .as("the page surface should be rendered before mouse input")
                .isTrue();

        onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_ENTERED, 80, 80));
        onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_MOVED, 80, 80));
        assertThat(waitUntil(() -> "inside".equals(title(view)), 3_000)).isTrue();

        onFxThread(() -> fireMouse(view, MouseEvent.MOUSE_EXITED, 805, 80));
        assertThat(waitUntil(() -> "outside".equals(title(view)), 3_000)).isTrue();
    }

    private static CefWebView createAttachedView() throws Exception {
        return java.util.Objects.requireNonNull(
                onFxThread(() -> {
                    CefWebView view = new CefWebView();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(new StackPane(view), 800, 600));
                    stage.show();
                    return view;
                }),
                "view");
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

    @Nullable
    private static Worker.State workerState(CefWebView view) {
        return onFxThreadUnchecked(() -> view.getEngine().getLoadWorker().getState());
    }

    @Nullable
    private static String title(CefWebView view) {
        return onFxThreadUnchecked(() -> view.getEngine().getTitle());
    }
}
