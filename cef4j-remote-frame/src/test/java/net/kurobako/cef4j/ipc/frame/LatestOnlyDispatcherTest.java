package net.kurobako.cef4j.ipc.frame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
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
}
