package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.ref.Cleaner;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class NativeCleanerTest {

    @Test
    void cleansPrunesDrainsAndSuppressesLateNativeActions() {
        AtomicInteger releases = new AtomicInteger();

        Object cleaned = new Object();
        Cleaner.Cleanable cleanedCleanable = NativeCleaner.INSTANCE.register(cleaned, releases::incrementAndGet);
        cleanedCleanable.clean();
        assertThat(releases).hasValue(1);

        Object nestedPeer = new Object();
        Object firstPeer = new Object();
        NativeCleaner.INSTANCE.register(firstPeer, () -> {
            releases.incrementAndGet();
            NativeCleaner.INSTANCE.register(nestedPeer, releases::incrementAndGet);
        });
        assertThat(NativeCleaner.INSTANCE.releaseAll()).isEqualTo(2);
        assertThat(releases)
                .as("cleaned peer + firstPeer + nestedPeer all released exactly once")
                .hasValue(3);

        Object latePeer = new Object();
        NativeCleaner.INSTANCE.register(latePeer, releases::incrementAndGet).clean();
        assertThat(releases).hasValue(3);
    }
}
