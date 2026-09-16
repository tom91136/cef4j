package net.kurobako.cef4j.test.surface;

import net.kurobako.cef4j.test.DisplayLock;
import net.kurobako.cef4j.test.backend.BrowserContract;
import net.kurobako.cef4j.test.backend.CefTestCompatibility;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

@Timeout(600)
@ExtendWith(DisplayLock.class)
class RemoteSwingContractTest {
    @Test
    void remoteBrowserPanelSatisfiesSharedBrowserContract() throws Exception {
        Assumptions.assumeTrue(
                CefTestCompatibility.hasReliableNativeBrowserInfoHandshake(CefTestCompatibility.cefApiVersion()),
                "CEF <142 browser-info handshake race (chromiumembedded/cef#4001; fixed in CEF 142)");
        BrowserContract.verify(new RemoteSwingBrowserBackend());
    }
}
