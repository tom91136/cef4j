package net.kurobako.cef4j.osr.jfx.compat.javafx;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

final class FxWebViewRuntimeTestSupportTest {
    @Test
    void drainsPopupCreationForAffectedAlloyVersions() {
        assertThat(FxWebViewRuntimeTestSupport.requiresPopupCreationDrain(120)).isFalse();
        assertThat(FxWebViewRuntimeTestSupport.requiresPopupCreationDrain(125)).isTrue();
        assertThat(FxWebViewRuntimeTestSupport.requiresPopupCreationDrain(130)).isTrue();
        assertThat(FxWebViewRuntimeTestSupport.requiresPopupCreationDrain(135)).isTrue();
        assertThat(FxWebViewRuntimeTestSupport.requiresPopupCreationDrain(140)).isFalse();
    }
}
