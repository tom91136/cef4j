package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javafx.concurrent.Worker;
import javafx.css.CssMetaData;
import javafx.scene.Parent;
import javafx.scene.text.FontSmoothingType;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.test.CefTestLaunch;
import net.kurobako.cef4j.test.DisplayLock;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

@Timeout(60)
@ExtendWith(DisplayLock.class)
class CefWebViewDropInTest {
    @TempDir
    static Path tempDir;

    @Test
    void viewMirrorsWebViewLayoutAndPropertyContract() throws Exception {
        initialiseCef();
        CefWebView view = Objects.requireNonNull(onFxThread(() -> new CefWebView()), "view");
        try {
            assertThat(CefWebView.class).isFinal().hasSuperclass(Parent.class);
            onFxThread(() -> {
                assertThat(view.isResizable()).isTrue();
                assertThat(view.prefWidth(-1)).isEqualTo(800);
                assertThat(view.prefHeight(-1)).isEqualTo(600);
                assertThat(view.minWidth(-1)).isZero();
                assertThat(view.maxWidth(-1)).isEqualTo(Double.MAX_VALUE);
                assertThat(view.getFontScale()).isEqualTo(1.0);
                assertThat(view.getFontSmoothingType()).isEqualTo(FontSmoothingType.LCD);
                assertThat(view.isContextMenuEnabled()).isTrue();
                assertThat(view.getStyleClass()).contains("web-view");

                view.setPrefSize(320, 240);
                view.setMinSize(-5, Double.NaN);
                assertThat(view.prefWidth(-1)).isEqualTo(320);
                assertThat(view.prefHeight(-1)).isEqualTo(240);
                assertThat(view.minWidth(-1)).isZero();
                assertThat(view.minHeight(-1)).isZero();

                view.resize(123, 45);
                assertThat(view.getWidth()).isEqualTo(123);
                assertThat(view.getHeight()).isEqualTo(45);
                assertThat(view.widthProperty().get()).isEqualTo(123);

                view.setContextMenuEnabled(false);
                assertThat(view.contextMenuAllowed).isFalse();
                view.setFontSmoothingType(FontSmoothingType.GRAY);
                assertThat(view.fontSmoothingTypeProperty().get()).isEqualTo(FontSmoothingType.GRAY);

                List<String> css = CefWebView.getClassCssMetaData().stream()
                        .map(CssMetaData::getProperty)
                        .collect(Collectors.toList());
                assertThat(css)
                        .contains(
                                "-fx-context-menu-enabled",
                                "-fx-font-smoothing-type",
                                "-fx-zoom",
                                "-fx-font-scale",
                                "-fx-min-width",
                                "-fx-pref-width",
                                "-fx-max-width",
                                "-fx-min-height",
                                "-fx-pref-height",
                                "-fx-max-height");
                assertThat(view.getCssMetaData()).isSameAs(CefWebView.getClassCssMetaData());
            });
        } finally {
            Objects.requireNonNull(onFxThread(view::releaseAsync)).get(10, TimeUnit.SECONDS);
        }
    }

    @Test
    void headlessEngineQueuesLoadsAndAppliesFontScale() throws Exception {
        initialiseCef();
        CefWebEngine engine = Objects.requireNonNull(
                onFxThread(() -> new CefWebEngine("data:text/html,<title>one</title>")), "engine");
        try {
            assertThat(waitUntil(() -> state(engine) == Worker.State.SUCCEEDED, 15_000))
                    .as("constructor url loads without a window")
                    .isTrue();
            onFxThread(() -> engine.loadContent("<html><body>two</body></html>"));
            assertThat(waitUntil(
                            () -> evaluate(engine, "document.body.textContent").equals("two"), 15_000))
                    .as("loadContent replaces the constructor page")
                    .isTrue();

            assertThat(evaluate(engine, "window.innerWidth")).isEqualTo("800");
            onFxThread(() -> engine.getView().setFontScale(2.0));
            assertThat(waitUntil(() -> evaluate(engine, "window.innerWidth").equals("400"), 15_000))
                    .as("fontScale maps onto the CEF zoom level")
                    .isTrue();
        } finally {
            Objects.requireNonNull(onFxThread(() -> engine.getView().releaseAsync()))
                    .get(10, TimeUnit.SECONDS);
        }
    }

    private static Worker.State state(CefWebEngine engine) {
        return Objects.requireNonNull(
                onFxThreadUnchecked(() -> engine.getLoadWorker().getState()));
    }

    private static String evaluate(CefWebEngine engine, String expression) {
        try {
            CompletableFuture<@Nullable String> result =
                    Objects.requireNonNull(onFxThread(() -> engine.evaluateScriptAsync(expression)));
            String json = result.get(5, TimeUnit.SECONDS);
            return json == null ? "" : json.replace("\"", "");
        } catch (Exception failure) {
            return "";
        }
    }

    private static void initialiseCef() throws Exception {
        assumeDisplayServer();
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        CefTestLaunch.setRootCachePath(settings, cacheDir.toAbsolutePath().toString());
        startJavaFx();
        onFxThread(() -> CefWebView.initialise(settings, CefTestLaunch.extraArgs()), CEF_STARTUP_TIMEOUT);
    }

    @AfterAll
    static void shutdown() throws Exception {
        closeAllWindows();
        if (Cef.INSTANCE.state() == Cef.State.INITIALISED) {
            onFxThread(CefWebView::terminate);
        }
        shutdownJavaFx();
    }
}
