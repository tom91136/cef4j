// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

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
     * Method executed for visiting the DOM. The document object passed to this method represents a snapshot of the DOM
     * at the time this method is executed. DOM objects are only valid for the scope of this method. Do not keep
     * references to or attempt to access any DOM objects outside the scope of this method.
     *
     * <p>Definition generated from cef_string_visitor_capi.h
     *
     * <pre>void (CEF_CALLBACK* visit)(struct _cef_string_visitor_t* self, const cef_string_t* string);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:55</a>
     */
    default void visit(@Nonnull String string) {}
}
