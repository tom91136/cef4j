package net.kurobako.cef4j.packager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CefRuntimePackagerTest {
    @TempDir
    Path temporary;

    static Stream<CefPlatform> platforms() {
        return Stream.of(CefPlatform.values());
    }

    @ParameterizedTest
    @MethodSource("platforms")
    void packagesEveryPlatformAndDropsBuildInputs(CefPlatform platform) throws Exception {
        Path archive = TestArchives.create(temporary.resolve(platform.cefName() + ".tar.bz2"), platform);
        Path output = temporary.resolve("output-" + platform.cefName());

        CefRuntimePackager.Result result = packageArchive(archive, output, platform, List.of(), false);

        assertThat(result.runtimeRoot().resolve(platform.runtimeBinary())).isRegularFile();
        assertThat(result.runtimeRoot().resolve("file-list.txt")).isRegularFile();
        assertThat(result.runtimeRoot().resolve("cef-runtime.properties")).isRegularFile();
        assertThat(result.runtimeRoot().resolve("CEF-LICENSE.txt")).hasContent("license");
        assertThat(result.files()).doesNotContain("libcef.lib", "libcef.a");
        assertThat(result.files()).anyMatch(path -> path.toLowerCase().contains("swiftshader"));
        assertThat(new CefRuntimeVerifier().verify(output, platform)).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("platforms")
    void keepsRequestedLocaleFamilies(CefPlatform platform) throws Exception {
        Path archive = TestArchives.create(temporary.resolve("locales-" + platform.cefName() + ".tar.bz2"), platform);
        CefRuntimePackager.Result result = packageArchive(
                archive, temporary.resolve("locales-output-" + platform.cefName()), platform, List.of("fr"), false);

        assertThat(result.files()).anyMatch(path -> path.contains("fr"));
        assertThat(result.files()).noneMatch(path -> path.contains("en-US") || path.contains("en.lproj"));
        assertThat(result.locales()).containsExactly("fr");
    }

    @ParameterizedTest
    @MethodSource("platforms")
    void removesOnlySwiftShaderFiles(CefPlatform platform) throws Exception {
        Path archive = TestArchives.create(temporary.resolve("gpu-" + platform.cefName() + ".tar.bz2"), platform);
        CefRuntimePackager.Result result = packageArchive(
                archive, temporary.resolve("gpu-output-" + platform.cefName()), platform, List.of(), true);

        assertThat(result.files()).noneMatch(path -> path.toLowerCase().contains("swiftshader"));
        assertThat(result.runtimeRoot().resolve(platform.runtimeBinary())).isRegularFile();
    }

    @Test
    void rejectsTraversalEntries() throws Exception {
        Path archive = TestArchives.createTraversal(temporary.resolve("traversal.tar.bz2"));

        assertThatThrownBy(() -> packageArchive(
                        archive, temporary.resolve("traversal-output"), CefPlatform.LINUX_X86_64, List.of(), false))
                .isInstanceOf(java.io.IOException.class)
                .hasMessageContaining("escapes its root");
        assertThat(temporary.resolve("escape")).doesNotExist();
    }

    @Test
    void failsWhenARequestedLocaleDoesNotExist() throws Exception {
        Path archive = TestArchives.create(temporary.resolve("missing-locale.tar.bz2"), CefPlatform.LINUX_X86_64);

        assertThatThrownBy(() -> packageArchive(
                        archive,
                        temporary.resolve("missing-locale-output"),
                        CefPlatform.LINUX_X86_64,
                        List.of("ja"),
                        false))
                .isInstanceOf(java.io.IOException.class)
                .hasMessageContaining("ja");
    }

    @Test
    void emitsStableApiVersion() {
        assertThat(CefRuntimePackager.stableApiVersion("150.0.18+fixture")).isEqualTo("15000");
        assertThat(CefRuntimePackager.stableApiVersion("116.0.27+fixture")).isEqualTo("116");
    }

    @Test
    void recognizesOnlyCompleteMatchingOutputAsCurrent() throws Exception {
        CefPlatform platform = CefPlatform.LINUX_X86_64;
        Path archive = TestArchives.create(temporary.resolve("current.tar.bz2"), platform);
        Path output = temporary.resolve("current-output");
        CefRuntimePackager.Request request = request(archive, output, platform, List.of("en-US"), true);
        CefRuntimePackager packager = new CefRuntimePackager();

        assertThat(packager.isCurrent(request)).isFalse();
        CefRuntimePackager.Result result = packager.packageArchive(request);
        assertThat(packager.isCurrent(request)).isTrue();

        Files.delete(result.runtimeRoot().resolve(platform.runtimeBinary()));
        assertThat(packager.isCurrent(request)).isFalse();
    }

    private CefRuntimePackager.Result packageArchive(
            Path archive, Path output, CefPlatform platform, List<String> locales, boolean withoutSwiftShader)
            throws Exception {
        return new CefRuntimePackager().packageArchive(request(archive, output, platform, locales, withoutSwiftShader));
    }

    private CefRuntimePackager.Request request(
            Path archive, Path output, CefPlatform platform, List<String> locales, boolean withoutSwiftShader)
            throws Exception {
        return new CefRuntimePackager.Request(
                "150.0.0+fixture",
                platform,
                archive,
                output,
                locales,
                withoutSwiftShader,
                Digests.digest(archive, "SHA-1"),
                Digests.digest(archive, "SHA-256"),
                false);
    }
}
