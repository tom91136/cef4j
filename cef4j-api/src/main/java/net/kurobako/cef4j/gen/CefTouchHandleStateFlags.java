// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Values indicating what state of the touch handle is set. */
public enum CefTouchHandleStateFlags {
    CEF_THS_FLAG_NONE(0L),
    CEF_THS_FLAG_ENABLED(1L),
    CEF_THS_FLAG_ORIENTATION(2L),
    CEF_THS_FLAG_ORIGIN(4L),
    CEF_THS_FLAG_ALPHA(8L),
    UNKNOWN(-1L);

    public final long value;

    CefTouchHandleStateFlags(long v) {
        this.value = v;
    }

    public static CefTouchHandleStateFlags fromLong(long v) {
        for (CefTouchHandleStateFlags e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
