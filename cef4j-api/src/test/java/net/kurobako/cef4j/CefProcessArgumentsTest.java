package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import net.kurobako.cef4j.gen.CefSettings;
import org.junit.jupiter.api.Test;

class CefProcessArgumentsTest {

    @Test
    void launchArgumentsAreMutableAndDefensivelyCopied() {
        ArrayList<String> source = new ArrayList<>(List.of("--initial"));
        Cef.LaunchArgs launch = new Cef.LaunchArgs(new CefSettings.Mutable(), source);

        source.add("--source-only");
        launch.args().add("--caller-added");

        assertThat(launch.args()).containsExactly("--initial", "--caller-added");
    }

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

    @Test
    void sandboxFlagsAreNeverInjected() {
        assertThat(Cef.processArguments(List.of()))
                .doesNotContain(
                        "--no-sandbox",
                        "--disable-setuid-sandbox",
                        "--disable-seccomp-filter-sandbox",
                        "--disable-gpu-sandbox");
    }

    @Test
    void unsupportedSandboxRequiresExplicitUnsafeOptIn() {
        CefSettings.Mutable settings = new CefSettings.Mutable();

        assertThatThrownBy(() -> Cef.validateSettings(settings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("noSandbox=1");

        settings.noSandbox = 1;
        assertThatNoException().isThrownBy(() -> Cef.validateSettings(settings));
    }
}
