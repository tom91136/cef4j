// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Callback interface for {@link CefBrowserHost#getNavigationEntries(CefNavigationEntryVisitor, boolean)}. The methods
 * of this class will be called on the browser process UI thread.
 *
 * <p>Definition generated from cef_browser_capi.h
 *
 * <pre>typedef struct _cef_navigation_entry_visitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_navigation_entry_visitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:219</a>
 */
public interface CefNavigationEntryVisitor extends CefClientHandler {

    /**
     * Method executed for visiting the DOM. The document object passed to this method represents a snapshot of the DOM
     * at the time this method is executed. DOM objects are only valid for the scope of this method. Do not keep
     * references to or attempt to access any DOM objects outside the scope of this method.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* visit)(struct _cef_navigation_entry_visitor_t* self, struct _cef_navigation_entry_t* entry, int current, int index, int total);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dom_8h.html">cef_dom.h:55</a>
     */
    default int visit(@Nonnull CefNavigationEntry entry, int current, int index, int total) {
        return 0;
    }
}
