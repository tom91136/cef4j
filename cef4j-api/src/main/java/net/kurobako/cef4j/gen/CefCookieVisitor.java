// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Interface to implement for visiting cookie values. The methods of this class will always be called on the UI thread.
 *
 * <p>Definition generated from cef_cookie_capi.h
 *
 * <pre>typedef struct _cef_cookie_visitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_cookie_visitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__cookie_8h.html">cef_cookie.h:129</a>
 */
public interface CefCookieVisitor extends CefClientHandler {

    /**
     * Method executed for visiting the DOM. The document object passed to this method represents a snapshot of the DOM
     * at the time this method is executed. DOM objects are only valid for the scope of this method. Do not keep
     * references to or attempt to access any DOM objects outside the scope of this method.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* visit)(struct _cef_cookie_visitor_t* self, const struct _cef_cookie_t* cookie, int count, int total, int* deleteCookie);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:55</a>
     */
    default int visit(@Nonnull CefCookie cookie, int count, int total, int[] deletecookie) {
        return 0;
    }
}
