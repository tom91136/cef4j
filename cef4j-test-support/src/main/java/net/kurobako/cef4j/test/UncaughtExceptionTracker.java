package net.kurobako.cef4j.test;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;

/** Records the first uncaught thread failure so an owning test can report it on the test thread. */
public final class UncaughtExceptionTracker implements Thread.UncaughtExceptionHandler {
    private final AtomicReference<Failure> failure = new AtomicReference<>();

    @Override
    public void uncaughtException(@Nonnull Thread thread, @Nonnull Throwable throwable) {
        failure.compareAndSet(null, new Failure(thread.getName(), throwable));
    }

    /** Throws and clears the first recorded failure, if any. */
    public void throwIfPresent() {
        Failure recorded = failure.getAndSet(null);
        if (recorded != null) {
            throw new AssertionError("Uncaught exception on " + recorded.threadName, recorded.throwable);
        }
    }

    private static final class Failure {
        private final String threadName;
        private final Throwable throwable;

        private Failure(String threadName, Throwable throwable) {
            this.threadName = Objects.requireNonNull(threadName, "threadName");
            this.throwable = Objects.requireNonNull(throwable, "throwable");
        }
    }
}
