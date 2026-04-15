// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle context menu events. The methods of this class will be called on the UI thread.
 * <p>Definition generated from cef_context_menu_handler_capi.h
 * <pre>typedef struct _cef_context_menu_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_context_menu_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:88</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefContextMenuHandler extends CefClientHandler {

    /**
     * Called before a context menu is displayed. {@code params} provides information about the context menu state. {@code model} initially contains the default context menu. The {@code model} can be cleared to show no context menu or modified to show a custom menu. Do not keep references to {@code params} or {@code model} outside of this callback.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_before_context_menu)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_context_menu_params_t* params, struct _cef_menu_model_t* model);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:98</a>
     */
    default void onBeforeContextMenu(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefContextMenuParams params, @Nullable CefMenuModel model) {
    }

    /**
     * Called to allow custom display of the context menu. {@code params} provides information about the context menu state. {@code model} contains the context menu model resulting from OnBeforeContextMenu. For custom display return {@code true} and execute {@code callback} either synchronously or asynchronously with the selected command ID. For default display return {@code false}. Do not keep references to {@code params} or {@code model} outside of this callback.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>int (CEF_CALLBACK* run_context_menu)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_context_menu_params_t* params, struct _cef_menu_model_t* model, struct _cef_run_context_menu_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:111</a>
     */
    default boolean runContextMenu(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefContextMenuParams params, @Nullable CefMenuModel model, @Nullable CefRunContextMenuCallback callback) {
        return false;
    }

    /**
     * Called to execute a command selected from the context menu. Return {@code true} if the command was handled or {@code false} for the default implementation. See cef_menu_id_t for the command ids that have default implementations. All user-defined command ids should be between MENU_ID_USER_FIRST and MENU_ID_USER_LAST. {@code params} will have the same values as what was passed to OnBeforeContextMenu(). Do not keep a reference to {@code params} outside of this callback.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_context_menu_command)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_context_menu_params_t* params, int command_id, cef_event_flags_t event_flags);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:128</a>
     */
    default boolean onContextMenuCommand(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefContextMenuParams params, int commandId, @Nonnull CefEventFlags eventFlags) {
        return false;
    }

    /**
     * Called when the context menu is dismissed irregardless of whether the menu was canceled or a command was selected.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_context_menu_dismissed)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:146</a>
     */
    default void onContextMenuDismissed(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
    }

    /**
     * Called to allow custom display of the quick menu for a windowless browser. {@code location} is the top left corner of the selected region. {@code size} is the size of the selected region. {@code edit_state_flags} is a combination of flags that represent the state of the quick menu. Return {@code true} if the menu will be handled and execute {@code callback} either synchronously or asynchronously with the selected command ID. Return {@code false} to cancel the menu.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>int (CEF_CALLBACK* run_quick_menu)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_point_t* location, const cef_size_t* size, cef_quick_menu_edit_state_flags_t edit_state_flags, struct _cef_run_quick_menu_callback_t* callback);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:154</a>
     */
    default boolean runQuickMenu(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefPoint location, @Nonnull CefSize size, @Nonnull CefQuickMenuEditStateFlags editStateFlags, @Nullable CefRunQuickMenuCallback callback) {
        return false;
    }

    /**
     * Called to execute a command selected from the quick menu for a windowless browser. Return {@code true} if the command was handled or {@code false} for the default implementation. See cef_menu_id_t for command IDs that have default implementations.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_quick_menu_command)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int command_id, cef_event_flags_t event_flags);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:172</a>
     */
    default boolean onQuickMenuCommand(@Nullable CefBrowser browser, @Nullable CefFrame frame, int commandId, @Nonnull CefEventFlags eventFlags) {
        return false;
    }

    /**
     * Called when the quick menu for a windowless browser is dismissed irregardless of whether the menu was canceled or a command was selected.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_quick_menu_dismissed)(struct _cef_context_menu_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:186</a>
     */
    default void onQuickMenuDismissed(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefContextMenuHandler {
        private final java.util.List<CefContextMenuHandler> delegates;

        public Delegating(java.util.List<CefContextMenuHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onBeforeContextMenu(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefContextMenuParams params, @Nullable CefMenuModel model) {
            for (CefContextMenuHandler d : delegates) d.onBeforeContextMenu(browser, frame, params, model);
        }

        @Override
        public boolean runContextMenu(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefContextMenuParams params, @Nullable CefMenuModel model, @Nullable CefRunContextMenuCallback callback) {
            for (CefContextMenuHandler d : delegates) {
                if (d.runContextMenu(browser, frame, params, model, callback)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onContextMenuCommand(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefContextMenuParams params, int commandId, @Nonnull CefEventFlags eventFlags) {
            for (CefContextMenuHandler d : delegates) {
                if (d.onContextMenuCommand(browser, frame, params, commandId, eventFlags)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onContextMenuDismissed(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
            for (CefContextMenuHandler d : delegates) d.onContextMenuDismissed(browser, frame);
        }

        @Override
        public boolean runQuickMenu(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefPoint location, @Nonnull CefSize size, @Nonnull CefQuickMenuEditStateFlags editStateFlags, @Nullable CefRunQuickMenuCallback callback) {
            for (CefContextMenuHandler d : delegates) {
                if (d.runQuickMenu(browser, frame, location, size, editStateFlags, callback)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public boolean onQuickMenuCommand(@Nullable CefBrowser browser, @Nullable CefFrame frame, int commandId, @Nonnull CefEventFlags eventFlags) {
            for (CefContextMenuHandler d : delegates) {
                if (d.onQuickMenuCommand(browser, frame, commandId, eventFlags)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }

        @Override
        public void onQuickMenuDismissed(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
            for (CefContextMenuHandler d : delegates) d.onQuickMenuDismissed(browser, frame);
        }
    }

}
