package net.kurobako.cef4j.cache;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.stream.Stream;

public final class CefCachePublisher {

    private CefCachePublisher() {}

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: CefCachePublisher <staged-dir> <target-dir> <marker-name>");
        }
        publish(Path.of(args[0]), Path.of(args[1]), args[2]);
    }

    static void publish(Path staged, Path target, String markerName) throws IOException {
        Path normalizedStaged = staged.toAbsolutePath().normalize();
        Path normalizedTarget = target.toAbsolutePath().normalize();
        Path cacheRoot = normalizedTarget.getParent();
        Path stagingRoot = normalizedStaged.getParent();
        Path stagingParent = stagingRoot == null ? null : stagingRoot.getParent();
        Path stagingName = stagingRoot == null ? null : stagingRoot.getFileName();
        if (cacheRoot == null
                || stagingRoot == null
                || stagingName == null
                || !cacheRoot.equals(stagingParent)
                || !stagingName.toString().startsWith(".cef-extract-")) {
            throw new IOException("CEF staging directory is outside the target cache: " + normalizedStaged);
        }
        requireComplete(normalizedStaged, markerName);

        Path lockPath = normalizedTarget.resolveSibling(normalizedTarget.getFileName() + ".cef4j.lock");
        try (FileChannel channel = FileChannel.open(lockPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                FileLock publicationLock = channel.lock()) {
            if (!publicationLock.isValid()) throw new IOException("Failed to acquire CEF cache publication lock");
            if (isComplete(normalizedTarget, markerName)) {
                deleteTree(normalizedStaged);
                return;
            }
            if (Files.exists(normalizedTarget, LinkOption.NOFOLLOW_LINKS)) {
                deleteTree(normalizedTarget);
            }
            try {
                Files.move(normalizedStaged, normalizedTarget, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException e) {
                throw new IOException("CEF cache filesystem does not support atomic publication", e);
            }
        }
    }

    private static void requireComplete(Path directory, String markerName) throws IOException {
        if (!isComplete(directory, markerName)) {
            throw new IOException("Incomplete staged CEF distribution: " + directory);
        }
    }

    private static boolean isComplete(Path directory, String markerName) {
        return Files.isRegularFile(directory.resolve("include/cef_version.h"), LinkOption.NOFOLLOW_LINKS)
                && Files.isDirectory(directory.resolve("Release"), LinkOption.NOFOLLOW_LINKS)
                && Files.isRegularFile(directory.resolve(markerName), LinkOption.NOFOLLOW_LINKS);
    }

    private static void deleteTree(Path root) throws IOException {
        try (Stream<Path> paths = Files.walk(root)) {
            Path[] descending = paths.sorted(Comparator.reverseOrder()).toArray(Path[]::new);
            for (Path path : descending) Files.delete(path);
        }
    }
}
