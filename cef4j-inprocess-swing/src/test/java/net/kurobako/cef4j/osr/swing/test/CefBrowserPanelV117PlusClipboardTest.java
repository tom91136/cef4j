package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;
import javax.swing.SwingUtilities;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Timeout(40)
class CefBrowserPanelV117PlusClipboardTest extends SwingBrowserPanelTestBase {
    private static final int REPEAT_COUNT = 5;
    private static final double SOURCE_X = 180;
    private static final double TARGET_X = 620;
    private static final double TEXT_Y = 120;
    private static final String SAMPLE_TEXT = "alpha beta";

    @ParameterizedTest(name = "copy/paste layout={0}, copy={1}, paste={2}")
    @MethodSource("copyPasteModes")
    void copyAndPasteAcrossKeyboardAndFrameCommand(Layout layout, Trigger copy, Trigger paste) throws Exception {
        CefBrowserPanel panel = loadClipboardPage(layout);

        for (int iteration = 1; iteration <= REPEAT_COUNT; iteration++) {
            String text = iterationText(iteration);
            resetPageState(panel, text);
            setSystemClipboardText("seed");
            selectSourceText(panel, layout);
            performClipboardAction(panel, Region.SOURCE, ClipboardAction.COPY, copy);
            focusTarget(panel, layout);
            performClipboardAction(panel, Region.TARGET, ClipboardAction.PASTE, paste);

            assertThat(waitUntil(() -> titleFor(panel).equals(text + "|" + text), 5_000))
                    .as("copy/paste iteration %s should complete", iteration)
                    .isTrue();
        }
    }

    @ParameterizedTest(name = "cut/paste layout={0}, cut={1}, paste={2}")
    @MethodSource("cutPasteModes")
    void cutAndPasteAcrossKeyboardAndFrameCommand(Layout layout, Trigger cut, Trigger paste) throws Exception {
        CefBrowserPanel panel = loadClipboardPage(layout);

        for (int iteration = 1; iteration <= REPEAT_COUNT; iteration++) {
            String text = iterationText(iteration);
            resetPageState(panel, text);
            setSystemClipboardText("seed");
            selectSourceText(panel, layout);
            performClipboardAction(panel, Region.SOURCE, ClipboardAction.CUT, cut);
            focusTarget(panel, layout);
            performClipboardAction(panel, Region.TARGET, ClipboardAction.PASTE, paste);

            assertThat(waitUntil(() -> titleFor(panel).equals("|" + text), 5_000))
                    .as("cut/paste iteration %s should complete", iteration)
                    .isTrue();
        }
    }

    static Stream<Arguments> copyPasteModes() {
        return Stream.of(Layout.values())
                .flatMap(layout -> Stream.of(Trigger.values())
                        .flatMap(copy -> Stream.of(Trigger.values()).map(paste -> Arguments.of(layout, copy, paste))));
    }

    static Stream<Arguments> cutPasteModes() {
        return Stream.of(Layout.values())
                .flatMap(layout -> Stream.of(Trigger.values())
                        .flatMap(cut -> Stream.of(Trigger.values()).map(paste -> Arguments.of(layout, cut, paste))));
    }

    private static CefBrowserPanel loadClipboardPage(Layout layout) throws Exception {
        CefBrowserPanel panel = createAttachedPanel();
        loadContent(panel, clipboardPageHtml(layout));
        assertThat(waitUntil(() -> titleFor(panel).equals(SAMPLE_TEXT + "|"), 5_000))
                .isTrue();
        return panel;
    }

    private static void selectSourceText(CefBrowserPanel panel, Layout layout) throws Exception {
        focusSource(panel, layout);
        invokeShortcut(panel, KeyEvent.VK_A);
        executeJavaScript(
                panel,
                "var el = window.__getSrcEl && window.__getSrcEl();"
                        + "if (el) { el.focus(); if (el.select) { el.select(); } if (window.__syncTitle) { window.__syncTitle(); } }");
        Thread.sleep(50);
    }

    private static String iterationText(int iteration) {
        return SAMPLE_TEXT + " iter " + iteration;
    }

