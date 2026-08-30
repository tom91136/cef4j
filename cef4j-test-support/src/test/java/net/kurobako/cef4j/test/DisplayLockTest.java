package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

final class DisplayLockTest {
    @Test
    void rejectsAnOverlappingLockAndReleasesForTheNextOwner(@TempDir Path temporaryDirectory) throws Exception {
        Path lockPath = temporaryDirectory.resolve("display.lock");
        try (DisplayLock first = new DisplayLock(lockPath, Duration.ofMillis(10))) {
            first.acquire();
            try (DisplayLock second = new DisplayLock(lockPath, Duration.ofMillis(10))) {
                assertThatThrownBy(second::acquire)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessageContaining("already held by this JVM");
            }
        }

        try (DisplayLock next = new DisplayLock(lockPath, Duration.ofMillis(10))) {
            next.acquire();
        }
    }
}
