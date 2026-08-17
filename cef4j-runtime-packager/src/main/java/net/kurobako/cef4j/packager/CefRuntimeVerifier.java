package net.kurobako.cef4j.packager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class CefRuntimeVerifier {
    List<String> verify(Path root, CefPlatform platform) throws IOException {
        List<String> problems = new ArrayList<>();
        Path runtime = root.resolve("cef-runtime").resolve(platform.cefName());
        if (!Files.isDirectory(runtime)) {
            problems.add("missing runtime directory: " + runtime);
            return problems;
        }
        if (!Files.isRegularFile(runtime.resolve("file-list.txt"))) problems.add("missing file-list.txt");
        if (!Files.isRegularFile(runtime.resolve("cef-runtime.properties"))) {
            problems.add("missing cef-runtime.properties");
        }
        if (!Files.isRegularFile(runtime.resolve(platform.runtimeBinary()))) {
            problems.add("missing runtime binary: " + platform.runtimeBinary());
        }
        return problems;
    }
}
