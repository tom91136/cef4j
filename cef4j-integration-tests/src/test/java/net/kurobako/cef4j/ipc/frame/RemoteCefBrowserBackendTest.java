package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class RemoteCefBrowserBackendTest {

    @Test
    void resolvesAnUnpackedCefDistribution(@TempDir Path directory) throws Exception {
        Path release = Files.createDirectories(directory.resolve("Release"));
        Path resources = Files.createDirectories(directory.resolve("Resources"));

        assertThat(RemoteCefBrowserBackend.releaseDirectory(directory)).isEqualTo(release);
        assertThat(RemoteCefBrowserBackend.resourcesDirectory(directory)).isEqualTo(resources);
    }

    @Test
    void resolvesANormalizedPackagerDirectory(@TempDir Path directory) {
        assertThat(RemoteCefBrowserBackend.releaseDirectory(directory)).isEqualTo(directory);
        assertThat(RemoteCefBrowserBackend.resourcesDirectory(directory)).isEqualTo(directory);
    }

    @Test
    void macRuntimeUsesFrameworkInsideAnUnpackedCefDistribution(@TempDir Path directory) throws Exception {
        Path framework =
                Files.createDirectories(directory.resolve("Release").resolve("Chromium Embedded Framework.framework"));

        assertThat(RemoteCefBrowserBackend.frameworkDirectory(directory)).isEqualTo(framework);
    }

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
