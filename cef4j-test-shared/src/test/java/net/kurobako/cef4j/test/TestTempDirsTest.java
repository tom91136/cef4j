package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.nio.file.Files;
import java.nio.file.Path;
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
    void rejectsSystemTempRootAndWorkspace() throws Exception {
        Path tempRoot = Path.of(System.getProperty("java.io.tmpdir")).toRealPath();
        assertThatIllegalArgumentException().isThrownBy(() -> TestTempDirs.cleanupAtExit(tempRoot));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> TestTempDirs.cleanupAtExit(Path.of(System.getProperty("user.dir"))));
    }
}
