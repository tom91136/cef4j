package net.kurobako.cef4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads the cef4j native library and prepares the CEF runtime environment.
 *
 * <p>Loading strategy:
 *
 * <ol>
 *   <li>Try {@code System.loadLibrary("cef4j")} (honors {@code java.library.path} and {@code LD_LIBRARY_PATH})
 *   <li>If that fails, extract the cef4j JNI bridge and launcher from classpath resources, then use either packaged
 *       {@code cef-runtime/<platform>} resources or a CEF installation selected by {@code LIBCEF_DIR}
 * </ol>
 *
 * <p>When used, {@code LIBCEF_DIR} points to the {@code Release/} directory of a CEF binary distribution. On macOS that
 * directory contains {@code Chromium Embedded Framework.framework}.
 */
@SuppressWarnings("unused")
public final class SystemBootstrap {

    private static final Logger log = LoggerFactory.getLogger(SystemBootstrap.class);
    private static final Object LOAD_LOCK = new Object();
    private static final String CEF_API_VERSION_RESOURCE = "/META-INF/cef4j/cef-api-version";

    private SystemBootstrap() {}

    private static volatile boolean loaded = false;
    private static volatile @Nullable Path extractionDir;
    private static volatile @Nullable Path cachedLibcefDir;
    private static volatile boolean libcefDirResolved = false;

    /**
     * Load the native library. Tries system paths first, then classpath extraction with packaged or external CEF.
     *
     * @throws UnsatisfiedLinkError if the library cannot be loaded
     */
    public static void load() {
        synchronized (LOAD_LOCK) {
            if (loaded) return;

            try {
                log.debug("Trying System.loadLibrary(\"cef4j\")");
                System.loadLibrary("cef4j");
                if (OS.isMacOS()) {
                    final Path libcefDir = libcefDir().orElse(null);
                    if (libcefDir == null) {
                        throw new UnsatisfiedLinkError("LIBCEF_DIR must be set on macOS to locate the CEF framework");
                    }
                    loadCefFramework(libcefDir);
                }
                loaded = true;
                log.debug("Loaded cef4j native library from system path");
            } catch (UnsatisfiedLinkError e) {
                log.debug("System library path failed: {}", e.getMessage());
                try {
                    loadFromClasspath();
                } catch (IOException ex) {
                    throw new UnsatisfiedLinkError(ex.getMessage());
                }
            }

            finishLoad();
        }
    }

    /**
     * Load the native library from an explicit filesystem path.
     *
     * @param libraryPath the directory containing libcef4j.so
     * @throws UnsatisfiedLinkError if the library cannot be loaded
     */
    public static void loadFrom(Path libraryPath) {
        synchronized (LOAD_LOCK) {
            if (loaded) return;
            final String libName = OS.mapLibraryName("cef4j");
            final Path fullPath = libraryPath.resolve(libName);
            log.debug("Loading native library from explicit path: {}", fullPath);
            System.load(fullPath.toAbsolutePath().toString());
            if (OS.isMacOS()) loadCefFramework(libraryPath);
            loaded = true;
            log.debug("Loaded cef4j native library from filesystem path {}", fullPath);
            finishLoad();
        }
    }

    /** Loads the CEF framework of the directory, which the library of the build needs on macOS. */
    private static void loadCefFramework(Path directory) {
        final Path frameworkBinary =
                directory.resolve("Chromium Embedded Framework.framework").resolve("Chromium Embedded Framework");
        if (!loadCefLibrary0(frameworkBinary.toAbsolutePath().toString())) {
            throw new UnsatisfiedLinkError("cef_load_library() failed for: " + frameworkBinary);
        }
    }

    private static void finishLoad() {
        NativeStderr.install();
    }

    /** Returns whether the native library has been loaded. */
    public static boolean isLoaded() {
        return loaded;
    }

    /**
     * Returns the directory where native files were extracted, or empty if the library was loaded from the system path.
     */
    public static Optional<Path> extractionDir() {
        return Optional.ofNullable(extractionDir);
    }

