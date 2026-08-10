package net.kurobako.cef4j.ipc.frame;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;

/**
 * Per-frame description handed to {@link FrameTransport.FrameConsumer}. Provides the bookkeeping a renderer needs for
 * partial blits without re-scanning the pixel buffer.
 *
 * <p>{@code sequenceId} is monotonically increasing within a single transport instance, useful for diagnostics or
 * frame-rate tracking; consumers must not assume it matches the helper's view-state counter. {@code timestampNanos} is
 * the JVM-side capture time of the paint event ({@link System#nanoTime}), not a wall-clock time.
 */
public final class FrameMetadata {

    private final int sequenceId;
    private final long timestampNanos;
    private final PixelFormat format;
    private final List<Rect> dirtyRects;

    public FrameMetadata(
            int sequenceId, long timestampNanos, @Nonnull PixelFormat format, @Nonnull List<Rect> dirtyRects) {
        this.sequenceId = sequenceId;
        this.timestampNanos = timestampNanos;
        this.format = format;
        this.dirtyRects = Collections.unmodifiableList(dirtyRects);
    }

    public int sequenceId() {
        return sequenceId;
    }

    public long timestampNanos() {
        return timestampNanos;
    }

    @Nonnull
    public PixelFormat format() {
        return format;
    }

    /** Rectangles inside the frame that changed since the previous frame; never empty. */
    @Nonnull
    public List<Rect> dirtyRects() {
        return dirtyRects;
    }
}
