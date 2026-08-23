package net.kurobako.cef4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;

public final class CefCachePublisherTest {

    private static final String MARKER = ".cef4j-complete-test";

    private CefCachePublisherTest() {}

    public static void main(String[] args) throws Exception {
        Path root = Files.createTempDirectory("cef-cache-publisher-test-");
        try {
            Path target = root.resolve("cef_binary_test_linux64_minimal");
            Path first = stagedDistribution(root, "first", "first");
            CefCachePublisher.publish(first, target, MARKER);
            require("first".equals(Files.readString(target.resolve("payload"))), "first publish failed");

            Path redundant = stagedDistribution(root, "redundant", "redundant");
            CefCachePublisher.publish(redundant, target, MARKER);
            require(!Files.exists(redundant), "redundant staging directory was not removed");
            require("first".equals(Files.readString(target.resolve("payload"))), "complete target was replaced");

            Files.delete(target.resolve(MARKER));
            Path replacement = stagedDistribution(root, "replacement", "replacement");
            CefCachePublisher.publish(replacement, target, MARKER);
            require(
                    "replacement".equals(Files.readString(target.resolve("payload"))),
                    "invalid target was not replaced");

            Path concurrentTarget = root.resolve("cef_binary_concurrent_linux64_minimal");
            Path concurrentFirst = stagedDistribution(root, "concurrent-first", "concurrent-first");
            Path concurrentSecond = stagedDistribution(root, "concurrent-second", "concurrent-second");
            Process firstPublisher =
                    publisherProcess(concurrentFirst, concurrentTarget).start();
            Process secondPublisher =
                    publisherProcess(concurrentSecond, concurrentTarget).start();
            require(firstPublisher.waitFor() == 0, "first concurrent publisher failed");
            require(secondPublisher.waitFor() == 0, "second concurrent publisher failed");
            String concurrentPayload = Files.readString(concurrentTarget.resolve("payload"));
            require(
                    concurrentPayload.equals("concurrent-first") || concurrentPayload.equals("concurrent-second"),
                    "concurrent publication produced an invalid target");
        } finally {
            deleteTree(root);
        }
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

    private static Path stagedDistribution(Path root, String id, String payload) throws Exception {
        Path staged = root.resolve(".cef-extract-" + id).resolve("cef_binary_test_linux64_minimal");
        Files.createDirectories(staged.resolve("include"));
        Files.createDirectories(staged.resolve("Release"));
        Files.writeString(staged.resolve("include/cef_version.h"), "test");
        Files.writeString(staged.resolve(MARKER), "");
        Files.writeString(staged.resolve("payload"), payload);
        return staged;
    }

    private static void deleteTree(Path root) throws Exception {
        if (!Files.exists(root)) return;
        try (java.util.stream.Stream<Path> paths = Files.walk(root)) {
            Path[] descending =
                    paths.sorted(java.util.Comparator.reverseOrder()).toArray(Path[]::new);
            for (Path path : descending) Files.delete(path);
        }
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
