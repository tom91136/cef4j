package net.kurobako.cef4j.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(10)
class CefHttpURLConnectionTest {

    private static CefHttpURLConnection conn(String s, FakeCefHttpEngine engine) throws Exception {
        URL u = new URL(null, s, new CefStreamHandler(engine));
        return (CefHttpURLConnection) u.openConnection();
    }

    @Test
    void getFiresEngineWithUrlMethodAndNoBody() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(200, Map.of(), new byte[0]);
        CefHttpURLConnection c = conn("http://example.com/foo?bar=1", engine);
        c.connect();
        assertThat(engine.capturedSpec().url).isEqualTo("http://example.com/foo?bar=1");
        assertThat(engine.capturedSpec().method).isEqualTo("GET");
        assertThat(engine.capturedSpec().body).isEmpty();
    }

    @Test
    void requestHeadersFlowThrough() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(200, Map.of(), new byte[0]);
        CefHttpURLConnection c = conn("http://example.com/", engine);
        c.setRequestProperty("X-Thing", "one");
        c.addRequestProperty("X-Thing", "two");
        c.setRequestProperty("Accept", "application/json");
        c.connect();
        Map<String, List<String>> sent = engine.capturedSpec().headers;
        // URLConnection does not specify the order of values returned by getRequestProperties();
        // OpenJDK currently exposes repeated values newest-first. The bridge's contract is to
        // preserve every value, not to impose an ordering the source API does not guarantee.
        assertThat(sent.get("X-Thing")).containsExactlyInAnyOrder("one", "two");
        assertThat(sent.get("Accept")).containsExactly("application/json");
    }

    @Test
    void responseCodeAndHeadersSurface() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine()
                .stage(
                        201,
                        Map.of(
                                "Content-Type", List.of("text/plain"),
                                "X-Multi", List.of("a", "b")),
                        new byte[0]);
        CefHttpURLConnection c = conn("http://example.com/", engine);
        assertThat(c.getResponseCode()).isEqualTo(201);
        assertThat(c.getHeaderField("Content-Type")).isEqualTo("text/plain");
        assertThat(c.getHeaderFields().get("X-Multi")).containsExactly("a", "b");
    }

    @Test
    void responseBodyReadsThroughInputStream() throws Exception {
        byte[] body = "hello, world".getBytes(StandardCharsets.UTF_8);
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(200, Map.of(), body);
        CefHttpURLConnection c = conn("http://example.com/", engine);
        try (InputStream in = c.getInputStream()) {
            assertThat(in.readAllBytes()).isEqualTo(body);
        }
    }

    @Test
    void multipleChunksConcatenateInOrder() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine();
        CefHttpURLConnection c = conn("http://example.com/", engine);
        // connect() fires send() (capturing lastSink) but does not wait for a response.
        // Headers/body arrive asynchronously from the driver thread below; the reader blocks
        // until they do, exercising the async-to-sync bridge.
        c.connect();
        Thread t = new Thread(() -> {
            engine.capturedSink().onResponse(200, "OK", Map.of());
            engine.capturedSink().onData("one ".getBytes(StandardCharsets.UTF_8));
            engine.capturedSink().onData("two ".getBytes(StandardCharsets.UTF_8));
            engine.capturedSink().onData("three".getBytes(StandardCharsets.UTF_8));
            engine.capturedSink().onComplete();
        });
        t.start();
        try (InputStream in = c.getInputStream()) {
            assertThat(new String(in.readAllBytes(), StandardCharsets.UTF_8)).isEqualTo("one two three");
        }
        t.join();
    }

    @Test
    void postSendsBodyBytes() throws Exception {
        byte[] reqBody = "{\"a\":1}".getBytes(StandardCharsets.UTF_8);
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(200, Map.of(), new byte[0]);
        CefHttpURLConnection c = conn("http://example.com/", engine);
        c.setRequestMethod("POST");
        c.setDoOutput(true);
        try (OutputStream out = c.getOutputStream()) {
            out.write(reqBody);
        }
        c.connect();
        assertThat(engine.capturedSpec().method).isEqualTo("POST");
        assertThat(engine.capturedSpec().body).isEqualTo(reqBody);
    }

    @Test
    void errorIsSurfacedAsIOException() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stageError(new IOException("boom"));
        CefHttpURLConnection c = conn("http://example.com/", engine);
        assertThatIOException().isThrownBy(c::getResponseCode).withMessageContaining("boom");
    }

    @Test
    void disconnectCallsEngineCancel() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(200, Map.of(), new byte[0]);
        CefHttpURLConnection c = conn("http://example.com/", engine);
        c.connect();
        c.disconnect();
        assertThat(engine.cancelled).isTrue();
    }

    @Test
    void connectIsIdempotent() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(200, Map.of(), new byte[0]);
        CefHttpURLConnection c = conn("http://example.com/", engine);
        c.connect();
        c.connect();
        c.connect();
        assertThat(engine.sendCount()).isEqualTo(1);
    }

    @Test
    void errorStatusRoutesBodyToErrorStream() throws Exception {
        byte[] body = "server broke".getBytes(StandardCharsets.UTF_8);
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(500, Map.of(), body);
        CefHttpURLConnection c = conn("http://example.com/", engine);
        assertThat(c.getResponseCode()).isEqualTo(500);
        assertThatIOException().isThrownBy(c::getInputStream);
        InputStream err = c.getErrorStream();
        assertThat(err).isNotNull();
        try (InputStream in = java.util.Objects.requireNonNull(err)) {
            assertThat(in.readAllBytes()).isEqualTo(body);
        }
    }

    @Test
    void usingProxyDefaultsFalse() throws Exception {
        CefHttpURLConnection c = conn("http://example.com/", new FakeCefHttpEngine());
        assertThat(c.usingProxy()).isFalse();
    }

    @Test
    void responseMessageReturnsStatusText() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(200, Map.of(), new byte[0]);
        CefHttpURLConnection c = conn("http://example.com/", engine);
        assertThat(c.getResponseMessage()).isEqualTo("OK");
    }
}
