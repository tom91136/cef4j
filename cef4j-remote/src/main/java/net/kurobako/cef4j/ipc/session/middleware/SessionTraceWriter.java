package net.kurobako.cef4j.ipc.session.middleware;

import java.io.Closeable;
import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/** Streaming destination used by a {@link SessionTraceCodec}. Implementations must be thread-safe. */
@NullableBoundary("trace records use null for absent optional detail")
public interface SessionTraceWriter extends Closeable {
    default void append(@Nonnull SessionTrace.Kind kind, long operationId, int messageId, @Nullable byte[] payload)
            throws IOException {
        append(kind, operationId, messageId, payload, null, null);
    }

    void append(
            @Nonnull SessionTrace.Kind kind,
            long operationId,
            int messageId,
            @Nullable byte[] payload,
            @Nullable String detailType,
            @Nullable String detailMessage)
            throws IOException;

    void flush() throws IOException;

    @Override
    void close() throws IOException;
}
