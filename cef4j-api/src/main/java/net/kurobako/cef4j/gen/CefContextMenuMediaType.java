// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported context menu media types. These constants match their equivalents in Chromium's ContextMenuDataMediaType
 * and should not be renumbered.
 */
public enum CefContextMenuMediaType {

    /** No special node is in context. */
    CM_MEDIATYPE_NONE(0L),
    /** An image node is selected. */
    CM_MEDIATYPE_IMAGE(1L),
    /** A video node is selected. */
    CM_MEDIATYPE_VIDEO(2L),
    /** An audio node is selected. */
    CM_MEDIATYPE_AUDIO(3L),
    /** An canvas node is selected. */
    CM_MEDIATYPE_CANVAS(4L),
    /** A file node is selected. */
    CM_MEDIATYPE_FILE(5L),
    /** A plugin node is selected. */
    CM_MEDIATYPE_PLUGIN(6L),
    CM_MEDIATYPE_NUM_VALUES(7L),
    UNKNOWN(-1L);

    public final long value;

    CefContextMenuMediaType(long v) {
        this.value = v;
    }

    public static CefContextMenuMediaType fromLong(long v) {
        for (CefContextMenuMediaType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
