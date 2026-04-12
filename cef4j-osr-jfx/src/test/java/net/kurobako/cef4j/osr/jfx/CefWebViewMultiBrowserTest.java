package net.kurobako.cef4j.osr.jfx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefScriptEngine;
import net.kurobako.cef4j.OS;
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

    private static volatile boolean fxStarted;

    @BeforeAll
    static void setup() throws Exception {
        assumeTrue(!java.awt.GraphicsEnvironment.isHeadless(), "Requires a display (headless environment detected)");

        // On macOS with -XstartOnFirstThread, cef_initialize() must complete before
        // Platform.startup() claims the AppKit main thread, otherwise it deadlocks.
        // CefWebView.initialise() can't be called yet (CefWebView extends Node, triggering
        // Node.<clinit> before the toolkit is ready), so pre-init CEF directly.
        if (net.kurobako.cef4j.OS.isMacOS()) {
            net.kurobako.cef4j.SystemBootstrap.load();
            CefSettings.Mutable pre = new CefSettings.Mutable();
            pre.windowlessRenderingEnabled = 1;
            pre.externalMessagePump = 1;
            pre.multiThreadedMessageLoop = 0;
            pre.noSandbox = 1;
            Cef.INSTANCE.initialise(pre, java.util.List.of("--no-sandbox"));
        }

        // Start JavaFX before CefWebView.initialise() so that loading CefWebView.class (which
        // extends Node) does not trigger Node.<clinit> before the toolkit is ready.
        CountDownLatch fxLatch = new CountDownLatch(1);
        try {
            Platform.startup(fxLatch::countDown);
        } catch (IllegalStateException alreadyRunning) {
            fxLatch.countDown();
        } catch (RuntimeException | Error e) {
            assumeTrue(false, "JavaFX not available in this environment: " + e.getMessage());
            return;
        }
        assumeTrue(fxLatch.await(10, TimeUnit.SECONDS), "Timed out starting JavaFX");
        fxStarted = true;

        CefSettings.Mutable settings = new CefSettings.Mutable();
        CefWebView.initialise(settings);
    }

    @AfterAll
    static void cleanup() {
        if (!fxStarted) return;
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
        return pumpGet(ready, 10_000);
    }

    private static <T> T pumpGet(CompletableFuture<T> future, long timeoutMs) throws Exception {
        if (OS.isMacOS()) {
            long deadline = System.currentTimeMillis() + timeoutMs;
            while (!future.isDone() && System.currentTimeMillis() < deadline) {
                Cef.INSTANCE.doMessageLoopWork();
                Thread.sleep(5);
            }
            if (!future.isDone()) throw new TimeoutException("Timed out after " + timeoutMs + "ms");
            return future.get();
        }
        return future.get(timeoutMs, TimeUnit.MILLISECONDS);
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
            String result = pumpGet(eng.evaluate(String.valueOf(i + 10)), 5_000);
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

        String rA = pumpGet(engA.evaluate("1 + 2"), 5_000);
        assertThat(rA).isEqualTo("3");

        String rB = pumpGet(engB.evaluate("10 + 20"), 5_000);
        assertThat(rB).isEqualTo("30");
    }

    /** A single fresh CefWebView should always be able to eval. */
    @Test
    void singleBrowserEval() throws Exception {
        CefScriptEngine eng = createAndLoad("single");
        String result = pumpGet(eng.evaluate("42"), 5_000);
        assertThat(result).isEqualTo("42");
    }
}
