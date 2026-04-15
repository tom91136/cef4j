package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
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
import net.kurobako.cef4j.UrlSchemeHandlerFactory;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefGlobals;
import net.kurobako.cef4j.gen.CefSchemeOptions;
import net.kurobako.cef4j.gen.CefSchemeRegistrar;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(30)
class CefWebViewSchemeTest {

    @BeforeAll
    static void setup(@TempDir Path tempDir) throws Exception {
        assumeDisplayServer();

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

        Cef.INSTANCE.addAppHandler(new CefApp() {
            @Override
            public void onRegisterCustomSchemes(CefSchemeRegistrar registrar) {
                if (registrar != null) {
                    int options = (int) (CefSchemeOptions.Kind.SECURE.value
                            | CefSchemeOptions.Kind.CORS_ENABLED.value
                            | CefSchemeOptions.Kind.FETCH_ENABLED.value);
                    registrar.addCustomScheme("classpath", options);
                }
            }
        });

        Cef.LaunchArgs launch = Cef.osrLaunchArgs();
        launch.settings().cachePath = Files.createDirectories(tempDir.resolve("cef-cache"))
                .toAbsolutePath()
                .toString();
        Cef.INSTANCE.initialise(launch.settings(), launch.args());

        startJavaFx();

        CefGlobals.registerSchemeHandlerFactory("classpath", null, new UrlSchemeHandlerFactory());
    }

    @AfterAll
    static void cleanup() throws Exception {
        closeAllWindows();
        Cef.INSTANCE.terminate();
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
        String bodyText = eng.evaluate("document.body.textContent.trim()").get(5, TimeUnit.SECONDS);
        assertThat(bodyText).isEqualTo("\"webview scheme handler works\"");
    }
}
