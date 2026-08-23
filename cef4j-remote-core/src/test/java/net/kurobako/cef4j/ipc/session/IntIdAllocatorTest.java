package net.kurobako.cef4j.ipc.session;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Test;

class IntIdAllocatorTest {
    @Test
    void wrapsToPositiveIdsAndSkipsCollisions() {
        Set<Integer> claimed = ConcurrentHashMap.newKeySet();
        claimed.add(1);
        IntIdAllocator allocator = new IntIdAllocator(Integer.MAX_VALUE);

        assertThat(allocator.allocate(claimed::add)).isEqualTo(Integer.MAX_VALUE);
        assertThat(allocator.allocate(claimed::add)).isEqualTo(2);
    }
}
