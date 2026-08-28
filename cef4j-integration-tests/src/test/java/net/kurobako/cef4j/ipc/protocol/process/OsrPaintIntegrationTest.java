package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.OsrPaintEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import net.kurobako.cef4j.test.TestDeadline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class OsrPaintIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void osrPaintEventDeliversNonEmptyBitmap() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            LinkedBlockingQueue<OsrPaintEvent> events = new LinkedBlockingQueue<>();
            session.on(OsrPaintEvent.MESSAGE_ID, OsrPaintEvent.DECODER, events::offer);

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });
            RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
            Browser browser = new Browser(session, browserHandle);
            Frame frame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
            frame.loadUrl("data:text/html,<html><body style='background:red'>x</body></html>")
                    .get(5, TimeUnit.SECONDS);

            OsrPaintEvent ev = events.poll(15, TimeUnit.SECONDS);
            assertThat(ev).isNotNull();
            Path sharedPath = Paths.get(ev.shmName());
            assertThat(sharedPath.getFileName().toString())
                    .startsWith("cef4j-paint-")
                    .endsWith(".frame");
            assertThat(ev.frameSequence()).isPositive().isEven();
            assertThat(ev.width()).isEqualTo(800);
            assertThat(ev.height()).isEqualTo(600);
            assertThat(ev.byteCount()).isEqualTo(800 * 600 * 4);
            assertThat(ev.dirtyX()).isGreaterThanOrEqualTo(0);
            assertThat(ev.dirtyY()).isGreaterThanOrEqualTo(0);
            assertThat(ev.dirtyX() + ev.dirtyWidth()).isLessThanOrEqualTo(ev.width());
            assertThat(ev.dirtyY() + ev.dirtyHeight()).isLessThanOrEqualTo(ev.height());

            TestDeadline.after(Duration.ofSeconds(2))
                    .until(
                            () -> {
                                String diagnostics = server.diagnosticSummary();
                                return diagnostics.contains("reached on-paint")
                                        && diagnostics.contains("reached buffer-ready")
                                        && diagnostics.contains("reached event-sent");
                            },
                            Duration.ofMillis(10),
                            "runtime-server paint diagnostics");

            assertThat(Files.exists(sharedPath))
                    .as("shared frame file %s exists", sharedPath)
                    .isTrue();

            try (RandomAccessFile raf = new RandomAccessFile(sharedPath.toFile(), "r");
                    FileChannel ch = raf.getChannel()) {
                assertThat(ch.size()).isGreaterThanOrEqualTo((long) ev.byteCount() + Long.BYTES);
                ByteBuffer mapped = ch.map(FileChannel.MapMode.READ_ONLY, 0, (long) ev.byteCount() + Long.BYTES)
                        .order(ByteOrder.nativeOrder());
                assertThat(mapped.capacity()).isEqualTo(ev.byteCount() + Long.BYTES);
                mapped.getLong(0);
                mapped.get(Long.BYTES + ev.byteCount() - 1);
            }
        }
    }
}
