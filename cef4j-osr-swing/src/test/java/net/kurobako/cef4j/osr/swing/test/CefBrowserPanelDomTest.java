package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelDomTest extends SwingBrowserPanelTestBase {

    @Test
    void selectElementOptionsAccessibleViaJavaScript() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        loadContent(
                panel,
                "<html><body>"
                        + "<select id='sel'>"
                        + "  <option value='a'>Alpha</option>"
                        + "  <option value='b'>Beta</option>"
                        + "  <option value='c'>Gamma</option>"
                        + "</select>"
                        + "<script>"
                        + "  var sel = document.getElementById('sel');"
                        + "  document.title = sel.options.length + '-' + sel.options[0].text;"
                        + "</script>"
                        + "</body></html>");

        assertThat(waitUntil(() -> "3-Alpha".equals(getTitle(panel)), 5_000)).isTrue();
    }

    @Test
    void selectElementChangeEventFires() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        loadContent(
                panel,
                "<html><body>"
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
                        + "</body></html>");

        assertThat(waitForLoadEnd(panel, 5_000)).isTrue();

        executeJavaScript(
                panel,
                "var sel = document.getElementById('sel');"
                        + "sel.value = 'b';"
                        + "sel.dispatchEvent(new Event('change'));");

        assertThat(waitUntil(() -> "changed-b".equals(getTitle(panel)), 5_000)).isTrue();
    }
}
