package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefScriptEngine;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

@Timeout(30)
class CefWebViewV117PlusMultiBrowserTest {

    @BeforeAll
    static void setup(@TempDir(cleanup = CleanupMode.NEVER) Path tempDir) throws Exception {
        assumeDisplayServer();

        Cef.LaunchArgs launch = Cef.osrLaunchArgs();
        launch.settings().cachePath = Files.createDirectories(tempDir.resolve("cef-cache"))
                .toAbsolutePath()
                .toString();
        java.util.List<String> args = new java.util.ArrayList<>(launch.args());
        args.addAll(net.kurobako.cef4j.test.CefTestLaunch.extraArgs());
        Cef.INSTANCE.initialise(launch.settings(), args);
        startJavaFx();
    }

    @AfterAll
    static void cleanup() throws Exception {
        closeAllWindows();
        Cef.INSTANCE.terminate();
    }

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

    @Test
    void sequentialBrowserCreationAndEval() throws Exception {
        for (int i = 0; i < 3; i++) {
            CefScriptEngine eng = createAndLoad("browser-" + i);
            String result = eng.evaluate(String.valueOf(i + 10)).get(5, TimeUnit.SECONDS);
            assertThat(result).as("eval on browser %d", i).isEqualTo(String.valueOf(i + 10));
        }
    }

    @Test
    void twoPreCreatedBrowsers() throws Exception {
        CefScriptEngine engA = createAndLoad("pre-A");
        CefScriptEngine engB = createAndLoad("pre-B");

        String rA = engA.evaluate("1 + 2").get(5, TimeUnit.SECONDS);
        assertThat(rA).isEqualTo("3");

        String rB = engB.evaluate("10 + 20").get(5, TimeUnit.SECONDS);
        assertThat(rB).isEqualTo("30");
    }

    @Test
    void singleBrowserEval() throws Exception {
        CefScriptEngine eng = createAndLoad("single");
        String result = eng.evaluate("42").get(5, TimeUnit.SECONDS);
        assertThat(result).isEqualTo("42");
    }
}
