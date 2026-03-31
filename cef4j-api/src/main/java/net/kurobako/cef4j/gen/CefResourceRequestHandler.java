// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle events related to browser requests. The methods of this class will be called on
 * the IO thread unless otherwise indicated.
 */
public interface CefResourceRequestHandler {

    /**
     * Called on the IO thread before a resource request is loaded. The |browser| and |frame| values represent the
     * source of the request, and may be NULL for requests originating from service workers or CefURLRequest. To
     * optionally filter cookies for the request return a CefCookieAccessFilter object. The |request| object cannot not
     * be modified in this callback.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default long getCookieAccessFilter(long browser, long frame, long request) {
        return 0L;
    }

    /**
     * Called on the IO thread before a resource request is loaded. The |browser| and |frame| values represent the
     * source of the request, and may be NULL for requests originating from service workers or CefURLRequest. To
     * redirect or change the resource load optionally modify |request|. Modification of the request URL will be treated
     * as a redirect. Return RV_CONTINUE to continue the request immediately. Return RV_CONTINUE_ASYNC and call
     * CefCallback methods at a later time to continue or cancel the request asynchronously. Return RV_CANCEL to cancel
     * the request immediately.
     *
     * @param browser may be null
     * @param frame may be null
     * @return the result, or {@code RV_CONTINUE} for default handling
     */
    default CefReturnValue onBeforeResourceLoad(long browser, long frame, long request, long callback) {
        return CefReturnValue.RV_CONTINUE;
    }

    /**
     * Called on the IO thread before a resource is loaded. The |browser| and |frame| values represent the source of the
     * request, and may be NULL for requests originating from service workers or CefURLRequest. To allow the resource to
     * load using the default network loader return NULL. To specify a handler for the resource return a
     * CefResourceHandler object. The |request| object cannot not be modified in this callback.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default long getResourceHandler(long browser, long frame, long request) {
        return 0L;
    }

    /**
     * Called on the IO thread when a resource load is redirected. The |browser| and |frame| values represent the source
     * of the request, and may be NULL for requests originating from service workers or CefURLRequest. The |request|
     * parameter will contain the old URL and other request-related information. The |response| parameter will contain
     * the response that resulted in the redirect. The |new_url| parameter will contain the new URL and can be changed
     * if desired. The |request| and |response| objects cannot be modified in this callback.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default void onResourceRedirect(long browser, long frame, long request, long response, @Nonnull String newUrl) {}

    /**
     * Called on the IO thread when a resource response is received. The |browser| and |frame| values represent the
     * source of the request, and may be NULL for requests originating from service workers or CefURLRequest. To allow
     * the resource load to proceed without modification return false. To redirect or retry the resource load optionally
     * modify |request| and return true. Modification of the request URL will be treated as a redirect. Requests handled
     * using the default network loader cannot be redirected in this callback. The |response| object cannot be modified
     * in this callback. WARNING: Redirecting using this method is deprecated. Use OnBeforeResourceLoad or
     * GetResourceHandler to perform redirects.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default boolean onResourceResponse(long browser, long frame, long request, long response) {
        return false;
    }

    /**
     * Called on the IO thread to optionally filter resource response content. The |browser| and |frame| values
     * represent the source of the request, and may be NULL for requests originating from service workers or
     * CefURLRequest. |request| and |response| represent the request and response respectively and cannot be modified in
     * this callback.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default long getResourceResponseFilter(long browser, long frame, long request, long response) {
        return 0L;
    }

    /**
     * Called on the IO thread when a resource load has completed. The |browser| and |frame| values represent the source
     * of the request, and may be NULL for requests originating from service workers or CefURLRequest. |request| and
     * |response| represent the request and response respectively and cannot be modified in this callback. |status|
     * indicates the load completion status. |received_content_length| is the number of response bytes actually read.
     * This method will be called for all requests, including requests that are aborted due to CEF shutdown or
     * destruction of the associated browser. In cases where the associated browser is destroyed this callback may
     * arrive after the CefLifeSpanHandler::OnBeforeClose callback for that browser. The CefFrame::IsValid method can be
     * used to test for this situation, and care should be taken not to call |browser| or |frame| methods that modify
     * state (like LoadURL, SendProcessMessage, etc.) if the frame is invalid.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default void onResourceLoadComplete(
            long browser,
            long frame,
            long request,
            long response,
            @Nonnull CefUrlrequestStatus status,
            long receivedContentLength) {}

    /**
     * Called on the IO thread to handle requests for URLs with an unknown protocol component. The |browser| and |frame|
     * values represent the source of the request, and may be NULL for requests originating from service workers or
     * CefURLRequest. |request| cannot be modified in this callback. Set |allow_os_execution| to true to attempt
     * execution via the registered OS protocol handler, if any. SECURITY WARNING: YOU SHOULD USE THIS METHOD TO ENFORCE
     * RESTRICTIONS BASED ON SCHEME, HOST OR OTHER URL ANALYSIS BEFORE ALLOWING OS EXECUTION.
     *
     * @param browser may be null
     * @param frame may be null
     */
    default void onProtocolExecution(long browser, long frame, long request, int[] allowOsExecution) {}
}
