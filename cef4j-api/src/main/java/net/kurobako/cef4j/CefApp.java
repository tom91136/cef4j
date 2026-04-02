package net.kurobako.cef4j;

import java.nio.file.Path;
import java.util.Arrays;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefCommandLine;
import net.kurobako.cef4j.gen.NativePointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enum singleton managing the CEF lifecycle: configuration, initialization, message pump, and shutdown.
 *
 * <p>CEF requires that {@code cef_initialize}, {@code cef_do_message_loop_work}, {@code cef_shutdown}, and browser
 * creation all happen on the same thread (the "CEF UI thread"). When {@code externalMessagePump} is true, the caller is
 * responsible for driving the message loop by calling {@link #doMessageLoopWork()} periodically on the thread that
 * called {@link #initialize()}.
 *
 * <p>Usage:
 *
 * <pre>{@code
 * CefApp.INSTANCE
 *     .cachePath(path)
 *     .offScreenRendering(true)
 *     .initialize();
 * }</pre>
 *
 * <p>CEF cannot be re-initialized after {@link #dispose()}.
 */
public enum CefApp {
    INSTANCE;

    /** CEF application state. */
    public enum State {
        /** Not yet configured or initialized. */
        UNCONFIGURED,
        /** Configuration set but not yet initialized. */
        CONFIGURED,
        /** {@code cef_initialize} in progress. */
        INITIALIZING,
        /** CEF is running. */
        INITIALIZED,
        /** {@code cef_shutdown} in progress. */
        SHUTTING_DOWN,
        /** CEF has been shut down. Cannot be reused. */
        TERMINATED
    }

    private static final Logger log = LoggerFactory.getLogger(CefApp.class);

    private volatile State state = State.UNCONFIGURED;
    private String cachePath;
    private String userAgent;
    private boolean externalMessagePump = true;
    private String subprocessPath;
    private String[] extraArgs;

    /**
     * Set the cache/user-data directory. Null for in-memory.
     *
     * @return this instance for chaining
     * @throws IllegalStateException if already initialized or terminated
     */
    public CefApp cachePath(String cachePath) {
        checkConfigurable();
        this.cachePath = cachePath;
        this.state = State.CONFIGURED;
        return this;
    }

    /**
     * Set a custom user-agent string. Null for default.
     *
     * @return this instance for chaining
     * @throws IllegalStateException if already initialized or terminated
     */
    public CefApp userAgent(String userAgent) {
        checkConfigurable();
        this.userAgent = userAgent;
        this.state = State.CONFIGURED;
        return this;
    }

    /**
     * Set whether the caller drives the message loop externally via {@link #doMessageLoopWork()}. Default is true.
     *
     * @return this instance for chaining
     * @throws IllegalStateException if already initialized or terminated
     */
    public CefApp externalMessagePump(boolean externalMessagePump) {
        checkConfigurable();
        this.externalMessagePump = externalMessagePump;
        this.state = State.CONFIGURED;
        return this;
    }

    /**
     * Set the path to the CEF subprocess helper executable. Null to auto-detect.
     *
     * @return this instance for chaining
     * @throws IllegalStateException if already initialized or terminated
     */
    public CefApp subprocessPath(String subprocessPath) {
        checkConfigurable();
        this.subprocessPath = subprocessPath;
        this.state = State.CONFIGURED;
        return this;
    }

    /**
     * Set additional command-line arguments passed to CEF (e.g. {@code "--enable-unsafe-swiftshader"}).
     * {@code --no-zygote} is always added automatically on Linux/macOS. Null for none.
     *
     * @return this instance for chaining
     * @throws IllegalStateException if already initialized or terminated
     */
    public CefApp extraArgs(String... extraArgs) {
        checkConfigurable();
        this.extraArgs = extraArgs;
        this.state = State.CONFIGURED;
        return this;
    }

    private void checkConfigurable() {
        State s = state;
        if (s == State.INITIALIZED || s == State.INITIALIZING) {
            throw new IllegalStateException("CEF is already initialized - configuration is locked");
        }
        if (s == State.TERMINATED || s == State.SHUTTING_DOWN) {
            throw new IllegalStateException("CEF has been shut down and cannot be reconfigured");
        }
    }

    /**
     * Create a native {@code cef_main_args_t} suitable for passing to {@code cef_initialize}.
     *
     * <p>On Linux/macOS, builds an {@code argc/argv} with {@code --no-zygote} prepended, followed by any extra args.
     * Some flags (e.g. {@code --no-zygote}, {@code --ozone-platform}) must be in argv to take effect before CEF's
     * subprocess fork. On Windows, uses {@code GetModuleHandle(NULL)} and ignores extra args (they go through
     * {@code CefApp::OnBeforeCommandLineProcessing} instead).
     *
     * <p>The returned pointer must be {@link NativePointer.Managed#close() closed} after use. CEF copies what it needs
     * during {@code cef_initialize}, so it can be freed immediately after.
     *
     * @param extraArgs additional command-line arguments (may be null or empty). Used on Linux/macOS only.
     * @return a managed pointer to the allocated cef_main_args_t
     */
    static NativePointer.Managed createMainArgs(String[] extraArgs) {
        long addr = nCreateMainArgs(extraArgs);
        return new NativePointer.Managed(addr, CefApp::nFreeMainArgs);
    }

    private static native long nCreateMainArgs(String[] extraArgs);

    private static native void nFreeMainArgs(long address);

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
        log.info("Initializing CEF with args: {}", extraArgs != null ? Arrays.asList(extraArgs) : "[]");

        if (!SystemBootstrap.isLoaded()) {
            SystemBootstrap.load();
        }

        // Auto-resolve subprocess helper if not explicitly provided
        String resolvedSubprocessPath = subprocessPath;
        if (resolvedSubprocessPath == null) {
            resolvedSubprocessPath = SystemBootstrap.getHelperPath();
        }

        // Resolve the resources path (where .pak, .dat, locales/ are).
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

        String localesPath = resourcesPath != null ? resourcesPath + "/locales" : null;
        var settings = new net.kurobako.cef4j.gen.CefSettings.Mutable();
        settings.noSandbox = 1;
        settings.browserSubprocessPath = resolvedSubprocessPath;
        settings.externalMessagePump = externalMessagePump ? 1 : 0;
        settings.windowlessRenderingEnabled = 1;
        settings.cachePath = cachePath;
        settings.rootCachePath = cachePath;
        settings.userAgent = userAgent;
        settings.resourcesDirPath = resourcesPath;
        settings.localesDirPath = localesPath;
        settings.disableSignalHandlers = 1;

        // On Linux/macOS, extra args go into cef_main_args_t.argv so flags like
        // --ozone-platform take effect before subprocess fork. On Windows,
        // cef_main_args_t only holds HINSTANCE, so extra args go through
        // OnBeforeCommandLineProcessing instead.
        boolean isWindows = OS.isWindows();
        String[] mainExtraArgs = isWindows ? null : this.extraArgs;
        String[] handlerExtraArgs = isWindows ? this.extraArgs : null;

        net.kurobako.cef4j.gen.CefApp appHandler = (handlerExtraArgs != null && handlerExtraArgs.length > 0)
                ? new net.kurobako.cef4j.gen.CefApp() {
                    @Override
                    public void onBeforeCommandLineProcessing(
                            @Nullable String processType, @Nullable CefCommandLine commandLine) {
                        if (commandLine != null && (processType == null || processType.isEmpty())) {
                            for (String arg : handlerExtraArgs) {
                                if (arg != null && !arg.isEmpty()) {
                                    commandLine.appendSwitch(arg.startsWith("--") ? arg.substring(2) : arg);
                                }
                            }
                        }
                    }
                }
                : null;

        // CEF copies main_args during cef_initialize, so we free immediately after.
        try (var args = createMainArgs(mainExtraArgs)) {
            int result = net.kurobako.cef4j.gen.CefGlobals.initialize(args, settings.toImmutable(), appHandler, null);
            if (result == 0) {
                state = State.UNCONFIGURED;
                log.error("cef_initialize failed");
                throw new RuntimeException("cef_initialize failed");
            }
        }

        state = State.INITIALIZED;
        log.info("CEF initialized");
    }

    /**
     * Create an offscreen browser with the given client and URL.
     *
     * @param client the CefClient implementation providing handler callbacks
     * @param url the initial URL to load
     * @return a new CefBrowserOsr instance
     */
    public CefBrowserOsr createBrowser(net.kurobako.cef4j.gen.CefClient client, String url) {
        return createBrowser(client, url, 0);
    }

    /**
     * Create an offscreen browser with the given client, URL, and frame rate.
     *
     * @param client the CefClient implementation providing handler callbacks
     * @param url the initial URL to load
     * @param frameRate the target frame rate for OSR rendering (0 for default 60fps)
     * @return a new CefBrowserOsr instance
     */
    public CefBrowserOsr createBrowser(net.kurobako.cef4j.gen.CefClient client, String url, int frameRate) {
        if (state != State.INITIALIZED) {
            initialize();
        }
        return new CefBrowserOsr(client, url, frameRate);
    }

    /**
     * Shut down CEF cleanly. Must be called on the same thread that called {@link #initialize()}.
     *
     * <p>After this call, CEF cannot be re-initialized in the same process. The singleton remains accessible but all
     * operations will throw {@link IllegalStateException}.
     */
    public synchronized void dispose() {
        if (state != State.INITIALIZED) return;

        state = State.SHUTTING_DOWN;
        log.info("CEF shutting down");

        int released = NativeCleaner.INSTANCE.releaseAll();
        log.info("Released {} outstanding NativePeers before shutdown", released);
        net.kurobako.cef4j.gen.CefGlobals.shutdown();
        state = State.TERMINATED;
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
            net.kurobako.cef4j.gen.CefGlobals.doMessageLoopWork();
        }
    }
}
