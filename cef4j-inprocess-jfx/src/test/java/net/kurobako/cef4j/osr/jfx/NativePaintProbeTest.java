package net.kurobako.cef4j.osr.jfx;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import net.kurobako.cef4j.test.backend.BrowserSession;
import org.junit.jupiter.api.Test;

class NativePaintProbeTest {

    @Test
    void waitsForActualPaintAtRequestedSize() throws Exception {
        NativePaintProbe probe = new NativePaintProbe();
        probe.accept(512, 480);
        probe.accept(512, 384);

        BrowserSession.PaintInfo paint = probe.await(512, 384, Duration.ofSeconds(1));

        assertThat(paint.width).isEqualTo(512);
        assertThat(paint.height).isEqualTo(384);
        assertThat(paint.byteCount).isEqualTo(512L * 384L * 4L);
    }

    @Test
    void doesNotInferPaintFromRequestedDimensions() {
        NativePaintProbe probe = new NativePaintProbe();
        probe.accept(512, 480);

        assertThatThrownBy(() -> probe.await(512, 384, Duration.ofMillis(10)))
                .isInstanceOf(TimeoutException.class)
                .hasMessageContaining("last was 512x480");
    }
}
