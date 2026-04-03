// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Message loop types. Indicates the set of asynchronous events that a message loop can process.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   ML_TYPE_DEFAULT = 0,
 *   ML_TYPE_UI = 1,
 *   ML_TYPE_IO = 2,
 *   ML_NUM_VALUES = 3
 * } cef_message_loop_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#TYPE_DEFAULT}, {@link Kind#TYPE_UI}, {@link Kind#TYPE_IO}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefMessageLoopType implements CefEnum<CefMessageLoopType> {

    /** Known constants for {@link CefMessageLoopType}. */
    public enum Kind {
        /** Supports tasks and timers. */
        TYPE_DEFAULT(0, "0", "ML_TYPE_DEFAULT"),
        /** Supports tasks, timers and native UI events (e.g. Windows messages). */
        TYPE_UI(1, "1", "ML_TYPE_UI"),
        /** Supports tasks, timers and asynchronous IO events. */
        TYPE_IO(2, "2", "ML_TYPE_IO"),
        NUM_VALUES(3, "3", "ML_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_message_loop_type_t"}). */
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

    private CefMessageLoopType(long value) {
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
    public static CefMessageLoopType of(long v) {
        return new CefMessageLoopType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefMessageLoopType of(Kind k) {
        return new CefMessageLoopType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefMessageLoopType)) return false;
        return this.value == ((CefMessageLoopType) obj).value;
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
