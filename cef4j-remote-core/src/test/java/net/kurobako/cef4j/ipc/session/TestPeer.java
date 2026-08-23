package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import net.kurobako.cef4j.ipc.transport.CefTransportException;

/**
 * Test-side counterpart to a {@link net.kurobako.cef4j.ipc.session.CefSession}. Wraps a {@link CefTransport} (typically
 * the other end of a {@code LoopbackTransport.Pair}) and exposes envelope-aware send helpers plus a queue of received
 * frames so the test can drive the server-side conversation explicitly.
 */
final class TestPeer implements AutoCloseable {

    final CefTransport transport;
    private final BlockingQueue<DecodedFrame> received = new LinkedBlockingQueue<>();

    TestPeer(CefTransport transport) {
        this.transport = transport;
        transport.onReceive(buf -> {
            ByteBuffer view = buf.duplicate();
            Envelope.Header h = Envelope.readHeader(view);
            byte[] payload = new byte[view.remaining()];
            view.get(payload);
            received.offer(new DecodedFrame(h, payload));
        });
    }

    void sendResponse(int corrId, int messageId, byte[] payload) throws CefTransportException {
        sendKind(Envelope.Kind.RESPONSE, corrId, messageId, payload);
    }

    void sendEvent(int messageId, byte[] payload) throws CefTransportException {
        sendKind(Envelope.Kind.EVENT, Envelope.NO_CORR_ID, messageId, payload);
    }

    void sendIntercept(int corrId, int messageId, byte[] payload) throws CefTransportException {
        sendKind(Envelope.Kind.INTERCEPT, corrId, messageId, payload);
    }

    void sendError(int corrId, int messageId, byte[] payload) throws CefTransportException {
        sendKind(Envelope.Kind.ERROR, corrId, messageId, payload);
    }

    @Nullable
    DecodedFrame poll(long timeout, TimeUnit unit) throws InterruptedException {
        return received.poll(timeout, unit);
    }

    int receivedCount() {
        return received.size();
    }

    private void sendKind(Envelope.Kind k, int corrId, int messageId, byte[] payload) throws CefTransportException {
        ByteBuffer buf =
                ByteBuffer.allocate(Envelope.HEADER_SIZE + payload.length).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(buf, k, /*flags*/ 0, corrId, messageId, payload.length);
        buf.put(payload);
        buf.flip();
        transport.send(buf);
    }

    @Override
    public void close() {
        transport.close();
    }

    static final class DecodedFrame {
        final Envelope.Header header;
        final byte[] payload;

        DecodedFrame(Envelope.Header header, byte[] payload) {
            this.header = header;
            this.payload = payload;
        }
    }
}
