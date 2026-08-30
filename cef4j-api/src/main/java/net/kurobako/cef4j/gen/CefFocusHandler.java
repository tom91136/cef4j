// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Implement this interface to handle events related to focus. The methods of this class will be called on the UI
 * thread.
 *
 * <p>Definition generated from cef_focus_handler_capi.h
 *
 * <pre>typedef struct _cef_focus_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_focus_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__focus__handler_8h.html">cef_focus_handler.h:46</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefFocusHandler extends CefClientHandler {

    /**
     * Called when the browser component is about to loose focus. For instance, if focus was on the last HTML element
     * and the user pressed the TAB key. {@code next} will be {@code true} if the browser is giving focus to the next
     * component and {@code false} if the browser is giving focus to the previous component.
     *
     * <p>Definition generated from cef_focus_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_take_focus)(struct _cef_focus_handler_t* self, struct _cef_browser_t* browser, int next);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__focus__handler_8h.html">cef_focus_handler.h:55</a>
     */
    default void onTakeFocus(@Nullable CefBrowser browser, boolean next) {}

    /**
     * Called when the browser component is requesting focus. {@code source} indicates where the focus request is
     * originating from. Return {@code false} to allow the focus to be set or {@code true} to cancel setting the focus.
     *
     * <p>Definition generated from cef_focus_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_set_focus)(struct _cef_focus_handler_t* self, struct _cef_browser_t* browser, cef_focus_source_t source);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__focus__handler_8h.html">cef_focus_handler.h:64</a>
     */
    default boolean onSetFocus(@Nullable CefBrowser browser, @Nonnull CefFocusSource source) {
        return false;
    }

    /**
     * Called when the browser component has received focus.
     *
     * <p>Definition generated from cef_focus_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_got_focus)(struct _cef_focus_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__focus__handler_8h.html">cef_focus_handler.h:74</a>
     */
    default void onGotFocus(@Nullable CefBrowser browser) {}
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefFocusHandler {
        private final java.util.List<CefFocusHandler> delegates;

        public Delegating(java.util.List<CefFocusHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onTakeFocus(@Nullable CefBrowser browser, boolean next) {
            for (CefFocusHandler d : delegates) d.onTakeFocus(browser, next);
        }

        @Override
        public boolean onSetFocus(@Nullable CefBrowser browser, @Nonnull CefFocusSource source) {
            for (CefFocusHandler d : delegates) {
                if (d.onSetFocus(browser, source)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onGotFocus(@Nullable CefBrowser browser) {
            for (CefFocusHandler d : delegates) d.onGotFocus(browser);
        }
    }
}
