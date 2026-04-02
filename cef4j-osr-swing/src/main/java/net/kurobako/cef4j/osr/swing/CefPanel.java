package net.kurobako.cef4j.osr.swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import javax.swing.*;
import net.kurobako.cef4j.CefBrowserOsr;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefKeyEvent;
import net.kurobako.cef4j.gen.CefKeyEventType;
import net.kurobako.cef4j.gen.CefMouseButtonType;
import net.kurobako.cef4j.gen.CefMouseEvent;
import net.kurobako.cef4j.gen.CefPaintElementType;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;

/**
 * A Swing {@link JPanel} that displays a CEF off-screen rendered browser.
 *
 * <p>Handles the full OSR pipeline: pixel buffer management, render handler, mouse/keyboard forwarding, cursor mapping,
 * and resize notification. Use {@link #createRenderHandler()} to obtain a render handler for the panel, then attach the
 * browser via {@link #setBrowser(CefBrowserOsr)}.
 */
@SuppressWarnings({"this-escape"})
public class CefPanel extends JPanel {

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
                CefBrowserHost h = host();
                if (h != null) h.setFocus(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                CefBrowserHost h = host();
                if (h != null) h.setFocus(false);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
                CefBrowserHost h = host();
                if (h != null) {
                    h.sendMouseClickEvent(
                            new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)),
                            CefMouseButtonType.of(cefButton(e)),
                            false,
                            e.getClickCount());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                CefBrowserHost h = host();
                if (h != null) {
                    h.sendMouseClickEvent(
                            new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)),
                            CefMouseButtonType.of(cefButton(e)),
                            true,
                            e.getClickCount());
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                CefBrowserHost h = host();
                if (h != null) h.sendMouseMoveEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), false);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                CefBrowserHost h = host();
                if (h != null) h.sendMouseMoveEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), false);
            }
        });
        addMouseWheelListener(e -> {
            CefBrowserHost h = host();
            if (h != null) {
                int delta = -e.getUnitsToScroll() * 20;
                h.sendMouseWheelEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), 0, delta);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                CefBrowserHost h = host();
                if (h == null) return;
                int mods = keyModifiers(e);
                h.sendKeyEvent(new CefKeyEvent(
                        CefKeyEventType.of(CefKeyEventType.Kind.RAWKEYDOWN),
                        mods,
                        e.getKeyCode(),
                        e.getKeyCode(),
                        0,
                        (char) 0,
                        (char) 0,
                        0));
                char c = e.getKeyChar();
                if (c != KeyEvent.CHAR_UNDEFINED && !e.isActionKey()) {
                    h.sendKeyEvent(new CefKeyEvent(
                            CefKeyEventType.of(CefKeyEventType.Kind.CHAR), mods, (int) c, (int) c, 0, c, c, 0));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                CefBrowserHost h = host();
                if (h != null) {
                    h.sendKeyEvent(new CefKeyEvent(
                            CefKeyEventType.of(CefKeyEventType.Kind.KEYUP),
                            keyModifiers(e),
                            e.getKeyCode(),
                            e.getKeyCode(),
                            0,
                            (char) 0,
                            (char) 0,
                            0));
                }
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                CefBrowserHost h = host();
                if (h != null) {
                    frameBuffer.resetBackPressure();
                    h.wasResized();
                    h.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
                }
            }
        });
    }

    private CefBrowserHost host() {
        CefBrowserOsr b = browser;
        return b != null ? b.getHost() : null;
    }

    /**
     * Create a render handler that paints into this panel's frame buffer. Use this when building a
     * {@link net.kurobako.cef4j.gen.CefClient} implementation for this panel.
     */
    public CefRenderHandler createRenderHandler() {
        return new CefRenderHandler() {
            @Override
            public void getViewRect(CefBrowser b, CefRect.Mutable rect) {
                rect.x = 0;
                rect.y = 0;
                rect.width = getWidth();
                rect.height = getHeight();
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
                    repaint();
                }
            }
        };
    }

    /**
     * Attach a browser to this panel. Call this after creating a browser via
     * {@link net.kurobako.cef4j.CefApp#createBrowser(net.kurobako.cef4j.gen.CefClient, String)}.
     */
    public void setBrowser(CefBrowserOsr browser) {
        this.browser = browser;
    }

    /** Returns the browser instance, or {@code null} if not yet attached. */
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

    /** Maps a CEF cursor type to a Swing {@link Cursor}. Override to customise. */
    public Cursor mapCursor(CefCursorType type) {
        return type.kind().map(CefPanel::cursorForKind).orElse(Cursor.getDefaultCursor());
    }

    private static Cursor cursorForKind(CefCursorType.Kind k) {
        switch (k) {
            case CROSS:
                return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
            case HAND:
                return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
            case IBEAM:
                return Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR);
            case WAIT:
                return Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            case MOVE:
                return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
            case NORTHRESIZE:
                return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
            case SOUTHRESIZE:
                return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
            case EASTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
            case WESTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
            case NORTHEASTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
            case NORTHWESTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
            case SOUTHEASTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
            case SOUTHWESTRESIZE:
                return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
            case NORTHSOUTHRESIZE:
            case ROWRESIZE:
                return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
            case EASTWESTRESIZE:
            case COLUMNRESIZE:
                return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
            default:
                return Cursor.getDefaultCursor();
        }
    }

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
