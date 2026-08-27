package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.test.TestExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import org.newsclub.net.unix.AFUNIXServerSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

@DisabledOnOs(value = OS.WINDOWS, disabledReason = "Unix-domain sockets are not a Windows transport")
final class UdsTransportTest extends CefTransportContractTest {
    @TempDir
    static Path tmpDir;

    @Override
    protected Pair newPair() throws Exception {
        Path path = tmpDir.resolve("uds-" + System.nanoTime() + ".sock");
        try (AFUNIXServerSocket server = AFUNIXServerSocket.newInstance();
                TestExecutor executor = TestExecutor.single("uds-test-accept")) {
            server.bind(AFUNIXSocketAddress.of(path));
            CompletableFuture<UdsTransport> accepted = CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            return UdsTransport.accepted(path, server.accept());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    },
                    executor);
            UdsTransport client = UdsTransport.connect(UdsTransport.endpoint(path));
            try {
                return new Pair(client, accepted.get(10, TimeUnit.SECONDS));
            } catch (Exception failure) {
                client.close();
                throw failure;
            }
        }
    }

    @Test
    void providerIsDiscoverable() {
        assertThat(CefTransports.available()).contains("uds");
    }
}
