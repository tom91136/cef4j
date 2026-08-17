package net.kurobako.cef4j.packager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

/** Converts an upstream CEF minimal archive into cef4j's classpath runtime layout. */
public final class CefRuntimePackager {
    private static final String MAC_FRAMEWORK = "Chromium Embedded Framework.framework/";
    private static final long MAX_ENTRY_SIZE = 3L * 1024 * 1024 * 1024;

    /** Creates a stateless runtime packager. */
    public CefRuntimePackager() {}

    /** Returns whether an existing output is complete and was produced from the same material inputs. */
    public boolean isCurrent(Request request) throws IOException {
        Objects.requireNonNull(request, "request");
        Path runtimeRoot = request.output
                .toAbsolutePath()
                .normalize()
                .resolve("cef-runtime")
                .resolve(request.platform.cefName());
        Path metadata = runtimeRoot.resolve("cef-runtime.properties");
        Path fileList = runtimeRoot.resolve("file-list.txt");
        if (!Files.isRegularFile(metadata) || !Files.isRegularFile(fileList)) return false;

        Properties properties = new Properties();
        try (var reader = Files.newBufferedReader(metadata, StandardCharsets.UTF_8)) {
            properties.load(reader);
        }
        if (!request.cefVersion.equals(properties.getProperty("cef.version"))) return false;
        if (!request.platform.cefName().equals(properties.getProperty("cef.platform"))) return false;
        if (!request.archiveSha256.equalsIgnoreCase(properties.getProperty("archive.sha256", ""))) return false;
        if (!String.join(",", normalizeLocales(request.locales)).equals(properties.getProperty("locales", ""))) {
            return false;
        }
        if (!Boolean.toString(!request.withoutSwiftShader).equals(properties.getProperty("swiftshader"))) return false;
        for (String relative : Files.readAllLines(fileList, StandardCharsets.UTF_8)) {
            if (relative.isBlank() || !Files.isRegularFile(runtimeRoot.resolve(relative))) return false;
        }
        return true;
    }

    /** Creates a reusable cef4j resource tree from an upstream minimal archive. */
    public Result packageArchive(Request request) throws IOException {
        Objects.requireNonNull(request, "request");
        Path output = request.output.toAbsolutePath().normalize();
        Files.createDirectories(output);
        Path runtimeRoot = output.resolve("cef-runtime").resolve(request.platform.cefName()).normalize();
        requireContained(output, runtimeRoot);
        Path temporary = Files.createTempDirectory(output, ".cef4j-runtime-");
        Path stagedRoot = temporary.resolve(request.platform.cefName());
        Files.createDirectories(stagedRoot);

        Set<String> files = new TreeSet<>();
        Set<String> matchedLocales = new TreeSet<>();
        Set<String> requestedLocales = normalizeLocales(request.locales);
        try {
            extract(request, stagedRoot, files, matchedLocales, requestedLocales);
            verifyRequired(stagedRoot, request.platform);
            if (!requestedLocales.isEmpty()) verifyLocales(requestedLocales, matchedLocales);
            writeMetadata(request, stagedRoot, files);
            files.add("cef-runtime.properties");
            writeFileList(stagedRoot, files);

            deleteTree(runtimeRoot);
            Files.createDirectories(runtimeRoot.getParent());
            try {
                Files.move(stagedRoot, runtimeRoot, StandardCopyOption.ATOMIC_MOVE);
            } catch (java.nio.file.AtomicMoveNotSupportedException ignored) {
                Files.move(stagedRoot, runtimeRoot);
            }
            return new Result(runtimeRoot, List.copyOf(files), matchedLocales);
        } finally {
            deleteTree(temporary);
        }
    }

    private void extract(
            Request request,
            Path stagedRoot,
            Set<String> files,
            Set<String> matchedLocales,
            Set<String> requestedLocales)
            throws IOException {
        try (InputStream fileInput = Files.newInputStream(request.archive);
                BZip2CompressorInputStream bzipInput = new BZip2CompressorInputStream(fileInput, true);
                TarArchiveInputStream tarInput = new TarArchiveInputStream(bzipInput)) {
            TarArchiveEntry entry;
            while ((entry = tarInput.getNextEntry()) != null) {
                if (entry.isDirectory() || entry.isSymbolicLink() || entry.isLink()) continue;
                if (!entry.isFile()) continue;
                if (entry.getSize() < 0 || entry.getSize() > MAX_ENTRY_SIZE) {
                    throw new IOException("CEF archive entry has an unsafe size: " + entry.getName());
                }
                String source = safeArchivePath(entry.getName());
                String relative = mapRuntimePath(source, request.platform);
                if (relative == null || isBuildOnly(relative)) continue;
                if (!requestedLocales.isEmpty() && isLocale(relative, request.platform)) {
                    String locale = localeOf(relative, request.platform);
                    if (!matchesLocale(locale, requestedLocales)) continue;
                    matchedLocales.add(normalizeLocaleBase(locale));
                }
                if (request.withoutSwiftShader && isSwiftShader(relative)) continue;

                Path target = stagedRoot.resolve(relative).normalize();
                requireContained(stagedRoot, target);
                Files.createDirectories(target.getParent());
                Files.copy(tarInput, target, StandardCopyOption.REPLACE_EXISTING);
                files.add(relative.replace('\\', '/'));
            }
        }
    }

