// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Callback interface for {@link net.kurobako.cef4j.gen.CefBrowserHost#getNavigationEntries(CefNavigationEntryVisitor, boolean)}. The methods of this class will be called on the browser process UI thread.
 * <p>Definition generated from cef_browser_capi.h
 * <pre>typedef struct _cef_navigation_entry_visitor_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_navigation_entry_visitor_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser_8h.html">cef_browser.h:219</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefNavigationEntryVisitor extends CefClientHandler {

    /**
     * Method that will be executed. Do not keep a reference to {@code entry} outside of this callback. Return {@code true} to continue visiting entries or {@code false} to stop. {@code current} is {@code true} if this entry is the currently loaded navigation entry. {@code index} is the 0-based index of this entry and {@code total} is the total number of entries.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>int (CEF_CALLBACK* visit)(struct _cef_navigation_entry_visitor_t* self, struct _cef_navigation_entry_t* entry, int current, int index, int total);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser_8h.html">cef_browser.h:226</a>
     */
    default boolean visit(@Nullable CefNavigationEntry entry, boolean current, int index, int total) {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefNavigationEntryVisitor {
        private final java.util.List<CefNavigationEntryVisitor> delegates;

        public Delegating(java.util.List<CefNavigationEntryVisitor> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean visit(@Nullable CefNavigationEntry entry, boolean current, int index, int total) {
            for (CefNavigationEntryVisitor d : delegates) {
                if (d.visit(entry, current, index, total)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
