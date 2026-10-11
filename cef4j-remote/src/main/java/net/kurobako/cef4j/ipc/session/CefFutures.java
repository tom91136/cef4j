package net.kurobako.cef4j.ipc.session;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

public final class CefFutures {
    private CefFutures() {}

    @SuppressWarnings("FutureReturnValueIgnored")
    public static <S, T> CompletableFuture<T> map(
            CompletableFuture<S> source, Function<? super S, ? extends T> mapper) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(mapper, "mapper");
        PropagatingFuture<T> result = new PropagatingFuture<>(source::cancel);
        source.whenComplete((value, failure) -> {
            if (failure != null) {
                result.completeExceptionally(failure);
            } else if (!result.isDone()) {
                try {
                    result.complete(mapper.apply(value));
                } catch (Throwable mappingFailure) {
                    result.completeExceptionally(mappingFailure);
                }
            }
        });
        return result;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    public static CompletableFuture<Void> discard(CompletableFuture<?> source) {
        Objects.requireNonNull(source, "source");
        PropagatingFuture<Void> result = new PropagatingFuture<>(source::cancel);
        source.whenComplete((value, failure) -> {
            if (failure != null) result.completeExceptionally(failure);
            else result.complete(null);
        });
        return result;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    public static <S, T> CompletableFuture<T> flatMap(
            CompletableFuture<S> source, Function<? super S, ? extends CompletableFuture<T>> mapper) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(mapper, "mapper");
        AtomicReference<CompletableFuture<?>> active = new AtomicReference<>(source);
        PropagatingFuture<T> result = new PropagatingFuture<>(mayInterrupt ->
                Objects.requireNonNull(active.get(), "active future").cancel(mayInterrupt));
        source.whenComplete((value, failure) -> {
            if (failure != null) {
                result.completeExceptionally(failure);
                return;
            }
            if (result.isDone()) return;
            CompletableFuture<T> next;
            try {
                next = Objects.requireNonNull(mapper.apply(value), "future mapper returned null");
            } catch (Throwable mappingFailure) {
                result.completeExceptionally(mappingFailure);
                return;
            }
            if (!active.compareAndSet(source, next) || result.isDone()) {
                next.cancel(false);
                return;
            }
            next.whenComplete((mapped, nextFailure) -> {
                if (nextFailure != null) result.completeExceptionally(nextFailure);
                else result.complete(mapped);
            });
        });
        return result;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    public static void observeFailure(CompletableFuture<?> future, Consumer<? super Throwable> observer) {
        Objects.requireNonNull(future, "future");
        Objects.requireNonNull(observer, "observer");
        future.whenComplete((ignored, failure) -> {
            if (failure != null) observer.accept(failure);
        });
    }

    @SuppressWarnings("serial")
    private static final class PropagatingFuture<T> extends CompletableFuture<T> {
        private final java.util.function.Consumer<Boolean> cancellation;

        private PropagatingFuture(java.util.function.Consumer<Boolean> cancellation) {
            this.cancellation = cancellation;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            boolean cancelled = super.cancel(mayInterruptIfRunning);
            if (cancelled) cancellation.accept(mayInterruptIfRunning);
            return cancelled;
        }
    }
}
