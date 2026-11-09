// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefCursorType implements CefEnum<CefCursorType> {

    /** Known constants for {@link CefCursorType}. */
    public enum Kind {
        POINTER(new EnumConstant(0, "0", "CT_POINTER")),
        CROSS(new EnumConstant(1, "1", "CT_CROSS")),
        HAND(new EnumConstant(2, "2", "CT_HAND")),
        IBEAM(new EnumConstant(3, "3", "CT_IBEAM")),
        WAIT(new EnumConstant(4, "4", "CT_WAIT")),
        HELP(new EnumConstant(5, "5", "CT_HELP")),
        EASTRESIZE(new EnumConstant(6, "6", "CT_EASTRESIZE")),
        NORTHRESIZE(new EnumConstant(7, "7", "CT_NORTHRESIZE")),
        NORTHEASTRESIZE(new EnumConstant(8, "8", "CT_NORTHEASTRESIZE")),
        NORTHWESTRESIZE(new EnumConstant(9, "9", "CT_NORTHWESTRESIZE")),
        SOUTHRESIZE(new EnumConstant(10, "10", "CT_SOUTHRESIZE")),
        SOUTHEASTRESIZE(new EnumConstant(11, "11", "CT_SOUTHEASTRESIZE")),
        SOUTHWESTRESIZE(new EnumConstant(12, "12", "CT_SOUTHWESTRESIZE")),
        WESTRESIZE(new EnumConstant(13, "13", "CT_WESTRESIZE")),
        NORTHSOUTHRESIZE(new EnumConstant(14, "14", "CT_NORTHSOUTHRESIZE")),
        EASTWESTRESIZE(new EnumConstant(15, "15", "CT_EASTWESTRESIZE")),
        NORTHEASTSOUTHWESTRESIZE(new EnumConstant(16, "16", "CT_NORTHEASTSOUTHWESTRESIZE")),
        NORTHWESTSOUTHEASTRESIZE(new EnumConstant(17, "17", "CT_NORTHWESTSOUTHEASTRESIZE")),
        COLUMNRESIZE(new EnumConstant(18, "18", "CT_COLUMNRESIZE")),
        ROWRESIZE(new EnumConstant(19, "19", "CT_ROWRESIZE")),
        MIDDLEPANNING(new EnumConstant(20, "20", "CT_MIDDLEPANNING")),
        EASTPANNING(new EnumConstant(21, "21", "CT_EASTPANNING")),
        NORTHPANNING(new EnumConstant(22, "22", "CT_NORTHPANNING")),
        NORTHEASTPANNING(new EnumConstant(23, "23", "CT_NORTHEASTPANNING")),
        NORTHWESTPANNING(new EnumConstant(24, "24", "CT_NORTHWESTPANNING")),
        SOUTHPANNING(new EnumConstant(25, "25", "CT_SOUTHPANNING")),
        SOUTHEASTPANNING(new EnumConstant(26, "26", "CT_SOUTHEASTPANNING")),
        SOUTHWESTPANNING(new EnumConstant(27, "27", "CT_SOUTHWESTPANNING")),
        WESTPANNING(new EnumConstant(28, "28", "CT_WESTPANNING")),
        MOVE(new EnumConstant(29, "29", "CT_MOVE")),
        VERTICALTEXT(new EnumConstant(30, "30", "CT_VERTICALTEXT")),
        CELL(new EnumConstant(31, "31", "CT_CELL")),
        CONTEXTMENU(new EnumConstant(32, "32", "CT_CONTEXTMENU")),
        ALIAS(new EnumConstant(33, "33", "CT_ALIAS")),
        PROGRESS(new EnumConstant(34, "34", "CT_PROGRESS")),
        NODROP(new EnumConstant(35, "35", "CT_NODROP")),
        COPY(new EnumConstant(36, "36", "CT_COPY")),
        NONE(new EnumConstant(37, "37", "CT_NONE")),
        NOTALLOWED(new EnumConstant(38, "38", "CT_NOTALLOWED")),
        ZOOMIN(new EnumConstant(39, "39", "CT_ZOOMIN")),
        ZOOMOUT(new EnumConstant(40, "40", "CT_ZOOMOUT")),
        GRAB(new EnumConstant(41, "41", "CT_GRAB")),
        GRABBING(new EnumConstant(42, "42", "CT_GRABBING")),
        MIDDLE_PANNING_VERTICAL(new EnumConstant(43, "43", "CT_MIDDLE_PANNING_VERTICAL")),
        MIDDLE_PANNING_HORIZONTAL(new EnumConstant(44, "44", "CT_MIDDLE_PANNING_HORIZONTAL")),
        CUSTOM(new EnumConstant(45, "45", "CT_CUSTOM")),
        DND_NONE(new EnumConstant(46, "46", "CT_DND_NONE")),
        DND_MOVE(new EnumConstant(47, "47", "CT_DND_MOVE")),
        DND_COPY(new EnumConstant(48, "48", "CT_DND_COPY")),
        DND_LINK(new EnumConstant(49, "49", "CT_DND_LINK")),
        NUM_VALUES(new EnumConstant(50, "50", "CT_NUM_VALUES"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_cursor_type_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
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
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefCursorType of(long v) {
        return new CefCursorType(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefCursorType of(Kind k) {
        return new CefCursorType(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
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
