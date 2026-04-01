package net.kurobako.cef4j.osr.jfx;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import net.kurobako.cef4j.CefApp;
import net.kurobako.cef4j.CefBrowserOsr;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefMutableRect;
import net.kurobako.cef4j.gen.CefMutableScreenInfo;
import net.kurobako.cef4j.gen.CefPaintElementType;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;

/**
 * A JavaFX {@link Region} that displays a CEF off-screen rendered browser.
 *
 * <p>Uses {@link CefFrameBuffer} to copy pixels from native memory on the CEF thread, then updates a JavaFX 13+
 * {@link PixelBuffer} on the application thread for efficient GPU upload.
 *
 * <p>Create a browser via {@link #createBrowser(CefClient, String, int)} after the pane is part of an active scene.
 */
@SuppressWarnings("this-escape")
public class CefPane extends Region {

    // CEF key event types (cef_key_event_type_t)
    private static final int KEYEVENT_RAWKEYDOWN = 0;
    private static final int KEYEVENT_KEYUP = 2;
    private static final int KEYEVENT_CHAR = 3;

    // CEF event flags (cef_event_flags_t)
    private static final int EVENTFLAG_SHIFT_DOWN = 1 << 1;
    private static final int EVENTFLAG_CONTROL_DOWN = 1 << 2;
    private static final int EVENTFLAG_ALT_DOWN = 1 << 3;
    private static final int EVENTFLAG_LEFT_MOUSE_BUTTON = 1 << 4;
    private static final int EVENTFLAG_MIDDLE_MOUSE_BUTTON = 1 << 5;
    private static final int EVENTFLAG_RIGHT_MOUSE_BUTTON = 1 << 6;
    private static final int EVENTFLAG_COMMAND_DOWN = 1 << 7;
    private static final int EVENTFLAG_IS_LEFT = 1 << 10;
    private static final int EVENTFLAG_IS_RIGHT = 1 << 11;

    private final ImageView imageView = new ImageView();
    private volatile CefBrowserOsr browser;

    // Frame buffer — copies pixels from native memory on CEF thread.
    // The stamp callback returns the pixel array itself (identity); the actual
    // PixelBuffer update happens on the JFX application thread.
    private final CefFrameBuffer<int[]> frameBuffer;

    // PixelBuffer state — managed on JFX application thread, recreated on resize.
    private IntBuffer pixelBuf;
    private PixelBuffer<IntBuffer> pixelBuffer;
    private WritableImage writableImage;
    private int bufWidth;
    private int bufHeight;

    public CefPane() {
        int maxW = 1, maxH = 1;
        for (Screen screen : Screen.getScreens()) {
            javafx.geometry.Rectangle2D bounds = screen.getBounds();
            double scale = Math.max(screen.getOutputScaleX(), screen.getOutputScaleY());
            int pw = (int) Math.ceil(bounds.getWidth() * scale);
            int ph = (int) Math.ceil(bounds.getHeight() * scale);
            if (pw > maxW) maxW = pw;
            if (ph > maxH) maxH = ph;
        }
        frameBuffer = new CefFrameBuffer<>(maxW, maxH, (prev, pixels, w, h, dirty) -> pixels);

        getChildren().add(imageView);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(false);
        setFocusTraversable(true);

        // Mouse events
        setOnMousePressed(this::handleMousePressed);
        setOnMouseReleased(this::handleMouseReleased);
        setOnMouseMoved(this::handleMouseMoved);
        setOnMouseDragged(this::handleMouseMoved);
        setOnScroll(this::handleScroll);

        // Keyboard events
        setOnKeyPressed(this::handleKeyPressed);
        setOnKeyReleased(this::handleKeyReleased);

        // Focus
        focusedProperty().addListener((obs, was, is) -> {
            if (browser != null) browser.setFocus(is);
        });

        // Resize
        widthProperty().addListener((obs, oldV, newV) -> onResize());
        heightProperty().addListener((obs, oldV, newV) -> onResize());
    }

