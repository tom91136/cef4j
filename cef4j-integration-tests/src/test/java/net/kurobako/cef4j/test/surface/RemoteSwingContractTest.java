package net.kurobako.cef4j.test.surface;

import net.kurobako.cef4j.test.DisplayLock;
import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

@Timeout(240)
@ExtendWith(DisplayLock.class)
class RemoteSwingContractTest {
    @Test
    void remoteBrowserPanelSatisfiesSharedBrowserContract() throws Exception {
        BrowserContract.verify(new RemoteSwingBrowserBackend());
    }
}
