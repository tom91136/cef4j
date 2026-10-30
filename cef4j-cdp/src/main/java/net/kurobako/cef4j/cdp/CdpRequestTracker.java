package net.kurobako.cef4j.cdp;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/** Allocates CDP message identifiers and owns the lifecycle of their pending results. */
public final class CdpRequestTracker<T> {
    private final AtomicInteger nextId = new AtomicInteger();
    private final ConcurrentHashMap<Integer, Request<T>> pending = new ConcurrentHashMap<>();

    @SuppressWarnings("FutureReturnValueIgnored")
    public Request<T> register() {
        int id = allocateId();
        Request<T> request = new Request<>(id);
        while (pending.putIfAbsent(id, request) != null) {
            id = allocateId();
            request = new Request<>(id);
        }
        trackCompletion(id, request);
        return request;
    }

    /** The identifier of the next request, which wraps around when the counter is exhausted. */
    private int allocateId() {
        return nextId.updateAndGet(previous -> previous == Integer.MAX_VALUE ? 1 : previous + 1);
    }

    /** Forgets the request once it is done. */
    @SuppressWarnings("FutureReturnValueIgnored")
    private void trackCompletion(int id, Request<T> request) {
        request.whenComplete((ignored, failure) -> pending.remove(id, request));
    }

    public void complete(int id, T value) {
        final Request<T> request = pending.remove(id);
        if (request != null) request.complete(Objects.requireNonNull(value, "value"));
    }

    public void fail(int id, Throwable failure) {
        final Request<T> request = pending.remove(id);
        if (request != null) request.completeExceptionally(Objects.requireNonNull(failure, "failure"));
    }

    public void failAll(Throwable failure) {
        Objects.requireNonNull(failure, "failure");
        pending.forEach((id, request) -> {
            if (pending.remove(id, request)) request.completeExceptionally(failure);
        });
    }

    int pendingCount() {
        return pending.size();
    }

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
