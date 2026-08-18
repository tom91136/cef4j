package net.kurobako.cef4j.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.osr.jfx.CefWebView;

public final class JfxBrowserApp {

    private static Path createCacheDir() throws IOException {
        Path cacheDir = Files.createTempDirectory("cef4j-jfx-sample-");
        cacheDir.toFile().deleteOnExit();
        return cacheDir;
    }

    public static void main(String[] args) {
        SigintHelper.install(() -> {
            if (Platform.isFxApplicationThread()) {
                Platform.exit();
            } else {
                Platform.runLater(Platform::exit);
            }
        });
        Application.launch(JfxApp.class, args);
        CefWebView.terminate();
        // halt() instead of normal return: on macOS, JVM teardown fires CEF's
        // CFRunLoop observers after the message loop has stopped, causing a crash.
        Runtime.getRuntime().halt(0);
    }

    public static class JfxApp extends Application {
        private static final String DEFAULT_URL = "https://codepen.io/rcyou/pen/QEObEZ";

        @Override
        public void start(Stage stage) throws IOException {
            // JavaFX must establish its AppKit integration before CEF on macOS.
            CefSettings.Mutable settings = new CefSettings.Mutable();
            settings.cachePath = createCacheDir().toAbsolutePath().toString();
            CefWebView.initialise(settings, List.of(), Optional.empty());

            stage.setTitle("cef4j Browser (JavaFX)");
            stage.setWidth(1280);
            stage.setHeight(800);

            TabPane tabPane = new TabPane();

            // "+" tab trick - an empty non-closable tab that creates a new tab when selected
            Tab newTabTab = new Tab("+");
            newTabTab.setClosable(false);
            tabPane.getTabs().add(newTabTab);

            BorderPane root = new BorderPane();
            root.setCenter(tabPane);

            tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
                if (newTab == newTabTab) {
                    BrowserTab created = createTab(tabPane, stage, DEFAULT_URL);
                    tabPane.getSelectionModel().select(created);
                    return;
                }
                if (newTab instanceof BrowserTab) {
                    ((BrowserTab) newTab).updateStageTitle();
                } else {
                    stage.setTitle("cef4j Browser (JavaFX)");
                }
            });
            tabPane.getTabs().addListener((ListChangeListener<? super Tab>) change -> {
                while (change.next()) {
                    if (change.wasRemoved()) {
                        for (Tab removed : change.getRemoved()) {
                            if (removed instanceof BrowserTab) {
                                ((BrowserTab) removed).release();
                            }
                        }
                    }
                }
                // Only the "+" tab remains - exit
                if (tabPane.getTabs().size() <= 1) {
                    Platform.exit();
                    return;
                }
                // Ensure "+" tab is always last
                int plusIdx = tabPane.getTabs().indexOf(newTabTab);
                int lastIdx = tabPane.getTabs().size() - 1;
                if (plusIdx >= 0 && plusIdx != lastIdx) {
                    tabPane.getTabs().remove(newTabTab);
                    tabPane.getTabs().add(newTabTab);
                }
            });

            select(tabPane, createTab(tabPane, stage, DEFAULT_URL));

