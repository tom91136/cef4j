package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import javafx.concurrent.Worker;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class WebViewRuntimeSingleWindowClipboardCompatTest extends WebViewRuntimeCompatTestBase {
    private static final int SOURCE_X = 120;
    private static final int SOURCE_BASE_Y = 80;
    private static final int SOURCE_ROW_STEP = 76;
    private static final int SINK_X = 640;
    private static final int SINK_Y = 180;
    private static final String[] TOKENS = {
        "ALPHA_ONE", "BRAVO_TWO", "CHARLIE_THREE", "DELTA_FOUR", "ECHO_FIVE", "FOXTROT_SIX"
    };
    private static final String[] ROWS = {
        "ALPHA_ONE unique clipboard phrase first",
        "BRAVO_TWO unique clipboard phrase second",
        "CHARLIE_THREE unique clipboard phrase third",
        "DELTA_FOUR unique clipboard phrase fourth",
        "ECHO_FIVE unique clipboard phrase fifth",
        "FOXTROT_SIX unique clipboard phrase sixth"
    };
    private static final String PAGE_HTML = "<html><body style='margin:0;background:#f3f4f6'>"
            + "<iframe id='sourceFrame' style='position:absolute;left:40px;top:40px;width:500px;height:500px;border:2px solid #111'></iframe>"
            + "<iframe id='auxFrame' style='position:absolute;left:560px;top:40px;width:220px;height:60px;border:1px dashed #444;background:#fff'></iframe>"
            + "<textarea id='sink' style='position:absolute;left:560px;top:120px;width:220px;height:300px;font-size:14px'></textarea>"
            + "<script>(function(){"
            + "  var d = document.getElementById('sourceFrame').contentWindow.document;"
            + "  d.open();"
            + "  d.write('<!doctype html><html><body style=\"margin:0;font-family:sans-serif\">"
            + "<textarea id=\"t1\" style=\"position:absolute;left:20px;top:20px;width:440px;height:52px;font-size:22px\">"
            + ROWS[0]
            + "</textarea>"
            + "<textarea id=\"t2\" style=\"position:absolute;left:20px;top:96px;width:440px;height:52px;font-size:22px\">"
            + ROWS[1]
            + "</textarea>"
            + "<textarea id=\"t3\" style=\"position:absolute;left:20px;top:172px;width:440px;height:52px;font-size:22px\">"
            + ROWS[2]
            + "</textarea>"
            + "<textarea id=\"t4\" style=\"position:absolute;left:20px;top:248px;width:440px;height:52px;font-size:22px\">"
            + ROWS[3]
            + "</textarea>"
            + "<textarea id=\"t5\" style=\"position:absolute;left:20px;top:324px;width:440px;height:52px;font-size:22px\">"
            + ROWS[4]
            + "</textarea>"
            + "<textarea id=\"t6\" style=\"position:absolute;left:20px;top:400px;width:440px;height:52px;font-size:22px\">"
            + ROWS[5]
            + "</textarea>"
            + "</body></html>');"
            + "  d.close();"
            + "  var a = document.getElementById('auxFrame').contentWindow.document;"
            + "  a.open();"
            + "  a.write('<!doctype html><html><body style=\"margin:0;font:12px sans-serif;padding:6px\">aux frame marker</body></html>');"
            + "  a.close();"
            + "  var sink = document.getElementById('sink');"
            + "  sink.addEventListener('input', function(){ document.title = 'v|' + sink.value; });"
            + "  document.title = 'ready';"
            + "})();</script>"
            + "</body></html>";

    @Test
    @Timeout(90)
    void copyPasteInSingleWindowStaysInSyncAcrossRows() throws Exception {
        WebView view = createAttachedWebView();
        loadPage(view);

        String previous = null;
        for (int i = 0; i < ROWS.length; i++) {
            String seed = "seed-" + (i + 1);
            setClipboardText(seed);
            clearSink(view);

            leftClick(view, SOURCE_X, sourceY(i));
            invokeShortcut(view, KeyCode.A);
            invokeShortcut(view, KeyCode.C);

            String copied = getClipboardText();
            assertThat(copied)
                    .as("row %s: copy should update clipboard", i + 1)
                    .isNotBlank()
                    .isNotEqualTo(seed)
                    .contains(TOKENS[i]);
            if (previous != null) {
                assertThat(copied)
                        .as("row %s: copy should not repeat previous row", i + 1)
                        .isNotEqualTo(previous);
            }
            previous = copied;

            leftClick(view, SINK_X, SINK_Y);
            invokeShortcut(view, KeyCode.V);

            String expected = "v|" + copied;
            assertThat(waitUntilOnFx(() -> expected.equals(title(view)), 2_500))
                    .as("row %s: first paste should match copied value", i + 1)
                    .isTrue();
        }
    }

    private static int sourceY(int rowIndex) {
        return SOURCE_BASE_Y + (rowIndex * SOURCE_ROW_STEP);
    }

    private static void loadPage(WebView view) throws Exception {
        onFxThread(() -> view.getEngine().loadContent(PAGE_HTML));
        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 20_000))
                .as("page load should complete")
                .isTrue();
        assertThat(waitUntilOnFx(() -> "ready".equals(title(view)), 5_000))
                .as("page should reach ready state")
                .isTrue();
    }

    private static void clearSink(WebView view) throws Exception {
        onFxThread(() -> view.getEngine()
                .executeScript("var sink = document.getElementById('sink');"
                        + "if (sink) { sink.value = ''; sink.focus(); document.title = 'v|'; }"));
        assertThat(waitUntilOnFx(() -> "v|".equals(title(view)), 2_000)).isTrue();
    }
}
