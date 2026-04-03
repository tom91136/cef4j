package net.kurobako.cef4j.sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.osr.jfx.CefWebView;

public final class JfxBrowserApp {
    public static void main(String[] args) throws IOException {
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = createCacheDir().toAbsolutePath().toString();
        CefWebView.setup(settings);
        SigintHelper.install(() -> {
            if (Platform.isFxApplicationThread()) {
                Platform.exit();
            } else {
                Platform.runLater(Platform::exit);
            }
        });
        Application.launch(JfxApp.class, args);
    }

    public static class JfxApp extends Application {
        private static final String DEFAULT_URL = "https://3dtransforms.desandro.com/";

        @Override
        public void start(Stage stage) throws IOException {
            stage.setTitle("cef4j Browser (JavaFX)");
            stage.setWidth(1280);
            stage.setHeight(800);

            TabPane tabPane = new TabPane();
            Button newTabBtn = new Button("+");
            newTabBtn.setOnAction(e -> select(tabPane, createTab(tabPane, stage, DEFAULT_URL)));

            BorderPane root = new BorderPane();
            root.setCenter(tabPane);
            root.setTop(new HBox(4, newTabBtn));

            tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
                BrowserTab browserTab = newTab instanceof BrowserTab ? (BrowserTab) newTab : null;
                if (browserTab == null) {
                    stage.setTitle("cef4j Browser (JavaFX)");
                } else {
                    browserTab.updateStageTitle();
                }
            });
            tabPane.getTabs().addListener((ListChangeListener<? super Tab>) change -> {
                while (change.next()) {
                    if (change.wasRemoved()) {
                        for (Tab removed : change.getRemoved()) {
                            if (removed instanceof BrowserTab) {
                                ((BrowserTab) removed).dispose();
                            }
                        }
                    }
                }
                if (tabPane.getTabs().isEmpty()) {
                    Platform.exit();
                }
            });

            select(tabPane, createTab(tabPane, stage, DEFAULT_URL));

            stage.setOnCloseRequest(e -> {
                for (Tab tab : tabPane.getTabs()) {
                    if (tab instanceof BrowserTab) {
                        ((BrowserTab) tab).dispose();
                    }
                }
                Platform.exit();
            });
            stage.setScene(new Scene(root));
            stage.show();
        }

        private BrowserTab createTab(TabPane tabPane, Stage stage, String initialUrl) {
            BrowserTab tab = new BrowserTab(tabPane, stage, initialUrl);
            tabPane.getTabs().add(tab);
            return tab;
        }

        private static void select(TabPane tabPane, BrowserTab tab) {
            tabPane.getSelectionModel().select(tab);
        }
    }

    private static final class BrowserTab extends Tab {
        private final TabPane owner;
        private final Stage stage;
        private final CefWebView view;
        private final TextField urlBar = new TextField();
        private final Label statusLabel = new Label(" ");
        private final ProgressBar progressBar = new ProgressBar(0);
        private boolean disposed;

        private BrowserTab(TabPane owner, Stage stage, String initialUrl) {
            this.owner = owner;
            this.stage = stage;
            this.view = new CefWebView();
            this.view.setZoom(1.0);

            setText("New Tab");
            setClosable(true);
            setContent(createContent());
            setOnClosed(e -> dispose());

            Button backBtn = new Button("\u25C0");
            Button fwdBtn = new Button("\u25B6");
            Button reloadBtn = new Button("\u21BB");
            Button zoomOutBtn = new Button("-");
            Button zoomResetBtn = new Button("100%");
            Button zoomInBtn = new Button("+");

            HBox navBar = new HBox(4, backBtn, fwdBtn, reloadBtn, zoomOutBtn, zoomResetBtn, zoomInBtn, urlBar);
            HBox.setHgrow(urlBar, Priority.ALWAYS);
            navBar.setStyle("-fx-padding: 4;");

            BorderPane statusBar = new BorderPane();
            statusBar.setCenter(statusLabel);
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
            urlBar.setOnAction(e -> view.getEngine().load(urlBar.getText().trim()));
            view.zoomProperty()
                    .addListener((obs, oldZoom, newZoom) ->
                            zoomResetBtn.setText(Math.round(newZoom.doubleValue() * 100) + "%"));

            view.getEngine().setCreatePopupHandler(features -> {
                BrowserTab popupTab = new BrowserTab(owner, stage, JfxApp.DEFAULT_URL);
                owner.getTabs().add(popupTab);
                Platform.runLater(() -> owner.getSelectionModel().select(popupTab));
                return popupTab.view.getEngine();
            });
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

        private void dispose() {
            if (disposed) return;
            disposed = true;
            view.dispose();
        }

        private static double clampZoom(double value) {
            return Math.max(0.25, Math.min(5.0, value));
        }
    }

    private static Path createCacheDir() throws IOException {
        Path cacheDir = Files.createTempDirectory("cef4j-jfx-sample-");
        cacheDir.toFile().deleteOnExit();
        return cacheDir;
    }
}
