package net.kurobako.cef4j.osr.jfx;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
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
import javafx.stage.Window;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.CefInputEventFlags;
import net.kurobako.cef4j.CefScriptEngine;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefKeyEvent;
import net.kurobako.cef4j.gen.CefKeyEventType;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefMouseButtonType;
import net.kurobako.cef4j.gen.CefMouseEvent;
import net.kurobako.cef4j.gen.CefPaintElementType;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefScreenInfo;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefWindowInfo;

/** JavaFX off-screen rendering view backed by a CEF browser. */
@SuppressWarnings({"this-escape", "resource"})
public class CefWebView extends Region {
    private static final Object SETUP_LOCK = new Object();
    private static final Cleaner CLEANER = Cleaner.create();
    private static volatile SetupState activeSetup;

    private final ImageView imageView = new ImageView();
    private final CefFrameBuffer<int[]> frameBuffer;
    final CefWebEngine engine = new CefWebEngine(this);
    final CefScriptEngine scriptEngine = new CefScriptEngine(
            () -> getBrowser() != null ? getBrowser().getMainFrame().orElse(null) : null);
    private final CefClient client = new CefWebViewClient(this);
    private final ChangeListener<Boolean> windowShowingListener = (obs, wasShowing, isShowing) -> {
        if (isShowing) {
            maybeCreateBrowser(false);
        }
    };
    private final ChangeListener<Window> sceneWindowListener = (obs, oldWindow, newWindow) -> {
        if (oldWindow != null) {
            detachWindowListeners(oldWindow);
            oldWindow.showingProperty().removeListener(windowShowingListener);
        }
        onWindowChanged(newWindow);
    };
    private final ChangeListener<Number> windowBoundsListener = (obs, oldValue, newValue) -> requestViewRefresh(true);
    private final ChangeListener<Boolean> windowFocusedListener = (obs, wasFocused, isFocused) -> {
        if (!isFocused) {
            hidePopupOverlay();
            CefBrowserHost h = host();
            if (h != null) h.setFocus(false);
        }
    };
    private final DoubleProperty zoom = new SimpleDoubleProperty(this, "zoom", 1.0) {
        @Override
        protected void invalidated() {
            applyZoom(get());
        }
    };
    private volatile BrowserHandle browser;
    private final BrowserCleanupAction browserCleanup = new BrowserCleanupAction();
    private final Cleaner.Cleanable cleanable;
    ContextMenu activeContextMenu;
    private IntBuffer pixelBuf;
    private PixelBuffer<IntBuffer> pixelBuffer;
    private WritableImage writableImage;
    private int bufWidth;
    private int bufHeight;
    private final CefWebViewPopupSurface popupSurface = new CefWebViewPopupSurface(this);
    volatile CefRect popupRect;
    private volatile boolean browserCreationPosted;
    private volatile boolean browserCreated;
    volatile Rectangle2D detachedBounds = new Rectangle2D(0, 0, 1, 1);
    private final Queue<Consumer<BrowserHandle>> pendingBrowserActions = new ConcurrentLinkedQueue<>();

