package net.kurobako.cef4j.test;

import javax.annotation.Nullable;
import net.kurobako.cef4j.policy.NullableBoundary;

/** Cleanup helpers that keep the original failure primary. */
@NullableBoundary("cleanup accepts resources that were never opened and an absent first failure")
public final class TestResources {
    private TestResources() {}

    /** Closes {@code resource}, recording any cleanup failure as suppressed on {@code original}. */
    public static void closeAfterFailure(@Nullable AutoCloseable resource, Exception original) {
        if (resource == null) return;
        try {
            resource.close();
        } catch (Exception cleanupFailure) {
            original.addSuppressed(cleanupFailure);
        }
    }

    /** Returns the first failure, with {@code next} suppressed on it. */
    public static RuntimeException merge(@Nullable RuntimeException failure, RuntimeException next) {
        if (failure == null) return next;
        failure.addSuppressed(next);
        return failure;
    }
}
