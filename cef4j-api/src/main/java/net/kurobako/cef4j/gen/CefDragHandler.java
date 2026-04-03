// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Implement this interface to handle events related to dragging. The methods of this class will be called on the UI
 * thread.
 *
 * <p>Definition generated from cef_drag_handler_capi.h
 *
 * <pre>typedef struct _cef_drag_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_drag_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__handler_8h.html">cef_drag_handler.h:46</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefDragHandler extends CefClientHandler {

    /**
     * Called when an external drag event enters the browser window. {@code dragData} contains the drag event data and
     * {@code mask} represents the type of drag operation. Return {@code false} for default drag handling behavior or
     * {@code true} to cancel the drag event.
     *
     * <p>Definition generated from cef_drag_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_drag_enter)(struct _cef_drag_handler_t* self, struct _cef_browser_t* browser, struct _cef_drag_data_t* dragData, cef_drag_operations_mask_t mask);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__handler_8h.html">cef_drag_handler.h:55</a>
     */
    default boolean onDragEnter(
            @Nullable CefBrowser browser, @Nullable CefDragData dragData, @Nonnull CefDragOperationsMask mask) {
        return false;
    }

    /**
     * Called whenever draggable regions for the browser window change. These can be specified using the
     * '-webkit-app-region: drag/no-drag' CSS-property. If draggable regions are never defined in a document this method
     * will also never be called. If the last draggable region is removed from a document this method will be called
     * with an empty vector.
     *
     * <p>Definition generated from cef_drag_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_draggable_regions_changed)(struct _cef_drag_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, size_t regionsCount, cef_draggable_region_t const* regions);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__handler_8h.html">cef_drag_handler.h:68</a>
     */
    default void onDraggableRegionsChanged(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            long regionsCount,
            @Nullable NativePointer regions) {}
}
