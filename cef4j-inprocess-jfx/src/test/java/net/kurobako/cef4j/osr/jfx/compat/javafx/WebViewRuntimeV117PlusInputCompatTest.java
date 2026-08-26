package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import javafx.concurrent.Worker;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeV117PlusInputCompatTest extends WebViewRuntimeCompatTestBase {
    private static final long SCROLL_DELIVERY_TIMEOUT_MILLIS = 10_000;
    private static final Color PAGE_BACKGROUND = Color.web("#123456");

    @Test
    void horizontalScrollEventsReachThePage() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body style='margin:0;background:#123456'>"
                        + "<script>"
                        + "window.addEventListener('wheel', function(e) {"
                        + "  document.title = String(Math.round(e.deltaX));"
                        + "}, { passive: true });"
                        + "document.title = '0';"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "0".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
        assertThat(waitForRenderedColor(view, 120, 120, PAGE_BACKGROUND, 10_000))
                .as("the page surface should be rendered before horizontal input")
                .isTrue();

        leftClick(view, 120, 120);
        onFxThread(() -> fireScroll(view, 120, 120, -140, 0));
        assertThat(waitUntilOnFx(() -> !"0".equals(view.getEngine().getTitle()), SCROLL_DELIVERY_TIMEOUT_MILLIS))
                .isTrue();
    }

    @Test
    void verticalScrollEventsReachThePage() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body style='margin:0;background:#123456'>"
                        + "<script>"
                        + "window.addEventListener('wheel', function(e) {"
                        + "  document.title = String(Math.round(e.deltaY));"
                        + "}, { passive: true });"
                        + "document.title = '0';"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "0".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
        assertThat(waitForRenderedColor(view, 120, 120, PAGE_BACKGROUND, 10_000))
                .as("the page surface should be rendered before vertical input")
                .isTrue();

        leftClick(view, 120, 120);
        onFxThread(() -> fireScroll(view, 120, 120, 0, 160));
        assertThat(waitUntilOnFx(() -> !"0".equals(view.getEngine().getTitle()), SCROLL_DELIVERY_TIMEOUT_MILLIS))
                .isTrue();
    }
}
