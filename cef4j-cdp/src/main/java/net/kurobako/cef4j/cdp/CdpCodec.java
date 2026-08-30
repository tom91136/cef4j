package net.kurobako.cef4j.cdp;

import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Converts between JSON bytes and JSON-compatible JDK values. Decoders return maps with string keys, lists, strings,
 * boxed numbers/booleans, or {@code null}; public CDP models never depend on the codec implementation's node types.
 */
@NullableBoundary("JSON null decodes to the JDK null wire value")
public interface CdpCodec {
    byte[] encode(Object value);

    @Nullable
    Object decode(byte[] json);
}
