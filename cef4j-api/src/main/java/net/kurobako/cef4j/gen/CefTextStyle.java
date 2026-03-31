// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Text style types. Should be kepy in sync with gfx::TextStyle. */
public enum CefTextStyle {
    CEF_TEXT_STYLE_BOLD(0L),
    CEF_TEXT_STYLE_ITALIC(1L),
    CEF_TEXT_STYLE_STRIKE(2L),
    CEF_TEXT_STYLE_DIAGONAL_STRIKE(3L),
    CEF_TEXT_STYLE_UNDERLINE(4L),
    CEF_TEXT_STYLE_NUM_VALUES(5L),
    UNKNOWN(-1L);

    public final long value;

    CefTextStyle(long v) {
        this.value = v;
    }

    public static CefTextStyle fromLong(long v) {
        for (CefTextStyle e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
