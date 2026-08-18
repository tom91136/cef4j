// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Callback interface for {@link net.kurobako.cef4j.gen.CefRequestContext#resolveHost(String, CefResolveCallback)}.
 * <p>Definition generated from cef_request_context_capi.h
 * <pre>typedef struct _cef_resolve_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resolve_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request__context_8h.html">cef_request_context.h:53</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefResolveCallback extends CefClientHandler {

    /**
     * Called on the UI thread after the ResolveHost request has completed. {@code result} will be the result code. {@code resolved_ips} will be the list of resolved IP addresses or empty if the resolution failed.
     * <p>Definition generated from cef_request_context_capi.h
     * <pre>void (CEF_CALLBACK* on_resolve_completed)(struct _cef_resolve_callback_t* self, cef_errorcode_t result, cef_string_list_t resolved_ips);</pre>
     *
     * @param resolvedIps may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request__context_8h.html">cef_request_context.h:59</a>
     */
    default void onResolveCompleted(@Nonnull CefErrorCode result, @Nullable List<String> resolvedIps) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefResolveCallback {
        private final java.util.List<CefResolveCallback> delegates;

        public Delegating(java.util.List<CefResolveCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onResolveCompleted(@Nonnull CefErrorCode result, @Nullable List<String> resolvedIps) {
            for (CefResolveCallback d : delegates) d.onResolveCompleted(result, resolvedIps);
        }
    }

}
