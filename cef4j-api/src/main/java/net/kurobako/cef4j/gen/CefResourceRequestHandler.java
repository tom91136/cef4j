// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser requests. The methods of this class will be called on
 * the IO thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_resource_request_handler_capi.h
 *
 * <pre>typedef struct _cef_resource_request_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_request_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:52</a>
 */
public interface CefResourceRequestHandler extends CefClientHandler {

    /**
     * Called on the IO thread before a resource request is loaded. The {@code browser} and {@code frame} values
     * represent the source of the request, and may be {@code null} for requests originating from service workers or
     * CefURLRequest. To optionally filter cookies for the request return a CefCookieAccessFilter object. The
     * {@code request} object cannot not be modified in this callback.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * cef_cookie_access_filter_t* (CEF_CALLBACK* get_cookie_access_filter)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:63</a>
     */
    default Optional<CefCookieAccessFilter> getCookieAccessFilter(
            @Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefRequest request) {
        return Optional.empty();
    }

    /**
     * Called on the IO thread before a resource request is loaded. The {@code browser} and {@code frame} values
     * represent the source of the request, and may be {@code null} for requests originating from service workers or
     * CefURLRequest. To redirect or change the resource load optionally modify {@code request}. Modification of the
     * request URL will be treated as a redirect. Return RV_CONTINUE to continue the request immediately. Return
     * RV_CONTINUE_ASYNC and call CefCallback methods at a later time to continue or cancel the request asynchronously.
     * Return RV_CANCEL to cancel the request immediately.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * cef_return_value_t (CEF_CALLBACK* on_before_resource_load)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_callback_t* callback);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @return the result, or {@code RV_CONTINUE} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:78</a>
     */
    default CefReturnValue onBeforeResourceLoad(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nonnull CefRequest request,
            @Nonnull CefCallback callback) {
        return CefReturnValue.of(CefReturnValue.Kind.CONTINUE);
    }

    /**
     * Called on the IO thread before a resource is loaded. The {@code browser} and {@code frame} values represent the
     * source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. To
     * allow the resource to load using the default network loader return {@code null}. To specify a handler for the
     * resource return a CefResourceHandler object. The {@code request} object cannot not be modified in this callback.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * cef_resource_handler_t* (CEF_CALLBACK* get_resource_handler)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:98</a>
     */
    default Optional<CefResourceHandler> getResourceHandler(
            @Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefRequest request) {
        return Optional.empty();
    }

    /**
     * Called on the IO thread when a resource load is redirected. The {@code browser} and {@code frame} values
     * represent the source of the request, and may be {@code null} for requests originating from service workers or
     * CefURLRequest. The {@code request} parameter will contain the old URL and other request-related information. The
     * {@code response} parameter will contain the response that resulted in the redirect. The {@code new_url} parameter
     * will contain the new URL and can be changed if desired. The {@code request} and {@code response} objects cannot
     * be modified in this callback.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_resource_redirect)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response, cef_string_t* new_url);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:114</a>
     */
    default void onResourceRedirect(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nonnull CefRequest request,
            @Nonnull CefResponse response,
            @Nonnull String newUrl) {}

    /**
     * Called on the IO thread when a resource response is received. The {@code browser} and {@code frame} values
     * represent the source of the request, and may be {@code null} for requests originating from service workers or
     * CefURLRequest. To allow the resource load to proceed without modification return {@code false}. To redirect or
     * retry the resource load optionally modify {@code request} and return {@code true}. Modification of the request
     * URL will be treated as a redirect. Requests handled using the default network loader cannot be redirected in this
     * callback. The {@code response} object cannot be modified in this callback.
     *
     * <p><b>WARNING:</b> Redirecting using this method is deprecated. Use OnBeforeResourceLoad or GetResourceHandler to
     * perform redirects.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_resource_response)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:131</a>
     */
    default boolean onResourceResponse(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nonnull CefRequest request,
            @Nonnull CefResponse response) {
        return false;
    }

    /**
     * Called on the IO thread to optionally filter resource response content. The {@code browser} and {@code frame}
     * values represent the source of the request, and may be {@code null} for requests originating from service workers
     * or CefURLRequest. {@code request} and {@code response} represent the request and response respectively and cannot
     * be modified in this callback.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * cef_response_filter_t* (CEF_CALLBACK* get_resource_response_filter)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:152</a>
     */
    default Optional<CefResponseFilter> getResourceResponseFilter(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nonnull CefRequest request,
            @Nonnull CefResponse response) {
        return Optional.empty();
    }

    /**
     * Called on the IO thread when a resource load has completed. The {@code browser} and {@code frame} values
     * represent the source of the request, and may be {@code null} for requests originating from service workers or
     * CefURLRequest. {@code request} and {@code response} represent the request and response respectively and cannot be
     * modified in this callback. {@code status} indicates the load completion status. {@code received_content_length}
     * is the number of response bytes actually read. This method will be called for all requests, including requests
     * that are aborted due to CEF shutdown or destruction of the associated browser. In cases where the associated
     * browser is destroyed this callback may arrive after the {@link CefLifeSpanHandler#onBeforeClose(CefBrowser)}
     * callback for that browser. The {@link CefFrame#isValid()} method can be used to test for this situation, and care
     * should be taken not to call {@code browser} or {@code frame} methods that modify state (like LoadURL,
     * SendProcessMessage, etc.) if the frame is invalid.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_resource_load_complete)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response, cef_urlrequest_status_t status, int64_t received_content_length);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:168</a>
     */
    default void onResourceLoadComplete(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nonnull CefRequest request,
            @Nonnull CefResponse response,
            @Nonnull CefUrlRequestStatus status,
            long receivedContentLength) {}

    /**
     * Called on the IO thread to handle requests for URLs with an unknown protocol component. The {@code browser} and
     * {@code frame} values represent the source of the request, and may be {@code null} for requests originating from
     * service workers or CefURLRequest. {@code request} cannot be modified in this callback. Set
     * {@code allow_os_execution} to {@code true} to attempt execution via the registered OS protocol handler, if any.
     * SECURITY WARNING: YOU SHOULD USE THIS METHOD TO ENFORCE RESTRICTIONS BASED ON SCHEME, HOST OR OTHER URL ANALYSIS
     * BEFORE ALLOWING OS EXECUTION.
     *
     * <p>Definition generated from cef_resource_request_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_protocol_execution)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int* allow_os_execution);
     * </pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:192</a>
     */
    default void onProtocolExecution(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nonnull CefRequest request,
            int[] allowOsExecution) {}
}
