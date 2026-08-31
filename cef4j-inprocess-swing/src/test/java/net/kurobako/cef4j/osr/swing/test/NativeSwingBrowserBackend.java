package net.kurobako.cef4j.osr.swing.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefScriptEngine;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefErrorCode;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefProcessId;
import net.kurobako.cef4j.gen.CefProcessMessage;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefTransitionType;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import net.kurobako.cef4j.test.CefTestLaunch;
import net.kurobako.cef4j.test.TestTempDirs;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;
import net.kurobako.cef4j.test.backend.CefTestCompatibility;

final class NativeSwingBrowserBackend implements BrowserBackend {
    @Override
    @Nonnull
    public String name() {
        return "native-swing";
    }

    @Override
    public boolean isAvailable() {
        if (OS.isMacOS() && CefTestCompatibility.cefApiVersion() <= 116) return false;
        String os = System.getProperty("os.name", "").toLowerCase(java.util.Locale.ROOT);
        return !os.contains("linux") || System.getenv("DISPLAY") != null || System.getenv("WAYLAND_DISPLAY") != null;
    }

    @Override
    @Nonnull
    public Set<Capability> capabilities() {
        return Collections.singleton(Capability.VIEWPORT_RESIZE);
    }

    @Override
    @Nonnull
    public BrowserSession openSession(@Nonnull SessionConfig config) {
        try {
            Path tmp = Files.createTempDirectory("cef4j-native-swing-contract");
            TestTempDirs.cleanupAtExit(tmp);
            CefSettings.Mutable settings = new CefSettings.Mutable();
            settings.noSandbox = 1;
            settings.cachePath = tmp.toAbsolutePath().toString();
            settings.rootCachePath = tmp.toAbsolutePath().toString();
            CefBrowserPanel.initialise(settings, CefTestLaunch.extraArgs(), Optional.empty());
            return new Session(config);
        } catch (Exception e) {
            throw new RuntimeException("failed to open native Swing contract session", e);
        }
    }

    private static void onEdt(Runnable action) throws Exception {
        if (SwingUtilities.isEventDispatchThread()) action.run();
        else SwingUtilities.invokeAndWait(action);
    }

    private static final class Session implements BrowserSession {
        private final BlockingQueue<PaintInfo> paints = new ArrayBlockingQueue<>(1);
        private final AtomicReference<CefBrowser> browser = new AtomicReference<>();
        private final AtomicReference<PendingLoad> pendingLoad = new AtomicReference<>();
        private final AtomicLong paintGeneration = new AtomicLong();
        private final java.util.concurrent.atomic.AtomicBoolean releaseRequested =
                new java.util.concurrent.atomic.AtomicBoolean();
        private final CompletableFuture<Void> browserClosed = new CompletableFuture<>();
        private final CefScriptEngine scripts = new CefScriptEngine(() -> Optional.ofNullable(browser.get())
                .flatMap(CefBrowser::getMainFrame)
                .orElse(null));
        private final ObservedPanel panel;
        private final JFrame frame;
        private volatile int width;
        private volatile int height;

