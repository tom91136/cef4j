// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Print job color mode values. */
public enum CefColorModel {
    COLOR_MODEL_UNKNOWN(0L),
    COLOR_MODEL_GRAY(1L),
    COLOR_MODEL_COLOR(2L),
    COLOR_MODEL_CMYK(3L),
    COLOR_MODEL_CMY(4L),
    COLOR_MODEL_KCMY(5L),
    COLOR_MODEL_CMY_K(6L),
    COLOR_MODEL_BLACK(7L),
    COLOR_MODEL_GRAYSCALE(8L),
    COLOR_MODEL_RGB(9L),
    COLOR_MODEL_RGB16(10L),
    COLOR_MODEL_RGBA(11L),
    COLOR_MODEL_COLORMODE_COLOR(12L),
    COLOR_MODEL_COLORMODE_MONOCHROME(13L),
    COLOR_MODEL_HP_COLOR_COLOR(14L),
    COLOR_MODEL_HP_COLOR_BLACK(15L),
    COLOR_MODEL_PRINTOUTMODE_NORMAL(16L),
    COLOR_MODEL_PRINTOUTMODE_NORMAL_GRAY(17L),
    COLOR_MODEL_PROCESSCOLORMODEL_CMYK(18L),
    COLOR_MODEL_PROCESSCOLORMODEL_GREYSCALE(19L),
    COLOR_MODEL_PROCESSCOLORMODEL_RGB(20L),
    COLOR_MODEL_NUM_VALUES(21L),
    UNKNOWN(-1L);

    public final long value;

    CefColorModel(long v) {
        this.value = v;
    }

    public static CefColorModel fromLong(long v) {
        for (CefColorModel e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
