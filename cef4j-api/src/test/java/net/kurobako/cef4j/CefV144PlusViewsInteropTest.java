package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.views.CefBrowserView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CefV144PlusViewsInteropTest extends CefTestBase {

    @BeforeAll
    static void initCef() throws Exception {
        CefTestBase.initCef(List.of());
    }

    @Test
    void browserView_getRuntimeStyle() throws Exception {
        CefBrowserSettings.Mutable settings = new CefBrowserSettings.Mutable();
        CefClient client = new CefClient() {};

        try (CefBrowserView browserView = CefBrowserView.create(
                        client, "about:blank", settings.toImmutable(), null, null, null)
                .orElseThrow()) {
            pumpFor(500);
            assertThat(browserView.getRuntimeStyle()).isNotNull();
        }
    }

    private static void pumpFor(long durationMs) throws InterruptedException {
        long deadline = System.currentTimeMillis() + durationMs;
        while (System.currentTimeMillis() < deadline) {
            Cef.INSTANCE.doMessageLoopWork();
            Thread.sleep(16);
        }
    }
}
