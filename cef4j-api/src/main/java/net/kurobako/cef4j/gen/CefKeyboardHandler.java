// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Implement this interface to handle events related to keyboard input. The methods of this class will be called on the
 * UI thread.
 */
public interface CefKeyboardHandler {

    /**
     * Called before a keyboard event is sent to the renderer. |event| contains information about the keyboard event.
     * |os_event| is the operating system event message, if any. Return true if the event was handled or false
     * otherwise. If the event will be handled in OnKeyEvent() as a keyboard shortcut set |is_keyboard_shortcut| to true
     * and return false.
     */
    default boolean onPreKeyEvent(long browser, long event, long osEvent, int[] isKeyboardShortcut) {
        return false;
    }

    /**
     * Called after the renderer and JavaScript in the page has had a chance to handle the event. |event| contains
     * information about the keyboard event. |os_event| is the operating system event message, if any. Return true if
     * the keyboard event was handled or false otherwise.
     */
    default boolean onKeyEvent(long browser, long event, long osEvent) {
        return false;
    }
}
