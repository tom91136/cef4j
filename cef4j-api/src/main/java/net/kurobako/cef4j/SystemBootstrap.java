package net.kurobako.cef4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads the cef4j native library and prepares the CEF runtime environment.
 *
 * <p>Loading strategy:
 *
 * <ol>
 *   <li>Try {@code System.loadLibrary("cef4j")} (honors {@code java.library.path} and {@code LD_LIBRARY_PATH})
 *   <li>If that fails, extract {@code libcef4j.so} and {@code cef4j_helper} from classpath resources to a temporary
 *       directory, symlink CEF shared libraries from {@code LIBCEF_DIR} into it, and load from there
 * </ol>
 *
 * <p>The {@code LIBCEF_DIR} environment variable must point to a directory containing {@code libcef.so} and CEF
 * resource files (locales, .pak, .dat, .bin). This is typically the {@code Release/} directory of a CEF binary
 * distribution.
 */
public final class SystemBootstrap {

    private static final Logger log = LoggerFactory.getLogger(SystemBootstrap.class);

    private SystemBootstrap() {}

    private static volatile boolean loaded = false;
    private static volatile Path extractionDir;
    private static volatile Path cachedLibcefDir;
    private static volatile boolean libcefDirResolved = false;

    /**
     * Load the native library. Tries system path first, falls back to classpath extraction with LIBCEF_DIR.
     *
     * @throws UnsatisfiedLinkError if the library cannot be loaded
     */
    public static synchronized void load() {
        if (loaded) return;

        // Strategy 1: system library path (java.library.path / LD_LIBRARY_PATH)
        try {
            log.debug("Trying System.loadLibrary(\"cef4j\")");
            System.loadLibrary("cef4j");
            loaded = true;
            log.info("Loaded cef4j native library from system path");
        } catch (UnsatisfiedLinkError e) {
            log.debug("System library path failed: {}", e.getMessage());
            // Strategy 2: extract from classpath + LIBCEF_DIR
            try {
                loadFromClasspath();
            } catch (IOException ex) {
                throw new UnsatisfiedLinkError(ex.getMessage());
            }
        }

        // Redirect native stderr to SLF4J before cef_initialize
        NativeStderr.install();
    }

    /**
     * Load the native library from an explicit filesystem path.
     *
     * @param libraryPath the directory containing libcef4j.so
     * @throws UnsatisfiedLinkError if the library cannot be loaded
     */
    public static synchronized void loadFrom(Path libraryPath) {
        if (loaded) return;
        String libName = OS.mapLibraryName("cef4j");
        Path fullPath = libraryPath.resolve(libName);
        log.debug("Loading native library from explicit path: {}", fullPath);
        System.load(fullPath.toAbsolutePath().toString());
        loaded = true;
        log.info("Loaded cef4j native library from {}", fullPath);
    }

    /** Returns whether the native library has been loaded. */
    public static boolean isLoaded() {
        return loaded;
    }

    /**
     * Returns the directory where native files were extracted, or null if the library was loaded from the system path.
     */
    public static Path getExtractionDir() {
        return extractionDir;
    }

