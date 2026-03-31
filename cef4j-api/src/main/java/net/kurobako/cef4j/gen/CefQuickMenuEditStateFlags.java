// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported quick menu state bit flags. */
public enum CefQuickMenuEditStateFlags {
    QM_EDITFLAG_NONE(0L),
    QM_EDITFLAG_CAN_ELLIPSIS(1L),
    QM_EDITFLAG_CAN_CUT(2L),
    QM_EDITFLAG_CAN_COPY(4L),
    QM_EDITFLAG_CAN_PASTE(8L),
    UNKNOWN(-1L);

    public final long value;

    CefQuickMenuEditStateFlags(long v) {
        this.value = v;
    }

    public static CefQuickMenuEditStateFlags fromLong(long v) {
        for (CefQuickMenuEditStateFlags e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
