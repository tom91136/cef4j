package net.kurobako.cef4j.webdriver.remote;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.ipc.devtools.RemoteDevToolsSessionFactory;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.session.CefFutures;
import net.kurobako.cef4j.remote.RemoteBrowserRuntime;
import net.kurobako.cef4j.remote.RemoteBrowserRuntimeFactory;
import net.kurobako.cef4j.webdriver.AutomationBackend;
import net.kurobako.cef4j.webdriver.AutomationBackendFactory;
import net.kurobako.cef4j.webdriver.CdpAutomationBackend;
import net.kurobako.cef4j.webdriver.JsonCdpBrowser;
import net.kurobako.cef4j.webdriver.JsonObject;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Adapts any transport-neutral Remote CEF runtime factory to the WebDriver HTTP layer. */
public final class RemoteCefAutomationBackendFactory implements AutomationBackendFactory {
    private static final Logger LOG = LoggerFactory.getLogger(RemoteCefAutomationBackendFactory.class);
    private static final long DEVTOOLS_CLOSE_TIMEOUT_SECONDS = 5;
    private final RemoteBrowserRuntimeFactory runtimeFactory;
    private final RemoteDevToolsSessionFactory devToolsFactory;
    private final WebDriverJsonCodec jsonCodec;

    public RemoteCefAutomationBackendFactory(@Nonnull RemoteBrowserRuntimeFactory runtimeFactory) {
        this(runtimeFactory, RemoteDevToolsSessionFactory.installed(), WebDriverJsonCodec.installed());
    }

    public RemoteCefAutomationBackendFactory(
            @Nonnull RemoteBrowserRuntimeFactory runtimeFactory,
            @Nonnull RemoteDevToolsSessionFactory devToolsFactory,
            @Nonnull WebDriverJsonCodec jsonCodec) {
        this.runtimeFactory = Objects.requireNonNull(runtimeFactory, "runtimeFactory");
        this.devToolsFactory = Objects.requireNonNull(devToolsFactory, "devToolsFactory");
        this.jsonCodec = Objects.requireNonNull(jsonCodec, "jsonCodec");
    }

    @Override
    @Nonnull
    public CompletableFuture<? extends AutomationBackend> create(@Nonnull JsonObject requestedCapabilities) {
        return CefFutures.flatMap(runtimeFactory.create(), runtime -> {
            Browser browser = new Browser(runtime.session(), runtime.browser());
            AtomicBoolean adapterOwnsRuntime = new AtomicBoolean();
            CompletableFuture<AutomationBackend> creation = CefFutures.flatMap(
                    browser.getHost(),
                    host -> CefFutures.flatMap(
                            devToolsFactory.attach(runtime.session(), runtime.browser(), host),
                            devTools -> createBackend(runtime, browser, devTools, adapterOwnsRuntime)));
            CefFutures.observeFailure(creation, failure -> {
                if (!adapterOwnsRuntime.get()) runtime.close();
            });
            return creation;
        });
    }

    private CompletableFuture<AutomationBackend> createBackend(
            RemoteBrowserRuntime runtime, Browser browser, CdpTransport devTools, AtomicBoolean adapterOwnsRuntime) {
        RemoteCdpBrowser adapter = new RemoteCdpBrowser(runtime, browser, devTools, jsonCodec);
        adapterOwnsRuntime.set(true);
        try {
            CompletableFuture<AutomationBackend> backend =
                    CefFutures.map(CdpAutomationBackend.create(adapter), value -> value);
            CefFutures.observeFailure(backend, failure -> adapter.close());
            return backend;
        } catch (RuntimeException failure) {
            adapter.close();
            return failed(failure);
        }
    }

    private static final class RemoteCdpBrowser implements JsonCdpBrowser {
        private final RemoteBrowserRuntime runtime;
        private final Browser browser;
        private final CdpTransport devTools;
        private final WebDriverJsonCodec jsonCodec;
        private final AtomicBoolean closed = new AtomicBoolean();

        private RemoteCdpBrowser(
                RemoteBrowserRuntime runtime, Browser browser, CdpTransport devTools, WebDriverJsonCodec jsonCodec) {
            this.runtime = runtime;
            this.browser = browser;
            this.devTools = devTools;
            this.jsonCodec = jsonCodec;
        }

        @Override
        public WebDriverJsonCodec jsonCodec() {
            return jsonCodec;
        }

        @Override
        public java.util.concurrent.CompletionStage<byte[]> execute(String method, @Nullable byte[] params) {
            return devTools.execute(method, params);
        }

        @Override
        public CdpSubscription subscribe(String method, Consumer<byte[]> handler) {
            return devTools.subscribe(method, handler);
        }

        @Override
        public CompletableFuture<Void> loadUrl(String url) {
            return CefFutures.flatMap(browser.getMainFrame(), frame -> frame.loadUrl(url));
        }

        @Override
        public CompletableFuture<Boolean> canGoBack() {
            return CefFutures.map(browser.canGoBack(), value -> value != 0);
        }

        @Override
        public CompletableFuture<Void> goBack() {
            return browser.goBack();
        }

        @Override
        public CompletableFuture<Boolean> canGoForward() {
            return CefFutures.map(browser.canGoForward(), value -> value != 0);
        }

        @Override
        public CompletableFuture<Void> goForward() {
            return browser.goForward();
        }

        @Override
        public CompletableFuture<Boolean> loading() {
            return CefFutures.map(browser.isLoading(), value -> value != 0);
        }

        @Override
        public void close() {
            if (!closed.compareAndSet(false, true)) return;
            closeDevToolsThenRuntime(devTools, runtime);
        }
    }

    /**
     * Gives native DevTools detach a short grace period, then always releases the owned runtime-server process.
     *
     * <p>A remote CEF server can be wedged precisely while processing detach. Waiting without a bound here turns a
     * normal WebDriver session close into an unkillable JVM-side leak and prevents the process supervisor from applying
     * its own bounded shutdown policy.
     */
    static void closeDevToolsThenRuntime(CdpTransport devTools, RemoteBrowserRuntime runtime) {
        try {
            devTools.closeAsync().toCompletableFuture().get(DEVTOOLS_CLOSE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException failure) {
            Thread.currentThread().interrupt();
            LOG.warn("interrupted while waiting for remote DevTools detach");
        } catch (ExecutionException | TimeoutException failure) {
            LOG.warn("remote DevTools detach did not complete before runtime shutdown: {}", failure.toString());
        } finally {
            runtime.close();
        }
    }

    private static <T> CompletableFuture<T> failed(Throwable failure) {
        CompletableFuture<T> result = new CompletableFuture<>();
        result.completeExceptionally(failure);
        return result;
    }
}
