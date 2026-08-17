package net.kurobako.cef4j.packager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class CefPlatformTest {
    @Test
    void acceptsPublicAndUpstreamNames() {
        assertThat(CefPlatform.parse("linux-x86_64")).isEqualTo(CefPlatform.LINUX_X86_64);
        assertThat(CefPlatform.parse("windowsarm64")).isEqualTo(CefPlatform.WINDOWS_ARM64);
        assertThat(CefPlatform.parse("macosx-x86_64")).isEqualTo(CefPlatform.MACOS_X86_64);
    }

    @Test
    void rejectsUnknownPlatform() {
        assertThatIllegalArgumentException().isThrownBy(() -> CefPlatform.parse("plan9-amd64"));
    }
}
