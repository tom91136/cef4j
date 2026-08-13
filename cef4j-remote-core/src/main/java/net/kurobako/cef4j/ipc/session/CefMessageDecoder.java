package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/**
 * Decodes a payload into a {@link CefMessageView}. Codegen produces concrete decoders in Slice D.
 *
 * <p>The buffer passed in covers exactly the payload (envelope header already consumed). Implementations must not
 * assume any particular {@link java.nio.ByteOrder}; set order explicitly if the format relies on it.
 */
@FunctionalInterface
public interface CefMessageDecoder<T extends CefMessageView> {
    @Nonnull
    T decode(@Nonnull ByteBuffer payload);
}
