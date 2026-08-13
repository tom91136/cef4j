package net.kurobako.cef4j.test.backend;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nonnull;

/**
 * Per-browser test handle. The shape is intentionally narrow — only the operations the existing CEF test suite actually
 * exercises (load, await, evaluate, close). Backends are free to implement these via in-process JCEF or via the IPC
 * helper; tests don't care which.
 *
 * <p>Threading: all methods can be called from any thread. {@link CompletableFuture} returns let the test thread decide
 * whether to {@code .get()} or chain. Backends marshal onto their own UI/IO thread internally.
 */
public interface BrowserSession extends AutoCloseable {

    /** Navigate the main frame to {@code url}; future completes when the load reports SUCCESS or FAIL. */
    @Nonnull
    CompletableFuture<Void> loadUrl(@Nonnull String url);

    /**
     * Run JavaScript in the main frame and return the result as a string. {@code null} for a void result.
     * Implementations may serialise complex values (objects, arrays) as JSON.
     */
    @Nonnull
    CompletableFuture<String> evaluateJavascript(@Nonnull String script);

    /**
     * Wait for at least one paint to land. Returns the (width, height, byteCount) of the most recent paint or times
     * out. Backends without a paint pipeline (headless variants) should fail this synchronously rather than hanging.
     */
    @Nonnull
    PaintInfo awaitFirstPaint(@Nonnull Duration timeout) throws InterruptedException;

    /** Resize the browser's CSS viewport. Only valid when the backend advertises VIEWPORT_RESIZE. */
    @Nonnull
    default CompletableFuture<Void> resizeViewport(int width, int height) {
        CompletableFuture<Void> failure = new CompletableFuture<>();
        failure.completeExceptionally(new UnsupportedOperationException("viewport resize is not supported"));
        return failure;
    }

    /** Wait until a paint with the requested dimensions arrives. */
    @Nonnull
    default PaintInfo awaitPaint(int width, int height, @Nonnull Duration timeout) throws InterruptedException {
        long deadline = System.nanoTime() + timeout.toNanos();
        PaintInfo last = null;
        while (System.nanoTime() < deadline) {
            Duration remaining = Duration.ofNanos(Math.max(1L, deadline - System.nanoTime()));
            last = awaitFirstPaint(remaining);
            if (last.width == width && last.height == height) return last;
        }
        throw new InterruptedException("no " + width + "x" + height + " paint within " + timeout
                + (last == null ? "" : "; last was " + last.width + "x" + last.height));
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
