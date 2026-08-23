package net.kurobako.cef4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class CefFrameBufferTest {

    @Test
    void rejectsDimensionsWhosePixelCountExceedsTheSupportedMaximum() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CefFrameBuffer<>(50_000, 50_000, (previous, pixels, width, height, dirty) -> 1))
                .withMessageContaining("pixel count");
    }

    @Test
    void rejectsTruncatedPaintWithoutPoisoningBackPressure() {
        AtomicInteger writes = new AtomicInteger();
        CefFrameBuffer<Integer> frames =
                new CefFrameBuffer<>(2, 2, (previous, pixels, width, height, dirty) -> writes.incrementAndGet());

        assertThat(frames.onPaint(ByteBuffer.allocateDirect(12), 2, 2)).isEmpty();
        assertThat(frames.onPaint(ByteBuffer.allocateDirect(16), 2, 2)).contains(1);
    }

    @Test
    void writerFailureDoesNotPoisonBackPressure() {
        AtomicInteger writes = new AtomicInteger();
        CefFrameBuffer<Integer> frames = new CefFrameBuffer<>(2, 2, (previous, pixels, width, height, dirty) -> {
            if (writes.getAndIncrement() == 0) throw new IllegalStateException("stamp failed");
            return 7;
        });

        assertThatThrownBy(() -> frames.onPaint(ByteBuffer.allocateDirect(16), 2, 2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("stamp failed");
        assertThat(frames.onPaint(ByteBuffer.allocateDirect(16), 2, 2)).contains(7);
    }

    @Test
    void checkedPixelCopyRejectsInvalidDimensionsAndTruncatedBuffers() {
        assertThat(CefFrameBuffer.copyBgraPixels(ByteBuffer.allocate(16), 0, 2)).isEmpty();
        assertThat(CefFrameBuffer.copyBgraPixels(ByteBuffer.allocate(16), -1, 2))
                .isEmpty();
        assertThat(CefFrameBuffer.copyBgraPixels(ByteBuffer.allocate(16), Integer.MAX_VALUE, 2))
                .isEmpty();
        assertThat(CefFrameBuffer.copyBgraPixels(ByteBuffer.allocate(15), 2, 2)).isEmpty();
        assertThat(CefFrameBuffer.copyBgraPixels(ByteBuffer.allocate(16), 2, 2))
                .hasValueSatisfying(pixels -> assertThat(pixels).hasSize(4));
    }
}
