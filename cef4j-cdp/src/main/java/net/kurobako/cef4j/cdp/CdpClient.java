package net.kurobako.cef4j.cdp;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import net.kurobako.cef4j.cdp.generated.CdpDomains;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Typed CDP facade over any in-process or remote transport.
 *
 * <p>A decoder that fails must be reported to the caller of the command, so every failure of the decoder is caught and
 * delivered through the returned future rather than being lost in the completion stage.
 */
@SuppressWarnings("baumkuchen:catch-throwable")
public final class CdpClient implements AutoCloseable {
    private final CdpTransport transport;
    private final CdpCodec codec;
    private final CdpDomains domains;

    public CdpClient(CdpTransport transport, CdpCodec codec) {
        this.transport = Objects.requireNonNull(transport, "transport");
        this.codec = Objects.requireNonNull(codec, "codec");
        domains = new CdpDomains(this);
    }

    public CdpDomains domains() {
        return domains;
    }

    public CdpTransport raw() {
        return transport;
    }

    @SuppressWarnings("FutureReturnValueIgnored")
    @NullableBoundary("CDP commands omit the JSON params member with null")
    public <T> CompletionStage<T> call(
            String method, @Nullable Map<String, Object> params, Function<Map<String, Object>, T> decoder) {
        final byte[] bytes = params == null ? null : codec.encode(params);
        final CompletableFuture<byte[]> source =
                transport.execute(method, bytes).toCompletableFuture();
        final CompletableFuture<T> result = new CompletableFuture<>();
        source.whenComplete((value, failure) -> {
            try {
                if (failure != null) result.completeExceptionally(failure);
                else result.complete(decoder.apply(decodeObject(value)));
            } catch (Throwable decodeFailure) {
                result.completeExceptionally(decodeFailure);
            }
        });
        result.whenComplete((ignored, failure) -> {
            if (result.isCancelled()) source.cancel(false);
        });
        return result;
    }

    /** Sends a command whose result carries no fields. */
    @SuppressWarnings("FutureReturnValueIgnored")
    @NullableBoundary("CDP commands omit the JSON params member with null")
    public CompletionStage<Void> call(String method, @Nullable Map<String, Object> params) {
        final CompletableFuture<Map<String, Object>> source =
                call(method, params, result -> result).toCompletableFuture();
        final CompletableFuture<Void> result = source.thenAccept(ignored -> {});
        result.whenComplete((ignored, failure) -> {
            if (result.isCancelled()) source.cancel(false);
        });
        return result;
    }

    public void cancelPending(Throwable failure) {
        transport.cancelPending(failure);
    }

    public <T> CdpSubscription on(String method, Function<Map<String, Object>, T> decoder, Consumer<T> handler) {
        return transport.subscribe(method, bytes -> handler.accept(decoder.apply(decodeObject(bytes))));
    }

    @Override
    public void close() {
        transport.close();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> decodeObject(byte[] json) {
        return codec.decode(json).map(object -> (Map<String, Object>) object).orElse(Collections.emptyMap());
    }
}
