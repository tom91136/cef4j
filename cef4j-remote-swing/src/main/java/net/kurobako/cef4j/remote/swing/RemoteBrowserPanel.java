package net.kurobako.cef4j.remote.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.kurobako.cef4j.ipc.frame.FrameTransport;
import net.kurobako.cef4j.ipc.frame.LatestOnlyDispatcher;
import net.kurobako.cef4j.ipc.frame.SharedFileFrameTransport;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeRequest;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSession.HandlerRegistration;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.remote.RemoteViewportConstraints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Swing component that displays and controls a browser owned by a Remote CEF runtime server. */
@SuppressWarnings("serial")
public final class RemoteBrowserPanel extends JPanel {
    private static final Logger LOG = LoggerFactory.getLogger(RemoteBrowserPanel.class);
    private static final int KEYEVENT_RAWKEYDOWN = 0;
    private static final int KEYEVENT_KEYUP = 2;
    private static final int KEYEVENT_CHAR = 3;

    private volatile CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();
    private final AtomicReference<BrowserHost> hostRef = new AtomicReference<>();
    private final AtomicLong desiredSize = new AtomicLong(packSize(1, 1));
    private final AtomicLong reportedSize = new AtomicLong(-1);
    private final FrameTransportFactory frameTransportFactory;
    private final LatestOnlyDispatcher<FrameSnapshot> frameDispatcher =
            new LatestOnlyDispatcher<>(SwingUtilities::invokeLater, this::presentFrame);

    @Nullable
    private volatile CefSession session;

    @Nullable
    private volatile RemoteHandle readyBrowser;

    @Nullable
    private FrameTransport frameTransport;

    @Nullable
    private HandlerRegistration lifecycleRegistration;

    @Nullable
    private RuntimeException setupFailure;

    @Nullable
    private BufferedImage image;

    private boolean attachedOnce;

    public RemoteBrowserPanel() {
        this(SharedFileFrameTransport::bind);
    }