    private static void resetPageState(CefBrowserPanel panel, String text) throws Exception {
        executeJavaScript(
                panel,
                "var src = window.__getSrcEl && window.__getSrcEl();"
                        + "var dst = window.__getDstEl && window.__getDstEl();"
                        + "if (src) { src.value = '" + text + "'; }"
                        + "if (dst) { dst.value = ''; }"
                        + "if (window.__syncTitle) { window.__syncTitle(); }");
        assertThat(waitUntil(() -> titleFor(panel).equals(text + "|"), 2_000)).isTrue();
    }

    private static void performClipboardAction(
            CefBrowserPanel panel,
            @SuppressWarnings("UnusedVariable") Region region,
            ClipboardAction action,
            Trigger trigger)
            throws Exception {
        if (trigger == Trigger.KEYBOARD) {
            invokeShortcut(panel, action.keyCode);
            return;
        }
        invokeFrameClipboardAction(panel, action);
    }

    private static void focusSource(CefBrowserPanel panel, Layout layout) throws Exception {
        focusElement(panel, layout, true, SOURCE_X);
    }

    private static void focusTarget(CefBrowserPanel panel, Layout layout) throws Exception {
        focusElement(panel, layout, false, TARGET_X);
    }

    private static void focusElement(
            CefBrowserPanel panel, @SuppressWarnings("UnusedVariable") Layout layout, boolean source, double x)
            throws Exception {
        leftClick(panel, x, TEXT_Y);
        executeJavaScript(
                panel,
                source
                        ? "var el = window.__getSrcEl && window.__getSrcEl();"
                                + "if (el) { el.focus(); if (el.setSelectionRange) { el.setSelectionRange(el.value.length, el.value.length); } }"
                        : "var el = window.__getDstEl && window.__getDstEl();"
                                + "if (el) { el.focus(); if (el.setSelectionRange) { el.setSelectionRange(el.value.length, el.value.length); } }");
        Thread.sleep(50);
    }

    private static void leftClick(CefBrowserPanel panel, double x, double y) throws Exception {
        onSwingThread(() -> {
            Window window = SwingUtilities.getWindowAncestor(panel);
            if (window != null) {
                window.toFront();
                window.requestFocus();
            }
            panel.requestFocusInWindow();
            int px = (int) Math.round(x);
            int py = (int) Math.round(y);
            long now = System.currentTimeMillis();
            panel.dispatchEvent(new MouseEvent(panel, MouseEvent.MOUSE_MOVED, now, 0, px, py, 0, false, 0));
            panel.dispatchEvent(new MouseEvent(
                    panel,
                    MouseEvent.MOUSE_PRESSED,
                    now,
                    InputEvent.BUTTON1_DOWN_MASK,
                    px,
                    py,
                    1,
                    false,
                    MouseEvent.BUTTON1));
            panel.dispatchEvent(
                    new MouseEvent(panel, MouseEvent.MOUSE_RELEASED, now, 0, px, py, 1, false, MouseEvent.BUTTON1));
            panel.dispatchEvent(
                    new MouseEvent(panel, MouseEvent.MOUSE_CLICKED, now, 0, px, py, 1, false, MouseEvent.BUTTON1));
        });
        Thread.sleep(75);
    }

    private static void invokeShortcut(CefBrowserPanel panel, int keyCode) throws Exception {
        onSwingThread(() -> {
            Window window = SwingUtilities.getWindowAncestor(panel);
            if (window != null) {
                window.toFront();
                window.requestFocus();
            }
            panel.requestFocusInWindow();
            long now = System.currentTimeMillis();
            dispatchKey(
                    panel,
                    now,
                    KeyEvent.KEY_PRESSED,
                    KeyEvent.VK_CONTROL,
                    KeyEvent.CHAR_UNDEFINED,
                    InputEvent.CTRL_DOWN_MASK);
            dispatchKey(panel, now, KeyEvent.KEY_PRESSED, keyCode, KeyEvent.CHAR_UNDEFINED, InputEvent.CTRL_DOWN_MASK);
            dispatchKey(panel, now, KeyEvent.KEY_RELEASED, keyCode, KeyEvent.CHAR_UNDEFINED, InputEvent.CTRL_DOWN_MASK);
            dispatchKey(panel, now, KeyEvent.KEY_RELEASED, KeyEvent.VK_CONTROL, KeyEvent.CHAR_UNDEFINED, 0);
        });
        Thread.sleep(75);
    }