    /**
     * Create a render handler that paints into this pane's frame buffer. Use this when building a {@link CefClient}
     * implementation for this pane.
     */
    public CefRenderHandler createRenderHandler() {
        return new CefRenderHandler() {
            @Override
            public void getViewRect(CefBrowser b, CefMutableRect rect) {
                rect.x = 0;
                rect.y = 0;
                rect.width = Math.max(1, (int) getWidth());
                rect.height = Math.max(1, (int) getHeight());
            }

            @Override
            public boolean getScreenInfo(CefBrowser b, CefMutableScreenInfo screenInfo) {
                float scale = getDeviceScaleFactor();
                int w = Math.max(1, (int) getWidth());
                int h = Math.max(1, (int) getHeight());
                screenInfo.deviceScaleFactor = scale;
                screenInfo.depth = 32;
                screenInfo.depthPerComponent = 8;
                screenInfo.rect = new CefRect(0, 0, w, h);
                screenInfo.availableRect = new CefRect(0, 0, w, h);
                return true;
            }

            @Override
            public boolean getScreenPoint(CefBrowser b, int viewX, int viewY, int[] screenX, int[] screenY) {
                float scale = getDeviceScaleFactor();
                screenX[0] = Math.round(viewX * scale);
                screenY[0] = Math.round(viewY * scale);
                return true;
            }

            @Override
            public void onPaint(
                    CefBrowser b,
                    CefPaintElementType type,
                    long dirtyRectsCount,
                    CefRect[] dirtyRects,
                    ByteBuffer buffer,
                    int width,
                    int height) {
                if (frameBuffer.onPaint(buffer, width, height, dirtyRects) != null) {
                    Platform.runLater(() -> blitFrame(width, height));
                }
            }
        };
    }

    /** Create a load handler that injects scrollbar CSS matching the current JFX theme on each page load. */
    public CefLoadHandler createScrollbarLoadHandler() {
        String scrollbarCss = ScrollbarTheme.generateCss(getScene());
        String scrollbarScript = ScrollbarTheme.injectScript(scrollbarCss);
        return new CefLoadHandler() {
            @Override
            public void onLoadEnd(CefBrowser browser, CefFrame frame, int httpStatusCode) {
                CefBrowserOsr br = CefPane.this.browser;
                if (br != null) {
                    br.executeJavaScript(scrollbarScript, "", 0);
                }
            }
        };
    }

    /**
     * Attach a browser to this pane. Call this after creating a browser via {@link CefApp#createBrowser(CefClient,
     * String)}.
     */
    public void setBrowser(CefBrowserOsr browser) {
        this.browser = browser;
    }

    /** Returns the browser instance, or {@code null} if {@link #createBrowser} hasn't been called yet. */
    public CefBrowserOsr getBrowser() {
        return browser;
    }

    @Override
    protected void layoutChildren() {
        imageView.setFitWidth(getWidth());
        imageView.setFitHeight(getHeight());
    }

    // --- Pixel buffer management ---

    private void ensureBuffer(int w, int h) {
        if (pixelBuffer != null && bufWidth == w && bufHeight == h) return;
        bufWidth = w;
        bufHeight = h;
        pixelBuf = IntBuffer.allocate(w * h);
        pixelBuffer = new PixelBuffer<>(w, h, pixelBuf, PixelFormat.getIntArgbPreInstance());
        writableImage = new WritableImage(pixelBuffer);
        imageView.setImage(writableImage);
    }

    private void blitFrame(int width, int height) {
        int[] pixels = frameBuffer.consume();
        if (pixels == null) return;

        ensureBuffer(width, height);
        // Copy directly into the backing array — never touch IntBuffer position/limit,
        // since JFX's render thread may read the buffer asynchronously after updateBuffer.
        System.arraycopy(pixels, 0, pixelBuf.array(), 0, width * height);
        pixelBuffer.updateBuffer(pb -> null);
    }

    private void onResize() {
        if (browser != null) {
            frameBuffer.resetBackPressure();
            browser.wasResized();
            browser.invalidate();
        }
    }

    // --- Cursor mapping ---

    /** Maps a CEF cursor type to a JavaFX {@link Cursor}. Override to customise. */
    public Cursor mapCursor(CefCursorType type) {
        return type.kind().map(CefPane::cursorForKind).orElse(Cursor.DEFAULT);
    }

