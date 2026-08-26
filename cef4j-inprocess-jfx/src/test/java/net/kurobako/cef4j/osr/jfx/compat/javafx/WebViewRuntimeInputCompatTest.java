package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import javafx.concurrent.Worker;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeInputCompatTest extends WebViewRuntimeCompatTestBase {
    private static final Color INPUT_BACKGROUND = Color.web("#123456");

    @Test
    void keyboardInputWorksAfterEveryClick() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body style='margin:0'>"
                        + "<input id='field' style='position:absolute;left:0;top:0;width:100%;height:100%;"
                        + "border:0;background:#123456;font-size:24px'/>"
                        + "<script>"
                        + "const field = document.getElementById('field');"
                        + "let clicks = 0;"
                        + "function syncTitle(){ document.title = clicks + '|' + field.value; }"
                        + "field.addEventListener('click', function(){ clicks++; syncTitle(); });"
                        + "field.addEventListener('input', syncTitle);"
                        + "syncTitle();"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "0|".equals(title(view)), 3_000)).isTrue();
        assertThat(waitForRenderedColor(view, 120, 120, INPUT_BACKGROUND, 10_000))
                .as("the input surface should be rendered before clicking")
                .isTrue();

        for (int i = 1; i <= 4; i++) {
            leftClick(view, 120, 120);
            String clicked = i + "|" + "a".repeat(i - 1);
            assertThat(waitUntilOnFx(() -> clicked.equals(title(view)), 2_000))
                    .as("click #%s should reach the input exactly once", i)
                    .isTrue();
            typeText(view, javafx.scene.input.KeyCode.A, "a");
            String expected = i + "|" + "a".repeat(i);
            assertThat(waitUntilOnFx(() -> expected.equals(title(view)), 2_000))
                    .as("keyboard input after click #%s", i)
                    .isTrue();
        }
    }
}
