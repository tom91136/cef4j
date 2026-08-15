package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class RemoteCefBrowserBackendTest {

    @Test
    void macRuntimeUsesFrameworkInsideTheApplicationBundle(@TempDir Path directory) throws Exception {
        Path framework = directory
                .resolve("cef4j-runtime-server.app")
                .resolve("Contents")
                .resolve("Frameworks")
                .resolve("Chromium Embedded Framework.framework");
        Files.createDirectories(framework);

        assertThat(RemoteCefBrowserBackend.frameworkDirectory(directory)).isEqualTo(framework);
    }

    @Test
    void macRuntimeAcceptsTheLegacyOuterFrameworkLayout(@TempDir Path directory) throws Exception {
        Path framework = directory.resolve("Frameworks").resolve("Chromium Embedded Framework.framework");
        Files.createDirectories(framework);

        assertThat(RemoteCefBrowserBackend.frameworkDirectory(directory)).isEqualTo(framework);
    }
}
