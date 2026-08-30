// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Interface to implement for visiting cookie values. The methods of this class will always be called on the UI thread.
 *
 * <p>Definition generated from cef_cookie_capi.h
 *
 * <pre>typedef struct _cef_cookie_visitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_cookie_visitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__cookie_8h.html">cef_cookie.h:129</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefCookieVisitor extends CefClientHandler {

    /**
     * Method that will be called once for each cookie. {@code count} is the 0-based index for the current cookie.
     * {@code total} is the total number of cookies. Set {@code deleteCookie} to {@code true} to delete the cookie
     * currently being visited. Return {@code false} to stop visiting cookies. This method may never be called if no
     * cookies are found.
     *
     * <p>Definition generated from cef_cookie_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* visit)(struct _cef_cookie_visitor_t* self, const struct _cef_cookie_t* cookie, int count, int total, int* deleteCookie);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__cookie_8h.html">cef_cookie.h:136</a>
     */
    default boolean visit(@Nonnull CefCookie cookie, int count, int total, int[] deleteCookie) {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefCookieVisitor {
        private final java.util.List<CefCookieVisitor> delegates;

        public Delegating(java.util.List<CefCookieVisitor> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean visit(@Nonnull CefCookie cookie, int count, int total, int[] deleteCookie) {
            for (CefCookieVisitor d : delegates) {
                if (d.visit(cookie, count, total, deleteCookie)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }
}
