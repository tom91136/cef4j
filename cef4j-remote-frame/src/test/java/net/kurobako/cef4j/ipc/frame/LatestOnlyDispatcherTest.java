package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class LatestOnlyDispatcherTest {

    @Test
    void coalescesPendingValuesAndNeverQueuesMoreThanOneUiTask() {
        Queue<Runnable> tasks = new ArrayDeque<>();
        List<Integer> presented = new ArrayList<>();
        LatestOnlyDispatcher<Integer> dispatcher = new LatestOnlyDispatcher<>(tasks::add, presented::add);

        dispatcher.submit(1);
        dispatcher.submit(2);
        dispatcher.submit(3);

        assertThat(tasks).hasSize(1);
        tasks.remove().run();
        assertThat(presented).containsExactly(3);

        dispatcher.submit(4);
        assertThat(tasks).hasSize(1);
        tasks.remove().run();
        assertThat(presented).containsExactly(3, 4);
    }

    @Test
    void retriesAfterTransientExecutorRejection() {
        Queue<Runnable> tasks = new ArrayDeque<>();
        AtomicBoolean reject = new AtomicBoolean(true);
        List<Integer> presented = new ArrayList<>();
        LatestOnlyDispatcher<Integer> dispatcher = new LatestOnlyDispatcher<>(
                task -> {
                    if (reject.getAndSet(false)) throw new RejectedExecutionException("transient");
                    tasks.add(task);
                },
                presented::add);

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> dispatcher.submit(1))
                .isInstanceOf(RejectedExecutionException.class);
        dispatcher.submit(2);
        tasks.remove().run();

        assertThat(presented).containsExactly(2);
    }
}
