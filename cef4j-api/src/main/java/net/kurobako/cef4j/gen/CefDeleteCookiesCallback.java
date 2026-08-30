// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Interface to implement to be notified of asynchronous completion via
 * {@link net.kurobako.cef4j.gen.CefCookieManager#deleteCookies(String, String, CefDeleteCookiesCallback)}.
 *
 * <p>Definition generated from cef_cookie_capi.h
 *
 * <pre>typedef struct _cef_delete_cookies_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_delete_cookies_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__cookie_8h.html">cef_cookie.h:165</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefDeleteCookiesCallback extends CefClientHandler {

    /**
     * Method that will be called upon completion. {@code num_deleted} will be the number of cookies that were deleted.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_complete)(struct _cef_delete_cookies_callback_t* self, int num_deleted);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__cookie_8h.html">cef_cookie.h:172</a>
     */
    default void onComplete(int numDeleted) {}
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefDeleteCookiesCallback {
        private final java.util.List<CefDeleteCookiesCallback> delegates;

        public Delegating(java.util.List<CefDeleteCookiesCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onComplete(int numDeleted) {
            for (CefDeleteCookiesCallback d : delegates) d.onComplete(numDeleted);
        }
    }
}
