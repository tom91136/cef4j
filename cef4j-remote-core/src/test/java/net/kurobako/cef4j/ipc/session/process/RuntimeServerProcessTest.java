package net.kurobako.cef4j.ipc.session.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

final class RuntimeServerProcessTest {
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
