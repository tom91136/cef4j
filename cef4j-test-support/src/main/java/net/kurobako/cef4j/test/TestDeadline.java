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
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** One monotonic timeout budget shared by every asynchronous phase of a test operation. */
// The action runs on the thread of the scheduler, and a failure in it, of any kind, has to reach the test on the thread
// that waits for the action; await(...) rethrows it there, so it is not swallowed.
@SuppressWarnings("bonsai:catch-throwable")
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
        } catch (ArithmeticException ignored) {
            durationNanos = Long.MAX_VALUE;
        }
        timeoutNanos = durationNanos;
    }

    public static TestDeadline after(Duration budget) {
        return new TestDeadline(budget);
    }

    public long remainingNanos() {
        final long elapsed = System.nanoTime() - startedNanos;
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
        } catch (ArithmeticException ignored) {
            maximumNanos = Long.MAX_VALUE;
        }
        return Duration.ofNanos(Math.min(remainingNanos(), maximumNanos));
    }

    public boolean hasTimeRemaining() {
        return remainingNanos() > 0L;
    }

    @NullableBoundary("futures may complete with null")
    public <T extends @Nullable Object> T await(CompletableFuture<T> attempt, String phase)
            throws InterruptedException, ExecutionException, TimeoutException {
        return await(attempt, phase, Duration.ofNanos(Long.MAX_VALUE));
    }

    @NullableBoundary("futures may complete with null")
    public <T extends @Nullable Object> T await(CompletableFuture<T> attempt, String phase, Duration attemptLimit)
            throws InterruptedException, ExecutionException, TimeoutException {
        Objects.requireNonNull(attempt, "attempt");
        Objects.requireNonNull(phase, "phase");
        final long remaining = remainingUpTo(attemptLimit).toNanos();
        try {
            return attempt.get(remaining, TimeUnit.NANOSECONDS);
        } catch (TimeoutException timedOut) {
            attempt.cancel(true);
            final TimeoutException failure =
                    remaining < remainingNanos() ? timeout(phase, attemptLimit) : timeout(phase);
            failure.initCause(timedOut);
            throw failure;
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
        final CompletableFuture<Boolean> completion = new CompletableFuture<>();
        scheduler.accept(() -> {
            if (completion.isCancelled()) return;
            try {
                action.run();
                completion.complete(Boolean.TRUE);
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
        final long pollNanos = requirePollNanos(pollInterval);
        while (!condition.getAsBoolean()) {
            progress.run();
            if (condition.getAsBoolean()) return;
            final long remaining = remainingNanos();
            if (remaining == 0L) throw timeout(phase);
            TimeUnit.NANOSECONDS.sleep(Math.min(remaining, pollNanos));
        }
    }

    public <T> T poll(CheckedSupplier<T> probe, Predicate<T> complete, Duration pollInterval, String phase)
            throws Exception {
        Objects.requireNonNull(probe, "probe");
        Objects.requireNonNull(complete, "complete");
        final long pollNanos = requirePollNanos(pollInterval);
        while (true) {
            final T value = probe.get();
            if (complete.test(value)) return value;
            final long remaining = remainingNanos();
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
        final long remaining = remainingNanos();
        if (remaining == 0L) throw timeout(phase);
        thread.join(TimeUnit.NANOSECONDS.toMillis(remaining), (int) (remaining % 1_000_000L));
        if (thread.isAlive()) throw timeout(phase);
    }

    private static long requirePollNanos(Duration pollInterval) {
        Objects.requireNonNull(pollInterval, "pollInterval");
        final long pollNanos = pollInterval.toNanos();
        if (pollNanos <= 0L) throw new IllegalArgumentException("poll interval must be positive");
        return pollNanos;
    }

    private TimeoutException timeout(String phase) {
        final long elapsedNanos = Math.max(0L, System.nanoTime() - startedNanos);
        return new TimeoutException(
                phase + " did not complete within " + budget + " (elapsed " + Duration.ofNanos(elapsedNanos) + ")");
    }

    private TimeoutException timeout(String phase, Duration attemptLimit) {
        final long elapsedNanos = Math.max(0L, System.nanoTime() - startedNanos);
        return new TimeoutException(phase + " attempt did not complete within " + attemptLimit + " (deadline " + budget
                + ", elapsed " + Duration.ofNanos(elapsedNanos) + ")");
    }
}
