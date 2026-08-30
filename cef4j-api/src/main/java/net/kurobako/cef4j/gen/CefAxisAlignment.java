// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Specifies where along the axis the CefBoxLayout child views should be laid out. Should be kept in sync with
 * Chromium's views::LayoutAlignment type.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_AXIS_ALIGNMENT_START = 0,
 *   CEF_AXIS_ALIGNMENT_CENTER = 1,
 *   CEF_AXIS_ALIGNMENT_END = 2,
 *   CEF_AXIS_ALIGNMENT_STRETCH = 3,
 *   CEF_AXIS_ALIGNMENT_NUM_VALUES = 4
 * } cef_axis_alignment_t;</pre>
 *
 * <p>Possible values: {@link Kind#START}, {@link Kind#CENTER}, {@link Kind#END}, {@link Kind#STRETCH},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefAxisAlignment implements CefEnum<CefAxisAlignment> {

    /** Known constants for {@link CefAxisAlignment}. */
    public enum Kind {
        /** Child views will be left/top-aligned. */
        START(0, "0", "CEF_AXIS_ALIGNMENT_START"),
        /** Child views will be center-aligned. */
        CENTER(1, "1", "CEF_AXIS_ALIGNMENT_CENTER"),
        /** Child views will be right/bottom-aligned. */
        END(2, "2", "CEF_AXIS_ALIGNMENT_END"),
        /** Child views will be stretched to fit. */
        STRETCH(3, "3", "CEF_AXIS_ALIGNMENT_STRETCH"),
        NUM_VALUES(4, "4", "CEF_AXIS_ALIGNMENT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_axis_alignment_t"}). */
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

    private CefAxisAlignment(long value) {
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
    public static CefAxisAlignment of(long v) {
        return new CefAxisAlignment(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefAxisAlignment of(Kind k) {
        return new CefAxisAlignment(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefAxisAlignment)) return false;
        return this.value == ((CefAxisAlignment) obj).value;
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
