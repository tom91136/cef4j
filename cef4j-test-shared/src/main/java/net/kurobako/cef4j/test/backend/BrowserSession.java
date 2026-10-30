package net.kurobako.cef4j.test.backend;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import net.kurobako.cef4j.test.TestDeadline;

/**
 * Per-browser test handle. The shape is intentionally narrow — only the operations the existing CEF test suite actually
 * exercises (load, await, evaluate, close). Backends are free to implement these via in-process JCEF or via the IPC
 * helper; tests don't care which.
 *
 * <p>Threading: all methods can be called from any thread. {@link CompletableFuture} returns let the test thread decide
 * whether to {@code .get()} or chain. Backends marshal onto their own UI/IO thread internally.
 */
public interface BrowserSession extends AutoCloseable {

    /** Navigate the main frame to {@code url}; completion means the backend observed navigation and rendering. */
    CompletableFuture<Void> loadUrl(String url);

    /**
     * Run JavaScript in the main frame and return the result as a string. {@code null} for a void result.
     * Implementations may serialise complex values (objects, arrays) as JSON.
     */
    CompletableFuture<String> evaluateJavascript(String script);

    /**
     * Wait for the next observed paint. Returns dimensions and byte count reported by the paint pipeline, never
     * dimensions inferred from toolkit state. Backends without a paint pipeline should fail synchronously.
     */
    PaintInfo awaitNextPaint(Duration timeout) throws InterruptedException, TimeoutException;

    /** Resize the browser's CSS viewport. Only valid when the backend advertises VIEWPORT_RESIZE. */
    default CompletableFuture<Void> resizeViewport(int width, int height) {
        final CompletableFuture<Void> failure = new CompletableFuture<>();
        failure.completeExceptionally(new UnsupportedOperationException("viewport resize is not supported"));
        return failure;
    }

    /** Wait until a paint with the requested dimensions arrives. */
    default PaintInfo awaitPaint(PaintTarget target) throws InterruptedException, TimeoutException {
        final TestDeadline deadline = TestDeadline.after(target.timeout);
        PaintInfo last = null;
        TimeoutException lastTimeout = null;
        while (deadline.hasTimeRemaining()) {
            try {
                last = awaitNextPaint(deadline.remaining());
            } catch (TimeoutException exhausted) {
                lastTimeout = exhausted;
                break;
            }
            if (last.width == target.width && last.height == target.height) return last;
        }
        final TimeoutException failure = new TimeoutException("no " + target.width + "x" + target.height
                + " paint within " + target.timeout
                + (last == null ? "" : "; last was " + last.width + "x" + last.height)
                + (lastTimeout == null ? "" : "; backend=" + lastTimeout.getMessage()));
        if (lastTimeout != null) failure.initCause(lastTimeout);
        throw failure;
    }

    @Override
    void close();

    /** Lightweight paint summary — enough for tests to assert "we got pixels of the expected shape". */
    final class PaintInfo {
        public final int width;
        public final int height;
        public final long byteCount;

        public PaintInfo(int width, int height, long byteCount) {
            this.width = width;
            this.height = height;
            this.byteCount = byteCount;
        }
    }
}
