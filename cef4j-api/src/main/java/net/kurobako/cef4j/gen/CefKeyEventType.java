// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Key event types.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   KEYEVENT_RAWKEYDOWN = 0,
 *   KEYEVENT_KEYDOWN = 1,
 *   KEYEVENT_KEYUP = 2,
 *   KEYEVENT_CHAR = 3
 * } cef_key_event_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#RAWKEYDOWN}, {@link Kind#KEYDOWN}, {@link Kind#KEYUP}, {@link Kind#CHAR}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefKeyEventType implements CefEnum<CefKeyEventType> {

    /** Known constants for {@link CefKeyEventType}. */
    public enum Kind {
        /** Notification that a key transitioned from "up" to "down". */
        RAWKEYDOWN(0, "0", "KEYEVENT_RAWKEYDOWN"),
        /**
         * Notification that a key was pressed. This does not necessarily correspond to a character depending on the key
         * and language. Use KEYEVENT_CHAR for character input.
         */
        KEYDOWN(1, "1", "KEYEVENT_KEYDOWN"),
        /** Notification that a key was released. */
        KEYUP(2, "2", "KEYEVENT_KEYUP"),
        /**
         * Notification that a character was typed. Use this for text input. Key down events may generate 0, 1, or more
         * than one character event depending on the key, locale, and operating system.
         */
        CHAR(3, "3", "KEYEVENT_CHAR");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_key_event_type_t"}). */
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

    private CefKeyEventType(long value) {
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
    public static CefKeyEventType of(long v) {
        return new CefKeyEventType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefKeyEventType of(Kind k) {
        return new CefKeyEventType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefKeyEventType)) return false;
        return this.value == ((CefKeyEventType) obj).value;
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
