package net.kurobako.cef4j.test.matrix;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * The actual cross-backend matrix. This module owns {@code RemoteCefBrowserBackend} and pulls
 * {@code NativeBrowserBackend} from {@code cef4j-inprocess-jfx} onto its test classpath via a test-jar dependency, so
 * {@link BrowserBackend#discover()} returns both impls and each test method runs twice — once per backend.
 *
 * <p>Skip rules: each backend reports its own availability ({@code DISPLAY} env, server binary path system property, a
 * writable temporary directory for IPC). The {@code Assumptions.assumeTrue} call inside each method ensures a missing
 * prereq just skips that backend's leg without failing the test.
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

    @ParameterizedTest(name = "shared contract: {0}")
    @MethodSource("backends")
    void satisfiesSharedBrowserContract(BrowserBackend backend) throws Exception {
        BrowserContract.verify(backend);
    }
}
