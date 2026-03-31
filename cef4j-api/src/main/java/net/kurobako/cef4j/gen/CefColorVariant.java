// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Specifies the color variants supported by CefRequestContext::SetChromeThemeColor. */
public enum CefColorVariant {
    CEF_COLOR_VARIANT_SYSTEM(0L),
    CEF_COLOR_VARIANT_LIGHT(1L),
    CEF_COLOR_VARIANT_DARK(2L),
    CEF_COLOR_VARIANT_TONAL_SPOT(3L),
    CEF_COLOR_VARIANT_NEUTRAL(4L),
    CEF_COLOR_VARIANT_VIBRANT(5L),
    CEF_COLOR_VARIANT_EXPRESSIVE(6L),
    CEF_COLOR_VARIANT_NUM_VALUES(7L),
    UNKNOWN(-1L);

    public final long value;

    CefColorVariant(long v) {
        this.value = v;
    }

    public static CefColorVariant fromLong(long v) {
        for (CefColorVariant e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
