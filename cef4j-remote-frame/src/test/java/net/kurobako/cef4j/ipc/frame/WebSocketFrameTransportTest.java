package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import net.kurobako.cef4j.ipc.protocol.gen.InlinePaintEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.transport.LoopbackTransport;
import org.junit.jupiter.api.Test;

final class WebSocketFrameTransportTest {
    @Test
    void deliversInlineBgraFrame() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                InlineFrameTransport frames = InlineFrameTransport.bindAll(session)) {
            byte[] pixels = new byte[] {1, 2, 3, 4, 5, 6, 7, 8};
            InlinePaintEvent event = new InlinePaintEvent(new RemoteHandle(7), 42L, 2, 1, 0, 1, 0, 1, 1, pixels);
            CountDownLatch arrived = new CountDownLatch(1);
            AtomicReference<byte[]> received = new AtomicReference<>();
            AtomicReference<FrameMetadata> metadata = new AtomicReference<>();
            frames.onFrame((width, height, buffer, meta) -> {
                assertThat(width).isEqualTo(2);
                assertThat(height).isEqualTo(1);
                byte[] copy = new byte[buffer.remaining()];
                buffer.get(copy);
                received.set(copy);
                metadata.set(meta);
                arrived.countDown();
            });

            sendEvent(pair.b, event);

            assertThat(arrived.await(5, TimeUnit.SECONDS)).isTrue();
            assertThat(received.get()).isEqualTo(pixels);
            FrameMetadata deliveredMetadata = Objects.requireNonNull(metadata.get());
            assertThat(deliveredMetadata.format()).isEqualTo(PixelFormat.BGRA);
            assertThat(deliveredMetadata.dirtyRects()).singleElement().satisfies(rect -> {
                assertThat(rect.x()).isZero();
                assertThat(rect.width()).isEqualTo(2);
            });
        } finally {
            pair.b.close();
        }
    }

    @Test
    void filtersByBrowserAndRejectsMalformedPixelLength() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                InlineFrameTransport frames = InlineFrameTransport.bind(session, new RemoteHandle(9))) {
            CountDownLatch arrived = new CountDownLatch(1);
            frames.onFrame((width, height, buffer, meta) -> arrived.countDown());
            sendEvent(pair.b, new InlinePaintEvent(new RemoteHandle(8), 1L, 1, 1, 0, 0, 0, 1, 1, new byte[4]));
            sendEvent(pair.b, new InlinePaintEvent(new RemoteHandle(9), 2L, 2, 2, 0, 0, 0, 2, 2, new byte[4]));

            assertThat(arrived.await(300, TimeUnit.MILLISECONDS)).isFalse();
        } finally {
            pair.b.close();
        }
    }

    private static void sendEvent(LoopbackTransport peer, InlinePaintEvent event) throws Exception {
        ByteBuffer frame =
                ByteBuffer.allocate(Envelope.HEADER_SIZE + event.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(
                frame, Envelope.Kind.EVENT, 0, Envelope.NO_CORR_ID, InlinePaintEvent.MESSAGE_ID, event.encodedSize());
        event.encodeInto(frame);
        frame.flip();
        peer.send(frame);
    }
}
