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
import net.kurobako.cef4j.test.TestTempDirs;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Timeout(60)
class CefDaemonRenderTest {

    @TempDir(cleanup = CleanupMode.NEVER)
    @SuppressWarnings("NullAway.Init")
    static Path tempDir;

    @BeforeAll
    static void initCef() throws Exception {
        SystemBootstrap.load();
        if (Cef.INSTANCE.state() == Cef.State.INITIALISED) return;

        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));
        TestTempDirs.cleanupAtExit(tempDir);

        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.rootCachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 1;
        settings.externalMessagePump = 0;
        settings.multiThreadedMessageLoop = 0;

        Cef.INSTANCE.initialise(settings, CefTestLaunch.extraArgs());
    }

    @Test
    @Order(1)
    void daemonThreadOnPaintFiresWithPixelData() throws Exception {
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
        try {
            byte[] pixels = firstPaint.get(15, TimeUnit.SECONDS);

            assertThat(viewRectCalled.get()).as("getViewRect was called").isTrue();
            assertThat(paintWidth.get()).as("paint width").isEqualTo(viewWidth);
            assertThat(paintHeight.get()).as("paint height").isEqualTo(viewHeight);
            assertThat(pixels).as("pixel buffer").isNotNull();
            assertThat(pixels.length).as("pixel buffer size (BGRA)").isEqualTo(viewWidth * viewHeight * 4);
        } finally {
            closeBrowser(browser);
            browser.close();
        }
    }

    @Test
    @Order(2)
    void daemonThreadColoredPageRendersCorrectPixels() throws Exception {
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
        ScheduledExecutorService poller = null;
        try {
            pageReady.get(10, TimeUnit.SECONDS);
            try (CefFrame frame = browser.getMainFrame().orElseThrow()) {
                frame.executeJavaScript(
                        "document.body.style.margin='0'; document.body.style.background='red';", "about:blank", 0);
            }

            poller = startInvalidatePoller(browser, colorPaint);
            byte[] pixels = colorPaint.get(30, TimeUnit.SECONDS);

            assertThat(pixels.length).isEqualTo(viewWidth * viewHeight * 4);

            int centerOffset = ((viewHeight / 2) * viewWidth + (viewWidth / 2)) * 4;
            int b = pixels[centerOffset] & 0xFF;
            int g = pixels[centerOffset + 1] & 0xFF;
            int r = pixels[centerOffset + 2] & 0xFF;
            int a = pixels[centerOffset + 3] & 0xFF;

            assertThat(r).as("red channel at center").isEqualTo(255);
            assertThat(g).as("green channel at center").isEqualTo(0);
            assertThat(b).as("blue channel at center").isEqualTo(0);
            assertThat(a).as("alpha channel at center").isEqualTo(255);
        } finally {
            if (poller != null) poller.shutdownNow();
            closeBrowser(browser);
            browser.close();
        }
    }

    @Test
    @Order(3)
    void daemonThreadMultiBrowserRender() throws Exception {
        int viewSize = 80;

        CompletableFuture<byte[]> redPaint = new CompletableFuture<>();
        CompletableFuture<byte[]> bluePaint = new CompletableFuture<>();
        CompletableFuture<CefBrowser> redBrowserReady = new CompletableFuture<>();
        CompletableFuture<CefBrowser> blueBrowserReady = new CompletableFuture<>();
        CompletableFuture<Void> redPageReady = new CompletableFuture<>();
        CompletableFuture<Void> bluePageReady = new CompletableFuture<>();

        int redChannelOffset = 2;
        int blueChannelOffset = 0;
        CefClient redClient =
                makeColorClient(viewSize, viewSize, redChannelOffset, redPaint, redBrowserReady, redPageReady);
        CefClient blueClient =
                makeColorClient(viewSize, viewSize, blueChannelOffset, bluePaint, blueBrowserReady, bluePageReady);

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
        ScheduledExecutorService poller = Executors.newSingleThreadScheduledExecutor();
        try {
            redPageReady.get(10, TimeUnit.SECONDS);
            bluePageReady.get(10, TimeUnit.SECONDS);
            try (CefFrame frame = redBrowser.getMainFrame().orElseThrow()) {
                frame.executeJavaScript(
                        "document.body.style.margin='0'; document.body.style.background='red';", "about:blank", 0);
            }
            try (CefFrame frame = blueBrowser.getMainFrame().orElseThrow()) {
                frame.executeJavaScript(
                        "document.body.style.margin='0'; document.body.style.background='blue';", "about:blank", 0);
            }

            try (CefBrowserHost redHost = redBrowser.getHost().orElseThrow();
                    CefBrowserHost blueHost = blueBrowser.getHost().orElseThrow()) {
                java.util.concurrent.Future<?> unused = poller.scheduleAtFixedRate(
                        () -> {
                            if (!redPaint.isDone()) {
                                redHost.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
                            }
                            if (!bluePaint.isDone()) {
                                blueHost.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
                            }
                        },
                        100,
                        100,
                        TimeUnit.MILLISECONDS);

                byte[] redPixels = redPaint.get(15, TimeUnit.SECONDS);
                byte[] bluePixels = bluePaint.get(15, TimeUnit.SECONDS);

                int center = ((viewSize / 2) * viewSize + (viewSize / 2)) * 4;
                assertThat(redPixels[center + 2] & 0xFF).as("red browser R").isEqualTo(255);
                assertThat(redPixels[center + 1] & 0xFF).as("red browser G").isEqualTo(0);
                assertThat(redPixels[center] & 0xFF).as("red browser B").isEqualTo(0);

                assertThat(bluePixels[center + 2] & 0xFF).as("blue browser R").isEqualTo(0);
                assertThat(bluePixels[center + 1] & 0xFF).as("blue browser G").isEqualTo(0);
                assertThat(bluePixels[center] & 0xFF).as("blue browser B").isEqualTo(255);
            }
        } finally {
            poller.shutdownNow();
            closeBrowser(redBrowser);
            closeBrowser(blueBrowser);
            redBrowser.close();
            blueBrowser.close();
        }
    }

    private static ScheduledExecutorService startInvalidatePoller(CefBrowser browser, CompletableFuture<?> doneFuture) {
        ScheduledExecutorService poller = Executors.newSingleThreadScheduledExecutor();
        java.util.concurrent.Future<?> unused = poller.scheduleAtFixedRate(
                () -> {
                    if (!doneFuture.isDone()) {
                        Optional<CefBrowserHost> host = browser.getHost();
                        if (host.isEmpty()) {
                            doneFuture.completeExceptionally(new IllegalStateException("browser host unavailable"));
                        } else {
                            try (CefBrowserHost current = host.get()) {
                                current.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
                            }
                        }
                    }
                },
                100,
                100,
                TimeUnit.MILLISECONDS);
        return poller;
    }

    private static void closeBrowser(@Nullable CefBrowser browser) {
        if (browser == null) return;
        browser.getHost().ifPresent(host -> {
            try (host) {
                host.closeBrowser(true);
            }
        });
    }

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
