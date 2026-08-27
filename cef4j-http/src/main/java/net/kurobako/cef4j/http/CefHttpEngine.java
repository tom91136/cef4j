package net.kurobako.cef4j.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

interface CefHttpEngine {

    @Nonnull
    Cancellation send(@Nonnull RequestSpec spec, @Nonnull ResponseSink sink);

    interface Cancellation extends AutoCloseable {
        void cancel();

        @Override
        default void close() {
            cancel();
        }
    }

    interface ResponseSink {
        void onResponse(int status, @Nonnull String statusText, @Nonnull Map<String, List<String>> headers);

        void onData(@Nonnull byte[] chunk);

        void onComplete();

        void onError(@Nonnull IOException error);
    }

    final class RequestSpec {
        final String url;
        final String method;
        final Map<String, List<String>> headers;
        final byte[] body;

        RequestSpec(
                @Nonnull String url,
                @Nonnull String method,
                @Nonnull Map<String, List<String>> headers,
                @Nonnull byte[] body) {
            this.url = url;
            this.method = method;
            this.headers = headers;
            this.body = body;
        }
    }
}
