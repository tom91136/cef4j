// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Thread priority values listed in increasing order of importance. */
public enum CefThreadPriority {

    /** Suitable for threads that shouldn't disrupt high priority work. */
    TP_BACKGROUND(0L),
    /** Default priority level. */
    TP_NORMAL(1L),
    /** Suitable for threads which generate data for the display (at ~60Hz). */
    TP_DISPLAY(2L),
    /** Suitable for low-latency, glitch-resistant audio. */
    TP_REALTIME_AUDIO(3L),
    TP_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefThreadPriority(long v) {
        this.value = v;
    }

    public static CefThreadPriority fromLong(long v) {
        for (CefThreadPriority e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
