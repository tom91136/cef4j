// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Callback interface for {@link CefRequestContext#resolveHost(String, CefResolveCallback)}.
 *
 * <p>Definition generated from cef_request_context_capi.h
 *
 * <pre>typedef struct _cef_resolve_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resolve_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:53</a>
 */
public interface CefResolveCallback extends CefClientHandler {

    /**
     * Called on the UI thread after the ResolveHost request has completed. {@code result} will be the result code.
     * {@code resolved_ips} will be the list of resolved IP addresses or empty if the resolution failed.
     *
     * <p>Definition generated from cef_request_context_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_resolve_completed)(struct _cef_resolve_callback_t* self, cef_errorcode_t result, cef_string_list_t resolved_ips);
     * </pre>
     *
     * @param resolvedIps may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request__context_8h.html">cef_request_context.h:59</a>
     */
    default void onResolveCompleted(@Nonnull CefErrorCode result, @Nullable List<String> resolvedIps) {}
}
