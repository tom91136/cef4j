// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Composition underline style. */
public enum CefCompositionUnderlineStyle {
    CEF_CUS_SOLID(0L),
    CEF_CUS_DOT(1L),
    CEF_CUS_DASH(2L),
    CEF_CUS_NONE(3L),
    CEF_CUS_NUM_VALUES(4L),
    UNKNOWN(-1L);

    public final long value;

    CefCompositionUnderlineStyle(long v) {
        this.value = v;
    }

    public static CefCompositionUnderlineStyle fromLong(long v) {
        for (CefCompositionUnderlineStyle e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
