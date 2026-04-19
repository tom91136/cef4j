package net.kurobako.cef4j;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
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
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefSettings.Mutable;
import net.kurobako.cef4j.gen.CefWindowInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton managing the CEF lifecycle: configuration, initialization, message loop, and shutdown.
 *
 * <p>The message-loop thread is chosen per platform by {@link #osrLaunchArgs()} and honoured automatically by
 * {@link #initialise(Mutable, List)}:
 *
 * <ul>
 *   <li><b>macOS</b> — {@code cef_initialize()} is dispatched onto Thread 0 (the AppKit main thread) via GCD with
 *       {@code externalMessagePump=1}. A CFRunLoop timer on Thread 0 calls {@code cef_do_message_loop_work()} at ~60
 *       Hz, coexisting with AWT/Glass event handling. No {@code -XstartOnFirstThread} is required. {@link #terminate()}
 *       dispatches cleanup ({@code cef_shutdown}) to Thread 0 via GCD.
 *   <li><b>Linux</b> — {@code multiThreadedMessageLoop=1}. CEF runs its own UI thread internally.
 *   <li><b>Windows</b> — CEF runs on a daemon thread created by {@code initialise()}.
 * </ul>
 *
 * <p>When {@code externalMessagePump} is explicitly set by the caller (advanced use), cef4j skips the internal
 * lifecycle and the caller must drive the loop via {@link #doMessageLoopWork()} on the init thread.
 *
 * <p>Register {@link CefApp} handlers (e.g. for custom schemes) via {@link #addAppHandler(CefApp)} before calling
 * {@link #initialise(Mutable, List)} or creating any browser view. Registrations are thread-safe and compose: all
 * registered handlers are invoked via a generated delegating wrapper.
 *
 * <p>Usage:
 *
 * <pre>{@code
 * Cef.INSTANCE.addAppHandler(new CefApp() { ... });
 * Cef.INSTANCE.initialise(settings, args);
 * // ... use CEF ...
 * Cef.INSTANCE.terminate();
 * }</pre>
 *
 * <p>CEF cannot be re-initialized after {@link #terminate()}.
 */
@SuppressWarnings({"unused", "ImmutableEnumChecker"})
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

    /**
     * Mutable launch configuration: a {@link CefSettings.Mutable} the caller can tweak and the argv list passed to CEF.
     *
     * <p>Obtained from {@link #osrLaunchArgs()} pre-populated with defaults suitable for off-screen rendering UI
     * embeddings (JavaFX, Swing). Both the {@code settings} object and the {@code args} list are mutable - callers may
     * adjust before passing to {@link #initialise(CefSettings.Mutable, List)}.
     */
    public static final class LaunchArgs {
        private final CefSettings.Mutable settings;
        private final List<String> args;

        public LaunchArgs(@Nonnull CefSettings.Mutable settings, @Nonnull List<String> args) {
            this.settings = Objects.requireNonNull(settings, "settings");
            this.args = List.copyOf(Objects.requireNonNull(args, "args"));
        }

        @Nonnull
        public CefSettings.Mutable settings() {
            return settings;
        }

        @Nonnull
        public List<String> args() {
            return args;
        }
    }

    /**
     * Returns a fresh {@link LaunchArgs} configured for OSR UI embeddings (Swing, JavaFX).
     *
     * <p>Encodes the platform-specific message-loop mode that avoids conflicts with the host UI toolkit:
     *
     * <ul>
     *   <li>macOS: both loop modes off - {@link #initialiseInternal} forces {@code externalMessagePump=1} and
     *       dispatches {@code cef_initialize()} onto Thread 0 (the AppKit main thread) via GCD, then installs a
     *       CFRunLoop timer that calls {@code cef_do_message_loop_work()} at ~60 Hz on Thread 0. This avoids claiming
     *       {@code [NSApp run]} (which conflicts with AWT/Glass) while satisfying CEF's requirement that UI callbacks
     *       land on the same thread that called {@code cef_initialize()}. No {@code -XstartOnFirstThread} is required.
     *   <li>Linux: {@code multiThreadedMessageLoop=1} plus {@code --ozone-platform=x11} - Glass-GTK3 owns the process
     *       GDK default display; CEF must run its own UI thread rather than a daemon-wrapped loop.
     *   <li>Windows: both loop modes off - CEF runs on an internal daemon thread managed by cef4j.
     * </ul>
     *
     * <p>Always sets {@code windowlessRenderingEnabled=1} and adds {@code --disable-popup-blocking}. Callers typically
     * set {@code cachePath} and any extra args before passing to {@link #initialise(CefSettings.Mutable, List)}.
     */
    public static LaunchArgs osrLaunchArgs() {
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.windowlessRenderingEnabled = 1;
        if (OS.isLinux()) {
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 1;
        } else {
            // macOS: cef4j dispatches init + message loop onto Thread 0 via GCD.
            // Windows: CEF runs on a daemon thread managed by cef4j.
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 0;
        }
        List<String> args = new ArrayList<>();
        args.add("--disable-popup-blocking");
        if (OS.isLinux()) args.add("--ozone-platform=x11");
        return new LaunchArgs(settings, args);
    }

    private static final Logger log = LoggerFactory.getLogger(Cef.class);

    private volatile State state = State.UNINITIALISED;
    private volatile @Nullable Thread initThread = null;
    private volatile boolean daemonManaged = false;
    private volatile boolean macOsManaged = false;
    private volatile @Nullable CountDownLatch shutdownLatch;
    private volatile @Nullable CefSettings activeSettings;
    private final CopyOnWriteArrayList<CefApp> appHandlers = new CopyOnWriteArrayList<>();

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
     * Register a {@link CefApp} handler. Must be called before CEF is initialised (either explicitly via
     * {@link #initialise(Mutable, List)} or implicitly via first browser view creation).
     *
     * <p>Multiple handlers may be registered; all receive each callback in registration order via a generated
     * delegating wrapper.
     *
     * @throws IllegalStateException if CEF is already initialised
     */
    public void addAppHandler(@Nonnull CefApp handler) {
        Objects.requireNonNull(handler, "handler");
        if (state != State.UNINITIALISED) {
            throw new IllegalStateException(
                    "CEF is already initialized -- register handlers before calling initialise() or creating any browser view. State="
                            + state);
        }
        appHandlers.addIfAbsent(handler);
    }

    /** Unregister a previously-added {@link CefApp} handler. No-op if not registered. Safe to call at any time. */
    public void removeAppHandler(@Nonnull CefApp handler) {
        Objects.requireNonNull(handler, "handler");
        appHandlers.remove(handler);
    }

    /**
     * Initialise CEF. Safe to call multiple times - subsequent calls are no-ops if CEF is already initialised.
     * Re-initialising after {@link #terminate()} is not supported per CEF design.
     *
     * <p>When {@code externalMessagePump} is false (the default), a daemon thread is created to run CEF's message loop.
     * This method blocks until initialisation completes, then returns. When {@code externalMessagePump} is true, the
     * caller must drive the message loop by calling {@link #doMessageLoopWork()} on the same thread.
     *
     * <p>Register {@link CefApp} handlers via {@link #addAppHandler(CefApp)} before this call. If no handlers are
     * registered, a default handler is installed that forwards {@code extraArgs} as command-line switches on Windows.
     *
     * @throws IllegalStateException if CEF has been terminated
     * @throws IllegalArgumentException if settings contain unsupported options (see {@link #initialiseUnsafe} to
     *     bypass)
     */
    public synchronized void initialise(@Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs) {
        validateSettings(settings);
        initialiseInternal(settings, extraArgs);
    }

    /**
     * Initialise CEF without validating settings. Use this only if you know what you are doing — certain configurations
     * (e.g. {@code multiThreadedMessageLoop=1} on macOS) will crash or hang the process.
     */
    public synchronized void initialiseUnsafe(@Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs) {
        initialiseInternal(settings, extraArgs);
    }

    private static void validateSettings(CefSettings.Mutable settings) {
        if (OS.isMacOS() && settings.multiThreadedMessageLoop != 0) {
            throw new IllegalArgumentException("multiThreadedMessageLoop is not supported on macOS. "
                    + "Leave both externalMessagePump and multiThreadedMessageLoop at 0 and cef4j will "
                    + "dispatch cef_initialize()/cef_run_message_loop() onto Thread 0 internally. "
                    + "Call initialiseUnsafe() to bypass this check.");
        }
    }

    private synchronized void initialiseInternal(
            @Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs) {
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
            Path baseDir = null;
            Path extDir = SystemBootstrap.getExtractionDir();
            if (extDir != null) {
                baseDir = extDir;
            } else {
                baseDir = SystemBootstrap.getLibcefDir();
            }
            if (baseDir != null) {
                if (OS.isMacOS()) {
                    Path frameworkDir = baseDir.resolve("Chromium Embedded Framework.framework");
                    if (settings.frameworkDirPath == null) {
                        settings.frameworkDirPath =
                                frameworkDir.toAbsolutePath().toString();
                    }
                    settings.resourcesDirPath =
                            frameworkDir.resolve("Resources").toAbsolutePath().toString();
                    settings.localesDirPath = settings.resourcesDirPath;
                } else {
                    settings.resourcesDirPath = baseDir.toAbsolutePath().toString();
                }
            }
        }

        if (!OS.isMacOS() && settings.localesDirPath == null) {
            settings.localesDirPath = settings.resourcesDirPath != null ? settings.resourcesDirPath + "/locales" : null;
        }
        setOptionalIntSetting(settings, "disableSignalHandlers", 1);

        // Sandbox is not supported in JVM-based CEF embeddings: the subprocess helper is a
        // separate executable and sandbox initialisation requires same-process control that the
        // JVM cannot provide. See JCEF context.cpp and CEF sandbox_setup docs.
        settings.noSandbox = 1;

        log.debug("CEF config: subprocess={}, resources={}", settings.browserSubprocessPath, settings.resourcesDirPath);

        CefApp appHandler = buildAppHandler(extraArgs);

        java.util.ArrayList<String> argv = new java.util.ArrayList<>(2 + extraArgs.size());
        argv.add("cef4j");
        argv.addAll(extraArgs);
        addArgIfMissing(argv, "--no-sandbox");
        if (OS.isLinux()) {
            addArgIfMissing(argv, "--disable-setuid-sandbox");
            addArgIfMissing(argv, "--disable-seccomp-filter-sandbox");
            addArgIfMissing(argv, "--disable-gpu-sandbox");
        }

        boolean useExternalPump = settings.externalMessagePump != 0;
        boolean useMultiThreadedLoop = settings.multiThreadedMessageLoop != 0;

        if (useExternalPump || useMultiThreadedLoop) {
            // External pump: caller drives the loop via doMessageLoopWork().
            // Multi-threaded loop: CEF spawns its own UI thread internally.
            // In both cases cef_initialize() must be followed by neither runMessageLoop() nor a
            // daemon-thread wrapper - runMessageLoop() is invalid under multiThreadedMessageLoop
            // and forking from a multithreaded JVM corrupts child-process FD inheritance.
            CefSettings immutable = settings.toImmutable();
            final int result = CefGlobals.initialize(new CefMainArgs(argv.size(), argv), immutable, appHandler, null);
            if (result == 0) {
                log.error("CefGlobals.initialize (cef_initialize) failed");
                throw new RuntimeException("CefGlobals.initialize (cef_initialize) failed");
            }
            activeSettings = immutable;
            state = State.INITIALISED;
            initThread = Thread.currentThread();
            daemonManaged = false;
        } else if (OS.isMacOS()) {
            // macOS path: dispatch cef_initialize() + cef_run_message_loop() + cleanup onto
            // Thread 0 (the AppKit main thread) in a single dispatch_async block.
            // cef_run_message_loop() calls [NSApp run] which becomes the event loop for Thread 0.
            // terminate() calls cef_quit_message_loop() + [NSApp stop:] and waits for the
            // dispatch block to finish via a semaphore.
            final CefSettings finalSettings = settings.toImmutable();
            final CefApp finalAppHandler = appHandler;
            final List<String> finalArgv = List.copyOf(argv);
            final AtomicReference<Throwable> initError = new AtomicReference<>();
            final int[] result = new int[1];
            SystemBootstrap.initAndRunOnMainThread(
                    () -> {
                        try {
                            result[0] = CefGlobals.initialize(
                                    new CefMainArgs(finalArgv.size(), finalArgv), finalSettings, finalAppHandler, null);
                        } catch (Throwable t) {
                            initError.set(t);
                        }
                    },
                    () -> {
                        // Runs on Thread 0 after cef_run_message_loop() returns.
                        int released = NativeCleaner.INSTANCE.releaseAll();
                        log.info("Released {} outstanding NativePeers before shutdown", released);
                    });
            Throwable err = initError.get();
            if (err != null) {
                state = State.UNINITIALISED;
                throw (err instanceof RuntimeException) ? (RuntimeException) err : new RuntimeException(err);
            }
            if (result[0] == 0) {
                state = State.UNINITIALISED;
                log.error("CefGlobals.initialize (cef_initialize) failed");
                throw new RuntimeException("CefGlobals.initialize (cef_initialize) failed");
            }
            activeSettings = finalSettings;
            state = State.INITIALISED;
            initThread = Thread.currentThread();
            daemonManaged = false;
            macOsManaged = true;
        } else {
            // Daemon thread path: CEF init + message loop run on a dedicated thread.
            CountDownLatch initLatch = new CountDownLatch(1);
            AtomicReference<Throwable> initError = new AtomicReference<>();
            CountDownLatch sdLatch = new CountDownLatch(1);
            this.shutdownLatch = sdLatch;

            final CefSettings finalSettings = settings.toImmutable();
            final CefApp finalAppHandler = appHandler;
            final List<String> finalArgv = List.copyOf(argv);

            Thread daemon = new Thread(
                    () -> {
                        try {
                            int result = CefGlobals.initialize(
                                    new CefMainArgs(finalArgv.size(), finalArgv), finalSettings, finalAppHandler, null);
                            if (result == 0) {
                                initError.set(new RuntimeException("CefGlobals.initialize (cef_initialize) failed"));
                                return;
                            }
                            log.info("CEF initialized on daemon thread");
                            initLatch.countDown();

                            CefGlobals.runMessageLoop();

                            log.info("CEF message loop exited, shutting down");
                            int released = NativeCleaner.INSTANCE.releaseAll();
                            log.info(
                                    "Released {} outstanding NativePeers from daemon thread before shutdown", released);
                            CefGlobals.shutdown();
                            log.info("CEF terminated");
                        } catch (Throwable t) {
                            initError.compareAndSet(null, t);
                        } finally {
                            initLatch.countDown();
                            sdLatch.countDown();
                        }
                    },
                    "cef4j-message-loop");
            daemon.setDaemon(true);
            daemon.start();

            try {
                initLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted waiting for CEF initialization", e);
            }

            Throwable error = initError.get();
            if (error != null) {
                state = State.UNINITIALISED;
                throw (error instanceof RuntimeException) ? (RuntimeException) error : new RuntimeException(error);
            }

            activeSettings = finalSettings;
            state = State.INITIALISED;
            initThread = daemon;
            daemonManaged = true;
        }

        log.info("CEF initialized");
        if (activeSettings != null && activeSettings.cachePath != null) {
            NativeStderr.setCrashLogPath(activeSettings.cachePath);
        }
    }

    private CefApp buildAppHandler(List<String> extraArgs) {
        CefApp windowsArgsHandler = new CefApp() {
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
        if (appHandlers.isEmpty()) {
            return windowsArgsHandler;
        }
        java.util.ArrayList<CefApp> composed = new java.util.ArrayList<>(appHandlers.size() + 1);
        composed.add(windowsArgsHandler);
        composed.addAll(appHandlers);
        return new CefApp.Delegating(List.copyOf(composed));
    }

    private static void addArgIfMissing(List<String> args, String arg) {
        if (!args.contains(arg)) {
            args.add(arg);
        }
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
     * Shut down CEF cleanly. Can be called from any thread. Safe to call multiple times - subsequent calls are no-ops.
     *
     * <p>When the daemon thread manages the message loop, this method signals the loop to exit and waits for shutdown
     * to complete. When using the external message pump, this must be called on the init thread.
     *
     * <p><b>macOS:</b> after this method returns, the caller should terminate the JVM via
     * {@code Runtime.getRuntime().halt(0)} rather than {@code System.exit(0)} or normal return. CEF's CFRunLoop
     * observers remain registered (because {@code cef_shutdown()} is skipped due to async browser close) and normal JVM
     * teardown would fire them, causing a CHECK failure. The native side parks Thread 0 for up to 5 seconds as a safety
     * net; if the JVM hasn't halted by then, it calls {@code _exit(0)}.
     *
     * <p>As per CEF design, after this call, CEF cannot be re-initialised in the same process (i.e. JVM). The singleton
     * remains accessible but all operations will throw {@link IllegalStateException}.
     */
    public void terminate() {
        // Transition to SHUTTING_DOWN under the monitor, then release it before blocking calls.
        // Holding the monitor during dispatch_sync/semaphore_wait would deadlock if Thread 0
        // (or the daemon thread) tries to enter any synchronized Cef method during shutdown.
        boolean isMacOs;
        boolean isDaemon;
        synchronized (this) {
            if (state == State.UNINITIALISED || state == State.TERMINATED || state == State.SHUTTING_DOWN) return;

            if (!daemonManaged && !macOsManaged && Thread.currentThread() != initThread) {
                throw new IllegalStateException(
                        "terminate() must be called on the init thread for non-daemon-managed CEF: current="
                                + Thread.currentThread() + ", cef=" + initThread);
            }

            state = State.SHUTTING_DOWN;
            isMacOs = macOsManaged;
            isDaemon = daemonManaged;
        }
        log.info("CEF shutting down");

        if (isMacOs) {
            // Quit the message loop (cef_quit_message_loop + cef4j_stop_nsapp) and
            // wait for the dispatch block to finish cleanup (NativeCleaner.releaseAll).
            // cef_shutdown() is skipped — see terminate() javadoc.
            SystemBootstrap.quitAndWaitMainThreadMessageLoop();
        } else if (isDaemon) {
            // Signal the daemon thread's runMessageLoop() to return; cleanup runs there.
            CefGlobals.quitMessageLoop();
            try {
                if (shutdownLatch != null) {
                    shutdownLatch.await();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("Interrupted waiting for CEF shutdown");
            }
        } else {
            // External message pump / multithreaded loop path: clean-up runs on the calling thread.
            int released = NativeCleaner.INSTANCE.releaseAll();
            log.info("Released {} outstanding NativePeers before shutdown", released);
            CefGlobals.shutdown();
        }

        synchronized (this) {
            state = State.TERMINATED;
        }
        log.info("CEF terminated");
    }

    /**
     * Create a {@link CefWindowInfo} configured for windowless (off-screen) rendering with the given bounds. Selects
     * the correct platform-specific implementation automatically.
     */
    public static CefWindowInfo createWindowlessInfo(CefRect bounds) {
        if (OS.isMacOS()) {
            var wi = new net.kurobako.cef4j.gen.mac.CefWindowInfo.Mutable();
            wi.bounds = bounds;
            wi.windowlessRenderingEnabled = 1;
            return wi.toImmutable();
        } else if (OS.isWindows()) {
            var wi = new net.kurobako.cef4j.gen.win.CefWindowInfo.Mutable();
            wi.bounds = bounds;
            wi.windowlessRenderingEnabled = 1;
            return wi.toImmutable();
        } else {
            var wi = new net.kurobako.cef4j.gen.linux.CefWindowInfo.Mutable();
            wi.bounds = bounds;
            wi.windowlessRenderingEnabled = 1;
            return wi.toImmutable();
        }
    }

    /** Returns the current CEF application state. */
    public State getState() {
        return state;
    }

    /**
     * Returns the settings CEF was initialized with, or {@link Optional#empty()} if CEF has not yet been initialised.
     * Used by lazy-init paths to validate compatibility before adding a new browser view.
     */
    public Optional<CefSettings> getActiveSettings() {
        return Optional.ofNullable(activeSettings);
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

    private static void setOptionalIntSetting(CefSettings.Mutable settings, String fieldName, int value) {
        try {
            var field = settings.getClass().getField(fieldName);
            field.setInt(settings, value);
        } catch (NoSuchFieldException ignored) {
            // Older generated settings structs may not expose newer optional fields.
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to set CEF setting: " + fieldName, e);
        }
    }
}
