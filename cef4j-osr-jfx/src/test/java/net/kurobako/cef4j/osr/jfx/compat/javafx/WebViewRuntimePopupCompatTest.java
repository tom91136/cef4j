package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javafx.concurrent.Worker;
import javafx.geometry.Rectangle2D;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimePopupCompatTest extends WebViewRuntimeCompatTestBase {

    @Test
    void createPopupHandlerReceivesPopupFeaturesOnWindowOpen() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener", "<html><body><script>window.open('/popup', '_blank');</script></body></html>",
                "/popup", "<html><head><title>popup-page</title></head><body>popup</body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicBoolean popupRequested = new AtomicBoolean();
            AtomicReference<PopupFeatures> receivedFeatures = new AtomicReference<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                popupRequested.set(true);
                receivedFeatures.set(features);
                WebView popupView = new WebView();
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            assertThat(waitUntil(popupRequested::get, 8_000)).isTrue();
            assertThat(receivedFeatures.get()).isNotNull();
        }
    }

    @Test
    void createPopupHandlerReturningNullBlocksPopup() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener",
                        "<html><body><script>var w = window.open('/popup', '_blank'); document.title = w === null ? 'blocked' : 'opened';</script></body></html>",
                "/popup", "<html><head><title>popup-page</title></head><body>popup</body></html>"))) {
            WebView view = createAttachedWebView();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> null));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));
            assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 8_000))
                    .isTrue();
            assertThat(waitUntilOnFx(() -> "blocked".equals(view.getEngine().getTitle()), 5_000))
                    .isTrue();
        }
    }

    @Test
    void popupHandlerAllowsPopupEngineToLoadContent() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener", "<html><body><script>window.open('/popup', '_blank');</script></body></html>",
                "/popup", "<html><head><title>popup-title</title></head><body>popup</body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicReference<String> popupTitle = new AtomicReference<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                popupView
                        .getEngine()
                        .titleProperty()
                        .addListener((obs, oldValue, newValue) -> popupTitle.set(newValue));
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            assertThat(waitUntil(() -> "popup-title".equals(popupTitle.get()), 8_000))
                    .as("expected popup title 'popup-title'; got '%s'", popupTitle.get())
                    .isTrue();
        }
    }

    @Test
    void namedPopupNavigatesToLatestRequestedPage() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener",
                        "<html><body><script>"
                                + "window.open('/first', 'reuse-me');"
                                + "window.open('/second', 'reuse-me');"
                                + "</script></body></html>",
                "/first", "<html><head><title>first-popup</title></head><body>first</body></html>",
                "/second", "<html><head><title>second-popup</title></head><body>second</body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicReference<String> popupTitle = new AtomicReference<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                popupView
                        .getEngine()
                        .titleProperty()
                        .addListener((obs, oldValue, newValue) -> popupTitle.set(newValue));
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            assertThat(waitUntil(() -> "second-popup".equals(popupTitle.get()), 8_000))
                    .as("expected popup title 'second-popup'; got '%s'", popupTitle.get())
                    .isTrue();
        }
    }

    @Test
    void popupVisibilityChangesWhenPopupOpensAndCloses() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener", "<html><body><script>" + "window.open('/popup', '_blank');" + "</script></body></html>",
                "/popup",
                        "<html><body><script>"
                                + "setTimeout(function() { window.close(); }, 200);"
                                + "</script></body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicBoolean becameVisible = new AtomicBoolean();
            AtomicBoolean becameHidden = new AtomicBoolean();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                popupView.getEngine().setOnVisibilityChanged(event -> {
                    if (Boolean.TRUE.equals(event.getData())) {
                        becameVisible.set(true);
                    } else {
                        becameHidden.set(true);
                    }
                });
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            assertThat(waitUntil(becameVisible::get, 8_000)).isTrue();
            assertThat(waitUntil(becameHidden::get, 8_000)).isTrue();
        }
    }

    @Test
    void popupOpensAtRequestedPositionAndSize() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener",
                "<html><body><script>"
                        + "window.open('/popup', '_blank', 'left=120,top=80,width=400,height=300');"
                        + "</script></body></html>",
                "/popup",
                "<html><head><title>sized-popup</title></head><body>popup</body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicReference<Rectangle2D> popupBounds = new AtomicReference<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                WebEngine popupEngine = popupView.getEngine();
                popupEngine.setOnResized(event -> popupBounds.set(event.getData()));
                return popupEngine;
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            assertThat(waitUntil(() -> popupBounds.get() != null, 8_000)).isTrue();
            Rectangle2D bounds = popupBounds.get();
            assertThat(bounds.getWidth()).isBetween(350.0, 450.0);
            assertThat(bounds.getHeight()).isBetween(250.0, 350.0);
        }
    }

    @Test
    void popupWithSmallDimensionsReportsSmallSize() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener",
                "<html><body><script>"
                        + "window.open('/popup', '_blank', 'width=150,height=100');"
                        + "</script></body></html>",
                "/popup",
                "<html><head><title>small-popup</title></head><body>small</body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicReference<Rectangle2D> popupBounds = new AtomicReference<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                WebEngine popupEngine = popupView.getEngine();
                popupEngine.setOnResized(event -> popupBounds.set(event.getData()));
                return popupEngine;
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            assertThat(waitUntil(() -> popupBounds.get() != null, 8_000)).isTrue();
            Rectangle2D bounds = popupBounds.get();
            assertThat(bounds.getWidth()).isBetween(100.0, 200.0);
            assertThat(bounds.getHeight()).isBetween(50.0, 150.0);
        }
    }

    @Test
    void popupResizeToUpdatesGeometry() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener",
                        "<html><body><script>"
                                + "var w = window.open('/popup', '_blank', 'width=200,height=200');"
                                + "</script></body></html>",
                "/popup", "<html><body><script>" + "window.resizeTo(500, 400);" + "</script></body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicReference<Rectangle2D> latestBounds = new AtomicReference<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                WebEngine popupEngine = popupView.getEngine();
                popupEngine.setOnResized(event -> latestBounds.set(event.getData()));
                return popupEngine;
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            assertThat(waitUntil(
                            () -> {
                                Rectangle2D b = latestBounds.get();
                                return b != null && b.getWidth() > 400;
                            },
                            8_000))
                    .isTrue();
            Rectangle2D bounds = latestBounds.get();
            assertThat(bounds.getWidth()).isBetween(450.0, 550.0);
            assertThat(bounds.getHeight()).isBetween(350.0, 450.0);
        }
    }

    @Test
    void windowOpenTargetSelfNavigatesInPlace() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/start", "<html><body><script>window.open('/dest', '_self');</script></body></html>",
                "/dest", "<html><head><title>dest-page</title></head><body>destination</body></html>"))) {
            WebView view = createAttachedWebView();

            onFxThread(() -> view.getEngine().load(server.url("/start")));

            assertThat(waitUntilOnFx(() -> "dest-page".equals(view.getEngine().getTitle()), 8_000))
                    .isTrue();
        }
    }
}
