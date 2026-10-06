package net.kurobako.cef4j.ipc.protocol.gen;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.ipc.transport.LoopbackTransport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(10)
class GeneratedMessagesIntegrationTest {

    @Test
    void navigateRequestRoundTripsThroughSession() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSession session = new CefSessionImpl(pair.a, Duration.ofSeconds(2))) {
            CefTransport peer = pair.b;
            peer.onReceive(frame -> {
                try {
                    ByteBuffer view = frame.duplicate();
                    Envelope.Header h = Envelope.readHeader(view);
                    if (h.kind == Envelope.Kind.REQUEST && h.messageId == NavigateRequest.MESSAGE_ID) {
                        NavigateRequest req = NavigateRequest.DECODER.decode(view.slice());
                        NavigateResult result = new NavigateResult(
                                1,
                                200,
                                "https://example.com/redirected" + req.url().length(),
                                4096L,
                                true);
                        sendKind(peer, Envelope.Kind.RESPONSE, h.corrId, h.messageId, result);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            CompletableFuture<NavigateResult> fut = session.request(
                    new NavigateRequest("https://example.com/start", "https://example.com/from", 1, true),
                    NavigateResult.DECODER);
            NavigateResult got = fut.get(2, TimeUnit.SECONDS);

            assertThat(got.browserId()).isEqualTo(1);
            assertThat(got.httpStatus()).isEqualTo(200);
            assertThat(got.bytesLoaded()).isEqualTo(4096L);
            assertThat(got.ok()).isTrue();
            assertThat(got.finalUrl()).startsWith("https://example.com/redirected");
        } finally {
            pair.b.close();
        }
    }

    private static void sendKind(
            CefTransport peer, Envelope.Kind kind, int corrId, int messageId, CefMessageEncoder enc) throws Exception {
        ByteBuffer buf =
                ByteBuffer.allocate(Envelope.HEADER_SIZE + enc.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(buf, kind, 0, corrId, messageId, enc.encodedSize());
        enc.encodeInto(buf);
        buf.flip();
        peer.send(buf);
    }
}
