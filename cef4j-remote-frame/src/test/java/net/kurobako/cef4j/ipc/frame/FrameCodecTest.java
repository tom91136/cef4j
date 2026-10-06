package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.TestExecutor;
import org.junit.jupiter.api.Test;

final class FrameCodecTest {
    @Test
    void pipelineCloseIsBoundedWhenCodecDoesNotReturn() throws Exception {
        CountDownLatch encodeEntered = new CountDownLatch(1);
        CountDownLatch releaseEncode = new CountDownLatch(1);
        CountDownLatch codecClosed = new CountDownLatch(1);
        FrameCodec codec = new FrameCodec() {
            @Override
            public CodecDescriptor descriptor() {
                return new CodecDescriptor("stuck", "application/x-stuck", false);
            }

            @Override
            public EncodedFrame encode(RawFrame frame) {
                encodeEntered.countDown();
                while (releaseEncode.getCount() != 0) {
                    try {
                        releaseEncode.await();
                    } catch (InterruptedException ignored) {
                        // XXX: Model a native codec that does not respond to interruption.
                    }
                }
                return encoded(descriptor(), frame.metadata().sourceSequence(), EncodedFrame.NO_BASE_SEQUENCE, true);
            }

            @Override
            public void close() {
                codecClosed.countDown();
            }
        };
        EncodedFramePipeline pipeline = new EncodedFramePipeline(codec, ignored -> {}, Duration.ofMillis(100), false);
        try {
            TestDeadline deadline = TestDeadline.after(Duration.ofSeconds(2));
            pipeline.submit(frame(1, 1, 1, new byte[] {0, 0, 0, (byte) 255}));
            deadline.await(encodeEntered, "codec encode entry");

            Thread closer = new Thread(pipeline::close, "bounded-frame-pipeline-close");
            closer.start();
            deadline.join(closer, "bounded pipeline close");
        } finally {
            releaseEncode.countDown();
        }
        TestDeadline.after(Duration.ofSeconds(2)).await(codecClosed, "codec close");
    }

    @Test
    void rawFramesRejectUndersizedAndOverflowingLayouts() {
        FrameMetadata metadata = new FrameMetadata(
                1, 1, System.nanoTime(), PixelFormat.BGRA, Collections.singletonList(new Rect(0, 0, 2, 2)));
        assertThatThrownBy(() -> new RawFrame(2, 2, 8, ByteBuffer.allocate(15), metadata))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("pixel buffer");
        assertThatThrownBy(() -> new RawFrame(Integer.MAX_VALUE, 1, 1, ByteBuffer.allocate(1), metadata))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("dimensions");
    }

