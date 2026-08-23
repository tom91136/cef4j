package net.kurobako.cef4j.ipc.devtools;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.cdp.CdpException;
import net.kurobako.cef4j.cdp.CdpRequestTracker;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsAgentDetachedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsAttachRequest;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsAttachResponse;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsDetachRequest;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsDetachResponse;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsMessageEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Correlates raw Chrome DevTools Protocol messages over a {@link CefSession}.
 *
 * <p>This class knows nothing about the session's concrete transport or JSON implementation. The same instance works
 * over ZeroMQ, Unix domain sockets, WebSocket, recording/replay, or an in-memory loopback transport with either the
 * Gson or Jackson codec.
 */
public final class DevToolsSession implements CdpTransport {
    private static final Logger LOG = LoggerFactory.getLogger(DevToolsSession.class);

    private final CefSession session;
    private final RemoteHandle browser;
    private final BrowserHost host;
    private final CdpCodec codec;
    private final AtomicBoolean open = new AtomicBoolean(true);
    private final Object closeLock = new Object();
    private final CdpRequestTracker<Map<String, Object>> requests = new CdpRequestTracker<>();
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Consumer<Map<String, Object>>>> eventHandlers =
            new ConcurrentHashMap<>();
    private final CefSession.HandlerRegistration messageRegistration;
    private final CefSession.HandlerRegistration detachedRegistration;

    @Nullable
    private CefSession.HandlerRegistration closeRegistration;

    @Nullable
    private CompletableFuture<Void> closeFuture;

    private DevToolsSession(CefSession session, RemoteHandle browser, BrowserHost host, CdpCodec codec) {
        this.session = session;
        this.browser = browser;
        this.host = host;
        this.codec = codec;
        messageRegistration =
                session.on(DevToolsMessageEvent.MESSAGE_ID, DevToolsMessageEvent.DECODER, this::handleMessage);
        detachedRegistration =
                session.on(DevToolsAgentDetachedEvent.MESSAGE_ID, DevToolsAgentDetachedEvent.DECODER, event -> {
                    if (browser.equals(event.browser()))
                        failAndClose(new IllegalStateException("DevTools agent detached"));
                });
        closeRegistration = session.onClose(() -> failAndClose(new IllegalStateException("CEF session closed")));
    }

    /** Registers the server-side observer after installing client event handlers, avoiding an attach race. */
    @Nonnull
    public static CompletableFuture<DevToolsSession> attach(
            @Nonnull CefSession session,
            @Nonnull RemoteHandle browser,
            @Nonnull BrowserHost host,
            @Nonnull CdpCodec codec) {
        Objects.requireNonNull(session, "session");
        Objects.requireNonNull(browser, "browser");
        Objects.requireNonNull(host, "host");
        Objects.requireNonNull(codec, "codec");
        DevToolsSession devTools = new DevToolsSession(session, browser, host, codec);
        return session.request(new DevToolsAttachRequest(browser), DevToolsAttachResponse.DECODER)
                .thenApply(ignored -> devTools)
                .whenComplete((ignored, failure) -> {
                    if (failure != null) devTools.failAndClose(failure);
                });
    }

    /** Sends a CDP method and completes with its result object. */
    @Nonnull
    @SuppressWarnings({"FutureReturnValueIgnored", "NullableForbidden"}) // null params omit the CDP request body
    public CompletableFuture<Map<String, Object>> send(@Nonnull String method, @Nullable Map<String, Object> params) {
        Objects.requireNonNull(method, "method");
        if (!open.get()) return failedFuture(new IllegalStateException("DevTools session is closed"));

        CdpRequestTracker.Request<Map<String, Object>> request = requests.register();
        int id = request.id();
        Map<String, Object> command = new java.util.HashMap<>();
        command.put("id", id);
        command.put("method", method);
        if (params != null) command.put("params", params);

        if (!open.get()) {
            requests.fail(id, new IllegalStateException("DevTools session is closed"));
            return request;
        }
        host.sendDevToolsMessage(codec.encode(command)).whenComplete((accepted, failure) -> {
            if (failure != null) {
                completeSendFailure(id, failure);
            } else if (accepted == null || accepted == 0) {
                completeSendFailure(id, new IllegalStateException("CEF rejected DevTools message " + id));
            }
        });
        return request;
    }

    /** Raw codec-neutral entry point used by the typed {@code cef4j-cdp} facade. */
    @Override
    @Nonnull
    @SuppressWarnings("FutureReturnValueIgnored")
    public CompletableFuture<byte[]> execute(@Nonnull String method, @Nullable byte[] params) {
        Map<String, Object> object = null;
        if (params != null) {
            Object decoded = codec.decode(params);
            if (!(decoded instanceof Map)) throw new IllegalArgumentException("CDP params must be a JSON object");
            object = asMap(decoded);
        }
        CompletableFuture<Map<String, Object>> source = send(method, object);
        CompletableFuture<byte[]> result = new CompletableFuture<>();
        source.whenComplete((value, failure) -> {
            try {
                if (failure != null) result.completeExceptionally(failure);
                else result.complete(codec.encode(value));
            } catch (Throwable encodeFailure) {
                result.completeExceptionally(encodeFailure);
            }
        });
        result.whenComplete((ignored, failure) -> {
            if (result.isCancelled()) source.cancel(false);
        });
        return result;
    }

