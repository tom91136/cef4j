package net.kurobako.cef4j;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton managing the CEF lifecycle: initialization, message pump, and shutdown.
 *
 * <p>CEF requires that {@code cef_initialize}, {@code cef_do_message_loop_work}, {@code cef_shutdown}, and browser
 * creation all happen on the same thread (the "CEF UI thread"). When {@code externalMessagePump} is true, the caller is
 * responsible for driving the message loop by calling {@link #doMessageLoopWork()} periodically on the thread that
 * called {@link #initialize()}.
 *
 * <p>No AWT/Swing dependency.
 */
public final class CefApp {

    /** CEF application state. */
    public enum State {
        NONE,
        INITIALIZING,
        INITIALIZED,
        SHUTTING_DOWN,
        TERMINATED
    }

    private static final Logger log = LoggerFactory.getLogger(CefApp.class);
    private static final AtomicReference<CefApp> INSTANCE = new AtomicReference<>();

    private volatile State state = State.NONE;
    private final String cachePath;
    private final String userAgent;
    private final boolean externalMessagePump;
    private final String subprocessPath;
    private final String[] extraArgs;

    private CefApp(
            String cachePath,
            String userAgent,
            boolean externalMessagePump,
            String subprocessPath,
            String[] extraArgs) {
        this.cachePath = cachePath;
        this.userAgent = userAgent;
        this.externalMessagePump = externalMessagePump;
        this.subprocessPath = subprocessPath;
        this.extraArgs = extraArgs;
    }

    /**
     * Get or create the singleton CefApp instance with default settings.
     *
     * @return the CefApp singleton
     */
    public static CefApp getInstance() {
        return getInstance(null, null, true, null, null);
    }

    /**
     * Get or create the singleton CefApp instance.
     *
     * @param cachePath path for CEF cache (may be null for in-memory)
     * @param userAgent custom user-agent string (may be null for default)
     * @param externalMessagePump if true, caller drives the message loop via {@link #doMessageLoopWork()}
     * @param subprocessPath path to the CEF subprocess helper (may be null to use default)
     * @return the CefApp singleton
     */
    public static CefApp getInstance(
            String cachePath, String userAgent, boolean externalMessagePump, String subprocessPath) {
        return getInstance(cachePath, userAgent, externalMessagePump, subprocessPath, null);
    }

    /**
     * Get or create the singleton CefApp instance.
     *
     * @param cachePath path for CEF cache (may be null for in-memory)
     * @param userAgent custom user-agent string (may be null for default)
     * @param externalMessagePump if true, caller drives the message loop via {@link #doMessageLoopWork()}
     * @param subprocessPath path to the CEF subprocess helper (may be null to use default)
     * @param extraArgs additional command-line arguments passed to CEF (e.g. {@code "--enable-unsafe-swiftshader"}).
     *     {@code --no-zygote} is always added automatically on Linux/macOS. May be null.
     * @return the CefApp singleton
     */
    public static CefApp getInstance(
            String cachePath,
            String userAgent,
            boolean externalMessagePump,
            String subprocessPath,
            String[] extraArgs) {
        CefApp existing = INSTANCE.get();
        if (existing != null) return existing;

        CefApp app = new CefApp(cachePath, userAgent, externalMessagePump, subprocessPath, extraArgs);
        log.info("Initializing CEF with args: {}", extraArgs != null ? Arrays.asList(extraArgs) : "[]");
        if (INSTANCE.compareAndSet(null, app)) {
            return app;
        }
        return INSTANCE.get();
    }

    /**
     * Initialize CEF. Must be called before creating any browsers. Safe to call multiple times - subsequent calls are
     * no-ops.
     *
     * <p>When {@code externalMessagePump} is true, the caller must drive the message loop by calling
     * {@link #doMessageLoopWork()} periodically on the same thread that called this method.
     *
     * @throws IllegalStateException if CEF has been shut down
     */
    public synchronized void initialize() {
        if (state == State.INITIALIZED) return;
        if (state == State.TERMINATED || state == State.SHUTTING_DOWN) {
            throw new IllegalStateException("CEF has been shut down and cannot be reinitialized");
        }

        state = State.INITIALIZING;
        log.info("CEF initializing");

        if (!SystemBootstrap.isLoaded()) {
            SystemBootstrap.load();
        }

        // Auto-resolve subprocess helper if not explicitly provided
        String resolvedSubprocessPath = subprocessPath;
        if (resolvedSubprocessPath == null) {
            resolvedSubprocessPath = SystemBootstrap.getHelperPath();
        }

        // Resolve the resources path (where .pak, .dat, locales/ are).
        // When using platform JAR, resources are extracted to the cache dir.
        // When using LIBCEF_DIR, resources are in the Release/ directory.
        String resourcesPath = null;
        Path extDir = SystemBootstrap.getExtractionDir();
        if (extDir != null) {
            resourcesPath = extDir.toAbsolutePath().toString();
        }
        if (resourcesPath == null) {
            Path libcefDir = SystemBootstrap.getLibcefDir();
            if (libcefDir != null) {
                resourcesPath = libcefDir.toAbsolutePath().toString();
            }
        }

        log.debug(
                "CEF config: cachePath={}, subprocess={}, resources={}, externalMessagePump={}",
                cachePath,
                resolvedSubprocessPath,
                resourcesPath,
                externalMessagePump);

        boolean ok = N_Initialize(
                cachePath, userAgent, true, externalMessagePump, resolvedSubprocessPath, resourcesPath, extraArgs);
        if (!ok) {
            state = State.NONE;
            log.error("cef_initialize failed");
            throw new RuntimeException("cef_initialize failed");
        }

        state = State.INITIALIZED;
        log.info("CEF initialized");
    }

    /**
     * Create a new CefClient for handler registration and browser creation. Initializes CEF if not already done.
     *
     * @return a new CefClient instance
     */
    public CefClient createClient() {
        if (state != State.INITIALIZED) {
            initialize();
        }
        log.debug("Created new CefClient");
        return new CefClient();
    }

    /**
     * Shut down CEF cleanly. Calls cef_shutdown and clears the singleton. Must be called on the same thread that called
     * {@link #initialize()}.
     */
    public synchronized void dispose() {
        if (state != State.INITIALIZED) return;

        state = State.SHUTTING_DOWN;
        log.info("CEF shutting down");

        N_Shutdown();
        state = State.TERMINATED;
        INSTANCE.set(null);
        log.info("CEF terminated");
    }

    /**
     * Returns the current CEF application state.
     *
     * @return the current state
     */
    public State getState() {
        return state;
    }

    /**
     * Perform a single iteration of CEF message loop work. Must be called on the same thread that called
     * {@link #initialize()}.
     */
    public void doMessageLoopWork() {
        if (state == State.INITIALIZED) {
            N_DoMessageLoopWork();
        }
    }

    // Native methods - implemented in cef_app.cpp
    private native boolean N_Initialize(
            String cachePath,
            String userAgent,
            boolean windowlessMode,
            boolean externalMessagePump,
            String subprocessPath,
            String resourcesPath,
            String[] extraArgs);

    private native void N_Shutdown();

    private native void N_DoMessageLoopWork();

    private native void N_RunMessageLoop();

    private native void N_QuitMessageLoop();
}
