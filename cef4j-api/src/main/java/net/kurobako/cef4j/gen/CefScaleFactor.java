// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported UI scale factors for the platform. SCALE_FACTOR_NONE is used for density independent resources such as
 * string, html/js files or an image that can be used for any scale factors (such as wallpapers).
 */
public enum CefScaleFactor {
    SCALE_FACTOR_NONE(0L),
    SCALE_FACTOR_100P(1L),
    SCALE_FACTOR_125P(2L),
    SCALE_FACTOR_133P(3L),
    SCALE_FACTOR_140P(4L),
    SCALE_FACTOR_150P(5L),
    SCALE_FACTOR_180P(6L),
    SCALE_FACTOR_200P(7L),
    SCALE_FACTOR_250P(8L),
    SCALE_FACTOR_300P(9L),
    SCALE_FACTOR_NUM_VALUES(10L),
    UNKNOWN(-1L);

    public final long value;

    CefScaleFactor(long v) {
        this.value = v;
    }

    public static CefScaleFactor fromLong(long v) {
        for (CefScaleFactor e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