    /** Returns the resolved LIBCEF_DIR, or empty if not set. Result is cached after first call. */
    public static Optional<Path> libcefDir() {
        if (libcefDirResolved) return Optional.ofNullable(cachedLibcefDir);
        final Optional<Path> result = configuredLibcefDir().or(SystemBootstrap::discoverCefDist);
        cachedLibcefDir = result.orElse(null);
        libcefDirResolved = true;
        return result;
    }

    private static Optional<Path> configuredLibcefDir() {
        String value = System.getenv("LIBCEF_DIR");
        if (value == null || value.isEmpty()) value = System.getProperty("cef4j.libcef.dir");
        return value == null || value.isEmpty() ? Optional.empty() : Optional.of(Paths.get(value));
    }

    private static Optional<Path> discoverCefDist() {
        return discoverCefDist(
                Paths.get(System.getProperty("user.dir")), expectedCefMajor().orElse(null));
    }

    static Optional<Path> discoverCefDist(Path start, @Nullable String expectedMajor) {
        Path dir = start;
        for (int i = 0; i < 5 && dir != null; i++, dir = dir.getParent()) {
            final Path cefDist = dir.resolve(".cef-dist");
            if (!Files.isDirectory(cefDist)) continue;
            try (java.util.stream.Stream<Path> children = Files.list(cefDist)) {
                final String suffix = "_" + OS.platform() + "_minimal";
                List<Path> candidates = children.filter(child -> {
                            final Path fileName = child.getFileName();
                            return fileName != null && fileName.toString().endsWith(suffix);
                        })
                        .map(child -> child.resolve("Release"))
                        .filter(SystemBootstrap::isValidCefReleaseDir)
                        .sorted(Comparator.comparing(Path::toString))
                        .collect(Collectors.toList());
                if (expectedMajor != null) {
                    candidates = candidates.stream()
                            .filter(path -> {
                                final Path parent = path.getParent();
                                final Path fileName = parent == null ? null : parent.getFileName();
                                return parent != null
                                        && fileName != null
                                        && fileName.toString().startsWith("cef_binary_" + expectedMajor + ".");
                            })
                            .collect(Collectors.toList());
                }
                if (!candidates.isEmpty()) return selectCandidate(candidates);
            } catch (IOException e) {
                log.trace("skipping unreadable CEF dist candidate {}: {}", cefDist, e.toString());
            }
        }
        return Optional.empty();
    }

    /** The only candidate of the list, or nothing when the list holds more than one of them. */
    private static Optional<Path> selectCandidate(List<Path> candidates) {
        if (candidates.size() == 1) {
            log.debug("Auto-discovered CEF dist: {}", candidates.get(0));
            return Optional.of(candidates.get(0));
        }
        log.warn("Multiple CEF distributions match this build; set LIBCEF_DIR explicitly: {}", candidates);
        return Optional.empty();
    }

    private static Optional<String> expectedCefMajor() {
        final String override = System.getProperty("cef4j.test.cefApiVersion");
        final Optional<String> version =
                override == null || override.isBlank() ? packagedCefApiVersion() : Optional.of(override);
        return version.flatMap(value -> {
            final java.util.regex.Matcher matcher =
                    java.util.regex.Pattern.compile("^(\\d+)").matcher(value);
            return matcher.find() ? Optional.of(matcher.group(1)) : Optional.empty();
        });
    }

    static Optional<String> packagedCefApiVersion() {
        try (InputStream stream = SystemBootstrap.class.getResourceAsStream(CEF_API_VERSION_RESOURCE)) {
            if (stream == null) return Optional.empty();
            final String version = new String(stream.readAllBytes(), StandardCharsets.UTF_8).trim();
            return version.isEmpty() ? Optional.empty() : Optional.of(version);
        } catch (IOException e) {
            log.warn("Unable to read packaged CEF API version", e);
            return Optional.empty();
        }
    }

