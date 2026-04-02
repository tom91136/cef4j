// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Generic callback interface used for asynchronous completion.
 *
 * <p>Definition generated from cef_callback_capi.h
 *
 * <pre>typedef struct _cef_completion_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_completion_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__callback_8h.html">cef_callback.h:62</a>
 */
public interface CefCompletionCallback extends CefClientHandler {

    /**
     * Method that will be called once the task is complete.
     *
     * <p>Definition generated from cef_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_complete)(struct _cef_completion_callback_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__callback_8h.html">cef_callback.h:68</a>
     */
    default void onComplete() {}
}
