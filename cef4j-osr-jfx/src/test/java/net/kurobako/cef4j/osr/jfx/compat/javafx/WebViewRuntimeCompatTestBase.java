package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static net.kurobako.cef4j.osr.jfx.compat.javafx.FxWebViewRuntimeTestSupport.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

abstract class WebViewRuntimeCompatTestBase {

    @BeforeAll
    static void startJavaFx() throws Exception {
        ensureStarted();
    }

    @AfterEach
    void cleanupStages() throws Exception {
        closeStages();
    }
}
