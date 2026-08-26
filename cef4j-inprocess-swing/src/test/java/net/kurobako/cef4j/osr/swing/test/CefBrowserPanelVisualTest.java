package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelVisualTest extends SwingBrowserPanelTestBase {

    @Test
    void closingFrameAllowsNewPanelToLoad() throws Exception {
        CefBrowserPanel first = createAttachedPanel();
        loadContent(first, "<html><head><title>first</title></head><body>first</body></html>");
        assertThat(waitUntil(() -> "first".equals(getTitle(first)), 5_000)).isTrue();

        closeFrames();

        CefBrowserPanel second = createAttachedPanel();
        loadContent(second, "<html><head><title>second-view</title></head><body>ok</body></html>");

        assertThat(waitUntil(() -> "second-view".equals(getTitle(second)), 5_000))
                .isTrue();
    }

    @Test
    void resizePanelTriggersRepaint() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        long loadBaseline = loadGeneration(panel);
        loadContent(
                panel,
                "<html><body><script>"
                        + "document.title = window.innerWidth + 'x' + window.innerHeight;"
                        + "window.addEventListener('resize', function() {"
                        + "  document.title = window.innerWidth + 'x' + window.innerHeight;"
                        + "});"
                        + "</script></body></html>");

        assertThat(waitForLoadEnd(panel, loadBaseline, 5_000)).isTrue();
        String initialTitle = getTitle(panel);

        onSwingThread(() -> {
            javax.swing.JFrame frame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(panel);
            if (frame != null) {
                frame.setSize(400, 300);
            }
        });

        assertThat(waitUntil(() -> !initialTitle.equals(getTitle(panel)), 5_000))
                .as("expected title to change after resize; still '%s'", initialTitle)
                .isTrue();
    }
}
