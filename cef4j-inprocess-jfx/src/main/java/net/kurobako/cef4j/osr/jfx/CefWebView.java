package net.kurobako.cef4j.osr.jfx;

import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.value.ChangeListener;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableBooleanProperty;
import javafx.css.StyleableDoubleProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.css.converter.BooleanConverter;
import javafx.css.converter.EnumConverter;
import javafx.css.converter.SizeConverter;
import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Rectangle2D;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.util.Duration;
import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.CefFrameBuffer;
import net.kurobako.cef4j.CefInputEventFlags;
import net.kurobako.cef4j.CefScriptEngine;
import net.kurobako.cef4j.SystemBootstrap;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserProcessHandler;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCursorType;
import net.kurobako.cef4j.gen.CefFrame;
import net.kurobako.cef4j.gen.CefGlobals;
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
import net.kurobako.cef4j.gen.CefTask;
import net.kurobako.cef4j.gen.CefThreadId;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * JavaFX off-screen rendering view backed by a CEF browser.
 *
 * <p>The public surface mirrors {@code javafx.scene.web.WebView}: the view is a final {@link Parent} that sizes itself
 * through the {@code min}, {@code pref} and {@code max} size properties (800 by 600 preferred by default) and exposes
 * the same CSS properties.
 */
@SuppressWarnings("this-escape")
public final class CefWebView extends Parent implements AutoCloseable {
    private static final boolean DEFAULT_CONTEXT_MENU_ENABLED = true;
    private static final FontSmoothingType DEFAULT_FONT_SMOOTHING_TYPE = FontSmoothingType.LCD;
    private static final double DEFAULT_ZOOM = 1.0;
    private static final double DEFAULT_FONT_SCALE = 1.0;
    private static final double DEFAULT_MIN_WIDTH = 0;
    private static final double DEFAULT_MIN_HEIGHT = 0;
    private static final double DEFAULT_PREF_WIDTH = 800;
    private static final double DEFAULT_PREF_HEIGHT = 600;
    private static final double DEFAULT_MAX_WIDTH = Double.MAX_VALUE;
    private static final double DEFAULT_MAX_HEIGHT = Double.MAX_VALUE;
    private static final double HEADLESS_WIDTH = 800;
    private static final double HEADLESS_HEIGHT = 600;
    private static final Cleaner CLEANER = Cleaner.create();
    private static final Object INITIALISE_LOCK = new Object();
    private static final CefKeyEventType KEY_RAWKEYDOWN = CefKeyEventType.of(CefKeyEventType.Kind.RAWKEYDOWN);
    private static final CefKeyEventType KEY_CHAR = CefKeyEventType.of(CefKeyEventType.Kind.CHAR);
    private static final CefKeyEventType KEY_KEYUP = CefKeyEventType.of(CefKeyEventType.Kind.KEYUP);
    private static final CefPaintElementType PAINT_VIEW = CefPaintElementType.of(CefPaintElementType.Kind.VIEW);
    private static final CefThreadId CEF_UI_THREAD = CefThreadId.of(CefThreadId.Kind.UI);
    private static final long FX_CALLBACK_TIMEOUT_SECONDS = 10;
    private static final java.util.concurrent.Executor CREATED_BROWSER_CLOSER = Executors.newSingleThreadExecutor(r -> {
        final Thread thread = new Thread(r, "cef4j-created-browser-closer");
        thread.setDaemon(true);
        return thread;
    });
    private final ImageView imageView = new ImageView();
    private final Rectangle surface = new Rectangle();
    private final CefFrameBuffer<int[]> frameBuffer;
    final CefWebEngine engine;
    private final boolean headless;
    final CefScriptEngine scriptEngine = new CefScriptEngine(
            () -> getBrowser().flatMap(CefBrowser::getMainFrame).orElse(null));
    private volatile String scrollbarScript = ScrollbarTheme.injectScript(ScrollbarTheme.generateCss(null));
    private final CefClient client = new CefWebViewClient(this);
    private final CompletableFuture<Void> browserClosed = new CompletableFuture<>();
    private final CompletableFuture<Void> browserReleased = new CompletableFuture<>();
    private final AtomicBoolean releaseStarted = new AtomicBoolean();
    private final AtomicBoolean deferredViewRefreshPosted = new AtomicBoolean();
    private final Timeline periodicRepaint =
            new Timeline(new KeyFrame(Duration.millis(100), e -> ifHostPresent(h -> h.invalidate(PAINT_VIEW))));
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
    private final ChangeListener<Number> windowBoundsListener = (obs, oldValue, newValue) -> {
        refreshViewportSnapshot();
        requestViewRefresh(true);
    };
    private final ChangeListener<Boolean> windowFocusedListener = (obs, wasFocused, isFocused) -> {
        if (!isFocused) {
            hidePopupOverlay();
            ifHostPresent(h -> h.setFocus(false));
        }
    };
    private final ReadOnlyDoubleWrapper width = new ReadOnlyDoubleWrapper(this, "width");
    private final ReadOnlyDoubleWrapper height = new ReadOnlyDoubleWrapper(this, "height");
    private final DoubleProperty zoom =
            new StyledDouble(this, "zoom", DEFAULT_ZOOM, StyleableProperties.ZOOM, this::applyZoom);
    private final DoubleProperty fontScale =
            new StyledDouble(this, "fontScale", DEFAULT_FONT_SCALE, StyleableProperties.FONT_SCALE, this::applyZoom);
    private final DoubleProperty minWidth =
            new StyledDouble(this, "minWidth", DEFAULT_MIN_WIDTH, StyleableProperties.MIN_WIDTH, this::layoutParent);
    private final DoubleProperty minHeight =
            new StyledDouble(this, "minHeight", DEFAULT_MIN_HEIGHT, StyleableProperties.MIN_HEIGHT, this::layoutParent);
    private final DoubleProperty prefWidth =
            new StyledDouble(this, "prefWidth", DEFAULT_PREF_WIDTH, StyleableProperties.PREF_WIDTH, this::layoutParent);
    private final DoubleProperty prefHeight = new StyledDouble(
            this, "prefHeight", DEFAULT_PREF_HEIGHT, StyleableProperties.PREF_HEIGHT, this::layoutParent);
    private final DoubleProperty maxWidth =
            new StyledDouble(this, "maxWidth", DEFAULT_MAX_WIDTH, StyleableProperties.MAX_WIDTH, this::layoutParent);
    private final DoubleProperty maxHeight =
            new StyledDouble(this, "maxHeight", DEFAULT_MAX_HEIGHT, StyleableProperties.MAX_HEIGHT, this::layoutParent);
    private final ObjectProperty<FontSmoothingType> fontSmoothingType =
            new StyleableObjectProperty<>(DEFAULT_FONT_SMOOTHING_TYPE) {
                @Override
                public CssMetaData<CefWebView, FontSmoothingType> getCssMetaData() {
                    return StyleableProperties.FONT_SMOOTHING_TYPE;
                }

                @Override
                public Object getBean() {
                    return CefWebView.this;
                }

                @Override
                public String getName() {
                    return "fontSmoothingType";
                }
            };
    private final BooleanProperty contextMenuEnabled = new StyleableBooleanProperty(DEFAULT_CONTEXT_MENU_ENABLED) {
        @Override
        protected void invalidated() {
            contextMenuAllowed = get();
        }

        @Override
        public CssMetaData<CefWebView, Boolean> getCssMetaData() {
            return StyleableProperties.CONTEXT_MENU_ENABLED;
        }

        @Override
        public Object getBean() {
            return CefWebView.this;
        }

        @Override
        public String getName() {
            return "contextMenuEnabled";
        }
    };
    volatile boolean contextMenuAllowed = DEFAULT_CONTEXT_MENU_ENABLED;

