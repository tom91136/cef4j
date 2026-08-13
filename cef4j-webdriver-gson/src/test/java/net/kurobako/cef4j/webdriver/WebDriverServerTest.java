package net.kurobako.cef4j.webdriver;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import net.kurobako.cef4j.webdriver.gson.GsonWebDriverJsonCodec;
import org.junit.jupiter.api.Test;

final class WebDriverServerTest {
    private final HttpClient client = HttpClient.newHttpClient();
    private final GsonWebDriverJsonCodec codec = new GsonWebDriverJsonCodec();

    @Test
    void ownsOneSessionAndReportsW3cCapabilities() throws Exception {
        AtomicReference<JsonObject> requested = new AtomicReference<>();
        FakeBackend backend = new FakeBackend();
        try (WebDriverServer server = WebDriverServer.start(capabilities -> {
            requested.set(capabilities.deepCopy());
            return CompletableFuture.completedFuture(backend);
        })) {
            Response initial = request(server, "GET", "/status", null);
            assertThat(initial.status).isEqualTo(200);
            assertThat(initial.value().asObject().get("ready").booleanValue()).isTrue();

            String capabilities = "{\"capabilities\":{" + "\"alwaysMatch\":{"
                    + "\"browserName\":\"cef4j\",\"pageLoadStrategy\":\"normal\"},"
                    + "\"firstMatch\":[{\"cef4j:options\":{\"transport\":\"uds\"}}]}}";
            Response created = request(server, "POST", "/session", capabilities);

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

            Response second = request(server, "POST", "/session", "{\"capabilities\":{}}");
            assertError(second, 500, "session not created");

            Response deleted = request(server, "DELETE", "/session/" + sessionId, null);
            assertThat(deleted.status).isEqualTo(200);
            assertThat(deleted.value().isNull()).isTrue();
            assertThat(backend.closed.get()).isTrue();
            assertThat(request(server, "GET", "/status", null)
                            .value()
                            .asObject()
                            .get("ready")
                            .booleanValue())
                    .isTrue();
        }
    }

    @Test
    void routesInitialBrowserCommandSlice() throws Exception {
        FakeBackend backend = new FakeBackend();
        try (WebDriverServer server = WebDriverServer.start(ignored -> CompletableFuture.completedFuture(backend))) {
            String sessionId = createSession(server);
            String prefix = "/session/" + sessionId;

            assertThat(request(server, "POST", prefix + "/url", "{\"url\":\"https://example.test/page\"}")
                            .value()
                            .isNull())
                    .isTrue();
            assertThat(backend.url).isEqualTo("https://example.test/page");
            assertThat(request(server, "GET", prefix + "/url", null).value().string())
                    .isEqualTo("https://example.test/page");
            assertThat(request(server, "GET", prefix + "/title", null).value().string())
                    .isEqualTo("Fake title");
            assertThat(request(server, "GET", prefix + "/source", null).value().string())
                    .isEqualTo("<html>fake</html>");

            Response script = request(
                    server,
                    "POST",
                    prefix + "/execute/sync",
                    "{\"script\":\"return arguments[0]\",\"args\":[{\"answer\":42}]}");
            assertThat(script.value().asObject().get("answer").intValue()).isEqualTo(42);
            assertThat(backend.lastScript).isEqualTo("return arguments[0]");

            assertThat(request(server, "GET", prefix + "/screenshot", null)
                            .value()
                            .string())
                    .isEqualTo("iVBORw0KGgo=");
        }
    }

    @Test
    void returnsStandardErrorsForBadRequests() throws Exception {
        FakeBackend backend = new FakeBackend();
        try (WebDriverServer server = WebDriverServer.start(ignored -> CompletableFuture.completedFuture(backend))) {
            assertError(request(server, "POST", "/session", "not-json"), 400, "invalid argument");
            assertError(
                    request(server, "POST", "/session", "{\"capabilities\":{\"alwaysMatch\":{\"unexpected\":true}}}"),
                    400,
                    "invalid argument");
            assertError(request(server, "GET", "/session/missing/url", null), 404, "invalid session id");

            String sessionId = createSession(server);
            assertError(
                    request(server, "POST", "/session/" + sessionId + "/execute/sync", "{\"script\":7,\"args\":[]}"),
                    400,
                    "invalid argument");
            assertError(
                    request(server, "GET", "/session/" + sessionId + "/not-a-command", null), 404, "unknown command");
        }
    }

    @Test
    void mapsBackendTimeoutAndPreservesLoopbackDefault() throws Exception {
        FakeBackend backend = new FakeBackend();
        backend.navigation = new CompletableFuture<>();
        try (WebDriverServer server = WebDriverServer.start(
                new InetSocketAddress(java.net.InetAddress.getLoopbackAddress(), 0),
                ignored -> CompletableFuture.completedFuture(backend),
                Duration.ofMillis(50))) {
            assertThat(server.endpoint().getHost()).isIn("127.0.0.1", "0:0:0:0:0:0:0:1", "::1");
            String sessionId = createSession(server);
            request(server, "POST", "/session/" + sessionId + "/timeouts", "{\"pageLoad\":50}");
            Response response =
                    request(server, "POST", "/session/" + sessionId + "/url", "{\"url\":\"https://slow.test\"}");
            assertError(response, 500, "timeout");
            assertThat(backend.closed).isTrue();
            assertError(request(server, "GET", "/session/" + sessionId + "/url", null), 404, "invalid session id");
        }
    }

