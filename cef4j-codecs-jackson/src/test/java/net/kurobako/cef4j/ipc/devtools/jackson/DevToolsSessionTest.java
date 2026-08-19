package net.kurobako.cef4j.ipc.devtools.jackson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpException;
import net.kurobako.cef4j.cdp.jackson.JacksonCdpCodec;
import net.kurobako.cef4j.ipc.devtools.DevToolsSession;
import net.kurobako.cef4j.ipc.devtools.RemoteDevToolsSessionFactory;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHostSendDevToolsMessageRequest;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHostSendDevToolsMessageResponse;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsAgentDetachedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsMessageEvent;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.ipc.transport.CefTransportException;
import net.kurobako.cef4j.ipc.transport.LoopbackTransport;
import org.junit.jupiter.api.Test;

class DevToolsSessionTest {
    private static final ObjectMapper JSON = new ObjectMapper();

    @Test
    void installsRemoteFactoryProvider() {
        assertThat(RemoteDevToolsSessionFactory.installed()).isInstanceOf(JacksonRemoteDevToolsSessionFactory.class);
    }

    @Test
    void correlatesCommandsAndEventsOverTransportNeutralSession() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSessionImpl session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                Peer peer = new Peer(pair.b)) {
            RemoteHandle browser = new RemoteHandle(11);
            BrowserHost host = new BrowserHost(session, new RemoteHandle(22));

            var attaching = DevToolsSession.attach(session, browser, host, new JacksonCdpCodec());
            Frame attach = peer.receive();
            assertThat(attach.messageId).isEqualTo(27);
            peer.respond(attach, null);
            DevToolsSession devTools = attaching.get(2, TimeUnit.SECONDS);

            Map<String, Object> params = Map.of("expression", "6 * 7");
            var command = devTools.send("Runtime.evaluate", params);
            Frame send = peer.receive();
            assertThat(send.messageId).isEqualTo(BrowserHostSendDevToolsMessageRequest.MESSAGE_ID);
            BrowserHostSendDevToolsMessageRequest request =
                    BrowserHostSendDevToolsMessageRequest.DECODER.decode(ByteBuffer.wrap(send.payload));
            JsonNode wireJson = JSON.readTree(request.message());
            assertThat(wireJson.get("method").asText()).isEqualTo("Runtime.evaluate");
            assertThat(wireJson.get("params").get("expression").asText()).isEqualTo("6 * 7");
            int commandId = wireJson.get("id").asInt();
            peer.respond(send, new BrowserHostSendDevToolsMessageResponse(1));
            peer.event(new DevToolsMessageEvent(
                    browser,
                    ("{\"id\":" + commandId + ",\"result\":{\"answer\":42}}").getBytes(StandardCharsets.UTF_8)));
            Map<String, Object> response = command.get(2, TimeUnit.SECONDS);
            assertThat(((Number) Objects.requireNonNull(response.get("answer"))).intValue())
                    .isEqualTo(42);

            CdpClient typed = new CdpClient(devTools, new JacksonCdpCodec());
            var typedCommand = typed.domains().runtime().evaluate("document.title");
            Frame typedSend = peer.receive();
            int typedId = commandId(typedSend);
            peer.respond(typedSend, new BrowserHostSendDevToolsMessageResponse(1));
            peer.event(new DevToolsMessageEvent(
                    browser,
                    ("{\"id\":" + typedId + ",\"result\":{\"result\":{\"type\":\"string\",\"value\":\"cef4j\"}}}")
                            .getBytes(StandardCharsets.UTF_8)));
            assertThat(typedCommand
                            .toCompletableFuture()
                            .get(2, TimeUnit.SECONDS)
                            .result()
                            .value())
                    .isEqualTo(Optional.of("cef4j"));

            BlockingQueue<Map<String, Object>> consoleEvents = new LinkedBlockingQueue<>();
            devTools.on("Runtime.consoleAPICalled", consoleEvents::offer);
            peer.event(new DevToolsMessageEvent(
                    browser,
                    "{\"method\":\"Runtime.consoleAPICalled\",\"params\":{\"type\":\"log\"}}"
                            .getBytes(StandardCharsets.UTF_8)));
            assertThat(consoleEvents.poll(2, TimeUnit.SECONDS).get("type")).isEqualTo("log");

            CompletableFuture<Void> closing = devTools.closeAsync().toCompletableFuture();
            Frame detach = peer.receive();
            assertThat(detach.messageId).isEqualTo(30);
            assertThat(closing).isNotDone();
            peer.respond(detach, null);
            closing.get(2, TimeUnit.SECONDS);
        }
    }

    @Test
    void reportsCdpErrorsAndFailsPendingCallsWhenAgentDetaches() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSessionImpl session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                Peer peer = new Peer(pair.b)) {
            RemoteHandle browser = new RemoteHandle(31);
            var attaching = DevToolsSession.attach(
                    session, browser, new BrowserHost(session, new RemoteHandle(32)), new JacksonCdpCodec());
            peer.respond(peer.receive(), null);
            DevToolsSession devTools = attaching.get(2, TimeUnit.SECONDS);

            var failed = devTools.send("No.suchMethod", null);
            Frame failedSend = peer.receive();
            int failedId = commandId(failedSend);
            peer.respond(failedSend, new BrowserHostSendDevToolsMessageResponse(1));
            peer.event(new DevToolsMessageEvent(
                    browser,
                    ("{\"id\":" + failedId + ",\"error\":{\"code\":-32601,\"message\":\"unknown method\"}}")
                            .getBytes(StandardCharsets.UTF_8)));
            assertThatThrownBy(() -> failed.get(2, TimeUnit.SECONDS))
                    .hasRootCauseInstanceOf(CdpException.class)
                    .hasRootCauseMessage("unknown method");

            var pending = devTools.send("Page.captureScreenshot", null);
            Frame pendingSend = peer.receive();
            peer.respond(pendingSend, new BrowserHostSendDevToolsMessageResponse(1));
            peer.event(new DevToolsAgentDetachedEvent(browser));
            assertThatThrownBy(() -> pending.get(2, TimeUnit.SECONDS))
                    .hasRootCauseInstanceOf(IllegalStateException.class)
                    .hasRootCauseMessage("DevTools agent detached");
        }
    }

    private static int commandId(Frame frame) {
        BrowserHostSendDevToolsMessageRequest request =
                BrowserHostSendDevToolsMessageRequest.DECODER.decode(ByteBuffer.wrap(frame.payload));
        try {
            return JSON.readTree(request.message()).get("id").asInt();
        } catch (java.io.IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static final class Peer implements AutoCloseable {
        private final CefTransport transport;
        private final BlockingQueue<Frame> frames = new LinkedBlockingQueue<>();

        private Peer(CefTransport transport) {
            this.transport = transport;
            transport.onReceive(buffer -> {
                ByteBuffer copy = buffer.duplicate();
                Envelope.Header header = Envelope.readHeader(copy);
                byte[] payload = new byte[copy.remaining()];
                copy.get(payload);
                frames.offer(new Frame(header.corrId, header.messageId, payload));
            });
        }

        private Frame receive() throws InterruptedException {
            Frame frame = frames.poll(2, TimeUnit.SECONDS);
            if (frame == null) throw new AssertionError("timed out waiting for IPC frame");
            return frame;
        }

        private void respond(Frame request, @Nullable CefMessageEncoder response) throws CefTransportException {
            send(Envelope.Kind.RESPONSE, request.corrId, request.messageId, response);
        }

        private void event(CefMessageEncoder event) throws CefTransportException {
            send(Envelope.Kind.EVENT, Envelope.NO_CORR_ID, event.messageId(), event);
        }

        private void send(Envelope.Kind kind, int corrId, int messageId, @Nullable CefMessageEncoder message)
                throws CefTransportException {
            int payloadSize = message == null ? 0 : message.encodedSize();
            ByteBuffer frame =
                    ByteBuffer.allocate(Envelope.HEADER_SIZE + payloadSize).order(ByteOrder.LITTLE_ENDIAN);
            Envelope.writeHeader(frame, kind, 0, corrId, messageId, payloadSize);
            if (message != null) message.encodeInto(frame);
            frame.flip();
            transport.send(frame);
        }

        @Override
        public void close() {
            transport.close();
        }
    }

    private static final class Frame {
        private final int corrId;
        private final int messageId;
        private final byte[] payload;

        private Frame(int corrId, int messageId, byte[] payload) {
            this.corrId = corrId;
            this.messageId = messageId;
            this.payload = payload;
        }
    }
}
