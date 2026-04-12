package net.kurobako.cef4j;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefCommandLine;
import net.kurobako.cef4j.gen.CefGlobals;
import net.kurobako.cef4j.gen.CefMainArgs;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefSettings.Mutable;
import net.kurobako.cef4j.gen.CefWindowInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton managing the CEF lifecycle: configuration, initialization, message pump, and shutdown.
 *
 * <p>CEF requires that {@code cef_initialize}, {@code cef_do_message_loop_work}, {@code cef_shutdown}, and browser
 * creation all happen on the same thread (the "CEF UI thread"). When {@code externalMessagePump} is true, the caller is
 * responsible for driving the message loop by calling {@link #doMessageLoopWork()} periodically on the thread that
 * called {@link #initialise(Mutable, List)}}.
 *
 * <p>Usage:
 *
 * <pre>{@code
 * Cef.INSTANCE.initialise(settings, args);
 * }</pre>
 *
 * <p>CEF cannot be re-initialized after {@link #terminate()}.
 */
@SuppressWarnings("unused")
public enum Cef {
    INSTANCE;

    /** CEF state. */
    public enum State {
        /** Not yet configured or initialized. */
        UNINITIALISED,
        /** CEF is running. */
        INITIALISED,
        /** {@code cef_shutdown} in progress. */
        SHUTTING_DOWN,
        /** CEF has been shut down. Cannot be reused. */
        TERMINATED
    }

    private static final Logger log = LoggerFactory.getLogger(Cef.class);

    private volatile State state = State.UNINITIALISED;
    private volatile Thread initThread = null;

    private void checkState() {
        if (state != State.INITIALISED)
            throw new IllegalStateException("CEF must be in state INITIALISED, was: " + state);
        if (Thread.currentThread() != initThread) {
            throw new IllegalStateException(
                    "Must be called on the CEF thread (the thread that called initialise()): current="
                            + Thread.currentThread() + ", cef=" + initThread);
        }
    }

    /**
     * Initialise CEF on the current thread. All subsequent CEF lifecycle calls ({@link #terminate()},
     * {@link #doMessageLoopWork()}, etc.) must be made on the same thread. Safe to call multiple times - subsequent
     * calls are no-ops if CEF is already initialised. Re-initialising after {@link #terminate()} is not supported per
     * CEF design.
     *
     * <p>If using {@code CefWebView}, prefer {@code CefWebView.initialise()} which calls this internally. If a
     * higher-level library (e.g. {@code CefMonacoPane}) provides its own {@code initialise()}, call that first - it
     * configures custom schemes and handlers before calling through to this method. Less-specific initialisations that
     * follow are no-ops since CEF is already running.
     *
     * @throws IllegalStateException if CEF has been terminated
     */
    public synchronized void initialise(@Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs) {
        initialise(settings, extraArgs, null);
    }

    /**
     * Initialise CEF with a custom {@link CefApp} handler.
     *
     * <p>If {@code appHandler} is null, a default handler is used that only handles Windows command-line processing.
     * Use this overload when you need to register custom schemes via {@link CefApp#onRegisterCustomSchemes}.
     *
     * <p>Note: when a non-null {@code appHandler} is provided, the default Windows {@code extraArgs} command-line
     * processing is not applied - your handler is responsible for implementing
     * {@link CefApp#onBeforeCommandLineProcessing} if needed.
     *
     * @throws IllegalStateException if CEF has been shut down
     */
    public synchronized void initialise(
            @Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs, @Nullable CefApp appHandler) {
        Objects.requireNonNull(settings);
        Objects.requireNonNull(extraArgs);
        if (state == State.INITIALISED) return;
        if (state == State.TERMINATED || state == State.SHUTTING_DOWN) {
            throw new IllegalStateException("CEF has been shut down and cannot be reinitialized");
        }

        log.info("Initializing CEF with args: {}", extraArgs);

        if (!SystemBootstrap.isLoaded()) {
            SystemBootstrap.load();
        }

        if (settings.browserSubprocessPath == null) {
            settings.browserSubprocessPath = SystemBootstrap.getHelperPath();
        }

        if (settings.resourcesDirPath == null) {
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
            settings.resourcesDirPath = resourcesPath;
        }

        if (settings.localesDirPath == null) {
            settings.localesDirPath = settings.resourcesDirPath != null ? settings.resourcesDirPath + "/locales" : null;
        }
        settings.disableSignalHandlers = 1;

        log.debug(
                "CEF config: cachePath= subprocess={}, resources={}",
                settings.browserSubprocessPath,
                settings.resourcesDirPath);

        if (appHandler == null) {
            appHandler = new CefApp() {
                @Override
                public void onBeforeCommandLineProcessing(
                        @Nullable String processType, @Nullable CefCommandLine commandLine) {
                    if (OS.isWindows() && commandLine != null && (processType == null || processType.isEmpty())) {
                        for (String arg : extraArgs) {
                            if (arg != null && !arg.isEmpty()) {
                                commandLine.appendSwitch(arg.startsWith("--") ? arg.substring(2) : arg);
                            }
                        }
                    }
                }
            };
        }

        List<String> argv = List.of("cef4j");
        if (!extraArgs.isEmpty()) {
            argv = new java.util.ArrayList<>(1 + extraArgs.size());
            argv.add("cef4j");
            argv.addAll(extraArgs);
        }
        CefMainArgs args = new CefMainArgs(argv.size(), argv);
        final var result = CefGlobals.initialize(args, settings.toImmutable(), appHandler, null);
        if (result == 0) {
            state = State.UNINITIALISED;
            log.error("CefGlobals.initialize (cef_initialize) failed with error code: {}", result);
            throw new RuntimeException("CefGlobals.initialize (cef_initialize) failed with error code: " + result);
        }

        state = State.INITIALISED;
        initThread = Thread.currentThread();
        log.info("CEF initialized");
    }

    /**
     * Create a new browser synchronously. Must be called on the same thread that called {@link #initialise} and only
     * while CEF is in the {@link State#INITIALISED} state.
     *
     * @throws IllegalStateException if CEF is not initialised or called from the wrong thread
     */
    public CefBrowser createBrowser(CefClient client, String url, CefWindowInfo info, CefBrowserSettings settings) {
        checkState();
        return CefBrowserHost.createBrowserSync(info, client, url, settings, null, null)
                .orElseThrow();
    }

    /**
     * Shut down CEF cleanly. Must be called on the same thread that called {@link #initialise(Mutable, List)} when
     * using the external message pump (single-threaded mode). Safe to call multiple times - subsequent calls are
     * no-ops.
     *
     * <p>As per CEF design, after this call, CEF cannot be re-initialised in the same process (i.e. JVM). The singleton
     * remains accessible but all operations will throw {@link IllegalStateException}.
     */
    public synchronized void terminate() {
        if (state == State.UNINITIALISED || state == State.TERMINATED || state == State.SHUTTING_DOWN) return;
        checkState();

        state = State.SHUTTING_DOWN;
        log.info("CEF shutting down");

        int released = NativeCleaner.INSTANCE.releaseAll();
        log.info("Released {} outstanding NativePeers before shutdown", released);
        CefGlobals.shutdown();
        state = State.TERMINATED;
        log.info("CEF terminated");
    }

    /** Returns the current CEF application state. */
    public State getState() {
        return state;
    }

    /**
     * Perform a single iteration of CEF message loop work. Must be called on the same thread that called
     * {@link #initialise(Mutable, List)}}. This is only needed if {@link #initialise(Mutable, List)} is configured with
     * {@link CefSettings#externalMessagePump} set to true.
     *
     * @see CefGlobals#doMessageLoopWork()
     */
    public void doMessageLoopWork() {
        checkState();
        CefGlobals.doMessageLoopWork();
    }
}
