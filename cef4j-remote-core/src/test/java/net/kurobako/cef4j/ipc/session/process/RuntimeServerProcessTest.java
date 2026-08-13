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
}
