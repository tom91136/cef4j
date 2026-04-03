// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Cursor type values.
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   CT_POINTER = 0,
 *   CT_CROSS = 1,
 *   CT_HAND = 2,
 *   CT_IBEAM = 3,
 *   CT_WAIT = 4,
 *   ...
 * } cef_cursor_type_t;</pre>
 *
 * <p>Possible values: {@link Kind#POINTER}, {@link Kind#CROSS}, {@link Kind#HAND}, {@link Kind#IBEAM},
 * {@link Kind#WAIT}, {@link Kind#HELP}, {@link Kind#EASTRESIZE}, {@link Kind#NORTHRESIZE},
 * {@link Kind#NORTHEASTRESIZE}, {@link Kind#NORTHWESTRESIZE}, {@link Kind#SOUTHRESIZE}, {@link Kind#SOUTHEASTRESIZE},
 * {@link Kind#SOUTHWESTRESIZE}, {@link Kind#WESTRESIZE}, {@link Kind#NORTHSOUTHRESIZE}, {@link Kind#EASTWESTRESIZE},
 * {@link Kind#NORTHEASTSOUTHWESTRESIZE}, {@link Kind#NORTHWESTSOUTHEASTRESIZE}, {@link Kind#COLUMNRESIZE},
 * {@link Kind#ROWRESIZE}, {@link Kind#MIDDLEPANNING}, {@link Kind#EASTPANNING}, {@link Kind#NORTHPANNING},
 * {@link Kind#NORTHEASTPANNING}, {@link Kind#NORTHWESTPANNING}, {@link Kind#SOUTHPANNING},
 * {@link Kind#SOUTHEASTPANNING}, {@link Kind#SOUTHWESTPANNING}, {@link Kind#WESTPANNING}, {@link Kind#MOVE},
 * {@link Kind#VERTICALTEXT}, {@link Kind#CELL}, {@link Kind#CONTEXTMENU}, {@link Kind#ALIAS}, {@link Kind#PROGRESS},
 * {@link Kind#NODROP}, {@link Kind#COPY}, {@link Kind#NONE}, {@link Kind#NOTALLOWED}, {@link Kind#ZOOMIN},
 * {@link Kind#ZOOMOUT}, {@link Kind#GRAB}, {@link Kind#GRABBING}, {@link Kind#MIDDLE_PANNING_VERTICAL},
 * {@link Kind#MIDDLE_PANNING_HORIZONTAL}, {@link Kind#CUSTOM}, {@link Kind#DND_NONE}, {@link Kind#DND_MOVE},
 * {@link Kind#DND_COPY}, {@link Kind#DND_LINK}, {@link Kind#NUM_VALUES}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public final class CefCursorType implements CefEnum<CefCursorType> {

    /** Known constants for {@link CefCursorType}. */
    public enum Kind {
        POINTER(0, "0", "CT_POINTER"),
        CROSS(1, "1", "CT_CROSS"),
        HAND(2, "2", "CT_HAND"),
        IBEAM(3, "3", "CT_IBEAM"),
        WAIT(4, "4", "CT_WAIT"),
        HELP(5, "5", "CT_HELP"),
        EASTRESIZE(6, "6", "CT_EASTRESIZE"),
        NORTHRESIZE(7, "7", "CT_NORTHRESIZE"),
        NORTHEASTRESIZE(8, "8", "CT_NORTHEASTRESIZE"),
        NORTHWESTRESIZE(9, "9", "CT_NORTHWESTRESIZE"),
        SOUTHRESIZE(10, "10", "CT_SOUTHRESIZE"),
        SOUTHEASTRESIZE(11, "11", "CT_SOUTHEASTRESIZE"),
        SOUTHWESTRESIZE(12, "12", "CT_SOUTHWESTRESIZE"),
        WESTRESIZE(13, "13", "CT_WESTRESIZE"),
        NORTHSOUTHRESIZE(14, "14", "CT_NORTHSOUTHRESIZE"),
        EASTWESTRESIZE(15, "15", "CT_EASTWESTRESIZE"),
        NORTHEASTSOUTHWESTRESIZE(16, "16", "CT_NORTHEASTSOUTHWESTRESIZE"),
        NORTHWESTSOUTHEASTRESIZE(17, "17", "CT_NORTHWESTSOUTHEASTRESIZE"),
        COLUMNRESIZE(18, "18", "CT_COLUMNRESIZE"),
        ROWRESIZE(19, "19", "CT_ROWRESIZE"),
        MIDDLEPANNING(20, "20", "CT_MIDDLEPANNING"),
        EASTPANNING(21, "21", "CT_EASTPANNING"),
        NORTHPANNING(22, "22", "CT_NORTHPANNING"),
        NORTHEASTPANNING(23, "23", "CT_NORTHEASTPANNING"),
        NORTHWESTPANNING(24, "24", "CT_NORTHWESTPANNING"),
        SOUTHPANNING(25, "25", "CT_SOUTHPANNING"),
        SOUTHEASTPANNING(26, "26", "CT_SOUTHEASTPANNING"),
        SOUTHWESTPANNING(27, "27", "CT_SOUTHWESTPANNING"),
        WESTPANNING(28, "28", "CT_WESTPANNING"),
        MOVE(29, "29", "CT_MOVE"),
        VERTICALTEXT(30, "30", "CT_VERTICALTEXT"),
        CELL(31, "31", "CT_CELL"),
        CONTEXTMENU(32, "32", "CT_CONTEXTMENU"),
        ALIAS(33, "33", "CT_ALIAS"),
        PROGRESS(34, "34", "CT_PROGRESS"),
        NODROP(35, "35", "CT_NODROP"),
        COPY(36, "36", "CT_COPY"),
        NONE(37, "37", "CT_NONE"),
        NOTALLOWED(38, "38", "CT_NOTALLOWED"),
        ZOOMIN(39, "39", "CT_ZOOMIN"),
        ZOOMOUT(40, "40", "CT_ZOOMOUT"),
        GRAB(41, "41", "CT_GRAB"),
        GRABBING(42, "42", "CT_GRABBING"),
        MIDDLE_PANNING_VERTICAL(43, "43", "CT_MIDDLE_PANNING_VERTICAL"),
        MIDDLE_PANNING_HORIZONTAL(44, "44", "CT_MIDDLE_PANNING_HORIZONTAL"),
        CUSTOM(45, "45", "CT_CUSTOM"),
        DND_NONE(46, "46", "CT_DND_NONE"),
        DND_MOVE(47, "47", "CT_DND_MOVE"),
        DND_COPY(48, "48", "CT_DND_COPY"),
        DND_LINK(49, "49", "CT_DND_LINK"),
        NUM_VALUES(50, "50", "CT_NUM_VALUES");

        private static final Kind[] VALUES = Kind.values();

        /** The underlying C enum numeric value. */
        public final long value;

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public final String expr;

        /** The C constant name (e.g., {@code "cef_cursor_type_t"}). */
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

    private CefCursorType(long value) {
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
    public static CefCursorType of(long v) {
        return new CefCursorType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefCursorType of(Kind k) {
        return new CefCursorType(k.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefCursorType)) return false;
        return this.value == ((CefCursorType) obj).value;
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
