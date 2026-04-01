// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Cookie priority values.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_COOKIE_PRIORITY_LOW = -1,
 *   CEF_COOKIE_PRIORITY_MEDIUM = 0,
 *   CEF_COOKIE_PRIORITY_HIGH = 1
 * } cef_cookie_priority_t;</pre>
 *
 * <p>Possible values: {@link Kind#LOW}, {@link Kind#MEDIUM}, {@link Kind#HIGH}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefCookiePriority implements CefEnum<CefCookiePriority> {

    /** Known constants for {@link CefCookiePriority}. */
    public enum Kind {
        LOW(-1, "-1", "CEF_COOKIE_PRIORITY_LOW"),
        MEDIUM(0, "0", "CEF_COOKIE_PRIORITY_MEDIUM"),
        HIGH(1, "1", "CEF_COOKIE_PRIORITY_HIGH");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_cookie_priority_t"}). */
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

    private CefCookiePriority(long value) {
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
    public static CefCookiePriority of(long v) {
        return new CefCookiePriority(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefCookiePriority of(Kind k) {
        return new CefCookiePriority(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCookiePriority)) return false;
        return this.value == ((CefCookiePriority) obj).value;
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
