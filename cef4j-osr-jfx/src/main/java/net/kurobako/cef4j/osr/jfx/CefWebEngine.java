package net.kurobako.cef4j.osr.jfx;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.util.Callback;
import net.kurobako.cef4j.CefScriptEngine;
import org.w3c.dom.Document;

/**
 * JavaFX WebView-compatiable engine facade backed by a {@link CefWebView}.
 *
 * <p>The API intentionally tracks the most common {@code WebEngine} entry points first. Callers that need full CEF
 * control can drop down to the owning {@link CefWebView}.
 */
@SuppressWarnings("unused")
public final class CefWebEngine {
    private final CefWebView view;
    private final CefWebHistory history;
    private final CefLoadWorker loadWorker;
    private final ReadOnlyStringWrapper location = new ReadOnlyStringWrapper(this, "location", "about:blank");
    private final ReadOnlyStringWrapper title = new ReadOnlyStringWrapper(this, "title", "");
    private final ReadOnlyDoubleWrapper loadProgress = new ReadOnlyDoubleWrapper(this, "loadProgress", 0.0);
    private final ReadOnlyBooleanWrapper loading = new ReadOnlyBooleanWrapper(this, "loading", false);
    private final ReadOnlyBooleanWrapper canGoBack = new ReadOnlyBooleanWrapper(this, "canGoBack", false);
    private final ReadOnlyBooleanWrapper canGoForward = new ReadOnlyBooleanWrapper(this, "canGoForward", false);
    private final ReadOnlyObjectWrapper<Document> document = new ReadOnlyObjectWrapper<>(this, "document");
    private final SimpleBooleanProperty javaScriptEnabled = new SimpleBooleanProperty(this, "javaScriptEnabled", true);
    private final SimpleStringProperty userStyleSheetLocation =
            new SimpleStringProperty(this, "userStyleSheetLocation");
    private final SimpleStringProperty userAgent = new SimpleStringProperty(this, "userAgent");
    private final SimpleObjectProperty<File> userDataDirectory = new SimpleObjectProperty<>(this, "userDataDirectory");
    private final ObjectProperty<EventHandler<CefWebEvent<String>>> onAlert =
            new SimpleObjectProperty<>(this, "onAlert");
    private final ObjectProperty<EventHandler<CefWebEvent<String>>> onStatusChanged =
            new SimpleObjectProperty<>(this, "onStatusChanged");
    private final ObjectProperty<EventHandler<CefWebEvent<Rectangle2D>>> onResized =
            new SimpleObjectProperty<>(this, "onResized");
    private final ObjectProperty<EventHandler<CefWebEvent<Boolean>>> onVisibilityChanged =
            new SimpleObjectProperty<>(this, "onVisibilityChanged");
    private final ObjectProperty<Callback<CefPopupFeatures, CefWebEngine>> createPopupHandler =
            new SimpleObjectProperty<>(this, "createPopupHandler");
    private final ObjectProperty<Callback<String, Boolean>> confirmHandler =
            new SimpleObjectProperty<>(this, "confirmHandler");
    private final ObjectProperty<Callback<CefPromptData, String>> promptHandler =
            new SimpleObjectProperty<>(this, "promptHandler");
    private final ObjectProperty<EventHandler<CefWebErrorEvent>> onError = new SimpleObjectProperty<>(this, "onError");
    private volatile boolean suppressNavigationHistory;

    CefWebEngine(CefWebView view) {
        this.view = view;
        this.history = new CefWebHistory(view);
        this.loadWorker = new CefLoadWorker(this);
    }

    public CefWebView getView() {
        return view;
    }

    public Worker<Void> getLoadWorker() {
        return loadWorker;
    }

    public void load(String url) {
        suppressNavigationHistory = false;
        loadInternal(url);
    }

    private void loadInternal(String url) {
        String next = url == null || url.isEmpty() ? "about:blank" : url;
        location.set(next);
        document.set(null);
        loadWorker.beginLoad(next);
        view.load(next);
    }

    public void loadContent(String content) {
        loadContent(content, "text/html");
    }

    public void loadContent(String content, String contentType) {
        suppressNavigationHistory = true;
        String mime = contentType == null || contentType.isEmpty() ? "text/html" : contentType;
        String body = content == null ? "" : content;
        String encoded = URLEncoder.encode(body, StandardCharsets.UTF_8).replace("+", "%20");
        loadInternal("data:" + mime + ";charset=UTF-8," + encoded);
    }

