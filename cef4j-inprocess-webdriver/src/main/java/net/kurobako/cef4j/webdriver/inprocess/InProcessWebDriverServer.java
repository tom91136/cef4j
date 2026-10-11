package net.kurobako.cef4j.webdriver.inprocess;

import java.io.IOException;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;
import net.kurobako.cef4j.webdriver.WebDriverServer;

/** Convenience entry point for a WebDriver endpoint backed by CEF in the current JVM. */
public final class InProcessWebDriverServer {
    private InProcessWebDriverServer() {}

    public static WebDriverServer start(InProcessBrowserRuntimeFactory runtimeFactory) throws IOException {
        WebDriverJsonCodec codec = WebDriverJsonCodec.installed();
        return start(runtimeFactory, codec);
    }

    public static WebDriverServer start(InProcessBrowserRuntimeFactory runtimeFactory, WebDriverJsonCodec codec)
            throws IOException {
        return WebDriverServer.start(new InProcessCefAutomationBackendFactory(runtimeFactory, codec), codec);
    }
}
