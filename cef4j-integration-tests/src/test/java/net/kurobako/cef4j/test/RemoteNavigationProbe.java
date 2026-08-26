package net.kurobako.cef4j.test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;

public final class RemoteNavigationProbe implements AutoCloseable {
    private final AtomicReference<PendingNavigation> pending = new AtomicReference<>();
    private final AtomicBoolean closed = new AtomicBoolean();
    private final CefSession.HandlerRegistration registration;
    private final Supplier<RemoteHandle> browser;

    public RemoteNavigationProbe(CefSession session, Supplier<RemoteHandle> browser) {
        this.browser = browser;
        registration = session.on(V8ContextCreatedEvent.MESSAGE_ID, V8ContextCreatedEvent.DECODER, this::onContext);
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    public CompletableFuture<Void> load(String url, Duration timeout, Supplier<CompletableFuture<Void>> queueLoad) {
        if (closed.get()) return CompletableFuture.failedFuture(new IllegalStateException("navigation probe closed"));
        PendingNavigation next = new PendingNavigation(url);
        if (!pending.compareAndSet(null, next)) {
            return CompletableFuture.failedFuture(new IllegalStateException("a navigation is already pending"));
        }
        CompletableFuture<Void> queued;
        try {
            queued = queueLoad.get();
        } catch (RuntimeException failure) {
            pending.compareAndSet(next, null);
            throw failure;
        }
        if (closed.get()) {
            pending.compareAndSet(next, null);
            queued.cancel(true);
            next.result.cancel(true);
            return next.result;
        }
        CompletableFuture<Void> stage = queued.thenRun(() -> {
                    next.acknowledged.set(true);
                    next.completeIfReady();
                })
                .thenCompose(ignored -> next.ready)
                .orTimeout(timeout.toNanos(), TimeUnit.NANOSECONDS);
        stage.whenComplete((ignored, failure) -> {
            if (failure != null) next.result.completeExceptionally(failure);
            else next.result.complete(null);
        });
        next.result.whenComplete((ignored, failure) -> {
            pending.compareAndSet(next, null);
            if (failure != null) {
                queued.cancel(true);
                next.ready.cancel(true);
                stage.cancel(true);
            }
        });
        return next.result;
    }

    private void onContext(V8ContextCreatedEvent event) {
        PendingNavigation current = pending.get();
        RemoteHandle expectedBrowser = browser.get();
        if (current != null
                && expectedBrowser != null
                && expectedBrowser.equals(event.browser())
                && current.url.equals(event.frameUrl())) {
            current.contextObserved.set(true);
            current.completeIfReady();
        }
    }

    @Override
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        registration.close();
        PendingNavigation current = pending.getAndSet(null);
        if (current != null) current.result.cancel(true);
    }

    private static final class PendingNavigation {
        private final String url;
        private final CompletableFuture<Void> ready = new CompletableFuture<>();
        private final CompletableFuture<Void> result = new CompletableFuture<>();
        private final AtomicBoolean acknowledged = new AtomicBoolean();
        private final AtomicBoolean contextObserved = new AtomicBoolean();

        private PendingNavigation(String url) {
            this.url = url;
        }

        private void completeIfReady() {
            if (acknowledged.get() && contextObserved.get()) ready.complete(null);
        }
    }
}
