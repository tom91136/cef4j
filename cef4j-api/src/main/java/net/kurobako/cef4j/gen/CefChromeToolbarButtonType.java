// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Chrome toolbar button types. Should be kept in sync with CEF's internal ToolbarButtonType type. */
public enum CefChromeToolbarButtonType {
    CEF_CTBT_CAST_DEPRECATED(0L),
    CEF_CTBT_DOWNLOAD_DEPRECATED(1L),
    CEF_CTBT_SEND_TAB_TO_SELF_DEPRECATED(2L),
    CEF_CTBT_SIDE_PANEL_DEPRECATED(3L),
    CEF_CTBT_MEDIA(4L),
    CEF_CTBT_TAB_SEARCH(5L),
    CEF_CTBT_BATTERY_SAVER(6L),
    CEF_CTBT_AVATAR(7L),
    CEF_CTBT_NUM_VALUES(8L),
    UNKNOWN(-1L);

    public final long value;

    CefChromeToolbarButtonType(long v) {
        this.value = v;
    }

    public static CefChromeToolbarButtonType fromLong(long v) {
        for (CefChromeToolbarButtonType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
