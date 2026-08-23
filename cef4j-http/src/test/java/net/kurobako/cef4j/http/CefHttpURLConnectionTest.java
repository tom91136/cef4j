package net.kurobako.cef4j.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(60)
class CefHttpURLConnectionTest {

    private static CefHttpURLConnection conn(String s, CefHttpEngine engine) throws Exception {
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

    @Test
    void zeroLengthReadDoesNotBlock() throws Exception {
        FakeCefHttpEngine engine = FakeCefHttpEngine.empty();
        CefHttpURLConnection c = conn("http://example.com/", engine);
        c.connect();
        engine.capturedSink().onResponse(200, "OK", Map.of());
        InputStream in = c.getInputStream();
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
            try {
                return in.read(new byte[0], 0, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        assertThat(result.get(2, TimeUnit.SECONDS)).isZero();
        c.disconnect();
    }

    @Test
    void midBodyFailureSurfacesAsIOException() throws Exception {
        FakeCefHttpEngine engine = FakeCefHttpEngine.empty();
        CefHttpURLConnection c = conn("http://example.com/", engine);
        c.connect();
        engine.capturedSink().onResponse(200, "OK", Map.of());
        InputStream in = c.getInputStream();
        engine.capturedSink().onData("part".getBytes(StandardCharsets.UTF_8));
        engine.capturedSink().onError(new IOException("boom"));
        assertThatIOException().isThrownBy(in::readAllBytes).withMessageContaining("boom");
        c.disconnect();
    }

    @Test
    void followsRedirectToFinalUrl() throws Exception {
        List<String> requestedUrls = new ArrayList<>();
        CefHttpEngine redirecting = (spec, sink) -> {
            requestedUrls.add(spec.url);
            if (spec.url.equals("http://example.com/start")) {
                sink.onResponse(302, "Found", Map.of("Location", List.of("http://example.com/new")));
                sink.onComplete();
            } else {
                sink.onResponse(200, "OK", Map.of());
                sink.onData("final".getBytes(StandardCharsets.UTF_8));
                sink.onComplete();
            }
            return () -> {};
        };
        CefHttpURLConnection c = conn("http://example.com/start", redirecting);
        assertThat(c.getResponseCode()).isEqualTo(200);
        assertThat(requestedUrls).containsExactly("http://example.com/start", "http://example.com/new");
        assertThat(c.getURL().toString()).isEqualTo("http://example.com/new");
        try (InputStream in = c.getInputStream()) {
            assertThat(in.readAllBytes()).isEqualTo("final".getBytes(StandardCharsets.UTF_8));
        }
    }

    @Test
    void crossOriginRedirectStripsCredentialsAndConnectionHeaders() throws Exception {
        List<CefHttpEngine.RequestSpec> specs = new ArrayList<>();
        CefHttpEngine engine = (spec, sink) -> {
            specs.add(spec);
            if (specs.size() == 1) {
                sink.onResponse(302, "Found", Map.of("Location", List.of("https://other.example/end")));
            } else {
                sink.onResponse(200, "OK", Map.of());
                sink.onComplete();
            }
            return () -> {};
        };
        CefHttpURLConnection c = conn("http://example.com/start", engine);
        c.setRequestProperty("Authorization", "Bearer secret");
        c.setRequestProperty("Cookie", "session=secret");
        c.setRequestProperty("Connection", "close");
        c.setRequestProperty("Host", "spoofed.example");
        c.setRequestProperty("X-Trace", "kept");

        assertThat(c.getResponseCode()).isEqualTo(200);
        assertThat(specs).hasSize(2);
        assertThat(specs.get(0).headers).containsEntry("Authorization", List.of("Bearer secret"));
        assertThat(specs.get(0).headers).doesNotContainKeys("Connection", "Host");
        assertThat(specs.get(1).headers)
                .containsEntry("X-Trace", List.of("kept"))
                .doesNotContainKeys("Authorization", "Cookie", "Connection", "Host");
    }

    @Test
    void sameOriginRedirectPreservesCredentials() throws Exception {
        List<CefHttpEngine.RequestSpec> specs = new ArrayList<>();
        CefHttpEngine engine = (spec, sink) -> {
            specs.add(spec);
            if (specs.size() == 1) sink.onResponse(302, "Found", Map.of("Location", List.of("/end")));
            else {
                sink.onResponse(200, "OK", Map.of());
                sink.onComplete();
            }
            return () -> {};
        };
        CefHttpURLConnection c = conn("http://example.com/start", engine);
        c.setRequestProperty("Authorization", "Bearer secret");
        assertThat(c.getResponseCode()).isEqualTo(200);
        assertThat(specs.get(1).headers).containsEntry("Authorization", List.of("Bearer secret"));
    }

    @Test
    void staleRedirectCallbacksCannotCorruptFinalBody() throws Exception {
        List<CefHttpEngine.ResponseSink> sinks = new ArrayList<>();
        AtomicBoolean firstCancelled = new AtomicBoolean();
        CefHttpEngine engine = (spec, sink) -> {
            sinks.add(sink);
            if (sinks.size() == 1) {
                sink.onResponse(302, "Found", Map.of("Location", List.of("/end")));
                return () -> firstCancelled.set(true);
            }
            sink.onResponse(200, "OK", Map.of());
            sink.onData("final".getBytes(StandardCharsets.UTF_8));
            sinks.get(0).onData("stale".getBytes(StandardCharsets.UTF_8));
            sinks.get(0).onComplete();
            sink.onComplete();
            return () -> {};
        };
        CefHttpURLConnection c = conn("http://example.com/start", engine);
        assertThat(c.getInputStream().readAllBytes()).isEqualTo("final".getBytes(StandardCharsets.UTF_8));
        assertThat(firstCancelled).isTrue();
    }

    @Test
    void connectTimeoutBoundsHeaderWait() throws Exception {
        CefHttpURLConnection c = conn("http://example.com/", FakeCefHttpEngine.empty());
        c.setConnectTimeout(25);
        assertThatExceptionOfType(SocketTimeoutException.class).isThrownBy(c::getResponseCode);
        c.disconnect();
    }

    @Test
    void readTimeoutBoundsBodyWait() throws Exception {
        FakeCefHttpEngine engine = FakeCefHttpEngine.empty();
        CefHttpURLConnection c = conn("http://example.com/", engine);
        c.setReadTimeout(25);
        c.connect();
        engine.capturedSink().onResponse(200, "OK", Map.of());
        InputStream input = c.getInputStream();
        assertThatExceptionOfType(SocketTimeoutException.class).isThrownBy(input::read);
        c.disconnect();
    }

    @Test
    void disconnectReleasesHeaderWaiter() throws Exception {
        FakeCefHttpEngine engine = FakeCefHttpEngine.empty();
        CefHttpURLConnection c = conn("http://example.com/", engine);
        CompletableFuture<Integer> response = CompletableFuture.supplyAsync(() -> {
            try {
                return c.getResponseCode();
            } catch (IOException failure) {
                throw new java.util.concurrent.CompletionException(failure);
            }
        });
        while (engine.sendCount() == 0) Thread.onSpinWait();
        c.disconnect();
        assertThatExceptionOfType(java.util.concurrent.ExecutionException.class)
                .isThrownBy(() -> response.get(2, TimeUnit.SECONDS))
                .withRootCauseInstanceOf(IOException.class);
    }

    @Test
    void requestBodyBeyondLimitFails() throws Exception {
        FakeCefHttpEngine engine = new FakeCefHttpEngine().stage(200, Map.of(), new byte[0]);
        CefHttpURLConnection c = new CefHttpURLConnection(new URL("http://example.com/"), engine, 16);
        c.setRequestMethod("POST");
        c.setDoOutput(true);
        OutputStream out = c.getOutputStream();
        out.write(new byte[16]);
        assertThatIOException().isThrownBy(() -> out.write(1)).withMessageContaining("exceeds");
        c.disconnect();
    }

    @Test
    void closingResponseBodyCancelsRequest() throws Exception {
        FakeCefHttpEngine engine = FakeCefHttpEngine.empty();
        CefHttpURLConnection c = conn("http://example.com/", engine);
        c.connect();
        engine.capturedSink().onResponse(200, "OK", Map.of());

        c.getInputStream().close();

        assertThat(engine.cancelled).isTrue();
    }

    @Test
    void responseBufferOverflowCancelsRequestAndFailsReader() throws Exception {
        FakeCefHttpEngine engine = FakeCefHttpEngine.empty();
        CefHttpURLConnection c = new CefHttpURLConnection(new URL("http://example.com/"), engine, 16, 4, 2);
        c.connect();
        engine.capturedSink().onResponse(200, "OK", Map.of());
        engine.capturedSink().onData(new byte[] {1, 2, 3});
        engine.capturedSink().onData(new byte[] {4, 5});

        assertThat(engine.cancelled).isTrue();
        assertThatIOException()
                .isThrownBy(() -> c.getInputStream().readAllBytes())
                .withMessageContaining("buffer");
    }

    @Test
    void responseChunkCountOverflowCancelsRequest() throws Exception {
        FakeCefHttpEngine engine = FakeCefHttpEngine.empty();
        CefHttpURLConnection c = new CefHttpURLConnection(new URL("http://example.com/"), engine, 16, 32, 1);
        c.connect();
        engine.capturedSink().onResponse(200, "OK", Map.of());
        engine.capturedSink().onData(new byte[] {1});
        engine.capturedSink().onData(new byte[] {2});

        assertThat(engine.cancelled).isTrue();
    }
}
