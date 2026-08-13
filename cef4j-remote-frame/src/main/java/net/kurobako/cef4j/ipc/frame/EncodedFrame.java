package net.kurobako.cef4j.ipc.frame;

import java.nio.ByteBuffer;
import java.util.Objects;
import javax.annotation.Nonnull;

/** Transport-neutral encoded-frame envelope. */
public final class EncodedFrame {
    public static final long NO_BASE_SEQUENCE = -1L;

    private final CodecDescriptor codec;
    private final long sequence;
    private final long baseSequence;
    private final boolean keyFrame;
    private final int width;
    private final int height;
    private final ByteBuffer payload;

    public EncodedFrame(
            @Nonnull CodecDescriptor codec,
            long sequence,
            long baseSequence,
            boolean keyFrame,
            int width,
            int height,
            @Nonnull ByteBuffer payload) {
        if (sequence < 0 || width <= 0 || height <= 0) throw new IllegalArgumentException("invalid frame envelope");
        if (keyFrame && baseSequence != NO_BASE_SEQUENCE) {
            throw new IllegalArgumentException("key frames cannot reference a base frame");
        }
        if (!keyFrame && baseSequence < 0) throw new IllegalArgumentException("delta frame requires base sequence");
        this.codec = Objects.requireNonNull(codec, "codec");
        this.sequence = sequence;
        this.baseSequence = baseSequence;
        this.keyFrame = keyFrame;
        this.width = width;
        this.height = height;
        this.payload = Objects.requireNonNull(payload, "payload").asReadOnlyBuffer();
    }

    @Nonnull
    public CodecDescriptor codec() {
        return codec;
    }

    public long sequence() {
        return sequence;
    }

    public long baseSequence() {
        return baseSequence;
    }

    public boolean keyFrame() {
        return keyFrame;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    @Nonnull
    public ByteBuffer payload() {
        return payload.asReadOnlyBuffer();
    }
}
