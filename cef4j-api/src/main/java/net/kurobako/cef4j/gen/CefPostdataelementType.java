// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Post data elements may represent either bytes or files.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   PDE_TYPE_EMPTY = 0,
 *   PDE_TYPE_BYTES = 1,
 *   PDE_TYPE_FILE = 2,
 *   PDE_TYPE_NUM_VALUES = 3
 * } cef_postdataelement_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#EMPTY}, {@link Kind#BYTES}, {@link Kind#FILE}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefPostdataelementType implements CefEnum<CefPostdataelementType> {

    /** Known constants for {@link CefPostdataelementType}. */
    public enum Kind {
        EMPTY(0, "0", "PDE_TYPE_EMPTY"),
        BYTES(1, "1", "PDE_TYPE_BYTES"),
        FILE(2, "2", "PDE_TYPE_FILE"),
        NUM_VALUES(3, "3", "PDE_TYPE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_postdataelement_type_t"}). */
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

    private CefPostdataelementType(long value) {
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
    public static CefPostdataelementType of(long v) {
        return new CefPostdataelementType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefPostdataelementType of(Kind k) {
        return new CefPostdataelementType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefPostdataelementType)) return false;
        return this.value == ((CefPostdataelementType) obj).value;
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
