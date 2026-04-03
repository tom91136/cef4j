// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Implement this interface to handle menu model events. The methods of this class will be called on the browser process
 * UI thread unless otherwise indicated.
 *
 * <p>Definition generated from cef_menu_model_delegate_capi.h
 *
 * <pre>typedef struct _cef_menu_model_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_menu_model_delegate_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model__delegate_8h.html">cef_menu_model_delegate.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefMenuModelDelegate extends CefClientHandler {

    /**
     * Perform the action associated with the specified {@code command_id} and optional {@code event_flags}.
     *
     * <p>Definition generated from cef_menu_model_delegate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* execute_command)(struct _cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, int command_id, cef_event_flags_t event_flags);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model__delegate_8h.html">cef_menu_model_delegate.h:53</a>
     */
    default void executeCommand(@Nullable CefMenuModel menuModel, int commandId, @Nonnull CefEventFlags eventFlags) {}

    /**
     * Called when the user moves the mouse outside the menu and over the owning window.
     *
     * <p>Definition generated from cef_menu_model_delegate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* mouse_outside_menu)(struct _cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, const cef_point_t* screen_point);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model__delegate_8h.html">cef_menu_model_delegate.h:62</a>
     */
    default void mouseOutsideMenu(@Nullable CefMenuModel menuModel, @Nonnull CefPoint screenPoint) {}

    /**
     * Called on unhandled open submenu keyboard commands. {@code is_rtl} will be {@code true} if the menu is displaying
     * a right-to-left language.
     *
     * <p>Definition generated from cef_menu_model_delegate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* unhandled_open_submenu)(struct _cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, int is_rtl);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model__delegate_8h.html">cef_menu_model_delegate.h:70</a>
     */
    default void unhandledOpenSubmenu(@Nullable CefMenuModel menuModel, boolean isRtl) {}

    /**
     * Called on unhandled close submenu keyboard commands. {@code is_rtl} will be {@code true} if the menu is
     * displaying a right-to-left language.
     *
     * <p>Definition generated from cef_menu_model_delegate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* unhandled_close_submenu)(struct _cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, int is_rtl);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model__delegate_8h.html">cef_menu_model_delegate.h:78</a>
     */
    default void unhandledCloseSubmenu(@Nullable CefMenuModel menuModel, boolean isRtl) {}

    /**
     * The menu is about to show.
     *
     * <p>Definition generated from cef_menu_model_delegate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* menu_will_show)(struct _cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model__delegate_8h.html">cef_menu_model_delegate.h:86</a>
     */
    default void menuWillShow(@Nullable CefMenuModel menuModel) {}

    /**
     * The menu has closed.
     *
     * <p>Definition generated from cef_menu_model_delegate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* menu_closed)(struct _cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model__delegate_8h.html">cef_menu_model_delegate.h:92</a>
     */
    default void menuClosed(@Nullable CefMenuModel menuModel) {}

    /**
     * Optionally modify a menu item label. Return {@code true} if {@code label} was modified.
     *
     * <p>Definition generated from cef_menu_model_delegate_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* format_label)(struct _cef_menu_model_delegate_t* self, struct _cef_menu_model_t* menu_model, cef_string_t* label);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model__delegate_8h.html">cef_menu_model_delegate.h:98</a>
     */
    default boolean formatLabel(@Nullable CefMenuModel menuModel, @Nullable String label) {
        return false;
    }
}
