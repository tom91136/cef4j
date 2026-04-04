// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Interface to implement to be notified of asynchronous completion via
 * {@link net.kurobako.cef4j.gen.CefCookieManager#deleteCookies(String, String, CefDeleteCookiesCallback)}.
 *
 * <p>Definition generated from cef_cookie_capi.h
 *
 * <pre>typedef struct _cef_delete_cookies_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_delete_cookies_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:165</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefDeleteCookiesCallback extends CefClientHandler {

    /**
     * Method that will be called upon completion. {@code num_deleted} will be the number of cookies that were deleted.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_complete)(struct _cef_delete_cookies_callback_t* self, int num_deleted);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:172</a>
     */
    default void onComplete(int numDeleted) {}
}
