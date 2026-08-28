package net.kurobako.cef4j.test.surface;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import net.kurobako.cef4j.test.backend.BrowserSession;
import org.junit.jupiter.api.Test;

class RemoteSurfaceSupportTest {
    @Test
    void includesFramePipelineStateInPaintTimeout() {
        RemoteSurfaceSupport.FrameProbe probe = new RemoteSurfaceSupport.FrameProbe();
        probe.pipelineDiagnosticSource(() -> "shared-file{events=0, callbacks=0}");
        probe.runtimeDiagnosticSource(() -> "runtime-server{recent=[on-paint, buffer-ready]}");

        Throwable failure =
                org.assertj.core.api.Assertions.catchThrowable(() -> probe.await(640, 480, Duration.ofMillis(1)));

        assertThat(failure)
                .isInstanceOf(TimeoutException.class)
                .hasMessageContaining("pipeline=shared-file{events=0, callbacks=0}")
                .hasMessageContaining("runtime=runtime-server{recent=[on-paint, buffer-ready]}");
    }

    @Test
    void retainsRecentViewportTransitions() throws Exception {
        RemoteSurfaceSupport.FrameProbe probe = new RemoteSurfaceSupport.FrameProbe();
        probe.accept(512, 384, ByteBuffer.allocate(4));
        for (int i = 0; i < 8; i++) {
            probe.accept(640, 480, ByteBuffer.allocate(4));
        }

        BrowserSession.PaintInfo resized = probe.await(512, 384, Duration.ofMillis(50));

        assertThat(resized.width).isEqualTo(512);
        assertThat(resized.height).isEqualTo(384);
    }
}
