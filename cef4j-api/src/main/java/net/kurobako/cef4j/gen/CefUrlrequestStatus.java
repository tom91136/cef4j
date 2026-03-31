// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Flags that represent CefURLRequest status. */
public enum CefUrlrequestStatus {

    /** Unknown status. */
    UR_UNKNOWN(0L),
    /** Request succeeded. */
    UR_SUCCESS(1L),
    /** An IO request is pending, and the caller will be informed when it is completed. */
    UR_IO_PENDING(2L),
    /** Request was canceled programatically. */
    UR_CANCELED(3L),
    /** Request failed for some reason. */
    UR_FAILED(4L),
    UR_NUM_VALUES(5L),
    UNKNOWN(-1L);

    public final long value;

    CefUrlrequestStatus(long v) {
        this.value = v;
    }

    public static CefUrlrequestStatus fromLong(long v) {
        for (CefUrlrequestStatus e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
