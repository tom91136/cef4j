// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to implement render process callbacks. The methods of this class will be called on the render process main thread ({@code TID_RENDERER}) unless otherwise indicated.
 * <p>Definition generated from cef_render_process_handler_capi.h
 * <pre>typedef struct _cef_render_process_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_render_process_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:50</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefRenderProcessHandler extends CefClientHandler {

    /**
     * Called after WebKit has been initialized.
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_web_kit_initialized)(struct _cef_render_process_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:60</a>
     */
    default void onWebKitInitialized() {
    }

    /**
     * Called after a browser has been created. When browsing cross-origin a new browser will be created before the old browser with the same identifier is destroyed. {@code extra_info} is an optional read-only value originating from net.kurobako.cef4j.gen.CefBrowserHost.createBrowser(), net.kurobako.cef4j.gen.CefBrowserHost.createBrowserSync(), {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforePopup(CefBrowser, CefFrame, int, String, String, CefWindowOpenDisposition, boolean, CefPopupFeatures, CefWindowInfo.Mutable, java.util.concurrent.atomic.AtomicReference, CefBrowserSettings.Mutable, java.util.concurrent.atomic.AtomicReference, int[])} or net.kurobako.cef4j.gen.views.CefBrowserView.createBrowserView().
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_browser_created)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_dictionary_value_t* extra_info);</pre>
     *
     * @param extraInfo may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:66</a>
     */
    default void onBrowserCreated(@Nullable CefBrowser browser, @Nullable CefDictionaryValue extraInfo) {
    }

    /**
     * Called before a browser is destroyed.
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_browser_destroyed)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:78</a>
     */
    default void onBrowserDestroyed(@Nullable CefBrowser browser) {
    }

    /**
     * Return the handler for browser load status events.
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>cef_load_handler_t* (CEF_CALLBACK* get_load_handler)(struct _cef_render_process_handler_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:84</a>
     */
    default Optional<CefLoadHandler> getLoadHandler() {
        return Optional.empty();
    }

    /**
     * Called immediately after the V8 context for a frame has been created. To retrieve the JavaScript 'window' object use the {@link net.kurobako.cef4j.gen.CefV8Context#getGlobal()} method. V8 handles can only be accessed from the thread on which they are created. A task runner for posting tasks on the associated thread can be retrieved via the {@link net.kurobako.cef4j.gen.CefV8Context#getTaskRunner()} method.
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_context_created)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:90</a>
     */
    default void onContextCreated(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefV8Context context) {
    }

    /**
     * Called immediately before the V8 context for a frame is released. No references to the context should be kept after this method is called.
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_context_released)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:102</a>
     */
    default void onContextReleased(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefV8Context context) {
    }

    /**
     * Called for global uncaught exceptions in a frame. Execution of this callback is disabled by default. To enable set cef_settings_t.uncaught_exception_stack_size > 0.
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_uncaught_exception)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_v8_context_t* context, struct _cef_v8_exception_t* exception, struct _cef_v8_stack_trace_t* stackTrace);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:111</a>
     */
    default void onUncaughtException(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefV8Context context, @Nullable CefV8Exception exception, @Nullable CefV8StackTrace stackTrace) {
    }

    /**
     * Called when a new node in the the browser gets focus. The {@code node} value may be empty if no specific node has gained focus. The node object passed to this method represents a snapshot of the DOM at the time this method is executed. DOM objects are only valid for the scope of this method. Do not keep references to or attempt to access any DOM objects outside the scope of this method.
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_focused_node_changed)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, struct _cef_domnode_t* node);</pre>
     *
     * @param frame may be null
     * @param node may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:123</a>
     */
    default void onFocusedNodeChanged(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefDomNode node) {
    }

    /**
     * Called when a new message is received from a different process. Return {@code true} if the message was handled or {@code false} otherwise. It is safe to keep a reference to {@code message} outside of this callback.
     * <p>Definition generated from cef_render_process_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_process_message_received)(struct _cef_render_process_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, cef_process_id_t source_process, struct _cef_process_message_t* message);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__render__process__handler_8h.html">cef_render_process_handler.h:136</a>
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
    class Delegating implements CefRenderProcessHandler {
        private final java.util.List<CefRenderProcessHandler> delegates;

        public Delegating(java.util.List<CefRenderProcessHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onWebKitInitialized() {
            for (CefRenderProcessHandler d : delegates) d.onWebKitInitialized();
        }

        @Override
        public void onBrowserCreated(@Nullable CefBrowser browser, @Nullable CefDictionaryValue extraInfo) {
            for (CefRenderProcessHandler d : delegates) d.onBrowserCreated(browser, extraInfo);
        }

        @Override
        public void onBrowserDestroyed(@Nullable CefBrowser browser) {
            for (CefRenderProcessHandler d : delegates) d.onBrowserDestroyed(browser);
        }

        @Override
        public Optional<CefLoadHandler> getLoadHandler() {
            java.util.ArrayList<CefLoadHandler> collected = new java.util.ArrayList<>();
            for (CefRenderProcessHandler d : delegates) d.getLoadHandler().ifPresent(collected::add);
            return collected.isEmpty()
                    ? Optional.empty()
                    : Optional.of(new CefLoadHandler.Delegating(collected));
        }

        @Override
        public void onContextCreated(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefV8Context context) {
            for (CefRenderProcessHandler d : delegates) d.onContextCreated(browser, frame, context);
        }

        @Override
        public void onContextReleased(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefV8Context context) {
            for (CefRenderProcessHandler d : delegates) d.onContextReleased(browser, frame, context);
        }

        @Override
        public void onUncaughtException(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefV8Context context, @Nullable CefV8Exception exception, @Nullable CefV8StackTrace stackTrace) {
            for (CefRenderProcessHandler d : delegates) d.onUncaughtException(browser, frame, context, exception, stackTrace);
        }

        @Override
        public void onFocusedNodeChanged(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable CefDomNode node) {
            for (CefRenderProcessHandler d : delegates) d.onFocusedNodeChanged(browser, frame, node);
        }

        @Override
        public boolean onProcessMessageReceived(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefProcessId sourceProcess, @Nullable CefProcessMessage message) {
            for (CefRenderProcessHandler d : delegates) {
                if (d.onProcessMessageReceived(browser, frame, sourceProcess, message)) return true;
            }
            if (!delegates.isEmpty()) return false;
            return false;
        }
    }

}
