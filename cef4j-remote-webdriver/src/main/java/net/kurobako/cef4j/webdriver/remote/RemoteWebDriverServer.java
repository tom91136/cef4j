package net.kurobako.cef4j.webdriver.remote;

import java.io.IOException;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.devtools.RemoteDevToolsSessionFactory;
import net.kurobako.cef4j.remote.RemoteBrowserRuntimeFactory;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;
import net.kurobako.cef4j.webdriver.WebDriverServer;

/** Convenience entry point for a WebDriver endpoint backed by the Remote CEF runtime server. */
public final class RemoteWebDriverServer {
    private RemoteWebDriverServer() {}

    @Nonnull
    public static WebDriverServer start(@Nonnull RemoteBrowserRuntimeFactory runtimeFactory) throws IOException {
        RemoteDevToolsSessionFactory devTools = RemoteDevToolsSessionFactory.installed();
        WebDriverJsonCodec codec = WebDriverJsonCodec.installed();
        return start(runtimeFactory, devTools, codec);
    }

    @Nonnull
    public static WebDriverServer start(
            @Nonnull RemoteBrowserRuntimeFactory runtimeFactory,
            @Nonnull RemoteDevToolsSessionFactory devTools,
            @Nonnull WebDriverJsonCodec codec)
            throws IOException {
        return WebDriverServer.start(new RemoteCefAutomationBackendFactory(runtimeFactory, devTools, codec), codec);
    }
}
