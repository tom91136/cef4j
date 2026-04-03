package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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

    @BeforeAll
    static void initCef() throws Exception {
        SystemBootstrap.load();

        Path cacheDir = Files.createTempDirectory("cef4j-test-cache-");
        cacheDir.toFile().deleteOnExit();

        if (Cef.INSTANCE.getState() == Cef.State.UNINITIALISED) {
            CefSettings.Mutable settings = new CefSettings.Mutable();
            settings.cachePath = cacheDir.toAbsolutePath().toString();
            settings.windowlessRenderingEnabled = 1;
            settings.externalMessagePump = 1;
            settings.multiThreadedMessageLoop = 0;

            List<String> extraArgs = new ArrayList<>();
            extraArgs.add("--disable-popup-blocking");
            if (OS.isLinux()) {
                extraArgs.add("--no-sandbox");
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
            Cef.INSTANCE.initialise(settings, extraArgs);
        }
    }

    @AfterAll
    static void shutdownCef() {
        // Do not dispose - CEF cannot be re-initialized after shutdown, and other test
        // classes in the same JVM fork need it. The process exit cleans up CEF resources.
    }

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
                    public void onAfterCreated(@Nonnull CefBrowser browser) {
                        browserRef.set(browser);
                        createdLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nonnull CefBrowser browser, @Nonnull CefFrame frame, int httpstatuscode) {
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

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(createdLatch, 10_000))
                .as("onAfterCreated should fire within 10s")
                .isTrue();
        assertThat(browserRef.get()).as("browser object").isNotNull();

        assertThat(pumpUntil(loadEndLatch, 10_000))
                .as("onLoadEnd should fire within 10s")
                .isTrue();

        // about:blank loads with status 0 (no HTTP involved) or 200
        assertThat(httpStatus.get()).as("HTTP status for about:blank").isIn(0, 200);

        closeBrowser(browser);
    }

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
                    public void getViewRect(@Nonnull CefBrowser browser, @Nonnull CefRect.Mutable rect) {
                        rect.x = 0;
                        rect.y = 0;
                        rect.width = viewWidth;
                        rect.height = viewHeight;
                        viewRectCalled.set(true);
                    }

                    @Override
                    public void onPaint(
                            @Nonnull CefBrowser browser,
                            @Nonnull CefPaintElementType type,
                            long dirtyRectsCount,
                            @Nonnull CefRect[] dirtyRects,
                            @Nonnull ByteBuffer buffer,
                            int width,
                            int height) {
                        if (paintLatch.getCount() > 0) {
                            paintWidth.set(width);
                            paintHeight.set(height);
                            byte[] copy = new byte[buffer.remaining()];
                            buffer.get(copy);
                            pixelBuffer.set(copy);
                            paintLatch.countDown();
                        }
                    }
                });
            }
        };

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(paintLatch, 15_000))
                .as("onPaint should fire within 15s")
                .isTrue();

        assertThat(viewRectCalled.get()).as("getViewRect was called").isTrue();
        assertThat(paintWidth.get()).as("paint width").isEqualTo(viewWidth);
        assertThat(paintHeight.get()).as("paint height").isEqualTo(viewHeight);

        byte[] buf = pixelBuffer.get();
        assertThat(buf).as("pixel buffer").isNotNull();
        assertThat(buf.length).as("pixel buffer size (BGRA)").isEqualTo(viewWidth * viewHeight * 4);

        closeBrowser(browser);
    }

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
                    public void onTitleChange(@Nonnull CefBrowser browser, String title) {
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

        CefBrowser browser = createWindowlessBrowser(client, dataUrl);

        assertThat(pumpUntil(titleLatch, 10_000))
                .as("onTitleChange should fire within 10s")
                .isTrue();
        assertThat(receivedTitle.get()).as("title from HTML").isEqualTo("cef4j-test-title");

        closeBrowser(browser);
    }

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
                            @Nonnull CefBrowser browser, boolean isloading, boolean cangoback, boolean cangoforward) {
                        events.add("stateChange:isLoading=" + isloading);
                        if (!isloading) {
                            doneLatch.countDown();
                        }
                    }

                    @Override
                    public void onLoadStart(
                            @Nonnull CefBrowser browser,
                            @Nonnull CefFrame frame,
                            @Nonnull CefTransitionType transitionType) {
                        events.add("loadStart");
                    }

                    @Override
                    public void onLoadEnd(@Nonnull CefBrowser browser, @Nonnull CefFrame frame, int httpstatuscode) {
                        events.add("loadEnd:" + httpstatuscode);
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(doneLatch, 10_000))
                .as("loading should complete within 10s")
                .isTrue();

        assertThat(events)
                .as("load event sequence")
                .contains("stateChange:isLoading=true", "stateChange:isLoading=false");

        closeBrowser(browser);
    }

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
                    public void onAfterCreated(@Nonnull CefBrowser browser) {
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

        CefBrowser browser1 = createWindowlessBrowser(client, "about:blank");
        CefBrowser browser2 = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(createdLatch, 10_000))
                .as("both browsers should be created")
                .isTrue();

        assertThat(browsers).as("two distinct browsers").hasSize(2);
        assertThat(browsers.get(0))
                .as("browsers have different native pointers")
                .isNotEqualTo(browsers.get(1));

        closeBrowser(browser1);
        closeBrowser(browser2);
    }

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
                    public void onLoadEnd(@Nonnull CefBrowser browser, @Nonnull CefFrame frame, int httpstatuscode) {
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefDisplayHandler> getDisplayHandler() {
                return Optional.of(new CefDisplayHandler() {
                    @Override
                    public boolean onConsoleMessage(
                            @Nonnull CefBrowser browser,
                            @Nonnull CefLogSeverity level,
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

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(loadLatch, 10_000)).isTrue();

        browser.getMainFrame().orElseThrow().executeJavaScript("console.log('cef4j-interop-test')", "test.js", 1);

        assertThat(pumpUntil(consoleLatch, 10_000))
                .as("console message callback should fire")
                .isTrue();
        assertThat(consoleMsg.get()).as("console message text").isEqualTo("cef4j-interop-test");
        assertThat(consoleSeverity.get()).as("console log severity").isNotNull();

        closeBrowser(browser);
    }

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
                    public void onFrameCreated(@Nonnull CefBrowser browser, @Nonnull CefFrame frame) {
                        frameCreatedCalled.set(true);
                        frameCreatedLatch.countDown();
                    }

                    @Override
                    public void onMainFrameChanged(@Nonnull CefBrowser browser, CefFrame oldFrame, CefFrame newFrame) {
                        mainFrameLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(frameCreatedLatch, 10_000))
                .as("onFrameCreated should fire")
                .isTrue();
        assertThat(pumpUntil(mainFrameLatch, 10_000))
                .as("onMainFrameChanged should fire")
                .isTrue();
        assertThat(frameCreatedCalled.get()).isTrue();

        closeBrowser(browser);
    }

    @Test
    @Order(8)
    void requestHandler_renderViewReady() throws Exception {

        CountDownLatch readyLatch = new CountDownLatch(1);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefRequestHandler> getRequestHandler() {
                return Optional.of(new CefRequestHandler() {
                    @Override
                    public void onRenderViewReady(@Nonnull CefBrowser browser) {
                        readyLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(readyLatch, 10_000))
                .as("onRenderViewReady should fire")
                .isTrue();

        closeBrowser(browser);
    }

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

    @Test
    @Order(15)
    void globals_currentlyOnThread() {
        // We're on the UI thread (same thread as cef_initialize)
        assertThat(CefGlobals.currentlyOn(CefThreadId.of(CefThreadId.Kind.UI))).isEqualTo(1);
        // We're NOT on the IO thread
        assertThat(CefGlobals.currentlyOn(CefThreadId.of(CefThreadId.Kind.IO))).isEqualTo(0);
    }

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

    @Test
    @Order(19)
    void objectPtr_isSameIsEqual_preservesArgValidity() {
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

    @Test
    @Order(21)
    void dictionaryCopy_isEqual() throws Exception {
        CefDictionaryValue original = CefDictionaryValue.create().orElseThrow();
        original.setString("a", "1");
        original.setInt("b", 2);

        CefDictionaryValue copy = original.copy(false).orElseThrow();
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

    @Test
    @Order(23)
    void byValueSize_pendingOnJvm_sizeofFromNative() throws Exception {
        // Entering CEF: Java-created structs show "pending" in toString (size == -1)
        CefWindowInfo.Mutable windowInfo = new CefWindowInfo.Mutable();
        assertThat(windowInfo.toString())
                .as("JVM-created CefWindowInfo.Mutable")
                .contains("size=pending");

        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        assertThat(browserSettings.toString())
                .as("JVM-created CefBrowserSettings.Mutable")
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
                    public void onAfterCreated(@Nonnull CefBrowser browser) {
                        createdLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nonnull CefBrowser browser, @Nonnull CefFrame frame, int httpstatuscode) {
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefKeyboardHandler> getKeyboardHandler() {
                return Optional.of(new CefKeyboardHandler() {
                    @Override
                    public boolean onKeyEvent(@Nonnull CefBrowser browser, @Nonnull CefKeyEvent event, long osEvent) {
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

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(createdLatch, 10_000)).as("browser created").isTrue();
        assertThat(pumpUntil(loadLatch, 10_000)).as("page loaded").isTrue();

        // Leaving CEF: send a key event and capture it back via keyboard handler
        var host = browser.getHost().orElseThrow();
        host.setFocus(true);
        host.sendKeyEvent(new net.kurobako.cef4j.gen.CefKeyEvent(
                net.kurobako.cef4j.gen.CefKeyEventType.of(net.kurobako.cef4j.gen.CefKeyEventType.Kind.KEYUP),
                0,
                65,
                0,
                0,
                'A',
                'A',
                0));
        assertThat(pumpUntil(keyLatch, 10_000)).as("onKeyEvent should fire").isTrue();

        CefKeyEvent nativeEvent = capturedEvent.get();
        assertThat(nativeEvent).as("captured key event").isNotNull();
        assertThat(nativeEvent.toString())
                .as("native CefKeyEvent should have actual sizeof, not pending")
                .doesNotContain("size=pending")
                .matches(".*size=\\d+.*");

        closeBrowser(browser);
    }

    @Test
    @Order(24)
    void binaryValue_roundTrip() {
        byte[] data = "Hello, binary!".getBytes(StandardCharsets.UTF_8);
        ByteBuffer direct = ByteBuffer.allocateDirect(data.length);
        direct.put(data);

        CefBinaryValue bin = CefBinaryValue.create(direct).orElseThrow();
        assertThat(bin.isValid()).isTrue();
        assertThat(bin.getSize()).isEqualTo(data.length);

        ByteBuffer out = ByteBuffer.allocateDirect(data.length);
        long copied = bin.getData(out, 0);
        assertThat(copied).isEqualTo(data.length);

        byte[] result = new byte[data.length];
        out.position(0);
        out.get(result);
        assertThat(new String(result, StandardCharsets.UTF_8)).isEqualTo("Hello, binary!");

        // copy() returns a distinct but equal value
        CefBinaryValue copy = bin.copy().orElseThrow();
        assertThat(copy.isEqual(bin)).isTrue();
        assertThat(copy.isSame(bin)).isFalse();

        copy.close();
        bin.close();
    }

    @Test
    @Order(25)
    void multimapValues_roundTrip() {
        CefRequest req = CefRequest.create().orElseThrow();

        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Accept", List.of("text/html", "application/json"));
        headers.put("X-Custom", List.of("one", "two", "three"));
        req.setHeaderMap(headers);

        Map<String, List<String>> retrieved = new HashMap<>();
        req.getHeaderMap(retrieved);

        assertThat(retrieved.get("Accept")).containsExactlyInAnyOrder("text/html", "application/json");
        assertThat(retrieved.get("X-Custom")).containsExactlyInAnyOrder("one", "two", "three");
    }

    @Test
    @Order(26)
    void processMessage_createAndAccessArgList() {
        CefProcessMessage msg = CefProcessMessage.create("test-msg").orElseThrow();
        assertThat(msg.isValid()).isTrue();
        assertThat(msg.isReadOnly()).isFalse();
        assertThat(msg.getName()).hasValue("test-msg");

        CefListValue args = msg.getArgumentList().orElseThrow();
        assertThat(args.isValid()).isTrue();
        args.setSize(2);
        args.setString(0, "arg0");
        args.setInt(1, 99);

        // Read back through the message's argument list
        CefListValue args2 = msg.getArgumentList().orElseThrow();
        assertThat(args2.getSize()).isEqualTo(2);
        assertThat(args2.getValue(0).orElseThrow().getString()).hasValue("arg0");
        assertThat(args2.getValue(1).orElseThrow().getInt()).isEqualTo(99);

        // copy preserves the argument list
        CefProcessMessage copy = msg.copy().orElseThrow();
        assertThat(copy.getName()).hasValue("test-msg");
        CefListValue copyArgs = copy.getArgumentList().orElseThrow();
        assertThat(copyArgs.getSize()).isEqualTo(2);
        assertThat(copyArgs.getValue(0).orElseThrow().getString()).hasValue("arg0");

        copy.close();
        msg.close();
    }

    @Test
    @Order(27)
    void renderHandler_getScreenInfoWithNestedRect() throws Exception {
        AtomicBoolean screenInfoCalled = new AtomicBoolean(false);
        AtomicReference<CefScreenInfo.Mutable> captured = new AtomicReference<>();
        CountDownLatch paintLatch = new CountDownLatch(1);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefRenderHandler() {
                    @Override
                    public void getViewRect(@Nonnull CefBrowser browser, @Nonnull CefRect.Mutable rect) {
                        rect.x = 0;
                        rect.y = 0;
                        rect.width = 200;
                        rect.height = 150;
                    }

                    @Override
                    public boolean getScreenInfo(
                            @Nonnull CefBrowser browser, @Nonnull CefScreenInfo.Mutable screenInfo) {
                        screenInfo.deviceScaleFactor = 2.0f;
                        screenInfo.depth = 32;
                        screenInfo.depthPerComponent = 8;
                        screenInfo.rect = new CefRect(0, 0, 200, 150);
                        screenInfo.availableRect = new CefRect(0, 0, 200, 150);
                        screenInfoCalled.set(true);
                        captured.set(screenInfo);
                        return true;
                    }

                    @Override
                    public void onPaint(
                            @Nonnull CefBrowser browser,
                            @Nonnull CefPaintElementType type,
                            long dirtyRectsCount,
                            @Nonnull CefRect[] dirtyRects,
                            @Nonnull ByteBuffer buffer,
                            int width,
                            int height) {
                        paintLatch.countDown();
                    }
                });
            }
        };

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(paintLatch, 15_000)).as("onPaint should fire").isTrue();
        assertThat(screenInfoCalled.get()).as("getScreenInfo was called").isTrue();

        // Verify CEF passed the mutable struct correctly (nested CefRect fields survived the boundary)
        CefScreenInfo.Mutable info = captured.get();
        assertThat(info).isNotNull();
        assertThat(info.deviceScaleFactor).isEqualTo(2.0f);
        assertThat(info.depth).isEqualTo(32);
        assertThat(info.rect).isNotNull();
        assertThat(info.rect.width).isEqualTo(200);
        assertThat(info.rect.height).isEqualTo(150);

        closeBrowser(browser);
    }

    @Test
    @Order(29)
    void nativePeer_doubleCloseIsSafe() {
        CefDictionaryValue dict = CefDictionaryValue.create().orElseThrow();
        dict.setString("k", "v");
        assertThat(dict.isValid()).isTrue();

        dict.close();
        // Second close must not crash (Cleaner.Cleanable.clean() is idempotent)
        dict.close();
        // Methods on a closed peer throw IllegalStateException instead of crashing the JVM
        assertThatThrownBy(dict::isValid).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @Order(30)
    void closedPeerAsArgument_shouldThrow() {
        ByteBuffer buf1 = ByteBuffer.allocateDirect(4);
        buf1.put(new byte[] {1, 2, 3, 4});
        ByteBuffer buf2 = ByteBuffer.allocateDirect(4);
        buf2.put(new byte[] {1, 2, 3, 4});
        CefBinaryValue val1 = CefBinaryValue.create(buf1).orElseThrow();
        CefBinaryValue val2 = CefBinaryValue.create(buf2).orElseThrow();
        val2.close();
        // val2.nativePtr is non-zero but freed; C++ reads it via GetLongField and calls add_ref on garbage
        assertThatThrownBy(() -> val1.isSame(val2)).isInstanceOf(IllegalStateException.class);
        val1.close();
    }

    @Test
    @Order(31)
    void heapByteBuffer_throwsIllegalArgument() {
        ByteBuffer heap = ByteBuffer.allocate(10);
        heap.put("test".getBytes(StandardCharsets.UTF_8));
        // Codegen emits a guard: GetDirectBufferAddress returns null for heap buffers
        assertThatThrownBy(() -> CefBinaryValue.create(heap))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("direct");
    }

    @Test
    @Order(33)
    void onBeforePopup_firesWithoutCrash() throws Exception {
        // Regression test for JNI local reference overflow in _on_before_popup.
        // The generated C++ code creates ~100+ local JNI refs but PushLocalFrame(97)
        // is too small, causing FindClass to return null and SIGSEGV on GetMethodID.
        CountDownLatch loadLatch = new CountDownLatch(1);
        CountDownLatch paintLatch = new CountDownLatch(1);
        CountDownLatch popupLatch = new CountDownLatch(1);
        AtomicBoolean popupFired = new AtomicBoolean(false);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public boolean onBeforePopup(
                            @Nullable CefBrowser browser,
                            @Nullable CefFrame frame,
                            int popupId,
                            @Nullable String targetUrl,
                            @Nullable String targetFrameName,
                            @Nonnull CefWindowOpenDisposition targetDisposition,
                            boolean userGesture,
                            @Nullable NativePointer popupFeatures,
                            @Nonnull CefWindowInfo.Mutable windowInfo,
                            @Nullable java.util.concurrent.atomic.AtomicReference<CefClient> client,
                            @Nonnull CefBrowserSettings.Mutable settings,
                            @Nullable java.util.concurrent.atomic.AtomicReference<CefDictionaryValue> extraInfo,
                            int[] noJavascriptAccess) {
                        popupFired.set(true);
                        popupLatch.countDown();
                        return true; // cancel the popup
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nonnull CefBrowser browser, @Nonnull CefFrame frame, int httpStatusCode) {
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefRenderHandler() {
                    @Override
                    public void getViewRect(@Nonnull CefBrowser browser, @Nonnull CefRect.Mutable rect) {
                        rect.x = 0;
                        rect.y = 0;
                        rect.width = 200;
                        rect.height = 200;
                    }

                    @Override
                    public void onPaint(
                            @Nullable CefBrowser browser,
                            @Nonnull CefPaintElementType type,
                            long dirtyRectsCount,
                            @Nonnull CefRect[] dirtyRects,
                            @Nonnull java.nio.ByteBuffer buffer,
                            int width,
                            int height) {
                        paintLatch.countDown();
                    }
                });
            }
        };

        // Load a page with a link that opens a popup via target=_blank.
        // Use sendMouseClickEvent for a real user gesture so CEF doesn't block the popup.
        String html = "<html><body style='margin:0'><a id='link' href='about:blank' target='_blank'"
                + " style='display:block;width:200px;height:200px;background:blue;'>open</a></body></html>";
        String dataUrl = "data:text/html;base64,"
                + java.util.Base64.getEncoder().encodeToString(html.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        CefBrowser browser = createWindowlessBrowser(client, dataUrl);
        assertThat(pumpUntil(loadLatch, 10_000)).as("page loaded").isTrue();
        assertThat(pumpUntil(paintLatch, 10_000)).as("first paint").isTrue();

        // Click the link at (50, 25) - inside the 200x200 anchor element
        CefBrowserHost host = browser.getHost().orElseThrow();
        CefMouseEvent mouseEvent = new CefMouseEvent(50, 25, 0);
        CefMouseButtonType left = CefMouseButtonType.of(CefMouseButtonType.Kind.LEFT);
        host.sendMouseClickEvent(mouseEvent, left, false, 1);
        host.sendMouseClickEvent(mouseEvent, left, true, 1);

        assertThat(pumpUntil(popupLatch, 10_000))
                .as("onBeforePopup should fire without SIGSEGV")
                .isTrue();
        assertThat(popupFired.get()).isTrue();

        closeBrowser(browser);
    }

    @Test
    @Order(32)
    void closedPeerAsNestedArgument_shouldThrow() {
        CefDictionaryValue outer = CefDictionaryValue.create().orElseThrow();
        CefDictionaryValue inner = CefDictionaryValue.create().orElseThrow();
        inner.setString("k", "v");
        inner.close();
        // inner.nativePtr is stale; C++ unwraps it and passes to cef_dictionary_value_t::set_dictionary
        assertThatThrownBy(() -> outer.setDictionary("nested", inner)).isInstanceOf(IllegalStateException.class);
        outer.close();
    }

    // Pump CEF message loop until latch reaches zero or timeout.
    // Must run on the same thread as cef_initialize.
    private static boolean pumpUntil(CountDownLatch latch, long timeoutMs) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (latch.getCount() > 0 && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
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
        public void getViewRect(@Nonnull CefBrowser browser, @Nonnull CefRect.Mutable rect) {
            rect.x = 0;
            rect.y = 0;
            rect.width = width;
            rect.height = height;
        }
    }

    private static CefBrowser createWindowlessBrowser(CefClient client, String url) {
        CefWindowInfo.Mutable windowInfo = new CefWindowInfo.Mutable();
        windowInfo.bounds = new CefRect(0, 0, 800, 600);
        windowInfo.windowlessRenderingEnabled = 1;
        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        browserSettings.windowlessFrameRate = 60;
        return Cef.INSTANCE.createBrowser(client, url, windowInfo.toImmutable(), browserSettings.toImmutable());
    }

    private static void closeBrowser(CefBrowser browser) {
        if (browser != null) {
            browser.getHost().ifPresent(host -> host.closeBrowser(true));
        }
    }
}
