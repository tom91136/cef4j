package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SystemBootstrapTest {

    @TempDir
    Path tempDir;

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
}
