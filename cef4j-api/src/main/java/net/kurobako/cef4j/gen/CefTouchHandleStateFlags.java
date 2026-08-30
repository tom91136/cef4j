// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Values indicating what state of the touch handle is set.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CEF_THS_FLAG_NONE = 0,
 *   CEF_THS_FLAG_ENABLED = 1 &lt;&lt; 0,
 *   CEF_THS_FLAG_ORIENTATION = 1 &lt;&lt; 1,
 *   CEF_THS_FLAG_ORIGIN = 1 &lt;&lt; 2,
 *   CEF_THS_FLAG_ALPHA = 1 &lt;&lt; 3
 * } cef_touch_handle_state_flags_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#ENABLED}, {@link Kind#ORIENTATION}, {@link Kind#ORIGIN},
 * {@link Kind#ALPHA}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefTouchHandleStateFlags implements CefEnum<CefTouchHandleStateFlags> {

    /** Known constants for {@link CefTouchHandleStateFlags}. */
    public enum Kind {
        NONE(0, "0", "CEF_THS_FLAG_NONE"),
        ENABLED(1 << 0, "1 << 0", "CEF_THS_FLAG_ENABLED"),
        ORIENTATION(1 << 1, "1 << 1", "CEF_THS_FLAG_ORIENTATION"),
        ORIGIN(1 << 2, "1 << 2", "CEF_THS_FLAG_ORIGIN"),
        ALPHA(1 << 3, "1 << 3", "CEF_THS_FLAG_ALPHA");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_touch_handle_state_flags_t"}). */
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

    private CefTouchHandleStateFlags(long value) {
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
    public static CefTouchHandleStateFlags of(long v) {
        return new CefTouchHandleStateFlags(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefTouchHandleStateFlags of(Kind k) {
        return new CefTouchHandleStateFlags(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefTouchHandleStateFlags)) return false;
        return this.value == ((CefTouchHandleStateFlags) obj).value;
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
