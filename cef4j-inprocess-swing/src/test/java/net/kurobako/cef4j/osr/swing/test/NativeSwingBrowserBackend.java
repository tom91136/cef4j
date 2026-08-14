package net.kurobako.cef4j.osr.swing.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefScriptEngine;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefProcessId;
import net.kurobako.cef4j.gen.CefProcessMessage;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import net.kurobako.cef4j.test.CefTestLaunch;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

/** Shared-contract adapter for the real in-process Swing browser component. */
final class NativeSwingBrowserBackend implements BrowserBackend {
    @Override
    @Nonnull
    public String name() {
        return "native-swing";
    }

    @Override
    public boolean isAvailable() {
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
            CefSettings.Mutable settings = new CefSettings.Mutable();
            settings.cachePath =
                    Files.createTempDirectory("cef4j-native-swing-contract").toString();
            CefBrowserPanel.initialise(settings, CefTestLaunch.extraArgs(), null);
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
        private final LinkedBlockingQueue<PaintInfo> paints = new LinkedBlockingQueue<>();
        private final AtomicReference<CefBrowser> browser = new AtomicReference<>();
        private final AtomicReference<CompletableFuture<Void>> pendingLoad = new AtomicReference<>();
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
                ObservedPanel nextPanel = new ObservedPanel(paints);
                nextPanel.setPreferredSize(new Dimension(width, height));
                JFrame nextFrame = new JFrame("cef4j native Swing contract");
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
                                browser.set(created);
                                nextPanel.browser(created);
                                ready.countDown();
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
                            public void onLoadingStateChange(
                                    @Nullable CefBrowser ignored,
                                    boolean loading,
                                    boolean canGoBack,
                                    boolean canGoForward) {
                                if (!loading) {
                                    CompletableFuture<Void> completion = pendingLoad.getAndSet(null);
                                    if (completion != null) completion.complete(null);
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
                CefBrowserHost.createBrowser(windowInfo, client, "", browserSettings.toImmutable(), null, null);
                panelRef.set(nextPanel);
                frameRef.set(nextFrame);
            });
            panel = Objects.requireNonNull(panelRef.get(), "native Swing panel");
            frame = Objects.requireNonNull(frameRef.get(), "native Swing frame");
            if (!ready.await(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS)) {
                throw new IllegalStateException("native Swing browser startup timed out");
            }
            if (!config.initialUrl().isEmpty()) {
                loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            CompletableFuture<Void> completion = new CompletableFuture<>();
            pendingLoad.set(completion);
            Optional.ofNullable(browser.get()).flatMap(CefBrowser::getMainFrame).ifPresent(f -> f.loadUrl(url));
            return completion;
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return scripts.evaluate(script);
        }

        @Override
        @Nonnull
        public PaintInfo awaitFirstPaint(@Nonnull Duration timeout) throws InterruptedException {
            long deadline = System.nanoTime() + timeout.toNanos();
            PaintInfo last = null;
            while (System.nanoTime() < deadline) {
                last = paints.poll(Math.max(1L, deadline - System.nanoTime()), TimeUnit.NANOSECONDS);
                if (last != null && last.width == width && last.height == height) return last;
            }
            throw new InterruptedException("no native Swing paint within " + timeout);
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> resizeViewport(int width, int height) {
            CompletableFuture<Void> result = new CompletableFuture<>();
            try {
                onEdt(() -> {
                    panel.setPreferredSize(new Dimension(width, height));
                    frame.pack();
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
        private final LinkedBlockingQueue<BrowserSession.PaintInfo> paints;

        ObservedPanel(LinkedBlockingQueue<BrowserSession.PaintInfo> paints) {
            this.paints = paints;
        }

        @Override
        protected float getEffectiveScaleFactor() {
            // The cross-backend contract compares logical viewport and buffer dimensions. Production panels retain
            // the real display scale (and therefore HiDPI physical buffers); normalize only this contract probe.
            return 1f;
        }

        @Override
        protected void onViewPainted(int width, int height) {
            paints.offer(new BrowserSession.PaintInfo(width, height, (long) width * height * 4L));
        }
    }
}
