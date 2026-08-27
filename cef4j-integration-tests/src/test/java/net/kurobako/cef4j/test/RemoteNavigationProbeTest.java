package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
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
                RemoteNavigationProbe navigation = new RemoteNavigationProbe(session, () -> new RemoteHandle(1))) {
            CountDownLatch firstObserved = new CountDownLatch(1);
            AtomicReference<CountDownLatch> observed = new AtomicReference<>(firstObserved);
            CefSession.HandlerRegistration registration = session.on(
                    V8ContextCreatedEvent.MESSAGE_ID,
                    V8ContextCreatedEvent.DECODER,
                    event -> Objects.requireNonNull(observed.get(), "event barrier")
                            .countDown());
            try {
                CompletableFuture<Void> queued = new CompletableFuture<>();
                CompletableFuture<Void> loaded =
                        navigation.load("https://ready.test", Duration.ofSeconds(2), () -> queued);

                sendContext(pair.b, "https://other.test");
                assertThat(firstObserved.await(2, TimeUnit.SECONDS)).isTrue();
                assertThat(loaded).isNotDone();
                queued.complete(null);
                assertThat(loaded).isNotDone();
                CountDownLatch secondObserved = new CountDownLatch(1);
                observed.set(secondObserved);
                sendContext(pair.b, new RemoteHandle(2), "https://ready.test");
                assertThat(secondObserved.await(2, TimeUnit.SECONDS)).isTrue();
                assertThat(loaded).isNotDone();
                sendContext(pair.b, "https://ready.test");

                assertThat(loaded.get(2, TimeUnit.SECONDS)).isNull();
            } finally {
                registration.close();
            }
        } finally {
            pair.b.close();
        }
    }

    @Test
    void remembersMatchingRendererContextObservedBeforeQueueAcknowledgement() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                RemoteNavigationProbe navigation = new RemoteNavigationProbe(session, () -> new RemoteHandle(1))) {
            CountDownLatch observed = new CountDownLatch(1);
            CefSession.HandlerRegistration registration = session.on(
                    V8ContextCreatedEvent.MESSAGE_ID, V8ContextCreatedEvent.DECODER, event -> observed.countDown());
            try {
                CompletableFuture<Void> queued = new CompletableFuture<>();
                CompletableFuture<Void> loaded =
                        navigation.load("https://ready.test", Duration.ofSeconds(2), () -> queued);

                sendContext(pair.b, "https://ready.test");
                assertThat(observed.await(2, TimeUnit.SECONDS)).isTrue();
                assertThat(loaded).isNotDone();

                queued.complete(null);

                assertThat(loaded.get(2, TimeUnit.SECONDS)).isNull();
            } finally {
                registration.close();
            }
        } finally {
            pair.b.close();
        }
    }

    @Test
    void closeCancelsQueuedNavigation() {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2))) {
            RemoteNavigationProbe navigation = new RemoteNavigationProbe(session, () -> new RemoteHandle(1));
            CompletableFuture<Void> queued = new CompletableFuture<>();
            CompletableFuture<Void> loaded =
                    navigation.load("https://pending.test", Duration.ofSeconds(2), () -> queued);

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
        try (TestExecutor caller = TestExecutor.single("navigation-queue-caller");
                CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                TestGate queueing = new TestGate()) {
            RemoteNavigationProbe navigation = new RemoteNavigationProbe(session, () -> new RemoteHandle(1));
            CompletableFuture<Void> queued = new CompletableFuture<>();
            AtomicReference<CompletableFuture<Void>> loaded = new AtomicReference<>();
            CompletableFuture<Void> invocation = CompletableFuture.runAsync(
                    () -> loaded.set(navigation.load("https://pending.test", Duration.ofSeconds(2), () -> {
                        queueing.enter();
                        return queued;
                    })),
                    caller);
            TestDeadline deadline = TestDeadline.after(Duration.ofSeconds(2));
            queueing.awaitEntered(deadline, "navigation queue entry");

            navigation.close();
            queueing.release();

            deadline.await(invocation, "navigation queue completion");
            assertThat(Objects.requireNonNull(loaded.get(), "navigation result"))
                    .isCancelled();
            assertThat(queued).isCancelled();
        } finally {
            pair.b.close();
        }
    }

    private static void sendContext(LoopbackTransport peer, String url) throws Exception {
        sendContext(peer, new RemoteHandle(1), url);
    }

    private static void sendContext(LoopbackTransport peer, RemoteHandle browser, String url) throws Exception {
        V8ContextCreatedEvent event = new V8ContextCreatedEvent(browser, url);
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
