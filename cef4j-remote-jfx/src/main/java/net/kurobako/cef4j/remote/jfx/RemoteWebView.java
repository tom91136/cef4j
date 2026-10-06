package net.kurobako.cef4j.remote.jfx;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.frame.FrameMetadata;
import net.kurobako.cef4j.ipc.frame.LatestOnlyDispatcher;
import net.kurobako.cef4j.ipc.frame.RemoteBrowserBinding;
import net.kurobako.cef4j.ipc.frame.SharedFileFrameTransport;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaFX node that displays a CEF browser running in a remote server subprocess. Drop-in shape mirrors the in-process
 * {@code cef4j-inprocess-jfx} {@code CefWebView} (Region with an embedded {@link ImageView}); the difference is that
 * all browser state lives in the server, not the JVM, so the JFX side just routes pixel frames + control commands over
 * the configured Remote CEF transports.
 *
 * <p>Construction is asynchronous: {@link #attach(CefSession)} subscribes to the server's browser-created event, binds
 * the configured frame transport as soon as the browser handle is known, and resolves {@link #browserReady()} once
 * {@code LifeSpanHandlerOnAfterCreatedEvent} arrives. Until that point {@link #loadUrl} / {@link #evaluateJavascript}
 * chain off the same future and execute lazily.
 *
 * <p>Input: mouse press/release/move/exit/scroll and key press/release/typed events are forwarded through the codegen
 * {@code BrowserHost.sendMouse{Click,Move,Wheel}Event} / {@code sendKeyEvent} wires. The view captures focus on click.
 * Modifier-key state is mapped to CEF's {@code event_flags_t}; mouse-button bits are reconstructed per event.
 *
 * <p>Threading: pixel updates land on the JFX application thread via {@link Platform#runLater}; control methods can be
 * called from any thread. The CEF UI thread is the server's, not ours.
 *
 * <p>Resize: the JFX layout drives a per-browser viewport size via {@code SetViewportSizeRequest}; the server updates
 * its render-handler view rect and calls {@code was_resized} so CEF re-queries dimensions and emits a fresh paint. The
 * server grows or shrinks its double-buffered shm regions with hysteresis as viewport requirements change.
 */
public final class RemoteWebView extends Region implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteWebView.class);

    private final ImageView imageView = new ImageView();
    private final RemoteBrowserBinding binding;
    private final LatestOnlyDispatcher<FrameSnapshot> frameDispatcher =
            new LatestOnlyDispatcher<>(Platform::runLater, this::presentFrameOnFxThread);

    public RemoteWebView() {
        this(SharedFileFrameTransport::bind);
    }

    /** Creates a view whose pixels are supplied by the given transport factory when {@link #attach} is called. */
    public RemoteWebView(@Nonnull RemoteBrowserBinding.FrameTransportFactory frameTransportFactory) {
        binding = new RemoteBrowserBinding("RemoteWebView", Platform::runLater, frameTransportFactory, this::onFrame);
        getChildren().add(imageView);
        imageView.setManaged(false);
        imageView.setFitWidth(0); // size from intrinsic until paint arrives
        wireInputForwarding();
    }

    /**
     * Routes JFX mouse events to {@code BrowserHost.sendMouse*Event}. The view must be focusable for scroll/click to
     * land — JFX gives Region a focus traversable property already.
     */
    private void wireInputForwarding() {
        setFocusTraversable(true);
        setOnMousePressed(e -> {
            requestFocus();
            forwardMouseClick(e, /*mouseUp=*/ false);
        });
        setOnMouseReleased(e -> forwardMouseClick(e, /*mouseUp=*/ true));
        setOnMouseMoved(e -> forwardMouseMove(e, /*mouseLeave=*/ false));
        setOnMouseDragged(e -> forwardMouseMove(e, /*mouseLeave=*/ false));
        setOnMouseExited(e -> forwardMouseMove(e, /*mouseLeave=*/ true));
        setOnScroll(this::forwardMouseWheel);
        addEventFilter(KeyEvent.KEY_PRESSED, this::forwardKeyPressed);
        addEventFilter(KeyEvent.KEY_RELEASED, this::forwardKeyReleased);
        addEventFilter(KeyEvent.KEY_TYPED, this::forwardKeyTyped);
    }

    private void forwardMouseClick(MouseEvent e, boolean mouseUp) {
        BrowserHost host = binding.host().orElse(null);
        if (host == null) return;
        int button = jfxButtonToCef(e.getButton());
        if (button < 0) return; // unsupported (no-button event, etc.)
        net.kurobako.cef4j.ipc.protocol.gen.MouseEvent ev =
                new net.kurobako.cef4j.ipc.protocol.gen.MouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e));
        binding.observe(
                host.sendMouseClickEvent(ev, button, mouseUp ? 1 : 0, e.getClickCount()), "forward mouse click");
    }

    private void forwardMouseMove(MouseEvent e, boolean mouseLeave) {
        BrowserHost host = binding.host().orElse(null);
        if (host == null) return;
        net.kurobako.cef4j.ipc.protocol.gen.MouseEvent ev =
                new net.kurobako.cef4j.ipc.protocol.gen.MouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e));
        binding.observe(host.sendMouseMoveEvent(ev, mouseLeave ? 1 : 0), "forward mouse move");
    }

    private void forwardMouseWheel(ScrollEvent e) {
        BrowserHost host = binding.host().orElse(null);
        if (host == null) return;
        net.kurobako.cef4j.ipc.protocol.gen.MouseEvent ev =
                new net.kurobako.cef4j.ipc.protocol.gen.MouseEvent((int) e.getX(), (int) e.getY(), 0);
        binding.observe(host.sendMouseWheelEvent(ev, (int) e.getDeltaX(), (int) e.getDeltaY()), "forward mouse wheel");
    }

    /**
     * Maps JFX {@link MouseButton} to CEF's {@code cef_mouse_button_type_t} (0=left, 1=middle, 2=right). Returns -1 for
     * buttons CEF doesn't model (NONE / BACK / FORWARD via JFX's secondary buttons).
     */
    private static int jfxButtonToCef(MouseButton b) {
        switch (b) {
            case PRIMARY:
                return 0;
            case MIDDLE:
                return 1;
            case SECONDARY:
                return 2;
            default:
                return -1;
        }
    }

    /**
     * CEF event_flags bits we care about. From cef_event_flags_t: NONE=0, CAPS=1<<0, SHIFT=1<<1, CTRL=1<<2, ALT=1<<3,
     * LEFT_MOUSE_BUTTON=1<<4, MIDDLE=1<<5, RIGHT=1<<6, COMMAND=1<<7, NUM_LOCK=1<<8.
     */
    private static int baseModifiers(boolean shift, boolean ctrl, boolean alt, boolean meta) {
        int m = 0;
        if (shift) m |= (1 << 1);
        if (ctrl) m |= (1 << 2);
        if (alt) m |= (1 << 3);
        if (meta) m |= (1 << 7); // COMMAND
        return m;
    }

    private static int mouseModifiers(MouseEvent e) {
        int m = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        if (e.isPrimaryButtonDown()) m |= (1 << 4);
        if (e.isMiddleButtonDown()) m |= (1 << 5);
        if (e.isSecondaryButtonDown()) m |= (1 << 6);
        return m;
    }

    private static int keyModifiers(KeyEvent e) {
        return baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
    }

    /**
     * {@code cef_key_event_type_t}: 0=RAWKEYDOWN, 1=KEYDOWN, 2=KEYUP, 3=CHAR. JFX KEY_PRESSED maps to RAWKEYDOWN (CEF
     * then handles the OS-level char-translation step that, on a real window, would arrive as a separate WM_CHAR; for
     * OSR we replicate that via KEY_TYPED → CHAR).
     */
    private static final int KEYEVENT_RAWKEYDOWN = 0;

    private static final int KEYEVENT_KEYUP = 2;
    private static final int KEYEVENT_CHAR = 3;

    private void forwardKeyPressed(KeyEvent e) {
        sendKey(KEYEVENT_RAWKEYDOWN, e, e.getCode().getCode(), /*character=*/ 0);
    }

    private void forwardKeyReleased(KeyEvent e) {
        sendKey(KEYEVENT_KEYUP, e, e.getCode().getCode(), /*character=*/ 0);
    }

    private void forwardKeyTyped(KeyEvent e) {
        String text = e.getCharacter();
        if (text == null || text.isEmpty() || KeyEvent.CHAR_UNDEFINED.equals(text)) return;
        int c = text.charAt(0);
        sendKey(KEYEVENT_CHAR, e, c, c);
    }

    /**
     * Builds and dispatches a single CEF KeyEvent. {@code keyCode} populates both windowsKeyCode and nativeKeyCode (we
     * don't have OS-specific scancodes from JFX); {@code character} is non-zero only for KEYEVENT_CHAR.
     */
    private void sendKey(int eventType, KeyEvent jfx, int keyCode, int character) {
        BrowserHost host = binding.host().orElse(null);
        if (host == null) return;
        binding.observe(
                host.sendKeyEvent(net.kurobako.cef4j.ipc.protocol.gen.KeyEvent.builder()
                        .type(eventType)
                        .modifiers(keyModifiers(jfx))
                        .windowsKeyCode(keyCode)
                        .nativeKeyCode(keyCode)
                        .isSystemKey(0)
                        .character(character)
                        .unmodifiedCharacter(character)
                        .focusOnEditableField(0)
                        .build()),
                "forward key event");
    }

    /**
     * Wire this view to an IPC session. The session must already have a connected transport. Repeating the call with
     * the same session is idempotent; attaching this one-shot view to a different session is rejected because browser
     * handles and their completion future are session-scoped. The future returned by {@link #browserReady()} resolves
     * once the server publishes its auto-bootstrap browser handle.
     */
    public void attach(@Nonnull CefSession session) {
        binding.attach(session);
    }

    /** Future resolves with the browser handle once the server has reported its auto-created browser. */
    @Nonnull
    public CompletableFuture<RemoteHandle> browserReady() {
        return binding.browserReady();
    }

    /** Future resolves when the load is queued (server-side ack); does not wait for page rendering. */
    @Nonnull
    public CompletableFuture<Void> loadUrl(@Nonnull String url) {
        return binding.loadUrl(url);
    }

    /**
     * Evaluate JS in the main frame. Result is JSON-stringified for objects/arrays; primitives come back as their
     * string form. Returns empty string for null/undefined results, throws via the future for JS errors.
     */
    @Nonnull
    public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
        return binding.evaluateJavascript(script);
    }

    /** Requests a browser viewport resize and completes only after the remote runtime acknowledges it. */
    @Nonnull
    public CompletableFuture<Void> resizeViewport(int width, int height) {
        return binding.resizeViewport(width, height);
    }

    /**
     * Frame callback fired on the IPC session's IO thread. Copies the callback-scoped transport buffer into an owned
     * immutable snapshot, then transfers that snapshot to the JFX thread. A fresh PixelBuffer per delivered paint is
     * deliberate: JavaFX never reads a buffer that the IPC thread can concurrently overwrite.
     */
    private void onFrame(int width, int height, ByteBuffer pixels, FrameMetadata meta) {
        long expectedBytes = (long) width * height * 4L;
        if (width <= 0 || height <= 0 || expectedBytes > Integer.MAX_VALUE || pixels.remaining() != expectedBytes) {
            LOG.warn(
                    "dropping malformed frame {}x{} with {} bytes (expected {})",
                    width,
                    height,
                    pixels.remaining(),
                    expectedBytes);
            return;
        }
        ByteBuffer snapshot = ByteBuffer.allocateDirect((int) expectedBytes);
        snapshot.put(pixels.duplicate()).flip();
        frameDispatcher.submit(new FrameSnapshot(width, height, snapshot));
    }

    private void presentFrameOnFxThread(FrameSnapshot frame) {
        if (!binding.isAttached()) return;
        PixelBuffer<ByteBuffer> buffer =
                new PixelBuffer<>(frame.width, frame.height, frame.pixels, PixelFormat.getByteBgraPreInstance());
        imageView.setImage(new WritableImage(buffer));
        imageView.setFitWidth(frame.width);
        imageView.setFitHeight(frame.height);
    }

    private static final class FrameSnapshot {
        private final int width;
        private final int height;
        private final ByteBuffer pixels;

        private FrameSnapshot(int width, int height, ByteBuffer pixels) {
            this.width = width;
            this.height = height;
            this.pixels = pixels;
        }
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        binding.reportViewportSize((int) Math.max(1, width), (int) Math.max(1, height));
    }

    @Override
    protected void layoutChildren() {
        imageView.relocate(0, 0);
        imageView.setFitWidth(getWidth());
        imageView.setFitHeight(getHeight());
        binding.reportViewportSize((int) Math.max(1, getWidth()), (int) Math.max(1, getHeight()));
    }

    /** Disposes the frame transport subscription. Does not close the session; callers own its lifetime. */
    public void release() {
        binding.release();
    }

    @Override
    public void close() {
        release();
    }

    /** Blocking variant of {@link #browserReady()} that fails if no browser is reported within {@code timeout}. */
    @Nonnull
    public RemoteHandle awaitBrowserHandle(@Nonnull Duration timeout) throws Exception {
        return binding.awaitBrowserHandle(timeout);
    }
}
