package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javafx.geometry.Rectangle2D;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeVisualCompatTest extends WebViewRuntimeCompatTestBase {

    @Test
    void popupDelayedResizeToUpdatesGeometry() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener",
                        "<html><body><script>"
                                + "window.open('/popup', '_blank', 'width=220,height=180');"
                                + "</script></body></html>",
                "/popup",
                        "<html><body><script>"
                                + "setTimeout(function() { window.resizeTo(420, 320); }, 80);"
                                + "</script></body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicReference<Rectangle2D> latestBounds = new AtomicReference<>();
            AtomicReference<WebView> popupRef = new AtomicReference<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                popupView.getEngine().setOnResized(event -> latestBounds.set(event.getData()));
                popupRef.set(popupView);
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            try {
                assertThat(waitUntil(
                                () -> {
                                    Rectangle2D b = latestBounds.get();
                                    return b != null && b.getWidth() > 350;
                                },
                                5_000))
                        .isTrue();
                Rectangle2D bounds = latestBounds.get();
                assertThat(bounds.getWidth()).isBetween(380.0, 460.0);
                assertThat(bounds.getHeight()).isBetween(280.0, 360.0);
            } finally {
                releasePopup(popupRef.get());
            }
        }
    }

    @Test
    void popupSequentialResizesReportFinalBounds() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener",
                        "<html><body><script>"
                                + "window.open('/popup', '_blank', 'width=180,height=160');"
                                + "</script></body></html>",
                "/popup",
                        "<html><body><script>"
                                + "setTimeout(function() { window.resizeTo(300, 240); }, 50);"
                                + "setTimeout(function() { window.resizeTo(520, 410); }, 200);"
                                + "</script></body></html>"))) {
            WebView view = createAttachedWebView();
            AtomicReference<Rectangle2D> latestBounds = new AtomicReference<>();
            AtomicReference<WebView> popupRef = new AtomicReference<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                popupView.getEngine().setOnResized(event -> latestBounds.set(event.getData()));
                popupRef.set(popupView);
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            try {
                assertThat(waitUntil(
                                () -> {
                                    Rectangle2D b = latestBounds.get();
                                    return b != null && b.getWidth() > 480;
                                },
                                8_000))
                        .as("expected second resizeTo(520,410) to fire; last bounds=%s", latestBounds.get())
                        .isTrue();
                Rectangle2D bounds = latestBounds.get();
                assertThat(bounds.getWidth()).isBetween(480.0, 560.0);
                assertThat(bounds.getHeight()).isBetween(370.0, 450.0);
            } finally {
                releasePopup(popupRef.get());
            }
        }
    }

    // Detached popup WebViews leak WebKit scheduler state across tests in the same JVM, delaying or dropping
    // subsequent popups' events. Loading about:blank drains pending timers before the next test starts.
    private static void releasePopup(WebView popup) throws Exception {
        if (popup == null) return;
        onFxThread(() -> {
            popup.getEngine().setOnResized(null);
            popup.getEngine().setOnVisibilityChanged(null);
            popup.getEngine().load("about:blank");
        });
    }

    @Test
    void closingHostStageAllowsFutureViewToLoad() throws Exception {
        WebView first = createAttachedWebView();
        Stage firstStage = onFxThread(() -> (Stage) first.getScene().getWindow());
        onFxThread(firstStage::close);

        WebView second = createAttachedWebView();
        onFxThread(() ->
                second.getEngine().loadContent("<html><head><title>second-view</title></head><body>ok</body></html>"));

        assertThat(waitUntilOnFx(() -> "second-view".equals(second.getEngine().getTitle()), 5_000))
                .isTrue();
    }
}