    private static String safeArchivePath(String raw) throws IOException {
        String normalized = raw.replace('\\', '/');
        if (normalized.startsWith("/") || normalized.matches("^[A-Za-z]:.*")) {
            throw new IOException("CEF archive contains an absolute path: " + raw);
        }
        Path path = Path.of(normalized).normalize();
        if (path.startsWith("..")) throw new IOException("CEF archive path escapes its root: " + raw);
        return path.toString().replace('\\', '/');
    }

    private static String mapRuntimePath(String source, CefPlatform platform) {
        int firstSlash = source.indexOf('/');
        if (firstSlash < 0) return null;
        String relative = source.substring(firstSlash + 1);
        if (relative.equals("LICENSE.txt")) return "CEF-LICENSE.txt";
        if (relative.equals("CREDITS.html")) return "CEF-CREDITS.html";
        if (platform.isMacOS()) {
            return relative.startsWith("Release/" + MAC_FRAMEWORK)
                    ? relative.substring("Release/".length())
                    : null;
        }
        if (relative.startsWith("Release/")) return relative.substring("Release/".length());
        if (relative.startsWith("Resources/")) return relative.substring("Resources/".length());
        return null;
    }

    private static boolean isBuildOnly(String relative) {
        String lower = relative.toLowerCase(Locale.ROOT);
        return lower.endsWith(".lib") || lower.endsWith(".a") || lower.endsWith(".pdb") || lower.endsWith(".exp");
    }

    private static boolean isSwiftShader(String relative) {
        return relative.toLowerCase(Locale.ROOT).contains("swiftshader");
    }

    private static boolean isLocale(String relative, CefPlatform platform) {
        String normalized = relative.replace('\\', '/');
        if (platform.isMacOS()) {
            return normalized.startsWith(MAC_FRAMEWORK + "Resources/") && normalized.contains(".lproj/");
        }
        return normalized.startsWith("locales/") && normalized.endsWith(".pak");
    }

    private static String localeOf(String relative, CefPlatform platform) {
        if (platform.isMacOS()) {
            String prefix = MAC_FRAMEWORK + "Resources/";
            String name = relative.substring(prefix.length(), relative.indexOf(".lproj/"));
            return name.replace('_', '-');
        }
        String name = relative.substring("locales/".length(), relative.length() - ".pak".length());
        return name.replace('_', '-');
    }

    private static boolean matchesLocale(String candidate, Set<String> requested) {
        String base = normalizeLocaleBase(candidate);
        if (requested.contains(base)) return true;
        if (base.equals("en") && requested.contains("en-us")) return true;
        return requested.stream().anyMatch(value -> value.startsWith(base + "-") || base.startsWith(value + "-"));
    }

