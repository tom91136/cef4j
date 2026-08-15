package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CefProcessArgumentsTest {

    @Test
    void posixLaunchDisablesCefSignalHandlerInstallation() {
        List<String> arguments = Cef.processArguments(List.of("--user-switch"));

        if (OS.isWindows()) {
            assertThat(arguments).doesNotContain("--disable-in-process-stack-traces");
        } else {
            assertThat(arguments).contains("--disable-in-process-stack-traces");
        }
    }

    @Test
    void requiredArgumentsAreNotDuplicated() {
        List<String> arguments = Cef.processArguments(List.of("--disable-in-process-stack-traces"));

        assertThat(arguments).containsOnlyOnce("--disable-in-process-stack-traces");
    }
}
