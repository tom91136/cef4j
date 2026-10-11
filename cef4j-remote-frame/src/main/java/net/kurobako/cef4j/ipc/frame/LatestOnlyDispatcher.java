package net.kurobako.cef4j.ipc.frame;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import org.jspecify.annotations.Nullable;

/** Coalesces a burst of values into at most one pending executor task, retaining only the newest value. */
public final class LatestOnlyDispatcher<T> {
    private final Executor executor;
    private final Consumer<T> consumer;
    private final AtomicReference<@Nullable T> latest = new AtomicReference<>();
    private final AtomicBoolean scheduled = new AtomicBoolean();

    public LatestOnlyDispatcher(Executor executor, Consumer<T> consumer) {
        this.executor = Objects.requireNonNull(executor, "executor");
        this.consumer = Objects.requireNonNull(consumer, "consumer");
    }

    public void submit(T value) {
        latest.set(Objects.requireNonNull(value, "value"));
        schedule();
    }

    private void schedule() {
        if (!scheduled.compareAndSet(false, true)) return;
        try {
            executor.execute(this::dispatch);
        } catch (RuntimeException rejection) {
            scheduled.set(false);
            throw rejection;
        }
    }

    private void dispatch() {
        T value = latest.getAndSet(null);
        try {
            if (value != null) consumer.accept(value);
        } finally {
            scheduled.set(false);
            if (latest.get() != null) schedule();
        }
    }
}
