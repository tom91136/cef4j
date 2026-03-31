// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Log items prepended to each log line. */
public enum CefLogItems {

    /** Prepend the default list of items. */
    LOG_ITEMS_DEFAULT(0L),
    /** Prepend no items. */
    LOG_ITEMS_NONE(1L),
    /** Prepend the process ID. */
    LOG_ITEMS_FLAG_PROCESS_ID(2L),
    /** Prepend the thread ID. */
    LOG_ITEMS_FLAG_THREAD_ID(4L),
    /** Prepend the timestamp. */
    LOG_ITEMS_FLAG_TIME_STAMP(8L),
    /** Prepend the tickcount. */
    LOG_ITEMS_FLAG_TICK_COUNT(16L),
    UNKNOWN(-1L);

    public final long value;

    CefLogItems(long v) {
        this.value = v;
    }

    public static CefLogItems fromLong(long v) {
        for (CefLogItems e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
