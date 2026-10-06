package net.kurobako.cef4j.webdriver.remote;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.Duration;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.remote.RemoteBrowserRuntimeFactory;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;
import net.kurobako.cef4j.webdriver.WebDriverServer;

/** Convenience entry point for a WebDriver endpoint backed by the Remote CEF runtime server. */
public final class RemoteWebDriverServer {
    private RemoteWebDriverServer() {}

    @Nonnull
    public static WebDriverServer start(@Nonnull RemoteBrowserRuntimeFactory runtimeFactory) throws IOException {
        return start(runtimeFactory, WebDriverJsonCodec.installed());
    }

    /** Starts a loopback endpoint with a command budget suitable for the caller's runtime startup environment. */
    @Nonnull
    public static WebDriverServer start(
            @Nonnull RemoteBrowserRuntimeFactory runtimeFactory, @Nonnull Duration commandTimeout) throws IOException {
        return start(runtimeFactory, commandTimeout, WebDriverJsonCodec.installed());
    }

    @Nonnull
    public static WebDriverServer start(
            @Nonnull RemoteBrowserRuntimeFactory runtimeFactory,
            @Nonnull Duration commandTimeout,
            @Nonnull WebDriverJsonCodec codec)
            throws IOException {
        return WebDriverServer.start(
                new InetSocketAddress(InetAddress.getLoopbackAddress(), 0),
                new RemoteCefAutomationBackendFactory(runtimeFactory, codec),
                commandTimeout,
                codec);
    }

    @Nonnull
    public static WebDriverServer start(
            @Nonnull RemoteBrowserRuntimeFactory runtimeFactory, @Nonnull WebDriverJsonCodec codec) throws IOException {
        return WebDriverServer.start(new RemoteCefAutomationBackendFactory(runtimeFactory, codec), codec);
    }
}
