package net.kurobako.cef4j.cdp;

import java.util.Optional;

/**
 * Converts between JSON bytes and JSON-compatible JDK values. Decoders return maps with string keys, lists, strings,
 * boxed numbers/booleans, or empty for JSON null; public CDP models never depend on the codec implementation's node
 * types.
 */
public interface CdpCodec {
    byte[] encode(Object value);

    Optional<Object> decode(byte[] json);
}