    @Test
    void appliesTimeoutsRequestedAtSessionCreation() throws Exception {
        try (WebDriverServer server =
                WebDriverServer.start(ignored -> CompletableFuture.completedFuture(new FakeBackend()))) {
            Response created = request(
                    server,
                    "POST",
                    "/session",
                    "{\"capabilities\":{\"alwaysMatch\":{\"timeouts\":{\"implicit\":123}}}}");
            String sessionId = created.value().asObject().get("sessionId").string();

            JsonObject timeouts = request(server, "GET", "/session/" + sessionId + "/timeouts", null)
                    .value()
                    .asObject();
            assertThat(timeouts.get("implicit").longValue()).isEqualTo(123L);
        }
    }

    @Test
    void closesBackendThatArrivesAfterSessionCreationTimeout() throws Exception {
        CompletableFuture<AutomationBackend> creation = new CompletableFuture<>();
        FakeBackend backend = new FakeBackend();
        try (WebDriverServer server = WebDriverServer.start(
                new InetSocketAddress(java.net.InetAddress.getLoopbackAddress(), 0),
                ignored -> creation,
                Duration.ofMillis(25))) {
            Response response = request(server, "POST", "/session", "{\"capabilities\":{}}");
            assertError(response, 500, "timeout");
            creation.complete(backend);
            for (int i = 0; i < 50 && !backend.closed.get(); i++) Thread.sleep(2);
            assertThat(backend.closed).isTrue();
        }
    }

    @Test
    void implicitWaitRetriesElementSearch() throws Exception {
        FakeBackend backend = new FakeBackend();
        backend.emptySearches.set(2);
        try (WebDriverServer server = WebDriverServer.start(ignored -> CompletableFuture.completedFuture(backend))) {
            String sessionId = createSession(server);
            request(server, "POST", "/session/" + sessionId + "/timeouts", "{\"implicit\":500}");
            Response found = request(
                    server,
                    "POST",
                    "/session/" + sessionId + "/element",
                    "{\"using\":\"css selector\",\"value\":\"#eventual\"}");
            assertThat(found.status).isEqualTo(200);
            assertThat(backend.searches).hasValue(3);
        }
    }

    private String createSession(WebDriverServer server) throws Exception {
        Response response = request(server, "POST", "/session", "{\"capabilities\":{}}");
        assertThat(response.status).isEqualTo(200);
        return response.value().asObject().get("sessionId").string();
    }

    private Response request(WebDriverServer server, String method, String path, @Nullable String body)
            throws IOException, InterruptedException {
        HttpRequest.BodyPublisher publisher =
                body == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(body);
        HttpRequest request = HttpRequest.newBuilder(server.endpoint().resolve(path))
                .method(method, publisher)
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new Response(response.statusCode(), codec.decode(response.body()).asObject());
    }

    private static void assertError(Response response, int status, String error) {
        assertThat(response.status).isEqualTo(status);
        JsonObject value = response.value().asObject();
        assertThat(value.get("error").string()).isEqualTo(error);
        assertThat(value.get("message").string()).isNotBlank();
        assertThat(value.get("stacktrace").string()).isEmpty();
    }

    private static final class Response {
        private final int status;
        private final JsonObject body;

        private Response(int status, JsonObject body) {
            this.status = status;
            this.body = body;
        }

        private JsonElement value() {
            return body.get("value");
        }
    }

    private static final class FakeBackend implements AutomationBackend {
        private final AtomicBoolean closed = new AtomicBoolean();
        private String url = "about:blank";
        private String lastScript = "";
        private CompletableFuture<Void> navigation = CompletableFuture.completedFuture(null);
        private final AtomicInteger searches = new AtomicInteger();
        private final AtomicInteger emptySearches = new AtomicInteger();

        @Override
        public JsonObject capabilities() {
            JsonObject result = new JsonObject();
            result.addProperty("browserVersion", "146-test");
            return result;
        }

        @Override
        public CompletableFuture<Void> navigate(String url) {
            this.url = url;
            return navigation;
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
        public CompletableFuture<java.util.List<String>> findElements(
                String using, String value, @Nullable String parentElement) {
            searches.incrementAndGet();
            if (emptySearches.getAndDecrement() > 0) return CompletableFuture.completedFuture(java.util.List.of());
            return CompletableFuture.completedFuture(java.util.List.of("element-1"));
        }

        @Override
        public void close() {
            closed.set(true);
        }
    }
}
