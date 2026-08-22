package net.kurobako.cef4j.packager;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
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
        Path bridge = Files.createDirectories(temporary.resolve("bridge"));
        Files.writeString(bridge.resolve("libcef4j.so"), "fixture bridge");
        Files.writeString(bridge.resolve("cef4j_launcher"), "fixture launcher");
        CommandLine cli = new CommandLine(new CefPackager());

        int packaged = cli.execute(
                "package",
                "--cef-version=150.0.0+fixture",
                "--platform=linux-x86_64",
                "--archive=" + archive,
                "--bridge-directory=" + bridge,
                "--locales=en-US",
                "--without-swiftshader",
                "--output=" + output);
        int verified = cli.execute("verify", "--platform=linux-x86_64", "--root=" + output);

        assertThat(packaged).isZero();
        assertThat(verified).isZero();
        assertThat(output.resolve("cef-runtime/linux64/libcef.so")).isRegularFile();
        assertThat(output.resolve("cef-runtime/linux64/locales/en-US.pak")).isRegularFile();
        assertThat(output.resolve("cef-runtime/linux64/libvk_swiftshader.so")).doesNotExist();
        assertThat(output.resolve("native/linux64/libcef4j.so")).hasContent("fixture bridge");
        assertThat(output.resolve("native/linux64/cef4j_launcher")).hasContent("fixture launcher");
    }

    @Test
    void rejectsOneArchiveForMultiplePlatforms() {
        int status = new CommandLine(new CefPackager())
                .execute(
                        "package",
                        "--cef-version=150.0.0+fixture",
                        "--platform=linux-x86_64,windows-x86_64",
                        "--archive=" + temporary.resolve("cef.tar.bz2"),
                        "--output=" + temporary.resolve("resources"));

        assertThat(status).isEqualTo(CommandLine.ExitCode.USAGE);
    }

    @Test
    void acceptsHostAsAnExplicitFailFastPlatformChoice() throws Exception {
        CefPlatform host = CefPlatform.detectHost(System.getProperty("os.name"), System.getProperty("os.arch"));
        Path archive = TestArchives.create(temporary.resolve("host.tar.bz2"), host);
        Path output = temporary.resolve("host-resources");

        int status = new CommandLine(new CefPackager())
                .execute(
                        "package",
                        "--cef-version=150.0.0+fixture",
                        "--platform=host",
                        "--archive=" + archive,
                        "--output=" + output);

        assertThat(status).isZero();
        assertThat(output.resolve("cef-runtime").resolve(host.cefName()).resolve(host.runtimeBinary()))
                .isRegularFile();
    }
}
