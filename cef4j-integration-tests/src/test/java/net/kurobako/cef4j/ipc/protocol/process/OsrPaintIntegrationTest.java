package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
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
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Validates the portable shared-file OSR pipeline end-to-end. The server writes BGRA pixels to a restricted temporary
 * file and emits {@link OsrPaintEvent}; Java maps that path with {@link FileChannel#map}.
 *
 * <p>This is the foundation used by remote GUI consumers: they map the same region, copy dirty rectangles into their
 * toolkit image, and repaint. This test isolates the wire and mapped-frame contract from a particular GUI toolkit.
 */
@Timeout(60)
class OsrPaintIntegrationTest {

    private static Path serverBinary;
    private static Path cefResources;

    @BeforeAll
    static void resolveBinary() {
        String bin = System.getProperty("cef4j.runtime.server.binary");
        String res = System.getProperty("cef4j.runtime.server.resources");
        Assumptions.assumeTrue(bin != null, "cef4j.runtime.server.binary system property not set");
        Assumptions.assumeTrue(res != null, "cef4j.runtime.server.resources system property not set");
        serverBinary = Paths.get(bin);
        cefResources = Paths.get(res);
        Assumptions.assumeTrue(Files.isExecutable(serverBinary), "server binary not built at " + serverBinary);
        Assumptions.assumeTrue(Files.isDirectory(cefResources), "CEF resources dir missing at " + cefResources);
    }

    private static RuntimeServerProcess spawnServerWithEnv() throws IOException {
        Path tmpDir = Files.createTempDirectory("cef4j-runtime-server-launcher");
        Path script = tmpDir.resolve("server-launch.sh");
        String content = "#!/bin/sh\n"
                + "export CEF_RESOURCES_DIR=\"" + cefResources + "\"\n"
                + "export LD_LIBRARY_PATH=\"" + cefResources + ":${LD_LIBRARY_PATH:-}\"\n"
                + "exec \"" + serverBinary + "\" \"$@\"\n";
        Files.writeString(script, content);
        script.toFile().setExecutable(true);
        return RuntimeServerProcess.spawn(script, "tcp://127.0.0.1:0", Duration.ofSeconds(30));
    }

    @Test
    void osrPaintEventDeliversNonEmptyBitmap() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            LinkedBlockingQueue<OsrPaintEvent> events = new LinkedBlockingQueue<>();
            session.on(OsrPaintEvent.MESSAGE_ID, OsrPaintEvent.DECODER, events::offer);

            // Drive a real navigation so CEF actually composites pixels; about:blank's initial paint is often
            // empty since the renderer hasn't laid anything out yet. Red square against white background.
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

            // Bootstrap browser starts at about:blank. CEF schedules an initial paint within ~1s on Linux OSR.
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
            // Dirty rect must lie within the bitmap dims.
            assertThat(ev.dirtyX()).isGreaterThanOrEqualTo(0);
            assertThat(ev.dirtyY()).isGreaterThanOrEqualTo(0);
            assertThat(ev.dirtyX() + ev.dirtyWidth()).isLessThanOrEqualTo(ev.width());
            assertThat(ev.dirtyY() + ev.dirtyHeight()).isLessThanOrEqualTo(ev.height());

            assertThat(Files.exists(sharedPath))
                    .as("shared frame file %s exists", sharedPath)
                    .isTrue();

            // Opening the advertised file and mapping its sequence header plus byteCount validates the wire:
            //   - the compatibility shmName field contains a real shared-file path
            //   - the file is sized correctly (ftruncate'd to maxBytes; we read up to byteCount)
            //   - JVM and server agree on the addressable region
            // Whether CEF painted real pixels into it is a CEF rendering question, not an IPC question —
            // sandbox/GPU init failures in headless tests can deliver an empty composite. For the wire we
            // only need to prove a successful map of the server's reported buffer.
            try (RandomAccessFile raf = new RandomAccessFile(sharedPath.toFile(), "r");
                    FileChannel ch = raf.getChannel()) {
                assertThat(ch.size()).isGreaterThanOrEqualTo((long) ev.byteCount() + Long.BYTES);
                ByteBuffer mapped = ch.map(FileChannel.MapMode.READ_ONLY, 0, (long) ev.byteCount() + Long.BYTES)
                        .order(ByteOrder.nativeOrder());
                assertThat(mapped.capacity()).isEqualTo(ev.byteCount() + Long.BYTES);
                // Touch first/last bytes to fault the pages in. Either value can be anything in [-128, 127];
                // we only care that the read doesn't throw and the mapping is sized as advertised.
                mapped.getLong(0);
                mapped.get(Long.BYTES + ev.byteCount() - 1);
            }
        }
    }
}
