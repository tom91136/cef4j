// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser load status. The methods of this class will be called on
 * the browser process UI thread or render process main thread ({@code TID_RENDERER}).
 *
 * <p>Definition generated from cef_load_handler_capi.h
 *
 * <pre>typedef struct _cef_load_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_load_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__load__handler_8h.html">cef_load_handler.h:45</a>
 */
public interface CefLoadHandler extends CefClientHandler {

    /**
     * Called when the loading state has changed. This callback will be executed twice -- once when loading is initiated
     * either programmatically or by user action, and once when loading is terminated due to completion, cancellation of
     * failure. It will be called before any calls to OnLoadStart and after all calls to OnLoadError and/or OnLoadEnd.
     *
     * <p>Definition generated from cef_load_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_loading_state_change)(struct _cef_load_handler_t* self, struct _cef_browser_t* browser, int isLoading, int canGoBack, int canGoForward);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__load__handler_8h.html">cef_load_handler.h:56</a>
     */
    default void onLoadingStateChange(
            @Nullable CefBrowser browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {}

    /**
     * Called after a navigation has been committed and before the browser begins loading contents in the frame. The
     * {@code frame} value will never be empty --call the IsMain() method to check if this frame is the main frame.
     * {@code transition_type} provides information about the source of the navigation and an accurate value is only
     * available in the browser process. Multiple frames may be loading at the same time. Sub-frames may start or
     * continue loading after the main frame load has ended. This method will not be called for same page navigations
     * (fragments, history state, etc.) or for navigations that fail or are canceled before commit. For notification of
     * overall browser load status use OnLoadingStateChange instead.
     *
     * <p>Definition generated from cef_load_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_load_start)(struct _cef_load_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, cef_transition_type_t transition_type);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__load__handler_8h.html">cef_load_handler.h:69</a>
     */
    default void onLoadStart(
            @Nullable CefBrowser browser, @Nullable CefFrame frame, @Nonnull CefTransitionType transitionType) {}

    /**
     * Called when the browser is done loading a frame. The {@code frame} value will never be empty -- call the IsMain()
     * method to check if this frame is the main frame. Multiple frames may be loading at the same time. Sub-frames may
     * start or continue loading after the main frame load has ended. This method will not be called for same page
     * navigations (fragments, history state, etc.) or for navigations that fail or are canceled before commit. For
     * notification of overall browser load status use OnLoadingStateChange instead.
     *
     * <p>Definition generated from cef_load_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_load_end)(struct _cef_load_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, int httpStatusCode);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__load__handler_8h.html">cef_load_handler.h:86</a>
     */
    default void onLoadEnd(@Nullable CefBrowser browser, @Nullable CefFrame frame, int httpStatusCode) {}

    /**
     * Called when a navigation fails or is canceled. This method may be called by itself if before commit or in
     * combination with OnLoadStart/OnLoadEnd if after commit. {@code errorCode} is the error code number,
     * {@code errorText} is the error text and {@code failedUrl} is the URL that failed to load. See
     * net\base\net_error_list.h for complete descriptions of the error codes.
     *
     * <p>Definition generated from cef_load_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_load_error)(struct _cef_load_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, cef_errorcode_t errorCode, const cef_string_t* errorText, const cef_string_t* failedUrl);
     * </pre>
     *
     * @param errorText may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__load__handler_8h.html">cef_load_handler.h:101</a>
     */
    default void onLoadError(
            @Nullable CefBrowser browser,
            @Nullable CefFrame frame,
            @Nonnull CefErrorCode errorCode,
            @Nullable String errorText,
            @Nullable String failedUrl) {}
}
