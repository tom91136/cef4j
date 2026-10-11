package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;

/** Stateful, per-browser frame encoder. Instances are invoked serially and are never shared between streams. */
public interface FrameCodec extends AutoCloseable {
    CodecDescriptor descriptor();

    EncodedFrame encode(RawFrame frame) throws IOException;

    /** Requests that the next encoded frame be independently decodable. */
    default void requestKeyFrame() {
        reset(CodecResetReason.KEY_FRAME_REQUESTED);
    }

    /** Drops codec reference state after a resize, dependency gap, or stream restart. */
    default void reset(CodecResetReason reason) {}

    @Override
    default void close() {}
}
