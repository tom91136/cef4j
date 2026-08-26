package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelInputTest extends SwingBrowserPanelTestBase {
    private static final int PAGE_BACKGROUND = new Color(0x12, 0x34, 0x56).getRGB();

    @Test
    void mouseExitEventsReachThePage() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        String html = "<html><body style='margin:0;height:100vh;background:#123456'>"
                + "<script>"
                + "document.addEventListener('mousemove', function() { document.title = 'inside'; });"
                + "document.addEventListener('mouseleave', function() { document.title = 'outside'; });"
                + "document.title = 'start';"
                + "</script>"
                + "</body></html>";
        loadContent(panel, html);

        assertThat(waitUntil(() -> "start".equals(getTitle(panel)), 10_000))
                .as("the input fixture page should finish loading")
                .isTrue();
        assertThat(waitForRenderedColor(panel, 80, 80, PAGE_BACKGROUND, 10_000))
                .as("the page surface should be rendered before mouse input")
                .isTrue();

        dispatchMouse(panel, MouseEvent.MOUSE_ENTERED, 80, 80, 0, MouseEvent.NOBUTTON, false, 0);
        dispatchMouse(panel, MouseEvent.MOUSE_MOVED, 80, 80, 0, MouseEvent.NOBUTTON, false, 0);
        assertThat(waitUntil(() -> "inside".equals(getTitle(panel)), 3_000)).isTrue();
        dispatchMouse(panel, MouseEvent.MOUSE_EXITED, 805, 80, 0, MouseEvent.NOBUTTON, false, 0);
        assertThat(waitUntil(() -> "outside".equals(getTitle(panel)), 3_000)).isTrue();
    }

    @Test
    void verticalScrollEventsReachThePage() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        loadContent(
                panel,
                "<html><body style='margin:0;background:#123456'>"
                        + "<script>"
                        + "window.addEventListener('wheel', function(e) {"
                        + "  document.title = String(Math.round(e.deltaY));"
                        + "}, { passive: true });"
                        + "document.title = '0';"
                        + "</script>"
                        + "</body></html>");

        assertThat(waitUntil(() -> "0".equals(getTitle(panel)), 5_000)).isTrue();
        assertThat(waitForRenderedColor(panel, 120, 120, PAGE_BACKGROUND, 10_000))
                .as("the page surface should be rendered before wheel input")
                .isTrue();
        leftClick(panel, 120, 120);
        dispatchWheel(panel, 120, 120, 3);
        assertThat(waitUntil(() -> !"0".equals(getTitle(panel)), 10_000)).isTrue();
    }

    private static void leftClick(CefBrowserPanel panel, double x, double y) throws Exception {
        dispatchMouse(
                panel, MouseEvent.MOUSE_PRESSED, x, y, InputEvent.BUTTON1_DOWN_MASK, MouseEvent.BUTTON1, false, 1);
        dispatchMouse(panel, MouseEvent.MOUSE_RELEASED, x, y, 0, MouseEvent.BUTTON1, false, 1);
        dispatchMouse(panel, MouseEvent.MOUSE_CLICKED, x, y, 0, MouseEvent.BUTTON1, false, 1);
    }

    private static void dispatchWheel(CefBrowserPanel panel, double x, double y, int wheelRotation) throws Exception {
        onSwingThread(() -> {
            prepareFocus(panel);
            int px = (int) Math.round(x);
            int py = (int) Math.round(y);
            long now = System.currentTimeMillis();
            panel.dispatchEvent(new MouseWheelEvent(
                    panel,
                    MouseEvent.MOUSE_WHEEL,
                    now,
                    0,
                    px,
                    py,
                    0,
                    false,
                    MouseWheelEvent.WHEEL_UNIT_SCROLL,
                    1,
                    wheelRotation));
        });
        drainCefUi();
    }

    private static void dispatchMouse(
            CefBrowserPanel panel,
            int id,
            double x,
            double y,
            int modifiersEx,
            int button,
            boolean popupTrigger,
            int clickCount)
            throws Exception {
        onSwingThread(() -> {
            prepareFocus(panel);
            int px = (int) Math.round(x);
            int py = (int) Math.round(y);
            long now = System.currentTimeMillis();
            panel.dispatchEvent(new MouseEvent(panel, id, now, modifiersEx, px, py, clickCount, popupTrigger, button));
        });
        drainCefUi();
    }

    private static void prepareFocus(CefBrowserPanel panel) {
        Window window = SwingUtilities.getWindowAncestor(panel);
        if (window != null) {
            window.toFront();
            window.requestFocus();
        }
        panel.requestFocusInWindow();
    }
}
