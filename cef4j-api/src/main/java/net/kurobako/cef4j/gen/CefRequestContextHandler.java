// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
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
}
