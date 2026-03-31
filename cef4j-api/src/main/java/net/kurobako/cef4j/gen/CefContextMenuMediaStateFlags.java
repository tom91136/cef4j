// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported context menu media state bit flags. These constants match their equivalents in Chromium's
 * ContextMenuData::MediaFlags and should not be renumbered.
 */
public enum CefContextMenuMediaStateFlags {
    CM_MEDIAFLAG_NONE(0L),
    CM_MEDIAFLAG_IN_ERROR(1L),
    CM_MEDIAFLAG_PAUSED(2L),
    CM_MEDIAFLAG_MUTED(4L),
    CM_MEDIAFLAG_LOOP(8L),
    CM_MEDIAFLAG_CAN_SAVE(16L),
    CM_MEDIAFLAG_HAS_AUDIO(32L),
    CM_MEDIAFLAG_CAN_TOGGLE_CONTROLS(64L),
    CM_MEDIAFLAG_CONTROLS(128L),
    CM_MEDIAFLAG_CAN_PRINT(256L),
    CM_MEDIAFLAG_CAN_ROTATE(512L),
    CM_MEDIAFLAG_CAN_PICTURE_IN_PICTURE(1024L),
    CM_MEDIAFLAG_PICTURE_IN_PICTURE(2048L),
    CM_MEDIAFLAG_CAN_LOOP(4096L),
    UNKNOWN(-1L);

    public final long value;

    CefContextMenuMediaStateFlags(long v) {
        this.value = v;
    }

    public static CefContextMenuMediaStateFlags fromLong(long v) {
        for (CefContextMenuMediaStateFlags e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
