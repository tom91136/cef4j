package net.kurobako.cef4j.webdriver.inprocess;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserHost;
import net.kurobako.cef4j.gen.CefDevToolsMessageObserver;
import net.kurobako.cef4j.gen.CefRegistration;
import net.kurobako.cef4j.gen.CefTask;
import net.kurobako.cef4j.gen.CefTaskRunner;
import net.kurobako.cef4j.gen.CefThreadId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CDP transport for a browser hosted in the current JVM. It uses CEF's observer API and does not require a remote
 * debugging port or a system Chrome installation.
 */
public final class InProcessDevToolsSession implements CdpTransport, CefDevToolsMessageObserver {
    private static final Logger LOG = LoggerFactory.getLogger(InProcessDevToolsSession.class);

    private final CefBrowserHost host;
    private final AtomicInteger nextMessageId = new AtomicInteger();
    private final AtomicBoolean open = new AtomicBoolean(true);
    private final ConcurrentHashMap<Integer, CompletableFuture<byte[]>> pending = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Consumer<byte[]>>> handlers =
            new ConcurrentHashMap<>();

    @Nullable
    private volatile CefRegistration registration;

    private InProcessDevToolsSession(CefBrowserHost host) {
        this.host = host;
    }

    @Nonnull
    public static CompletableFuture<InProcessDevToolsSession> attach(@Nonnull CefBrowser browser) {
        Objects.requireNonNull(browser, "browser");
        CefBrowserHost host = browser.getHost().orElse(null);
        if (host == null) return failed(new IllegalStateException("in-process browser has no host"));
        InProcessDevToolsSession session = new InProcessDevToolsSession(host);
        return onUiThread(() -> {
                    session.registration = host.addDevToolsMessageObserver(session)
                            .orElseThrow(() -> new IllegalStateException("CEF rejected DevTools observer"));
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
        int id = nextMessageId.updateAndGet(previous -> previous == Integer.MAX_VALUE ? 1 : previous + 1);
        CompletableFuture<byte[]> result = new CompletableFuture<>();
        pending.put(id, result);
        if (!open.get() && pending.remove(id, result)) {
            result.completeExceptionally(new IllegalStateException("DevTools session is closed"));
            return result;
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
        onUiThread(() -> host.sendDevToolsMessage(message)).whenComplete((accepted, failure) -> {
            if (failure != null) completeFailure(id, failure);
            else if (!accepted) completeFailure(id, new IllegalStateException("CEF rejected DevTools message " + id));
        });
        return result;
    }

    @Override
    @Nonnull
    public CdpSubscription subscribe(@Nonnull String method, @Nonnull Consumer<byte[]> handler) {
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(handler, "handler");
        CopyOnWriteArrayList<Consumer<byte[]>> current =
                handlers.computeIfAbsent(method, ignored -> new CopyOnWriteArrayList<>());
        current.add(handler);
        return () -> current.remove(handler);
    }

    @Override
    public boolean onDevToolsMessage(@Nullable CefBrowser browser, @Nonnull ByteBuffer message) {
        return false;
    }

    @Override
    public void onDevToolsMethodResult(
            @Nullable CefBrowser browser, int messageId, boolean success, @Nullable ByteBuffer result) {
        CompletableFuture<byte[]> future = pending.remove(messageId);
        if (future == null) return;
        byte[] bytes = bytes(result);
        if (success) future.complete(bytes);
        else future.completeExceptionally(new IllegalStateException(new String(bytes, StandardCharsets.UTF_8)));
    }

    @Override
    public void onDevToolsEvent(@Nullable CefBrowser browser, @Nullable String method, @Nullable ByteBuffer params) {
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

    @Override
    public void onDevToolsAgentDetached(@Nullable CefBrowser browser) {
        failPending(new IllegalStateException("DevTools agent detached"));
    }

    @Override
    public void close() {
        if (!open.compareAndSet(true, false)) return;
        handlers.clear();
        failPending(new IllegalStateException("DevTools session is closed"));
        CefRegistration current = registration;
        registration = null;
        if (current != null)
            onUiThread(() -> {
                        current.close();
                        return Boolean.TRUE;
                    })
                    .exceptionally(failure -> {
                        LOG.debug("DevTools observer close failed", failure);
                        return Boolean.FALSE;
                    });
    }

    private void completeFailure(int id, Throwable failure) {
        CompletableFuture<byte[]> future = pending.remove(id);
        if (future != null) future.completeExceptionally(failure);
    }

    private void failPending(Throwable failure) {
        pending.forEach((id, future) -> {
            if (pending.remove(id, future)) future.completeExceptionally(failure);
        });
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
        T call();
    }
}
