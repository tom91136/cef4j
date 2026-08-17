package net.kurobako.cef4j.ipc.devtools.gson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.gson.GsonCdpCodec;
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
    @Test
    void installsRemoteFactoryProvider() {
        assertThat(ServiceLoader.load(RemoteDevToolsSessionFactory.class))
                .anyMatch(factory -> factory instanceof GsonRemoteDevToolsSessionFactory);
    }

    @Test
    void correlatesCommandsAndEventsOverTransportNeutralSession() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSessionImpl session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                Peer peer = new Peer(pair.b)) {
            RemoteHandle browser = new RemoteHandle(11);
            BrowserHost host = new BrowserHost(session, new RemoteHandle(22));

            var attaching = DevToolsSession.attach(session, browser, host);
            Frame attach = peer.receive();
            assertThat(attach.messageId).isEqualTo(27);
            peer.respond(attach, null);
            DevToolsSession devTools = attaching.get(2, TimeUnit.SECONDS);

            JsonObject params = new JsonObject();
            params.addProperty("expression", "6 * 7");
            var command = devTools.send("Runtime.evaluate", params);
            Frame send = peer.receive();
            assertThat(send.messageId).isEqualTo(BrowserHostSendDevToolsMessageRequest.MESSAGE_ID);
            BrowserHostSendDevToolsMessageRequest request =
                    BrowserHostSendDevToolsMessageRequest.DECODER.decode(ByteBuffer.wrap(send.payload));
            JsonObject wireJson = JsonParser.parseString(new String(request.message(), StandardCharsets.UTF_8))
                    .getAsJsonObject();
            assertThat(wireJson.get("method").getAsString()).isEqualTo("Runtime.evaluate");
            assertThat(wireJson.getAsJsonObject("params").get("expression").getAsString())
                    .isEqualTo("6 * 7");
            int commandId = wireJson.get("id").getAsInt();
            peer.respond(send, new BrowserHostSendDevToolsMessageResponse(1));
            peer.event(new DevToolsMessageEvent(
                    browser,
                    ("{\"id\":" + commandId + ",\"result\":{\"answer\":42}}").getBytes(StandardCharsets.UTF_8)));
            assertThat(command.get(2, TimeUnit.SECONDS).get("answer").getAsInt())
                    .isEqualTo(42);

            CdpClient typed = new CdpClient(devTools, new GsonCdpCodec());
            var typedCommand = typed.domains()
                    .runtime()
                    .evaluate(net.kurobako.cef4j.cdp.generated.Runtime.EvaluateParams.builder()
                            .expression("document.title")
                            .returnByValue(true)
                            .build());
            Frame typedSend = peer.receive();
            int typedId = commandId(typedSend);
            peer.respond(typedSend, new BrowserHostSendDevToolsMessageResponse(1));
            peer.event(new DevToolsMessageEvent(
                    browser,
                    ("{\"id\":" + typedId + ",\"result\":{\"result\":{\"type\":\"string\",\"value\":\"cef4j\"}}}")
                            .getBytes(StandardCharsets.UTF_8)));
            assertThat(Objects.requireNonNull(typedCommand
                                    .toCompletableFuture()
                                    .get(2, TimeUnit.SECONDS)
                                    .result())
                            .value())
                    .isEqualTo("cef4j");

            BlockingQueue<JsonObject> consoleEvents = new LinkedBlockingQueue<>();
            devTools.on("Runtime.consoleAPICalled", consoleEvents::offer);
            peer.event(new DevToolsMessageEvent(
                    browser,
                    "{\"method\":\"Runtime.consoleAPICalled\",\"params\":{\"type\":\"log\"}}"
                            .getBytes(StandardCharsets.UTF_8)));
            assertThat(consoleEvents.poll(2, TimeUnit.SECONDS).get("type").getAsString())
                    .isEqualTo("log");

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
            var attaching = DevToolsSession.attach(session, browser, new BrowserHost(session, new RemoteHandle(32)));
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

    @Test
    @SuppressWarnings("try") // Explicit close simulates an unexpected transport disconnect.
    void transportDisconnectFailsPendingCdpCalls() throws Exception {
        LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSessionImpl session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                Peer peer = new Peer(pair.b)) {
            RemoteHandle browser = new RemoteHandle(41);
            var attaching = DevToolsSession.attach(session, browser, new BrowserHost(session, new RemoteHandle(42)));
            peer.respond(peer.receive(), null);
            DevToolsSession devTools = attaching.get(2, TimeUnit.SECONDS);
            var pending = devTools.send("Page.captureScreenshot", null);
            peer.respond(peer.receive(), new BrowserHostSendDevToolsMessageResponse(1));
            peer.close();
            // The session request and CDP close listener race deliberately here. Either path may
            // complete the future first, but both must report the transport loss promptly.
            assertThatThrownBy(() -> pending.get(2, TimeUnit.SECONDS)).satisfies(failure -> {
                Throwable cause = Objects.requireNonNull(failure.getCause());
                assertThat(cause).isInstanceOfAny(IllegalStateException.class, CefTransportException.class);
                assertThat(cause.getMessage()).isIn("CEF session closed", "transport disconnected");
            });
        }
    }

    private static int commandId(Frame frame) {
        BrowserHostSendDevToolsMessageRequest request =
                BrowserHostSendDevToolsMessageRequest.DECODER.decode(ByteBuffer.wrap(frame.payload));
        return JsonParser.parseString(new String(request.message(), StandardCharsets.UTF_8))
                .getAsJsonObject()
                .get("id")
                .getAsInt();
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
