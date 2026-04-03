// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Interface to implement for visiting the DOM. The methods of this class will be called on the render process main
 * thread.
 *
 * <p>Definition generated from cef_dom_capi.h
 *
 * <pre>typedef struct _cef_domvisitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_domvisitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:48</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefDomVisitor extends CefClientHandler {

    /**
     * Method executed for visiting the DOM. The document object passed to this method represents a snapshot of the DOM
     * at the time this method is executed. DOM objects are only valid for the scope of this method. Do not keep
     * references to or attempt to access any DOM objects outside the scope of this method.
     *
     * <p>Definition generated from cef_dom_capi.h
     *
     * <pre>void (CEF_CALLBACK* visit)(struct _cef_domvisitor_t* self, struct _cef_domdocument_t* document);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:55</a>
     */
    default void visit(@Nullable CefDomDocument document) {}
}
