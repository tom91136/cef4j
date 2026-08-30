package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

final class TestGateTest {
    @Test
    void closeReleasesAnEnteredWorker() throws Exception {
        try (TestGate gate = new TestGate()) {
            CompletableFuture<Void> worker = CompletableFuture.runAsync(gate::enter);
            gate.awaitEntered(TestDeadline.after(Duration.ofSeconds(1)), "worker entry");

            assertThat(worker).isNotDone();
            gate.release();
            worker.get(1, TimeUnit.SECONDS);
        }
    }
}
