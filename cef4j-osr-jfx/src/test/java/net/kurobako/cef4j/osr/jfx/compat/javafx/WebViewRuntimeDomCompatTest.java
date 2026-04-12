package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import javafx.concurrent.Worker;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeDomCompatTest extends WebViewRuntimeCompatTestBase {

    @Test
    void selectElementOptionsAccessibleViaJavaScript() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body>"
                        + "<select id='sel'>"
                        + "  <option value='a'>Alpha</option>"
                        + "  <option value='b'>Beta</option>"
                        + "  <option value='c'>Gamma</option>"
                        + "</select>"
                        + "<script>"
                        + "  var sel = document.getElementById('sel');"
                        + "  document.title = sel.options.length + '-' + sel.options[0].text;"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "3-Alpha".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
    }

    @Test
    void selectElementChangeEventFires() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body>"
                        + "<select id='sel'>"
                        + "  <option value='a'>Alpha</option>"
                        + "  <option value='b'>Beta</option>"
                        + "</select>"
                        + "<script>"
                        + "  var sel = document.getElementById('sel');"
                        + "  sel.addEventListener('change', function() {"
                        + "    document.title = 'changed-' + sel.value;"
                        + "  });"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();

        onFxThread(() -> view.getEngine()
                .executeScript("var sel = document.getElementById('sel');"
                        + "sel.value = 'b';"
                        + "sel.dispatchEvent(new Event('change'));"));

        assertThat(waitUntilOnFx(() -> "changed-b".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
    }
}
