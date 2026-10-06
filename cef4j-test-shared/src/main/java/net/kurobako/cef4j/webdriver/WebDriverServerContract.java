package net.kurobako.cef4j.webdriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.test.TestDeadline;
import org.junit.jupiter.api.Test;

public abstract class WebDriverServerContract {
    private final HttpClient client = HttpClient.newHttpClient();

    @Nonnull
    protected abstract WebDriverJsonCodec codec();

    @Test
    final void suppliedHttpExecutorRemainsCallerOwned() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            try (WebDriverServer server = WebDriverServer.start(
                    new InetSocketAddress(InetAddress.getLoopbackAddress(), 0),
                    capabilities -> CompletableFuture.completedFuture(new Backend()),
                    Duration.ofSeconds(5),
                    codec(),
                    executor)) {
                assertThat(statusReady(server)).isTrue();
            }
            assertThat(executor.isShutdown()).isFalse();
        } finally {
            executor.shutdownNow();
        }
    }

    @Test
    final void suppliedPollingExecutorRunsLoadCompletionPolling() throws Exception {
        AtomicInteger executions = new AtomicInteger();
        try (CdpAutomationBackend backend = CdpAutomationBackend.create(new StubCdpBrowser(codec()), command -> {
                    executions.incrementAndGet();
                    command.run();
                })
                .get(1, TimeUnit.SECONDS)) {
            backend.navigate("https://example.test").get(1, TimeUnit.SECONDS);
        }
        assertThat(executions).hasValue(1);
    }

    @Test
    @SuppressWarnings("NullAway")
    final void recoversAfterSynchronousFactoryFailures() throws Exception {
        AtomicInteger calls = new AtomicInteger();
        try (WebDriverServer server = WebDriverServer.start(
                capabilities -> {
                    int call = calls.getAndIncrement();
                    if (call == 0) throw new IllegalStateException("factory boom");
                    if (call == 1) return null;
                    return CompletableFuture.completedFuture(new Backend());
                },
                codec())) {
            assertError(request(server, "{\"capabilities\":{}}"), "session not created");
            assertThat(statusReady(server)).isTrue();
            assertError(request(server, "{\"capabilities\":{}}"), "session not created");
            assertThat(statusReady(server)).isTrue();
            assertThat(request(server, "{\"capabilities\":{}}").status).isEqualTo(200);
        }
    }

    @Test
    final void triesCapabilityCandidatesAndRejectsUnsatisfiedConstraints() throws Exception {
        java.util.List<Backend> backends = new java.util.ArrayList<>();
        try (WebDriverServer server = WebDriverServer.start(
                capabilities -> {
                    Backend backend = new Backend();
                    backends.add(backend);
                    return CompletableFuture.completedFuture(backend);
                },
                codec())) {
            Response matched =
                    request(server, "{\"capabilities\":{\"firstMatch\":[{\"browserVersion\":\"wrong\"},{}]}}");
            assertThat(matched.status).isEqualTo(200);
            assertThat(backends).hasSize(2);
            assertThat(backends.get(0).closed).isTrue();
        }
        assertUnsatisfiedCapability("\"acceptInsecureCerts\":true");
        assertUnsatisfiedCapability("\"platformName\":\"unsupported\"");
        assertUnsatisfiedCapability("\"pageLoadStrategy\":\"eager\"");
        assertUnsatisfiedCapability("\"setWindowRect\":true");
    }

    @Test
    final void validatesTimeoutNumbersWithoutLossyNarrowing() throws Exception {
        assertSessionStatus("9223372036854775807", 200);
        assertSessionStatus("1e3", 200);
        assertSessionStatus("9223372036854775808", 400);
        assertSessionStatus("1.5", 400);
        assertSessionStatus("-1", 400);
    }

    @Test
    final void rejectsCookieTypeCoercionAndInvalidRanges() {
        assertCookieFailure("{\"name\":7,\"value\":\"value\"}", "name must be a string");
        assertCookieFailure("{\"name\":\"name\",\"value\":\"value\",\"secure\":\"true\"}", "secure must be a boolean");
        assertCookieFailure(
                "{\"name\":\"name\",\"value\":\"value\",\"expiry\":-1}", "expiry must be a non-negative integer");
        assertCookieFailure(
                "{\"name\":\"name\",\"value\":\"value\",\"expiry\":1.5}", "expiry must be a non-negative integer");
        assertCookieFailure(
                "{\"name\":\"name\",\"value\":\"value\",\"sameSite\":\"sometimes\"}", "invalid cookie sameSite");
    }

    @Test
    final void deletingSessionCancelsActiveCommandBeforeClosingBackend() throws Exception {
        BlockingBackend backend = new BlockingBackend();
        try (WebDriverServer server =
                WebDriverServer.start(capabilities -> CompletableFuture.completedFuture(backend), codec())) {
            String sessionId = request(server, "{\"capabilities\":{}}")
                    .value()
                    .get("sessionId")
                    .string();
            CompletableFuture<HttpResponse<String>> navigation = client.sendAsync(
                    HttpRequest.newBuilder(server.endpoint().resolve("/session/" + sessionId + "/url"))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString("{\"url\":\"https://pending.test\"}"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
            assertThat(backend.started.await(5, TimeUnit.SECONDS)).isTrue();

            CompletableFuture<HttpResponse<String>> deletion = client.sendAsync(
                    HttpRequest.newBuilder(server.endpoint().resolve("/session/" + sessionId))
                            .DELETE()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
            assertThat(backend.cancelObserved.await(5, TimeUnit.SECONDS)).isTrue();
            assertThat(backend.closed).isFalse();
            backend.allowCommandExit.countDown();
            HttpResponse<String> deleted = deletion.get(5, TimeUnit.SECONDS);

            assertThat(deleted.statusCode()).isEqualTo(200);
            assertThat(navigation.get(5, TimeUnit.SECONDS).statusCode()).isEqualTo(500);
            assertThat(backend.cancelled).isTrue();
            assertThat(backend.closed).isTrue();
        }
    }

    @Test
    final void deletingSessionStillClosesBackendWhenCancellationFails() throws Exception {
        CancelFailingBackend backend = new CancelFailingBackend();
        try (WebDriverServer server =
                WebDriverServer.start(capabilities -> CompletableFuture.completedFuture(backend), codec())) {
            String sessionId = request(server, "{\"capabilities\":{}}")
                    .value()
                    .get("sessionId")
                    .string();

            HttpResponse<String> deletion = client.send(
                    HttpRequest.newBuilder(server.endpoint().resolve("/session/" + sessionId))
                            .DELETE()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());

            assertThat(deletion.statusCode()).isEqualTo(200);
            assertThat(backend.closed).isTrue();
        }
    }

    @Test
    final void failedNewSessionResponseClosesBackendAndReleasesSession() throws Exception {
        Backend backend = new Backend();
        try (WebDriverServer server =
                WebDriverServer.start(capabilities -> CompletableFuture.completedFuture(backend), codec())) {
            Method handle = WebDriverServer.class.getDeclaredMethod("handle", HttpExchange.class);
            handle.setAccessible(true);
            handle.invoke(server, new FailingResponseExchange("{\"capabilities\":{}}"));

            assertThat(backend.closed).isTrue();
            assertThat(statusReady(server)).isTrue();
        }
    }

    @Test
    final void codecKeepsExplicitNullsAndExactNumbers() {
        String json = "{\"value\":null,\"items\":[1,null],\"int\":42,\"long\":9223372036854775807,"
                + "\"big\":9223372036854775808,\"decimal\":1.50,\"text\":\"<a&b>\"}";
        JsonObject decoded = codec().decode(json).asObject();
        assertThat(decoded.get("value").isNull()).isTrue();
        assertThat(decoded.array("items").get(1).isNull()).isTrue();
        assertThat(decoded.get("int").asPrimitive().value()).isEqualTo(42);
        assertThat(decoded.get("long").asPrimitive().value()).isEqualTo(Long.MAX_VALUE);
        assertThat(decoded.get("big").asPrimitive().value()).isEqualTo(new BigInteger("9223372036854775808"));
        assertThat(decoded.get("decimal").asPrimitive().value()).isEqualTo(new BigDecimal("1.50"));
        assertThat(new String(codec().encode(decoded), StandardCharsets.UTF_8)).isEqualTo(json);
    }

    @Test
    final void ownsOneSessionAndReportsW3cCapabilities() throws Exception {
        assertThat(WebDriverJsonCodec.installed().cdpCodec().getClass())
                .isEqualTo(codec().cdpCodec().getClass());
        AtomicReference<JsonObject> requested = new AtomicReference<>();
        ScriptedBackend backend = new ScriptedBackend();
        try (WebDriverServer server = WebDriverServer.start(capabilities -> {
            requested.set(capabilities.deepCopy());
            return CompletableFuture.completedFuture(backend);
        })) {
            Reply initial = exchange(server, "GET", "/status", null);
            assertThat(initial.status).isEqualTo(200);
            assertThat(initial.value().asObject().get("ready").booleanValue()).isTrue();

            String capabilities = "{\"capabilities\":{\"alwaysMatch\":{"
                    + "\"browserName\":\"cef4j\",\"pageLoadStrategy\":\"normal\"},"
                    + "\"firstMatch\":[{\"cef4j:options\":{\"transport\":\"uds\"}}]}}";
            Reply created = exchange(server, "POST", "/session", capabilities);

            assertThat(created.status).isEqualTo(200);
            JsonObject sessionValue = created.value().asObject();
            String sessionId = sessionValue.get("sessionId").string();
            assertThat(sessionId).isNotBlank();
            assertThat(sessionValue.object("capabilities").get("browserName").string())
                    .isEqualTo("cef4j");
            assertThat(java.util.Objects.requireNonNull(requested.get())
                            .object("cef4j:options")
                            .get("transport")
                            .string())
                    .isEqualTo("uds");

            Reply second = exchange(server, "POST", "/session", "{\"capabilities\":{}}");
            assertError(second, 500, "session not created");

            Reply deleted = exchange(server, "DELETE", "/session/" + sessionId, null);
            assertThat(deleted.status).isEqualTo(200);
            assertThat(deleted.value().isNull()).isTrue();
            assertThat(backend.closed).isTrue();
            assertThat(exchange(server, "GET", "/status", null)
                            .value()
                            .asObject()
                            .get("ready")
                            .booleanValue())
                    .isTrue();
        }
    }

    @Test
    final void routesInitialBrowserCommandSlice() throws Exception {
        ScriptedBackend backend = new ScriptedBackend();
        try (WebDriverServer server =
                WebDriverServer.start(ignored -> CompletableFuture.completedFuture(backend), codec())) {
            String prefix = "/session/" + createSession(server);

            assertThat(exchange(server, "POST", prefix + "/url", "{\"url\":\"https://example.test/page\"}")
                            .value()
                            .isNull())
                    .isTrue();
            assertThat(backend.url).isEqualTo("https://example.test/page");
            assertThat(exchange(server, "GET", prefix + "/url", null).value().string())
                    .isEqualTo("https://example.test/page");
            assertThat(exchange(server, "GET", prefix + "/title", null).value().string())
                    .isEqualTo("Fake title");
            assertThat(exchange(server, "GET", prefix + "/source", null).value().string())
                    .isEqualTo("<html>fake</html>");

            Reply script = exchange(
                    server,
                    "POST",
                    prefix + "/execute/sync",
                    "{\"script\":\"return arguments[0]\",\"args\":[{\"answer\":42}]}");
            assertThat(script.value().asObject().get("answer").intValue()).isEqualTo(42);
            assertThat(backend.lastScript).isEqualTo("return arguments[0]");

            assertThat(exchange(server, "GET", prefix + "/screenshot", null)
                            .value()
                            .string())
                    .isEqualTo("iVBORw0KGgo=");
        }
    }

    @Test
    final void returnsStandardErrorsForBadRequests() throws Exception {
        ScriptedBackend backend = new ScriptedBackend();
        try (WebDriverServer server =
                WebDriverServer.start(ignored -> CompletableFuture.completedFuture(backend), codec())) {
            assertError(exchange(server, "POST", "/session", "not-json"), 400, "invalid argument");
            assertError(
                    exchange(server, "POST", "/session", "{\"capabilities\":{\"alwaysMatch\":{\"unexpected\":true}}}"),
                    400,
                    "invalid argument");
            assertError(exchange(server, "GET", "/session/missing/url", null), 404, "invalid session id");

            String sessionId = createSession(server);
            assertError(
                    exchange(server, "POST", "/session/" + sessionId + "/execute/sync", "{\"script\":7,\"args\":[]}"),
                    400,
                    "invalid argument");
            assertError(
                    exchange(server, "GET", "/session/" + sessionId + "/not-a-command", null), 404, "unknown command");
        }
    }

    @Test
    final void mapsBackendTimeoutAndPreservesLoopbackDefault() throws Exception {
        ScriptedBackend backend = new ScriptedBackend();
        backend.hangNavigation = true;
        try (WebDriverServer server = WebDriverServer.start(
                new InetSocketAddress(InetAddress.getLoopbackAddress(), 0),
                ignored -> CompletableFuture.completedFuture(backend),
                Duration.ofMillis(50),
                codec())) {
            assertThat(server.endpoint().getHost()).isIn("127.0.0.1", "0:0:0:0:0:0:0:1", "::1");
            String sessionId = createSession(server);
            exchange(server, "POST", "/session/" + sessionId + "/timeouts", "{\"pageLoad\":50}");
            Reply response =
                    exchange(server, "POST", "/session/" + sessionId + "/url", "{\"url\":\"https://slow.test\"}");
            assertError(response, 500, "timeout");
            assertThat(backend.cancelledCommands).hasValue(1);
            assertThat(backend.closed).isFalse();
            assertThat(exchange(server, "GET", "/session/" + sessionId + "/timeouts", null).status)
                    .isEqualTo(200);
        }
    }

    @Test
    final void appliesTimeoutsRequestedAtSessionCreation() throws Exception {
        try (WebDriverServer server =
                WebDriverServer.start(ignored -> CompletableFuture.completedFuture(new ScriptedBackend()), codec())) {
            Reply created = exchange(
                    server,
                    "POST",
                    "/session",
                    "{\"capabilities\":{\"alwaysMatch\":{\"timeouts\":{\"implicit\":123}}}}");
            String sessionId = created.value().asObject().get("sessionId").string();

            JsonObject timeouts = exchange(server, "GET", "/session/" + sessionId + "/timeouts", null)
                    .value()
                    .asObject();
            assertThat(timeouts.get("implicit").longValue()).isEqualTo(123L);
        }
    }

    @Test
    final void closesBackendThatArrivesAfterSessionCreationTimeout() throws Exception {
        CompletableFuture<AutomationBackend> creation = new CompletableFuture<>();
        ScriptedBackend backend = new ScriptedBackend();
        try (WebDriverServer server = WebDriverServer.start(
                new InetSocketAddress(InetAddress.getLoopbackAddress(), 0),
                ignored -> creation,
                Duration.ofMillis(25),
                codec())) {
            Reply response = exchange(server, "POST", "/session", "{\"capabilities\":{}}");
            assertError(response, 500, "timeout");
            creation.complete(backend);
            TestDeadline.after(Duration.ofSeconds(1))
                    .until(backend.closed::get, Duration.ofMillis(2), "late backend cleanup");
            assertThat(backend.closed).isTrue();
        }
    }

    @Test
    final void implicitWaitRetriesElementSearch() throws Exception {
        ScriptedBackend backend = new ScriptedBackend();
        backend.emptySearches.set(2);
        try (WebDriverServer server =
                WebDriverServer.start(ignored -> CompletableFuture.completedFuture(backend), codec())) {
            String sessionId = createSession(server);
            exchange(server, "POST", "/session/" + sessionId + "/timeouts", "{\"implicit\":500}");
            Reply found = exchange(
                    server,
                    "POST",
                    "/session/" + sessionId + "/element",
                    "{\"using\":\"css selector\",\"value\":\"#eventual\"}");
            assertThat(found.status).isEqualTo(200);
            assertThat(backend.searches).hasValue(3);
        }
    }

    @Test
    final void maximumImplicitWaitDoesNotOverflowDeadline() throws Exception {
        ScriptedBackend backend = new ScriptedBackend();
        backend.emptySearches.set(1);
        try (WebDriverServer server =
                WebDriverServer.start(ignored -> CompletableFuture.completedFuture(backend), codec())) {
            String sessionId = createSession(server);
            exchange(server, "POST", "/session/" + sessionId + "/timeouts", "{\"implicit\":9223372036854775807}");
            Reply found = exchange(
                    server,
                    "POST",
                    "/session/" + sessionId + "/element",
                    "{\"using\":\"css selector\",\"value\":\"#eventual\"}");
            assertThat(found.status).isEqualTo(200);
            assertThat(backend.searches).hasValue(2);
        }
    }

    private String createSession(WebDriverServer server) throws IOException, InterruptedException {
        Reply response = exchange(server, "POST", "/session", "{\"capabilities\":{}}");
        assertThat(response.status).isEqualTo(200);
        return response.value().asObject().get("sessionId").string();
    }

    private Reply exchange(WebDriverServer server, String method, String path, @Nullable String body)
            throws IOException, InterruptedException {
        HttpRequest.BodyPublisher publisher =
                body == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(body);
        HttpRequest request = HttpRequest.newBuilder(server.endpoint().resolve(path))
                .method(method, publisher)
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new Reply(response.statusCode(), codec().decode(response.body()).asObject());
    }

    private static void assertError(Reply response, int status, String error) {
        assertThat(response.status).isEqualTo(status);
        JsonObject value = response.value().asObject();
        assertThat(value.get("error").string()).isEqualTo(error);
        assertThat(value.get("message").string()).isNotBlank();
        assertThat(value.get("stacktrace").string()).isEmpty();
    }

    private void assertCookieFailure(String json, String message) {
        JsonObject cookie = codec().decode(json).asObject();
        assertThatThrownBy(() -> CdpAutomationBackend.validateCookie(cookie))
                .isInstanceOf(WebDriverException.class)
                .hasMessageContaining(message);
    }

    private static final class StubCdpBrowser implements JsonCdpBrowser {
        private final WebDriverJsonCodec codec;

        private StubCdpBrowser(WebDriverJsonCodec codec) {
            this.codec = codec;
        }

        @Override
        public WebDriverJsonCodec jsonCodec() {
            return codec;
        }

        @Override
        public CompletableFuture<byte[]> execute(String method, @Nullable byte[] params) {
            if (!method.equals("Browser.getVersion")) {
                return CompletableFuture.failedFuture(new AssertionError("unexpected CDP command " + method));
            }
            JsonObject version = new JsonObject();
            version.addProperty("protocolVersion", "1.3");
            version.addProperty("product", "cef4j-test");
            version.addProperty("revision", "test");
            version.addProperty("userAgent", "cef4j-test");
            version.addProperty("jsVersion", "test");
            return CompletableFuture.completedFuture(codec.encode(version));
        }

        @Override
        public CdpSubscription subscribe(String method, Consumer<byte[]> handler) {
            return () -> {};
        }

        @Override
        public CompletableFuture<Void> loadUrl(String url) {
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public CompletableFuture<Boolean> canGoBack() {
            return CompletableFuture.completedFuture(false);
        }

        @Override
        public CompletableFuture<Void> goBack() {
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public CompletableFuture<Boolean> canGoForward() {
            return CompletableFuture.completedFuture(false);
        }

        @Override
        public CompletableFuture<Void> goForward() {
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public CompletableFuture<Boolean> loading() {
            return CompletableFuture.completedFuture(false);
        }

        @Override
        public void close() {}
    }

    private void assertSessionStatus(String timeout, int expectedStatus) throws Exception {
        try (WebDriverServer server =
                WebDriverServer.start(capabilities -> CompletableFuture.completedFuture(new Backend()), codec())) {
            Response response =
                    request(server, "{\"capabilities\":{\"alwaysMatch\":{\"timeouts\":{\"script\":" + timeout + "}}}}");
            assertThat(response.status).isEqualTo(expectedStatus);
            if (expectedStatus != 200) {
                assertThat(response.value().get("error").string()).isEqualTo("invalid argument");
            }
        }
    }

    private void assertUnsatisfiedCapability(String capability) throws Exception {
        try (WebDriverServer server =
                WebDriverServer.start(capabilities -> CompletableFuture.completedFuture(new Backend()), codec())) {
            assertError(
                    request(server, "{\"capabilities\":{\"alwaysMatch\":{" + capability + "}}}"),
                    "session not created");
        }
    }

    private boolean statusReady(WebDriverServer server) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(server.endpoint().resolve("/status"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return codec().decode(response.body())
                .asObject()
                .object("value")
                .get("ready")
                .booleanValue();
    }

    private Response request(WebDriverServer server, String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(server.endpoint().resolve("/session"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new Response(
                response.statusCode(),
                codec().decode(response.body()).asObject().object("value"));
    }

    private static void assertError(Response response, String expected) {
        assertThat(response.status).isEqualTo(500);
        assertThat(response.value().get("error").string()).isEqualTo(expected);
    }

    private static final class Response {
        private final int status;
        private final JsonObject value;

        private Response(int status, JsonObject value) {
            this.status = status;
            this.value = value;
        }

        private JsonObject value() {
            return value;
        }
    }

    private static final class Reply {
        private final int status;
        private final JsonObject body;

        private Reply(int status, JsonObject body) {
            this.status = status;
            this.body = body;
        }

        private JsonElement value() {
            return body.get("value");
        }
    }

    @SuppressWarnings("NullAway")
    private static final class FailingResponseExchange extends HttpExchange {
        private final byte[] requestBody;
        private int responseCode = -1;

        private FailingResponseExchange(String body) {
            requestBody = body.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        }

        @Override
        public Headers getRequestHeaders() {
            Headers headers = new Headers();
            headers.set("Content-Type", "application/json");
            return headers;
        }

        @Override
        public Headers getResponseHeaders() {
            return new Headers();
        }

        @Override
        public URI getRequestURI() {
            return URI.create("/session");
        }

        @Override
        public String getRequestMethod() {
            return "POST";
        }

        @Override
        public HttpContext getHttpContext() {
            return null;
        }

        @Override
        public void close() {}

        @Override
        public InputStream getRequestBody() {
            return new ByteArrayInputStream(requestBody);
        }

        @Override
        public OutputStream getResponseBody() {
            return new OutputStream() {
                @Override
                public void write(int value) throws IOException {
                    throw new IOException("client disconnected");
                }
            };
        }

        @Override
        public void sendResponseHeaders(int responseCode, long responseLength) {
            this.responseCode = responseCode;
        }

        @Override
        public int getResponseCode() {
            return responseCode;
        }

        @Override
        public InetSocketAddress getRemoteAddress() {
            return new InetSocketAddress(InetAddress.getLoopbackAddress(), 1);
        }

        @Override
        public InetSocketAddress getLocalAddress() {
            return new InetSocketAddress(InetAddress.getLoopbackAddress(), 2);
        }

        @Override
        public String getProtocol() {
            return "HTTP/1.1";
        }

        @Override
        public Object getAttribute(String name) {
            return null;
        }

        @Override
        public void setAttribute(String name, Object value) {}

        @Override
        public void setStreams(InputStream input, OutputStream output) {}

        @Override
        public HttpPrincipal getPrincipal() {
            return null;
        }
    }

    private static class Backend implements AutomationBackend {
        private final AtomicBoolean closed = new AtomicBoolean();

        @Override
        public JsonObject capabilities() {
            JsonObject result = new JsonObject();
            result.addProperty("browserName", "cef4j");
            result.addProperty("browserVersion", "contract-version");
            result.addProperty("platformName", "contract-platform");
            result.addProperty("acceptInsecureCerts", false);
            result.addProperty("pageLoadStrategy", "normal");
            result.addProperty("setWindowRect", false);
            result.addProperty("strictFileInteractability", false);
            return result;
        }

        @Override
        public CompletableFuture<Void> navigate(String url) {
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public CompletableFuture<String> currentUrl() {
            return CompletableFuture.completedFuture("about:blank");
        }

        @Override
        public CompletableFuture<String> title() {
            return CompletableFuture.completedFuture("");
        }

        @Override
        public CompletableFuture<String> pageSource() {
            return CompletableFuture.completedFuture("");
        }

        @Override
        public CompletableFuture<JsonElement> executeScript(String script, JsonArray arguments) {
            return CompletableFuture.completedFuture(arguments);
        }

        @Override
        public CompletableFuture<byte[]> screenshot() {
            return CompletableFuture.completedFuture(new byte[0]);
        }

        @Override
        public void close() {
            closed.set(true);
        }
    }

    private static final class ScriptedBackend extends Backend {
        private final AtomicBoolean closed = new AtomicBoolean();
        private final AtomicInteger cancelledCommands = new AtomicInteger();
        private final AtomicInteger searches = new AtomicInteger();
        private final AtomicInteger emptySearches = new AtomicInteger();
        private volatile String url = "about:blank";
        private volatile String lastScript = "";
        private volatile boolean hangNavigation;

        @Override
        public CompletableFuture<Void> navigate(String url) {
            this.url = url;
            return hangNavigation ? new CompletableFuture<>() : CompletableFuture.completedFuture(null);
        }

        @Override
        public CompletableFuture<String> currentUrl() {
            return CompletableFuture.completedFuture(url);
        }

        @Override
        public CompletableFuture<String> title() {
            return CompletableFuture.completedFuture("Fake title");
        }

        @Override
        public CompletableFuture<String> pageSource() {
            return CompletableFuture.completedFuture("<html>fake</html>");
        }

        @Override
        public CompletableFuture<JsonElement> executeScript(String script, JsonArray arguments) {
            lastScript = script;
            JsonElement result = arguments.size() == 0
                    ? new JsonPrimitive("no arguments")
                    : arguments.get(0).deepCopy();
            return CompletableFuture.completedFuture(result);
        }

        @Override
        public CompletableFuture<byte[]> screenshot() {
            return CompletableFuture.completedFuture(
                    new byte[] {(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A});
        }

        @Override
        public CompletableFuture<List<String>> findElements(
                String using, String value, Optional<String> parentElement) {
            searches.incrementAndGet();
            if (emptySearches.getAndDecrement() > 0) return CompletableFuture.completedFuture(List.of());
            return CompletableFuture.completedFuture(List.of("element-1"));
        }

        @Override
        public void cancelPendingCommands(Throwable failure) {
            cancelledCommands.incrementAndGet();
        }

        @Override
        public void close() {
            closed.set(true);
        }
    }

    private static final class BlockingBackend extends Backend {
        private final CountDownLatch started = new CountDownLatch(1);
        private final CountDownLatch cancelObserved = new CountDownLatch(1);
        private final CountDownLatch allowCommandExit = new CountDownLatch(1);
        private final AtomicReference<Consumer<Throwable>> cancelNavigation = new AtomicReference<>();
        private final AtomicBoolean cancelled = new AtomicBoolean();
        private final AtomicBoolean closed = new AtomicBoolean();

        @Override
        public CompletableFuture<Void> navigate(String url) {
            BlockingFuture navigation = new BlockingFuture(cancelObserved, allowCommandExit);
            cancelNavigation.set(navigation::completeExceptionally);
            started.countDown();
            return navigation;
        }

        @Override
        public void cancelPendingCommands(Throwable failure) {
            cancelled.set(true);
            Consumer<Throwable> cancel = cancelNavigation.get();
            if (cancel != null) cancel.accept(failure);
        }

        @Override
        public void close() {
            closed.set(true);
        }
    }

    private static final class CancelFailingBackend extends Backend {
        private final AtomicBoolean closed = new AtomicBoolean();

        @Override
        public void cancelPendingCommands(Throwable failure) {
            throw new IllegalStateException("cancel failed");
        }

        @Override
        public void close() {
            closed.set(true);
        }
    }

    private static final class BlockingFuture extends CompletableFuture<Void> {
        private final CountDownLatch cancelObserved;
        private final CountDownLatch allowCommandExit;

        private BlockingFuture(CountDownLatch cancelObserved, CountDownLatch allowCommandExit) {
            this.cancelObserved = cancelObserved;
            this.allowCommandExit = allowCommandExit;
        }

        @Override
        public Void get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            try {
                return super.get(timeout, unit);
            } catch (ExecutionException | CancellationException failure) {
                cancelObserved.countDown();
                if (!allowCommandExit.await(timeout, unit)) throw new TimeoutException("command exit not released");
                throw failure;
            }
        }
    }
}
