package net.kurobako.cef4j.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Best-effort JVM-exit cleanup for {@code @TempDir(CleanupMode.NEVER)} directories.
 *
 * <p>CEF holds cache files open until the fork exits (cef_shutdown is skipped on macOS), so JUnit's eager cleanup
 * throws DirectoryNotEmptyException. Registering the directory here deletes it from a shutdown hook, after the native
 * handles are gone, instead of leaking it for the OS to reap.
 */
public final class TestTempDirs {
    private static final Set<Path> REGISTERED = ConcurrentHashMap.newKeySet();
    private static volatile boolean hookInstalled;

    private TestTempDirs() {}

    public static void cleanupAtExit(Path dir) {
        if (dir == null) return;
        REGISTERED.add(dir);
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

    private static void deleteRegistered() {
        for (Path dir : REGISTERED) {
            try (var paths = Files.walk(dir)) {
                paths.sorted(Comparator.reverseOrder()).forEach(path -> {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException ignored) {
                        // best-effort cleanup
                    }
                });
            } catch (IOException ignored) {
                // best-effort cleanup
            }
        }
    }
}
