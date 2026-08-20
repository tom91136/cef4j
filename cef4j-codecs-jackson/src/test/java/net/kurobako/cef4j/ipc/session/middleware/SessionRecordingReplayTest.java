package net.kurobako.cef4j.ipc.session.middleware;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.transport.LoopbackTransport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(15)
class SessionRecordingReplayTest {
    private static final int REQUEST = 101;
    private static final int EVENT = 202;
    private static final int INTERCEPT = 303;

    @Test
    void recordsAndReplaysRequestsEventsAndIntercepts(@TempDir Path directory) throws Exception {
        Path traceFile = directory.resolve("reproducer.cef4japi.jsonl");
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        Peer peer = new Peer(pair.b);
        List<String> liveEvents = new ArrayList<>();

        try (RecordingCefSession live = RecordingCefSession.toFile(new CefSessionImpl(pair.a), traceFile)) {
            CefSession.HandlerRegistration events = live.on(EVENT, decoder(EVENT), value -> liveEvents.add(value.text));
            CefSession.HandlerRegistration intercept =
                    live.intercept(INTERCEPT, decoder(INTERCEPT), value -> encoder(INTERCEPT, "reply:" + value.text));

            CompletableFuture<TextView> answer = live.request(encoder(REQUEST, "question"), decoder(REQUEST));
            Peer.Frame request = peer.take();
            peer.event(EVENT, "paint");
            peer.intercept(77, INTERCEPT, "permission");
            Peer.Frame interceptReply = peer.take();
            peer.response(request.correlationId, REQUEST, "answer");

            assertThat(answer.get(5, TimeUnit.SECONDS).text).isEqualTo("answer");
            assertThat(liveEvents).containsExactly("paint");
            assertThat(interceptReply.kind).isEqualTo(Envelope.Kind.INTERCEPT_RESPONSE);
            assertThat(interceptReply.text()).isEqualTo("reply:permission");
            events.unregister();
            intercept.unregister();
        } finally {
            peer.close();
        }

        SessionTrace.Recording recording = SessionTrace.read(traceFile);
        assertThat(recording.metadata()).containsEntry("format", "cef4j-session-api");
        assertThat(recording.entries())
                .extracting(entry -> entry.kind)
                .containsExactly(
                        SessionTrace.Kind.SUBSCRIBE,
                        SessionTrace.Kind.INTERCEPT_REGISTER,
                        SessionTrace.Kind.REQUEST,
                        SessionTrace.Kind.EVENT,
                        SessionTrace.Kind.INTERCEPT,
                        SessionTrace.Kind.INTERCEPT_RESPONSE,
                        SessionTrace.Kind.RESPONSE,
                        SessionTrace.Kind.UNSUBSCRIBE,
                        SessionTrace.Kind.INTERCEPT_UNREGISTER,
                        SessionTrace.Kind.CLOSE);

        List<String> replayedEvents = new ArrayList<>();
        ReplayCefSession replay = new ReplayCefSession(recording, ReplayMode.IMMEDIATE);
        CefSession.HandlerRegistration events =
                replay.on(EVENT, decoder(EVENT), value -> replayedEvents.add(value.text));
        CefSession.HandlerRegistration intercept =
                replay.intercept(INTERCEPT, decoder(INTERCEPT), value -> encoder(INTERCEPT, "reply:" + value.text));
        replay.start();
        assertThat(replay.request(encoder(REQUEST, "question"), decoder(REQUEST))
                        .get(5, TimeUnit.SECONDS)
                        .text)
                .isEqualTo("answer");
        assertThat(replayedEvents).containsExactly("paint");
        events.unregister();
        intercept.unregister();
        replay.close();
        replay.verifyComplete();
    }

    @Test
    void strictReplayReportsTheEntryAndFirstDifferentByte(@TempDir Path directory) throws Exception {
        Path traceFile = directory.resolve("mismatch.cef4japi.jsonl");
        try (SessionTraceWriter writer = SessionTrace.writer(traceFile)) {
            writer.append(SessionTrace.Kind.REQUEST, 1, REQUEST, bytes("expected"));
            writer.append(SessionTrace.Kind.RESPONSE, 1, REQUEST, bytes("answer"));
            writer.append(SessionTrace.Kind.CLOSE, 0, 0, null);
        }

        ReplayCefSession replay = ReplayCefSession.fromFile(traceFile, ReplayMode.IMMEDIATE);
        replay.start();
        CompletableFuture<TextView> result = replay.request(encoder(REQUEST, "expXcted"), decoder(REQUEST));
        assertThatThrownBy(() -> result.get(5, TimeUnit.SECONDS))
                .hasRootCauseInstanceOf(ReplayMismatchException.class)
                .hasRootCauseMessage("API replay mismatch at entry #1 (REQUEST): request payload differs at byte 3 "
                        + "(recorded=8 bytes, actual=8 bytes)");
    }

