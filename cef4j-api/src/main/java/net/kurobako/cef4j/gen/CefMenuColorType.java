// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported color types for menu items.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_MENU_COLOR_TEXT = 0,
 *   CEF_MENU_COLOR_TEXT_HOVERED = 1,
 *   CEF_MENU_COLOR_TEXT_ACCELERATOR = 2,
 *   CEF_MENU_COLOR_TEXT_ACCELERATOR_HOVERED = 3,
 *   CEF_MENU_COLOR_BACKGROUND = 4,
 *   ...
 * } cef_menu_color_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#TEXT}, {@link Kind#TEXT_HOVERED}, {@link Kind#TEXT_ACCELERATOR},
 * {@link Kind#TEXT_ACCELERATOR_HOVERED}, {@link Kind#BACKGROUND}, {@link Kind#BACKGROUND_HOVERED},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefMenuColorType implements CefEnum<CefMenuColorType> {

    /** Known constants for {@link CefMenuColorType}. */
    public enum Kind {
        TEXT(0, "0", "CEF_MENU_COLOR_TEXT"),
        TEXT_HOVERED(1, "1", "CEF_MENU_COLOR_TEXT_HOVERED"),
        TEXT_ACCELERATOR(2, "2", "CEF_MENU_COLOR_TEXT_ACCELERATOR"),
        TEXT_ACCELERATOR_HOVERED(3, "3", "CEF_MENU_COLOR_TEXT_ACCELERATOR_HOVERED"),
        BACKGROUND(4, "4", "CEF_MENU_COLOR_BACKGROUND"),
        BACKGROUND_HOVERED(5, "5", "CEF_MENU_COLOR_BACKGROUND_HOVERED"),
        NUM_VALUES(6, "6", "CEF_MENU_COLOR_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_menu_color_type_t"}). */
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

    private CefMenuColorType(long value) {
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
    public static CefMenuColorType of(long v) {
        return new CefMenuColorType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefMenuColorType of(Kind k) {
        return new CefMenuColorType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMenuColorType)) return false;
        return this.value == ((CefMenuColorType) obj).value;
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
