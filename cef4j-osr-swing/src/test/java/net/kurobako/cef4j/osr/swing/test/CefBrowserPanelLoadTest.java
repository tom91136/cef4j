package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(30)
class CefBrowserPanelLoadTest extends SwingBrowserPanelTestBase {

    @Test
    void loadContentUpdatesTitle() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        loadContent(panel, "<html><head><title>swing-title</title></head><body>ok</body></html>");

        assertThat(waitUntil(() -> "swing-title".equals(getTitle(panel)), 5_000))
                .isTrue();
    }

    @Test
    void loadContentViaDataUrlCreatesNavigationHistory() throws Exception {
        CefBrowserPanel panel = createAttachedPanel();

        loadContent(panel, "<html><head><title>one</title></head><body>one</body></html>");
        assertThat(waitUntil(() -> "one".equals(getTitle(panel)), 5_000)).isTrue();

        loadContent(panel, "<html><head><title>two</title></head><body>two</body></html>");
        assertThat(waitUntil(() -> "two".equals(getTitle(panel)), 5_000)).isTrue();

        assertThat(waitUntil(() -> STATES.get(panel).canGoBack, 5_000)).isTrue();

        panel.getBrowser().goBack();
        assertThat(waitUntil(() -> "one".equals(getTitle(panel)), 5_000)).isTrue();
    }

    @Test
    void loadTracksRequestedLocationImmediately() throws Exception {
        try (LocalTestServer server =
                startServer(Map.of("/page", "<html><head><title>loaded</title></head><body>ok</body></html>"))) {
            CefBrowserPanel panel = createAttachedPanel();
            String url = server.url("/page");

            loadUrl(panel, url);

            assertThat(waitUntil(() -> url.equals(getLocation(panel)), 5_000)).isTrue();
            assertThat(waitUntil(() -> "loaded".equals(getTitle(panel)), 5_000)).isTrue();
        }
    }

    @Test
    void historyTracksRealNavigationLoads() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/one", "<html><head><title>one</title></head><body>one</body></html>",
                "/two", "<html><head><title>two</title></head><body>two</body></html>"))) {
            CefBrowserPanel panel = createAttachedPanel();

            loadUrl(panel, server.url("/one"));
            assertThat(waitUntil(() -> "one".equals(getTitle(panel)), 5_000)).isTrue();

            loadUrl(panel, server.url("/two"));
            assertThat(waitUntil(() -> "two".equals(getTitle(panel)), 5_000)).isTrue();

            panel.getBrowser().goBack();
            assertThat(waitUntil(() -> "one".equals(getTitle(panel)), 5_000)).isTrue();
        }
    }

    @Test
    void historyGoForwardRestoresLaterPage() throws Exception {
        try (LocalTestServer server = startServer(Map.of(
                "/one", "<html><head><title>one</title></head><body>one</body></html>",
                "/two", "<html><head><title>two</title></head><body>two</body></html>"))) {
            CefBrowserPanel panel = createAttachedPanel();

            loadUrl(panel, server.url("/one"));
            assertThat(waitUntil(() -> "one".equals(getTitle(panel)), 5_000)).isTrue();

            loadUrl(panel, server.url("/two"));
            assertThat(waitUntil(() -> "two".equals(getTitle(panel)), 5_000)).isTrue();

            panel.getBrowser().goBack();
            assertThat(waitUntil(() -> "one".equals(getTitle(panel)), 5_000)).isTrue();

            panel.getBrowser().goForward();
            assertThat(waitUntil(() -> "two".equals(getTitle(panel)), 5_000)).isTrue();
        }
    }

    @Test
    void laterLoadWinsWhenPreviousRequestIsStillInFlight() throws Exception {
        try (LocalTestServer server = startServerWithResponses(Map.of(
                "/slow",
                ResponseSpec.html("<html><head><title>slow</title></head><body>slow</body></html>", 750),
                "/fast",
                ResponseSpec.html("<html><head><title>fast</title></head><body>fast</body></html>")))) {
            CefBrowserPanel panel = createAttachedPanel();

            loadUrl(panel, server.url("/slow"));
            loadUrl(panel, server.url("/fast"));

            assertThat(waitUntil(() -> "fast".equals(getTitle(panel)), 5_000)).isTrue();
            assertThat(getLocation(panel)).isEqualTo(server.url("/fast"));
        }
    }

    @Test
    void redirectUpdatesLocationAndFinalTitle() throws Exception {
        try (LocalTestServer server = startServerWithResponses(Map.of(
                "/redir", ResponseSpec.redirect("/final"),
                "/final",
                        ResponseSpec.html("<html><head><title>final-page</title></head><body>final</body></html>")))) {
            CefBrowserPanel panel = createAttachedPanel();

            loadUrl(panel, server.url("/redir"));

            assertThat(waitUntil(() -> "final-page".equals(getTitle(panel)), 5_000))
                    .isTrue();
            assertThat(getLocation(panel)).isEqualTo(server.url("/final"));
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
            CefBrowserPanel panel = createAttachedPanel();
            String hostUrl = server.url("/host");

            loadUrl(panel, hostUrl);

            assertThat(waitUntil(() -> "child-ready".equals(getTitle(panel)), 5_000))
                    .isTrue();
            assertThat(getLocation(panel)).isEqualTo(hostUrl);
        }
    }

}
