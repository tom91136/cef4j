package net.kurobako.cef4j.webdriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import org.junit.jupiter.api.Test;

public abstract class WebDriverServerContract {
    private final HttpClient client = HttpClient.newHttpClient();

    @Nonnull
    protected abstract WebDriverJsonCodec codec();

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

    private void assertCookieFailure(String json, String message) {
        JsonObject cookie = codec().decode(json).asObject();
        assertThatThrownBy(() -> CdpAutomationBackend.validateCookie(cookie))
                .isInstanceOf(WebDriverException.class)
                .hasMessageContaining(message);
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

    private static final class Backend implements AutomationBackend {
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
}
