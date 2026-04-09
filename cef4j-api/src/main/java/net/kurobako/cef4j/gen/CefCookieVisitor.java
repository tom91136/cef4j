// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;

/**
 * Interface to implement for visiting cookie values. The methods of this class will always be called on the UI thread.
 * <p>Definition generated from cef_cookie_capi.h
 * <pre>typedef struct _cef_cookie_visitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_cookie_visitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:129</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefCookieVisitor extends CefClientHandler {

    /**
     * Method that will be called once for each cookie. {@code count} is the 0-based index for the current cookie. {@code total} is the total number of cookies. Set {@code deleteCookie} to {@code true} to delete the cookie currently being visited. Return {@code false} to stop visiting cookies. This method may never be called if no cookies are found.
     * <p>Definition generated from cef_cookie_capi.h
     * <pre>int (CEF_CALLBACK* visit)(struct _cef_cookie_visitor_t* self, const struct _cef_cookie_t* cookie, int count, int total, int* deleteCookie);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:136</a>
     */
    default boolean visit(@Nonnull CefCookie cookie, int count, int total, int[] deleteCookie) {
        return false;
    }
}
