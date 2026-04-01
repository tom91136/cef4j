// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events when window rendering is disabled. The methods of this class will be called
 * on the UI thread.
 *
 * <p>Definition generated from cef_render_handler_capi.h
 *
 * <pre>typedef struct _cef_render_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_render_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:48</a>
 */
public interface CefRenderHandler extends CefClientHandler {

    /**
     * Return the handler for accessibility notifications. If no handler is provided the default implementation will be
     * used.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>cef_accessibility_handler_t* (CEF_CALLBACK* get_accessibility_handler)(struct _cef_render_handler_t* self);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:61</a>
     */
    default Optional<CefAccessibilityHandler> getAccessibilityHandler() {
        return Optional.empty();
    }

    /**
     * Called to retrieve the root window rectangle in screen DIP coordinates. Return {@code true} if the rectangle was
     * provided. If this method returns {@code false} the rectangle from GetViewRect will be used.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_root_screen_rect)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, cef_rect_t* rect);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:70</a>
     */
    default boolean getRootScreenRect(@Nonnull CefBrowser browser, @Nonnull CefMutableRect rect) {
        return false;
    }

    /**
     * Called to retrieve the view rectangle in screen DIP coordinates. This method must always provide a non-empty
     * rectangle.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* get_view_rect)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, cef_rect_t* rect);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:80</a>
     */
    default void getViewRect(@Nonnull CefBrowser browser, @Nonnull CefMutableRect rect) {}

    /**
     * Called to retrieve the translation from view DIP coordinates to screen coordinates. Windows/Linux should provide
     * screen device (pixel) coordinates and MacOS should provide screen DIP coordinates. Return {@code true} if the
     * requested coordinates were provided.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_screen_point)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, int viewX, int viewY, int* screenX, int* screenY);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:87</a>
     */
    default boolean getScreenPoint(@Nonnull CefBrowser browser, int viewx, int viewy, int[] screenx, int[] screeny) {
        return false;
    }

    /**
     * Called to allow the client to fill in the CefScreenInfo object with appropriate values. Return {@code true} if
     * the {@code screen_info} structure has been modified.
     *
     * <p>If the screen info rectangle is left empty the rectangle from GetViewRect will be used. If the rectangle is
     * still empty or invalid popups may not be drawn correctly.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_screen_info)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, cef_screen_info_t* screen_info);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:102</a>
     */
    default boolean getScreenInfo(@Nonnull CefBrowser browser, @Nonnull CefMutableScreenInfo screenInfo) {
        return false;
    }

    /**
     * Called when the browser wants to show or hide the popup widget. The popup should be shown if {@code show} is
     * {@code true} and hidden if {@code show} is {@code false}.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_popup_show)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, int show);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:117</a>
     */
    default void onPopupShow(@Nonnull CefBrowser browser, boolean show) {}

    /**
     * Called when the browser wants to move or resize the popup widget. {@code rect} contains the new location and size
     * in view coordinates.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_popup_size)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, const cef_rect_t* rect);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:124</a>
     */
    default void onPopupSize(@Nonnull CefBrowser browser, @Nonnull CefRect rect) {}

    /**
     * Called when an element should be painted. Pixel values passed to this method are scaled relative to view
     * coordinates based on the value of CefScreenInfo.device_scale_factor returned from GetScreenInfo. {@code type}
     * indicates whether the element is the view or the popup widget. {@code buffer} contains the pixel data for the
     * whole image. {@code dirtyRects} contains the set of rectangles in pixel coordinates that need to be repainted.
     * {@code buffer} will be {@code width}*{@code height}*4 bytes in size and represents a BGRA image with an
     * upper-left origin. This method is only called when CefWindowInfo.sharedTextureEnabled() is set to {@code false}.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_paint)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, cef_paint_element_type_t type, size_t dirtyRectsCount, cef_rect_t const* dirtyRects, const void* buffer, int width, int height);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:132</a>
     */
    default void onPaint(
            @Nonnull CefBrowser browser,
            @Nonnull CefPaintElementType type,
            long dirtyrectscount,
            @Nonnull CefRect[] dirtyrects,
            @Nonnull ByteBuffer buffer,
            int width,
            int height) {}

