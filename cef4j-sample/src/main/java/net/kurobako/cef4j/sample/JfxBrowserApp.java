package net.kurobako.cef4j.sample;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
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
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.NativePointer;
import net.kurobako.cef4j.osr.jfx.CefPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Sample JavaFX browser using {@link CefPane}. */
public final class JfxBrowserApp {

    private static final Logger log = LoggerFactory.getLogger(JfxBrowserApp.class);

    static final AtomicReference<CefBrowserOsr> browserRef = new AtomicReference<>();
    static volatile boolean shutdownRequested;
    static final CountDownLatch uiReady = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        log.info("cef4j JavaFX Browser starting");

        SystemBootstrap.load();

        Path cacheDir = Files.createTempDirectory("cef4j-jfx-");
        cacheDir.toFile().deleteOnExit();

        CefApp.INSTANCE
                .cachePath(cacheDir.toAbsolutePath().toString())
                .extraArgs("--ozone-platform=x11")
                .initialize();

        // Launch JavaFX on a daemon thread - main thread stays as CEF UI thread.
        Thread jfxThread = new Thread(() -> Application.launch(JfxApp.class, args));
        jfxThread.setDaemon(true);
        jfxThread.start();

        // Wait for the UI to create the browser
        uiReady.await();

        CefBrowserOsr browser = browserRef.get();
        if (browser != null) {
            browser.createImmediately();
            var host = browser.getHost();
            if (host != null) host.setFocus(true);
        }

        Thread mainThread = Thread.currentThread();
        SigintHelper.install(() -> {
            shutdownRequested = true;
            mainThread.interrupt();
        });

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
            while (!shutdownRequested && CefApp.INSTANCE.getState() == CefApp.State.INITIALIZED) {
                CefApp.INSTANCE.doMessageLoopWork();
                Thread.sleep(8);
            }
        } catch (InterruptedException ignored) {
        }

        log.info("Shutting down");
        if (browser != null) browser.close(true);
        CefApp.INSTANCE.dispose();
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
                CefBrowserOsr osr = browserRef.get();
                var b = osr != null ? osr.getBrowser() : null;
                if (b != null) b.goBack();
            });
            fwdBtn.setOnAction(e -> {
                CefBrowserOsr osr = browserRef.get();
                var b = osr != null ? osr.getBrowser() : null;
                if (b != null) b.goForward();
            });
            reloadBtn.setOnAction(e -> {
                CefBrowserOsr osr = browserRef.get();
                var b = osr != null ? osr.getBrowser() : null;
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

            CefRenderHandler renderHandler = surface.createRenderHandler();
            CefLoadHandler scrollbarHandler = surface.createScrollbarLoadHandler();

            CefClient client = new CefClient() {
                @Override
                public Optional<CefRenderHandler> getRenderHandler() {
                    return Optional.of(renderHandler);
                }

                @Override
                public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                    return Optional.of(new CefLifeSpanHandler() {
                        @Override
                        public void onAfterCreated(CefBrowser b) {
                            Platform.runLater(() -> surface.requestFocus());
                        }
                    });
                }

                @Override
                public Optional<CefLoadHandler> getLoadHandler() {
                    return Optional.of(new CefLoadHandler() {
                        @Override
                        public void onLoadingStateChange(
                                CefBrowser b, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                            Platform.runLater(() -> {
                                backBtn.setDisable(!canGoBack);
                                fwdBtn.setDisable(!canGoForward);
                                if (!isLoading) {
                                    progressBar.setVisible(false);
                                    progressBar.setProgress(0);
                                }
                            });
                        }

                        @Override
                        public void onLoadEnd(CefBrowser browser, CefFrame frame, int httpStatusCode) {
                            scrollbarHandler.onLoadEnd(browser, frame, httpStatusCode);
                        }
                    });
                }

                @Override
                public Optional<CefDisplayHandler> getDisplayHandler() {
                    return Optional.of(new CefDisplayHandler() {
                        @Override
                        public void onTitleChange(CefBrowser b, String title) {
                            Platform.runLater(() -> stage.setTitle(title + " - cef4j (JavaFX)"));
                        }

                        @Override
                        public void onAddressChange(CefBrowser b, CefFrame f, String url) {
                            Platform.runLater(() -> urlBar.setText(url));
                        }

                        @Override
                        public void onStatusMessage(CefBrowser b, String value) {
                            Platform.runLater(
                                    () -> statusLabel.setText(value != null && !value.isEmpty() ? value : " "));
                        }

                        @Override
                        public void onLoadingProgressChange(CefBrowser b, double progress) {
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
                        public boolean onCursorChange(
                                CefBrowser b, long cursor, CefCursorType type, NativePointer customCursorInfo) {
                            javafx.scene.Cursor jfxCursor = surface.mapCursor(type);
                            Platform.runLater(() -> surface.setCursor(jfxCursor));
                            return true;
                        }
                    });
                }
            };

            CefBrowserOsr b =
                    CefApp.INSTANCE.createBrowser(client, urlBar.getText().trim(), 60);
            surface.setBrowser(b);
            browserRef.set(b);
            uiReady.countDown();
        }
    }
}
