package net.kurobako.cef4j.ipc.frame;

import java.util.Map;
import javax.annotation.Nonnull;

/** Service-provider interface for application codecs. Providers are discovered with {@link java.util.ServiceLoader}. */
public interface FrameCodecProvider {
    @Nonnull
    CodecDescriptor descriptor();

    @Nonnull
    FrameCodec newEncoder(@Nonnull Map<String, String> configuration);

    @Nonnull
    FrameDecoder newDecoder(@Nonnull Map<String, String> configuration);
}