    /**
     * Return the major version of the loaded libcef, which may be newer than {@link #packagedCefApiMajor()} when a
     * versioned-API build runs on a newer CEF. Falls back to the packaged major before libcef is loaded.
     */
    public static OptionalInt runtimeCefMajor() {
        if (loaded) {
            final int major = runtimeCefMajor0();
            if (major > 0) return OptionalInt.of(major);
        }
        return packagedCefApiMajor();
    }

    /** Return the major CEF API version embedded in this cef4j build, if available. */
    public static OptionalInt packagedCefApiMajor() {
        final java.util.regex.Pattern major = java.util.regex.Pattern.compile("^(\\d+)");
        return packagedCefApiVersion()
                .map(major::matcher)
                .filter(java.util.regex.Matcher::find)
                .map(matcher -> OptionalInt.of(Integer.parseInt(matcher.group(1))))
                .orElse(OptionalInt.empty());
    }

    private static boolean isValidCefReleaseDir(Path release) {
        if (!Files.isDirectory(release)) return false;
        if (OS.isMacOS()) {
            return Files.isDirectory(release.resolve("Chromium Embedded Framework.framework"));
        }
        final String cefLib = OS.isWindows() ? "libcef.dll" : "libcef.so";
        return Files.isRegularFile(release.resolve(cefLib));
    }

    /**
     * Returns the path to the cef4j_launcher executable. Prefers the extraction directory, falls back to LIBCEF_DIR,
     * then empty.
     */
    public static Optional<String> helperPath() {
        final String launcherName = OS.isWindows() ? "cef4j_launcher.exe" : "cef4j_launcher";
        final Path extracted = extractionDir;
        if (extracted != null) {
            final Optional<String> path = launcherPath(extracted, launcherName);
            if (path.isPresent()) return path;
        }
        final Path libcefDir = libcefDir().orElse(null);
        if (libcefDir != null) return launcherPath(libcefDir, launcherName);
        return Optional.empty();
    }

    /** The path of the launcher of the directory, which is empty when the directory holds none. */
    private static Optional<String> launcherPath(Path directory, String launcherName) {
        final Path launcher = directory.resolve(launcherName);
        if (!Files.exists(launcher)) return Optional.empty();
        return Optional.of(launcher.toAbsolutePath().toString());
    }

