// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Interface to implement to be notified of asynchronous completion via {@link CefCookieManager#setCookie(String,
 * CefCookie, CefSetCookieCallback)}.
 *
 * <p>Definition generated from cef_cookie_capi.h
 *
 * <pre>typedef struct _cef_set_cookie_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_set_cookie_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:150</a>
 */
public interface CefSetCookieCallback extends CefClientHandler {

    /**
     * Method that will be called upon completion. {@code num_deleted} will be the number of cookies that were deleted.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_complete)(struct _cef_set_cookie_callback_t* self, int success);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:172</a>
     */
    default void onComplete(int success) {}
}
