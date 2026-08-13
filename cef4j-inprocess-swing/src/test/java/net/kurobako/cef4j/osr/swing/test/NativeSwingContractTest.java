package net.kurobako.cef4j.osr.swing.test;

import net.kurobako.cef4j.Cef;
import net.kurobako.cef4j.OS;
import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(90)
class NativeSwingContractTest {
    @AfterAll
    static void shutdown() {
        // Windows' daemon-managed CEF loop does not reliably return from cef_quit_message_loop,
        // even after the contract session has received onBeforeClose. This class owns its
        // Surefire JVM, so use that process boundary after verifying browser closure.
        if (!OS.isWindows() && Cef.INSTANCE.state() == Cef.State.INITIALISED) Cef.INSTANCE.terminate();
    }

    @Test
    void nativeBrowserPanelSatisfiesSharedBrowserContract() throws Exception {
        BrowserContract.verify(new NativeSwingBrowserBackend());
    }
}
