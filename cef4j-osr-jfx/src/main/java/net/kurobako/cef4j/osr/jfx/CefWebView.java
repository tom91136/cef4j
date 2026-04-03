package net.kurobako.cef4j.osr.jfx;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Window;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDictionaryValue;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefFocusHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefJsDialogCallback;
import net.kurobako.cef4j.gen.CefJsDialogHandler;
import net.kurobako.cef4j.gen.CefJsDialogType;
import net.kurobako.cef4j.gen.CefKeyEvent;
import net.kurobako.cef4j.gen.CefKeyEventType;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefMouseButtonType;
import net.kurobako.cef4j.gen.CefMouseEvent;
import net.kurobako.cef4j.gen.CefNavigationEntry;
import net.kurobako.cef4j.gen.CefNavigationEntryVisitor;
import net.kurobako.cef4j.gen.CefPaintElementType;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefScreenInfo;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefSize;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.gen.CefWindowOpenDisposition;
import net.kurobako.cef4j.gen.NativePointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A JavaFX {@link Region} with a JavaFX {@code WebView}-like surface backed by CEF off-screen rendering.
 *
 * <p>This is source-compatible with the most common JavaFX {@code WebView} usage pattern:
 *
 * <pre>{@code
 * CefWebView webView = new CefWebView();
 * webView.getEngine().load("https://example.com");
 * }</pre>
 *
 * <p>It is not a perfect behavioural clone. Browser lifetime is managed internally, but some semantics remain
 * approximate.
 */
@SuppressWarnings("this-escape")
public class CefWebView extends Region {
    private static final Logger log = LoggerFactory.getLogger(CefWebView.class);
    private static final boolean debug = Boolean.getBoolean("cef4j.jfx.debug");
    private static final Object SETUP_LOCK = new Object();

    private static final int EVENTFLAG_SHIFT_DOWN = 1 << 1;
    private static final int EVENTFLAG_CONTROL_DOWN = 1 << 2;
    private static final int EVENTFLAG_ALT_DOWN = 1 << 3;
    private static final int EVENTFLAG_LEFT_MOUSE_BUTTON = 1 << 4;
    private static final int EVENTFLAG_MIDDLE_MOUSE_BUTTON = 1 << 5;
    private static final int EVENTFLAG_RIGHT_MOUSE_BUTTON = 1 << 6;
    private static final int EVENTFLAG_COMMAND_DOWN = 1 << 7;
    private static volatile SetupState activeSetup;
    private static volatile boolean shutdownHookRegistered;

    private final ImageView imageView = new ImageView();
    private final CefFrameBuffer<int[]> frameBuffer;
    private final CefWebEngine engine = new CefWebEngine(this);
    private final CefClient client = new DefaultClient();
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
    private final DoubleProperty zoom = new SimpleDoubleProperty(this, "zoom", 1.0) {
        @Override
        protected void invalidated() {
            applyZoom(get());
        }
    };
    private volatile BrowserHandle browser;
    private IntBuffer pixelBuf;
    private PixelBuffer<IntBuffer> pixelBuffer;
    private WritableImage writableImage;
    private int bufWidth;
    private int bufHeight;
    private volatile boolean browserCreationPosted;
    private volatile boolean browserCreated;

