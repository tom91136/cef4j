// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported menu item types.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   MENUITEMTYPE_NONE = 0,
 *   MENUITEMTYPE_COMMAND = 1,
 *   MENUITEMTYPE_CHECK = 2,
 *   MENUITEMTYPE_RADIO = 3,
 *   MENUITEMTYPE_SEPARATOR = 4,
 *   ...
 * } cef_menu_item_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#COMMAND}, {@link Kind#CHECK}, {@link Kind#RADIO},
 * {@link Kind#SEPARATOR}, {@link Kind#SUBMENU}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefMenuItemType implements CefEnum<CefMenuItemType> {

    /** Known constants for {@link CefMenuItemType}. */
    public enum Kind {
        NONE(0, "0", "MENUITEMTYPE_NONE"),
        COMMAND(1, "1", "MENUITEMTYPE_COMMAND"),
        CHECK(2, "2", "MENUITEMTYPE_CHECK"),
        RADIO(3, "3", "MENUITEMTYPE_RADIO"),
        SEPARATOR(4, "4", "MENUITEMTYPE_SEPARATOR"),
        SUBMENU(5, "5", "MENUITEMTYPE_SUBMENU");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_menu_item_type_t"}). */
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

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefMenuItemType(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(k -> k.expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
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

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefMenuItemType of(long v) {
        return new CefMenuItemType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefMenuItemType of(Kind k) {
        return new CefMenuItemType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMenuItemType)) return false;
        return this.value == ((CefMenuItemType) obj).value;
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
