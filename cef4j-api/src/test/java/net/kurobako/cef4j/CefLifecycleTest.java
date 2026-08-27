package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import net.kurobako.cef4j.test.TestDeadline;
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
        TestDeadline.after(Duration.ofSeconds(2)).join(waiter, "shutdown waiter");

        assertThat(completed).isTrue();
        assertThat(interrupted).isTrue();
    }
}
