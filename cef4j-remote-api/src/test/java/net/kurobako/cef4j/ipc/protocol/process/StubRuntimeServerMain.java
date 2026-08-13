package net.kurobako.cef4j.ipc.protocol.process;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.ReleaseHandleRequest;
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Pure-Java stand-in for the C++ runtime server, used by {@link RuntimeServerProcessTest}. It binds a PAIR socket,
 * publishes the versioned runtime-server handshake, and implements a tiny conversation:
 *
 * <ul>
 *   <li>{@link ReleaseHandleRequest} REQUEST → empty RESPONSE (ack), then a synthetic
 *       {@link LifeSpanHandlerOnAfterCreatedEvent} echoing back a handle id of 42.
 * </ul>
 *
 * Used by tests that verify runtime-server bootstrap and request/event roundtrip without needing CEF. Exits when the
 * peer disconnects or on SIGTERM.
 */
public final class StubRuntimeServerMain {

    public static void main(String[] args) {
        String bind = parseBindArg(args);
        String transport = parseOption(args, "--transport", "zmq");
        String frameTransport = parseOption(args, "--frame-transport", "mmap");
        try (ZContext ctx = new ZContext()) {
            ZMQ.Socket sock = ctx.createSocket(SocketType.PAIR);
            sock.setLinger(0);
            sock.setReceiveTimeOut(200); // poll-like loop so we can notice ctx close / interrupts
            sock.bind(bind);
            String resolved = sock.getLastEndpoint();
            System.out.println("CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=14600 transport=" + transport
                    + " frame=" + frameTransport + " endpoint=" + resolved + " capabilities=remote-cef-api");
            System.out.flush();

            while (!Thread.currentThread().isInterrupted()) {
                byte[] frame = sock.recv(0);
                if (frame == null) continue; // timeout
                handleFrame(sock, frame);
            }
        }
    }

    private static String parseBindArg(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if ("--bind".equals(args[i])) return args[i + 1];
        }
        throw new IllegalArgumentException("missing --bind <endpoint>");
    }

    private static String parseOption(String[] args, String name, String fallback) {
        for (int i = 0; i < args.length - 1; i++) {
            if (name.equals(args[i])) return args[i + 1];
        }
        return fallback;
    }

    private static void handleFrame(ZMQ.Socket sock, byte[] frame) {
        ByteBuffer buf = ByteBuffer.wrap(frame);
        Envelope.Header h = Envelope.readHeader(buf);
        if (h.kind != Envelope.Kind.REQUEST) return;

        // 1) Send empty RESPONSE so the JVM's request future resolves.
        ByteBuffer ack = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(ack, Envelope.Kind.RESPONSE, /*flags*/ 0, h.corrId, h.messageId, /*payloadLen*/ 0);
        ack.flip();
        sock.send(toArray(ack), 0);

        // 2) Always fire a synthetic LifeSpanHandlerOnAfterCreatedEvent so the JVM event handler sees something.
        LifeSpanHandlerOnAfterCreatedEvent ev = new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(42));
        ByteBuffer evBuf =
                ByteBuffer.allocate(Envelope.HEADER_SIZE + ev.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(
                evBuf,
                Envelope.Kind.EVENT,
                /*flags*/ 0,
                Envelope.NO_CORR_ID,
                LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID,
                ev.encodedSize());
        ev.encodeInto(evBuf);
        evBuf.flip();
        sock.send(toArray(evBuf), 0);
    }

    private static byte[] toArray(ByteBuffer buf) {
        byte[] copy = new byte[buf.remaining()];
        buf.get(copy);
        return copy;
    }

    private StubRuntimeServerMain() {}
}
