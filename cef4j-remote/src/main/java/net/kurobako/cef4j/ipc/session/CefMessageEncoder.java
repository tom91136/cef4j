package net.kurobako.cef4j.ipc.session;

import java.nio.ByteBuffer;

/** Writes one complete message payload into a destination buffer. */
public interface CefMessageEncoder {

    /** Number of payload bytes that {@link #encodeInto} will write. */
    default int encodedSize() {
        return 0;
    }

    /** Append exactly {@link #encodedSize} bytes to {@code dst}, advancing its position. */
    default void encodeInto(ByteBuffer dst) {}
}
