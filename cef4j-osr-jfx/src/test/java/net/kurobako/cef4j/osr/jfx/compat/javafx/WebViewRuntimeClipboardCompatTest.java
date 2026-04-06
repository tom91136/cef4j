package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import javafx.concurrent.Worker;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebView;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WebViewRuntimeClipboardCompatTest extends WebViewRuntimeCompatTestBase {
    private static final double SOURCE_X = 180;
    private static final double TARGET_X = 620;
    private static final double TEXT_Y = 120;
    private static final String SAMPLE_TEXT = "alpha beta";

    @ParameterizedTest(name = "copy/paste via copy={0}, paste={1}")
    @MethodSource("copyPasteModes")
    void copyAndPasteAcrossKeyboardAndContextMenu(Trigger copy, Trigger paste) throws Exception {
        WebView view = loadClipboardPage();

        setClipboardText("seed");
        selectSourceText(view);
        performClipboardAction(view, Region.SOURCE, ClipboardAction.COPY, copy);
        focusTarget(view);
        performClipboardAction(view, Region.TARGET, ClipboardAction.PASTE, paste);

        assertThat(waitUntilOnFx(() -> titleFor(view).equals(SAMPLE_TEXT + "|" + SAMPLE_TEXT), 5_000))
                .isTrue();
        assertThat(getClipboardText()).isEqualTo(SAMPLE_TEXT);
    }

    @ParameterizedTest(name = "cut/paste via cut=context_menu, paste={0}")
    @MethodSource("pasteModes")
    void cutAndPasteAcrossKeyboardAndContextMenu(Trigger paste) throws Exception {
        WebView view = loadClipboardPage();

        setClipboardText("seed");
        selectSourceText(view);
        performClipboardAction(view, Region.SOURCE, ClipboardAction.CUT, Trigger.CONTEXT_MENU);
        focusTarget(view);
        performClipboardAction(view, Region.TARGET, ClipboardAction.PASTE, paste);

        assertThat(waitUntilOnFx(() -> titleFor(view).equals("|" + SAMPLE_TEXT), 5_000))
                .isTrue();
        assertThat(getClipboardText()).isEqualTo(SAMPLE_TEXT);
    }

    static Stream<Arguments> copyPasteModes() {
        return transferModes();
    }

    static Stream<Trigger> pasteModes() {
        return Stream.of(Trigger.values());
    }

    private static WebView loadClipboardPage() throws Exception {
        WebView view = createAttachedWebView();
        onFxThread(() -> view.getEngine()
                .loadContent("<html><body style='margin:0'>"
                        + "<textarea id='src' style='position:absolute;left:0;top:0;width:49%;height:100%;'>"
                        + SAMPLE_TEXT
                        + "</textarea>"
                        + "<textarea id='dst' style='position:absolute;right:0;top:0;width:49%;height:100%;'></textarea>"
                        + "<script>"
                        + "function syncTitle(){"
                        + "  document.title = document.getElementById('src').value + '|' + document.getElementById('dst').value;"
                        + "}"
                        + "document.getElementById('src').addEventListener('input', syncTitle);"
                        + "document.getElementById('dst').addEventListener('input', syncTitle);"
                        + "syncTitle();"
                        + "</script>"
                        + "</body></html>"));
        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> titleFor(view).equals(SAMPLE_TEXT + "|"), 3_000))
                .isTrue();
        return view;
    }

    private static Stream<Arguments> transferModes() {
        return Stream.of(Trigger.values())
                .flatMap(transfer -> Stream.of(Trigger.values()).map(paste -> Arguments.of(transfer, paste)));
    }

    private static void selectSourceText(WebView view) throws Exception {
        focusSource(view);
        invokeShortcut(KeyCode.A);
        onFxThread(() -> view.getEngine()
                .executeScript("var el = document.getElementById('src'); el.focus(); if (el.select) { el.select(); }"));
        Thread.sleep(50);
    }

    private static void performClipboardAction(WebView view, Region region, ClipboardAction action, Trigger trigger)
            throws Exception {
        double x = region == Region.SOURCE ? SOURCE_X : TARGET_X;
        if (trigger == Trigger.KEYBOARD) {
            invokeShortcut(action.keyCode);
        } else {
            invokeContextMenuItem(view, x, TEXT_Y, action.menuText);
        }
    }

    private static void focusSource(WebView view) throws Exception {
        focusElement(view, "src", SOURCE_X);
    }

    private static void focusTarget(WebView view) throws Exception {
        focusElement(view, "dst", TARGET_X);
    }

    private static void focusElement(WebView view, String id, double x) throws Exception {
        leftClick(view, x, TEXT_Y);
        onFxThread(
                () -> view.getEngine()
                        .executeScript(
                                "var el = document.getElementById('" + id
                                        + "'); el.focus(); if (el.setSelectionRange) { el.setSelectionRange(el.value.length, el.value.length); }"));
        Thread.sleep(50);
    }

    private static String titleFor(WebView view) throws Exception {
        return onFxThread(() -> view.getEngine().getTitle());
    }

    private enum Trigger {
        KEYBOARD,
        CONTEXT_MENU
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
