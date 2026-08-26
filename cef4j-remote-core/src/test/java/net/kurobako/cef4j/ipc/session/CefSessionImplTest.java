package net.kurobako.cef4j.ipc.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.ipc.transport.CefTransportException;
import net.kurobako.cef4j.ipc.transport.LoopbackTransport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(15)
class CefSessionImplTest {

    private static final int MSG_PING = 100;
    private static final int MSG_EVENT = 200;
    private static final int MSG_INTERCEPT = 300;

    private LoopbackTransport.Pair pair;
    private CefSessionImpl session;
    private TestPeer peer;

    @BeforeEach
    void setUp() {
        pair = LoopbackTransport.create();
        session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
        peer = new TestPeer(pair.b);
    }

    @AfterEach
    void tearDown() {
        session.close();
        peer.close();
    }

    @Test
    void runtimeServerSessionWaitsForAcknowledgedReadyBarrier() {
        AcknowledgingRuntimeTransport transport = new AcknowledgingRuntimeTransport();
        try (CefSessionImpl acknowledged = new CefSessionImpl(transport, Duration.ofSeconds(2))) {
            assertThat(acknowledged).isNotNull();
            ByteBuffer readyRequest = Objects.requireNonNull(transport.readyRequest);
            Envelope.Header header = Envelope.readHeader(readyRequest.duplicate());
            assertThat(header.kind).isEqualTo(Envelope.Kind.REQUEST);
            assertThat(header.messageId).isZero();
            assertThat(header.corrId).isZero();
        }
    }

    @Test
    void runtimeServerSessionRetransmitsDroppedReadyBarrier() {
        AcknowledgingRuntimeTransport transport = new AcknowledgingRuntimeTransport(1);
        try (CefSessionImpl acknowledged = new CefSessionImpl(transport, Duration.ofSeconds(2))) {
            assertThat(acknowledged).isNotNull();
            assertThat(transport.sendCount).isEqualTo(2);
        }
    }

    @Test
    void runtimeSessionReadinessDoesNotConsumeTheRequestTimeout() {
        AcknowledgingRuntimeTransport transport = new AcknowledgingRuntimeTransport(0, 200);
        try (CefSessionImpl acknowledged = new CefSessionImpl(transport, Duration.ofMillis(100))) {
            assertThat(acknowledged).isNotNull();
        }
    }

