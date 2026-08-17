package net.kurobako.cef4j.osr.swing.test;

import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.osr.swing.CefBrowserPanel;
import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(90)
class NativeSwingContractTest {
    @AfterAll
    static void shutdown() throws Exception {
        // XXX: Windows CEF message-loop shutdown hangs after onBeforeClose; the isolated test process owns teardown.
        if (!OS.isWindows() && Cef.INSTANCE.state() == Cef.State.INITIALISED) {
            CefBrowserPanel.terminate();
        } else if (OS.isWindows()) {
            javax.swing.SwingUtilities.invokeAndWait(() -> {
                for (java.awt.Frame frame : java.awt.Frame.getFrames()) frame.dispose();
            });
        }
    }

    @Test
    void nativeBrowserPanelSatisfiesSharedBrowserContract() throws Exception {
        BrowserContract.verify(new NativeSwingBrowserBackend());
    }
}
