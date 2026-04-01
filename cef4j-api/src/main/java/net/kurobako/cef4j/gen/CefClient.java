// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Implement this interface to provide handler implementations.
 *
 * <p>Definition generated from cef_client_capi.h
 *
 * <pre>typedef struct _cef_client_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_client_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:62</a>
 */
public interface CefClient extends CefClientHandler {

    /**
     * Return the handler for audio rendering events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_audio_handler_t* (CEF_CALLBACK* get_audio_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:68</a>
     */
    default Optional<CefAudioHandler> getAudioHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for commands. If no handler is provided the default implementation will be used.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_command_handler_t* (CEF_CALLBACK* get_command_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:74</a>
     */
    default Optional<CefCommandHandler> getCommandHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for context menus. If no handler is provided the default implementation will be used.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_context_menu_handler_t* (CEF_CALLBACK* get_context_menu_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:81</a>
     */
    default Optional<CefContextMenuHandler> getContextMenuHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for dialogs. If no handler is provided the default implementation will be used.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_dialog_handler_t* (CEF_CALLBACK* get_dialog_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:90</a>
     */
    default Optional<CefDialogHandler> getDialogHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for browser display state events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_display_handler_t* (CEF_CALLBACK* get_display_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:97</a>
     */
    default Optional<CefDisplayHandler> getDisplayHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for download events. If no handler is returned downloads will not be allowed.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_download_handler_t* (CEF_CALLBACK* get_download_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:103</a>
     */
    default Optional<CefDownloadHandler> getDownloadHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for drag events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_drag_handler_t* (CEF_CALLBACK* get_drag_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:110</a>
     */
    default Optional<CefDragHandler> getDragHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for find result events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_find_handler_t* (CEF_CALLBACK* get_find_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:116</a>
     */
    default Optional<CefFindHandler> getFindHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for focus events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_focus_handler_t* (CEF_CALLBACK* get_focus_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:122</a>
     */
    default Optional<CefFocusHandler> getFocusHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for events related to CefFrame lifespan. This method will be called once during CefBrowser
     * creation and the result will be cached for performance reasons.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_frame_handler_t* (CEF_CALLBACK* get_frame_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:128</a>
     */
    default Optional<CefFrameHandler> getFrameHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for permission requests.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_permission_handler_t* (CEF_CALLBACK* get_permission_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:136</a>
     */
    default Optional<CefPermissionHandler> getPermissionHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for JavaScript dialogs. If no handler is provided the default implementation will be used.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_jsdialog_handler_t* (CEF_CALLBACK* get_jsdialog_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:144</a>
     */
    default Optional<CefJsDialogHandler> getJsDialogHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for keyboard events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_keyboard_handler_t* (CEF_CALLBACK* get_keyboard_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:151</a>
     */
    default Optional<CefKeyboardHandler> getKeyboardHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for browser life span events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_life_span_handler_t* (CEF_CALLBACK* get_life_span_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:157</a>
     */
    default Optional<CefLifeSpanHandler> getLifeSpanHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for browser load status events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_load_handler_t* (CEF_CALLBACK* get_load_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:84</a>
     */
    default Optional<CefLoadHandler> getLoadHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for printing on Linux. If a print handler is not provided then printing will not be supported
     * on the Linux platform.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_print_handler_t* (CEF_CALLBACK* get_print_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:169</a>
     */
    default Optional<CefPrintHandler> getPrintHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for off-screen rendering events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_render_handler_t* (CEF_CALLBACK* get_render_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:176</a>
     */
    default Optional<CefRenderHandler> getRenderHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for browser request events.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>cef_request_handler_t* (CEF_CALLBACK* get_request_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__client_8h.html">cef_client.h:182</a>
     */
    default Optional<CefRequestHandler> getRequestHandler() {
        return Optional.empty();
    }

    /**
     * Called when a new message is received from a different process. Return {@code true} if the message was handled or
     * {@code false} otherwise. It is safe to keep a reference to {@code message} outside of this callback.
     *
     * <p>Definition generated from cef_client_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_process_message_received)(struct _cef_client_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, cef_process_id_t source_process, struct _cef_process_message_t* message);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:136</a>
     */
    default boolean onProcessMessageReceived(
            @Nonnull CefBrowser browser,
            @Nonnull CefFrame frame,
            @Nonnull CefProcessId sourceProcess,
            @Nonnull CefProcessMessage message) {
        return false;
    }
}
