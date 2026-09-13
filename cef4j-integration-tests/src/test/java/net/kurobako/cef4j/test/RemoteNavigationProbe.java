package net.kurobako.cef4j.test;

import java.net.InetAddress;
import java.net.URI;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LoadHandlerOnLoadEndEvent;
import net.kurobako.cef4j.ipc.protocol.gen.LoadHandlerOnLoadErrorEvent;
import net.kurobako.cef4j.ipc.protocol.gen.RequestHandlerOnRenderProcessTerminatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.transport.CefTransportException;

public final class RemoteNavigationProbe implements AutoCloseable {
    private final AtomicReference<PendingNavigation> pending = new AtomicReference<>();
    private final AtomicBoolean closed = new AtomicBoolean();
    private final CefSession.HandlerRegistration contextRegistration;
    private final CefSession.HandlerRegistration loadEndRegistration;
    private final CefSession.HandlerRegistration loadErrorRegistration;
    private final CefSession.HandlerRegistration rendererTerminatedRegistration;
    private final CefSession.HandlerRegistration closeRegistration;
    private final Supplier<RemoteHandle> browser;
    private final CefSession session;

    public RemoteNavigationProbe(CefSession session, Supplier<RemoteHandle> browser) {
        this.session = session;
        this.browser = browser;
        contextRegistration =
                session.on(V8ContextCreatedEvent.MESSAGE_ID, V8ContextCreatedEvent.DECODER, this::onContext);
        loadEndRegistration =
                session.on(LoadHandlerOnLoadEndEvent.MESSAGE_ID, LoadHandlerOnLoadEndEvent.DECODER, this::onLoadEnd);
        loadErrorRegistration = session.on(
                LoadHandlerOnLoadErrorEvent.MESSAGE_ID, LoadHandlerOnLoadErrorEvent.DECODER, this::onLoadError);
        rendererTerminatedRegistration = session.on(
                RequestHandlerOnRenderProcessTerminatedEvent.MESSAGE_ID,
                RequestHandlerOnRenderProcessTerminatedEvent.DECODER,
                this::onRendererTerminated);
        closeRegistration = session.onClose(this::onSessionClosed);
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    private void onLoadEnd(LoadHandlerOnLoadEndEvent event) {
        PendingNavigation current = pending.get();
        RemoteHandle expectedBrowser = browser.get();
        if (current == null || expectedBrowser == null || !expectedBrowser.equals(event.browser())) return;
        Frame frame = new Frame(session, event.frame());
        frame.isMain()
                .thenCombine(frame.getUrl(), (isMain, url) -> isMain != 0 && sameNavigationUrl(current.url, url))
                .whenComplete((matches, failure) -> {
                    if (pending.get() != current) return;
                    if (failure != null) current.ready.completeExceptionally(failure);
                    else if (matches) {
                        current.contextObserved.set(true);
                        current.completeIfReady();
                    }
                });
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    public CompletableFuture<Void> load(String url, Duration timeout, Supplier<CompletableFuture<Void>> queueLoad) {
        if (closed.get()) return CompletableFuture.failedFuture(new IllegalStateException("navigation probe closed"));
        PendingNavigation next = new PendingNavigation(url);
        if (!pending.compareAndSet(null, next)) {
            return CompletableFuture.failedFuture(new IllegalStateException("a navigation is already pending"));
        }
        pollFrameUrl(next);
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

    @SuppressWarnings("FutureReturnValueIgnored")
    private void pollFrameUrl(PendingNavigation current) {
        if (closed.get() || pending.get() != current || current.ready.isDone()) return;
        RemoteHandle browserHandle = browser.get();
        if (browserHandle == null) {
            scheduleFrameUrlPoll(current);
            return;
        }
        new Browser(session, browserHandle)
                .getMainFrame()
                .thenCompose(Frame::getUrl)
                .whenComplete((url, failure) -> {
                    if (closed.get() || pending.get() != current || current.ready.isDone()) return;
                    if (failure == null && sameNavigationUrl(current.url, url)) {
                        current.contextObserved.set(true);
                        current.completeIfReady();
                    } else {
                        scheduleFrameUrlPoll(current);
                    }
                });
    }

    private void scheduleFrameUrlPoll(PendingNavigation current) {
        CompletableFuture.delayedExecutor(50, TimeUnit.MILLISECONDS).execute(() -> pollFrameUrl(current));
    }

    private void onContext(V8ContextCreatedEvent event) {
        PendingNavigation current = pending.get();
        RemoteHandle expectedBrowser = browser.get();
        if (current != null
                && expectedBrowser != null
                && expectedBrowser.equals(event.browser())
                && sameNavigationUrl(current.url, event.frameUrl())) {
            current.contextObserved.set(true);
            current.completeIfReady();
        }
    }

    private void onLoadError(LoadHandlerOnLoadErrorEvent event) {
        PendingNavigation current = pending.get();
        RemoteHandle expectedBrowser = browser.get();
        if (current != null
                && expectedBrowser != null
                && expectedBrowser.equals(event.browser())
                && sameNavigationUrl(current.url, event.failedUrl())) {
            current.ready.completeExceptionally(new IllegalStateException(
                    "navigation failed with CEF error " + event.errorCode() + ": " + event.errorText()));
        }
    }

    private void onRendererTerminated(RequestHandlerOnRenderProcessTerminatedEvent event) {
        PendingNavigation current = pending.get();
        RemoteHandle expectedBrowser = browser.get();
        if (current != null && expectedBrowser != null && expectedBrowser.equals(event.browser())) {
            current.ready.completeExceptionally(
                    new IllegalStateException("renderer process terminated with status " + event.status()));
        }
    }

    static boolean sameNavigationUrl(String requested, String observed) {
        if (requested.equals(observed)) return true;
        try {
            URI left = URI.create(requested);
            URI right = URI.create(observed);
            if (!left.getScheme().equalsIgnoreCase(right.getScheme())
                    || effectivePort(left) != effectivePort(right)
                    || !Objects.equals(left.getRawPath(), right.getRawPath())
                    || !Objects.equals(left.getRawQuery(), right.getRawQuery())
                    || !Objects.equals(left.getRawFragment(), right.getRawFragment())) return false;
            String leftHost = left.getHost();
            String rightHost = right.getHost();
            if (leftHost == null || rightHost == null) return false;
            if (leftHost.equalsIgnoreCase(rightHost)) return true;
            InetAddress leftAddress = InetAddress.getByName(leftHost);
            InetAddress rightAddress = InetAddress.getByName(rightHost);
            return leftAddress.isLoopbackAddress() && rightAddress.isLoopbackAddress();
        } catch (IllegalArgumentException | java.io.IOException invalidUrl) {
            return false;
        }
    }

    private static int effectivePort(URI uri) {
        if (uri.getPort() >= 0) return uri.getPort();
        if ("http".equalsIgnoreCase(uri.getScheme())) return 80;
        if ("https".equalsIgnoreCase(uri.getScheme())) return 443;
        return -1;
    }

    @Override
    public void close() {
        if (!closed.compareAndSet(false, true)) return;
        contextRegistration.close();
        loadEndRegistration.close();
        loadErrorRegistration.close();
        rendererTerminatedRegistration.close();
        closeRegistration.close();
        PendingNavigation current = pending.getAndSet(null);
        if (current != null) current.result.cancel(true);
    }

    private void onSessionClosed() {
        PendingNavigation current = pending.getAndSet(null);
        if (current != null) current.result.completeExceptionally(new CefTransportException("session closed"));
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
