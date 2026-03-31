// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle events related to commands. The methods of this class will be called on the UI
 * thread.
 */
public interface CefCommandHandler {

    /**
     * Called to execute a Chrome command triggered via menu selection or keyboard shortcut. Use the
     * cef_id_for_command_id_name() function for version-safe mapping of command IDC names from cef_command_ids.h to
     * version-specific numerical |command_id| values. |disposition| provides information about the intended command
     * target. Return true if the command was handled or false for the default implementation. For context menu commands
     * this will be called after CefContextMenuHandler::OnContextMenuCommand. Only used with Chrome style.
     */
    default boolean onChromeCommand(long browser, int commandId, @Nonnull CefWindowOpenDisposition disposition) {
        return false;
    }

    /**
     * Called to check if a Chrome app menu item should be visible. Use the cef_id_for_command_id_name() function for
     * version-safe mapping of command IDC names from cef_command_ids.h to version-specific numerical |command_id|
     * values. Only called for menu items that would be visible by default. Only used with Chrome style.
     */
    default boolean isChromeAppMenuItemVisible(long browser, int commandId) {
        return false;
    }

    /**
     * Called to check if a Chrome app menu item should be enabled. Use the cef_id_for_command_id_name() function for
     * version-safe mapping of command IDC names from cef_command_ids.h to version-specific numerical |command_id|
     * values. Only called for menu items that would be enabled by default. Only used with Chrome style.
     */
    default boolean isChromeAppMenuItemEnabled(long browser, int commandId) {
        return false;
    }

    /**
     * Called during browser creation to check if a Chrome page action icon should be visible. Only called for icons
     * that would be visible by default. Only used with Chrome style.
     */
    default boolean isChromePageActionIconVisible(@Nonnull CefChromePageActionIconType iconType) {
        return false;
    }

    /**
     * Called during browser creation to check if a Chrome toolbar button should be visible. Only called for buttons
     * that would be visible by default. Only used with Chrome style.
     */
    default boolean isChromeToolbarButtonVisible(@Nonnull CefChromeToolbarButtonType buttonType) {
        return false;
    }
}
