package net.kurobako.cef4j.cdp;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.generated.CdpDomains;

/** Typed CDP facade over any in-process or remote transport. */
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

    @SuppressWarnings({"FutureReturnValueIgnored", "NullableForbidden"})
    public <T> CompletionStage<T> call(
            String method, @Nullable Map<String, Object> params, Function<Map<String, Object>, T> decoder) {
        byte[] bytes = params == null ? null : codec.encode(params);
        CompletableFuture<byte[]> source = transport.execute(method, bytes).toCompletableFuture();
        CompletableFuture<T> result = new CompletableFuture<>();
        source.whenComplete((value, failure) -> {
            try {
                if (failure != null) result.completeExceptionally(failure);
                else result.complete(decoder.apply(asObject(codec.decode(value))));
            } catch (Throwable decodeFailure) {
                result.completeExceptionally(decodeFailure);
            }
        });
        result.whenComplete((ignored, failure) -> {
            if (result.isCancelled()) source.cancel(false);
        });
        return result;
    }

    public void cancelPending(Throwable failure) {
        transport.cancelPending(failure);
    }

    public <T> CdpSubscription on(String method, Function<Map<String, Object>, T> decoder, Consumer<T> handler) {
        return transport.subscribe(method, bytes -> handler.accept(decoder.apply(asObject(codec.decode(bytes)))));
    }

    @Override
    public void close() {
        transport.close();
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> asObject(@Nullable Object value) {
        return value == null ? Collections.emptyMap() : (Map<String, Object>) value;
    }
}
