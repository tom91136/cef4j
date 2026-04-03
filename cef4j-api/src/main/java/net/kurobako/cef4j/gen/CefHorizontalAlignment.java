// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Specifies the horizontal text alignment mode.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_HORIZONTAL_ALIGNMENT_LEFT = 0,
 *   CEF_HORIZONTAL_ALIGNMENT_CENTER = 1,
 *   CEF_HORIZONTAL_ALIGNMENT_RIGHT = 2
 * } cef_horizontal_alignment_t;</pre>
 *
 * <p>Possible values: {@link Kind#LEFT}, {@link Kind#CENTER}, {@link Kind#RIGHT}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefHorizontalAlignment implements CefEnum<CefHorizontalAlignment> {

    /** Known constants for {@link CefHorizontalAlignment}. */
    public enum Kind {
        /** Align the text's left edge with that of its display area. */
        LEFT(0, "0", "CEF_HORIZONTAL_ALIGNMENT_LEFT"),
        /** Align the text's center with that of its display area. */
        CENTER(1, "1", "CEF_HORIZONTAL_ALIGNMENT_CENTER"),
        /** Align the text's right edge with that of its display area. */
        RIGHT(2, "2", "CEF_HORIZONTAL_ALIGNMENT_RIGHT");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_horizontal_alignment_t"}). */
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

    private CefHorizontalAlignment(long value) {
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
    public static CefHorizontalAlignment of(long v) {
        return new CefHorizontalAlignment(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefHorizontalAlignment of(Kind k) {
        return new CefHorizontalAlignment(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefHorizontalAlignment)) return false;
        return this.value == ((CefHorizontalAlignment) obj).value;
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
