package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelV117PlusLoadTest extends SwingBrowserPanelTestBase {

    @Test
    void iframeLoadErrorsDoNotLeaveTopLevelLoadStuck() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        loadContent(
                panel,
                "<html><head><title>host-start</title></head><body>"
                        + "<iframe src='http://127.0.0.1:9/unreachable' "
                        + "onerror=\"parent.document.title='iframe-error'\">"
                        + "</iframe>"
                        + "<script>setTimeout(function(){ document.title = 'host-stable'; }, 150);</script>"
                        + "</body></html>");

        assertThat(waitUntil(() -> "host-stable".equals(getTitle(panel)), 5_000))
                .isTrue();
        Thread.sleep(300);
        assertThat(isLoading(panel)).isFalse();
    }
}
