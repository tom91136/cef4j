package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import net.kurobako.cef4j.ipc.protocol.gen.OsrPaintEvent;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.junit.jupiter.api.Test;

class MmapFrameTransportTest {
    @Test
    void replaysLatestPaintWhenConsumerIsInstalledAfterBinding() throws Exception {
        Path frame = Path.of(
                System.getProperty("java.io.tmpdir"),
                "cef4j-paint-" + ProcessHandle.current().pid() + "-7-1-0.frame");
        ByteBuffer contents = ByteBuffer.allocate(Long.BYTES + 8).order(ByteOrder.nativeOrder());
        contents.putLong(2L).put(new byte[] {1, 2, 3, 4, 5, 6, 7, 8});
        Files.write(frame, contents.array());

        OsrPaintEvent latest = new OsrPaintEvent(new RemoteHandle(7), frame.toString(), 2L, 2, 1, 8, 0, 0, 0, 2, 1);
        AtomicReference<byte[]> observed = new AtomicReference<>();
        try (FrameTransport transport = SharedFileFrameTransport.bindAll(new LatestEventSession(latest))) {
            transport.onRawFrame(value -> {
                byte[] pixels = new byte[value.pixels().remaining()];
                value.pixels().get(pixels);
                observed.set(pixels);
            });
            assertThat(observed.get()).containsExactly(new byte[] {1, 2, 3, 4, 5, 6, 7, 8});
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
}
