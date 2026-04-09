// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__keyboard__handler_8h.html">cef_keyboard_handler.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefKeyboardHandler extends CefClientHandler {

    /**
     * Called before a keyboard event is sent to the renderer. {@code event} contains information about the keyboard event. {@code os_event} is the operating system event message, if any. Return {@code true} if the event was handled or {@code false} otherwise. If the event will be handled in OnKeyEvent() as a keyboard shortcut set {@code is_keyboard_shortcut} to {@code true} and return {@code false}.
     * <p>Definition generated from cef_keyboard_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_pre_key_event)(struct _cef_keyboard_handler_t* self, struct _cef_browser_t* browser, const cef_key_event_t* event, cef_event_handle_t os_event, int* is_keyboard_shortcut);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__keyboard__handler_8h.html">cef_keyboard_handler.h:51</a>
     */
    default boolean onPreKeyEvent(@Nullable CefBrowser browser, @Nonnull CefKeyEvent event, long osEvent, int[] isKeyboardShortcut) {
        return false;
    }

    /**
     * Called after the renderer and JavaScript in the page has had a chance to handle the event. {@code event} contains information about the keyboard event. {@code os_event} is the operating system event message, if any. Return {@code true} if the keyboard event was handled or {@code false} otherwise.
     * <p>Definition generated from cef_keyboard_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_key_event)(struct _cef_keyboard_handler_t* self, struct _cef_browser_t* browser, const cef_key_event_t* event, cef_event_handle_t os_event);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__keyboard__handler_8h.html">cef_keyboard_handler.h:66</a>
     */
    default boolean onKeyEvent(@Nullable CefBrowser browser, @Nonnull CefKeyEvent event, long osEvent) {
        return false;
    }
}
