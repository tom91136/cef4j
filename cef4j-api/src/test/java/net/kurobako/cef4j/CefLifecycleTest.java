package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class CefLifecycleTest {

    @Test
    void shutdownWaitCannotBeAbandonedByInterruption() throws Exception {
        CountDownLatch shutdown = new CountDownLatch(1);
        AtomicBoolean completed = new AtomicBoolean();
        AtomicBoolean interrupted = new AtomicBoolean();
        Thread waiter = new Thread(() -> {
            Cef.awaitUninterruptibly(shutdown);
            completed.set(true);
            interrupted.set(Thread.currentThread().isInterrupted());
        });
        waiter.start();

        waiter.interrupt();
        assertThat(completed).isFalse();
        shutdown.countDown();
        waiter.join(TimeUnit.SECONDS.toMillis(2));

        assertThat(completed).isTrue();
        assertThat(interrupted).isTrue();
    }
}
