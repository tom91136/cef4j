// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to filter cookies that may be sent or received from resource requests. The methods of this
 * class will be called on the IO thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_resource_request_handler_capi.h
 *
 * <pre>typedef struct _cef_cookie_access_filter_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_cookie_access_filter_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:209</a>
 */
public interface CefCookieAccessFilter extends CefClientHandler {

    /**
     * Called on the IO thread before a resource request is sent. The {@code browser} and {@code frame} values represent
     * the source of the request, and may be {@code null} for requests originating from service workers or
     * CefURLRequest. {@code request} cannot be modified in this callback. Return {@code true} if the specified cookie
     * can be sent with the request or {@code false} otherwise.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* can_send_cookie)(struct _cef_cookie_access_filter_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, const struct _cef_cookie_t* cookie);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:217</a>
     */
    default boolean canSendCookie(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nullable CefRequest request,
            @Nonnull CefCookie cookie) {
        return false;
    }

    /**
     * Called on the IO thread after a resource response is received. The {@code browser} and {@code frame} values
     * represent the source of the request, and may be {@code null} for requests originating from service workers or
     * CefURLRequest. {@code request} cannot be modified in this callback. Return {@code true} if the specified cookie
     * returned with the response can be saved or {@code false} otherwise.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* can_save_cookie)(struct _cef_cookie_access_filter_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response, const struct _cef_cookie_t* cookie);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:232</a>
     */
    default boolean canSaveCookie(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nullable CefRequest request,
            @Nullable CefResponse response,
            @Nonnull CefCookie cookie) {
        return false;
    }
}
