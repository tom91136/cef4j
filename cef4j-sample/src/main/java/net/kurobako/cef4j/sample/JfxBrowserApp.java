package net.kurobako.cef4j.sample;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import net.kurobako.cef4j.CefApp;
import net.kurobako.cef4j.CefBrowserOsr;
import net.kurobako.cef4j.CefClient;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.osr.jfx.CefPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Sample JavaFX browser using {@link CefPane}. */
public final class JfxBrowserApp {

    private static final Logger log = LoggerFactory.getLogger(JfxBrowserApp.class);

    static CefApp cefApp;
    static final AtomicReference<CefBrowserOsr> browserRef = new AtomicReference<>();
    static volatile boolean shutdownRequested;
    static final CountDownLatch uiReady = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        log.info("cef4j JavaFX Browser starting");

        SystemBootstrap.load();

        Path cacheDir = Files.createTempDirectory("cef4j-jfx-");
        cacheDir.toFile().deleteOnExit();

        cefApp = CefApp.getInstance(
                cacheDir.toAbsolutePath().toString(), null, true, null, new String[] {"--ozone-platform=x11"});
        cefApp.initialize();

        // Launch JavaFX on a daemon thread — main thread stays as CEF UI thread.
        Thread jfxThread = new Thread(() -> Application.launch(JfxApp.class, args));
        jfxThread.setDaemon(true);
        jfxThread.start();

        // Wait for the UI to create the browser
        uiReady.await();

        CefBrowserOsr browser = browserRef.get();
        if (browser != null) {
            browser.createImmediately();
            browser.setFocus(true);
        }

        installSigintHandler(Thread.currentThread());

        // Auto-exit for headless testing: -Dcef4j.exit.after=<millis>
        String exitAfter = System.getProperty("cef4j.exit.after");
        if (exitAfter != null) {
            long delay = Long.parseLong(exitAfter);
            log.info("Auto-exit scheduled in {}ms", delay);
            Executors.newSingleThreadScheduledExecutor(r -> {
                        Thread t = new Thread(r, "cef4j-exit-timer");
                        t.setDaemon(true);
                        return t;
                    })
                    .schedule(() -> shutdownRequested = true, delay, TimeUnit.MILLISECONDS);
        }

        try {
            while (!shutdownRequested && cefApp.getState() == CefApp.State.INITIALIZED) {
                cefApp.doMessageLoopWork();
                Thread.sleep(8);
            }
        } catch (InterruptedException ignored) {
        }

        log.info("Shutting down");
        if (browser != null) browser.close(true);
        cefApp.dispose();
        log.info("Exiting");
        System.exit(0);
    }

    /** Separate Application subclass so the main class doesn't extend Application (avoids JavaFX module check). */
    public static class JfxApp extends Application {
        @Override
        public void start(Stage stage) {
            stage.setTitle("cef4j Browser (JavaFX)");
            stage.setWidth(1280);
            stage.setHeight(800);

            TextField urlBar = new TextField("https://3dtransforms.desandro.com/");
            urlBar.setOnAction(e -> {
                CefBrowserOsr b = browserRef.get();
                if (b != null) b.loadURL(urlBar.getText().trim());
            });

            Button backBtn = new Button("\u25C0");
            Button fwdBtn = new Button("\u25B6");
            Button reloadBtn = new Button("\u21BB");
            backBtn.setOnAction(e -> {
                CefBrowserOsr b = browserRef.get();
                if (b != null) b.goBack();
            });
            fwdBtn.setOnAction(e -> {
                CefBrowserOsr b = browserRef.get();
                if (b != null) b.goForward();
            });
            reloadBtn.setOnAction(e -> {
                CefBrowserOsr b = browserRef.get();
                if (b != null) b.reload();
            });

            HBox navBar = new HBox(4, backBtn, fwdBtn, reloadBtn, urlBar);
            HBox.setHgrow(urlBar, Priority.ALWAYS);
            navBar.setStyle("-fx-padding: 4;");

            Label statusLabel = new Label(" ");
            ProgressBar progressBar = new ProgressBar(0);
            progressBar.setPrefWidth(120);
            progressBar.setVisible(false);
            BorderPane statusBar = new BorderPane();
            statusBar.setCenter(statusLabel);
            statusBar.setRight(progressBar);
            statusBar.setStyle("-fx-padding: 2 6;");

            CefPane surface = new CefPane();

            BorderPane root = new BorderPane();
            root.setTop(navBar);
            root.setCenter(surface);
            root.setBottom(statusBar);

            stage.setScene(new Scene(root));
            stage.setOnCloseRequest(e -> shutdownRequested = true);
            stage.show();

            CefClient client = cefApp.createClient();
            client.addLifeSpanHandler(new CefLifeSpanHandler() {
                        @Override
                        public void onAfterCreated(long b) {
                            Platform.runLater(() -> surface.requestFocus());
                        }
                    })
                    .addLoadHandler(new CefLoadHandler() {
                        @Override
                        public void onLoadingStateChange(
                                long b, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                            Platform.runLater(() -> {
                                backBtn.setDisable(!canGoBack);
                                fwdBtn.setDisable(!canGoForward);
                                if (!isLoading) {
                                    progressBar.setVisible(false);
                                    progressBar.setProgress(0);
                                }
                            });
                        }
                    })
                    .addDisplayHandler(new CefDisplayHandler() {
                        @Override
                        public void onTitleChange(long b, String title) {
                            Platform.runLater(() -> stage.setTitle(title + " - cef4j (JavaFX)"));
                        }

                        @Override
                        public void onAddressChange(long b, long f, String url) {
                            Platform.runLater(() -> urlBar.setText(url));
                        }

                        @Override
                        public void onStatusMessage(long b, String value) {
                            Platform.runLater(
                                    () -> statusLabel.setText(value != null && !value.isEmpty() ? value : " "));
                        }

                        @Override
                        public void onLoadingProgressChange(long b, double progress) {
                            Platform.runLater(() -> {
                                if (progress >= 0 && progress < 1.0) {
                                    progressBar.setVisible(true);
                                    progressBar.setProgress(progress);
                                } else {
                                    progressBar.setVisible(false);
                                }
                            });
                        }

                        @Override
                        public boolean onCursorChange(long b, long cursor, CefCursorType type, long customCursorInfo) {
                            javafx.scene.Cursor jfxCursor = surface.mapCursor(type);
                            Platform.runLater(() -> surface.setCursor(jfxCursor));
                            return true;
                        }
                    });

            CefBrowserOsr b = surface.createBrowser(client, urlBar.getText().trim(), 60);
            browserRef.set(b);
            uiReady.countDown();
        }
    }

    private static void installSigintHandler(Thread threadToInterrupt) {
        try {
            Class<?> signalClass = Class.forName("sun.misc.Signal");
            Class<?> handlerClass = Class.forName("sun.misc.SignalHandler");
            Object sigInt = signalClass.getConstructor(String.class).newInstance("INT");
            Object handler = java.lang.reflect.Proxy.newProxyInstance(
                    handlerClass.getClassLoader(), new Class<?>[] {handlerClass}, (proxy, method, margs) -> {
                        if ("handle".equals(method.getName())) {
                            shutdownRequested = true;
                            threadToInterrupt.interrupt();
                        }
                        return null;
                    });
            signalClass.getMethod("handle", signalClass, handlerClass).invoke(null, sigInt, handler);
        } catch (Exception e) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                shutdownRequested = true;
                threadToInterrupt.interrupt();
            }));
        }
    }
}
