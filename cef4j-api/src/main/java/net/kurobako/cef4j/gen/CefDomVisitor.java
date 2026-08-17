// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Interface to implement for visiting the DOM. The methods of this class will be called on the render process main thread.
 * <p>Definition generated from cef_dom_capi.h
 * <pre>typedef struct _cef_domvisitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_domvisitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:48</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefDomVisitor extends CefClientHandler {

    /**
     * Method executed for visiting the DOM. The document object passed to this method represents a snapshot of the DOM at the time this method is executed. DOM objects are only valid for the scope of this method. Do not keep references to or attempt to access any DOM objects outside the scope of this method.
     * <p>Definition generated from cef_dom_capi.h
     * <pre>void (CEF_CALLBACK* visit)(struct _cef_domvisitor_t* self, struct _cef_domdocument_t* document);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dom_8h.html">cef_dom.h:55</a>
     */
    default void visit(@Nullable CefDomDocument document) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefDomVisitor {
        private final java.util.List<CefDomVisitor> delegates;

        public Delegating(java.util.List<CefDomVisitor> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void visit(@Nullable CefDomDocument document) {
            for (CefDomVisitor d : delegates) d.visit(document);
        }
    }

}
