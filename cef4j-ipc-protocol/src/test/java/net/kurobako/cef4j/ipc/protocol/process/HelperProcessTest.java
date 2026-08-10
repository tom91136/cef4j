package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.ReleaseHandleRequest;
import net.kurobako.cef4j.ipc.protocol.gen.ReleaseHandleResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.HelperProcess;
import net.kurobako.cef4j.ipc.transport.ZmqTransport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(30)
class HelperProcessTest {

    /**
     * Writes a tiny launcher script that runs {@link StubHelperMain} with the test's classpath. We use a script (vs.
     * passing {@code java} + args directly to {@link HelperProcess#spawn}) so HelperProcess only needs a path-to-binary
     * argument - the same shape it will eventually take for the real C++ helper.
     */
    private static Path writeLauncherScript(Path dir) throws IOException {
        boolean isWindows =
                System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
        Path javaHome = Path.of(System.getProperty("java.home"));
        Path javaExe = javaHome.resolve("bin").resolve(isWindows ? "java.exe" : "java");
        String classpath = System.getProperty("java.class.path");
        String mainClass = StubHelperMain.class.getName();

        Path script;
        if (isWindows) {
            script = dir.resolve("stub-helper.bat");
            String content = "@echo off\r\n\""
                    + javaExe.toString().replace("/", "\\")
                    + "\" -cp \""
                    + classpath
                    + "\" "
                    + mainClass
                    + " %*\r\n";
            Files.writeString(script, content);
        } else {
            script = dir.resolve("stub-helper.sh");
            String content = "#!/bin/sh\nexec \"" + javaExe + "\" -cp \"" + classpath + "\" " + mainClass + " \"$@\"\n";
            Files.writeString(script, content);
            script.toFile().setExecutable(true);
        }
        return script;
    }

    @Test
    void spawnsAndReceivesEndpoint(@TempDir Path tmp) throws Exception {
        Path script = writeLauncherScript(tmp);
        try (HelperProcess helper = HelperProcess.spawn(script, "tcp://127.0.0.1:0")) {
            assertThat(helper.endpoint()).startsWith("tcp://127.0.0.1:");
            assertThat(helper.isAlive()).isTrue();
            assertThat(helper.pid()).isPositive();
        }
    }

    @Test
    void closeTerminatesHelperProcess(@TempDir Path tmp) throws Exception {
        Path script = writeLauncherScript(tmp);
        HelperProcess helper = HelperProcess.spawn(script, "tcp://127.0.0.1:0");
        long pid = helper.pid();
        assertThat(helper.isAlive()).isTrue();
        helper.close();
        assertThat(helper.isAlive())
                .as("helper pid=%d should not be alive after close()", pid)
                .isFalse();
    }

    @Test
    void roundTripRequestAndEventThroughHelperAndSession(@TempDir Path tmp) throws Exception {
        // Validates the full helper bootstrap + session conversation: spawn process, parse ENDPOINT, request →
        // ack, event delivery. The stub helper acks any request with an empty RESPONSE and unconditionally
        // emits a LifeSpanHandlerOnAfterCreatedEvent with handle=42.
        Path script = writeLauncherScript(tmp);
        try (HelperProcess helper = HelperProcess.spawn(script, "tcp://127.0.0.1:0");
                ZmqTransport transport = ZmqTransport.connect(helper.endpoint());
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(5))) {

            CountDownLatch sawEvent = new CountDownLatch(1);
            int[] capturedHandle = {-1};
            session.on(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        capturedHandle[0] = ev.browser().id();
                        sawEvent.countDown();
                    });

            ReleaseHandleResponse ack = session.request(
                            new ReleaseHandleRequest(new RemoteHandle(99), "cef_browser_t"),
                            ReleaseHandleResponse.DECODER)
                    .get(5, TimeUnit.SECONDS);
            assertThat(ack).isNotNull();

            assertThat(sawEvent.await(5, TimeUnit.SECONDS))
                    .as("BrowserCreatedEvent should arrive within 5s")
                    .isTrue();
            assertThat(capturedHandle[0]).isEqualTo(42);
        }
    }

    @Test
    void spawnFailsIfBinaryDoesNotExist(@TempDir Path tmp) {
        Path missing = tmp.resolve("does-not-exist");
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> HelperProcess.spawn(missing, "tcp://127.0.0.1:0"))
                .isInstanceOf(IOException.class);
    }
}
