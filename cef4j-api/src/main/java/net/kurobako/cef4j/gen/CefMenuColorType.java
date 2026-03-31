// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Supported color types for menu items. */
public enum CefMenuColorType {
    CEF_MENU_COLOR_TEXT(0L),
    CEF_MENU_COLOR_TEXT_HOVERED(1L),
    CEF_MENU_COLOR_TEXT_ACCELERATOR(2L),
    CEF_MENU_COLOR_TEXT_ACCELERATOR_HOVERED(3L),
    CEF_MENU_COLOR_BACKGROUND(4L),
    CEF_MENU_COLOR_BACKGROUND_HOVERED(5L),
    CEF_MENU_COLOR_NUM_VALUES(6L),
    UNKNOWN(-1L);

    public final long value;

    CefMenuColorType(long v) {
        this.value = v;
    }

    public static CefMenuColorType fromLong(long v) {
        for (CefMenuColorType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
