package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.*;
import net.kurobako.cef4j.test.CefTestLaunch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests the daemon-thread render path: {@code externalMessagePump=0, multiThreadedMessageLoop=0}.
 *
 * <p>This is the production code path used by CefWebView and CefBrowserPanel. CEF's message loop runs on an internal
 * daemon thread managed by {@link Cef}. Browser creation is async ({@link CefBrowserHost#createBrowser}), and all
 * callbacks (onPaint, onAfterCreated, etc.) arrive on the daemon thread.
 *
 * <p>Must run in its own surefire fork because CEF is initialised with different settings than the default
 * external-message-pump tests.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Timeout(60)
class CefDaemonRenderTest {

    // CEF deliberately remains alive until this dedicated test fork exits, so its cache cannot be removed by
    // JUnit's before-exit cleanup on every platform/JDK combination.
    @TempDir(cleanup = CleanupMode.NEVER)
    @SuppressWarnings("NullAway.Init")
    static Path tempDir;

    @BeforeAll
    static void initCef() throws Exception {
        SystemBootstrap.load();
        if (Cef.INSTANCE.state() == Cef.State.INITIALISED) return;

        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));

        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 1;
        settings.externalMessagePump = 0;
        settings.multiThreadedMessageLoop = 0;

        Cef.INSTANCE.initialise(settings, CefTestLaunch.extraArgs());
    }

    @AfterAll
    static void shutdownCef() {
        // Don't call Cef.INSTANCE.terminate() here — this test runs in its own surefire fork,
        // and process exit handles cleanup. Calling terminate() can hang if browsers haven't
        // fully closed yet.
    }

    @Test
    @Order(1)
    void daemonThread_onPaintFiresWithPixelData() throws Exception {
        int viewWidth = 200;
        int viewHeight = 150;

        CompletableFuture<CefBrowser> browserReady = new CompletableFuture<>();
        CompletableFuture<byte[]> firstPaint = new CompletableFuture<>();
        AtomicBoolean viewRectCalled = new AtomicBoolean();
        AtomicInteger paintWidth = new AtomicInteger();
        AtomicInteger paintHeight = new AtomicInteger();

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
                        if (!firstPaint.isDone()) {
                            paintWidth.set(width);
                            paintHeight.set(height);
                            byte[] copy = new byte[buffer.remaining()];
                            buffer.get(copy);
                            firstPaint.complete(copy);
                        }
                    }
                });
            }

            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@Nullable CefBrowser browser) {
                        browserReady.complete(browser);
                    }
                });
            }
        };

        CefWindowInfo windowInfo = Cef.createWindowlessInfo(new CefRect(0, 0, viewWidth, viewHeight));
        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        browserSettings.windowlessFrameRate = 60;
        int ok = CefBrowserHost.createBrowser(
                windowInfo, client, "about:blank", browserSettings.toImmutable(), null, null);
        assertThat(ok).as("createBrowser should succeed").isNotEqualTo(0);

        CefBrowser browser = browserReady.get(10, TimeUnit.SECONDS);
        assertThat(browser).isNotNull();

        byte[] pixels = firstPaint.get(15, TimeUnit.SECONDS);

        assertThat(viewRectCalled.get()).as("getViewRect was called").isTrue();
        assertThat(paintWidth.get()).as("paint width").isEqualTo(viewWidth);
        assertThat(paintHeight.get()).as("paint height").isEqualTo(viewHeight);
        assertThat(pixels).as("pixel buffer").isNotNull();
        assertThat(pixels.length).as("pixel buffer size (BGRA)").isEqualTo(viewWidth * viewHeight * 4);

        browser.getHost().ifPresent(host -> host.closeBrowser(true));
    }

    @Test
    @Order(2)
    void daemonThread_coloredPageRendersCorrectPixels() throws Exception {
        int viewWidth = 100;
        int viewHeight = 100;

        CompletableFuture<CefBrowser> browserReady = new CompletableFuture<>();
        CompletableFuture<Void> pageReady = new CompletableFuture<>();
        CompletableFuture<byte[]> colorPaint = new CompletableFuture<>();

        CefClient client = makeColorClient(viewWidth, viewHeight, 2, colorPaint, browserReady, pageReady);

        CefWindowInfo windowInfo = Cef.createWindowlessInfo(new CefRect(0, 0, viewWidth, viewHeight));
        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        browserSettings.windowlessFrameRate = 60;
        CefBrowserHost.createBrowser(windowInfo, client, "about:blank", browserSettings.toImmutable(), null, null);

        CefBrowser browser = browserReady.get(10, TimeUnit.SECONDS);
        pageReady.get(10, TimeUnit.SECONDS);

        // onAfterCreated does not imply that the initial document has loaded. Wait for main-frame onLoadEnd before
        // mutating about:blank, otherwise the pending navigation can replace the document and discard this script.
        browser.getMainFrame()
                .ifPresent(frame -> frame.executeJavaScript(
                        "document.body.style.margin='0'; document.body.style.background='red';", "about:blank", 0));

        ScheduledExecutorService poller = startInvalidatePoller(browser, colorPaint);
        byte[] pixels = colorPaint.get(30, TimeUnit.SECONDS);
        poller.shutdownNow();

        assertThat(pixels.length).isEqualTo(viewWidth * viewHeight * 4);

        // Sample center pixel — BGRA format: expect red = [B=0, G=0, R=255, A=255]
        int centerOffset = ((viewHeight / 2) * viewWidth + (viewWidth / 2)) * 4;
        int b = pixels[centerOffset] & 0xFF;
        int g = pixels[centerOffset + 1] & 0xFF;
        int r = pixels[centerOffset + 2] & 0xFF;
        int a = pixels[centerOffset + 3] & 0xFF;

        assertThat(r).as("red channel at center").isEqualTo(255);
        assertThat(g).as("green channel at center").isEqualTo(0);
        assertThat(b).as("blue channel at center").isEqualTo(0);
        assertThat(a).as("alpha channel at center").isEqualTo(255);

        browser.getHost().ifPresent(host -> host.closeBrowser(true));
    }

    @Test
    @Order(3)
    void daemonThread_multiBrowserRender() throws Exception {
        int viewSize = 80;

        CompletableFuture<byte[]> redPaint = new CompletableFuture<>();
        CompletableFuture<byte[]> bluePaint = new CompletableFuture<>();
        CompletableFuture<CefBrowser> redBrowserReady = new CompletableFuture<>();
        CompletableFuture<CefBrowser> blueBrowserReady = new CompletableFuture<>();
        CompletableFuture<Void> redPageReady = new CompletableFuture<>();
        CompletableFuture<Void> bluePageReady = new CompletableFuture<>();

        // BGRA offset: red channel = +2, blue channel = +0
        CefClient redClient = makeColorClient(viewSize, viewSize, 2, redPaint, redBrowserReady, redPageReady);
        CefClient blueClient = makeColorClient(viewSize, viewSize, 0, bluePaint, blueBrowserReady, bluePageReady);

        CefBrowserSettings.Mutable bs = new CefBrowserSettings.Mutable();
        bs.windowlessFrameRate = 60;
        CefBrowserHost.createBrowser(
                Cef.createWindowlessInfo(new CefRect(0, 0, viewSize, viewSize)),
                redClient,
                "about:blank",
                bs.toImmutable(),
                null,
                null);
        CefBrowserHost.createBrowser(
                Cef.createWindowlessInfo(new CefRect(0, 0, viewSize, viewSize)),
                blueClient,
                "about:blank",
                bs.toImmutable(),
                null,
                null);

        CefBrowser redBrowser = redBrowserReady.get(10, TimeUnit.SECONDS);
        CefBrowser blueBrowser = blueBrowserReady.get(10, TimeUnit.SECONDS);
        redPageReady.get(10, TimeUnit.SECONDS);
        bluePageReady.get(10, TimeUnit.SECONDS);

        // Set colors via JS after browsers are created
        redBrowser
                .getMainFrame()
                .ifPresent(frame -> frame.executeJavaScript(
                        "document.body.style.margin='0'; document.body.style.background='red';", "about:blank", 0));
        blueBrowser
                .getMainFrame()
                .ifPresent(frame -> frame.executeJavaScript(
                        "document.body.style.margin='0'; document.body.style.background='blue';", "about:blank", 0));

        ScheduledExecutorService poller = Executors.newSingleThreadScheduledExecutor();
        java.util.concurrent.Future<?> unused = poller.scheduleAtFixedRate(
                () -> {
                    if (!redPaint.isDone()) {
                        redBrowser
                                .getHost()
                                .ifPresent(
                                        host -> host.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW)));
                    }
                    if (!bluePaint.isDone()) {
                        blueBrowser
                                .getHost()
                                .ifPresent(
                                        host -> host.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW)));
                    }
                },
                100,
                100,
                TimeUnit.MILLISECONDS);

        byte[] redPixels = redPaint.get(15, TimeUnit.SECONDS);
        byte[] bluePixels = bluePaint.get(15, TimeUnit.SECONDS);
        poller.shutdownNow();

        // Verify red browser center pixel
        int center = ((viewSize / 2) * viewSize + (viewSize / 2)) * 4;
        assertThat(redPixels[center + 2] & 0xFF).as("red browser R").isEqualTo(255);
        assertThat(redPixels[center + 1] & 0xFF).as("red browser G").isEqualTo(0);
        assertThat(redPixels[center] & 0xFF).as("red browser B").isEqualTo(0);

        // Verify blue browser center pixel
        assertThat(bluePixels[center + 2] & 0xFF).as("blue browser R").isEqualTo(0);
        assertThat(bluePixels[center + 1] & 0xFF).as("blue browser G").isEqualTo(0);
        assertThat(bluePixels[center] & 0xFF).as("blue browser B").isEqualTo(255);

        redBrowser.getHost().ifPresent(host -> host.closeBrowser(true));
        blueBrowser.getHost().ifPresent(host -> host.closeBrowser(true));
    }

    /** Starts a poller that calls {@code invalidate()} every 100ms to drive frame production. */
    private static ScheduledExecutorService startInvalidatePoller(CefBrowser browser, CompletableFuture<?> doneFuture) {
        ScheduledExecutorService poller = Executors.newSingleThreadScheduledExecutor();
        java.util.concurrent.Future<?> unused = poller.scheduleAtFixedRate(
                () -> {
                    if (!doneFuture.isDone()) {
                        browser.getHost()
                                .ifPresent(
                                        host -> host.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW)));
                    }
                },
                100,
                100,
                TimeUnit.MILLISECONDS);
        return poller;
    }

    /**
     * Creates a client that waits for a paint where the center pixel's BGRA channel at {@code expectedChannelOffset}
     * exceeds 200.
     */
    private static CefClient makeColorClient(
            int viewWidth,
            int viewHeight,
            int expectedChannelOffset,
            CompletableFuture<byte[]> paintFuture,
            CompletableFuture<CefBrowser> browserReady,
            CompletableFuture<Void> pageReady) {
        return new CefClient() {
            @Override
            public Optional<CefRenderHandler> getRenderHandler() {
                return Optional.of(new CefRenderHandler() {
                    @Override
                    public void getViewRect(@Nullable CefBrowser browser, @Nonnull CefRect.Mutable rect) {
                        rect.x = 0;
                        rect.y = 0;
                        rect.width = viewWidth;
                        rect.height = viewHeight;
                    }

                    @Override
                    public boolean getScreenInfo(
                            @Nullable CefBrowser browser, @Nonnull CefScreenInfo.Mutable screenInfo) {
                        screenInfo.deviceScaleFactor = 1.0f;
                        screenInfo.depth = 32;
                        screenInfo.depthPerComponent = 8;
                        screenInfo.rect = new CefRect(0, 0, viewWidth, viewHeight);
                        screenInfo.availableRect = new CefRect(0, 0, viewWidth, viewHeight);
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
                        if (!paintFuture.isDone() && width == viewWidth && height == viewHeight) {
                            int centerOffset = ((height / 2) * width + (width / 2)) * 4;
                            int value = buffer.get(centerOffset + expectedChannelOffset) & 0xFF;
                            if (value > 200) {
                                byte[] copy = new byte[width * height * 4];
                                buffer.position(0);
                                buffer.get(copy);
                                paintFuture.complete(copy);
                            }
                        }
                    }
                });
            }

            @Override
            public Optional<CefLoadHandler> getLoadHandler() {
                return Optional.of(new CefLoadHandler() {
                    @Override
                    public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpStatusCode) {
                        if (frame != null && frame.isMain()) pageReady.complete(null);
                    }
                });
            }

            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@Nullable CefBrowser browser) {
                        if (browser == null) return;
                        browser.getHost().ifPresent(host -> {
                            host.wasResized();
                            host.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
                        });
                        browserReady.complete(browser);
                    }
                });
            }
        };
    }
}
