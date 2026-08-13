package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.TriggerInterceptRequest;
import net.kurobako.cef4j.ipc.protocol.gen.TriggerInterceptResponse;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Validates the synchronous server→JVM intercept wire end-to-end. Path:
 *
 * <ol>
 *   <li>JVM sends {@link TriggerInterceptRequest}{echoMessageId, echoPayload}.
 *   <li>Runtime server allocates a corrId, fires Kind::Intercept(echoMessageId, corrId, echoPayload), blocks.
 *   <li>JVM's intercept handler for echoMessageId runs, returns a payload (bytes wrapped as {@link #encoderFor}).
 *   <li>Session sends Kind::InterceptResponse(corrId, ..., responsePayload).
 *   <li>Runtime server's InterceptRegistry wakes the waiter, server acks original request with
 *       {@link TriggerInterceptResponse}{returnedPayload = handler's payload}.
 * </ol>
 *
 * This is the foundation for every non-void handler callback: OnBeforePopup, DoClose, OnJsDialog, OnBeforeBrowse.
 * Codegen integration (replacing the hand-written TriggerIntercept fixture with one server→JVM callback per non-void
 * CEF handler method) builds on top of this same wire.
 */
@Timeout(60)
class InterceptWireIntegrationTest {

    /** Picked arbitrarily, well above the AST messageId range. */
    private static final int ECHO_MESSAGE_ID = 99001;

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
        return RuntimeServerProcess.spawn(
                serverBinary,
                "zmq",
                "tcp://127.0.0.1:0",
                "shared-file",
                Duration.ofSeconds(30),
                net.kurobako.cef4j.ipc.frame.RemoteCefBrowserBackend.runtimeEnvironment(cefResources));
    }

    @Test
    void interceptHandlerReturnIsRoutedBackToRuntimeServer() throws Exception {
        try (RuntimeServerProcess server = spawnServerWithEnv();
                ZmqTransport transport = ZmqTransport.connect(server.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(10))) {

            byte[] requestPayload = "ping".getBytes(java.nio.charset.StandardCharsets.UTF_8);
            byte[] expectedReply = "pong".getBytes(java.nio.charset.StandardCharsets.UTF_8);

            // Register an intercept handler that observes the server's request and returns a fixed reply.
            session.intercept(ECHO_MESSAGE_ID, RawPayloadView.DECODER, observed -> {
                assertThat(observed.bytes()).isEqualTo(requestPayload);
                return encoderFor(ECHO_MESSAGE_ID, expectedReply);
            });

            TriggerInterceptResponse resp = session.request(
                            new TriggerInterceptRequest(ECHO_MESSAGE_ID, requestPayload),
                            TriggerInterceptResponse.DECODER)
                    .get(10, TimeUnit.SECONDS);

            // The bytes the server got back from awaitResponse are exactly what our handler returned.
            assertThat(resp.returnedPayload()).isEqualTo(expectedReply);
        }
    }

    /** Minimal {@link CefMessageView} that exposes the raw payload bytes — handlers don't care about decoding here. */
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

    /** Wraps a raw byte[] as a {@link CefMessageEncoder} so the intercept handler can return arbitrary bytes. */
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
