package net.kurobako.cef4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import net.kurobako.cef4j.gen.CefApp;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefRect;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.gen.CefWindowInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.io.TempDir;

abstract class CefTestBase {
    @TempDir
    static Path tempDir;

    @BeforeAll
    static void initCef() throws Exception {
        initCef(List.of(), null);
    }

    static void initCef(List<String> additionalArgs) throws Exception {
        initCef(additionalArgs, null);
    }

    static void initCef(List<String> additionalArgs, CefApp appHandler) throws Exception {
        SystemBootstrap.load();
        Cef.State state = Cef.INSTANCE.getState();
        if (state == Cef.State.INITIALISED) return;
        if (state == Cef.State.SHUTTING_DOWN || state == Cef.State.TERMINATED) {
            throw new IllegalStateException("CEF is not re-initialisable in this JVM once shutdown has begun");
        }

        Path cacheDir = Files.createDirectories(tempDir.resolve("cef-cache"));

        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 1;
        settings.externalMessagePump = 1;
        settings.multiThreadedMessageLoop = 0;

        List<String> args = new ArrayList<>(additionalArgs);
        if (OS.isLinux()) {
            String ozonePlatform = System.getProperty("cef4j.test.ozonePlatform");
            if (ozonePlatform != null && !ozonePlatform.isBlank()) {
                args.add("--ozone-platform=" + ozonePlatform.trim());
            }
        }
        String extraArgsProperty = System.getProperty("cef4j.test.extraArgs");
        if (extraArgsProperty != null && !extraArgsProperty.isBlank()) {
            for (String arg : extraArgsProperty.split(",")) {
                String trimmed = arg.trim();
                if (!trimmed.isEmpty()) {
                    args.add(trimmed);
                }
            }
        }
        Cef.INSTANCE.initialise(settings, args, appHandler);
    }

    static CefBrowser createWindowlessBrowser(CefClient client, String url) {
        CefWindowInfo windowInfo = Cef.createWindowlessInfo(new CefRect(0, 0, 800, 600));
        CefBrowserSettings.Mutable browserSettings = new CefBrowserSettings.Mutable();
        browserSettings.windowlessFrameRate = 60;
        return Cef.INSTANCE.createBrowser(client, url, windowInfo, browserSettings.toImmutable());
    }

    static void closeBrowser(CefBrowser browser) {
        if (browser != null) {
            browser.getHost().ifPresent(host -> host.closeBrowser(true));
        }
    }

    static boolean pumpUntil(CountDownLatch latch, long timeoutMs) throws InterruptedException {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (latch.getCount() > 0 && System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(5);
        }
        return latch.getCount() == 0;
    }
}
