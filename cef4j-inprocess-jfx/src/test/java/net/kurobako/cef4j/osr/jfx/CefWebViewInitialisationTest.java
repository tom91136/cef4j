package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import net.kurobako.cef4j.test.DisplayLock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;

@Timeout(30)
@ExtendWith(DisplayLock.class)
class CefWebViewInitialisationTest {
    @Test
    void constructorThrowsClearErrorWhenCefNotInitialised() throws Exception {
        assumeDisplayServer();
        startJavaFx();
        try {
            assertThatThrownBy(() -> onFxThread(CefWebView::new))
                    .hasCauseInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("CEF must be initialised for off-screen rendering");
        } finally {
            shutdownJavaFx();
        }
    }
}
