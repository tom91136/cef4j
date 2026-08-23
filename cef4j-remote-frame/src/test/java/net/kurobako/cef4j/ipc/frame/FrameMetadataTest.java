package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class FrameMetadataTest {
    @Test
    void copiesDirtyRectList() {
        List<Rect> source = new ArrayList<>();
        Rect rect = new Rect(1, 2, 3, 4);
        source.add(rect);
        FrameMetadata metadata = new FrameMetadata(1, 2L, 3L, PixelFormat.BGRA, source);

        source.clear();

        assertThat(metadata.dirtyRects()).containsExactly(rect);
    }
}
