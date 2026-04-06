package net.kurobako.cef4j.osr.jfx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.cef4j.CefScriptEngine;
import net.kurobako.cef4j.gen.CefSettings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Tests that multiple {@link CefWebView} instances can coexist and independently evaluate JavaScript.
 *
 * <p>CefWebView uses {@code multiThreadedMessageLoop=1} where CEF manages its own message loop thread. This test
 * verifies that creating multiple CefWebView instances sequentially does not break IPC eval.
 */
@Timeout(30)
class CefWebViewMultiBrowserTest {

    @BeforeAll
    static void setup() throws Exception {
        assumeTrue(
                System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null,
                "Requires a display server; run under xvfb-run");

        CefSettings.Mutable settings = new CefSettings.Mutable();
        CefWebView.setup(settings);

        CountDownLatch fxLatch = new CountDownLatch(1);
        try {
            Platform.startup(fxLatch::countDown);
        } catch (IllegalStateException alreadyRunning) {
            fxLatch.countDown();
        }
        assertThat(fxLatch.await(10, TimeUnit.SECONDS)).as("JavaFX started").isTrue();
    }

    @AfterAll
    static void cleanup() {
        Platform.runLater(() -> {
            for (javafx.stage.Window w : new java.util.ArrayList<>(javafx.stage.Window.getWindows())) {
                if (w.isShowing()) w.hide();
            }
        });
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    /** Creates a CefWebView in a Stage, loads content, waits for SUCCEEDED, returns the script engine. */
    private static CefScriptEngine createAndLoad(String bodyContent) throws Exception {
        CompletableFuture<CefScriptEngine> ready = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                CefWebView view = new CefWebView();
                CefWebEngine engine = view.getEngine();
                engine.getLoadWorker().stateProperty().addListener((obs, old, state) -> {
                    if (state == State.SUCCEEDED && !ready.isDone()) {
                        ready.complete(engine.getScriptEngine());
                    }
                });
                Stage stage = new Stage();
                stage.setScene(new Scene(new StackPane(view), 200, 200));
                stage.show();
                engine.loadContent("<!doctype html><html><body>" + bodyContent + "</body></html>");
            } catch (Throwable t) {
                ready.completeExceptionally(t);
            }
        });
        return ready.get(10, TimeUnit.SECONDS);
    }

    // -----------------------------------------------------------------------
    // Tests
    // -----------------------------------------------------------------------

    /**
     * Creates 3 CefWebViews sequentially, loading a page and evaluating JS on each. Each browser is created from the FX
     * thread, the page load is awaited, and then eval is called from the test thread. This reproduces the pattern used
     * in integration tests that create a new browser per-test.
     */
    @Test
    void sequentialBrowserCreationAndEval() throws Exception {
        for (int i = 0; i < 3; i++) {
            CefScriptEngine eng = createAndLoad("browser-" + i);
            String result = eng.evaluate(String.valueOf(i + 10)).get(5, TimeUnit.SECONDS);
            assertThat(result).as("eval on browser %d", i).isEqualTo(String.valueOf(i + 10));
        }
    }

    /**
     * Creates two CefWebViews upfront (both loaded), then evals on both from the test thread. Both browsers are fully
     * ready before any eval happens.
     */
    @Test
    void twoPreCreatedBrowsers() throws Exception {
        CefScriptEngine engA = createAndLoad("pre-A");
        CefScriptEngine engB = createAndLoad("pre-B");

        String rA = engA.evaluate("1 + 2").get(5, TimeUnit.SECONDS);
        assertThat(rA).isEqualTo("3");

        String rB = engB.evaluate("10 + 20").get(5, TimeUnit.SECONDS);
        assertThat(rB).isEqualTo("30");
    }

    /** A single fresh CefWebView should always be able to eval. */
    @Test
    void singleBrowserEval() throws Exception {
        CefScriptEngine eng = createAndLoad("single");
        String result = eng.evaluate("42").get(5, TimeUnit.SECONDS);
        assertThat(result).isEqualTo("42");
    }
}
