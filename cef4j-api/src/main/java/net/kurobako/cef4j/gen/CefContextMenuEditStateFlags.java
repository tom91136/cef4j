// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported context menu edit state bit flags. These constants match their equivalents in Chromium's
 * ContextMenuDataEditFlags and should not be renumbered.
 */
public enum CefContextMenuEditStateFlags {
    CM_EDITFLAG_NONE(0L),
    CM_EDITFLAG_CAN_UNDO(1L),
    CM_EDITFLAG_CAN_REDO(2L),
    CM_EDITFLAG_CAN_CUT(4L),
    CM_EDITFLAG_CAN_COPY(8L),
    CM_EDITFLAG_CAN_PASTE(16L),
    CM_EDITFLAG_CAN_DELETE(32L),
    CM_EDITFLAG_CAN_SELECT_ALL(64L),
    CM_EDITFLAG_CAN_TRANSLATE(128L),
    CM_EDITFLAG_CAN_EDIT_RICHLY(256L),
    UNKNOWN(-1L);

    public final long value;

    CefContextMenuEditStateFlags(long v) {
        this.value = v;
    }

    public static CefContextMenuEditStateFlags fromLong(long v) {
        for (CefContextMenuEditStateFlags e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
