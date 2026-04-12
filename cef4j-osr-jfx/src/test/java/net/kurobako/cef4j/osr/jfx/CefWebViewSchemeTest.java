package net.kurobako.cef4j.osr.jfx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.ArrayList;
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
import net.kurobako.cef4j.UrlSchemeHandlerFactory;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefGlobals;
import net.kurobako.cef4j.gen.CefSchemeOptions;
import net.kurobako.cef4j.gen.CefSchemeRegistrar;
import net.kurobako.cef4j.gen.CefSettings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Integration test for the CEF classpath: scheme handler through {@link CefWebView}. Verifies that CefWebView can load
 * pages via the {@code classpath:} URL scheme using the multi-threaded message loop.
 */
@Timeout(30)
class CefWebViewSchemeTest {

    private static volatile boolean fxStarted;

    @BeforeAll
    @Timeout(30)
    static void setup() throws Exception {
        assumeTrue(!java.awt.GraphicsEnvironment.isHeadless(), "Requires a display (headless environment detected)");

        // Register classpath: URL handler
        try {
            URL.setURLStreamHandlerFactory(protocol -> {
                if ("classpath".equals(protocol)) {
                    return new URLStreamHandler() {
                        @Override
                        protected URLConnection openConnection(URL u) {
                            return new URLConnection(u) {
                                private InputStream stream;

                                @Override
                                public void connect() throws IOException {
                                    String path = u.getPath();
                                    stream = CefWebViewSchemeTest.class.getResourceAsStream(path);
                                    if (stream == null) {
                                        throw new IOException("Resource not found: " + path);
                                    }
                                    connected = true;
                                }

                                @Override
                                public InputStream getInputStream() throws IOException {
                                    if (!connected) connect();
                                    return stream;
                                }

                                @Override
                                public String getContentType() {
                                    String path = url.getPath().toLowerCase();
                                    if (path.endsWith(".html")) return "text/html";
                                    if (path.endsWith(".js")) return "text/javascript";
                                    if (path.endsWith(".css")) return "text/css";
                                    return "application/octet-stream";
                                }
                            };
                        }
                    };
                }
                return null;
            });
        } catch (Error alreadySet) {
            // Factory already registered
        }

        CefApp appHandler = new CefApp() {
            @Override
            public void onRegisterCustomSchemes(CefSchemeRegistrar registrar) {
                if (registrar != null) {
                    int options = (int) (CefSchemeOptions.Kind.SECURE.value
                            | CefSchemeOptions.Kind.CORS_ENABLED.value
                            | CefSchemeOptions.Kind.FETCH_ENABLED.value);
                    registrar.addCustomScheme("classpath", options);
                }
            }
        };

        // On macOS with -XstartOnFirstThread, cef_initialize() must complete before
        // Platform.startup() claims the AppKit main thread, otherwise it deadlocks.
        if (net.kurobako.cef4j.OS.isMacOS()) {
            net.kurobako.cef4j.SystemBootstrap.load();
            CefSettings.Mutable pre = new CefSettings.Mutable();
            pre.windowlessRenderingEnabled = 1;
            pre.externalMessagePump = 1;
            pre.multiThreadedMessageLoop = 0;
            pre.noSandbox = 1;
            Cef.INSTANCE.initialise(pre, java.util.List.of("--no-sandbox"), appHandler);
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
        CefWebView.initialise(settings, appHandler);
        CefGlobals.registerSchemeHandlerFactory("classpath", null, new UrlSchemeHandlerFactory());
    }

    @AfterAll
    static void cleanup() {
        if (!fxStarted) return;
        Platform.runLater(() -> {
            for (javafx.stage.Window w : new ArrayList<>(javafx.stage.Window.getWindows())) {
                if (w.isShowing()) w.hide();
            }
        });
    }

    @Test
    void classpathUrl_loadsInCefWebView() throws Exception {
        CompletableFuture<CefScriptEngine> engineFuture = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                CefWebView view = new CefWebView();
                CefWebEngine engine = view.getEngine();
                engine.getLoadWorker().stateProperty().addListener((obs, old, state) -> {
                    if (state == State.SUCCEEDED && !engineFuture.isDone()) {
                        engineFuture.complete(engine.getScriptEngine());
                    }
                });
                Stage stage = new Stage();
                stage.setScene(new Scene(new StackPane(view), 200, 200));
                stage.show();
                engine.load("classpath:///cef4j-webview-scheme-test.html");
            } catch (Throwable t) {
                engineFuture.completeExceptionally(t);
            }
        });

        CefScriptEngine eng = pumpGet(engineFuture, 15_000);
        // evaluate() returns JSON-stringified values, so strings are quoted
        String bodyText = pumpGet(eng.evaluate("document.body.textContent.trim()"), 5_000);
        assertThat(bodyText).isEqualTo("\"webview scheme handler works\"");
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
}
