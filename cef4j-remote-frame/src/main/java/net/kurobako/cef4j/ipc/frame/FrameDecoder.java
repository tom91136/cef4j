package net.kurobako.cef4j.ipc.frame;

import java.io.IOException;
import javax.annotation.Nonnull;

/** Stateful decoder counterpart for custom and inter-frame codecs. */
public interface FrameDecoder extends AutoCloseable {
    @Nonnull
    CodecDescriptor descriptor();

    @Nonnull
    RawFrame decode(@Nonnull EncodedFrame frame) throws IOException;

    default void reset(@Nonnull CodecResetReason reason) {}

    @Override
    default void close() {}
}
