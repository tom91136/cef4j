package net.kurobako.cef4j.packager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Locale;
import java.util.Objects;

final class CefArchiveResolver {
    static final URI DEFAULT_BASE_URI = URI.create("https://cef-builds.spotifycdn.com/");
    static final URI DEFAULT_INDEX_URI = DEFAULT_BASE_URI.resolve("index.json");

    private final HttpClient client;
    private final ObjectMapper mapper;

    CefArchiveResolver() {
        this(HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build(), new ObjectMapper());
    }

    CefArchiveResolver(HttpClient client, ObjectMapper mapper) {
        this.client = Objects.requireNonNull(client, "client");
        this.mapper = Objects.requireNonNull(mapper, "mapper");
    }

    ResolvedArchive resolve(
            String version,
            CefPlatform platform,
            Path cacheDirectory,
            Path explicitArchive,
            String expectedSha256,
            boolean offline,
            URI baseUri,
            URI indexUri)
            throws IOException, InterruptedException {
        Files.createDirectories(cacheDirectory);
        String archiveName = platform.archiveName(version);
        Path archive = explicitArchive == null ? cacheDirectory.resolve(archiveName) : explicitArchive;
        String upstreamSha1 = readCachedSha1(cacheDirectory.resolve(archiveName + ".sha1"));

        if (!Files.isRegularFile(archive)) {
            if (explicitArchive != null) {
                throw new IOException("CEF archive does not exist: " + explicitArchive);
            }
            if (offline) {
                throw new IOException("CEF archive is not cached in offline mode: " + archive);
            }
            upstreamSha1 = findUpstreamSha1(indexUri, platform, version, archiveName);
            download(baseUri.resolve(archiveName), archive);
            Files.writeString(cacheDirectory.resolve(archiveName + ".sha1"), upstreamSha1 + System.lineSeparator());
        } else if (upstreamSha1 == null && explicitArchive == null && !offline) {
            upstreamSha1 = findUpstreamSha1(indexUri, platform, version, archiveName);
            Files.writeString(cacheDirectory.resolve(archiveName + ".sha1"), upstreamSha1 + System.lineSeparator());
        }

        String actualSha1 = Digests.digest(archive, "SHA-1");
        if (upstreamSha1 != null && !upstreamSha1.equalsIgnoreCase(actualSha1)) {
            throw new IOException("CEF archive SHA-1 mismatch: expected " + upstreamSha1 + ", got " + actualSha1);
        }
        String actualSha256 = Digests.digest(archive, "SHA-256");
        if (expectedSha256 != null && !expectedSha256.equalsIgnoreCase(actualSha256)) {
            throw new IOException("CEF archive SHA-256 mismatch: expected " + expectedSha256 + ", got " + actualSha256);
        }
        return new ResolvedArchive(archive, actualSha1, actualSha256, upstreamSha1 != null);
    }

    private String findUpstreamSha1(URI indexUri, CefPlatform platform, String version, String archiveName)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(indexUri).timeout(Duration.ofMinutes(2)).GET().build();
        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        if (response.statusCode() != 200) {
            response.body().close();
            throw new IOException("CEF index returned HTTP " + response.statusCode() + ": " + indexUri);
        }
        try (InputStream input = response.body()) {
            JsonNode versions = mapper.readTree(input).path(platform.cefName()).path("versions");
            for (JsonNode candidate : versions) {
                if (!version.equals(candidate.path("cef_version").asText())) continue;
                for (JsonNode file : candidate.path("files")) {
                    if (archiveName.equals(file.path("name").asText())) {
                        String sha1 = file.path("sha1").asText();
                        if (sha1.matches("(?i)[0-9a-f]{40}")) return sha1.toLowerCase(Locale.ROOT);
                    }
                }
            }
        }
        throw new IOException("CEF index has no checksum for " + archiveName);
    }

    private void download(URI uri, Path destination) throws IOException, InterruptedException {
        if (!"https".equalsIgnoreCase(uri.getScheme())) {
            throw new IOException("Refusing non-HTTPS CEF download: " + uri);
        }
        Path parent = destination.toAbsolutePath().getParent();
        if (parent == null) throw new IOException("CEF archive has no parent directory: " + destination);
        Files.createDirectories(parent);
        Path temporary = Files.createTempFile(parent, destination.getFileName().toString(), ".part");
        try {
            HttpRequest request = HttpRequest.newBuilder(uri).timeout(Duration.ofMinutes(10)).GET().build();
            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(temporary));
            if (response.statusCode() != 200) {
                throw new IOException("CEF archive returned HTTP " + response.statusCode() + ": " + uri);
            }
            Files.move(temporary, destination, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temporary);
        }
    }

    private static String readCachedSha1(Path sidecar) throws IOException {
        if (!Files.isRegularFile(sidecar)) return null;
        String value = Files.readString(sidecar).trim();
        return value.matches("(?i)[0-9a-f]{40}") ? value.toLowerCase(Locale.ROOT) : null;
    }

    static final class ResolvedArchive {
        final Path path;
        final String sha1;
        final String sha256;
        final boolean upstreamVerified;

        ResolvedArchive(Path path, String sha1, String sha256, boolean upstreamVerified) {
            this.path = path;
            this.sha1 = sha1;
            this.sha256 = sha256;
            this.upstreamVerified = upstreamVerified;
        }
    }
}
