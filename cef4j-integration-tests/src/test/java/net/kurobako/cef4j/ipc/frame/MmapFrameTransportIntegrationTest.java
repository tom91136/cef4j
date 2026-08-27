package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
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
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class MmapFrameTransportIntegrationTest {

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void onFrameDeliversBgraPixelsAndDirtyRect() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            CompletableFuture<int[]> firstFrame = new CompletableFuture<>();
            AtomicInteger frameCount = new AtomicInteger();

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });

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
                assertThat(f[0]).as("width").isEqualTo(800);
                assertThat(f[1]).as("height").isEqualTo(600);
                assertThat(f[2]).as("BGRA bytes").isEqualTo(800 * 600 * 4);
                assertThat(f[3]).as("dirty x").isGreaterThanOrEqualTo(0);
                assertThat(f[4]).as("dirty y").isGreaterThanOrEqualTo(0);
                assertThat(f[3] + f[5]).as("dirty rect right edge").isLessThanOrEqualTo(f[0]);
                assertThat(f[4] + f[6]).as("dirty rect bottom edge").isLessThanOrEqualTo(f[1]);
                assertThat(f[7]).as("first frame sequence").isEqualTo(1);
                assertThat(consumerThreadName.get())
                        .as("consumer must run on a transport-owned thread, not the test main")
                        .isNotNull()
                        .isNotEqualTo(mainThread.getName());
            }
            assertThat(frameCount.get()).isGreaterThanOrEqualTo(1);
        }
    }

    @Test
    void onFrameOnlyDeliversForBoundBrowser() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            CompletableFuture<RemoteHandle> handleFuture = new CompletableFuture<>();
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, e -> {
                        if (!handleFuture.isDone()) handleFuture.complete(e.browser());
                    });
            RemoteHandle realBrowser = handleFuture.get(30, TimeUnit.SECONDS);

            RemoteHandle wrongHandle = new RemoteHandle(realBrowser.id() + 999);
            CompletableFuture<ByteBuffer> spuriousFrame = new CompletableFuture<>();
            try (FrameTransport ft = SharedFileFrameTransport.bind(session, wrongHandle)) {
                ft.onFrame((w, h, pixels, meta) -> spuriousFrame.complete(pixels));

                Browser browser = new Browser(session, realBrowser);
                Frame frame = browser.getMainFrame().get(5, TimeUnit.SECONDS);
                frame.loadUrl("data:text/html,<html><body style='background:blue'>y</body></html>")
                        .get(5, TimeUnit.SECONDS);

                assertThatThrownBy(() -> spuriousFrame.get(8, TimeUnit.SECONDS))
                        .isInstanceOf(java.util.concurrent.TimeoutException.class);
            }
        }
    }

    @Test
    void jvmAndFrameConsumerRecoverAcrossRealServerCrash() throws Exception {
        Map<String, String> environment = RUNTIME.processEnvironment();
        RuntimeServerSupervisor.Configuration configuration = new RuntimeServerSupervisor.Configuration(
                RUNTIME.binary(),
                "zmq",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofMinutes(3),
                Duration.ofSeconds(10),
                Duration.ofMillis(50),
                Duration.ofSeconds(1),
                3,
                environment);
        LinkedBlockingQueue<RuntimeServerSupervisor.Connection> generations = new LinkedBlockingQueue<>();
        try (RuntimeServerSupervisor supervisor = new RuntimeServerSupervisor(configuration);
                AutoCloseable registration = supervisor.onConnection(generations::offer)) {
            assertThat(registration).isNotNull();
            RuntimeServerSupervisor.Connection first = supervisor.start().get(3, TimeUnit.MINUTES);
            assertThat(generations.poll(10, TimeUnit.SECONDS)).isSameAs(first);
            try (FrameTransport firstFrames = SharedFileFrameTransport.bindAll(first.session())) {
                awaitFrame(firstFrames);
                supervisor.restart();
                RuntimeServerSupervisor.Connection second = generations.poll(3, TimeUnit.MINUTES);
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
        assertThat(arrived.get(60, TimeUnit.SECONDS)).isPositive();
    }
}
