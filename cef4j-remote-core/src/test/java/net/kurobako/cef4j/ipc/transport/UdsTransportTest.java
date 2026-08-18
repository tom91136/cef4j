package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.newsclub.net.unix.AFUNIXServerSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

final class UdsTransportTest extends CefTransportContractTest {
    @TempDir
    static Path tmpDir;

    @Override
    protected Pair newPair() throws Exception {
        Path path = tmpDir.resolve("uds-" + System.nanoTime() + ".sock");
        AFUNIXServerSocket server = AFUNIXServerSocket.newInstance();
        server.bind(AFUNIXSocketAddress.of(path));
        CompletableFuture<UdsTransport> accepted = CompletableFuture.supplyAsync(() -> {
            try {
                return UdsTransport.accepted(path, server.accept());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        UdsTransport client = UdsTransport.connect(UdsTransport.endpoint(path));
        UdsTransport peer = accepted.get(10, TimeUnit.SECONDS);
        server.close();
        return new Pair(client, peer);
    }

    @Test
    void providerIsDiscoverable() {
        assertThat(CefTransports.available()).contains("uds");
    }
}
