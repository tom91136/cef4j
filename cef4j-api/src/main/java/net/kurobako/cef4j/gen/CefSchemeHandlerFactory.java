// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class that creates CefResourceHandler instances for handling scheme requests. The methods of this class will always
 * be called on the IO thread.
 *
 * <p>Definition generated from cef_scheme_capi.h
 *
 * <pre>typedef struct _cef_scheme_handler_factory_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_scheme_handler_factory_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__scheme_8h.html">cef_scheme.h:99</a>
 */
public interface CefSchemeHandlerFactory extends CefClientHandler {

    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_scheme_capi.h
     *
     * <pre>
     * cef_resource_handler_t* (CEF_CALLBACK* create)(struct _cef_scheme_handler_factory_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* scheme_name, struct _cef_request_t* request);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    default Optional<CefResourceHandler> create(
            @Nonnull CefBrowser browser,
            @Nonnull CefFrame frame,
            @Nonnull String schemeName,
            @Nonnull CefRequest request) {
        return Optional.empty();
    }
}
