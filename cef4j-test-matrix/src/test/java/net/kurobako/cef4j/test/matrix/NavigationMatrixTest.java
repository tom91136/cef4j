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
 * Cross-backend navigation: load URL A, then load URL B, assert the JS evaluation reflects B's content. Catches the
 * regression where navigation queues but never actually replaces the document — the IPC backend wires
 * {@code Frame.loadUrl} through the session and acks immediately on the helper side, but the page swap is asynchronous;
 * we want a positive signal that B's DOM is the one being evaluated by the time the future resolves.
 *
 * <p>Lives in its own class so {@code reuseForks=false} hands it a fresh JVM — see {@link CrossBackendMatrixTest} for
 * the per-backend single-session-per-JVM rationale.
 */
@Timeout(60)
class NavigationMatrixTest {

    static List<BrowserBackend> backends() {
        return BrowserBackend.discover();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("backends")
    void secondLoadReplacesDocument(BrowserBackend backend) throws Exception {
        Assumptions.assumeTrue(backend.isAvailable(), () -> backend.name() + " unavailable");
        BrowserBackend.SessionConfig cfg = new BrowserBackend.SessionConfig(
                "data:text/html,<html><body><span id='m'>first</span></body></html>", 400, 300, Duration.ofSeconds(20));
        try (BrowserSession s = backend.openSession(cfg)) {
            // Wait for the first paint so we know the initial document is committed before navigating away.
            s.awaitFirstPaint(Duration.ofSeconds(20));
            String first = s.evaluateJavascript("document.getElementById('m').textContent")
                    .get();
            assertThat(first).contains("first");

            // Navigate to a second document. The future resolves on the helper-side load ack; we then poll the DOM
            // to make sure the *new* document is the one being queried — JS eval against the old doc would still
            // return "first" and we'd miss the regression.
            s.loadUrl("data:text/html,<html><body><span id='m'>second</span></body></html>")
                    .get(20, java.util.concurrent.TimeUnit.SECONDS);

            String second = null;
            long deadline = System.nanoTime() + Duration.ofSeconds(15).toNanos();
            while (System.nanoTime() < deadline) {
                String v = s.evaluateJavascript("document.getElementById('m').textContent")
                        .get();
                if (v != null && v.contains("second")) {
                    second = v;
                    break;
                }
                Thread.sleep(100);
            }
            assertThat(second).as("second document should be live").contains("second");
        }
    }
}
