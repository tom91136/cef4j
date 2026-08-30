// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Print job duplex mode values.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   DUPLEX_MODE_UNKNOWN = -1,
 *   DUPLEX_MODE_SIMPLEX = 0,
 *   DUPLEX_MODE_LONG_EDGE = 1,
 *   DUPLEX_MODE_SHORT_EDGE = 2,
 *   DUPLEX_MODE_NUM_VALUES = 3
 * } cef_duplex_mode_t;</pre>
 *
 * <p>Possible values: {@link Kind#UNKNOWN}, {@link Kind#SIMPLEX}, {@link Kind#LONG_EDGE}, {@link Kind#SHORT_EDGE},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefDuplexMode implements CefEnum<CefDuplexMode> {

    /** Known constants for {@link CefDuplexMode}. */
    public enum Kind {
        UNKNOWN(-1, "- 1", "DUPLEX_MODE_UNKNOWN"),
        SIMPLEX(0, "0", "DUPLEX_MODE_SIMPLEX"),
        LONG_EDGE(1, "1", "DUPLEX_MODE_LONG_EDGE"),
        SHORT_EDGE(2, "2", "DUPLEX_MODE_SHORT_EDGE"),
        NUM_VALUES(3, "3", "DUPLEX_MODE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_duplex_mode_t"}). */
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

    private CefDuplexMode(long value) {
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
    public static CefDuplexMode of(long v) {
        return new CefDuplexMode(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefDuplexMode of(Kind k) {
        return new CefDuplexMode(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefDuplexMode)) return false;
        return this.value == ((CefDuplexMode) obj).value;
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
