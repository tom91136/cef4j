// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

/**
 * Implement this interface to receive accessibility notification when accessibility events have been registered. The
 * methods of this class will be called on the UI thread.
 *
 * <p>Definition generated from cef_accessibility_handler_capi.h
 *
 * <pre>typedef struct _cef_accessibility_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_accessibility_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__accessibility__handler_8h.html">cef_accessibility_handler.h:43</a>
 */
public interface CefAccessibilityHandler extends CefClientHandler {

    /**
     * Called after renderer process sends accessibility tree changes to the browser process.
     *
     * <p>Definition generated from cef_accessibility_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_accessibility_tree_change)(struct _cef_accessibility_handler_t* self, struct _cef_value_t* value);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__accessibility__handler_8h.html">cef_accessibility_handler.h:51</a>
     */
    default void onAccessibilityTreeChange(@Nullable CefValue value) {}

    /**
     * Called after renderer process sends accessibility location changes to the browser process.
     *
     * <p>Definition generated from cef_accessibility_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_accessibility_location_change)(struct _cef_accessibility_handler_t* self, struct _cef_value_t* value);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__accessibility__handler_8h.html">cef_accessibility_handler.h:58</a>
     */
    default void onAccessibilityLocationChange(@Nullable CefValue value) {}
}
