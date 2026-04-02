// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

/**
 * Implement this interface to receive string values asynchronously.
 *
 * <p>Definition generated from cef_string_visitor_capi.h
 *
 * <pre>typedef struct _cef_string_visitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_string_visitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__string__visitor_8h.html">cef_string_visitor.h:42</a>
 */
public interface CefStringVisitor extends CefClientHandler {

    /**
     * Method that will be executed.
     *
     * <p>Definition generated from cef_string_visitor_capi.h
     *
     * <pre>void (CEF_CALLBACK* visit)(struct _cef_string_visitor_t* self, const cef_string_t* string);</pre>
     *
     * @param string may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__string__visitor_8h.html">cef_string_visitor.h:48</a>
     */
    default void visit(@Nullable String string) {}
}
