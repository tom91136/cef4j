package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
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
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
final class WebSocketFrameTransportIntegrationTest {
    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void realRuntimeServerPublishesInlinePixelsOverWebSocket() throws Exception {
        try (RuntimeServerProcess server = spawnServer();
                CefTransport control = server.connect();
                CefSession session = new CefSessionImpl(control, Duration.ofSeconds(30));
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
        return RUNTIME.spawn("websocket", "ws://127.0.0.1:0/cef4j", "inline", Duration.ofSeconds(30));
    }
}
