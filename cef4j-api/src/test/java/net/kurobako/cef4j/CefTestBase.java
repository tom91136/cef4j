package net.kurobako.cef4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefWindowInfo;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

abstract class CefTestBase {
    // CleanupMode.NEVER: CEF holds files in the cache dir open until JVM exit (cef_shutdown
    // is skipped on macOS; see the platform notes in DEBUG.md), so JUnit's auto-cleanup throws
    // DirectoryNotEmptyException at @AfterAll time. The OS cleans /tmp eventually.
    @TempDir(cleanup = CleanupMode.NEVER)
    @SuppressWarnings("NullAway.Init") // Populated by JUnit @TempDir before @Test methods run.
    static Path tempDir;

    static void initCef(List<String> additionalArgs) throws Exception {
        SystemBootstrap.load();
        Cef.State state = Cef.INSTANCE.state();
        if (state == Cef.State.INITIALISED) return;
        if (state == Cef.State.SHUTTING_DOWN || state == Cef.State.TERMINATED) {
            throw new IllegalStateException("CEF is not re-initialisable in this JVM once shutdown has begun");
        }

        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));

        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 1;
        if (OS.isMacOS()) {
            // macOS: use daemon thread path (externalMessagePump=0). externalMessagePump=1
            // crashes with SIGTRAP on macOS.
            settings.externalMessagePump = 0;
            settings.multiThreadedMessageLoop = 0;
        } else {
            settings.externalMessagePump = 1;
            settings.multiThreadedMessageLoop = 0;
        }

        List<String> args = new ArrayList<>(additionalArgs);
        if (OS.isLinux()) {
            String ozonePlatform = System.getProperty("cef4j.test.ozonePlatform");
            if (ozonePlatform != null && !ozonePlatform.isBlank()) {
                args.add("--ozone-platform=" + ozonePlatform.trim());
            }
        }
        String extraArgsProperty = System.getProperty("cef4j.test.extraArgs");
        if (extraArgsProperty != null && !extraArgsProperty.isBlank()) {
            java.util.regex.Pattern.compile(",")
                    .splitAsStream(extraArgsProperty)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .forEach(args::add);
        }
        Cef.INSTANCE.initialise(settings, args);
    }

    static CefBrowser createWindowlessBrowser(CefClient client, String url) {
        CefWindowInfo windowInfo = Cef.createWindowlessInfo(new CefRect(0, 0, 800, 600));
        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        browserSettings.windowlessFrameRate = 60;
        if (OS.isMacOS()) {
            // macOS daemon thread: createBrowserSync requires the CEF thread (daemon), but tests
            // run on the test thread. Use async createBrowser and intercept via onAfterCreated.
            CountDownLatch created = new CountDownLatch(1);
            AtomicReference<CefBrowser> ref = new AtomicReference<>();
            CefClient interceptor = new CefClient() {
                @Override
                public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
                    return Optional.of(new CefLifeSpanHandler() {
                        @Override
                        public void onAfterCreated(@Nullable CefBrowser browser) {
                            ref.compareAndSet(null, browser);
                            created.countDown();
                        }
                    });
                }
            };
            CefClient composite = new CefClient.Delegating(List.of(interceptor, client));
            int ok =
                    CefBrowserHost.createBrowser(windowInfo, composite, url, browserSettings.toImmutable(), null, null);
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
        return Cef.INSTANCE.createBrowser(client, url, windowInfo, browserSettings.toImmutable());
    }

    static void closeBrowser(@Nullable CefBrowser browser) {
        if (browser != null) {
            browser.getHost().ifPresent(host -> host.closeBrowser(true));
        }
    }

    static boolean pumpUntil(CountDownLatch latch, long timeoutMs) throws InterruptedException {
        if (OS.isMacOS()) {
            // macOS daemon thread: message loop runs on daemon thread, just wait on the latch.
            return latch.await(timeoutMs, java.util.concurrent.TimeUnit.MILLISECONDS);
        }
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (latch.getCount() > 0 && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(5);
        }
        return latch.getCount() == 0;
    }
}
