package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
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

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                popupView.getEngine().setOnResized(event -> latestBounds.set(event.getData()));
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

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

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                popupView.getEngine().setOnResized(event -> latestBounds.set(event.getData()));
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

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
        }
    }

    @Test
    void popupVisibilityChangesAreReportedInOrder() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/opener", "<html><body><script>" + "window.open('/popup', '_blank');" + "</script></body></html>",
                "/popup",
                        "<html><body><script>"
                                + "setTimeout(function() { window.close(); }, 200);"
                                + "</script></body></html>"))) {
            WebView view = createAttachedWebView();
            List<Boolean> visibilityEvents = new ArrayList<>();

            onFxThread(() -> view.getEngine().setCreatePopupHandler(features -> {
                WebView popupView = new WebView();
                popupView.getEngine().setOnVisibilityChanged(event -> visibilityEvents.add(event.getData()));
                return popupView.getEngine();
            }));

            onFxThread(() -> view.getEngine().load(server.url("/opener")));

            assertThat(waitUntil(() -> visibilityEvents.size() >= 2, 8_000))
                    .as("expected visibility [true, false]; got %s", visibilityEvents)
                    .isTrue();
            assertThat(visibilityEvents.subList(0, 2)).containsExactly(true, false);
        }
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
