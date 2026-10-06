package net.kurobako.cef4j.ipc.session;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntPredicate;

final class IntIdAllocator {
    private final AtomicInteger next;

    IntIdAllocator() {
        this(1);
    }

    IntIdAllocator(int first) {
        if (first <= 0) throw new IllegalArgumentException("first must be positive");
        next = new AtomicInteger(first);
    }

    int allocate(IntPredicate claim) {
        for (long attempts = 0; attempts < Integer.MAX_VALUE; attempts++) {
            int candidate = next.getAndUpdate(current -> current == Integer.MAX_VALUE ? 1 : current + 1);
            if (claim.test(candidate)) return candidate;
        }
        throw new IllegalStateException("positive int32 identifier space exhausted");
    }
}