    @Test
    void runtimeServerSessionFailsImmediatelyWhenReadyTaskIsRejected() {
        AcknowledgingRuntimeTransport transport = new AcknowledgingRuntimeTransport(0, 0, true);

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new CefSessionImpl(transport, Duration.ofSeconds(2)))
                .isInstanceOf(IllegalStateException.class)
                .cause()
                .isInstanceOfSatisfying(
                        CefRemoteException.class,
                        failure -> assertThat(failure.code()).isEqualTo(CefRemoteException.CODE_TASK_REJECTED));
        assertThat(transport.closeCount).hasValue(1);
    }

    @Test
    void requestResolvesWhenResponseArrives() throws Exception {
        CompletableFuture<TestMessages.BytesView> fut = session.request(
                new TestMessages.BytesEncoder(MSG_PING, "ping".getBytes(StandardCharsets.UTF_8)),
                TestMessages.bytesDecoder(MSG_PING));

        TestPeer.DecodedFrame seenRequest = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "request");
        assertThat(seenRequest.header.kind).isEqualTo(Envelope.Kind.REQUEST);
        assertThat(seenRequest.header.messageId).isEqualTo(MSG_PING);
        assertThat(seenRequest.payload).isEqualTo("ping".getBytes(StandardCharsets.UTF_8));

        peer.sendResponse(seenRequest.header.corrId, MSG_PING, "pong".getBytes(StandardCharsets.UTF_8));

        TestMessages.BytesView v = fut.get(2, TimeUnit.SECONDS);
        assertThat(v.messageId).isEqualTo(MSG_PING);
        assertThat(v.bytes).isEqualTo("pong".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void rejectsRegistrationsAfterClose() {
        session.close();

        assertThatThrownBy(() -> session.on(MSG_EVENT, TestMessages.bytesDecoder(MSG_EVENT), ignored -> {}))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session closed");
        assertThatThrownBy(() -> session.onLatest(MSG_EVENT, TestMessages.bytesDecoder(MSG_EVENT), ignored -> {}))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session closed");
        assertThatThrownBy(() -> session.intercept(
                        MSG_INTERCEPT,
                        TestMessages.bytesDecoder(MSG_INTERCEPT),
                        ignored -> new TestMessages.BytesEncoder(MSG_INTERCEPT, new byte[0])))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session closed");
    }

    @Test
    void cancelledRequestRejectsLateAndMismatchedResponses() throws Exception {
        CompletableFuture<TestMessages.BytesView> cancelled = session.request(
                new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));
        TestPeer.DecodedFrame cancelledRequest = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "request");
        assertThat(cancelled.cancel(true)).isTrue();

        CompletableFuture<TestMessages.BytesView> active = session.request(
                new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));
        TestPeer.DecodedFrame activeRequest = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "request");
        peer.sendResponse(cancelledRequest.header.corrId, MSG_PING, "late".getBytes(StandardCharsets.UTF_8));
        peer.sendResponse(activeRequest.header.corrId, MSG_EVENT, "wrong".getBytes(StandardCharsets.UTF_8));
        assertThat(active).isNotDone();

        peer.sendResponse(activeRequest.header.corrId, MSG_PING, "ok".getBytes(StandardCharsets.UTF_8));
        assertThat(active.get(2, TimeUnit.SECONDS).bytes).isEqualTo("ok".getBytes(StandardCharsets.UTF_8));
        assertThat(cancelled).isCancelled();
    }

    @Test
    void malformedStructuredErrorsFailTheRequestAsProtocolErrors() throws Exception {
        assertMalformedError(new byte[] {1, 0, 0});
        assertMalformedError(new byte[] {1, 0, 0, 0, -1, -1, -1, -1});
        assertMalformedError(new byte[] {1, 0, 0, 0, 1, 0, 0, 4});
        assertMalformedError(new byte[] {1, 0, 0, 0, 2, 0, 0, 0, 'x'});
        assertMalformedError(new byte[] {1, 0, 0, 0, 0, 0, 0, 0, 9});
    }

    @Test
    void taskSubmissionRejectionCompletesRequestImmediately() throws Exception {
        CompletableFuture<TestMessages.BytesView> future = session.request(
                new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));
        TestPeer.DecodedFrame request = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "request");
        peer.sendError(
                request.header.corrId, MSG_PING, new byte[] {CefRemoteException.CODE_TASK_REJECTED, 0, 0, 0, 0, 0, 0, 0
                });

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> future.get(2, TimeUnit.SECONDS))
                .isInstanceOf(ExecutionException.class)
                .cause()
                .isInstanceOfSatisfying(
                        CefRemoteException.class,
                        failure -> assertThat(failure.code()).isEqualTo(CefRemoteException.CODE_TASK_REJECTED));
    }

    private void assertMalformedError(byte[] payload) throws Exception {
        CompletableFuture<TestMessages.BytesView> future = session.request(
                new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));
        TestPeer.DecodedFrame request = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "request");
        peer.sendError(request.header.corrId, MSG_PING, payload);
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> future.get(2, TimeUnit.SECONDS))
                .isInstanceOf(ExecutionException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    private static final class AcknowledgingRuntimeTransport implements CefTransport {
        private final int requestsToDrop;
        private final long acknowledgementDelayMillis;
        private final boolean rejectReady;

        @Nullable
        private Consumer<ByteBuffer> receiver;

        @Nullable
        private ByteBuffer readyRequest;

        private int sendCount;
        private final AtomicInteger closeCount = new AtomicInteger();

        private AcknowledgingRuntimeTransport() {
            this(0);
        }

        private AcknowledgingRuntimeTransport(int requestsToDrop) {
            this(requestsToDrop, 0);
        }

        private AcknowledgingRuntimeTransport(int requestsToDrop, long acknowledgementDelayMillis) {
            this(requestsToDrop, acknowledgementDelayMillis, false);
        }

        private AcknowledgingRuntimeTransport(
                int requestsToDrop, long acknowledgementDelayMillis, boolean rejectReady) {
            this.requestsToDrop = requestsToDrop;
            this.acknowledgementDelayMillis = acknowledgementDelayMillis;
            this.rejectReady = rejectReady;
        }

        @Override
        public void send(@Nonnull ByteBuffer frame) throws CefTransportException {
            sendCount++;
            readyRequest = ByteBuffer.allocate(frame.remaining()).order(ByteOrder.LITTLE_ENDIAN);
            readyRequest.put(frame).flip();
            if (sendCount <= requestsToDrop) return;
            Envelope.Header request = Envelope.readHeader(readyRequest.duplicate());
            int payloadLength = rejectReady ? 2 * Integer.BYTES : 0;
            ByteBuffer response =
                    ByteBuffer.allocate(Envelope.HEADER_SIZE + payloadLength).order(ByteOrder.LITTLE_ENDIAN);
            Envelope.writeHeader(
                    response,
                    rejectReady ? Envelope.Kind.ERROR : Envelope.Kind.RESPONSE,
                    0,
                    request.corrId,
                    request.messageId,
                    payloadLength);
            if (rejectReady)
                response.putInt(CefRemoteException.CODE_TASK_REJECTED).putInt(0);
            response.flip();
            if (acknowledgementDelayMillis == 0) {
                Objects.requireNonNull(receiver).accept(response);
            } else {
                Thread delayed = new Thread(
                        () -> {
                            try {
                                Thread.sleep(acknowledgementDelayMillis);
                                Objects.requireNonNull(receiver).accept(response);
                            } catch (InterruptedException interrupted) {
                                Thread.currentThread().interrupt();
                            }
                        },
                        "delayed-runtime-ready");
                delayed.setDaemon(true);
                delayed.start();
            }
        }

        @Override
        public void onReceive(@Nonnull Consumer<ByteBuffer> handler) {
            receiver = handler;
        }

        @Override
        public void onDisconnect(@Nonnull Runnable handler) {}

        @Override
        public boolean isConnected() {
            return true;
        }

        @Override
        public boolean isRuntimeServerClient() {
            return true;
        }

        @Override
        public void close() {
            closeCount.incrementAndGet();
        }
    }

    @Test
    void concurrentCloseClosesTransportExactlyOnce() throws Exception {
        AcknowledgingRuntimeTransport transport = new AcknowledgingRuntimeTransport();
        CefSessionImpl concurrent = new CefSessionImpl(transport, Duration.ofSeconds(2));
        CountDownLatch start = new CountDownLatch(1);
        List<CompletableFuture<Void>> closers = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            closers.add(CompletableFuture.runAsync(() -> {
                try {
                    if (!start.await(5, TimeUnit.SECONDS)) throw new IllegalStateException("close start not released");
                    concurrent.close();
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    throw new java.util.concurrent.CompletionException(interrupted);
                }
            }));
        }
        start.countDown();
        CompletableFuture.allOf(closers.toArray(new CompletableFuture<?>[0])).get(5, TimeUnit.SECONDS);

        assertThat(transport.closeCount).hasValue(1);
    }

    @Test
    void concurrentRequestsResolveByCorrId() throws Exception {
        int n = 50;
        List<CompletableFuture<TestMessages.BytesView>> futures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            futures.add(session.request(
                    new TestMessages.BytesEncoder(MSG_PING, ("req-" + i).getBytes(StandardCharsets.UTF_8)),
                    TestMessages.bytesDecoder(MSG_PING)));
        }
        for (int i = 0; i < n; i++) {
            TestPeer.DecodedFrame f = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "request");
            byte[] resp = ("resp-corr-" + f.header.corrId).getBytes(StandardCharsets.UTF_8);
            peer.sendResponse(f.header.corrId, MSG_PING, resp);
        }
        for (int i = 0; i < n; i++) {
            TestMessages.BytesView v = futures.get(i).get(5, TimeUnit.SECONDS);
            String body = new String(v.bytes, StandardCharsets.UTF_8);
            assertThat(body).startsWith("resp-corr-");
        }
    }

    @Test
    void requestTimesOutWhenNoResponseArrives() {
        CefSessionImpl tightTimeoutSession = new CefSessionImpl(pair.a, Duration.ofMillis(100));
        try {
            CompletableFuture<TestMessages.BytesView> fut = tightTimeoutSession.request(
                    new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));
            try {
                peer.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            org.assertj.core.api.Assertions.assertThatThrownBy(() -> fut.get(2, TimeUnit.SECONDS))
                    .isInstanceOf(ExecutionException.class)
                    .hasCauseInstanceOf(TimeoutException.class);
        } finally {
            tightTimeoutSession.close();
        }
    }

    @Test
    void rejectedTimeoutSchedulingReturnsFailedFutureWithoutSending() throws Exception {
        LoopbackTransport.Pair isolated = LoopbackTransport.create();
        ScheduledExecutorService stopped = Executors.newSingleThreadScheduledExecutor();
        stopped.shutdownNow();
        CefSessionImpl rejected = new CefSessionImpl(isolated.a, Duration.ofSeconds(1), stopped);
        TestPeer isolatedPeer = new TestPeer(isolated.b);
        try {
            CompletableFuture<TestMessages.BytesView> future = rejected.request(
                    new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));

            assertThat(future).isCompletedExceptionally();
            assertThat(isolatedPeer.poll(100, TimeUnit.MILLISECONDS)).isNull();
        } finally {
            rejected.close();
            isolatedPeer.close();
        }
    }

    @Test
    void eventDispatchedToSubscriber() throws Exception {
        CountDownLatch arrived = new CountDownLatch(1);
        ConcurrentLinkedQueue<TestMessages.BytesView> sink = new ConcurrentLinkedQueue<>();
        session.on(MSG_EVENT, TestMessages.bytesDecoder(MSG_EVENT), v -> {
            sink.add(v);
            arrived.countDown();
        });
        peer.sendEvent(MSG_EVENT, "boom".getBytes(StandardCharsets.UTF_8));
        assertThat(arrived.await(2, TimeUnit.SECONDS)).isTrue();
        assertThat(sink).hasSize(1);
        assertThat(sink.peek().bytes).isEqualTo("boom".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void onLatestReplaysEventReceivedBeforeSubscription() throws Exception {
        CountDownLatch stored = new CountDownLatch(1);
        CefSession.HandlerRegistration initial =
                session.on(MSG_EVENT, TestMessages.bytesDecoder(MSG_EVENT), ignored -> stored.countDown());
        peer.sendEvent(MSG_EVENT, "ready".getBytes(StandardCharsets.UTF_8));
        assertThat(stored.await(2, TimeUnit.SECONDS)).isTrue();
        initial.unregister();

        CountDownLatch arrived = new CountDownLatch(1);
        AtomicReference<TestMessages.BytesView> seen = new AtomicReference<>();
        session.onLatest(MSG_EVENT, TestMessages.bytesDecoder(MSG_EVENT), value -> {
            seen.set(value);
            arrived.countDown();
        });

        assertThat(arrived.await(2, TimeUnit.SECONDS)).isTrue();
        assertThat(Objects.requireNonNull(seen.get()).bytes).isEqualTo("ready".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void multipleSubscribersAllReceive() throws Exception {
        AtomicInteger a = new AtomicInteger();
        AtomicInteger b = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(2);
        session.on(MSG_EVENT, TestMessages.bytesDecoder(MSG_EVENT), v -> {
            a.incrementAndGet();
            latch.countDown();
        });
        session.on(MSG_EVENT, TestMessages.bytesDecoder(MSG_EVENT), v -> {
            b.incrementAndGet();
            latch.countDown();
        });
        peer.sendEvent(MSG_EVENT, new byte[] {1});
        assertThat(latch.await(2, TimeUnit.SECONDS)).isTrue();
        assertThat(a.get()).isEqualTo(1);
        assertThat(b.get()).isEqualTo(1);
    }

    @Test
    void unregisterStopsDelivery() throws Exception {
        AtomicInteger count = new AtomicInteger();
        CountDownLatch first = new CountDownLatch(1);
        CountDownLatch unexpected = new CountDownLatch(1);
        CefSession.HandlerRegistration reg = session.on(MSG_EVENT, TestMessages.bytesDecoder(MSG_EVENT), v -> {
            if (count.incrementAndGet() == 1) first.countDown();
            else unexpected.countDown();
        });
        peer.sendEvent(MSG_EVENT, new byte[] {1});
        assertThat(first.await(2, TimeUnit.SECONDS)).isTrue();
        assertThat(count.get()).isEqualTo(1);

        reg.unregister();
        peer.sendEvent(MSG_EVENT, new byte[] {2});
        assertThat(unexpected.await(300, TimeUnit.MILLISECONDS)).isFalse();
        assertThat(count.get()).as("after unregister, no further deliveries").isEqualTo(1);
    }

    @Test
    void interceptHandlerResponseSentBack() throws Exception {
        session.intercept(MSG_INTERCEPT, TestMessages.bytesDecoder(MSG_INTERCEPT), event -> {
            byte[] reversed = new byte[event.bytes.length];
            for (int i = 0; i < event.bytes.length; i++) reversed[i] = event.bytes[event.bytes.length - 1 - i];
            return new TestMessages.BytesEncoder(MSG_INTERCEPT, reversed);
        });

        peer.sendIntercept(/*corrId*/ 99, MSG_INTERCEPT, "abc".getBytes(StandardCharsets.UTF_8));
        TestPeer.DecodedFrame resp = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "intercept response");
        assertThat(resp.header.kind).isEqualTo(Envelope.Kind.INTERCEPT_RESPONSE);
        assertThat(resp.header.corrId).isEqualTo(99);
        assertThat(resp.header.messageId).isEqualTo(MSG_INTERCEPT);
        assertThat(resp.payload).isEqualTo("cba".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void interceptWithoutHandlerSendsEmptyResponse() throws Exception {
        peer.sendIntercept(/*corrId*/ 7, MSG_INTERCEPT, "ignored".getBytes(StandardCharsets.UTF_8));
        TestPeer.DecodedFrame resp = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "default response");
        assertThat(resp.header.kind).isEqualTo(Envelope.Kind.INTERCEPT_RESPONSE);
        assertThat(resp.header.corrId).isEqualTo(7);
        assertThat(resp.header.messageId).isEqualTo(MSG_INTERCEPT);
        assertThat(resp.payload).isEmpty();
    }

    @Test
    void interceptHandlerReturningNullSendsEmptyResponse() throws Exception {
        session.intercept(MSG_INTERCEPT, TestMessages.bytesDecoder(MSG_INTERCEPT), event -> null);
        peer.sendIntercept(/*corrId*/ 11, MSG_INTERCEPT, "x".getBytes(StandardCharsets.UTF_8));
        TestPeer.DecodedFrame resp = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "null-handler response");
        assertThat(resp.header.kind).isEqualTo(Envelope.Kind.INTERCEPT_RESPONSE);
        assertThat(resp.payload).isEmpty();
    }

    @Test
    void closeFailsPendingRequests() {
        CompletableFuture<TestMessages.BytesView> fut = session.request(
                new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));
        session.close();
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> fut.get(2, TimeUnit.SECONDS))
                .isInstanceOf(ExecutionException.class);
        assertThat(fut.isCompletedExceptionally()).isTrue();
    }

    @Test
    void peerDisconnectStopsOwnedTimerThread() throws Exception {
        CompletableFuture<TestMessages.BytesView> fut = session.request(
                new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));
        Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "request");

        pair.b.close();
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> fut.get(2, TimeUnit.SECONDS))
                .isInstanceOf(ExecutionException.class);

        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(2);
        while (hasLiveSessionTimer() && System.nanoTime() < deadline) Thread.sleep(10);
        assertThat(hasLiveSessionTimer()).isFalse();
    }

    @Test
    void peerDisconnectNotifiesCloseListenersExactlyOnce() throws Exception {
        CountDownLatch closed = new CountDownLatch(1);
        AtomicInteger calls = new AtomicInteger();
        session.onClose(() -> {
            calls.incrementAndGet();
            closed.countDown();
        });
        pair.b.close();
        assertThat(closed.await(2, TimeUnit.SECONDS)).isTrue();
        session.close();
        assertThat(calls).hasValue(1);
    }

    private static boolean hasLiveSessionTimer() {
        return Thread.getAllStackTraces().keySet().stream()
                .anyMatch(t -> t.isAlive() && t.getName().equals("cef-session-timer"));
    }

    @Test
    void orphanResponseDoesNotCrashSession() throws Exception {
        peer.sendResponse(/*corrId*/ 12345, MSG_PING, new byte[] {1, 2, 3});
        CompletableFuture<TestMessages.BytesView> fut = session.request(
                new TestMessages.BytesEncoder(MSG_PING, new byte[0]), TestMessages.bytesDecoder(MSG_PING));
        TestPeer.DecodedFrame f = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "follow-up request");
        peer.sendResponse(f.header.corrId, MSG_PING, new byte[] {9});
        TestMessages.BytesView v = fut.get(2, TimeUnit.SECONDS);
        assertThat(v.bytes).containsExactly(9);
    }

    @Test
    void requestWithFailingEncoderCompletesFutureExceptionally() {
        CompletableFuture<TestMessages.BytesView> fut =
                session.request(new FailingEncoder(), TestMessages.bytesDecoder(MSG_PING));
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> fut.get(2, TimeUnit.SECONDS))
                .isInstanceOf(ExecutionException.class)
                .hasCauseInstanceOf(IllegalStateException.class);
    }

    @Test
    void sessionSurvivesAFailedEncode() throws Exception {
        var unused = session.request(new FailingEncoder(), TestMessages.bytesDecoder(MSG_PING));
        CompletableFuture<TestMessages.BytesView> healthy = session.request(
                new TestMessages.BytesEncoder(MSG_PING, "ok".getBytes(StandardCharsets.UTF_8)),
                TestMessages.bytesDecoder(MSG_PING));
        TestPeer.DecodedFrame frame = Objects.requireNonNull(peer.poll(2, TimeUnit.SECONDS), "healthy request");
        peer.sendResponse(frame.header.corrId, MSG_PING, "ack".getBytes(StandardCharsets.UTF_8));
        assertThat(healthy.get(2, TimeUnit.SECONDS).bytes).isEqualTo("ack".getBytes(StandardCharsets.UTF_8));
    }

    private static final class FailingEncoder implements CefMessageEncoder {
        @Override
        public int messageId() {
            return MSG_PING;
        }

        @Override
        public int encodedSize() {
            return 4;
        }

        @Override
        public void encodeInto(@Nonnull ByteBuffer destination) {
            throw new IllegalStateException("deliberate encode failure");
        }
    }
}
