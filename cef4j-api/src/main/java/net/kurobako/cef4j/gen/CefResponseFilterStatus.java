// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Return values for CefResponseFilter::Filter(). */
public enum CefResponseFilterStatus {

    /**
     * Some or all of the pre-filter data was read successfully but more data is needed in order to continue filtering
     * (filtered output is pending).
     */
    RESPONSE_FILTER_NEED_MORE_DATA(0L),
    /** Some or all of the pre-filter data was read successfully and all available filtered output has been written. */
    RESPONSE_FILTER_DONE(1L),
    /** An error occurred during filtering. */
    RESPONSE_FILTER_ERROR(2L),
    UNKNOWN(-1L);

    public final long value;

    CefResponseFilterStatus(long v) {
        this.value = v;
    }

    public static CefResponseFilterStatus fromLong(long v) {
        for (CefResponseFilterStatus e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
