package net.kurobako.cef4j.test.contract;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpCodec;
import net.kurobako.cef4j.cdp.CdpException;
import net.kurobako.cef4j.ipc.devtools.DevToolsSession;
import net.kurobako.cef4j.ipc.protocol.gen.BrowserHost;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsAgentDetachedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.DevToolsMessageEvent;
import net.kurobako.cef4j.ipc.session.CefMessageDecoder;
import net.kurobako.cef4j.ipc.session.CefMessageEncoder;
import net.kurobako.cef4j.ipc.session.CefMessageView;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.ipc.transport.CefTransportException;
import net.kurobako.cef4j.ipc.transport.LoopbackTransport;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("baumkuchen:auto-closeable-resource")
public abstract class DevToolsSessionContract {

    private static boolean hasSendDevToolsMessage = false;

    @BeforeAll
    static void checkAvailable() {
        try {
            Class.forName("net.kurobako.cef4j.ipc.protocol.gen.BrowserHostSendDevToolsMessageRequest");
            hasSendDevToolsMessage = true;
        } catch (ClassNotFoundException ignored) {
            hasSendDevToolsMessage = false;
        }
    }

    protected abstract CdpCodec codec();

    @Test
    final void correlatesCommandsAndEventsOverTransportNeutralSession() throws Exception {
        Assumptions.assumeTrue(hasSendDevToolsMessage, "sendDevToolsMessage not available in this CEF version");
        final LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSessionImpl session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                Peer peer = new Peer(pair.b)) {
            final RemoteHandle browser = new RemoteHandle(11);
            final BrowserHost host = new BrowserHost(session, new RemoteHandle(22));

            final var attaching = DevToolsSession.attach(session, browser, host, codec());
            final Frame attach = peer.receive();
            assertThat(attach.messageId).isEqualTo(27);
            peer.respond(attach, null);
            final DevToolsSession devTools = attaching.get(2, TimeUnit.SECONDS);

            final Map<String, Object> params = Map.of("expression", "6 * 7");
            final var command = devTools.send("Runtime.evaluate", params);
            final Frame send = peer.receive();
            assertThat(send.messageId).isEqualTo(sendDevToolsMessageId());
            final Map<?, ?> wireJson =
                    (Map<?, ?>) codec().decode(requestMessage(send)).orElseThrow();
            assertThat(wireJson.get("method")).isEqualTo("Runtime.evaluate");
            assertThat(((Map<?, ?>) Objects.requireNonNull(wireJson.get("params"))).get("expression"))
                    .isEqualTo("6 * 7");
            final int commandId = ((Number) Objects.requireNonNull(wireJson.get("id"))).intValue();
            peer.respond(send, successfulSendResponse());
            peer.event(new DevToolsMessageEvent(
                    browser,
                    ("{\"id\":" + commandId + ",\"result\":{\"answer\":42}}").getBytes(StandardCharsets.UTF_8)));
            final Map<String, Object> response = command.get(2, TimeUnit.SECONDS);
            assertThat(((Number) Objects.requireNonNull(response.get("answer"))).intValue())
                    .isEqualTo(42);

            final CdpClient typed = new CdpClient(devTools, codec());
            final var typedCommand = typed.domains().runtime().evaluate("document.title");
            respondWithEvent(peer, browser, "\"result\":{\"result\":{\"type\":\"string\",\"value\":\"cef4j\"}}");
            assertThat(typedCommand
                            .toCompletableFuture()
                            .get(2, TimeUnit.SECONDS)
                            .result()
                            .value())
                    .isEqualTo(Optional.of("cef4j"));

            final BlockingQueue<Map<String, Object>> consoleEvents = new LinkedBlockingQueue<>();
            devTools.on("Runtime.consoleAPICalled", event -> {
                if (!consoleEvents.offer(event)) throw new IllegalStateException("console event queue full");
            });
            peer.event(new DevToolsMessageEvent(
                    browser,
                    "{\"method\":\"Runtime.consoleAPICalled\",\"params\":{\"type\":\"log\"}}"
                            .getBytes(StandardCharsets.UTF_8)));
            final Map<String, Object> event = consoleEvents.poll(2, TimeUnit.SECONDS);
            assertThat(event).isNotNull();
            assertThat(event.get("type")).isEqualTo("log");

            final CompletableFuture<Void> closing = devTools.closeAsync().toCompletableFuture();
            final Frame detach = peer.receive();
            assertThat(detach.messageId).isEqualTo(30);
            assertThat(closing).isNotDone();
            peer.respond(detach, null);
            closing.get(2, TimeUnit.SECONDS);
        }
    }

    @Test
    final void reportsCdpErrorsAndFailsPendingCallsWhenAgentDetaches() throws Exception {
        Assumptions.assumeTrue(hasSendDevToolsMessage, "sendDevToolsMessage not available in this CEF version");
        final LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSessionImpl session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                Peer peer = new Peer(pair.b)) {
            final RemoteHandle browser = new RemoteHandle(31);
            final var attaching =
                    DevToolsSession.attach(session, browser, new BrowserHost(session, new RemoteHandle(32)), codec());
            peer.respond(peer.receive(), null);
            final DevToolsSession devTools = attaching.get(2, TimeUnit.SECONDS);

            final var failed = devTools.send("No.suchMethod", null);
            respondWithEvent(peer, browser, "\"error\":{\"code\":-32601,\"message\":\"unknown method\"}");
            assertThatThrownBy(() -> failed.get(2, TimeUnit.SECONDS))
                    .hasRootCauseInstanceOf(CdpException.class)
                    .hasRootCauseMessage("unknown method");

            final var pending = devTools.send("Page.captureScreenshot", null);
            final Frame pendingSend = peer.receive();
            peer.respond(pendingSend, successfulSendResponse());
            peer.event(new DevToolsAgentDetachedEvent(browser));
            assertThatThrownBy(() -> pending.get(2, TimeUnit.SECONDS))
                    .hasRootCauseInstanceOf(IllegalStateException.class)
                    .hasRootCauseMessage("DevTools agent detached");
        }
    }

    @Test
    @SuppressWarnings("try")
    final void transportDisconnectFailsPendingCdpCalls() throws Exception {
        Assumptions.assumeTrue(hasSendDevToolsMessage, "sendDevToolsMessage not available in this CEF version");
        final LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSessionImpl session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                Peer peer = new Peer(pair.b)) {
            final RemoteHandle browser = new RemoteHandle(41);
            final var attaching =
                    DevToolsSession.attach(session, browser, new BrowserHost(session, new RemoteHandle(42)), codec());
            peer.respond(peer.receive(), null);
            final DevToolsSession devTools = attaching.get(2, TimeUnit.SECONDS);
            final var pending = devTools.send("Page.captureScreenshot", null);
            peer.respond(peer.receive(), successfulSendResponse());
            peer.close();
            assertThatThrownBy(() -> pending.get(2, TimeUnit.SECONDS)).satisfies(failure -> {
                final Throwable cause = Objects.requireNonNull(failure.getCause());
                assertThat(cause).isInstanceOfAny(IllegalStateException.class, CefTransportException.class);
                assertThat(cause.getMessage()).isIn("CEF session closed", "transport disconnected");
            });
        }
    }

    @Test
    final void cancelledCommandDoesNotConsumeALaterResponse() throws Exception {
        Assumptions.assumeTrue(hasSendDevToolsMessage, "sendDevToolsMessage not available in this CEF version");
        final LoopbackTransport.Pair pair = LoopbackTransport.create();
        try (CefSessionImpl session = new CefSessionImpl(pair.a, Duration.ofSeconds(2));
                Peer peer = new Peer(pair.b)) {
            final RemoteHandle browser = new RemoteHandle(51);
            final var attaching =
                    DevToolsSession.attach(session, browser, new BrowserHost(session, new RemoteHandle(52)), codec());
            peer.respond(peer.receive(), null);
            final DevToolsSession devTools = attaching.get(2, TimeUnit.SECONDS);

            final CompletableFuture<Map<String, Object>> cancelled = devTools.send("Runtime.evaluate", null);
            final Frame firstSend = peer.receive();
            final int firstId = commandId(firstSend);
            peer.respond(firstSend, successfulSendResponse());
            cancelled.cancel(false);
            peer.event(new DevToolsMessageEvent(
                    browser,
                    ("{\"id\":" + firstId + ",\"result\":{\"stale\":true}}").getBytes(StandardCharsets.UTF_8)));

            final CompletableFuture<Map<String, Object>> next = devTools.send("Runtime.evaluate", null);
            respondWithEvent(peer, browser, "\"result\":{\"answer\":42}");

            assertThat(cancelled).isCancelled();
            assertThat(((Number) Objects.requireNonNull(
                                    next.get(2, TimeUnit.SECONDS).get("answer")))
                            .intValue())
                    .isEqualTo(42);
        }
    }

    /** Answers the command the peer receives, and delivers the event that carries the given result of it. */
    private void respondWithEvent(Peer peer, RemoteHandle browser, String result) throws Exception {
        final Frame send = peer.receive();
        final int id = commandId(send);
        peer.respond(send, successfulSendResponse());
        peer.event(new DevToolsMessageEvent(
                browser, ("{\"id\":" + id + "," + result + "}").getBytes(StandardCharsets.UTF_8)));
    }

    private int commandId(Frame frame) throws ReflectiveOperationException {
        final Map<?, ?> message =
                (Map<?, ?>) codec().decode(requestMessage(frame)).orElseThrow();
        return ((Number) Objects.requireNonNull(message.get("id"))).intValue();
    }

    private static int sendDevToolsMessageId() throws ReflectiveOperationException {
        return sendRequestClass().getField("MESSAGE_ID").getInt(null);
    }

    private static byte[] requestMessage(Frame frame) throws ReflectiveOperationException {
        @SuppressWarnings("unchecked")
        final CefMessageDecoder<CefMessageView> decoder = (CefMessageDecoder<CefMessageView>)
                sendRequestClass().getField("DECODER").get(null);
        final Object request = decoder.decode(ByteBuffer.wrap(frame.payload));
        return (byte[]) request.getClass().getMethod("message").invoke(request);
    }

    private static CefMessageEncoder successfulSendResponse() throws ReflectiveOperationException {
        final Class<?> type =
                Class.forName("net.kurobako.cef4j.ipc.protocol.gen.BrowserHostSendDevToolsMessageResponse");
        return (CefMessageEncoder) type.getConstructor(int.class).newInstance(1);
    }

    private static Class<?> sendRequestClass() throws ClassNotFoundException {
        return Class.forName("net.kurobako.cef4j.ipc.protocol.gen.BrowserHostSendDevToolsMessageRequest");
    }

    private static final class Peer implements AutoCloseable {
        private final CefTransport transport;
        private final BlockingQueue<Frame> frames = new LinkedBlockingQueue<>();

        private Peer(CefTransport transport) {
            this.transport = transport;
            transport.onReceive(buffer -> {
                final ByteBuffer copy = buffer.duplicate();
                final Envelope.Header header = Envelope.readHeader(copy);
                final byte[] payload = new byte[copy.remaining()];
                copy.get(payload);
                frames.add(new Frame(header.corrId, header.messageId, payload));
            });
        }

        private Frame receive() throws InterruptedException {
            final Frame frame = frames.poll(2, TimeUnit.SECONDS);
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
            final int payloadSize = message == null ? 0 : message.encodedSize();
            final ByteBuffer frame =
                    ByteBuffer.allocate(Envelope.HEADER_SIZE + payloadSize).order(ByteOrder.LITTLE_ENDIAN);
            Envelope.writeHeader(frame, new Envelope.Header(payloadSize, kind, 0, corrId, messageId));
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
