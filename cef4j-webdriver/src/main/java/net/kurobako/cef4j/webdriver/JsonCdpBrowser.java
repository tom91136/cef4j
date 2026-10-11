package net.kurobako.cef4j.webdriver;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpTransport;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/** Browser lifecycle and JSON-tree Chrome DevTools channel consumed by the shared WebDriver implementation. */
@NullableBoundary("CDP commands use null to omit parameters")
public interface JsonCdpBrowser extends CdpTransport {

    /** Navigates the main frame using the hosting CEF API. */
    CompletableFuture<Void> loadUrl(String url);

    CompletableFuture<Boolean> canGoBack();

    CompletableFuture<Void> goBack();

    CompletableFuture<Boolean> canGoForward();

    CompletableFuture<Void> goForward();

    /** Returns CEF's current main-frame loading state. */
    CompletableFuture<Boolean> loading();

    @Override
    void close();

    WebDriverJsonCodec jsonCodec();

    default CompletableFuture<JsonObject> send(String method, @Nullable JsonObject params) {
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

    default EventRegistration on(String method, Consumer<JsonObject> handler) {
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
