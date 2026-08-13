package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.PromptData;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import org.junit.jupiter.api.Test;

class WebViewCompileCompatTest {

    @Test
    void webViewSurfaceCompilesAgainstJavafxContracts() {
        Supplier<WebView> ctor = WebView::new;
        Function<WebView, WebEngine> getEngine = WebView::getEngine;
        Consumer<WebView> copyZoom = view -> view.setZoom(view.getZoom());

        assertThat(ctor).isNotNull();
        assertThat(getEngine).isNotNull();
        assertThat(copyZoom).isNotNull();
    }

    @Test
    void webEngineSurfaceCompilesAgainstJavafxContracts() {
        Function<WebEngine, Worker<Void>> getLoadWorker = WebEngine::getLoadWorker;
        Function<WebEngine, WebHistory> getHistory = WebEngine::getHistory;
        BiConsumer<WebEngine, String> load = WebEngine::load;
        BiConsumer<WebEngine, String> setUserAgent = WebEngine::setUserAgent;
        BiConsumer<WebEngine, File> setUserDataDirectory = WebEngine::setUserDataDirectory;
        Consumer<WebEngine> copyJavaScriptEnabled = engine -> engine.setJavaScriptEnabled(engine.isJavaScriptEnabled());
        Consumer<WebEngine> copyUserStyleSheetLocation =
                engine -> engine.setUserStyleSheetLocation(engine.getUserStyleSheetLocation());
        BiConsumer<WebEngine, EventHandler<WebEvent<String>>> setOnAlert = WebEngine::setOnAlert;
        BiConsumer<WebEngine, EventHandler<WebEvent<String>>> setOnStatusChanged = WebEngine::setOnStatusChanged;
        BiConsumer<WebEngine, EventHandler<WebEvent<Rectangle2D>>> setOnResized = WebEngine::setOnResized;
        BiConsumer<WebEngine, EventHandler<WebEvent<Boolean>>> setOnVisibilityChanged =
                WebEngine::setOnVisibilityChanged;
        BiConsumer<WebEngine, Callback<String, Boolean>> setConfirmHandler = WebEngine::setConfirmHandler;
        BiConsumer<WebEngine, Callback<PromptData, String>> setPromptHandler = WebEngine::setPromptHandler;
        BiConsumer<WebEngine, Callback<PopupFeatures, WebEngine>> setCreatePopupHandler =
                WebEngine::setCreatePopupHandler;
        BiConsumer<WebEngine, EventHandler<WebErrorEvent>> setOnError = WebEngine::setOnError;

        assertThat(getLoadWorker).isNotNull();
        assertThat(getHistory).isNotNull();
        assertThat(load).isNotNull();
        assertThat(setUserAgent).isNotNull();
        assertThat(setUserDataDirectory).isNotNull();
        assertThat(copyJavaScriptEnabled).isNotNull();
        assertThat(copyUserStyleSheetLocation).isNotNull();
        assertThat(setOnAlert).isNotNull();
        assertThat(setOnStatusChanged).isNotNull();
        assertThat(setOnResized).isNotNull();
        assertThat(setOnVisibilityChanged).isNotNull();
        assertThat(setConfirmHandler).isNotNull();
        assertThat(setPromptHandler).isNotNull();
        assertThat(setCreatePopupHandler).isNotNull();
        assertThat(setOnError).isNotNull();
    }

    @Test
    void webHistorySurfaceCompilesAgainstJavafxContracts() {
        Consumer<WebHistory> copyMaxSize = history -> history.setMaxSize(history.getMaxSize());
        Consumer<WebHistory> noopGo = history -> history.go(0);
        Function<WebHistory, Integer> getCurrentIndex = WebHistory::getCurrentIndex;

        assertThat(copyMaxSize).isNotNull();
        assertThat(noopGo).isNotNull();
        assertThat(getCurrentIndex).isNotNull();
    }
}