    public CefWebView() {
        if (activeSetup == null) {
            throw new IllegalStateException(
                    "CefWebView.initialise() must be called before creating a CefWebView instance");
        }
        int maxW = 1;
        int maxH = 1;
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
        imageView.setMouseTransparent(true);
        setFocusTraversable(true);

        setOnMousePressed(e -> handleMouseClick(e, false));
        setOnMouseReleased(e -> handleMouseClick(e, true));
        setOnMouseMoved(this::handleMouseMoved);
        setOnMouseDragged(this::handleMouseMoved);
        setOnMouseExited(this::handleMouseExited);
        setOnScroll(this::handleScroll);
        setOnKeyPressed(this::handleKeyPressed);
        setOnKeyReleased(this::handleKeyReleased);
        setOnKeyTyped(this::handleKeyTyped);

        focusedProperty().addListener((obs, was, is) -> {
            if (is) {
                CefBrowserHost h = host();
                if (h != null) h.setFocus(true);
            } else {
                // Let window focus loss drive host focus changes; doing it here closes compositor popups too early.
                hidePopupOverlay();
            }
        });
        widthProperty().addListener((obs, oldV, newV) -> onResize());
        heightProperty().addListener((obs, oldV, newV) -> onResize());
        sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (oldScene != null) {
                oldScene.windowProperty().removeListener(sceneWindowListener);
            }
            if (newScene != null) {
                newScene.windowProperty().addListener(sceneWindowListener);
                onWindowChanged(newScene.getWindow());
            }
            maybeCreateBrowser(false);
        });
        cleanable = CLEANER.register(this, browserCleanup);
    }

    public static void initialise() {
        initialise(new CefSettings.Mutable());
    }

    /** Initialise CEF with the given settings. */
    public static void initialise(CefSettings.Mutable settings, String... extraArgs) {
        initialise(settings, null, extraArgs);
    }

    /**
     * Initialise CEF for off-screen rendering with an optional custom {@link CefApp} handler.
     *
     * @throws IllegalStateException if the JavaFX toolkit is already running, or if CEF was terminated
     */
    public static void initialise(CefSettings.Mutable settings, CefApp appHandler, String... extraArgs) {
        SetupState requested = SetupState.of(settings, extraArgs);
        synchronized (SETUP_LOCK) {
            if (activeSetup != null && activeSetup.equals(requested)) return;
            if (activeSetup != null) {
                throw new IllegalStateException("CEF can only be configured once per JVM. Existing setup "
                        + activeSetup
                        + " does not match requested setup "
                        + requested
                        + ".");
            }
            if (Platform.isFxApplicationThread()
                    || Thread.getAllStackTraces().keySet().stream()
                            .anyMatch(t -> "JavaFX Application Thread".equals(t.getName()))) {
                throw new IllegalStateException(
                        "CefWebView.initialise() must be called before the JavaFX toolkit is started");
            }
            SystemBootstrap.load();
            Cef.INSTANCE.initialise(requested.settings.toMutable(), requested.extraArgs, appHandler);
            activeSetup = requested;
        }
    }

    /** Terminate CEF and release all native resources. */
    public static void terminate() {
        synchronized (SETUP_LOCK) {
            Cef.INSTANCE.terminate();
            activeSetup = null;
        }
    }

    public CefWebEngine getEngine() {
        return engine;
    }

    public CefClient getCefClient() {
        return client;
    }

    public CefScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    /** Returns the underlying browser instance, or {@code null} if it does not exist yet. */
    public CefBrowser getBrowser() {
        BrowserHandle current = browser;
        return current != null ? current.getBrowser() : null;
    }

    /** Returns the underlying browser host, or {@code null} if it does not exist yet. */
    public CefBrowserHost getBrowserHost() {
        BrowserHandle current = browser;
        return current != null ? current.getHost() : null;
    }

    /** Returns the current zoom factor. */
    public final double getZoom() {
        return zoom.get();
    }

    public final void setZoom(double value) {
        zoom.set(value);
    }

    public final DoubleProperty zoomProperty() {
        return zoom;
    }

    /**
     * Forces immediate browser creation.
     *
     * @throws IllegalStateException if the view is not attached to a showing window
     */
    public void createImmediately() {
        maybeCreateBrowser(true);
    }

    /** Navigates to the given URL. */
    public void load(String url) {
        engine.updateLocation(url);
        runWhenBrowserReady(false, current -> {
            current.loadUrl(engine.getLocation());
            requestViewRefresh(false);
        });
    }

    /** Reloads the current page if the browser exists. */
    public void reload() {
        runWhenBrowserReady(false, current -> {
            CefBrowser b = current.getBrowser();
            if (b != null) {
                b.reload();
                requestViewRefresh(false);
            }
        });
    }

    /** Stops the current load if the browser exists. */
    public void stop() {
        runWhenBrowserReady(false, current -> {
            CefBrowser b = current.getBrowser();
            if (b != null) {
                b.stopLoad();
                requestViewRefresh(false);
            }
        });
    }

    /** Navigates back if the browser exists. */
    public void goBack() {
        runWhenBrowserReady(false, current -> {
            CefBrowser b = current.getBrowser();
            if (b != null) {
                b.goBack();
                requestViewRefresh(false);
            }
        });
    }

    /** Navigates forward if the browser exists. */
    public void goForward() {
        runWhenBrowserReady(false, current -> {
            CefBrowser b = current.getBrowser();
            if (b != null) {
                b.goForward();
                requestViewRefresh(false);
            }
        });
    }

    /** Executes JavaScript in the main frame if JavaScript is enabled. */
    public void executeScript(String script) {
        if (!engine.isJavaScriptEnabled()) return;
        runWhenBrowserReady(
                false, current -> current.executeJavaScript(script == null ? "" : script, engine.getLocation(), 0));
    }

    /** Releases this view's native browser and associated resources. */
    public void release() {
        popupSurface.hide();
        BrowserHandle h = browser;
        browser = null;
        browserCleanup.browser = null;
        browserCreated = false;
        browserCreationPosted = false;
        imageView.setImage(null);
        pixelBuf = null;
        pixelBuffer = null;
        writableImage = null;
        var scene = getScene();
        if (scene != null) {
            scene.windowProperty().removeListener(sceneWindowListener);
            Window window = scene.getWindow();
            if (window != null) detachWindowListeners(window);
        }
        scriptEngine.dispose();
        if (h != null) {
            h.close(true);
        }
        Platform.runLater(() -> engine.fireVisibilityChanged(false));
        cleanable.clean();
    }

    @Override
    protected void layoutChildren() {
        imageView.setFitWidth(getWidth());
        imageView.setFitHeight(getHeight());
    }

    /** Creates the render handler used by the default client. */
    public CefRenderHandler createRenderHandler() {
        return new CefRenderHandler() {
            @Override
            public boolean getRootScreenRect(CefBrowser b, @Nonnull CefRect.Mutable rect) {
                javafx.geometry.Bounds bounds = localToScreen(getBoundsInLocal());
                if (bounds == null) {
                    Rectangle2D fallback = detachedBounds;
                    rect.x = (int) Math.round(fallback.getMinX());
                    rect.y = (int) Math.round(fallback.getMinY());
                    rect.width = Math.max(1, (int) Math.round(fallback.getWidth()));
                    rect.height = Math.max(1, (int) Math.round(fallback.getHeight()));
                    return true;
                }
                rect.x = (int) Math.round(bounds.getMinX());
                rect.y = (int) Math.round(bounds.getMinY());
                rect.width = Math.max(1, (int) Math.round(bounds.getWidth()));
                rect.height = Math.max(1, (int) Math.round(bounds.getHeight()));
                return true;
            }

            @Override
            public void getViewRect(CefBrowser b, @Nonnull CefRect.Mutable rect) {
                Rectangle2D bounds = detachedBounds;
                rect.x = 0;
                rect.y = 0;
                rect.width = Math.max(1, (int) Math.round(getWidth() > 0 ? getWidth() : bounds.getWidth()));
                rect.height = Math.max(1, (int) Math.round(getHeight() > 0 ? getHeight() : bounds.getHeight()));
            }

            @Override
            public boolean getScreenInfo(CefBrowser b, @Nonnull CefScreenInfo.Mutable screenInfo) {
                Screen screen = currentScreen();
                var scale = currentScaleFactor(screen);
                screenInfo.deviceScaleFactor = (float) scale;
                screenInfo.depth = 32;
                screenInfo.depthPerComponent = 8;
                javafx.geometry.Rectangle2D bounds = screen.getBounds();
                javafx.geometry.Rectangle2D available = screen.getVisualBounds();
                screenInfo.rect = new CefRect(
                        (int) Math.round(bounds.getMinX() * scale),
                        (int) Math.round(bounds.getMinY() * scale),
                        Math.max(1, (int) Math.round(bounds.getWidth() * scale)),
                        Math.max(1, (int) Math.round(bounds.getHeight() * scale)));
                screenInfo.availableRect = new CefRect(
                        (int) Math.round(available.getMinX() * scale),
                        (int) Math.round(available.getMinY() * scale),
                        Math.max(1, (int) Math.round(available.getWidth() * scale)),
                        Math.max(1, (int) Math.round(available.getHeight() * scale)));
                return true;
            }

            @Override
            public boolean getScreenPoint(CefBrowser b, int viewX, int viewY, int[] screenX, int[] screenY) {
                javafx.geometry.Point2D point = localToScreen(viewX, viewY);
                if (point != null) {
                    screenX[0] = (int) Math.round(point.getX());
                    screenY[0] = (int) Math.round(point.getY());
                    return true;
                }
                Rectangle2D fallback = detachedBounds;
                screenX[0] = (int) Math.round(fallback.getMinX() + viewX);
                screenY[0] = (int) Math.round(fallback.getMinY() + viewY);
                return true;
            }

            @Override
            public void onPopupShow(@Nullable CefBrowser browser, boolean show) {
                Platform.runLater(() -> {
                    if (show) {
                        popupSurface.show();
                    } else {
                        popupSurface.hide();
                    }
                });
            }

            @Override
            public void onPopupSize(@Nullable CefBrowser browser, @Nonnull CefRect rect) {
                popupRect = new CefRect(rect.x, rect.y, rect.width, rect.height);
            }

            @Override
            public void onPaint(
                    CefBrowser b,
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
                    java.nio.IntBuffer src =
                            buffer.order(java.nio.ByteOrder.LITTLE_ENDIAN).asIntBuffer();
                    src.get(px, 0, pixelCount);
                    Platform.runLater(() -> popupSurface.blit(px, width, height));
                } else {
                    if (frameBuffer.onPaint(buffer, width, height, dirtyRects) != null) {
                        Platform.runLater(() -> blitFrame(width, height));
                    }
                }
            }
        };
    }

    /** Creates the load handler that injects the custom scrollbar styling. */
    public CefLoadHandler createScrollbarLoadHandler() {
        String scrollbarCss = ScrollbarTheme.generateCss(getScene());
        String scrollbarScript = ScrollbarTheme.injectScript(scrollbarCss);
        return new CefLoadHandler() {
            @Override
            public void onLoadEnd(CefBrowser browser, CefFrame frame, int httpStatusCode) {
                BrowserHandle br = CefWebView.this.browser;
                if (br != null) {
                    br.executeJavaScript(scrollbarScript, "", 0);
                }
            }
        };
    }

    /** Maps a CEF cursor type to the JavaFX cursor shown by this view. */
    public Cursor mapCursor(CefCursorType type) {
        return type.kind().map(CefWebView::cursorForKind).orElse(Cursor.DEFAULT);
    }

    private void maybeCreateBrowser(boolean failIfUnavailable) {
        if (!canCreateBrowserNow()) {
            if (failIfUnavailable) {
                throw new IllegalStateException(
                        "CefWebView must be attached to a showing window before browser creation");
            }
            return;
        }
        if (browser != null || browserCreationPosted || browserCreated) return;
        browserCreationPosted = true;
        try {
            if (activeSetup == null) {
                initialise();
            }
            CefWindowInfo.Mutable windowInfo = new CefWindowInfo.Mutable();
            windowInfo.bounds = new CefRect(0, 0, Math.max(1, (int) getWidth()), Math.max(1, (int) getHeight()));
            windowInfo.windowlessRenderingEnabled = 1;
            CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            // Create without an initial URL so queued load/loadContent actions define the first committed page.
            int result = CefBrowserHost.createBrowser(
                    windowInfo.toImmutable(), client, "", browserSettings.toImmutable(), null, null);
            if (result == 0) {
                throw new IllegalStateException("CEF failed to create windowless browser");
            }
        } catch (RuntimeException e) {
            browserCreationPosted = false;
            if (failIfUnavailable) throw e;
        }
    }

    private CefBrowserHost host() {
        BrowserHandle current = browser;
        return current != null ? current.getHost() : null;
    }

    private void blitFrame(int width, int height) {
        int[] pixels = frameBuffer.consume();
        if (pixels == null) return;

        if (pixelBuffer == null || bufWidth != width || bufHeight != height) {
            bufWidth = width;
            bufHeight = height;
            pixelBuf = IntBuffer.allocate(width * height);
            pixelBuffer = new PixelBuffer<>(width, height, pixelBuf, PixelFormat.getIntArgbPreInstance());
            writableImage = new WritableImage(pixelBuffer);
            imageView.setImage(writableImage);
        }
        System.arraycopy(pixels, 0, pixelBuf.array(), 0, width * height);
        pixelBuffer.updateBuffer(pb -> null);
        var scale = currentScaleFactor(currentScreen());
        int expectedW = (int) Math.max(1.0, Math.round(getWidth() * scale));
        int expectedH = (int) Math.max(1.0, Math.round(getHeight() * scale));
        if (width != expectedW || height != expectedH) {
            requestViewRefresh(true);
        }
    }

    private void onResize() {
        detachedBounds = new Rectangle2D(detachedBounds.getMinX(), detachedBounds.getMinY(), getWidth(), getHeight());
        frameBuffer.resetBackPressure();
        Platform.runLater(() -> engine.fireResized(new Rectangle2D(0, 0, getWidth(), getHeight())));
        requestViewRefresh(true);
    }

    private void onWindowChanged(Window window) {
        if (window != null) {
            attachWindowListeners(window);
            if (window.isShowing()) {
                maybeCreateBrowser(false);
                requestViewRefresh(true);
            }
        }
    }

    private void attachWindowListeners(Window window) {
        window.showingProperty().removeListener(windowShowingListener);
        window.showingProperty().addListener(windowShowingListener);
        window.focusedProperty().removeListener(windowFocusedListener);
        window.focusedProperty().addListener(windowFocusedListener);
        window.xProperty().removeListener(windowBoundsListener);
        window.yProperty().removeListener(windowBoundsListener);
        window.widthProperty().removeListener(windowBoundsListener);
        window.heightProperty().removeListener(windowBoundsListener);
        window.xProperty().addListener(windowBoundsListener);
        window.yProperty().addListener(windowBoundsListener);
        window.widthProperty().addListener(windowBoundsListener);
        window.heightProperty().addListener(windowBoundsListener);
    }

    private void detachWindowListeners(Window window) {
        window.showingProperty().removeListener(windowShowingListener);
        window.focusedProperty().removeListener(windowFocusedListener);
        window.xProperty().removeListener(windowBoundsListener);
        window.yProperty().removeListener(windowBoundsListener);
        window.widthProperty().removeListener(windowBoundsListener);
        window.heightProperty().removeListener(windowBoundsListener);
    }

    private boolean canCreateBrowserNow() {
        if (getScene() == null) return false;
        Window window = getScene().getWindow();
        if (window == null || !window.isShowing()) return false;
        return getWidth() > 0 && getHeight() > 0;
    }

    void applyZoom(double zoomFactor) {
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                double effectiveZoom = Math.max(zoomFactor, 0.01);
                h.setZoomLevel(Math.log(effectiveZoom) / Math.log(1.2));
                h.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
            }
        });
    }

    void requestViewRefresh(boolean screenInfoChanged) {
        frameBuffer.resetBackPressure();
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                if (screenInfoChanged) {
                    h.notifyScreenInfoChanged();
                    h.wasResized();
                }
                h.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
            }
        });
    }

    void updateDetachedBounds(@Nullable CefRect bounds, boolean notify) {
        if (bounds == null) return;
        detachedBounds = new Rectangle2D(bounds.x, bounds.y, Math.max(1, bounds.width), Math.max(1, bounds.height));
        if (notify) {
            Platform.runLater(() ->
                    engine.fireResized(new Rectangle2D(0, 0, Math.max(1, bounds.width), Math.max(1, bounds.height))));
        }
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

    Screen currentScreen() {
        javafx.geometry.Bounds bounds = localToScreen(getBoundsInLocal());
        if (bounds != null) {
            List<Screen> screens = Screen.getScreensForRectangle(
                    bounds.getMinX(),
                    bounds.getMinY(),
                    Math.max(1.0, bounds.getWidth()),
                    Math.max(1.0, bounds.getHeight()));
            if (!screens.isEmpty()) {
                return screens.get(0);
            }
        }
        var scene = getScene();
        Window window = scene != null ? scene.getWindow() : null;
        if (window != null) {
            List<Screen> screens = Screen.getScreensForRectangle(
                    window.getX(), window.getY(), Math.max(1.0, window.getWidth()), Math.max(1.0, window.getHeight()));
            if (!screens.isEmpty()) {
                return screens.get(0);
            }
        }
        return Screen.getPrimary();
    }

    double currentScaleFactor(Screen screen) {
        var scene = getScene();
        var window = scene != null ? scene.getWindow() : null;
        if (window != null) {
            double outputScale = Math.max(window.getOutputScaleX(), window.getOutputScaleY());
            if (outputScale > 0.0) {
                return outputScale;
            }
            double renderScale = Math.max(window.getRenderScaleX(), window.getRenderScaleY());
            if (renderScale > 0.0) {
                return renderScale;
            }
        }
        try {
            return (float) Math.max(screen.getOutputScaleX(), screen.getOutputScaleY());
        } catch (Exception ignored) {
            return 1.0;
        }
    }

    private void handleMouseClick(MouseEvent e, boolean mouseUp) {
        if (!mouseUp) {
            hideContextMenu();
            requestFocus();
        }
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendMouseClickEvent(
                        new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)),
                        CefMouseButtonType.of(cefButton(e)),
                        mouseUp,
                        e.getClickCount());
            }
        });
    }

    private void handleMouseMoved(MouseEvent e) {
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendMouseMoveEvent(new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)), false);
            }
        });
    }

    private void hidePopupOverlay() {
        popupSurface.hide();
    }

    private void handleMouseExited(MouseEvent e) {
        if (popupSurface.containsScreenPoint(e.getScreenX(), e.getScreenY())) return;
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendMouseMoveEvent(new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)), true);
            }
        });
    }

    private void handleScroll(ScrollEvent e) {
        if (e.isControlDown()) {
            double delta = e.getDeltaY() > 0 ? 0.1 : -0.1;
            setZoom(Math.max(0.25, Math.min(5.0, getZoom() + delta)));
            e.consume();
            return;
        }
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendMouseWheelEvent(
                        new CefMouseEvent(
                                (int) e.getX(),
                                (int) e.getY(),
                                baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown())),
                        (int) e.getDeltaX(),
                        (int) e.getDeltaY());
            }
        });
    }

    private void handleKeyPressed(KeyEvent e) {
        if (e.isControlDown() || e.isMetaDown()) {
            if (e.getCode() == KeyCode.EQUALS || e.getCode() == KeyCode.PLUS || e.getCode() == KeyCode.ADD) {
                setZoom(Math.min(5.0, getZoom() + 0.1));
                e.consume();
                return;
            } else if (e.getCode() == KeyCode.MINUS || e.getCode() == KeyCode.SUBTRACT) {
                setZoom(Math.max(0.25, getZoom() - 0.1));
                e.consume();
                return;
            } else if (e.getCode() == KeyCode.DIGIT0 || e.getCode() == KeyCode.NUMPAD0) {
                setZoom(1.0);
                e.consume();
                return;
            }
        }
        int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        int keyCode = e.getCode().getCode();
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h == null) return;
            h.sendKeyEvent(new CefKeyEvent(
                    CefKeyEventType.of(CefKeyEventType.Kind.RAWKEYDOWN),
                    mods,
                    keyCode,
                    keyCode,
                    0,
                    (char) 0,
                    (char) 0,
                    0));
        });
    }

    private void handleKeyTyped(KeyEvent e) {
        String text = e.getCharacter();
        if (text == null || text.isEmpty() || KeyEvent.CHAR_UNDEFINED.equals(text)) return;
        char c = text.charAt(0);
        int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendKeyEvent(new CefKeyEvent(
                        CefKeyEventType.of(CefKeyEventType.Kind.CHAR), mods, (int) c, (int) c, 0, c, c, 0));
            }
        });
    }

    private void handleKeyReleased(KeyEvent e) {
        int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        int keyCode = e.getCode().getCode();
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendKeyEvent(new CefKeyEvent(
                        CefKeyEventType.of(CefKeyEventType.Kind.KEYUP),
                        mods,
                        keyCode,
                        keyCode,
                        0,
                        (char) 0,
                        (char) 0,
                        0));
            }
        });
    }

    private void runWhenBrowserReady(boolean failIfUnavailable, Consumer<BrowserHandle> action) {
        BrowserHandle current = browser;
        if (current != null) {
            action.accept(current);
            return;
        }
        maybeCreateBrowser(failIfUnavailable);
        current = browser;
        if (current != null) {
            action.accept(current);
            return;
        }
        if (!browserCreated) {
            pendingBrowserActions.add(action);
        }
    }

    void runWithBrowserHost(boolean failIfUnavailable, Consumer<CefBrowserHost> action) {
        runWhenBrowserReady(failIfUnavailable, current -> {
            CefBrowserHost host = current.getHost();
            if (host != null) action.accept(host);
        });
    }

    static int cefButton(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) return 0;
        if (e.getButton() == MouseButton.MIDDLE) return 1;
        if (e.getButton() == MouseButton.SECONDARY) return 2;
        return 0;
    }

    static int baseModifiers(boolean shift, boolean ctrl, boolean alt, boolean meta) {
        return CefInputEventFlags.baseModifiers(shift, ctrl, alt, meta);
    }

    static int mouseModifiers(MouseEvent e) {
        return CefInputEventFlags.withMouseButtons(
                baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown()),
                e.isPrimaryButtonDown(),
                e.isMiddleButtonDown(),
                e.isSecondaryButtonDown());
    }

    void hideContextMenu() {
        if (activeContextMenu != null) {
            activeContextMenu.hide();
            activeContextMenu = null;
        }
    }

    void restoreBrowserFocus() {
        Platform.runLater(() -> {
            requestFocus();
            CefBrowserHost h = host();
            if (h != null) h.setFocus(true);
        });
    }

    void onBrowserCreated(CefBrowser browser) {
        BrowserHandle created = new BrowserHandle(browser);
        if (this.browser == null) {
            this.browser = created;
            browserCleanup.browser = created;
        }
        Consumer<BrowserHandle> action;
        while ((action = pendingBrowserActions.poll()) != null) {
            action.accept(this.browser);
        }
        Platform.runLater(() -> {
            requestFocus();
            browser.getHost().ifPresent(h -> h.setFocus(true));
            browserCreated = true;
            browserCreationPosted = false;
            applyZoom(getZoom());
            engine.fireVisibilityChanged(true);
            requestViewRefresh(true);
        });
    }

    boolean handleBeforePopup(@Nonnull CefWindowInfo.Mutable windowInfo, AtomicReference<CefClient> clientRef) {
        javafx.util.Callback<CefPopupFeatures, CefWebEngine> handler = engine.getCreatePopupHandler();
        if (handler == null) return true;
        final AtomicReference<CefWebEngine> popupEngine = new AtomicReference<>();
        runOnFxAndWait(() -> popupEngine.set(handler.call(new CefPopupFeatures(false, false, false, true))));
        CefWebEngine createdEngine = popupEngine.get();
        if (createdEngine == null) return true;
        createdEngine.getView().updateDetachedBounds(windowInfo.bounds, true);
        clientRef.set(createdEngine.getView().getCefClient());
        return false;
    }

    void onBeforeBrowserClose() {
        scriptEngine.dispose();
        Platform.runLater(() -> engine.fireVisibilityChanged(false));
    }

    void runOnFxAndWait(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
            return;
        }
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                runnable.run();
            } finally {
                latch.countDown();
            }
        });
        boolean interrupted = false;
        while (true) {
            try {
                latch.await();
                break;
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
        if (interrupted) Thread.currentThread().interrupt();
    }

    private static final class BrowserCleanupAction implements Runnable {
        volatile BrowserHandle browser;

        @Override
        public void run() {
            BrowserHandle h = browser;
            browser = null;
            if (h != null) h.close(true);
        }
    }

    private static final class BrowserHandle {
        private final CefBrowser browser;

        private BrowserHandle(CefBrowser browser) {
            this.browser = Objects.requireNonNull(browser, "browser");
        }

        private CefBrowser getBrowser() {
            return browser;
        }

        private CefBrowserHost getHost() {
            return browser.getHost().orElse(null);
        }

        private void loadUrl(String url) {
            browser.getMainFrame().ifPresent(frame -> frame.loadUrl(url));
        }

        private void executeJavaScript(String code, String scriptUrl, int startLine) {
            browser.getMainFrame().ifPresent(frame -> frame.executeJavaScript(code, scriptUrl, startLine));
        }

        private void close(boolean force) {
            CefBrowserHost host = getHost();
            if (host != null) {
                host.closeBrowser(force);
            }
        }
    }

    private static final class SetupState {
        private final CefSettings settings;
        private final List<String> extraArgs;

        private SetupState(CefSettings settings, List<String> extraArgs) {
            this.settings = settings;
            this.extraArgs = extraArgs;
        }

        private static SetupState of(CefSettings.Mutable requestedSettings, String... requestedExtraArgs) {
            CefSettings.Mutable settings = (requestedSettings != null ? requestedSettings : new CefSettings.Mutable());
            if (settings.cachePath == null) {
                Path cacheDir = Path.of(System.getProperty("java.io.tmpdir"), "cef4j-jfx-cache");
                try {
                    Files.createDirectories(cacheDir);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
                settings.cachePath = cacheDir.toAbsolutePath().toString();
            }
            settings.windowlessRenderingEnabled = 1;
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 1;
            List<String> args = new ArrayList<>();
            args.add("--disable-popup-blocking");
            if (OS.isLinux()) {
                // JavaFX still renders through X11 here, so force Chromium onto the same platform.
                args.add("--ozone-platform=x11");
            }
            if (requestedExtraArgs != null) {
                for (String requestedExtraArg : requestedExtraArgs) {
                    if (requestedExtraArg == null) continue;
                    String trimmed = requestedExtraArg.trim();
                    if (!trimmed.isEmpty()) {
                        args.add(trimmed);
                    }
                }
            }
            return new SetupState(settings.toImmutable(), List.copyOf(args));
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof SetupState)) return false;
            SetupState other = (SetupState) obj;
            return Objects.equals(settings, other.settings) && Objects.equals(extraArgs, other.extraArgs);
        }

        @Override
        public int hashCode() {
            return Objects.hash(settings, extraArgs);
        }

        @Override
        public String toString() {
            return "SetupState{" + "settings=" + settings + ", extraArgs=" + extraArgs + "}";
        }
    }
}
