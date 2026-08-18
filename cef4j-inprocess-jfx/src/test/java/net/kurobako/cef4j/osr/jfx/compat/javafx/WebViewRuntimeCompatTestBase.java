package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;

import java.nio.file.Files;
import java.nio.file.Path;
import net.kurobako.cef4j.test.DisplayLock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

@ExtendWith(DisplayLock.class)
abstract class WebViewRuntimeCompatTestBase {

    @BeforeAll
    static void startJavaFx(@TempDir Path tempDir) throws Exception {
        setCefCachePath(Files.createDirectories(tempDir.resolve("cef-cache")));
        ensureStarted();
    }

    @AfterEach
    void cleanupStages() throws Exception {
        closeStages();
    }

    @AfterAll
    static void shutdownRuntime() {
        shutdownCefHarness();
    }
}
