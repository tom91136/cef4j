package net.kurobako.cef4j.test.backend;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.test.TestDeadline;
import org.junit.jupiter.api.Assumptions;

/** Shared behavioural contract run unchanged against in-process and Remote CEF browser surfaces. */
public final class BrowserContract {
    private static final Duration MAX_EVALUATION_ATTEMPT = Duration.ofSeconds(5);
    private static final Duration MAX_PAINT_ATTEMPT = Duration.ofSeconds(5);
    private static final Duration CONTRACT_TIMEOUT = Duration.ofMinutes(2);

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
            TestDeadline deadline = TestDeadline.after(CONTRACT_TIMEOUT);
            assertPaint(session.awaitPaint(640, 480, deadline.remainingUpTo(timeout)), 640, 480);

            assertThat(deadline.await(session.evaluateJavascript("1 + 2 + 3"), "evaluate arithmetic", timeout))
                    .isEqualTo("6");
            assertThat(deadline.await(session.evaluateJavascript("true"), "evaluate true", timeout))
                    .isEqualTo("true");
            assertThat(deadline.await(session.evaluateJavascript("false"), "evaluate false", timeout))
                    .isEqualTo("false");
            assertThat(deadline.await(session.evaluateJavascript("-7"), "evaluate integer", timeout))
                    .isEqualTo("-7");
            assertThat(Double.parseDouble(
                            deadline.await(session.evaluateJavascript("Math.PI"), "evaluate floating point", timeout)))
                    .isEqualTo(Math.PI);
            assertThat(deadline.await(session.evaluateJavascript("'hello'"), "evaluate string", timeout))
                    .isIn("hello", "\"hello\"");
            assertThat(unquote(deadline.await(
                            session.evaluateJavascript("document.getElementById('marker').textContent"),
                            "read initial marker",
                            timeout)))
                    .isEqualTo("first");

            deadline.await(session.loadUrl(site.url("/second")), "navigate to second page", timeout);
            assertEventuallyEquals(
                    session, "document.getElementById('marker').textContent", "second", deadline, timeout);

            if (backend.capabilities().contains(BrowserBackend.Capability.VIEWPORT_RESIZE)) {
                assertPaint(resizeUntilPaint(session, 512, 384, deadline.remainingUpTo(timeout)), 512, 384);
                assertEventuallyEquals(
                        session, "window.innerWidth + 'x' + window.innerHeight", "512x384", deadline, timeout);
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

    static void assertEventuallyEquals(BrowserSession session, String expression, String expected, Duration timeout)
            throws Exception {
        assertEventuallyEquals(session, expression, expected, TestDeadline.after(timeout), timeout);
    }

    static BrowserSession.PaintInfo resizeUntilPaint(BrowserSession session, int width, int height, Duration timeout)
            throws Exception {
        TestDeadline deadline = TestDeadline.after(timeout);
        TimeoutException lastTimeout = null;
        while (!deadline.isExpired()) {
            try {
                deadline.await(session.resizeViewport(width, height), "resize viewport", MAX_PAINT_ATTEMPT);
                return session.awaitPaint(width, height, deadline.remainingUpTo(MAX_PAINT_ATTEMPT));
            } catch (TimeoutException timeoutException) {
                lastTimeout = timeoutException;
            }
        }
        TimeoutException exhausted = new TimeoutException("no " + width + "x" + height + " paint within " + timeout);
        if (lastTimeout != null) exhausted.initCause(lastTimeout);
        throw exhausted;
    }

    static void assertEventuallyEquals(
            BrowserSession session,
            String expression,
            String expected,
            TestDeadline deadline,
            Duration operationTimeout)
            throws Exception {
        long attemptTimeout = Math.max(1L, Math.min(MAX_EVALUATION_ATTEMPT.toNanos(), operationTimeout.toNanos() / 4L));
        String value = null;
        TimeoutException lastTimeout = null;
        Duration phaseBudget = deadline.remainingUpTo(operationTimeout);
        TestDeadline phaseDeadline = TestDeadline.after(phaseBudget);
        while (!phaseDeadline.isExpired()) {
            CompletableFuture<String> evaluation = session.evaluateJavascript(expression);
            try {
                value = unquote(
                        phaseDeadline.await(evaluation, "evaluate " + expression, Duration.ofNanos(attemptTimeout)));
                lastTimeout = null;
            } catch (TimeoutException timeoutException) {
                evaluation.cancel(true);
                lastTimeout = timeoutException;
                continue;
            }
            if (expected.equals(value)) return;
            Thread.sleep(50);
        }
        if (lastTimeout != null) throw lastTimeout;
        assertThat(value).isEqualTo(expected);
    }

    private static String unquote(String value) {
        if (value != null && value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
}
