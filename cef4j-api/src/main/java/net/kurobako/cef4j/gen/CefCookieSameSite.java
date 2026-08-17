// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Cookie same site values.
 * <p>Definition generated from cef_types.h
 * <pre>typedef enum {
 *   CEF_COOKIE_SAME_SITE_UNSPECIFIED = 0,
 *   CEF_COOKIE_SAME_SITE_NO_RESTRICTION = 1,
 *   CEF_COOKIE_SAME_SITE_LAX_MODE = 2,
 *   CEF_COOKIE_SAME_SITE_STRICT_MODE = 3,
 *   CEF_COOKIE_SAME_SITE_NUM_VALUES = 4
 * } cef_cookie_same_site_t;</pre>
 * <p>Possible values: {@link Kind#UNSPECIFIED}, {@link Kind#NO_RESTRICTION}, {@link Kind#LAX_MODE}, {@link Kind#STRICT_MODE}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public final class CefCookieSameSite implements CefEnum<CefCookieSameSite> {

    /** Known constants for {@link CefCookieSameSite}. */
    public enum Kind {
        UNSPECIFIED(0, "0", "CEF_COOKIE_SAME_SITE_UNSPECIFIED"),
        NO_RESTRICTION(1, "1", "CEF_COOKIE_SAME_SITE_NO_RESTRICTION"),
        LAX_MODE(2, "2", "CEF_COOKIE_SAME_SITE_LAX_MODE"),
        STRICT_MODE(3, "3", "CEF_COOKIE_SAME_SITE_STRICT_MODE"),
        NUM_VALUES(4, "4", "CEF_COOKIE_SAME_SITE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_cookie_same_site_t"}). */
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

    private CefCookieSameSite(long value) {
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
    public static CefCookieSameSite of(long v) {
        return new CefCookieSameSite(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefCookieSameSite of(Kind k) {
        return new CefCookieSameSite(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCookieSameSite)) return false;
        return this.value == ((CefCookieSameSite) obj).value;
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
