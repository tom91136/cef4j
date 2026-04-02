// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

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
     * Method that will be executed. Do not keep a reference to {@code entry} outside of this callback. Return
     * {@code true} to continue visiting entries or {@code false} to stop. {@code current} is {@code true} if this entry
     * is the currently loaded navigation entry. {@code index} is the 0-based index of this entry and {@code total} is
     * the total number of entries.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* visit)(struct _cef_navigation_entry_visitor_t* self, struct _cef_navigation_entry_t* entry, int current, int index, int total);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:226</a>
     */
    default int visit(@Nullable CefNavigationEntry entry, int current, int index, int total) {
        return 0;
    }
}
