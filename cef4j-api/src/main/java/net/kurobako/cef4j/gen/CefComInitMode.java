// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Windows COM initialization mode. Specifies how COM will be initialized for a new thread.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   COM_INIT_MODE_NONE = 0,
 *   COM_INIT_MODE_STA = 1,
 *   COM_INIT_MODE_MTA = 2
 * } cef_com_init_mode_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#STA}, {@link Kind#MTA}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefComInitMode implements CefEnum<CefComInitMode> {

    /** Known constants for {@link CefComInitMode}. */
    public enum Kind {
        /** No COM initialization. */
        NONE(0, "0", "COM_INIT_MODE_NONE"),
        /** Initialize COM using single-threaded apartments. */
        STA(1, "1", "COM_INIT_MODE_STA"),
        /** Initialize COM using multi-threaded apartments. */
        MTA(2, "2", "COM_INIT_MODE_MTA");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_com_init_mode_t"}). */
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

    private CefComInitMode(long value) {
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
    public static CefComInitMode of(long v) {
        return new CefComInitMode(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefComInitMode of(Kind k) {
        return new CefComInitMode(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefComInitMode)) return false;
        return this.value == ((CefComInitMode) obj).value;
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
