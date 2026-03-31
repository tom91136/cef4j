// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Implement this interface to filter cookies that may be sent or received from resource requests. The methods of this
 * class will be called on the IO thread unless otherwise indicated.
 */
public interface CefCookieAccessFilter {

    /**
     * Called on the IO thread before a resource request is sent. The |browser| and |frame| values represent the source
     * of the request, and may be NULL for requests originating from service workers or CefURLRequest. |request| cannot
     * be modified in this callback. Return true if the specified cookie can be sent with the request or false
     * otherwise.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default boolean canSendCookie(long browser, long frame, long request, long cookie) {
        return false;
    }

    /**
     * Called on the IO thread after a resource response is received. The |browser| and |frame| values represent the
     * source of the request, and may be NULL for requests originating from service workers or CefURLRequest. |request|
     * cannot be modified in this callback. Return true if the specified cookie returned with the response can be saved
     * or false otherwise.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default boolean canSaveCookie(long browser, long frame, long request, long response, long cookie) {
        return false;
    }
}
