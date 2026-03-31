// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle menu model events. The methods of this class will be called on the browser process
 * UI thread unless otherwise indicated.
 */
public interface CefMenuModelDelegate {

    /** Perform the action associated with the specified |command_id| and optional |event_flags|. */
    default void executeCommand(long menuModel, int commandId, @Nonnull CefEventFlags eventFlags) {}

    /** Called when the user moves the mouse outside the menu and over the owning window. */
    default void mouseOutsideMenu(long menuModel, @Nonnull CefPoint screenPoint) {}

    /**
     * Called on unhandled open submenu keyboard commands. |is_rtl| will be true if the menu is displaying a
     * right-to-left language.
     */
    default void unhandledOpenSubmenu(long menuModel, boolean isRtl) {}

    /**
     * Called on unhandled close submenu keyboard commands. |is_rtl| will be true if the menu is displaying a
     * right-to-left language.
     */
    default void unhandledCloseSubmenu(long menuModel, boolean isRtl) {}

    /** The menu is about to show. */
    default void menuWillShow(long menuModel) {}

    /** The menu has closed. */
    default void menuClosed(long menuModel) {}

    /** Optionally modify a menu item label. Return true if |label| was modified. */
    default boolean formatLabel(long menuModel, @Nonnull String label) {
        return false;
    }
}
