package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class TestTempDirsTest {

    @Test
    void marksAndAcceptsOwnedDirectoryBelowSystemTemp() throws Exception {
        Path directory = Files.createTempDirectory("cef4j-cleanup-test-");
        try {
            TestTempDirs.cleanupAtExit(directory);
            assertThat(directory.resolve(TestTempDirs.OWNERSHIP_MARKER)).isRegularFile();
        } finally {
            Files.deleteIfExists(directory.resolve(TestTempDirs.OWNERSHIP_MARKER));
            Files.deleteIfExists(directory);
        }
    }

    @Test
    void acceptsDirectoryBelowSymlinkedSystemTempRoot() throws Exception {
        assumeTrue(FileSystems.getDefault().supportedFileAttributeViews().contains("posix"));
        String originalTemp = System.getProperty("java.io.tmpdir");
        Path originalRoot = Path.of(originalTemp).toRealPath();
        Path realRoot = Files.createTempDirectory(originalRoot, "cef4j-real-temp-");
        Path alias = originalRoot.resolve("cef4j-temp-alias-" + UUID.randomUUID());
        Path directory = realRoot.resolve("owned");
        Files.createDirectory(directory);
        Files.createSymbolicLink(alias, realRoot);
        try {
            System.setProperty("java.io.tmpdir", alias.toString());
            TestTempDirs.cleanupAtExit(alias.resolve("owned"));
            assertThat(directory.resolve(TestTempDirs.OWNERSHIP_MARKER)).isRegularFile();
        } finally {
            System.setProperty("java.io.tmpdir", originalTemp);
            Files.deleteIfExists(directory.resolve(TestTempDirs.OWNERSHIP_MARKER));
            Files.deleteIfExists(directory);
            Files.deleteIfExists(alias);
            Files.deleteIfExists(realRoot);
        }
    }

    @Test
    void rejectsSystemTempRootAndWorkspace() throws Exception {
        Path tempRoot = Path.of(System.getProperty("java.io.tmpdir")).toRealPath();
        assertThatIllegalArgumentException().isThrownBy(() -> TestTempDirs.cleanupAtExit(tempRoot));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> TestTempDirs.cleanupAtExit(Path.of(System.getProperty("user.dir"))));
    }
}