    @Nullable
    volatile BiConsumer<Integer, Integer> paintObserver;

    @Nullable
    private volatile BrowserHandle browser;

    private volatile boolean releaseRequested = false;

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
    private int pressedKeyCode;
    private final CefWebViewPopupSurface popupSurface = new CefWebViewPopupSurface(this);

    @Nullable
    volatile CefRect popupRect;

    private volatile boolean browserCreationPosted = false;
    private volatile boolean browserCreated = false;
    volatile boolean popupBrowser = false;
    volatile Rectangle2D detachedBounds = new Rectangle2D(0, 0, 1, 1);
    private volatile ViewportSnapshot viewportSnapshot = ViewportSnapshot.detached();
    private final Queue<Consumer<BrowserHandle>> pendingBrowserActions = new ConcurrentLinkedQueue<>();

    /** Number of view (non-popup) paints delivered to this view; package-private for tests. */
    final java.util.concurrent.atomic.LongAdder framesPainted = new java.util.concurrent.atomic.LongAdder();

    /**
     * Initialise CEF for off-screen rendering. On macOS, call this after the JavaFX toolkit has started (normally from
     * {@link javafx.application.Application#start(javafx.stage.Stage)}); JavaFX and Glass must establish their AppKit
     * integration before CEF takes over the shared application event loop. Calling from that same lifecycle point is
     * also supported on Linux and Windows.
     *
     * <p>OSR-required settings ({@code windowlessRenderingEnabled=1}, the platform-appropriate message-loop mode, and
     * {@code --disable-popup-blocking} / {@code --ozone-platform=x11} on Linux) are stamped onto {@code settings} /
     * prepended to {@code extraArgs} by {@link Cef#osrLaunchArgs()}. Any other fields on {@code settings} (e.g.
     * {@code cachePath}) are preserved.
     *
     * @param settings caller-provided settings; OSR fields will be overwritten
     * @param extraArgs additional CEF command-line args; OSR defaults are prepended
     * @throws IllegalStateException if CEF has been terminated
     */
    public static void initialise(CefSettings.Mutable settings, List<String> extraArgs) {
        initialiseImpl(settings, extraArgs, null);
    }

    /**
     * Initialise CEF for off-screen rendering with an application handler.
     *
     * @param settings caller-provided settings; OSR fields will be overwritten
     * @param extraArgs additional CEF command-line args; OSR defaults are prepended
     * @param appHandler {@link CefApp} handler registered via {@link Cef#addAppHandler(CefApp)} before initialisation
     * @throws IllegalStateException if CEF has been terminated
     */
    public static void initialise(CefSettings.Mutable settings, List<String> extraArgs, CefApp appHandler) {
        initialiseImpl(settings, extraArgs, Objects.requireNonNull(appHandler, "appHandler"));
    }

    private static void initialiseImpl(
            CefSettings.Mutable settings, List<String> extraArgs, @Nullable CefApp appHandler) {
        synchronized (INITIALISE_LOCK) {
            Objects.requireNonNull(settings, "settings");
            Objects.requireNonNull(extraArgs, "extraArgs");
            // XXX: Application.launch owns JavaFX startup, and macOS requires Glass/AppKit before CEF enters its
            // managed
            // loop; remove this caller-owned ordering only if JavaFX exposes a supported non-Application startup
            // bridge.
            if (Cef.INSTANCE.state() == Cef.State.INITIALISED) {
                requireOsrInitialised();
                return;
            }
            SystemBootstrap.load();
            final Cef.LaunchArgs defaults = Cef.osrLaunchArgs();
            defaults.applyTo(settings);
            if (net.kurobako.cef4j.OS.isLinux()
                    && SystemBootstrap.runtimeCefMajor().orElse(Integer.MAX_VALUE) <= 85) {
                if (!Platform.isFxApplicationThread()) {
                    throw new IllegalStateException("Legacy CEF JavaFX initialisation must run on the JavaFX thread");
                }
                settings.externalMessagePump = 1;
                settings.multiThreadedMessageLoop = 0;
                Cef.INSTANCE.addAppHandler(new CefApp() {
                    @Override
                    public Optional<CefBrowserProcessHandler> getBrowserProcessHandler() {
                        return Optional.of(new CefBrowserProcessHandler() {
                            @Override
                            public void onScheduleMessagePumpWork(long delayMs) {
                                SystemBootstrap.scheduleLinuxMessageLoopWork(delayMs);
                            }
                        });
                    }
                });
            }
            if (appHandler != null) {
                Cef.INSTANCE.addAppHandler(appHandler);
            }
            Cef.INSTANCE.initialise(settings, defaults.argsWith(extraArgs));
        }
    }

    /** Terminate CEF. See {@link Cef#terminate()}. */
    public static void terminate() {
        if (net.kurobako.cef4j.OS.isLinux()) SystemBootstrap.cancelLinuxMessageLoopWork();
        Cef.INSTANCE.terminate();
    }

