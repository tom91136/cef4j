// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Process termination status values. */
public enum CefTerminationStatus {

    /** Non-zero exit status. */
    TS_ABNORMAL_TERMINATION(0L),
    /** SIGKILL or task manager kill. */
    TS_PROCESS_WAS_KILLED(1L),
    /** Segmentation fault. */
    TS_PROCESS_CRASHED(2L),
    /** Out of memory. Some platforms may use TS_PROCESS_CRASHED instead. */
    TS_PROCESS_OOM(3L),
    /** Child process never launched. */
    TS_LAUNCH_FAILED(4L),
    /** On Windows, the OS terminated the process due to code integrity failure. */
    TS_INTEGRITY_FAILURE(5L),
    TS_NUM_VALUES(6L),
    UNKNOWN(-1L);

    public final long value;

    CefTerminationStatus(long v) {
        this.value = v;
    }

    public static CefTerminationStatus fromLong(long v) {
        for (CefTerminationStatus e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
