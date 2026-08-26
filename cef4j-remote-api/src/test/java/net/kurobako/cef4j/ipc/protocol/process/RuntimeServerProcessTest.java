package net.kurobako.cef4j.ipc.protocol.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import net.kurobako.cef4j.ipc.protocol.gen.LifeSpanHandlerOnAfterCreatedEvent;
import net.kurobako.cef4j.ipc.protocol.gen.ReleaseHandleRequest;
import net.kurobako.cef4j.ipc.protocol.gen.ReleaseHandleResponse;
import net.kurobako.cef4j.ipc.session.CefSession;
import net.kurobako.cef4j.ipc.session.CefSessionImpl;
import net.kurobako.cef4j.ipc.session.RemoteHandle;
import net.kurobako.cef4j.ipc.session.process.RuntimeServerProcess;
import net.kurobako.cef4j.ipc.transport.CefTransport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

@Timeout(600)
class RuntimeServerProcessTest {

    /**
     * Writes a tiny launcher script that runs {@link StubRuntimeServerMain} with the test's classpath. We use a script
     * (vs. passing {@code java} + args directly to {@link RuntimeServerProcess#spawn}) so RuntimeServerProcess only
     * needs a path-to-binary argument - the same shape it will eventually take for the real C++ server.
     */
    private static Path writeLauncherScript(Path dir) throws IOException {
        return writeLauncherScript(dir, true);
    }

    private static Path writeLauncherScript(Path dir, boolean replaceLauncherProcess) throws IOException {
        boolean isWindows =
                System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
        Path javaHome = Path.of(System.getProperty("java.home"));
        Path javaExe = javaHome.resolve("bin").resolve(isWindows ? "java.exe" : "java");
        String classpath = System.getProperty("java.class.path");
        String mainClass = StubRuntimeServerMain.class.getName();

        Path script;
        if (isWindows) {
            script = dir.resolve("stub-runtime-server.bat");
            String content = "@echo off\r\n\""
                    + javaExe.toString().replace("/", "\\")
                    + "\" -cp \""
                    + classpath
                    + "\" "
                    + mainClass
                    + " %*\r\n";
            Files.writeString(script, content);
        } else {
            script = dir.resolve("stub-runtime-server.sh");
            String command = (replaceLauncherProcess ? "exec " : "") + "\"" + javaExe + "\"";
            String content = "#!/bin/sh\n" + command + " -cp \"" + classpath + "\" " + mainClass + " \"$@\"\n";
            Files.writeString(script, content);
            script.toFile().setExecutable(true);
        }
        return script;
    }

    @Test
    void spawnsAndReceivesEndpoint(@TempDir Path tmp) throws Exception {
        Path script = writeLauncherScript(tmp);
        try (RuntimeServerProcess server = RuntimeServerProcess.spawn(script, "tcp://127.0.0.1:0")) {
            assertThat(server.endpoint()).startsWith("tcp://127.0.0.1:");
            assertThat(server.frameTransport()).isEqualTo("shared-file");
            assertThat(server.isAlive()).isTrue();
            assertThat(server.pid()).isPositive();
        }
    }

    @Test
    void closeTerminatesRuntimeServerProcess(@TempDir Path tmp) throws Exception {
        Path script = writeLauncherScript(tmp);
        RuntimeServerProcess server = RuntimeServerProcess.spawn(script, "tcp://127.0.0.1:0");
        long pid = server.pid();
        assertThat(server.isAlive()).isTrue();
        server.close();
        assertThat(server.isAlive())
                .as("server pid=%d should not be alive after close()", pid)
                .isFalse();
        assertThat(server.onExit().get(1, TimeUnit.SECONDS))
                .as("cooperative runtime shutdown should preserve a clean exit code")
                .isZero();
    }

    @Test
    void closeTerminatesLauncherDescendants(@TempDir Path tmp) throws Exception {
        Path script = writeLauncherScript(tmp, false);
        RuntimeServerProcess server = RuntimeServerProcess.spawn(script, "tcp://127.0.0.1:0");
        List<ProcessHandle> descendants;
        try (java.util.stream.Stream<ProcessHandle> handles =
                ProcessHandle.of(server.pid()).orElseThrow().descendants()) {
            descendants = handles.collect(Collectors.toList());
        }
        assertThat(descendants)
                .as("launcher should own the stub server process")
                .isNotEmpty();

        server.close();

        assertThat(descendants).noneMatch(ProcessHandle::isAlive);
    }

    @Test
    void roundTripRequestAndEventThroughRuntimeServerAndSession(@TempDir Path tmp) throws Exception {
        // Validates the full server bootstrap + session conversation: spawn process, parse ENDPOINT, request →
        // ack, event delivery. The stub server acks any request with an empty RESPONSE and unconditionally
        // emits a LifeSpanHandlerOnAfterCreatedEvent with handle=42.
        Path script = writeLauncherScript(tmp);
        try (RuntimeServerProcess server = RuntimeServerProcess.spawn(script, "tcp://127.0.0.1:0");
                CefTransport transport = server.connect();
                CefSession session = new CefSessionImpl(transport, Duration.ofSeconds(30))) {

            CountDownLatch sawEvent = new CountDownLatch(1);
            int[] capturedHandle = {-1};
            session.onLatest(
                    LifeSpanHandlerOnAfterCreatedEvent.MESSAGE_ID, LifeSpanHandlerOnAfterCreatedEvent.DECODER, ev -> {
                        capturedHandle[0] = ev.browser().id();
                        sawEvent.countDown();
                    });

            ReleaseHandleResponse ack = session.request(
                            new ReleaseHandleRequest(new RemoteHandle(99), "cef_browser_t"),
                            ReleaseHandleResponse.DECODER)
                    .get(30, TimeUnit.SECONDS);
            assertThat(ack).isNotNull();

            assertThat(sawEvent.await(30, TimeUnit.SECONDS))
                    .as("BrowserCreatedEvent should arrive within 30s")
                    .isTrue();
            assertThat(capturedHandle[0]).isEqualTo(42);
        }
    }

    @Test
    void spawnFailsIfBinaryDoesNotExist(@TempDir Path tmp) {
        Path missing = tmp.resolve("does-not-exist");
        org.assertj.core.api.Assertions.assertThatThrownBy(
                        () -> RuntimeServerProcess.spawn(missing, "tcp://127.0.0.1:0"))
                .isInstanceOf(IOException.class);
    }
}
