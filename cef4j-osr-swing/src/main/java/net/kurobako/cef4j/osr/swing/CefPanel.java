package net.kurobako.cef4j.osr.swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import javax.swing.*;
import net.kurobako.cef4j.CefBrowserOsr;
import net.kurobako.cef4j.CefClient;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefMutableRect;
import net.kurobako.cef4j.gen.CefPaintElementType;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;

/**
 * A Swing {@link JPanel} that displays a CEF off-screen rendered browser.
 *
 * <p>Handles the full OSR pipeline: pixel buffer management, render handler, mouse/keyboard forwarding, cursor mapping,
 * and resize notification. Create a browser via {@link #createBrowser(CefClient, String, int)} after the panel is
 * visible and has a non-zero size.
 */
@SuppressWarnings({"serial", "this-escape"})
public class CefPanel extends JPanel {

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

    private final transient CefFrameBuffer<BufferedImage> frameBuffer;
    private transient volatile CefBrowserOsr browser;

    public CefPanel() {
        int maxW = 1, maxH = 1;
        for (GraphicsDevice dev :
                GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            DisplayMode dm = dev.getDisplayMode();
            if (dm.getWidth() > maxW) maxW = dm.getWidth();
            if (dm.getHeight() > maxH) maxH = dm.getHeight();
        }
        frameBuffer = new CefFrameBuffer<>(maxW, maxH, (prev, pixels, w, h, dirty) -> {
            BufferedImage img = (prev != null && prev.getWidth() == w && prev.getHeight() == h)
                    ? prev
                    : new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            int[] dst = ((java.awt.image.DataBufferInt) img.getRaster().getDataBuffer()).getData();
            System.arraycopy(pixels, 0, dst, 0, w * h);
            return img;
        });

        setBackground(Color.WHITE);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (browser != null) browser.setFocus(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (browser != null) browser.setFocus(false);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
                if (browser != null) {
                    browser.sendMouseClickEvent(
                            e.getX(), e.getY(), mouseModifiers(e), cefButton(e), false, e.getClickCount());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (browser != null) {
                    browser.sendMouseClickEvent(
                            e.getX(), e.getY(), mouseModifiers(e), cefButton(e), true, e.getClickCount());
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (browser != null) browser.sendMouseMoveEvent(e.getX(), e.getY(), mouseModifiers(e), false);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (browser != null) browser.sendMouseMoveEvent(e.getX(), e.getY(), mouseModifiers(e), false);
            }
        });
        addMouseWheelListener(e -> {
            if (browser != null) {
                int delta = -e.getUnitsToScroll() * 20;
                browser.sendMouseWheelEvent(e.getX(), e.getY(), mouseModifiers(e), 0, delta);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (browser == null) return;
                int mods = keyModifiers(e);
                browser.sendKeyEvent(
                        KEYEVENT_RAWKEYDOWN, mods, e.getKeyCode(), e.getKeyCode(), (char) 0, (char) 0, false);
                char c = e.getKeyChar();
                if (c != KeyEvent.CHAR_UNDEFINED && !e.isActionKey()) {
                    browser.sendKeyEvent(KEYEVENT_CHAR, mods, (int) c, (int) c, c, c, false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (browser != null) {
                    browser.sendKeyEvent(
                            KEYEVENT_KEYUP, keyModifiers(e), e.getKeyCode(), e.getKeyCode(), (char) 0, (char) 0, false);
                }
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (browser != null) {
                    frameBuffer.resetBackPressure();
                    browser.wasResized();
                    browser.invalidate();
                }
            }
        });
    }

    /**
     * Create a CEF browser attached to this panel.
     *
     * <p>Must be called on the Swing EDT. The returned browser's {@link CefBrowserOsr#createImmediately()} must be
     * called on the CEF UI thread (typically the main thread).
     *
     * @param client the CEF client to attach handlers to
     * @param url initial URL to load
     * @param frameRate target frame rate (use monitor refresh rate)
     * @return the browser instance
     */
    public CefBrowserOsr createBrowser(CefClient client, String url, int frameRate) {
        CefPanel self = this;
        client.addRenderHandler(new CefRenderHandler() {
            @Override
            public void getViewRect(long b, CefMutableRect rect) {
                rect.x = 0;
                rect.y = 0;
                rect.width = self.getWidth();
                rect.height = self.getHeight();
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
                if (frameBuffer.onPaint(buffer, width, height, dirtyRects) != null) {
                    self.repaint();
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = frameBuffer.consume();
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }
    }

    // --- Cursor mapping ---

    /** Maps a CEF cursor type to a Swing {@link Cursor}. Override to customise. */
    public Cursor mapCursor(CefCursorType type) {
        switch (type) {
            case CT_CROSS:
                return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
            case CT_HAND:
                return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            case CT_IBEAM:
                return Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR);
            case CT_WAIT:
                return Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            case CT_MOVE:
                return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
            case CT_NORTHRESIZE:
                return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
            case CT_SOUTHRESIZE:
                return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
            case CT_EASTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
            case CT_WESTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
            case CT_NORTHEASTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
            case CT_NORTHWESTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
            case CT_SOUTHEASTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
            case CT_SOUTHWESTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
            case CT_NORTHSOUTHRESIZE:
            case CT_ROWRESIZE:
                return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
            case CT_EASTWESTRESIZE:
            case CT_COLUMNRESIZE:
                return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
            case CT_POINTER:
            default:
                return Cursor.getDefaultCursor();
        }
    }

    // --- Input event helpers ---

    private static int cefButton(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) return 0;
        if (SwingUtilities.isMiddleMouseButton(e)) return 1;
        if (SwingUtilities.isRightMouseButton(e)) return 2;
        return 0;
    }

    private static int baseModifiers(InputEvent e) {
        int mods = 0;
        if (e.isShiftDown()) mods |= EVENTFLAG_SHIFT_DOWN;
        if (e.isControlDown()) mods |= EVENTFLAG_CONTROL_DOWN;
        if (e.isAltDown()) mods |= EVENTFLAG_ALT_DOWN;
        if (e.isMetaDown()) mods |= EVENTFLAG_COMMAND_DOWN;
        return mods;
    }

    private static int mouseModifiers(MouseEvent e) {
        int mods = baseModifiers(e);
        if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0) mods |= EVENTFLAG_LEFT_MOUSE_BUTTON;
        if ((e.getModifiersEx() & InputEvent.BUTTON2_DOWN_MASK) != 0) mods |= EVENTFLAG_MIDDLE_MOUSE_BUTTON;
        if ((e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) != 0) mods |= EVENTFLAG_RIGHT_MOUSE_BUTTON;
        return mods;
    }

    private static int keyModifiers(KeyEvent e) {
        int mods = baseModifiers(e);
        int loc = e.getKeyLocation();
        if (loc == KeyEvent.KEY_LOCATION_LEFT) mods |= EVENTFLAG_IS_LEFT;
        if (loc == KeyEvent.KEY_LOCATION_RIGHT) mods |= EVENTFLAG_IS_RIGHT;
        return mods;
    }
}
