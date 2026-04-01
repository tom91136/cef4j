// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Supported event bit flags.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   EVENTFLAG_NONE = 0,
 *   EVENTFLAG_CAPS_LOCK_ON = 1 << 0,
 *   EVENTFLAG_SHIFT_DOWN = 1 << 1,
 *   EVENTFLAG_CONTROL_DOWN = 1 << 2,
 *   EVENTFLAG_ALT_DOWN = 1 << 3,
 *   ...
 * } cef_event_flags_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#CAPS_LOCK_ON}, {@link Kind#SHIFT_DOWN}, {@link Kind#CONTROL_DOWN},
 * {@link Kind#ALT_DOWN}, {@link Kind#LEFT_MOUSE_BUTTON}, {@link Kind#MIDDLE_MOUSE_BUTTON},
 * {@link Kind#RIGHT_MOUSE_BUTTON}, {@link Kind#COMMAND_DOWN}, {@link Kind#NUM_LOCK_ON}, {@link Kind#IS_KEY_PAD},
 * {@link Kind#IS_LEFT}, {@link Kind#IS_RIGHT}, {@link Kind#ALTGR_DOWN}, {@link Kind#IS_REPEAT},
 * {@link Kind#PRECISION_SCROLLING_DELTA}, {@link Kind#SCROLL_BY_PAGE}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
public final class CefEventFlags implements CefEnum<CefEventFlags> {

    /** Known constants for {@link CefEventFlags}. */
    public enum Kind {
        NONE(0, "0", "EVENTFLAG_NONE"),
        CAPS_LOCK_ON(1 << 0, "1 << 0", "EVENTFLAG_CAPS_LOCK_ON"),
        SHIFT_DOWN(1 << 1, "1 << 1", "EVENTFLAG_SHIFT_DOWN"),
        CONTROL_DOWN(1 << 2, "1 << 2", "EVENTFLAG_CONTROL_DOWN"),
        ALT_DOWN(1 << 3, "1 << 3", "EVENTFLAG_ALT_DOWN"),
        LEFT_MOUSE_BUTTON(1 << 4, "1 << 4", "EVENTFLAG_LEFT_MOUSE_BUTTON"),
        MIDDLE_MOUSE_BUTTON(1 << 5, "1 << 5", "EVENTFLAG_MIDDLE_MOUSE_BUTTON"),
        RIGHT_MOUSE_BUTTON(1 << 6, "1 << 6", "EVENTFLAG_RIGHT_MOUSE_BUTTON"),
        /** Mac OS-X command key. */
        COMMAND_DOWN(1 << 7, "1 << 7", "EVENTFLAG_COMMAND_DOWN"),
        NUM_LOCK_ON(1 << 8, "1 << 8", "EVENTFLAG_NUM_LOCK_ON"),
        IS_KEY_PAD(1 << 9, "1 << 9", "EVENTFLAG_IS_KEY_PAD"),
        IS_LEFT(1 << 10, "1 << 10", "EVENTFLAG_IS_LEFT"),
        IS_RIGHT(1 << 11, "1 << 11", "EVENTFLAG_IS_RIGHT"),
        ALTGR_DOWN(1 << 12, "1 << 12", "EVENTFLAG_ALTGR_DOWN"),
        IS_REPEAT(1 << 13, "1 << 13", "EVENTFLAG_IS_REPEAT"),
        PRECISION_SCROLLING_DELTA(1 << 14, "1 << 14", "EVENTFLAG_PRECISION_SCROLLING_DELTA"),
        SCROLL_BY_PAGE(1 << 15, "1 << 15", "EVENTFLAG_SCROLL_BY_PAGE");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression (e.g., {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_event_flags_t"}). */
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

    private CefEventFlags(long value) {
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
    public static CefEventFlags of(long v) {
        return new CefEventFlags(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefEventFlags of(Kind k) {
        return new CefEventFlags(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefEventFlags)) return false;
        return this.value == ((CefEventFlags) obj).value;
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
