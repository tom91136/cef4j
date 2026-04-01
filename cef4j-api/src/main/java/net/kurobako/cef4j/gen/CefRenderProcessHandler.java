// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to implement render process callbacks. The methods of this class will be called on the render process main
 * thread ({@code TID_RENDERER}) unless otherwise indicated.
 *
 * <p>Definition generated from cef_render_process_handler_capi.h
 *
 * <pre>typedef struct _cef_render_process_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_render_process_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:50</a>
 */
public interface CefRenderProcessHandler extends CefClientHandler {

    /**
     * Called after WebKit has been initialized.
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_web_kit_initialized)(struct _cef_render_process_handler_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:60</a>
     */
    default void onWebKitInitialized() {}

    /**
     * Called after a browser has been created. When browsing cross-origin a new browser will be created before the old
     * browser with the same identifier is destroyed. {@code extra_info} is an optional read-only value originating from
     * CefBrowserHost.createBrowser(), CefBrowserHost.createBrowserSync(),
     * {@link CefLifeSpanHandler#onBeforePopup(CefBrowser, CefFrame, int, String, String, CefWindowOpenDisposition,
     * boolean, NativePointer, CefMutableWindowInfo, AtomicReference<CefClient>, CefMutableBrowserSettings,
     * AtomicReference<CefDictionaryValue>, int[])} or CefBrowserView.createBrowserView().
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_browser_created)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_dictionary_value_t* extra_info);
     * </pre>
     *
     * @param extraInfo may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:66</a>
     */
    default void onBrowserCreated(@Nonnull CefBrowser browser, @Nullable CefDictionaryValue extraInfo) {}

    /**
     * Called before a browser is destroyed.
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_browser_destroyed)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:78</a>
     */
    default void onBrowserDestroyed(@Nonnull CefBrowser browser) {}

    /**
     * Return the handler for browser load status events.
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>cef_load_handler_t* (CEF_CALLBACK* get_load_handler)(struct _cef_render_process_handler_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:84</a>
     */
    default Optional<CefLoadHandler> getLoadHandler() {
        return Optional.empty();
    }

    /**
     * Called immediately after the V8 context for a frame has been created. To retrieve the JavaScript 'window' object
     * use the {@link CefV8Context#getGlobal()} method. V8 handles can only be accessed from the thread on which they
     * are created. A task runner for posting tasks on the associated thread can be retrieved via the
     * {@link CefV8Context#getTaskRunner()} method.
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_context_created)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:90</a>
     */
    default void onContextCreated(
            @Nonnull CefBrowser browser, @Nonnull CefFrame frame, @Nonnull CefV8Context context) {}

    /**
     * Called immediately before the V8 context for a frame is released. No references to the context should be kept
     * after this method is called.
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_context_released)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:102</a>
     */
    default void onContextReleased(
            @Nonnull CefBrowser browser, @Nonnull CefFrame frame, @Nonnull CefV8Context context) {}

    /**
     * Called for global uncaught exceptions in a frame. Execution of this callback is disabled by default. To enable
     * set cef_settings_t.uncaught_exception_stack_size > 0.
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_uncaught_exception)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context, struct _cef_v8_exception_t* exception, struct _cef_v8_stack_trace_t* stackTrace);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:111</a>
     */
    default void onUncaughtException(
            @Nonnull CefBrowser browser,
            @Nonnull CefFrame frame,
            @Nonnull CefV8Context context,
            @Nonnull CefV8Exception exception,
            @Nonnull CefV8StackTrace stacktrace) {}

    /**
     * Called when a new node in the the browser gets focus. The {@code node} value may be empty if no specific node has
     * gained focus. The node object passed to this method represents a snapshot of the DOM at the time this method is
     * executed. DOM objects are only valid for the scope of this method. Do not keep references to or attempt to access
     * any DOM objects outside the scope of this method.
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_focused_node_changed)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_domnode_t* node);
     * </pre>
     *
     * @param frame may be null
     * @param node may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:123</a>
     */
    default void onFocusedNodeChanged(
            @Nonnull CefBrowser browser, @Nullable CefFrame frame, @Nullable CefDomNode node) {}

    /**
     * Called when a new message is received from a different process. Return {@code true} if the message was handled or
     * {@code false} otherwise. It is safe to keep a reference to {@code message} outside of this callback.
     *
     * <p>Definition generated from cef_render_process_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_process_message_received)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, cef_process_id_t source_process, struct _cef_process_message_t* message);
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
