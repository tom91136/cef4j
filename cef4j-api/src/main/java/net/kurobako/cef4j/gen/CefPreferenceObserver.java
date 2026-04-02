// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

/**
 * Implemented by the client to observe preference changes and registered via
 * {@link CefPreferenceManager#addPreferenceObserver(String, CefPreferenceObserver)}. The methods of this class will be
 * called on the browser process UI thread.
 *
 * <p>Definition generated from cef_preference_capi.h
 *
 * <pre>typedef struct _cef_preference_observer_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_preference_observer_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:70</a>
 */
public interface CefPreferenceObserver extends CefClientHandler {

    /**
     * Called when a preference has changed. The new value can be retrieved using
     * {@link CefPreferenceManager#getPreference(String)}.
     *
     * <p>Definition generated from cef_preference_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_preference_changed)(struct _cef_preference_observer_t* self, const cef_string_t* name);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:78</a>
     */
    default void onPreferenceChanged(@Nullable String name) {}
}
