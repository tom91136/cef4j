package net.kurobako.cef4j.ipc.jfx;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
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
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.frame.MmapFrameTransport;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaFX node that displays a CEF browser running in a remote helper subprocess. Drop-in shape mirrors the in-process
 * {@code cef4j-osr-jfx} {@code CefWebView} (Region with an embedded {@link ImageView}); the difference is that all
 * browser state lives in the helper, not the JVM, so the JFX side just routes pixel frames + control commands over the
 * IPC pipe.
 *
 * <p>Construction is asynchronous: {@link #attach(CefSession)} subscribes a {@link MmapFrameTransport} eagerly (before
 * the browser handle is known, so the helper's first paint never gets dropped) and resolves {@link #browserReady()}
 * once {@code LifeSpanHandlerOnAfterCreatedEvent} arrives. Until that point {@link #loadUrl} /
 * {@link #evaluateJavascript} chain off the same future and execute lazily.
 *
 * <p>Input: mouse press/release/move/exit/scroll and key press/release/typed events are forwarded through the codegen
 * {@code BrowserHost.sendMouse{Click,Move,Wheel}Event} / {@code sendKeyEvent} wires. The view captures focus on click.
 * Modifier-key state is mapped to CEF's {@code event_flags_t}; mouse-button bits are reconstructed per event.
 * Drag-and-drop, IME and clipboard are not yet wired (cef4j-osr-jfx covers all three for the in-process backend; the
 * IPC equivalents are follow-up work).
 *
 * <p>Threading: pixel updates land on the JFX application thread via {@link Platform#runLater}; control methods can be
 * called from any thread. The CEF UI thread is the helper's, not ours.
 *
 * <p>Resize: the JFX layout drives a per-browser viewport size via {@code SetViewportSizeRequest}; the helper updates
 * its render-handler view rect and calls {@code was_resized} so CEF re-queries dimensions and emits a fresh paint. The
 * helper grows or shrinks its double-buffered shm regions with hysteresis as viewport requirements change.
 */
public final class IpcWebView extends Region {

    private static final Logger LOG = LoggerFactory.getLogger(IpcWebView.class);

    private final ImageView imageView = new ImageView();
    private final CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();

    @Nullable
    private volatile CefSession session;

    @Nullable
    private volatile RemoteHandle readyBrowser;

    @Nullable
    private MmapFrameTransport frameTransport;

    @Nullable
    private HandlerRegistration lifecycleRegistration;

    @Nullable
    private WritableImage backingImage;

    @Nullable
    private PixelBuffer<ByteBuffer> backingPixelBuffer;

    private boolean attachedOnce;

    /**
     * Last (width, height) we told the helper about via SetViewportSizeRequest, packed into the high/low halves of a
     * long so updates are atomic without an extra lock. -1 means "never reported".
     */
    private final AtomicLong desiredSize = new AtomicLong(packSize(1, 1));

    private final AtomicLong reportedSize = new AtomicLong(-1);

    /** Cached browser-host handle for input forwarding; resolved once {@link #browserReady()} fires. */
    private final AtomicReference<BrowserHost> hostRef = new AtomicReference<>();

    public IpcWebView() {
        getChildren().add(imageView);
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
        // Use event filters (capture phase) for keys so the IpcWebView consumes them before scene-level
        // shortcuts steal them. Mirrors cef4j-osr-jfx's CefWebView. Filters fire on the focused node only.
        addEventFilter(KeyEvent.KEY_PRESSED, this::forwardKeyPressed);
        addEventFilter(KeyEvent.KEY_RELEASED, this::forwardKeyReleased);
        addEventFilter(KeyEvent.KEY_TYPED, this::forwardKeyTyped);
    }

    private void forwardMouseClick(MouseEvent e, boolean mouseUp) {
        BrowserHost host = hostRef.get();
        if (host == null) return;
        int button = jfxButtonToCef(e.getButton());
        if (button < 0) return; // unsupported (no-button event, etc.)
        net.kurobako.cef4j.ipc.protocol.gen.MouseEvent ev =
                new net.kurobako.cef4j.ipc.protocol.gen.MouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e));
        host.sendMouseClickEvent(ev, button, mouseUp ? 1 : 0, e.getClickCount());
    }

    private void forwardMouseMove(MouseEvent e, boolean mouseLeave) {
        BrowserHost host = hostRef.get();
        if (host == null) return;
        net.kurobako.cef4j.ipc.protocol.gen.MouseEvent ev =
                new net.kurobako.cef4j.ipc.protocol.gen.MouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e));
        host.sendMouseMoveEvent(ev, mouseLeave ? 1 : 0);
    }

    private void forwardMouseWheel(ScrollEvent e) {
        BrowserHost host = hostRef.get();
        if (host == null) return;
        net.kurobako.cef4j.ipc.protocol.gen.MouseEvent ev =
                new net.kurobako.cef4j.ipc.protocol.gen.MouseEvent((int) e.getX(), (int) e.getY(), 0);
        host.sendMouseWheelEvent(ev, (int) e.getDeltaX(), (int) e.getDeltaY());
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
     * don't have OS-specific scancodes from JFX); {@code character} is non-zero only for KEYEVENT_CHAR. The size field
     * is left at 0 — CEF fills it on the helper side, and the wire encoder ignores it for our overlay.
     */
    private void sendKey(int eventType, KeyEvent jfx, int keyCode, int character) {
        BrowserHost host = hostRef.get();
        if (host == null) return;
        host.sendKeyEvent(new net.kurobako.cef4j.ipc.protocol.gen.KeyEvent(
                /*size=*/ 0L,
                eventType,
                keyModifiers(jfx),
                /*windowsKeyCode=*/ keyCode,
                /*nativeKeyCode=*/ keyCode,
                /*isSystemKey=*/ 0,
                character,
                /*unmodifiedCharacter=*/ character,
                /*focusOnEditableField=*/ 0));
    }

    /**
     * Wire this view to an IPC session. The session must already have a connected transport. Repeating the call with
     * the same session is idempotent; attaching this one-shot view to a different session is rejected because browser
     * handles and their completion future are session-scoped. The future returned by {@link #browserReady()} resolves
     * once the helper publishes its auto-bootstrap browser handle.
     */
    public synchronized void attach(@Nonnull CefSession session) {
        if (attachedOnce) {
            if (this.session == session) return;
            throw new IllegalStateException("IpcWebView instances cannot be attached to more than one session");
        }
        attachedOnce = true;
        this.session = session;

        // Subscribe to paint events eagerly so we never miss the helper's bootstrap paint. Filter is set once
        // we learn the browser handle below; until then, a single bootstrap browser means accept-all is safe.
        this.frameTransport = MmapFrameTransport.bindAll(session);
        frameTransport.onFrame(this::onFrame);

        this.lifecycleRegistration = session.on(
                LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                    if (!browserHandle.isDone()) {
                        readyBrowser = ev.browser();
                        browserHandle.complete(ev.browser());
                    }
                });
        // Resolve the BrowserHost facade as soon as the browser handle lands so the input handlers can fire.
        // Failures are logged but non-fatal — input is best-effort, the page can still render and respond
        // to programmatic eval.
        browserHandle.thenCompose(h -> new Browser(session, h).getHost()).whenComplete((host, ex) -> {
            if (ex != null) LOG.warn("failed to resolve BrowserHost for input forwarding: {}", ex.toString());
            else if (this.session == session) hostRef.set(host);
        });
        // layoutChildren records desiredSize even before attachment. Flush that latest value—not merely a
        // subsequent size change—as soon as the browser handle becomes available.
        browserHandle.thenAccept(handle -> flushViewportSize(session, handle));
    }

    /** Future resolves with the browser handle once the helper has reported its auto-created browser. */
    @Nonnull
    public CompletableFuture<RemoteHandle> browserReady() {
        return browserHandle.copy();
    }

    /** Future resolves when the load is queued (helper-side ack); does not wait for page rendering. */
    @Nonnull
    public CompletableFuture<Void> loadUrl(@Nonnull String url) {
        return browserHandle.thenCompose(h -> {
            CefSession s = requireSession();
            return new Browser(s, h).getMainFrame().thenCompose(frame -> frame.loadUrl(url));
        });
    }

    /**
     * Evaluate JS in the main frame. Result is JSON-stringified for objects/arrays; primitives come back as their
     * string form. Returns empty string for null/undefined results, throws via the future for JS errors.
     */
    @Nonnull
    public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
        return browserHandle.thenCompose(h -> {
            CefSession s = requireSession();
            Browser browser = new Browser(s, h);
            return browser.getMainFrame().thenCompose(frame -> s.request(
                            new EvaluateJavascriptRequest(frame.handle(), script, /*retainHandle=*/ false),
                            EvaluateJavascriptResponse.DECODER)
                    .thenApply(IpcWebView::stringify));
        });
    }

    /**
     * Frame callback fired on the IPC session's IO thread. Copies the callback-scoped transport buffer into an owned
     * immutable snapshot, then transfers that snapshot to the JFX thread. A fresh PixelBuffer per delivered paint is
     * deliberate: JavaFX never reads a buffer that the IPC thread can concurrently overwrite.
     */
    private void onFrame(int width, int height, ByteBuffer pixels, net.kurobako.cef4j.ipc.frame.FrameMetadata meta) {
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
        ByteBuffer src = pixels.duplicate();
        ByteBuffer snapshot = ByteBuffer.allocateDirect((int) expectedBytes);
        snapshot.put(src).flip();
        Platform.runLater(() -> presentFrameOnFxThread(width, height, snapshot));
    }

    private void presentFrameOnFxThread(int width, int height, ByteBuffer snapshot) {
        if (session == null) return;
        this.backingPixelBuffer = new PixelBuffer<>(width, height, snapshot, PixelFormat.getByteBgraPreInstance());
        this.backingImage = new WritableImage(backingPixelBuffer);
        imageView.setImage(backingImage);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    @Override
    protected void layoutChildren() {
        // Stretch the image view to our layout bounds, then push the new viewport size to the helper if it
        // changed. The helper updates its render-handler view rect and triggers a was_resized so CEF
        // repaints at the new size. Skip when nothing changed (or below 1px) to avoid request churn during
        // layout passes.
        imageView.relocate(0, 0);
        imageView.setFitWidth(getWidth());
        imageView.setFitHeight(getHeight());
        int w = (int) Math.max(1, getWidth());
        int h = (int) Math.max(1, getHeight());
        reportViewportSize(w, h);
    }

    private void reportViewportSize(int width, int height) {
        desiredSize.set(packSize(width, height));
        CefSession s = this.session;
        if (s == null) return;
        RemoteHandle handle = readyBrowser;
        if (handle != null) flushViewportSize(s, handle);
    }

    private void flushViewportSize(CefSession expectedSession, RemoteHandle handle) {
        if (session != expectedSession) return;
        long desired = desiredSize.get();
        long previous = reportedSize.getAndSet(desired);
        if (previous == desired) return;
        int width = (int) (desired >>> 32);
        int height = (int) desired;
        expectedSession
                .request(new SetViewportSizeRequest(handle, width, height), SetViewportSizeResponse.DECODER)
                .exceptionally(ex -> {
                    // Allow the next layout pass to retry this exact size after a transient send failure.
                    reportedSize.compareAndSet(desired, -1);
                    LOG.debug("viewport resize to {}x{} failed: {}", width, height, ex.toString());
                    return null;
                });
    }

    private static long packSize(int width, int height) {
        return ((long) width << 32) | (height & 0xFFFFFFFFL);
    }

    private CefSession requireSession() {
        CefSession s = this.session;
        if (s == null) throw new IllegalStateException("IpcWebView has not been attach()ed to a session");
        return s;
    }

    /**
     * Disposes the frame transport subscription. Does not close the underlying session — callers own the session
     * lifetime.
     */
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
        if (!browserHandle.isDone()) {
            browserHandle.completeExceptionally(new IllegalStateException("IpcWebView released before browser ready"));
        }
    }

    /**
     * Convenience: blocking variant of {@link #browserReady()} for callers that want imperative startup. Throws if the
     * helper doesn't report a browser within {@code timeout}.
     */
    @Nonnull
    public RemoteHandle awaitBrowserHandle(@Nonnull Duration timeout) throws Exception {
        return browserHandle.get(timeout.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    private static String stringify(EvaluateJavascriptResponse resp) {
        return net.kurobako.cef4j.ipc.session.JsResult.fromWire(
                        resp.valueKind(),
                        resp.boolValue(),
                        resp.intValue(),
                        resp.doubleValue(),
                        resp.stringValue(),
                        resp.errorMessage())
                .coerceToString();
    }
}
