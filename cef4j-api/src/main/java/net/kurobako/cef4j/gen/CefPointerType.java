// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** The device type that caused the event. */
public enum CefPointerType {
    CEF_POINTER_TYPE_TOUCH(0L),
    CEF_POINTER_TYPE_MOUSE(1L),
    CEF_POINTER_TYPE_PEN(2L),
    CEF_POINTER_TYPE_ERASER(3L),
    CEF_POINTER_TYPE_UNKNOWN(4L),
    UNKNOWN(-1L);

    public final long value;

    CefPointerType(long v) {
        this.value = v;
    }

    public static CefPointerType fromLong(long v) {
        for (CefPointerType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
