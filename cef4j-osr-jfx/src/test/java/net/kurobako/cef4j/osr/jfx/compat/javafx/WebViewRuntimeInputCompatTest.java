package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import javafx.concurrent.Worker;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeInputCompatTest extends WebViewRuntimeCompatTestBase {

    @Test
    void keyboardInputWorksAfterEveryClick() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body style='margin:0'>"
                        + "<input id='field' style='position:absolute;left:0;top:0;width:100%;height:100%;font-size:24px'/>"
                        + "<script>"
                        + "const field = document.getElementById('field');"
                        + "document.title = '';"
                        + "field.addEventListener('input', function(){ document.title = field.value; });"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                .isTrue();

        for (int i = 1; i <= 4; i++) {
            leftClick(view, 120, 120);
            typeText(view, javafx.scene.input.KeyCode.A, "a");
            String expected = "a".repeat(i);
            assertThat(waitUntilOnFx(() -> expected.equals(title(view)), 2_000))
                    .as("keyboard input after click #%s", i)
                    .isTrue();
        }
    }

}
