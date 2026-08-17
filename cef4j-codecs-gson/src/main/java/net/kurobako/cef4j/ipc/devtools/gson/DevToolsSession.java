package net.kurobako.cef4j.ipc.devtools.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
 * <p>This class knows nothing about the session's concrete transport. The same instance works over ZeroMQ, Unix domain
 * sockets, WebSocket, recording/replay, or an in-memory loopback transport.
 */
public final class DevToolsSession implements CdpTransport {
    private static final Logger LOG = LoggerFactory.getLogger(DevToolsSession.class);

    private final CefSession session;
    private final RemoteHandle browser;
    private final BrowserHost host;
    private final AtomicInteger nextMessageId = new AtomicInteger();
    private final AtomicBoolean open = new AtomicBoolean(true);
    private final Object closeLock = new Object();
    private final ConcurrentHashMap<Integer, CompletableFuture<JsonObject>> pending = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Consumer<JsonObject>>> eventHandlers =
            new ConcurrentHashMap<>();
    private final CefSession.HandlerRegistration messageRegistration;
    private final CefSession.HandlerRegistration detachedRegistration;

    @Nullable
    private CefSession.HandlerRegistration closeRegistration;

    @Nullable
    private CompletableFuture<Void> closeFuture;

    private DevToolsSession(CefSession session, RemoteHandle browser, BrowserHost host) {
        this.session = session;
        this.browser = browser;
        this.host = host;
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
            @Nonnull CefSession session, @Nonnull RemoteHandle browser, @Nonnull BrowserHost host) {
        Objects.requireNonNull(session, "session");
        Objects.requireNonNull(browser, "browser");
        Objects.requireNonNull(host, "host");
        DevToolsSession devTools = new DevToolsSession(session, browser, host);
        return session.request(new DevToolsAttachRequest(browser), DevToolsAttachResponse.DECODER)
                .thenApply(ignored -> devTools)
                .whenComplete((ignored, failure) -> {
                    if (failure != null) devTools.failAndClose(failure);
                });
    }

    /** Sends a CDP method and completes with its result object. */
    @Nonnull
    @SuppressWarnings("FutureReturnValueIgnored")
    public CompletableFuture<JsonObject> send(@Nonnull String method, @Nullable JsonObject params) {
        Objects.requireNonNull(method, "method");
        if (!open.get()) return failedFuture(new IllegalStateException("DevTools session is closed"));

        int id = nextMessageId.updateAndGet(previous -> previous == Integer.MAX_VALUE ? 1 : previous + 1);
        JsonObject command = new JsonObject();
        command.addProperty("id", id);
        command.addProperty("method", method);
        if (params != null) command.add("params", params);

        CompletableFuture<JsonObject> result = new CompletableFuture<>();
        pending.put(id, result);
        if (!open.get() && pending.remove(id, result)) {
            result.completeExceptionally(new IllegalStateException("DevTools session is closed"));
            return result;
        }
        host.sendDevToolsMessage(command.toString().getBytes(StandardCharsets.UTF_8))
                .whenComplete((accepted, failure) -> {
                    if (failure != null) {
                        completeSendFailure(id, failure);
                    } else if (accepted == null || accepted == 0) {
                        completeSendFailure(id, new IllegalStateException("CEF rejected DevTools message " + id));
                    }
                });
        return result;
    }

    /** Raw codec-neutral entry point used by the typed {@code cef4j-cdp} facade. */
    @Override
    @Nonnull
    public CompletableFuture<byte[]> execute(@Nonnull String method, @Nullable byte[] params) {
        JsonObject object = null;
        if (params != null) {
            JsonElement parsed = JsonParser.parseString(new String(params, StandardCharsets.UTF_8));
            if (!parsed.isJsonObject()) throw new IllegalArgumentException("CDP params must be a JSON object");
            object = parsed.getAsJsonObject();
        }
        return send(method, object).thenApply(result -> result.toString().getBytes(StandardCharsets.UTF_8));
    }

    /** Raw codec-neutral event entry point used by the typed {@code cef4j-cdp} facade. */
    @Override
    @Nonnull
    public CdpSubscription subscribe(@Nonnull String method, @Nonnull Consumer<byte[]> handler) {
        Objects.requireNonNull(handler, "handler");
        return on(method, params -> handler.accept(params.toString().getBytes(StandardCharsets.UTF_8)))::unregister;
    }

    /** Subscribes to one CDP event method. Callbacks run on the IPC transport's receive thread. */
    @Nonnull
    public EventRegistration on(@Nonnull String method, @Nonnull Consumer<JsonObject> handler) {
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(handler, "handler");
        CopyOnWriteArrayList<Consumer<JsonObject>> handlers =
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
            failPending(new IllegalStateException("DevTools session is closed"));
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
            JsonElement parsed = JsonParser.parseString(new String(event.message(), StandardCharsets.UTF_8));
            if (!parsed.isJsonObject()) throw new IllegalArgumentException("CDP message is not an object");
            JsonObject message = parsed.getAsJsonObject();
            JsonElement idElement = message.get("id");
            if (idElement != null && idElement.isJsonPrimitive()) {
                completeResult(idElement.getAsInt(), message);
                return;
            }
            JsonElement methodElement = message.get("method");
            if (methodElement != null && methodElement.isJsonPrimitive()) {
                dispatchEvent(methodElement.getAsString(), objectOrEmpty(message.get("params")));
            }
        } catch (RuntimeException failure) {
            LOG.warn("Discarding malformed DevTools protocol message", failure);
        }
    }

    private void completeResult(int id, JsonObject message) {
        CompletableFuture<JsonObject> future = pending.remove(id);
        if (future == null) return;
        JsonElement errorElement = message.get("error");
        if (errorElement != null && errorElement.isJsonObject()) {
            JsonObject error = errorElement.getAsJsonObject();
            int code = error.has("code") ? error.get("code").getAsInt() : -1;
            String text = error.has("message") ? error.get("message").getAsString() : "CDP command failed";
            future.completeExceptionally(new CdpException(code, text, error.get("data")));
        } else {
            future.complete(objectOrEmpty(message.get("result")));
        }
    }

    private void dispatchEvent(String method, JsonObject params) {
        CopyOnWriteArrayList<Consumer<JsonObject>> handlers = eventHandlers.get(method);
        if (handlers == null) return;
        for (Consumer<JsonObject> handler : handlers) {
            try {
                handler.accept(params);
            } catch (RuntimeException failure) {
                LOG.warn("DevTools event handler failed for {}", method, failure);
            }
        }
    }

    private static JsonObject objectOrEmpty(@Nullable JsonElement element) {
        return element != null && element.isJsonObject() ? element.getAsJsonObject() : new JsonObject();
    }

    private void completeSendFailure(int id, Throwable failure) {
        CompletableFuture<JsonObject> future = pending.remove(id);
        if (future != null) future.completeExceptionally(failure);
    }

    private void failAndClose(Throwable failure) {
        if (open.compareAndSet(true, false)) {
            messageRegistration.unregister();
            detachedRegistration.unregister();
            unregisterClose();
            eventHandlers.clear();
        }
        failPending(failure);
    }

    private void failPending(Throwable failure) {
        pending.forEach((id, future) -> {
            if (pending.remove(id, future)) future.completeExceptionally(failure);
        });
    }

    private void unregisterClose() {
        CefSession.HandlerRegistration current = closeRegistration;
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
