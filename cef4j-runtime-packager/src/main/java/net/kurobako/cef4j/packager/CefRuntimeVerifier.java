package net.kurobako.cef4j.packager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

final class CefRuntimeVerifier {
    List<String> verify(Path root, CefPlatform platform) throws IOException {
        List<String> problems = new ArrayList<>();
        Path runtime = root.resolve("cef-runtime").resolve(platform.cefName());
        if (!Files.isDirectory(runtime)) {
            problems.add("missing runtime directory: " + runtime);
            return problems;
        }
        problems.addAll(verifyRuntime(runtime, platform));
        return problems;
    }

    static List<String> verifyRuntime(Path runtime, CefPlatform platform) throws IOException {
        List<String> problems = new ArrayList<>();
        Path fileList = runtime.resolve("file-list.txt");
        Path metadata = runtime.resolve("cef-runtime.properties");
        if (!Files.isRegularFile(fileList)) problems.add("missing file-list.txt");
        if (!Files.isRegularFile(metadata)) problems.add("missing cef-runtime.properties");
        if (!problems.isEmpty()) return problems;

        List<String> listed = Files.readAllLines(fileList, StandardCharsets.UTF_8);
        Set<String> unique = new HashSet<>();
        for (String relative : listed) {
            if (relative.isBlank()) {
                problems.add("file-list.txt contains a blank entry");
                continue;
            }
            Path path = Path.of(relative);
            Path resolved = runtime.resolve(path).normalize();
            if (path.isAbsolute() || path.startsWith("..") || !resolved.startsWith(runtime.normalize())) {
                problems.add("file-list.txt entry escapes runtime: " + relative);
                continue;
            }
            String normalized = path.toString().replace('\\', '/');
            if (!unique.add(normalized)) problems.add("duplicate file-list.txt entry: " + relative);
            if (!Files.isRegularFile(resolved)) problems.add("missing listed file: " + relative);
            else if (Files.size(resolved) == 0) problems.add("empty listed file: " + relative);
        }

        try (var files = Files.walk(runtime)) {
            for (Path file : files.filter(Files::isRegularFile).collect(Collectors.toList())) {
                String relative = runtime.relativize(file).toString().replace('\\', '/');
                if (!relative.equals("file-list.txt") && !unique.contains(relative)) {
                    problems.add("unlisted runtime file: " + relative);
                }
            }
        }

        Properties properties = new Properties();
        try (var reader = Files.newBufferedReader(metadata, StandardCharsets.UTF_8)) {
            properties.load(reader);
        }
        requireProperty(properties, "format.version", "1", problems);
        requireProperty(properties, "cef.platform", platform.cefName(), problems);
        requireNonBlank(properties, "cef.version", problems);
        requireNonBlank(properties, "cef.api.version", problems);
        String sha256 = properties.getProperty("archive.sha256", "");
        if (!sha256.matches("(?i)[0-9a-f]{64}")) problems.add("invalid archive.sha256 metadata");
        try {
            int expected = Integer.parseInt(properties.getProperty("file.count", ""));
            int actual = unique.contains("cef-runtime.properties") ? unique.size() - 1 : unique.size();
            if (expected != actual) problems.add("file.count metadata is " + expected + " but expected " + actual);
        } catch (NumberFormatException ignored) {
            problems.add("invalid file.count metadata");
        }
        for (String missing : CefRuntimePackager.missingRequired(runtime, platform)) {
            problems.add("missing required file: " + missing);
        }
        return problems;
    }

    private static void requireProperty(Properties properties, String name, String expected, List<String> problems) {
        if (!expected.equals(properties.getProperty(name))) problems.add("invalid " + name + " metadata");
    }

    private static void requireNonBlank(Properties properties, String name, List<String> problems) {
        if (properties.getProperty(name, "").isBlank()) problems.add("missing " + name + " metadata");
    }
}
