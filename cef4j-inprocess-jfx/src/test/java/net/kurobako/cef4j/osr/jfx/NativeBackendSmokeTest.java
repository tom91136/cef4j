package net.kurobako.cef4j.osr.jfx;

import static net.kurobako.cef4j.osr.jfx.CefWebViewTestSupport.assumeDisplayServer;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Drives the {@link BrowserBackend} SPI against the in-process native backend exposed by this module's test scope.
 * Companion to {@code BrowserBackendSmokeTest} in {@code cef4j-integration-tests}; both demonstrate that test code can
 * be written once and parameterised over the discovered backend list.
 *
 * <p>This module ships {@code NativeBrowserBackend} via {@code META-INF/services}; if some future module also lands an
 * additional backend on this test classpath, it'd be picked up automatically by {@link #backends()}.
 */
@Timeout(60)
class NativeBackendSmokeTest {

    @AfterAll
    static void shutdownCef() throws Exception {
        CefWebViewTestSupport.closeAllWindows();
        CefWebViewTestSupport.drainJavaFx();
        if (net.kurobako.cef4j.Cef.INSTANCE.state() == net.kurobako.cef4j.Cef.State.INITIALISED) {
            CefWebView.terminate();
        }
        CefWebViewTestSupport.shutdownJavaFx();
    }

    static List<BrowserBackend> backends() {
        return BrowserBackend.discover();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("backends")
    void deliversPaintAndEvaluatesJsAcrossBackend(BrowserBackend backend) throws Exception {
        Assumptions.assumeTrue(backend.isAvailable(), () -> backend.name() + " unavailable");
        assumeDisplayServer();

        BrowserBackend.SessionConfig cfg = new BrowserBackend.SessionConfig(
                "data:text/html,<html><body style='background:red'>p</body></html>", 800, 600, Duration.ofSeconds(20));
        try (BrowserSession s = backend.openSession(cfg)) {
            BrowserSession.PaintInfo p = s.awaitFirstPaint(Duration.ofSeconds(20));
            assertThat(p.width).isEqualTo(800);
            assertThat(p.height).isEqualTo(600);
            assertThat(p.byteCount).isEqualTo(800L * 600 * 4);

            String result = s.evaluateJavascript("1 + 2 + 3").get();
            assertThat(result).isEqualTo("6");
        }
    }
}
