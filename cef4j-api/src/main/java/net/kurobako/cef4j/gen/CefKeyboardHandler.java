// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to keyboard input. The methods of this class will be called on the UI thread.
 * <p>Definition generated from cef_keyboard_handler_capi.h
 * <pre>typedef struct _cef_keyboard_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_keyboard_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__keyboard__handler_8h.html">cef_keyboard_handler.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefKeyboardHandler extends CefClientHandler {

    /**
     * Called before a keyboard event is sent to the renderer. {@code event} contains information about the keyboard event. {@code os_event} is the operating system event message, if any. Return {@code true} if the event was handled or {@code false} otherwise. If the event will be handled in OnKeyEvent() as a keyboard shortcut set {@code is_keyboard_shortcut} to {@code true} and return {@code false}.
     * <p>Definition generated from cef_keyboard_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_pre_key_event)(struct _cef_keyboard_handler_t* self, struct _cef_browser_t* browser, const cef_key_event_t* event, cef_event_handle_t os_event, int* is_keyboard_shortcut);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__keyboard__handler_8h.html">cef_keyboard_handler.h:51</a>
     */
    default boolean onPreKeyEvent(@Nullable CefBrowser browser, @Nonnull CefKeyEvent event, long osEvent, int[] isKeyboardShortcut) {
        return false;
    }

    /**
     * Called after the renderer and JavaScript in the page has had a chance to handle the event. {@code event} contains information about the keyboard event. {@code os_event} is the operating system event message, if any. Return {@code true} if the keyboard event was handled or {@code false} otherwise.
     * <p>Definition generated from cef_keyboard_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_key_event)(struct _cef_keyboard_handler_t* self, struct _cef_browser_t* browser, const cef_key_event_t* event, cef_event_handle_t os_event);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__keyboard__handler_8h.html">cef_keyboard_handler.h:66</a>
     */
    default boolean onKeyEvent(@Nullable CefBrowser browser, @Nonnull CefKeyEvent event, long osEvent) {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefKeyboardHandler {
        private final java.util.List<CefKeyboardHandler> delegates;

        public Delegating(java.util.List<CefKeyboardHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public boolean onPreKeyEvent(@Nullable CefBrowser browser, @Nonnull CefKeyEvent event, long osEvent, int[] isKeyboardShortcut) {
            for (CefKeyboardHandler d : delegates) {
                if (d.onPreKeyEvent(browser, event, osEvent, isKeyboardShortcut)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onKeyEvent(@Nullable CefBrowser browser, @Nonnull CefKeyEvent event, long osEvent) {
            for (CefKeyboardHandler d : delegates) {
                if (d.onKeyEvent(browser, event, osEvent)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
