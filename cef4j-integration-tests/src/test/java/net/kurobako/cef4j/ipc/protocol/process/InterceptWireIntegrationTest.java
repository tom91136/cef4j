package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.TriggerInterceptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.TriggerInterceptResponse;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.test.RuntimeServerTestEnvironment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(600)
class InterceptWireIntegrationTest {

    private static final int ECHO_MESSAGE_ID = 99001;

    private static final RuntimeServerTestEnvironment RUNTIME = RuntimeServerTestEnvironment.require();

    @Test
    void interceptHandlerReturnIsRoutedBackToRuntimeServer() throws Exception {
        try (RuntimeServerProcess server = RUNTIME.spawn();
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            byte[] requestPayload = "ping".getBytes(java.nio.charset.StandardCharsets.UTF_8);
            byte[] expectedReply = "pong".getBytes(java.nio.charset.StandardCharsets.UTF_8);

            session.intercept(ECHO_MESSAGE_ID, RawPayloadView.DECODER, observed -> {
                assertThat(observed.bytes()).isEqualTo(requestPayload);
                return encoderFor(ECHO_MESSAGE_ID, expectedReply);
            });

            TriggerInterceptResponse resp = session.request(
                            new TriggerInterceptRequest(ECHO_MESSAGE_ID, requestPayload),
                            TriggerInterceptResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);

            assertThat(resp.returnedPayload()).isEqualTo(expectedReply);
        }
    }

    private static final class RawPayloadView implements CefMessageView {
        static final net.kurobako.cef4j.ipc.session.CefMessageDecoder<RawPayloadView> DECODER = src -> {
            byte[] copy = new byte[src.remaining()];
            src.get(copy);
            return new RawPayloadView(copy);
        };

        private final byte[] bytes;

        RawPayloadView(byte[] bytes) {
            this.bytes = bytes;
        }

        byte[] bytes() {
            return bytes;
        }

        @Override
        public int messageId() {
            return ECHO_MESSAGE_ID;
        }
    }

    private static CefMessageEncoder encoderFor(int messageId, byte[] payload) {
        return new CefMessageEncoder() {
            @Override
            public int messageId() {
                return messageId;
            }

            @Override
            public int encodedSize() {
                return payload.length;
            }

            @Override
            public void encodeInto(ByteBuffer dst) {
                ByteOrder original = dst.order();
                try {
                    dst.order(ByteOrder.LITTLE_ENDIAN);
                    dst.put(payload);
                } finally {
                    dst.order(original);
                }
            }
        };
    }
}
