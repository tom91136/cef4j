package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import net.kurobako.cef4j.test.TestDeadline;
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
        try {
            assertThat(availabilityChecked.await(1, TimeUnit.SECONDS)).isTrue();
            assertThat(readEntered.getCount()).isEqualTo(1L);

            org.junit.jupiter.api.Assertions.assertTimeoutPreemptively(
                    Duration.ofSeconds(1), () -> transport.send(ByteBuffer.wrap(new byte[] {1, 2, 3})));
            assertThat(output.toByteArray()).containsExactly(0, 0, 0, 3, 1, 2, 3);
        } finally {
            transport.close();
            unblockRead.countDown();
        }
    }

    @Test
    void closeDoesNotWaitForBlockedNativeIo() throws Exception {
        CountDownLatch closeEntered = new CountDownLatch(1);
        CountDownLatch closeFinished = new CountDownLatch(1);
        CountDownLatch unblockClose = new CountDownLatch(1);
        AtomicBoolean closed = new AtomicBoolean();
        Closeable blockingHandle = () -> {
            closeEntered.countDown();
            try {
                unblockClose.await();
                closed.set(true);
            } catch (InterruptedException failure) {
                Thread.currentThread().interrupt();
            } finally {
                closeFinished.countDown();
            }
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        NamedPipeTransport.closeAsync("pipe://blocked-test", blockingHandle, executor);
        try {
            assertThat(closeEntered.await(1, TimeUnit.SECONDS)).isTrue();
            assertThat(closed).isFalse();
        } finally {
            unblockClose.countDown();
        }
        TestDeadline.after(Duration.ofSeconds(1)).await(closeFinished, "blocked native close");
        assertThat(executor.isShutdown()).isFalse();
        executor.shutdownNow();
        assertThat(closed).isTrue();
    }

    @Test
    void closeFallsBackWhenSuppliedExecutorRejects() throws Exception {
        CountDownLatch closed = new CountDownLatch(1);

        NamedPipeTransport.closeAsync("pipe://rejected-close-test", closed::countDown, command -> {
            throw new RejectedExecutionException("stopped");
        });

        TestDeadline.after(Duration.ofSeconds(1)).await(closed, "rejected native close");
    }
}
