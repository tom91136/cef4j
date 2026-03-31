// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

public enum CefColorType {
    CEF_COLOR_TYPE_RGBA_8888(0L),
    CEF_COLOR_TYPE_BGRA_8888(1L),
    CEF_COLOR_TYPE_NUM_VALUES(2L),
    UNKNOWN(-1L);

    public final long value;

    CefColorType(long v) {
        this.value = v;
    }

    public static CefColorType fromLong(long v) {
        for (CefColorType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