    private static void dispatchKey(
            CefBrowserPanel panel, long when, int id, int keyCode, char keyChar, int modifiers) {
        panel.dispatchEvent(new KeyEvent(panel, id, when, modifiers, keyCode, keyChar, KeyEvent.KEY_LOCATION_STANDARD));
    }

    private static void invokeFrameClipboardAction(CefBrowserPanel panel, ClipboardAction action) throws Exception {
        CefBrowser browser = Objects.requireNonNull(panel.browser(), "browser");
        CefFrame frame =
                browser.getFocusedFrame().orElseGet(() -> browser.getMainFrame().orElse(null));
        assertThat(frame).isNotNull();
        switch (action) {
            case COPY:
                frame.copy();
                break;
            case CUT:
                frame.cut();
                break;
            case PASTE:
                frame.paste();
                break;
        }
        Thread.sleep(75);
    }

    private static String clipboardPageHtml(Layout layout) {
        switch (layout) {
            case NORMAL:
                return "<html><body style='margin:0'>"
                        + "<textarea id='src' style='position:absolute;left:0;top:0;width:49%;height:100%;'>"
                        + SAMPLE_TEXT
                        + "</textarea>"
                        + "<textarea id='dst' style='position:absolute;right:0;top:0;width:49%;height:100%;'></textarea>"
                        + "<script>"
                        + "window.__getSrcEl = function(){ return document.getElementById('src'); };"
                        + "window.__getDstEl = function(){ return document.getElementById('dst'); };"
                        + "window.__syncTitle = function(){"
                        + "  var src = window.__getSrcEl(); var dst = window.__getDstEl();"
                        + "  document.title = (src ? src.value : '') + '|' + (dst ? dst.value : '');"
                        + "};"
                        + "window.__getSrcEl().addEventListener('input', window.__syncTitle);"
                        + "window.__getDstEl().addEventListener('input', window.__syncTitle);"
                        + "window.__syncTitle();"
                        + "</script>"
                        + "</body></html>";
            case IFRAME:
                return "<html><body style='margin:0'>"
                        + "<iframe id='mainFrame'"
                        + " name='foo'"
                        + " title='foo'"
                        + " src='about:blank'"
                        + " sandbox='allow-downloads allow-forms allow-modals allow-pointer-lock allow-popups"
                        + " allow-popups-to-escape-sandbox allow-presentation allow-same-origin allow-scripts"
                        + " allow-top-navigation-by-user-activation'"
                        + " allow='accelerometer *; bluetooth *; camera *; clipboard-read *; clipboard-write *;"
                        + " display-capture *; encrypted-media *; geolocation *; gyroscope *; language-detector *;"
                        + " language-model *; local-network-access *; microphone *; midi *; rewriter *; serial *;"
                        + " summarizer *; translator *; web-share *; writer *; xr-spatial-tracking *'"
                        + " scrolling='auto'"
                        + " allowtransparency='true'"
                        + " allowpaymentrequest='true'"
                        + " allowfullscreen='true'"
                        + " class='result-iframe'"
                        + " loading='lazy'"
                        + " style='position:absolute;left:0;top:0;width:100%;height:100%;border:0'>"
                        + "</iframe>"
                        + "<script>"
                        + "(function(){"
                        + "  var frame = document.getElementById('mainFrame');"
                        + "  var d = frame.contentWindow.document;"
                        + "  d.open();"
                        + "  d.write('<!doctype html><html><body style=\"margin:0\">"
                        + "<textarea id=\"src\" style=\"position:absolute;left:0;top:0;width:49%;height:100%;\">"
                        + SAMPLE_TEXT
                        + "</textarea>"
                        + "<textarea id=\"dst\" style=\"position:absolute;right:0;top:0;width:49%;height:100%;\"></textarea>"
                        + "</body></html>');"
                        + "  d.close();"
                        + "  window.__getSrcEl = function(){ return d.getElementById('src'); };"
                        + "  window.__getDstEl = function(){ return d.getElementById('dst'); };"
                        + "  window.__syncTitle = function(){"
                        + "    var src = window.__getSrcEl(); var dst = window.__getDstEl();"
                        + "    document.title = (src ? src.value : '') + '|' + (dst ? dst.value : '');"
                        + "  };"
                        + "  window.__getSrcEl().addEventListener('input', window.__syncTitle);"
                        + "  window.__getDstEl().addEventListener('input', window.__syncTitle);"
                        + "  window.__syncTitle();"
                        + "})();"
                        + "</script>"
                        + "</body></html>";
            case FRAMES:
                return "<html><body style='margin:0'>"
                        + "<iframe id='srcFrame' style='position:absolute;left:0;top:0;width:49%;height:100%;border:0'></iframe>"
                        + "<iframe id='dstFrame' style='position:absolute;right:0;top:0;width:49%;height:100%;border:0'></iframe>"
                        + "<script>"
                        + "(function(){"
                        + "  var srcDoc = document.getElementById('srcFrame').contentWindow.document;"
                        + "  var dstDoc = document.getElementById('dstFrame').contentWindow.document;"
                        + "  srcDoc.open();"
                        + "  srcDoc.write('<!doctype html><html><body style=\"margin:0\">"
                        + "<textarea id=\"src\" style=\"position:absolute;left:0;top:0;width:100%;height:100%;\">"
                        + SAMPLE_TEXT
                        + "</textarea></body></html>');"
                        + "  srcDoc.close();"
                        + "  dstDoc.open();"
                        + "  dstDoc.write('<!doctype html><html><body style=\"margin:0\">"
                        + "<textarea id=\"dst\" style=\"position:absolute;left:0;top:0;width:100%;height:100%;\"></textarea>"
                        + "</body></html>');"
                        + "  dstDoc.close();"
                        + "  window.__getSrcEl = function(){ return srcDoc.getElementById('src'); };"
                        + "  window.__getDstEl = function(){ return dstDoc.getElementById('dst'); };"
                        + "  window.__syncTitle = function(){"
                        + "    var src = window.__getSrcEl(); var dst = window.__getDstEl();"
                        + "    document.title = (src ? src.value : '') + '|' + (dst ? dst.value : '');"
                        + "  };"
                        + "  window.__getSrcEl().addEventListener('input', window.__syncTitle);"
                        + "  window.__getDstEl().addEventListener('input', window.__syncTitle);"
                        + "  window.__syncTitle();"
                        + "})();"
                        + "</script>"
                        + "</body></html>";
            default:
                throw new IllegalArgumentException("Unknown layout: " + layout);
        }
    }

