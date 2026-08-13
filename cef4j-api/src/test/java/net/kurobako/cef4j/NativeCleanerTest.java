package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class NativeCleanerTest {

    @Test
    void shutdownDrainsPeersRegisteredByReleaseAndSuppressesLateNativeActions() {
        AtomicInteger releases = new AtomicInteger();
        Object nestedPeer = new Object();
        Object firstPeer = new Object();

        NativeCleaner.INSTANCE.register(firstPeer, () -> {
            releases.incrementAndGet();
            NativeCleaner.INSTANCE.register(nestedPeer, releases::incrementAndGet);
        });

        assertThat(NativeCleaner.INSTANCE.releaseAll()).isEqualTo(2);
        assertThat(releases).hasValue(2);

        Object latePeer = new Object();
        NativeCleaner.INSTANCE.register(latePeer, releases::incrementAndGet).clean();
        assertThat(releases)
                .as("late registrations must not enter CEF after shutdown")
                .hasValue(2);
    }
}
