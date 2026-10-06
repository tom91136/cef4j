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
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.kurobako.cef4j.ipc.frame.FrameMetadata;
import net.kurobako.cef4j.ipc.frame.LatestOnlyDispatcher;
import net.kurobako.cef4j.ipc.frame.RemoteBrowserBinding;
import net.kurobako.cef4j.ipc.frame.SharedFileFrameTransport;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Swing component that displays and controls a browser owned by a Remote CEF runtime server. */
@SuppressWarnings("serial")
public final class RemoteBrowserPanel extends JPanel implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(RemoteBrowserPanel.class);
    private static final int KEYEVENT_RAWKEYDOWN = 0;
    private static final int KEYEVENT_KEYUP = 2;
    private static final int KEYEVENT_CHAR = 3;

    private final RemoteBrowserBinding binding;
    private final LatestOnlyDispatcher<FrameSnapshot> frameDispatcher =
            new LatestOnlyDispatcher<>(SwingUtilities::invokeLater, this::presentFrame);

    @Nullable
    private BufferedImage image;

    public RemoteBrowserPanel() {
        this(SharedFileFrameTransport::bind);
    }

    public RemoteBrowserPanel(@Nonnull RemoteBrowserBinding.FrameTransportFactory frameTransportFactory) {
        binding = new RemoteBrowserBinding(
                "RemoteBrowserPanel", SwingUtilities::invokeLater, frameTransportFactory, this::onFrame);
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        wireInputForwarding();
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent event) {
                binding.reportViewportSize(Math.max(1, getWidth()), Math.max(1, getHeight()));
            }
        });
    }

    public void attach(@Nonnull CefSession session) {
        binding.reportViewportSize(Math.max(1, getWidth()), Math.max(1, getHeight()));
        binding.attach(session);
    }

    @Nonnull
    public CompletableFuture<RemoteHandle> browserReady() {
        return binding.browserReady();
    }

    @Nonnull
    public RemoteHandle awaitBrowserHandle(@Nonnull Duration timeout) throws Exception {
        return binding.awaitBrowserHandle(timeout);
    }

    @Nonnull
    public CompletableFuture<Void> loadUrl(@Nonnull String url) {
        return binding.loadUrl(url);
    }

    @Nonnull
    public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
        return binding.evaluateJavascript(script);
    }

    /** Requests a browser viewport resize and completes only after the remote runtime acknowledges it. */
    @Nonnull
    public CompletableFuture<Void> resizeViewport(int width, int height) {
        return binding.resizeViewport(width, height);
    }

    private void onFrame(int width, int height, ByteBuffer pixels, FrameMetadata meta) {
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
        if (!binding.isAttached()) return;
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
        BrowserHost host = binding.host().orElse(null);
        int button = swingButtonToCef(event.getButton());
        if (host == null || button < 0) return;
        binding.observe(
                host.sendMouseClickEvent(mouseEvent(event), button, mouseUp ? 1 : 0, event.getClickCount()),
                "forward mouse click");
    }

    private void forwardMouseMove(MouseEvent event, boolean mouseLeave) {
        BrowserHost host = binding.host().orElse(null);
        if (host != null)
            binding.observe(host.sendMouseMoveEvent(mouseEvent(event), mouseLeave ? 1 : 0), "forward mouse move");
    }

    private void forwardMouseWheel(MouseWheelEvent event) {
        BrowserHost host = binding.host().orElse(null);
        if (host != null) {
            int deltaY = (int) Math.round(-event.getPreciseWheelRotation() * 40.0);
            binding.observe(host.sendMouseWheelEvent(mouseEvent(event), 0, deltaY), "forward mouse wheel");
        }
    }

    private void sendKey(int type, KeyEvent event, int keyCode, int character) {
        BrowserHost host = binding.host().orElse(null);
        if (host == null) return;
        binding.observe(
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

    public void release() {
        try {
            binding.release();
        } finally {
            image = null;
            repaint();
        }
    }

    @Override
    public void close() {
        release();
    }
}