    private static String titleFor(CefBrowserPanel panel) {
        return getTitle(panel);
    }

    private enum Layout {
        NORMAL,
        IFRAME,
        FRAMES
    }

    private enum Trigger {
        KEYBOARD,
        FRAME_COMMAND
    }

    private enum Region {
        SOURCE,
        TARGET
    }

    private enum ClipboardAction {
        COPY(KeyEvent.VK_C),
        CUT(KeyEvent.VK_X),
        PASTE(KeyEvent.VK_V);

        private final int keyCode;

        ClipboardAction(int keyCode) {
            this.keyCode = keyCode;
        }
    }

    private static void setSystemClipboardText(String text) throws Exception {
        runWithTimeout(
                () -> {
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
                    return null;
                },
                1_000,
                "Timed out writing system clipboard");
    }

    private static <T> T runWithTimeout(Callable<T> task, long timeoutMillis, String timeoutMessage) throws Exception {
        CompletableFuture<T> future = CompletableFuture.supplyAsync(() -> {
            try {
                return task.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        try {
            return future.get(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException && cause.getCause() instanceof Exception) {
                throw (Exception) cause.getCause();
            }
            if (cause instanceof Exception) {
                throw (Exception) cause;
            }
            throw new RuntimeException(cause);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw new TimeoutException(timeoutMessage);
        }
    }
}
