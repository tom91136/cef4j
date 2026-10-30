package net.kurobako.cef4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.Optional;
import net.kurobako.cef4j.gen.CefCallback;
import net.kurobako.cef4j.gen.CefRequest;
import net.kurobako.cef4j.gen.CefResourceHandler;
import net.kurobako.cef4j.gen.CefResponse;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link CefResourceHandler} that bridges CEF resource requests to Java's {@link URL} system. Any URL scheme
 * registered via {@link java.net.URL#setURLStreamHandlerFactory} (e.g. {@code classpath:}) is transparently handled.
 */
@SuppressWarnings("unused")
public final class UrlResourceHandler implements CefResourceHandler, AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(UrlResourceHandler.class);

    private @Nullable String mimeType;
    private long contentLength = -1;
    private @Nullable InputStream inputStream;
    private boolean failed;

    private final ConnectionFactory connectionFactory;

    public UrlResourceHandler() {
        this(url -> new URL(url).openConnection());
    }

    UrlResourceHandler(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    @NullableBoundary("CEF resource callbacks are nullable across supported ABI versions")
    public boolean processRequest(@Nullable CefRequest request, @Nullable CefCallback callback) {
        final boolean handled = open(request, new int[] {0}, callback);
        if (handled && callback != null) callback.cont();
        return handled;
    }

    @Override
    @NullableBoundary("CEF resource callbacks are nullable across supported ABI versions")
    public boolean open(@Nullable CefRequest request, int[] handleRequest, @Nullable CefCallback callback) {
        closeStream();
        failed = false;
        mimeType = null;
        contentLength = -1;
        handleRequest[0] = 1;
        if (request == null) {
            failed = true;
            return true;
        }
        final String url = request.getUrl().orElse(null);
        if (url == null) {
            failed = true;
            return true;
        }
        try {
            final URLConnection conn = connectionFactory.open(url);
            conn.connect();
            mimeType = stripMimeParams(conn.getContentType())
                    .filter(type -> !type.isEmpty() && !"content/unknown".equals(type))
                    .orElseGet(() -> guessMimeType(url));
            contentLength = conn.getContentLengthLong();
            inputStream = conn.getInputStream();
        } catch (IOException e) {
            log.debug("Failed to open URL: {}", url, e);
            failed = true;
        }
        return true;
    }

    @Override
    public void getResponseHeaders(
            @Nullable CefResponse response, long[] responseLength, @Nullable String redirectUrl) {
        if (response == null) return;
        if (failed || inputStream == null) {
            response.setStatus(404);
            response.setStatusText("Not Found");
            response.setMimeType("text/plain");
            responseLength[0] = 0;
        } else {
            response.setStatus(200);
            response.setStatusText("OK");
            response.setMimeType(mimeType);
            responseLength[0] = contentLength;
        }
    }

    @Override
    @NullableBoundary("CEF resource callbacks are nullable across supported ABI versions")
    public boolean readResponse(@Nullable ByteBuffer dataOut, int[] bytesRead, @Nullable CefCallback callback) {
        if (dataOut == null || inputStream == null) {
            bytesRead[0] = 0;
            return false;
        }
        try {
            final int bytesToRead = Math.max(0, dataOut.capacity());
            if (bytesToRead == 0) {
                bytesRead[0] = 0;
                return false;
            }
            final byte[] buf = new byte[bytesToRead];
            final int n = inputStream.read(buf);
            if (n <= 0) {
                bytesRead[0] = 0;
                closeStream();
                return false;
            }
            dataOut.clear();
            dataOut.put(buf, 0, n);
            bytesRead[0] = n;
            return true;
        } catch (IOException e) {
            log.debug("Error reading stream", e);
            bytesRead[0] = 0;
            closeStream();
            return false;
        }
    }

    @Override
    public void cancel() {
        closeStream();
    }

    @Override
    public void close() {
        cancel();
    }

    private void closeStream() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.debug("Failed to close URL resource stream", e);
            }
            inputStream = null;
        }
    }

    private static Optional<String> stripMimeParams(@Nullable String contentType) {
        return Optional.ofNullable(contentType).map(type -> {
            final int semi = type.indexOf(';');
            return semi >= 0 ? type.substring(0, semi).trim() : type.trim();
        });
    }

    private static String guessMimeType(String url) {
        final String lower = url.toLowerCase(java.util.Locale.ROOT);
        if (lower.endsWith(".js") || lower.endsWith(".mjs")) return "text/javascript";
        if (lower.endsWith(".html") || lower.endsWith(".htm")) return "text/html";
        if (lower.endsWith(".css")) return "text/css";
        if (lower.endsWith(".json")) return "application/json";
        if (lower.endsWith(".ttf")) return "font/ttf";
        if (lower.endsWith(".woff")) return "font/woff";
        if (lower.endsWith(".woff2")) return "font/woff2";
        if (lower.endsWith(".svg")) return "image/svg+xml";
        if (lower.endsWith(".png")) return "image/png";
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return "image/jpeg";
        if (lower.endsWith(".gif")) return "image/gif";
        if (lower.endsWith(".wasm")) return "application/wasm";
        if (lower.endsWith(".xml")) return "application/xml";
        if (lower.endsWith(".txt")) return "text/plain";
        return "application/octet-stream";
    }

    @FunctionalInterface
    interface ConnectionFactory {
        URLConnection open(String url) throws IOException;
    }
}
