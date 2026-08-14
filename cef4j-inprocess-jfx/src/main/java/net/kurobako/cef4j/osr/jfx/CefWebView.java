package net.kurobako.cef4j.osr.jfx;

import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
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
    private static final Cleaner CLEANER = Cleaner.create();
    private static final Object INITIALISE_LOCK = new Object();
    private static final CefKeyEventType KEY_RAWKEYDOWN = CefKeyEventType.of(CefKeyEventType.Kind.RAWKEYDOWN);
    private static final CefKeyEventType KEY_CHAR = CefKeyEventType.of(CefKeyEventType.Kind.CHAR);
    private static final CefKeyEventType KEY_KEYUP = CefKeyEventType.of(CefKeyEventType.Kind.KEYUP);
    private static final CefPaintElementType PAINT_VIEW = CefPaintElementType.of(CefPaintElementType.Kind.VIEW);

    private final ImageView imageView = new ImageView();
    private final CefFrameBuffer<int[]> frameBuffer;
    final CefWebEngine engine = new CefWebEngine(this);
    final CefScriptEngine scriptEngine = new CefScriptEngine(() -> {
        CefBrowser b = getBrowser();
        return b != null ? b.getMainFrame().orElse(null) : null;
    });
    private final CefClient client = new CefWebViewClient(this);
    private final CompletableFuture<Void> browserClosed = new CompletableFuture<>();
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
            ifHostPresent(h -> h.setFocus(false));
        }
    };
    private final DoubleProperty zoom = new SimpleDoubleProperty(this, "zoom", 1.0) {
        @Override
        protected void invalidated() {
            applyZoom(get());
        }
    };

    @Nullable
    private volatile BrowserHandle browser;

    private volatile boolean releaseRequested;

    private final BrowserCleanupAction browserCleanup = new BrowserCleanupAction();
    private final Cleaner.Cleanable cleanable;

    @Nullable
    ContextMenu activeContextMenu;

    @Nullable
    private IntBuffer pixelBuf;

    @Nullable
    private PixelBuffer<IntBuffer> pixelBuffer;

    private int bufWidth;
    private int bufHeight;
    private final CefWebViewPopupSurface popupSurface = new CefWebViewPopupSurface(this);

    @Nullable
    volatile CefRect popupRect;

    private volatile boolean browserCreationPosted;
    private volatile boolean browserCreated;
    volatile Rectangle2D detachedBounds = new Rectangle2D(0, 0, 1, 1);
    private final Queue<Consumer<BrowserHandle>> pendingBrowserActions = new ConcurrentLinkedQueue<>();

    /** Number of view (non-popup) paints delivered to this view; package-private for tests. */
    final java.util.concurrent.atomic.LongAdder framesPainted = new java.util.concurrent.atomic.LongAdder();

    /**
     * Initialise CEF for off-screen rendering. Must be called before
     * {@link javafx.application.Application#launch(Class, String...)}, typically from the {@code main} thread before
     * JavaFX is initialised.
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
            // Note: we intentionally do NOT call Platform.isFxApplicationThread() here.
            // On macOS without -XstartOnFirstThread, that call triggers JavaFX/Glass initialisation
            // which blocks until [NSApp run] is running on Thread 0.  CEF must initialise first.
            if (Cef.INSTANCE.state() == Cef.State.INITIALISED) {
                // Already up - just validate it's configured for OSR.
                requireOsrInitialised();
                return;
            }
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

    /** Terminate CEF. See {@link Cef#terminate()}. */
    public static void terminate() {
        Cef.INSTANCE.terminate();
    }

    private static void requireOsrInitialised() {
        Cef.State state = Cef.INSTANCE.state();
        if (state != Cef.State.INITIALISED) {
            throw new IllegalStateException(
                    "CEF must be initialised for off-screen rendering before creating a CefWebView.\n"
                            + "Add this before Application.launch():\n\n"
                            + "    Cef.LaunchArgs launch = Cef.osrLaunchArgs();\n"
                            + "    Cef.INSTANCE.initialise(launch.settings(), launch.args());\n");
        }
        Cef.INSTANCE
                .activeSettings()
                .filter(s -> s.windowlessRenderingEnabled == 0)
                .ifPresent(s -> {
                    throw new IllegalStateException(
                            "CEF was initialised without windowlessRenderingEnabled=1. CefWebView requires OSR mode.\n"
                                    + "Use Cef.osrLaunchArgs() when calling Cef.INSTANCE.initialise().\n");
                });
    }

    public CefWebView() {
        requireOsrInitialised();
        int maxW = 1;
        int maxH = 1;
        for (Screen screen : Screen.getScreens()) {
            Rectangle2D bounds = screen.getBounds();
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
        addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);
        addEventFilter(KeyEvent.KEY_TYPED, this::handleKeyTyped);

        focusedProperty().addListener((obs, was, is) -> {
            if (is) {
                ifHostPresent(h -> h.setFocus(true));
            } else {
                // Let window focus loss drive host focus changes; doing it here closes compositor popups too early.
                popupSurface.hide();
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
    @Nullable
    public CefBrowser getBrowser() {
        BrowserHandle current = browser;
        return current != null ? current.getBrowser() : null;
    }

    /** Returns the underlying browser host, or {@code null} if it does not exist yet. */
    @Nullable
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

    /** Fluent API; JavaFX bean accessors remain available for property and binding compatibility. */
    public CefWebEngine engine() {
        return getEngine();
    }

    public CefClient cefClient() {
        return getCefClient();
    }

    public CefScriptEngine scriptEngine() {
        return getScriptEngine();
    }

    @Nullable
    public CefBrowser browser() {
        return getBrowser();
    }

    @Nullable
    public CefBrowserHost browserHost() {
        return getBrowserHost();
    }

    public final double zoom() {
        return getZoom();
    }

    public final void zoom(double value) {
        setZoom(value);
    }

    public final DoubleProperty zoomProperty() {
        return zoom;
    }

    /** Navigates to the given URL. */
    public void load(String url) {
        engine.updateLocation(url);
        runWithBrowser(false, current -> {
            // loadUrl replaces any in-flight navigation itself. Calling stopLoad first can race the
            // first renderer commit on older CEF releases and leave the replacement queued forever.
            current.getMainFrame().ifPresent(frame -> frame.loadUrl(engine.getLocation()));
            requestViewRefresh(false);
        });
    }

    /** Reloads the current page if the browser exists. */
    public void reload() {
        runWithBrowser(false, current -> {
            current.reload();
            requestViewRefresh(false);
        });
    }

    /** Stops the current load if the browser exists. */
    public void stop() {
        runWithBrowser(false, current -> {
            current.stopLoad();
            requestViewRefresh(false);
        });
    }

    /** Navigates back if the browser exists. */
    public void goBack() {
        runWithBrowser(false, current -> {
            current.goBack();
            requestViewRefresh(false);
        });
    }

    /** Navigates forward if the browser exists. */
    public void goForward() {
        runWithBrowser(false, current -> {
            current.goForward();
            requestViewRefresh(false);
        });
    }

    /** Executes JavaScript in the main frame if JavaScript is enabled. */
    public void executeScript(String script) {
        if (!engine.isJavaScriptEnabled()) return;
        runWithBrowser(
                false,
                current -> current.getMainFrame()
                        .ifPresent(frame -> frame.executeJavaScript(
                                Objects.requireNonNullElse(script, ""), engine.getLocation(), 0)));
    }

    /** Releases this view's native browser and associated resources. */
    @SuppressWarnings("FutureReturnValueIgnored")
    public void release() {
        releaseAsync();
    }

    /**
     * Releases this view and completes after CEF confirms that its browser has closed.
     *
     * <p>The returned future is useful when an application must establish a clean browser-lifecycle boundary before
     * calling {@link #terminate()}.
     */
    public CompletableFuture<Void> releaseAsync() {
        boolean creationPending = browserCreationPosted;
        releaseRequested = true;
        popupSurface.hide();
        BrowserHandle h = browser;
        browser = null;
        browserCleanup.browser = null;
        browserCreated = false;
        browserCreationPosted = false;
        imageView.setImage(null);
        pixelBuf = null;
        pixelBuffer = null;
        var scene = getScene();
        if (scene != null) {
            scene.windowProperty().removeListener(sceneWindowListener);
            Window window = scene.getWindow();
            if (window != null) detachWindowListeners(window);
        }
        scriptEngine.dispose();
        if (h != null) {
            h.close(true);
        } else if (!creationPending) {
            browserClosed.complete(null);
        }
        Platform.runLater(() -> engine.fireVisibilityChanged(false));
        cleanable.clean();
        return browserClosed;
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
            public boolean getRootScreenRect(@Nullable CefBrowser b, @Nonnull CefRect.Mutable rect) {
                Bounds bounds = localToScreen(getBoundsInLocal());
                Rectangle2D resolved = bounds != null
                        ? new Rectangle2D(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight())
                        : detachedBounds;
                rect.x = (int) Math.round(resolved.getMinX());
                rect.y = (int) Math.round(resolved.getMinY());
                rect.width = Math.max(1, (int) Math.round(resolved.getWidth()));
                rect.height = Math.max(1, (int) Math.round(resolved.getHeight()));
                return true;
            }

            @Override
            public void getViewRect(@Nullable CefBrowser b, @Nonnull CefRect.Mutable rect) {
                Rectangle2D bounds = detachedBounds;
                rect.x = 0;
                rect.y = 0;
                rect.width = Math.max(1, (int) Math.round(getWidth() > 0 ? getWidth() : bounds.getWidth()));
                rect.height = Math.max(1, (int) Math.round(getHeight() > 0 ? getHeight() : bounds.getHeight()));
            }

            @Override
            public boolean getScreenInfo(@Nullable CefBrowser b, @Nonnull CefScreenInfo.Mutable screenInfo) {
                Screen screen = currentScreen();
                var scale = currentScaleFactor(screen);
                screenInfo.deviceScaleFactor = (float) scale;
                screenInfo.depth = 32;
                screenInfo.depthPerComponent = 8;
                Rectangle2D bounds = screen.getBounds();
                Rectangle2D available = screen.getVisualBounds();
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
            public boolean getScreenPoint(@Nullable CefBrowser b, int viewX, int viewY, int[] screenX, int[] screenY) {
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
                    java.nio.IntBuffer src =
                            buffer.order(java.nio.ByteOrder.LITTLE_ENDIAN).asIntBuffer();
                    src.get(px, 0, pixelCount);
                    Platform.runLater(() -> popupSurface.blit(px, width, height));
                } else {
                    if (frameBuffer.onPaint(buffer, width, height, dirtyRects) != null) {
                        framesPainted.increment();
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
            public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpStatusCode) {
                BrowserHandle br = CefWebView.this.browser;
                if (br != null) {
                    br.getBrowser()
                            .getMainFrame()
                            .ifPresent(mainFrame -> mainFrame.executeJavaScript(scrollbarScript, "", 0));
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
            CefWindowInfo windowInfo = Cef.createWindowlessInfo(
                    new CefRect(0, 0, Math.max(1, (int) getWidth()), Math.max(1, (int) getHeight())));
            CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            // Create without an initial URL so queued load/loadContent actions define the first committed page.
            int result =
                    CefBrowserHost.createBrowser(windowInfo, client, "", browserSettings.toImmutable(), null, null);
            if (result == 0) {
                throw new IllegalStateException("CEF failed to create windowless browser");
            }
        } catch (RuntimeException e) {
            browserCreationPosted = false;
            if (failIfUnavailable) throw e;
        }
    }

    @Nullable
    private CefBrowserHost host() {
        BrowserHandle current = browser;
        return current != null ? current.getHost() : null;
    }

    private void ifHostPresent(Consumer<CefBrowserHost> action) {
        CefBrowserHost h = host();
        if (h != null) action.accept(h);
    }

    private void blitFrame(int width, int height) {
        int[] pixels = frameBuffer.consume();
        if (pixels == null) return;

        IntBuffer buf = pixelBuf;
        PixelBuffer<IntBuffer> pb = pixelBuffer;
        if (pb == null || buf == null || bufWidth != width || bufHeight != height) {
            bufWidth = width;
            bufHeight = height;
            buf = IntBuffer.allocate(width * height);
            pb = new PixelBuffer<>(width, height, buf, PixelFormat.getIntArgbPreInstance());
            pixelBuf = buf;
            pixelBuffer = pb;
            imageView.setImage(new WritableImage(pb));
        }
        System.arraycopy(pixels, 0, buf.array(), 0, width * height);
        pb.updateBuffer(ignored -> null);
        var scale = currentScaleFactor(currentScreen());
        int expectedW = (int) Math.max(1, Math.round(getWidth() * scale));
        int expectedH = (int) Math.max(1, Math.round(getHeight() * scale));
        if (width != expectedW || height != expectedH) {
            requestViewRefresh(true);
        }
    }

    private void onResize() {
        Rectangle2D current = detachedBounds;
        detachedBounds = new Rectangle2D(current.getMinX(), current.getMinY(), getWidth(), getHeight());
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
        runWithBrowserHost(false, host -> {
            double effectiveZoom = Math.max(zoomFactor, 0.01);
            host.setZoomLevel(Math.log(effectiveZoom) / Math.log(1.2));
            host.invalidate(PAINT_VIEW);
        });
    }

    void requestViewRefresh(boolean screenInfoChanged) {
        frameBuffer.resetBackPressure();
        runWithBrowserHost(false, host -> {
            if (screenInfoChanged) {
                host.notifyScreenInfoChanged();
                host.wasResized();
            }
            host.invalidate(PAINT_VIEW);
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
            case NORTHSOUTHRESIZE:
            case ROWRESIZE:
                return Cursor.N_RESIZE;
            case SOUTHRESIZE:
                return Cursor.S_RESIZE;
            case EASTRESIZE:
            case EASTWESTRESIZE:
            case COLUMNRESIZE:
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
            default:
                return Cursor.DEFAULT;
        }
    }

    Screen currentScreen() {
        Bounds bounds = localToScreen(getBoundsInLocal());
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
            runWithBrowserHost(false, host -> host.setFocus(true));
        }
        runWithBrowserHost(false, host -> {
            host.sendMouseClickEvent(
                    new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)),
                    CefMouseButtonType.of(cefButton(e)),
                    mouseUp,
                    e.getClickCount());
        });
    }

    private void handleMouseMoved(MouseEvent e) {
        runWithBrowserHost(false, host -> {
            host.sendMouseMoveEvent(new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)), false);
        });
    }

    private void hidePopupOverlay() {
        popupSurface.hide();
    }

    private void handleMouseExited(MouseEvent e) {
        if (popupSurface.containsScreenPoint(e.getScreenX(), e.getScreenY())) return;
        runWithBrowserHost(false, host -> {
            host.sendMouseMoveEvent(new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)), true);
        });
    }

    private void handleScroll(ScrollEvent e) {
        if (e.isControlDown()) {
            double delta = e.getDeltaY() > 0 ? 0.1 : -0.1;
            setZoom(Math.max(0.25, Math.min(5.0, getZoom() + delta)));
            e.consume();
            return;
        }
        runWithBrowserHost(false, host -> {
            host.sendMouseWheelEvent(
                    new CefMouseEvent(
                            (int) e.getX(),
                            (int) e.getY(),
                            baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown())),
                    (int) e.getDeltaX(),
                    (int) e.getDeltaY());
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
        runWithBrowserHost(false, host -> {
            host.sendKeyEvent(new CefKeyEvent(KEY_RAWKEYDOWN, mods, keyCode, keyCode, 0, (char) 0, (char) 0, 0));
        });
    }

    private void handleKeyTyped(KeyEvent e) {
        String text = e.getCharacter();
        if (text == null || text.isEmpty() || KeyEvent.CHAR_UNDEFINED.equals(text)) return;
        char c = text.charAt(0);
        int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        runWithBrowserHost(false, host -> {
            host.sendKeyEvent(new CefKeyEvent(KEY_CHAR, mods, c, c, 0, c, c, 0));
        });
    }

    private void handleKeyReleased(KeyEvent e) {
        int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        int keyCode = e.getCode().getCode();
        runWithBrowserHost(false, host -> {
            host.sendKeyEvent(new CefKeyEvent(KEY_KEYUP, mods, keyCode, keyCode, 0, (char) 0, (char) 0, 0));
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

    void runWithBrowser(boolean failIfUnavailable, Consumer<CefBrowser> action) {
        runWhenBrowserReady(failIfUnavailable, current -> action.accept(current.getBrowser()));
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
            ifHostPresent(h -> h.setFocus(true));
        });
    }

    void onBrowserCreated(@Nullable CefBrowser browser) {
        if (browser == null) return;
        BrowserHandle created = new BrowserHandle(browser);
        if (releaseRequested) {
            // A popup can be released after onBeforePopup returns but before CEF finishes CreateInternal. Closing it
            // synchronously from onAfterCreated clears CEF's platform delegate before CreateInternal subsequently
            // calls NotifyBrowserCreated, causing a native null dereference on CEF 144+. Defer the close until this
            // callback has unwound; closeBrowser is safe to invoke from the JavaFX thread and posts to CEF's UI
            // thread when required.
            Platform.runLater(() -> created.close(true));
            return;
        }
        if (this.browser == null) {
            this.browser = created;
            browserCleanup.browser = created;
        }
        BrowserHandle current = Objects.requireNonNull(this.browser);
        Consumer<BrowserHandle> action;
        while ((action = pendingBrowserActions.poll()) != null) {
            action.accept(current);
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

    boolean handleBeforePopup(
            @Nonnull CefWindowInfo.Mutable windowInfo, @Nullable AtomicReference<CefClient> clientRef) {
        if (clientRef == null) return true;
        javafx.util.Callback<CefPopupFeatures, CefWebEngine> handler = engine.getCreatePopupHandler();
        if (handler == null) return true;
        final AtomicReference<CefWebEngine> popupEngine = new AtomicReference<>();
        runOnFxAndWait(() -> {
            CefWebEngine created = handler.call(new CefPopupFeatures(false, false, false, true));
            if (created != null) {
                // CEF, rather than maybeCreateBrowser(), owns popup creation. Record the in-flight browser before
                // returning from the user callback: observers may react to the callback immediately and release the
                // view, which must then wait for CEF's onAfterCreated/onBeforeClose lifecycle.
                created.getView().browserCreationPosted = true;
            }
            popupEngine.set(created);
        });
        CefWebEngine createdEngine = popupEngine.get();
        if (createdEngine == null) return true;
        CefRect popupBounds;
        if (windowInfo instanceof net.kurobako.cef4j.gen.mac.CefWindowInfo.Mutable) {
            var wi = (net.kurobako.cef4j.gen.mac.CefWindowInfo.Mutable) windowInfo;
            wi.windowlessRenderingEnabled = 1;
            if (wi.bounds == null) wi.bounds = new CefRect(0, 0, 800, 600);
            popupBounds = wi.bounds;
        } else if (windowInfo instanceof net.kurobako.cef4j.gen.win.CefWindowInfo.Mutable) {
            var wi = (net.kurobako.cef4j.gen.win.CefWindowInfo.Mutable) windowInfo;
            wi.windowlessRenderingEnabled = 1;
            if (wi.bounds == null) wi.bounds = new CefRect(0, 0, 800, 600);
            popupBounds = wi.bounds;
        } else if (windowInfo instanceof net.kurobako.cef4j.gen.linux.CefWindowInfo.Mutable) {
            var wi = (net.kurobako.cef4j.gen.linux.CefWindowInfo.Mutable) windowInfo;
            wi.windowlessRenderingEnabled = 1;
            if (wi.bounds == null) wi.bounds = new CefRect(0, 0, 800, 600);
            popupBounds = wi.bounds;
        } else {
            throw new IllegalStateException(
                    "Unsupported platform: " + windowInfo.getClass().getName());
        }
        createdEngine.getView().updateDetachedBounds(popupBounds, true);
        clientRef.set(createdEngine.getView().getCefClient());
        return false;
    }

    void onBeforeBrowserClose() {
        scriptEngine.dispose();
        browserClosed.complete(null);
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
        @Nullable
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

        @Nullable
        private CefBrowserHost getHost() {
            return browser.getHost().orElse(null);
        }

        private void close(boolean force) {
            CefBrowserHost host = getHost();
            if (host != null) {
                host.closeBrowser(force);
            }
        }
    }
}
