package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.*;
import net.kurobako.cef4j.test.CefTestLifecycle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefScriptEngineMultiBrowserTest extends CefTestBase {

    @SuppressWarnings("NullAway.Init")
    private static CefScriptEngine engineA;

    @SuppressWarnings("NullAway.Init")
    private static CefScriptEngine engineB;

    @SuppressWarnings("NullAway.Init")
    private static CefBrowser browserA;

    @SuppressWarnings("NullAway.Init")
    private static CefBrowser browserB;

    private static final CountDownLatch closedA = new CountDownLatch(1);
    private static final CountDownLatch closedB = new CountDownLatch(1);

    @BeforeAll
    static void initCef() throws Exception {
        initCef(List.of());

        engineA = new CefScriptEngine(
                () -> browserA != null ? browserA.getMainFrame().orElse(null) : null);
        AtomicInteger loadCountA = new AtomicInteger();
        CountDownLatch createdA = new CountDownLatch(1);
        CountDownLatch loadedA = new CountDownLatch(1);
        CefClient clientA = makeClient(engineA, createdA, loadCountA, loadedA, closedA, 2);
        browserA = createWindowlessBrowser(clientA, "about:blank");
        assertThat(pumpUntil(createdA, 10_000)).as("browser A created").isTrue();

        String dataUrlA = dataUrl("<html><body>A</body></html>");
        browserA.getMainFrame().ifPresent(f -> f.loadUrl(dataUrlA));
        assertThat(pumpUntil(loadedA, 10_000)).as("browser A data loaded").isTrue();

        engineB = new CefScriptEngine(
                () -> browserB != null ? browserB.getMainFrame().orElse(null) : null);
        AtomicInteger loadCountB = new AtomicInteger();
        CountDownLatch createdB = new CountDownLatch(1);
        CountDownLatch loadedB = new CountDownLatch(1);
        CefClient clientB = makeClient(engineB, createdB, loadCountB, loadedB, closedB, 2);
        browserB = createWindowlessBrowser(clientB, "about:blank");
        assertThat(pumpUntil(createdB, 10_000)).as("browser B created").isTrue();

        String dataUrlB = dataUrl("<html><body>B</body></html>");
        browserB.getMainFrame().ifPresent(f -> f.loadUrl(dataUrlB));
        assertThat(pumpUntil(loadedB, 10_000)).as("browser B data loaded").isTrue();
    }

    @AfterAll
    static void cleanup() throws Exception {
        if (engineA != null) engineA.dispose();
        if (engineB != null) engineB.dispose();
        closeBrowser(browserA);
        assertThat(pumpUntil(closedA, 10_000)).as("browser A closed").isTrue();
        if (browserB != null && browserB.isValid()) closeBrowser(browserB);
        assertThat(closedB.await(10, TimeUnit.SECONDS)).as("browser B closed").isTrue();
        if (!OS.isMacOS() && CefTestLifecycle.explicitShutdownSafe() && Cef.INSTANCE.state() == Cef.State.INITIALISED)
            Cef.INSTANCE.terminate();
    }

    @Test
    @Order(1)
    void evalOnBrowserA_afterBrowserBCreated() throws Exception {
        String result = pumpAndGet(engineA.evaluate("1 + 2"), 5_000);
        assertThat(result).isEqualTo("3");
    }

    @Test
    @Order(2)
    void evalOnBrowserB() throws Exception {
        String result = pumpAndGet(engineB.evaluate("10 + 20"), 5_000);
        assertThat(result).isEqualTo("30");
    }

    @Test
    @Order(3)
    void handleOnBrowserA_isolatedFromB() throws Exception {
        pumpAndGet(engineA.evaluate("window.__testA = 'fromA'"), 5_000);
        String resultB = pumpAndGet(engineB.evaluate("typeof window.__testA"), 5_000);
        assertThat(resultB).isEqualTo("\"undefined\"");
    }

    @Test
    @Order(4)
    void handleOnBrowserB_isolatedFromA() throws Exception {
        pumpAndGet(engineB.evaluate("window.__testB = 'fromB'"), 5_000);
        String resultA = pumpAndGet(engineA.evaluate("typeof window.__testB"), 5_000);
        assertThat(resultA).isEqualTo("\"undefined\"");
    }

    @Test
    @Order(5)
    void handleModeOnBothBrowsers() throws Exception {
        int handleA = pumpAndGet(engineA.evaluateHandle("({x: 'a'})"), 5_000);
        int handleB = pumpAndGet(engineB.evaluateHandle("({x: 'b'})"), 5_000);

        CefScriptEngine.Result rA = pumpAndGet(engineA.getProperty(handleA, "x", false), 5_000);
        assertThat(rA.json()).isEqualTo("\"a\"");

        CefScriptEngine.Result rB = pumpAndGet(engineB.getProperty(handleB, "x", false), 5_000);
        assertThat(rB.json()).isEqualTo("\"b\"");

        engineA.release(handleA);
        engineB.release(handleB);
    }

    @Test
    @Order(6)
    void interleavedEvals() throws Exception {
        CompletableFuture<String> fA = engineA.evaluate("'hello'");
        CompletableFuture<String> fB = engineB.evaluate("'world'");
        pumpUntilAllDone(5_000, fA, fB);
        assertThat(fA.get()).isEqualTo("\"hello\"");
        assertThat(fB.get()).isEqualTo("\"world\"");
    }

    @Test
    @Order(7)
    void closeBrowserB_doesNotBreakA() throws Exception {
        browserB.getHost().ifPresent(host -> host.closeBrowser(true));
        engineB.dispose();

        long deadline = System.currentTimeMillis() + 2_000;
        while (System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }

        String result = pumpAndGet(engineA.evaluate("42"), 5_000);
        assertThat(result).isEqualTo("42");
    }

    private static String dataUrl(String html) {
        String encoded = URLEncoder.encode(html, StandardCharsets.UTF_8).replace("+", "%20");
        return "data:text/html;charset=UTF-8," + encoded;
    }

    private static CefClient makeClient(
            CefScriptEngine engine,
            CountDownLatch created,
            AtomicInteger loadCount,
            CountDownLatch loaded,
            CountDownLatch closed,
            int targetLoadCount) {
        return new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@Nullable CefBrowser b) {
                        created.countDown();
                    }

                    @Override
                    public void onBeforeClose(@Nullable CefBrowser b) {
                        closed.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nullable CefBrowser b, @Nullable CefFrame frame, int httpStatusCode) {
                        if (loadCount.incrementAndGet() >= targetLoadCount) {
                            loaded.countDown();
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
                    @Nullable CefProcessId sourceProcess,
                    @Nullable CefProcessMessage message) {
                if (b == null || frame == null || sourceProcess == null || message == null) return false;
                return engine.handleMessage(b, frame, sourceProcess, message);
            }
        };
    }

    private static <T> T pumpAndGet(CompletableFuture<T> future, long timeoutMs) throws Exception {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (!future.isDone() && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
        assertThat(future)
                .as("future should complete within " + timeoutMs + "ms")
                .isDone();
        return future.get(0, TimeUnit.MILLISECONDS);
    }

    private static void pumpUntilAllDone(long timeoutMs, CompletableFuture<?>... futures) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            boolean allDone = true;
            for (CompletableFuture<?> f : futures) {
                if (!f.isDone()) {
                    allDone = false;
                    break;
                }
            }
            if (allDone) return;
            Thread.sleep(16);
        }
        for (CompletableFuture<?> f : futures) {
            assertThat(f).as("all futures should complete").isDone();
        }
    }
}
