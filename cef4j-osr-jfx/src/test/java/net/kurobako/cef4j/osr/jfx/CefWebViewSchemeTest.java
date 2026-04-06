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
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.cef4j.CefScriptEngine;
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

    @BeforeAll
    static void setup() throws Exception {
        assumeTrue(
                System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null,
                "Requires a display server; run under xvfb-run");

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
        CefSettings.Mutable settings = new CefSettings.Mutable();
        CefWebView.setup(settings, appHandler);
        CefGlobals.registerSchemeHandlerFactory("classpath", null, new UrlSchemeHandlerFactory());

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

        CefScriptEngine eng = engineFuture.get(15, TimeUnit.SECONDS);
        // evaluate() returns JSON-stringified values, so strings are quoted
        String bodyText = eng.evaluate("document.body.textContent.trim()").get(5, TimeUnit.SECONDS);
        assertThat(bodyText).isEqualTo("\"webview scheme handler works\"");
    }
}
