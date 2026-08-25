package net.kurobako.cef4j.webdriver.inprocess;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.webdriver.AutomationBackend;
import net.kurobako.cef4j.webdriver.AutomationBackendFactory;
import net.kurobako.cef4j.webdriver.CdpAutomationBackend;
import net.kurobako.cef4j.webdriver.JsonCdpBrowser;
import net.kurobako.cef4j.webdriver.JsonObject;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;

/** Adapts an owned in-process {@link CefBrowser} to the runtime-neutral WebDriver implementation. */
public final class InProcessCefAutomationBackendFactory implements AutomationBackendFactory {
    private final InProcessBrowserRuntimeFactory runtimeFactory;
    private final WebDriverJsonCodec jsonCodec;

    public InProcessCefAutomationBackendFactory(@Nonnull InProcessBrowserRuntimeFactory runtimeFactory) {
        this(runtimeFactory, WebDriverJsonCodec.installed());
    }

    public InProcessCefAutomationBackendFactory(
            @Nonnull InProcessBrowserRuntimeFactory runtimeFactory, @Nonnull WebDriverJsonCodec jsonCodec) {
        this.runtimeFactory = Objects.requireNonNull(runtimeFactory, "runtimeFactory");
        this.jsonCodec = Objects.requireNonNull(jsonCodec, "jsonCodec");
    }

    @Override
    @Nonnull
    public CompletableFuture<? extends AutomationBackend> create(@Nonnull JsonObject requestedCapabilities) {
        return flatMap(
                runtimeFactory.create(),
                runtime -> flatMap(InProcessCdpBrowser.attach(runtime, jsonCodec), adapter -> {
                    CompletableFuture<AutomationBackend> backend = flatMap(
                            CdpAutomationBackend.create(adapter), value -> CompletableFuture.completedFuture(value));
                    observeFailure(backend, failure -> adapter.close());
                    return backend;
                }));
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private static <S, T> CompletableFuture<T> flatMap(
            CompletableFuture<? extends S> source,
            Function<? super S, ? extends CompletableFuture<? extends T>> mapper) {
        AtomicReference<CompletableFuture<?>> active = new AtomicReference<>(source);
        PropagatingFuture<T> result = new PropagatingFuture<>(mayInterrupt ->
                Objects.requireNonNull(active.get(), "active future").cancel(mayInterrupt));
        source.whenComplete((value, failure) -> {
            if (failure != null) {
                result.completeExceptionally(failure);
                return;
            }
            if (result.isDone()) return;
            CompletableFuture<? extends T> next;
            try {
                next = Objects.requireNonNull(mapper.apply(value), "future mapper returned null");
            } catch (Throwable mappingFailure) {
                result.completeExceptionally(mappingFailure);
                return;
            }
            if (!active.compareAndSet(source, next) || result.isDone()) {
                next.cancel(false);
                return;
            }
            next.whenComplete((mapped, nextFailure) -> {
                if (nextFailure != null) result.completeExceptionally(nextFailure);
                else result.complete(mapped);
            });
        });
        return result;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private static void observeFailure(CompletableFuture<?> future, Consumer<? super Throwable> observer) {
        future.whenComplete((ignored, failure) -> {
            if (failure != null) observer.accept(failure);
        });
    }

    private static final class InProcessCdpBrowser implements JsonCdpBrowser {
        private final InProcessBrowserRuntime runtime;
        private final CefBrowser browser;
        private final InProcessDevToolsSession devTools;
        private final WebDriverJsonCodec jsonCodec;

        private InProcessCdpBrowser(
                InProcessBrowserRuntime runtime,
                CefBrowser browser,
                InProcessDevToolsSession devTools,
                WebDriverJsonCodec jsonCodec) {
            this.runtime = runtime;
            this.browser = browser;
            this.devTools = devTools;
            this.jsonCodec = jsonCodec;
        }

        private static CompletableFuture<InProcessCdpBrowser> attach(
                InProcessBrowserRuntime runtime, WebDriverJsonCodec jsonCodec) {
            try {
                CefBrowser browser = Objects.requireNonNull(runtime.browser(), "runtime.browser()");
                CompletableFuture<InProcessCdpBrowser> attached = flatMap(
                        InProcessDevToolsSession.attach(browser, jsonCodec),
                        devTools -> CompletableFuture.completedFuture(
                                new InProcessCdpBrowser(runtime, browser, devTools, jsonCodec)));
                observeFailure(attached, failure -> runtime.close());
                return attached;
            } catch (RuntimeException failure) {
                runtime.close();
                return failed(failure);
            }
        }

        @Override
        public WebDriverJsonCodec jsonCodec() {
            return jsonCodec;
        }

        @Override
        public CompletableFuture<byte[]> execute(String method, @Nullable byte[] params) {
            return devTools.execute(method, params);
        }

        @Override
        public net.kurobako.cef4j.cdp.CdpSubscription subscribe(String method, Consumer<byte[]> handler) {
            return devTools.subscribe(method, handler);
        }

        @Override
        public CompletableFuture<Void> loadUrl(String url) {
            return flatMap(
                    InProcessDevToolsSession.onUiThread(() -> {
                        browser.getMainFrame()
                                .orElseThrow(() -> new IllegalStateException("in-process browser has no main frame"))
                                .loadUrl(url);
                        return Boolean.TRUE;
                    }),
                    ignored -> CompletableFuture.completedFuture(null));
        }

        @Override
        public CompletableFuture<Boolean> canGoBack() {
            return CompletableFuture.completedFuture(browser.canGoBack());
        }

        @Override
        public CompletableFuture<Void> goBack() {
            browser.goBack();
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public CompletableFuture<Boolean> canGoForward() {
            return CompletableFuture.completedFuture(browser.canGoForward());
        }

        @Override
        public CompletableFuture<Void> goForward() {
            browser.goForward();
            return CompletableFuture.completedFuture(null);
        }

        @Override
        public CompletableFuture<Boolean> loading() {
            return CompletableFuture.completedFuture(browser.isLoading());
        }

        @Override
        public void close() {
            devTools.close();
            runtime.close();
        }
    }

    private static <T> CompletableFuture<T> failed(Throwable failure) {
        CompletableFuture<T> result = new CompletableFuture<>();
        result.completeExceptionally(failure);
        return result;
    }

    @SuppressWarnings("serial")
    private static final class PropagatingFuture<T> extends CompletableFuture<T> {
        private final Consumer<Boolean> cancellation;

        private PropagatingFuture(Consumer<Boolean> cancellation) {
            this.cancellation = cancellation;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            boolean cancelled = super.cancel(mayInterruptIfRunning);
            if (cancelled) cancellation.accept(mayInterruptIfRunning);
            return cancelled;
        }
    }
}
