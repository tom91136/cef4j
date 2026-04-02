// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Composition underline style.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_CUS_SOLID = 0,
 *   CEF_CUS_DOT = 1,
 *   CEF_CUS_DASH = 2,
 *   CEF_CUS_NONE = 3,
 *   CEF_CUS_NUM_VALUES = 4
 * } cef_composition_underline_style_t;</pre>
 *
 * <p>Possible values: {@link Kind#SOLID}, {@link Kind#DOT}, {@link Kind#DASH}, {@link Kind#NONE},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefCompositionUnderlineStyle implements CefEnum<CefCompositionUnderlineStyle> {

    /** Known constants for {@link CefCompositionUnderlineStyle}. */
    public enum Kind {
        SOLID(0, "0", "CEF_CUS_SOLID"),
        DOT(1, "1", "CEF_CUS_DOT"),
        DASH(2, "2", "CEF_CUS_DASH"),
        NONE(3, "3", "CEF_CUS_NONE"),
        NUM_VALUES(4, "4", "CEF_CUS_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_composition_underline_style_t"}). */
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

    private CefCompositionUnderlineStyle(long value) {
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
    public static CefCompositionUnderlineStyle of(long v) {
        return new CefCompositionUnderlineStyle(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefCompositionUnderlineStyle of(Kind k) {
        return new CefCompositionUnderlineStyle(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCompositionUnderlineStyle)) return false;
        return this.value == ((CefCompositionUnderlineStyle) obj).value;
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
