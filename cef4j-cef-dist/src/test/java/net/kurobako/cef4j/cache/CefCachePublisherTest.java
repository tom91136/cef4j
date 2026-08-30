package net.kurobako.cef4j.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

final class CefCachePublisherTest {

    private static final String MARKER = ".cef4j-complete-test";

    @TempDir
    Path root;

    @Test
    void publishesCompleteStagingDirectory() throws Exception {
        Path target = root.resolve("cef_binary_test_linux64_minimal");
        Path staged = stagedDistribution("first", "first");

        CefCachePublisher.publish(staged, target, MARKER);

        assertEquals("first", Files.readString(target.resolve("payload")));
        assertFalse(Files.exists(staged));
    }

    @Test
    void keepsExistingCompleteTargetAndRemovesRedundantStaging() throws Exception {
        Path target = root.resolve("cef_binary_test_linux64_minimal");
        CefCachePublisher.publish(stagedDistribution("first", "first"), target, MARKER);
        Path redundant = stagedDistribution("redundant", "redundant");

        CefCachePublisher.publish(redundant, target, MARKER);

        assertFalse(Files.exists(redundant));
        assertEquals("first", Files.readString(target.resolve("payload")));
    }

    @Test
    void replacesIncompleteTarget() throws Exception {
        Path target = root.resolve("cef_binary_test_linux64_minimal");
        CefCachePublisher.publish(stagedDistribution("first", "first"), target, MARKER);
        Files.delete(target.resolve(MARKER));

        CefCachePublisher.publish(stagedDistribution("replacement", "replacement"), target, MARKER);

        assertEquals("replacement", Files.readString(target.resolve("payload")));
    }

    @Test
    void rejectsIncompleteOrOutOfCacheStaging() throws Exception {
        Path target = root.resolve("cef_binary_test_linux64_minimal");
        Path incomplete = root.resolve(".cef-extract-incomplete/cef_binary_test_linux64_minimal");
        Files.createDirectories(incomplete);

        assertThrows(IOException.class, () -> CefCachePublisher.publish(incomplete, target, MARKER));

        Path outside = root.resolve("outside");
        Files.createDirectories(outside.resolve("include"));
        Files.createDirectories(outside.resolve("Release"));
        Files.writeString(outside.resolve("include/cef_version.h"), "test");
        Files.writeString(outside.resolve(MARKER), "");
        assertThrows(IOException.class, () -> CefCachePublisher.publish(outside, target, MARKER));
    }

    @Test
    @Timeout(30)
    void concurrentPublishersLeaveOneCompleteDistribution() throws Exception {
        Path target = root.resolve("cef_binary_concurrent_linux64_minimal");
        Path first = stagedDistribution("concurrent-first", "concurrent-first");
        Path second = stagedDistribution("concurrent-second", "concurrent-second");
        Process firstPublisher = publisherProcess(first, target).start();
        Process secondPublisher = publisherProcess(second, target).start();

        assertProcessSucceeded(firstPublisher);
        assertProcessSucceeded(secondPublisher);
        assertTrue(java.util.Set.of("concurrent-first", "concurrent-second")
                .contains(Files.readString(target.resolve("payload"))));
        assertTrue(Files.isRegularFile(target.resolve(MARKER)));
    }

    private static ProcessBuilder publisherProcess(Path staged, Path target) {
        return new ProcessBuilder(
                        Path.of(System.getProperty("java.home"), "bin", "java").toString(),
                        "-cp",
                        System.getProperty("java.class.path"),
                        CefCachePublisher.class.getName(),
                        staged.toString(),
                        target.toString(),
                        MARKER)
                .inheritIO();
    }

    private Path stagedDistribution(String id, String payload) throws Exception {
        Path staged = root.resolve(".cef-extract-" + id).resolve("cef_binary_test_linux64_minimal");
        Files.createDirectories(staged.resolve("include"));
        Files.createDirectories(staged.resolve("Release"));
        Files.writeString(staged.resolve("include/cef_version.h"), "test");
        Files.writeString(staged.resolve(MARKER), "");
        Files.writeString(staged.resolve("payload"), payload);
        return staged;
    }

    private static void assertProcessSucceeded(Process process) throws Exception {
        if (!process.waitFor(Duration.ofSeconds(20).toMillis(), TimeUnit.MILLISECONDS)) {
            process.destroyForcibly();
            throw new AssertionError("publisher process did not exit");
        }
        assertEquals(0, process.exitValue());
    }
}
