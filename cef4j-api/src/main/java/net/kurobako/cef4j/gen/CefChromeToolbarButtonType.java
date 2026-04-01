// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Chrome toolbar button types. Should be kept in sync with CEF's internal ToolbarButtonType type.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_CTBT_CAST_DEPRECATED = 0,
 *   CEF_CTBT_DOWNLOAD_DEPRECATED = 1,
 *   CEF_CTBT_SEND_TAB_TO_SELF_DEPRECATED = 2,
 *   CEF_CTBT_SIDE_PANEL_DEPRECATED = 3,
 *   CEF_CTBT_MEDIA = 4,
 *   ...
 * } cef_chrome_toolbar_button_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#CAST_DEPRECATED}, {@link Kind#DOWNLOAD_DEPRECATED},
 * {@link Kind#SEND_TAB_TO_SELF_DEPRECATED}, {@link Kind#SIDE_PANEL_DEPRECATED}, {@link Kind#MEDIA},
 * {@link Kind#TAB_SEARCH}, {@link Kind#BATTERY_SAVER}, {@link Kind#AVATAR}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefChromeToolbarButtonType implements CefEnum<CefChromeToolbarButtonType> {

    /** Known constants for {@link CefChromeToolbarButtonType}. */
    public enum Kind {
        CAST_DEPRECATED(0, "0", "CEF_CTBT_CAST_DEPRECATED"),
        DOWNLOAD_DEPRECATED(1, "1", "CEF_CTBT_DOWNLOAD_DEPRECATED"),
        SEND_TAB_TO_SELF_DEPRECATED(2, "2", "CEF_CTBT_SEND_TAB_TO_SELF_DEPRECATED"),
        SIDE_PANEL_DEPRECATED(3, "3", "CEF_CTBT_SIDE_PANEL_DEPRECATED"),
        MEDIA(4, "4", "CEF_CTBT_MEDIA"),
        TAB_SEARCH(5, "5", "CEF_CTBT_TAB_SEARCH"),
        BATTERY_SAVER(6, "6", "CEF_CTBT_BATTERY_SAVER"),
        AVATAR(7, "7", "CEF_CTBT_AVATAR"),
        NUM_VALUES(8, "8", "CEF_CTBT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_chrome_toolbar_button_type_t"}). */
        public final String name;

        Kind(long value, String expr, String name) {
            this.value = value;
            this.expr = expr;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(expr=" + expr + ", value=" + value + ")";
        }
    }

    /** The underlying C enum numeric value. May not correspond to any known {@link Kind}. */
    public final long value;

    private CefChromeToolbarButtonType(long value) {
        this.value = value;
    }

    @Override
    public long value() {
        return value;
    }

    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    @Override
    public String name() {
        return kind().map(k -> k.name).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value. No data is lost — unknown or composite values are preserved. */
    public static CefChromeToolbarButtonType of(long v) {
        return new CefChromeToolbarButtonType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefChromeToolbarButtonType of(Kind k) {
        return new CefChromeToolbarButtonType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefChromeToolbarButtonType)) return false;
        return this.value == ((CefChromeToolbarButtonType) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
