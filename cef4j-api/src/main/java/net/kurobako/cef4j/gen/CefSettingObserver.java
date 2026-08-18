// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implemented by the client to observe content and website setting changes and registered via {@link net.kurobako.cef4j.gen.CefRequestContext#addSettingObserver(CefSettingObserver)}. The methods of this class will be called on the browser process UI thread.
 * <p>Definition generated from cef_request_context_capi.h
 * <pre>typedef struct _cef_setting_observer_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_setting_observer_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request__context_8h.html">cef_request_context.h:71</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefSettingObserver extends CefClientHandler {

    /**
     * Called when a content or website setting has changed. The new value can be retrieved using {@link net.kurobako.cef4j.gen.CefRequestContext#getContentSetting(String, String, CefContentSettingTypes)} or {@link net.kurobako.cef4j.gen.CefRequestContext#getWebsiteSetting(String, String, CefContentSettingTypes)}.
     * <p>Definition generated from cef_request_context_capi.h
     * <pre>void (CEF_CALLBACK* on_setting_changed)(struct _cef_setting_observer_t* self, const cef_string_t* requesting_url, const cef_string_t* top_level_url, cef_content_setting_types_t content_type);</pre>
     *
     * @param requestingUrl may be null
     * @param topLevelUrl may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request__context_8h.html">cef_request_context.h:79</a>
     */
    default void onSettingChanged(@Nullable String requestingUrl, @Nullable String topLevelUrl, @Nonnull CefContentSettingTypes contentType) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefSettingObserver {
        private final java.util.List<CefSettingObserver> delegates;

        public Delegating(java.util.List<CefSettingObserver> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onSettingChanged(@Nullable String requestingUrl, @Nullable String topLevelUrl, @Nonnull CefContentSettingTypes contentType) {
            for (CefSettingObserver d : delegates) d.onSettingChanged(requestingUrl, topLevelUrl, contentType);
        }
    }

}
