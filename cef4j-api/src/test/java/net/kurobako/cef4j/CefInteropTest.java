package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import net.kurobako.cef4j.gen.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Headless (OSR) integration tests for JNI-CEF interop.
 *
 * <p>These tests exercise the full native call chain: Java -> JNI -> CEF C API -> callbacks -> JNI -> Java. CEF runs in
 * off-screen rendering mode with no display surface required.
 *
 * <p>Requires:
 *
 * <ul>
 *   <li>{@code -Djava.library.path=<dir containing libcef4j.so>}
 *   <li>{@code -Dcef4j.cef.path=<CEF binary distribution root>} (for subprocess helper)
 * </ul>
 *
 * <p>If the native library is not available, all tests are skipped via assumption.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefInteropTest {

    private static CefApp app;
    private static boolean nativeAvailable;

    @BeforeAll
    static void initCef() throws Exception {
        SystemBootstrap.load();

        Path cacheDir = Files.createTempDirectory("cef4j-test-cache-");
        cacheDir.toFile().deleteOnExit();

        // Helper path is auto-resolved by CefApp from SystemBootstrap
        app = CefApp.getInstance(cacheDir.toAbsolutePath().toString(), null, true, null);
        app.initialize();
    }

    @AfterAll
    static void shutdownCef() {
        if (app != null && app.getState() == CefApp.State.INITIALIZED) {
            app.dispose();
        }
    }

    // -----------------------------------------------------------------------
    // 1. Basic lifecycle: initialize, create browser, callbacks fire, close
    // -----------------------------------------------------------------------

    @Test
    @Order(1)
    void browserLifecycle_onAfterCreatedAndOnLoadEndFire() throws Exception {

        CountDownLatch createdLatch = new CountDownLatch(1);
        CountDownLatch loadEndLatch = new CountDownLatch(1);
        AtomicReference<Long> browserIdRef = new AtomicReference<>(0L);
        AtomicInteger httpStatus = new AtomicInteger(-1);

        CefClient client = app.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(long browser) {
                        browserIdRef.set(browser);
                        createdLatch.countDown();
                    }
                })
                .addLoadHandler(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(long browser, long frame, int httpStatusCode) {
                        httpStatus.set(httpStatusCode);
                        loadEndLatch.countDown();
                    }
                })
                .addRenderHandler(new MinimalRenderHandler(800, 600));

        CefBrowserOsr browser = client.createBrowser("about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(createdLatch, 10_000))
                .as("onAfterCreated should fire within 10s")
                .isTrue();
        assertThat(browserIdRef.get()).as("browser pointer").isNotEqualTo(0L);

        assertThat(pumpUntil(loadEndLatch, 10_000))
                .as("onLoadEnd should fire within 10s")
                .isTrue();

        // about:blank loads with status 0 (no HTTP involved) or 200
        assertThat(httpStatus.get()).as("HTTP status for about:blank").isIn(0, 200);

        browser.close(true);
    }

    // -----------------------------------------------------------------------
    // 2. Render handler: getViewRect output parameter and onPaint callback
    // -----------------------------------------------------------------------

    @Test
    @Order(2)
    void renderHandler_getViewRectAndOnPaintFire() throws Exception {

        int viewWidth = 320;
        int viewHeight = 240;

        AtomicBoolean viewRectCalled = new AtomicBoolean(false);
        CountDownLatch paintLatch = new CountDownLatch(1);
        AtomicInteger paintWidth = new AtomicInteger();
        AtomicInteger paintHeight = new AtomicInteger();
        AtomicReference<byte[]> pixelBuffer = new AtomicReference<>();

        CefClient client = app.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {})
                .addLoadHandler(new CefLoadHandler() {})
                .addRenderHandler(new CefRenderHandler() {
                    @Override
                    public void getViewRect(long browser, CefMutableRect rect) {
                        rect.x = 0;
                        rect.y = 0;
                        rect.width = viewWidth;
                        rect.height = viewHeight;
                        viewRectCalled.set(true);
                    }

                    @Override
                    public void onPaint(
                            long browser,
                            CefPaintElementType type,
                            long dirtyRectsCount,
                            CefRect[] dirtyRects,
                            java.nio.ByteBuffer buffer,
                            int width,
                            int height) {
                        if (paintLatch.getCount() > 0) {
                            paintWidth.set(width);
                            paintHeight.set(height);
                            // Capture a copy of the pixel buffer
                            if (buffer != null) {
                                byte[] copy = new byte[buffer.remaining()];
                                buffer.get(copy);
                                pixelBuffer.set(copy);
                            }
                            paintLatch.countDown();
                        }
                    }
                });

        CefBrowserOsr browser = client.createBrowser("about:blank");
        browser.createImmediately();

        // Wait for at least one paint
        assertThat(pumpUntil(paintLatch, 15_000))
                .as("onPaint should fire within 15s")
                .isTrue();

        assertThat(viewRectCalled.get()).as("getViewRect was called").isTrue();
        assertThat(paintWidth.get()).as("paint width").isEqualTo(viewWidth);
        assertThat(paintHeight.get()).as("paint height").isEqualTo(viewHeight);

        // BGRA pixel buffer: 4 bytes per pixel
        byte[] buf = pixelBuffer.get();
        assertThat(buf).as("pixel buffer").isNotNull();
        assertThat(buf.length).as("pixel buffer size (BGRA)").isEqualTo(viewWidth * viewHeight * 4);

        browser.close(true);
    }

    // -----------------------------------------------------------------------
    // 3. Display handler: title change callback with string marshaling
    // -----------------------------------------------------------------------

    @Test
    @Order(3)
    void displayHandler_onTitleChangeMarshalsString() throws Exception {

        CountDownLatch titleLatch = new CountDownLatch(1);
        AtomicReference<String> receivedTitle = new AtomicReference<>();

        CefClient client = app.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {})
                .addLoadHandler(new CefLoadHandler() {})
                .addRenderHandler(new MinimalRenderHandler(100, 100))
                .addDisplayHandler(new CefDisplayHandler() {
                    @Override
                    public void onTitleChange(long browser, String title) {
                        if (title != null && !title.isEmpty()) {
                            receivedTitle.set(title);
                            titleLatch.countDown();
                        }
                    }
                });

        // data: URI with a title set via HTML
        String html = "<html><head><title>cef4j-test-title</title></head><body></body></html>";
        String dataUrl = "data:text/html;charset=utf-8," + html.replace(" ", "%20");

        CefBrowserOsr browser = client.createBrowser(dataUrl);
        browser.createImmediately();

        assertThat(pumpUntil(titleLatch, 10_000))
                .as("onTitleChange should fire within 10s")
                .isTrue();
        assertThat(receivedTitle.get()).as("title from HTML").isEqualTo("cef4j-test-title");

        browser.close(true);
    }

    // -----------------------------------------------------------------------
    // 4. Load handler: full loading state sequence
    // -----------------------------------------------------------------------

    @Test
    @Order(4)
    void loadHandler_loadingStateSequence() throws Exception {

        CopyOnWriteArrayList<String> events = new CopyOnWriteArrayList<>();
        CountDownLatch doneLatch = new CountDownLatch(1);

        CefClient client = app.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {})
                .addRenderHandler(new MinimalRenderHandler(100, 100))
                .addLoadHandler(new CefLoadHandler() {
                    @Override
                    public void onLoadingStateChange(
                            long browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                        events.add("stateChange:isLoading=" + isLoading);
                        if (!isLoading) {
                            doneLatch.countDown();
                        }
                    }

                    @Override
                    public void onLoadStart(long browser, long frame, CefTransitionType transitionType) {
                        events.add("loadStart");
                    }

                    @Override
                    public void onLoadEnd(long browser, long frame, int httpStatusCode) {
                        events.add("loadEnd:" + httpStatusCode);
                    }
                });

        CefBrowserOsr browser = client.createBrowser("about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(doneLatch, 10_000))
                .as("loading should complete within 10s")
                .isTrue();

        // Expect at least: loading started, loadStart, loadEnd, loading finished
        assertThat(events)
                .as("load event sequence")
                .contains("stateChange:isLoading=true", "stateChange:isLoading=false");

        browser.close(true);
    }

    // -----------------------------------------------------------------------
    // 5. Multiple browsers on a single client (object ownership)
    // -----------------------------------------------------------------------

    @Test
    @Order(5)
    void multipleBrowsers_sameClient() throws Exception {

        CountDownLatch createdLatch = new CountDownLatch(2);
        CopyOnWriteArrayList<Long> browserPtrs = new CopyOnWriteArrayList<>();

        CefClient client = app.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(long browser) {
                        browserPtrs.add(browser);
                        createdLatch.countDown();
                    }
                })
                .addLoadHandler(new CefLoadHandler() {})
                .addRenderHandler(new MinimalRenderHandler(100, 100));

        CefBrowserOsr browser1 = client.createBrowser("about:blank");
        CefBrowserOsr browser2 = client.createBrowser("about:blank");
        browser1.createImmediately();
        browser2.createImmediately();

        assertThat(pumpUntil(createdLatch, 10_000))
                .as("both browsers should be created")
                .isTrue();

        assertThat(browserPtrs).as("two distinct browser pointers").hasSize(2);
        assertThat(browserPtrs.get(0))
                .as("browsers have different native pointers")
                .isNotEqualTo(browserPtrs.get(1));

        browser1.close(true);
        browser2.close(true);
    }

    // -----------------------------------------------------------------------
    // 6. Console message - exercises enum + string + int marshaling
    // -----------------------------------------------------------------------

    @Test
    @Order(6)
    void displayHandler_consoleMessageFromJavaScript() throws Exception {

        CountDownLatch consoleLatch = new CountDownLatch(1);
        AtomicReference<String> consoleMsg = new AtomicReference<>();
        AtomicReference<CefLogSeverity> consoleSeverity = new AtomicReference<>();
        CountDownLatch loadLatch = new CountDownLatch(1);

        CefClient client = app.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {})
                .addRenderHandler(new MinimalRenderHandler(100, 100))
                .addLoadHandler(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(long browser, long frame, int httpStatusCode) {
                        loadLatch.countDown();
                    }
                })
                .addDisplayHandler(new CefDisplayHandler() {
                    @Override
                    public boolean onConsoleMessage(
                            long browser, CefLogSeverity level, String message, String source, int line) {
                        consoleMsg.set(message);
                        consoleSeverity.set(level);
                        consoleLatch.countDown();
                        return false;
                    }
                });

        CefBrowserOsr browser = client.createBrowser("about:blank");
        browser.createImmediately();

        // Wait for page load before executing JS
        assertThat(pumpUntil(loadLatch, 10_000)).isTrue();

        browser.executeJavaScript("console.log('cef4j-interop-test')", "test.js", 1);

        assertThat(pumpUntil(consoleLatch, 10_000))
                .as("console message callback should fire")
                .isTrue();
        assertThat(consoleMsg.get()).as("console message text").isEqualTo("cef4j-interop-test");
        assertThat(consoleSeverity.get()).as("console log severity").isNotNull();

        browser.close(true);
    }

    // -----------------------------------------------------------------------
    // 7. Frame handler: frame lifecycle callbacks
    // -----------------------------------------------------------------------

    @Test
    @Order(7)
    void frameHandler_frameCreatedAndMainFrameChanged() throws Exception {

        CountDownLatch frameCreatedLatch = new CountDownLatch(1);
        CountDownLatch mainFrameLatch = new CountDownLatch(1);
        AtomicBoolean frameCreatedCalled = new AtomicBoolean(false);

        CefClient client = app.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {})
                .addLoadHandler(new CefLoadHandler() {})
                .addRenderHandler(new MinimalRenderHandler(100, 100))
                .addFrameHandler(new CefFrameHandler() {
                    @Override
                    public void onFrameCreated(long browser, long frame) {
                        frameCreatedCalled.set(true);
                        frameCreatedLatch.countDown();
                    }

                    @Override
                    public void onMainFrameChanged(long browser, long oldFrame, long newFrame) {
                        mainFrameLatch.countDown();
                    }
                });

        CefBrowserOsr browser = client.createBrowser("about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(frameCreatedLatch, 10_000))
                .as("onFrameCreated should fire")
                .isTrue();
        assertThat(pumpUntil(mainFrameLatch, 10_000))
                .as("onMainFrameChanged should fire")
                .isTrue();
        assertThat(frameCreatedCalled.get()).isTrue();

        browser.close(true);
    }

    // -----------------------------------------------------------------------
    // 8. Request handler: onRenderViewReady fires
    // -----------------------------------------------------------------------

    @Test
    @Order(8)
    void requestHandler_renderViewReady() throws Exception {

        CountDownLatch readyLatch = new CountDownLatch(1);

        CefClient client = app.createClient();
        client.addLifeSpanHandler(new CefLifeSpanHandler() {})
                .addLoadHandler(new CefLoadHandler() {})
                .addRenderHandler(new MinimalRenderHandler(100, 100))
                .addRequestHandler(new CefRequestHandler() {
                    @Override
                    public void onRenderViewReady(long browser) {
                        readyLatch.countDown();
                    }
                });

        CefBrowserOsr browser = client.createBrowser("about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(readyLatch, 10_000))
                .as("onRenderViewReady should fire")
                .isTrue();

        browser.close(true);
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    /**
     * Pump the CEF message loop on this thread until the latch reaches zero or timeout expires. CEF requires
     * doMessageLoopWork to run on the same thread as cef_initialize.
     */
    private static boolean pumpUntil(CountDownLatch latch, long timeoutMs) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (latch.getCount() > 0 && System.currentTimeMillis() < deadline) {
            app.doMessageLoopWork();
            Thread.sleep(16); // ~60Hz
        }
        return latch.getCount() == 0;
    }

    /**
     * Minimal render handler that provides a fixed viewport size. Required for all OSR browsers - CEF queries
     * getViewRect to know the render target size.
     */
    static class MinimalRenderHandler implements CefRenderHandler {
        private final int width;
        private final int height;

        MinimalRenderHandler(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void getViewRect(long browser, CefMutableRect rect) {
            rect.x = 0;
            rect.y = 0;
            rect.width = width;
            rect.height = height;
        }
    }
}
