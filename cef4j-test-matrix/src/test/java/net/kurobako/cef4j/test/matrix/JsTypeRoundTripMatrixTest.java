package net.kurobako.cef4j.test.matrix;

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
 * Cross-backend JS type round-trip: confirms each scalar JS value coerces to the same string form across both backends,
 * and surfaces existing intentional divergences (string-quoting; documented inline) so they don't silently widen.
 *
 * <p>One test method, multiple eval round-trips per session — the per-backend "one session per JVM" rule from
 * {@link CrossBackendMatrixTest} applies here too.
 */
@Timeout(60)
class JsTypeRoundTripMatrixTest {

    static List<BrowserBackend> backends() {
        return BrowserBackend.discover();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("backends")
    void scalarsCoerceConsistently(BrowserBackend backend) throws Exception {
        Assumptions.assumeTrue(backend.isAvailable(), () -> backend.name() + " unavailable");
        BrowserBackend.SessionConfig cfg =
                new BrowserBackend.SessionConfig("data:text/html,<html></html>", 400, 300, Duration.ofSeconds(20));
        try (BrowserSession s = backend.openSession(cfg)) {
            // The IPC renderer process is spawned lazily on first paint; without this wait the first JS eval
            // hangs on a missing renderer. The native backend doesn't strictly need it but the wait is cheap.
            s.awaitFirstPaint(Duration.ofSeconds(20));

            // ints round-trip exact
            assertThat(s.evaluateJavascript("42").get()).isEqualTo("42");
            assertThat(s.evaluateJavascript("-7").get()).isEqualTo("-7");

            // bools round-trip as "true" / "false"
            assertThat(s.evaluateJavascript("true").get()).isEqualTo("true");
            assertThat(s.evaluateJavascript("false").get()).isEqualTo("false");

            // doubles arrive as their Java toString form (varies by backend toString policy; both should at least
            // parse back to the same numeric value)
            String pi = s.evaluateJavascript("Math.PI").get();
            assertThat(Double.parseDouble(pi)).isEqualTo(Math.PI);

            // strings: native wraps in quotes ("\"hello\""), IPC strips them ("hello"). Documented in
            // CrossBackendMatrixTest; we re-assert here so any narrowing of either policy stays a deliberate
            // change rather than a silent regression.
            String hi = s.evaluateJavascript("'hello'").get();
            assertThat(hi).isIn("hello", "\"hello\"");
        }
    }
}