    @Test
    void manualReplayAdvancesOneDeliveryAtATime(@TempDir Path directory) throws Exception {
        Path traceFile = directory.resolve("manual.cef4japi.jsonl");
        try (SessionTraceWriter writer = SessionTrace.writer(traceFile)) {
            writer.append(SessionTrace.Kind.REQUEST, 1, REQUEST, bytes("question"));
            writer.append(SessionTrace.Kind.RESPONSE, 1, REQUEST, bytes("answer"));
            writer.append(SessionTrace.Kind.CLOSE, 0, 0, null);
        }

        ReplayCefSession replay = ReplayCefSession.fromFile(traceFile, ReplayMode.MANUAL);
        replay.start();
        CompletableFuture<TextView> result = replay.request(encoder(REQUEST, "question"), decoder(REQUEST));
        assertThat(result).isNotDone();
        assertThat(replay.advance()).isTrue();
        assertThat(result.get(5, TimeUnit.SECONDS).text).isEqualTo("answer");
        assertThat(replay.advance()).isFalse();
        replay.close();
        replay.verifyComplete();
    }

    @Test
    void preservesConcurrentRequestCompletionOrder(@TempDir Path directory) throws Exception {
        Path traceFile = directory.resolve("concurrent.cef4japi.jsonl");
        try (SessionTraceWriter writer = SessionTrace.writer(traceFile)) {
            writer.append(SessionTrace.Kind.REQUEST, 1, REQUEST, bytes("first"));
            writer.append(SessionTrace.Kind.REQUEST, 2, REQUEST, bytes("second"));
            writer.append(SessionTrace.Kind.RESPONSE, 2, REQUEST, bytes("second-done"));
            writer.append(SessionTrace.Kind.RESPONSE, 1, REQUEST, bytes("first-done"));
            writer.append(SessionTrace.Kind.CLOSE, 0, 0, null);
        }

        ReplayCefSession replay = ReplayCefSession.fromFile(traceFile, ReplayMode.IMMEDIATE);
        replay.start();
        CompletableFuture<TextView> first = replay.request(encoder(REQUEST, "first"), decoder(REQUEST));
        assertThat(first).isNotDone();
        CompletableFuture<TextView> second = replay.request(encoder(REQUEST, "second"), decoder(REQUEST));
        assertThat(second.get(5, TimeUnit.SECONDS).text).isEqualTo("second-done");
        assertThat(first.get(5, TimeUnit.SECONDS).text).isEqualTo("first-done");
        replay.close();
        replay.verifyComplete();
    }

    @Test
    void timedReplayPreservesRecordedDelay() throws Exception {
        List<SessionTrace.Entry> entries = List.of(
                new SessionTrace.Entry(1, 0, SessionTrace.Kind.REQUEST, 1, REQUEST, bytes("question"), null, null),
                new SessionTrace.Entry(
                        2,
                        TimeUnit.MILLISECONDS.toNanos(100),
                        SessionTrace.Kind.RESPONSE,
                        1,
                        REQUEST,
                        bytes("answer"),
                        null,
                        null),
                new SessionTrace.Entry(
                        3, TimeUnit.MILLISECONDS.toNanos(101), SessionTrace.Kind.CLOSE, 0, 0, null, null, null));
        ReplayCefSession replay =
                new ReplayCefSession(new SessionTrace.Recording(Collections.emptyMap(), entries), ReplayMode.TIMED);
        long startedAt = System.nanoTime();
        replay.start();
        CompletableFuture<TextView> result = replay.request(encoder(REQUEST, "question"), decoder(REQUEST));
        assertThat(result.get(5, TimeUnit.SECONDS).text).isEqualTo("answer");
        assertThat(System.nanoTime() - startedAt).isGreaterThanOrEqualTo(TimeUnit.MILLISECONDS.toNanos(100));
        replay.close();
        replay.verifyComplete();
    }

    @Test
    void replaysRecordedRequestFailure(@TempDir Path directory) throws Exception {
        Path traceFile = directory.resolve("failure.cef4japi.jsonl");
        try (SessionTraceWriter writer = SessionTrace.writer(traceFile)) {
            writer.append(SessionTrace.Kind.REQUEST, 1, REQUEST, bytes("question"));
            writer.append(
                    SessionTrace.Kind.FAILURE,
                    1,
                    REQUEST,
                    null,
                    "java.util.concurrent.TimeoutException",
                    "server did not answer");
            writer.append(SessionTrace.Kind.CLOSE, 0, 0, null);
        }

        ReplayCefSession replay = ReplayCefSession.fromFile(traceFile, ReplayMode.IMMEDIATE);
        replay.start();
        assertThatThrownBy(() -> replay.request(encoder(REQUEST, "question"), decoder(REQUEST))
                        .get(5, TimeUnit.SECONDS))
                .hasRootCauseInstanceOf(ReplayedSessionException.class)
                .hasRootCauseMessage("java.util.concurrent.TimeoutException: server did not answer");
        replay.close();
        replay.verifyComplete();
    }