    private static void loadFromClasspath() throws IOException {
        final String platform = OS.platform();
        final String libName = OS.mapLibraryName("cef4j");
        final String resourceBase = "native/" + platform + "/";
        log.debug("Detected platform: {}, native lib: {}", platform, libName);

        final boolean packagedRuntimeAvailable = isPackagedRuntimeAvailable();
        final Optional<Path> configuredLibcefDir = configuredLibcefDir();
        final Optional<Path> discoveredLibcefDir =
                configuredLibcefDir.isEmpty() && !packagedRuntimeAvailable ? discoverCefDist() : Optional.empty();
        final Path libcefDir = selectLibcefDir(
                        configuredLibcefDir.orElse(null), discoveredLibcefDir.orElse(null), packagedRuntimeAvailable)
                .orElse(null);
        if (libcefDir == null && !packagedRuntimeAvailable) {
            throw new IOException("CEF runtime not found: no cef-runtime/" + platform
                    + " resources, LIBCEF_DIR env var, or -Dcef4j.libcef.dir system property");
        }
        if (libcefDir != null && !Files.isDirectory(libcefDir)) {
            throw new IOException("LIBCEF_DIR is not a directory: " + libcefDir);
        }

        final Path cacheDir =
                resolveExtractionCacheDir(platform, libName, resourceBase, libcefDir, packagedRuntimeAvailable);
        log.debug("Extraction cache dir: {}", cacheDir);
        createPrivateDirectory(cacheDir);

        final String launcherName = OS.isWindows() ? "cef4j_launcher.exe" : "cef4j_launcher";
        final Path launcher = cacheDir.resolve(launcherName);
        final Path extractionLock = cacheDir.resolve(".extract.lock");
        try (FileChannel lockChannel =
                FileChannel.open(extractionLock, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            final java.nio.channels.FileLock extractionFileLock = lockChannel.lock();
            try (extractionFileLock) {
                extractResource(resourceBase + libName, cacheDir.resolve(libName));
                extractResource(resourceBase + launcherName, launcher);
                makeExecutable(launcher);

                if (libcefDir != null) {
                    log.debug("Linking CEF runtime from LIBCEF_DIR: {}", libcefDir);
                    prepareExternalRuntime(libcefDir, cacheDir);
                } else {
                    log.debug("Packaged CEF runtime found, extracting");
                    extractPackagedRuntime(cacheDir);
                }
                extractionDir = cacheDir;
            }
        }

        if (OS.isMacOS()) {
            if (libcefDir == null) prepareMacAngleLibraries(cacheDir);

            // XXX: CEF 109-150 requires macOS framework loading through cef_load_library before API use; replace this
            // sequence only when the minimum supported CEF documents a different loader contract.
            System.load(cacheDir.resolve(libName).toAbsolutePath().toString());
            final Path frameworkBinary =
                    cacheDir.resolve("Chromium Embedded Framework.framework").resolve("Chromium Embedded Framework");
            if (!loadCefLibrary0(frameworkBinary.toAbsolutePath().toString())) {
                throw new IOException("cef_load_library() failed for: " + frameworkBinary);
            }
            try {
                new ProcessBuilder(
                                "xattr",
                                "-r",
                                "-d",
                                "com.apple.quarantine",
                                cacheDir.toAbsolutePath().toString())
                        .redirectOutput(ProcessBuilder.Redirect.DISCARD)
                        .redirectError(ProcessBuilder.Redirect.DISCARD)
                        .start()
                        .waitFor();
            } catch (IOException e) {
                log.trace("Unable to clear macOS quarantine attribute from {}: {}", cacheDir, e.toString());
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        } else if (OS.isWindows()) {
            for (String dep : new String[] {"chrome_elf.dll", "libcef.dll"}) {
                final Path depPath = cacheDir.resolve(dep);
                if (Files.exists(depPath)) {
                    System.load(depPath.toAbsolutePath().toString());
                }
            }
            System.load(cacheDir.resolve(libName).toAbsolutePath().toString());
        } else {
            System.load(cacheDir.resolve("libcef.so").toAbsolutePath().toString());
            System.load(cacheDir.resolve(libName).toAbsolutePath().toString());
        }
        loaded = true;
        log.debug("Loaded cef4j native library from classpath extraction: {}", cacheDir);
    }

    private static Path resolveExtractionCacheDir(
            String platform,
            String libName,
            String resourceBase,
            @Nullable Path libcefDir,
            boolean packagedRuntimeAvailable)
            throws IOException {
        final String configured = System.getProperty("cef4j.native.cache");
        final Path baseDir = configured == null || configured.isBlank()
                ? Paths.get(System.getProperty("user.home"), ".cache", "cef4j", "native", platform)
                : Paths.get(configured).resolve(platform);
        createPrivateDirectory(baseDir);
        return baseDir.resolve(
                computeExtractionCacheKey(platform, libName, resourceBase, libcefDir, packagedRuntimeAvailable));
    }

    static void createPrivateDirectory(Path directory) throws IOException {
        Files.createDirectories(directory);
        if (Files.isSymbolicLink(directory) || !Files.isDirectory(directory, LinkOption.NOFOLLOW_LINKS)) {
            throw new IOException("Native cache is not a real directory: " + directory);
        }
        if (!isOwnedByCurrentUser(directory)) {
            throw new IOException("Native cache is not owned by the current user: " + directory);
        }
        try {
            Files.setPosixFilePermissions(
                    directory,
                    Set.of(
                            PosixFilePermission.OWNER_READ,
                            PosixFilePermission.OWNER_WRITE,
                            PosixFilePermission.OWNER_EXECUTE));
        } catch (UnsupportedOperationException e) {
            log.trace("POSIX permissions are unavailable for {}: {}", directory, e.toString());
        }
    }

    static boolean isOwnedByCurrentUser(Path directory) throws IOException {
        final UserPrincipal owner = Files.getOwner(directory, LinkOption.NOFOLLOW_LINKS);
        final Path probe = Files.createTempFile("cef4j-owner-", ".tmp");
        try {
            return owner.equals(Files.getOwner(probe, LinkOption.NOFOLLOW_LINKS));
        } finally {
            Files.deleteIfExists(probe);
        }
    }

    private static String computeExtractionCacheKey(
            String platform,
            String libName,
            String resourceBase,
            @Nullable Path libcefDir,
            boolean packagedRuntimeAvailable)
            throws IOException {
        final MessageDigest digest = newSha256Digest();
        updateDigestFromResource(digest, resourceBase + libName);
        updateDigestFromResource(digest, resourceBase + (OS.isWindows() ? "cef4j_launcher.exe" : "cef4j_launcher"));
        updateDigestWithString(digest, platform);
        if (packagedRuntimeAvailable) {
            updateDigestWithString(digest, "packaged-runtime-v2");
            updateDigestFromResource(digest, platformRuntimeResource("file-list.txt"));
            updateDigestFromResource(digest, platformRuntimeFingerprintResource());
        } else {
            final Path resolvedLibcefDir =
                    java.util.Objects.requireNonNull(libcefDir).toRealPath();
            updateDigestWithString(digest, resolvedLibcefDir.toString());
            final Path runtimeBinary = resolvedLibcefDir.resolve(platformRuntimeBinaryName());
            if (Files.exists(runtimeBinary)) {
                updateDigestWithString(digest, Long.toString(Files.size(runtimeBinary)));
                updateDigestWithString(
                        digest,
                        Long.toString(Files.getLastModifiedTime(runtimeBinary).toMillis()));
            }
        }
        final StringBuilder out = new StringBuilder();
        final byte[] bytes = digest.digest();
        for (int i = 0; i < 8; i++) {
            out.append(String.format(Locale.ROOT, "%02x", bytes[i]));
        }
        return out.toString();
    }

    private static boolean isPackagedRuntimeAvailable() {
        return SystemBootstrap.class.getClassLoader().getResource(platformRuntimeResource("file-list.txt")) != null;
    }

    static Optional<Path> selectLibcefDir(
            @Nullable Path configured, @Nullable Path discovered, boolean packagedRuntimeAvailable) {
        if (configured != null) return Optional.of(configured);
        return packagedRuntimeAvailable ? Optional.empty() : Optional.ofNullable(discovered);
    }

    private static void extractPackagedRuntime(Path cacheDir) throws IOException {
        final String fileListResource = platformRuntimeResource("file-list.txt");
        final InputStream in = SystemBootstrap.class.getClassLoader().getResourceAsStream(fileListResource);
        if (in == null) {
            throw new IOException(fileListResource + " not found in packaged CEF resources");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().replace('\\', '/');
                if (line.isEmpty()) continue;
                final Path target = cacheDir.resolve(line).normalize();
                if (!target.startsWith(cacheDir)) {
                    throw new IOException("manifest entry escapes extraction directory: " + line);
                }
                if (isUsableExistingFile(target)) continue;
                final Path parent = target.getParent();
                if (parent != null) Files.createDirectories(parent);
                Files.deleteIfExists(target);
                extractResource(platformRuntimeResource(line), target);
                final Path fileName = target.getFileName();
                final String fileNameStr = fileName == null ? "" : fileName.toString();
                if (line.endsWith(".so")
                        || line.endsWith(".so.1")
                        || line.endsWith(".dylib")
                        || line.endsWith("chrome-sandbox")
                        || line.endsWith("bootstrap")
                        || fileNameStr.equals("Chromium Embedded Framework")) {
                    makeExecutable(target);
                }
            }
        }
    }

    private static boolean isUsableExistingFile(Path target) {
        try {
            return Files.isRegularFile(target, LinkOption.NOFOLLOW_LINKS) && Files.size(target) > 0;
        } catch (IOException ignored) {
            return false;
        }
    }

    private static void linkCefRuntime(Path libcefDir, Path cacheDir) throws IOException {
        if (OS.isMacOS()) {
            final String framework = "Chromium Embedded Framework.framework";
            linkOrCopy(libcefDir.resolve(framework), cacheDir.resolve(framework));
            return;
        }

        final Path resourcesDir = libcefDir.resolveSibling("Resources");
        if (Files.isDirectory(resourcesDir)) {
            for (String res :
                    new String[] {"icudtl.dat", "resources.pak", "chrome_100_percent.pak", "chrome_200_percent.pak"}) {
                linkOrCopy(resourcesDir.resolve(res), libcefDir.resolve(res));
            }
            linkOrCopy(resourcesDir.resolve("locales"), libcefDir.resolve("locales"));
        }

        String[] libs;
        if (OS.isWindows()) {
            libs = new String[] {
                "libcef.dll", "chrome_elf.dll", "d3dcompiler_47.dll", "vk_swiftshader.dll", "vulkan-1.dll"
            };
        } else {
            libs = new String[] {"libcef.so", "libEGL.so", "libGLESv2.so", "libvk_swiftshader.so", "libvulkan.so.1"};
        }
        for (String lib : libs) {
            linkOrCopy(libcefDir.resolve(lib), cacheDir.resolve(lib));
        }

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

    static void prepareExternalRuntime(Path libcefDir, Path nativeDir) throws IOException {
        linkCefRuntime(libcefDir, nativeDir);
        if (OS.isMacOS()) prepareMacAngleLibraries(nativeDir);
    }

    private static void prepareMacAngleLibraries(Path runtimeDir) throws IOException {
        final Path frameworkLibs =
                runtimeDir.resolve("Chromium Embedded Framework.framework").resolve("Libraries");
        for (String lib :
                new String[] {"libEGL.dylib", "libGLESv2.dylib", "libvk_swiftshader.dylib", "vk_swiftshader_icd.json"
                }) {
            linkOrCopy(frameworkLibs.resolve(lib), runtimeDir.resolve(lib));
        }
    }

    private static native boolean loadCefLibrary0(String frameworkBinaryPath);

    static native void initAndRunOnMainThread0(BooleanSupplier initializer, Runnable cleanupRunnable);

    static native void dispatchToMainThreadSync0(Runnable runnable);

    static native void quitAndWaitMainThreadMessageLoop0();

    private static native void purgeAppKitObservers0();

    private static native void scheduleLinuxMessageLoopWork0(long delayMs);

    private static native void cancelLinuxMessageLoopWork0();

    private static native int runtimeCefMajor0();

    /** Schedule one CEF message-loop iteration on Linux's default GLib context. */
    public static void scheduleLinuxMessageLoopWork(long delayMs) {
        if (!OS.isLinux()) throw new UnsupportedOperationException("Linux only");
        scheduleLinuxMessageLoopWork0(delayMs);
    }

    /** Cancel a pending CEF message-loop iteration on Linux's default GLib context. */
    public static void cancelLinuxMessageLoopWork() {
        if (!OS.isLinux()) throw new UnsupportedOperationException("Linux only");
        cancelLinuxMessageLoopWork0();
    }

    /**
     * macOS only: initialise CEF, run the message loop, and run cleanup — all on Thread 0 in a single GCD block. See
     * {@link #initAndRunOnMainThread0(BooleanSupplier, Runnable)}.
     */
    public static void initAndRunOnMainThread(BooleanSupplier initializer, Runnable cleanupRunnable) {
        if (!OS.isMacOS()) throw new UnsupportedOperationException("macOS only");
        initAndRunOnMainThread0(initializer, cleanupRunnable);
    }

    /** macOS only. See {@link #dispatchToMainThreadSync0(Runnable)}. */
    public static void dispatchToMainThreadSync(Runnable runnable) {
        if (!OS.isMacOS()) throw new UnsupportedOperationException("macOS only");
        if (!loaded) load();
        dispatchToMainThreadSync0(runnable);
    }

    /** macOS only. See {@link #quitAndWaitMainThreadMessageLoop0()}. */
    public static void quitAndWaitMainThreadMessageLoop() {
        if (!OS.isMacOS()) throw new UnsupportedOperationException("macOS only");
        quitAndWaitMainThreadMessageLoop0();
    }

    /** macOS only. Removes the AppKit observers CEF registered, which outlive {@code cef_shutdown}. */
    static void purgeAppKitObservers() {
        if (!OS.isMacOS()) throw new UnsupportedOperationException("macOS only");
        purgeAppKitObservers0();
    }

    private static void makeExecutable(Path file) throws IOException {
        if (!file.toFile().setExecutable(true)) throw new IOException("Cannot make " + file + " executable");
    }

    static void extractResource(String resourcePath, Path target) throws IOException {
        if (Files.isSymbolicLink(target)) throw new IOException("Refusing symbolic link resource target: " + target);
        final byte[] expectedDigest = resourceDigest(resourcePath);
        if (Files.isRegularFile(target, LinkOption.NOFOLLOW_LINKS)) {
            if (Arrays.equals(expectedDigest, fileDigest(target))) return;
            Files.delete(target);
        } else if (Files.exists(target, LinkOption.NOFOLLOW_LINKS)) {
            throw new IOException("Resource target is not a regular file: " + target);
        }
        try (InputStream in = SystemBootstrap.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IOException("Resource not found on classpath: " + resourcePath);
            }
            final Path parent = target.toAbsolutePath().getParent();
            if (parent == null) throw new IOException("target has no parent directory: " + target);
            Files.createDirectories(parent);
            final Path fileName = target.getFileName();
            final String prefix = fileName == null ? "cef4j" : fileName.toString();
            final Path temporary = Files.createTempFile(parent, prefix, ".part");
            try {
                Files.copy(in, temporary, StandardCopyOption.REPLACE_EXISTING);
                try {
                    Files.move(temporary, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                } catch (java.nio.file.AtomicMoveNotSupportedException ignored) {
                    Files.move(temporary, target, StandardCopyOption.REPLACE_EXISTING);
                }
            } finally {
                Files.deleteIfExists(temporary);
            }
        }
    }

    private static byte[] resourceDigest(String resourcePath) throws IOException {
        final MessageDigest digest = newSha256Digest();
        updateDigestFromResource(digest, resourcePath);
        return digest.digest();
    }

    private static byte[] fileDigest(Path path) throws IOException {
        final MessageDigest digest = newSha256Digest();
        try (InputStream in = Files.newInputStream(path, LinkOption.NOFOLLOW_LINKS)) {
            final byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) != -1) digest.update(buffer, 0, read);
        }
        return digest.digest();
    }

