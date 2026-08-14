package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.gen.CefSettings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

/**
 * Smoke test for the JavaFX OSR render path.
 *
 * <p>Guards against the "blank screen" regression introduced by switching macOS to {@code externalMessagePump=1}
 * without a pump driver: without a daemon message loop (or an external pump), CEF never loads pages and the
 * {@code CefWebView} stays empty. This test loads an inline page and asserts that at least one {@code OnPaint} callback
 * fires — which can only happen if the CEF message loop is actually running.
 *
 * <p>Also asserts the constructor throws a clear error when CEF has not been initialised, so a user who forgets the
 * explicit {@link CefWebView#initialise(CefSettings.Mutable, List, net.kurobako.cef4j.gen.CefApp)} call gets a
 * descriptive exception instead of a silent blank view.
 *
 * <p>JavaFX starts before CEF so macOS establishes Glass/AppKit before CEF enters the shared application event loop.
 */
@Timeout(30)
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
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
        settings.cachePath = Files.createDirectories(tempDir.resolve("cef-cache"))
                .toAbsolutePath()
                .toString();
        CefWebView.initialise(settings, List.of(), null);
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
            } finally {
                CompletableFuture<Void> released = onFxThread(view::releaseAsync);
                if (released != null) released.get(10, TimeUnit.SECONDS);
            }
        } finally {
            closeAllWindows();
            drainJavaFx();
            if (net.kurobako.cef4j.test.CefTestLifecycle.explicitShutdownSafe()) CefWebView.terminate();
            shutdownJavaFx();
        }
    }

    @BeforeAll
    static void startupSanity() {
        // No-op: each test is self-contained w.r.t. CEF state.
    }

    @AfterAll
    static void shutdownSanity() throws Exception {
        // Ensure CEF is terminated even if the second test fails mid-flight.
        closeAllWindows();
        drainJavaFx();
        if (net.kurobako.cef4j.test.CefTestLifecycle.explicitShutdownSafe()
                && Cef.INSTANCE.state() == Cef.State.INITIALISED) {
            Cef.INSTANCE.terminate();
        }
        shutdownJavaFx();
    }
}
