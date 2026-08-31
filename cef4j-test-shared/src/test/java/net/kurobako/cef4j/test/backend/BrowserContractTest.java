package net.kurobako.cef4j.test.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import org.junit.jupiter.api.Test;

class BrowserContractTest {

    @Test
    void convergesInitialPaintThroughViewportResize() throws Exception {
        AtomicInteger resizes = new AtomicInteger();
        Queue<BrowserSession.PaintInfo> paints = new ArrayDeque<>();
        paints.add(new BrowserSession.PaintInfo(800, 600, 800L * 600L * 4L));
        BrowserSession session = new BrowserSession() {
            @Override
            @Nonnull
            public CompletableFuture<Void> loadUrl(@Nonnull String url) {
                throw new UnsupportedOperationException();
            }

            @Override
            @Nonnull
            public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
                return CompletableFuture.completedFuture("640x480");
            }

            @Override
            @Nonnull
            public PaintInfo awaitNextPaint(@Nonnull Duration timeout) throws TimeoutException {
                PaintInfo paint = paints.poll();
                if (paint == null) throw new TimeoutException("no paint");
                return paint;
            }

            @Override
            @Nonnull
            public CompletableFuture<Void> resizeViewport(int width, int height) {
                resizes.incrementAndGet();
                paints.add(new PaintInfo(width, height, (long) width * height * 4L));
                return CompletableFuture.completedFuture(null);
            }

            @Override
            public void close() {}
        };

        BrowserSession.PaintInfo paint =
                BrowserContract.awaitInitialPaint(session, true, 640, 480, Duration.ofSeconds(1));

        assertThat(paint.width).isEqualTo(640);
        assertThat(paint.height).isEqualTo(480);
        assertThat(resizes).hasValue(1);
    }

    @Test
    void preservesBackendDiagnosticsWhenPaintTimesOut() {
        BrowserSession session = new BrowserSession() {
            @Override
            @Nonnull
            public CompletableFuture<Void> loadUrl(@Nonnull String url) {
                throw new UnsupportedOperationException();
            }

            @Override
            @Nonnull
            public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
                throw new UnsupportedOperationException();
            }

            @Override
            @Nonnull
            public PaintInfo awaitNextPaint(@Nonnull Duration timeout) throws TimeoutException {
                throw new TimeoutException("pipeline=shared-file{events=0, callbacks=0}");
            }

            @Override
            public void close() {}
        };

        Throwable failure = org.assertj.core.api.Assertions.catchThrowable(
                () -> session.awaitPaint(640, 480, Duration.ofMillis(1)));

        assertThat(failure)
                .isInstanceOf(TimeoutException.class)
                .hasMessageContaining("pipeline=shared-file{events=0, callbacks=0}")
                .hasCauseInstanceOf(TimeoutException.class);
    }

    @Test
    void retriesAcknowledgedResizeUntilTheRequestedPaintArrives() throws Exception {
        AtomicInteger resizes = new AtomicInteger();
        BrowserSession session = new BrowserSession() {
            @Override
            @Nonnull
            public CompletableFuture<Void> loadUrl(@Nonnull String url) {
                throw new UnsupportedOperationException();
            }

            @Override
            @Nonnull
            public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
                return CompletableFuture.completedFuture("512x384");
            }

            @Override
            @Nonnull
            public PaintInfo awaitNextPaint(@Nonnull Duration timeout) throws TimeoutException {
                if (resizes.get() < 2) throw new TimeoutException("resize acknowledged without a paint");
                return new PaintInfo(512, 384, 512L * 384L * 4L);
            }

            @Override
            @Nonnull
            public CompletableFuture<Void> resizeViewport(int width, int height) {
                resizes.incrementAndGet();
                return CompletableFuture.completedFuture(null);
            }

            @Override
            public void close() {}
        };

        BrowserSession.PaintInfo paint = BrowserContract.resizeUntilPaint(session, 512, 384, Duration.ofSeconds(1));

        assertThat(paint.width).isEqualTo(512);
        assertThat(paint.height).isEqualTo(384);
        assertThat(resizes).hasValue(3);
    }

    @Test
    void retriggersARealResizeWhenTheFirstTargetPaintIsLost() throws Exception {
        AtomicInteger width = new AtomicInteger(640);
        AtomicInteger height = new AtomicInteger(480);
        AtomicInteger targetTransitions = new AtomicInteger();
        Queue<BrowserSession.PaintInfo> paints = new ArrayDeque<>();
        BrowserSession session = new BrowserSession() {
            @Override
            @Nonnull
            public CompletableFuture<Void> loadUrl(@Nonnull String url) {
                throw new UnsupportedOperationException();
            }

            @Override
            @Nonnull
            public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
                return CompletableFuture.completedFuture(width.get() + "x" + height.get());
            }

            @Override
            @Nonnull
            public PaintInfo awaitNextPaint(@Nonnull Duration timeout) throws TimeoutException {
                PaintInfo paint = paints.poll();
                if (paint == null) throw new TimeoutException("no paint after an unchanged resize");
                return paint;
            }

            @Override
            @Nonnull
            public CompletableFuture<Void> resizeViewport(int nextWidth, int nextHeight) {
                int previousWidth = width.getAndSet(nextWidth);
                int previousHeight = height.getAndSet(nextHeight);
                if (previousWidth == nextWidth && previousHeight == nextHeight) {
                    return CompletableFuture.completedFuture(null);
                }
                if (nextWidth != 512 || nextHeight != 384 || targetTransitions.incrementAndGet() > 1) {
                    paints.add(new PaintInfo(nextWidth, nextHeight, (long) nextWidth * nextHeight * 4L));
                }
                return CompletableFuture.completedFuture(null);
            }

            @Override
            public void close() {}
        };

        BrowserSession.PaintInfo paint = BrowserContract.resizeUntilPaint(session, 512, 384, Duration.ofMillis(100));

        assertThat(paint.width).isEqualTo(512);
        assertThat(paint.height).isEqualTo(384);
        assertThat(targetTransitions).hasValue(2);
    }

    @Test
    void retriesWhenLocalPaintShapePrecedesCefViewportConvergence() throws Exception {
        AtomicInteger resizes = new AtomicInteger();
        BrowserSession session = new BrowserSession() {
            @Override
            @Nonnull
            public CompletableFuture<Void> loadUrl(@Nonnull String url) {
                throw new UnsupportedOperationException();
            }

            @Override
            @Nonnull
            public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
                return CompletableFuture.completedFuture(resizes.get() < 2 ? "512x480" : "512x384");
            }

            @Override
            @Nonnull
            public PaintInfo awaitNextPaint(@Nonnull Duration timeout) {
                return new PaintInfo(512, 384, 512L * 384L * 4L);
            }

            @Override
            @Nonnull
            public CompletableFuture<Void> resizeViewport(int width, int height) {
                resizes.incrementAndGet();
                return CompletableFuture.completedFuture(null);
            }

            @Override
            public void close() {}
        };

        BrowserSession.PaintInfo paint = BrowserContract.resizeUntilPaint(session, 512, 384, Duration.ofSeconds(1));

        assertThat(paint.width).isEqualTo(512);
        assertThat(paint.height).isEqualTo(384);
        assertThat(resizes).hasValue(2);
    }
}
