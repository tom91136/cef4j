package net.kurobako.cef4j.osr.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.CefInputEventFlags;
import net.kurobako.cef4j.SystemBootstrap;
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
import net.kurobako.cef4j.gen.CefScreenInfo;
import net.kurobako.cef4j.gen.CefSettings;

/** Swing off-screen rendering panel for a CEF browser. */
@SuppressWarnings({"this-escape", "resource"})
public class CefBrowserPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private static final int SCROLL_UNITS_MULTIPLIER = 20;
    private static final int SCREEN_DEPTH = 32;
    private static final int SCREEN_DEPTH_PER_COMPONENT = 8;

    private final transient CefFrameBuffer<BufferedImage> frameBuffer;
    private transient volatile CefBrowser browser;
    private transient volatile CefRect popupRect;
    private transient JWindow osrPopupWindow;
    private transient BufferedImage osrPopupImage;
    private transient BufferedImage lastPaintedImage;

    /**
     * Lazily initialise CEF with Swing OSR defaults if it has not already been initialised. Callers that need custom
     * settings should call {@link Cef#initialise(CefSettings.Mutable, List)} directly before creating any panel.
     */
    public static synchronized void ensureCefInitialised() {
        Cef.State state = Cef.INSTANCE.getState();
        if (state == Cef.State.INITIALISED) {
            Cef.INSTANCE
                    .getActiveSettings()
                    .filter(s -> s.windowlessRenderingEnabled == 0)
                    .ifPresent(s -> {
                        throw new IllegalStateException(
                                "CEF is already initialised without windowlessRenderingEnabled; CefBrowserPanel needs OSR mode");
                    });
            return;
        }
        if (state == Cef.State.SHUTTING_DOWN || state == Cef.State.TERMINATED) {
            throw new IllegalStateException("CEF has been shut down and cannot be reinitialised in this JVM");
        }
        SystemBootstrap.load();
        java.nio.file.Path cacheDir = java.nio.file.Path.of(System.getProperty("java.io.tmpdir"), "cef4j-swing-cache");
        try {
            java.nio.file.Files.createDirectories(cacheDir);
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
        Cef.LaunchArgs launch = Cef.osrLaunchArgs();
        launch.settings().cachePath = cacheDir.toAbsolutePath().toString();
        Cef.INSTANCE.initialise(launch.settings(), launch.args());
    }

    public CefBrowserPanel() {
        int maxW = 1, maxH = 1;
        for (GraphicsDevice dev :
                GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            DisplayMode dm = dev.getDisplayMode();
            AffineTransform tx = dev.getDefaultConfiguration().getDefaultTransform();
            double scale = Math.max(tx.getScaleX(), tx.getScaleY());
            int pw = (int) Math.ceil(dm.getWidth() * scale);
            int ph = (int) Math.ceil(dm.getHeight() * scale);
            if (pw > maxW) maxW = pw;
            if (ph > maxH) maxH = ph;
        }
        frameBuffer = new CefFrameBuffer<>(maxW, maxH, (prev, pixels, w, h, dirty) -> {
            BufferedImage img = (prev != null && prev.getWidth() == w && prev.getHeight() == h)
                    ? prev
                    : new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            int[] dst = ((java.awt.image.DataBufferInt) img.getRaster().getDataBuffer()).getData();
            // The paint buffer is a full frame; partial copies flicker with the current double-buffering path.
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
                // Keep focus while the popup window owns pointer interaction, or CEF closes the popup immediately.
                if (osrPopupWindow != null && osrPopupWindow.isVisible()) return;
                CefBrowserHost h = host();
                if (h != null) h.setFocus(false);
            }
        });

        Consumer<MouseEvent> sendMouseMove = e -> {
            CefBrowserHost h = host();
            if (h != null) h.sendMouseMoveEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), false);
        };
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
                e.consume();
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
                e.consume();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sendMouseMove.accept(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Ignore transitions into the popup window so CEF does not treat them as a real leave.
                JWindow popup = osrPopupWindow;
                if (popup != null && popup.isVisible()) {
                    Point screen = e.getLocationOnScreen();
                    if (popup.getBounds().contains(screen)) return;
                }
                CefBrowserHost h = host();
                if (h != null) {
                    h.sendMouseMoveEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), true);
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                sendMouseMove.accept(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                sendMouseMove.accept(e);
            }
        });
        addMouseWheelListener(e -> {
            CefBrowserHost h = host();
            if (h != null) {
                int delta = -e.getUnitsToScroll() * SCROLL_UNITS_MULTIPLIER;
                int deltaX = 0;
                int deltaY = delta;
                if (e.isShiftDown()) {
                    deltaX = delta;
                    deltaY = 0;
                }
                h.sendMouseWheelEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), deltaX, deltaY);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isConsumed()) return;
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
                if (e.isConsumed()) return;
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
                refreshView(false);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                refreshView(true);
            }
        });
        addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
            @Override
            public void ancestorMoved(HierarchyEvent e) {
                refreshView(true);
            }
        });

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0) {
                if (!isDisplayable()) {
                    release();
                }
            }
        });
    }

    @Override
    public void addNotify() {
        super.addNotify();
        ensureCefInitialised();
    }

    private CefBrowserHost host() {
        CefBrowser current = browser;
        return current != null ? current.getHost().orElse(null) : null;
    }

    private void refreshView(boolean screenInfoChanged) {
        CefBrowserHost h = host();
        if (h == null) return;
        frameBuffer.resetBackPressure();
        if (screenInfoChanged) {
            h.notifyScreenInfoChanged();
        }
        h.wasResized();
        h.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
    }

    /** Creates the render handler used to paint this panel. */
    public CefRenderHandler createRenderHandler() {
        return new CefRenderHandler() {
            @Override
            public boolean getRootScreenRect(@Nullable CefBrowser b, @Nonnull CefRect.Mutable rect) {
                try {
                    Point loc = getLocationOnScreen();
                    rect.x = loc.x;
                    rect.y = loc.y;
                    rect.width = Math.max(1, getWidth());
                    rect.height = Math.max(1, getHeight());
                    return true;
                } catch (java.awt.IllegalComponentStateException e) {
                    return false;
                }
            }

            @Override
            public void getViewRect(@Nullable CefBrowser b, @Nonnull CefRect.Mutable rect) {
                rect.x = 0;
                rect.y = 0;
                rect.width = Math.max(1, getWidth());
                rect.height = Math.max(1, getHeight());
            }

            @Override
            public boolean getScreenPoint(@Nullable CefBrowser b, int viewX, int viewY, int[] screenX, int[] screenY) {
                try {
                    Point loc = getLocationOnScreen();
                    screenX[0] = loc.x + viewX;
                    screenY[0] = loc.y + viewY;
                    return true;
                } catch (java.awt.IllegalComponentStateException e) {
                    return false;
                }
            }

            @Override
            public boolean getScreenInfo(@Nullable CefBrowser b, @Nonnull CefScreenInfo.Mutable screenInfo) {
                GraphicsConfiguration gc = getGraphicsConfiguration();
                if (gc == null) return false;
                float scale = getEffectiveScaleFactor();
                screenInfo.deviceScaleFactor = scale;
                screenInfo.depth = SCREEN_DEPTH;
                screenInfo.depthPerComponent = SCREEN_DEPTH_PER_COMPONENT;
                Rectangle bounds = gc.getBounds();
                screenInfo.rect = new CefRect(
                        (int) (bounds.x * scale),
                        (int) (bounds.y * scale),
                        Math.max(1, (int) (bounds.width * scale)),
                        Math.max(1, (int) (bounds.height * scale)));
                Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
                screenInfo.availableRect = new CefRect(
                        (int) ((bounds.x + insets.left) * scale),
                        (int) ((bounds.y + insets.top) * scale),
                        Math.max(1, (int) ((bounds.width - insets.left - insets.right) * scale)),
                        Math.max(1, (int) ((bounds.height - insets.top - insets.bottom) * scale)));
                return true;
            }

            @Override
            public void onPopupShow(@Nullable CefBrowser browser, boolean show) {
                SwingUtilities.invokeLater(() -> {
                    if (show) {
                        showOsrPopup();
                    } else {
                        hideOsrPopup();
                    }
                });
            }

            @Override
            public void onPopupSize(@Nullable CefBrowser browser, @Nonnull CefRect rect) {
                popupRect = new CefRect(rect.x, rect.y, rect.width, rect.height);
            }

            @Override
            public void onPaint(
                    @Nullable CefBrowser b,
                    @Nonnull CefPaintElementType type,
                    long dirtyRectsCount,
                    @Nonnull CefRect[] dirtyRects,
                    @Nonnull ByteBuffer buffer,
                    int width,
                    int height) {
                boolean isPopup = type.kind().orElse(CefPaintElementType.Kind.VIEW) == CefPaintElementType.Kind.POPUP;
                if (isPopup) {
                    int pixelCount = width * height;
                    int[] px = new int[pixelCount];
                    buffer.order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().get(px, 0, pixelCount);
                    SwingUtilities.invokeLater(() -> blitOsrPopup(px, width, height));
                } else {
                    if (frameBuffer.onPaint(buffer, width, height, dirtyRects) != null) {
                        repaint();
                    }
                }
            }
        };
    }

    /** Attaches an already-created browser to this panel. */
    public void setBrowser(CefBrowser browser) {
        this.browser = browser;
    }

    /** Returns the attached browser, or {@code null} if none is attached. */
    public CefBrowser getBrowser() {
        return browser;
    }

    /** Returns the effective HiDPI scale factor for this panel. */
    float getEffectiveScaleFactor() {
        GraphicsConfiguration gc = getGraphicsConfiguration();
        if (gc == null) return 1f;
        AffineTransform tx = gc.getDefaultTransform();
        return (float) Math.max(tx.getScaleX(), tx.getScaleY());
    }

    /** Releases the attached browser if present. */
    public void release() {
        CefBrowser b = browser;
        browser = null;
        lastPaintedImage = null;
        hideOsrPopup();
        if (b != null) {
            b.getHost().ifPresent(host -> host.closeBrowser(true));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage fresh = frameBuffer.consume();
        if (fresh != null) {
            lastPaintedImage = fresh;
        }
        BufferedImage img = lastPaintedImage;
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }
    }

    public Cursor mapCursor(CefCursorType type) {
        return type.kind().map(CefBrowserPanel::cursorForKind).orElse(Cursor.getDefaultCursor());
    }

    private void showOsrPopup() {
        hideOsrPopup();
        Window ancestor = SwingUtilities.getWindowAncestor(this);
        if (ancestor == null) return;
        JWindow popup = new JWindow(ancestor);
        popup.setFocusableWindowState(false);
        JComponent surface = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                BufferedImage img = osrPopupImage;
                if (img != null) {
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        surface.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                forwardPopupMouse(e, false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                forwardPopupMouse(e, true);
            }
        });
        surface.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                forwardPopupMouseMove(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                forwardPopupMouseMove(e);
            }
        });
        surface.addMouseWheelListener(e -> {
            CefRect rect = popupRect;
            if (rect == null) return;
            CefBrowserHost h = host();
            if (h != null) {
                int delta = -e.getUnitsToScroll() * SCROLL_UNITS_MULTIPLIER;
                h.sendMouseWheelEvent(
                        new CefMouseEvent(e.getX() + rect.x, e.getY() + rect.y, baseModifiers(e)), 0, delta);
            }
        });
        popup.setContentPane(surface);
        osrPopupWindow = popup;
    }

    private void forwardPopupMouse(MouseEvent e, boolean mouseUp) {
        CefRect rect = popupRect;
        if (rect == null) return;
        CefBrowserHost h = host();
        if (h != null) {
            h.sendMouseClickEvent(
                    new CefMouseEvent(e.getX() + rect.x, e.getY() + rect.y, mouseModifiers(e)),
                    CefMouseButtonType.of(cefButton(e)),
                    mouseUp,
                    e.getClickCount());
        }
    }

    private void forwardPopupMouseMove(MouseEvent e) {
        CefRect rect = popupRect;
        if (rect == null) return;
        CefBrowserHost h = host();
        if (h != null) {
            h.sendMouseMoveEvent(new CefMouseEvent(e.getX() + rect.x, e.getY() + rect.y, mouseModifiers(e)), false);
        }
    }

    private void hideOsrPopup() {
        if (osrPopupWindow != null) {
            osrPopupWindow.dispose();
            osrPopupWindow = null;
        }
        osrPopupImage = null;
    }

    private void blitOsrPopup(int[] pixels, int width, int height) {
        if (osrPopupWindow == null) return;
        CefRect rect = popupRect;
        if (rect == null) return;
        BufferedImage img = osrPopupImage;
        if (img == null || img.getWidth() != width || img.getHeight() != height) {
            img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            osrPopupImage = img;
        }
        int[] dst = ((java.awt.image.DataBufferInt) img.getRaster().getDataBuffer()).getData();
        System.arraycopy(pixels, 0, dst, 0, width * height);
        osrPopupWindow.setSize(rect.width, rect.height);
        try {
            Point panelScreen = getLocationOnScreen();
            osrPopupWindow.setLocation(panelScreen.x + rect.x, panelScreen.y + rect.y);
        } catch (java.awt.IllegalComponentStateException ignored) {
        }
        if (!osrPopupWindow.isVisible()) {
            osrPopupWindow.setVisible(true);
        }
        osrPopupWindow.repaint();
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
            case NORTHSOUTHRESIZE:
            case ROWRESIZE:
                return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
            case SOUTHRESIZE:
                return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
            case EASTRESIZE:
            case EASTWESTRESIZE:
            case COLUMNRESIZE:
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
        return CefInputEventFlags.baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
    }

    private static int mouseModifiers(MouseEvent e) {
        return CefInputEventFlags.withMouseButtons(
                baseModifiers(e),
                (e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0,
                (e.getModifiersEx() & InputEvent.BUTTON2_DOWN_MASK) != 0,
                (e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) != 0);
    }

    private static int keyModifiers(KeyEvent e) {
        int loc = e.getKeyLocation();
        return CefInputEventFlags.withKeyLocation(
                baseModifiers(e), loc == KeyEvent.KEY_LOCATION_LEFT, loc == KeyEvent.KEY_LOCATION_RIGHT);
    }
}
