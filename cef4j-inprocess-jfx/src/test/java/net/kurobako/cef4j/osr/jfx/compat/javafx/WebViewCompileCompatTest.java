package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.concurrent.Worker;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.text.FontSmoothingType;
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
    void webViewIsAParentWithTheSameLayoutAndPropertySurface() {
        Function<WebView, Parent> asParent = view -> view;
        Function<WebView, Boolean> resizable = WebView::isResizable;
        Function<WebView, ReadOnlyDoubleProperty> widthProperty = WebView::widthProperty;
        Function<WebView, ReadOnlyDoubleProperty> heightProperty = WebView::heightProperty;
        Function<WebView, Double> width = WebView::getWidth;
        Function<WebView, Double> height = WebView::getHeight;
        BiConsumer<WebView, Double> setFontScale = WebView::setFontScale;
        Function<WebView, Double> getFontScale = WebView::getFontScale;
        Function<WebView, DoubleProperty> fontScaleProperty = WebView::fontScaleProperty;
        BiConsumer<WebView, FontSmoothingType> setFontSmoothingType = WebView::setFontSmoothingType;
        Function<WebView, FontSmoothingType> getFontSmoothingType = WebView::getFontSmoothingType;
        Function<WebView, ObjectProperty<FontSmoothingType>> fontSmoothingTypeProperty =
                WebView::fontSmoothingTypeProperty;
        BiConsumer<WebView, Boolean> setContextMenuEnabled = WebView::setContextMenuEnabled;
        Function<WebView, Boolean> isContextMenuEnabled = WebView::isContextMenuEnabled;
        Function<WebView, BooleanProperty> contextMenuEnabledProperty = WebView::contextMenuEnabledProperty;
        Supplier<List<CssMetaData<? extends Styleable, ?>>> classCssMetaData = WebView::getClassCssMetaData;
        Function<WebView, List<CssMetaData<? extends Styleable, ?>>> cssMetaData = WebView::getCssMetaData;

        assertThat(asParent).isNotNull();
        assertThat(resizable).isNotNull();
        assertThat(widthProperty).isNotNull();
        assertThat(heightProperty).isNotNull();
        assertThat(width).isNotNull();
        assertThat(height).isNotNull();
        assertThat(setFontScale).isNotNull();
        assertThat(getFontScale).isNotNull();
        assertThat(fontScaleProperty).isNotNull();
        assertThat(setFontSmoothingType).isNotNull();
        assertThat(getFontSmoothingType).isNotNull();
        assertThat(fontSmoothingTypeProperty).isNotNull();
        assertThat(setContextMenuEnabled).isNotNull();
        assertThat(isContextMenuEnabled).isNotNull();
        assertThat(contextMenuEnabledProperty).isNotNull();
        assertThat(classCssMetaData).isNotNull();
        assertThat(cssMetaData).isNotNull();
    }

    @Test
    void webViewSizeMembersCompileAgainstJavafxContracts() {
        Consumer<WebView> copySizes = view -> {
            view.setMinSize(view.getMinWidth(), view.getMinHeight());
            view.setPrefSize(view.getPrefWidth(), view.getPrefHeight());
            view.setMaxSize(view.getMaxWidth(), view.getMaxHeight());
            view.setMinWidth(view.minWidth(-1));
            view.setMinHeight(view.minHeight(-1));
            view.setPrefWidth(view.prefWidth(-1));
            view.setPrefHeight(view.prefHeight(-1));
            view.setMaxWidth(view.maxWidth(-1));
            view.setMaxHeight(view.maxHeight(-1));
            view.resize(view.getWidth(), view.getHeight());
        };
        Consumer<WebView> properties = view -> {
            DoubleProperty[] all = {
                view.minWidthProperty(),
                view.minHeightProperty(),
                view.prefWidthProperty(),
                view.prefHeightProperty(),
                view.maxWidthProperty(),
                view.maxHeightProperty()
            };
            assertThat(all).doesNotContainNull();
        };

        assertThat(copySizes).isNotNull();
        assertThat(properties).isNotNull();
    }

    @Test
    void webEngineConstructorsAndPrintCompileAgainstJavafxContracts() {
        Supplier<WebEngine> noArgs = WebEngine::new;
        Function<String, WebEngine> withUrl = WebEngine::new;
        BiConsumer<WebEngine, PrinterJob> print = WebEngine::print;

        assertThat(noArgs).isNotNull();
        assertThat(withUrl).isNotNull();
        assertThat(print).isNotNull();
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
