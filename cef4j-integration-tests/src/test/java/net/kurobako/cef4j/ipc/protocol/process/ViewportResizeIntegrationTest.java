package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.OsrPaintEvent;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeRequest;
import net.kurobako.cef4j.ipc.protocol.gen.SetViewportSizeResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Validates the {@code SetViewportSizeRequest} → server {@code was_resized} → CEF repaint round-trip. Exercises the
 * open-issue #3 resize handshake: the server's render handler now reads per-browser viewport sizes from a map updated
 * by this request, and CEF emits a fresh paint at the new dimensions.
 *
 * <p>The mapped frame files use the same Java 11 API on every supported platform.
 */
@Timeout(60)
class ViewportResizeIntegrationTest {

    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        RuntimeServerTestEnvironment environment = RuntimeServerTestEnvironment.require();
        serverBinary = environment.binary();
        cefResources = environment.resources();
    }

    private static RuntimeServerProcess spawnServerWithEnv() throws IOException {
        return RuntimeServerProcess.spawn(
                serverBinary,
                "zmq",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofSeconds(30),
                net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend.runtimeEnvironment(cefResources));
    }

    @Test
    void setViewportSizeTriggersRepaintAtNewDimensions() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(15))) {

            // Subscribe to OsrPaintEvent first so the bootstrap paint isn't dropped.
            LinkedBlockingQueue<OsrPaintEvent> paints = new LinkedBlockingQueue<>();
            session.on(OsrPaintEvent.MESSAGE_ID, OsrPaintEvent.DECODER, paints::offer);

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });
            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);

            // Drain the bootstrap paint (default 800x600). It might or might not arrive before our resize —
            // either way, we want to compare against a paint that lands AFTER the resize ack.
            OsrPaintEvent bootstrap = paints.poll(15, TimeUnit.SECONDS);
            assertThat(bootstrap).isNotNull();
            assertThat(bootstrap.width()).isEqualTo(800);
            assertThat(bootstrap.height()).isEqualTo(600);

            // Resize: tell the server the viewport is now 1024x768. The server updates its render handler's
            // view rect and posts was_resized to the CEF UI thread; CEF eventually calls back into the
            // render handler to repaint at the new dims.
            session.request(new SetViewportSizeRequest(browserHandle, 1024, 768), SetViewportSizeResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);

            // Poll for a paint at the new size. Drop bootstrap-leftover frames (any 800x600 paint queued
            // after our request but before CEF re-rendered).
            OsrPaintEvent resized = null;
            long deadline = System.nanoTime() + Duration.ofSeconds(15).toNanos();
            while (System.nanoTime() < deadline) {
                OsrPaintEvent p = paints.poll(2, TimeUnit.SECONDS);
                if (p == null) continue;
                if (p.width() == 1024 && p.height() == 768) {
                    resized = p;
                    break;
                }
            }
            if (resized == null) throw new AssertionError("no paint at new viewport size within deadline");
            assertThat(resized.width()).isEqualTo(1024);
            assertThat(resized.height()).isEqualTo(768);
            assertThat(resized.byteCount()).isEqualTo(1024 * 768 * 4);
        }
    }
}
