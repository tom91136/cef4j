package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * macOS-only regression coverage for CEF's leaked AppKit observers. The scenario runs in a child JVM because an unfixed
 * build dies with SIGTRAP inside the notification block, which would kill the Surefire fork.
 */
class MacShutdownNotificationPurgeTest {
    private static final long PROBE_TIMEOUT_SECONDS = 240;

    @SuppressWarnings("NullAway.Init")
    private static Path hooksLibrary;

    @BeforeAll
    static void assumeMacOs() throws Exception {
        assumeTrue(OS.isMacOS(), "macOS-only AppKit shutdown regression");
        hooksLibrary = compileHooksLibrary();
    }

    @Test
    @Timeout(300)
    void lateWorkspaceNotificationDoesNotReenterShutDownCef() throws Exception {
        ChildResult result = runProbe(false);
        assertThat(result.exitCode).as("probe output:%n%s", result.output).isZero();
        assertThat(result.output).contains("TERMINATED").contains("PROBE_OK");
    }

    @Test
    @Timeout(300)
    void purgeKeepsHostNotificationObserversRegistered() throws Exception {
        ChildResult result = runProbe(true);
        assertThat(result.exitCode).as("probe output:%n%s", result.output).isZero();
        assertThat(result.output).contains("HOST_NOTIFICATIONS=1").contains("PROBE_OK");
    }

    private static ChildResult runProbe(boolean companion) throws IOException, InterruptedException {
        Path java = Path.of(System.getProperty("java.home"), "bin", "java");
        List<String> command = new ArrayList<>();
        command.add(java.toString());
        int feature = Runtime.version().feature();
        if (feature >= 17) command.add("--enable-native-access=ALL-UNNAMED");
        if (feature >= 23) command.add("--sun-misc-unsafe-memory-access=allow");
        command.add("-cp");
        command.add(System.getProperty("java.class.path"));
        command.add("-Dcef4j.test.macHooks=" + hooksLibrary.toAbsolutePath());
        copyProperty(command, "cef4j.test.cefApiVersion");
        copyProperty(command, "cef4j.test.extraArgs");
        command.add(MacShutdownObserverProbe.class.getName());
        if (companion) command.add("companion");

        Path outputFile = Files.createTempFile("cef4j-mac-shutdown-probe", ".log");
        Process process = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .redirectOutput(outputFile.toFile())
                .start();
        boolean finished = process.waitFor(PROBE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        if (!finished) {
            process.destroyForcibly();
            process.waitFor(10, TimeUnit.SECONDS);
            throw new AssertionError(
                    "probe timed out after " + PROBE_TIMEOUT_SECONDS + " seconds; output:%n" + readOutput(outputFile));
        }
        String output = readOutput(outputFile);
        Files.deleteIfExists(outputFile);
        return new ChildResult(process.exitValue(), output);
    }

    private static String readOutput(Path outputFile) throws IOException {
        return new String(Files.readAllBytes(outputFile), StandardCharsets.UTF_8);
    }

    private static void copyProperty(List<String> command, String name) {
        String value = System.getProperty(name);
        if (value != null) command.add("-D" + name + "=" + value);
    }

    private static Path compileHooksLibrary() throws IOException, InterruptedException, URISyntaxException {
        Path source = Path.of(Objects.requireNonNull(
                        MacShutdownNotificationPurgeTest.class.getResource("/macos/appkit_notification_hooks.m"))
                .toURI());
        Path macosDir = Objects.requireNonNull(source.getParent(), "macos resource directory");
        Path testClasses = Objects.requireNonNull(macosDir.getParent(), "test classes directory");
        Path target = Objects.requireNonNull(testClasses.getParent(), "module target directory");
        Path targetDir = target.resolve("macos-test-hooks");
        Files.createDirectories(targetDir);
        Path library = targetDir.resolve("libcef4j-appkit-hooks.dylib");
        if (Files.isRegularFile(library)
                && Files.getLastModifiedTime(library).compareTo(Files.getLastModifiedTime(source)) >= 0) {
            return library;
        }
        String javaHome = System.getProperty("java.home");
        List<String> command = List.of(
                "/usr/bin/xcrun",
                "clang",
                "-dynamiclib",
                "-fblocks",
                "-I" + javaHome + "/include",
                "-I" + javaHome + "/include/darwin",
                "-framework",
                "AppKit",
                "-framework",
                "Foundation",
                "-o",
                library.toString(),
                source.toString());
        Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
        boolean finished = process.waitFor(120, TimeUnit.SECONDS);
        if (!finished) {
            process.destroyForcibly();
            process.waitFor(10, TimeUnit.SECONDS);
            throw new AssertionError("timed out compiling " + source);
        }
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        if (process.exitValue() != 0) {
            throw new AssertionError("failed to compile " + source + ":%n" + output);
        }
        return library;
    }

    private static final class ChildResult {
        final int exitCode;
        final String output;

        ChildResult(int exitCode, String output) {
            this.exitCode = exitCode;
            this.output = output;
        }
    }
}
