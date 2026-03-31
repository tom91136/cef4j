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
import net.kurobako.cef4j.CefBrowserOsr;
import net.kurobako.cef4j.CefClient;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.gen.CefCursorType;
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
     * Create a CEF browser attached to this pane.
     *
     * <p>Must be called on the JavaFX application thread. The returned browser's
     * {@link CefBrowserOsr#createImmediately()} must be called on the CEF UI thread (typically the main thread).
     *
     * @param client the CEF client to attach handlers to
     * @param url initial URL to load
     * @param frameRate target frame rate
     * @return the browser instance
     */
    public CefBrowserOsr createBrowser(CefClient client, String url, int frameRate) {
        CefPane self = this;
        client.addRenderHandler(new CefRenderHandler() {
            @Override
            public void getViewRect(long b, CefMutableRect rect) {
                rect.x = 0;
                rect.y = 0;
                rect.width = Math.max(1, (int) self.getWidth());
                rect.height = Math.max(1, (int) self.getHeight());
            }

            @Override
            public boolean getScreenInfo(long b, CefMutableScreenInfo screenInfo) {
                float scale = getDeviceScaleFactor();
                int w = Math.max(1, (int) self.getWidth());
                int h = Math.max(1, (int) self.getHeight());
                screenInfo.deviceScaleFactor = scale;
                screenInfo.depth = 32;
                screenInfo.depthPerComponent = 8;
                screenInfo.rect = new CefRect(0, 0, w, h);
                screenInfo.availableRect = new CefRect(0, 0, w, h);
                return true;
            }

            @Override
            public boolean getScreenPoint(long b, int viewX, int viewY, int[] screenX, int[] screenY) {
                float scale = getDeviceScaleFactor();
                screenX[0] = Math.round(viewX * scale);
                screenY[0] = Math.round(viewY * scale);
                return true;
            }

            @Override
            public void onPaint(
                    long b,
                    CefPaintElementType type,
                    long dirtyRectsCount,
                    CefRect[] dirtyRects,
                    ByteBuffer buffer,
                    int width,
                    int height) {
                // Copy pixels from native memory on the CEF thread (buffer is only valid here).
                if (frameBuffer.onPaint(buffer, width, height, dirtyRects) != null) {
                    Platform.runLater(() -> blitFrame(width, height));
                }
            }
        });

        // Inject scrollbar CSS that approximates the current JFX theme
        String scrollbarCss = ScrollbarTheme.generateCss(getScene());
        String scrollbarScript = ScrollbarTheme.injectScript(scrollbarCss);
        client.addLoadHandler(new CefLoadHandler() {
            @Override
            public void onLoadEnd(long browser, long frame, int httpStatusCode) {
                CefBrowserOsr br = CefPane.this.browser;
                if (br != null) {
                    br.executeJavaScript(scrollbarScript, "", 0);
                }
            }
        });

        CefBrowserOsr b = client.createBrowser(url, frameRate);
        this.browser = b;
        return b;
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
        switch (type) {
            case CT_CROSS:
                return Cursor.CROSSHAIR;
            case CT_HAND:
                return Cursor.HAND;
            case CT_IBEAM:
                return Cursor.TEXT;
            case CT_WAIT:
                return Cursor.WAIT;
            case CT_MOVE:
                return Cursor.MOVE;
            case CT_NORTHRESIZE:
                return Cursor.N_RESIZE;
            case CT_SOUTHRESIZE:
                return Cursor.S_RESIZE;
            case CT_EASTRESIZE:
                return Cursor.E_RESIZE;
            case CT_WESTRESIZE:
                return Cursor.W_RESIZE;
            case CT_NORTHEASTRESIZE:
                return Cursor.NE_RESIZE;
            case CT_NORTHWESTRESIZE:
                return Cursor.NW_RESIZE;
            case CT_SOUTHEASTRESIZE:
                return Cursor.SE_RESIZE;
            case CT_SOUTHWESTRESIZE:
                return Cursor.SW_RESIZE;
            case CT_NORTHSOUTHRESIZE:
            case CT_ROWRESIZE:
                return Cursor.N_RESIZE;
            case CT_EASTWESTRESIZE:
            case CT_COLUMNRESIZE:
                return Cursor.E_RESIZE;
            case CT_POINTER:
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
            browser.sendMouseWheelEvent((int) e.getX(), (int) e.getY(), scrollModifiers(e), 0, (int) e.getDeltaY());
        }
    }

    private void handleKeyPressed(KeyEvent e) {
        if (browser == null) return;
        int mods = keyModifiers(e);
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
            int mods = keyModifiers(e);
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

    private static int mouseModifiers(MouseEvent e) {
        int mods = 0;
        if (e.isShiftDown()) mods |= EVENTFLAG_SHIFT_DOWN;
        if (e.isControlDown()) mods |= EVENTFLAG_CONTROL_DOWN;
        if (e.isAltDown()) mods |= EVENTFLAG_ALT_DOWN;
        if (e.isMetaDown()) mods |= EVENTFLAG_COMMAND_DOWN;
        if (e.isPrimaryButtonDown()) mods |= EVENTFLAG_LEFT_MOUSE_BUTTON;
        if (e.isMiddleButtonDown()) mods |= EVENTFLAG_MIDDLE_MOUSE_BUTTON;
        if (e.isSecondaryButtonDown()) mods |= EVENTFLAG_RIGHT_MOUSE_BUTTON;
        return mods;
    }

    private static int scrollModifiers(ScrollEvent e) {
        int mods = 0;
        if (e.isShiftDown()) mods |= EVENTFLAG_SHIFT_DOWN;
        if (e.isControlDown()) mods |= EVENTFLAG_CONTROL_DOWN;
        if (e.isAltDown()) mods |= EVENTFLAG_ALT_DOWN;
        if (e.isMetaDown()) mods |= EVENTFLAG_COMMAND_DOWN;
        return mods;
    }

    private static int keyModifiers(KeyEvent e) {
        int mods = 0;
        if (e.isShiftDown()) mods |= EVENTFLAG_SHIFT_DOWN;
        if (e.isControlDown()) mods |= EVENTFLAG_CONTROL_DOWN;
        if (e.isAltDown()) mods |= EVENTFLAG_ALT_DOWN;
        if (e.isMetaDown()) mods |= EVENTFLAG_COMMAND_DOWN;
        return mods;
    }

    /** Map JavaFX KeyCode to AWT KeyEvent.VK_* code (CEF uses Windows virtual key codes which align with AWT). */
    private static int mapKeyCode(KeyCode code) {
        return code.getCode();
    }
}
