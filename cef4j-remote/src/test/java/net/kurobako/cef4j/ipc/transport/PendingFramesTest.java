package net.kurobako.cef4j.ipc.transport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class PendingFramesTest {
    @Test
    void boundsFrameCountAndReclaimsCapacityWhenPolled() {
        PendingFrames frames = new PendingFrames(2, 100);

        assertThat(frames.offer(new byte[1])).isTrue();
        assertThat(frames.offer(new byte[1])).isTrue();
        assertThat(frames.offer(new byte[1])).isFalse();
        assertThat(frames.poll()).hasSize(1);
        assertThat(frames.offer(new byte[1])).isTrue();
    }

    @Test
    void boundsTotalBytesAndHandlesOversizedFirstFrame() {
        PendingFrames frames = new PendingFrames(10, 4);

        assertThat(frames.offer(new byte[5])).isFalse();
        assertThat(frames.offer(new byte[3])).isTrue();
        assertThat(frames.offer(new byte[2])).isFalse();
        assertThat(frames.poll()).hasSize(3);
        assertThat(frames.offer(new byte[4])).isTrue();
    }

    @Test
    void rejectsNonPositiveLimits() {
        assertThatIllegalArgumentException().isThrownBy(() -> new PendingFrames(0, 1));
        assertThatIllegalArgumentException().isThrownBy(() -> new PendingFrames(1, 0));
    }
}
