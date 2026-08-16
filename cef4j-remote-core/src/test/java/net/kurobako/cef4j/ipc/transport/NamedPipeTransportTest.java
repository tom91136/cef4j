package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class NamedPipeTransportTest {
    @Test
    @Timeout(2)
    void idleReaderDoesNotStartAReadThatBlocksSend() throws Exception {
        CountDownLatch availabilityChecked = new CountDownLatch(1);
        CountDownLatch readEntered = new CountDownLatch(1);
        CountDownLatch unblockRead = new CountDownLatch(1);
        InputStream blockingInput = new InputStream() {
            @Override
            public int available() {
                availabilityChecked.countDown();
                return 0;
            }

            @Override
            public int read() throws java.io.IOException {
                return awaitEndOfStream();
            }

            @Override
            public int read(byte[] bytes, int offset, int length) throws java.io.IOException {
                return awaitEndOfStream();
            }

            private int awaitEndOfStream() throws java.io.IOException {
                readEntered.countDown();
                try {
                    unblockRead.await();
                    return -1;
                } catch (InterruptedException failure) {
                    Thread.currentThread().interrupt();
                    throw new java.io.IOException(failure);
                }
            }
        };
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        NamedPipeTransport transport =
                new NamedPipeTransport("pipe://duplex-test", blockingInput, blockingInput, output);
        assertThat(availabilityChecked.await(1, TimeUnit.SECONDS)).isTrue();
        assertThat(readEntered.getCount()).isEqualTo(1L);

        org.junit.jupiter.api.Assertions.assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> transport.send(ByteBuffer.wrap(new byte[] {1, 2, 3})));
        assertThat(output.toByteArray()).containsExactly(0, 0, 0, 3, 1, 2, 3);

        transport.close();
        unblockRead.countDown();
    }

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
