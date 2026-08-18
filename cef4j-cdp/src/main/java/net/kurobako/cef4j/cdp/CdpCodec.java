package net.kurobako.cef4j.cdp;

import javax.annotation.Nullable;

/**
 * Converts between JSON bytes and JSON-compatible JDK values. Decoders return maps with string keys, lists, strings,
 * boxed numbers/booleans, or {@code null}; public CDP models never depend on the codec implementation's node types.
 */
public interface CdpCodec {
    byte[] encode(Object value);

    // JSON-null documents decode to null
    @SuppressWarnings("NullableForbidden")
    @Nullable
    Object decode(byte[] json);
}
