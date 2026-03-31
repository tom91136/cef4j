// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Class used to implement render process callbacks. The methods of this class will be called on the render process main
 * thread (TID_RENDERER) unless otherwise indicated.
 */
public interface CefRenderProcessHandler {

    /** Called after WebKit has been initialized. */
    default void onWebKitInitialized() {}

    /**
     * Called after a browser has been created. When browsing cross-origin a new browser will be created before the old
     * browser with the same identifier is destroyed. |extra_info| is an optional read-only value originating from
     * CefBrowserHost::CreateBrowser(), CefBrowserHost::CreateBrowserSync(), CefLifeSpanHandler::OnBeforePopup() or
     * CefBrowserView::CreateBrowserView().
     *
     * @param extraInfo may be null
     */
    default void onBrowserCreated(long browser, long extraInfo) {}

    /** Called before a browser is destroyed. */
    default void onBrowserDestroyed(long browser) {}

    /** Return the handler for browser load status events. */
    default long getLoadHandler() {
        return 0L;
    }

    /**
     * Called immediately after the V8 context for a frame has been created. To retrieve the JavaScript 'window' object
     * use the CefV8Context::GetGlobal() method. V8 handles can only be accessed from the thread on which they are
     * created. A task runner for posting tasks on the associated thread can be retrieved via the
     * CefV8Context::GetTaskRunner() method.
     */
    default void onContextCreated(long browser, long frame, long context) {}

    /**
     * Called immediately before the V8 context for a frame is released. No references to the context should be kept
     * after this method is called.
     */
    default void onContextReleased(long browser, long frame, long context) {}

    /**
     * Called for global uncaught exceptions in a frame. Execution of this callback is disabled by default. To enable
     * set cef_settings_t.uncaught_exception_stack_size > 0.
     */
    default void onUncaughtException(long browser, long frame, long context, long exception, long stackTrace) {}

    /**
     * Called when a new node in the the browser gets focus. The |node| value may be empty if no specific node has
     * gained focus. The node object passed to this method represents a snapshot of the DOM at the time this method is
     * executed. DOM objects are only valid for the scope of this method. Do not keep references to or attempt to access
     * any DOM objects outside the scope of this method.
     *
     * @param frame may be null
     * @param node may be null
     */
    default void onFocusedNodeChanged(long browser, long frame, long node) {}

    /**
     * Called when a new message is received from a different process. Return true if the message was handled or false
     * otherwise. It is safe to keep a reference to |message| outside of this callback.
     */
    default boolean onProcessMessageReceived(
            long browser, long frame, @Nonnull CefProcessId sourceProcess, long message) {
        return false;
    }
}
