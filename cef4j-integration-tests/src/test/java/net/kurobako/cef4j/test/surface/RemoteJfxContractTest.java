package net.kurobako.cef4j.test.surface;

import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(90)
class RemoteJfxContractTest {
    @Test
    void remoteWebViewSatisfiesSharedBrowserContract() throws Exception {
        BrowserContract.verify(new RemoteJfxBrowserBackend());
    }
}
