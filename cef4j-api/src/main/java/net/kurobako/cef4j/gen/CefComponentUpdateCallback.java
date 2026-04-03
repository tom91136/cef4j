// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

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
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefComponentUpdateCallback extends CefClientHandler {

    /**
     * Called when the component update operation completes. {@code component_id} is the ID of the component that was
     * updated. {@code error} contains the result of the operation.
     *
     * <p>Definition generated from cef_component_updater_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_complete)(struct _cef_component_update_callback_t* self, const cef_string_t* component_id, cef_component_update_error_t error);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__component__updater_8h.html">cef_component_updater.h:55</a>
     */
    default void onComplete(@Nullable String componentId, @Nonnull CefComponentUpdateError error) {}
}
