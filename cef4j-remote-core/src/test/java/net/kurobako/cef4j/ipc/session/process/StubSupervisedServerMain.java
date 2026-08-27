package net.kurobako.cef4j.ipc.session.process;

import java.nio.file.Files;
import java.nio.file.Path;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public final class StubSupervisedServerMain {
    public static void main(String[] args) throws Exception {
        String pidFile = System.getenv("CEF4J_STUB_PID_FILE");
        if (pidFile != null)
            Files.writeString(
                    Path.of(pidFile), Long.toString(ProcessHandle.current().pid()));
        String bind = option(args, "--bind", "tcp://127.0.0.1:0");
        String transport = option(args, "--transport", "zmq");
        String frame = option(args, "--frame-transport", "shared-file");
        try (ZContext context = new ZContext()) {
            ZMQ.Socket socket = context.createSocket(SocketType.DEALER);
            socket.setLinger(0);
            socket.setReceiveTimeOut(100);
            socket.bind(bind);
            System.out.println("CEF4J_RUNTIME_SERVER protocol=1 api=remote-cef cef-api=14600 transport=" + transport
                    + " frame=" + frame + " endpoint=" + socket.getLastEndpoint()
                    + " capabilities=remote-cef-api");
            System.out.flush();
            byte[] readyRequest = awaitReadyRequest(socket);
            byte[] readyResponse = readyRequest.clone();
            // XXX: Replace byte-level envelope mutation when the shared test stub can depend on the session codec.
            readyResponse[4] = 2;
            socket.send(readyResponse);
            String exit = System.getenv("CEF4J_STUB_EXIT_AFTER_MS");
            if (exit != null) {
                Thread.sleep(Long.parseLong(exit));
                return;
            }
            String drop = System.getenv("CEF4J_STUB_DROP_AFTER_MS");
            if (drop != null) {
                Thread.sleep(Long.parseLong(drop));
                socket.close();
                Thread.sleep(30_000);
            } else {
                while (!Thread.currentThread().isInterrupted()) socket.recv(0);
            }
        }
    }

    private static byte[] awaitReadyRequest(ZMQ.Socket socket) {
        long deadline = System.nanoTime() + java.util.concurrent.TimeUnit.SECONDS.toNanos(10);
        byte[] request;
        while ((request = socket.recv(0)) == null && System.nanoTime() < deadline) {
            // XXX: Replace polling when JeroMQ exposes an interruptible bounded receive primitive.
        }
        if (request == null) throw new IllegalStateException("runtime readiness request timed out");
        if (request.length != 14 || request[4] != 1) {
            throw new IllegalArgumentException("invalid runtime readiness envelope");
        }
        for (int i = 0; i < request.length; i++) {
            if (i != 4 && request[i] != 0) {
                throw new IllegalArgumentException("invalid runtime readiness envelope");
            }
        }
        return request;
    }

    private static String option(String[] args, String name, String fallback) {
        for (int i = 0; i + 1 < args.length; i++) if (name.equals(args[i])) return args[i + 1];
        return fallback;
    }

    private StubSupervisedServerMain() {}
}
