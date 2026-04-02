// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported UI scale factors for the platform. SCALE_FACTOR_NONE is used for density independent resources such as
 * string, html/js files or an image that can be used for any scale factors (such as wallpapers).
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   SCALE_FACTOR_NONE = 0,
 *   SCALE_FACTOR_100P = 1,
 *   SCALE_FACTOR_125P = 2,
 *   SCALE_FACTOR_133P = 3,
 *   SCALE_FACTOR_140P = 4,
 *   ...
 * } cef_scale_factor_t;</pre>
 *
 * <p>Possible values: {@link Kind#SCALE_FACTOR_NONE}, {@link Kind#SCALE_FACTOR_100P}, {@link Kind#SCALE_FACTOR_125P},
 * {@link Kind#SCALE_FACTOR_133P}, {@link Kind#SCALE_FACTOR_140P}, {@link Kind#SCALE_FACTOR_150P},
 * {@link Kind#SCALE_FACTOR_180P}, {@link Kind#SCALE_FACTOR_200P}, {@link Kind#SCALE_FACTOR_250P},
 * {@link Kind#SCALE_FACTOR_300P}, {@link Kind#SCALE_FACTOR_NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefScaleFactor implements CefEnum<CefScaleFactor> {

    /** Known constants for {@link CefScaleFactor}. */
    public enum Kind {
        SCALE_FACTOR_NONE(0, "0", "SCALE_FACTOR_NONE"),
        SCALE_FACTOR_100P(1, "1", "SCALE_FACTOR_100P"),
        SCALE_FACTOR_125P(2, "2", "SCALE_FACTOR_125P"),
        SCALE_FACTOR_133P(3, "3", "SCALE_FACTOR_133P"),
        SCALE_FACTOR_140P(4, "4", "SCALE_FACTOR_140P"),
        SCALE_FACTOR_150P(5, "5", "SCALE_FACTOR_150P"),
        SCALE_FACTOR_180P(6, "6", "SCALE_FACTOR_180P"),
        SCALE_FACTOR_200P(7, "7", "SCALE_FACTOR_200P"),
        SCALE_FACTOR_250P(8, "8", "SCALE_FACTOR_250P"),
        SCALE_FACTOR_300P(9, "9", "SCALE_FACTOR_300P"),
        SCALE_FACTOR_NUM_VALUES(10, "10", "SCALE_FACTOR_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_scale_factor_t"}). */
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

    private CefScaleFactor(long value) {
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
    public static CefScaleFactor of(long v) {
        return new CefScaleFactor(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefScaleFactor of(Kind k) {
        return new CefScaleFactor(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefScaleFactor)) return false;
        return this.value == ((CefScaleFactor) obj).value;
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
