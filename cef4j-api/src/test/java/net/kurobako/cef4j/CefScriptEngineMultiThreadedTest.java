package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.io.TempDir;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefScriptEngineMultiThreadedTest {

    private static CefScriptEngine engineA;
    private static CefScriptEngine engineB;
    private static CefBrowser browserA;
    private static CefBrowser browserB;

    @BeforeAll
    static void initCef(@TempDir Path tempDir) throws Exception {
        Assumptions.assumeFalse(OS.isMacOS(), "multiThreadedMessageLoop is not supported on macOS");
        SystemBootstrap.load();

        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));

        if (Cef.INSTANCE.getState() == Cef.State.UNINITIALISED) {
            CefSettings.Mutable settings = new CefSettings.Mutable();
            settings.cachePath = cacheDir.toAbsolutePath().toString();
            settings.windowlessRenderingEnabled = 1;
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 1;

            List<String> extraArgs = new ArrayList<>();
            if (OS.isLinux()) {
                String ozonePlatform = System.getProperty("cef4j.test.ozonePlatform");
                if (ozonePlatform != null && !ozonePlatform.isBlank()) {
                    extraArgs.add("--ozone-platform=" + ozonePlatform.trim());
                }
            }
            String extraArgsProperty = System.getProperty("cef4j.test.extraArgs");
            if (extraArgsProperty != null && !extraArgsProperty.isBlank()) {
                for (String arg : extraArgsProperty.split(",")) {
                    String trimmed = arg.trim();
                    if (!trimmed.isEmpty()) {
                        extraArgs.add(trimmed);
                    }
                }
            }
            Cef.INSTANCE.initialiseUnsafe(settings, extraArgs, null);
        }

        engineA = new CefScriptEngine(
                () -> browserA != null ? browserA.getMainFrame().orElse(null) : null);
        CompletableFuture<CefBrowser> browserAFuture = new CompletableFuture<>();
        CompletableFuture<Void> loadedA = new CompletableFuture<>();
        AtomicInteger loadCountA = new AtomicInteger();
        CefClient clientA = makeClient(engineA, browserAFuture, loadCountA, loadedA, 2);
        createBrowserAsync(clientA, "about:blank");
        browserA = browserAFuture.get(10, TimeUnit.SECONDS);

        String dataUrlA = dataUrl("<html><body>A</body></html>");
        browserA.getMainFrame().ifPresent(f -> f.loadUrl(dataUrlA));
        loadedA.get(10, TimeUnit.SECONDS);

        engineB = new CefScriptEngine(
                () -> browserB != null ? browserB.getMainFrame().orElse(null) : null);
        CompletableFuture<CefBrowser> browserBFuture = new CompletableFuture<>();
        CompletableFuture<Void> loadedB = new CompletableFuture<>();
        AtomicInteger loadCountB = new AtomicInteger();
        CefClient clientB = makeClient(engineB, browserBFuture, loadCountB, loadedB, 2);
        createBrowserAsync(clientB, "about:blank");
        browserB = browserBFuture.get(10, TimeUnit.SECONDS);

        String dataUrlB = dataUrl("<html><body>B</body></html>");
        browserB.getMainFrame().ifPresent(f -> f.loadUrl(dataUrlB));
        loadedB.get(10, TimeUnit.SECONDS);
    }

    @AfterAll
    static void cleanup() {
        if (engineA != null) engineA.dispose();
        if (engineB != null) engineB.dispose();
        if (browserA != null) browserA.getHost().ifPresent(host -> host.closeBrowser(true));
        if (browserB != null) browserB.getHost().ifPresent(host -> host.closeBrowser(true));
    }

    @Test
    @Order(1)
    void evalOnBrowserA() throws Exception {
        String result = engineA.evaluate("1 + 2").get(5, TimeUnit.SECONDS);
        assertThat(result).isEqualTo("3");
    }

    @Test
    @Order(2)
    void evalOnBrowserB() throws Exception {
        String result = engineB.evaluate("10 + 20").get(5, TimeUnit.SECONDS);
        assertThat(result).isEqualTo("30");
    }

    @Test
    @Order(3)
    void handleOnBrowserA_isolatedFromB() throws Exception {
        engineA.evaluate("window.__testA = 'fromA'").get(5, TimeUnit.SECONDS);
        String resultB = engineB.evaluate("typeof window.__testA").get(5, TimeUnit.SECONDS);
        assertThat(resultB).isEqualTo("\"undefined\"");
    }

    @Test
    @Order(4)
    void interleavedEvals() throws Exception {
        CompletableFuture<String> fA = engineA.evaluate("'hello'");
        CompletableFuture<String> fB = engineB.evaluate("'world'");
        assertThat(fA.get(5, TimeUnit.SECONDS)).isEqualTo("\"hello\"");
        assertThat(fB.get(5, TimeUnit.SECONDS)).isEqualTo("\"world\"");
    }

    private static String dataUrl(String html) {
        String encoded = URLEncoder.encode(html, StandardCharsets.UTF_8).replace("+", "%20");
        return "data:text/html;charset=UTF-8," + encoded;
    }

    private static CefClient makeClient(
            CefScriptEngine engine,
            CompletableFuture<CefBrowser> browserFuture,
            AtomicInteger loadCount,
            CompletableFuture<Void> loaded,
            int targetLoadCount) {
        return new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@Nonnull CefBrowser b) {
                        browserFuture.complete(b);
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nonnull CefBrowser b, @Nonnull CefFrame frame, int httpStatusCode) {
                        if (loadCount.incrementAndGet() >= targetLoadCount) {
                            loaded.complete(null);
                        }
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefInteropTest.MinimalRenderHandler(800, 600));
            }

            @Override
            public boolean onProcessMessageReceived(
                    @Nullable CefBrowser b,
                    @Nullable CefFrame frame,
                    @Nonnull CefProcessId sourceProcess,
                    @Nullable CefProcessMessage message) {
                return engine.handleMessage(b, frame, sourceProcess, message);
            }
        };
    }

    private static void createBrowserAsync(CefClient client, String url) {
        CefWindowInfo windowInfo = Cef.createWindowlessInfo(new CefRect(0, 0, 800, 600));
        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        browserSettings.windowlessFrameRate = 60;
        int ok = CefBrowserHost.createBrowser(windowInfo, client, url, browserSettings.toImmutable(), null, null);
        assertThat(ok).as("createBrowser should succeed").isNotEqualTo(0);
    }
}
