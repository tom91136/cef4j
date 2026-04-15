// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Implement this interface to provide handler implementations. The handler instance will not be released until all objects related to the context have been destroyed.
 * <p>Definition generated from cef_request_context_handler_capi.h
 * <pre>typedef struct _cef_request_context_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_request_context_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context__handler_8h.html">cef_request_context_handler.h:48</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefRequestContextHandler extends CefClientHandler {

    /**
     * Called on the browser process UI thread immediately after the request context has been initialized.
     * <p>Definition generated from cef_request_context_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_request_context_initialized)(struct _cef_request_context_handler_t* self, struct _cef_request_context_t* request_context);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context__handler_8h.html">cef_request_context_handler.h:56</a>
     */
    default void onRequestContextInitialized(@Nullable CefRequestContext requestContext) {
    }

    /**
     * Called on the browser process IO thread before a resource request is initiated. The {@code browser} and {@code frame} values represent the source of the request, and may be {@code null} for requests originating from service workers or CefURLRequest. {@code request} represents the request contents and cannot be modified in this callback. {@code is_navigation} will be {@code true} if the resource request is a navigation. {@code is_download} will be {@code true} if the resource request is a download. {@code request_initiator} is the origin (scheme + domain) of the page that initiated the request. Set {@code disable_default_handling} to {@code true} to disable default handling of the request, in which case it will need to be handled via {@link net.kurobako.cef4j.gen.CefResourceRequestHandler#getResourceHandler(CefBrowser, CefFrame, CefRequest)} or it will be canceled. To allow the resource load to proceed with default handling return {@code null}. To specify a handler for the resource return a CefResourceRequestHandler object. This method will not be called if the client associated with {@code browser} returns a non-{@code null} value from {@link net.kurobako.cef4j.gen.CefRequestHandler#getResourceRequestHandler(CefBrowser, CefFrame, CefRequest, boolean, boolean, String, int[])} for the same request (identified by {@link net.kurobako.cef4j.gen.CefRequest#getIdentifier()}).
     * <p>Definition generated from cef_request_context_handler_capi.h
     * <pre>cef_resource_request_handler_t* (CEF_CALLBACK* get_resource_request_handler)(struct _cef_request_context_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_request_t* request, int is_navigation, int is_download, const cef_string_t* request_initiator, int* disable_default_handling);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     * @param requestInitiator may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context__handler_8h.html">cef_request_context_handler.h:64</a>
     */
    default Optional<CefResourceRequestHandler> getResourceRequestHandler(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, boolean isNavigation, boolean isDownload, @Nullable String requestInitiator, int[] disableDefaultHandling) {
        return Optional.empty();
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefRequestContextHandler {
        private final java.util.List<CefRequestContextHandler> delegates;

        public Delegating(java.util.List<CefRequestContextHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onRequestContextInitialized(@Nullable CefRequestContext requestContext) {
            for (CefRequestContextHandler d : delegates) d.onRequestContextInitialized(requestContext);
        }

        @Override
        public Optional<CefResourceRequestHandler> getResourceRequestHandler(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefRequest request, boolean isNavigation, boolean isDownload, @Nullable String requestInitiator, int[] disableDefaultHandling) {
            java.util.ArrayList<CefResourceRequestHandler> collected = new java.util.ArrayList<>();
            for (CefRequestContextHandler d : delegates) d.getResourceRequestHandler(browser, frame, request, isNavigation, isDownload, requestInitiator, disableDefaultHandling).ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefResourceRequestHandler.Delegating(collected));
        }
    }

}
