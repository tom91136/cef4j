// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Describes how to interpret the alpha component of a pixel.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_ALPHA_TYPE_OPAQUE = 0,
 *   CEF_ALPHA_TYPE_PREMULTIPLIED = 1,
 *   CEF_ALPHA_TYPE_POSTMULTIPLIED = 2
 * } cef_alpha_type_t;</pre>
 * <p>Possible values: {@link Kind#OPAQUE}, {@link Kind#PREMULTIPLIED}, {@link Kind#POSTMULTIPLIED}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefAlphaType implements CefEnum<CefAlphaType> {

    /** Known constants for {@link CefAlphaType}. */
    public enum Kind {
        /** No transparency. The alpha component is ignored.  */
        OPAQUE(0, "0", "CEF_ALPHA_TYPE_OPAQUE"),
        /** Transparency with pre-multiplied alpha component.  */
        PREMULTIPLIED(1, "1", "CEF_ALPHA_TYPE_PREMULTIPLIED"),
        /** Transparency with post-multiplied alpha component.  */
        POSTMULTIPLIED(2, "2", "CEF_ALPHA_TYPE_POSTMULTIPLIED");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_alpha_type_t"}). */
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

    private CefAlphaType(long value) {
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
    public static CefAlphaType of(long v) {
        return new CefAlphaType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefAlphaType of(Kind k) {
        return new CefAlphaType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAlphaType)) return false;
        return this.value == ((CefAlphaType) obj).value;
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
