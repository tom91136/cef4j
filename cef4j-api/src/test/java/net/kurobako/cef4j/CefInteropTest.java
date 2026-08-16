package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.junit.jupiter.api.Timeout;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CefInteropTest extends CefTestBase {

    @BeforeAll
    static void initCef() throws Exception {
        initCef(List.of("--disable-popup-blocking"));
    }

    @AfterAll
    static void shutdownCef() {
        // Surefire runs every native test class in its own fork. On macOS, use cef4j's managed
        // main-loop shutdown so Thread 0 is parked before normal JVM teardown can fire CEF's
        // remaining CFRunLoop observers.
        if (OS.isMacOS() && Cef.INSTANCE.state() == Cef.State.INITIALISED) Cef.INSTANCE.terminate();
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
                    public void onAfterCreated(@Nullable CefBrowser browser) {
                        browserRef.set(browser);
                        createdLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpstatuscode) {
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
                    public void getViewRect(@Nullable CefBrowser browser, @Nonnull CefRect.Mutable rect) {
                        rect.x = 0;
                        rect.y = 0;
                        rect.width = viewWidth;
                        rect.height = viewHeight;
                        viewRectCalled.set(true);
                    }

                    @Override
                    public void onPaint(
                            @Nullable CefBrowser browser,
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
        assertThat(Objects.requireNonNull(buf).length)
                .as("pixel buffer size (BGRA)")
                .isEqualTo(viewWidth * viewHeight * 4);

        closeBrowser(browser);
    }

    @Test
    @Order(3)
    @Timeout(30)
    void displayHandler_onTitleChangeMarshalsString() throws Exception {

        CountDownLatch titleLatch = new CountDownLatch(1);
        AtomicReference<String> receivedTitle = new AtomicReference<>();

        CefClient client = new CefClient() {
            @Override
            public Optional<CefDisplayHandler> getDisplayHandler() {
                return Optional.of(new CefDisplayHandler() {
                    @Override
                    public void onTitleChange(@Nullable CefBrowser browser, @Nullable String title) {
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

        assertThat(pumpUntil(titleLatch, 20_000))
                .as("onTitleChange should fire within 20s")
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
                            @Nullable CefBrowser browser, boolean isloading, boolean cangoback, boolean cangoforward) {
                        events.add("stateChange:isLoading=" + isloading);
                        if (!isloading) {
                            doneLatch.countDown();
                        }
                    }

                    @Override
                    public void onLoadStart(
                            @Nullable CefBrowser browser,
                            @Nullable CefFrame frame,
                            @Nullable CefTransitionType transitionType) {
                        events.add("loadStart");
                    }

                    @Override
                    public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpstatuscode) {
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
                    public void onAfterCreated(@Nullable CefBrowser browser) {
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
                    public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpstatuscode) {
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefDisplayHandler> getDisplayHandler() {
                return Optional.of(new CefDisplayHandler() {
                    @Override
                    public boolean onConsoleMessage(
                            @Nullable CefBrowser browser,
                            @Nullable CefLogSeverity level,
                            @Nullable String message,
                            @Nullable String source,
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
                    public void onFrameCreated(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
                        frameCreatedCalled.set(true);
                        frameCreatedLatch.countDown();
                    }

                    @Override
                    public void onMainFrameChanged(
                            @Nullable CefBrowser browser, @Nullable CefFrame oldFrame, @Nullable CefFrame newFrame) {
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
                    public void onRenderViewReady(@Nullable CefBrowser browser) {
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
    void staticFactory_dictionaryValueRoundTrip() {
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

        assertThat(dict.isSame(dict)).isTrue();

        List<String> keys = new ArrayList<>();
        assertThat(dict.getKeys(keys)).isTrue();
        assertThat(keys).containsExactlyInAnyOrder("key1", "key2", "key3", "key4", "key5");
    }

    @Test
    @Order(10)
    void staticFactory_cefValueTypes() {
        CefValue val = CefValue.create().orElseThrow();
        assertThat(val.isValid()).isTrue();

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
    void globals_stringUtilities() throws Exception {
        assertThat(CefGlobals.getMimeType("html")).hasValue("text/html");
        assertThat(CefGlobals.getMimeType("json")).hasValue("application/json");
        assertThat(CefGlobals.getMimeType("png")).hasValue("image/png");

        List<String> extensions = new ArrayList<>();
        CefGlobals.getExtensionsForMimeType("text/html", extensions);
        assertThat(extensions).contains("html");

        assertThat(CefGlobals.uriencode("hello world", 0)).hasValue("hello%20world");
        assertThat(CefGlobals.uriencode("hello world", 1)).hasValue("hello+world");
    }

    @Test
    @Order(15)
    void globals_currentlyOnThread() {
        if (OS.isMacOS()) {
            assertThat(CefGlobals.currentlyOn(CefThreadId.of(CefThreadId.Kind.UI)))
                    .isEqualTo(0);
        } else {
            assertThat(CefGlobals.currentlyOn(CefThreadId.of(CefThreadId.Kind.UI)))
                    .isEqualTo(1);
        }
        assertThat(CefGlobals.currentlyOn(CefThreadId.of(CefThreadId.Kind.IO))).isEqualTo(0);
    }

    @Test
    @Order(16)
    void staticFactory_waitableEvent() {
        CefWaitableEvent event = CefWaitableEvent.create(0, 0).orElseThrow();
        assertThat(event.isSignaled()).isFalse();

        event.signal();
        assertThat(event.isSignaled()).isTrue();

        event.reset();
        assertThat(event.isSignaled()).isFalse();

        CefWaitableEvent autoEvent = CefWaitableEvent.create(1, 1).orElseThrow();
        assertThat(autoEvent.isSignaled()).isTrue();
        autoEvent.timedWait(0);
        assertThat(autoEvent.isSignaled()).isFalse();
    }

    @Test
    @Order(17)
    void staticFactory_globalRequestContext() {
        CefRequestContext ctx = CefRequestContext.getGlobalContext().orElseThrow();
        assertThat(ctx.isGlobal()).isTrue();
        assertThat(ctx.isSame(ctx)).isTrue();

        Optional<String> cachePath = ctx.getCachePath();
        assertThat(cachePath).isNotNull();
    }

    @Test
    @Order(18)
    void nestedDictionaryAndList() {
        CefDictionaryValue outer = CefDictionaryValue.create().orElseThrow();

        CefDictionaryValue inner = CefDictionaryValue.create().orElseThrow();
        inner.setString("nested", "value");
        outer.setDictionary("child", inner);

        assertThat(outer.getType("child")).isEqualTo(CefValueType.of(CefValueType.Kind.DICTIONARY));
        CefDictionaryValue retrieved = outer.getDictionary("child").orElseThrow();
        assertThat(retrieved.getString("nested")).hasValue("value");

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

        assertThat(d1.isSame(d2)).isFalse();
        assertThat(d2.isValid()).isTrue();
        assertThat(d2.getSize()).isEqualTo(0);

        assertThat(d1.isSame(d1)).isTrue();
        assertThat(d1.isValid()).isTrue();

        assertThat(d1.isEqual(d2)).isTrue();
        assertThat(d1.isValid()).isTrue();
        assertThat(d2.isValid()).isTrue();

        d1.setInt("x", 1);
        assertThat(d1.isEqual(d2)).isFalse();
        assertThat(d2.isValid()).isTrue();

        d1.close();
        d2.close();
    }

    @Test
    @Order(20)
    void objectPtr_setters_preserveArgValidity() {
        CefDictionaryValue parent = CefDictionaryValue.create().orElseThrow();

        CefDictionaryValue child = CefDictionaryValue.create().orElseThrow();
        child.setString("k", "v");
        parent.setDictionary("d", child);

        CefListValue list = CefListValue.create().orElseThrow();
        list.setSize(1);
        list.setString(0, "item");
        parent.setList("l", list);

        assertThat(parent.getType("d")).isEqualTo(CefValueType.of(CefValueType.Kind.DICTIONARY));
        assertThat(parent.getType("l")).isEqualTo(CefValueType.of(CefValueType.Kind.LIST));

        parent.close();
    }

    @Test
    @Order(21)
    void dictionaryCopy_isEqual() {
        CefDictionaryValue original = CefDictionaryValue.create().orElseThrow();
        original.setString("a", "1");
        original.setInt("b", 2);

        CefDictionaryValue copy = original.copy(false).orElseThrow();
        assertThat(copy.isValid()).isTrue();

        assertThat(original.isEqual(copy)).isTrue();
        assertThat(original.isSame(copy)).isFalse();

        copy.setString("c", "3");
        assertThat(original.hasKey("c")).isFalse();
        assertThat(original.isEqual(copy)).isFalse();

        copy.close();
        original.close();
    }

    @Test
    @Order(22)
    void cefValue_objectPtr_roundTrip() {
        CefValue val = CefValue.create().orElseThrow();
        CefDictionaryValue dict = CefDictionaryValue.create().orElseThrow();
        dict.setString("inside", "value");

        val.setDictionary(dict);
        assertThat(val.getType()).isEqualTo(CefValueType.of(CefValueType.Kind.DICTIONARY));

        CefDictionaryValue extracted = val.getDictionary().orElseThrow();
        assertThat(extracted.getString("inside")).hasValue("value");
    }

    @Test
    @Order(23)
    void byValueSize_pendingOnJvm_sizeofFromNative() throws Exception {
        CefWindowInfo.Mutable windowInfo;
        if (OS.isMacOS()) windowInfo = new net.kurobako.cef4j.gen.mac.CefWindowInfo.Mutable();
        else if (OS.isWindows()) windowInfo = new net.kurobako.cef4j.gen.win.CefWindowInfo.Mutable();
        else windowInfo = new net.kurobako.cef4j.gen.linux.CefWindowInfo.Mutable();
        assertPendingSizeIfTracked(windowInfo, "JVM-created CefWindowInfo.Mutable");

        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        assertPendingSizeIfTracked(browserSettings, "JVM-created CefBrowserSettings.Mutable");

        CefKeyEvent jvmKeyEvent =
                new CefKeyEvent(CefKeyEventType.of(CefKeyEventType.Kind.KEYDOWN), 0, 65, 0, 0, 'A', 'A', 0);
        assertPendingSizeIfTracked(jvmKeyEvent, "JVM-created CefKeyEvent");

        CountDownLatch createdLatch = new CountDownLatch(1);
        CountDownLatch loadLatch = new CountDownLatch(1);
        AtomicReference<CefKeyEvent> capturedEvent = new AtomicReference<>();
        CountDownLatch keyLatch = new CountDownLatch(1);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@Nullable CefBrowser browser) {
                        createdLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpstatuscode) {
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefKeyboardHandler> getKeyboardHandler() {
                return Optional.of(new CompatibleKeyboardHandler(capturedEvent, keyLatch));
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new MinimalRenderHandler(100, 100));
            }
        };

        CefBrowser browser = createWindowlessBrowser(client, "about:blank");

        assertThat(pumpUntil(createdLatch, 10_000)).as("browser created").isTrue();
        assertThat(pumpUntil(loadLatch, 10_000)).as("page loaded").isTrue();

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
        assertConcreteSizeIfTracked(
                Objects.requireNonNull(nativeEvent), "native CefKeyEvent should have actual sizeof, not pending");

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

        CefListValue args2 = msg.getArgumentList().orElseThrow();
        assertThat(args2.getSize()).isEqualTo(2);
        assertThat(args2.getValue(0).orElseThrow().getString()).hasValue("arg0");
        assertThat(args2.getValue(1).orElseThrow().getInt()).isEqualTo(99);

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
                    public void getViewRect(@Nullable CefBrowser browser, @Nonnull CefRect.Mutable rect) {
                        rect.x = 0;
                        rect.y = 0;
                        rect.width = 200;
                        rect.height = 150;
                    }

                    @Override
                    public boolean getScreenInfo(
                            @Nullable CefBrowser browser, @Nonnull CefScreenInfo.Mutable screenInfo) {
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
                            @Nullable CefBrowser browser,
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

        CefScreenInfo.Mutable info = Objects.requireNonNull(captured.get(), "captured screen info");
        assertThat(info.deviceScaleFactor).isEqualTo(2.0f);
        assertThat(info.depth).isEqualTo(32);
        CefRect rect = Objects.requireNonNull(info.rect, "screen info rect");
        assertThat(rect.width).isEqualTo(200);
        assertThat(rect.height).isEqualTo(150);

        closeBrowser(browser);
    }

    @Test
    @Order(29)
    void nativePeer_doubleCloseIsSafe() {
        CefDictionaryValue dict = CefDictionaryValue.create().orElseThrow();
        dict.setString("k", "v");
        assertThat(dict.isValid()).isTrue();

        dict.close();
        dict.close();
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
        assertThatThrownBy(() -> val1.isSame(val2)).isInstanceOf(IllegalStateException.class);
        val1.close();
    }

    @Test
    @Order(31)
    void heapByteBuffer_throwsIllegalArgument() {
        ByteBuffer heap = ByteBuffer.allocate(10);
        heap.put("test".getBytes(StandardCharsets.UTF_8));
        assertThatThrownBy(() -> CefBinaryValue.create(heap))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("direct");
    }

    @Test
    @Order(33)
    @Timeout(75)
    void onBeforePopup_firesWithoutCrash() throws Exception {
        CountDownLatch loadLatch = new CountDownLatch(1);
        CountDownLatch paintLatch = new CountDownLatch(1);
        CountDownLatch popupLatch = new CountDownLatch(1);
        AtomicBoolean popupFired = new AtomicBoolean(false);

        CefClient client = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CompatiblePopupLifeSpanHandler(popupFired, popupLatch));
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpStatusCode) {
                        loadLatch.countDown();
                    }
                });
            }

            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefRenderHandler() {
                    @Override
                    public void getViewRect(@Nullable CefBrowser browser, @Nonnull CefRect.Mutable rect) {
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

        String html = "<html><body style='margin:0'><a id='link' href='about:blank' target='_blank'"
                + " style='display:block;width:200px;height:200px;background:blue;'>open</a></body></html>";
        String dataUrl = "data:text/html;base64,"
                + java.util.Base64.getEncoder().encodeToString(html.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        CefBrowser browser = createWindowlessBrowser(client, dataUrl);
        assertThat(pumpUntil(loadLatch, 20_000)).as("page loaded").isTrue();
        assertThat(pumpUntil(paintLatch, 20_000)).as("first paint").isTrue();

        // CEF 109/116 accept window.open here, while newer Chromium builds can still require a user gesture in
        // hosted sessions despite --disable-popup-blocking. Trigger both paths: the handler cancels the popup, so
        // whichever path wins only completes the same latch and no second browser is created.
        browser.getMainFrame().orElseThrow().executeJavaScript("window.open('about:blank', '_blank')", "test.js", 1);
        CefBrowserHost host = browser.getHost().orElseThrow();
        CefMouseEvent mouseEvent = new CefMouseEvent(50, 25, 0);
        CefMouseButtonType left = CefMouseButtonType.of(CefMouseButtonType.Kind.LEFT);
        host.setFocus(true);
        host.sendMouseMoveEvent(mouseEvent, false);
        host.sendMouseClickEvent(mouseEvent, left, false, 1);
        host.sendMouseClickEvent(mouseEvent, left, true, 1);

        // macOS hosted sessions can reject synthetic mouse coordinates before an OSR view has a
        // backing screen. Focus the anchor and inject Enter as a second genuine input path.
        browser.getMainFrame().orElseThrow().executeJavaScript("document.getElementById('link').focus()", "test.js", 1);
        pumpUntil(new CountDownLatch(1), 100);
        CefKeyEventType rawDown = CefKeyEventType.of(CefKeyEventType.Kind.RAWKEYDOWN);
        CefKeyEventType keyUp = CefKeyEventType.of(CefKeyEventType.Kind.KEYUP);
        host.sendKeyEvent(new CefKeyEvent(rawDown, 0, 13, 13, 0, '\r', '\r', 0));
        host.sendKeyEvent(new CefKeyEvent(keyUp, 0, 13, 13, 0, '\r', '\r', 0));

        assertThat(pumpUntil(popupLatch, 20_000))
                .as("onBeforePopup should fire without SIGSEGV")
                .isTrue();
        assertThat(popupFired.get()).isTrue();

        closeBrowser(browser);
    }

    @Test
    @Order(34)
    void generatedSignatures_useTypedMappings() throws Exception {
        Method popupMethod = findSingleMethod(CefLifeSpanHandler.class, "onBeforePopup");
        Class<?>[] popupParams = popupMethod.getParameterTypes();
        int popupFeaturesIndex = popupParams.length == 13 ? 7 : 6;
        assertThat(popupParams.length).isIn(12, 13);
        assertThat(popupParams[popupFeaturesIndex]).isEqualTo(CefPopupFeatures.class);
        assertThat(popupParams[popupParams.length - 1]).isEqualTo(int[].class);

        Method cursorMethod = findSingleMethod(CefDisplayHandler.class, "onCursorChange");
        assertThat(cursorMethod.getParameterTypes()[3]).isEqualTo(CefCursorInfo.class);
    }

    @Test
    @Order(32)
    void closedPeerAsNestedArgument_shouldThrow() {
        CefDictionaryValue outer = CefDictionaryValue.create().orElseThrow();
        CefDictionaryValue inner = CefDictionaryValue.create().orElseThrow();
        inner.setString("k", "v");
        inner.close();
        assertThatThrownBy(() -> outer.setDictionary("nested", inner)).isInstanceOf(IllegalStateException.class);
        outer.close();
    }

    private static Optional<Method> findNamedMethod(Class<?> type, String name) {
        return Arrays.stream(type.getMethods())
                .filter(method -> method.getName().equals(name))
                .findFirst();
    }

    private static Method findSingleMethod(Class<?> type, String name) {
        return findNamedMethod(type, name)
                .orElseThrow(() -> new AssertionError("Missing method " + type.getSimpleName() + "." + name));
    }

    private static void assertPendingSizeIfTracked(Object value, String description) {
        String rendered = value.toString();
        if (rendered.contains("size=")) {
            assertThat(rendered).as(description).contains("size=pending");
        } else {
            assertThat(rendered).as(description).isNotBlank();
        }
    }

    private static void assertConcreteSizeIfTracked(Object value, String description) {
        String rendered = value.toString();
        if (rendered.contains("size=")) {
            assertThat(rendered).as(description).doesNotContain("size=pending").matches(".*size=\\d+.*");
        } else {
            assertThat(rendered).as(description).isNotBlank();
        }
    }

    static final class CompatibleKeyboardHandler implements CefKeyboardHandler {
        private final AtomicReference<CefKeyEvent> capturedEvent;
        private final CountDownLatch keyLatch;

        CompatibleKeyboardHandler(AtomicReference<CefKeyEvent> capturedEvent, CountDownLatch keyLatch) {
            this.capturedEvent = capturedEvent;
            this.keyLatch = keyLatch;
        }

        @SuppressWarnings("MissingOverride") // v117+-only overload; v109/v116 don't declare this signature
        public boolean onKeyEvent(@Nullable CefBrowser browser, @Nonnull CefKeyEvent event, long osEvent) {
            return capture(event);
        }

        @SuppressWarnings("MissingOverride") // v117+-only overload; v109/v116 don't declare this signature
        public boolean onKeyEvent(
                @Nullable CefBrowser browser, @Nonnull CefKeyEvent event, @Nullable NativePointer osEvent) {
            return capture(event);
        }

        private boolean capture(CefKeyEvent event) {
            capturedEvent.set(event);
            keyLatch.countDown();
            return false;
        }
    }

    static final class CompatiblePopupLifeSpanHandler implements CefLifeSpanHandler {
        private final AtomicBoolean popupFired;
        private final CountDownLatch popupLatch;

        CompatiblePopupLifeSpanHandler(AtomicBoolean popupFired, CountDownLatch popupLatch) {
            this.popupFired = popupFired;
            this.popupLatch = popupLatch;
        }

        @SuppressWarnings("MissingOverride") // v132+ with popupId; absent on v109/v116/v117-v131
        public boolean onBeforePopup(
                @Nullable CefBrowser browser,
                @Nullable CefFrame frame,
                int popupId,
                @Nullable String targetUrl,
                @Nullable String targetFrameName,
                @Nonnull CefWindowOpenDisposition targetDisposition,
                boolean userGesture,
                @Nullable CefPopupFeatures popupFeatures,
                @Nonnull CefWindowInfo.Mutable windowInfo,
                @Nullable AtomicReference<CefClient> client,
                @Nonnull CefBrowserSettings.Mutable settings,
                @Nullable AtomicReference<CefDictionaryValue> extraInfo,
                int[] noJavascriptAccess) {
            return cancelPopup();
        }

        @SuppressWarnings("MissingOverride") // v109/v116/v117-v131 without popupId; absent on v132+
        public boolean onBeforePopup(
                @Nullable CefBrowser browser,
                @Nullable CefFrame frame,
                @Nullable String targetUrl,
                @Nullable String targetFrameName,
                @Nonnull CefWindowOpenDisposition targetDisposition,
                boolean userGesture,
                @Nullable CefPopupFeatures popupFeatures,
                @Nonnull CefWindowInfo.Mutable windowInfo,
                @Nullable AtomicReference<CefClient> client,
                @Nonnull CefBrowserSettings.Mutable settings,
                @Nullable AtomicReference<CefDictionaryValue> extraInfo,
                int[] noJavascriptAccess) {
            return cancelPopup();
        }

        private boolean cancelPopup() {
            popupFired.set(true);
            popupLatch.countDown();
            return true;
        }
    }

    static class MinimalRenderHandler implements CefRenderHandler {
        private final int width;
        private final int height;

        MinimalRenderHandler(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void getViewRect(@Nullable CefBrowser browser, @Nonnull CefRect.Mutable rect) {
            rect.x = 0;
            rect.y = 0;
            rect.width = width;
            rect.height = height;
        }
    }
}
