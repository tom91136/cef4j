// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Interface to implement to be notified of asynchronous completion via {@link net.kurobako.cef4j.gen.CefCookieManager#setCookie(String, CefCookie, CefSetCookieCallback)}.
 * <p>Definition generated from cef_cookie_capi.h
 * <pre>typedef struct _cef_set_cookie_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_set_cookie_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__cookie_8h.html">cef_cookie.h:150</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefSetCookieCallback extends CefClientHandler {

    /**
     * Method that will be called upon completion. {@code success} will be {@code true} if the cookie was set successfully.
     * <p>Definition generated from cef_cookie_capi.h
     * <pre>void (CEF_CALLBACK* on_complete)(struct _cef_set_cookie_callback_t* self, int success);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__cookie_8h.html">cef_cookie.h:157</a>
     */
    default void onComplete(boolean success) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefSetCookieCallback {
        private final java.util.List<CefSetCookieCallback> delegates;

        public Delegating(java.util.List<CefSetCookieCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onComplete(boolean success) {
            for (CefSetCookieCallback d : delegates) d.onComplete(success);
        }
    }

}
