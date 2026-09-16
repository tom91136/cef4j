package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.assumeDisplayServer;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.onFxThread;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.shutdownJavaFx;
import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.startJavaFx;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javafx.application.Platform;
import net.kurobako.cef4j.test.DisplayLock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DisplayLock.class)
final class JavaFxUncaughtExceptionTest {
    @Test
    void reportsApplicationThreadFailureOnTestThread() throws Exception {
        assumeDisplayServer();
        startJavaFx();
        try {
            Platform.runLater(() -> {
                throw new IllegalStateException("sentinel JavaFX failure");
            });

            assertThatThrownBy(() -> onFxThread(() -> {}))
                    .isInstanceOf(AssertionError.class)
                    .hasMessage("Uncaught exception on JavaFX Application Thread")
                    .hasCauseInstanceOf(IllegalStateException.class);
        } finally {
            shutdownJavaFx();
        }
    }
}
