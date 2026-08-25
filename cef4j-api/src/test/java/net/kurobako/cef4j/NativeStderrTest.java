package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NativeStderrTest {
    @Test
    void keepsUnexpectedNativeFailuresActionable() {
        assertThat(NativeStderr.isActionable("ERROR:gpu process crashed")).isTrue();
        assertThat(NativeStderr.isActionable("WARNING:certificate validation failed"))
                .isTrue();
    }

    @Test
    void downgradesKnownCompatibilityDiagnostics() {
        assertThat(NativeStderr.isActionable(
                        "ERROR: Default dialog implementation requires a parent window handle; canceling"))
                .isFalse();
        assertThat(NativeStderr.isActionable("ERROR: Add application/vnd.portal.filetransfer to kAtomsToCache"))
                .isFalse();
        assertThat(NativeStderr.isActionable("ordinary native lifecycle output"))
                .isFalse();
    }
}
