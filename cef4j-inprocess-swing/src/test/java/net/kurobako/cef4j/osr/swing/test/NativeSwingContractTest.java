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
        // Windows' daemon-managed CEF loop does not reliably return from cef_quit_message_loop,
        // even after the contract session has received onBeforeClose. This class owns its
        // Surefire JVM, so use that process boundary after verifying browser closure.
        if (!OS.isWindows() && Cef.INSTANCE.state() == Cef.State.INITIALISED) {
            // This also disposes CefBrowserPanel's AWT bootstrap peer. Calling Cef directly leaves the AWT
            // auto-shutdown thread alive and makes Surefire report an otherwise-successful fork as failed.
            CefBrowserPanel.terminate();
        } else if (OS.isWindows()) {
            // The process boundary owns CEF on Windows, but displayable AWT peers would prevent that boundary from
            // being reached. The contract has already closed its browser, so dispose the test JVM's remaining peers.
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
