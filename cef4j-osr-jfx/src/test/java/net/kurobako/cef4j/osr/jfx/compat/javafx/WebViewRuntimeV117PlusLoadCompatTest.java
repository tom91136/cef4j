package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class WebViewRuntimeV117PlusLoadCompatTest extends WebViewRuntimeCompatTestBase {

    @Test
    void loadContentTransitionsThroughAllWorkerStates() throws Exception {
        WebView view = createAttachedWebView();
        List<State> observed = new CopyOnWriteArrayList<>();
        onFxThread(() -> {
            observed.add(view.getEngine().getLoadWorker().getState());
            view.getEngine().getLoadWorker().stateProperty().addListener((obs, old, state) -> observed.add(state));
            view.getEngine().loadContent("<!doctype html><html><body></body></html>");
        });

        assertThat(waitForWorkerState(view.getEngine(), State.SUCCEEDED, 5_000)).isTrue();
        assertThat(observed).containsExactly(State.READY, State.SCHEDULED, State.RUNNING, State.SUCCEEDED);
    }

    @Test
    void loadContentUpdatesWorkerAndTitle() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() ->
                view.getEngine().loadContent("<html><head><title>compat-title</title></head><body>ok</body></html>"));

        assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                .isTrue();
        assertThat(waitUntilOnFx(() -> "compat-title".equals(view.getEngine().getTitle()), 3_000))
                .isTrue();
    }

    @Test
    void loadTracksRequestedLocationImmediately() throws Exception {
        try (LocalTestServer server =
                startServer(Map.of("/page", "<html><head><title>loaded</title></head><body>ok</body></html>"))) {
            WebView view = createAttachedWebView();
            String url = server.url("/page");

            onFxThread(() -> view.getEngine().load(url));

            assertThat(waitUntilOnFx(() -> url.equals(view.getEngine().getLocation()), 3_000))
                    .isTrue();
            assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 3_000))
                    .isTrue();
            assertThat(waitUntilOnFx(() -> "loaded".equals(view.getEngine().getTitle()), 3_000))
                    .isTrue();
        }
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

        WebHistory history =
                Objects.requireNonNull(onFxThread(() -> view.getEngine().getHistory()), "history");
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

            WebHistory history =
                    Objects.requireNonNull(onFxThread(() -> view.getEngine().getHistory()), "history");
            assertThat(waitUntilOnFx(() -> history.getEntries().size() >= 2, 3_000))
                    .isTrue();
            assertThat(onFxThread(history::getCurrentIndex)).isGreaterThanOrEqualTo(1);

            onFxThread(() -> history.go(-1));
            assertThat(waitUntilOnFx(() -> "one".equals(view.getEngine().getTitle()), 3_000))
                    .isTrue();
        }
    }

    @Test
    void historyGoForwardRestoresLaterPage() throws Exception {
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

            WebHistory history =
                    Objects.requireNonNull(onFxThread(() -> view.getEngine().getHistory()), "history");
            onFxThread(() -> history.go(-1));
            assertThat(waitUntilOnFx(() -> "one".equals(view.getEngine().getTitle()), 3_000))
                    .isTrue();

            onFxThread(() -> history.go(1));
            assertThat(waitUntilOnFx(() -> "two".equals(view.getEngine().getTitle()), 3_000))
                    .isTrue();
        }
    }

    @Test
    void laterLoadWinsWhenPreviousRequestIsStillInFlight() throws Exception {
        try (LocalTestServer server = startServerWithResponses(Map.of(
                "/slow",
                ResponseSpec.html("<html><head><title>slow</title></head><body>slow</body></html>", 750),
                "/fast",
                ResponseSpec.html("<html><head><title>fast</title></head><body>fast</body></html>")))) {
            WebView view = createAttachedWebView();

            onFxThread(() -> view.getEngine().load(server.url("/slow")));
            onFxThread(() -> view.getEngine().load(server.url("/fast")));

            assertThat(waitUntilOnFx(() -> "fast".equals(view.getEngine().getTitle()), 5_000))
                    .isTrue();
            assertThat(onFxThread(() -> view.getEngine().getLocation())).isEqualTo(server.url("/fast"));
        }
    }

    @Test
    void redirectUpdatesLocationAndFinalTitle() throws Exception {
        try (LocalTestServer server = startServerWithResponses(Map.of(
                "/redir", ResponseSpec.redirect("/final"),
                "/final",
                        ResponseSpec.html("<html><head><title>final-page</title></head><body>final</body></html>")))) {
            WebView view = createAttachedWebView();

            onFxThread(() -> view.getEngine().load(server.url("/redir")));

            assertThat(waitForWorkerState(view.getEngine(), Worker.State.SUCCEEDED, 5_000))
                    .isTrue();
            assertThat(waitUntilOnFx(() -> "final-page".equals(view.getEngine().getTitle()), 3_000))
                    .isTrue();
            assertThat(onFxThread(() -> view.getEngine().getLocation())).isEqualTo(server.url("/final"));
        }
    }

    @Test
    void iframeNavigationDoesNotOverwriteTopLevelLocation() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/host",
                        "<html><head><title>host-start</title></head><body>"
                                + "<iframe src='/child'></iframe>"
                                + "</body></html>",
                "/child",
                        "<html><body><script>"
                                + "parent.document.title = 'child-ready';"
                                + "</script></body></html>"))) {
            WebView view = createAttachedWebView();
            String hostUrl = server.url("/host");

            onFxThread(() -> view.getEngine().load(hostUrl));

            assertThat(waitUntilOnFx(() -> "child-ready".equals(view.getEngine().getTitle()), 5_000))
                    .isTrue();
            assertThat(onFxThread(() -> view.getEngine().getLocation())).isEqualTo(hostUrl);
        }
    }

    @Test
    void iframeLoadErrorsDoNotFailTopLevelLoadWorker() throws Exception {
        WebView view = createAttachedWebView();

        onFxThread(() -> view.getEngine()
                .loadContent("<html><head><title>host-start</title></head><body>"
                        + "<iframe src='http://127.0.0.1:9/unreachable' "
                        + "onerror=\"parent.document.title='iframe-error'\">"
                        + "</iframe>"
                        + "<script>setTimeout(function(){ document.title = 'host-stable'; }, 150);</script>"
                        + "</body></html>"));

        assertThat(waitUntilOnFx(() -> "host-stable".equals(view.getEngine().getTitle()), 5_000))
                .isTrue();
        Thread.sleep(300);
        assertThat(onFxThread(() -> view.getEngine().getLoadWorker().getState()))
                .isEqualTo(Worker.State.SUCCEEDED);
    }
}
