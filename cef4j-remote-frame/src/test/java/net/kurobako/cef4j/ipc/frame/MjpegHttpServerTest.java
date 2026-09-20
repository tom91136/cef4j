package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import net.kurobako.cef4j.test.TestDeadline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(15)
class MjpegHttpServerTest {

    @Test
    void suppliedHttpExecutorRemainsCallerOwned() throws Exception {
        MjpegHttpServer.Configuration configuration = new MjpegHttpServer.Configuration(
                new InetSocketAddress(InetAddress.getLoopbackAddress(), 0),
                "/cef4j.mjpeg",
                16,
                0.80f,
                false,
                Optional.empty(),
                Optional.of("secret-token"));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            try (MjpegHttpServer server = MjpegHttpServer.start(configuration, executor)) {
                assertThat(statusCode(server.endpoint(), null)).isEqualTo(401);
            }
            assertThat(executor.isShutdown()).isFalse();
        } finally {
            executor.shutdownNow();
        }
    }

    @Test
    void sourceSwapFencesFramesAlreadyEncodingForThePreviousSource() throws Exception {
        CountDownLatch oldEncodeEntered = new CountDownLatch(1);
        CountDownLatch releaseOldEncode = new CountDownLatch(1);
        AtomicInteger codecs = new AtomicInteger();
        try (MjpegHttpServer server = new MjpegHttpServer(
                        MjpegHttpServer.Configuration.loopback(0),
                        () -> testCodec(codecs.getAndIncrement() == 0, oldEncodeEntered, releaseOldEncode));
                TestFrameSource oldSource = new TestFrameSource();
                TestFrameSource newSource = new TestFrameSource()) {
            try {
                server.attach(oldSource);
                oldSource.emit(1, new byte[] {0, 0, 0, (byte) 255});
                assertThat(oldEncodeEntered.await(2, TimeUnit.SECONDS)).isTrue();

                AtomicReference<Throwable> swapFailure = new AtomicReference<>();
                Thread swap = new Thread(
                        () -> {
                            try {
                                server.attach(newSource);
                            } catch (Throwable failure) {
                                swapFailure.set(failure);
                            }
                        },
                        "mjpeg-source-swap-test");
                swap.start();
                releaseOldEncode.countDown();
                TestDeadline.after(Duration.ofSeconds(5)).join(swap, "MJPEG source swap");
                assertThat(swapFailure.get()).isNull();

                newSource.emit(2, new byte[] {0, 0, 0, (byte) 255});
                try (Socket socket = new Socket(
                        server.endpoint().getHost(), server.endpoint().getPort())) {
                    socket.setSoTimeout(5_000);
                    OutputStream output = socket.getOutputStream();
                    output.write(("GET " + server.endpoint().getPath() + " HTTP/1.1\r\nHost: "
                                    + server.endpoint().getHost() + "\r\nConnection: close\r\n\r\n")
                            .getBytes(StandardCharsets.US_ASCII));
                    output.flush();
                    String response = new String(socket.getInputStream().readNBytes(512), StandardCharsets.ISO_8859_1);
                    assertThat(response).contains("X-Cef4j-Sequence: 2").doesNotContain("X-Cef4j-Sequence: 1");
                }
            } finally {
                releaseOldEncode.countDown();
            }
        }
    }

    private static FrameCodec testCodec(boolean block, CountDownLatch encodeEntered, CountDownLatch releaseEncode) {
        CodecDescriptor descriptor = new CodecDescriptor("test-jpeg", "image/jpeg", false);
        return new FrameCodec() {
            @Override
            public CodecDescriptor descriptor() {
                return descriptor;
            }

            @Override
            public EncodedFrame encode(RawFrame frame) throws IOException {
                if (block) {
                    encodeEntered.countDown();
                    try {
                        if (!releaseEncode.await(5, TimeUnit.SECONDS))
                            throw new IOException("encode release timed out");
                    } catch (InterruptedException interrupted) {
                        Thread.currentThread().interrupt();
                        throw new IOException(interrupted);
                    }
                }
                return new EncodedFrame(
                        descriptor,
                        frame.metadata().sourceSequence(),
                        EncodedFrame.NO_BASE_SEQUENCE,
                        true,
                        frame.width(),
                        frame.height(),
                        ByteBuffer.wrap(new byte[1024]));
            }
        };
    }

    @Test
    void bearerTokenIsRequiredAndAccepted() throws Exception {
        MjpegHttpServer.Configuration configuration = new MjpegHttpServer.Configuration(
                new InetSocketAddress(InetAddress.getLoopbackAddress(), 0),
                "/cef4j.mjpeg",
                16,
                0.80f,
                false,
                Optional.empty(),
                Optional.of("secret-token"));
        try (MjpegHttpServer server = MjpegHttpServer.start(configuration);
                TestFrameSource frames = new TestFrameSource()) {
            server.attach(frames);
            URI uri = server.endpoint();
            assertThat(statusCode(uri, null)).isEqualTo(401);
            frames.emit(new byte[] {0, 0, 0, (byte) 255});
            assertThat(statusCode(uri, "secret-token")).isEqualTo(200);
        }
    }

    @Test
    void idleSourceDoesNotEvictHealthyViewer() throws Exception {
        MjpegHttpServer.Configuration configuration =
                MjpegHttpServer.Configuration.loopback(0).withClientStallTimeout(Duration.ofMillis(100));
        try (MjpegHttpServer server = MjpegHttpServer.start(configuration);
                TestFrameSource frames = new TestFrameSource();
                Socket socket = new Socket(
                        server.endpoint().getHost(), server.endpoint().getPort())) {
            server.attach(frames);
            socket.setSoTimeout(10_000);
            OutputStream out = socket.getOutputStream();
            out.write(("GET " + server.endpoint().getPath() + " HTTP/1.1\r\nHost: "
                            + server.endpoint().getHost() + "\r\nConnection: close\r\n\r\n")
                    .getBytes(StandardCharsets.UTF_8));
            out.flush();
            frames.emit(1, new byte[] {0, 0, 0, (byte) 255});
            InputStream input = socket.getInputStream();
            assertThat(readAsciiLine(input)).contains("200");
            String header;
            do {
                header = readAsciiLine(input);
            } while (!header.isEmpty());
            assertMjpegPart(input, 1);

            // The first frame proves that the viewer is registered before it remains idle for longer than the
            // configured stall timeout. An idle viewer has no write in progress and must remain connected.
            assertThat(new java.util.concurrent.CountDownLatch(1).await(300, TimeUnit.MILLISECONDS))
                    .isFalse();
            frames.emit(2, new byte[] {0, 0, 0, (byte) 255});
            assertMjpegPart(input, 2);
        }
    }

    private static void assertMjpegPart(InputStream input, long expectedSequence) throws IOException {
        String line;
        do {
            line = readAsciiLine(input);
        } while (line.isEmpty());
        if (line.matches("[0-9a-fA-F]+")) line = readAsciiLine(input);
        assertThat(line).isEqualTo("--cef4j-frame");

        int contentLength = -1;
        String sequence = null;
        while (true) {
            line = readAsciiLine(input);
            if (line.isEmpty()) break;
            if (line.startsWith("Content-Length: ")) {
                contentLength = Integer.parseInt(line.substring("Content-Length: ".length()));
            } else if (line.startsWith("X-Cef4j-Sequence: ")) {
                sequence = line.substring("X-Cef4j-Sequence: ".length());
            }
        }
        assertThat(contentLength).isPositive();
        assertThat(sequence).isEqualTo(Long.toString(expectedSequence));
        assertThat(input.readNBytes(contentLength)).hasSize(contentLength);
        assertThat(input.read()).isEqualTo('\r');
        assertThat(input.read()).isEqualTo('\n');
    }

    private static String readAsciiLine(InputStream input) throws IOException {
        ByteArrayOutputStream line = new ByteArrayOutputStream();
        while (true) {
            int value = input.read();
            if (value < 0) throw new IOException("unexpected end of MJPEG response");
            if (value == '\n') break;
            if (value != '\r') line.write(value);
        }
        return line.toString(StandardCharsets.US_ASCII);
    }

    private static int statusCode(URI uri, @Nullable String token) throws IOException {
        try (Socket socket = new Socket(uri.getHost(), uri.getPort())) {
            OutputStream out = socket.getOutputStream();
            out.write(("GET " + uri.getPath() + " HTTP/1.1\r\nHost: " + uri.getHost() + "\r\n"
                            + (token == null ? "" : "Authorization: Bearer " + token + "\r\n")
                            + "Connection: close\r\n\r\n")
                    .getBytes(StandardCharsets.UTF_8));
            out.flush();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            String line = reader.readLine();
            if (line == null) throw new IOException("no HTTP response");
            String[] parts = line.split("\\s+", -1);
            return Integer.parseInt(parts[1]);
        }
    }

    private static final class TestFrameSource implements FrameTransport {
        @Nullable
        private RawFrameConsumer sink;

        @Override
        public void onFrame(@Nullable FrameConsumer consumer) {}

        @Override
        public void onRawFrame(@Nullable RawFrameConsumer consumer) {
            this.sink = consumer;
        }

        @Override
        public void close() {}

        void emit(byte[] pixels) {
            emit(1, pixels);
        }

        void emit(long sequence, byte[] pixels) {
            FrameMetadata metadata = new FrameMetadata(
                    1, sequence, System.nanoTime(), PixelFormat.BGRA, Collections.singletonList(new Rect(0, 0, 1, 1)));
            Objects.requireNonNull(sink, "no raw-frame consumer installed")
                    .accept(new RawFrame(1, 1, 4, ByteBuffer.wrap(pixels), metadata));
        }
    }
}
