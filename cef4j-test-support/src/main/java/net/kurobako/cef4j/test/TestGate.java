package net.kurobako.cef4j.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public final class TestGate implements AutoCloseable {
    private final CountDownLatch entered = new CountDownLatch(1);
    private final CountDownLatch released = new CountDownLatch(1);

    public void enter() {
        entered.countDown();
        try {
            released.await();
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("interrupted while waiting for test gate", interrupted);
        }
    }

    public void awaitEntered(TestDeadline deadline, String phase) throws InterruptedException, TimeoutException {
        deadline.await(entered, phase);
    }

    public void release() {
        released.countDown();
    }

    @Override
    public void close() {
        release();
    }
}
