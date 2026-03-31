// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * "Verb" of a drag-and-drop operation as negotiated between the source and destination. These constants match their
 * equivalents in WebCore's DragActions.h and should not be renumbered.
 */
public enum CefDragOperationsMask {
    DRAG_OPERATION_NONE(0L),
    DRAG_OPERATION_COPY(1L),
    DRAG_OPERATION_LINK(2L),
    DRAG_OPERATION_GENERIC(4L),
    DRAG_OPERATION_PRIVATE(8L),
    DRAG_OPERATION_MOVE(16L),
    DRAG_OPERATION_DELETE(32L),
    UNKNOWN(-1L);

    public final long value;

    CefDragOperationsMask(long v) {
        this.value = v;
    }

    public static CefDragOperationsMask fromLong(long v) {
        for (CefDragOperationsMask e : values()) {
            if (e.value == v) return e;
        }
        return UNKNOWN;
    }
}