    public void reload() {
        view.reload();
    }

    /**
     * This method exists to provide feature parity with JavaFX's WebEngine; however, CEF does not implement LiveConnect
     * so it is not generally possible to return a JS proxy object. See {@link #evaluateScriptAsync(String)} for a safer
     * alternative.
     */
    @Deprecated
    public Object executeScript(String script) {
        view.executeScript(script);
        return null;
    }

    /**
     * Evaluate a JavaScript expression asynchronously and return the JSON-serialized result.
     *
     * @param expression the JS expression to evaluate
     * @return future completing with the JSON string result
     */
    public CompletableFuture<String> evaluateScriptAsync(String expression) {
        return view.getScriptEngine().evaluate(expression);
    }

    /** Returns the {@link CefScriptEngine} for advanced handle-based operations. */
    public CefScriptEngine getScriptEngine() {
        return view.getScriptEngine();
    }

    public void stop() {
        view.stop();
    }

    public CefWebHistory getHistory() {
        return history;
    }

    public String getLocation() {
        return location.get();
    }

    public ReadOnlyStringProperty locationProperty() {
        return location.getReadOnlyProperty();
    }

    public String getTitle() {
        return title.get();
    }

    public ReadOnlyStringProperty titleProperty() {
        return title.getReadOnlyProperty();
    }

    public boolean isLoading() {
        return loading.get();
    }

    public ReadOnlyBooleanProperty loadingProperty() {
        return loading.getReadOnlyProperty();
    }

    public double getLoadProgress() {
        return loadProgress.get();
    }

    public ReadOnlyDoubleProperty loadProgressProperty() {
        return loadProgress.getReadOnlyProperty();
    }

    public boolean isJavaScriptEnabled() {
        return javaScriptEnabled.get();
    }

    public void setJavaScriptEnabled(boolean enabled) {
        javaScriptEnabled.set(enabled);
    }

    public BooleanProperty javaScriptEnabledProperty() {
        return javaScriptEnabled;
    }

    public ReadOnlyObjectProperty<Document> documentProperty() {
        return document.getReadOnlyProperty();
    }

    public Document getDocument() {
        return document.get();
    }

    public boolean canGoBack() {
        return canGoBack.get();
    }

    public ReadOnlyBooleanProperty canGoBackProperty() {
        return canGoBack.getReadOnlyProperty();
    }

    public boolean canGoForward() {
        return canGoForward.get();
    }

    public ReadOnlyBooleanProperty canGoForwardProperty() {
        return canGoForward.getReadOnlyProperty();
    }

    public String getUserStyleSheetLocation() {
        return userStyleSheetLocation.get();
    }

    public void setUserStyleSheetLocation(String location) {
        userStyleSheetLocation.set(location);
    }

    public StringProperty userStyleSheetLocationProperty() {
        return userStyleSheetLocation;
    }

    public String getUserAgent() {
        return userAgent.get();
    }

    public void setUserAgent(String value) {
        userAgent.set(value);
    }

    public StringProperty userAgentProperty() {
        return userAgent;
    }

    public File getUserDataDirectory() {
        return userDataDirectory.get();
    }

    public void setUserDataDirectory(File value) {
        userDataDirectory.set(value);
    }

    public ObjectProperty<File> userDataDirectoryProperty() {
        return userDataDirectory;
    }

    public EventHandler<CefWebEvent<String>> getOnAlert() {
        return onAlert.get();
    }

    public void setOnAlert(EventHandler<CefWebEvent<String>> handler) {
        onAlert.set(handler);
    }

    public ObjectProperty<EventHandler<CefWebEvent<String>>> onAlertProperty() {
        return onAlert;
    }

    public EventHandler<CefWebEvent<String>> getOnStatusChanged() {
        return onStatusChanged.get();
    }

    public void setOnStatusChanged(EventHandler<CefWebEvent<String>> handler) {
        onStatusChanged.set(handler);
    }

    public ObjectProperty<EventHandler<CefWebEvent<String>>> onStatusChangedProperty() {
        return onStatusChanged;
    }

    public EventHandler<CefWebEvent<Rectangle2D>> getOnResized() {
        return onResized.get();
    }

    public void setOnResized(EventHandler<CefWebEvent<Rectangle2D>> handler) {
        onResized.set(handler);
    }

    public ObjectProperty<EventHandler<CefWebEvent<Rectangle2D>>> onResizedProperty() {
        return onResized;
    }

