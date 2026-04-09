// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to find results. The methods of this class will be called on the UI thread.
 * <p>Definition generated from cef_find_handler_capi.h
 * <pre>typedef struct _cef_find_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_find_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__find__handler_8h.html">cef_find_handler.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefFindHandler extends CefClientHandler {

    /**
     * Called to report find results returned by {@link net.kurobako.cef4j.gen.CefBrowserHost#find(String, boolean, boolean, boolean)}. {@code identifer} is a unique incremental identifier for the currently active search, {@code count} is the number of matches currently identified, {@code selectionRect} is the location of where the match was found (in window coordinates), {@code activeMatchOrdinal} is the current position in the search results, and {@code finalUpdate} is {@code true} if this is the last find notification.
     * <p>Definition generated from cef_find_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_find_result)(struct _cef_find_handler_t* self, struct _cef_browser_t* browser, int identifier, int count, const cef_rect_t* selectionRect, int activeMatchOrdinal, int finalUpdate);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__find__handler_8h.html">cef_find_handler.h:51</a>
     */
    default void onFindResult(@Nullable CefBrowser browser, int identifier, int count, @Nonnull CefRect selectionRect, int activeMatchOrdinal, boolean finalUpdate) {
    }
}
