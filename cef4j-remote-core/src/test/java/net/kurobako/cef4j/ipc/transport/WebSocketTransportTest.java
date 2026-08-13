package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.junit.jupiter.api.Test;

final class WebSocketTransportTest extends CefTransportContractTest {
    @Override
    protected Pair newPair() throws Exception {
        try (ServerSocket server = new ServerSocket(0)) {
            CompletableFuture<RawWebSocketPeer> accepted = CompletableFuture.supplyAsync(() -> {
                try {
                    return RawWebSocketPeer.accept(server.accept());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            WebSocketTransport client =
                    WebSocketTransport.connect("ws://127.0.0.1:" + server.getLocalPort() + "/cef4j");
            return new Pair(client, accepted.get(10, TimeUnit.SECONDS));
        }
    }

    @Test
    void providerIsDiscoverable() {
        assertThat(CefTransports.available()).contains("websocket");
    }

    /** Minimal RFC 6455 server peer used to run the shared transport contract without another dependency. */
    private static final class RawWebSocketPeer implements CefTransport {
        private static final int MAX_FRAME_SIZE = 64 * 1024 * 1024;
        private static final String ACCEPT_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

        private final Socket socket;
        private final DataInputStream input;
        private final OutputStream output;
        private final Object sendLock = new Object();
        private final Object receiveLock = new Object();
        private final ArrayDeque<byte[]> pending = new ArrayDeque<>();
        private final AtomicBoolean disconnectNotified = new AtomicBoolean();

        @Nullable
        private volatile Consumer<ByteBuffer> receiveHandler;

        @Nullable
        private volatile Runnable disconnectHandler;

        private volatile boolean closed;
        private volatile boolean disconnected;

        private RawWebSocketPeer(Socket socket, InputStream input, OutputStream output) {
            this.socket = socket;
            this.input = new DataInputStream(input);
            this.output = output;
            Thread reader = new Thread(this::readLoop, "raw-websocket-test-peer");
            reader.setDaemon(true);
            reader.start();
        }

        static RawWebSocketPeer accept(Socket socket) throws IOException {
            BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
            String request = readHeaders(input);
            String[] lines = request.lines().toArray(String[]::new);
            if (lines.length == 0 || !"GET /cef4j HTTP/1.1".equals(lines[0])) {
                throw new IOException("unexpected WebSocket request line");
            }
            Map<String, String> headers = new HashMap<>();
            for (int i = 1; i < lines.length; i++) {
                int colon = lines[i].indexOf(':');
                if (colon > 0) {
                    headers.put(
                            lines[i].substring(0, colon).trim().toLowerCase(Locale.ROOT),
                            lines[i].substring(colon + 1).trim());
                }
            }
            String key = headers.get("sec-websocket-key");
            if (key == null) throw new IOException("missing Sec-WebSocket-Key");
            String accept;
            try {
                accept = Base64.getEncoder()
                        .encodeToString(MessageDigest.getInstance("SHA-1")
                                .digest((key + ACCEPT_GUID).getBytes(StandardCharsets.US_ASCII)));
            } catch (NoSuchAlgorithmException e) {
                throw new IOException(e);
            }
            OutputStream output = socket.getOutputStream();
            output.write(("HTTP/1.1 101 Switching Protocols\r\n"
                            + "Upgrade: websocket\r\n"
                            + "Connection: Upgrade\r\n"
                            + "Sec-WebSocket-Accept: "
                            + accept
                            + "\r\n\r\n")
                    .getBytes(StandardCharsets.US_ASCII));
            output.flush();
            return new RawWebSocketPeer(socket, input, output);
        }

        @Override
        public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
            if (closed) throw new CefTransportException("send on closed test peer");
            if (disconnected) throw new CefTransportException("client disconnected");
            byte[] copy = new byte[frame.remaining()];
            frame.get(copy);
            if (copy.length > MAX_FRAME_SIZE) throw new CefTransportException("frame is too large");
            synchronized (sendLock) {
                try {
                    writeFrame(0x2, copy);
                } catch (IOException e) {
                    markDisconnected();
                    throw new CefTransportException("send failed", e);
                }
            }
        }

        @Override
        public void onReceive(@Nonnull Consumer<ByteBuffer> handler) {
            synchronized (receiveLock) {
                receiveHandler = handler;
                byte[] frame;
                while ((frame = pending.poll()) != null) handler.accept(ByteBuffer.wrap(frame));
            }
        }

        @Override
        public void onDisconnect(@Nonnull Runnable handler) {
            disconnectHandler = handler;
            fireDisconnectIfReady();
        }

        @Override
        public boolean isConnected() {
            return !closed && !disconnected;
        }

        @Override
        public void close() {
            if (closed) return;
            closed = true;
            synchronized (sendLock) {
                try {
                    writeFrame(0x8, new byte[] {0x03, (byte) 0xE8});
                } catch (IOException ignored) {
                    // Socket close below is the fallback close signal.
                }
            }
            try {
                socket.close();
            } catch (IOException ignored) {
                // Idempotent best-effort test cleanup.
            }
        }

        private void readLoop() {
            ByteArrayOutputStream message = new ByteArrayOutputStream();
            boolean fragmented = false;
            try {
                while (!closed) {
                    int first = input.readUnsignedByte();
                    int second = input.readUnsignedByte();
                    boolean fin = (first & 0x80) != 0;
                    int opcode = first & 0x0F;
                    if ((second & 0x80) == 0) throw new IOException("client frame is not masked");
                    long length = second & 0x7F;
                    if (length == 126) length = input.readUnsignedShort();
                    else if (length == 127) length = input.readLong();
                    if (length < 0 || length > MAX_FRAME_SIZE) throw new IOException("invalid frame size");
                    byte[] mask = new byte[4];
                    input.readFully(mask);
                    byte[] payload = new byte[(int) length];
                    input.readFully(payload);
                    for (int i = 0; i < payload.length; i++) payload[i] ^= mask[i & 3];
                    if (opcode == 0x8) {
                        synchronized (sendLock) {
                            writeFrame(0x8, payload);
                        }
                        break;
                    }
                    if (opcode == 0x9) {
                        synchronized (sendLock) {
                            writeFrame(0xA, payload);
                        }
                        continue;
                    }
                    if (opcode == 0xA) continue;
                    if (opcode == 0x2) {
                        if (fragmented) throw new IOException("nested fragmented message");
                        message.reset();
                    } else if (opcode != 0x0 || !fragmented) {
                        throw new IOException("unexpected opcode " + opcode);
                    }
                    if (message.size() + payload.length > MAX_FRAME_SIZE) throw new IOException("message is too large");
                    message.write(payload);
                    fragmented = !fin;
                    if (fin) accept(message.toByteArray());
                }
            } catch (EOFException ignored) {
                // Normal peer socket shutdown.
            } catch (IOException ignored) {
                // Disconnect is the externally observable result.
            } finally {
                markDisconnected();
                try {
                    socket.close();
                } catch (IOException ignored) {
                    // Already disconnected.
                }
            }
        }

        private void writeFrame(int opcode, byte[] payload) throws IOException {
            output.write(0x80 | opcode);
            if (payload.length < 126) {
                output.write(payload.length);
            } else if (payload.length <= 0xFFFF) {
                output.write(126);
                output.write(payload.length >>> 8);
                output.write(payload.length);
            } else {
                output.write(127);
                for (int shift = 56; shift >= 0; shift -= 8) output.write((int) ((long) payload.length >>> shift));
            }
            output.write(payload);
            output.flush();
        }

        private void accept(byte[] frame) {
            synchronized (receiveLock) {
                Consumer<ByteBuffer> handler = receiveHandler;
                if (handler == null) pending.add(frame);
                else handler.accept(ByteBuffer.wrap(frame));
            }
        }

        private void markDisconnected() {
            if (closed || disconnected) return;
            disconnected = true;
            fireDisconnectIfReady();
        }

        private void fireDisconnectIfReady() {
            Runnable handler = disconnectHandler;
            if (disconnected && !closed && handler != null && disconnectNotified.compareAndSet(false, true)) {
                handler.run();
            }
        }

        private static String readHeaders(InputStream input) throws IOException {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            int matched = 0;
            while (bytes.size() < 16 * 1024) {
                int value = input.read();
                if (value < 0) throw new EOFException("EOF during WebSocket handshake");
                bytes.write(value);
                int expected = "\r\n\r\n".charAt(matched);
                matched = value == expected ? matched + 1 : (value == '\r' ? 1 : 0);
                if (matched == 4) return bytes.toString(StandardCharsets.US_ASCII.name());
            }
            throw new IOException("WebSocket handshake is too large");
        }
    }
}
