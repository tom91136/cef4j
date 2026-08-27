package net.kurobako.cef4j.webdriver.remote;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.Duration;
import javax.annotation.Nullable;
import net.kurobako.cef4j.remote.RuntimeServerBrowserRuntimeFactory;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import net.kurobako.cef4j.webdriver.WebDriverServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

@Timeout(600)
class RuntimeServerWebDriverIntegrationTest {
    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void servesM1CommandsOverZmq() throws Exception {
        verifyTransport("zmq", "tcp://127.0.0.1:0", "mmap");
    }

    @Test
    void servesM1CommandsOverWebSocket() throws Exception {
        verifyTransport("websocket", "ws://127.0.0.1:0/cef4j", "inline");
    }

    @Test
    void servesM1CommandsOverPlatformLocalTransport() throws Exception {
        String endpoint =
                System.getProperty("os.name").toLowerCase(java.util.Locale.ROOT).contains("win")
                        ? "pipe://cef4j-webdriver-" + Long.toUnsignedString(System.nanoTime())
                        : "tcp://127.0.0.1:0";
        verifyTransport("local", endpoint, "inline");
    }

    @Test
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "Unix-domain sockets are not a Windows transport")
    void servesM1CommandsOverUnixDomainSocket(@TempDir Path socketDirectory) throws Exception {
        verifyTransport("uds", "unix://" + socketDirectory.resolve("control.sock"), "inline");
    }

    @Test
    void acceptsAnUnmodifiedSeleniumRemoteWebDriver() throws Exception {
        byte[] page = ("<title>Selenium talks to CEF</title>"
                        + "<main id='root'><p id='value' class='shown' data-kind='answer'>contained</p>"
                        + "<input id='enabled'><input id='checked' type='checkbox' checked>"
                        + "<button id='button' onclick=\"this.textContent='clicked'\">press</button></main>")
                .getBytes(StandardCharsets.UTF_8);
        HttpServer fixture = startFixture(page);
        URI pageUri = URI.create("http://127.0.0.1:" + fixture.getAddress().getPort() + "/page");
        RuntimeServerBrowserRuntimeFactory runtimes = runtimeFactory("zmq", "tcp://127.0.0.1:0", "mmap");
        try (WebDriverServer webdriver = RemoteWebDriverServer.start(runtimes, Duration.ofMinutes(4))) {
            RemoteWebDriver driver = new RemoteWebDriver(
                    webdriver.endpoint().toURL(), new ImmutableCapabilities("browserName", "cef4j"));
            try {
                driver.get(pageUri.toString());
                assertThat(driver.getCurrentUrl()).isEqualTo(pageUri.toString());
                assertThat(driver.getTitle()).isEqualTo("Selenium talks to CEF");
                assertThat(driver.getPageSource()).contains("contained");
                assertThat(driver.executeScript("return document.querySelector('#value').textContent"))
                        .isEqualTo("contained");
                WebElement root = driver.findElement(By.id("root"));
                WebElement value = root.findElement(By.cssSelector("#value"));
                assertThat(value.getTagName()).isEqualTo("p");
                assertThat(value.getText()).isEqualTo("contained");
                assertThat(value.getAttribute("data-kind")).isEqualTo("answer");
                assertThat(value.getDomProperty("id")).isEqualTo("value");
                assertThat(value.getCssValue("display")).isEqualTo("block");
                assertThat(value.getRect().getWidth()).isPositive();
                assertThat(value.isDisplayed()).isTrue();
                assertThat(driver.findElement(By.id("enabled")).isEnabled()).isTrue();
                assertThat(driver.findElement(By.id("checked")).isSelected()).isTrue();
                assertThat(driver.findElements(By.tagName("input"))).hasSize(2);
                WebElement input = driver.findElement(By.id("enabled"));
                input.sendKeys("portable");
                assertThat(input.getDomProperty("value")).isEqualTo("portable");
                input.clear();
                assertThat(input.getDomProperty("value")).isEmpty();
                WebElement button = driver.findElement(By.id("button"));
                button.click();
                assertThat(button.getText()).isEqualTo("clicked");
                driver.manage().addCookie(new Cookie("portable", "yes"));
                Cookie storedCookie = driver.manage().getCookieNamed("portable");
                assertThat(storedCookie).isNotNull();
                assertThat(java.util.Objects.requireNonNull(storedCookie).getValue())
                        .isEqualTo("yes");
                driver.manage().deleteCookieNamed("portable");
                assertThat(driver.manage().getCookieNamed("portable")).isNull();
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(50));
                driver.navigate().refresh();
                assertThat(driver.getTitle()).isEqualTo("Selenium talks to CEF");
                assertThat(driver.getScreenshotAs(OutputType.BYTES)).startsWith(0x89, 0x50, 0x4e, 0x47);
                driver.get("about:blank");
                assertThatThrownBy(value::getText).isInstanceOf(StaleElementReferenceException.class);
            } finally {
                driver.quit();
            }
        } finally {
            fixture.stop(0);
        }
    }

    private static void verifyTransport(String transport, String endpoint, String frameTransport) throws Exception {
        byte[] page = ("<!doctype html><html><head><title>cef4j portable</title></head>"
                        + "<body><main id='answer'>forty-two</main></body></html>")
                .getBytes(StandardCharsets.UTF_8);
        HttpServer fixture = startFixture(page);
        URI pageUri = URI.create("http://127.0.0.1:" + fixture.getAddress().getPort() + "/page");

        RuntimeServerBrowserRuntimeFactory runtimes = runtimeFactory(transport, endpoint, frameTransport);

        try (WebDriverServer webdriver = RemoteWebDriverServer.start(runtimes, Duration.ofMinutes(4))) {
            HttpClient client = HttpClient.newHttpClient();
            JsonObject created =
                    request(client, webdriver.endpoint().resolve("/session"), "POST", "{\"capabilities\":{}}");
            JsonObject session = created.getAsJsonObject("value");
            String id = session.get("sessionId").getAsString();
            assertThat(session.getAsJsonObject("capabilities")
                            .get("browserName")
                            .getAsString())
                    .isEqualTo("cef4j");

            URI base = webdriver.endpoint().resolve("/session/" + id + "/");
            request(client, base.resolve("url"), "POST", "{\"url\":" + quote(pageUri.toString()) + "}");
            assertThat(value(request(client, base.resolve("url"), "GET", null)).getAsString())
                    .isEqualTo(pageUri.toString());
            assertThat(value(request(client, base.resolve("title"), "GET", null))
                            .getAsString())
                    .isEqualTo("cef4j portable");
            assertThat(value(request(client, base.resolve("source"), "GET", null))
                            .getAsString())
                    .contains("forty-two");
            assertThat(value(request(
                                    client,
                                    base.resolve("execute/sync"),
                                    "POST",
                                    "{\"script\":\"return arguments[0] * 2\",\"args\":[21]}"))
                            .getAsInt())
                    .isEqualTo(42);
            byte[] png = java.util.Base64.getDecoder()
                    .decode(value(request(client, base.resolve("screenshot"), "GET", null))
                            .getAsString());
            assertThat(png).startsWith(0x89, 0x50, 0x4e, 0x47);
            request(client, webdriver.endpoint().resolve("/session/" + id), "DELETE", null);
        } finally {
            fixture.stop(0);
        }
    }

    private static RuntimeServerBrowserRuntimeFactory runtimeFactory(
            String transport, String endpoint, String frameTransport) {
        return new RuntimeServerBrowserRuntimeFactory(
                RUNTIME.binary(),
                transport,
                endpoint,
                frameTransport,
                Duration.ofSeconds(30),
                RUNTIME.processEnvironment());
    }

    private static HttpServer startFixture(byte[] page) throws Exception {
        HttpServer fixture = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 4);
        fixture.createContext("/page", exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, page.length);
            exchange.getResponseBody().write(page);
            exchange.close();
        });
        fixture.start();
        return fixture;
    }

    private static JsonObject request(HttpClient client, URI uri, String method, @Nullable String body)
            throws Exception {
        HttpRequest.BodyPublisher publisher = body == null
                ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json; charset=utf-8")
                .method(method, publisher)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertThat(response.statusCode()).as(response.body()).isEqualTo(200);
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    private static com.google.gson.JsonElement value(JsonObject response) {
        return response.get("value");
    }

    private static String quote(String value) {
        return new com.google.gson.JsonPrimitive(value).toString();
    }
}
