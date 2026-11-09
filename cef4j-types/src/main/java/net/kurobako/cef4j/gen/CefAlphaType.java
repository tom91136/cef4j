// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Describes how to interpret the alpha component of a pixel.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_ALPHA_TYPE_OPAQUE = 0,
 *   CEF_ALPHA_TYPE_PREMULTIPLIED = 1,
 *   CEF_ALPHA_TYPE_POSTMULTIPLIED = 2
 * } cef_alpha_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#OPAQUE}, {@link Kind#PREMULTIPLIED}, {@link Kind#POSTMULTIPLIED}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefAlphaType implements CefEnum<CefAlphaType> {

    /** Known constants for {@link CefAlphaType}. */
    public enum Kind {
        /** No transparency. The alpha component is ignored. */
        OPAQUE(new EnumConstant(0, "0", "CEF_ALPHA_TYPE_OPAQUE")),
        /** Transparency with pre-multiplied alpha component. */
        PREMULTIPLIED(new EnumConstant(1, "1", "CEF_ALPHA_TYPE_PREMULTIPLIED")),
        /** Transparency with post-multiplied alpha component. */
        POSTMULTIPLIED(new EnumConstant(2, "2", "CEF_ALPHA_TYPE_POSTMULTIPLIED"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_alpha_type_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
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
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefAlphaType of(long v) {
        return new CefAlphaType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefAlphaType of(Kind k) {
        return new CefAlphaType(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
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
