package net.kurobako.cef4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefCallback;
import net.kurobako.cef4j.gen.CefRequest;
import net.kurobako.cef4j.gen.CefResourceHandler;
import net.kurobako.cef4j.gen.CefResourceReadCallback;
import net.kurobako.cef4j.gen.CefResponse;
import net.kurobako.cef4j.gen.NativePointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link CefResourceHandler} that bridges CEF resource requests to Java's {@link URL} system. Any URL scheme
 * registered via {@link java.net.URL#setURLStreamHandlerFactory} (e.g. {@code classpath:}) is transparently handled.
 */
@SuppressWarnings("unused")
public final class UrlResourceHandler implements CefResourceHandler {

    private static final Logger log = LoggerFactory.getLogger(UrlResourceHandler.class);

    private String mimeType;
    private long contentLength = -1;
    private InputStream inputStream;
    private boolean failed;

    @Override
    public boolean open(@Nullable CefRequest request, int[] handleRequest, @Nullable CefCallback callback) {
        handleRequest[0] = 1; // synchronous
        if (request == null) {
            failed = true;
            return true;
        }
        String url = request.getUrl().orElse(null);
        if (url == null) {
            failed = true;
            return true;
        }
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            mimeType = stripMimeParams(conn.getContentType());
            if (mimeType == null || mimeType.isEmpty() || "content/unknown".equals(mimeType)) {
                mimeType = guessMimeType(url);
            }
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
    public boolean read(
            @Nullable NativePointer dataOut,
            int bytesToRead,
            int[] bytesRead,
            @Nullable CefResourceReadCallback callback) {
        if (dataOut == null || inputStream == null) {
            bytesRead[0] = 0;
            return false;
        }
        try {
            byte[] buf = new byte[bytesToRead];
            int n = inputStream.read(buf);
            if (n <= 0) {
                bytesRead[0] = 0;
                return false;
            }
            NativeMemory.putBytes(dataOut.address, buf, 0, n);
            bytesRead[0] = n;
            return true;
        } catch (IOException e) {
            log.debug("Error reading stream", e);
            bytesRead[0] = 0;
            return false;
        }
    }

    @Override
    public void cancel() {
        closeStream();
    }

    private void closeStream() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }
            inputStream = null;
        }
    }

    /** Strip parameters (e.g. {@code ; charset=UTF-8}) - CEF's setMimeType expects just the MIME type. */
    private static String stripMimeParams(String contentType) {
        if (contentType == null) return null;
        int semi = contentType.indexOf(';');
        return semi >= 0 ? contentType.substring(0, semi).trim() : contentType.trim();
    }

    private static String guessMimeType(String url) {
        String lower = url.toLowerCase();
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
}
