package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

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
}
