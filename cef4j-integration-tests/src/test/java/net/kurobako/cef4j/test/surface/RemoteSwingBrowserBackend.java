package net.kurobako.cef4j.test.surface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import net.kurobako.cef4j.remote.swing.RemoteBrowserPanel;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

/** Shared-contract adapter that drives the real RemoteBrowserPanel Swing component. */
final class RemoteSwingBrowserBackend implements BrowserBackend {
    @Override
    @Nonnull
    public String name() {
        return "remote-swing";
    }

    @Override
    public boolean isAvailable() {
        return RemoteSurfaceSupport.available();
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
            return new Session(config);
        } catch (Exception e) {
            throw new RuntimeException("failed to open RemoteBrowserPanel contract session", e);
        }
    }

    private static void onEdt(Runnable action) throws Exception {
        if (SwingUtilities.isEventDispatchThread()) {
            action.run();
        } else {
            SwingUtilities.invokeAndWait(action);
        }
    }

    private static final class Session implements BrowserSession {
        private final RemoteSurfaceSupport.RuntimeFixture runtime;
        private final RemoteSurfaceSupport.NavigationProbe navigation;
        private final RemoteSurfaceSupport.FrameProbe frames = new RemoteSurfaceSupport.FrameProbe();
        private final RemoteBrowserPanel panel;
        private final JFrame frame;
        private volatile int width;
        private volatile int height;

        Session(SessionConfig config) throws Exception {
            this.width = config.width();
            this.height = config.height();
            AtomicReference<RemoteBrowserPanel> panelRef = new AtomicReference<>();
            AtomicReference<JFrame> frameRef = new AtomicReference<>();
            onEdt(() -> {
                RemoteBrowserPanel nextPanel = new RemoteBrowserPanel(frames::bind);
                nextPanel.setPreferredSize(new Dimension(width, height));
                JFrame nextFrame = new JFrame("cef4j remote Swing contract");
                nextFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                nextFrame.setLayout(new BorderLayout());
                nextFrame.add(nextPanel, BorderLayout.CENTER);
                nextFrame.pack();
                panelRef.set(nextPanel);
                frameRef.set(nextFrame);
            });
            this.panel = Objects.requireNonNull(panelRef.get(), "remote Swing panel");
            this.frame = Objects.requireNonNull(frameRef.get(), "remote Swing frame");
            this.runtime = RemoteSurfaceSupport.open(config.startupTimeout());
            this.navigation = new RemoteSurfaceSupport.NavigationProbe(runtime.session);
            try {
                // Attach before showing; browser-created is a one-shot session event.
                panel.attach(runtime.session);
                onEdt(() -> {
                    frame.setVisible(true);
                });
                panel.awaitBrowserHandle(config.startupTimeout());
                if (!config.initialUrl().isEmpty()) {
                    loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
                }
            } catch (Exception e) {
                navigation.close();
                runtime.close();
                throw e;
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            return navigation.load(url, () -> panel.loadUrl(url));
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return panel.evaluateJavascript(script);
        }

        @Override
        @Nonnull
        public PaintInfo awaitFirstPaint(@Nonnull Duration timeout) throws InterruptedException {
            return frames.await(width, height, timeout);
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
            try {
                onEdt(() -> {
                    panel.release();
                    frame.dispose();
                });
            } catch (Exception ignored) {
                // Continue closing the server-side resources.
            }
            navigation.close();
            runtime.close();
        }
    }
}
