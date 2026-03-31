// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Paint element types. */
public enum CefPaintElementType {
    PET_VIEW(0L),
    PET_POPUP(1L),
    UNKNOWN(-1L);

    public final long value;

    CefPaintElementType(long v) {
        this.value = v;
    }

    public static CefPaintElementType fromLong(long v) {
        for (CefPaintElementType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
