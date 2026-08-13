package net.kurobako.cef4j.cdp;

import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Raw, transport-independent CDP command and event channel. */
public interface CdpTransport extends AutoCloseable {
    @Nonnull
    CompletionStage<byte[]> execute(@Nonnull String method, @Nullable byte[] params);

    @Nonnull
    CdpSubscription subscribe(@Nonnull String method, @Nonnull Consumer<byte[]> handler);

    @Override
    default void close() {}
}
