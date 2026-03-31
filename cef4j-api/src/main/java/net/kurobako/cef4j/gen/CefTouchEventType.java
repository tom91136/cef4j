// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Touch points states types. */
public enum CefTouchEventType {
    CEF_TET_RELEASED(0L),
    CEF_TET_PRESSED(1L),
    CEF_TET_MOVED(2L),
    CEF_TET_CANCELLED(3L),
    UNKNOWN(-1L);

    public final long value;

    CefTouchEventType(long v) {
        this.value = v;
    }

    public static CefTouchEventType fromLong(long v) {
        for (CefTouchEventType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
