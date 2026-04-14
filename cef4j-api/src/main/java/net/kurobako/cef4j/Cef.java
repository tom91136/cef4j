package net.kurobako.cef4j;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
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
 * <p>When {@code externalMessagePump} is false (the default for OSR components), CEF's message loop runs on an internal
 * daemon thread. The caller simply calls {@link #initialise(Mutable, List, CefApp)} (which blocks until CEF is ready)
 * and later {@link #terminate()} (which can be called from any thread).
 *
 * <p>When {@code externalMessagePump} is true, the caller is responsible for driving the message loop by calling
 * {@link #doMessageLoopWork()} periodically on the thread that called {@link #initialise(Mutable, List, CefApp)}.
 *
 * <p>Usage:
 *
 * <pre>{@code
 * Cef.INSTANCE.initialise(settings, args, null);
 * // ... use CEF ...
 * Cef.INSTANCE.terminate();
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
    private volatile boolean daemonManaged = false;
    private volatile CountDownLatch shutdownLatch;

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
     * Initialise CEF. Safe to call multiple times - subsequent calls are no-ops if CEF is already initialised.
     * Re-initialising after {@link #terminate()} is not supported per CEF design.
     *
     * <p>When {@code externalMessagePump} is false (the default), a daemon thread is created to run CEF's message loop.
     * This method blocks until initialisation completes, then returns. When {@code externalMessagePump} is true, the
     * caller must drive the message loop by calling {@link #doMessageLoopWork()} on the same thread.
     *
     * <p>If using {@code CefWebView}, prefer {@code CefWebView.initialise()} which calls this internally. If a
     * higher-level library (e.g. {@code CefMonacoPane}) provides its own {@code initialise()}, call that first - it
     * configures custom schemes and handlers before calling through to this method. Less-specific initialisations that
     * follow are no-ops since CEF is already running.
     *
     * <p>If {@code appHandler} is null, a default handler is used that only handles Windows command-line processing.
     * When a non-null {@code appHandler} is provided, the default Windows {@code extraArgs} command-line processing is
     * not applied — your handler is responsible for implementing {@link CefApp#onBeforeCommandLineProcessing} if
     * needed.
     *
     * @throws IllegalStateException if CEF has been terminated
     * @throws IllegalArgumentException if settings contain unsupported options (see {@link #initialiseUnsafe} to
     *     bypass)
     */
    public synchronized void initialise(
            @Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs, @Nullable CefApp appHandler) {
        validateSettings(settings);
        initialiseInternal(settings, extraArgs, appHandler);
    }

    /**
     * Initialise CEF without validating settings. Use this only if you know what you are doing — certain configurations
     * (e.g. {@code multiThreadedMessageLoop=1} on macOS) will crash or hang the process.
     */
    public synchronized void initialiseUnsafe(
            @Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs, @Nullable CefApp appHandler) {
        initialiseInternal(settings, extraArgs, appHandler);
    }

    private static void validateSettings(CefSettings.Mutable settings) {
        if (OS.isMacOS() && settings.multiThreadedMessageLoop != 0) {
            throw new IllegalArgumentException("multiThreadedMessageLoop is not supported on macOS. "
                    + "Use externalMessagePump=0 (daemon thread) or externalMessagePump=1 (manual pump) instead. "
                    + "Call initialiseUnsafe() to bypass this check.");
        }
    }

    private synchronized void initialiseInternal(
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
        settings.disableSignalHandlers = 1;

        // Sandbox is not supported in JVM-based CEF embeddings: the subprocess helper is a
        // separate executable and sandbox initialisation requires same-process control that the
        // JVM cannot provide. See JCEF context.cpp and CEF sandbox_setup docs.
        settings.noSandbox = 1;

        log.debug("CEF config: subprocess={}, resources={}", settings.browserSubprocessPath, settings.resourcesDirPath);

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

        java.util.ArrayList<String> argv = new java.util.ArrayList<>(2 + extraArgs.size());
        argv.add("cef4j");
        argv.addAll(extraArgs);
        if (!argv.contains("--no-sandbox")) {
            argv.add("--no-sandbox");
        }

        boolean useExternalPump = settings.externalMessagePump != 0;

        if (useExternalPump) {
            // Legacy path: caller manages the message loop via doMessageLoopWork().
            final int result =
                    CefGlobals.initialize(new CefMainArgs(argv.size(), argv), settings.toImmutable(), appHandler, null);
            if (result == 0) {
                log.error("CefGlobals.initialize (cef_initialize) failed");
                throw new RuntimeException("CefGlobals.initialize (cef_initialize) failed");
            }
            state = State.INITIALISED;
            initThread = Thread.currentThread();
            daemonManaged = false;
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
                            log.info("Released {} outstanding NativePeers before shutdown", released);
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

            state = State.INITIALISED;
            initThread = daemon;
            daemonManaged = true;
        }

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
     * Shut down CEF cleanly. Can be called from any thread. Safe to call multiple times - subsequent calls are no-ops.
     *
     * <p>When the daemon thread manages the message loop, this method signals the loop to exit and waits for shutdown
     * to complete. When using the external message pump, this must be called on the init thread.
     *
     * <p>As per CEF design, after this call, CEF cannot be re-initialised in the same process (i.e. JVM). The singleton
     * remains accessible but all operations will throw {@link IllegalStateException}.
     */
    public synchronized void terminate() {
        if (state == State.UNINITIALISED || state == State.TERMINATED || state == State.SHUTTING_DOWN) return;

        state = State.SHUTTING_DOWN;
        log.info("CEF shutting down");

        if (daemonManaged) {
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
            // External message pump path: cleanup runs on the calling thread.
            checkState();
            int released = NativeCleaner.INSTANCE.releaseAll();
            log.info("Released {} outstanding NativePeers before shutdown", released);
            CefGlobals.shutdown();
        }

        state = State.TERMINATED;
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
     * Perform a single iteration of CEF message loop work. Must be called on the same thread that called
     * {@link #initialise(Mutable, List, CefApp)}}. This is only needed if {@link #initialise(Mutable, List, CefApp)} is
     * configured with {@link CefSettings#externalMessagePump} set to true.
     *
     * @see CefGlobals#doMessageLoopWork()
     */
    public void doMessageLoopWork() {
        checkState();
        CefGlobals.doMessageLoopWork();
    }
}
