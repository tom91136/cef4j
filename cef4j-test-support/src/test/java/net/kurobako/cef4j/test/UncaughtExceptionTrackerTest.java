package net.kurobako.cef4j.test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

final class UncaughtExceptionTrackerTest {
    @Test
    void reportsFirstFailureOnceOnOwningThread() {
        UncaughtExceptionTracker tracker = new UncaughtExceptionTracker();
        IllegalStateException first = new IllegalStateException("first");
        tracker.uncaughtException(new Thread("JavaFX Application Thread"), first);
        tracker.uncaughtException(new Thread("later thread"), new IllegalArgumentException("later"));

        assertThatThrownBy(tracker::throwIfPresent)
                .isInstanceOf(AssertionError.class)
                .hasMessage("Uncaught exception on JavaFX Application Thread")
                .hasCause(first);
        assertThatCode(tracker::throwIfPresent).doesNotThrowAnyException();
    }
}
