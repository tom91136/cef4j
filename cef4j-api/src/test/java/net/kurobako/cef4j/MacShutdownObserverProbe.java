package net.kurobako.cef4j;

import java.nio.file.Files;
import java.nio.file.Path;
import net.kurobako.cef4j.gen.CefSettings;
import net.kurobako.cef4j.test.CefTestLaunch;

/**
 * Child-JVM entry point for {@link MacShutdownNotificationPurgeTest}. Loads the macOS JNI hooks dylib, then initialises
 * and shuts CEF down in-process before posting the workspace accessibility notification that used to reenter the
 * unloaded framework. A surviving observer aborts the process in the notification block; the parent test reads the exit
 * code and output.
 */
public final class MacShutdownObserverProbe {
    private static native void installHostObserver();

    private static native int takeHostNotificationCount();

    private static native void postWorkspaceAccessibilityNotification();

    public static void main(String[] args) throws Exception {
        boolean companion = args.length > 0 && "companion".equals(args[0]);
        System.load(System.getProperty("cef4j.test.macHooks"));
        SystemBootstrap.load();
        if (companion) installHostObserver();

        Path cacheDir = Files.createTempDirectory("cef4j-mac-shutdown-probe");
        CefSettings.Mutable settings = new CefSettings.Mutable();
        settings.noSandbox = 1;
        settings.cachePath = cacheDir.toAbsolutePath().toString();
        settings.windowlessRenderingEnabled = 1;
        Cef.INSTANCE.initialise(settings, CefTestLaunch.extraArgs());
        System.out.println("CEF_MAJOR=" + SystemBootstrap.runtimeCefMajor().orElse(-1));
        Cef.INSTANCE.terminate();
        System.out.println("TERMINATED");

        SystemBootstrap.dispatchToMainThreadSync(MacShutdownObserverProbe::postWorkspaceAccessibilityNotification);
        if (companion) System.out.println("HOST_NOTIFICATIONS=" + takeHostNotificationCount());
        System.out.println("PROBE_OK");
        System.out.flush();
        Runtime.getRuntime().halt(0);
    }
}
