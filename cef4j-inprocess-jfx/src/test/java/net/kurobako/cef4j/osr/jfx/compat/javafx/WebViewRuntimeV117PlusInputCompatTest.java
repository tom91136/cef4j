package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import javafx.concurrent.Worker;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeV117PlusInputCompatTest extends WebViewRuntimeCompatTestBase {

    @Test
    void horizontalScrollEventsReachThePage() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body>"
                        + "<script>"
                        + "document.title = '0';"
                        + "window.addEventListener('wheel', function(e) {"
                        + "  document.title = String(Math.round(e.deltaX));"
                        + "}, { passive: true });"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "0".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();

        assertThat(waitUntilFiringOnFx(
                        () -> !"0".equals(view.getEngine().getTitle()),
                        3_000,
                        () -> fireScroll(view, 120, 120, -140, 0)))
                .isTrue();
    }

    @Test
    void verticalScrollEventsReachThePage() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body>"
                        + "<script>"
                        + "document.title = '0';"
                        + "window.addEventListener('wheel', function(e) {"
                        + "  document.title = String(Math.round(e.deltaY));"
                        + "}, { passive: true });"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "0".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();

        assertThat(waitUntilFiringOnFx(
                        () -> !"0".equals(view.getEngine().getTitle()),
                        3_000,
                        () -> fireScroll(view, 120, 120, 0, 160)))
                .isTrue();
    }
}
