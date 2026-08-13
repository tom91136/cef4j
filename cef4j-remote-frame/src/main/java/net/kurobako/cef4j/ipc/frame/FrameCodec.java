package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import javax.annotation.Nonnull;

/** Stateful, per-browser frame encoder. Instances are invoked serially and are never shared between streams. */
public interface FrameCodec extends AutoCloseable {
    @Nonnull
    CodecDescriptor descriptor();

    @Nonnull
    EncodedFrame encode(@Nonnull RawFrame frame) throws IOException;

    /** Requests that the next encoded frame be independently decodable. */
    default void requestKeyFrame() {
        reset(CodecResetReason.KEY_FRAME_REQUESTED);
    }

    /** Drops codec reference state after a resize, dependency gap, or stream restart. */
    default void reset(@Nonnull CodecResetReason reason) {}

    @Override
    default void close() {}
}
