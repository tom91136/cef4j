package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

class TestDeadlineTest {
    @Test
    void pollsValuesWithinOneSharedBudget() throws Exception {
        java.util.concurrent.atomic.AtomicInteger attempts = new java.util.concurrent.atomic.AtomicInteger();

        int result = TestDeadline.after(Duration.ofSeconds(1))
                .poll(attempts::incrementAndGet, value -> value == 3, Duration.ofMillis(1), "value");

        assertThat(result).isEqualTo(3);
    }

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
        try (TestExecutor executor = TestExecutor.single("deadline-queue-barrier")) {
            StringBuilder order = new StringBuilder();
            executor.execute(() -> order.append("first"));
            TestDeadline.after(Duration.ofSeconds(1))
                    .runOn(false, executor::execute, () -> order.append("-barrier"), "serial queue barrier");
            assertThat(order).hasToString("first-barrier");
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

    @Test
    void oneDeadlineBoundsLatchesAndThreads() throws Exception {
        TestDeadline deadline = TestDeadline.after(Duration.ofSeconds(1));
        CountDownLatch latch = new CountDownLatch(1);
        Thread worker = new Thread(latch::countDown);

        worker.start();
        deadline.await(latch, "worker entry");
        deadline.join(worker, "worker exit");

        assertThat(worker.isAlive()).isFalse();
    }

    @Test
    void testGateReleasesBlockedWorkOnClose() throws Exception {
        TestGate gate = new TestGate();
        Thread worker = new Thread(gate::enter);
        TestDeadline deadline = TestDeadline.after(Duration.ofSeconds(1));

        worker.start();
        gate.awaitEntered(deadline, "gate entry");
        gate.close();
        deadline.join(worker, "gate exit");

        assertThat(worker.isAlive()).isFalse();
    }

    @Test
    void pollingCanDriveTheSystemUnderTest() throws Exception {
        AtomicInteger progress = new AtomicInteger();

        TestDeadline.after(Duration.ofSeconds(1))
                .until(() -> progress.get() == 3, progress::incrementAndGet, Duration.ofMillis(1), "progress");

        assertThat(progress).hasValue(3);
    }
}
