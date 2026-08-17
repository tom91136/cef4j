// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Implement this interface to receive string values asynchronously.
 * <p>Definition generated from cef_string_visitor_capi.h
 * <pre>typedef struct _cef_string_visitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_string_visitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__string__visitor_8h.html">cef_string_visitor.h:42</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefStringVisitor extends CefClientHandler {

    /**
     * Method that will be executed.
     * <p>Definition generated from cef_string_visitor_capi.h
     * <pre>void (CEF_CALLBACK* visit)(struct _cef_string_visitor_t* self, const cef_string_t* string);</pre>
     *
     * @param string may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__string__visitor_8h.html">cef_string_visitor.h:48</a>
     */
    default void visit(@Nullable String string) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefStringVisitor {
        private final java.util.List<CefStringVisitor> delegates;

        public Delegating(java.util.List<CefStringVisitor> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void visit(@Nullable String string) {
            for (CefStringVisitor d : delegates) d.visit(string);
        }
    }

}
