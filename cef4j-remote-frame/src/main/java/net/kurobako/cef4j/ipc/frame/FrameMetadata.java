package net.kurobako.cef4j.ipc.frame;

import java.util.List;
import java.util.Objects;

/**
 * Description of a delivered pixel frame. The sequence is monotonic within one transport instance and the timestamp is
 * the JVM-side {@link System#nanoTime} capture time, not wall-clock time.
 */
public final class FrameMetadata {
    private final int sequenceId;
    private final long sourceSequence;
    private final long timestampNanos;
    private final PixelFormat format;
    private final List<Rect> dirtyRects;

    public FrameMetadata(int sequenceId, long timestampNanos, PixelFormat format, List<Rect> dirtyRects) {
        this(sequenceId, sequenceId, timestampNanos, format, dirtyRects);
    }

    public FrameMetadata(
            int sequenceId, long sourceSequence, long timestampNanos, PixelFormat format, List<Rect> dirtyRects) {
        this.sequenceId = sequenceId;
        this.sourceSequence = sourceSequence;
        this.timestampNanos = timestampNanos;
        this.format = Objects.requireNonNull(format, "format");
        this.dirtyRects = List.copyOf(Objects.requireNonNull(dirtyRects, "dirtyRects"));
    }

    public int sequenceId() {
        return sequenceId;
    }

    /** Sequence assigned by the producing runtime server; use this for encoded-frame dependencies. */
    public long sourceSequence() {
        return sourceSequence;
    }

    public long timestampNanos() {
        return timestampNanos;
    }

    public PixelFormat format() {
        return format;
    }

    public List<Rect> dirtyRects() {
        return dirtyRects;
    }
}