            stage.setOnCloseRequest(e -> {
                for (Tab tab : tabPane.getTabs()) {
                    if (tab instanceof BrowserTab) {
                        ((BrowserTab) tab).release();
                    }
                }
                Platform.exit();
            });
            Scene scene = new Scene(root);
            // Scene-level shortcuts so they work regardless of focus
            scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, e -> {
                if (!e.isShortcutDown()) return;
                switch (e.getCode()) {
                    case T:
                        BrowserTab created = createTab(tabPane, stage, DEFAULT_URL);
                        tabPane.getSelectionModel().select(created);
                        e.consume();
                        break;
                    case W:
                        Tab current = tabPane.getSelectionModel().getSelectedItem();
                        if (current instanceof BrowserTab) {
                            tabPane.getTabs().remove(current);
                        }
                        e.consume();
                        break;
                    default:
                        break;
                }
            });
            stage.setScene(scene);
            stage.show();
        }

        @Override
        public void stop() {}

        private BrowserTab createTab(TabPane tabPane, Stage stage, String initialUrl) {
            BrowserTab tab = new BrowserTab(tabPane, stage, initialUrl);
            // Insert before the "+" tab (always last)
            int insertAt = Math.max(0, tabPane.getTabs().size() - 1);
            tabPane.getTabs().add(insertAt, tab);
            return tab;
        }

        private static void select(TabPane tabPane, BrowserTab tab) {
            tabPane.getSelectionModel().select(tab);
        }
    }

    private static final class BrowserTab extends Tab {
        private final Stage stage;
        private final CefWebView view;
        private final TextField urlBar = new TextField();
        private final Label statusLabel = new Label(" ");
        private final ProgressBar progressBar = new ProgressBar(0);
        private boolean disposed;

        private BrowserTab(TabPane owner, Stage stage, String initialUrl) {
            this.stage = stage;
            this.view = new CefWebView();
            this.view.setZoom(1.0);

            setText("New Tab");
            setClosable(true);
            setContent(createContent());
            setOnClosed(e -> release());

            Button backBtn = new Button("\u25C0");
            Button fwdBtn = new Button("\u25B6");
            Button reloadBtn = new Button("\u21BB");
            Button zoomOutBtn = new Button("-");
            Button zoomResetBtn = new Button("100%");
            Button zoomInBtn = new Button("+");
            Button devToolsBtn = new Button("\u2699");

            HBox navBar =
                    new HBox(4, backBtn, fwdBtn, reloadBtn, urlBar, zoomOutBtn, zoomResetBtn, zoomInBtn, devToolsBtn);
            HBox.setHgrow(urlBar, Priority.ALWAYS);
            navBar.setStyle("-fx-padding: 4;");

            BorderPane statusBar = new BorderPane();
            statusBar.setLeft(statusLabel);
            statusBar.setRight(progressBar);
            statusBar.setStyle("-fx-padding: 2 6;");

            BorderPane root = (BorderPane) getContent();
            root.setTop(navBar);
            root.setBottom(statusBar);

            progressBar.setPrefWidth(120);
            progressBar.setVisible(false);
            urlBar.setText(initialUrl);

            backBtn.setOnAction(e -> {
                if (view.getEngine().getHistory().getCurrentIndex() > 0) {
                    view.getEngine().getHistory().go(-1);
                }
            });
            fwdBtn.setOnAction(e -> {
                int current = view.getEngine().getHistory().getCurrentIndex();
                int last = view.getEngine().getHistory().getEntries().size() - 1;
                if (current >= 0 && current < last) {
                    view.getEngine().getHistory().go(1);
                }
            });
            reloadBtn.setOnAction(e -> view.getEngine().reload());
            zoomOutBtn.setOnAction(e -> view.setZoom(clampZoom(view.getZoom() / 1.2)));
            zoomResetBtn.setOnAction(e -> view.setZoom(1.0));
            zoomInBtn.setOnAction(e -> view.setZoom(clampZoom(view.getZoom() * 1.2)));
            devToolsBtn.setOnAction(e -> toggleDevTools());
            urlBar.setOnAction(e -> view.getEngine().load(urlBar.getText().trim()));
            view.zoomProperty()
                    .addListener((obs, oldZoom, newZoom) ->
                            zoomResetBtn.setText(Math.round(newZoom.doubleValue() * 100) + "%"));

            // Mouse back/forward buttons
            view.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_PRESSED, e -> {
                if (e.getButton() == MouseButton.BACK) {
                    view.goBack();
                    e.consume();
                } else if (e.getButton() == MouseButton.FORWARD) {
                    view.goForward();
                    e.consume();
                }
            });
            // Browser keyboard shortcuts
            view.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, e -> {
                boolean shortcut = e.isShortcutDown();
                switch (e.getCode()) {
                    case F12:
                        toggleDevTools();
                        e.consume();
                        break;
                    case F5:
                        view.getEngine().reload();
                        e.consume();
                        break;
                    case ESCAPE:
                        view.getEngine().stop();
                        e.consume();
                        break;
                    case LEFT:
                        if (e.isAltDown()) {
                            view.goBack();
                            e.consume();
                        }
                        break;
                    case RIGHT:
                        if (e.isAltDown()) {
                            view.goForward();
                            e.consume();
                        }
                        break;
                    case R:
                        if (shortcut) {
                            view.getEngine().reload();
                            e.consume();
                        }
                        break;
                    case L:
                    case D:
                        if (shortcut) {
                            urlBar.requestFocus();
                            urlBar.selectAll();
                            e.consume();
                        }
                        break;
                    // Ctrl+T and Ctrl+W handled at scene level
                    default:
                        break;
                }
            });

            view.getEngine().setCreatePopupHandler(features -> {
                BrowserTab popupTab = new BrowserTab(owner, stage, "about:blank");
                owner.getTabs().add(popupTab);
                Platform.runLater(() -> owner.getSelectionModel().select(popupTab));
                return popupTab.view.getEngine();
            });
            // JS alert/confirm/prompt - custom handlers override the built-in JavaFX dialogs
            view.getEngine()
                    .setOnAlert(
                            event -> statusLabel.setText("Alert: " + (event.getData() != null ? event.getData() : "")));
            view.getEngine().titleProperty().addListener((obs, oldTitle, newTitle) -> {
                updateTabTitle(newTitle);
                if (isSelected()) {
                    updateStageTitle();
                }
            });
            view.getEngine().locationProperty().addListener((obs, oldLocation, newLocation) -> {
                if (newLocation != null && !newLocation.isEmpty()) {
                    urlBar.setText(newLocation);
                }
                if ((getText() == null || getText().isEmpty() || "New Tab".equals(getText()))
                        && newLocation != null
                        && !newLocation.isEmpty()) {
                    updateTabTitle(newLocation);
                }
                if (isSelected()) {
                    updateStageTitle();
                }
            });

            ChangeListener<Object> historyStateListener = (obs, oldValue, newValue) -> {
                int current = view.getEngine().getHistory().getCurrentIndex();
                int size = view.getEngine().getHistory().getEntries().size();
                backBtn.setDisable(current <= 0);
                fwdBtn.setDisable(current < 0 || current >= size - 1);
            };
            view.getEngine().getHistory().currentIndexProperty().addListener(historyStateListener);
            view.getEngine().getHistory().getEntries().addListener((ListChangeListener<? super Object>)
                    change -> historyStateListener.changed(null, null, null));
            historyStateListener.changed(null, null, null);

            view.getEngine().getLoadWorker().progressProperty().addListener((obs, oldProgress, newProgress) -> {
                double progress = newProgress != null ? newProgress.doubleValue() : -1;
                if (progress >= 0 && progress < 1.0) {
                    progressBar.setVisible(true);
                    progressBar.setProgress(progress);
                } else {
                    progressBar.setVisible(false);
                    progressBar.setProgress(0);
                }
            });
            view.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                if (newState == Worker.State.FAILED) {
                    Throwable error = view.getEngine().getLoadWorker().getException();
                    statusLabel.setText(
                            error != null && error.getMessage() != null ? error.getMessage() : "Load failed");
                } else if (newState == Worker.State.SUCCEEDED || newState == Worker.State.READY) {
                    statusLabel.setText(" ");
                }
            });
            view.getEngine().setOnStatusChanged(event -> {
                String value = event.getData();
                statusLabel.setText(value != null && !value.isEmpty() ? value : " ");
            });

            view.getEngine().load(initialUrl);
        }

        private BorderPane createContent() {
            BorderPane root = new BorderPane();
            root.setCenter(view);
            return root;
        }

        private void updateStageTitle() {
            String title = view.getEngine().getTitle();
            String location = view.getEngine().getLocation();
            stage.setTitle(((title == null || title.isEmpty()) ? location : title) + " - cef4j (JavaFX)");
        }

        private void updateTabTitle(String value) {
            if (value == null || value.isEmpty()) return;
            setText(value.length() > 24 ? value.substring(0, 24) + "\u2026" : value);
        }

        private void toggleDevTools() {
            net.kurobako.cef4j.gen.CefBrowserHost host = view.getBrowserHost();
            if (host == null) return;
            if (host.hasDevTools()) {
                host.closeDevTools();
            } else {
                // DevTools is always Chrome-style windowed - pass defaults to let CEF create a native window
                host.showDevTools(null, null, null, new net.kurobako.cef4j.gen.CefPoint(0, 0));
            }
        }

        private void release() {
            if (disposed) return;
            disposed = true;
            view.release();
        }

        private static double clampZoom(double value) {
            return Math.max(0.25, Math.min(5.0, value));
        }
    }
}
