// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to provide handler implementations.
 * <p>Definition generated from cef_client_capi.h
 * <pre>typedef struct _cef_client_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_client_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:62</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefClient extends CefClientHandler {

    /**
     * Return the handler for audio rendering events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_audio_handler_t* (CEF_CALLBACK* get_audio_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:68</a>
     */
    default Optional<CefAudioHandler> getAudioHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for commands. If no handler is provided the default implementation will be used.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_command_handler_t* (CEF_CALLBACK* get_command_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:74</a>
     */
    default Optional<CefCommandHandler> getCommandHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for context menus. If no handler is provided the default implementation will be used.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_context_menu_handler_t* (CEF_CALLBACK* get_context_menu_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:81</a>
     */
    default Optional<CefContextMenuHandler> getContextMenuHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for dialogs. If no handler is provided the default implementation will be used.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_dialog_handler_t* (CEF_CALLBACK* get_dialog_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:90</a>
     */
    default Optional<CefDialogHandler> getDialogHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for browser display state events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_display_handler_t* (CEF_CALLBACK* get_display_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:97</a>
     */
    default Optional<CefDisplayHandler> getDisplayHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for download events. If no handler is returned downloads will not be allowed.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_download_handler_t* (CEF_CALLBACK* get_download_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:103</a>
     */
    default Optional<CefDownloadHandler> getDownloadHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for drag events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_drag_handler_t* (CEF_CALLBACK* get_drag_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:110</a>
     */
    default Optional<CefDragHandler> getDragHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for find result events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_find_handler_t* (CEF_CALLBACK* get_find_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:116</a>
     */
    default Optional<CefFindHandler> getFindHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for focus events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_focus_handler_t* (CEF_CALLBACK* get_focus_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:122</a>
     */
    default Optional<CefFocusHandler> getFocusHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for events related to CefFrame lifespan. This method will be called once during CefBrowser creation and the result will be cached for performance reasons.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_frame_handler_t* (CEF_CALLBACK* get_frame_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:128</a>
     */
    default Optional<CefFrameHandler> getFrameHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for permission requests.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_permission_handler_t* (CEF_CALLBACK* get_permission_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:136</a>
     */
    default Optional<CefPermissionHandler> getPermissionHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for JavaScript dialogs. If no handler is provided the default implementation will be used.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_jsdialog_handler_t* (CEF_CALLBACK* get_jsdialog_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:144</a>
     */
    default Optional<CefJsDialogHandler> getJsDialogHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for keyboard events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_keyboard_handler_t* (CEF_CALLBACK* get_keyboard_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:151</a>
     */
    default Optional<CefKeyboardHandler> getKeyboardHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for browser life span events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_life_span_handler_t* (CEF_CALLBACK* get_life_span_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:157</a>
     */
    default Optional<CefLifeSpanHandler> getLifeSpanHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for browser load status events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_load_handler_t* (CEF_CALLBACK* get_load_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:163</a>
     */
    default Optional<CefLoadHandler> getLoadHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for printing on Linux. If a print handler is not provided then printing will not be supported on the Linux platform.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_print_handler_t* (CEF_CALLBACK* get_print_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:169</a>
     */
    default Optional<CefPrintHandler> getPrintHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for off-screen rendering events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_render_handler_t* (CEF_CALLBACK* get_render_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:176</a>
     */
    default Optional<CefRenderHandler> getRenderHandler() {
        return Optional.empty();
    }

    /**
     * Return the handler for browser request events.
     * <p>Definition generated from cef_client_capi.h
     * <pre>cef_request_handler_t* (CEF_CALLBACK* get_request_handler)(struct _cef_client_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:182</a>
     */
    default Optional<CefRequestHandler> getRequestHandler() {
        return Optional.empty();
    }

    /**
     * Called when a new message is received from a different process. Return {@code true} if the message was handled or {@code false} otherwise.  It is safe to keep a reference to {@code message} outside of this callback.
     * <p>Definition generated from cef_client_capi.h
     * <pre>int (CEF_CALLBACK* on_process_message_received)(struct _cef_client_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, cef_process_id_t source_process, struct _cef_process_message_t* message);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__client_8h.html">cef_client.h:188</a>
     */
    default boolean onProcessMessageReceived(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefProcessId sourceProcess, @Nullable CefProcessMessage message) {
        return false;
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefClient {
        private final java.util.List<CefClient> delegates;

        public Delegating(java.util.List<CefClient> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public Optional<CefAudioHandler> getAudioHandler() {
            java.util.ArrayList<CefAudioHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getAudioHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefAudioHandler.Delegating(collected));
        }

        @Override
        public Optional<CefCommandHandler> getCommandHandler() {
            java.util.ArrayList<CefCommandHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getCommandHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefCommandHandler.Delegating(collected));
        }

        @Override
        public Optional<CefContextMenuHandler> getContextMenuHandler() {
            java.util.ArrayList<CefContextMenuHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getContextMenuHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefContextMenuHandler.Delegating(collected));
        }

        @Override
        public Optional<CefDialogHandler> getDialogHandler() {
            java.util.ArrayList<CefDialogHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getDialogHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefDialogHandler.Delegating(collected));
        }

        @Override
        public Optional<CefDisplayHandler> getDisplayHandler() {
            java.util.ArrayList<CefDisplayHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getDisplayHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefDisplayHandler.Delegating(collected));
        }

        @Override
        public Optional<CefDownloadHandler> getDownloadHandler() {
            java.util.ArrayList<CefDownloadHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getDownloadHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefDownloadHandler.Delegating(collected));
        }

        @Override
        public Optional<CefDragHandler> getDragHandler() {
            java.util.ArrayList<CefDragHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getDragHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefDragHandler.Delegating(collected));
        }

        @Override
        public Optional<CefFindHandler> getFindHandler() {
            java.util.ArrayList<CefFindHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getFindHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefFindHandler.Delegating(collected));
        }

        @Override
        public Optional<CefFocusHandler> getFocusHandler() {
            java.util.ArrayList<CefFocusHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getFocusHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefFocusHandler.Delegating(collected));
        }

        @Override
        public Optional<CefFrameHandler> getFrameHandler() {
            java.util.ArrayList<CefFrameHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getFrameHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefFrameHandler.Delegating(collected));
        }

        @Override
        public Optional<CefPermissionHandler> getPermissionHandler() {
            java.util.ArrayList<CefPermissionHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getPermissionHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefPermissionHandler.Delegating(collected));
        }

        @Override
        public Optional<CefJsDialogHandler> getJsDialogHandler() {
            java.util.ArrayList<CefJsDialogHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getJsDialogHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefJsDialogHandler.Delegating(collected));
        }

        @Override
        public Optional<CefKeyboardHandler> getKeyboardHandler() {
            java.util.ArrayList<CefKeyboardHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getKeyboardHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefKeyboardHandler.Delegating(collected));
        }

        @Override
        public Optional<CefLifeSpanHandler> getLifeSpanHandler() {
            java.util.ArrayList<CefLifeSpanHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getLifeSpanHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefLifeSpanHandler.Delegating(collected));
        }

        @Override
        public Optional<CefLoadHandler> getLoadHandler() {
            java.util.ArrayList<CefLoadHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getLoadHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefLoadHandler.Delegating(collected));
        }

        @Override
        public Optional<CefPrintHandler> getPrintHandler() {
            java.util.ArrayList<CefPrintHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getPrintHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefPrintHandler.Delegating(collected));
        }

        @Override
        public Optional<CefRenderHandler> getRenderHandler() {
            java.util.ArrayList<CefRenderHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getRenderHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefRenderHandler.Delegating(collected));
        }

        @Override
        public Optional<CefRequestHandler> getRequestHandler() {
            java.util.ArrayList<CefRequestHandler> collected = new java.util.ArrayList<>();
            for (CefClient d : delegates) d.getRequestHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefRequestHandler.Delegating(collected));
        }

        @Override
        public boolean onProcessMessageReceived(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefProcessId sourceProcess, @Nullable CefProcessMessage message) {
            for (CefClient d : delegates) {
                if (d.onProcessMessageReceived(browser, frame, sourceProcess, message)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
