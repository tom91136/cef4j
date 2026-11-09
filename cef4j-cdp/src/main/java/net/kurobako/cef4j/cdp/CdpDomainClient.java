package net.kurobako.cef4j.cdp;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** Base of the generated per-domain command clients. */
@NullableBoundary("CDP commands omit the JSON params member with null")
public abstract class CdpDomainClient {
    private final CdpClient client;

    protected CdpDomainClient(CdpClient client) {
        this.client = client;
    }

    protected final CompletionStage<Void> call(String method) {
        return client.call(method, null);
    }

    protected final CompletionStage<Void> call(String method, @Nullable Map<String, Object> params) {
        return client.call(method, params);
    }

    protected final <T> CompletionStage<T> call(String method, Function<Map<String, Object>, T> decoder) {
        return client.call(method, null, decoder);
    }

    protected final <T> CompletionStage<T> call(
            String method, @Nullable Map<String, Object> params, Function<Map<String, Object>, T> decoder) {
        return client.call(method, params, decoder);
    }

    protected final CompletionStage<Void> call(String method, @Nullable CdpObject request) {
        return client.call(method, request == null ? null : request.toMap());
    }

    protected final <T> CompletionStage<T> call(
            String method, @Nullable CdpObject request, Function<Map<String, Object>, T> decoder) {
        return client.call(method, request == null ? null : request.toMap(), decoder);
    }

    protected final <T> CdpSubscription on(
            String method, Function<Map<String, Object>, T> decoder, Consumer<T> handler) {
        return client.on(method, decoder, handler);
    }

    protected static Map<String, Object> params(@Nullable Object... pairs) {
        final Map<String, Object> result = new LinkedHashMap<>();
        for (int i = 0; i < pairs.length; i += 2) {
            final @Nullable Object value = pairs[i + 1];
            if (value != null) result.put((String) pairs[i], CdpObject.json(value));
        }
        return result;
    }
}
