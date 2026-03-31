// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle context menu events. The methods of this class will be called on the UI thread.
 */
public interface CefContextMenuHandler {

    /**
     * Called before a context menu is displayed. |params| provides information about the context menu state. |model|
     * initially contains the default context menu. The |model| can be cleared to show no context menu or modified to
     * show a custom menu. Do not keep references to |params| or |model| outside of this callback.
     */
    default void onBeforeContextMenu(long browser, long frame, long params, long model) {}

    /**
     * Called to allow custom display of the context menu. |params| provides information about the context menu state.
     * |model| contains the context menu model resulting from OnBeforeContextMenu. For custom display return true and
     * execute |callback| either synchronously or asynchronously with the selected command ID. For default display
     * return false. Do not keep references to |params| or |model| outside of this callback.
     */
    default boolean runContextMenu(long browser, long frame, long params, long model, long callback) {
        return false;
    }

    /**
     * Called to execute a command selected from the context menu. Return true if the command was handled or false for
     * the default implementation. See cef_menu_id_t for the command ids that have default implementations. All
     * user-defined command ids should be between MENU_ID_USER_FIRST and MENU_ID_USER_LAST. |params| will have the same
     * values as what was passed to OnBeforeContextMenu(). Do not keep a reference to |params| outside of this callback.
     */
    default boolean onContextMenuCommand(
            long browser, long frame, long params, int commandId, @Nonnull CefEventFlags eventFlags) {
        return false;
    }

    /**
     * Called when the context menu is dismissed irregardless of whether the menu was canceled or a command was
     * selected.
     */
    default void onContextMenuDismissed(long browser, long frame) {}

    /**
     * Called to allow custom display of the quick menu for a windowless browser. |location| is the top left corner of
     * the selected region. |size| is the size of the selected region. |edit_state_flags| is a combination of flags that
     * represent the state of the quick menu. Return true if the menu will be handled and execute |callback| either
     * synchronously or asynchronously with the selected command ID. Return false to cancel the menu.
     */
    default boolean runQuickMenu(
            long browser,
            long frame,
            @Nonnull CefPoint location,
            @Nonnull CefSize size,
            @Nonnull CefQuickMenuEditStateFlags editStateFlags,
            long callback) {
        return false;
    }

    /**
     * Called to execute a command selected from the quick menu for a windowless browser. Return true if the command was
     * handled or false for the default implementation. See cef_menu_id_t for command IDs that have default
     * implementations.
     */
    default boolean onQuickMenuCommand(long browser, long frame, int commandId, @Nonnull CefEventFlags eventFlags) {
        return false;
    }

    /**
     * Called when the quick menu for a windowless browser is dismissed irregardless of whether the menu was canceled or
     * a command was selected.
     */
    default void onQuickMenuDismissed(long browser, long frame) {}
}