    private static void updateDigestFromResource(MessageDigest digest, String resourcePath) throws IOException {
        try (InputStream in = SystemBootstrap.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IOException("Resource not found on classpath: " + resourcePath);
            }
            final byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
        }
    }

    private static void updateDigestWithString(MessageDigest digest, String value) {
        digest.update(value.getBytes(StandardCharsets.UTF_8));
        digest.update((byte) 0);
    }

    private static String platformRuntimeFingerprintResource() {
        if (OS.isMacOS()) {
            return platformRuntimeResource("Chromium Embedded Framework.framework/Chromium Embedded Framework");
        }
        return platformRuntimeResource(platformRuntimeBinaryName());
    }

    private static String platformRuntimeResource(String path) {
        return "cef-runtime/" + OS.platform() + "/" + path;
    }

    private static String platformRuntimeBinaryName() {
        return OS.isWindows() ? "libcef.dll" : "libcef.so";
    }

    private static MessageDigest newSha256Digest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 digest unavailable", e);
        }
    }

    private static void linkOrCopy(Path source, Path link) throws IOException {
        if (!Files.exists(source)) return;
        if (Files.isSymbolicLink(link)) {
            if (Files.readSymbolicLink(link).equals(source.toAbsolutePath())) return;
            Files.delete(link);
        } else if (Files.exists(link)) {
            return;
        }
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
                    final Path dest = target.resolve(source.relativize(s));
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
