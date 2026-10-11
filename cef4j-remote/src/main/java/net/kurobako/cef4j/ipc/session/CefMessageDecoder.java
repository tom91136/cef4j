package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;

/**
 * Decodes one complete message payload. The buffer excludes the envelope header; implementations must select any
 * required {@link java.nio.ByteOrder} explicitly.
 */
@FunctionalInterface
public interface CefMessageDecoder<T extends CefMessageView> {
    T decode(ByteBuffer payload);
}
