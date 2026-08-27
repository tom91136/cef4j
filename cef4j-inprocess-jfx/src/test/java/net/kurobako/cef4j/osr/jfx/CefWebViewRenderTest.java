package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefScreenInfo;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.test.CefTestLaunch;
import net.kurobako.cef4j.test.DisplayLock;
import net.kurobako.cef4j.test.TestExecutor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

@Timeout(30)
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
@ExtendWith(DisplayLock.class)
class CefWebViewRenderTest {

    @Order(1)
    @Test
    void constructorThrowsClearErrorWhenCefNotInitialised() throws Exception {
        assumeDisplayServer();
        startJavaFx();
        assertThatThrownBy(() -> onFxThread(() -> new CefWebView()))
                .hasCauseInstanceOf(IllegalStateException.class)
                .hasMessageContaining("CEF must be initialised for off-screen rendering");
    }

    @Order(2)
    @Test
    void webViewPaintsFramesAfterPageLoad(@TempDir Path tempDir) throws Exception {
        assumeDisplayServer();
        startJavaFx();
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.rootCachePath = cacheDir.toAbsolutePath().toString();
        CefWebView.initialise(settings, CefTestLaunch.extraArgs(), Optional.empty());
        try {
            CefWebView view = Objects.requireNonNull(
                    onFxThread(() -> {
                        CefWebView v = new CefWebView();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(new StackPane(v), 400, 300));
                        stage.show();
                        return v;
                    }),
                    "view");
            try {
                CefRenderHandler renderHandler = onFxThread(view::createRenderHandler);
                try (TestExecutor executor = TestExecutor.single("jfx-render-handler-probe")) {
                    CompletableFuture.runAsync(
                                    () -> {
                                        CefRect.Mutable root = new CefRect.Mutable();
                                        CefRect.Mutable viewport = new CefRect.Mutable();
                                        CefScreenInfo.Mutable screen = new CefScreenInfo.Mutable();
                                        int[] x = {0};
                                        int[] y = {0};
                                        Objects.requireNonNull(renderHandler).getRootScreenRect(null, root);
                                        renderHandler.getViewRect(null, viewport);
                                        renderHandler.getScreenInfo(null, screen);
                                        renderHandler.getScreenPoint(null, 1, 1, x, y);
                                    },
                                    executor)
                            .get(2, TimeUnit.SECONDS);
                }
                onFxThread(() -> view.getEngine()
                        .loadContent(
                                "<html><body style='margin:0;height:100vh;background:#ff0000'>hello</body></html>"));

                assertThat(waitUntil(
                                () -> onFxThreadUnchecked(() ->
                                                view.getEngine().getLoadWorker().getState())
                                        == Worker.State.SUCCEEDED,
                                10_000))
                        .as("page load should succeed")
                        .isTrue();

                assertThat(waitUntil(() -> view.framesPainted.sum() > 0, 10_000))
                        .as("CefWebView should have received at least one paint (framesPainted > 0)")
                        .isTrue();

                assertThat(onFxThread(
                                () -> view.getChildrenUnmodifiable().stream().noneMatch(javafx.scene.Node::isManaged)))
                        .as("paint surfaces must not contribute their last frame size to viewport layout")
                        .isTrue();
            } finally {
                CompletableFuture<Void> released = onFxThread(view::releaseAsync);
                assertThat(onFxThread(view::releaseAsync)).isSameAs(released);
                if (released != null) released.get(10, TimeUnit.SECONDS);
            }
        } finally {
            closeAllWindows();
            drainJavaFx();
            CefWebView.terminate();
            shutdownJavaFx();
        }
    }

    @BeforeAll
    static void startupSanity() {}

    @AfterAll
    static void shutdownSanity() throws Exception {
        closeAllWindows();
        drainJavaFx();
        if (Cef.INSTANCE.state() == Cef.State.INITIALISED) {
            Cef.INSTANCE.terminate();
        }
        shutdownJavaFx();
    }
}
