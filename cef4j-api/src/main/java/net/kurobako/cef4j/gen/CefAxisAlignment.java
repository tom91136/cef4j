// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Specifies where along the axis the CefBoxLayout child views should be laid out. Should be kept in sync with
 * Chromium's views::LayoutAlignment type.
 */
public enum CefAxisAlignment {

    /** Child views will be left/top-aligned. */
    CEF_AXIS_ALIGNMENT_START(0L),
    /** Child views will be center-aligned. */
    CEF_AXIS_ALIGNMENT_CENTER(1L),
    /** Child views will be right/bottom-aligned. */
    CEF_AXIS_ALIGNMENT_END(2L),
    /** Child views will be stretched to fit. */
    CEF_AXIS_ALIGNMENT_STRETCH(3L),
    CEF_AXIS_ALIGNMENT_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefAxisAlignment(long v) {
        this.value = v;
    }

    public static CefAxisAlignment fromLong(long v) {
        for (CefAxisAlignment e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
