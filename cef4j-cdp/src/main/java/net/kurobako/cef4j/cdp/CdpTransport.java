package net.kurobako.cef4j.cdp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/** Raw, transport-independent CDP command and event channel. */
@NullableBoundary("CDP transports use null bytes to omit command parameters")
public interface CdpTransport extends AutoCloseable {
    @Nonnull
    CompletionStage<byte[]> execute(@Nonnull String method, @Nullable byte[] params);

    /** Fails transport requests that no longer have a live caller or command deadline. */
    default void cancelPending(@Nonnull Throwable failure) {}

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
