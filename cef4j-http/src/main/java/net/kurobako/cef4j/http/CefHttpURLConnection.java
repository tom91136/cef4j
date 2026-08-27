package net.kurobako.cef4j.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class CefHttpURLConnection extends HttpURLConnection implements AutoCloseable {

    private static final byte[] EOF = new byte[0];
    private static final int MAX_REDIRECTS = 20;
    private static final int DEFAULT_MAX_REQUEST_BODY = 128 * 1024 * 1024;
    private static final int DEFAULT_MAX_BUFFERED_RESPONSE_BODY = 16 * 1024 * 1024;
    private static final int DEFAULT_MAX_BUFFERED_RESPONSE_CHUNKS = 256;

    private final CefHttpEngine engine;
    private final int maxRequestBodyBytes;
    private final int maxBufferedResponseBytes;
    private final int maxBufferedResponseChunks;
    private final ByteArrayOutputStream reqBody = new ByteArrayOutputStream();
    private volatile Attempt attempt;

    @Nullable
    private volatile CefHttpEngine.Cancellation cancellation;

    private Map<String, List<String>> requestHeaders = Map.of();
    private String requestMethod = "GET";
    private byte[] requestBody = new byte[0];

    CefHttpURLConnection(@Nonnull URL url, @Nonnull CefHttpEngine engine) {
        this(
                url,
                engine,
                DEFAULT_MAX_REQUEST_BODY,
                DEFAULT_MAX_BUFFERED_RESPONSE_BODY,
                DEFAULT_MAX_BUFFERED_RESPONSE_CHUNKS);
    }

    CefHttpURLConnection(@Nonnull URL url, @Nonnull CefHttpEngine engine, int maxRequestBodyBytes) {
        this(
                url,
                engine,
                maxRequestBodyBytes,
                DEFAULT_MAX_BUFFERED_RESPONSE_BODY,
                DEFAULT_MAX_BUFFERED_RESPONSE_CHUNKS);
    }

    CefHttpURLConnection(
            @Nonnull URL url,
            @Nonnull CefHttpEngine engine,
            int maxRequestBodyBytes,
            int maxBufferedResponseBytes,
            int maxBufferedResponseChunks) {
        super(url);
        this.engine = engine;
        if (maxRequestBodyBytes <= 0 || maxBufferedResponseBytes <= 0 || maxBufferedResponseChunks <= 0) {
            throw new IllegalArgumentException("body and chunk limits must be positive");
        }
        this.maxRequestBodyBytes = maxRequestBodyBytes;
        this.maxBufferedResponseBytes = maxBufferedResponseBytes;
        this.maxBufferedResponseChunks = maxBufferedResponseChunks;
        attempt = new Attempt(maxBufferedResponseBytes, maxBufferedResponseChunks);
    }

    @Override
    public synchronized void connect() throws IOException {
        if (connected) return;
        requestHeaders = redirectHeaders(getRequestProperties(), false);
        requestMethod = method;
        requestBody = reqBody.toByteArray();
        connected = true;
        reissue();
    }

    private void reissue() {
        Attempt next = new Attempt(maxBufferedResponseBytes, maxBufferedResponseChunks);
        attempt = next;
        CefHttpEngine.RequestSpec spec =
                new CefHttpEngine.RequestSpec(url.toString(), requestMethod, requestHeaders, requestBody);
        CefHttpEngine.Cancellation nextCancellation = engine.send(spec, new Sink(next));
        cancellation = nextCancellation;
        if (next.cancelRequested()) nextCancellation.cancel();
    }

    private synchronized void awaitResponse() throws IOException {
        connect();
        int timeout = getConnectTimeout();
        long deadline = timeout > 0 ? System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeout) : 0;
        for (int redirects = 0; ; redirects++) {
            Attempt current = attempt;
            try {
                if (timeout > 0) {
                    long remaining = deadline - System.nanoTime();
                    if (remaining <= 0 || !current.responseLatch.await(remaining, TimeUnit.NANOSECONDS)) {
                        throw new SocketTimeoutException("timed out awaiting response headers");
                    }
                }
                if (timeout == 0) current.responseLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("interrupted while awaiting response", e);
            }
            IOException err = current.error;
            if (err != null) throw err;
            String location = redirectLocation(current, redirects);
            if (location == null) return;
            if (current.statusCode == 301 || current.statusCode == 302 || current.statusCode == 303) {
                requestMethod = "GET";
                requestBody = new byte[0];
            }
            CefHttpEngine.Cancellation previous = cancellation;
            if (previous != null) previous.cancel();
            URL previousUrl = url;
            URL nextUrl = new URL(previousUrl, location);
            requestHeaders = redirectHeaders(requestHeaders, !sameOrigin(previousUrl, nextUrl));
            url = nextUrl;
            reissue();
        }
    }

    @Nullable
    private String redirectLocation(Attempt current, int redirects) {
        if (redirects >= MAX_REDIRECTS || !instanceFollowRedirects) return null;
        if (current.statusCode != 301
                && current.statusCode != 302
                && current.statusCode != 303
                && current.statusCode != 307
                && current.statusCode != 308) {
            return null;
        }
        for (Map.Entry<String, List<String>> e : current.responseHeaders.entrySet()) {
            if (e.getKey().equalsIgnoreCase("location")) {
                List<String> values = e.getValue();
                if (!values.isEmpty()) return values.get(0);
            }
        }
        return null;
    }

    private static boolean sameOrigin(URL left, URL right) {
        return left.getProtocol().equalsIgnoreCase(right.getProtocol())
                && left.getHost().equalsIgnoreCase(right.getHost())
                && effectivePort(left) == effectivePort(right);
    }

    private static int effectivePort(URL value) {
        return value.getPort() >= 0 ? value.getPort() : value.getDefaultPort();
    }

    private static Map<String, List<String>> redirectHeaders(Map<String, List<String>> source, boolean crossOrigin) {
        Map<String, List<String>> result = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : source.entrySet()) {
            String name = entry.getKey().toLowerCase(Locale.ROOT);
            if (name.equals("host")
                    || name.equals("connection")
                    || name.equals("keep-alive")
                    || name.equals("proxy-authenticate")
                    || name.equals("te")
                    || name.equals("trailer")
                    || name.equals("transfer-encoding")
                    || name.equals("upgrade")) continue;
            if (crossOrigin
                    && (name.equals("authorization")
                            || name.equals("proxy-authorization")
                            || name.equals("cookie")
                            || name.equals("cookie2"))) continue;
            result.put(entry.getKey(), List.copyOf(entry.getValue()));
        }
        return Map.copyOf(result);
    }

    @Override
    public int getResponseCode() throws IOException {
        awaitResponse();
        return attempt.statusCode;
    }

    @Override
    @SuppressWarnings("NullableForbidden")
    @Nullable
    public String getResponseMessage() throws IOException {
        awaitResponse();
        return attempt.statusText;
    }

    @Override
    public Map<String, List<String>> getHeaderFields() {
        try {
            awaitResponse();
        } catch (IOException e) {
            return Map.of();
        }
        return attempt.responseHeaders;
    }

    @Override
    @SuppressWarnings("NullableForbidden")
    @Nullable
    public String getHeaderField(@Nullable String name) {
        if (name == null) return null;
        try {
            awaitResponse();
        } catch (IOException e) {
            return null;
        }
        for (Map.Entry<String, List<String>> e : attempt.responseHeaders.entrySet()) {
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
        return new BoundedBodyOutputStream();
    }

    @Override
    @Nonnull
    public InputStream getInputStream() throws IOException {
        awaitResponse();
        Attempt current = attempt;
        if (current.statusCode >= 400) {
            throw new IOException("Server returned HTTP response code " + current.statusCode + " for URL: " + url);
        }
        return new ChunkInputStream(current);
    }

    @Override
    @SuppressWarnings("NullableForbidden")
    @Nullable
    public InputStream getErrorStream() {
        try {
            awaitResponse();
        } catch (IOException e) {
            return null;
        }
        Attempt current = attempt;
        if (current.statusCode < 400) return null;
        return new ChunkInputStream(current);
    }

    @Override
    public void disconnect() {
        attempt.fail(new IOException("connection disconnected"));
        CefHttpEngine.Cancellation c = cancellation;
        if (c != null) c.cancel();
    }

    @Override
    public void close() {
        disconnect();
    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    private static final class Attempt {
        private final CountDownLatch responseLatch = new CountDownLatch(1);
        private final BlockingQueue<byte[]> chunks;
        private final int maxBufferedBytes;
        private final int maxBufferedChunks;
        private int bufferedBytes;
        private int bufferedChunks;
        private boolean terminal;
        private boolean cancelRequested;
        private volatile int statusCode = -1;
        private volatile String statusText = "";
        private volatile Map<String, List<String>> responseHeaders = Map.of();

        @Nullable
        private volatile IOException error;

        private Attempt(int maxBufferedBytes, int maxBufferedChunks) {
            this.maxBufferedBytes = maxBufferedBytes;
            this.maxBufferedChunks = maxBufferedChunks;
            chunks = new ArrayBlockingQueue<>(maxBufferedChunks + 1);
        }

        private synchronized boolean offer(byte[] chunk) {
            if (terminal) return true;
            if (bufferedChunks >= maxBufferedChunks || chunk.length > maxBufferedBytes - bufferedBytes) {
                fail(new IOException("response body buffer limit exceeded"), true);
                return false;
            }
            if (!chunks.offer(chunk)) {
                fail(new IOException("response body chunk buffer limit exceeded"), true);
                return false;
            }
            bufferedBytes += chunk.length;
            bufferedChunks++;
            return true;
        }

        private byte[] take(int timeoutMillis) throws InterruptedException {
            byte[] chunk = timeoutMillis > 0 ? chunks.poll(timeoutMillis, TimeUnit.MILLISECONDS) : chunks.take();
            if (chunk != null && chunk != EOF) {
                synchronized (this) {
                    bufferedBytes -= chunk.length;
                    bufferedChunks--;
                }
            }
            return chunk;
        }

        private synchronized void complete() {
            if (terminal) return;
            terminal = true;
            chunks.add(EOF);
        }

        private synchronized void fail(IOException failure) {
            fail(failure, false);
        }

        private synchronized void fail(IOException failure, boolean cancel) {
            if (terminal) return;
            if (error == null) error = failure;
            terminal = true;
            cancelRequested = cancel;
            bufferedBytes = 0;
            bufferedChunks = 0;
            chunks.clear();
            chunks.add(EOF);
            responseLatch.countDown();
        }

        private synchronized boolean closeBody() {
            boolean cancel = !terminal;
            terminal = true;
            bufferedBytes = 0;
            bufferedChunks = 0;
            chunks.clear();
            chunks.add(EOF);
            return cancel;
        }

        private synchronized boolean cancelRequested() {
            return cancelRequested;
        }
    }

    private final class Sink implements CefHttpEngine.ResponseSink {
        private final Attempt target;

        private Sink(Attempt target) {
            this.target = target;
        }

        @Override
        public void onResponse(int status, @Nonnull String statusText0, @Nonnull Map<String, List<String>> headers) {
            target.statusCode = status;
            target.statusText = statusText0;
            target.responseHeaders = Map.copyOf(headers);
            target.responseLatch.countDown();
        }

        @Override
        public void onData(@Nonnull byte[] chunk) {
            if (chunk.length > 0 && !target.offer(chunk)) cancel(target);
        }

        @Override
        public void onComplete() {
            target.complete();
        }

        @Override
        public void onError(@Nonnull IOException err) {
            target.fail(err);
        }
    }

    private final class BoundedBodyOutputStream extends OutputStream {
        @Override
        public void write(int b) throws IOException {
            checkCapacity(1);
            reqBody.write(b);
        }

        @Override
        public void write(@Nonnull byte[] b, int off, int len) throws IOException {
            checkCapacity(len);
            reqBody.write(b, off, len);
        }

        private void checkCapacity(int additional) throws IOException {
            if (additional > maxRequestBodyBytes - reqBody.size()) {
                throw new IOException("request body exceeds maximum of " + maxRequestBodyBytes + " bytes");
            }
        }
    }

    private final class ChunkInputStream extends InputStream {
        private final Attempt source;
        private byte[] current = EOF;
        private int pos = 0;
        private boolean eof = false;
        private boolean closed;

        private ChunkInputStream(Attempt source) {
            this.source = source;
        }

        @Override
        public int read() throws IOException {
            byte[] one = new byte[1];
            int n = read(one, 0, 1);
            return (n < 0) ? -1 : (one[0] & 0xff);
        }

        @Override
        public int read(@Nonnull byte[] b, int off, int len) throws IOException {
            if (closed) throw new IOException("response body stream is closed");
            if (len == 0) return 0;
            if (eof) return -1;
            while (pos >= current.length) {
                try {
                    int timeout = getReadTimeout();
                    current = source.take(timeout);
                    if (current == null) {
                        SocketTimeoutException failure = new SocketTimeoutException("timed out reading response body");
                        source.fail(failure, true);
                        cancel(source);
                        throw failure;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IOException("interrupted while reading body", e);
                }
                if (current == EOF) {
                    eof = true;
                    IOException failure = source.error;
                    if (failure != null) throw failure;
                    return -1;
                }
                pos = 0;
            }
            int n = Math.min(len, current.length - pos);
            System.arraycopy(current, pos, b, off, n);
            pos += n;
            return n;
        }

        @Override
        public void close() {
            if (closed) return;
            closed = true;
            current = EOF;
            pos = 0;
            eof = true;
            if (source.closeBody()) cancel(source);
        }
    }

    private void cancel(Attempt target) {
        if (attempt != target) return;
        CefHttpEngine.Cancellation current = cancellation;
        if (current != null) current.cancel();
    }
}
