// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Chrome toolbar types.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_CTT_UNKNOWN = 0,
 *   CEF_CTT_NONE = 1,
 *   CEF_CTT_NORMAL = 2,
 *   CEF_CTT_LOCATION = 3,
 *   CEF_CTT_NUM_VALUES = 4
 * } cef_chrome_toolbar_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#NONE}, {@link Kind#NORMAL}, {@link Kind#LOCATION},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefChromeToolbarType implements CefEnum<CefChromeToolbarType> {

    /** Known constants for {@link CefChromeToolbarType}. */
    public enum Kind {
        UNKNOWN(0, "0", "CEF_CTT_UNKNOWN"),
        NONE(1, "1", "CEF_CTT_NONE"),
        NORMAL(2, "2", "CEF_CTT_NORMAL"),
        LOCATION(3, "3", "CEF_CTT_LOCATION"),
        NUM_VALUES(4, "4", "CEF_CTT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_chrome_toolbar_type_t"}). */
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

    private CefChromeToolbarType(long value) {
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
    public static CefChromeToolbarType of(long v) {
        return new CefChromeToolbarType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefChromeToolbarType of(Kind k) {
        return new CefChromeToolbarType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefChromeToolbarType)) return false;
        return this.value == ((CefChromeToolbarType) obj).value;
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
