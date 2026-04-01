// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Specifies the color variants supported by CefRequestContext.setChromeThemeColor().
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_COLOR_VARIANT_SYSTEM = 0,
 *   CEF_COLOR_VARIANT_LIGHT = 1,
 *   CEF_COLOR_VARIANT_DARK = 2,
 *   CEF_COLOR_VARIANT_TONAL_SPOT = 3,
 *   CEF_COLOR_VARIANT_NEUTRAL = 4,
 *   ...
 * } cef_color_variant_t;</pre>
 *
 * <p>Possible values: {@link Kind#SYSTEM}, {@link Kind#LIGHT}, {@link Kind#DARK}, {@link Kind#TONAL_SPOT},
 * {@link Kind#NEUTRAL}, {@link Kind#VIBRANT}, {@link Kind#EXPRESSIVE}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefColorVariant implements CefEnum<CefColorVariant> {

    /** Known constants for {@link CefColorVariant}. */
    public enum Kind {
        SYSTEM(0, "0", "CEF_COLOR_VARIANT_SYSTEM"),
        LIGHT(1, "1", "CEF_COLOR_VARIANT_LIGHT"),
        DARK(2, "2", "CEF_COLOR_VARIANT_DARK"),
        TONAL_SPOT(3, "3", "CEF_COLOR_VARIANT_TONAL_SPOT"),
        NEUTRAL(4, "4", "CEF_COLOR_VARIANT_NEUTRAL"),
        VIBRANT(5, "5", "CEF_COLOR_VARIANT_VIBRANT"),
        EXPRESSIVE(6, "6", "CEF_COLOR_VARIANT_EXPRESSIVE"),
        NUM_VALUES(7, "7", "CEF_COLOR_VARIANT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_color_variant_t"}). */
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

    private CefColorVariant(long value) {
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
    public static CefColorVariant of(long v) {
        return new CefColorVariant(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefColorVariant of(Kind k) {
        return new CefColorVariant(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefColorVariant)) return false;
        return this.value == ((CefColorVariant) obj).value;
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