    private static Cursor cursorForKind(CefCursorType.Kind k) {
        switch (k) {
            case CROSS:
                return Cursor.CROSSHAIR;
            case HAND:
                return Cursor.HAND;
            case IBEAM:
                return Cursor.TEXT;
            case WAIT:
                return Cursor.WAIT;
            case MOVE:
                return Cursor.MOVE;
            case NORTHRESIZE:
                return Cursor.N_RESIZE;
            case SOUTHRESIZE:
                return Cursor.S_RESIZE;
            case EASTRESIZE:
                return Cursor.E_RESIZE;
            case WESTRESIZE:
                return Cursor.W_RESIZE;
            case NORTHEASTRESIZE:
                return Cursor.NE_RESIZE;
            case NORTHWESTRESIZE:
                return Cursor.NW_RESIZE;
            case SOUTHEASTRESIZE:
                return Cursor.SE_RESIZE;
            case SOUTHWESTRESIZE:
                return Cursor.SW_RESIZE;
            case NORTHSOUTHRESIZE:
            case ROWRESIZE:
                return Cursor.N_RESIZE;
            case EASTWESTRESIZE:
            case COLUMNRESIZE:
                return Cursor.E_RESIZE;
            default:
                return Cursor.DEFAULT;
        }
    }

    // --- Scale factor ---

    /** Returns the device scale factor for HiDPI rendering. */
    private static float getDeviceScaleFactor() {
        try {
            return (float) Screen.getPrimary().getOutputScaleX();
        } catch (Exception e) {
            return 1.0f;
        }
    }

    // --- Input event helpers ---

    private void handleMousePressed(MouseEvent e) {
        requestFocus();
        if (browser != null) {
            browser.sendMouseClickEvent(
                    (int) e.getX(), (int) e.getY(), mouseModifiers(e), cefButton(e), false, e.getClickCount());
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        if (browser != null) {
            browser.sendMouseClickEvent(
                    (int) e.getX(), (int) e.getY(), mouseModifiers(e), cefButton(e), true, e.getClickCount());
        }
    }

    private void handleMouseMoved(MouseEvent e) {
        if (browser != null) {
            browser.sendMouseMoveEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e), false);
        }
    }

    private void handleScroll(ScrollEvent e) {
        if (browser != null) {
            browser.sendMouseWheelEvent(
                    (int) e.getX(),
                    (int) e.getY(),
                    baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown()),
                    0,
                    (int) e.getDeltaY());
        }
    }

    private void handleKeyPressed(KeyEvent e) {
        if (browser == null) return;
        int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        int keyCode = mapKeyCode(e.getCode());
        browser.sendKeyEvent(KEYEVENT_RAWKEYDOWN, mods, keyCode, keyCode, (char) 0, (char) 0, false);
        String text = e.getText();
        if (text != null
                && !text.isEmpty()
                && !e.getCode().isArrowKey()
                && !e.getCode().isFunctionKey()
                && !e.getCode().isNavigationKey()
                && !e.getCode().isModifierKey()) {
            char c = text.charAt(0);
            browser.sendKeyEvent(KEYEVENT_CHAR, mods, (int) c, (int) c, c, c, false);
        }
    }

    private void handleKeyReleased(KeyEvent e) {
        if (browser != null) {
            int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
            int keyCode = mapKeyCode(e.getCode());
            browser.sendKeyEvent(KEYEVENT_KEYUP, mods, keyCode, keyCode, (char) 0, (char) 0, false);
        }
    }

    private static int cefButton(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) return 0;
        if (e.getButton() == MouseButton.MIDDLE) return 1;
        if (e.getButton() == MouseButton.SECONDARY) return 2;
        return 0;
    }

    private static int baseModifiers(boolean shift, boolean ctrl, boolean alt, boolean meta) {
        int mods = 0;
        if (shift) mods |= EVENTFLAG_SHIFT_DOWN;
        if (ctrl) mods |= EVENTFLAG_CONTROL_DOWN;
        if (alt) mods |= EVENTFLAG_ALT_DOWN;
        if (meta) mods |= EVENTFLAG_COMMAND_DOWN;
        return mods;
    }

    private static int mouseModifiers(MouseEvent e) {
        int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        if (e.isPrimaryButtonDown()) mods |= EVENTFLAG_LEFT_MOUSE_BUTTON;
        if (e.isMiddleButtonDown()) mods |= EVENTFLAG_MIDDLE_MOUSE_BUTTON;
        if (e.isSecondaryButtonDown()) mods |= EVENTFLAG_RIGHT_MOUSE_BUTTON;
        return mods;
    }

    /** Map JavaFX KeyCode to AWT KeyEvent.VK_* code (CEF uses Windows virtual key codes which align with AWT). */
    private static int mapKeyCode(KeyCode code) {
        return code.getCode();
    }
}
