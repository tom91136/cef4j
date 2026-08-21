package net.kurobako.cef4j.test.backend;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import org.junit.jupiter.api.Assumptions;

/** Shared behavioural contract run unchanged against in-process and Remote CEF browser surfaces. */
public final class BrowserContract {
    private BrowserContract() {}

    public static void verify(@Nonnull BrowserBackend backend) throws Exception {
        Assumptions.assumeTrue(backend.isAvailable(), () -> backend.name() + " unavailable");
        try (FixtureSite site = FixtureSite.start()) {
            verify(backend, site);
        }
    }

    private static void verify(BrowserBackend backend, FixtureSite site) throws Exception {
        Duration timeout = Duration.ofSeconds(40);
        BrowserBackend.SessionConfig config = new BrowserBackend.SessionConfig(site.url("/first"), 640, 480, timeout);

        try (BrowserSession session = backend.openSession(config)) {
            assertPaint(session.awaitPaint(640, 480, timeout), 640, 480);

            assertThat(session.evaluateJavascript("1 + 2 + 3").get(timeout.toSeconds(), TimeUnit.SECONDS))
                    .isEqualTo("6");
            assertThat(session.evaluateJavascript("true").get(timeout.toSeconds(), TimeUnit.SECONDS))
                    .isEqualTo("true");
            assertThat(session.evaluateJavascript("false").get(timeout.toSeconds(), TimeUnit.SECONDS))
                    .isEqualTo("false");
            assertThat(session.evaluateJavascript("-7").get(timeout.toSeconds(), TimeUnit.SECONDS))
                    .isEqualTo("-7");
            assertThat(Double.parseDouble(
                            session.evaluateJavascript("Math.PI").get(timeout.toSeconds(), TimeUnit.SECONDS)))
                    .isEqualTo(Math.PI);
            assertThat(session.evaluateJavascript("'hello'").get(timeout.toSeconds(), TimeUnit.SECONDS))
                    .isIn("hello", "\"hello\"");
            assertThat(unquote(session.evaluateJavascript("document.getElementById('marker').textContent")
                            .get(timeout.toSeconds(), TimeUnit.SECONDS)))
                    .isEqualTo("first");

            session.loadUrl(site.url("/second")).get(timeout.toSeconds(), TimeUnit.SECONDS);
            assertEventuallyEquals(session, "document.getElementById('marker').textContent", "second", timeout);

            if (backend.capabilities().contains(BrowserBackend.Capability.VIEWPORT_RESIZE)) {
                session.resizeViewport(512, 384).get(timeout.toSeconds(), TimeUnit.SECONDS);
                assertPaint(session.awaitPaint(512, 384, timeout), 512, 384);
                assertEventuallyEquals(session, "window.innerWidth + 'x' + window.innerHeight", "512x384", timeout);
            }
        }
    }

    private static final class FixtureSite implements AutoCloseable {
        private final HttpServer server;

        private FixtureSite(HttpServer server) {
            this.server = server;
        }

        static FixtureSite start() throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
            server.createContext(
                    "/first",
                    exchange -> respond(
                            exchange,
                            "<html><body style='margin:0;background:rgb(255,0,0)'>"
                                    + "<span id='marker'>first</span></body></html>"));
            server.createContext(
                    "/second",
                    exchange -> respond(exchange, "<html><body><span id='marker'>second</span></body></html>"));
            server.start();
            return new FixtureSite(server);
        }

        String url(String path) {
            String host = server.getAddress().getAddress().getHostAddress();
            if (host.contains(":")) host = "[" + host + "]";
            return "http://" + host + ":" + server.getAddress().getPort() + path;
        }

        private static void respond(HttpExchange exchange, String html) throws IOException {
            byte[] body = html.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, body.length);
            try (java.io.OutputStream response = exchange.getResponseBody()) {
                response.write(body);
            }
        }

        @Override
        public void close() {
            server.stop(0);
        }
    }

    private static void assertPaint(BrowserSession.PaintInfo paint, int width, int height) {
        assertThat(paint.width).isEqualTo(width);
        assertThat(paint.height).isEqualTo(height);
        assertThat(paint.byteCount).isEqualTo((long) width * height * 4L);
    }

    private static void assertEventuallyEquals(
            BrowserSession session, String expression, String expected, Duration timeout) throws Exception {
        long deadline = System.nanoTime() + timeout.toNanos();
        String value = null;
        while (System.nanoTime() < deadline) {
            value = unquote(session.evaluateJavascript(expression).get(timeout.toSeconds(), TimeUnit.SECONDS));
            if (expected.equals(value)) return;
            Thread.sleep(50);
        }
        assertThat(value).isEqualTo(expected);
    }

    private static String unquote(String value) {
        if (value != null && value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
}
