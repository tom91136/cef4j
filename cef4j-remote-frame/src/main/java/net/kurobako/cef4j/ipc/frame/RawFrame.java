package net.kurobako.cef4j.ipc.frame;

import java.nio.ByteBuffer;
import java.util.Objects;
import javax.annotation.Nonnull;

/** A zero-copy view of one decoded CEF paint. Its pixels are valid only for the delivering callback. */
public final class RawFrame {
    private final int width;
    private final int height;
    private final int stride;
    private final ByteBuffer pixels;
    private final FrameMetadata metadata;

    public RawFrame(int width, int height, int stride, @Nonnull ByteBuffer pixels, @Nonnull FrameMetadata metadata) {
        if (width <= 0 || height <= 0 || stride < width * 4) throw new IllegalArgumentException("invalid dimensions");
        this.width = width;
        this.height = height;
        this.stride = stride;
        this.pixels = Objects.requireNonNull(pixels, "pixels").asReadOnlyBuffer();
        this.metadata = Objects.requireNonNull(metadata, "metadata");
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int stride() {
        return stride;
    }

    @Nonnull
    public ByteBuffer pixels() {
        return pixels.asReadOnlyBuffer();
    }

    @Nonnull
    public FrameMetadata metadata() {
        return metadata;
    }

    /** Makes an explicitly owned snapshot suitable for retaining beyond the callback. */
    @Nonnull
    public RawFrame snapshot() {
        ByteBuffer source = pixels();
        ByteBuffer copy = ByteBuffer.allocateDirect(source.remaining());
        copy.put(source).flip();
        return new RawFrame(width, height, stride, copy, metadata);
    }
}