    /** Returns the resolved LIBCEF_DIR, or null if not set. Result is cached after first call. */
    public static Path getLibcefDir() {
        if (libcefDirResolved) return cachedLibcefDir;
        String env = System.getenv("LIBCEF_DIR");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("cef4j.libcef.dir");
        }
        Path result;
        if (env != null) {
            result = Paths.get(env);
        } else {
            result = discoverCefDist();
        }
        cachedLibcefDir = result;
        libcefDirResolved = true;
        return result;
    }

    // Walk up from working directory looking for .cef-dist/*/Release/libcef.so,
    // so IDE run configs work without setting LIBCEF_DIR.
    private static Path discoverCefDist() {
        String cefLib = OS.isWindows() ? "libcef.dll" : "libcef.so";
        Path dir = Paths.get(System.getProperty("user.dir"));
        for (int i = 0; i < 5 && dir != null; i++, dir = dir.getParent()) {
            Path cefDist = dir.resolve(".cef-dist");
            if (!Files.isDirectory(cefDist)) continue;
            try (java.util.stream.Stream<Path> children = Files.list(cefDist)) {
                Path found = children.map(child -> child.resolve("Release"))
                        .filter(release -> Files.isRegularFile(release.resolve(cefLib)))
                        .findFirst()
                        .orElse(null);
                if (found != null) {
                    log.debug("Auto-discovered CEF dist: {}", found);
                    return found;
                }
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    /**
     * Returns the path to the cef4j_helper executable. Prefers the extraction directory, falls back to LIBCEF_DIR, then
     * null.
     */
    public static String getHelperPath() {
        if (extractionDir != null) {
            Path helper = extractionDir.resolve("cef4j_helper");
            if (Files.isExecutable(helper)) {
                return helper.toAbsolutePath().toString();
            }
        }
        Path libcefDir = getLibcefDir();
        if (libcefDir != null) {
            Path helper = libcefDir.resolve("cef4j_helper");
            if (Files.isExecutable(helper)) {
                return helper.toAbsolutePath().toString();
            }
        }
        return null;
    }

    private static void loadFromClasspath() throws IOException {
        String platform = OS.getPlatform();
        String libName = OS.mapLibraryName("cef4j");
        String resourceBase = "native/" + platform + "/";
        log.debug("Detected platform: {}, native lib: {}", platform, libName);

        // Extract to a persistent cache dir to avoid re-extraction
        Path cacheDir = Paths.get(System.getProperty("java.io.tmpdir"), "cef4j-cache", platform);
        log.debug("Extraction cache dir: {}", cacheDir);
        Files.createDirectories(cacheDir);

        // Extract libcef4j.so and cef4j_helper from cef4j-java jar resources
        extractResource(resourceBase + libName, cacheDir.resolve(libName));
        Path helper = cacheDir.resolve(OS.isWindows() ? "cef4j_helper.exe" : "cef4j_helper");
        extractResource(resourceBase + helper.getFileName().toString(), helper);
        helper.toFile().setExecutable(true);

        if (isPlatformJarAvailable()) {
            log.debug("CEF runtime found in platform jar, extracting");
            extractPlatformRuntime(cacheDir);
        } else {
            log.debug("cef4j-platform jar not on classpath, checking LIBCEF_DIR");
            // Fallback: symlink from LIBCEF_DIR
            Path libcefDir = getLibcefDir();
            if (libcefDir == null) {
                throw new IOException("CEF runtime not found: cef4j-platform jar not on classpath,"
                        + " LIBCEF_DIR env var not set, -Dcef4j.libcef.dir system property not set");
            }
            if (!Files.isDirectory(libcefDir)) {
                throw new IOException("LIBCEF_DIR is not a directory: " + libcefDir);
            }
            log.debug("Linking CEF runtime from LIBCEF_DIR: {}", libcefDir);
            linkCefRuntime(libcefDir, cacheDir);
        }

        extractionDir = cacheDir;

        // Load libcef.so first so the linker can resolve libcef4j.so's dependency
        String cefLib = OS.isWindows() ? "libcef.dll" : "libcef.so";
        System.load(cacheDir.resolve(cefLib).toAbsolutePath().toString());
        System.load(cacheDir.resolve(libName).toAbsolutePath().toString());
        loaded = true;
        log.info("Loaded cef4j native library from classpath extraction: {}", cacheDir);
    }

    private static boolean isPlatformJarAvailable() {
        String sentinel = OS.isWindows() ? "cef-runtime/libcef.dll" : "cef-runtime/libcef.so";
        return SystemBootstrap.class.getClassLoader().getResource(sentinel) != null;
    }

    private static void extractPlatformRuntime(Path cacheDir) throws IOException {
        try (InputStream in = SystemBootstrap.class.getClassLoader().getResourceAsStream("cef-runtime/file-list.txt")) {
            if (in == null) {
                throw new IOException("cef-runtime/file-list.txt not found in platform jar");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                Path target = cacheDir.resolve(line);
                // Skip if already extracted and non-empty
                if (Files.exists(target) && Files.size(target) > 0) continue;
                Files.createDirectories(target.getParent());
                extractResource("cef-runtime/" + line, target);
                if (line.endsWith(".so") || line.endsWith(".so.1")) {
                    target.toFile().setExecutable(true);
                }
            }
        }
    }

    private static void linkCefRuntime(Path libcefDir, Path cacheDir) throws IOException {
        // CEF resolves libcef.so symlinks and looks for resources (icudtl.dat,
        // .pak files, locales/) next to the real libcef.so. Symlink Resources/
        // into LIBCEF_DIR (Release/) if not already present.
        Path resourcesDir = libcefDir.resolveSibling("Resources");
        if (Files.isDirectory(resourcesDir)) {
            for (String res :
                    new String[] {"icudtl.dat", "resources.pak", "chrome_100_percent.pak", "chrome_200_percent.pak"}) {
                symlinkIfNeeded(resourcesDir.resolve(res), libcefDir.resolve(res));
            }
            symlinkIfNeeded(resourcesDir.resolve("locales"), libcefDir.resolve("locales"));
        }

        // Symlink CEF shared libraries from LIBCEF_DIR so the $ORIGIN rpath resolves
        for (String lib :
                new String[] {"libcef.so", "libEGL.so", "libGLESv2.so", "libvk_swiftshader.so", "libvulkan.so.1"}) {
            symlinkIfNeeded(libcefDir.resolve(lib), cacheDir.resolve(lib));
        }

        // Also symlink resources into cache dir for subprocess helper (cef4j_helper)
        for (String res : new String[] {
            "icudtl.dat",
            "resources.pak",
            "chrome_100_percent.pak",
            "chrome_200_percent.pak",
            "v8_context_snapshot.bin",
            "vk_swiftshader_icd.json"
        }) {
            symlinkIfNeeded(libcefDir.resolve(res), cacheDir.resolve(res));
        }
        symlinkIfNeeded(libcefDir.resolve("locales"), cacheDir.resolve("locales"));
    }

    private static void extractResource(String resourcePath, Path target) throws IOException {
        try (InputStream in = SystemBootstrap.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IOException("Resource not found on classpath: " + resourcePath);
            }
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void symlinkIfNeeded(Path source, Path link) throws IOException {
        if (!Files.exists(source)) return;
        if (Files.isSymbolicLink(link)) {
            // Update if target changed
            if (Files.readSymbolicLink(link).equals(source.toAbsolutePath())) return;
            Files.delete(link);
        } else if (Files.exists(link)) {
            // Real file/directory already exists - leave it alone
            return;
        }
        Files.createSymbolicLink(link, source.toAbsolutePath());
    }
}
