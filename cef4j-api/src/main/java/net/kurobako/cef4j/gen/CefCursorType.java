// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Cursor type values. */
public enum CefCursorType {
    CT_POINTER(0L),
    CT_CROSS(1L),
    CT_HAND(2L),
    CT_IBEAM(3L),
    CT_WAIT(4L),
    CT_HELP(5L),
    CT_EASTRESIZE(6L),
    CT_NORTHRESIZE(7L),
    CT_NORTHEASTRESIZE(8L),
    CT_NORTHWESTRESIZE(9L),
    CT_SOUTHRESIZE(10L),
    CT_SOUTHEASTRESIZE(11L),
    CT_SOUTHWESTRESIZE(12L),
    CT_WESTRESIZE(13L),
    CT_NORTHSOUTHRESIZE(14L),
    CT_EASTWESTRESIZE(15L),
    CT_NORTHEASTSOUTHWESTRESIZE(16L),
    CT_NORTHWESTSOUTHEASTRESIZE(17L),
    CT_COLUMNRESIZE(18L),
    CT_ROWRESIZE(19L),
    CT_MIDDLEPANNING(20L),
    CT_EASTPANNING(21L),
    CT_NORTHPANNING(22L),
    CT_NORTHEASTPANNING(23L),
    CT_NORTHWESTPANNING(24L),
    CT_SOUTHPANNING(25L),
    CT_SOUTHEASTPANNING(26L),
    CT_SOUTHWESTPANNING(27L),
    CT_WESTPANNING(28L),
    CT_MOVE(29L),
    CT_VERTICALTEXT(30L),
    CT_CELL(31L),
    CT_CONTEXTMENU(32L),
    CT_ALIAS(33L),
    CT_PROGRESS(34L),
    CT_NODROP(35L),
    CT_COPY(36L),
    CT_NONE(37L),
    CT_NOTALLOWED(38L),
    CT_ZOOMIN(39L),
    CT_ZOOMOUT(40L),
    CT_GRAB(41L),
    CT_GRABBING(42L),
    CT_MIDDLE_PANNING_VERTICAL(43L),
    CT_MIDDLE_PANNING_HORIZONTAL(44L),
    CT_CUSTOM(45L),
    CT_DND_NONE(46L),
    CT_DND_MOVE(47L),
    CT_DND_COPY(48L),
    CT_DND_LINK(49L),
    CT_NUM_VALUES(50L),
    UNKNOWN(-1L);

    public final long value;

    CefCursorType(long v) {
        this.value = v;
    }

    public static CefCursorType fromLong(long v) {
        for (CefCursorType e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
