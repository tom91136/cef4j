package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Closeable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class NamedPipeTransportTest {
    @Test
    void closeDoesNotWaitForBlockedNativeIo() throws Exception {
        CountDownLatch closeEntered = new CountDownLatch(1);
        CountDownLatch unblockClose = new CountDownLatch(1);
        AtomicBoolean closed = new AtomicBoolean();
        Closeable blockingHandle = () -> {
            closeEntered.countDown();
            try {
                unblockClose.await();
                closed.set(true);
            } catch (InterruptedException failure) {
                Thread.currentThread().interrupt();
            }
        };

        long started = System.nanoTime();
        Thread closer = NamedPipeTransport.closeAsync("pipe://blocked-test", blockingHandle);
        assertThat(closeEntered.await(1, TimeUnit.SECONDS)).isTrue();
        assertThat(System.nanoTime() - started).isLessThan(TimeUnit.SECONDS.toNanos(1));
        assertThat(closer.isDaemon()).isTrue();
        assertThat(closed).isFalse();

        unblockClose.countDown();
        closer.join(TimeUnit.SECONDS.toMillis(1));
        assertThat(closed).isTrue();
    }
}
