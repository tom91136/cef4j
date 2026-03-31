// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Specifies the horizontal text alignment mode. */
public enum CefHorizontalAlignment {

    /** Align the text's left edge with that of its display area. */
    CEF_HORIZONTAL_ALIGNMENT_LEFT(0L),
    /** Align the text's center with that of its display area. */
    CEF_HORIZONTAL_ALIGNMENT_CENTER(1L),
    /** Align the text's right edge with that of its display area. */
    CEF_HORIZONTAL_ALIGNMENT_RIGHT(2L),
    UNKNOWN(-1L);

    public final long value;

    CefHorizontalAlignment(long v) {
        this.value = v;
    }

    public static CefHorizontalAlignment fromLong(long v) {
        for (CefHorizontalAlignment e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
