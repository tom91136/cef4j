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
 * <p>The message-loop thread is chosen per platform by {@link #osrLaunchArgs()} and managed by
 * {@link #initialise(Mutable, List)}. On macOS cef4j performs initialization and shutdown on the AppKit main thread; on
 * Linux CEF manages its own UI thread; on Windows cef4j manages a daemon thread.
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
            this.args = new ArrayList<>(Objects.requireNonNull(args, "args"));
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
     * <p>Always sets {@code windowlessRenderingEnabled=1} and adds {@code --disable-popup-blocking}. Callers must set
     * {@code noSandbox=1} to acknowledge that cef4j's direct-launch distribution cannot initialize CEF's supported
     * sandbox before passing the settings to {@link #initialise(CefSettings.Mutable, List)}.
     */
    public static LaunchArgs osrLaunchArgs() {
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.windowlessRenderingEnabled = 1;
        if (OS.isLinux()) {
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 1;
        } else {
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
    private final Object lifecycleLock = new Object();

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
    public void initialise(@Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs) {
        validateSettings(settings);
        initialiseInternal(settings, extraArgs);
    }

    /**
     * Initialise CEF without validating message-loop settings. This does not bypass the required {@code noSandbox=1}
     * acknowledgement. Use this only if you know what you are doing — certain configurations (e.g.
     * {@code multiThreadedMessageLoop=1} on macOS) will crash or hang the process.
     */
    public void initialiseUnsafe(@Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs) {
        validateSandboxAvailability(settings);
        initialiseInternal(settings, extraArgs);
    }

    static void validateSettings(CefSettings.Mutable settings) {
        validateSandboxAvailability(settings);
        if (OS.isMacOS() && settings.multiThreadedMessageLoop != 0) {
            throw new IllegalArgumentException("multiThreadedMessageLoop is not supported on macOS. "
                    + "Leave both externalMessagePump and multiThreadedMessageLoop at 0 and cef4j will "
                    + "dispatch cef_initialize()/cef_run_message_loop() onto Thread 0 internally. "
                    + "Call initialiseUnsafe() to bypass this check.");
        }
    }

    private static void validateSandboxAvailability(CefSettings.Mutable settings) {
        if (settings.noSandbox == 0) {
            throw new IllegalArgumentException(
                    "CEF sandboxing is unavailable with cef4j's direct-launch distribution. Set noSandbox=1 "
                            + "to explicitly accept unsandboxed renderer, GPU, and utility processes.");
        }
    }

    private void initialiseInternal(@Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs) {
        synchronized (lifecycleLock) {
            Objects.requireNonNull(settings);
            Objects.requireNonNull(extraArgs);
            if (state == State.INITIALISED) return;
            if (state == State.TERMINATED || state == State.SHUTTING_DOWN) {
                throw new IllegalStateException("CEF has been shut down and cannot be reinitialized");
            }

            log.debug("Initializing CEF with args: {}", extraArgs);

            if (!SystemBootstrap.isLoaded()) {
                SystemBootstrap.load();
            }

            if (settings.browserSubprocessPath == null) {
                settings.browserSubprocessPath = SystemBootstrap.helperPath().orElse(null);
            }

            if (settings.resourcesDirPath == null) {
                Path baseDir = SystemBootstrap.extractionDir()
                        .or(() -> SystemBootstrap.libcefDir())
                        .orElse(null);
                if (baseDir != null) {
                    if (OS.isMacOS()) {
                        Path frameworkDir = baseDir.resolve("Chromium Embedded Framework.framework");
                        if (settings.frameworkDirPath == null) {
                            settings.frameworkDirPath =
                                    frameworkDir.toAbsolutePath().toString();
                        }
                        settings.resourcesDirPath = frameworkDir
                                .resolve("Resources")
                                .toAbsolutePath()
                                .toString();
                        settings.localesDirPath = settings.resourcesDirPath;
                    } else {
                        settings.resourcesDirPath = baseDir.toAbsolutePath().toString();
                    }
                }
            }

            if (!OS.isMacOS() && settings.localesDirPath == null) {
                settings.localesDirPath =
                        settings.resourcesDirPath != null ? settings.resourcesDirPath + "/locales" : null;
            }
            setOptionalIntSetting(settings, "disableSignalHandlers", 1);

            log.debug(
                    "CEF config: subprocess={}, resources={}",
                    settings.browserSubprocessPath,
                    settings.resourcesDirPath);

            CefApp appHandler = buildAppHandler(extraArgs);

            List<String> argv = processArguments(extraArgs);

            boolean useExternalPump = settings.externalMessagePump != 0;
            boolean useMultiThreadedLoop = settings.multiThreadedMessageLoop != 0;

            if (useExternalPump || useMultiThreadedLoop) {
                CefSettings immutable = settings.toImmutable();
                final int result = CefGlobals.initialize(mainArgs(argv), immutable, appHandler, null);
                if (result == 0) {
                    log.error("CefGlobals.initialize (cef_initialize) failed");
                    throw new RuntimeException("CefGlobals.initialize (cef_initialize) failed");
                }
                activeSettings = immutable;
                state = State.INITIALISED;
                initThread = Thread.currentThread();
                daemonManaged = false;
            } else if (OS.isMacOS()) {
                final CefSettings finalSettings = settings.toImmutable();
                final CefApp finalAppHandler = appHandler;
                final List<String> finalArgv = List.copyOf(argv);
                final AtomicReference<Throwable> initError = new AtomicReference<>();
                final int[] result = new int[1];
                SystemBootstrap.initAndRunOnMainThread(
                        () -> {
                            try {
                                result[0] = CefGlobals.initialize(
                                        mainArgs(finalArgv), finalSettings, finalAppHandler, null);
                            } catch (Throwable t) {
                                initError.set(t);
                            }
                            return result[0] != 0 && initError.get() == null;
                        },
                        () -> {
                            int released = NativeCleaner.INSTANCE.releaseAll();
                            log.debug("Released {} outstanding NativePeers before shutdown", released);
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
                                        mainArgs(finalArgv), finalSettings, finalAppHandler, null);
                                if (result == 0) {
                                    initError.set(
                                            new RuntimeException("CefGlobals.initialize (cef_initialize) failed"));
                                    return;
                                }
                                log.trace("CEF initialized on daemon thread");
                                initLatch.countDown();

                                CefGlobals.runMessageLoop();

                                log.trace("CEF message loop exited, shutting down");
                                int released = NativeCleaner.INSTANCE.releaseAll();
                                log.debug(
                                        "Released {} outstanding NativePeers from daemon thread before shutdown",
                                        released);
                                CefGlobals.shutdown();
                                log.trace("CEF terminated on daemon thread");
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

                awaitUninterruptibly(initLatch);

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

            log.debug("CEF initialized");
            if (activeSettings != null && activeSettings.cachePath != null) {
                NativeStderr.setCrashLogPath(activeSettings.cachePath);
            }
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

    static List<String> processArguments(List<String> extraArgs) {
        java.util.ArrayList<String> argv = new java.util.ArrayList<>(3 + extraArgs.size());
        argv.add("cef4j");
        argv.addAll(extraArgs);
        if (!OS.isWindows()) {
            // XXX: CEF 109.1.18 and 116.0.27 lack disable_signal_handlers; keep HotSpot's fatal handlers protected
            // until those compatibility lanes are dropped.
            addArgIfMissing(argv, "--disable-in-process-stack-traces");
        }
        return argv;
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
     * <p>All browser instances must be closed before calling this method. On macOS, shutdown runs on Thread 0 after the
     * managed CEF message loop exits so that CEF can remove its CFRunLoop observers before ordinary JVM teardown.
     *
     * <p>As per CEF design, after this call, CEF cannot be re-initialised in the same process (i.e. JVM). The singleton
     * remains accessible but all operations will throw {@link IllegalStateException}.
     */
    public void terminate() {
        // XXX: macOS dispatch and daemon teardown can reenter the lifecycle; keep blocking shutdown calls outside
        // lifecycleLock until native teardown no longer reenters Java.
        boolean isMacOs;
        boolean isDaemon;
        synchronized (lifecycleLock) {
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
        log.debug("CEF shutting down");

        if (isMacOs) {
            SystemBootstrap.quitAndWaitMainThreadMessageLoop();
        } else if (isDaemon) {
            CefGlobals.quitMessageLoop();
            if (shutdownLatch != null) awaitUninterruptibly(shutdownLatch);
        } else {
            int released = NativeCleaner.INSTANCE.releaseAll();
            log.debug("Released {} outstanding NativePeers before shutdown", released);
            CefGlobals.shutdown();
        }

        synchronized (lifecycleLock) {
            state = State.TERMINATED;
        }
        log.debug("CEF terminated");
    }

    static void awaitUninterruptibly(CountDownLatch latch) {
        boolean interrupted = false;
        while (true) {
            try {
                latch.await();
                break;
            } catch (InterruptedException ignored) {
                interrupted = true;
            }
        }
        if (interrupted) Thread.currentThread().interrupt();
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

    private static CefMainArgs mainArgs(List<String> argv) {
        if (OS.isWindows()) {
            return new net.kurobako.cef4j.gen.win.CefMainArgs(0L);
        }
        if (OS.isMacOS()) {
            return new net.kurobako.cef4j.gen.mac.CefMainArgs(argv.size(), argv);
        }
        return new net.kurobako.cef4j.gen.linux.CefMainArgs(argv.size(), argv);
    }

    /** Returns the current CEF application state. */
    public State state() {
        return state;
    }

    /**
     * Returns the settings CEF was initialized with, or {@link Optional#empty()} if CEF has not yet been initialised.
     * Used by lazy-init paths to validate compatibility before adding a new browser view.
     */
    public Optional<CefSettings> activeSettings() {
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
            // XXX: CEF 109.1.18 and 116.0.27 generated settings lack newer fields; remove reflection when those
            // compatibility lanes are dropped.
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to set CEF setting: " + fieldName, e);
        }
    }
}
