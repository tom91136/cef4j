package net.kurobako.cef4j.ipc.devtools.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
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

/** Jackson CDP correlation session over any Remote CEF transport. */
public final class DevToolsSession implements CdpTransport {
    private static final Logger LOG = LoggerFactory.getLogger(DevToolsSession.class);
    private static final ObjectMapper JSON = new ObjectMapper();
    private final CefSession session;
    private final RemoteHandle browser;
    private final BrowserHost host;
    private final AtomicInteger nextMessageId = new AtomicInteger();
    private final AtomicBoolean open = new AtomicBoolean(true);
    private final Object closeLock = new Object();
    private final ConcurrentHashMap<Integer, CompletableFuture<ObjectNode>> pending = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Consumer<ObjectNode>>> handlers =
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

    public static CompletableFuture<DevToolsSession> attach(
            CefSession session, RemoteHandle browser, BrowserHost host) {
        Objects.requireNonNull(session, "session");
        Objects.requireNonNull(browser, "browser");
        Objects.requireNonNull(host, "host");
        DevToolsSession value = new DevToolsSession(session, browser, host);
        return session.request(new DevToolsAttachRequest(browser), DevToolsAttachResponse.DECODER)
                .thenApply(ignored -> value)
                .whenComplete((ignored, failure) -> {
                    if (failure != null) value.failAndClose(failure);
                });
    }

    @SuppressWarnings("FutureReturnValueIgnored") // The returned future is the correlated CDP response below.
    public CompletableFuture<ObjectNode> send(String method, @Nullable ObjectNode params) {
        if (!open.get()) return failed(new IllegalStateException("DevTools session is closed"));
        int id = nextMessageId.updateAndGet(previous -> previous == Integer.MAX_VALUE ? 1 : previous + 1);
        ObjectNode command =
                JSON.createObjectNode().put("id", id).put("method", Objects.requireNonNull(method, "method"));
        if (params != null) command.set("params", params);
        CompletableFuture<ObjectNode> result = new CompletableFuture<>();
        pending.put(id, result);
        if (!open.get() && pending.remove(id, result)) {
            result.completeExceptionally(new IllegalStateException("DevTools session is closed"));
            return result;
        }
        host.sendDevToolsMessage(bytes(command)).whenComplete((accepted, failure) -> {
            if (failure != null) completeSendFailure(id, failure);
            else if (accepted == null || accepted == 0)
                completeSendFailure(id, new IllegalStateException("CEF rejected DevTools message " + id));
        });
        return result;
    }

    @Override
    public CompletableFuture<byte[]> execute(String method, @Nullable byte[] params) {
        ObjectNode object = null;
        if (params != null) {
            JsonNode parsed = parse(params);
            if (!parsed.isObject()) throw new IllegalArgumentException("CDP params must be a JSON object");
            object = (ObjectNode) parsed;
        }
        return send(method, object).thenApply(DevToolsSession::bytes);
    }

    @Override
    public CdpSubscription subscribe(String method, Consumer<byte[]> handler) {
        return on(method, params -> handler.accept(bytes(params)))::close;
    }

    public EventRegistration on(String method, Consumer<ObjectNode> handler) {
        CopyOnWriteArrayList<Consumer<ObjectNode>> current =
                handlers.computeIfAbsent(method, ignored -> new CopyOnWriteArrayList<>());
        current.add(handler);
        return () -> current.remove(handler);
    }

    @Override
    public void close() {
        closeAsync();
    }

    @Override
    public CompletionStage<Void> closeAsync() {
        synchronized (closeLock) {
            if (closeFuture != null) return closeFuture.minimalCompletionStage();
            if (!open.compareAndSet(true, false)) return CompletableFuture.completedFuture(null);
            messageRegistration.unregister();
            detachedRegistration.unregister();
            unregisterClose();
            failPending(new IllegalStateException("DevTools session is closed"));
            handlers.clear();
            closeFuture = session.request(new DevToolsDetachRequest(browser), DevToolsDetachResponse.DECODER)
                    .handle((ignored, failure) -> {
                        if (failure != null) LOG.debug("DevTools detach failed", failure);
                        return null;
                    });
            return closeFuture.minimalCompletionStage();
        }
    }

    private void handleMessage(DevToolsMessageEvent event) {
        if (!open.get() || !browser.equals(event.browser())) return;
        try {
            JsonNode parsed = parse(event.message());
            if (!parsed.isObject()) throw new IllegalArgumentException("CDP message is not an object");
            ObjectNode message = (ObjectNode) parsed;
            JsonNode id = message.get("id");
            if (id != null && id.isIntegralNumber()) {
                completeResult(id.intValue(), message);
                return;
            }
            JsonNode method = message.get("method");
            if (method != null && method.isTextual())
                dispatchEvent(method.textValue(), objectOrEmpty(message.get("params")));
        } catch (RuntimeException failure) {
            LOG.warn("Discarding malformed DevTools protocol message", failure);
        }
    }

    private void completeResult(int id, ObjectNode message) {
        CompletableFuture<ObjectNode> future = pending.remove(id);
        if (future == null) return;
        JsonNode error = message.get("error");
        if (error != null && error.isObject())
            future.completeExceptionally(new CdpException(
                    error.path("code").asInt(-1),
                    error.path("message").asText("CDP command failed"),
                    error.get("data")));
        else future.complete(objectOrEmpty(message.get("result")));
    }

    private void dispatchEvent(String method, ObjectNode params) {
        CopyOnWriteArrayList<Consumer<ObjectNode>> current = handlers.get(method);
        if (current == null) return;
        for (Consumer<ObjectNode> handler : current)
            try {
                handler.accept(params);
            } catch (RuntimeException failure) {
                LOG.warn("DevTools event handler failed for {}", method, failure);
            }
    }

    private static ObjectNode objectOrEmpty(@Nullable JsonNode value) {
        return value != null && value.isObject() ? (ObjectNode) value : JSON.createObjectNode();
    }

    private static JsonNode parse(byte[] value) {
        try {
            return JSON.readTree(value);
        } catch (IOException e) {
            throw new IllegalArgumentException("invalid CDP JSON", e);
        }
    }

    private static byte[] bytes(JsonNode value) {
        try {
            return JSON.writeValueAsBytes(value);
        } catch (IOException e) {
            throw new IllegalStateException("could not encode CDP JSON", e);
        }
    }

    private void completeSendFailure(int id, Throwable failure) {
        CompletableFuture<ObjectNode> future = pending.remove(id);
        if (future != null) future.completeExceptionally(failure);
    }

    private void failAndClose(Throwable failure) {
        if (open.compareAndSet(true, false)) {
            messageRegistration.unregister();
            detachedRegistration.unregister();
            unregisterClose();
            handlers.clear();
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

    private static <T> CompletableFuture<T> failed(Throwable failure) {
        CompletableFuture<T> value = new CompletableFuture<>();
        value.completeExceptionally(failure);
        return value;
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
