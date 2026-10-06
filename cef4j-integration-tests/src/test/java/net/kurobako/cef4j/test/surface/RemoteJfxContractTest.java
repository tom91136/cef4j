package net.kurobako.cef4j.test.surface;

import net.kurobako.cef4j.test.DisplayLock;
import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

@Timeout(600)
@ExtendWith(DisplayLock.class)
class RemoteJfxContractTest {
    @Test
    void remoteWebViewSatisfiesSharedBrowserContract() throws Exception {
        BrowserContract.verify(new RemoteJfxBrowserBackend());
    }
}
