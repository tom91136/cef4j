package net.kurobako.cef4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefLifeSpanHandler;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefRequestContext;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefWindowInfo;
import net.kurobako.cef4j.test.CefTestLaunch;
import net.kurobako.cef4j.test.DisplayLock;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.TestTempDirs;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

@ExtendWith(DisplayLock.class)
abstract class CefTestBase {
    private static final IdentityHashMap<CefBrowser, CountDownLatch> browserClosed = new IdentityHashMap<>();

    // XXX: CEF 109-150 keeps cache files open until process exit on macOS; restore JUnit cleanup when macOS shutdown
    // closes every cache handle before @AfterAll.
    @TempDir(cleanup = CleanupMode.NEVER)
    @SuppressWarnings("NullAway.Init")
    static Path tempDir;

    static void initCef(List<String> additionalArgs) throws Exception {
        SystemBootstrap.load();
        TestTempDirs.cleanupAtExit(tempDir);
        Cef.State state = Cef.INSTANCE.state();
        if (state == Cef.State.INITIALISED) return;
        if (state == Cef.State.SHUTTING_DOWN || state == Cef.State.TERMINATED) {
            throw new IllegalStateException("CEF is not re-initialisable in this JVM once shutdown has begun");
        }

        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));
        Path reportDir = Files.createDirectories(Path.of("target", "surefire-reports"));

        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        String helperPath = System.getProperty("cef4j.test.helper-path");
        if (helperPath != null) settings.browserSubprocessPath = helperPath;
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.logFile = reportDir
                .resolve("cef-" + ProcessHandle.current().pid() + ".log")
                .toAbsolutePath()
                .toString();
        settings.windowlessRenderingEnabled = 1;
        if (OS.isMacOS()) {
            // XXX: CEF 109-150 crashes with SIGTRAP under externalMessagePump on macOS; retry it when the minimum CEF
            // version exceeds 150.
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 0;
        } else {
            settings.externalMessagePump = 1;
            settings.multiThreadedMessageLoop = 0;
        }

        List<String> args = new ArrayList<>(additionalArgs);
        args.addAll(CefTestLaunch.extraArgs());
        Cef.INSTANCE.initialise(settings, args);
    }

    static CefBrowser createWindowlessBrowser(CefClient client, String url) {
        CefWindowInfo windowInfo = Cef.createWindowlessInfo(new CefRect(0, 0, 800, 600));
        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        browserSettings.windowlessFrameRate = 60;
        CountDownLatch created = new CountDownLatch(1);
        CountDownLatch closed = new CountDownLatch(1);
        AtomicReference<CefBrowser> ref = new AtomicReference<>();
        CefClient interceptor = new CefClient() {
            @Override
            public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                return Optional.of(new CefLifeSpanHandler() {
                    @Override
                    public void onAfterCreated(@Nullable CefBrowser browser) {
                        if (browser != null) {
                            ref.compareAndSet(null, browser);
                            synchronized (browserClosed) {
                                browserClosed.put(browser, closed);
                            }
                        }
                        created.countDown();
                    }

                    @Override
                    public void onBeforeClose(@Nullable CefBrowser browser) {
                        closed.countDown();
                    }
                });
            }
        };
        CefClient composite = new CefClient.Delegating(List.of(interceptor, client));
        if (OS.isMacOS()) {
            // XXX: Use synchronous creation when CEF permits createBrowserSync from the macOS test thread.
            int ok = createBrowserCompat(windowInfo, composite, url, browserSettings.toImmutable());
            if (ok == 0) throw new RuntimeException("createBrowser failed");
            try {
                if (!created.await(10, TimeUnit.SECONDS)) {
                    throw new RuntimeException("Timed out waiting for browser creation on macOS");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Objects.requireNonNull(ref.get(), "browser creation returned null");
        }
        CefBrowser browser = Cef.INSTANCE.createBrowser(composite, url, windowInfo, browserSettings.toImmutable());
        synchronized (browserClosed) {
            browserClosed.put(browser, closed);
        }
        return browser;
    }

    static int createBrowserCompat(
            CefWindowInfo windowInfo, CefClient client, String url, CefBrowserSettings settings) {
        Class<?>[] base = {CefWindowInfo.class, CefClient.class, String.class, CefBrowserSettings.class};
        // Try: 6-param (CefDictionaryValue+CefRequestContext), 5-param (CefRequestContext), 4-param (none)
        try {
            Class<?>[] six = java.util.Arrays.copyOf(base, base.length + 2);
            six[4] = Class.forName("net.kurobako.cef4j.gen.CefDictionaryValue");
            six[5] = CefRequestContext.class;
            return (int) CefBrowserHost.class
                    .getMethod("createBrowser", six)
                    .invoke(null, windowInfo, client, url, settings, null, null);
        } catch (NoSuchMethodException | ClassNotFoundException missingSixParameterOverload) {
            try {
                Class<?>[] five = java.util.Arrays.copyOf(base, base.length + 1);
                five[4] = CefRequestContext.class;
                return (int) CefBrowserHost.class
                        .getMethod("createBrowser", five)
                        .invoke(null, windowInfo, client, url, settings, null);
            } catch (NoSuchMethodException missingFiveParameterOverload) {
                try {
                    return (int) CefBrowserHost.class
                            .getMethod("createBrowser", base)
                            .invoke(null, windowInfo, client, url, settings);
                } catch (ReflectiveOperationException failure) {
                    throw new IllegalStateException("Unable to create CEF browser", failure);
                }
            } catch (ReflectiveOperationException failure) {
                throw new IllegalStateException("Unable to create CEF browser", failure);
            }
        } catch (ReflectiveOperationException failure) {
            throw new IllegalStateException("Unable to create CEF browser", failure);
        }
    }

    static boolean isBrowserValid(CefBrowser browser) {
        try {
            return (boolean) CefBrowser.class.getMethod("isValid").invoke(browser);
        } catch (java.lang.reflect.InvocationTargetException e) {
            return true;
        } catch (Exception e) {
            // CEF <95 lacks isValid(); fall back to checking if host is still available
            return !browser.getHost().isEmpty();
        }
    }

    static void closeBrowser(@Nullable CefBrowser browser) throws InterruptedException {
        if (browser == null) return;
        CountDownLatch closed;
        synchronized (browserClosed) {
            closed = browserClosed.get(browser);
        }
        try {
            try (CefBrowserHost host = browser.getHost().orElseThrow()) {
                host.closeBrowser(true);
            }
            try {
                TestDeadline.after(Duration.ofSeconds(10))
                        .until(
                                () -> closed != null ? closed.getCount() == 0 : !isBrowserValid(browser),
                                () -> {
                                    if (!OS.isMacOS()) Cef.INSTANCE.doMessageLoopWork();
                                },
                                Duration.ofMillis(5),
                                "CEF browser closure");
            } catch (java.util.concurrent.TimeoutException timedOut) {
                throw new AssertionError(timedOut);
            }
        } finally {
            synchronized (browserClosed) {
                browserClosed.remove(browser);
            }
            browser.close();
        }
    }

    static boolean pumpUntil(CountDownLatch latch, long timeoutMs) throws InterruptedException {
        try {
            TestDeadline deadline = TestDeadline.after(Duration.ofMillis(timeoutMs));
            if (OS.isMacOS()) deadline.await(latch, "CEF callback");
            else
                deadline.until(
                        () -> latch.getCount() == 0,
                        () -> Cef.INSTANCE.doMessageLoopWork(),
                        Duration.ofMillis(5),
                        "CEF callback");
            return true;
        } catch (java.util.concurrent.TimeoutException timedOut) {
            return false;
        }
    }
}