    public EventHandler<CefWebEvent<Boolean>> getOnVisibilityChanged() {
        return onVisibilityChanged.get();
    }

    public void setOnVisibilityChanged(EventHandler<CefWebEvent<Boolean>> handler) {
        onVisibilityChanged.set(handler);
    }

    public ObjectProperty<EventHandler<CefWebEvent<Boolean>>> onVisibilityChangedProperty() {
        return onVisibilityChanged;
    }

    public Callback<CefPopupFeatures, CefWebEngine> getCreatePopupHandler() {
        return createPopupHandler.get();
    }

    public void setCreatePopupHandler(Callback<CefPopupFeatures, CefWebEngine> handler) {
        createPopupHandler.set(handler);
    }

    public ObjectProperty<Callback<CefPopupFeatures, CefWebEngine>> createPopupHandlerProperty() {
        return createPopupHandler;
    }

    public Callback<String, Boolean> getConfirmHandler() {
        return confirmHandler.get();
    }

    public void setConfirmHandler(Callback<String, Boolean> handler) {
        confirmHandler.set(handler);
    }

    public ObjectProperty<Callback<String, Boolean>> confirmHandlerProperty() {
        return confirmHandler;
    }

    public Callback<CefPromptData, String> getPromptHandler() {
        return promptHandler.get();
    }

    public void setPromptHandler(Callback<CefPromptData, String> handler) {
        promptHandler.set(handler);
    }

    public ObjectProperty<Callback<CefPromptData, String>> promptHandlerProperty() {
        return promptHandler;
    }

    public EventHandler<CefWebErrorEvent> getOnError() {
        return onError.get();
    }

    public void setOnError(EventHandler<CefWebErrorEvent> handler) {
        onError.set(handler);
    }

    public ObjectProperty<EventHandler<CefWebErrorEvent>> onErrorProperty() {
        return onError;
    }

    void updateLocation(String value) {
        location.set(value == null || value.isEmpty() ? "about:blank" : value);
    }

    void updateTitle(String value) {
        title.set(value == null ? "" : value);
    }

    void updateLoadState(boolean isLoading, boolean back, boolean forward) {
        loading.set(isLoading);
        canGoBack.set(back);
        canGoForward.set(forward);
        loadWorker.updateRunning(isLoading);
        if (!isLoading) {
            loadProgress.set(1.0);
        } else if (loadProgress.get() >= 1.0) {
            loadProgress.set(0.0);
        }
    }

    void updateLoadProgress(double progress) {
        double clamped = Math.max(0.0, Math.min(1.0, progress));
        loadProgress.set(clamped);
        loadWorker.updateProgress(clamped);
    }

    void markLoadFinished() {
        loading.set(false);
        loadProgress.set(1.0);
        loadWorker.markSucceeded();
    }

    void markLoadFailed(Throwable failure) {
        loading.set(false);
        document.set(null);
        loadWorker.markFailed(failure);
    }

    void refreshHistory(List<CefWebHistory.EntrySnapshot> entries, int currentIndex) {
        history.replaceEntries(entries, currentIndex);
    }

    boolean shouldSuppressNavigationHistory() {
        return suppressNavigationHistory;
    }

    void fireAlert(String data) {
        EventHandler<CefWebEvent<String>> handler = getOnAlert();
        if (handler != null) handler.handle(new CefWebEvent<>(this, CefWebEvent.ALERT, data));
    }

    void fireStatusChanged(String data) {
        EventHandler<CefWebEvent<String>> handler = getOnStatusChanged();
        if (handler != null) handler.handle(new CefWebEvent<>(this, CefWebEvent.STATUS_CHANGED, data));
    }

    void fireResized(Rectangle2D data) {
        EventHandler<CefWebEvent<Rectangle2D>> handler = getOnResized();
        if (handler != null) handler.handle(new CefWebEvent<>(this, CefWebEvent.RESIZED, data));
    }

    void fireVisibilityChanged(boolean visible) {
        EventHandler<CefWebEvent<Boolean>> handler = getOnVisibilityChanged();
        if (handler != null) handler.handle(new CefWebEvent<>(this, CefWebEvent.VISIBILITY_CHANGED, visible));
    }

    void fireError(String message, Throwable error) {
        EventHandler<CefWebErrorEvent> handler = getOnError();
        if (handler != null) {
            handler.handle(new CefWebErrorEvent(this, CefWebErrorEvent.ANY, message, error));
        }
    }
}
