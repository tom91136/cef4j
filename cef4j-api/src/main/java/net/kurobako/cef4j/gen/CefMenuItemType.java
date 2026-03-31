// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported menu item types. */
public enum CefMenuItemType {
    MENUITEMTYPE_NONE(0L),
    MENUITEMTYPE_COMMAND(1L),
    MENUITEMTYPE_CHECK(2L),
    MENUITEMTYPE_RADIO(3L),
    MENUITEMTYPE_SEPARATOR(4L),
    MENUITEMTYPE_SUBMENU(5L),
    UNKNOWN(-1L);

    public final long value;

    CefMenuItemType(long v) {
        this.value = v;
    }

    public static CefMenuItemType fromLong(long v) {
        for (CefMenuItemType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
