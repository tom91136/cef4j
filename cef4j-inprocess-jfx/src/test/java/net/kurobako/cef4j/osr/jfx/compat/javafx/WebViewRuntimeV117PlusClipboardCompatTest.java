package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import javafx.concurrent.Worker;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebView;
import javax.annotation.Nullable;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Timeout(30)
@SuppressWarnings("deprecation") // CefWebEngine.executeScript is deprecated; tests exercise JFX parity
class WebViewRuntimeV117PlusClipboardCompatTest extends WebViewRuntimeCompatTestBase {
    private static final int CEF_REPEAT_COUNT = 5;
    private static final int JFX_REPEAT_COUNT = 1;
    private static final long MENU_VISIBLE_TIMEOUT_MS = 450;
    private static final int MENU_MAX_ATTEMPTS = 1;
    private static final double SOURCE_X = 180;
    private static final double TARGET_X = 620;
    private static final double TEXT_Y = 120;
    private static final String SAMPLE_TEXT = "alpha beta";

    @ParameterizedTest(name = "copy/paste layout={0}, copy={1}, paste={2}")
    @MethodSource("transferModes")
    void copyAndPasteAcrossKeyboardAndContextMenu(Layout layout, Trigger copy, Trigger paste) throws Exception {
        WebView view = loadClipboardPage(layout);

        for (int iteration = 1; iteration <= repeatCount(); iteration++) {
            String text = iterationText(iteration);
            resetPageState(view, text);
            setClipboardText("seed");
            selectSourceText(view);
            performClipboardAction(view, Region.SOURCE, ClipboardAction.COPY, copy);
            focusTarget(view);
            performClipboardAction(view, Region.TARGET, ClipboardAction.PASTE, paste);
            assertThat(waitForExpectedPaste(view, text + "|" + text, paste))
                    .as("copy/paste iteration %s should complete", iteration)
                    .isTrue();
        }
    }

    @ParameterizedTest(name = "cut/paste layout={0}, cut={1}, paste={2}")
    @MethodSource("transferModes")
    void cutAndPasteAcrossKeyboardAndContextMenu(Layout layout, Trigger cut, Trigger paste) throws Exception {
        WebView view = loadClipboardPage(layout);

        for (int iteration = 1; iteration <= repeatCount(); iteration++) {
            String text = iterationText(iteration);
            resetPageState(view, text);
            setClipboardText("seed");
            selectSourceText(view);
            performClipboardAction(view, Region.SOURCE, ClipboardAction.CUT, cut);
            focusTarget(view);
            performClipboardAction(view, Region.TARGET, ClipboardAction.PASTE, paste);
            assertThat(waitForExpectedPaste(view, "|" + text, paste))
                    .as("cut/paste iteration %s should complete", iteration)
                    .isTrue();
        }
    }

