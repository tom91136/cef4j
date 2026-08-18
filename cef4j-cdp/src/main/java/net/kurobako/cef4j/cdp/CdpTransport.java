package net.kurobako.cef4j.cdp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Raw, transport-independent CDP command and event channel. */
public interface CdpTransport extends AutoCloseable {
    // null params omit the JSON-RPC request body
    @SuppressWarnings("NullableForbidden")
    @Nonnull
    CompletionStage<byte[]> execute(@Nonnull String method, @Nullable byte[] params);

    @Nonnull
    CdpSubscription subscribe(@Nonnull String method, @Nonnull Consumer<byte[]> handler);

    /** Begins transport cleanup and completes when any remote detach has been acknowledged. */
    @Nonnull
    default CompletionStage<Void> closeAsync() {
        close();
        return CompletableFuture.completedFuture(null);
    }

    @Override
    default void close() {}
}
