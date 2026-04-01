// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Callback interface for component update results.
 *
 * <p>Definition generated from cef_component_updater_capi.h
 *
 * <pre>typedef struct _cef_component_update_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_component_update_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__component__updater_8h.html">cef_component_updater.h:49</a>
 */
public interface CefComponentUpdateCallback extends CefClientHandler {

    /**
     * Method that will be called upon completion. {@code num_deleted} will be the number of cookies that were deleted.
     *
     * <p>Definition generated from cef_component_updater_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_complete)(struct _cef_component_update_callback_t* self, const cef_string_t* component_id, cef_component_update_error_t error);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:172</a>
     */
    default void onComplete(@Nonnull String componentId, @Nonnull CefComponentUpdateError error) {}
}
