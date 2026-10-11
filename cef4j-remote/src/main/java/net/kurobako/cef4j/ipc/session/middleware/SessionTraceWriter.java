package net.kurobako.cef4j.ipc.session.middleware;

import java.io.Closeable;
import java.io.IOException;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** Streaming destination used by a {@link SessionTraceCodec}. Implementations must be thread-safe. */
@NullableBoundary("trace records use null for absent optional detail")
public interface SessionTraceWriter extends Closeable {
    default void append(SessionTrace.Kind kind, long operationId, int messageId, byte @Nullable [] payload)
            throws IOException {
        append(kind, operationId, messageId, payload, null, null);
    }

    void append(
            SessionTrace.Kind kind,
            long operationId,
            int messageId,
            byte @Nullable [] payload,
            @Nullable String detailType,
            @Nullable String detailMessage)
            throws IOException;

    void flush() throws IOException;

    @Override
    void close() throws IOException;
}
