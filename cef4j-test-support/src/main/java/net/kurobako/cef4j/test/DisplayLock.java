package net.kurobako.cef4j.test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public final class DisplayLock implements BeforeAllCallback, AfterAllCallback, AutoCloseable {
    private static final String LOCK_PATH = System.getProperty(
            "cef4j.test.displayLockPath",
            Path.of(System.getProperty("java.io.tmpdir", "/tmp"), "cef4j-ui-display.lock")
                    .toString());
    private static final long ACQUIRE_TIMEOUT_SECONDS = Long.getLong("cef4j.test.displayLockTimeoutSeconds", 600L);

    private final Path path;
    private final Duration acquireTimeout;

    @Nullable
    private FileChannel channel;

    @Nullable
    private FileLock lock;

    public DisplayLock() {
        this(Path.of(LOCK_PATH), Duration.ofSeconds(ACQUIRE_TIMEOUT_SECONDS));
    }

    DisplayLock(Path path, Duration acquireTimeout) {
        this.path = Objects.requireNonNull(path, "path");
        this.acquireTimeout = Objects.requireNonNull(acquireTimeout, "acquireTimeout");
        if (acquireTimeout.isNegative() || acquireTimeout.isZero()) {
            throw new IllegalArgumentException("acquireTimeout must be positive");
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        acquire();
    }

    void acquire() throws Exception {
        FileChannel opened = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        try {
            long deadline = System.nanoTime() + acquireTimeout.toNanos();
            while (true) {
                try {
                    FileLock acquired = opened.tryLock();
                    if (acquired != null) {
                        channel = opened;
                        lock = acquired;
                        return;
                    }
                } catch (OverlappingFileLockException sameJvm) {
                    throw new IllegalStateException("display lock already held by this JVM: " + path, sameJvm);
                }
                if (System.nanoTime() > deadline) {
                    throw new IllegalStateException("timed out acquiring display lock after " + acquireTimeout + " at "
                            + path + "; another fork is holding it");
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (Exception failure) {
            opened.close();
            throw failure;
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        close();
    }

    @Override
    public void close() throws IOException {
        IOException failure = null;
        if (lock != null) {
            try {
                lock.release();
            } catch (IOException e) {
                failure = e;
            }
        }
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException e) {
                if (failure == null) failure = e;
            }
        }
        lock = null;
        channel = null;
        if (failure != null) throw failure;
    }
}
