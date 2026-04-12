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
 *   <li>If that fails, extract {@code libcef4j.so} and {@code cef4j_launcher} from classpath resources to a temporary
 *       directory, symlink CEF shared libraries from {@code LIBCEF_DIR} into it, and load from there
 * </ol>
 *
 * <p>The {@code LIBCEF_DIR} environment variable must point to a directory containing the CEF runtime files. On
 * Linux/Windows this is the {@code Release/} directory of a CEF binary distribution. On macOS this is also the
 * {@code Release/} directory, which contains {@code Chromium Embedded Framework.framework}.
 */
@SuppressWarnings("unused")
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
            if (OS.isMacOS()) {
                // Even when loaded from system path, CEF must be loaded via cef_load_library().
                Path libcefDir = getLibcefDir();
                if (libcefDir == null) {
                    throw new UnsatisfiedLinkError("LIBCEF_DIR must be set on macOS to locate the CEF framework");
                }
                Path frameworkBinary = libcefDir
                        .resolve("Chromium Embedded Framework.framework")
                        .resolve("Chromium Embedded Framework");
                if (!loadCefLibrary0(frameworkBinary.toAbsolutePath().toString())) {
                    throw new UnsatisfiedLinkError("cef_load_library() failed for: " + frameworkBinary);
                }
            }
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
        if (OS.isMacOS()) {
            Path frameworkBinary =
                    libraryPath.resolve("Chromium Embedded Framework.framework").resolve("Chromium Embedded Framework");
            if (!loadCefLibrary0(frameworkBinary.toAbsolutePath().toString())) {
                throw new UnsatisfiedLinkError("cef_load_library() failed for: " + frameworkBinary);
            }
        }
        loaded = true;
        log.info("Loaded cef4j native library from filesystem path {}", fullPath);
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

    // Walk up from working directory looking for .cef-dist/*/Release/ containing the CEF runtime,
    // so IDE run configs work without setting LIBCEF_DIR.
    private static Path discoverCefDist() {
        Path dir = Paths.get(System.getProperty("user.dir"));
        for (int i = 0; i < 5 && dir != null; i++, dir = dir.getParent()) {
            Path cefDist = dir.resolve(".cef-dist");
            if (!Files.isDirectory(cefDist)) continue;
            try (java.util.stream.Stream<Path> children = Files.list(cefDist)) {
                Path found = children.map(child -> child.resolve("Release"))
                        .filter(SystemBootstrap::isValidCefReleaseDir)
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

    private static boolean isValidCefReleaseDir(Path release) {
        if (!Files.isDirectory(release)) return false;
        if (OS.isMacOS()) {
            return Files.isDirectory(release.resolve("Chromium Embedded Framework.framework"));
        }
        String cefLib = OS.isWindows() ? "libcef.dll" : "libcef.so";
        return Files.isRegularFile(release.resolve(cefLib));
    }

    /**
     * Returns the path to the cef4j_launcher executable. Prefers the extraction directory, falls back to LIBCEF_DIR,
     * then null.
     */
    public static String getHelperPath() {
        String launcherName = OS.isWindows() ? "cef4j_launcher.exe" : "cef4j_launcher";
        if (extractionDir != null) {
            Path launcher = extractionDir.resolve(launcherName);
            if (Files.exists(launcher)) {
                return launcher.toAbsolutePath().toString();
            }
        }
        Path libcefDir = getLibcefDir();
        if (libcefDir != null) {
            Path launcher = libcefDir.resolve(launcherName);
            if (Files.exists(launcher)) {
                return launcher.toAbsolutePath().toString();
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

        // Extract libcef4j native library and subprocess launcher from classpath resources
        extractResource(resourceBase + libName, cacheDir.resolve(libName));
        String launcherName = OS.isWindows() ? "cef4j_launcher.exe" : "cef4j_launcher";
        Path launcher = cacheDir.resolve(launcherName);
        extractResource(resourceBase + launcherName, launcher);
        launcher.toFile().setExecutable(true);

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

        if (OS.isMacOS()) {
            // The GPU subprocess (cef4j_launcher --type=gpu-process) looks for ANGLE libraries
            // (libEGL.dylib, libGLESv2.dylib, libvk_swiftshader.dylib) relative to its own
            // executable path (cacheDir), not relative to the framework bundle. Symlink them from
            // the framework's Libraries/ directory so the GPU process finds them.
            Path frameworkLibs =
                    cacheDir.resolve("Chromium Embedded Framework.framework").resolve("Libraries");
            for (String lib : new String[] {
                "libEGL.dylib", "libGLESv2.dylib", "libvk_swiftshader.dylib", "vk_swiftshader_icd.json"
            }) {
                linkOrCopy(frameworkLibs.resolve(lib), cacheDir.resolve(lib));
            }

            // On macOS, the CEF framework must NOT be direct-linked; it is loaded dynamically via
            // cef_load_library() (per CEF README). Load libcef4j.dylib first (it contains the
            // cef_load_library stub), then call loadCefLibrary0() with the explicit framework path.
            System.load(cacheDir.resolve(libName).toAbsolutePath().toString());
            Path frameworkBinary =
                    cacheDir.resolve("Chromium Embedded Framework.framework").resolve("Chromium Embedded Framework");
            if (!loadCefLibrary0(frameworkBinary.toAbsolutePath().toString())) {
                throw new IOException("cef_load_library() failed for: " + frameworkBinary);
            }
            // Remove macOS quarantine attributes so CEF can spawn subprocess helpers.
            try {
                new ProcessBuilder(
                                "xattr",
                                "-r",
                                "-d",
                                "com.apple.quarantine",
                                cacheDir.toAbsolutePath().toString())
                        .redirectErrorStream(true)
                        .start()
                        .waitFor();
            } catch (Exception ignored) {
                // xattr may not exist or quarantine may not be set; not fatal
            }
        } else if (OS.isWindows()) {
            // On Windows, pre-load dependencies in order so each DLL is already
            // resident when the next one tries to import it.  The JVM does not add
            // a DLL's directory to the search path automatically, so we must
            // resolve every required DLL explicitly before loading libcef.dll.
            for (String dep : new String[] {"chrome_elf.dll", "libcef.dll"}) {
                Path depPath = cacheDir.resolve(dep);
                if (Files.exists(depPath)) {
                    System.load(depPath.toAbsolutePath().toString());
                }
            }
            System.load(cacheDir.resolve(libName).toAbsolutePath().toString());
        } else {
            // On Linux, pre-load the CEF shared library so the linker resolves
            // libcef4j's dependency on it before we load libcef4j itself.
            System.load(cacheDir.resolve("libcef.so").toAbsolutePath().toString());
            System.load(cacheDir.resolve(libName).toAbsolutePath().toString());
        }
        loaded = true;
        log.info("Loaded cef4j native library from classpath extraction: {}", cacheDir);
    }

    private static boolean isPlatformJarAvailable() {
        // file-list.txt is present in every platform JAR (Linux, macOS, Windows)
        return SystemBootstrap.class.getClassLoader().getResource("cef-runtime/file-list.txt") != null;
    }

    private static void extractPlatformRuntime(Path cacheDir) throws IOException {
        try (InputStream in = SystemBootstrap.class.getClassLoader().getResourceAsStream("cef-runtime/file-list.txt")) {
            if (in == null) {
                throw new IOException("cef-runtime/file-list.txt not found in platform jar");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().replace('\\', '/');
                if (line.isEmpty()) continue;
                Path target = cacheDir.resolve(line);
                // Skip if already extracted and non-empty
                if (Files.exists(target) && Files.size(target) > 0) continue;
                Files.createDirectories(target.getParent());
                extractResource("cef-runtime/" + line, target);
                // Mark shared libraries and the macOS framework binary as executable
                String fileName = target.getFileName().toString();
                if (line.endsWith(".so")
                        || line.endsWith(".so.1")
                        || line.endsWith(".dylib")
                        || fileName.equals("Chromium Embedded Framework")) {
                    target.toFile().setExecutable(true);
                }
            }
        }
    }

    private static void linkCefRuntime(Path libcefDir, Path cacheDir) throws IOException {
        if (OS.isMacOS()) {
            // On macOS, libcef4j.dylib uses @loader_path to find the framework.
            // Symlink the entire framework next to libcef4j.dylib in the cache dir.
            String framework = "Chromium Embedded Framework.framework";
            linkOrCopy(libcefDir.resolve(framework), cacheDir.resolve(framework));
            return;
        }

        // CEF resolves libcef.so symlinks and looks for resources (icudtl.dat,
        // .pak files, locales/) next to the real libcef.so. Symlink Resources/
        // into LIBCEF_DIR (Release/) if not already present.
        Path resourcesDir = libcefDir.resolveSibling("Resources");
        if (Files.isDirectory(resourcesDir)) {
            for (String res :
                    new String[] {"icudtl.dat", "resources.pak", "chrome_100_percent.pak", "chrome_200_percent.pak"}) {
                linkOrCopy(resourcesDir.resolve(res), libcefDir.resolve(res));
            }
            linkOrCopy(resourcesDir.resolve("locales"), libcefDir.resolve("locales"));
        }

        // Link CEF shared libraries from LIBCEF_DIR into cache dir
        String[] libs;
        if (OS.isWindows()) {
            libs = new String[] {"libcef.dll", "chrome_elf.dll", "d3dcompiler_47.dll", "vk_swiftshader.dll", "vulkan-1.dll"};
        } else {
            libs = new String[] {"libcef.so", "libEGL.so", "libGLESv2.so", "libvk_swiftshader.so", "libvulkan.so.1"};
        }
        for (String lib : libs) {
            linkOrCopy(libcefDir.resolve(lib), cacheDir.resolve(lib));
        }

        // Also symlink resources into cache dir for subprocess launcher (cef4j_launcher)
        for (String res : new String[] {
            "icudtl.dat",
            "resources.pak",
            "chrome_100_percent.pak",
            "chrome_200_percent.pak",
            "v8_context_snapshot.bin",
            "vk_swiftshader_icd.json"
        }) {
            linkOrCopy(libcefDir.resolve(res), cacheDir.resolve(res));
        }
        linkOrCopy(libcefDir.resolve("locales"), cacheDir.resolve("locales"));
    }

    // macOS only: calls cef_load_library() from libcef_dll_dylib.cc to dynamically load the
    // CEF framework via dlopen before cef_initialize() is called.
    private static native boolean loadCefLibrary0(String frameworkBinaryPath);

    private static void extractResource(String resourcePath, Path target) throws IOException {
        try (InputStream in = SystemBootstrap.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IOException("Resource not found on classpath: " + resourcePath);
            }
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void linkOrCopy(Path source, Path link) throws IOException {
        if (!Files.exists(source)) return;
        if (Files.isSymbolicLink(link)) {
            // Update if target changed
            if (Files.readSymbolicLink(link).equals(source.toAbsolutePath())) return;
            Files.delete(link);
        } else if (Files.exists(link)) {
            // Real file/directory already exists - leave it alone
            return;
        }
        // Symbolic links on Windows require developer mode or admin privileges.
        // Fall back to a regular copy when symlink creation fails.
        try {
            Files.createSymbolicLink(link, source.toAbsolutePath());
        } catch (UnsupportedOperationException | IOException e) {
            log.debug("Symlink failed ({}), falling back to copy: {} -> {}", e.getMessage(), source, link);
            if (Files.isDirectory(source)) {
                copyDirectoryRecursive(source, link);
            } else {
                Files.copy(source, link);
            }
        }
    }

    private static void copyDirectoryRecursive(Path source, Path target) throws IOException {
        Files.createDirectories(target);
        try (java.util.stream.Stream<Path> walk = Files.walk(source)) {
            walk.forEach(s -> {
                try {
                    Path dest = target.resolve(source.relativize(s));
                    if (Files.isDirectory(s)) {
                        Files.createDirectories(dest);
                    } else {
                        Files.copy(s, dest, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException ex) {
                    throw new java.io.UncheckedIOException(ex);
                }
            });
        }
    }
}
