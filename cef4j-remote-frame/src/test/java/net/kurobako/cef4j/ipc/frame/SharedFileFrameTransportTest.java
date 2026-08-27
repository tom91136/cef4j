package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.protocol.gen.OsrPaintEvent;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.TestExecutor;
import net.kurobako.cef4j.test.TestGate;
import org.junit.jupiter.api.Test;

class SharedFileFrameTransportTest {
    private static final AtomicLong NEXT_GENERATION = new AtomicLong();

    private final long frameGeneration = NEXT_GENERATION.incrementAndGet();

    @Test
    void currentJdksUseScopedMappedMemory() throws Exception {
        Path file = Files.createTempFile("cef4j-mapped-region-", ".frame");
        Files.write(file, new byte[] {1, 2, 3, 4});
        try (FileChannel channel = FileChannel.open(file)) {
            MappedBufferCleaner.Mapping mapping = MappedBufferCleaner.map(channel, 4);
            try {
                assertThat(mapping).isInstanceOf(AutoCloseable.class);
                assertThat(mapping.buffer().get(0)).isEqualTo((byte) 1);
                assertThat(mapping.isScoped()).isEqualTo(Runtime.version().feature() >= 21);
            } finally {
                assertThat(mapping.release()).isTrue();
            }
        } finally {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void replaysLatestPaintWhenConsumerIsInstalledAfterBinding() throws Exception {
        Path frame = frameFile("0");
        ByteBuffer contents = ByteBuffer.allocate(Long.BYTES + 8).order(ByteOrder.nativeOrder());
        contents.putLong(2L).put(new byte[] {1, 2, 3, 4, 5, 6, 7, 8});
        Files.write(frame, contents.array());

        OsrPaintEvent latest = new OsrPaintEvent(new RemoteHandle(7), frame.toString(), 2L, 2, 1, 8, 0, 0, 0, 2, 1);
        Thread caller = Thread.currentThread();
        CompletableFuture<byte[]> observed = new CompletableFuture<>();
        AtomicReference<Thread> callbackThread = new AtomicReference<>();
        try (FrameTransport transport = SharedFileFrameTransport.bindAll(new LatestEventSession(latest))) {
            transport.onRawFrame(value -> {
                byte[] pixels = new byte[value.pixels().remaining()];
                value.pixels().get(pixels);
                callbackThread.set(Thread.currentThread());
                observed.complete(pixels);
            });
            assertThat(observed.get(5, TimeUnit.SECONDS)).containsExactly(new byte[] {1, 2, 3, 4, 5, 6, 7, 8});
            assertThat(callbackThread.get()).isNotSameAs(caller);
        } finally {
            Files.deleteIfExists(frame);
        }
    }

    private static final class LatestEventSession implements CefSession {
        private final OsrPaintEvent latest;

        private LatestEventSession(OsrPaintEvent latest) {
            this.latest = latest;
        }

        @Override
        public <R extends CefMessageView> CompletableFuture<R> request(
                CefMessageEncoder request, CefMessageDecoder<R> decoder) {
            throw new UnsupportedOperationException();
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration on(
                int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
            return () -> {};
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration onLatest(
                int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
            ByteBuffer payload = ByteBuffer.allocate(latest.encodedSize());
            latest.encodeInto(payload);
            payload.flip();
            handler.accept(decoder.decode(payload));
            return () -> {};
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration intercept(
                int messageId, CefMessageDecoder<E> decoder, InterceptHandler<E> handler) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void close() {}
    }

    @Test
    void consumerIsNeverInvokedConcurrently() throws Exception {
        Path frame0 = frameFile("0");
        Path frame1 = frameFile("1");
        ByteBuffer contents = ByteBuffer.allocate(Long.BYTES + 8).order(ByteOrder.nativeOrder());
        contents.putLong(2L).put(new byte[] {1, 2, 3, 4, 5, 6, 7, 8});
        byte[] payload = contents.array();
        Files.write(frame0, payload);
        Files.write(frame1, payload);
        try {
            OsrPaintEvent event0 =
                    new OsrPaintEvent(new RemoteHandle(7), frame0.toString(), 2L, 2, 1, 8, 0, 0, 0, 2, 1);
            OsrPaintEvent event1 =
                    new OsrPaintEvent(new RemoteHandle(7), frame1.toString(), 2L, 2, 1, 8, 0, 0, 0, 2, 1);
            StoringSession session = new StoringSession();
            try (TestExecutor executor = TestExecutor.fixed(2, "shared-file-frame");
                    TestGate gate = new TestGate();
                    FrameTransport transport = SharedFileFrameTransport.bindAll(session)) {
                AtomicInteger inFlight = new AtomicInteger();
                AtomicBoolean overlapped = new AtomicBoolean();
                transport.onFrame((width, height, pixels, meta) -> {
                    if (inFlight.incrementAndGet() > 1) overlapped.set(true);
                    try {
                        gate.enter();
                    } finally {
                        inFlight.decrementAndGet();
                    }
                });
                CompletableFuture<Void> a = CompletableFuture.runAsync(() -> session.fire(event0), executor);
                CompletableFuture<Void> b = CompletableFuture.runAsync(() -> session.fire(event1), executor);
                TestDeadline deadline = TestDeadline.after(java.time.Duration.ofSeconds(5));
                gate.awaitEntered(deadline, "first frame callback");
                gate.release();
                deadline.await(CompletableFuture.allOf(a, b), "serialized frame callbacks");
                assertThat(overlapped).isFalse();
            }
        } finally {
            Files.deleteIfExists(frame0);
            Files.deleteIfExists(frame1);
        }
    }

    private Path frameFile(String slot) {
        return Path.of(System.getProperty("java.io.tmpdir"))
                .resolve("cef4j-paint-" + ProcessHandle.current().pid() + "-7-" + frameGeneration + "-" + slot
                        + ".frame");
    }

    private static final class StoringSession implements CefSession {
        @Nullable
        private Consumer<OsrPaintEvent> handler;

        @Override
        public <R extends CefMessageView> CompletableFuture<R> request(
                CefMessageEncoder request, CefMessageDecoder<R> decoder) {
            throw new UnsupportedOperationException();
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration on(
                int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
            throw new UnsupportedOperationException();
        }

        @Override
        @SuppressWarnings("unchecked")
        public <E extends CefMessageView> HandlerRegistration onLatest(
                int messageId, CefMessageDecoder<E> decoder, Consumer<E> handler) {
            this.handler = (Consumer<OsrPaintEvent>) handler;
            return () -> {};
        }

        @Override
        public <E extends CefMessageView> HandlerRegistration intercept(
                int messageId, CefMessageDecoder<E> decoder, InterceptHandler<E> handler) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void close() {}

        void fire(OsrPaintEvent event) {
            Objects.requireNonNull(handler, "no onLatest handler installed").accept(event);
        }
    }
}
