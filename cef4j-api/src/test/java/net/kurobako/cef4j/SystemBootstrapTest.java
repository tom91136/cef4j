package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIOException;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.io.TempDir;

class SystemBootstrapTest {

    @TempDir
    Path tempDir;

    @Test
    void packagedCefApiVersionMatchesTheBuild() {
        assertThat(SystemBootstrap.packagedCefApiVersion()).isEqualTo(System.getProperty("cef4j.test.cefApiVersion"));
    }

    @Test
    void autoDiscoverySelectsOnlyThePackagedCefMajor() throws Exception {
        String expectedMajor = java.util.Objects.requireNonNull(SystemBootstrap.packagedCefApiVersion());
        Path matching = createCefDistribution(expectedMajor);
        createCefDistribution("999");

        assertThat(SystemBootstrap.discoverCefDist(tempDir, expectedMajor)).isEqualTo(matching);

        deleteTree(java.util.Objects.requireNonNull(matching.getParent()));

        assertThat(SystemBootstrap.discoverCefDist(tempDir, expectedMajor)).isNull();
    }

    @Test
    @EnabledOnOs(org.junit.jupiter.api.condition.OS.LINUX)
    void nativeStderrDescriptorsAreNotInheritedByCefSubprocesses() {
        SystemBootstrap.load();

        assertThat(NativeStderr.internalDescriptorsCloseOnExec()).isTrue();
    }

    @Test
    void packagedRuntimeWinsOverAutoDiscoveryButNotExplicitConfiguration() {
        Path configured = Path.of("configured");
        Path discovered = Path.of("discovered");

        assertThat(SystemBootstrap.selectLibcefDir(configured, discovered, true))
                .isEqualTo(configured);
        assertThat(SystemBootstrap.selectLibcefDir(null, discovered, true)).isNull();
        assertThat(SystemBootstrap.selectLibcefDir(null, discovered, false)).isEqualTo(discovered);
    }

    @Test
    void extractionReplacesMismatchedExistingContent() throws Exception {
        Path target = tempDir.resolve("resource.html");
        Files.writeString(target, "attacker-controlled");

        SystemBootstrap.extractResource("cef4j-scheme-test.html", target);

        assertThat(Files.readString(target)).contains("scheme handler works");
    }

    @Test
    void extractionRejectsSymbolicLinkTargets() throws Exception {
        Path destination = tempDir.resolve("destination.html");
        Files.writeString(destination, "unchanged");
        Path target = tempDir.resolve("resource.html");
        try {
            Files.createSymbolicLink(target, destination);
        } catch (UnsupportedOperationException | java.io.IOException e) {
            Assumptions.assumeTrue(false, "Symbolic links unavailable: " + e);
            return;
        }

        assertThatIOException()
                .isThrownBy(() -> SystemBootstrap.extractResource("cef4j-scheme-test.html", target))
                .withMessageContaining("symbolic link");
        assertThat(Files.readString(destination)).isEqualTo("unchanged");
    }

    @Test
    void externalRuntimeIncludesCefResourcesAndLauncherDependencies() throws Exception {
        Path release = Files.createDirectories(tempDir.resolve("cef/Release"));
        Path runtime = Files.createDirectories(tempDir.resolve("reactor"));

        if (OS.isMacOS()) {
            Path libraries =
                    Files.createDirectories(release.resolve("Chromium Embedded Framework.framework/Libraries"));
            touch(libraries.resolve("libEGL.dylib"));
            touch(libraries.resolve("libGLESv2.dylib"));
            touch(libraries.resolve("libvk_swiftshader.dylib"));
            touch(libraries.resolve("vk_swiftshader_icd.json"));

            SystemBootstrap.prepareExternalRuntime(release, runtime);

            assertThat(runtime.resolve("Chromium Embedded Framework.framework")).exists();
            assertThat(runtime.resolve("libEGL.dylib")).exists();
            assertThat(runtime.resolve("libGLESv2.dylib")).exists();
            assertThat(runtime.resolve("libvk_swiftshader.dylib")).exists();
            assertThat(runtime.resolve("vk_swiftshader_icd.json")).exists();
            return;
        }

        String[] libraries = OS.isWindows()
                ? new String[] {
                    "libcef.dll", "chrome_elf.dll", "d3dcompiler_47.dll", "vk_swiftshader.dll", "vulkan-1.dll"
                }
                : new String[] {"libcef.so", "libEGL.so", "libGLESv2.so", "libvk_swiftshader.so", "libvulkan.so.1"};
        for (String library : libraries) touch(release.resolve(library));
        touch(release.resolve("v8_context_snapshot.bin"));
        touch(release.resolve("vk_swiftshader_icd.json"));

        Path resources = Files.createDirectories(tempDir.resolve("cef/Resources"));
        for (String resource :
                new String[] {"icudtl.dat", "resources.pak", "chrome_100_percent.pak", "chrome_200_percent.pak"}) {
            touch(resources.resolve(resource));
        }
        Files.createDirectories(resources.resolve("locales"));

        SystemBootstrap.prepareExternalRuntime(release, runtime);

        for (String library : libraries) assertThat(runtime.resolve(library)).exists();
        assertThat(release.resolve("icudtl.dat")).exists();
        assertThat(release.resolve("locales")).isDirectory();
        assertThat(runtime.resolve("icudtl.dat")).exists();
        assertThat(runtime.resolve("locales")).isDirectory();
    }

    private static void touch(Path path) throws Exception {
        Files.write(path, new byte[] {1});
    }

    private Path createCefDistribution(String major) throws Exception {
        Path release = Files.createDirectories(tempDir.resolve(".cef-dist")
                .resolve("cef_binary_" + major + ".0_test_" + OS.platform() + "_minimal")
                .resolve("Release"));
        if (OS.isMacOS()) {
            Files.createDirectories(release.resolve("Chromium Embedded Framework.framework"));
        } else {
            touch(release.resolve(OS.isWindows() ? "libcef.dll" : "libcef.so"));
        }
        return release;
    }

    private static void deleteTree(Path root) throws Exception {
        try (java.util.stream.Stream<Path> paths = Files.walk(root)) {
            Path[] descending =
                    paths.sorted(java.util.Comparator.reverseOrder()).toArray(Path[]::new);
            for (Path path : descending) Files.delete(path);
        }
    }
}
