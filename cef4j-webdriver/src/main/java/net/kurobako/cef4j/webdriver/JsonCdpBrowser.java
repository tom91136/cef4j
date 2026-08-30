package net.kurobako.cef4j.webdriver;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.policy.NullableBoundary;

/** JSON-tree view over the raw, codec-neutral {@link CdpBrowser} channel. */
@NullableBoundary("CDP commands use null to omit parameters")
public interface JsonCdpBrowser extends CdpBrowser {
    @Nonnull
    WebDriverJsonCodec jsonCodec();

    @Nonnull
    default CompletableFuture<JsonObject> send(@Nonnull String method, @Nullable JsonObject params) {
        byte[] encoded = params == null ? null : jsonCodec().encode(params);
        return execute(method, encoded)
                .thenApply(bytes -> {
                    JsonElement value =
                            bytes.length == 0 ? new JsonObject() : jsonCodec().decode(bytes);
                    if (!value.isObject()) throw new IllegalArgumentException("DevTools payload is not an object");
                    return value.asObject();
                })
                .toCompletableFuture();
    }

    @Nonnull
    default EventRegistration on(@Nonnull String method, @Nonnull Consumer<JsonObject> handler) {
        CdpSubscription subscription = subscribe(method, bytes -> {
            JsonElement value = jsonCodec().decode(bytes);
            if (!value.isObject()) throw new IllegalArgumentException("DevTools event payload is not an object");
            handler.accept(value.asObject());
        });
        return subscription::close;
    }

    @FunctionalInterface
    interface EventRegistration extends AutoCloseable {
        @Override
        void close();
    }
}
