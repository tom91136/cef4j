package net.kurobako.cef4j.http;

import static org.assertj.core.api.Assertions.assertThat;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.test.CefTestLaunch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

/**
 * End-to-end through real CEF: starts a {@link HttpServer} fixture, opens connections via {@link CefStreamHandler}
 * backed by {@link CefUrlRequestHttpEngine#INSTANCE}, and verifies the response round-trips through Chromium's network
 * stack.
 *
 * <p>Runs in its own surefire fork because it boots CEF in daemon-thread mode (matching {@code CefDaemonRenderTest}).
 * Linux requires a display - wrap with {@code xvfb-run}.
 */
@Timeout(60)
class CefHttpIntegrationTest {

    // CEF owns files in this cache until the isolated fork exits. JUnit's default cleanup runs
    // before process exit and races Chromium recreating SingletonSocket, so leave it to the CI
    // worker's temporary-filesystem cleanup instead.
    @TempDir(cleanup = CleanupMode.NEVER)
    @SuppressWarnings("NullAway.Init")
    static Path tempDir;

    @SuppressWarnings("NullAway.Init")
    private static HttpServer server;

    private static int port;

    @BeforeAll
    static void setup() throws Exception {
        SystemBootstrap.load();
        if (Cef.INSTANCE.state() != Cef.State.INITIALISED) {
            Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));
            CefSettings.Mutable settings = new CefSettings.Mutable();
            settings.cachePath = cacheDir.toAbsolutePath().toString();
            settings.windowlessRenderingEnabled = 1;
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 0;
            Cef.INSTANCE.initialise(settings, CefTestLaunch.extraArgs());
        }

        server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        server.createContext("/text", ex -> respond(ex, 200, "hello from fixture"));
        server.createContext("/missing", ex -> respond(ex, 404, "nope"));
        server.createContext("/echo", ex -> {
            byte[] body = ex.getRequestBody().readAllBytes();
            respond(ex, 200, new String(body, StandardCharsets.UTF_8));
        });
        server.createContext("/header-echo", ex -> {
            String x = ex.getRequestHeaders().getFirst("X-Probe");
            respond(ex, 200, x == null ? "" : x);
        });
        server.start();
        port = server.getAddress().getPort();
    }

    @AfterAll
    static void teardown() {
        if (server != null) server.stop(0);
        // Don't terminate CEF; isolated fork handles cleanup at process exit.
    }

    private static void respond(HttpExchange ex, int status, String body) throws IOException {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(status, b.length);
        try (OutputStream out = ex.getResponseBody()) {
            out.write(b);
        }
    }

    private static CefHttpURLConnection openCef(String path) throws Exception {
        URL u = new URL(
                null, "http://127.0.0.1:" + port + path, new CefStreamHandler(CefUrlRequestHttpEngine.INSTANCE));
        return (CefHttpURLConnection) u.openConnection();
    }

    @Test
    void fetchesSimpleText() throws Exception {
        CefHttpURLConnection c = openCef("/text");
        assertThat(c.getResponseCode()).isEqualTo(200);
        try (InputStream in = c.getInputStream()) {
            assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("hello from fixture");
        }
    }

    @Test
    void notFoundRoutesToErrorStream() throws Exception {
        CefHttpURLConnection c = openCef("/missing");
        assertThat(c.getResponseCode()).isEqualTo(404);
        InputStream err = c.getErrorStream();
        assertThat(err).isNotNull();
        try (InputStream in = Objects.requireNonNull(err)) {
            assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("nope");
        }
    }

    @Test
    void postEchoesBody() throws Exception {
        CefHttpURLConnection c = openCef("/echo");
        c.setRequestMethod("POST");
        c.setDoOutput(true);
        try (OutputStream out = c.getOutputStream()) {
            out.write("ping".getBytes(StandardCharsets.UTF_8));
        }
        assertThat(c.getResponseCode()).isEqualTo(200);
        try (InputStream in = c.getInputStream()) {
            assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("ping");
        }
    }

    @Test
    void requestHeadersReachServer() throws Exception {
        CefHttpURLConnection c = openCef("/header-echo");
        c.setRequestProperty("X-Probe", "secret-value");
        try (InputStream in = c.getInputStream()) {
            assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("secret-value");
        }
    }
}