    public CefWebView() {
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

        setOnMousePressed(this::handleMousePressed);
        setOnMouseReleased(this::handleMouseReleased);
        setOnMouseMoved(this::handleMouseMoved);
        setOnMouseDragged(this::handleMouseMoved);
        setOnScroll(this::handleScroll);
        setOnKeyPressed(this::handleKeyPressed);
        setOnKeyReleased(this::handleKeyReleased);
        setOnKeyTyped(this::handleKeyTyped);

        focusedProperty().addListener((obs, was, is) -> {
            CefBrowserHost h = host();
            if (h != null) h.setFocus(is);
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

        engine.load("about:blank");
    }

    public static void setup() {
        setup(new CefSettings.Mutable());
    }

    public static void setup(CefSettings.Mutable settings, String... extraArgs) {
        SetupState requested = SetupState.of(settings, extraArgs);
        synchronized (SETUP_LOCK) {
            if (activeSetup != null && !activeSetup.equals(requested)) {
                throw new IllegalStateException("CEF can only be configured once per JVM. Existing setup "
                        + activeSetup
                        + " does not match requested setup "
                        + requested
                        + ".");
            }
            if (activeSetup == null) {
                SystemBootstrap.load();
                Cef.INSTANCE.initialise(requested.settings.toMutable(), requested.extraArgs);
                activeSetup = requested;
                if (!shutdownHookRegistered) {
                    Runtime.getRuntime().addShutdownHook(new Thread(CefWebView::shutdownCef, "cef4j-jfx-shutdown"));
                    shutdownHookRegistered = true;
                }
            }
        }
    }

    public CefWebEngine getEngine() {
        return engine;
    }

    public CefClient getCefClient() {
        return client;
    }

    /** Returns the underlying browser instance, or {@code null} if not yet attached or not yet created. */
    public CefBrowser getBrowser() {
        BrowserHandle current = browser;
        return current != null ? current.getBrowser() : null;
    }

    public CefBrowserHost getBrowserHost() {
        BrowserHandle current = browser;
        return current != null ? current.getHost() : null;
    }

    /** Attach an externally-created browser to this view. */
    public void setBrowser(CefBrowser browser) {
        if (browser == null) {
            this.browser = null;
            this.browserCreated = false;
        } else {
            this.browser = new BrowserHandle(browser);
            this.browserCreated = true;
            applyZoom(getZoom());
        }
    }

    /** Returns the zoom factor, matching the JavaFX {@code WebView} API shape. */
    public final double getZoom() {
        return zoom.get();
    }

    public final void setZoom(double value) {
        zoom.set(value);
    }

    public final DoubleProperty zoomProperty() {
        return zoom;
    }

    /** Ensures that browser creation is scheduled on the internally-managed CEF runtime thread. */
    public void createImmediately() {
        maybeCreateBrowser(true);
    }

    public void load(String url) {
        engine.updateLocation(url);
        runWhenBrowserReady(false, current -> {
            current.loadUrl(engine.getLocation());
            requestViewRefresh(false);
        });
    }

    public void reload() {
        runWhenBrowserReady(false, current -> {
            CefBrowser b = current.getBrowser();
            if (b != null) {
                b.reload();
                requestViewRefresh(false);
            }
        });
    }

    public void stop() {
        runWhenBrowserReady(false, current -> {
            CefBrowser b = current.getBrowser();
            if (b != null) {
                b.stopLoad();
                requestViewRefresh(false);
            }
        });
    }

    public void goBack() {
        runWhenBrowserReady(false, current -> {
            CefBrowser b = current.getBrowser();
            if (b != null) {
                b.goBack();
                requestViewRefresh(false);
            }
        });
    }

    public void goForward() {
        runWhenBrowserReady(false, current -> {
            CefBrowser b = current.getBrowser();
            if (b != null) {
                b.goForward();
                requestViewRefresh(false);
            }
        });
    }

    public void executeScript(String script) {
        if (!engine.isJavaScriptEnabled()) return;
        runWhenBrowserReady(
                false, current -> current.executeJavaScript(script == null ? "" : script, engine.getLocation(), 0));
    }

    public void dispose() {
        BrowserHandle current = browser;
        browser = null;
        browserCreated = false;
        browserCreationPosted = false;
        imageView.setImage(null);
        pixelBuf = null;
        pixelBuffer = null;
        writableImage = null;
        javafx.scene.Scene scene = getScene();
        if (scene != null) {
            scene.windowProperty().removeListener(sceneWindowListener);
            Window window = scene.getWindow();
            if (window != null) detachWindowListeners(window);
        }
        Platform.runLater(() -> engine.fireVisibilityChanged(false));
        if (current != null) {
            current.close(true);
        }
    }

    @Override
    protected void layoutChildren() {
        imageView.setFitWidth(getWidth());
        imageView.setFitHeight(getHeight());
    }

    public CefRenderHandler createRenderHandler() {
        return new CefRenderHandler() {
            @Override
            public boolean getRootScreenRect(CefBrowser b, @Nonnull CefRect.Mutable rect) {
                javafx.geometry.Bounds bounds = localToScreen(getBoundsInLocal());
                if (bounds == null) {
                    return false;
                }
                rect.x = (int) Math.round(bounds.getMinX());
                rect.y = (int) Math.round(bounds.getMinY());
                rect.width = Math.max(1, (int) Math.round(bounds.getWidth()));
                rect.height = Math.max(1, (int) Math.round(bounds.getHeight()));
                debug("getRootScreenRect dip={}x{} at {},{}", rect.width, rect.height, rect.x, rect.y);
                return true;
            }

            @Override
            public void getViewRect(CefBrowser b, @Nonnull CefRect.Mutable rect) {
                rect.x = 0;
                rect.y = 0;
                rect.width = Math.max(1, (int) getWidth());
                rect.height = Math.max(1, (int) getHeight());
                debug("getViewRect dip={}x{}", rect.width, rect.height);
            }

            @Override
            public boolean getScreenInfo(CefBrowser b, @Nonnull CefScreenInfo.Mutable screenInfo) {
                Screen screen = currentScreen();
                float scale = currentScaleFactor(screen);
                screenInfo.deviceScaleFactor = scale;
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
                debug(
                        "getScreenInfo scale={} screenBounds={}x{} visualBounds={}x{} screenRect={}x{} availableRect={}x{}",
                        scale,
                        bounds.getWidth(),
                        bounds.getHeight(),
                        available.getWidth(),
                        available.getHeight(),
                        screenInfo.rect.width,
                        screenInfo.rect.height,
                        screenInfo.availableRect.width,
                        screenInfo.availableRect.height);
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
                float scale = currentScaleFactor(currentScreen());
                screenX[0] = Math.round(viewX * scale);
                screenY[0] = Math.round(viewY * scale);
                return true;
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
                if (frameBuffer.onPaint(buffer, width, height, dirtyRects) != null) {
                    Platform.runLater(() -> blitFrame(width, height));
                }
            }
        };
    }

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

    /** Maps a CEF cursor type to a JavaFX {@link Cursor}. Override to customize. */
    public Cursor mapCursor(CefCursorType type) {
        return type.kind().map(CefWebView::cursorForKind).orElse(Cursor.DEFAULT);
    }

    private void maybeCreateBrowser(boolean failIfUnavailable) {
        if (!canCreateBrowserNow()) {
            debug(
                    "skip create failIfUnavailable={} scenePresent={} windowPresent={} windowShowing={} size={}x{}",
                    failIfUnavailable,
                    getScene() != null,
                    getScene() != null && getScene().getWindow() != null,
                    getScene() != null
                            && getScene().getWindow() != null
                            && getScene().getWindow().isShowing(),
                    getWidth(),
                    getHeight());
            if (failIfUnavailable) {
                throw new IllegalStateException(
                        "CefWebView must be attached to a showing window before browser creation");
            }
            return;
        }
        if (browser != null || browserCreationPosted || browserCreated) return;
        browserCreationPosted = true;
        debug("creating browser location={}", engine.getLocation());
        try {
            ensureConfigured();
            CefWindowInfo.Mutable windowInfo = new CefWindowInfo.Mutable();
            windowInfo.bounds = new CefRect(0, 0, Math.max(1, (int) getWidth()), Math.max(1, (int) getHeight()));
            windowInfo.windowlessRenderingEnabled = 1;
            CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            int result = CefBrowserHost.createBrowser(
                    windowInfo.toImmutable(), client, engine.getLocation(), browserSettings.toImmutable(), null, null);
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

    private void ensureBuffer(int w, int h) {
        if (pixelBuffer != null && bufWidth == w && bufHeight == h) return;
        bufWidth = w;
        bufHeight = h;
        debug("ensureBuffer {}x{}", w, h);
        pixelBuf = IntBuffer.allocate(w * h);
        pixelBuffer = new PixelBuffer<>(w, h, pixelBuf, PixelFormat.getIntArgbPreInstance());
        writableImage = new WritableImage(pixelBuffer);
        imageView.setImage(writableImage);
    }

    private void blitFrame(int width, int height) {
        int[] pixels = frameBuffer.consume();
        if (pixels == null) return;

        ensureBuffer(width, height);
        System.arraycopy(pixels, 0, pixelBuf.array(), 0, width * height);
        pixelBuffer.updateBuffer(pb -> null);
    }

    private void onResize() {
        frameBuffer.resetBackPressure();
        debug("onResize logical={}x{} scale={}", getWidth(), getHeight(), currentScaleFactor(currentScreen()));
        Platform.runLater(() -> engine.fireResized(new Rectangle2D(0, 0, getWidth(), getHeight())));
        requestViewRefresh(true);
    }

    private void onWindowChanged(Window window) {
        if (window != null) {
            attachWindowListeners(window);
            debug(
                    "windowChanged showing={} outputScale={}x{} renderScale={}x{} size={}x{}",
                    window.isShowing(),
                    window.getOutputScaleX(),
                    window.getOutputScaleY(),
                    window.getRenderScaleX(),
                    window.getRenderScaleY(),
                    window.getWidth(),
                    window.getHeight());
            if (window.isShowing()) {
                maybeCreateBrowser(false);
                requestViewRefresh(true);
            }
        }
    }

    private void attachWindowListeners(Window window) {
        window.showingProperty().removeListener(windowShowingListener);
        window.showingProperty().addListener(windowShowingListener);
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

    private void applyZoom(double zoomFactor) {
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                double effectiveZoom = Math.max(zoomFactor, 0.01);
                debug("applyZoom requested={} effective={}", zoomFactor, effectiveZoom);
                h.setZoomLevel(Math.log(effectiveZoom) / Math.log(1.2));
                h.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
            }
        });
    }

    private void requestViewRefresh(boolean screenInfoChanged) {
        frameBuffer.resetBackPressure();
        debug("requestViewRefresh screenInfoChanged={}", screenInfoChanged);
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                if (screenInfoChanged) {
                    debug("notifyScreenInfoChanged + wasResized");
                    h.notifyScreenInfoChanged();
                    h.wasResized();
                }
                debug("invalidate view");
                h.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
            }
        });
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

