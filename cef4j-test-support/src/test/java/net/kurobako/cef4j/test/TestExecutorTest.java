package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;

class TestExecutorTest {
    @Test
    void ownsNamedDaemonThreads() throws Exception {
        CompletableFuture<Thread> observed = new CompletableFuture<>();

        try (TestExecutor executor = TestExecutor.single("owned-test-worker")) {
            executor.execute(() -> observed.complete(Thread.currentThread()));
            Thread thread = TestDeadline.after(Duration.ofSeconds(1)).await(observed, "test task");

            assertThat(thread.getName()).startsWith("owned-test-worker-");
            assertThat(thread.isDaemon()).isTrue();
        }
    }

    @Test
    void rejectsWorkAfterClose() {
        TestExecutor executor = TestExecutor.single("closed-test-worker");
        executor.close();

        assertThatThrownBy(() -> executor.execute(() -> {})).isInstanceOf(RuntimeException.class);
    }
}
