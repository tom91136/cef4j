package net.kurobako.cef4j.ipc.session;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.kurobako.cef4j.ipc.transport.MessageLog;
import net.kurobako.cef4j.ipc.transport.ReplayTransport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(15)
class SessionReplayTest {

    private static final int MSG_NAVIGATE = 401;
    private static final int MSG_TITLE_CHANGED = 402;

    private static byte[] envelope(Envelope.Kind kind, int corrId, int messageId, byte[] payload) {
        ByteBuffer buf =
                ByteBuffer.allocate(Envelope.HEADER_SIZE + payload.length).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(buf, kind, 0, corrId, messageId, payload.length);
        buf.put(payload);
        return buf.array();
    }

    @Test
    void replayedEventsDispatchToSubscribedHandlers(@TempDir Path tmp) throws Exception {
        Path log = tmp.resolve("session.log");
        try (MessageLog.Writer w = MessageLog.writer(log)) {
            w.append(
                    MessageLog.Direction.INBOUND,
                    100L,
                    envelope(
                            Envelope.Kind.EVENT,
                            Envelope.NO_CORR_ID,
                            MSG_TITLE_CHANGED,
                            "first title".getBytes(StandardCharsets.UTF_8)));
            w.append(
                    MessageLog.Direction.INBOUND,
                    200L,
                    envelope(
                            Envelope.Kind.EVENT,
                            Envelope.NO_CORR_ID,
                            MSG_TITLE_CHANGED,
                            "second title".getBytes(StandardCharsets.UTF_8)));
        }

        try (ReplayTransport replay = ReplayTransport.fromFile(log);
                CefSessionImpl session = new CefSessionImpl(replay)) {
            List<String> titles = new ArrayList<>();
            session.on(
                    MSG_TITLE_CHANGED,
                    TestMessages.bytesDecoder(MSG_TITLE_CHANGED),
                    v -> titles.add(new String(v.bytes, StandardCharsets.UTF_8)));

            replay.start();

            assertThat(titles).containsExactly("first title", "second title");
        }
    }

    @Test
    void replayedResponseResolvesPendingRequest(@TempDir Path tmp) throws Exception {
        Path log = tmp.resolve("session.log");
        try (MessageLog.Writer w = MessageLog.writer(log)) {
            w.append(
                    MessageLog.Direction.INBOUND,
                    100L,
                    envelope(Envelope.Kind.RESPONSE, 1, MSG_NAVIGATE, "navigated".getBytes(StandardCharsets.UTF_8)));
        }

        try (ReplayTransport replay = ReplayTransport.fromFile(log);
                CefSessionImpl session = new CefSessionImpl(replay)) {
            java.util.concurrent.CompletableFuture<TestMessages.BytesView> fut = session.request(
                    new TestMessages.BytesEncoder(MSG_NAVIGATE, "https://example.com".getBytes(StandardCharsets.UTF_8)),
                    TestMessages.bytesDecoder(MSG_NAVIGATE));

            replay.start();

            TestMessages.BytesView v = fut.get(2, java.util.concurrent.TimeUnit.SECONDS);
            assertThat(new String(v.bytes, StandardCharsets.UTF_8)).isEqualTo("navigated");

            assertThat(replay.actualOutbound()).hasSize(1);
        }
    }
}