    @Test
    void replaysRecordedInterceptFailure(@TempDir Path directory) throws Exception {
        Path traceFile = directory.resolve("intercept-failure.cef4japi.jsonl");
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        Peer peer = new Peer(pair.b);
        try (RecordingCefSession live = RecordingCefSession.toFile(new CefSessionImpl(pair.a), traceFile)) {
            CefSession.HandlerRegistration registration = live.intercept(INTERCEPT, decoder(INTERCEPT), value -> {
                throw new IllegalArgumentException("deliberate failure");
            });
            peer.intercept(91, INTERCEPT, "permission");
            assertThat(peer.take().payload).isEmpty();
            registration.unregister();
        } finally {
            peer.close();
        }

        ReplayCefSession replay = ReplayCefSession.fromFile(traceFile, ReplayMode.IMMEDIATE);
        CefSession.HandlerRegistration registration = replay.intercept(INTERCEPT, decoder(INTERCEPT), value -> {
            throw new IllegalArgumentException("deliberate failure");
        });
        replay.start();
        registration.unregister();
        replay.close();
        replay.verifyComplete();
    }

    private static TextEncoder encoder(int messageId, String text) {
        return new TextEncoder(messageId, bytes(text));
    }

    private static CefMessageDecoder<TextView> decoder(int messageId) {
        return payload -> {
            byte[] bytes = new byte[payload.remaining()];
            payload.get(bytes);
            return new TextView(messageId, new String(bytes, StandardCharsets.UTF_8));
        };
    }

    private static byte[] bytes(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }

    private static final class TextEncoder implements CefMessageEncoder {
        private final int messageId;
        private final byte[] bytes;

        TextEncoder(int messageId, byte[] bytes) {
            this.messageId = messageId;
            this.bytes = bytes;
        }

        @Override
        public int messageId() {
            return messageId;
        }

        @Override
        public int encodedSize() {
            return bytes.length;
        }

        @Override
        public void encodeInto(@Nonnull ByteBuffer destination) {
            destination.put(bytes);
        }
    }

    private static final class TextView implements CefMessageView {
        private final int messageId;
        private final String text;

        TextView(int messageId, String text) {
            this.messageId = messageId;
            this.text = text;
        }

        @Override
        public int messageId() {
            return messageId;
        }
    }

    private static final class Peer implements AutoCloseable {
        private final net.kurobako.cef4j.ipc.transport.CefTransport transport;
        private final java.util.concurrent.BlockingQueue<Frame> frames =
                new java.util.concurrent.LinkedBlockingQueue<>();

        Peer(net.kurobako.cef4j.ipc.transport.CefTransport transport) {
            this.transport = transport;
            transport.onReceive(payload -> {
                ByteBuffer frame = payload.duplicate();
                Envelope.Header header = Envelope.readHeader(frame);
                byte[] bytes = new byte[frame.remaining()];
                frame.get(bytes);
                frames.offer(new Frame(header.kind, header.corrId, bytes));
            });
        }

        Frame take() throws InterruptedException {
            Frame frame = frames.poll(5, TimeUnit.SECONDS);
            if (frame == null) throw new AssertionError("peer did not receive a frame");
            return frame;
        }

        void response(int correlationId, int messageId, String text)
                throws net.kurobako.cef4j.ipc.transport.CefTransportException {
            send(Envelope.Kind.RESPONSE, correlationId, messageId, text);
        }

        void event(int messageId, String text) throws net.kurobako.cef4j.ipc.transport.CefTransportException {
            send(Envelope.Kind.EVENT, Envelope.NO_CORR_ID, messageId, text);
        }

        void intercept(int correlationId, int messageId, String text)
                throws net.kurobako.cef4j.ipc.transport.CefTransportException {
            send(Envelope.Kind.INTERCEPT, correlationId, messageId, text);
        }

        private void send(Envelope.Kind kind, int correlationId, int messageId, String text)
                throws net.kurobako.cef4j.ipc.transport.CefTransportException {
            byte[] bytes = bytes(text);
            ByteBuffer frame =
                    ByteBuffer.allocate(Envelope.HEADER_SIZE + bytes.length).order(java.nio.ByteOrder.LITTLE_ENDIAN);
            Envelope.writeHeader(frame, kind, 0, correlationId, messageId, bytes.length);
            frame.put(bytes).flip();
            transport.send(frame);
        }

        @Override
        public void close() {
            transport.close();
        }

        private static final class Frame {
            final Envelope.Kind kind;
            final int correlationId;
            final byte[] payload;

            Frame(Envelope.Kind kind, int correlationId, byte[] payload) {
                this.kind = kind;
                this.correlationId = correlationId;
                this.payload = payload;
            }

            String text() {
                return new String(payload, StandardCharsets.UTF_8);
            }
        }
    }
}
