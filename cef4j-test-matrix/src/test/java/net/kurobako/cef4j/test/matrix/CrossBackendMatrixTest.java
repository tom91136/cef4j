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
 * The actual cross-backend matrix. This module pulls both {@code IpcBrowserBackend} (from {@code cef4j-ipc-frame-mmap})
 * and {@code NativeBrowserBackend} (from {@code cef4j-osr-jfx}) onto its test classpath via test-jar deps, so
 * {@link BrowserBackend#discover()} returns both impls and each test method runs twice — once per backend.
 *
 * <p>Skip rules: each backend reports its own availability ({@code DISPLAY} env, helper binary path system property,
 * {@code /dev/shm} for IPC). The {@code Assumptions.assumeTrue} call inside each method ensures a missing prereq just
 * skips that backend's leg without failing the test.
 *
 * <p>Why a separate module: each per-module smoke test ({@code BrowserBackendSmokeTest},
 * {@code NativeBackendSmokeTest}) only sees its own backend on its own classpath. A meaningful matrix needs both impls
 * discoverable from one place — that's this module.
 */
@Timeout(60)
class CrossBackendMatrixTest {

    static List<BrowserBackend> backends() {
        return BrowserBackend.discover();
    }

    /**
     * Sanity: ServiceLoader discovers both backends. Failure here means the test-jar deps in this module's pom didn't
     * carry the META-INF/services registrations through.
     */
    @org.junit.jupiter.api.Test
    void discoverFindsBothBackends() {
        List<String> names = BrowserBackend.discover().stream()
                .map(BrowserBackend::name)
                .sorted()
                .collect(java.util.stream.Collectors.toList());
        assertThat(names).contains(BrowserBackend.IPC_NAME, BrowserBackend.NATIVE_NAME);
    }

    /**
     * Single combined check per backend. Multiple sessions in one JVM trip per-backend lifecycle issues we already
     * documented elsewhere: jeromq's cumulative ZContext state for IPC and JFX Stage state for native both don't
     * survive a second open/close cycle in the same fork. {@code reuseForks=false} only buys us a fresh JVM per test
     * <em>class</em>, so to keep the matrix in one class we open one session and run all asserts against it. Same
     * pattern as {@code BrowserBackendSmokeTest} in {@code cef4j-ipc-frame-mmap}.
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("backends")
    void deliversPaintAndEvaluatesJs(BrowserBackend backend) throws Exception {
        Assumptions.assumeTrue(backend.isAvailable(), () -> backend.name() + " unavailable");
        BrowserBackend.SessionConfig cfg = new BrowserBackend.SessionConfig(
                "data:text/html,<html><body style='background:red'>p</body></html>", 800, 600, Duration.ofSeconds(20));
        try (BrowserSession s = backend.openSession(cfg)) {
            BrowserSession.PaintInfo p = s.awaitFirstPaint(Duration.ofSeconds(20));
            assertThat(p.width).isEqualTo(800);
            assertThat(p.height).isEqualTo(600);
            assertThat(p.byteCount).isEqualTo(800L * 600 * 4);

            assertThat(s.evaluateJavascript("1 + 2 + 3").get()).isEqualTo("6");

            // JSON-stringification policy differs subtly: native wraps in quotes, IPC strips them. Accept either;
            // what matters is the value is correctly transported.
            String stringResult = s.evaluateJavascript("'hello, ' + 'world'").get();
            assertThat(stringResult).isIn("hello, world", "\"hello, world\"");
        }
    }
}
