package net.kurobako.cef4j.ipc.frame;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.EvaluateJavascriptResponse;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeRequest;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSession.HandlerRegistration;
import net.kurobako.cef4j.ipc.session.JsResult;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.remote.RemoteViewportConstraints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Toolkit-neutral binding between a UI surface and the browser a Remote CEF runtime server auto-creates. Owns the
 * session attachment, the frame transport, viewport reporting, and main-frame commands; the surface keeps only paint
 * and input translation.
 *
 * <p>The frame transport is bound when {@code LifeSpanHandlerOnAfterCreatedEvent} arrives and {@link #browserReady()}
 * resolves then. {@link #loadUrl} and {@link #evaluateJavascript} chain off the same future.
 */
public final class RemoteBrowserBinding implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(RemoteBrowserBinding.class);
    private static final long UI_BARRIER_TIMEOUT_SECONDS = 10;

    private final String owner;
    private final Executor uiThread;
    private final FrameTransportFactory frameTransportFactory;
    private final FrameTransport.FrameConsumer frames;
    private final AtomicLong desiredSize = new AtomicLong(packSize(1, 1));
    private final AtomicLong reportedSize = new AtomicLong(-1);
    private final AtomicReference<BrowserHost> hostRef = new AtomicReference<>();
    private volatile CompletableFuture<RemoteHandle> browserHandle = new CompletableFuture<>();

    @Nullable
    private volatile CefSession session;

    @Nullable
    private volatile RemoteHandle readyBrowser;

    @Nullable
    private FrameTransport frameTransport;

    @Nullable
    private HandlerRegistration lifecycleRegistration;

    @Nullable
    private RuntimeException setupFailure;

    private boolean attachedOnce;

    /**
     * @param owner surface name used in error messages
     * @param uiThread executor for the surface's UI thread; explicit resizes are ordered after work already queued
     *     there
     * @param frameTransportFactory binds the frame transport once the browser handle is known
     * @param frames receives frames on the transport's thread
     */
    public RemoteBrowserBinding(
            @Nonnull String owner,
            @Nonnull Executor uiThread,
            @Nonnull FrameTransportFactory frameTransportFactory,
            @Nonnull FrameTransport.FrameConsumer frames) {
        this.owner = Objects.requireNonNull(owner, "owner");
        this.uiThread = Objects.requireNonNull(uiThread, "uiThread");
        this.frameTransportFactory = Objects.requireNonNull(frameTransportFactory, "frameTransportFactory");
        this.frames = Objects.requireNonNull(frames, "frames");
    }

    /**
     * Wires this binding to a session with a connected transport. Repeating the call with the same session is
     * idempotent; attaching to a different session is rejected because browser handles are session-scoped.
     */
    public synchronized void attach(@Nonnull CefSession session) {
        if (attachedOnce) {
            if (this.session == session) return;
            throw new IllegalStateException(owner + " instances cannot be attached to more than one session");
        }
        Objects.requireNonNull(session, "session");
        if (browserHandle.isCompletedExceptionally()) browserHandle = new CompletableFuture<>();
        this.session = session;
        setupFailure = null;
        HandlerRegistration registration;
        try {
            registration = session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                    LifeSpanHandlerOnAfterCreatedEvent.DECODER,
                    event -> installBrowser(session, event.browser()));
        } catch (RuntimeException failure) {
            this.session = null;
            throw failure;
        }
        if (setupFailure != null || this.session != session) {
            registration.unregister();
            RuntimeException failure = setupFailure;
            setupFailure = null;
            this.session = null;
            throw Objects.requireNonNull(failure, "frame transport setup failure");
        }
        lifecycleRegistration = registration;
        attachedOnce = true;
        observe(
                browserHandle
                        .thenCompose(handle -> new Browser(session, handle).getHost())
                        .thenAccept(host -> {
                            if (this.session == session) hostRef.set(host);
                        }),
                "resolve BrowserHost for input forwarding");
        observe(browserHandle.thenCompose(handle -> flushViewportSize(session, handle)), "flush initial viewport size");
    }

    private synchronized void installBrowser(CefSession expectedSession, RemoteHandle browser) {
        CompletableFuture<RemoteHandle> pendingBrowser = browserHandle;
        if (session != expectedSession || pendingBrowser.isDone()) return;
        FrameTransport created = null;
        try {
            created = frameTransportFactory.bind(expectedSession, browser);
            created.onFrame(frames);
            frameTransport = created;
            readyBrowser = browser;
            pendingBrowser.complete(browser);
        } catch (RuntimeException failure) {
            if (created != null) {
                try {
                    created.close();
                } catch (RuntimeException closeFailure) {
                    failure.addSuppressed(closeFailure);
                }
            }
            frameTransport = null;
            readyBrowser = null;
            setupFailure = failure;
            HandlerRegistration registration = lifecycleRegistration;
            lifecycleRegistration = null;
            session = null;
            attachedOnce = false;
            if (registration != null) registration.unregister();
            pendingBrowser.completeExceptionally(failure);
        }
    }

    /** Whether a session is attached; frames delivered after release must not be presented. */
    public boolean isAttached() {
        return session != null;
    }

    /** Browser host for input forwarding; empty before the browser is ready or after release. */
    public Optional<BrowserHost> host() {
        return Optional.ofNullable(hostRef.get());
    }

    @Nonnull
    public CompletableFuture<RemoteHandle> browserReady() {
        return browserHandle.copy();
    }

    @Nonnull
    public RemoteHandle awaitBrowserHandle(@Nonnull Duration timeout) throws Exception {
        return browserHandle.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
    }

    /** Completes when the load is queued (server-side ack); does not wait for page rendering. */
    @Nonnull
    public CompletableFuture<Void> loadUrl(@Nonnull String url) {
        return browserHandle.thenCompose(handle ->
                new Browser(requireSession(), handle).getMainFrame().thenCompose(frame -> frame.loadUrl(url)));
    }

    /**
     * Evaluates JS in the main frame. Objects and arrays come back JSON-stringified, primitives in their string form,
     * and null/undefined as an empty string; JS errors fail the future.
     */
    @Nonnull
    public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
        return browserHandle.thenCompose(handle -> {
            CefSession current = requireSession();
            return new Browser(current, handle)
                    .getMainFrame()
                    .thenCompose(frame -> current.request(
                            new EvaluateJavascriptRequest(frame.handle(), script, false),
                            EvaluateJavascriptResponse.DECODER))
                    .thenApply(RemoteBrowserBinding::stringify);
        });
    }

    /** Requests a viewport resize after queued UI work and completes once the remote runtime acknowledges it. */
    @Nonnull
    public CompletableFuture<Void> resizeViewport(int width, int height) {
        RemoteViewportConstraints.validate(width, height);
        CompletableFuture<Void> queuedUiWork = new CompletableFuture<>();
        try {
            uiThread.execute(() -> queuedUiWork.complete(null));
        } catch (RuntimeException failure) {
            queuedUiWork.completeExceptionally(failure);
        }
        long desired = packSize(width, height);
        return queuedUiWork
                .orTimeout(UI_BARRIER_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .thenCompose(ignored -> browserHandle.thenCompose(handle -> {
                    desiredSize.set(desired);
                    return requestViewportSize(requireSession(), handle, desired);
                }));
    }

    /**
     * Records the surface size and forwards it once the browser is ready; sizes the runtime cannot honour are ignored.
     */
    public void reportViewportSize(int width, int height) {
        try {
            RemoteViewportConstraints.validate(width, height);
        } catch (IllegalArgumentException invalidSize) {
            LOG.debug("ignoring automatic viewport resize to {}x{}: {}", width, height, invalidSize.getMessage());
            return;
        }
        desiredSize.set(packSize(width, height));
        CefSession current = session;
        RemoteHandle handle = readyBrowser;
        if (current != null && handle != null) observe(flushViewportSize(current, handle), "resize viewport");
    }

    private CompletableFuture<Void> flushViewportSize(CefSession expectedSession, RemoteHandle handle) {
        if (session != expectedSession) return CompletableFuture.completedFuture(null);
        long desired = desiredSize.get();
        if (reportedSize.getAndSet(desired) == desired) return CompletableFuture.completedFuture(null);
        return requestViewportSize(expectedSession, handle, desired);
    }

    private CompletableFuture<Void> requestViewportSize(CefSession expectedSession, RemoteHandle handle, long desired) {
        if (session != expectedSession) return CompletableFuture.completedFuture(null);
        int width = (int) (desired >>> 32);
        int height = (int) desired;
        reportedSize.set(desired);
        return expectedSession
                .request(new SetViewportSizeRequest(handle, width, height), SetViewportSizeResponse.DECODER)
                .thenApply(ignored -> (Void) null)
                .whenComplete((ignored, failure) -> {
                    if (failure != null) {
                        reportedSize.compareAndSet(desired, -1);
                        LOG.debug("viewport resize to {}x{} failed: {}", width, height, failure.toString());
                    }
                });
    }

    /** Observes best-effort asynchronous work so failures are visible without blocking the UI thread. */
    @SuppressWarnings("FutureReturnValueIgnored")
    public void observe(@Nonnull CompletableFuture<?> future, @Nonnull String action) {
        future.whenComplete((ignored, failure) -> {
            if (failure == null) return;
            if (session == null) LOG.debug("{} stopped during release: {}", action, failure.toString());
            else LOG.warn("failed to {}: {}", action, failure.toString());
        });
    }

    /** Disposes the frame transport subscription. The caller owns the session lifetime. */
    public synchronized void release() {
        if (!attachedOnce) return;
        RuntimeException failure = null;
        FrameTransport transport = frameTransport;
        frameTransport = null;
        if (transport != null) {
            try {
                transport.close();
            } catch (RuntimeException closeFailure) {
                failure = closeFailure;
            }
        }
        if (lifecycleRegistration != null) {
            try {
                lifecycleRegistration.unregister();
            } catch (RuntimeException closeFailure) {
                if (failure == null) failure = closeFailure;
                else failure.addSuppressed(closeFailure);
            }
            lifecycleRegistration = null;
        }
        hostRef.set(null);
        session = null;
        if (!browserHandle.isDone()) {
            browserHandle.completeExceptionally(new IllegalStateException(owner + " released before browser ready"));
        }
        if (failure != null) throw failure;
    }

    @Override
    public void close() {
        release();
    }

    private CefSession requireSession() {
        CefSession current = session;
        if (current == null) throw new IllegalStateException(owner + " has not been attach()ed to a session");
        return current;
    }

    private static long packSize(int width, int height) {
        return ((long) width << 32) | (height & 0xFFFFFFFFL);
    }

    private static String stringify(EvaluateJavascriptResponse response) {
        return JsResult.fromWire(
                        response.valueKind(),
                        response.boolValue(),
                        response.intValue(),
                        response.doubleValue(),
                        response.stringValue(),
                        response.errorMessage())
                .coerceToString();
    }

    /** Creates and subscribes a frame transport for the attached session's browser. */
    @FunctionalInterface
    public interface FrameTransportFactory {
        @Nonnull
        FrameTransport bind(@Nonnull CefSession session, @Nonnull RemoteHandle browser);
    }
}
