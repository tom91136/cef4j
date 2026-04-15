// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class that creates CefResourceHandler instances for handling scheme requests. The methods of this class will always be called on the IO thread.
 * <p>Definition generated from cef_scheme_capi.h
 * <pre>typedef struct _cef_scheme_handler_factory_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_scheme_handler_factory_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scheme_8h.html">cef_scheme.h:99</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefSchemeHandlerFactory extends CefClientHandler {

    /**
     * Return a new resource handler instance to handle the request or an empty reference to allow default handling of the request. {@code browser} and {@code frame} will be the browser window and frame respectively that originated the request or {@code null} if the request did not originate from a browser window (for example, if the request came from CefURLRequest). The {@code request} object passed to this method cannot be modified.
     * <p>Definition generated from cef_scheme_capi.h
     * <pre>cef_resource_handler_t* (CEF_CALLBACK* create)(struct _cef_scheme_handler_factory_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* scheme_name, struct _cef_request_t* request);</pre>
     *
     * @param browser may be null
     * @param frame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scheme_8h.html">cef_scheme.h:106</a>
     */
    default Optional<CefResourceHandler> create(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable String schemeName, @Nullable CefRequest request) {
        return Optional.empty();
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefSchemeHandlerFactory {
        private final java.util.List<CefSchemeHandlerFactory> delegates;

        public Delegating(java.util.List<CefSchemeHandlerFactory> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public Optional<CefResourceHandler> create(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable String schemeName, @Nullable CefRequest request) {
            java.util.ArrayList<CefResourceHandler> collected = new java.util.ArrayList<>();
            for (CefSchemeHandlerFactory d : delegates) d.create(browser, frame, schemeName, request).ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefResourceHandler.Delegating(collected));
        }
    }

}
