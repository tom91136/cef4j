package net.kurobako.cef4j.osr.jfx;

import java.time.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import net.kurobako.cef4j.test.TestDeadline;
import net.kurobako.cef4j.test.backend.BrowserSession;

final class NativePaintProbe {
    private static final int CAPACITY = 32;

    private final ArrayBlockingQueue<BrowserSession.PaintInfo> paints = new ArrayBlockingQueue<>(CAPACITY);

    void accept(int width, int height) {
        BrowserSession.PaintInfo paint = new BrowserSession.PaintInfo(width, height, (long) width * height * 4L);
        while (!paints.offer(paint)) paints.poll();
    }

    BrowserSession.PaintInfo await(int width, int height, Duration timeout)
            throws InterruptedException, TimeoutException {
        TestDeadline deadline = TestDeadline.after(timeout);
        BrowserSession.PaintInfo last = null;
        while (!deadline.isExpired()) {
            BrowserSession.PaintInfo next = paints.poll(deadline.remainingNanos(), TimeUnit.NANOSECONDS);
            if (next != null) last = next;
            if (next != null && next.width == width && next.height == height) return next;
        }
        throw new TimeoutException("no " + width + "x" + height + " native JavaFX paint within " + timeout
                + (last == null ? "" : "; last was " + last.width + "x" + last.height));
    }
}
