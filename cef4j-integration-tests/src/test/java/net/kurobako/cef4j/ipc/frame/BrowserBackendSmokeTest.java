package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserSession;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Smoke test that drives every {@link BrowserBackend} discovered through the shared test SPI, including the in-process
 * and remote CEF backends contributed on this module's test classpath.
 */
@Timeout(60)
class BrowserBackendSmokeTest {

    static List<BrowserBackend> backends() {
        return BrowserBackend.discover();
    }

    /** Combined paint + eval check against one browser session for every backend. */
    @ParameterizedTest(name = "{0}")
    @MethodSource("backends")
    void deliversPaintAndEvaluatesJsAcrossBackend(BrowserBackend backend) throws Exception {
        Assumptions.assumeTrue(backend.isAvailable(), () -> backend.name() + " unavailable");
        BrowserBackend.SessionConfig cfg = new BrowserBackend.SessionConfig(
                "data:text/html,<html><body style='background:red'>p</body></html>", 800, 600, Duration.ofSeconds(20));
        try (BrowserSession s = backend.openSession(cfg)) {
            BrowserSession.PaintInfo p = s.awaitFirstPaint(Duration.ofSeconds(15));
            assertThat(p.width).isEqualTo(800);
            assertThat(p.height).isEqualTo(600);
            assertThat(p.byteCount).isEqualTo(800L * 600 * 4);

            String result = s.evaluateJavascript("1 + 2 + 3").get();
            assertThat(result).isEqualTo("6");
        }
    }
}
