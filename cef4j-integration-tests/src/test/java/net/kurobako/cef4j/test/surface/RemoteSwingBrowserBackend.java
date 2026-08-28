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
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.remote.swing.RemoteBrowserPanel;
import net.kurobako.cef4j.test.RemoteNavigationProbe;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;

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
        private final RemoteNavigationProbe navigation;
        private final RemoteSurfaceSupport.FrameProbe frames = new RemoteSurfaceSupport.FrameProbe();
        private final RemoteBrowserPanel panel;
        private final JFrame frame;
        private volatile int width;
        private volatile int height;
        private final Duration navigationTimeout;

        Session(SessionConfig config) throws Exception {
            this.width = config.width();
            this.height = config.height();
            this.navigationTimeout = config.startupTimeout();
            AtomicReference<RemoteBrowserPanel> panelRef = new AtomicReference<>();
            AtomicReference<JFrame> frameRef = new AtomicReference<>();
            onEdt(() -> {
                RemoteBrowserPanel nextPanel = new RemoteBrowserPanel((session, browser) -> frames.bind(session));
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
            try {
                this.runtime = RemoteSurfaceSupport.open(config.startupTimeout());
                frames.runtimeDiagnosticSource(runtime.server::diagnosticSummary);
            } catch (Exception failure) {
                closeSurface(failure);
                throw failure;
            }
            AtomicReference<RemoteHandle> handleRef = new AtomicReference<>();
            this.navigation = new RemoteNavigationProbe(runtime.session, handleRef::get);
            try {
                panel.attach(runtime.session);
                onEdt(() -> {
                    frame.setVisible(true);
                });
                handleRef.set(panel.awaitBrowserHandle(config.startupTimeout()));
                Objects.requireNonNull(handleRef.get(), "remote Swing browser");
                if (!config.initialUrl().isEmpty()) {
                    loadUrl(config.initialUrl()).get(config.startupTimeout().toMillis(), TimeUnit.MILLISECONDS);
                }
            } catch (Exception e) {
                closeSurface(e);
                closeAfterFailure(navigation, e);
                closeAfterFailure(runtime, e);
                throw e;
            }
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> loadUrl(@Nonnull String url) {
            return navigation.load(url, navigationTimeout, () -> panel.loadUrl(url));
        }

        @Override
        @Nonnull
        public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
            return panel.evaluateJavascript(script);
        }

        @Override
        @Nonnull
        public PaintInfo awaitNextPaint(@Nonnull Duration timeout)
                throws InterruptedException, java.util.concurrent.TimeoutException {
            return frames.await(width, height, timeout);
        }

        @Override
        @Nonnull
        public CompletableFuture<Void> resizeViewport(int width, int height) {
            AtomicReference<CompletableFuture<Void>> result = new AtomicReference<>();
            try {
                onEdt(() -> {
                    panel.setPreferredSize(new Dimension(width, height));
                    frame.pack();
                    this.width = width;
                    this.height = height;
                    result.set(panel.resizeViewport(width, height));
                });
                return Objects.requireNonNull(result.get(), "remote Swing viewport resize");
            } catch (Exception e) {
                return CompletableFuture.failedFuture(e);
            }
        }

        @Override
        public void close() {
            RuntimeException failure = null;
            try {
                closeSurface(null);
            } catch (RuntimeException cleanupFailure) {
                failure = cleanupFailure;
            }
            try {
                navigation.close();
            } catch (RuntimeException cleanupFailure) {
                failure = merge(failure, cleanupFailure);
            }
            try {
                runtime.close();
            } catch (RuntimeException cleanupFailure) {
                failure = merge(failure, cleanupFailure);
            }
            if (failure != null) throw failure;
        }

        private void closeSurface(@javax.annotation.Nullable Exception original) {
            try {
                onEdt(() -> {
                    try {
                        panel.release();
                    } finally {
                        frame.dispose();
                    }
                });
            } catch (Exception cleanupFailure) {
                if (original == null)
                    throw new IllegalStateException("failed to close remote Swing surface", cleanupFailure);
                original.addSuppressed(cleanupFailure);
            }
        }
    }

    private static void closeAfterFailure(AutoCloseable resource, Exception original) {
        try {
            resource.close();
        } catch (Exception cleanupFailure) {
            original.addSuppressed(cleanupFailure);
        }
    }

    private static RuntimeException merge(@javax.annotation.Nullable RuntimeException failure, RuntimeException next) {
        if (failure == null) return next;
        failure.addSuppressed(next);
        return failure;
    }
}