    /**
     * Called when an element has been rendered to the shared texture handle. {@code type} indicates whether the element
     * is the view or the popup widget. {@code dirtyRects} contains the set of rectangles in pixel coordinates that need
     * to be repainted. {@code info} contains the shared handle; on Windows it is a HANDLE to a texture that can be
     * opened with D3D11 OpenSharedResource1 or D3D12 OpenSharedHandle, on macOS it is an IOSurface pointer that can be
     * opened with Metal or OpenGL, and on Linux it contains several planes, each with an fd to the underlying system
     * native buffer.
     *
     * <p>The underlying implementation uses a pool to deliver frames. As a result, the handle may differ every frame
     * depending on how many frames are in-progress. The handle's resource cannot be cached and cannot be accessed
     * outside of this callback. It should be reopened each time this callback is executed and the contents should be
     * copied to a texture owned by the client application. The contents of {@code info} will be released back to the
     * pool after this callback returns.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_accelerated_paint)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, cef_paint_element_type_t type, size_t dirtyRectsCount, cef_rect_t const* dirtyRects, const cef_accelerated_paint_info_t* info);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:151</a>
     */
    default void onAcceleratedPaint(
            @Nonnull CefBrowser browser,
            @Nonnull CefPaintElementType type,
            long dirtyrectscount,
            @Nonnull CefRect[] dirtyrects,
            @Nonnull NativePointer info) {}

    /**
     * Called to retrieve the size of the touch handle for the specified {@code orientation}.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* get_touch_handle_size)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, cef_horizontal_alignment_t orientation, cef_size_t* size);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:175</a>
     */
    default void getTouchHandleSize(
            @Nonnull CefBrowser browser, @Nonnull CefHorizontalAlignment orientation, @Nonnull CefMutableSize size) {}

    /**
     * Called when touch handle state is updated. The client is responsible for rendering the touch handles.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_touch_handle_state_changed)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, const cef_touch_handle_state_t* state);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:184</a>
     */
    default void onTouchHandleStateChanged(@Nonnull CefBrowser browser, @Nonnull CefTouchHandleState state) {}

    /**
     * Called when the user starts dragging content in the web view. Contextual information about the dragged content is
     * supplied by {@code drag_data}. ({@code x}, {@code y}) is the drag start location in screen coordinates. OS APIs
     * that run a system message loop may be used within the StartDragging call.
     *
     * <p>Return {@code false} to abort the drag operation. Don't call any of CefBrowserHost.dragSource()*Ended* methods
     * after returning {@code false}.
     *
     * <p>Return {@code true} to handle the drag operation. Call {@link CefBrowserHost#dragSourceEndedAt(int, int,
     * CefDragOperationsMask)} and DragSourceSystemDragEnded either synchronously or asynchronously to inform the web
     * view that the drag operation has ended.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* start_dragging)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, struct _cef_drag_data_t* drag_data, cef_drag_operations_mask_t allowed_ops, int x, int y);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:192</a>
     */
    default boolean startDragging(
            @Nonnull CefBrowser browser,
            @Nonnull CefDragData dragData,
            @Nonnull CefDragOperationsMask allowedOps,
            int x,
            int y) {
        return false;
    }

    /**
     * Called when the web view wants to update the mouse cursor during a drag & drop operation. {@code operation}
     * describes the allowed operation (none, move, copy, link).
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* update_drag_cursor)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, cef_drag_operations_mask_t operation);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:216</a>
     */
    default void updateDragCursor(@Nonnull CefBrowser browser, @Nonnull CefDragOperationsMask operation) {}

    /**
     * Called when the scroll offset has changed.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_scroll_offset_changed)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, double x, double y);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:225</a>
     */
    default void onScrollOffsetChanged(@Nonnull CefBrowser browser, double x, double y) {}

    /**
     * Called when the IME composition range has changed. {@code selected_range} is the range of characters that have
     * been selected. {@code character_bounds} is the bounds of each character in view coordinates.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_ime_composition_range_changed)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, const cef_range_t* selected_range, size_t character_boundsCount, cef_rect_t const* character_bounds);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:233</a>
     */
    default void onImeCompositionRangeChanged(
            @Nonnull CefBrowser browser,
            @Nonnull CefRange selectedRange,
            long characterBoundsCount,
            @Nonnull CefRect[] characterBounds) {}

    /**
     * Called when text selection has changed for the specified {@code browser}. {@code selected_text} is the currently
     * selected text and {@code selected_range} is the character range.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_text_selection_changed)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* selected_text, const cef_range_t* selected_range);
     * </pre>
     *
     * @param selectedText may be null
     * @param selectedRange may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:243</a>
     */
    default void onTextSelectionChanged(
            @Nonnull CefBrowser browser, @Nullable String selectedText, @Nullable CefRange selectedRange) {}

    /**
     * Called when an on-screen keyboard should be shown or hidden for the specified {@code browser}. {@code input_mode}
     * specifies what kind of keyboard should be opened. If {@code input_mode} is {@link CefTextInputMode.Kind#NONE},
     * any existing keyboard for this browser should be hidden.
     *
     * <p>Definition generated from cef_render_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_virtual_keyboard_requested)(struct _cef_render_handler_t* self, struct _cef_browser_t* browser, cef_text_input_mode_t input_mode);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__handler_8h.html">cef_render_handler.h:253</a>
     */
    default void onVirtualKeyboardRequested(@Nonnull CefBrowser browser, @Nonnull CefTextInputMode inputMode) {}
}
