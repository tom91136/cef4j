package net.kurobako.cef4j.webdriver.inprocess;

import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpException;
import net.kurobako.cef4j.cdp.CdpRequestTracker;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefTask;
import net.kurobako.cef4j.gen.CefTaskRunner;
import net.kurobako.cef4j.gen.CefThreadId;
import net.kurobako.cef4j.webdriver.JsonElement;
import net.kurobako.cef4j.webdriver.JsonObject;
import net.kurobako.cef4j.webdriver.WebDriverJsonCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class InProcessDevToolsSession implements CdpTransport {
    private static final Logger LOG = LoggerFactory.getLogger(InProcessDevToolsSession.class);

    private final CefBrowserHost host;
    private final WebDriverJsonCodec jsonCodec;
    private final AtomicBoolean open = new AtomicBoolean(true);
    private final CdpRequestTracker<byte[]> requests = new CdpRequestTracker<>();
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Consumer<byte[]>>> handlers =
            new ConcurrentHashMap<>();

    @Nullable
    private volatile Object registration;

    @Nullable
    private volatile Object observer;

    private InProcessDevToolsSession(CefBrowserHost host, WebDriverJsonCodec jsonCodec) {
        this.host = host;
        this.jsonCodec = jsonCodec;
    }

    @Nonnull
    public static CompletableFuture<InProcessDevToolsSession> attach(@Nonnull CefBrowser browser) {
        return attach(browser, WebDriverJsonCodec.installed());
    }

    @Nonnull
    public static CompletableFuture<InProcessDevToolsSession> attach(
            @Nonnull CefBrowser browser, @Nonnull WebDriverJsonCodec jsonCodec) {
        Objects.requireNonNull(browser, "browser");
        Objects.requireNonNull(jsonCodec, "jsonCodec");
        Class<?> observerType;
        try {
            observerType = Class.forName("net.kurobako.cef4j.gen.CefDevToolsMessageObserver");
        } catch (ClassNotFoundException unavailableInOlderCef) {
            return failed(new UnsupportedOperationException("DevTools requires CEF 81 or newer"));
        }
        CefBrowserHost host = browser.getHost().orElse(null);
        if (host == null) return failed(new IllegalStateException("in-process browser has no host"));
        InProcessDevToolsSession session = new InProcessDevToolsSession(host, jsonCodec);
        return onUiThread(() -> {
                    session.observer = createObserver(observerType, session);
                    @SuppressWarnings("unchecked")
                    java.util.Optional<Object> registration = (java.util.Optional<Object>) host.getClass()
                            .getMethod("addDevToolsMessageObserver", observerType)
                            .invoke(host, session.observer);
                    session.registration =
                            registration.orElseThrow(() -> new IllegalStateException("CEF rejected DevTools observer"));
                    return session;
                })
                .whenComplete((ignored, failure) -> {
                    if (failure != null) session.close();
                });
    }

    @Override
    @Nonnull
    @SuppressWarnings("FutureReturnValueIgnored")
    public CompletableFuture<byte[]> execute(@Nonnull String method, @Nullable byte[] params) {
        Objects.requireNonNull(method, "method");
        if (!method.matches("[A-Za-z0-9_.-]+")) throw new IllegalArgumentException("invalid CDP method name");
        if (!open.get()) return failed(new IllegalStateException("DevTools session is closed"));
        CdpRequestTracker.Request<byte[]> request = requests.register();
        int id = request.id();
        if (!open.get()) {
            requests.fail(id, new IllegalStateException("DevTools session is closed"));
            return request;
        }
        byte[] prefix = ("{\"id\":" + id + ",\"method\":\"" + method + "\"").getBytes(StandardCharsets.UTF_8);
        byte[] bytes;
        if (params == null) {
            bytes = java.util.Arrays.copyOf(prefix, prefix.length + 1);
            bytes[bytes.length - 1] = '}';
        } else {
            byte[] separator = ",\"params\":".getBytes(StandardCharsets.UTF_8);
            bytes = new byte[prefix.length + separator.length + params.length + 1];
            System.arraycopy(prefix, 0, bytes, 0, prefix.length);
            System.arraycopy(separator, 0, bytes, prefix.length, separator.length);
            System.arraycopy(params, 0, bytes, prefix.length + separator.length, params.length);
            bytes[bytes.length - 1] = '}';
        }
        ByteBuffer message = ByteBuffer.allocateDirect(bytes.length);
        message.put(bytes).flip();
        onUiThread(() -> sendDevToolsMessage(host, message)).whenComplete((accepted, failure) -> {
            if (failure != null) completeFailure(id, failure);
            else if (!accepted) completeFailure(id, new IllegalStateException("CEF rejected DevTools message " + id));
        });
        return request;
    }

    @Override
    public void cancelPending(@Nonnull Throwable failure) {
        requests.failAll(failure);
    }

    @Override
    @Nonnull
    public CdpSubscription subscribe(@Nonnull String method, @Nonnull Consumer<byte[]> handler) {
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(handler, "handler");
        if (!open.get()) throw new IllegalStateException("DevTools session is closed");
        CopyOnWriteArrayList<Consumer<byte[]>> current =
                handlers.computeIfAbsent(method, ignored -> new CopyOnWriteArrayList<>());
        current.add(handler);
        return () -> current.remove(handler);
    }

    @SuppressWarnings("UnusedVariable")
    private boolean onDevToolsMessage(@Nullable CefBrowser browser, @Nonnull ByteBuffer message) {
        return false;
    }

    @SuppressWarnings("UnusedVariable")
    private void onDevToolsMethodResult(
            @Nullable CefBrowser browser, int messageId, boolean success, @Nullable ByteBuffer result) {
        byte[] bytes = bytes(result);
        if (success) requests.complete(messageId, bytes);
        else requests.fail(messageId, decodeError(bytes));
    }

    @SuppressWarnings("UnusedVariable")
    private void onDevToolsEvent(@Nullable CefBrowser browser, @Nullable String method, @Nullable ByteBuffer params) {
        if (!open.get() || method == null) return;
        CopyOnWriteArrayList<Consumer<byte[]>> current = handlers.get(method);
        if (current == null) return;
        byte[] bytes = bytes(params);
        for (Consumer<byte[]> handler : current) {
            try {
                handler.accept(bytes);
            } catch (RuntimeException failure) {
                LOG.warn("DevTools event handler failed for {}", method, failure);
            }
        }
    }

    @SuppressWarnings("UnusedVariable")
    private void onDevToolsAgentDetached(@Nullable CefBrowser browser) {
        terminate(new IllegalStateException("DevTools agent detached"));
    }

    @Override
    public void close() {
        terminate(new IllegalStateException("DevTools session is closed"));
    }

    private void terminate(IllegalStateException failure) {
        if (!open.compareAndSet(true, false)) return;
        handlers.clear();
        requests.failAll(failure);
        Object current = registration;
        registration = null;
        observer = null;
        if (current != null)
            onUiThread(() -> {
                        current.getClass().getMethod("close").invoke(current);
                        return Boolean.TRUE;
                    })
                    .exceptionally(closeFailure -> {
                        LOG.debug("DevTools observer close failed", closeFailure);
                        return Boolean.FALSE;
                    });
    }

    private CdpException decodeError(byte[] bytes) {
        try {
            JsonObject error = jsonCodec.decode(bytes).asObject();
            JsonElement code = error.get("code");
            JsonElement message = error.get("message");
            if (code != null && message != null) {
                return new CdpException(code.intValue(), message.string(), error.get("data"));
            }
        } catch (RuntimeException ignored) {
            return undecodedError(bytes);
        }
        return undecodedError(bytes);
    }

    private static CdpException undecodedError(byte[] bytes) {
        return new CdpException(-1, new String(bytes, StandardCharsets.UTF_8), null);
    }

    private void completeFailure(int id, Throwable failure) {
        requests.fail(id, failure);
    }

    private static Object createObserver(Class<?> observerType, InProcessDevToolsSession session) {
        return Proxy.newProxyInstance(
                observerType.getClassLoader(), new Class<?>[] {observerType}, (proxy, method, args) -> {
                    switch (method.getName()) {
                        case "onDevToolsMessage":
                            return session.onDevToolsMessage((CefBrowser) args[0], (ByteBuffer) args[1]);
                        case "onDevToolsMethodResult":
                            session.onDevToolsMethodResult(
                                    (CefBrowser) args[0], (Integer) args[1], (Boolean) args[2], (ByteBuffer) args[3]);
                            return null;
                        case "onDevToolsEvent":
                            session.onDevToolsEvent((CefBrowser) args[0], (String) args[1], (ByteBuffer) args[2]);
                            return null;
                        case "onDevToolsAgentDetached":
                            session.onDevToolsAgentDetached((CefBrowser) args[0]);
                            return null;
                        case "onDevToolsAgentAttached":
                            return null;
                        case "toString":
                            return "InProcessDevToolsObserver";
                        case "hashCode":
                            return System.identityHashCode(proxy);
                        case "equals":
                            return proxy == args[0];
                        default:
                            throw new UnsupportedOperationException("Unexpected DevTools observer method: " + method);
                    }
                });
    }

    private static boolean sendDevToolsMessage(CefBrowserHost host, ByteBuffer message) throws Exception {
        Object accepted = host.getClass()
                .getMethod("sendDevToolsMessage", ByteBuffer.class)
                .invoke(host, message);
        if (accepted instanceof Boolean) return (Boolean) accepted;
        return ((Number) accepted).intValue() != 0;
    }

    private static byte[] bytes(@Nullable ByteBuffer source) {
        if (source == null) return new byte[0];
        ByteBuffer copy = source.duplicate();
        byte[] result = new byte[copy.remaining()];
        copy.get(result);
        return result;
    }

    static <T> CompletableFuture<T> onUiThread(UiCallable<T> action) {
        CefTaskRunner runner =
                CefTaskRunner.getForThread(CefThreadId.of(CefThreadId.Kind.UI)).orElse(null);
        if (runner == null) return failed(new IllegalStateException("CEF UI thread is unavailable"));
        CompletableFuture<T> result = new CompletableFuture<>();
        Runnable invoke = () -> {
            try {
                result.complete(action.call());
            } catch (Throwable failure) {
                result.completeExceptionally(failure);
            }
        };
        if (runner.belongsToCurrentThread()) invoke.run();
        else if (!runner.postTask(new CefTask() {
            @Override
            public void execute() {
                invoke.run();
            }
        })) result.completeExceptionally(new IllegalStateException("Failed to post CEF UI task"));
        runner.close();
        return result;
    }

    private static <T> CompletableFuture<T> failed(Throwable failure) {
        CompletableFuture<T> result = new CompletableFuture<>();
        result.completeExceptionally(failure);
        return result;
    }

    @FunctionalInterface
    interface UiCallable<T> {
        T call() throws Exception;
    }
}
