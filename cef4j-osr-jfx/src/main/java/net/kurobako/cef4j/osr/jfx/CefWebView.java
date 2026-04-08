package net.kurobako.cef4j.osr.jfx;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
import net.kurobako.cef4j.CefScriptEngine;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefContextMenuHandler;
import net.kurobako.cef4j.gen.CefContextMenuParams;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefDictionaryValue;
import net.kurobako.cef4j.gen.CefDisplayHandler;
import net.kurobako.cef4j.gen.CefErrorCode;
import net.kurobako.cef4j.gen.CefEventFlags;
import net.kurobako.cef4j.gen.CefFocusHandler;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefJsDialogCallback;
import net.kurobako.cef4j.gen.CefJsDialogHandler;
import net.kurobako.cef4j.gen.CefJsDialogType;
import net.kurobako.cef4j.gen.CefKeyEvent;
import net.kurobako.cef4j.gen.CefKeyEventType;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefLoadHandler;
import net.kurobako.cef4j.gen.CefLogSeverity;
import net.kurobako.cef4j.gen.CefMenuItemType;
import net.kurobako.cef4j.gen.CefMenuModel;
import net.kurobako.cef4j.gen.CefMouseButtonType;
import net.kurobako.cef4j.gen.CefMouseEvent;
import net.kurobako.cef4j.gen.CefNavigationEntry;
import net.kurobako.cef4j.gen.CefNavigationEntryVisitor;
import net.kurobako.cef4j.gen.CefPaintElementType;
import net.kurobako.cef4j.gen.CefPoint;
import net.kurobako.cef4j.gen.CefProcessId;
import net.kurobako.cef4j.gen.CefProcessMessage;
import net.kurobako.cef4j.gen.CefQuickMenuEditStateFlags;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRenderHandler;
import net.kurobako.cef4j.gen.CefRunContextMenuCallback;
import net.kurobako.cef4j.gen.CefRunQuickMenuCallback;
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
@SuppressWarnings({"this-escape", "resource"})
public class CefWebView extends Region {
    private static final Logger log = LoggerFactory.getLogger(CefWebView.class);
    private static final Object SETUP_LOCK = new Object();
    private static final Cleaner CLEANER = Cleaner.create();

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
    private final CefScriptEngine scriptEngine = new CefScriptEngine(
            () -> getBrowser() != null ? getBrowser().getMainFrame().orElse(null) : null);
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
    private final ChangeListener<Boolean> windowFocusedListener = (obs, wasFocused, isFocused) -> {
        if (!isFocused) {
            hideOsrPopup();
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
    private final BrowserCloser browserCloser = new BrowserCloser();
    private final Cleaner.Cleanable cleanable;
    private ContextMenu activeContextMenu;
    private IntBuffer pixelBuf;
    private PixelBuffer<IntBuffer> pixelBuffer;
    private WritableImage writableImage;
    private int bufWidth;
    private int bufHeight;
    // OSR popup widget (e.g. <select> dropdown) - shown in a Popup window with event forwarding
    private javafx.stage.Popup osrPopup;
    private ImageView osrPopupImageView;
    private IntBuffer osrPopupPixelBuf;
    private PixelBuffer<IntBuffer> osrPopupPixelBuffer;
    private int osrPopupW;
    private int osrPopupH;
    private volatile CefRect popupRect;
    private volatile boolean browserCreationPosted;
    private volatile boolean browserCreated;
    private volatile Rectangle2D detachedBounds = new Rectangle2D(0, 0, 1, 1);
    private final Queue<BrowserAction> pendingBrowserActions = new ConcurrentLinkedQueue<>();

    public CefWebView() {
        if (activeSetup == null) {
            throw new IllegalStateException("CefWebView.setup() must be called before creating a CefWebView instance");
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

        setOnMousePressed(this::handleMousePressed);
        setOnMouseReleased(this::handleMouseReleased);
        setOnMouseMoved(this::handleMouseMoved);
        setOnMouseDragged(this::handleMouseMoved);
        setOnScroll(this::handleScroll);
        setOnKeyPressed(this::handleKeyPressed);
        setOnKeyReleased(this::handleKeyReleased);
        setOnKeyTyped(this::handleKeyTyped);

        focusedProperty().addListener((obs, was, is) -> {
            if (is) {
                CefBrowserHost h = host();
                if (h != null) h.setFocus(true);
            } else {
                // Don't call setFocus(false) here - it causes CEF to dismiss compositor
                // popup widgets (e.g. <select> dropdowns). Window-level focus loss is
                // handled by windowFocusedListener instead. Just dismiss the overlay.
                hideOsrPopup();
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
        cleanable = CLEANER.register(this, browserCloser);
    }

    public static void setup() {
        setup(new CefSettings.Mutable());
    }

    public static void setup(CefSettings.Mutable settings, String... extraArgs) {
        setup(settings, null, extraArgs);
    }

    /**
     * Initialise CEF for off-screen rendering with a custom {@link CefApp} handler.
     *
     * <p>Must be called before the JavaFX toolkit is started and before creating any {@code CefWebView} instances. Safe
     * to call multiple times with the same settings - subsequent calls are no-ops.
     *
     * <p>If a higher-level library (e.g. {@code CefMonacoPane}) provides its own {@code setup()}, call that instead -
     * it will call this method internally. The most specific setup should be called first; less-specific setups that
     * follow are no-ops since CEF is already initialised.
     *
     * <p>If {@code appHandler} is null, the default handler is used. Pass a custom handler when you need to register
     * custom schemes via {@link CefApp#onRegisterCustomSchemes}. Note: when a non-null handler is provided, the default
     * Windows command-line processing is not applied.
     *
     * @throws IllegalStateException if the JavaFX toolkit is already running
     */
    public static void setup(CefSettings.Mutable settings, CefApp appHandler, String... extraArgs) {
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
            if (activeSetup == null) {
                if (Platform.isFxApplicationThread()
                        || Thread.getAllStackTraces().keySet().stream()
                                .anyMatch(t -> "JavaFX Application Thread".equals(t.getName()))) {
                    throw new IllegalStateException(
                            "CefWebView.setup() must be called before the JavaFX toolkit is started");
                }
                SystemBootstrap.load();
                Cef.INSTANCE.initialise(requested.settings.toMutable(), requested.extraArgs, appHandler);
                activeSetup = requested;
                if (!shutdownHookRegistered) {
                    Runtime.getRuntime().addShutdownHook(new Thread(CefWebView::shutdownCef, "cef4j-jfx-shutdown"));
                    shutdownHookRegistered = true;
                }
            }
        }
    }

    /**
     * Shuts down CEF and releases all native resources.
     *
     * <p>After this call, no {@code CefWebView} instances may be used and {@link #setup} cannot be called again in the
     * same JVM (CEF does not support re-initialisation). If CEF has not been initialised, this method is a no-op.
     *
     * <p>This is equivalent to the work performed by the automatic shutdown hook registered during {@link #setup}, but
     * allows callers to control the timing explicitly - for example, to shut down CEF before other shutdown hooks run.
     */
    public static void shutdown() {
        synchronized (SETUP_LOCK) {
            shutdownCef();
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

    /** Returns the underlying browser instance, or {@code null} if not yet attached or not yet created. */
    public CefBrowser getBrowser() {
        BrowserHandle current = browser;
        return current != null ? current.getBrowser() : null;
    }

    public CefBrowserHost getBrowserHost() {
        BrowserHandle current = browser;
        return current != null ? current.getHost() : null;
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
        hideOsrPopup();
        browser = null;
        browserCloser.handle = null;
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
        Platform.runLater(() -> engine.fireVisibilityChanged(false));
        cleanable.clean();
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
                    Platform.runLater(() -> blitOsrPopup(px, width, height));
                } else {
                    if (frameBuffer.onPaint(buffer, width, height, dirtyRects) != null) {
                        Platform.runLater(() -> blitFrame(width, height));
                    }
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
            getScene();
            if (getScene() != null) {
                getScene().getWindow();
            }
            if (getScene() != null && getScene().getWindow() != null) {
                getScene().getWindow().isShowing();
            }
            getWidth();
            getHeight();
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
                setup();
            }
            CefWindowInfo.Mutable windowInfo = new CefWindowInfo.Mutable();
            windowInfo.bounds = new CefRect(0, 0, Math.max(1, (int) getWidth()), Math.max(1, (int) getHeight()));
            windowInfo.windowlessRenderingEnabled = 1;
            CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            // Always create the browser without an initial URL. The actual navigation
            // comes from the pending load action queued by load()/loadContent(). This
            // avoids a spurious about:blank load whose onLoadEnd fires a premature
            // SUCCEEDED before the real page has loaded, which causes the main frame
            // to be invalid when subsequent evaluate() calls try to use it.
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
        System.arraycopy(pixels, 0, pixelBuf.array(), 0, width * height);
        pixelBuffer.updateBuffer(pb -> null);
        var scale = currentScaleFactor(currentScreen());
        int expectedW = (int) Math.max(1.0, Math.round(getWidth() * scale));
        int expectedH = (int) Math.max(1.0, Math.round(getHeight() * scale));
        if (width != expectedW || height != expectedH) {
            requestViewRefresh(true);
        }
    }

    private void showOsrPopup() {
        hideOsrPopup();
        osrPopupImageView = new ImageView();
        osrPopupImageView.setPreserveRatio(false);
        osrPopupImageView.setSmooth(false);
        osrPopupImageView.setOnMousePressed(e -> forwardPopupMouse(e, false));
        osrPopupImageView.setOnMouseReleased(e -> forwardPopupMouse(e, true));
        osrPopupImageView.setOnMouseMoved(this::forwardPopupMouseMove);
        osrPopupImageView.setOnMouseDragged(this::forwardPopupMouseMove);
        osrPopupImageView.setOnScroll(e -> {
            CefRect rect = popupRect;
            if (rect == null) return;
            runWhenBrowserReady(false, current -> {
                CefBrowserHost h = current.getHost();
                if (h != null) {
                    h.sendMouseWheelEvent(
                            new CefMouseEvent(
                                    (int) e.getX() + rect.x,
                                    (int) e.getY() + rect.y,
                                    baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown())),
                            0,
                            (int) e.getDeltaY());
                }
            });
        });
        osrPopup = new javafx.stage.Popup();
        osrPopup.getContent().add(osrPopupImageView);
        osrPopup.setAutoFix(false);
        osrPopup.setAutoHide(false);
    }

    private void forwardPopupMouse(MouseEvent e, boolean mouseUp) {
        CefRect rect = popupRect;
        if (rect == null) return;
        int viewX = (int) e.getX() + rect.x;
        int viewY = (int) e.getY() + rect.y;
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendMouseClickEvent(
                        new CefMouseEvent(viewX, viewY, mouseModifiers(e)),
                        CefMouseButtonType.of(cefButton(e)),
                        mouseUp,
                        e.getClickCount());
            }
        });
    }

    private void forwardPopupMouseMove(MouseEvent e) {
        CefRect rect = popupRect;
        if (rect == null) return;
        int viewX = (int) e.getX() + rect.x;
        int viewY = (int) e.getY() + rect.y;
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                h.sendMouseMoveEvent(new CefMouseEvent(viewX, viewY, mouseModifiers(e)), false);
            }
        });
    }

    private void hideOsrPopup() {
        if (osrPopup != null) {
            osrPopup.hide();
            osrPopup = null;
        }
        osrPopupImageView = null;
        osrPopupPixelBuf = null;
        osrPopupPixelBuffer = null;
        osrPopupW = 0;
        osrPopupH = 0;
    }

    private void blitOsrPopup(int[] pixels, int width, int height) {
        if (osrPopup == null || osrPopupImageView == null) return;
        CefRect rect = popupRect;
        if (rect == null) return;
        if (osrPopupPixelBuffer == null || osrPopupW != width || osrPopupH != height) {
            osrPopupW = width;
            osrPopupH = height;
            osrPopupPixelBuf = IntBuffer.allocate(width * height);
            osrPopupPixelBuffer =
                    new PixelBuffer<>(width, height, osrPopupPixelBuf, PixelFormat.getIntArgbPreInstance());
            osrPopupImageView.setImage(new WritableImage(osrPopupPixelBuffer));
        }
        System.arraycopy(pixels, 0, osrPopupPixelBuf.array(), 0, width * height);
        osrPopupPixelBuffer.updateBuffer(pb -> null);
        var scale = currentScaleFactor(currentScreen());
        osrPopupImageView.setFitWidth(width / scale);
        osrPopupImageView.setFitHeight(height / scale);
        Point2D screen = localToScreen(rect.x, rect.y);
        if (screen != null && getScene() != null && getScene().getWindow() != null) {
            if (!osrPopup.isShowing()) {
                osrPopup.show(getScene().getWindow(), screen.getX(), screen.getY());
            } else {
                osrPopup.setAnchorX(screen.getX());
                osrPopup.setAnchorY(screen.getY());
            }
        }
    }

    private void onResize() {
        detachedBounds = new Rectangle2D(detachedBounds.getMinX(), detachedBounds.getMinY(), getWidth(), getHeight());
        frameBuffer.resetBackPressure();
        getWidth();
        getHeight();
        currentScaleFactor(currentScreen());
        Platform.runLater(() -> engine.fireResized(new Rectangle2D(0, 0, getWidth(), getHeight())));
        requestViewRefresh(true);
    }

    private void onWindowChanged(Window window) {
        if (window != null) {
            attachWindowListeners(window);
            window.isShowing();
            window.getOutputScaleX();
            window.getOutputScaleY();
            window.getRenderScaleX();
            window.getRenderScaleY();
            window.getWidth();
            window.getHeight();
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

    private void applyZoom(double zoomFactor) {
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
                double effectiveZoom = Math.max(zoomFactor, 0.01);
                h.setZoomLevel(Math.log(effectiveZoom) / Math.log(1.2));
                h.invalidate(CefPaintElementType.of(CefPaintElementType.Kind.VIEW));
            }
        });
    }

    private void requestViewRefresh(boolean screenInfoChanged) {
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

    private void updateDetachedBounds(@Nullable CefRect bounds, boolean notify) {
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

    private double currentScaleFactor(Screen screen) {
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

    private static void shutdownCef() {
        if (Cef.INSTANCE.getState() == Cef.State.INITIALISED) {
            try {
                Cef.INSTANCE.dispose();
            } catch (Throwable ignored) {
            }
        }
    }

    private void handleMousePressed(MouseEvent e) {
        hideContextMenu();
        requestFocus();
        runWhenBrowserReady(false, current -> {
            CefBrowserHost h = current.getHost();
            if (h != null) {
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
                        0,
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
            return;
        }
        if (!browserCreated) {
            pendingBrowserActions.add(action);
        }
    }

    private void flushPendingBrowserActions(BrowserHandle current) {
        BrowserAction action;
        while ((action = pendingBrowserActions.poll()) != null) {
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

    private void hideContextMenu() {
        if (activeContextMenu != null) {
            activeContextMenu.hide();
            activeContextMenu = null;
        }
    }

    private void restoreBrowserFocus() {
        Platform.runLater(() -> {
            requestFocus();
            CefBrowserHost h = host();
            if (h != null) h.setFocus(true);
        });
    }

    private List<MenuItem> buildMenuItems(
            CefMenuModel model,
            CefRunContextMenuCallback callback,
            java.util.concurrent.atomic.AtomicBoolean dispatched) {
        List<MenuItem> items = new ArrayList<>();
        long count = model.getCount();
        for (long i = 0; i < count; i++) {
            int commandId = model.getCommandIdAt(i);
            CefMenuItemType type = model.getType(commandId);
            CefMenuItemType.Kind kind = type.kind().orElse(CefMenuItemType.Kind.NONE);
            switch (kind) {
                case SEPARATOR:
                    items.add(new SeparatorMenuItem());
                    break;
                case SUBMENU:
                    model.getSubMenuAt(i).ifPresent(sub -> {
                        String subLabel = model.getLabel(commandId).orElse("").replace("&", "");
                        Menu menu = new Menu(subLabel);
                        menu.getItems().addAll(buildMenuItems(sub, callback, dispatched));
                        items.add(menu);
                    });
                    break;
                case CHECK: {
                    String label = model.getLabel(commandId).orElse("").replace("&", "");
                    CheckMenuItem ci = new CheckMenuItem(label);
                    ci.setSelected(model.isChecked(commandId));
                    ci.setDisable(!model.isEnabled(commandId));
                    ci.setOnAction(e -> {
                        if (dispatched.compareAndSet(false, true)) {
                            hideContextMenu();
                            callback.cont(commandId, CefEventFlags.of(CefEventFlags.Kind.NONE));
                            restoreBrowserFocus();
                        }
                    });
                    items.add(ci);
                    break;
                }
                default: {
                    String label = model.getLabel(commandId).orElse("").replace("&", "");
                    if (label.isEmpty() && kind == CefMenuItemType.Kind.NONE) break;
                    MenuItem mi = new MenuItem(label);
                    mi.setDisable(!model.isEnabled(commandId));
                    mi.setOnAction(e -> {
                        if (dispatched.compareAndSet(false, true)) {
                            hideContextMenu();
                            callback.cont(commandId, CefEventFlags.of(CefEventFlags.Kind.NONE));
                            restoreBrowserFocus();
                        }
                    });
                    items.add(mi);
                    break;
                }
            }
        }
        return items;
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
                    BrowserHandle created = new BrowserHandle(b);
                    if (browser == null) {
                        browser = created;
                        browserCloser.handle = created;
                    }
                    if (!pendingBrowserActions.isEmpty()) {
                        flushPendingBrowserActions(browser);
                    }
                    Platform.runLater(() -> {
                        requestFocus();
                        b.getHost().ifPresent(h -> h.setFocus(true));
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
                        @Nonnull CefWindowOpenDisposition targetDisposition,
                        boolean userGesture,
                        NativePointer popupFeatures,
                        @Nonnull CefWindowInfo.Mutable windowInfo,
                        AtomicReference<CefClient> clientRef,
                        @Nonnull CefBrowserSettings.Mutable settings,
                        AtomicReference<CefDictionaryValue> extraInfo,
                        int[] noJavascriptAccess) {
                    javafx.util.Callback<CefPopupFeatures, CefWebEngine> handler = engine.getCreatePopupHandler();
                    if (handler == null) return true;
                    final AtomicReference<CefWebEngine> popupEngine = new AtomicReference<>();
                    runOnFxAndWait(
                            () -> popupEngine.set(handler.call(new CefPopupFeatures(false, false, false, true))));
                    CefWebEngine createdEngine = popupEngine.get();
                    if (createdEngine == null) return true;
                    createdEngine.getView().updateDetachedBounds(windowInfo.bounds, true);
                    clientRef.set(createdEngine.getView().getCefClient());
                    return false;
                }

                @Override
                public void onBeforeClose(CefBrowser browser) {
                    scriptEngine.dispose();
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
                        @Nonnull CefErrorCode errorCode,
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
                public boolean onConsoleMessage(
                        CefBrowser b, @Nonnull CefLogSeverity level, String message, String source, int line) {
                    return false;
                }

                @Override
                public void onStatusMessage(CefBrowser b, String value) {
                    Platform.runLater(() -> engine.fireStatusChanged(value != null ? value : ""));
                }

                @Override
                public boolean onAutoResize(CefBrowser browser, @Nonnull CefSize newSize) {
                    Rectangle2D currentBounds = detachedBounds;
                    updateDetachedBounds(
                            new CefRect(
                                    (int) Math.round(currentBounds.getMinX()),
                                    (int) Math.round(currentBounds.getMinY()),
                                    Math.max(1, newSize.width),
                                    Math.max(1, newSize.height)),
                            false);
                    Platform.runLater(() -> engine.fireResized(new Rectangle2D(0, 0, newSize.width, newSize.height)));
                    return false;
                }

                @Override
                public boolean onContentsBoundsChange(CefBrowser browser, @Nonnull CefRect newBounds) {
                    updateDetachedBounds(newBounds, true);
                    requestViewRefresh(true);
                    return true;
                }

                @Override
                public boolean onCursorChange(
                        CefBrowser b, long cursor, @Nonnull CefCursorType type, NativePointer customCursorInfo) {
                    Cursor jfxCursor = mapCursor(type);
                    Platform.runLater(() -> setCursor(jfxCursor));
                    return true;
                }
            });
        }

        @Override
        public java.util.Optional<CefContextMenuHandler> getContextMenuHandler() {
            return java.util.Optional.of(new CefContextMenuHandler() {
                @Override
                public boolean runContextMenu(
                        @Nullable CefBrowser browser,
                        @Nullable CefFrame frame,
                        @Nullable CefContextMenuParams params,
                        @Nullable CefMenuModel model,
                        @Nullable CefRunContextMenuCallback callback) {
                    if (model == null || callback == null) return false;
                    // Extract menu data on CEF thread - model/params are invalid after return
                    java.util.concurrent.atomic.AtomicBoolean dispatched =
                            new java.util.concurrent.atomic.AtomicBoolean();
                    List<MenuItem> items = buildMenuItems(model, callback, dispatched);
                    int menuX = params != null ? params.getXCoord() : 0;
                    int menuY = params != null ? params.getYCoord() : 0;
                    if (items.isEmpty()) {
                        callback.cancel();
                        return true;
                    }
                    Platform.runLater(() -> {
                        hideContextMenu();
                        ContextMenu menu = new ContextMenu(items.toArray(new MenuItem[0]));
                        menu.setOnHidden(e -> {
                            if (dispatched.compareAndSet(false, true)) callback.cancel();
                            if (activeContextMenu == menu) activeContextMenu = null;
                        });
                        activeContextMenu = menu;
                        double screenX = 0, screenY = 0;
                        Point2D pt = localToScreen(menuX, menuY);
                        if (pt != null) {
                            screenX = pt.getX();
                            screenY = pt.getY();
                        }
                        menu.show(CefWebView.this, screenX, screenY);
                    });
                    return true;
                }

                @Override
                public boolean runQuickMenu(
                        @Nullable CefBrowser browser,
                        @Nullable CefFrame frame,
                        @Nonnull CefPoint location,
                        @Nonnull CefSize touchHandleSize,
                        @Nonnull CefQuickMenuEditStateFlags editStateFlags,
                        @Nullable CefRunQuickMenuCallback callback) {
                    if (callback != null) callback.cancel();
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
            if (engine.shouldSuppressNavigationHistory()) {
                Platform.runLater(() -> engine.refreshHistory(List.of(), 0));
                return;
            }
            CefBrowserHost host = browser != null ? browser.getHost().orElse(null) : null;
            if (host == null) return;
            List<CefWebHistory.EntrySnapshot> snapshots = new ArrayList<>();
            final int[] currentIndex = {-1};
            host.getNavigationEntries(
                    new CefNavigationEntryVisitor() {
                        @Override
                        public boolean visit(CefNavigationEntry entry, boolean current, int index, int total) {
                            snapshots.add(new CefWebHistory.EntrySnapshot(
                                    entry != null ? entry.getUrl().orElse("") : "",
                                    entry != null ? entry.getTitle().orElse("") : "",
                                    new Date()));
                            if (current) currentIndex[0] = index;
                            if (index + 1 == total) {
                                Platform.runLater(() -> engine.refreshHistory(snapshots, currentIndex[0]));
                            }
                            return true;
                        }
                    },
                    false);
        }

        @Override
        public boolean onProcessMessageReceived(
                @Nullable CefBrowser browser,
                @Nullable CefFrame frame,
                @Nonnull CefProcessId sourceProcess,
                @Nullable CefProcessMessage message) {
            return scriptEngine.handleMessage(browser, frame, sourceProcess, message);
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

    /**
     * Holds the native browser handle for Cleaner-based auto-close. Must not reference the enclosing {@code CefWebView}
     * instance - otherwise the phantom reference used by {@link Cleaner} would never enqueue.
     */
    private static final class BrowserCloser implements Runnable {
        volatile BrowserHandle handle;

        @Override
        public void run() {
            BrowserHandle h = handle;
            handle = null;
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
                // JFX is Xwayland/X11 only
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