    private static Set<String> normalizeLocales(Collection<String> locales) {
        return locales.stream()
                .flatMap(value -> List.of(value.split(",")).stream())
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .map(CefRuntimePackager::normalizeLocaleBase)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static String normalizeLocaleBase(String locale) {
        String normalized = locale.replace('_', '-').toLowerCase(Locale.ROOT);
        for (String suffix : List.of("-feminine", "-masculine", "-neuter")) {
            if (normalized.endsWith(suffix)) return normalized.substring(0, normalized.length() - suffix.length());
        }
        return normalized;
    }

    private static void verifyLocales(Set<String> requested, Set<String> matched) throws IOException {
        List<String> missing = requested.stream()
                .filter(locale -> !matched.contains(locale) && !(locale.equals("en-us") && matched.contains("en")))
                .collect(Collectors.toList());
        if (!missing.isEmpty()) throw new IOException("Requested CEF locales were not found: " + missing);
    }

    private static void verifyRequired(Path root, CefPlatform platform) throws IOException {
        List<String> required = new ArrayList<>();
        required.add(platform.runtimeBinary());
        if (platform.isMacOS()) {
            required.add(MAC_FRAMEWORK + "Resources/icudtl.dat");
        } else {
            required.add("icudtl.dat");
            required.add("v8_context_snapshot.bin");
            if (platform.isWindows()) required.add("chrome_elf.dll");
        }
        List<String> missing = required.stream()
                .filter(path -> !Files.isRegularFile(root.resolve(path)))
                .collect(Collectors.toList());
        if (platform.isMacOS()) {
            Path resources = root.resolve(MAC_FRAMEWORK + "Resources");
            boolean snapshot;
            try (var entries = Files.list(resources)) {
                snapshot = entries.anyMatch(path -> path.getFileName().toString().startsWith("v8_context_snapshot"));
            }
            if (!snapshot) missing.add(MAC_FRAMEWORK + "Resources/v8_context_snapshot.*.bin");
        }
        if (!missing.isEmpty()) throw new IOException("CEF runtime is missing required files: " + missing);
    }

    private static void writeMetadata(Request request, Path root, Set<String> files) throws IOException {
        Path metadata = root.resolve("cef-runtime.properties");
        try (BufferedWriter writer = Files.newBufferedWriter(metadata, StandardCharsets.UTF_8)) {
            writer.write("format.version=1\n");
            writer.write("cef.version=" + request.cefVersion + "\n");
            writer.write("cef.api.version=" + stableApiVersion(request.cefVersion) + "\n");
            writer.write("cef.platform=" + request.platform.cefName() + "\n");
            writer.write("archive.sha1=" + request.archiveSha1 + "\n");
            writer.write("archive.sha256=" + request.archiveSha256 + "\n");
            writer.write("archive.upstream-verified=" + request.upstreamVerified + "\n");
            writer.write("locales=" + String.join(",", normalizeLocales(request.locales)) + "\n");
            writer.write("swiftshader=" + !request.withoutSwiftShader + "\n");
            writer.write("file.count=" + files.size() + "\n");
            writer.write("generated.at=" + Instant.EPOCH + "\n");
        }
    }

    static String stableApiVersion(String cefVersion) {
        int dot = cefVersion.indexOf('.');
        String majorText = dot < 0 ? cefVersion : cefVersion.substring(0, dot);
        int major = Integer.parseInt(majorText);
        return major >= 133 ? Integer.toString(major * 100) : Integer.toString(major);
    }

    private static void writeFileList(Path root, Set<String> files) throws IOException {
        Files.write(root.resolve("file-list.txt"), files, StandardCharsets.UTF_8);
    }

    private static void requireContained(Path root, Path target) throws IOException {
        if (!target.normalize().startsWith(root.normalize())) {
            throw new IOException("Output path escapes its root: " + target);
        }
    }

    static void deleteTree(Path root) throws IOException {
        if (!Files.exists(root)) return;
        Files.walkFileTree(root, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path directory, IOException exception) throws IOException {
                if (exception != null) throw exception;
                Files.delete(directory);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /** Immutable inputs for one archive packaging operation. */
    public static final class Request {
        final String cefVersion;
        final CefPlatform platform;
        final Path archive;
        final Path output;
        final List<String> locales;
        final boolean withoutSwiftShader;
        final String archiveSha1;
        final String archiveSha256;
        final boolean upstreamVerified;

        /**
         * Creates packaging inputs.
         *
         * @param cefVersion exact upstream CEF version
         * @param platform target platform
         * @param archive upstream minimal archive
         * @param output generated-resources root
         * @param locales locale families to retain, or an empty list for all
         * @param withoutSwiftShader whether to omit SwiftShader files
         * @param archiveSha1 computed archive SHA-1
         * @param archiveSha256 computed archive SHA-256
         * @param upstreamVerified whether the SHA-1 was verified against the upstream index
         */
        public Request(
                String cefVersion,
                CefPlatform platform,
                Path archive,
                Path output,
                List<String> locales,
                boolean withoutSwiftShader,
                String archiveSha1,
                String archiveSha256,
                boolean upstreamVerified) {
            this.cefVersion = Objects.requireNonNull(cefVersion, "cefVersion");
            this.platform = Objects.requireNonNull(platform, "platform");
            this.archive = Objects.requireNonNull(archive, "archive");
            this.output = Objects.requireNonNull(output, "output");
            this.locales = List.copyOf(locales);
            this.withoutSwiftShader = withoutSwiftShader;
            this.archiveSha1 = Objects.requireNonNull(archiveSha1, "archiveSha1");
            this.archiveSha256 = Objects.requireNonNull(archiveSha256, "archiveSha256");
            this.upstreamVerified = upstreamVerified;
        }
    }

    /** Details of a completed packaging operation. */
    public static final class Result {
        private final Path runtimeRoot;
        private final List<String> files;
        private final Set<String> locales;

        Result(Path runtimeRoot, List<String> files, Set<String> locales) {
            this.runtimeRoot = runtimeRoot;
            this.files = files;
            this.locales = Set.copyOf(locales);
        }

        /** Returns the generated platform resource directory. */
        public Path runtimeRoot() {
            return runtimeRoot;
        }

        /** Returns normalized runtime file paths in deterministic order. */
        public List<String> files() {
            return files;
        }

        /** Returns locale families retained from the archive. */
        public Set<String> locales() {
            return locales;
        }
    }
}