    @Test
    void encodedFramesRejectDimensionsBeyondRawBufferLimit() {
        CodecDescriptor descriptor = new CodecDescriptor("test", "application/x-test", false);
        assertThatThrownBy(() -> new EncodedFrame(
                        descriptor, 1, EncodedFrame.NO_BASE_SEQUENCE, true, 32_768, 32_768, ByteBuffer.allocate(0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("dimensions");
    }

    @Test
    void pipelineSerializesKeyFrameRequestsAndCloseWithEncode() throws Exception {
        CountDownLatch encodeEntered = new CountDownLatch(1);
        CountDownLatch releaseEncode = new CountDownLatch(1);
        CountDownLatch keyFrameEntered = new CountDownLatch(1);
        CountDownLatch closeEntered = new CountDownLatch(1);
        AtomicBoolean encoding = new AtomicBoolean();
        AtomicBoolean overlap = new AtomicBoolean();
        FrameCodec codec = new FrameCodec() {
            @Override
            public CodecDescriptor descriptor() {
                return new CodecDescriptor("blocking", "application/x-blocking", true);
            }

            @Override
            public EncodedFrame encode(RawFrame frame) throws IOException {
                encoding.set(true);
                encodeEntered.countDown();
                try {
                    if (!releaseEncode.await(5, TimeUnit.SECONDS)) throw new IOException("encode release timed out");
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    throw new IOException(interrupted);
                } finally {
                    encoding.set(false);
                }
                return new EncodedFrame(
                        descriptor(),
                        frame.metadata().sourceSequence(),
                        EncodedFrame.NO_BASE_SEQUENCE,
                        true,
                        frame.width(),
                        frame.height(),
                        ByteBuffer.wrap(new byte[] {1}));
            }

            @Override
            public void requestKeyFrame() {
                if (encoding.get()) overlap.set(true);
                keyFrameEntered.countDown();
            }

            @Override
            public void close() {
                if (encoding.get()) overlap.set(true);
                closeEntered.countDown();
            }
        };
        EncodedFramePipeline pipeline = new EncodedFramePipeline(codec, ignored -> {});
        pipeline.submit(frame(1, 1, 1, new byte[] {0, 0, 0, (byte) 255}));
        assertThat(encodeEntered.await(2, TimeUnit.SECONDS)).isTrue();
        pipeline.requestKeyFrame();
        Thread closer = new Thread(pipeline::close, "frame-pipeline-close-test");
        closer.start();
        assertThat(keyFrameEntered.await(100, TimeUnit.MILLISECONDS)).isFalse();
        assertThat(closeEntered.await(100, TimeUnit.MILLISECONDS)).isFalse();
        releaseEncode.countDown();
        TestDeadline.after(Duration.ofSeconds(5)).join(closer, "serialized pipeline close");
        assertThat(keyFrameEntered.getCount()).isZero();
        assertThat(closeEntered.getCount()).isZero();
        assertThat(overlap).isFalse();
    }

    @Test
    void encodesIndependentlyDecodableJpeg() throws Exception {
        RawFrame source = frame(17L, 2, 1, new byte[] {0, 0, (byte) 255, (byte) 255, (byte) 255, 0, 0, (byte) 255});
        try (FrameCodec encoder = new JpegFrameCodec(0.95f)) {
            EncodedFrame encoded = encoder.encode(source);
            assertThat(encoded.codec().mediaType()).isEqualTo("image/jpeg");
            assertThat(encoded.sequence()).isEqualTo(17L);
            assertThat(encoded.keyFrame()).isTrue();
            assertThat(encoded.baseSequence()).isEqualTo(EncodedFrame.NO_BASE_SEQUENCE);

            ByteBuffer payload = encoded.payload();
            byte[] bytes = new byte[payload.remaining()];
            payload.get(bytes);
            java.awt.image.BufferedImage decoded = javax.imageio.ImageIO.read(new java.io.ByteArrayInputStream(bytes));
            assertThat(decoded.getWidth()).isEqualTo(2);
            assertThat(decoded.getHeight()).isEqualTo(1);
        }
    }

    @Test
    void servesChromeCompatibleMultipartMjpeg() throws Exception {
        TestFrameTransport frames = new TestFrameTransport();
        try (TestExecutor executor = TestExecutor.single("mjpeg-response-reader");
                MjpegHttpServer server = MjpegHttpServer.start(MjpegHttpServer.Configuration.loopback(0))) {
            server.attach(frames);
            CompletableFuture<byte[]> response = CompletableFuture.supplyAsync(
                    () -> {
                        HttpURLConnection connection = null;
                        try {
                            connection = (HttpURLConnection)
                                    server.endpoint().toURL().openConnection();
                            connection.setConnectTimeout(3000);
                            connection.setReadTimeout(5000);
                            assertThat(connection.getContentType()).startsWith("multipart/x-mixed-replace");
                            return connection.getInputStream().readNBytes(512);
                        } catch (Exception failure) {
                            throw new RuntimeException(failure);
                        } finally {
                            if (connection != null) connection.disconnect();
                        }
                    },
                    executor);
            frames.emit(frame(23L, 2, 1, new byte[] {0, 0, (byte) 255, (byte) 255, (byte) 255, 0, 0, (byte) 255}));
            byte[] multipart = TestDeadline.after(Duration.ofSeconds(8)).await(response, "MJPEG response");
            String header = new String(
                    multipart, 0, Math.min(multipart.length, 180), java.nio.charset.StandardCharsets.ISO_8859_1);
            assertThat(header).contains("Content-Type: image/jpeg").contains("X-Cef4j-Sequence: 23");
            assertThat(indexOf(multipart, new byte[] {(byte) 0xff, (byte) 0xd8}))
                    .isGreaterThan(0);
        } finally {
            frames.close();
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
