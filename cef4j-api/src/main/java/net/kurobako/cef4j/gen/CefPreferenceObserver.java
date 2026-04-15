// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Implemented by the client to observe preference changes and registered via {@link net.kurobako.cef4j.gen.CefPreferenceManager#addPreferenceObserver(String, CefPreferenceObserver)}. The methods of this class will be called on the browser process UI thread.
 * <p>Definition generated from cef_preference_capi.h
 * <pre>typedef struct _cef_preference_observer_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_preference_observer_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:70</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefPreferenceObserver extends CefClientHandler {

    /**
     * Called when a preference has changed. The new value can be retrieved using {@link net.kurobako.cef4j.gen.CefPreferenceManager#getPreference(String)}.
     * <p>Definition generated from cef_preference_capi.h
     * <pre>void (CEF_CALLBACK* on_preference_changed)(struct _cef_preference_observer_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__preference_8h.html">cef_preference.h:78</a>
     */
    default void onPreferenceChanged(@Nullable String name) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefPreferenceObserver {
        private final java.util.List<CefPreferenceObserver> delegates;

        public Delegating(java.util.List<CefPreferenceObserver> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onPreferenceChanged(@Nullable String name) {
            for (CefPreferenceObserver d : delegates) d.onPreferenceChanged(name);
        }
    }

}
