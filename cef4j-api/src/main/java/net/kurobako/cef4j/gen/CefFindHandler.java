// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle events related to find results. The methods of this class will be called on the UI
 * thread.
 */
public interface CefFindHandler {

    /**
     * Called to report find results returned by CefBrowserHost::Find(). |identifer| is a unique incremental identifier
     * for the currently active search, |count| is the number of matches currently identified, |selectionRect| is the
     * location of where the match was found (in window coordinates), |activeMatchOrdinal| is the current position in
     * the search results, and |finalUpdate| is true if this is the last find notification.
     */
    default void onFindResult(
            long browser,
            int identifier,
            int count,
            @Nonnull CefRect selectionRect,
            int activeMatchOrdinal,
            boolean finalUpdate) {}
}
