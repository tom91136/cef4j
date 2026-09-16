package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;

import java.nio.file.Path;
import net.kurobako.cef4j.test.DisplayLock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

@ExtendWith(DisplayLock.class)
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
    // Native CEF shutdown plus the final AWT queue barrier can exceed the default 30-second lifecycle budget on CI.
    @Timeout(120)
    static void shutdown() {
        shutdownCef();
    }
}