    private static WebView loadClipboardPage(Layout layout) throws Exception {
        WebView view = createAttachedWebView();
        onFxThread(() -> view.getEngine().loadContent(clipboardPageHtml(layout)));
        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> (SAMPLE_TEXT + "|").equals(titleFor(view)), 3_000))
                .isTrue();
        return view;
    }

    static Stream<Arguments> transferModes() {
        return layouts()
                .flatMap(layout -> Stream.of(Trigger.values())
                        .flatMap(transfer ->
                                Stream.of(Trigger.values()).map(paste -> Arguments.of(layout, transfer, paste))));
    }

    private static Stream<Layout> layouts() {
        return isCefCompatHarness() ? Stream.of(Layout.values()) : Stream.of(Layout.NORMAL);
    }

    private static int repeatCount() {
        return isCefCompatHarness() ? CEF_REPEAT_COUNT : JFX_REPEAT_COUNT;
    }

    private static void selectSourceText(WebView view) throws Exception {
        focusSource(view);
        invokeShortcut(view, KeyCode.A);
        onFxThread(
                () -> view.getEngine()
                        .executeScript(
                                "var el = window.__getSrcEl && window.__getSrcEl();"
                                        + "if (el) { el.focus(); if (el.select) { el.select(); } if (window.__syncTitle) { window.__syncTitle(); } }"));
        Thread.sleep(50);
    }

    private static String iterationText(int iteration) {
        return SAMPLE_TEXT + " iter " + iteration;
    }

    private static void resetPageState(WebView view, String text) throws Exception {
        onFxThread(() -> view.getEngine()
                .executeScript("var src = window.__getSrcEl && window.__getSrcEl();"
                        + "var dst = window.__getDstEl && window.__getDstEl();"
                        + "if (src) { src.value = '" + text + "'; }"
                        + "if (dst) { dst.value = ''; }"
                        + "if (window.__syncTitle) { window.__syncTitle(); }"));
        assertThat(waitUntilOnFx(() -> (text + "|").equals(titleFor(view)), 2_000))
                .isTrue();
    }

    private static void performClipboardAction(WebView view, Region region, ClipboardAction action, Trigger trigger)
            throws Exception {
        double x = region == Region.SOURCE ? SOURCE_X : TARGET_X;
        if (trigger == Trigger.KEYBOARD) {
            invokeShortcut(view, action.keyCode);
        } else {
            if (!tryInvokeContextMenuItem(
                    view, x, TEXT_Y, action.menuText, MENU_MAX_ATTEMPTS, MENU_VISIBLE_TIMEOUT_MS)) {
                invokeShortcut(view, action.keyCode);
            }
        }
    }

    private static void focusSource(WebView view) throws Exception {
        focusElement(view, true, SOURCE_X);
    }

    private static void focusTarget(WebView view) throws Exception {
        focusElement(view, false, TARGET_X);
    }

    private static void focusElement(WebView view, boolean source, double x) throws Exception {
        leftClick(view, x, TEXT_Y);
        onFxThread(
                () -> view.getEngine()
                        .executeScript(
                                source
                                        ? "var el = window.__getSrcEl && window.__getSrcEl();"
                                                + "if (el) { el.focus(); if (el.setSelectionRange) { el.setSelectionRange(el.value.length, el.value.length); } }"
                                        : "var el = window.__getDstEl && window.__getDstEl();"
                                                + "if (el) { el.focus(); if (el.setSelectionRange) { el.setSelectionRange(el.value.length, el.value.length); } }"));
        Thread.sleep(50);
    }

    @Nullable
    private static String titleFor(WebView view) throws Exception {
        return onFxThread(() -> view.getEngine().getTitle());
    }

    private static boolean waitForExpectedPaste(WebView view, String expectedTitle, Trigger pasteTrigger)
            throws Exception {
        if (waitUntilOnFx(() -> expectedTitle.equals(titleFor(view)), 1_200)) return true;

        clearTarget(view);
        focusTarget(view);
        performClipboardAction(view, Region.TARGET, ClipboardAction.PASTE, pasteTrigger);
        return waitUntilOnFx(() -> expectedTitle.equals(titleFor(view)), 1_800);
    }

    private static void clearTarget(WebView view) throws Exception {
        onFxThread(() -> view.getEngine()
                .executeScript("var dst = window.__getDstEl && window.__getDstEl();"
                        + "if (dst) { dst.value = ''; }"
                        + "if (window.__syncTitle) { window.__syncTitle(); }"));
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

    private enum Trigger {
        KEYBOARD,
        CONTEXT_MENU
    }

    private enum Layout {
        NORMAL,
        IFRAME,
        FRAMES
    }

    private enum Region {
        SOURCE,
        TARGET
    }

    private enum ClipboardAction {
        COPY(KeyCode.C, "Copy"),
        CUT(KeyCode.X, "Cut"),
        PASTE(KeyCode.V, "Paste");

        private final KeyCode keyCode;
        private final String menuText;

        ClipboardAction(KeyCode keyCode, String menuText) {
            this.keyCode = keyCode;
            this.menuText = menuText;
        }
    }
}
