package net.kurobako.cef4j.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// XXX: CEF 150 may retain or recreate Chromium cache files until process exit; remove this hook when every supported
// platform closes cache handles before JUnit TempDir cleanup.
public final class TestTempDirs {
    private static final Set<Path> REGISTERED = ConcurrentHashMap.newKeySet();
    static final String OWNERSHIP_MARKER = ".cef4j-test-tempdir";
    private static volatile boolean hookInstalled;

    private TestTempDirs() {}

    public static void cleanupAtExit(Path dir) {
        if (dir == null) return;
        Path safe = requireSafeTempDirectory(dir);
        REGISTERED.add(safe);
        if (!hookInstalled) {
            synchronized (TestTempDirs.class) {
                if (!hookInstalled) {
                    Runtime.getRuntime()
                            .addShutdownHook(new Thread(TestTempDirs::deleteRegistered, "cef4j-test-tempdir-cleanup"));
                    hookInstalled = true;
                }
            }
        }
    }

    private static Path requireSafeTempDirectory(Path dir) {
        try {
            if (Files.isSymbolicLink(dir)) {
                throw new IllegalArgumentException("cleanup target must not be a symbolic link: " + dir);
            }
            Path safe = dir.toRealPath();
            if (!Files.isDirectory(safe, LinkOption.NOFOLLOW_LINKS)) {
                throw new IllegalArgumentException("cleanup target must be a directory: " + safe);
            }
            Path tempRoot = Path.of(System.getProperty("java.io.tmpdir")).toRealPath();
            if (safe.equals(tempRoot) || !safe.startsWith(tempRoot)) {
                throw new IllegalArgumentException("cleanup target must be below the system temp directory: " + safe);
            }
            rejectBroadTarget(safe, Path.of(System.getProperty("user.home", ".")));
            rejectBroadTarget(safe, Path.of(System.getProperty("user.dir", ".")));
            Path marker = safe.resolve(OWNERSHIP_MARKER);
            if (!Files.exists(marker, LinkOption.NOFOLLOW_LINKS)) {
                Files.writeString(marker, "owned by cef4j test cleanup\n", StandardOpenOption.CREATE_NEW);
            }
            if (!Files.isRegularFile(marker, LinkOption.NOFOLLOW_LINKS)) {
                throw new IllegalArgumentException("invalid cleanup ownership marker: " + marker);
            }
            return safe;
        } catch (IOException failure) {
            throw new IllegalArgumentException("cannot validate cleanup target: " + dir, failure);
        }
    }

    private static void rejectBroadTarget(Path safe, Path protectedPath) throws IOException {
        if (!Files.exists(protectedPath)) return;
        Path protectedReal = protectedPath.toRealPath();
        if (protectedReal.startsWith(safe)) {
            throw new IllegalArgumentException("cleanup target contains protected path: " + protectedReal);
        }
    }

    private static void deleteRegistered() {
        for (Path dir : REGISTERED) {
            deleteTree(dir);
        }
    }

    private static void deleteTree(Path dir) {
        try (var paths = Files.walk(dir)) {
            paths.sorted(Comparator.reverseOrder()).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException ignored) {
                    return;
                }
            });
        } catch (IOException ignored) {
            return;
        }
    }
}
