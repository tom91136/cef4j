package net.kurobako.cef4j.webdriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
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
            assertThat(backend.navigation.cancelObserved.await(5, TimeUnit.SECONDS))
                    .isTrue();
            assertThat(backend.closed).isFalse();
            backend.navigation.allowCommandExit.countDown();
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

    private static final class BlockingBackend extends Backend {
        private final CountDownLatch started = new CountDownLatch(1);
        private final BlockingFuture navigation = new BlockingFuture();
        private final AtomicBoolean cancelled = new AtomicBoolean();
        private final AtomicBoolean closed = new AtomicBoolean();

        @Override
        public CompletableFuture<Void> navigate(String url) {
            started.countDown();
            return navigation;
        }

        @Override
        public void cancelPendingCommands(Throwable failure) {
            cancelled.set(true);
            navigation.completeExceptionally(failure);
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
        private final CountDownLatch cancelObserved = new CountDownLatch(1);
        private final CountDownLatch allowCommandExit = new CountDownLatch(1);

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