    private static void requireOsrInitialised() {
        final Cef.State state = Cef.INSTANCE.state();
        if (state != Cef.State.INITIALISED) {
            throw new IllegalStateException(
                    "CEF must be initialised for off-screen rendering before creating a CefWebView.\n"
                            + "Call this from Application.start(...) before creating the view:\n\n"
                            + "    CefWebView.initialise(settings, List.of());\n");
        }
        Cef.INSTANCE
                .activeSettings()
                .filter(s -> s.windowlessRenderingEnabled == 0)
                .ifPresent(s -> {
                    throw new IllegalStateException(
                            "CEF was initialised without windowlessRenderingEnabled=1. CefWebView requires OSR mode.\n"
                                    + "Use CefWebView.initialise() instead.\n");
                });
    }

    /** Creates a view that creates its browser once it is shown in a window. */
    public CefWebView() {
        this(null);
    }

    CefWebView(@Nullable CefWebEngine owner) {
        requireOsrInitialised();
        headless = owner != null;
        engine = owner != null ? owner : new CefWebEngine(this);
        int maxW = 1;
        int maxH = 1;
        for (Screen screen : Screen.getScreens()) {
            final Rectangle2D bounds = screen.getBounds();
            final double scale = Math.max(screen.getOutputScaleX(), screen.getOutputScaleY());
            final int pw = (int) Math.ceil(bounds.getWidth() * scale);
            final int ph = (int) Math.ceil(bounds.getHeight() * scale);
            if (pw > maxW) maxW = pw;
            if (ph > maxH) maxH = ph;
        }
        frameBuffer = new CefFrameBuffer<>(maxW, maxH, (prev, pixels, w, h, dirty) -> {
            // XXX: CefFrameBuffer reuses pixels after consume re-arms production; remove this copy only when the image
            // handoff transfers immutable pixel ownership to the JavaFX application thread.
            final int[] image = prev != null && prev.length >= w * h ? prev : new int[w * h];
            System.arraycopy(pixels, 0, image, 0, w * h);
            return image;
        });

        setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        getStyleClass().add("web-view");
        setPickOnBounds(true);
        surface.setManaged(false);
        surface.setFill(Color.TRANSPARENT);
        surface.setMouseTransparent(true);
        getChildren().addAll(surface, imageView);
        imageView.setManaged(false);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(false);
        imageView.setMouseTransparent(true);
        setFocusTraversable(true);
        periodicRepaint.setCycleCount(Animation.INDEFINITE);

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
                popupSurface.hide();
            }
        });
        if (headless) {
            width.set(HEADLESS_WIDTH);
            height.set(HEADLESS_HEIGHT);
            detachedBounds = new Rectangle2D(0, 0, HEADLESS_WIDTH, HEADLESS_HEIGHT);
            refreshViewportSnapshot();
        }
        widthProperty().addListener((obs, oldV, newV) -> onResize());
        heightProperty().addListener((obs, oldV, newV) -> onResize());
        localToSceneTransformProperty().addListener((obs, oldTransform, newTransform) -> refreshViewportSnapshot());
        sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (oldScene != null) {
                oldScene.windowProperty().removeListener(sceneWindowListener);
            }
            if (newScene != null) {
                newScene.windowProperty().addListener(sceneWindowListener);
                onWindowChanged(newScene.getWindow());
            }
            scrollbarScript = ScrollbarTheme.injectScript(ScrollbarTheme.generateCss(newScene));
            maybeCreateBrowser(false);
        });
        cleanable = CLEANER.register(this, browserCleanup);
    }

    /** Returns the engine of this view. */
    public CefWebEngine getEngine() {
        return engine;
    }

    public CefClient getCefClient() {
        return client;
    }

    public CefScriptEngine getScriptEngine() {
        return scriptEngine;
    }

    /** Returns the underlying browser instance, or empty if it does not exist yet. */
    public Optional<CefBrowser> getBrowser() {
        return Optional.ofNullable(browser).map(BrowserHandle::getBrowser);
    }

    /** Returns the underlying browser host, or empty if it does not exist yet. */
    public Optional<CefBrowserHost> getBrowserHost() {
        return Optional.ofNullable(browser).flatMap(BrowserHandle::getHost);
    }

    /** Returns the width of this view. */
    public double getWidth() {
        return width.get();
    }

    /** Returns the width property of this view. */
    public ReadOnlyDoubleProperty widthProperty() {
        return width.getReadOnlyProperty();
    }

    /** Returns the height of this view. */
    public double getHeight() {
        return height.get();
    }

    /** Returns the height property of this view. */
    public ReadOnlyDoubleProperty heightProperty() {
        return height.getReadOnlyProperty();
    }

    /** Returns the current zoom factor. */
    public final double getZoom() {
        return zoom.get();
    }

    public final void setZoom(double value) {
        zoom.set(value);
    }

    /** Returns the zoom factor applied to the entire page contents. */
    public final DoubleProperty zoomProperty() {
        return zoom;
    }

    /**
     * Returns the font scale.
     *
     * <p>CEF has no text-only zoom, so the scale multiplies the zoom factor and also scales images and fixed-size
     * elements.
     */
    public double getFontScale() {
        return fontScale.get();
    }

    public void setFontScale(double value) {
        fontScale.set(value);
    }

    /** Returns the font scale property, which defaults to 1. */
    public DoubleProperty fontScaleProperty() {
        return fontScale;
    }

    /** Returns the requested font smoothing type. Rendering ignores it because CEF cannot change it per browser. */
    public FontSmoothingType getFontSmoothingType() {
        return fontSmoothingType.get();
    }

    public void setFontSmoothingType(FontSmoothingType value) {
        fontSmoothingType.set(value);
    }

    /** Returns the font smoothing type property, which defaults to {@link FontSmoothingType#LCD}. */
    public ObjectProperty<FontSmoothingType> fontSmoothingTypeProperty() {
        return fontSmoothingType;
    }

    /** Returns whether the page context menu is shown. */
    public boolean isContextMenuEnabled() {
        return contextMenuEnabled.get();
    }

    public void setContextMenuEnabled(boolean value) {
        contextMenuEnabled.set(value);
    }

    /**
     * Returns the context menu property, which defaults to {@code true}.
     *
     * <p>While it is {@code false} the CEF context menu is cancelled; the page still receives its {@code contextmenu}
     * DOM event.
     */
    public BooleanProperty contextMenuEnabledProperty() {
        return contextMenuEnabled;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double newWidth, double newHeight) {
        if (newWidth != width.get() || newHeight != height.get()) {
            width.set(newWidth);
            height.set(newHeight);
            requestLayout();
        }
    }

    @Override
    public double minWidth(double ignoredHeight) {
        return nonNegative(getMinWidth());
    }

    @Override
    public double minHeight(double ignoredWidth) {
        return nonNegative(getMinHeight());
    }

    @Override
    public double prefWidth(double ignoredHeight) {
        return nonNegative(getPrefWidth());
    }

    @Override
    public double prefHeight(double ignoredWidth) {
        return nonNegative(getPrefHeight());
    }

    @Override
    public double maxWidth(double ignoredHeight) {
        return nonNegative(getMaxWidth());
    }

    @Override
    public double maxHeight(double ignoredWidth) {
        return nonNegative(getMaxHeight());
    }

    public DoubleProperty minWidthProperty() {
        return minWidth;
    }

    public void setMinWidth(double value) {
        minWidth.set(value);
    }

    public double getMinWidth() {
        return minWidth.get();
    }

    public DoubleProperty minHeightProperty() {
        return minHeight;
    }

    public void setMinHeight(double value) {
        minHeight.set(value);
    }

    public double getMinHeight() {
        return minHeight.get();
    }

    public void setMinSize(double newMinWidth, double newMinHeight) {
        setMinWidth(newMinWidth);
        setMinHeight(newMinHeight);
    }

    public DoubleProperty prefWidthProperty() {
        return prefWidth;
    }

    public void setPrefWidth(double value) {
        prefWidth.set(value);
    }

    public double getPrefWidth() {
        return prefWidth.get();
    }

    public DoubleProperty prefHeightProperty() {
        return prefHeight;
    }

    public void setPrefHeight(double value) {
        prefHeight.set(value);
    }

    public double getPrefHeight() {
        return prefHeight.get();
    }

    public void setPrefSize(double newPrefWidth, double newPrefHeight) {
        setPrefWidth(newPrefWidth);
        setPrefHeight(newPrefHeight);
    }

    public DoubleProperty maxWidthProperty() {
        return maxWidth;
    }

    public void setMaxWidth(double value) {
        maxWidth.set(value);
    }

    public double getMaxWidth() {
        return maxWidth.get();
    }

    public DoubleProperty maxHeightProperty() {
        return maxHeight;
    }

    public void setMaxHeight(double value) {
        maxHeight.set(value);
    }

    public double getMaxHeight() {
        return maxHeight.get();
    }

    public void setMaxSize(double newMaxWidth, double newMaxHeight) {
        setMaxWidth(newMaxWidth);
        setMaxHeight(newMaxHeight);
    }

    /** Returns the CSS metadata of this class, which includes the metadata of its superclasses. */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

    private static double nonNegative(double value) {
        return Double.isNaN(value) || value < 0 ? 0 : value;
    }

    private void layoutParent() {
        final Parent parent = getParent();
        if (parent != null) parent.requestLayout();
    }

    void print(PrinterJob job) {
        final Image page = imageView.getImage();
        if (page == null) return;
        final PageLayout layout = job.getJobSettings().getPageLayout();
        final ImageView printable = new ImageView(page);
        printable.setPreserveRatio(true);
        printable.setFitWidth(layout.getPrintableWidth());
        printable.setFitHeight(layout.getPrintableHeight());
        job.printPage(layout, printable);
    }

    /** Navigates to the given URL. */
    public void load(String url) {
        engine.updateLocation(url);
        runWithBrowser(false, current -> {
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
    @NullableBoundary("JavaFX WebEngine.executeScript accepts a null script")
    public void executeScript(@Nullable String script) {
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

    @Override
    public void close() {
        release();
    }

    /**
     * Releases this view and completes after CEF confirms that its browser has closed.
     *
     * <p>The returned future is useful when an application must establish a clean browser-lifecycle boundary before
     * calling {@link #terminate()}.
     */
    @SuppressWarnings("FutureReturnValueIgnored")
    public CompletableFuture<Void> releaseAsync() {
        if (!releaseStarted.compareAndSet(false, true)) return browserReleased.copy();
        final boolean creationPending = browserCreationPosted;
        releaseRequested = true;
        popupSurface.hide();
        final BrowserHandle h = browser;
        browser = null;
        browserCleanup.browser = null;
        browserCreated = false;
        browserCreationPosted = false;
        periodicRepaint.stop();
        imageView.setImage(null);
        pixelBuf = null;
        pixelBuffer = null;
        final var scene = getScene();
        if (scene != null) {
            scene.windowProperty().removeListener(sceneWindowListener);
            final Window window = scene.getWindow();
            if (window != null) detachWindowListeners(window);
        }
        scriptEngine.dispose();
        if (h != null) {
            releaseBrowserAfterClose(h);
            h.close(true);
        } else if (!creationPending) {
            browserClosed.complete(null);
            browserReleased.complete(null);
        }
        Platform.runLater(() -> engine.fireVisibilityChanged(false));
        cleanable.clean();
        return browserReleased.copy();
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private void releaseBrowserAfterClose(BrowserHandle handle) {
        browserClosed.whenComplete((ignored, failure) -> {
            final boolean posted = CefGlobals.postTask(CEF_UI_THREAD, new CefTask() {
                @Override
                public void execute() {
                    handle.release();
                    browserReleased.complete(null);
                }
            });
            if (!posted) {
                handle.release();
                browserReleased.completeExceptionally(
                        new IllegalStateException("CEF UI queue rejected browser release"));
            }
        });
    }

    @Override
    protected void layoutChildren() {
        surface.setWidth(getWidth());
        surface.setHeight(getHeight());
        imageView.setFitWidth(getWidth());
        imageView.setFitHeight(getHeight());
        refreshViewportSnapshot();
    }

    /** Creates the render handler used by the default client. */
    public CefRenderHandler createRenderHandler() {
        return new CefRenderHandler() {
            @Override
            public boolean getRootScreenRect(@Nullable CefBrowser b, CefRect.Mutable rect) {
                final Rectangle2D resolved = viewportSnapshot.rootBounds;
                rect.x = (int) Math.round(resolved.getMinX());
                rect.y = (int) Math.round(resolved.getMinY());
                rect.width = Math.max(1, (int) Math.round(resolved.getWidth()));
                rect.height = Math.max(1, (int) Math.round(resolved.getHeight()));
                return true;
            }

            @Override
            public void getViewRect(@Nullable CefBrowser b, CefRect.Mutable rect) {
                final ViewportSnapshot snapshot = viewportSnapshot;
                rect.x = 0;
                rect.y = 0;
                rect.width = snapshot.viewWidth;
                rect.height = snapshot.viewHeight;
            }

            @Override
            public boolean getScreenInfo(@Nullable CefBrowser b, CefScreenInfo.Mutable screenInfo) {
                final ViewportSnapshot snapshot = viewportSnapshot;
                screenInfo.deviceScaleFactor = (float) snapshot.scale;
                screenInfo.depth = 32;
                screenInfo.depthPerComponent = 8;
                screenInfo.rect = snapshot.screenBounds;
                screenInfo.availableRect = snapshot.availableBounds;
                return true;
            }

            @Override
            public boolean getScreenPoint(@Nullable CefBrowser b, int viewX, int viewY, int[] screenX, int[] screenY) {
                final ViewportSnapshot snapshot = viewportSnapshot;
                screenX[0] = (int) Math.round(snapshot.originX + snapshot.xx * viewX + snapshot.xy * viewY);
                screenY[0] = (int) Math.round(snapshot.originY + snapshot.yx * viewX + snapshot.yy * viewY);
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
            public void onPopupSize(@Nullable CefBrowser browser, CefRect rect) {
                popupRect = new CefRect(rect.x, rect.y, rect.width, rect.height);
            }

            @Override
            public void onPaint(
                    @Nullable CefBrowser b,
                    CefPaintElementType type,
                    long dirtyRectsCount,
                    CefRect[] dirtyRects,
                    ByteBuffer buffer,
                    int width,
                    int height) {
                final boolean isPopup =
                        type.kind().orElse(CefPaintElementType.Kind.VIEW) == CefPaintElementType.Kind.POPUP;
                if (isPopup) {
                    CefFrameBuffer.copyBgraPixels(buffer, width, height)
                            .ifPresent(px -> Platform.runLater(() -> popupSurface.blit(px, width, height)));
                } else {
                    if (frameBuffer.onPaint(buffer, width, height, dirtyRects).isPresent()) {
                        framesPainted.increment();
                        onViewPainted(width, height);
                        Platform.runLater(() -> blitFrame(width, height));
                    } else if (deferredViewRefreshPosted.compareAndSet(false, true)) {
                        // CEF paints are demand-driven. If a newer frame arrives before JavaFX consumes the current
                        // one, CefFrameBuffer applies back-pressure and records its dirty region, but CEF may not
                        // schedule another paint by itself. Queue the refresh behind the accepted frame's blit so the
                        // accumulated damage is delivered instead of leaving the first frame visible indefinitely.
                        Platform.runLater(() -> {
                            deferredViewRefreshPosted.set(false);
                            requestViewRefresh(false);
                        });
                    }
                }
            }
        };
    }

    /** Creates the load handler that injects the custom scrollbar styling. */
    public CefLoadHandler createScrollbarLoadHandler() {
        return new CefLoadHandler() {
            @Override
            public void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpStatusCode) {
                final BrowserHandle br = CefWebView.this.browser;
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
            final CefWindowInfo windowInfo = Cef.createWindowlessInfo(
                    new CefRect(0, 0, Math.max(1, (int) getWidth()), Math.max(1, (int) getHeight())));
            final CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
            browserSettings.windowlessFrameRate = 60;
            if (!Cef.createBrowserAsync(windowInfo, client, "", browserSettings.toImmutable())) {
                throw new IllegalStateException("CEF failed to create windowless browser");
            }
        } catch (RuntimeException e) {
            browserCreationPosted = false;
            if (failIfUnavailable) throw e;
        }
    }

    private Optional<CefBrowserHost> host() {
        return Optional.ofNullable(browser).flatMap(BrowserHandle::getHost);
    }

    private void ifHostPresent(Consumer<CefBrowserHost> action) {
        host().ifPresent(action);
    }

    private void blitFrame(int width, int height) {
        final int[] pixels = frameBuffer.consume().orElse(null);
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
        final var scale = currentScaleFactor(currentScreen());
        final int expectedW = (int) Math.max(1, Math.round(getWidth() * scale));
        final int expectedH = (int) Math.max(1, Math.round(getHeight() * scale));
        if (width != expectedW || height != expectedH) {
            requestViewRefresh(true);
        }
    }

    private void onResize() {
        final Rectangle2D current = detachedBounds;
        detachedBounds = new Rectangle2D(current.getMinX(), current.getMinY(), getWidth(), getHeight());
        refreshViewportSnapshot();
        frameBuffer.resetBackPressure();
        Platform.runLater(() -> engine.fireResized(new Rectangle2D(0, 0, getWidth(), getHeight())));
        requestViewRefresh(true);
    }

    private void onWindowChanged(@Nullable Window window) {
        if (window != null) {
            attachWindowListeners(window);
            if (window.isShowing()) {
                maybeCreateBrowser(false);
                requestViewRefresh(true);
            }
        }
        refreshViewportSnapshot();
    }

    private void attachWindowListeners(Window window) {
        window.showingProperty().removeListener(windowShowingListener);
        window.showingProperty().addListener(windowShowingListener);
        window.focusedProperty().removeListener(windowFocusedListener);
        window.focusedProperty().addListener(windowFocusedListener);
        removeWindowBoundsListeners(window);
        window.xProperty().addListener(windowBoundsListener);
        window.yProperty().addListener(windowBoundsListener);
        window.widthProperty().addListener(windowBoundsListener);
        window.heightProperty().addListener(windowBoundsListener);
    }

    private void detachWindowListeners(Window window) {
        window.showingProperty().removeListener(windowShowingListener);
        window.focusedProperty().removeListener(windowFocusedListener);
        removeWindowBoundsListeners(window);
    }

    private void removeWindowBoundsListeners(Window window) {
        window.xProperty().removeListener(windowBoundsListener);
        window.yProperty().removeListener(windowBoundsListener);
        window.widthProperty().removeListener(windowBoundsListener);
        window.heightProperty().removeListener(windowBoundsListener);
    }

    private boolean canCreateBrowserNow() {
        if (headless) return !releaseStarted.get();
        if (getScene() == null) return false;
        final Window window = getScene().getWindow();
        if (window == null || !window.isShowing()) return false;
        return getWidth() > 0 && getHeight() > 0;
    }

    private void applyZoom() {
        final double zoomFactor = getZoom() * getFontScale();
        runWithBrowserHost(false, host -> {
            final double effectiveZoom = Math.max(zoomFactor, 0.01);
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

    private void onViewPainted(int paintedWidth, int paintedHeight) {
        final BiConsumer<Integer, Integer> observer = paintObserver;
        if (observer != null) observer.accept(paintedWidth, paintedHeight);
    }

    void updateDetachedBounds(@Nullable CefRect bounds, boolean notify) {
        if (bounds == null) return;
        detachedBounds = new Rectangle2D(bounds.x, bounds.y, Math.max(1, bounds.width), Math.max(1, bounds.height));
        refreshViewportSnapshot();
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
        final Bounds bounds = localToScreen(getBoundsInLocal());
        if (bounds != null) {
            final List<Screen> screens = Screen.getScreensForRectangle(
                    bounds.getMinX(),
                    bounds.getMinY(),
                    Math.max(1.0, bounds.getWidth()),
                    Math.max(1.0, bounds.getHeight()));
            if (!screens.isEmpty()) {
                return screens.get(0);
            }
        }
        final var scene = getScene();
        final Window window = scene != null ? scene.getWindow() : null;
        if (window != null) {
            final List<Screen> screens = Screen.getScreensForRectangle(
                    window.getX(), window.getY(), Math.max(1.0, window.getWidth()), Math.max(1.0, window.getHeight()));
            if (!screens.isEmpty()) {
                return screens.get(0);
            }
        }
        return Screen.getPrimary();
    }

    double currentScaleFactor(Screen screen) {
        final var scene = getScene();
        final var window = scene != null ? scene.getWindow() : null;
        if (window != null) {
            final double outputScale = Math.max(window.getOutputScaleX(), window.getOutputScaleY());
            if (outputScale > 0.0) {
                return outputScale;
            }
            final double renderScale = Math.max(window.getRenderScaleX(), window.getRenderScaleY());
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

    private void refreshViewportSnapshot() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::refreshViewportSnapshot);
            return;
        }
        final Rectangle2D fallback = detachedBounds;
        final Bounds bounds = localToScreen(getBoundsInLocal());
        final Rectangle2D root = bounds == null
                ? fallback
                : new Rectangle2D(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
        javafx.geometry.Point2D origin = localToScreen(0, 0);
        javafx.geometry.Point2D xUnit = localToScreen(1, 0);
        javafx.geometry.Point2D yUnit = localToScreen(0, 1);
        if (origin == null || xUnit == null || yUnit == null) {
            origin = new javafx.geometry.Point2D(fallback.getMinX(), fallback.getMinY());
            xUnit = origin.add(1, 0);
            yUnit = origin.add(0, 1);
        }
        final Screen screen = currentScreen();
        final double scale = currentScaleFactor(screen);
        viewportSnapshot = new ViewportSnapshot(
                root,
                Math.max(1, (int) Math.round(getWidth() > 0 ? getWidth() : fallback.getWidth())),
                Math.max(1, (int) Math.round(getHeight() > 0 ? getHeight() : fallback.getHeight())),
                origin.getX(),
                origin.getY(),
                xUnit.getX() - origin.getX(),
                yUnit.getX() - origin.getX(),
                xUnit.getY() - origin.getY(),
                yUnit.getY() - origin.getY(),
                scale,
                scaledRect(screen.getBounds(), scale),
                scaledRect(screen.getVisualBounds(), scale));
    }

    private static CefRect scaledRect(Rectangle2D bounds, double scale) {
        return new CefRect(
                (int) Math.round(bounds.getMinX() * scale),
                (int) Math.round(bounds.getMinY() * scale),
                Math.max(1, (int) Math.round(bounds.getWidth() * scale)),
                Math.max(1, (int) Math.round(bounds.getHeight() * scale)));
    }

    private static final class StyledDouble extends StyleableDoubleProperty {
        private final CefWebView bean;
        private final String name;
        private final CssMetaData<CefWebView, Number> metaData;
        private final Runnable onInvalidated;

        private StyledDouble(
                CefWebView bean,
                String name,
                double initial,
                CssMetaData<CefWebView, Number> metaData,
                Runnable onInvalidated) {
            super(initial);
            this.bean = bean;
            this.name = name;
            this.metaData = metaData;
            this.onInvalidated = onInvalidated;
        }

        @Override
        protected void invalidated() {
            onInvalidated.run();
        }

        @Override
        public CssMetaData<CefWebView, Number> getCssMetaData() {
            return metaData;
        }

        @Override
        public Object getBean() {
            return bean;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    private static final class StyleableProperties {
        private static final CssMetaData<CefWebView, Boolean> CONTEXT_MENU_ENABLED =
                new CssMetaData<>(
                        "-fx-context-menu-enabled", BooleanConverter.getInstance(), DEFAULT_CONTEXT_MENU_ENABLED) {
                    @Override
                    public boolean isSettable(CefWebView view) {
                        return !view.contextMenuEnabled.isBound();
                    }

                    @Override
                    public StyleableProperty<Boolean> getStyleableProperty(CefWebView view) {
                        return (StyleableProperty<Boolean>) view.contextMenuEnabled;
                    }
                };

        private static final CssMetaData<CefWebView, FontSmoothingType> FONT_SMOOTHING_TYPE =
                new CssMetaData<>(
                        "-fx-font-smoothing-type",
                        new EnumConverter<>(FontSmoothingType.class),
                        DEFAULT_FONT_SMOOTHING_TYPE) {
                    @Override
                    public boolean isSettable(CefWebView view) {
                        return !view.fontSmoothingType.isBound();
                    }

                    @Override
                    public StyleableProperty<FontSmoothingType> getStyleableProperty(CefWebView view) {
                        return (StyleableProperty<FontSmoothingType>) view.fontSmoothingType;
                    }
                };

        private static final CssMetaData<CefWebView, Number> ZOOM = size("-fx-zoom", DEFAULT_ZOOM, v -> v.zoom);
        private static final CssMetaData<CefWebView, Number> FONT_SCALE =
                size("-fx-font-scale", DEFAULT_FONT_SCALE, v -> v.fontScale);
        private static final CssMetaData<CefWebView, Number> MIN_WIDTH =
                size("-fx-min-width", DEFAULT_MIN_WIDTH, v -> v.minWidth);
        private static final CssMetaData<CefWebView, Number> MIN_HEIGHT =
                size("-fx-min-height", DEFAULT_MIN_HEIGHT, v -> v.minHeight);
        private static final CssMetaData<CefWebView, Number> MAX_WIDTH =
                size("-fx-max-width", DEFAULT_MAX_WIDTH, v -> v.maxWidth);
        private static final CssMetaData<CefWebView, Number> MAX_HEIGHT =
                size("-fx-max-height", DEFAULT_MAX_HEIGHT, v -> v.maxHeight);
        private static final CssMetaData<CefWebView, Number> PREF_WIDTH =
                size("-fx-pref-width", DEFAULT_PREF_WIDTH, v -> v.prefWidth);
        private static final CssMetaData<CefWebView, Number> PREF_HEIGHT =
                size("-fx-pref-height", DEFAULT_PREF_HEIGHT, v -> v.prefHeight);

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Parent.getClassCssMetaData());
            styleables.add(CONTEXT_MENU_ENABLED);
            styleables.add(FONT_SMOOTHING_TYPE);
            styleables.add(ZOOM);
            styleables.add(FONT_SCALE);
            styleables.add(MIN_WIDTH);
            styleables.add(PREF_WIDTH);
            styleables.add(MAX_WIDTH);
            styleables.add(MIN_HEIGHT);
            styleables.add(PREF_HEIGHT);
            styleables.add(MAX_HEIGHT);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }

        private static CssMetaData<CefWebView, Number> size(
                String property, double initial, Function<CefWebView, DoubleProperty> accessor) {
            return new CssMetaData<>(property, SizeConverter.getInstance(), initial) {
                @Override
                public boolean isSettable(CefWebView view) {
                    return !accessor.apply(view).isBound();
                }

                @Override
                public StyleableProperty<Number> getStyleableProperty(CefWebView view) {
                    return (StyleableProperty<Number>) accessor.apply(view);
                }
            };
        }
    }

    private static final class ViewportSnapshot {
        private final Rectangle2D rootBounds;
        private final int viewWidth;
        private final int viewHeight;
        private final double originX;
        private final double originY;
        private final double xx;
        private final double xy;
        private final double yx;
        private final double yy;
        private final double scale;
        private final CefRect screenBounds;
        private final CefRect availableBounds;

        private ViewportSnapshot(
                Rectangle2D rootBounds,
                int viewWidth,
                int viewHeight,
                double originX,
                double originY,
                double xx,
                double xy,
                double yx,
                double yy,
                double scale,
                CefRect screenBounds,
                CefRect availableBounds) {
            this.rootBounds = rootBounds;
            this.viewWidth = viewWidth;
            this.viewHeight = viewHeight;
            this.originX = originX;
            this.originY = originY;
            this.xx = xx;
            this.xy = xy;
            this.yx = yx;
            this.yy = yy;
            this.scale = scale;
            this.screenBounds = screenBounds;
            this.availableBounds = availableBounds;
        }

        private static ViewportSnapshot detached() {
            final Rectangle2D unit = new Rectangle2D(0, 0, 1, 1);
            final CefRect rect = new CefRect(0, 0, 1, 1);
            return new ViewportSnapshot(unit, 1, 1, 0, 0, 1, 0, 0, 1, 1, rect, rect);
        }
    }

    private void handleMouseClick(MouseEvent e, boolean mouseUp) {
        if (!mouseUp) {
            hideContextMenu();
            requestFocus();
            runWithBrowserHost(false, host -> host.setFocus(true));
        }
        runWithBrowserHost(
                false,
                host -> host.sendMouseClickEvent(
                        new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)),
                        CefMouseButtonType.of(cefButton(e)),
                        mouseUp,
                        e.getClickCount()));
    }

    private void handleMouseMoved(MouseEvent e) {
        runWithBrowserHost(
                false,
                host -> host.sendMouseMoveEvent(
                        new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)), false));
    }

    private void hidePopupOverlay() {
        popupSurface.hide();
    }

    private void handleMouseExited(MouseEvent e) {
        if (popupSurface.containsScreenPoint(e.getScreenX(), e.getScreenY())) return;
        runWithBrowserHost(
                false,
                host -> host.sendMouseMoveEvent(
                        new CefMouseEvent((int) e.getX(), (int) e.getY(), mouseModifiers(e)), true));
    }

    private void handleScroll(ScrollEvent e) {
        if (e.isControlDown()) {
            final double delta = e.getDeltaY() > 0 ? 0.1 : -0.1;
            setZoom(Math.max(0.25, Math.min(5.0, getZoom() + delta)));
            e.consume();
            return;
        }
        runWithBrowserHost(false, host -> {
            final CefMouseEvent mouse = new CefMouseEvent(
                    (int) e.getX(),
                    (int) e.getY(),
                    baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown()));
            host.sendMouseMoveEvent(mouse, false);
            host.sendMouseWheelEvent(mouse, (int) e.getDeltaX(), (int) e.getDeltaY());
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
        final int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        final int keyCode = e.getCode().getCode();
        pressedKeyCode = keyCode;
        runWithBrowserHost(
                false,
                host -> host.sendKeyEvent(
                        new CefKeyEvent(KEY_RAWKEYDOWN, mods, keyCode, keyCode, 0, (char) 0, (char) 0, 0)));
    }

    private void handleKeyTyped(KeyEvent e) {
        final String text = e.getCharacter();
        if (text == null || text.isEmpty() || KeyEvent.CHAR_UNDEFINED.equals(text)) return;
        final char c = text.charAt(0);
        final int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        final int keyCode = pressedKeyCode != 0 ? pressedKeyCode : c;
        runWithBrowserHost(
                false, host -> host.sendKeyEvent(new CefKeyEvent(KEY_CHAR, mods, keyCode, keyCode, 0, c, c, 0)));
    }

    private void handleKeyReleased(KeyEvent e) {
        final int mods = baseModifiers(e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
        final int keyCode = e.getCode().getCode();
        if (pressedKeyCode == keyCode) pressedKeyCode = 0;
        runWithBrowserHost(
                false,
                host -> host.sendKeyEvent(
                        new CefKeyEvent(KEY_KEYUP, mods, keyCode, keyCode, 0, (char) 0, (char) 0, 0)));
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
            // XXX: The publication/queue-drain race requires this remove claim for exactly-once execution; remove it
            // only when browser publication and action enqueue share one atomic handoff.
            current = browser;
            if (current != null && pendingBrowserActions.remove(action)) action.accept(current);
        }
    }

    void runWithBrowserHost(boolean failIfUnavailable, Consumer<CefBrowserHost> action) {
        runWhenBrowserReady(failIfUnavailable, current -> current.getHost().ifPresent(action));
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
        final BrowserHandle created = new BrowserHandle(browser);
        if (releaseRequested) {
            releaseBrowserAfterClose(created);
            // Closing from onAfterCreated can invalidate CEF's popup delegate before the surrounding CreateBrowser /
            // AddNewContents call returns. A CEF UI task establishes the required callback-stack boundary.
            if (!CefGlobals.postTask(CEF_UI_THREAD, new CefTask() {
                @Override
                public void execute() {
                    created.close(true);
                }
            })) {
                CREATED_BROWSER_CLOSER.execute(() -> created.close(true));
            }
            return;
        }
        BrowserHandle current = this.browser;
        if (current == null) {
            current = created;
            this.browser = created;
            browserCleanup.browser = created;
        }
        Consumer<BrowserHandle> action;
        while ((action = pendingBrowserActions.poll()) != null) {
            action.accept(current);
        }
        Platform.runLater(() -> {
            requestFocus();
            browser.getHost().ifPresent(h -> h.setFocus(true));
            browserCreated = true;
            browserCreationPosted = false;
            applyZoom();
            engine.fireVisibilityChanged(true);
            requestViewRefresh(true);
            // XXX: CEF <= 75 software OSR skips onPaint for a compositor draw that lands after the renderer stops
            // requesting begin frames, so the last frame of an update stays hidden until the next invalidate; remove
            // with CEF 75 support.
            if (SystemBootstrap.runtimeCefMajor().orElse(Integer.MAX_VALUE) <= 75 && !releaseRequested) {
                periodicRepaint.play();
            }
        });
    }

    boolean handleBeforePopup(CefWindowInfo.Mutable windowInfo, @Nullable AtomicReference<CefClient> clientRef) {
        if (clientRef == null) return true;
        return Optional.ofNullable(engine.getCreatePopupHandler())
                .map(handler -> {
                    final AtomicReference<@Nullable CefWebEngine> popupEngine = new AtomicReference<>();
                    runOnFxAndWait(() -> {
                        final CefWebEngine created = handler.call(new CefPopupFeatures(false, false, false, true));
                        if (created != null) {
                            created.getView().browserCreationPosted = true;
                        }
                        popupEngine.set(created);
                    });
                    final CefWebEngine createdEngine = popupEngine.get();
                    if (createdEngine == null) return true;
                    final CefRect popupBounds = Cef.configureWindowlessPopup(windowInfo, new CefRect(0, 0, 800, 600));
                    createdEngine.getView().popupBrowser = true;
                    createdEngine.getView().updateDetachedBounds(popupBounds, true);
                    clientRef.set(createdEngine.getView().getCefClient());
                    return false;
                })
                .orElse(true);
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
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<@Nullable Throwable> failure = new AtomicReference<>();
        try {
            Platform.runLater(() -> {
                try {
                    runnable.run();
                } catch (Throwable thrown) {
                    failure.set(thrown);
                } finally {
                    latch.countDown();
                }
            });
        } catch (RuntimeException rejected) {
            throw new IllegalStateException("JavaFX rejected a CEF callback", rejected);
        }
        try {
            if (!latch.await(FX_CALLBACK_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                throw new IllegalStateException(
                        "JavaFX did not process a CEF callback within " + FX_CALLBACK_TIMEOUT_SECONDS + " seconds");
            }
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("interrupted while waiting for JavaFX callback", interrupted);
        }
        final Throwable thrown = failure.get();
        if (thrown instanceof RuntimeException) throw (RuntimeException) thrown;
        if (thrown instanceof Error) throw (Error) thrown;
    }

    private static final class BrowserCleanupAction implements Runnable {
        @Nullable
        volatile BrowserHandle browser;

        @Override
        public void run() {
            final BrowserHandle h = browser;
            browser = null;
            if (h != null) h.close(true);
        }
    }

    private static final class BrowserHandle {
        private final CefBrowser browser;
        private final AtomicBoolean closeRequested = new AtomicBoolean();
        private final AtomicBoolean released = new AtomicBoolean();

        private BrowserHandle(CefBrowser browser) {
            this.browser = Objects.requireNonNull(browser, "browser");
        }

        private CefBrowser getBrowser() {
            return browser;
        }

        private Optional<CefBrowserHost> getHost() {
            if (released.get()) return Optional.empty();
            try {
                return browser.getHost();
            } catch (IllegalStateException alreadyReleased) {
                // A queued close may race the browser-closed callback, which releases the Java peer on the same
                // CEF UI thread. Treat only that completed lifecycle transition as an idempotent close.
                if (released.get()) return Optional.empty();
                throw alreadyReleased;
            }
        }

        private void close(boolean force) {
            if (!closeRequested.compareAndSet(false, true)) return;
            if (CefGlobals.currentlyOn(CEF_UI_THREAD) == 0) {
                final boolean posted = CefGlobals.postTask(CEF_UI_THREAD, new CefTask() {
                    @Override
                    public void execute() {
                        closeOnCefUi(force);
                    }
                });
                if (posted) return;
            }
            closeOnCefUi(force);
        }

        private void closeOnCefUi(boolean force) {
            getHost().ifPresent(host -> {
                try (host) {
                    host.closeBrowser(force);
                }
            });
        }

        private void release() {
            if (released.compareAndSet(false, true)) browser.close();
        }
    }
}
