// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser requests. The methods of this class will be called on the IO thread unless otherwise indicated.
 * <p>Definition generated from cef_resource_request_handler_capi.h
 * <pre>typedef struct _cef_resource_request_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_request_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:52</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefResourceRequestHandler extends CefClientHandler {

    /**
     * Called on the IO thread before a resource request is loaded. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. To optionally filter cookies for the request return a CefCookieAccessFilter object. The {@code request} object cannot not be modified in this callback.
     * <p>Definition generated from cef_resource_request_handler_capi.h
     * <pre>cef_cookie_access_filter_t* (CEF_CALLBACK* get_cookie_access_filter)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:63</a>
     */
    default Optional<CefCookieAccessFilter> getCookieAccessFilter(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request) {
        return Optional.empty();
    }

    /**
     * Called on the IO thread before a resource request is loaded. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. To redirect or change the resource load optionally modify {@code request}. Modification of the request URL will be treated as a redirect. Return RV_CONTINUE to continue the request immediately. Return RV_CONTINUE_ASYNC and call CefCallback methods at a later time to continue or cancel the request asynchronously. Return RV_CANCEL to cancel the request immediately.
     * <p>Definition generated from cef_resource_request_handler_capi.h
     * <pre>cef_return_value_t (CEF_CALLBACK* on_before_resource_load)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_callback_t* callback);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @return the result, or {@code RV_CONTINUE} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:78</a>
     */
    default @Nullable CefReturnValue onBeforeResourceLoad(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefCallback callback) {
        return CefReturnValue.of(net.kurobako.cef4j.gen.CefReturnValue.Kind.CONTINUE);
    }

    /**
     * Called on the IO thread before a resource is loaded. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. To allow the resource to load using the default network loader return {@code null}. To specify a handler for the resource return a CefResourceHandler object. The {@code request} object cannot not be modified in this callback.
     * <p>Definition generated from cef_resource_request_handler_capi.h
     * <pre>cef_resource_handler_t* (CEF_CALLBACK* get_resource_handler)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:98</a>
     */
    default Optional<CefResourceHandler> getResourceHandler(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request) {
        return Optional.empty();
    }

    /**
     * Called on the IO thread when a resource load is redirected. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. The {@code request} parameter will contain the old URL and other request-related information. The {@code response} parameter will contain the response that resulted in the redirect. The {@code new_url} parameter will contain the new URL and can be changed if desired. The {@code request} and {@code response} objects cannot be modified in this callback.
     * <p>Definition generated from cef_resource_request_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_resource_redirect)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response, cef_string_t* new_url);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:114</a>
     */
    default void onResourceRedirect(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefResponse response, @Nullable String newUrl) {
    }

    /**
     * Called on the IO thread when a resource response is received. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. To allow the resource load to proceed without modification return {@code false}. To redirect or retry the resource load optionally modify {@code request} and return {@code true}. Modification of the request URL will be treated as a redirect. Requests handled using the default network loader cannot be redirected in this callback. The {@code response} object cannot be modified in this callback.
     * <p>
     * <b>WARNING:</b> Redirecting using this method is deprecated. Use OnBeforeResourceLoad or GetResourceHandler to perform redirects.
     * <p>Definition generated from cef_resource_request_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_resource_response)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:131</a>
     */
    default boolean onResourceResponse(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefResponse response) {
        return false;
    }

    /**
     * Called on the IO thread to optionally filter resource response content. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. {@code request} and {@code response} represent the request and response respectively and cannot be modified in this callback.
     * <p>Definition generated from cef_resource_request_handler_capi.h
     * <pre>cef_response_filter_t* (CEF_CALLBACK* get_resource_response_filter)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:152</a>
     */
    default Optional<CefResponseFilter> getResourceResponseFilter(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefResponse response) {
        return Optional.empty();
    }

    /**
     * Called on the IO thread when a resource load has completed. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. {@code request} and {@code response} represent the request and response respectively and cannot be modified in this callback. {@code status} indicates the load completion status. {@code received_content_length} is the number of response bytes actually read. This method will be called for all requests, including requests that are aborted due to CEF shutdown or destruction of the associated browser. In cases where the associated browser is destroyed this callback may arrive after the {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} callback for that browser. The {@link net.kurobako.cef4j.gen.CefFrame#isValid()} method can be used to test for this situation, and care should be taken not to call {@code browser} or {@code frame} methods that modify state (like LoadURL, SendProcessMessage, etc.) if the frame is invalid.
     * <p>Definition generated from cef_resource_request_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_resource_load_complete)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, struct _cef_response_t* response, cef_urlrequest_status_t status, int64_t received_content_length);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:168</a>
     */
    default void onResourceLoadComplete(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefResponse response, @Nonnull CefUrlRequestStatus status, long receivedContentLength) {
    }

    /**
     * Called on the IO thread to handle requests for URLs with an unknown protocol component. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. {@code request} cannot be modified in this callback. Set {@code allow_os_execution} to {@code true} to attempt execution via the registered OS protocol handler, if any. SECURITY WARNING: YOU SHOULD USE THIS METHOD TO ENFORCE RESTRICTIONS BASED ON SCHEME, HOST OR OTHER URL ANALYSIS BEFORE ALLOWING OS EXECUTION.
     * <p>Definition generated from cef_resource_request_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_protocol_execution)(struct _cef_resource_request_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int* allow_os_execution);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__request__handler_8h.html">cef_resource_request_handler.h:192</a>
     */
    default void onProtocolExecution(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, int[] allowOsExecution) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefResourceRequestHandler {
        private final java.util.List<CefResourceRequestHandler> delegates;

        public Delegating(java.util.List<CefResourceRequestHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public Optional<CefCookieAccessFilter> getCookieAccessFilter(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request) {
            java.util.ArrayList<CefCookieAccessFilter> collected = new java.util.ArrayList<>();
            for (CefResourceRequestHandler d : delegates) d.getCookieAccessFilter(browser, frame, request).ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefCookieAccessFilter.Delegating(collected));
        }

        @Override
        public @Nullable CefReturnValue onBeforeResourceLoad(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefCallback callback) {
            if (!delegates.isEmpty()) return delegates.get(0).onBeforeResourceLoad(browser, frame, request, callback);
            return CefReturnValue.of(net.kurobako.cef4j.gen.CefReturnValue.Kind.CONTINUE);
        }

        @Override
        public Optional<CefResourceHandler> getResourceHandler(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request) {
            java.util.ArrayList<CefResourceHandler> collected = new java.util.ArrayList<>();
            for (CefResourceRequestHandler d : delegates) d.getResourceHandler(browser, frame, request).ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefResourceHandler.Delegating(collected));
        }

        @Override
        public void onResourceRedirect(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefResponse response, @Nullable String newUrl) {
            for (CefResourceRequestHandler d : delegates) d.onResourceRedirect(browser, frame, request, response, newUrl);
        }

        @Override
        public boolean onResourceResponse(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefResponse response) {
            for (CefResourceRequestHandler d : delegates) {
                if (d.onResourceResponse(browser, frame, request, response)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public Optional<CefResponseFilter> getResourceResponseFilter(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefResponse response) {
            java.util.ArrayList<CefResponseFilter> collected = new java.util.ArrayList<>();
            for (CefResourceRequestHandler d : delegates) d.getResourceResponseFilter(browser, frame, request, response).ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefResponseFilter.Delegating(collected));
        }

        @Override
        public void onResourceLoadComplete(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, @Nullable CefResponse response, @Nonnull CefUrlRequestStatus status, long receivedContentLength) {
            for (CefResourceRequestHandler d : delegates) d.onResourceLoadComplete(browser, frame, request, response, status, receivedContentLength);
        }

        @Override
        public void onProtocolExecution(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, int[] allowOsExecution) {
            for (CefResourceRequestHandler d : delegates) d.onProtocolExecution(browser, frame, request, allowOsExecution);
        }
    }

}