    public RemoteBrowserPanel(@Nonnull FrameTransportFactory frameTransportFactory) {
        this.frameTransportFactory = frameTransportFactory;
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        wireInputForwarding();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                reportViewportSize(Math.max(1, getWidth()), Math.max(1, getHeight()));
            }
        });
    }

    public synchronized void attach(@Nonnull CefSession session) {
        if (attachedOnce) {
            if (this.session == session) return;
            throw new IllegalStateException("RemoteBrowserPanel instances cannot be attached to more than one session");
        }
        Objects.requireNonNull(session, "session");
        if (browserHandle.isCompletedExceptionally()) browserHandle = new CompletableFuture<>();
        this.session = session;
        setupFailure = null;
        desiredSize.set(packSize(Math.max(1, getWidth()), Math.max(1, getHeight())));
        HandlerRegistration registration;
        try {
            registration = session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    event -> installBrowser(session, event.browser()));
        } catch (RuntimeException failure) {
            this.session = null;
            throw failure;
        }
        if (setupFailure != null || this.session != session) {
            registration.unregister();
            RuntimeException failure = setupFailure;
            setupFailure = null;
            this.session = null;
            throw Objects.requireNonNull(failure, "frame transport setup failure");
        }
        lifecycleRegistration = registration;
        attachedOnce = true;
        observe(
                browserHandle
                        .thenCompose(handle -> new Browser(session, handle).getHost())
                        .thenAccept(host -> {
                            if (this.session == session) hostRef.set(host);
                        }),
                "resolve BrowserHost for input forwarding");
        observe(browserHandle.thenCompose(handle -> flushViewportSize(session, handle)), "flush initial viewport size");
    }

    private synchronized void installBrowser(CefSession expectedSession, RemoteHandle browser) {
        CompletableFuture<RemoteHandle> pendingBrowser = browserHandle;
        if (session != expectedSession || pendingBrowser.isDone()) return;
        FrameTransport created = null;
        try {
            created = frameTransportFactory.bind(expectedSession, browser);
            created.onFrame(this::onFrame);
            frameTransport = created;
            readyBrowser = browser;
            pendingBrowser.complete(browser);
        } catch (RuntimeException failure) {
            if (created != null) {
                try {
                    created.close();
                } catch (RuntimeException closeFailure) {
                    failure.addSuppressed(closeFailure);
                }
            }
            frameTransport = null;
            readyBrowser = null;
            setupFailure = failure;
            HandlerRegistration registration = lifecycleRegistration;
            lifecycleRegistration = null;
            session = null;
            attachedOnce = false;
            if (registration != null) registration.unregister();
            pendingBrowser.completeExceptionally(failure);
        }
    }

    @Nonnull
    public CompletableFuture<RemoteHandle> browserReady() {
        return browserHandle.copy();
    }

    @Nonnull
    public RemoteHandle awaitBrowserHandle(@Nonnull Duration timeout) throws Exception {
        return browserHandle.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Nonnull
    public CompletableFuture<Void> loadUrl(@Nonnull String url) {
        return browserHandle.thenCompose(handle ->
                new Browser(requireSession(), handle).getMainFrame().thenCompose(frame -> frame.loadUrl(url)));
    }

    @Nonnull
    public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
        return browserHandle.thenCompose(handle -> {
            CefSession current = requireSession();
            return new Browser(current, handle)
                    .getMainFrame()
                    .thenCompose(frame -> current.request(
                            new EvaluateJavascriptRequest(frame.handle(), script, false),
                            EvaluateJavascriptResponse.DECODER))
                    .thenApply(RemoteBrowserPanel::stringify);
        });
    }

    /** Requests a browser viewport resize and completes only after the remote runtime acknowledges it. */
    @Nonnull
    public CompletableFuture<Void> resizeViewport(int width, int height) {
        RemoteViewportConstraints.validate(width, height);
        long desired = packSize(width, height);
        desiredSize.set(desired);
        CompletableFuture<Void> pendingComponentEvents = new CompletableFuture<>();
        SwingUtilities.invokeLater(() -> pendingComponentEvents.complete(null));
        return pendingComponentEvents.thenCompose(
                ignored -> browserHandle.thenCompose(handle -> requestViewportSize(requireSession(), handle, desired)));
    }

    private void onFrame(int width, int height, ByteBuffer pixels, net.kurobako.cef4j.ipc.frame.FrameMetadata meta) {
        long expected = (long) width * height * 4L;
        if (width <= 0 || height <= 0 || expected > Integer.MAX_VALUE || pixels.remaining() != expected) {
            LOG.warn("dropping malformed frame {}x{} with {} bytes", width, height, pixels.remaining());
            return;
        }
        int[] snapshot = new int[width * height];
        pixels.duplicate().order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().get(snapshot);
        frameDispatcher.submit(new FrameSnapshot(width, height, snapshot));
    }

    private void presentFrame(FrameSnapshot frame) {
        if (session == null) return;
        BufferedImage next = new BufferedImage(frame.width, frame.height, BufferedImage.TYPE_INT_ARGB_PRE);
        next.setRGB(0, 0, frame.width, frame.height, frame.pixels, 0, frame.width);
        image = next;
        repaint();
    }

    private static final class FrameSnapshot {
        private final int width;
        private final int height;
        private final int[] pixels;

        private FrameSnapshot(int width, int height, int[] pixels) {
            this.width = width;
            this.height = height;
            this.pixels = pixels;
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        BufferedImage current = image;
        if (current != null) graphics.drawImage(current, 0, 0, getWidth(), getHeight(), null);
    }

    private void wireInputForwarding() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                requestFocusInWindow();
                forwardMouseClick(event, false);
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                forwardMouseClick(event, true);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                forwardMouseMove(event, true);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent event) {
                forwardMouseMove(event, false);
            }

            @Override
            public void mouseDragged(MouseEvent event) {
                forwardMouseMove(event, false);
            }
        });
        addMouseWheelListener(this::forwardMouseWheel);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                sendKey(KEYEVENT_RAWKEYDOWN, event, event.getKeyCode(), 0);
            }

            @Override
            public void keyReleased(KeyEvent event) {
                sendKey(KEYEVENT_KEYUP, event, event.getKeyCode(), 0);
            }

            @Override
            public void keyTyped(KeyEvent event) {
                char character = event.getKeyChar();
                if (character != KeyEvent.CHAR_UNDEFINED) sendKey(KEYEVENT_CHAR, event, character, character);
            }
        });
    }

    private void forwardMouseClick(MouseEvent event, boolean mouseUp) {
        BrowserHost host = hostRef.get();
        int button = swingButtonToCef(event.getButton());
        if (host == null || button < 0) return;
        observe(
                host.sendMouseClickEvent(mouseEvent(event), button, mouseUp ? 1 : 0, event.getClickCount()),
                "forward mouse click");
    }

    private void forwardMouseMove(MouseEvent event, boolean mouseLeave) {
        BrowserHost host = hostRef.get();
        if (host != null) observe(host.sendMouseMoveEvent(mouseEvent(event), mouseLeave ? 1 : 0), "forward mouse move");
    }

    private void forwardMouseWheel(MouseWheelEvent event) {
        BrowserHost host = hostRef.get();
        if (host != null) {
            int deltaY = (int) Math.round(-event.getPreciseWheelRotation() * 40.0);
            observe(host.sendMouseWheelEvent(mouseEvent(event), 0, deltaY), "forward mouse wheel");
        }
    }

    private void sendKey(int type, KeyEvent event, int keyCode, int character) {
        BrowserHost host = hostRef.get();
        if (host == null) return;
        observe(
                host.sendKeyEvent(net.kurobako.cef4j.ipc.protocol.gen.KeyEvent.builder()
                        .type(type)
                        .modifiers(modifiers(event))
                        .windowsKeyCode(keyCode)
                        .nativeKeyCode(event.getExtendedKeyCode())
                        .isSystemKey(event.isAltDown() ? 1 : 0)
                        .character(character)
                        .unmodifiedCharacter(character)
                        .focusOnEditableField(0)
                        .build()),
                "forward key event");
    }

    private static net.kurobako.cef4j.ipc.protocol.gen.MouseEvent mouseEvent(MouseEvent event) {
        return new net.kurobako.cef4j.ipc.protocol.gen.MouseEvent(event.getX(), event.getY(), modifiers(event));
    }

    private static int modifiers(InputEvent event) {
        int value = 0;
        if (event.isShiftDown()) value |= 1 << 1;
        if (event.isControlDown()) value |= 1 << 2;
        if (event.isAltDown()) value |= 1 << 3;
        if (event.isMetaDown()) value |= 1 << 7;
        int extended = event.getModifiersEx();
        if ((extended & InputEvent.BUTTON1_DOWN_MASK) != 0) value |= 1 << 4;
        if ((extended & InputEvent.BUTTON2_DOWN_MASK) != 0) value |= 1 << 5;
        if ((extended & InputEvent.BUTTON3_DOWN_MASK) != 0) value |= 1 << 6;
        return value;
    }

    private static int swingButtonToCef(int button) {
        if (button == MouseEvent.BUTTON1) return 0;
        if (button == MouseEvent.BUTTON2) return 1;
        if (button == MouseEvent.BUTTON3) return 2;
        return -1;
    }

    private void reportViewportSize(int width, int height) {
        desiredSize.set(packSize(width, height));
        CefSession current = session;
        RemoteHandle handle = readyBrowser;
        if (current != null && handle != null) {
            observe(flushViewportSize(current, handle), "resize viewport");
        }
    }

    private CompletableFuture<Void> flushViewportSize(CefSession expectedSession, RemoteHandle handle) {
        if (session != expectedSession) return CompletableFuture.completedFuture(null);
        long desired = desiredSize.get();
        long previous = reportedSize.getAndSet(desired);
        if (previous == desired) return CompletableFuture.completedFuture(null);
        return requestViewportSize(expectedSession, handle, desired);
    }

    private CompletableFuture<Void> requestViewportSize(CefSession expectedSession, RemoteHandle handle, long desired) {
        if (session != expectedSession) return CompletableFuture.completedFuture(null);
        int width = (int) (desired >>> 32);
        int height = (int) desired;
        try {
            RemoteViewportConstraints.validate(width, height);
        } catch (IllegalArgumentException invalidSize) {
            CompletableFuture<Void> failure = new CompletableFuture<>();
            failure.completeExceptionally(invalidSize);
            return failure;
        }
        reportedSize.set(desired);
        return expectedSession
                .request(new SetViewportSizeRequest(handle, width, height), SetViewportSizeResponse.DECODER)
                .thenApply(ignored -> (Void) null)
                .whenComplete((ignored, failure) -> {
                    if (failure != null) {
                        reportedSize.compareAndSet(desired, -1);
                        LOG.debug("viewport resize to {}x{} failed: {}", width, height, failure.toString());
                    }
                });
    }

    public synchronized void release() {
        if (!attachedOnce) return;
        if (frameTransport != null) {
            frameTransport.close();
            frameTransport = null;
        }
        if (lifecycleRegistration != null) {
            lifecycleRegistration.unregister();
            lifecycleRegistration = null;
        }
        hostRef.set(null);
        session = null;
        image = null;
        if (!browserHandle.isDone()) {
            browserHandle.completeExceptionally(
                    new IllegalStateException("RemoteBrowserPanel released before browser ready"));
        }
        repaint();
    }

    private CefSession requireSession() {
        CefSession current = session;
        if (current == null) throw new IllegalStateException("RemoteBrowserPanel has not been attach()ed to a session");
        return current;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private void observe(CompletableFuture<?> future, String action) {
        future.whenComplete((ignored, failure) -> {
            if (failure != null && session != null) LOG.warn("failed to {}: {}", action, failure.toString());
        });
    }

    private static long packSize(int width, int height) {
        return ((long) width << 32) | (height & 0xFFFFFFFFL);
    }

    private static String stringify(EvaluateJavascriptResponse response) {
        return net.kurobako.cef4j.ipc.session.JsResult.fromWire(
                        response.valueKind(),
                        response.boolValue(),
                        response.intValue(),
                        response.doubleValue(),
                        response.stringValue(),
                        response.errorMessage())
                .coerceToString();
    }

    @FunctionalInterface
    public interface FrameTransportFactory {
        @Nonnull
        FrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser);
    }
}
