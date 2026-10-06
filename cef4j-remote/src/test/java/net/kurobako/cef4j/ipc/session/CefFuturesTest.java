package net.kurobako.cef4j.ipc.session;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

class CefFuturesTest {
    @Test
    void mapCancellationCancelsSource() {
        CompletableFuture<String> source = new CompletableFuture<>();
        CompletableFuture<Integer> mapped = CefFutures.map(source, String::length);

        mapped.cancel(true);

        assertThat(source).isCancelled();
    }

    @Test
    void flatMapCancellationCancelsCurrentStage() {
        CompletableFuture<String> source = new CompletableFuture<>();
        CompletableFuture<String> next = new CompletableFuture<>();
        CompletableFuture<String> mapped = CefFutures.flatMap(source, ignored -> next);
        source.complete("ready");

        mapped.cancel(true);

        assertThat(next).isCancelled();
    }

    @Test
    void mappingPreservesValuesAndFailures() {
        assertThat(CefFutures.map(CompletableFuture.completedFuture("value"), String::length))
                .isCompletedWithValue(5);
        CompletableFuture<String> failed = new CompletableFuture<>();
        failed.completeExceptionally(new IllegalStateException("failed"));
        assertThat(CefFutures.map(failed, String::length)).isCompletedExceptionally();
    }

    @Test
    void failureObserversIgnoreSuccessfulCompletion() {
        AtomicReference<Throwable> observed = new AtomicReference<>();
        CompletableFuture<String> success = new CompletableFuture<>();
        CefFutures.observeFailure(success, observed::set);
        success.complete("done");
        assertThat(observed).hasValue(null);

        CompletableFuture<String> failed = new CompletableFuture<>();
        CefFutures.observeFailure(failed, observed::set);
        IllegalStateException failure = new IllegalStateException("failed");
        failed.completeExceptionally(failure);
        assertThat(observed).hasValue(failure);
    }
}
