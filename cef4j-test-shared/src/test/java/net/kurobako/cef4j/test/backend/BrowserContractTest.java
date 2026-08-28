package net.kurobako.cef4j.test.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import org.junit.jupiter.api.Test;

class BrowserContractTest {

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
        assertThat(resizes).hasValue(2);
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
