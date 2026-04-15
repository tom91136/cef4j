// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to dragging. The methods of this class will be called on the UI thread.
 * <p>Definition generated from cef_drag_handler_capi.h
 * <pre>typedef struct _cef_drag_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_drag_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__handler_8h.html">cef_drag_handler.h:46</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefDragHandler extends CefClientHandler {

    /**
     * Called when an external drag event enters the browser window. {@code dragData} contains the drag event data and {@code mask} represents the type of drag operation. Return {@code false} for default drag handling behavior or {@code true} to cancel the drag event.
     * <p>Definition generated from cef_drag_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_drag_enter)(struct _cef_drag_handler_t* self, struct _cef_browser_t* browser, struct _cef_drag_data_t* dragData, cef_drag_operations_mask_t mask);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__handler_8h.html">cef_drag_handler.h:55</a>
     */
    default boolean onDragEnter(@Nullable CefBrowser browser, @Nullable CefDragData dragData, @Nonnull CefDragOperationsMask mask) {
        return false;
    }

    /**
     * Called whenever draggable regions for the browser window change. These can be specified using the '-webkit-app-region: drag/no-drag' CSS-property. If draggable regions are never defined in a document this method will also never be called. If the last draggable region is removed from a document this method will be called with an empty vector.
     * <p>Definition generated from cef_drag_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_draggable_regions_changed)(struct _cef_drag_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, size_t regionsCount, cef_draggable_region_t const* regions);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__drag__handler_8h.html">cef_drag_handler.h:68</a>
     */
    default void onDraggableRegionsChanged(@Nullable CefBrowser browser, @Nullable CefFrame frame, long regionsCount, @Nonnull CefDraggableRegion[] regions) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefDragHandler {
        private final java.util.List<CefDragHandler> delegates;

        public Delegating(java.util.List<CefDragHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean onDragEnter(@Nullable CefBrowser browser, @Nullable CefDragData dragData, @Nonnull CefDragOperationsMask mask) {
            for (CefDragHandler d : delegates) {
                if (d.onDragEnter(browser, dragData, mask)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onDraggableRegionsChanged(@Nullable CefBrowser browser, @Nullable CefFrame frame, long regionsCount, @Nonnull CefDraggableRegion[] regions) {
            for (CefDragHandler d : delegates) d.onDraggableRegionsChanged(browser, frame, regionsCount, regions);
        }
    }

}
