package net.kurobako.cef4j.osr.swing.test;

import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import net.kurobako.cef4j.test.DisplayLock;
import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

@Timeout(180)
@ExtendWith(DisplayLock.class)
class NativeSwingContractTest {
    @AfterAll
    static void shutdown() {
        if (Cef.INSTANCE.state() == Cef.State.INITIALISED) CefBrowserPanel.terminate();
    }

    @Test
    void nativeBrowserPanelSatisfiesSharedBrowserContract() throws Exception {
        BrowserContract.verify(new NativeSwingBrowserBackend());
    }
}
