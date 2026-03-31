// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle events related to dragging. The methods of this class will be called on the UI
 * thread.
 */
public interface CefDragHandler {

    /**
     * Called when an external drag event enters the browser window. |dragData| contains the drag event data and |mask|
     * represents the type of drag operation. Return false for default drag handling behavior or true to cancel the drag
     * event.
     */
    default boolean onDragEnter(long browser, long dragData, @Nonnull CefDragOperationsMask mask) {
        return false;
    }

    /**
     * Called whenever draggable regions for the browser window change. These can be specified using the
     * '-webkit-app-region: drag/no-drag' CSS-property. If draggable regions are never defined in a document this method
     * will also never be called. If the last draggable region is removed from a document this method will be called
     * with an empty vector.
     */
    default void onDraggableRegionsChanged(long browser, long frame, long regionsCount, long regions) {}
}
