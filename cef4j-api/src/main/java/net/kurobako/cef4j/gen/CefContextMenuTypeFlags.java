// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported context menu type flags. */
public enum CefContextMenuTypeFlags {

    /** No node is selected. */
    CM_TYPEFLAG_NONE(0L),
    /** The top page is selected. */
    CM_TYPEFLAG_PAGE(1L),
    /** A subframe page is selected. */
    CM_TYPEFLAG_FRAME(2L),
    /** A link is selected. */
    CM_TYPEFLAG_LINK(4L),
    /** A media node is selected. */
    CM_TYPEFLAG_MEDIA(8L),
    /** There is a textual or mixed selection that is selected. */
    CM_TYPEFLAG_SELECTION(16L),
    /** An editable element is selected. */
    CM_TYPEFLAG_EDITABLE(32L),
    UNKNOWN(-1L);

    public final long value;

    CefContextMenuTypeFlags(long v) {
        this.value = v;
    }

    public static CefContextMenuTypeFlags fromLong(long v) {
        for (CefContextMenuTypeFlags e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
