package net.kurobako.cef4j.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class CefHttpURLConnection extends HttpURLConnection {

    private static final byte[] EOF = new byte[0];

    private final CefHttpEngine engine;
    private final ByteArrayOutputStream reqBody = new ByteArrayOutputStream();
    private final CountDownLatch responseLatch = new CountDownLatch(1);
    private final LinkedBlockingQueue<byte[]> chunks = new LinkedBlockingQueue<>();

    private volatile int statusCode = -1;
    private volatile String statusText = "";
    private volatile Map<String, List<String>> responseHeaders = Map.of();

    @Nullable
    private volatile IOException error;

    @Nullable
    private CefHttpEngine.Cancellation cancellation;

    CefHttpURLConnection(@Nonnull URL url, @Nonnull CefHttpEngine engine) {
        super(url);
        this.engine = engine;
    }

    @Override
    public synchronized void connect() throws IOException {
        if (connected) return;
        // Snapshot request headers before flipping connected; URLConnection.getRequestProperties()
        // throws IllegalStateException once connected=true.
        Map<String, List<String>> headers = getRequestProperties();
        connected = true;
        CefHttpEngine.RequestSpec spec =
                new CefHttpEngine.RequestSpec(url.toString(), method, headers, reqBody.toByteArray());
        cancellation = engine.send(spec, new Sink());
    }

    private void awaitResponse() throws IOException {
        connect();
        try {
            responseLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("interrupted while awaiting response", e);
        }
        IOException err = error;
        if (err != null) throw err;
    }

    @Override
    public int getResponseCode() throws IOException {
        awaitResponse();
        return statusCode;
    }

    @Override
    @Nullable
    public String getResponseMessage() throws IOException {
        awaitResponse();
        return statusText;
    }

    @Override
    public Map<String, List<String>> getHeaderFields() {
        try {
            awaitResponse();
        } catch (IOException e) {
            return Map.of();
        }
        return responseHeaders;
    }

    @Override
    @Nullable
    public String getHeaderField(@Nullable String name) {
        if (name == null) return null;
        try {
            awaitResponse();
        } catch (IOException e) {
            return null;
        }
        for (Map.Entry<String, List<String>> e : responseHeaders.entrySet()) {
            if (name.equalsIgnoreCase(e.getKey())) {
                List<String> vs = e.getValue();
                if (!vs.isEmpty()) return vs.get(0);
            }
        }
        return null;
    }

    @Override
    @Nonnull
    public OutputStream getOutputStream() throws IOException {
        if (connected) throw new ProtocolException("Cannot write after connect");
        if (!doOutput) throw new ProtocolException("setDoOutput(true) required before getOutputStream");
        return reqBody;
    }

    @Override
    @Nonnull
    public InputStream getInputStream() throws IOException {
        awaitResponse();
        if (statusCode >= 400) {
            throw new IOException("Server returned HTTP response code " + statusCode + " for URL: " + url);
        }
        return new ChunkInputStream();
    }

    @Override
    @Nullable
    public InputStream getErrorStream() {
        try {
            awaitResponse();
        } catch (IOException e) {
            return null;
        }
        if (statusCode < 400) return null;
        return new ChunkInputStream();
    }

    @Override
    public void disconnect() {
        CefHttpEngine.Cancellation c = cancellation;
        if (c != null) c.cancel();
    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    private final class Sink implements CefHttpEngine.ResponseSink {
        @Override
        public void onResponse(int status, @Nonnull String statusText0, @Nonnull Map<String, List<String>> headers) {
            statusCode = status;
            statusText = statusText0;
            responseHeaders = Map.copyOf(headers);
            responseLatch.countDown();
        }

        @Override
        public void onData(@Nonnull byte[] chunk) {
            if (chunk.length > 0) chunks.offer(chunk);
        }

        @Override
        public void onComplete() {
            chunks.offer(EOF);
        }

        @Override
        public void onError(@Nonnull IOException err) {
            error = err;
            chunks.offer(EOF);
            responseLatch.countDown();
        }
    }

    private final class ChunkInputStream extends InputStream {
        private byte[] current = EOF;
        private int pos = 0;
        private boolean eof = false;

        @Override
        public int read() throws IOException {
            byte[] one = new byte[1];
            int n = read(one, 0, 1);
            return (n < 0) ? -1 : (one[0] & 0xff);
        }

        @Override
        public int read(@Nonnull byte[] b, int off, int len) throws IOException {
            if (eof) return -1;
            while (pos >= current.length) {
                try {
                    current = chunks.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IOException("interrupted while reading body", e);
                }
                if (current == EOF) {
                    eof = true;
                    return -1;
                }
                pos = 0;
            }
            int n = Math.min(len, current.length - pos);
            System.arraycopy(current, pos, b, off, n);
            pos += n;
            return n;
        }
    }
}
