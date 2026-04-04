// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Implement this interface to handle events related to commands. The methods of this class will be called on the UI
 * thread.
 *
 * <p>Definition generated from cef_command_handler_capi.h
 *
 * <pre>typedef struct _cef_command_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_command_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__handler_8h.html">cef_command_handler.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefCommandHandler extends CefClientHandler {

    /**
     * Called to execute a Chrome command triggered via menu selection or keyboard shortcut. Use the
     * cef_id_for_command_id_name() function for version-safe mapping of command IDC names from cef_command_ids.h to
     * version-specific numerical {@code command_id} values. {@code disposition} provides information about the intended
     * command target. Return {@code true} if the command was handled or {@code false} for the default implementation.
     * For context menu commands this will be called after
     * {@link net.kurobako.cef4j.gen.CefContextMenuHandler#onContextMenuCommand(CefBrowser, CefFrame,
     * CefContextMenuParams, int, CefEventFlags)}. Only used with Chrome style.
     *
     * <p>Definition generated from cef_command_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_chrome_command)(struct _cef_command_handler_t* self, struct _cef_browser_t* browser, int command_id, cef_window_open_disposition_t disposition);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__handler_8h.html">cef_command_handler.h:51</a>
     */
    default boolean onChromeCommand(
            @Nullable CefBrowser browser, int commandId, @Nonnull CefWindowOpenDisposition disposition) {
        return false;
    }

    /**
     * Called to check if a Chrome app menu item should be visible. Use the cef_id_for_command_id_name() function for
     * version-safe mapping of command IDC names from cef_command_ids.h to version-specific numerical {@code command_id}
     * values. Only called for menu items that would be visible by default. Only used with Chrome style.
     *
     * <p>Definition generated from cef_command_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* is_chrome_app_menu_item_visible)(struct _cef_command_handler_t* self, struct _cef_browser_t* browser, int command_id);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__handler_8h.html">cef_command_handler.h:68</a>
     */
    default boolean isChromeAppMenuItemVisible(@Nullable CefBrowser browser, int commandId) {
        return false;
    }

    /**
     * Called to check if a Chrome app menu item should be enabled. Use the cef_id_for_command_id_name() function for
     * version-safe mapping of command IDC names from cef_command_ids.h to version-specific numerical {@code command_id}
     * values. Only called for menu items that would be enabled by default. Only used with Chrome style.
     *
     * <p>Definition generated from cef_command_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* is_chrome_app_menu_item_enabled)(struct _cef_command_handler_t* self, struct _cef_browser_t* browser, int command_id);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__handler_8h.html">cef_command_handler.h:81</a>
     */
    default boolean isChromeAppMenuItemEnabled(@Nullable CefBrowser browser, int commandId) {
        return false;
    }

    /**
     * Called during browser creation to check if a Chrome page action icon should be visible. Only called for icons
     * that would be visible by default. Only used with Chrome style.
     *
     * <p>Definition generated from cef_command_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* is_chrome_page_action_icon_visible)(struct _cef_command_handler_t* self, cef_chrome_page_action_icon_type_t icon_type);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__handler_8h.html">cef_command_handler.h:94</a>
     */
    default boolean isChromePageActionIconVisible(@Nonnull CefChromePageActionIconType iconType) {
        return false;
    }

    /**
     * Called during browser creation to check if a Chrome toolbar button should be visible. Only called for buttons
     * that would be visible by default. Only used with Chrome style.
     *
     * <p>Definition generated from cef_command_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* is_chrome_toolbar_button_visible)(struct _cef_command_handler_t* self, cef_chrome_toolbar_button_type_t button_type);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__command__handler_8h.html">cef_command_handler.h:105</a>
     */
    default boolean isChromeToolbarButtonVisible(@Nonnull CefChromeToolbarButtonType buttonType) {
        return false;
    }
}
