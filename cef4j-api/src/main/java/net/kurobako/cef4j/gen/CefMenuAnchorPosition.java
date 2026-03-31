// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Specifies how a menu will be anchored for non-RTL languages. The opposite position will be used for RTL languages.
 */
public enum CefMenuAnchorPosition {
    CEF_MENU_ANCHOR_TOPLEFT(0L),
    CEF_MENU_ANCHOR_TOPRIGHT(1L),
    CEF_MENU_ANCHOR_BOTTOMCENTER(2L),
    CEF_MENU_ANCHOR_NUM_VALUES(3L),
    UNKNOWN(-1L);

    public final long value;

    CefMenuAnchorPosition(long v) {
        this.value = v;
    }

    public static CefMenuAnchorPosition fromLong(long v) {
        for (CefMenuAnchorPosition e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
