// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Text style types. Should be kepy in sync with gfx::TextStyle.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_TEXT_STYLE_BOLD = 0,
 *   CEF_TEXT_STYLE_ITALIC = 1,
 *   CEF_TEXT_STYLE_STRIKE = 2,
 *   CEF_TEXT_STYLE_DIAGONAL_STRIKE = 3,
 *   CEF_TEXT_STYLE_UNDERLINE = 4,
 *   ...
 * } cef_text_style_t;</pre>
 * <p>Possible values: {@link Kind#BOLD}, {@link Kind#ITALIC}, {@link Kind#STRIKE}, {@link Kind#DIAGONAL_STRIKE}, {@link Kind#UNDERLINE}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public final class CefTextStyle implements CefEnum<CefTextStyle> {

    /** Known constants for {@link CefTextStyle}. */
    public enum Kind {
        BOLD(0, "0", "CEF_TEXT_STYLE_BOLD"),
        ITALIC(1, "1", "CEF_TEXT_STYLE_ITALIC"),
        STRIKE(2, "2", "CEF_TEXT_STYLE_STRIKE"),
        DIAGONAL_STRIKE(3, "3", "CEF_TEXT_STYLE_DIAGONAL_STRIKE"),
        UNDERLINE(4, "4", "CEF_TEXT_STYLE_UNDERLINE"),
        NUM_VALUES(5, "5", "CEF_TEXT_STYLE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_text_style_t"}). */
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

    private CefTextStyle(long value) {
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
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values.
     * Use this for exhaustive switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefTextStyle of(long v) {
        return new CefTextStyle(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefTextStyle of(Kind k) {
        return new CefTextStyle(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTextStyle)) return false;
        return this.value == ((CefTextStyle) obj).value;
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
