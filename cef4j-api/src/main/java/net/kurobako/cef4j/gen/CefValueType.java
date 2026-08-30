// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Supported value types.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   VTYPE_INVALID = 0,
 *   VTYPE_NULL = 1,
 *   VTYPE_BOOL = 2,
 *   VTYPE_INT = 3,
 *   VTYPE_DOUBLE = 4,
 *   ...
 * } cef_value_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#INVALID}, {@link Kind#NULL}, {@link Kind#BOOL}, {@link Kind#INT},
 * {@link Kind#DOUBLE}, {@link Kind#STRING}, {@link Kind#BINARY}, {@link Kind#DICTIONARY}, {@link Kind#LIST},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefValueType implements CefEnum<CefValueType> {

    /** Known constants for {@link CefValueType}. */
    public enum Kind {
        INVALID(0, "0", "VTYPE_INVALID"),
        NULL(1, "1", "VTYPE_NULL"),
        BOOL(2, "2", "VTYPE_BOOL"),
        INT(3, "3", "VTYPE_INT"),
        DOUBLE(4, "4", "VTYPE_DOUBLE"),
        STRING(5, "5", "VTYPE_STRING"),
        BINARY(6, "6", "VTYPE_BINARY"),
        DICTIONARY(7, "7", "VTYPE_DICTIONARY"),
        LIST(8, "8", "VTYPE_LIST"),
        NUM_VALUES(9, "9", "VTYPE_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_value_type_t"}). */
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

    private CefValueType(long value) {
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
    public static CefValueType of(long v) {
        return new CefValueType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefValueType of(Kind k) {
        return new CefValueType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefValueType)) return false;
        return this.value == ((CefValueType) obj).value;
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