    @Override
    public void cancelPending(@Nonnull Throwable failure) {
        requests.failAll(failure);
    }

    /** Raw codec-neutral event entry point used by the typed {@code cef4j-cdp} facade. */
    @Override
    @Nonnull
    public CdpSubscription subscribe(@Nonnull String method, @Nonnull Consumer<byte[]> handler) {
        Objects.requireNonNull(handler, "handler");
        return on(method, params -> handler.accept(codec.encode(params)))::unregister;
    }

    /** Subscribes to one CDP event method. Callbacks run on the IPC transport's receive thread. */
    @Nonnull
    public EventRegistration on(@Nonnull String method, @Nonnull Consumer<Map<String, Object>> handler) {
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(handler, "handler");
        CopyOnWriteArrayList<Consumer<Map<String, Object>>> handlers =
                eventHandlers.computeIfAbsent(method, ignored -> new CopyOnWriteArrayList<>());
        handlers.add(handler);
        return () -> handlers.remove(handler);
    }

    @Override
    public void close() {
        closeAsync();
    }

    @Override
    @Nonnull
    public CompletionStage<Void> closeAsync() {
        synchronized (closeLock) {
            if (closeFuture != null) return closeFuture.minimalCompletionStage();
            if (!open.compareAndSet(true, false)) return CompletableFuture.completedFuture(null);
            messageRegistration.unregister();
            detachedRegistration.unregister();
            unregisterClose();
            requests.failAll(new IllegalStateException("DevTools session is closed"));
            eventHandlers.clear();
            closeFuture = session.request(new DevToolsDetachRequest(browser), DevToolsDetachResponse.DECODER)
                    .handle((ignored, failure) -> {
                        if (failure != null) LOG.debug("DevTools detach failed during close", failure);
                        return null;
                    });
            return closeFuture.minimalCompletionStage();
        }
    }

    private void handleMessage(DevToolsMessageEvent event) {
        if (!open.get() || !browser.equals(event.browser())) return;
        try {
            Object decoded = codec.decode(event.message());
            if (!(decoded instanceof Map)) throw new IllegalArgumentException("CDP message is not an object");
            Map<String, Object> message = asMap(decoded);
            Object id = message.get("id");
            if (id instanceof Number) {
                completeResult(((Number) id).intValue(), message);
                return;
            }
            Object method = message.get("method");
            if (method instanceof String) {
                dispatchEvent((String) method, objectOrEmpty(message.get("params")));
            }
        } catch (RuntimeException failure) {
            LOG.warn("Discarding malformed DevTools protocol message", failure);
        }
    }

    private void completeResult(int id, Map<String, Object> message) {
        Object error = message.get("error");
        if (error instanceof Map) {
            Map<String, Object> errorMap = asMap(error);
            Object codeValue = errorMap.get("code");
            int code = codeValue instanceof Number ? ((Number) codeValue).intValue() : -1;
            Object messageValue = errorMap.get("message");
            String text = messageValue instanceof String ? (String) messageValue : "CDP command failed";
            requests.fail(id, new CdpException(code, text, errorMap.get("data")));
        } else {
            requests.complete(id, objectOrEmpty(message.get("result")));
        }
    }

    private void dispatchEvent(String method, Map<String, Object> params) {
        CopyOnWriteArrayList<Consumer<Map<String, Object>>> handlers = eventHandlers.get(method);
        if (handlers == null) return;
        for (Consumer<Map<String, Object>> handler : handlers) {
            try {
                handler.accept(params);
            } catch (RuntimeException failure) {
                LOG.warn("DevTools event handler failed for {}", method, failure);
            }
        }
    }

    private static Map<String, Object> objectOrEmpty(@Nullable Object value) {
        return value instanceof Map ? asMap(value) : Collections.emptyMap();
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> asMap(Object value) {
        return (Map<String, Object>) value;
    }

    private void completeSendFailure(int id, Throwable failure) {
        requests.fail(id, failure);
    }

    private void failAndClose(Throwable failure) {
        if (open.compareAndSet(true, false)) {
            messageRegistration.unregister();
            detachedRegistration.unregister();
            unregisterClose();
            eventHandlers.clear();
        }
        requests.failAll(failure);
    }

    private void unregisterClose() {
        CefSession.HandlerRegistration current = closeRegistration;
        closeRegistration = null;
        if (current != null) current.unregister();
    }

    private static <T> CompletableFuture<T> failedFuture(Throwable failure) {
        CompletableFuture<T> future = new CompletableFuture<>();
        future.completeExceptionally(failure);
        return future;
    }

    @FunctionalInterface
    public interface EventRegistration extends AutoCloseable {
        void unregister();

        @Override
        default void close() {
            unregister();
        }
    }
}