        Session(SessionConfig config) throws Exception {
            width = config.width();
            height = config.height();
            CountDownLatch ready = new CountDownLatch(1);
            AtomicReference<ObservedPanel> panelRef = new AtomicReference<>();
            AtomicReference<JFrame> frameRef = new AtomicReference<>();
            onEdt(() -> {
                ObservedPanel nextPanel = new ObservedPanel(paints, paintGeneration, this::completePendingLoad);
                JFrame nextFrame = null;
                try {
                    nextPanel.setPreferredSize(new Dimension(width, height));
                    nextFrame = new JFrame("cef4j native Swing contract");
                    nextFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    nextFrame.setLayout(new BorderLayout());
                    nextFrame.add(nextPanel, BorderLayout.CENTER);
                    nextFrame.pack();
                    nextFrame.setVisible(true);

                    CefClient client = new CefClient() {
                        @Override
                        public Optional<CefRenderHandler> getRenderHandler() {
                            return Optional.of(nextPanel.createRenderHandler());
                        }

                        @Override
                        public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                            return Optional.of(new CefLifeSpanHandler() {
                                @Override
                                public void onAfterCreated(@Nullable CefBrowser created) {
                                    if (created == null) return;
                                    SwingUtilities.invokeLater(() -> {
                                        if (releaseRequested.get()) {
                                            created.getHost().ifPresent(host -> host.closeBrowser(true));
                                            return;
                                        }
                                        browser.set(created);
                                        nextPanel.browser(created);
                                        ready.countDown();
                                    });
                                }

                                @Override
                                public void onBeforeClose(@Nullable CefBrowser closing) {
                                    browser.set(null);
                                    nextPanel.browser(null);
                                    browserClosed.complete(null);
                                }
                            });
                        }

                        @Override
                        public Optional<CefLoadHandler> getLoadHandler() {
                            return Optional.of(new CefLoadHandler() {
                                @Override
                                public void onLoadStart(
                                        @Nullable CefBrowser ignored,
                                        @Nullable CefFrame loadedFrame,
                                        @Nonnull CefTransitionType transitionType) {
                                    if (loadedFrame != null && loadedFrame.isMain()) {
                                        scripts.cancelPending("page navigation replaced the renderer context");
                                    }
                                    PendingLoad current = pendingLoad.get();
                                    if (current != null && loadedFrame != null && loadedFrame.isMain()) {
                                        String startedUrl = loadedFrame.getUrl().orElse("");
                                        if (!current.started && current.url.equals(startedUrl)) current.started = true;
                                        if (current.started) current.activeUrl = startedUrl;
                                    }
                                }

                                @Override
                                public void onLoadEnd(
                                        @Nullable CefBrowser ignored,
                                        @Nullable CefFrame loadedFrame,
                                        int httpStatusCode) {
                                    PendingLoad current = pendingLoad.get();
                                    if (current != null
                                            && current.started
                                            && loadedFrame != null
                                            && loadedFrame.isMain()
                                            && loadedFrame
                                                    .getUrl()
                                                    .filter(current.activeUrl::equals)
                                                    .isPresent()) {
                                        current.terminal = true;
                                        completePendingLoad();
                                    }
                                }

                                @Override
                                public void onLoadError(
                                        @Nullable CefBrowser ignored,
                                        @Nullable CefFrame loadedFrame,
                                        @Nonnull CefErrorCode errorCode,
                                        @Nullable String errorText,
                                        @Nullable String failedUrl) {
                                    PendingLoad current = pendingLoad.get();
                                    if (current != null
                                            && loadedFrame != null
                                            && loadedFrame.isMain()
                                            && (current.started || current.url.equals(failedUrl))
                                            && pendingLoad.compareAndSet(current, null)) {
                                        current.result.completeExceptionally(new IllegalStateException(
                                                Objects.requireNonNullElse(errorText, "load failed")));
                                    }
                                }
                            });
                        }

                        @Override
                        public boolean onProcessMessageReceived(
                                @Nullable CefBrowser sourceBrowser,
                                @Nullable CefFrame sourceFrame,
                                @Nonnull CefProcessId sourceProcess,
                                @Nullable CefProcessMessage message) {
                            return scripts.handleMessage(sourceBrowser, sourceFrame, sourceProcess, message);
                        }
                    };

                    CefWindowInfo windowInfo =
                            Cef.createWindowlessInfo(new CefRect(0, 0, Math.max(1, width), Math.max(1, height)));
                    CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
                    browserSettings.windowlessFrameRate = 60;
                    if (CefBrowserHost.createBrowser(windowInfo, client, "", browserSettings.toImmutable(), null, null)
                            == 0) {
                        throw new IllegalStateException("CEF rejected native Swing browser creation");
                    }
                    panelRef.set(nextPanel);
                    frameRef.set(nextFrame);
                } catch (RuntimeException | Error failure) {
                    releaseRequested.set(true);
                    nextPanel.release();
                    if (nextFrame != null) nextFrame.dispose();
                    throw failure;
                }
            });
            panel = Objects.requireNonNull(panelRef.get(), "native Swing panel");
            frame = Objects.requireNonNull(frameRef.get(), "native Swing frame");
            try {
                if (!ready.await(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS)) {
                    throw new IllegalStateException("native Swing browser startup timed out");
                }
                if (!config.initialUrl().isEmpty()) {
                    loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
                }
            } catch (Exception failure) {
                releaseRequested.set(true);
                scripts.dispose();
                onEdt(() -> {
                    panel.release();
                    frame.dispose();
                });
                throw failure;
            }
        }

