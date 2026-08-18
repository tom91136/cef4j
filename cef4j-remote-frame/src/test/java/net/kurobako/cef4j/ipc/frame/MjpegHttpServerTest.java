package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(15)
class MjpegHttpServerTest {

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
            String[] parts = line.split("\\s+");
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
            FrameMetadata metadata = new FrameMetadata(
                    1, 1L, System.nanoTime(), PixelFormat.BGRA, Collections.singletonList(new Rect(0, 0, 1, 1)));
            Objects.requireNonNull(sink, "no raw-frame consumer installed")
                    .accept(new RawFrame(1, 1, 4, ByteBuffer.wrap(pixels), metadata));
        }
    }
}
