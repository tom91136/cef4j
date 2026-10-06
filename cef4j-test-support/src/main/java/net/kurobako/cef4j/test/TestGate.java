package net.kurobako.cef4j.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public final class TestGate implements AutoCloseable {
    private final CompletableFuture<Void> entered = new CompletableFuture<>();
    private final CountDownLatch released = new CountDownLatch(1);

    public void enter() {
        entered.complete(null);
        try {
            released.await();
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("interrupted while waiting for test gate", interrupted);
        }
    }

    public void awaitEntered(TestDeadline deadline, String phase)
            throws InterruptedException, ExecutionException, TimeoutException {
        deadline.await(entered, phase);
    }

    /** Waits for entry, failing early with the cause if {@code abort} completes exceptionally first. */
    public void awaitEntered(TestDeadline deadline, CompletableFuture<?> abort, String phase)
            throws InterruptedException, ExecutionException, TimeoutException {
        deadline.await(CompletableFuture.anyOf(entered, abort), phase);
        if (!entered.isDone()) throw new IllegalStateException(phase + " completed without entering the gate");
    }

    public void release() {
        released.countDown();
    }

    @Override
    public void close() {
        release();
    }
}
