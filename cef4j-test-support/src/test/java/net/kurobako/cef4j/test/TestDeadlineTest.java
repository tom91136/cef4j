package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

class TestDeadlineTest {

    @Test
    void oneDeadlineBoundsAndCancelsAnUnfinishedAttempt() {
        TestDeadline deadline = TestDeadline.after(Duration.ofMillis(20));
        CompletableFuture<String> attempt = new CompletableFuture<>();

        assertThatThrownBy(() -> deadline.await(attempt, "browser evaluation"))
                .isInstanceOf(TimeoutException.class)
                .hasMessageContaining("browser evaluation");
        assertThat(attempt).isCancelled();
    }

    @Test
    void preservesCompletedValuesAndFailures() throws Exception {
        TestDeadline deadline = TestDeadline.after(Duration.ofSeconds(1));

        assertThat(deadline.await(CompletableFuture.completedFuture("done"), "completed operation"))
                .isEqualTo("done");
        CompletableFuture<String> failed = new CompletableFuture<>();
        failed.completeExceptionally(new IllegalStateException("boom"));
        assertThatThrownBy(() -> deadline.await(failed, "failed operation"))
                .isInstanceOf(ExecutionException.class)
                .hasCauseInstanceOf(IllegalStateException.class);
    }

    @Test
    void queueBarrierRunsAfterAlreadyQueuedWork() throws Exception {
        java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        try {
            StringBuilder order = new StringBuilder();
            executor.execute(() -> order.append("first"));
            TestDeadline.after(Duration.ofSeconds(1))
                    .runOn(false, executor::execute, () -> order.append("-barrier"), "serial queue barrier");
            assertThat(order).hasToString("first-barrier");
        } finally {
            executor.shutdownNow();
        }
    }

    @Test
    void queueBarrierRunsInlineOnOwningThread() throws Exception {
        AtomicBoolean scheduled = new AtomicBoolean();
        AtomicBoolean ran = new AtomicBoolean();

        TestDeadline.after(Duration.ofSeconds(1))
                .runOn(true, ignored -> scheduled.set(true), () -> ran.set(true), "inline queue action");

        assertThat(ran).isTrue();
        assertThat(scheduled).isFalse();
    }

    @Test
    void timedOutQueueActionDoesNotRunLater() {
        AtomicReference<Runnable> queued = new AtomicReference<>();
        AtomicBoolean ran = new AtomicBoolean();

        assertThatThrownBy(() -> TestDeadline.after(Duration.ZERO)
                        .runOn(false, queued::set, () -> ran.set(true), "expired queue action"))
                .isInstanceOf(TimeoutException.class);
        assertThat(queued.get()).isNotNull();
        Objects.requireNonNull(queued.get()).run();

        assertThat(ran).isFalse();
    }
}
