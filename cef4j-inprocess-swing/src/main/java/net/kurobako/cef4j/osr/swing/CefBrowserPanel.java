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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.CefInputEventFlags;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefApp;
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
    private static final Object INITIALISE_LOCK = new Object();

    @Nullable
    private static JFrame awtBootstrapFrame;

    private static final CefKeyEventType KEY_RAWKEYDOWN = CefKeyEventType.of(CefKeyEventType.Kind.RAWKEYDOWN);
    private static final CefKeyEventType KEY_CHAR = CefKeyEventType.of(CefKeyEventType.Kind.CHAR);
    private static final CefKeyEventType KEY_KEYUP = CefKeyEventType.of(CefKeyEventType.Kind.KEYUP);
    private static final CefPaintElementType PAINT_VIEW = CefPaintElementType.of(CefPaintElementType.Kind.VIEW);

    private final transient CefFrameBuffer<BufferedImage> frameBuffer;

    @Nullable
    private transient volatile CefBrowser browser;

    @Nullable
    private transient volatile CefRect popupRect;

    @Nullable
    private transient JWindow osrPopupWindow;

    @Nullable
    private transient BufferedImage osrPopupImage;

    @Nullable
    private transient BufferedImage lastPaintedImage;

    // Cached screen location, updated on the EDT. Read by CEF render handler callbacks
    // which may run on the AppKit main thread where acquiring AWTTreeLock can deadlock
    // against the EDT during window realisation.
    private volatile int cachedScreenX;
    private volatile int cachedScreenY;
    private volatile boolean screenLocationValid;

    /**
     * Initialise CEF for off-screen rendering. Must be called before constructing any {@link CefBrowserPanel},
     * typically from the {@code main} thread. On Linux and Windows this method realises and disposes a hidden AWT peer
     * before CEF starts, which avoids toolkit-ordering crashes in older CEF releases. On macOS CEF must start before
     * AWT and the peer bootstrap is therefore omitted.
     *
     * <p>OSR-required settings ({@code windowlessRenderingEnabled=1}, the platform-appropriate message-loop mode, and
     * {@code --disable-popup-blocking} / {@code --ozone-platform=x11} on Linux) are stamped onto {@code settings} /
     * prepended to {@code extraArgs} by {@link Cef#osrLaunchArgs()}. Any other fields on {@code settings} (e.g.
     * {@code cachePath}) are preserved.
     *
     * @param settings caller-provided settings; OSR fields will be overwritten
     * @param extraArgs additional CEF command-line args; OSR defaults are prepended
     * @param appHandler optional {@link CefApp} handler; if non-null it is registered via
     *     {@link Cef#addAppHandler(CefApp)} before initialisation
     * @throws IllegalStateException if CEF has been terminated
     */
    public static void initialise(
            @Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs, @Nullable CefApp appHandler) {
        synchronized (INITIALISE_LOCK) {
            Objects.requireNonNull(settings, "settings");
            Objects.requireNonNull(extraArgs, "extraArgs");
            // Do not touch Swing before initialiseAwtPeer has checked the platform. On macOS without
            // -XstartOnFirstThread, even SwingUtilities.isEventDispatchThread() can trigger AWT initialisation and
            // block until [NSApp run] is running on Thread 0. CEF must initialise first there.
            Cef.State cefState = Cef.INSTANCE.state();
            if (cefState == Cef.State.INITIALISED) {
                requireOsrInitialised();
                return;
            }
            initialiseAwtPeer();
            SystemBootstrap.load();
            Cef.LaunchArgs defaults = Cef.osrLaunchArgs();
            CefSettings.Mutable osrDefaults = defaults.settings();
            settings.windowlessRenderingEnabled = osrDefaults.windowlessRenderingEnabled;
            settings.externalMessagePump = osrDefaults.externalMessagePump;
            settings.multiThreadedMessageLoop = osrDefaults.multiThreadedMessageLoop;
            List<String> combinedArgs = new ArrayList<>(defaults.args().size() + extraArgs.size());
            combinedArgs.addAll(defaults.args());
            combinedArgs.addAll(extraArgs);
            if (appHandler != null) {
                Cef.INSTANCE.addAppHandler(appHandler);
            }
            Cef.INSTANCE.initialise(settings, combinedArgs);
        }
    }

    private static void initialiseAwtPeer() {
        if (System.getProperty("os.name", "").toLowerCase(java.util.Locale.ROOT).contains("mac")) {
            return;
        }
        Runnable initialise = () -> {
            JFrame frame = new JFrame();
            frame.setUndecorated(true);
            frame.pack();
            awtBootstrapFrame = frame;
        };
        if (SwingUtilities.isEventDispatchThread()) {
            initialise.run();
            return;
        }
        try {
            SwingUtilities.invokeAndWait(initialise);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while initialising AWT before CEF", e);
        } catch (java.lang.reflect.InvocationTargetException e) {
            throw new IllegalStateException("Failed to initialise AWT before CEF", e.getCause());
        }
    }

    /** Terminate CEF. See {@link Cef#terminate()}. */
    public static void terminate() {
        synchronized (INITIALISE_LOCK) {
            JFrame frame = awtBootstrapFrame;
            awtBootstrapFrame = null;
            if (frame != null) {
                Runnable dispose = frame::dispose;
                if (SwingUtilities.isEventDispatchThread()) {
                    dispose.run();
                } else {
                    try {
                        SwingUtilities.invokeAndWait(dispose);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new IllegalStateException("Interrupted while shutting down AWT before CEF", e);
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        throw new IllegalStateException("Failed to shut down AWT before CEF", e.getCause());
                    }
                }
            }
            Cef.INSTANCE.terminate();
        }
    }

    private static void requireOsrInitialised() {
        Cef.State state = Cef.INSTANCE.state();
        if (state != Cef.State.INITIALISED) {
            throw new IllegalStateException(
                    "CEF must be initialised for off-screen rendering before creating a CefBrowserPanel.\n"
                            + "Call CefBrowserPanel.initialise(settings, args, handler) before constructing any panel.\n");
        }
        Cef.INSTANCE
                .activeSettings()
                .filter(s -> s.windowlessRenderingEnabled == 0)
                .ifPresent(s -> {
                    throw new IllegalStateException(
                            "CEF was initialised without windowlessRenderingEnabled=1. CefBrowserPanel requires OSR mode.\n"
                                    + "Use Cef.osrLaunchArgs() or CefBrowserPanel.initialise() instead.\n");
                });
    }

    public CefBrowserPanel() {
        requireOsrInitialised();
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
                ifHostPresent(h -> h.setFocus(true));
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Keep focus while the popup window owns pointer interaction, or CEF closes the popup immediately.
                if (osrPopupWindow != null && osrPopupWindow.isVisible()) return;
                ifHostPresent(h -> h.setFocus(false));
            }
        });

        Consumer<MouseEvent> sendMouseMove = e -> ifHostPresent(
                h -> h.sendMouseMoveEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), false));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
                ifHostPresent(h -> h.sendMouseClickEvent(
                        new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)),
                        CefMouseButtonType.of(cefButton(e)),
                        false,
                        e.getClickCount()));
                e.consume();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ifHostPresent(h -> h.sendMouseClickEvent(
                        new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)),
                        CefMouseButtonType.of(cefButton(e)),
                        true,
                        e.getClickCount()));
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
                ifHostPresent(
                        h -> h.sendMouseMoveEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), true));
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
        addMouseWheelListener(e -> ifHostPresent(h -> {
            int delta = -e.getUnitsToScroll() * SCROLL_UNITS_MULTIPLIER;
            int deltaX = 0;
            int deltaY = delta;
            if (e.isShiftDown()) {
                deltaX = delta;
                deltaY = 0;
            }
            h.sendMouseWheelEvent(new CefMouseEvent(e.getX(), e.getY(), mouseModifiers(e)), deltaX, deltaY);
        }));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isConsumed()) return;
                ifHostPresent(h -> {
                    int mods = keyModifiers(e);
                    h.sendKeyEvent(new CefKeyEvent(
                            KEY_RAWKEYDOWN, mods, e.getKeyCode(), e.getKeyCode(), 0, (char) 0, (char) 0, 0));
                    char c = e.getKeyChar();
                    if (c != KeyEvent.CHAR_UNDEFINED && !e.isActionKey()) {
                        h.sendKeyEvent(new CefKeyEvent(KEY_CHAR, mods, (int) c, (int) c, 0, c, c, 0));
                    }
                });
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.isConsumed()) return;
                ifHostPresent(h -> h.sendKeyEvent(new CefKeyEvent(
                        KEY_KEYUP, keyModifiers(e), e.getKeyCode(), e.getKeyCode(), 0, (char) 0, (char) 0, 0)));
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateScreenLocation();
                refreshView(false);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                updateScreenLocation();
                refreshView(true);
            }
        });
        addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
            @Override
            public void ancestorMoved(HierarchyEvent e) {
                updateScreenLocation();
                refreshView(true);
            }
        });

        addHierarchyListener(e -> {
            long flags = e.getChangeFlags();
            if ((flags & (HierarchyEvent.SHOWING_CHANGED | HierarchyEvent.PARENT_CHANGED)) != 0) {
                updateScreenLocation();
            }
            if ((flags & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0) {
                if (!isDisplayable()) {
                    release();
                }
            }
        });
    }

    @Nullable
    private CefBrowserHost host() {
        CefBrowser current = browser;
        return current != null ? current.getHost().orElse(null) : null;
    }

    private void ifHostPresent(Consumer<CefBrowserHost> action) {
        CefBrowserHost h = host();
        if (h != null) action.accept(h);
    }

    private void refreshView(boolean screenInfoChanged) {
        ifHostPresent(h -> {
            frameBuffer.resetBackPressure();
            if (screenInfoChanged) {
                h.notifyScreenInfoChanged();
            }
            h.wasResized();
            h.invalidate(PAINT_VIEW);
        });
    }

    /**
     * Refreshes the cached screen location. Must be called on the EDT — it acquires the AWTTreeLock via
     * {@link #getLocationOnScreen()}. The cached coordinates are read lock-free by CEF render-handler callbacks that
     * may run on the AppKit main thread.
     */
    private void updateScreenLocation() {
        if (isShowing()) {
            try {
                Point loc = getLocationOnScreen();
                cachedScreenX = loc.x;
                cachedScreenY = loc.y;
                screenLocationValid = true;
                return;
            } catch (java.awt.IllegalComponentStateException ignored) {
                // fall through
            }
        }
        screenLocationValid = false;
    }

    /** Creates the render handler used to paint this panel. */
    public CefRenderHandler createRenderHandler() {
        return new CefRenderHandler() {
            @Override
            public boolean getRootScreenRect(@Nullable CefBrowser b, @Nonnull CefRect.Mutable rect) {
                if (!screenLocationValid) return false;
                rect.x = cachedScreenX;
                rect.y = cachedScreenY;
                rect.width = Math.max(1, getWidth());
                rect.height = Math.max(1, getHeight());
                return true;
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
                if (!screenLocationValid) return false;
                screenX[0] = cachedScreenX + viewX;
                screenY[0] = cachedScreenY + viewY;
                return true;
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
                        onViewPainted(width, height);
                        repaint();
                    }
                }
            }
        };
    }

    /** Test/diagnostic hook invoked after a complete view frame has entered the panel's frame buffer. */
    protected void onViewPainted(int width, int height) {}

    /** Attaches an already-created browser to this panel. */
    public void browser(@Nullable CefBrowser browser) {
        this.browser = browser;
    }

    /** Returns the attached browser, or {@code null} if none is attached. */
    @Nullable
    public CefBrowser browser() {
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
            ifHostPresent(h -> {
                int delta = -e.getUnitsToScroll() * SCROLL_UNITS_MULTIPLIER;
                h.sendMouseWheelEvent(
                        new CefMouseEvent(e.getX() + rect.x, e.getY() + rect.y, baseModifiers(e)), 0, delta);
            });
        });
        popup.setContentPane(surface);
        osrPopupWindow = popup;
    }

    private void forwardPopupMouse(MouseEvent e, boolean mouseUp) {
        CefRect rect = popupRect;
        if (rect == null) return;
        ifHostPresent(h -> h.sendMouseClickEvent(
                new CefMouseEvent(e.getX() + rect.x, e.getY() + rect.y, mouseModifiers(e)),
                CefMouseButtonType.of(cefButton(e)),
                mouseUp,
                e.getClickCount()));
    }

    private void forwardPopupMouseMove(MouseEvent e) {
        CefRect rect = popupRect;
        if (rect == null) return;
        ifHostPresent(h -> h.sendMouseMoveEvent(
                new CefMouseEvent(e.getX() + rect.x, e.getY() + rect.y, mouseModifiers(e)), false));
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
        } catch (java.awt.IllegalComponentStateException e) {
            // Panel not currently showing; skip position update - next paint will retry.
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
