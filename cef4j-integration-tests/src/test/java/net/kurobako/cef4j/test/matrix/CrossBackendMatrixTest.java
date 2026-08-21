package net.kurobako.cef4j.test.matrix;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.kurobako.cef4j.test.DisplayLock;
import net.kurobako.cef4j.test.backend.BrowserBackend;
import net.kurobako.cef4j.test.backend.BrowserContract;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/** Runs one browser contract against the in-process and packaged-runtime backends. */
@Timeout(600)
@ExtendWith(DisplayLock.class)
class CrossBackendMatrixTest {

    static List<BrowserBackend> backends() {
        return BrowserBackend.discover();
    }

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
