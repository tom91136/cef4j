package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

final class ZmqTransportTest extends CefTransportContractTest {
    @Override
    protected Pair newPair() {
        ZmqTransport server = ZmqTransport.bind("tcp://127.0.0.1:*");
        ZmqTransport client = ZmqTransport.connect(server.endpoint());
        return new Pair(client, server);
    }

    @Test
    void providerIsDiscoverable() {
        assertThat(CefTransports.available()).contains("zmq");
    }

    @Test
    void recoversInitialHandshakeBeforeRuntimeSessionReadinessExpires() {
        try (ZContext context = new ZContext();
                ZMQ.Socket socket = context.createSocket(SocketType.DEALER)) {
            ZmqTransport.configureLiveness(socket);

            assertThat(socket.getHeartbeatIvl()).isEqualTo(1_000);
            assertThat(socket.getHeartbeatTimeout()).isEqualTo(360_000);
            assertThat(socket.getHandshakeIvl())
                    .as("an unestablished pipe must recover before the five-minute SessionReady deadline")
                    .isLessThan(300_000);
        }
    }

    @Test
    void retainsFrameQueuedBeforePeerBinds() throws Exception {
        int port;
        try (ServerSocket reservation = new ServerSocket(0, 0, InetAddress.getLoopbackAddress())) {
            port = reservation.getLocalPort();
        }
        String endpoint = "tcp://127.0.0.1:" + port;
        try (ZmqTransport client = ZmqTransport.connect(endpoint)) {
            client.send(ByteBuffer.wrap("queued-before-bind".getBytes(StandardCharsets.UTF_8)));
            try (ZmqTransport server = ZmqTransport.bind(endpoint)) {
                CountDownLatch arrived = new CountDownLatch(1);
                server.onReceive(frame -> {
                    byte[] bytes = new byte[frame.remaining()];
                    frame.get(bytes);
                    assertThat(new String(bytes, StandardCharsets.UTF_8)).isEqualTo("queued-before-bind");
                    arrived.countDown();
                });
                assertThat(arrived.await(30, TimeUnit.SECONDS)).isTrue();
            }
        }
    }

    @Test
    void repeatedRuntimeSessionLifecycleDoesNotLoseFirstFrame() throws Exception {
        for (int iteration = 0; iteration < 64; iteration++) {
            try (ZmqTransport server = ZmqTransport.bind("tcp://127.0.0.1:*");
                    ZmqTransport client = ZmqTransport.connect(server.endpoint())) {
                CountDownLatch arrived = new CountDownLatch(1);
                server.onReceive(frame -> arrived.countDown());
                client.send(ByteBuffer.wrap(new byte[] {(byte) iteration}));
                assertThat(arrived.await(15, TimeUnit.SECONDS))
                        .as("iteration %s", iteration)
                        .isTrue();
            }
        }
    }

    @Test
    void startsFreshJeroMqContextAfterQuiescence() {
        long firstGeneration;
        try (ZmqTransport server = ZmqTransport.bind("tcp://127.0.0.1:*");
                ZmqTransport client = ZmqTransport.connect(server.endpoint())) {
            assertThat(client.endpoint()).isEqualTo(server.endpoint());
            firstGeneration = ZmqTransport.sharedContextGeneration();
        }

        try (ZmqTransport server = ZmqTransport.bind("tcp://127.0.0.1:*");
                ZmqTransport client = ZmqTransport.connect(server.endpoint())) {
            assertThat(client.endpoint()).isEqualTo(server.endpoint());
            assertThat(ZmqTransport.sharedContextGeneration()).isGreaterThan(firstGeneration);
        }
    }

    @Test
    void multipleTransportsReuseJeroMqIoInfrastructure() {
        int threadsBefore = jeroMqInfrastructureThreads();
        List<ZmqTransport> transports = new ArrayList<>();
        try {
            for (int i = 0; i < 4; i++) {
                ZmqTransport server = ZmqTransport.bind("tcp://127.0.0.1:*");
                transports.add(server);
                transports.add(ZmqTransport.connect(server.endpoint()));
            }

            assertThat(jeroMqInfrastructureThreads() - threadsBefore)
                    .as("additional JeroMQ I/O and reaper threads")
                    .isLessThanOrEqualTo(2);
        } finally {
            for (int i = transports.size() - 1; i >= 0; i--) transports.get(i).close();
        }
    }

    private static int jeroMqInfrastructureThreads() {
        return (int) Thread.getAllStackTraces().keySet().stream()
                .filter(Thread::isAlive)
                .map(Thread::getName)
                .filter(name -> name.startsWith("iothread-") || name.startsWith("reaper-"))
                .count();
    }

    @Test
    void reconnectsWhenPeerGreetingNeverCompletes() throws Exception {
        byte[] zmtpGreetingPrefix = {(byte) 0xff, 0, 0, 0, 0, 0, 0, 0, 1, 0x7f};
        CountDownLatch firstConnection = new CountDownLatch(1);
        CountDownLatch reconnected = new CountDownLatch(1);
        CountDownLatch disconnected = new CountDownLatch(1);
        try (ServerSocket server = new ServerSocket(0, 2, InetAddress.getLoopbackAddress())) {
            Thread peer = new Thread(
                    () -> {
                        try (Socket first = server.accept()) {
                            firstConnection.countDown();
                            first.getOutputStream().write(zmtpGreetingPrefix);
                            first.getOutputStream().flush();
                            try (Socket second = server.accept()) {
                                if (second.isConnected()) reconnected.countDown();
                            }
                        } catch (Exception ignored) {
                            // Closing the test server is the expected way to release a pending accept.
                        }
                    },
                    "stalled-zmtp-peer");
            peer.setDaemon(true);
            peer.start();
            try (ZmqTransport transport = ZmqTransport.connect("tcp://127.0.0.1:" + server.getLocalPort(), 1_000)) {
                transport.onDisconnect(disconnected::countDown);
                transport.send(ByteBuffer.wrap(new byte[] {1}));
                assertThat(firstConnection.await(2, TimeUnit.SECONDS)).isTrue();
                assertThat(reconnected.await(500, TimeUnit.MILLISECONDS))
                        .as("the configured greeting window should be honoured")
                        .isFalse();
                assertThat(reconnected.await(3, TimeUnit.SECONDS))
                        .as("a permanently stalled greeting should eventually reconnect")
                        .isTrue();
                assertThat(disconnected.await(250, TimeUnit.MILLISECONDS))
                        .as("an incomplete initial greeting is recoverable and must not disconnect the session")
                        .isFalse();
            }
        }
    }
}
