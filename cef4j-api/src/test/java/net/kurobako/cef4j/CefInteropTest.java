package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @BeforeAll
    static void initCef() throws Exception {
        SystemBootstrap.load();

        Path cacheDir = Files.createTempDirectory("cef4j-test-cache-");
        cacheDir.toFile().deleteOnExit();

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
        AtomicReference<CefBrowser> browserRef = new AtomicReference<>();
        AtomicInteger httpStatus = new AtomicInteger(-1);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@javax.annotation.Nonnull CefBrowser browser) {
                        browserRef.set(browser);
                        createdLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefFrame frame,
                            int httpstatuscode) {
                        httpStatus.set(httpstatuscode);
                        loadEndLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(800, 600));
            }
        };

        CefBrowserOsr browser = app.createBrowser(client, "about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(createdLatch, 10_000))
                .as("onAfterCreated should fire within 10s")
                .isTrue();
        assertThat(browserRef.get()).as("browser object").isNotNull();

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

        CefClient client = new CefClient() {
            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefRenderHandler() {
                    @Override
                    public void getViewRect(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefMutableRect rect) {
                        rect.x = 0;
                        rect.y = 0;
                        rect.width = viewWidth;
                        rect.height = viewHeight;
                        viewRectCalled.set(true);
                    }

                    @Override
                    public void onPaint(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefPaintElementType type,
                            long dirtyRectsCount,
                            @javax.annotation.Nonnull CefRect[] dirtyRects,
                            @javax.annotation.Nonnull ByteBuffer buffer,
                            int width,
                            int height) {
                        if (paintLatch.getCount() > 0) {
                            paintWidth.set(width);
                            paintHeight.set(height);
                            if (buffer != null) {
                                byte[] copy = new byte[buffer.remaining()];
                                buffer.get(copy);
                                pixelBuffer.set(copy);
                            }
                            paintLatch.countDown();
                        }
                    }
                });
            }
        };

        CefBrowserOsr browser = app.createBrowser(client, "about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(paintLatch, 15_000))
                .as("onPaint should fire within 15s")
                .isTrue();

        assertThat(viewRectCalled.get()).as("getViewRect was called").isTrue();
        assertThat(paintWidth.get()).as("paint width").isEqualTo(viewWidth);
        assertThat(paintHeight.get()).as("paint height").isEqualTo(viewHeight);

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

        CefClient client = new CefClient() {
            @Override
            public Optional<CefDisplayHandler> getDisplayHandler() {
                return Optional.of(new CefDisplayHandler() {
                    @Override
                    public void onTitleChange(@javax.annotation.Nonnull CefBrowser browser, String title) {
                        if (title != null && !title.isEmpty()) {
                            receivedTitle.set(title);
                            titleLatch.countDown();
                        }
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        String html = "<html><head><title>cef4j-test-title</title></head><body></body></html>";
        String dataUrl = "data:text/html;charset=utf-8," + html.replace(" ", "%20");

        CefBrowserOsr browser = app.createBrowser(client, dataUrl);
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

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadingStateChange(
                            @javax.annotation.Nonnull CefBrowser browser,
                            boolean isloading,
                            boolean cangoback,
                            boolean cangoforward) {
                        events.add("stateChange:isLoading=" + isloading);
                        if (!isloading) {
                            doneLatch.countDown();
                        }
                    }

                    @Override
                    public void onLoadStart(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefFrame frame,
                            @javax.annotation.Nonnull CefTransitionType transitionType) {
                        events.add("loadStart");
                    }

                    @Override
                    public void onLoadEnd(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefFrame frame,
                            int httpstatuscode) {
                        events.add("loadEnd:" + httpstatuscode);
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowserOsr browser = app.createBrowser(client, "about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(doneLatch, 10_000))
                .as("loading should complete within 10s")
                .isTrue();

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
        CopyOnWriteArrayList<CefBrowser> browsers = new CopyOnWriteArrayList<>();

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@javax.annotation.Nonnull CefBrowser browser) {
                        browsers.add(browser);
                        createdLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowserOsr browser1 = app.createBrowser(client, "about:blank");
        CefBrowserOsr browser2 = app.createBrowser(client, "about:blank");
        browser1.createImmediately();
        browser2.createImmediately();

        assertThat(pumpUntil(createdLatch, 10_000))
                .as("both browsers should be created")
                .isTrue();

        assertThat(browsers).as("two distinct browsers").hasSize(2);
        assertThat(browsers.get(0))
                .as("browsers have different native pointers")
                .isNotEqualTo(browsers.get(1));

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

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefFrame frame,
                            int httpstatuscode) {
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefDisplayHandler> getDisplayHandler() {
                return Optional.of(new CefDisplayHandler() {
                    @Override
                    public boolean onConsoleMessage(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefLogSeverity level,
                            String message,
                            String source,
                            int line) {
                        consoleMsg.set(message);
                        consoleSeverity.set(level);
                        consoleLatch.countDown();
                        return false;
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowserOsr browser = app.createBrowser(client, "about:blank");
        browser.createImmediately();

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

        CefClient client = new CefClient() {
            @Override
            public Optional<CefFrameHandler> getFrameHandler() {
                return Optional.of(new CefFrameHandler() {
                    @Override
                    public void onFrameCreated(
                            @javax.annotation.Nonnull CefBrowser browser, @javax.annotation.Nonnull CefFrame frame) {
                        frameCreatedCalled.set(true);
                        frameCreatedLatch.countDown();
                    }

                    @Override
                    public void onMainFrameChanged(
                            @javax.annotation.Nonnull CefBrowser browser, CefFrame oldFrame, CefFrame newFrame) {
                        mainFrameLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowserOsr browser = app.createBrowser(client, "about:blank");
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

        CefClient client = new CefClient() {
            @Override
            public Optional<CefRequestHandler> getRequestHandler() {
                return Optional.of(new CefRequestHandler() {
                    @Override
                    public void onRenderViewReady(@javax.annotation.Nonnull CefBrowser browser) {
                        readyLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowserOsr browser = app.createBrowser(client, "about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(readyLatch, 10_000))
                .as("onRenderViewReady should fire")
                .isTrue();

        browser.close(true);
    }

    // =======================================================================
    // Complex JNI interaction tests
    // =======================================================================

    // -----------------------------------------------------------------------
    // 9. Static factory: CefValue / CefDictionaryValue creation and round-trip
    // -----------------------------------------------------------------------

    @Test
    @Order(9)
    void staticFactory_dictionaryValueRoundTrip() throws Exception {
        CefDictionaryValue dict = CefDictionaryValue.create().orElseThrow();
        assertThat(dict.isValid()).isTrue();

        dict.setString("key1", "hello");
        dict.setInt("key2", 42);
        dict.setBool("key3", true);
        dict.setNull("key4");
        dict.setDouble("key5", 3.14);

        assertThat(dict.getSize()).isEqualTo(5);
        assertThat(dict.hasKey("key1")).isTrue();
        assertThat(dict.getString("key1")).hasValue("hello");
        assertThat(dict.getInt("key2")).isEqualTo(42);
        assertThat(dict.getBool("key3")).isTrue();
        assertThat(dict.getDouble("key5")).isCloseTo(3.14, org.assertj.core.data.Offset.offset(0.001));
        assertThat(dict.getType("key4")).isEqualTo(CefValueType.of(CefValueType.Kind.NULL));

        // isSame
        assertThat(dict.isSame(dict)).isTrue();

        // getKeys populates a string list
        List<String> keys = new ArrayList<>();
        assertThat(dict.getKeys(keys)).isTrue();
        assertThat(keys).containsExactlyInAnyOrder("key1", "key2", "key3", "key4", "key5");
    }

    // -----------------------------------------------------------------------
    // 10. Static factory: CefValue wrapping and type inspection
    // -----------------------------------------------------------------------

    @Test
    @Order(10)
    void staticFactory_cefValueTypes() {
        CefValue val = CefValue.create().orElseThrow();
        assertThat(val.isValid()).isTrue();

        // Initially null
        assertThat(val.getType()).isEqualTo(CefValueType.of(CefValueType.Kind.NULL));

        val.setBool(true);
        assertThat(val.getType()).isEqualTo(CefValueType.of(CefValueType.Kind.BOOL));
        assertThat(val.getBool()).isTrue();

        val.setInt(99);
        assertThat(val.getType()).isEqualTo(CefValueType.of(CefValueType.Kind.INT));
        assertThat(val.getInt()).isEqualTo(99);

        val.setString("test");
        assertThat(val.getType()).isEqualTo(CefValueType.of(CefValueType.Kind.STRING));
        assertThat(val.getString()).hasValue("test");

        val.setDouble(2.718);
        assertThat(val.getType()).isEqualTo(CefValueType.of(CefValueType.Kind.DOUBLE));
        assertThat(val.getDouble()).isCloseTo(2.718, org.assertj.core.data.Offset.offset(0.001));

        val.setNull();
        assertThat(val.getType()).isEqualTo(CefValueType.of(CefValueType.Kind.NULL));
    }

    // -----------------------------------------------------------------------
    // 11. Static factory: CefResponse with enum get/set
    // -----------------------------------------------------------------------

    @Test
    @Order(11)
    void staticFactory_responseEnumAndStrings() {
        CefResponse resp = CefResponse.create().orElseThrow();
        assertThat(resp.isReadOnly()).isFalse();

        resp.setStatus(404);
        assertThat(resp.getStatus()).isEqualTo(404);

        resp.setStatusText("Not Found");
        assertThat(resp.getStatusText()).hasValue("Not Found");

        resp.setMimeType("text/html");
        assertThat(resp.getMimeType()).hasValue("text/html");

        resp.setError(CefErrorCode.of(CefErrorCode.Kind.NONE));
        assertThat(resp.getError()).isEqualTo(CefErrorCode.of(CefErrorCode.Kind.NONE));

        resp.setCharset("utf-8");
        assertThat(resp.getCharset()).hasValue("utf-8");

        resp.setHeaderByName("X-Custom", "value123", true);
        assertThat(resp.getHeaderByName("X-Custom")).hasValue("value123");
    }

    // -----------------------------------------------------------------------
    // 12. Static factory: CefRequest with header map (multimap marshaling)
    // -----------------------------------------------------------------------

    @Test
    @Order(12)
    void staticFactory_requestHeaderMap() {
        CefRequest req = CefRequest.create().orElseThrow();
        assertThat(req.isReadOnly()).isFalse();

        req.setUrl("https://example.com/test");
        assertThat(req.getUrl()).hasValue("https://example.com/test");

        req.setMethod("POST");
        assertThat(req.getMethod()).hasValue("POST");

        // Header map round-trip
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Content-Type", List.of("application/json"));
        headers.put("Accept", List.of("text/html", "application/json"));
        req.setHeaderMap(headers);

        Map<String, List<String>> retrieved = new HashMap<>();
        req.getHeaderMap(retrieved);
        assertThat(retrieved).containsKey("Content-Type");
    }

    // -----------------------------------------------------------------------
    // 13. Static factory: CefCommandLine string marshaling
    // -----------------------------------------------------------------------

    @Test
    @Order(13)
    void staticFactory_commandLine() {
        CefCommandLine cmd = CefCommandLine.create().orElseThrow();
        assertThat(cmd.isValid()).isTrue();

        cmd.setProgram("test-app");
        assertThat(cmd.getProgram()).hasValue("test-app");

        cmd.appendSwitch("enable-logging");
        assertThat(cmd.hasSwitch("enable-logging")).isTrue();

        cmd.appendSwitchWithValue("log-level", "3");
        assertThat(cmd.getSwitchValue("log-level")).hasValue("3");

        cmd.appendArgument("extra-arg");
        List<String> args = new ArrayList<>();
        cmd.getArguments(args);
        assertThat(args).contains("extra-arg");

        assertThat(cmd.getCommandLineString()).isPresent();
    }

    // -----------------------------------------------------------------------
    // 14. CefGlobals: string utility statics (URI encode/decode, MIME, base64)
    // -----------------------------------------------------------------------

    @Test
    @Order(14)
    void globals_stringUtilities() {
        // MIME type lookup
        assertThat(CefGlobals.getMimeType("html")).hasValue("text/html");
        assertThat(CefGlobals.getMimeType("json")).hasValue("application/json");
        assertThat(CefGlobals.getMimeType("png")).hasValue("image/png");

        // Extensions for MIME type (populates a list)
        List<String> extensions = new ArrayList<>();
        CefGlobals.getExtensionsForMimeType("text/html", extensions);
        assertThat(extensions).contains("html");

        // URI encode
        assertThat(CefGlobals.uriencode("hello world", 0)).hasValue("hello%20world");
        assertThat(CefGlobals.uriencode("hello world", 1)).hasValue("hello+world");

        // Base64 encode
        byte[] data = "Hello, CEF!".getBytes(StandardCharsets.UTF_8);
        ByteBuffer buf = ByteBuffer.allocateDirect(data.length);
        buf.put(data);
        buf.flip();
        assertThat(CefGlobals.base64Encode(buf)).hasValue("SGVsbG8sIENFRiE=");
    }

    // -----------------------------------------------------------------------
    // 15. CefGlobals: thread ID enum parameter
    // -----------------------------------------------------------------------

    @Test
    @Order(15)
    void globals_currentlyOnThread() {
        // We're on the UI thread (same thread as cef_initialize)
        assertThat(CefGlobals.currentlyOn(CefThreadId.of(CefThreadId.Kind.UI))).isEqualTo(1);
        // We're NOT on the IO thread
        assertThat(CefGlobals.currentlyOn(CefThreadId.of(CefThreadId.Kind.IO))).isEqualTo(0);
    }

    // -----------------------------------------------------------------------
    // 16. Static factory: CefWaitableEvent signal/wait
    // -----------------------------------------------------------------------

    @Test
    @Order(16)
    void staticFactory_waitableEvent() {
        // Manual reset, initially unsignaled
        CefWaitableEvent event = CefWaitableEvent.create(0, 0).orElseThrow();
        assertThat(event.isSignaled()).isFalse();

        event.signal();
        assertThat(event.isSignaled()).isTrue();

        event.reset();
        assertThat(event.isSignaled()).isFalse();

        // Auto-reset, initially signaled
        CefWaitableEvent autoEvent = CefWaitableEvent.create(1, 1).orElseThrow();
        assertThat(autoEvent.isSignaled()).isTrue();
        // timedWait consumes the signal on auto-reset
        autoEvent.timedWait(0);
        assertThat(autoEvent.isSignaled()).isFalse();
    }

    // -----------------------------------------------------------------------
    // 17. Static factory: CefRequestContext global context
    // -----------------------------------------------------------------------

    @Test
    @Order(17)
    void staticFactory_globalRequestContext() {
        CefRequestContext ctx = CefRequestContext.getGlobalContext().orElseThrow();
        assertThat(ctx.isGlobal()).isTrue();
        assertThat(ctx.isSame(ctx)).isTrue();

        // cachePath returns Optional<String>
        Optional<String> cachePath = ctx.getCachePath();
        // Global context may or may not have a cache path
        assertThat(cachePath).isNotNull(); // Optional itself is non-null
    }

    // -----------------------------------------------------------------------
    // 18. Nested dictionary + list value (deep structure marshaling)
    // -----------------------------------------------------------------------

    @Test
    @Order(18)
    void nestedDictionaryAndList() {
        CefDictionaryValue outer = CefDictionaryValue.create().orElseThrow();

        // Nested dictionary
        CefDictionaryValue inner = CefDictionaryValue.create().orElseThrow();
        inner.setString("nested", "value");
        outer.setDictionary("child", inner);

        assertThat(outer.getType("child")).isEqualTo(CefValueType.of(CefValueType.Kind.DICTIONARY));
        CefDictionaryValue retrieved = outer.getDictionary("child").orElseThrow();
        assertThat(retrieved.getString("nested")).hasValue("value");

        // List value
        CefListValue list = CefListValue.create().orElseThrow();
        assertThat(list.isValid()).isTrue();
        assertThat(list.setSize(3)).isTrue();
        list.setString(0, "a");
        list.setString(1, "b");
        list.setInt(2, 42);
        assertThat(list.getSize()).isEqualTo(3);
        assertThat(list.getValue(0).orElseThrow().getString()).hasValue("a");
        assertThat(list.getValue(1).orElseThrow().getString()).hasValue("b");
        assertThat(list.getInt(2)).isEqualTo(42);
        assertThat(list.getType(0)).isEqualTo(CefValueType.of(CefValueType.Kind.STRING));
        assertThat(list.getType(2)).isEqualTo(CefValueType.of(CefValueType.Kind.INT));

        outer.setList("items", list);
        assertThat(outer.getType("items")).isEqualTo(CefValueType.of(CefValueType.Kind.LIST));
    }

    // -----------------------------------------------------------------------
    // 19. ObjectPtr parameter ref-counting: isSame/isEqual across objects
    // -----------------------------------------------------------------------

    @Test
    @Order(19)
    void objectPtr_isSameIsEqual_preservesArgValidity() throws Exception {
        CefDictionaryValue d1 = CefDictionaryValue.create().orElseThrow();
        CefDictionaryValue d2 = CefDictionaryValue.create().orElseThrow();

        // isSame with two different dicts
        assertThat(d1.isSame(d2)).isFalse();
        // Argument d2 must still be valid after being passed as ObjectPtr
        assertThat(d2.isValid()).isTrue();
        assertThat(d2.getSize()).isEqualTo(0);

        // isSame with self
        assertThat(d1.isSame(d1)).isTrue();
        assertThat(d1.isValid()).isTrue();

        // isEqual with two different (but empty) dicts
        assertThat(d1.isEqual(d2)).isTrue();
        assertThat(d1.isValid()).isTrue();
        assertThat(d2.isValid()).isTrue();

        // Make dicts differ, then check isEqual again
        d1.setInt("x", 1);
        assertThat(d1.isEqual(d2)).isFalse();
        assertThat(d2.isValid()).isTrue();

        // Both dicts must survive close without crash
        d1.close();
        d2.close();
    }

    // -----------------------------------------------------------------------
    // 20. ObjectPtr parameter ref-counting: setDictionary/setBinary/setList
    // -----------------------------------------------------------------------

    @Test
    @Order(20)
    void objectPtr_setters_preserveArgValidity() throws Exception {
        CefDictionaryValue parent = CefDictionaryValue.create().orElseThrow();

        // setDictionary: child is passed as ObjectPtr
        CefDictionaryValue child = CefDictionaryValue.create().orElseThrow();
        child.setString("k", "v");
        parent.setDictionary("d", child);
        // child may become owned by parent, but the pointer should still be valid
        // (CEF may copy if already owned, but our ref should survive)

        // setList: list is passed as ObjectPtr
        CefListValue list = CefListValue.create().orElseThrow();
        list.setSize(1);
        list.setString(0, "item");
        parent.setList("l", list);

        // Verify parent has both
        assertThat(parent.getType("d")).isEqualTo(CefValueType.of(CefValueType.Kind.DICTIONARY));
        assertThat(parent.getType("l")).isEqualTo(CefValueType.of(CefValueType.Kind.LIST));

        // Close parent without crash
        parent.close();
    }

    // -----------------------------------------------------------------------
    // 21. Dictionary copy and isEqual
    // -----------------------------------------------------------------------

    @Test
    @Order(21)
    void dictionaryCopy_isEqual() throws Exception {
        CefDictionaryValue original = CefDictionaryValue.create().orElseThrow();
        original.setString("a", "1");
        original.setInt("b", 2);

        CefDictionaryValue copy = original.copy(0).orElseThrow();
        assertThat(copy.isValid()).isTrue();

        // Copy is equal but not same
        assertThat(original.isEqual(copy)).isTrue();
        assertThat(original.isSame(copy)).isFalse();

        // Mutating copy doesn't affect original
        copy.setString("c", "3");
        assertThat(original.hasKey("c")).isFalse();
        assertThat(original.isEqual(copy)).isFalse();

        // Both survive close
        copy.close();
        original.close();
    }

    // -----------------------------------------------------------------------
    // 22. CefValue with ObjectPtr setters (setValue on CefDictionaryValue)
    // -----------------------------------------------------------------------

    @Test
    @Order(22)
    void cefValue_objectPtr_roundTrip() {
        CefValue val = CefValue.create().orElseThrow();
        CefDictionaryValue dict = CefDictionaryValue.create().orElseThrow();
        dict.setString("inside", "value");

        // setDictionary passes dict as ObjectPtr to CefValue
        val.setDictionary(dict);
        assertThat(val.getType()).isEqualTo(CefValueType.of(CefValueType.Kind.DICTIONARY));

        CefDictionaryValue extracted = val.getDictionary().orElseThrow();
        assertThat(extracted.getString("inside")).hasValue("value");
    }

    // -----------------------------------------------------------------------
    // 23. By-value struct size field: -1 on JVM, sizeof on native
    // -----------------------------------------------------------------------

    @Test
    @Order(23)
    void byValueSize_pendingOnJvm_sizeofFromNative() throws Exception {
        // Entering CEF: Java-created structs show "pending" in toString (size == -1)
        CefMutableWindowInfo windowInfo = new CefMutableWindowInfo();
        assertThat(windowInfo.toString()).as("JVM-created CefMutableWindowInfo").contains("size=pending");

        CefMutableBrowserSettings browserSettings = new CefMutableBrowserSettings();
        assertThat(browserSettings.toString())
                .as("JVM-created CefMutableBrowserSettings")
                .contains("size=pending");

        CefKeyEvent jvmKeyEvent =
                new CefKeyEvent(CefKeyEventType.of(CefKeyEventType.Kind.KEYDOWN), 0, 65, 0, 0, 'A', 'A', 0);
        assertThat(jvmKeyEvent.toString()).as("JVM-created CefKeyEvent").contains("size=pending");

        // Browser creation succeeds despite size being pending, proving JNI sets sizeof
        CountDownLatch createdLatch = new CountDownLatch(1);
        CountDownLatch loadLatch = new CountDownLatch(1);
        AtomicReference<CefKeyEvent> capturedEvent = new AtomicReference<>();
        CountDownLatch keyLatch = new CountDownLatch(1);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@javax.annotation.Nonnull CefBrowser browser) {
                        createdLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefFrame frame,
                            int httpstatuscode) {
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefKeyboardHandler> getKeyboardHandler() {
                return Optional.of(new CefKeyboardHandler() {
                    @Override
                    public boolean onKeyEvent(
                            @javax.annotation.Nonnull CefBrowser browser,
                            @javax.annotation.Nonnull CefKeyEvent event,
                            long osEvent) {
                        capturedEvent.set(event);
                        keyLatch.countDown();
                        return false;
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowserOsr browser = app.createBrowser(client, "about:blank");
        browser.createImmediately();

        assertThat(pumpUntil(createdLatch, 10_000)).as("browser created").isTrue();
        assertThat(pumpUntil(loadLatch, 10_000)).as("page loaded").isTrue();

        // Leaving CEF: send a key event and capture it back via keyboard handler
        browser.setFocus(true);
        browser.sendKeyEvent(2, 0, 65, 0, 'A', 'A', false); // KEYUP, VK_A
        assertThat(pumpUntil(keyLatch, 10_000)).as("onKeyEvent should fire").isTrue();

        CefKeyEvent nativeEvent = capturedEvent.get();
        assertThat(nativeEvent).as("captured key event").isNotNull();
        assertThat(nativeEvent.toString())
                .as("native CefKeyEvent should have actual sizeof, not pending")
                .doesNotContain("size=pending")
                .matches(".*size=\\d+.*");

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
        public void getViewRect(
                @javax.annotation.Nonnull CefBrowser browser, @javax.annotation.Nonnull CefMutableRect rect) {
            rect.x = 0;
            rect.y = 0;
            rect.width = width;
            rect.height = height;
        }
    }
}
