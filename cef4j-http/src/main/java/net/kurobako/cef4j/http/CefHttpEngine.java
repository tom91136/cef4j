package net.kurobako.cef4j.http;

import java.io.IOException;
import java.util.List;
import java.util.Map;

interface CefHttpEngine {

    Cancellation send(RequestSpec spec, ResponseSink sink);

    interface Cancellation extends AutoCloseable {
        void cancel();

        @Override
        default void close() {
            cancel();
        }
    }

    interface ResponseSink {
        void onResponse(int status, String statusText, Map<String, List<String>> headers);

        void onData(byte[] chunk);

        void onComplete();

        void onError(IOException error);
    }

    final class RequestSpec {
        final String url;
        final String method;
        final Map<String, List<String>> headers;
        final byte[] body;

        RequestSpec(String url, String method, Map<String, List<String>> headers, byte[] body) {
            this.url = url;
            this.method = method;
            this.headers = headers;
            this.body = body;
        }
    }
}
