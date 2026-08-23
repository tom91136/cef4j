package net.kurobako.cef4j.cdp;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;

/** Allocates CDP message identifiers and owns the lifecycle of their pending results. */
public final class CdpRequestTracker<T> {
    private final AtomicInteger nextId = new AtomicInteger();
    private final ConcurrentHashMap<Integer, Request<T>> pending = new ConcurrentHashMap<>();

    @Nonnull
    @SuppressWarnings("FutureReturnValueIgnored")
    public Request<T> register() {
        while (true) {
            int id = nextId.updateAndGet(previous -> previous == Integer.MAX_VALUE ? 1 : previous + 1);
            Request<T> request = new Request<>(id);
            if (pending.putIfAbsent(id, request) != null) continue;
            request.whenComplete((ignored, failure) -> pending.remove(id, request));
            return request;
        }
    }

    public void complete(int id, @Nonnull T value) {
        Request<T> request = pending.remove(id);
        if (request != null) request.complete(Objects.requireNonNull(value, "value"));
    }

    public void fail(int id, @Nonnull Throwable failure) {
        Request<T> request = pending.remove(id);
        if (request != null) request.completeExceptionally(Objects.requireNonNull(failure, "failure"));
    }

    public void failAll(@Nonnull Throwable failure) {
        Objects.requireNonNull(failure, "failure");
        pending.forEach((id, request) -> {
            if (pending.remove(id, request)) request.completeExceptionally(failure);
        });
    }

    int pendingCount() {
        return pending.size();
    }

    @SuppressWarnings("serial")
    public static final class Request<T> extends CompletableFuture<T> {
        private final int id;

        private Request(int id) {
            this.id = id;
        }

        public int id() {
            return id;
        }
    }
}
