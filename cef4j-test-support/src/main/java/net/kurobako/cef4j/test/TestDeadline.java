package net.kurobako.cef4j.test;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Predicate;

/** One monotonic timeout budget shared by every asynchronous phase of a test operation. */
public final class TestDeadline {
    @FunctionalInterface
    public interface CheckedSupplier<T> {
        T get() throws Exception;
    }

    private final long startedNanos;
    private final long timeoutNanos;
    private final Duration budget;

    private TestDeadline(Duration budget) {
        this.budget = Objects.requireNonNull(budget, "budget");
        if (budget.isNegative()) throw new IllegalArgumentException("deadline budget must not be negative");
        startedNanos = System.nanoTime();
        long durationNanos;
        try {
            durationNanos = budget.toNanos();
        } catch (ArithmeticException overflow) {
            durationNanos = Long.MAX_VALUE;
        }
        timeoutNanos = durationNanos;
    }

    public static TestDeadline after(Duration budget) {
        return new TestDeadline(budget);
    }

    public long remainingNanos() {
        long elapsed = System.nanoTime() - startedNanos;
        if (elapsed <= 0L) return timeoutNanos;
        return elapsed >= timeoutNanos ? 0L : timeoutNanos - elapsed;
    }

    public Duration remaining() {
        return Duration.ofNanos(remainingNanos());
    }

    public Duration remainingUpTo(Duration maximum) {
        Objects.requireNonNull(maximum, "maximum");
        if (maximum.isNegative()) throw new IllegalArgumentException("maximum wait must not be negative");
        long maximumNanos;
        try {
            maximumNanos = maximum.toNanos();
        } catch (ArithmeticException overflow) {
            maximumNanos = Long.MAX_VALUE;
        }
        return Duration.ofNanos(Math.min(remainingNanos(), maximumNanos));
    }

    public boolean isExpired() {
        return remainingNanos() == 0L;
    }

    public <T> T await(CompletableFuture<T> attempt, String phase)
            throws InterruptedException, ExecutionException, TimeoutException {
        return await(attempt, phase, Duration.ofNanos(Long.MAX_VALUE));
    }

    public <T> T await(CompletableFuture<T> attempt, String phase, Duration attemptLimit)
            throws InterruptedException, ExecutionException, TimeoutException {
        Objects.requireNonNull(attempt, "attempt");
        Objects.requireNonNull(phase, "phase");
        long remaining = remainingUpTo(attemptLimit).toNanos();
        try {
            return attempt.get(remaining, TimeUnit.NANOSECONDS);
        } catch (TimeoutException timedOut) {
            attempt.cancel(true);
            throw timeout(phase);
        }
    }

    public void runOn(boolean owningThread, Consumer<Runnable> scheduler, Runnable action, String phase)
            throws InterruptedException, ExecutionException, TimeoutException {
        Objects.requireNonNull(scheduler, "scheduler");
        Objects.requireNonNull(action, "action");
        if (owningThread) {
            action.run();
            return;
        }
        CompletableFuture<Void> completion = new CompletableFuture<>();
        scheduler.accept(() -> {
            if (completion.isCancelled()) return;
            try {
                action.run();
                completion.complete(null);
            } catch (Throwable failure) {
                completion.completeExceptionally(failure);
            }
        });
        await(completion, phase);
    }

    public void until(BooleanSupplier condition, Duration pollInterval, String phase)
            throws InterruptedException, TimeoutException {
        until(condition, () -> {}, pollInterval, phase);
    }

    public void until(BooleanSupplier condition, Runnable progress, Duration pollInterval, String phase)
            throws InterruptedException, TimeoutException {
        Objects.requireNonNull(condition, "condition");
        Objects.requireNonNull(progress, "progress");
        Objects.requireNonNull(pollInterval, "pollInterval");
        long pollNanos = pollInterval.toNanos();
        if (pollNanos <= 0L) throw new IllegalArgumentException("poll interval must be positive");
        while (!condition.getAsBoolean()) {
            progress.run();
            if (condition.getAsBoolean()) return;
            long remaining = remainingNanos();
            if (remaining == 0L) throw timeout(phase);
            TimeUnit.NANOSECONDS.sleep(Math.min(remaining, pollNanos));
        }
    }

    public <T> T poll(CheckedSupplier<T> probe, Predicate<T> complete, Duration pollInterval, String phase)
            throws Exception {
        Objects.requireNonNull(probe, "probe");
        Objects.requireNonNull(complete, "complete");
        Objects.requireNonNull(pollInterval, "pollInterval");
        long pollNanos = pollInterval.toNanos();
        if (pollNanos <= 0L) throw new IllegalArgumentException("poll interval must be positive");
        while (true) {
            T value = probe.get();
            if (complete.test(value)) return value;
            long remaining = remainingNanos();
            if (remaining == 0L) throw timeout(phase);
            TimeUnit.NANOSECONDS.sleep(Math.min(remaining, pollNanos));
        }
    }

    public void await(CountDownLatch latch, String phase) throws InterruptedException, TimeoutException {
        Objects.requireNonNull(latch, "latch");
        Objects.requireNonNull(phase, "phase");
        if (!latch.await(remainingNanos(), TimeUnit.NANOSECONDS)) throw timeout(phase);
    }

    public void join(Thread thread, String phase) throws InterruptedException, TimeoutException {
        Objects.requireNonNull(thread, "thread");
        Objects.requireNonNull(phase, "phase");
        long remaining = remainingNanos();
        if (remaining == 0L) throw timeout(phase);
        thread.join(TimeUnit.NANOSECONDS.toMillis(remaining), (int) (remaining % 1_000_000L));
        if (thread.isAlive()) throw timeout(phase);
    }

    private TimeoutException timeout(String phase) {
        long elapsedNanos = Math.max(0L, System.nanoTime() - startedNanos);
        return new TimeoutException(
                phase + " did not complete within " + budget + " (elapsed " + Duration.ofNanos(elapsedNanos) + ")");
    }
}
