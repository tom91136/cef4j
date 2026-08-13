package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Window;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.function.BooleanSupplier;
import javax.swing.SwingUtilities;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelInputTest extends SwingBrowserPanelTestBase {

    @Test
    void mouseExitEventsReachThePage() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        String html = "<html><body style='margin:0;height:100vh'>"
                + "<script>"
                + "document.title = 'start';"
                + "document.addEventListener('mousemove', function() { document.title = 'inside'; });"
                + "document.addEventListener('mouseleave', function() { document.title = 'outside'; });"
                + "</script>"
                + "</body></html>";
        loadContent(panel, html);

        assertThat(waitUntilDispatching(() -> "start".equals(getTitle(panel)), 10_000, () -> loadContent(panel, html)))
                .as("the input fixture page should finish loading")
                .isTrue();

        dispatchMouse(panel, MouseEvent.MOUSE_ENTERED, 80, 80, 0, MouseEvent.NOBUTTON, false, 0);
        assertThat(waitUntilDispatching(
                        () -> "inside".equals(getTitle(panel)),
                        3_000,
                        () -> dispatchMouse(panel, MouseEvent.MOUSE_MOVED, 80, 80, 0, MouseEvent.NOBUTTON, false, 0)))
                .isTrue();
        assertThat(waitUntilDispatching(
                        () -> "outside".equals(getTitle(panel)),
                        3_000,
                        () -> dispatchMouse(panel, MouseEvent.MOUSE_EXITED, 805, 80, 0, MouseEvent.NOBUTTON, false, 0)))
                .isTrue();
    }

    @Test
    void verticalScrollEventsReachThePage() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        loadContent(
                panel,
                "<html><body>"
                        + "<script>"
                        + "document.title = '0';"
                        + "window.addEventListener('wheel', function(e) {"
                        + "  document.title = String(Math.round(e.deltaY));"
                        + "}, { passive: true });"
                        + "</script>"
                        + "</body></html>");

        assertThat(waitUntil(() -> "0".equals(getTitle(panel)), 5_000)).isTrue();
        leftClick(panel, 120, 120);
        assertThat(waitUntilDispatching(
                        () -> !"0".equals(getTitle(panel)), 3_000, () -> dispatchWheel(panel, 120, 120, 3)))
                .isTrue();
    }

    private static boolean waitUntilDispatching(BooleanSupplier condition, long timeoutMillis, ThrowingRunnable action)
            throws Exception {
        long deadline = System.nanoTime() + java.util.concurrent.TimeUnit.MILLISECONDS.toNanos(timeoutMillis);
        while (System.nanoTime() < deadline) {
            action.run();
            long pollEnd = System.nanoTime() + java.util.concurrent.TimeUnit.MILLISECONDS.toNanos(100);
            while (System.nanoTime() < pollEnd) {
                if (condition.getAsBoolean()) return true;
                Thread.sleep(10);
            }
        }
        return condition.getAsBoolean();
    }

    @FunctionalInterface
    interface ThrowingRunnable {
        void run() throws Exception;
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
        Thread.sleep(75);
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
        Thread.sleep(75);
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
