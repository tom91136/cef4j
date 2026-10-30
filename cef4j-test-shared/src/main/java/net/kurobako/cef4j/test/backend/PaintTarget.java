package net.kurobako.cef4j.test.backend;

import java.time.Duration;
import java.util.Objects;

/** The size of the viewport a paint is expected for, and the time the paint is waited for. */
public final class PaintTarget {
    public final int width;
    public final int height;
    public final Duration timeout;

    public PaintTarget(int width, int height, Duration timeout) {
        this.width = width;
        this.height = height;
        this.timeout = Objects.requireNonNull(timeout, "timeout");
    }
}
