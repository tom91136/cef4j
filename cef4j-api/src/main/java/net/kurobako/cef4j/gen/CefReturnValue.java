// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Return value types. */
public enum CefReturnValue {

    /** Cancel immediately. */
    RV_CANCEL(0L),
    /** Continue immediately. */
    RV_CONTINUE(1L),
    /** Continue asynchronously (usually via a callback). */
    RV_CONTINUE_ASYNC(2L),
    UNKNOWN(-1L);

    public final long value;

    CefReturnValue(long v) {
        this.value = v;
    }

    public static CefReturnValue fromLong(long v) {
        for (CefReturnValue e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