        @Override
        @Nonnull
        @SuppressWarnings("FutureReturnValueIgnored")
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            CefFrame mainFrame = Optional.ofNullable(browser.get())
                    .flatMap(CefBrowser::getMainFrame)
                    .orElse(null);
            if (mainFrame == null) {
                return CompletableFuture.failedFuture(new IllegalStateException("native Swing main frame unavailable"));
            }
            PendingLoad next = new PendingLoad(url, paintGeneration.get());
            if (!pendingLoad.compareAndSet(null, next)) {
                return CompletableFuture.failedFuture(new IllegalStateException("a navigation is already pending"));
            }
            scripts.cancelPending("page navigation replaced the renderer context");
            try {
                mainFrame.loadUrl(url);
            } catch (RuntimeException failure) {
                pendingLoad.compareAndSet(next, null);
                next.result.completeExceptionally(failure);
            }
            next.result.whenComplete((ignored, failure) -> pendingLoad.compareAndSet(next, null));
            return next.result;
        }

        private void completePendingLoad() {
            PendingLoad current = pendingLoad.get();
            if (current != null
                    && current.terminal
                    && paintGeneration.get() > current.paintBaseline
                    && pendingLoad.compareAndSet(current, null)) {
                current.result.complete(null);
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return scripts.evaluate(script);
        }

        @Override
        @Nonnull
        public PaintInfo awaitNextPaint(@Nonnull Duration timeout) throws InterruptedException, TimeoutException {
            long deadline = System.nanoTime() + timeout.toNanos();
            PaintInfo last = null;
            while (System.nanoTime() < deadline) {
                CefBrowser current = browser.get();
                if (current != null) {
                    SwingUtilities.invokeLater(() -> {
                        if (browser.get() == current) panel.browser(current);
                    });
                }
                long remaining = Math.max(1L, deadline - System.nanoTime());
                PaintInfo observed =
                        paints.poll(Math.min(remaining, TimeUnit.SECONDS.toNanos(1)), TimeUnit.NANOSECONDS);
                if (observed != null) {
                    last = observed;
                    if (observed.width == width && observed.height == height) return observed;
                }
            }
            String observed = last == null ? "none" : last.width + "x" + last.height;
            throw new TimeoutException("no " + width + "x" + height + " native Swing paint within " + timeout
                    + "; last observed paint=" + observed);
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> resizeViewport(int width, int height) {
            CompletableFuture<Void> result = new CompletableFuture<>();
            try {
                onEdt(() -> {
                    panel.setPreferredSize(new Dimension(width, height));
                    Insets insets = frame.getInsets();
                    frame.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
                    frame.validate();
                    this.width = width;
                    this.height = height;
                    result.complete(null);
                });
            } catch (Exception e) {
                result.completeExceptionally(e);
            }
            return result;
        }

        @Override
        public void close() {
            if (!releaseRequested.compareAndSet(false, true)) return;
            PendingLoad load = pendingLoad.getAndSet(null);
            if (load != null) load.result.cancel(true);
            scripts.dispose();
            try {
                onEdt(() -> {
                    panel.release();
                    frame.dispose();
                });
                browserClosed.get(10, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new IllegalStateException("native Swing browser did not close cleanly", e);
            }
        }
    }

    private static final class ObservedPanel extends CefBrowserPanel {
        private static final long serialVersionUID = 1L;
        private final transient BlockingQueue<BrowserSession.PaintInfo> paints;
        private final transient AtomicLong paintGeneration;
        private final transient Runnable paintListener;

        ObservedPanel(
                BlockingQueue<BrowserSession.PaintInfo> paints, AtomicLong paintGeneration, Runnable paintListener) {
            this.paints = paints;
            this.paintGeneration = paintGeneration;
            this.paintListener = paintListener;
        }

        @Override
        protected float getEffectiveScaleFactor() {
            return 1f;
        }

        @Override
        protected void onViewPainted(int width, int height) {
            paintGeneration.incrementAndGet();
            BrowserSession.PaintInfo latest = new BrowserSession.PaintInfo(width, height, (long) width * height * 4L);
            while (!paints.offer(latest)) paints.poll();
            paintListener.run();
        }
    }

    private static final class PendingLoad {
        private final String url;
        private final long paintBaseline;
        private final CompletableFuture<Void> result = new CompletableFuture<>();
        private volatile boolean started;
        private volatile boolean terminal;
        private volatile String activeUrl = "";

        private PendingLoad(String url, long paintBaseline) {
            this.url = url;
            this.paintBaseline = paintBaseline;
        }
    }
}
