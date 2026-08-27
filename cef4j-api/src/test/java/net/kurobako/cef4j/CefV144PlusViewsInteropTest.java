package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.views.CefBrowserView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisabledOnOs(OS.MAC)
class CefV144PlusViewsInteropTest extends CefTestBase {

    @BeforeAll
    static void initCef() throws Exception {
        CefTestBase.initCef(List.of());
    }

    @Test
    void browserViewGetRuntimeStyle() {
        CefBrowserSettings.Mutable settings = new CefBrowserSettings.Mutable();
        CefClient client = new CefClient() {};

        try (CefBrowserView browserView = CefBrowserView.create(
                        client, "about:blank", settings.toImmutable(), null, null, null)
                .orElseThrow()) {
            assertThat(browserView.getRuntimeStyle()).isNotNull();
        }
    }
}
