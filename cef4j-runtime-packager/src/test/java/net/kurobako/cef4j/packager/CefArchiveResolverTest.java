package net.kurobako.cef4j.packager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CefArchiveResolverTest {
    private static final String VERSION = "150.0.0+fixture";

    @TempDir
    Path temporary;

    @Test
    void acceptsAnExplicitArchiveWithPinnedSha256() throws Exception {
        Path archive = TestArchives.create(temporary.resolve("input.tar.bz2"), CefPlatform.LINUX_X86_64);
        String sha256 = Digests.digest(archive, "SHA-256");

        CefArchiveResolver.ResolvedArchive resolved = new CefArchiveResolver().resolve(
                VERSION,
                CefPlatform.LINUX_X86_64,
                temporary.resolve("cache"),
                archive,
                sha256,
                true,
                URI.create("https://invalid.example/"),
                URI.create("https://invalid.example/index.json"));

        assertThat(resolved.path).isEqualTo(archive);
        assertThat(resolved.sha256).isEqualTo(sha256);
        assertThat(resolved.upstreamVerified).isFalse();
    }

    @Test
    void rejectsAnExplicitArchiveWithTheWrongSha256() throws Exception {
        Path archive = TestArchives.create(temporary.resolve("input.tar.bz2"), CefPlatform.LINUX_X86_64);

        assertThatThrownBy(() -> new CefArchiveResolver().resolve(
                        VERSION,
                        CefPlatform.LINUX_X86_64,
                        temporary.resolve("cache"),
                        archive,
                        "0".repeat(64),
                        true,
                        URI.create("https://invalid.example/"),
                        URI.create("https://invalid.example/index.json")))
                .isInstanceOf(java.io.IOException.class)
                .hasMessageContaining("SHA-256 mismatch");
    }

    @Test
    void verifiesACachedArchiveOfflineFromItsUpstreamSidecar() throws Exception {
        Path cache = temporary.resolve("cache");
        Files.createDirectories(cache);
        CefPlatform platform = CefPlatform.LINUX_X86_64;
        Path archive = TestArchives.create(cache.resolve(platform.archiveName(VERSION)), platform);
        Files.writeString(cache.resolve(platform.archiveName(VERSION) + ".sha1"), Digests.digest(archive, "SHA-1"));

        CefArchiveResolver.ResolvedArchive resolved = new CefArchiveResolver().resolve(
                VERSION,
                platform,
                cache,
                null,
                null,
                true,
                URI.create("https://invalid.example/"),
                URI.create("https://invalid.example/index.json"));

        assertThat(resolved.upstreamVerified).isTrue();
    }
}
