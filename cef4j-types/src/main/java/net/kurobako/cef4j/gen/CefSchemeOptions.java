// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Possible values: {@link Kind#NONE}, {@link Kind#STANDARD}, {@link Kind#LOCAL}, {@link Kind#DISPLAY_ISOLATED},
 * {@link Kind#SECURE}, {@link Kind#CORS_ENABLED}, {@link Kind#CSP_BYPASSING}, {@link Kind#FETCH_ENABLED}
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefSchemeOptions implements CefEnum<CefSchemeOptions> {

    /** Known constants for {@link CefSchemeOptions}. */
    public enum Kind {
        NONE(new EnumConstant(0, "0", "CEF_SCHEME_OPTION_NONE")),
        STANDARD(new EnumConstant(1 << 0, "1 << 0", "CEF_SCHEME_OPTION_STANDARD")),
        LOCAL(new EnumConstant(1 << 1, "1 << 1", "CEF_SCHEME_OPTION_LOCAL")),
        DISPLAY_ISOLATED(new EnumConstant(1 << 2, "1 << 2", "CEF_SCHEME_OPTION_DISPLAY_ISOLATED")),
        SECURE(new EnumConstant(1 << 3, "1 << 3", "CEF_SCHEME_OPTION_SECURE")),
        CORS_ENABLED(new EnumConstant(1 << 4, "1 << 4", "CEF_SCHEME_OPTION_CORS_ENABLED")),
        CSP_BYPASSING(new EnumConstant(1 << 5, "1 << 5", "CEF_SCHEME_OPTION_CSP_BYPASSING")),
        FETCH_ENABLED(new EnumConstant(1 << 6, "1 << 6", "CEF_SCHEME_OPTION_FETCH_ENABLED"));

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

        /** The C constant name (e.g., {@code "cef_scheme_options_t"}). */
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

    private CefSchemeOptions(long value) {
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
    public static CefSchemeOptions of(long v) {
        return new CefSchemeOptions(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefSchemeOptions of(Kind k) {
        return new CefSchemeOptions(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefSchemeOptions)) return false;
        return this.value == ((CefSchemeOptions) obj).value;
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
