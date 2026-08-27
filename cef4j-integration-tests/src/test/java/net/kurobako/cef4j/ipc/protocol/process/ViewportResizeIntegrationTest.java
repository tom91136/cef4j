package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

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
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class ViewportResizeIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void setViewportSizeTriggersRepaintAtNewDimensions() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            LinkedBlockingQueue<OsrPaintEvent> paints = new LinkedBlockingQueue<>();
            session.on(OsrPaintEvent.MESSAGE_ID, OsrPaintEvent.DECODER, paints::offer);

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });
            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);

            OsrPaintEvent bootstrap = paints.poll(15, TimeUnit.SECONDS);
            assertThat(bootstrap).isNotNull();
            assertThat(bootstrap.width()).isEqualTo(800);
            assertThat(bootstrap.height()).isEqualTo(600);

            session.request(new SetViewportSizeRequest(browserHandle, 1024, 768), SetViewportSizeResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);

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
