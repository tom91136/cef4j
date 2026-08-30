// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Thread priority values listed in increasing order of importance.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   TP_BACKGROUND = 0,
 *   TP_NORMAL = 1,
 *   TP_DISPLAY = 2,
 *   TP_REALTIME_AUDIO = 3,
 *   TP_NUM_VALUES = 4
 * } cef_thread_priority_t;</pre>
 *
 * <p>Possible values: {@link Kind#BACKGROUND}, {@link Kind#NORMAL}, {@link Kind#DISPLAY}, {@link Kind#REALTIME_AUDIO},
 * {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public final class CefThreadPriority implements CefEnum<CefThreadPriority> {

    /** Known constants for {@link CefThreadPriority}. */
    public enum Kind {
        /** Suitable for threads that shouldn't disrupt high priority work. */
        BACKGROUND(0, "0", "TP_BACKGROUND"),
        /** Default priority level. */
        NORMAL(1, "1", "TP_NORMAL"),
        /** Suitable for threads which generate data for the display (at ~60Hz). */
        DISPLAY(2, "2", "TP_DISPLAY"),
        /** Suitable for low-latency, glitch-resistant audio. */
        REALTIME_AUDIO(3, "3", "TP_REALTIME_AUDIO"),
        NUM_VALUES(4, "4", "TP_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_thread_priority_t"}). */
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

    private CefThreadPriority(long value) {
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
    public static CefThreadPriority of(long v) {
        return new CefThreadPriority(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefThreadPriority of(Kind k) {
        return new CefThreadPriority(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefThreadPriority)) return false;
        return this.value == ((CefThreadPriority) obj).value;
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
