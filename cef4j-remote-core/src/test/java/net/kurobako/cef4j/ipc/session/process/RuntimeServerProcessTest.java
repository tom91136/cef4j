package net.kurobako.cef4j.ipc.session.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

final class RuntimeServerProcessTest {
    @Test
    void alignsUnixChildTempDirectoryWithJava() {
        Map<String, String> environment = new HashMap<>();
        environment.put("TMPDIR", "/private/tmp");
        environment.put("KEEP", "value");

        Path tempDirectory = Path.of("/java/tmp");
        RuntimeServerProcess.alignTempEnvironment(environment, tempDirectory, "Mac OS X");

        assertThat(environment)
                .containsEntry("TMPDIR", tempDirectory.toString())
                .containsEntry("KEEP", "value");
    }

    @Test
    void alignsBothWindowsChildTempVariablesWithJava() {
        Map<String, String> environment = new HashMap<>(Map.of("TEMP", "C:\\old"));

        RuntimeServerProcess.alignTempEnvironment(environment, Path.of("C:\\java-tmp"), "Windows 11");

        assertThat(environment).containsEntry("TEMP", "C:\\java-tmp").containsEntry("TMP", "C:\\java-tmp");
    }

    @Test
    void serverCommandAppendsConfiguredCefArguments() {
        assertThat(RuntimeServerProcess.serverCommand(
                        Path.of("runtime-server"),
                        "zmq",
                        "tcp://127.0.0.1:0",
                        "shared-file",
                        " --disable-gpu, --disable-software-rasterizer "))
                .containsExactly(
                        "runtime-server",
                        "--transport",
                        "zmq",
                        "--bind",
                        "tcp://127.0.0.1:0",
                        "--frame-transport",
                        "shared-file",
                        "--disable-gpu",
                        "--disable-software-rasterizer");
    }

    @Test
    void classifiesRoutineChildDiagnosticsBelowInfo() {
        assertThat(RuntimeServerProcess.stderrLevel("[cef4j-runtime-server] shutdown: stopping IPC transport"))
                .isEqualTo(RuntimeServerProcess.ChildOutputLevel.TRACE);
        assertThat(RuntimeServerProcess.stderrLevel(
                        "[cef4j-runtime-server] CEF context initialized; publishing endpoint"))
                .isEqualTo(RuntimeServerProcess.ChildOutputLevel.TRACE);
        assertThat(RuntimeServerProcess.stderrLevel(
                        "[cef4j-runtime-server] shared-frame paint reached event-sent for browser=1"))
                .isEqualTo(RuntimeServerProcess.ChildOutputLevel.TRACE);
        assertThat(RuntimeServerProcess.stderrLevel("[cef4j-runtime-server] zmq_send failed: Host unreachable"))
                .isEqualTo(RuntimeServerProcess.ChildOutputLevel.DEBUG);
        assertThat(RuntimeServerProcess.stderrLevel("[123:456:INFO:component.cc:1] ready"))
                .isEqualTo(RuntimeServerProcess.ChildOutputLevel.DEBUG);
    }

    @Test
    void keepsUnexpectedChildFailuresAtWarn() {
        assertThat(RuntimeServerProcess.stderrLevel(
                        "[cef4j-runtime-server] zmq_bind(tcp://127.0.0.1:1) failed: Address in use"))
                .isEqualTo(RuntimeServerProcess.ChildOutputLevel.WARN);
        assertThat(RuntimeServerProcess.stderrLevel(
                        "[cef4j-runtime-server] macOS application bootstrap: unexpected NSApp class BrokenApp"))
                .isEqualTo(RuntimeServerProcess.ChildOutputLevel.WARN);
        assertThat(RuntimeServerProcess.stderrLevel("[123:456:ERROR:component.cc:1] crashed"))
                .isEqualTo(RuntimeServerProcess.ChildOutputLevel.WARN);
    }
}
