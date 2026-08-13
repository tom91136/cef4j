package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import org.junit.jupiter.api.Test;

final class FrameCodecTest {
    @Test
    void negotiatesExactCodecAndRequestsKeyFrameAfterDeltaGap() throws Exception {
        CodecDescriptor delta = new CodecDescriptor("test-delta", "application/x-test", true);
        assertThat(FrameCodecNegotiation.select(
                                java.util.List.of(delta, new CodecDescriptor("jpeg", "image/jpeg", false)),
                                java.util.List.of(new CodecDescriptor("test-delta", "application/x-test", true)))
                        .orElseThrow())
                .isSameAs(delta);
        assertThat(FrameCodecNegotiation.select(
                        java.util.List.of(delta),
                        java.util.List.of(new CodecDescriptor("test-delta", "application/x-test", false))))
                .isEmpty();

        AtomicInteger keyFrameRequests = new AtomicInteger();
        FrameDecoder decoder = new FrameDecoder() {
            @Override
            public CodecDescriptor descriptor() {
                return delta;
            }

            @Override
            public RawFrame decode(EncodedFrame encoded) {
                return frame(
                        encoded.sequence(),
                        encoded.width(),
                        encoded.height(),
                        new byte[encoded.width() * encoded.height() * 4]);
            }
        };
        try (EncodedFrameReceiver receiver = new EncodedFrameReceiver(decoder, keyFrameRequests::incrementAndGet)) {
            assertThat(receiver.accept(encoded(delta, 10, EncodedFrame.NO_BASE_SEQUENCE, true)))
                    .isPresent();
            assertThat(receiver.accept(encoded(delta, 12, 11, false))).isEqualTo(Optional.empty());
            assertThat(keyFrameRequests).hasValue(1);
            assertThat(receiver.accept(encoded(delta, 13, EncodedFrame.NO_BASE_SEQUENCE, true)))
                    .isPresent();
            receiver.restart();
            assertThat(keyFrameRequests).hasValue(2);
        }
    }

    @Test
    void discoversAndRoundTripsJpeg() throws Exception {
        assertThat(FrameCodecs.available()).contains("jpeg", "raw-bgra");
        FrameCodecProvider provider = FrameCodecs.find("jpeg");
        RawFrame source = frame(17L, 2, 1, new byte[] {0, 0, (byte) 255, (byte) 255, (byte) 255, 0, 0, (byte) 255});
        try (FrameCodec encoder = provider.newEncoder(Map.of("quality", "0.95"));
                FrameDecoder decoder = provider.newDecoder(Map.of())) {
            EncodedFrame encoded = encoder.encode(source);
            assertThat(encoded.codec().mediaType()).isEqualTo("image/jpeg");
            assertThat(encoded.sequence()).isEqualTo(17L);
            assertThat(encoded.keyFrame()).isTrue();
            assertThat(encoded.baseSequence()).isEqualTo(EncodedFrame.NO_BASE_SEQUENCE);

            RawFrame decoded = decoder.decode(encoded);
            assertThat(decoded.width()).isEqualTo(2);
            assertThat(decoded.height()).isEqualTo(1);
            assertThat(decoded.pixels().remaining()).isEqualTo(8);
        }
    }

    @Test
    void servesChromeCompatibleMultipartMjpeg() throws Exception {
        TestFrameTransport frames = new TestFrameTransport();
        try (MjpegHttpServer server = MjpegHttpServer.start(MjpegHttpServer.Configuration.loopback(0))) {
            server.attach(frames);
            CompletableFuture<byte[]> response = CompletableFuture.supplyAsync(() -> {
                try {
                    HttpURLConnection connection =
                            (HttpURLConnection) server.endpoint().toURL().openConnection();
                    connection.setConnectTimeout(3000);
                    connection.setReadTimeout(5000);
                    assertThat(connection.getContentType()).startsWith("multipart/x-mixed-replace");
                    return connection.getInputStream().readNBytes(512);
                } catch (Exception failure) {
                    throw new RuntimeException(failure);
                }
            });
            frames.emit(frame(23L, 2, 1, new byte[] {0, 0, (byte) 255, (byte) 255, (byte) 255, 0, 0, (byte) 255}));
            byte[] multipart = response.get(8, TimeUnit.SECONDS);
            String header = new String(
                    multipart, 0, Math.min(multipart.length, 180), java.nio.charset.StandardCharsets.ISO_8859_1);
            assertThat(header).contains("Content-Type: image/jpeg").contains("X-Cef4j-Sequence: 23");
            assertThat(indexOf(multipart, new byte[] {(byte) 0xff, (byte) 0xd8}))
                    .isGreaterThan(0);
        }
    }

    private static RawFrame frame(long sequence, int width, int height, byte[] pixels) {
        FrameMetadata metadata = new FrameMetadata(
                1,
                sequence,
                System.nanoTime(),
                PixelFormat.BGRA,
                Collections.singletonList(new Rect(0, 0, width, height)));
        return new RawFrame(width, height, width * 4, ByteBuffer.wrap(pixels), metadata);
    }

    private static EncodedFrame encoded(CodecDescriptor codec, long sequence, long baseSequence, boolean keyFrame) {
        return new EncodedFrame(codec, sequence, baseSequence, keyFrame, 1, 1, ByteBuffer.wrap(new byte[] {1}));
    }

    private static int indexOf(byte[] haystack, byte[] needle) {
        outer:
        for (int i = 0; i <= haystack.length - needle.length; i++) {
            for (int j = 0; j < needle.length; j++) if (haystack[i + j] != needle[j]) continue outer;
            return i;
        }
        return -1;
    }

    private static final class TestFrameTransport implements FrameTransport {
        @Nullable
        private RawFrameConsumer consumer;

        @Override
        public void onFrame(@Nullable FrameConsumer ignored) {}

        @Override
        public void onRawFrame(@Nullable RawFrameConsumer next) {
            consumer = next;
        }

        @Override
        public void close() {
            consumer = null;
        }

        void emit(RawFrame frame) {
            RawFrameConsumer current = consumer;
            if (current != null) current.accept(frame);
        }
    }
}
