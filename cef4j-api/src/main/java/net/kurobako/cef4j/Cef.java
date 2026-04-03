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
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefSettings.Mutable;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.gen.NativePointer;
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
 * CefApp.INSTANCE.initialise();
 * }</pre>
 *
 * <p>CEF cannot be re-initialized after {@link #dispose()}.
 */
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
            throw new IllegalStateException("Invalid CEF state, expected INITIALIZED, but was " + state);
        if (Thread.currentThread() != initThread) {
            throw new IllegalStateException(
                    "doMessageLoopWork must be called on the same thread that called Cef#initialize(): current="
                            + Thread.currentThread() + ", init=" + initThread);
        }
    }

    /**
     * Initialise CEF on the current thread, any CEF lifecycle (e.g. {@link #dispose()}, {@link #doMessageLoopWork()},
     * etc.) calls must be made on the same thread from here on. It is safe to call this method multiple times, but
     * further calls are no-ops and settings/args will be ignored. Re-initialising CEF is not supported per CEF design.
     * CEF must be initialised before creating any browsers.
     *
     * @throws IllegalStateException if CEF has been shut down
     */
    public synchronized void initialise(@Nonnull CefSettings.Mutable settings, @Nonnull List<String> extraArgs) {
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

        var appHandler = (OS.isWindows() && !extraArgs.isEmpty())
                ? new CefApp() {
                    @Override
                    public void onBeforeCommandLineProcessing(
                            @Nullable String processType, @Nullable CefCommandLine commandLine) {
                        if (commandLine != null && (processType == null || processType.isEmpty())) {
                            for (String arg : extraArgs) {
                                if (arg != null && !arg.isEmpty()) {
                                    commandLine.appendSwitch(arg.startsWith("--") ? arg.substring(2) : arg);
                                }
                            }
                        }
                    }
                }
                : null;

        try (var args = new NativePointer.Managed(
                createMainArgs0(OS.isWindows() ? null : extraArgs.toArray(String[]::new)), Cef::freeMainArgs0)) {
            final var result = CefGlobals.initialize(args, settings.toImmutable(), appHandler, null);
            if (result == 0) {
                state = State.UNINITIALISED;
                log.error("CefGlobals.initialize (cef_initialize) failed with error code: {}", result);
                throw new RuntimeException("CefGlobals.initialize (cef_initialize) failed with error code: " + result);
            }
        }
        state = State.INITIALISED;
        initThread = Thread.currentThread();
        log.info("CEF initialized");
    }

    /** Create a new browser with the given client, URL, and settings. */
    public CefBrowser createBrowser(CefClient client, String url, CefWindowInfo info, CefBrowserSettings settings) {
        return CefBrowserHost.createBrowserSync(info, client, url, settings, null, null)
                .orElseThrow();
    }

    /**
     * Shut down CEF cleanly. Must be called on the same thread that called {@link #initialise(Mutable, List)}.
     *
     * <p>As per CEF design, after this call, CEF cannot be re-initialised in the same process (i.e. JVM). The singleton
     * remains accessible but all operations will throw {@link IllegalStateException}.
     */
    public synchronized void dispose() {
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
     * <p>{@see CefGlobals#doMessageLoopWork()}
     */
    public void doMessageLoopWork() {
        checkState();
        CefGlobals.doMessageLoopWork();
    }

    private static native long createMainArgs0(String[] extraArgs);

    private static native void freeMainArgs0(long address);
}
