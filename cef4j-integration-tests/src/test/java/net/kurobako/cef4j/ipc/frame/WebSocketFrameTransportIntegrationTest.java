package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserSettings;
import net.kurobako.cef4j.ipc.protocol.gen.CreateBrowserRequest;
import net.kurobako.cef4j.ipc.protocol.gen.CreateBrowserResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

final class WebSocketFrameTransportIntegrationTest {
    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void locateServer() {
        String serverPath = System.getProperty("cef4j.runtime.server.binary", "");
        String resourcesPath = System.getProperty("cef4j.runtime.server.resources", "");
        Assumptions.assumeFalse(serverPath.isBlank(), "native server path is not configured");
        Assumptions.assumeFalse(resourcesPath.isBlank(), "CEF resources path is not configured");
        serverBinary = Path.of(serverPath);
        cefResources = Path.of(resourcesPath);
        Assumptions.assumeTrue(Files.isExecutable(serverBinary), "native server is not built");
        Assumptions.assumeTrue(Files.isDirectory(cefResources), "CEF resources are unavailable");
    }

    @Test
    void realRuntimeServerPublishesInlinePixelsOverWebSocket() throws Exception {
        try (RuntimeServerProcess server = spawnServer();
                CefTransport control = server.connect();
                CefSession session = new CefSessionImpl(control, Duration.ofSeconds(20));
                InlineFrameTransport frames = InlineFrameTransport.bindAll(session)) {
            CountDownLatch arrived = new CountDownLatch(1);
            AtomicInteger width = new AtomicInteger();
            AtomicInteger height = new AtomicInteger();
            AtomicInteger bytes = new AtomicInteger();
            frames.onFrame((frameWidth, frameHeight, pixels, metadata) -> {
                width.set(frameWidth);
                height.set(frameHeight);
                bytes.set(pixels.remaining());
                arrived.countDown();
            });

            session.request(
                            new CreateBrowserRequest(
                                    "about:blank", BrowserSettings.builder().build()),
                            CreateBrowserResponse.DECODER)
                    .get(20, TimeUnit.SECONDS);

            assertThat(arrived.await(20, TimeUnit.SECONDS)).isTrue();
            assertThat(width.get()).isPositive();
            assertThat(height.get()).isPositive();
            assertThat(bytes.get()).isEqualTo(width.get() * height.get() * 4);
            assertThat(server.transport()).isEqualTo("websocket");
            assertThat(server.frameTransport()).isEqualTo("inline");
        }
    }

    private static RuntimeServerProcess spawnServer() throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary,
                "websocket",
                "ws://127.0.0.1:0/cef4j",
                "inline",
                Duration.ofSeconds(30),
                RemoteCefBrowserBackend.runtimeEnvironment(cefResources));
    }
}
