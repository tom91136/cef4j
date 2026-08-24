package net.kurobako.cef4j.test.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import org.junit.jupiter.api.Test;

class BrowserContractTest {

    @Test
    void eventuallyRetriesAnEvaluationThatDoesNotComplete() throws Exception {
        AtomicInteger evaluations = new AtomicInteger();
        CompletableFuture<String> dropped = new CompletableFuture<>();
        BrowserSession session = new BrowserSession() {
            @Override
            @Nonnull
            public CompletableFuture<Void> loadUrl(@Nonnull String url) {
                throw new UnsupportedOperationException();
            }

            @Override
            @Nonnull
            public CompletableFuture<String> evaluateJavascript(@Nonnull String script) {
                return evaluations.getAndIncrement() == 0 ? dropped : CompletableFuture.completedFuture("\"ready\"");
            }

            @Override
            @Nonnull
            public PaintInfo awaitFirstPaint(@Nonnull Duration timeout) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void close() {}
        };

        BrowserContract.assertEventuallyEquals(session, "marker", "ready", Duration.ofSeconds(1));

        assertThat(evaluations).hasValue(2);
        assertThat(dropped).isCancelled();
    }
}
