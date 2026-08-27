package net.kurobako.cef4j.test;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class TestExecutor implements Executor, AutoCloseable {
    private static final Duration SHUTDOWN_TIMEOUT = Duration.ofSeconds(5);

    private final ExecutorService delegate;

    private TestExecutor(int threads, String threadName) {
        if (threads <= 0) throw new IllegalArgumentException("thread count must be positive");
        Objects.requireNonNull(threadName, "threadName");
        AtomicInteger sequence = new AtomicInteger();
        ThreadFactory factory = task -> {
            Thread thread = new Thread(task, threadName + "-" + sequence.incrementAndGet());
            thread.setDaemon(true);
            return thread;
        };
        delegate = Executors.newFixedThreadPool(threads, factory);
    }

    public static TestExecutor single(String threadName) {
        return new TestExecutor(1, threadName);
    }

    public static TestExecutor fixed(int threads, String threadName) {
        return new TestExecutor(threads, threadName);
    }

    @Override
    public void execute(Runnable command) {
        delegate.execute(command);
    }

    @Override
    public void close() {
        delegate.shutdownNow();
        try {
            if (!delegate.awaitTermination(SHUTDOWN_TIMEOUT.toNanos(), TimeUnit.NANOSECONDS)) {
                throw new IllegalStateException("test executor did not terminate within " + SHUTDOWN_TIMEOUT);
            }
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("interrupted while closing test executor", interrupted);
        }
    }
}
