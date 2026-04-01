// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implemented by the client to observe content and website setting changes and registered via
 * {@link CefRequestContext#addSettingObserver(CefSettingObserver)}. The methods of this class will be called on the
 * browser process UI thread.
 *
 * <p>Definition generated from cef_request_context_capi.h
 *
 * <pre>typedef struct _cef_setting_observer_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_setting_observer_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:71</a>
 */
public interface CefSettingObserver extends CefClientHandler {

    /**
     * Called when a content or website setting has changed. The new value can be retrieved using
     * {@link CefRequestContext#getContentSetting(String, String, CefContentSettingTypes)} or
     * {@link CefRequestContext#getWebsiteSetting(String, String, CefContentSettingTypes)}.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_setting_changed)(struct _cef_setting_observer_t* self, const cef_string_t* requesting_url, const cef_string_t* top_level_url, cef_content_setting_types_t content_type);
     * </pre>
     *
     * @param requestingUrl may be null
     * @param topLevelUrl may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:79</a>
     */
    default void onSettingChanged(
            @Nullable String requestingUrl,
            @Nullable String topLevelUrl,
            @Nonnull CefContentSettingTypes contentType) {}
}
