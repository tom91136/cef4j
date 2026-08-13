package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/**
 * Writes a single message's payload into a destination buffer. Codegen produces concrete encoders in Slice D;
 * hand-written encoders are used in Slice C and tests.
 */
public interface CefMessageEncoder {

    /** The wire-protocol message ID. */
    int messageId();

    /** Number of payload bytes that {@link #encodeInto} will write. */
    int encodedSize();

    /** Append exactly {@link #encodedSize} bytes to {@code dst}, advancing its position. */
    void encodeInto(@Nonnull ByteBuffer dst);
}
