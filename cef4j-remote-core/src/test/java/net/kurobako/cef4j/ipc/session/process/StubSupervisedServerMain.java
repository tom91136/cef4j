package net.kurobako.cef4j.ipc.session.process;

import java.nio.file.Files;
import java.nio.file.Path;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/** Minimal process used to verify crash/restart supervision without loading CEF. */
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
            String drop = System.getenv("CEF4J_STUB_DROP_AFTER_MS");
            if (drop != null) {
                // Start the fault timer only after the client has completed ZMTP and sent its
                // runtime-session-ready envelope. Otherwise slow Windows runners can close the
                // socket before it was ever a live transport, which tests startup rather than recovery.
                long readyDeadline = System.nanoTime() + java.util.concurrent.TimeUnit.SECONDS.toNanos(10);
                while (socket.recv(0) == null && System.nanoTime() < readyDeadline) {
                    // recv uses the bounded timeout configured above
                }
                Thread.sleep(Long.parseLong(drop));
                socket.close();
                Thread.sleep(30_000);
            } else {
                while (!Thread.currentThread().isInterrupted()) socket.recv(0);
            }
        }
    }

    private static String option(String[] args, String name, String fallback) {
        for (int i = 0; i + 1 < args.length; i++) if (name.equals(args[i])) return args[i + 1];
        return fallback;
    }

    private StubSupervisedServerMain() {}
}
