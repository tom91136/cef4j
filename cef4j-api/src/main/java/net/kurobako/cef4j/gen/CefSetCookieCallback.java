// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Interface to implement to be notified of asynchronous completion via
 * {@link net.kurobako.cef4j.gen.CefCookieManager#setCookie(String, CefCookie, CefSetCookieCallback)}.
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
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefSetCookieCallback extends CefClientHandler {

    /**
     * Method that will be called upon completion. {@code success} will be {@code true} if the cookie was set
     * successfully.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_complete)(struct _cef_set_cookie_callback_t* self, int success);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:157</a>
     */
    default void onComplete(boolean success) {}
}
