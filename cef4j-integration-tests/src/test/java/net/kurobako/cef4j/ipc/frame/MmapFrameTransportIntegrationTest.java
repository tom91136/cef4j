package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import net.kurobako.cef4j.ipc.protocol.gen.Browser;
import net.kurobako.cef4j.ipc.protocol.gen.Frame;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerSupervisor;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Validates the {@link SharedFileFrameTransport} end-to-end against a real server. Spawns a server, drives a
 * navigation, binds a frame transport to the browser, and checks that the consumer is invoked with a properly-sized
 * BGRA pixel buffer + populated dirty rect — i.e. the same wire validation as {@code OsrPaintIntegrationTest} but
 * routed through the public {@link FrameTransport} API instead of decoding {@code OsrPaintEvent} by hand.
 */
@Timeout(60)
class MmapFrameTransportIntegrationTest {

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
        return RemoteCefBrowserBackend.launchServer(serverBinary, cefResources, Duration.ofSeconds(30));
    }

    @Test
    void onFrameDeliversBgraPixelsAndDirtyRect() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            // Bind the frame transport eagerly — the server's first paint can fire before
            // LifeSpanHandlerOnAfterCreatedEvent reaches us, and a `bind(session, handle)` issued after
            // the handle resolves would miss it. `bindAll` registers immediately with no browser filter.
            CompletableFuture<int[]> firstFrame = new CompletableFuture<>();
            AtomicInteger frameCount = new AtomicInteger();

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });

            // Threading-contract guard (issue #7): FrameConsumer fires on the IPC IO thread, never the caller's
            // thread. Catches future regressions where someone helpfully adds a Platform.runLater inside
            // SharedFileFrameTransport — UI marshalling is the consumer's job, not the transport's, otherwise non-FX
            // consumers (Swing/headless tests/server-side renderers) silently pay an FX-pump dep.
            Thread mainThread = Thread.currentThread();
            java.util.concurrent.atomic.AtomicReference<String> consumerThreadName =
                    new java.util.concurrent.atomic.AtomicReference<>();

            try (FrameTransport ft = SharedFileFrameTransport.bindAll(session)) {
                ft.onFrame((width, height, pixels, meta) -> {
                    Thread t = Thread.currentThread();
                    consumerThreadName.compareAndSet(null, t.getName());
                    if (t == mainThread) {
                        throw new AssertionError("FrameConsumer fired on caller thread; transport must deliver "
                                + "on its own IO thread per FrameTransport javadoc");
                    }
                    frameCount.incrementAndGet();
                    if (firstFrame.isDone()) return;
                    // Capture the dimensions, byte count, and dirty rect of the very first paint we observe so the
                    // assertions outside this callback can run on a stable snapshot.
                    Rect dirty = meta.dirtyRects().get(0);
                    firstFrame.complete(new int[] {
                        width,
                        height,
                        pixels.remaining(),
                        dirty.x(),
                        dirty.y(),
                        dirty.width(),
                        dirty.height(),
                        meta.sequenceId()
                    });
                });

                RemoteHandle browserHandle = handleFuture.get(20, TimeUnit.SECONDS);
                Browser browser = new Browser(session, browserHandle);
                Frame frame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
                frame.loadUrl("data:text/html,<html><body style='background:red'>x</body></html>")
                        .get(5, TimeUnit.SECONDS);

                int[] f = firstFrame.get(15, TimeUnit.SECONDS);
                assertThat(f[0]).isEqualTo(800); // width
                assertThat(f[1]).isEqualTo(600); // height
                assertThat(f[2]).isEqualTo(800 * 600 * 4); // BGRA bytes
                assertThat(f[3]).isGreaterThanOrEqualTo(0); // dirty.x
                assertThat(f[4]).isGreaterThanOrEqualTo(0); // dirty.y
                assertThat(f[3] + f[5]).isLessThanOrEqualTo(f[0]); // dirty fits horizontally
                assertThat(f[4] + f[6]).isLessThanOrEqualTo(f[1]); // dirty fits vertically
                assertThat(f[7]).isEqualTo(1); // first frame -> sequenceId starts at 1
                assertThat(consumerThreadName.get())
                        .as("consumer must run on a transport-owned thread, not the test main")
                        .isNotNull()
                        .isNotEqualTo(mainThread.getName());
            }
            // After close the consumer is detached. Subsequent paints must not trigger the (already-cleared)
            // consumer; we just confirm the count we captured is from inside the try-with-resources scope.
            assertThat(frameCount.get()).isGreaterThanOrEqualTo(1);
        }
    }

    @Test
    void onFrameOnlyDeliversForBoundBrowser() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });
            RemoteHandle realBrowser = handleFuture.get(30, TimeUnit.SECONDS);

            // Bind to a NON-existent browser handle. The transport subscribes globally to OsrPaintEvent but filters
            // by browser id internally; events for the real browser must not slip through.
            RemoteHandle wrongHandle = new RemoteHandle(realBrowser.id() + 999);
            CompletableFuture<ByteBuffer> spuriousFrame = new CompletableFuture<>();
            try (FrameTransport ft = SharedFileFrameTransport.bind(session, wrongHandle)) {
                ft.onFrame((w, h, pixels, meta) -> spuriousFrame.complete(pixels));

                // Drive a paint on the real browser to ensure events are flowing.
                Browser browser = new Browser(session, realBrowser);
                Frame frame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
                frame.loadUrl("data:text/html,<html><body style='background:blue'>y</body></html>")
                        .get(5, TimeUnit.SECONDS);

                // Wait long enough to see at least one paint on the real browser; the wrong-handle transport must
                // stay silent.
                try {
                    spuriousFrame.get(8, TimeUnit.SECONDS);
                    throw new AssertionError("frame leaked across browser handles");
                } catch (java.util.concurrent.TimeoutException expected) {
                    // expected: no callback for the wrong handle
                }
            }
        }
    }

    @Test
    void jvmAndFrameConsumerRecoverAcrossRealServerCrash() throws Exception {
        Map<String, String> environment = RemoteCefBrowserBackend.runtimeEnvironment(cefResources);
        RuntimeServerSupervisor.Configuration configuration = new RuntimeServerSupervisor.Configuration(
                serverBinary,
                "zmq",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofSeconds(30),
                Duration.ofSeconds(10),
                Duration.ofMillis(50),
                Duration.ofSeconds(1),
                3,
                environment);
        LinkedBlockingQueue<RuntimeServerSupervisor.Connection> generations = new LinkedBlockingQueue<>();
        try (RuntimeServerSupervisor supervisor = new RuntimeServerSupervisor(configuration);
                AutoCloseable registration = supervisor.onConnection(generations::offer)) {
            assertThat(registration).isNotNull();
            RuntimeServerSupervisor.Connection first = supervisor.start().get(30, TimeUnit.SECONDS);
            assertThat(generations.poll(10, TimeUnit.SECONDS)).isSameAs(first);
            try (FrameTransport firstFrames = SharedFileFrameTransport.bindAll(first.session())) {
                awaitFrame(firstFrames);
                supervisor.restart();
                RuntimeServerSupervisor.Connection second = generations.poll(30, TimeUnit.SECONDS);
                assertThat(second).isNotNull();
                assertThat(second.generation()).isEqualTo(first.generation() + 1);
                assertThat(second.pid()).isNotEqualTo(first.pid());
                try (FrameTransport secondFrames = SharedFileFrameTransport.bindAll(second.session())) {
                    awaitFrame(secondFrames);
                }
            }
        }
    }

    private static void awaitFrame(FrameTransport frames) throws Exception {
        CompletableFuture<Long> arrived = new CompletableFuture<>();
        frames.onRawFrame(frame -> arrived.complete(frame.metadata().sourceSequence()));
        assertThat(arrived.get(20, TimeUnit.SECONDS)).isPositive();
    }
}
