package net.kurobako.cef4j.test;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/** Loopback HTTP fixture serving fixed responses per path. */
public final class LocalTestServer implements AutoCloseable {
    private final HttpServer server;

    private LocalTestServer(HttpServer server) {
        this.server = server;
    }

    /** Serves each route's HTML body. */
    public static LocalTestServer startServer(Map<String, String> routes) throws IOException {
        Map<String, ResponseSpec> specs = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : routes.entrySet()) {
            specs.put(entry.getKey(), ResponseSpec.html(entry.getValue()));
        }
        return startServerWithResponses(specs);
    }

    public static LocalTestServer startServerWithResponses(Map<String, ResponseSpec> routes) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        for (Map.Entry<String, ResponseSpec> entry : routes.entrySet()) {
            server.createContext(entry.getKey(), exchange -> respond(exchange, entry.getValue()));
        }
        server.start();
        return new LocalTestServer(server);
    }

    public String url(String path) {
        return "http://127.0.0.1:" + server.getAddress().getPort() + path;
    }

    @Override
    public void close() {
        server.stop(0);
    }

    private static void respond(HttpExchange exchange, ResponseSpec response) throws IOException {
        if (response.requestStarted != null) response.requestStarted.countDown();
        if (response.delayMillis > 0) {
            try {
                new CountDownLatch(1).await(response.delayMillis, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Interrupted while delaying response", e);
            }
        }
        for (Map.Entry<String, String> header : response.headers.entrySet()) {
            exchange.getResponseHeaders().set(header.getKey(), header.getValue());
        }
        byte[] bytes = response.body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(response.statusCode, bytes.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(bytes);
        }
    }

    public static final class ResponseSpec {
        private final int statusCode;
        private final Map<String, String> headers;
        private final String body;
        private final long delayMillis;

        @Nullable
        private final CountDownLatch requestStarted;

        private ResponseSpec(
                int statusCode,
                Map<String, String> headers,
                String body,
                long delayMillis,
                @Nullable CountDownLatch requestStarted) {
            this.statusCode = statusCode;
            this.headers = headers;
            this.body = body;
            this.delayMillis = delayMillis;
            this.requestStarted = requestStarted;
        }

        public static ResponseSpec html(String body) {
            return html(body, 0);
        }

        public static ResponseSpec html(String body, long delayMillis) {
            return new ResponseSpec(200, Map.of("Content-Type", "text/html; charset=UTF-8"), body, delayMillis, null);
        }

        /** Delays the response and counts down {@code requestStarted} as soon as the request arrives. */
        public static ResponseSpec html(String body, long delayMillis, CountDownLatch requestStarted) {
            return new ResponseSpec(
                    200, Map.of("Content-Type", "text/html; charset=UTF-8"), body, delayMillis, requestStarted);
        }

        public static ResponseSpec redirect(String location) {
            return new ResponseSpec(302, Map.of("Location", location), "", 0, null);
        }
    }
}
