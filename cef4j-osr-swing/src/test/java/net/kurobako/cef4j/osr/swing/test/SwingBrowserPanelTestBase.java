package net.kurobako.cef4j.osr.swing.test;

import static net.kurobako.cef4j.osr.swing.test.SwingBrowserPanelTestSupport.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

abstract class SwingBrowserPanelTestBase {

    @BeforeAll
    static void initCef() throws Exception {
        ensureCefStarted();
    }

    @AfterEach
    void cleanupFrames() throws Exception {
        closeFrames();
    }
}
