// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle events related to focus. The methods of this class will be called on the UI
 * thread.
 */
public interface CefFocusHandler {

    /**
     * Called when the browser component is about to loose focus. For instance, if focus was on the last HTML element
     * and the user pressed the TAB key. |next| will be true if the browser is giving focus to the next component and
     * false if the browser is giving focus to the previous component.
     */
    default void onTakeFocus(long browser, boolean next) {}

    /**
     * Called when the browser component is requesting focus. |source| indicates where the focus request is originating
     * from. Return false to allow the focus to be set or true to cancel setting the focus.
     */
    default boolean onSetFocus(long browser, @Nonnull CefFocusSource source) {
        return false;
    }

    /** Called when the browser component has received focus. */
    default void onGotFocus(long browser) {}
}
