// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to CefFrame life span. The order of callbacks is: (1) During initial CefBrowserHost creation and navigation of the main frame:
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onFrameCreated(CefBrowser, CefFrame)} => The initial main frame object has been</li>
 * </ul>
 * created. Any commands will be queued until the frame is attached.
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onMainFrameChanged(CefBrowser, CefFrame, CefFrame)} => The initial main frame object has</li>
 * </ul>
 * been assigned to the browser.
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onAfterCreated(CefBrowser)} => The browser is now valid and can be</li>
 * </ul>
 * used.
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onFrameAttached(CefBrowser, CefFrame, boolean)} => The initial main frame object is now</li>
 * </ul>
 * connected to its peer in the renderer process. Commands can be routed. (2) During further CefBrowserHost navigation/loading of the main frame and/or sub-frames:
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onFrameCreated(CefBrowser, CefFrame)} => A new main frame or sub-frame object</li>
 * </ul>
 * has been created. Any commands will be queued until the frame is attached.
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onFrameAttached(CefBrowser, CefFrame, boolean)} => A new main frame or sub-frame object</li>
 * </ul>
 * is now connected to its peer in the renderer process. Commands can be routed.
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onFrameDetached(CefBrowser, CefFrame)} => An existing main frame or sub-frame</li>
 * </ul>
 * object has lost its connection to the renderer process. If multiple objects are detached at the same time then notifications will be sent for any sub-frame objects before the main frame object. Commands can no longer be routed and will be discarded.
 * <ul>
 * <li>CefFremeHadler.onFrameDestroyed() => An existing main frame or sub-frame</li>
 * </ul>
 * object has been destroyed.
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onMainFrameChanged(CefBrowser, CefFrame, CefFrame)} => A new main frame object has been</li>
 * </ul>
 * assigned to the browser. This will only occur with cross-origin navigation or re-navigation after renderer process termination (due to crashes, etc). (3) During final CefBrowserHost destruction of the main frame:
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onFrameDetached(CefBrowser, CefFrame)} => Any sub-frame objects have lost their</li>
 * </ul>
 * connection to the renderer process. Commands can no longer be routed and will be discarded.
 * <ul>
 * <li>CefFreameHandler.onFrameDestroyed() => Any sub-frame objects have been</li>
 * </ul>
 * destroyed.
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} => The browser has been destroyed.</li>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onFrameDetached(CefBrowser, CefFrame)} => The main frame object have lost its</li>
 * </ul>
 * connection to the renderer process. Notifications will be sent for any sub-frame objects before the main frame object. Commands can no longer be routed and will be discarded.
 * <ul>
 * <li>CefFreameHandler.onFrameDestroyed() => The main frame object has been</li>
 * </ul>
 * destroyed.
 * <ul>
 * <li>{@link net.kurobako.cef4j.gen.CefFrameHandler#onMainFrameChanged(CefBrowser, CefFrame, CefFrame)} => The final main frame object has</li>
 * </ul>
 * been removed from the browser. Special handling applies for cross-origin loading on creation/navigation of sub-frames, and cross-origin loading on creation of new popup browsers. A temporary frame will first be created in the parent frame's renderer process. This temporary frame will never attach and will be discarded after the real cross-origin frame is created in the new/target renderer process. The client will receive creation callbacks for the temporary frame, followed by cross-origin navigation callbacks (2) for the transition from the temporary frame to the real frame. The temporary frame will not receive or execute commands during this transitional period (any sent commands will be discarded). When the main frame navigates to a different origin the OnMainFrameChanged callback (2) will be executed with the old and new main frame objects. Callbacks will not be executed for placeholders that may be created during pre-commit navigation for sub-frames that do not yet exist in the renderer process. Placeholders will have {@link net.kurobako.cef4j.gen.CefFrame#getIdentifier()} == -4. The methods of this class will be called on the UI thread unless otherwise indicated.
 * <p>Definition generated from cef_frame_handler_capi.h
 * <pre>typedef struct _cef_frame_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_frame_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame__handler_8h.html">cef_frame_handler.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefFrameHandler extends CefClientHandler {

    /**
     * Called when a new frame is created. This will be the first notification that references {@code frame}. Any commands that require transport to the associated renderer process (LoadRequest, SendProcessMessage, GetSource, etc.) will be queued. The queued commands will be sent before OnFrameAttached or discarded before OnFrameDestroyed if the frame never attaches.
     * <p>Definition generated from cef_frame_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_frame_created)(struct _cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame__handler_8h.html">cef_frame_handler.h:117</a>
     */
    default void onFrameCreated(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
    }

    /**
     * Called when an existing frame is destroyed. This will be the last notification that references {@code frame} and {@link net.kurobako.cef4j.gen.CefFrame#isValid()} will return {@code false} for {@code frame}. If called during browser destruction and after {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} then {@link net.kurobako.cef4j.gen.CefBrowser#isValid()} will return {@code false} for {@code browser}. Any queued commands that have not been sent will be discarded before this callback.
     * <p>Definition generated from cef_frame_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_frame_destroyed)(struct _cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame__handler_8h.html">cef_frame_handler.h:129</a>
     */
    default void onFrameDestroyed(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
    }

    /**
     * Called when a frame can begin routing commands to/from the associated renderer process. {@code reattached} will be {@code true} if the frame was re-attached after exiting the BackForwardCache or after encountering a recoverable connection error. Any queued commands will now have been dispatched. This method will not be called for temporary frames created during cross-origin navigation.
     * <p>Definition generated from cef_frame_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_frame_attached)(struct _cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int reattached);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame__handler_8h.html">cef_frame_handler.h:141</a>
     */
    default void onFrameAttached(@Nullable CefBrowser browser, @Nullable CefFrame frame, boolean reattached) {
    }

    /**
     * Called when a frame loses its connection to the renderer process. This may occur when a frame is destroyed, enters the BackForwardCache, or encounters a rare connection error. In the case of frame destruction this call will be followed by a (potentially async) call to OnFrameDestroyed. If frame destruction is occuring synchronously then {@link net.kurobako.cef4j.gen.CefFrame#isValid()} will return {@code false} for {@code frame}. If called during browser destruction and after {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} then {@link net.kurobako.cef4j.gen.CefBrowser#isValid()} will return {@code false} for {@code browser}. If, in the non-destruction case, the same frame later exits the BackForwardCache or recovers from a connection error then there will be a follow-up call to OnFrameAttached. This method will not be called for temporary frames created during cross-origin navigation.
     * <p>Definition generated from cef_frame_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_frame_detached)(struct _cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame__handler_8h.html">cef_frame_handler.h:154</a>
     */
    default void onFrameDetached(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
    }

    /**
     * Called when the main frame changes due to (a) initial browser creation, (b) final browser destruction, (c) cross-origin navigation or (d) re-navigation after renderer process termination (due to crashes, etc). {@code old_frame} will be {@code null} and {@code new_frame} will be non-{@code null} when a main frame is assigned to {@code browser} for the first time. {@code old_frame} will be non-{@code null} and {@code new_frame} will be {@code null} when a main frame is removed from {@code browser} for the last time. Both {@code old_frame} and {@code new_frame} will be non-{@code null} for cross-origin navigations or re-navigation after renderer process termination. This method will be called after OnFrameCreated() for {@code new_frame} and/or after OnFrameDestroyed() for {@code old_frame}. If called during browser destruction and after {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} then {@link net.kurobako.cef4j.gen.CefBrowser#isValid()} will return {@code false} for {@code browser}.
     * <p>Definition generated from cef_frame_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_main_frame_changed)(struct _cef_frame_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* old_frame, struct _cef_frame_t* new_frame);</pre>
     *
     * @param oldFrame may be null
     * @param newFrame may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__frame__handler_8h.html">cef_frame_handler.h:171</a>
     */
    default void onMainFrameChanged(@Nullable CefBrowser browser, @Nullable CefFrame oldFrame, @Nullable CefFrame newFrame) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefFrameHandler {
        private final java.util.List<CefFrameHandler> delegates;

        public Delegating(java.util.List<CefFrameHandler> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onFrameCreated(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
            for (CefFrameHandler d : delegates) d.onFrameCreated(browser, frame);
        }

        @Override
        public void onFrameDestroyed(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
            for (CefFrameHandler d : delegates) d.onFrameDestroyed(browser, frame);
        }

        @Override
        public void onFrameAttached(@Nullable CefBrowser browser, @Nullable CefFrame frame, boolean reattached) {
            for (CefFrameHandler d : delegates) d.onFrameAttached(browser, frame, reattached);
        }

        @Override
        public void onFrameDetached(@Nullable CefBrowser browser, @Nullable CefFrame frame) {
            for (CefFrameHandler d : delegates) d.onFrameDetached(browser, frame);
        }

        @Override
        public void onMainFrameChanged(@Nullable CefBrowser browser, @Nullable CefFrame oldFrame, @Nullable CefFrame newFrame) {
            for (CefFrameHandler d : delegates) d.onMainFrameChanged(browser, oldFrame, newFrame);
        }
    }

}