    private Screen currentScreen() {
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
        javafx.scene.Scene scene = getScene();
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

    private float currentScaleFactor(Screen screen) {
        javafx.scene.Scene scene = getScene();
        Window window = scene != null ? scene.getWindow() : null;
        if (window != null) {
            double outputScale = Math.max(window.getOutputScaleX(), window.getOutputScaleY());
            if (outputScale > 0.0) {
                debug("scale from window outputScale={}", outputScale);
                return (float) outputScale;
            }
            double renderScale = Math.max(window.getRenderScaleX(), window.getRenderScaleY());
            if (renderScale > 0.0) {
                debug("scale from window renderScale={}", renderScale);
                return (float) renderScale;
            }
        }
        float scale = getDeviceScaleFactor(screen);
        debug("scale from screen outputScale={}", scale);
        return scale;
    }

    private static float getDeviceScaleFactor(Screen screen) {
        try {
            return (float) Math.max(screen.getOutputScaleX(), screen.getOutputScaleY());
        } catch (Exception e) {
            return 1.0f;
        }
    }

    private static void debug(String message, Object... args) {
        if (debug) {
            log.info("[cef4j-jfx] " + message, args);
        }
    }

    private static void ensureConfigured() {
        if (activeSetup == null) {
            setup();
        }
    }

    private static void shutdownCef() {
        if (Cef.INSTANCE.getState() == Cef.State.INITIALISED) {
            try {
                Cef.INSTANCE.dispose();
            } catch (Throwable ignored) {
            }
        }
    }

    private void handleMousePressed(MouseEvent e) {
        requestFocus();
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.setFocus(true);
                h.sendMouseClickEvent(
                        new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)),
                        CefMouseButtonType.of(cefButton(e)),
                        false,
                        e.getClickCount());
            }
        });
    }

    private void handleMouseReleased(MouseEvent e) {
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendMouseClickEvent(
                        new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)),
                        CefMouseButtonType.of(cefButton(e)),
                        true,
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

    private void handleScroll(ScrollEvent e) {
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendMouseWheelEvent(
                        new CefMouseEvent(
                                (int) e.getX(),
                                (int) e.getY(),
                                baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown())),
                        0,
                        (int) e.getDeltaY());
            }
        });
    }

    private void handleKeyPressed(KeyEvent e) {
        int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        int keyCode = mapKeyCode(e.getCode());
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
        int keyCode = mapKeyCode(e.getCode());
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

    private void runWhenBrowserReady(boolean failIfUnavailable, BrowserAction action) {
        BrowserHandle current = browser;
        if (current != null) {
            action.run(current);
            return;
        }
        maybeCreateBrowser(failIfUnavailable);
        current = browser;
        if (current != null) {
            action.run(current);
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

    private static int mapKeyCode(KeyCode code) {
        return code.getCode();
    }

    private final class DefaultClient implements CefClient {
        private final CefRenderHandler renderHandler = createRenderHandler();
        private final CefLoadHandler scrollbarLoadHandler = createScrollbarLoadHandler();

        @Override
        public java.util.Optional<CefRenderHandler> getRenderHandler() {
            return java.util.Optional.of(renderHandler);
        }

        @Override
        public java.util.Optional<CefFocusHandler> getFocusHandler() {
            return java.util.Optional.of(new CefFocusHandler() {
                @Override
                public void onGotFocus(@Nullable CefBrowser browser) {
                    Platform.runLater(() -> {
                        if (!isFocused()) requestFocus();
                    });
                }

                @Override
                public void onTakeFocus(@Nullable CefBrowser browser, boolean next) {
                    // Browser is releasing focus - allow JavaFX to move to next/previous control
                }
            });
        }

        @Override
        public java.util.Optional<CefLifeSpanHandler> getLifeSpanHandler() {
            return java.util.Optional.of(new CefLifeSpanHandler() {
                @Override
                public void onAfterCreated(CefBrowser b) {
                    Platform.runLater(() -> {
                        if (browser == null) {
                            browser = new BrowserHandle(b);
                        }
                        requestFocus();
                        CefBrowserHost h = b.getHost().orElse(null);
                        if (h != null) h.setFocus(true);
                        browserCreated = true;
                        browserCreationPosted = false;
                        applyZoom(getZoom());
                        engine.fireVisibilityChanged(true);
                        requestViewRefresh(true);
                    });
                    refreshHistoryFromBrowser(b);
                }

                @Override
                public boolean onBeforePopup(
                        CefBrowser browser,
                        CefFrame frame,
                        int popupId,
                        String targetUrl,
                        String targetFrameName,
                        CefWindowOpenDisposition targetDisposition,
                        boolean userGesture,
                        NativePointer popupFeatures,
                        CefWindowInfo.Mutable windowInfo,
                        AtomicReference<CefClient> clientRef,
                        CefBrowserSettings.Mutable settings,
                        AtomicReference<CefDictionaryValue> extraInfo,
                        int[] noJavascriptAccess) {
                    javafx.util.Callback<CefPopupFeatures, CefWebEngine> handler = engine.getCreatePopupHandler();
                    if (handler == null) return true;
                    final AtomicReference<CefWebEngine> popupEngine = new AtomicReference<>();
                    runOnFxAndWait(
                            () -> popupEngine.set(handler.call(new CefPopupFeatures(false, false, false, true))));
                    CefWebEngine createdEngine = popupEngine.get();
                    if (createdEngine == null) return true;
                    clientRef.set(createdEngine.getView().getCefClient());
                    return false;
                }

                @Override
                public void onBeforeClose(CefBrowser browser) {
                    Platform.runLater(() -> engine.fireVisibilityChanged(false));
                }
            });
        }

        @Override
        public java.util.Optional<CefLoadHandler> getLoadHandler() {
            return java.util.Optional.of(new CefLoadHandler() {
                @Override
                public void onLoadingStateChange(
                        CefBrowser b, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                    Platform.runLater(() -> engine.updateLoadState(isLoading, canGoBack, canGoForward));
                    refreshHistoryFromBrowser(b);
                }

                @Override
                public void onLoadEnd(CefBrowser browser, CefFrame frame, int httpStatusCode) {
                    scrollbarLoadHandler.onLoadEnd(browser, frame, httpStatusCode);
                    Platform.runLater(() -> {
                        engine.markLoadFinished();
                        requestViewRefresh(false);
                    });
                    refreshHistoryFromBrowser(browser);
                }

                @Override
                public void onLoadError(
                        CefBrowser browser,
                        CefFrame frame,
                        net.kurobako.cef4j.gen.CefErrorCode errorCode,
                        String errorText,
                        String failedUrl) {
                    Platform.runLater(() -> engine.markLoadFailed(new RuntimeException(errorText)));
                }
            });
        }

        @Override
        public java.util.Optional<CefDisplayHandler> getDisplayHandler() {
            return java.util.Optional.of(new CefDisplayHandler() {
                @Override
                public void onTitleChange(CefBrowser b, String title) {
                    Platform.runLater(() -> engine.updateTitle(title));
                }

                @Override
                public void onAddressChange(CefBrowser b, CefFrame f, String url) {
                    Platform.runLater(() -> engine.updateLocation(url));
                }

                @Override
                public void onLoadingProgressChange(CefBrowser b, double progress) {
                    Platform.runLater(() -> engine.updateLoadProgress(progress));
                }

                @Override
                public void onStatusMessage(CefBrowser b, String value) {
                    Platform.runLater(() -> engine.fireStatusChanged(value != null ? value : ""));
                }

                @Override
                public boolean onAutoResize(CefBrowser browser, CefSize newSize) {
                    Platform.runLater(() -> engine.fireResized(new Rectangle2D(0, 0, newSize.width, newSize.height)));
                    return false;
                }

                @Override
                public boolean onCursorChange(
                        CefBrowser b, long cursor, CefCursorType type, NativePointer customCursorInfo) {
                    Cursor jfxCursor = mapCursor(type);
                    Platform.runLater(() -> setCursor(jfxCursor));
                    return true;
                }
            });
        }

        @Override
        public java.util.Optional<CefJsDialogHandler> getJsDialogHandler() {
            return java.util.Optional.of(new CefJsDialogHandler() {
                @Override
                public boolean onJsDialog(
                        @Nullable CefBrowser browser,
                        @Nullable String originUrl,
                        @Nonnull CefJsDialogType dialogType,
                        @Nullable String messageText,
                        @Nullable String defaultPromptText,
                        @Nullable CefJsDialogCallback callback,
                        @Nullable int[] suppressMessage) {
                    CefJsDialogType.Kind kind = dialogType.kind().orElse(CefJsDialogType.Kind.ALERT);
                    switch (kind) {
                        case ALERT:
                            Platform.runLater(() -> engine.fireAlert(messageText != null ? messageText : ""));
                            if (callback != null) callback.cont(1, null);
                            return true;
                        case CONFIRM:
                            javafx.util.Callback<String, Boolean> confirm = engine.getConfirmHandler();
                            if (confirm == null) return false;
                            AtomicReference<Boolean> confirmResult = new AtomicReference<>(Boolean.FALSE);
                            runOnFxAndWait(
                                    () -> confirmResult.set(confirm.call(messageText != null ? messageText : "")));
                            if (callback != null) callback.cont(Boolean.TRUE.equals(confirmResult.get()) ? 1 : 0, null);
                            return true;
                        case PROMPT:
                            javafx.util.Callback<CefPromptData, String> prompt = engine.getPromptHandler();
                            if (prompt == null) return false;
                            AtomicReference<String> promptResult = new AtomicReference<>();
                            runOnFxAndWait(() -> promptResult.set(prompt.call(new CefPromptData(
                                    messageText != null ? messageText : "",
                                    defaultPromptText != null ? defaultPromptText : ""))));
                            if (callback != null) {
                                callback.cont(promptResult.get() != null ? 1 : 0, promptResult.get());
                            }
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public boolean onBeforeUnloadDialog(
                        CefBrowser browser, String messageText, boolean isReload, CefJsDialogCallback callback) {
                    javafx.util.Callback<String, Boolean> confirm = engine.getConfirmHandler();
                    if (confirm == null) return false;
                    AtomicReference<Boolean> confirmResult = new AtomicReference<>(Boolean.FALSE);
                    runOnFxAndWait(() -> confirmResult.set(confirm.call(messageText != null ? messageText : "")));
                    if (callback != null) callback.cont(Boolean.TRUE.equals(confirmResult.get()) ? 1 : 0, null);
                    return true;
                }
            });
        }

        private void refreshHistoryFromBrowser(CefBrowser browser) {
            CefBrowserHost host = browser != null ? browser.getHost().orElse(null) : null;
            if (host == null) return;
            List<CefWebHistory.EntrySnapshot> snapshots = new ArrayList<>();
            final int[] currentIndex = {-1};
            host.getNavigationEntries(
                    new CefNavigationEntryVisitor() {
                        @Override
                        public int visit(CefNavigationEntry entry, int current, int index, int total) {
                            snapshots.add(new CefWebHistory.EntrySnapshot(
                                    entry != null ? entry.getUrl().orElse("") : "",
                                    entry != null ? entry.getTitle().orElse("") : "",
                                    new Date()));
                            if (current != 0) currentIndex[0] = index;
                            if (index + 1 == total) {
                                Platform.runLater(() -> engine.refreshHistory(snapshots, currentIndex[0]));
                            }
                            return 1;
                        }
                    },
                    false);
        }

        private void runOnFxAndWait(Runnable runnable) {
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
    }

    @FunctionalInterface
    private interface BrowserAction {
        void run(BrowserHandle browser);
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
            CefSettings.Mutable settings = (requestedSettings != null ? requestedSettings : new CefSettings.Mutable())
                    .toImmutable()
                    .toMutable();
            settings.noSandbox = 1;
            settings.windowlessRenderingEnabled = 1;
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 1;
            List<String> args = new ArrayList<>();
            if (OS.isLinux()) {
                args.add("--ozone-platform=x11");
                args.add("--no-zygote");
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
