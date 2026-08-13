package net.kurobako.cef4j.osr.swing.test;

import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(90)
class NativeSwingContractTest {
    @Test
    void nativeBrowserPanelSatisfiesSharedBrowserContract() throws Exception {
        BrowserContract.verify(new NativeSwingBrowserBackend());
    }
}
