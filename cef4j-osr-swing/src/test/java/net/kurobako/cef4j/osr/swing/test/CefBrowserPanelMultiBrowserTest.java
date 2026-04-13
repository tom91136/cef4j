package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelMultiBrowserTest extends SwingBrowserPanelTestBase {

    @Test
    void sequentialBrowserCreationAndLoad() throws Exception {
        for (int i = 0; i < 3; i++) {
            CefBrowserPanel panel = createAttachedPanel();
            String title = "browser-" + i;
            loadContent(panel, "<html><head><title>" + title + "</title></head><body>" + title + "</body></html>");
            assertThat(waitUntil(() -> title.equals(getTitle(panel)), 5_000))
                    .as("title for browser %d", i)
                    .isTrue();
            closeFrames();
        }
    }

    @Test
    void twoSimultaneousPanelsLoadIndependently() throws Exception {
        CefBrowserPanel panelA = createAttachedPanel();
        CefBrowserPanel panelB = createAttachedPanel();

        loadContent(panelA, "<html><head><title>panel-a</title></head><body>a</body></html>");
        loadContent(panelB, "<html><head><title>panel-b</title></head><body>b</body></html>");

        assertThat(waitUntil(() -> "panel-a".equals(getTitle(panelA)), 5_000)).isTrue();
        assertThat(waitUntil(() -> "panel-b".equals(getTitle(panelB)), 5_000)).isTrue();
    }

    @Test
    void javaScriptExecutesOnEachBrowser() throws Exception {
        CefBrowserPanel panelA = createAttachedPanel();
        CefBrowserPanel panelB = createAttachedPanel();

        loadContent(panelA, "<html><head><title>init-a</title></head><body>a</body></html>");
        loadContent(panelB, "<html><head><title>init-b</title></head><body>b</body></html>");

        assertThat(waitUntil(() -> "init-a".equals(getTitle(panelA)), 5_000)).isTrue();
        assertThat(waitUntil(() -> "init-b".equals(getTitle(panelB)), 5_000)).isTrue();

        executeJavaScript(panelA, "document.title = 'eval-a'");
        executeJavaScript(panelB, "document.title = 'eval-b'");

        assertThat(waitUntil(() -> "eval-a".equals(getTitle(panelA)), 5_000)).isTrue();
        assertThat(waitUntil(() -> "eval-b".equals(getTitle(panelB)), 5_000)).isTrue();
    }
}
