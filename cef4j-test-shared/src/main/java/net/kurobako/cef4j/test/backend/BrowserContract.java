package net.kurobako.cef4j.test.backend;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import net.kurobako.cef4j.test.TestDeadline;
import org.junit.jupiter.api.Assumptions;

/** Shared behavioural contract run unchanged against in-process and Remote CEF browser surfaces. */
public final class BrowserContract {
    private static final Duration MAX_PAINT_ATTEMPT = Duration.ofSeconds(5);
    private static final Duration MAX_SCRIPT_ATTEMPT = Duration.ofSeconds(5);
    private static final Duration CONTRACT_TIMEOUT = Duration.ofMinutes(2);

    private BrowserContract() {}

    public static void verify(BrowserBackend backend) throws Exception {
        Assumptions.assumeTrue(backend.isAvailable(), () -> backend.name() + " unavailable");
        try (FixtureSite site = FixtureSite.start()) {
            verify(backend, site);
        }
    }

    private static void verify(BrowserBackend backend, FixtureSite site) throws Exception {
        final Duration timeout = Duration.ofSeconds(40);
        final BrowserBackend.SessionConfig config =
                new BrowserBackend.SessionConfig(site.url("/first"), 640, 480, timeout);

        try (BrowserSession session = backend.openSession(config)) {
            final TestDeadline deadline = TestDeadline.after(CONTRACT_TIMEOUT);
            final PaintTarget initial = new PaintTarget(640, 480, deadline.remainingUpTo(timeout));
            assertPaint(
                    awaitInitialPaint(
                            session,
                            backend.capabilities().contains(BrowserBackend.Capability.VIEWPORT_RESIZE),
                            initial),
                    initial.width,
                    initial.height);

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
            awaitJavascriptReady(session, deadline.remainingUpTo(timeout));
            assertThat(unquote(deadline.await(
                            session.evaluateJavascript("document.getElementById('marker').textContent"),
                            "read second marker",
                            timeout)))
                    .isEqualTo("second");

            if (backend.capabilities().contains(BrowserBackend.Capability.VIEWPORT_RESIZE)) {
                final PaintTarget resized = new PaintTarget(512, 384, deadline.remainingUpTo(timeout));
                assertPaint(resizeUntilPaint(session, resized), resized.width, resized.height);
            }
        }
    }

    private static final class FixtureSite implements AutoCloseable {
        private final HttpServer server;

        private FixtureSite(HttpServer server) {
            this.server = server;
        }

        static FixtureSite start() throws IOException {
            final HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
            server.createContext(
                    "/first",
                    exchange -> respond(
                            exchange,
                            "<html><body style='margin:0;background:rgb(255,0,0)'>"
                                    + "<span id='marker'>first</span><script>void 0</script></body></html>"));
            server.createContext(
                    "/second",
                    exchange -> respond(
                            exchange,
                            "<html><body><span id='marker'>second</span><script>void 0</script></body></html>"));
            server.start();
            return new FixtureSite(server);
        }

        String url(String path) {
            String host = server.getAddress().getAddress().getHostAddress();
            if (host.contains(":")) host = "[" + host + "]";
            return "http://" + host + ":" + server.getAddress().getPort() + path;
        }

        private static void respond(HttpExchange exchange, String html) throws IOException {
            final byte[] body = html.getBytes(StandardCharsets.UTF_8);
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

    static BrowserSession.PaintInfo awaitInitialPaint(
            BrowserSession session, boolean supportsResize, PaintTarget target) throws Exception {
        return supportsResize ? resizeUntilPaint(session, target) : session.awaitPaint(target);
    }

    static BrowserSession.PaintInfo resizeUntilPaint(BrowserSession session, PaintTarget target) throws Exception {
        final TestDeadline deadline = TestDeadline.after(target.timeout);
        TimeoutException lastTimeout = null;
        final int width = target.width;
        final int height = target.height;
        final String expectedViewport = width + "x" + height;
        while (deadline.hasTimeRemaining()) {
            try {
                deadline.await(session.resizeViewport(width, height), "resize viewport", MAX_PAINT_ATTEMPT);
                final BrowserSession.PaintInfo paint =
                        session.awaitPaint(new PaintTarget(width, height, deadline.remainingUpTo(MAX_PAINT_ATTEMPT)));
                final String viewport = unquote(Objects.requireNonNull(
                        deadline.await(
                                session.evaluateJavascript("window.innerWidth + 'x' + window.innerHeight"),
                                "verify resized viewport",
                                MAX_PAINT_ATTEMPT),
                        "verify resized viewport: the future completed with null"));
                if (expectedViewport.equals(viewport)) return paint;
            } catch (TimeoutException timeoutException) {
                lastTimeout = timeoutException;
                if (deadline.hasTimeRemaining()) {
                    final int alternateWidth = width == 1 ? 2 : width - 1;
                    try {
                        deadline.await(
                                session.resizeViewport(alternateWidth, height),
                                "provoke viewport resize",
                                MAX_PAINT_ATTEMPT);
                        session.awaitNextPaint(deadline.remainingUpTo(MAX_PAINT_ATTEMPT));
                    } catch (TimeoutException provokeTimeout) {
                        timeoutException.addSuppressed(provokeTimeout);
                    }
                }
            }
        }
        final TimeoutException exhausted =
                new TimeoutException("no " + target.width + "x" + target.height + " paint within " + target.timeout);
        if (lastTimeout != null) exhausted.initCause(lastTimeout);
        throw exhausted;
    }

    static void awaitJavascriptReady(BrowserSession session, Duration timeout) throws Exception {
        final TestDeadline deadline = TestDeadline.after(timeout);
        TimeoutException lastTimeout = null;
        while (deadline.hasTimeRemaining()) {
            try {
                assertThat(deadline.await(
                                session.evaluateJavascript("1"), "probe renderer script readiness", MAX_SCRIPT_ATTEMPT))
                        .isEqualTo("1");
                return;
            } catch (TimeoutException timeoutException) {
                // Some CEF releases report load completion before browser-info routing is ready.
                lastTimeout = timeoutException;
            }
        }
        final TimeoutException exhausted =
                new TimeoutException("renderer script channel was not ready within " + timeout);
        if (lastTimeout != null) exhausted.initCause(lastTimeout);
        throw exhausted;
    }

    private static String unquote(String value) {
        if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
}
