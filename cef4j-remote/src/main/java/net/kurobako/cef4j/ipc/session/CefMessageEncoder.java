package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/** Writes one complete message payload into a destination buffer. */
public interface CefMessageEncoder {

    /** The wire-protocol message ID. */
    int messageId();

    /** Number of payload bytes that {@link #encodeInto} will write. */
    int encodedSize();

    /** Append exactly {@link #encodedSize} bytes to {@code dst}, advancing its position. */
    void encodeInto(@Nonnull ByteBuffer dst);
}
