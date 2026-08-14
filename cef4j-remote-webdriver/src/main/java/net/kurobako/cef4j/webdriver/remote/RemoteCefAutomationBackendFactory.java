package net.kurobako.cef4j.webdriver.remote;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.ipc.devtools.RemoteDevToolsSessionFactory;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.remote.RemoteBrowserRuntime;
import net.kurobako.cef4j.remote.RemoteBrowserRuntimeFactory;
import net.kurobako.cef4j.webdriver.AutomationBackend;
import net.kurobako.cef4j.webdriver.AutomationBackendFactory;
import net.kurobako.cef4j.webdriver.CdpAutomationBackend;
import net.kurobako.cef4j.webdriver.JsonCdpBrowser;
import net.kurobako.cef4j.webdriver.JsonObject;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;

/** Adapts any transport-neutral Remote CEF runtime factory to the WebDriver HTTP layer. */
public final class RemoteCefAutomationBackendFactory implements AutomationBackendFactory {
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
        return runtimeFactory.create().thenCompose(runtime -> {
            Browser browser = new Browser(runtime.session(), runtime.browser());
            AtomicBoolean adapterOwnsRuntime = new AtomicBoolean();
            return browser.getHost()
                    .thenCompose(host -> devToolsFactory
                            .attach(runtime.session(), runtime.browser(), host)
                            .thenCompose(devTools -> {
                                RemoteCdpBrowser adapter = new RemoteCdpBrowser(runtime, browser, devTools, jsonCodec);
                                adapterOwnsRuntime.set(true);
                                try {
                                    return CdpAutomationBackend.create(adapter)
                                            .thenApply(backend -> (AutomationBackend) backend);
                                } catch (RuntimeException failure) {
                                    adapter.close();
                                    return failed(failure);
                                }
                            }))
                    .whenComplete((backend, failure) -> {
                        if (failure != null && !adapterOwnsRuntime.get()) runtime.close();
                    });
        });
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
            return browser.getMainFrame().thenCompose(frame -> frame.loadUrl(url));
        }

        @Override
        public CompletableFuture<Boolean> canGoBack() {
            return browser.canGoBack().thenApply(value -> value != 0);
        }

        @Override
        public CompletableFuture<Void> goBack() {
            return browser.goBack();
        }

        @Override
        public CompletableFuture<Boolean> canGoForward() {
            return browser.canGoForward().thenApply(value -> value != 0);
        }

        @Override
        public CompletableFuture<Void> goForward() {
            return browser.goForward();
        }

        @Override
        public CompletableFuture<Boolean> loading() {
            return browser.isLoading().thenApply(value -> value != 0);
        }

        @Override
        public void close() {
            if (!closed.compareAndSet(false, true)) return;
            try {
                // Detach is a native UI-thread operation. Do not race server shutdown (sent over a separate
                // control pipe) against the IPC acknowledgement that the DevTools registration was released.
                devTools.closeAsync()
                        .toCompletableFuture()
                        .handle((ignored, failure) -> null)
                        .join();
            } finally {
                runtime.close();
            }
        }
    }

    private static <T> CompletableFuture<T> failed(Throwable failure) {
        CompletableFuture<T> result = new CompletableFuture<>();
        result.completeExceptionally(failure);
        return result;
    }
}
