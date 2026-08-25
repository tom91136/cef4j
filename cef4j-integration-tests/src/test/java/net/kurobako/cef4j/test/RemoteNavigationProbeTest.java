package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import net.kurobako.cef4j.ipc.protocol.gen.V8ContextCreatedEvent;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.transport.LoopbackTransport;
import org.junit.jupiter.api.Test;

class RemoteNavigationProbeTest {
    @Test
    void waitsForBothQueueAcknowledgementAndMatchingRendererContext() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                RemoteNavigationProbe navigation = new RemoteNavigationProbe(session)) {
            CompletableFuture<Void> queued = new CompletableFuture<>();
            CompletableFuture<Void> loaded = navigation.load("https://ready.test", () -> queued);

            sendContext(pair.b, "https://ready.test");
            assertThat(loaded).isNotDone();
            queued.complete(null);

            assertThat(loaded.get(2, TimeUnit.SECONDS)).isNull();
        } finally {
            pair.b.close();
        }
    }

    @Test
    void closeCancelsQueuedNavigation() {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2))) {
            RemoteNavigationProbe navigation = new RemoteNavigationProbe(session);
            CompletableFuture<Void> queued = new CompletableFuture<>();
            CompletableFuture<Void> loaded = navigation.load("https://pending.test", () -> queued);

            navigation.close();

            assertThat(loaded).isCancelled();
            assertThat(queued).isCancelled();
        } finally {
            pair.b.close();
        }
    }

    @Test
    void closeWhileNavigationIsBeingQueuedCancelsTheReturnedStage() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        ExecutorService caller = Executors.newSingleThreadExecutor();
        try (CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2))) {
            RemoteNavigationProbe navigation = new RemoteNavigationProbe(session);
            CompletableFuture<Void> queued = new CompletableFuture<>();
            AtomicReference<CompletableFuture<Void>> loaded = new AtomicReference<>();
            CountDownLatch entered = new CountDownLatch(1);
            CountDownLatch release = new CountDownLatch(1);
            Future<?> invocation = caller.submit(() -> loaded.set(navigation.load("https://pending.test", () -> {
                entered.countDown();
                try {
                    if (!release.await(2, TimeUnit.SECONDS)) throw new IllegalStateException("not released");
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException(interrupted);
                }
                return queued;
            })));
            assertThat(entered.await(2, TimeUnit.SECONDS)).isTrue();

            navigation.close();
            release.countDown();

            invocation.get(2, TimeUnit.SECONDS);
            assertThat(Objects.requireNonNull(loaded.get(), "navigation result"))
                    .isCancelled();
            assertThat(queued).isCancelled();
        } finally {
            caller.shutdownNow();
            pair.b.close();
        }
    }

    private static void sendContext(LoopbackTransport peer, String url) throws Exception {
        V8ContextCreatedEvent event = new V8ContextCreatedEvent(new RemoteHandle(1), url);
        ByteBuffer frame =
                ByteBuffer.allocate(Envelope.HEADER_SIZE + event.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(
                frame,
                Envelope.Kind.EVENT,
                0,
                Envelope.NO_CORR_ID,
                V8ContextCreatedEvent.MESSAGE_ID,
                event.encodedSize());
        event.encodeInto(frame);
        frame.flip();
        peer.send(frame);
    }
}
