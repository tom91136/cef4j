package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javafx.concurrent.Worker;
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
}
