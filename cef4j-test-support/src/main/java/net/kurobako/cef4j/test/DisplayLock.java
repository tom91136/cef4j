package net.kurobako.cef4j.test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public final class DisplayLock implements BeforeAllCallback, AfterAllCallback {
    private static final String LOCK_PATH = System.getProperty(
            "cef4j.test.displayLockPath",
            Path.of(System.getProperty("java.io.tmpdir", "/tmp"), "cef4j-ui-display.lock")
                    .toString());
    private static final long ACQUIRE_TIMEOUT_SECONDS = Long.getLong("cef4j.test.displayLockTimeoutSeconds", 600L);

    @Nullable
    private FileChannel channel;

    @Nullable
    private FileLock lock;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        channel = FileChannel.open(Path.of(LOCK_PATH), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(ACQUIRE_TIMEOUT_SECONDS);
        while (true) {
            try {
                FileLock acquired = channel.tryLock();
                if (acquired != null) {
                    lock = acquired;
                    return;
                }
            } catch (OverlappingFileLockException sameJvm) {
                throw new IllegalStateException("display lock already held by this JVM: " + LOCK_PATH, sameJvm);
            }
            if (System.nanoTime() > deadline) {
                throw new IllegalStateException("timed out acquiring display lock after " + ACQUIRE_TIMEOUT_SECONDS
                        + "s at " + LOCK_PATH + "; another fork is holding it");
            }
            Thread.sleep(100);
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
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
        if (failure != null) throw failure;
    }
}
