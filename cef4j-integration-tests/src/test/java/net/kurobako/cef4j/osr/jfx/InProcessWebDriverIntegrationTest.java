package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.assumeDisplayServer;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.closeAllWindows;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.onFxThread;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.startJavaFx;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.waitUntil;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import com.sun.net.httpserver.HttpServer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.webdriver.WebDriverServer;
import net.kurobako.cef4j.webdriver.inprocess.InProcessBrowserRuntime;
import net.kurobako.cef4j.webdriver.inprocess.InProcessWebDriverServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/** Proves that the common W3C/CDP implementation can automate CEF hosted in this JVM. */
@Timeout(90)
class InProcessWebDriverIntegrationTest {
    @BeforeAll
    static void initialiseCef() throws Exception {
        assumeFalse(OS.isMacOS(), "JavaFX startup is not supported from this JUnit launcher on macOS");
        assumeDisplayServer();
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = Files.createTempDirectory("cef4j-inprocess-webdriver-cache")
                .toAbsolutePath()
                .toString();
        CefWebView.initialise(settings, List.of(), null);
        startJavaFx();
        Platform.setImplicitExit(false);
    }

    @AfterAll
    static void terminateCef() throws Exception {
        closeAllWindows();
        if (Cef.INSTANCE.state() == Cef.State.INITIALISED) CefWebView.terminate();
    }

    @Test
    void acceptsSeleniumWithoutChromeOrChromeDriver() throws Exception {
        byte[] page = "<title>In-process CEF</title><main id='answer'>portable</main>".getBytes(StandardCharsets.UTF_8);
        HttpServer fixture = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 1);
        fixture.createContext("/page", exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, page.length);
            exchange.getResponseBody().write(page);
            exchange.close();
        });
        fixture.start();
        URI pageUri = URI.create("http://127.0.0.1:" + fixture.getAddress().getPort() + "/page");

        try (WebDriverServer server =
                InProcessWebDriverServer.start(InProcessWebDriverIntegrationTest::createBrowser)) {
            RemoteWebDriver driver =
                    new RemoteWebDriver(server.endpoint().toURL(), new ImmutableCapabilities("browserName", "cef4j"));
            try {
                driver.get(pageUri.toString());
                assertThat(driver.getTitle()).isEqualTo("In-process CEF");
                assertThat(driver.findElement(By.id("answer")).getText()).isEqualTo("portable");
                assertThat(driver.executeScript("return 6 * 7")).isEqualTo(42L);
            } finally {
                driver.quit();
            }
        } finally {
            fixture.stop(0);
        }
    }

    private static CompletableFuture<? extends InProcessBrowserRuntime> createBrowser() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Object[] created = Objects.requireNonNull(
                        onFxThread(() -> {
                            CefWebView view = new CefWebView();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(new StackPane(view), 800, 600));
                            stage.show();
                            return new Object[] {view, stage};
                        }),
                        "created browser");
                CefWebView view = (CefWebView) created[0];
                Stage stage = (Stage) created[1];
                if (!waitUntil(() -> view.getBrowser() != null, 20_000)) {
                    onFxThread(() -> {
                        stage.close();
                        view.release();
                    });
                    throw new IllegalStateException("CEF browser was not created within 20 seconds");
                }
                CefBrowser browser = Objects.requireNonNull(view.getBrowser(), "view browser");
                return new InProcessBrowserRuntime() {
                    @Override
                    public CefBrowser browser() {
                        return browser;
                    }

                    @Override
                    public void close() {
                        try {
                            onFxThread(() -> {
                                stage.close();
                                view.release();
                            });
                        } catch (Exception failure) {
                            throw new RuntimeException("failed to close in-process browser", failure);
                        }
                    }
                };
            } catch (Exception failure) {
                throw new CompletionException(failure);
            }
        });
    }
}
