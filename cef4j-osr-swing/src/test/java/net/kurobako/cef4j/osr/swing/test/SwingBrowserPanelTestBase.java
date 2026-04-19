package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;

import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.io.TempDir;

abstract class SwingBrowserPanelTestBase {
    @TempDir
    @SuppressWarnings("NullAway.Init")
    static Path tempDir;

    @BeforeAll
    static void initCef() throws Exception {
        ensureCefStarted(tempDir);
    }

    @AfterEach
    void cleanupFrames() throws Exception {
        closeFrames();
    }

    @AfterAll
    static void shutdown() {
        shutdownCef();
    }
}
