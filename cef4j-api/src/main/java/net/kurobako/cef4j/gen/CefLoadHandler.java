// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser load status. The methods of this class will be called on
 * the browser process UI thread or render process main thread (TID_RENDERER).
 */
public interface CefLoadHandler {

    /**
     * Called when the loading state has changed. This callback will be executed twice -- once when loading is initiated
     * either programmatically or by user action, and once when loading is terminated due to completion, cancellation of
     * failure. It will be called before any calls to OnLoadStart and after all calls to OnLoadError and/or OnLoadEnd.
     */
    default void onLoadingStateChange(long browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {}

    /**
     * Called after a navigation has been committed and before the browser begins loading contents in the frame. The
     * |frame| value will never be empty -- call the IsMain() method to check if this frame is the main frame.
     * |transition_type| provides information about the source of the navigation and an accurate value is only available
     * in the browser process. Multiple frames may be loading at the same time. Sub-frames may start or continue loading
     * after the main frame load has ended. This method will not be called for same page navigations (fragments, history
     * state, etc.) or for navigations that fail or are canceled before commit. For notification of overall browser load
     * status use OnLoadingStateChange instead.
     */
    default void onLoadStart(long browser, long frame, @Nonnull CefTransitionType transitionType) {}

    /**
     * Called when the browser is done loading a frame. The |frame| value will never be empty -- call the IsMain()
     * method to check if this frame is the main frame. Multiple frames may be loading at the same time. Sub-frames may
     * start or continue loading after the main frame load has ended. This method will not be called for same page
     * navigations (fragments, history state, etc.) or for navigations that fail or are canceled before commit. For
     * notification of overall browser load status use OnLoadingStateChange instead.
     */
    default void onLoadEnd(long browser, long frame, int httpStatusCode) {}

    /**
     * Called when a navigation fails or is canceled. This method may be called by itself if before commit or in
     * combination with OnLoadStart/OnLoadEnd if after commit. |errorCode| is the error code number, |errorText| is the
     * error text and |failedUrl| is the URL that failed to load. See net\base\net_error_list.h for complete
     * descriptions of the error codes.
     *
     * @param errorText may be null
     */
    default void onLoadError(
            long browser,
            long frame,
            @Nonnull CefErrorcode errorCode,
            @Nullable String errorText,
            @Nonnull String failedUrl) {}
}
