package net.kurobako.cef4j.ipc.protocol.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.session.Envelope;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public final class StubRuntimeServerMain {

    public static void main(String[] args) {
        String bind = parseBindArg(args);
        String transport = parseOption(args, "--transport", "zmq");
        String frameTransport = parseOption(args, "--frame-transport", "mmap");
        AtomicBoolean running = new AtomicBoolean(true);
        Thread control = new Thread(() -> awaitShutdownCommand(running), "stub-runtime-control");
        control.setDaemon(true);
        control.start();
        try (ZContext ctx = new ZContext()) {
            ZMQ.Socket sock = ctx.createSocket(SocketType.DEALER);
            sock.setLinger(0);
            sock.setReceiveTimeOut(200);
            sock.bind(bind);
            String resolved = sock.getLastEndpoint();
            System.out.println("CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=14600 transport=" + transport
                    + " frame=" + frameTransport + " endpoint=" + resolved
                    + " capabilities=remote-cef-api,graceful-shutdown");
            System.out.flush();

            while (running.get() && !Thread.currentThread().isInterrupted()) {
                byte[] frame = sock.recv(0);
                if (frame == null) continue;
                handleFrame(sock, frame);
            }
        }
    }

    private static void awaitShutdownCommand(AtomicBoolean running) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.US_ASCII))) {
            if ("CEF4J_SHUTDOWN".equals(reader.readLine())) running.set(false);
        } catch (java.io.IOException ignored) {
            return;
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

        ByteBuffer ack = ByteBuffer.allocate(Envelope.HEADER_SIZE).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(ack, Envelope.Kind.RESPONSE, 0, h.corrId, h.messageId, 0);
        ack.flip();
        sock.send(toArray(ack), 0);

        LifeSpanHandlerOnAfterCreatedEvent ev = new LifeSpanHandlerOnAfterCreatedEvent(new RemoteHandle(42));
        ByteBuffer evBuf =
                ByteBuffer.allocate(Envelope.HEADER_SIZE + ev.encodedSize()).order(ByteOrder.LITTLE_ENDIAN);
        Envelope.writeHeader(
                evBuf,
                Envelope.Kind.EVENT,
                0,
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
