package net.kurobako.cef4j.packager;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

class CefPackagerTest {
    @TempDir
    Path temporary;

    @Test
    void packagesAndVerifiesALocalArchiveThroughTheCli() throws Exception {
        Path archive = TestArchives.create(temporary.resolve("cef.tar.bz2"), CefPlatform.LINUX_X86_64);
        Path output = temporary.resolve("resources");
        CommandLine cli = new CommandLine(new CefPackager());

        int packaged = cli.execute(
                "package",
                "--cef-version=150.0.0+fixture",
                "--platform=linux-x86_64",
                "--archive=" + archive,
                "--locales=en-US",
                "--without-swiftshader",
                "--output=" + output);
        int verified = cli.execute("verify", "--platform=linux-x86_64", "--root=" + output);

        assertThat(packaged).isZero();
        assertThat(verified).isZero();
        assertThat(output.resolve("cef-runtime/linux64/libcef.so")).isRegularFile();
        assertThat(output.resolve("cef-runtime/linux64/locales/en-US.pak")).isRegularFile();
        assertThat(output.resolve("cef-runtime/linux64/libvk_swiftshader.so")).doesNotExist();
    }

    @Test
    void rejectsOneArchiveForMultiplePlatforms() {
        int status = new CommandLine(new CefPackager()).execute(
                "package",
                "--cef-version=150.0.0+fixture",
                "--platform=linux-x86_64,windows-x86_64",
                "--archive=" + temporary.resolve("cef.tar.bz2"),
                "--output=" + temporary.resolve("resources"));

        assertThat(status).isEqualTo(CommandLine.ExitCode.USAGE);
    }
}
