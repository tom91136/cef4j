package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import javafx.concurrent.Worker;
import javafx.scene.input.PickResult;
import javafx.scene.input.ScrollEvent;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeInputCompatTest extends WebViewRuntimeCompatTestBase {

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

        onFxThread(() -> fireHorizontalScroll(view, 120, 120, -140));

        assertThat(waitUntilOnFx(
                        () -> {
                            String t = view.getEngine().getTitle();
                            return t != null && !"0".equals(t);
                        },
                        10_000))
                .isTrue();
    }

    private static void fireHorizontalScroll(WebView view, double x, double y, double deltaX) {
        view.fireEvent(new ScrollEvent(
                ScrollEvent.SCROLL,
                x,
                y,
                x,
                y,
                false,
                false,
                false,
                false,
                false,
                false,
                deltaX,
                0,
                deltaX,
                0,
                ScrollEvent.HorizontalTextScrollUnits.NONE,
                0,
                ScrollEvent.VerticalTextScrollUnits.NONE,
                0,
                0,
                new PickResult(view, x, y)));
    }
}
