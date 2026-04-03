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
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeCompatTest {

    @BeforeAll
    static void startJavaFx() throws Exception {
        ensureStarted();
    }

    @Test
    void loadContentUpdatesWorkerAndTitle() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() ->
                view.getEngine().loadContent("<html><head><title>compat-title</title></head><body>ok</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(onFxThread(() -> view.getEngine().getTitle())).isEqualTo("compat-title");
    }

    @Test
    void alertHandlerFires() throws Exception {
        WebView view = createAttachedWebView();
        AtomicReference<String> alertText = new AtomicReference<>();

        onFxThread(() -> view.getEngine().setOnAlert(event -> alertText.set(event.getData())));
        onFxThread(
                () -> view.getEngine().loadContent("<html><body><script>alert('compat-alert')</script></body></html>"));

        assertThat(waitUntil(() -> "compat-alert".equals(alertText.get()), 3_000))
                .isTrue();
    }

    @Test
    void loadContentDoesNotCreateNavigationHistory() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine().loadContent("<html><head><title>one</title></head><body>one</body></html>"));
        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();

        onFxThread(() -> view.getEngine().loadContent("<html><head><title>two</title></head><body>two</body></html>"));
        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();

        WebHistory history = onFxThread(() -> view.getEngine().getHistory());
        assertThat(onFxThread(history::getEntries)).isEmpty();
        assertThat(onFxThread(history::getCurrentIndex)).isEqualTo(0);
    }

    @Test
    void historyTracksRealNavigationLoads() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/one", "<html><head><title>one</title></head><body>one</body></html>",
                "/two", "<html><head><title>two</title></head><body>two</body></html>"))) {
            WebView view = createAttachedWebView();

            onFxThread(() -> view.getEngine().load(server.url("/one")));
            assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                    .isTrue();

            onFxThread(() -> view.getEngine().load(server.url("/two")));
            assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                    .isTrue();

            WebHistory history = onFxThread(() -> view.getEngine().getHistory());
            assertThat(waitUntilOnFx(() -> history.getEntries().size() >= 2, 3_000))
                    .isTrue();
            assertThat(onFxThread(history::getCurrentIndex)).isGreaterThanOrEqualTo(1);

            onFxThread(() -> history.go(-1));
            assertThat(waitUntilOnFx(() -> "one".equals(view.getEngine().getTitle()), 3_000))
                    .isTrue();
        }
    }

    @Test
    void confirmHandlerReceivesMessageAndReturnsResult() throws Exception {
        WebView view = createAttachedWebView();
        AtomicReference<String> receivedMessage = new AtomicReference<>();

        onFxThread(() -> view.getEngine().setConfirmHandler(message -> {
            receivedMessage.set(message);
            return true;
        }));
        onFxThread(
                () -> view.getEngine()
                        .loadContent(
                                "<html><body><script>document.title = confirm('compat-confirm') ? 'yes' : 'no';</script></body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntil(() -> "compat-confirm".equals(receivedMessage.get()), 3_000))
                .isTrue();
        assertThat(onFxThread(() -> view.getEngine().getTitle())).isEqualTo("yes");
    }

    @Test
    void confirmHandlerDenialReturnsCorrectResult() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine().setConfirmHandler(message -> false));
        onFxThread(
                () -> view.getEngine()
                        .loadContent(
                                "<html><body><script>document.title = confirm('deny-me') ? 'yes' : 'no';</script></body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "no".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
    }

    @Test
    void promptHandlerReceivesMessageAndDefaultValue() throws Exception {
        WebView view = createAttachedWebView();
        AtomicReference<String> receivedMessage = new AtomicReference<>();
        AtomicReference<String> receivedDefault = new AtomicReference<>();

        onFxThread(() -> view.getEngine().setPromptHandler(promptData -> {
            receivedMessage.set(promptData.getMessage());
            receivedDefault.set(promptData.getDefaultValue());
            return "user-input";
        }));
        onFxThread(
                () -> view.getEngine()
                        .loadContent(
                                "<html><body><script>document.title = prompt('compat-prompt', 'default-val');</script></body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntil(() -> "compat-prompt".equals(receivedMessage.get()), 3_000))
                .isTrue();
        assertThat(receivedDefault.get()).isEqualTo("default-val");
        assertThat(onFxThread(() -> view.getEngine().getTitle())).isEqualTo("user-input");
    }

    @Test
    void promptHandlerReturningNullActsAsCancel() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine().setPromptHandler(promptData -> null));
        onFxThread(
                () -> view.getEngine()
                        .loadContent(
                                "<html><body><script>document.title = prompt('cancel-me') === null ? 'cancelled' : 'not-cancelled';</script></body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "cancelled".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
    }

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

            assertThat(waitUntil(popupRequested::get, 5_000)).isTrue();
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
            assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                    .isTrue();
            assertThat(waitUntilOnFx(() -> "blocked".equals(view.getEngine().getTitle()), 3_000))
                    .isTrue();
        }
    }

    @Test
    void selectElementOptionsAccessibleViaJavaScript() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body>"
                        + "<select id='sel'>"
                        + "  <option value='a'>Alpha</option>"
                        + "  <option value='b'>Beta</option>"
                        + "  <option value='c'>Gamma</option>"
                        + "</select>"
                        + "<script>"
                        + "  var sel = document.getElementById('sel');"
                        + "  document.title = sel.options.length + '-' + sel.options[0].text;"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(onFxThread(() -> view.getEngine().getTitle())).isEqualTo("3-Alpha");
    }

    @Test
    void selectElementChangeEventFires() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><body>"
                        + "<select id='sel'>"
                        + "  <option value='a'>Alpha</option>"
                        + "  <option value='b'>Beta</option>"
                        + "</select>"
                        + "<script>"
                        + "  var sel = document.getElementById('sel');"
                        + "  sel.addEventListener('change', function() {"
                        + "    document.title = 'changed-' + sel.value;"
                        + "  });"
                        + "</script>"
                        + "</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();

        // Programmatically change the select value and dispatch a change event
        onFxThread(() -> view.getEngine()
                .executeScript("var sel = document.getElementById('sel');"
                        + "sel.value = 'b';"
                        + "sel.dispatchEvent(new Event('change'));"));

        assertThat(waitUntilOnFx(() -> "changed-b".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
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

            assertThat(waitUntil(() -> popupBounds.get() != null, 5_000)).isTrue();
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

            assertThat(waitUntil(() -> popupBounds.get() != null, 5_000)).isTrue();
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

            // Wait for the resizeTo(500, 400) event - the last resize should reflect 500x400
            assertThat(waitUntil(
                            () -> {
                                Rectangle2D b = latestBounds.get();
                                return b != null && b.getWidth() > 400;
                            },
                            5_000))
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

            assertThat(waitUntilOnFx(() -> "dest-page".equals(view.getEngine().getTitle()), 5_000))
                    .isTrue();
        }
    }
}
