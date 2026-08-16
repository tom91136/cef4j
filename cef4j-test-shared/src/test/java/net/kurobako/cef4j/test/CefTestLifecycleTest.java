package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CefTestLifecycleTest {
    @Test
    void excludesOnlyTheLegacyMacSwingShutdownFault() {
        assertThat(CefTestLifecycle.nativeSwingContractAvailable("Linux", 109)).isTrue();
        assertThat(CefTestLifecycle.nativeSwingContractAvailable("Mac OS X", 109))
                .isFalse();
        assertThat(CefTestLifecycle.nativeSwingContractAvailable("Mac OS X", 116))
                .isFalse();
        assertThat(CefTestLifecycle.nativeSwingContractAvailable("Mac OS X", 144))
                .isTrue();
        assertThat(CefTestLifecycle.nativeSwingContractAvailable("Windows 11", 116))
                .isTrue();
    }
}
