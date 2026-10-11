package net.kurobako.cef4j.cdp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** Raw, transport-independent CDP command and event channel. */
@NullableBoundary("CDP transports use null bytes to omit command parameters")
public interface CdpTransport extends AutoCloseable {
    CompletionStage<byte[]> execute(String method, byte @Nullable [] params);

    /** Fails transport requests that no longer have a live caller or command deadline. */
    default void cancelPending(Throwable failure) {}

    CdpSubscription subscribe(String method, Consumer<byte[]> handler);

    /** Begins transport cleanup and completes when any remote detach has been acknowledged. */
    default CompletionStage<Void> closeAsync() {
        close();
        return CompletableFuture.completedFuture(null);
    }

    @Override
    default void close() {}
}
