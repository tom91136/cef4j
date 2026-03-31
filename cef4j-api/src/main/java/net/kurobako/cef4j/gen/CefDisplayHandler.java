// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser display state. The methods of this class will be called
 * on the UI thread.
 */
public interface CefDisplayHandler {

    /** Called when a frame's address has changed. */
    default void onAddressChange(long browser, long frame, @Nonnull String url) {}

    /**
     * Called when the page title changes.
     *
     * @param title may be null
     */
    default void onTitleChange(long browser, @Nullable String title) {}

    default void onFaviconUrlchange(long browser, @Nonnull java.util.List<String> iconUrls) {}

    /**
     * Called when web content in the page has toggled fullscreen mode. If |fullscreen| is true the content will
     * automatically be sized to fill the browser content area. If |fullscreen| is false the content will automatically
     * return to its original size and position. With Alloy style the client is responsible for triggering the
     * fullscreen transition (for example, by calling CefWindow::SetFullscreen when using Views). With Chrome style the
     * fullscreen transition will be triggered automatically. The CefWindowDelegate::OnWindowFullscreenTransition method
     * will be called during the fullscreen transition for notification purposes.
     */
    default void onFullscreenModeChange(long browser, boolean fullscreen) {}

    /**
     * Called when the browser is about to display a tooltip. |text| contains the text that will be displayed in the
     * tooltip. To handle the display of the tooltip yourself return true. Otherwise, you can optionally modify |text|
     * and then return false to allow the browser to display the tooltip. When window rendering is disabled the
     * application is responsible for drawing tooltips and the return value is ignored.
     *
     * @param text may be null
     */
    default boolean onTooltip(long browser, @Nullable String text) {
        return false;
    }

    /**
     * Called when the browser receives a status message. |value| contains the text that will be displayed in the status
     * message.
     *
     * @param value may be null
     */
    default void onStatusMessage(long browser, @Nullable String value) {}

    /**
     * Called to display a console message. Return true to stop the message from being output to the console.
     *
     * @param message may be null
     * @param source may be null
     */
    default boolean onConsoleMessage(
            long browser, @Nonnull CefLogSeverity level, @Nullable String message, @Nullable String source, int line) {
        return false;
    }

    /**
     * Called when auto-resize is enabled via CefBrowserHost::SetAutoResizeEnabled and the contents have auto-resized.
     * |new_size| will be the desired size in DIP coordinates. Return true if the resize was handled or false for
     * default handling.
     */
    default boolean onAutoResize(long browser, @Nonnull CefSize newSize) {
        return false;
    }

    /** Called when the overall page loading progress has changed. |progress| ranges from 0.0 to 1.0. */
    default void onLoadingProgressChange(long browser, double progress) {}

    /**
     * Called when the browser's cursor has changed. If |type| is CT_CUSTOM then |custom_cursor_info| will be populated
     * with the custom cursor information. Return true if the cursor change was handled or false for default handling.
     */
    default boolean onCursorChange(long browser, long cursor, @Nonnull CefCursorType type, long customCursorInfo) {
        return false;
    }

    /** Called when the browser's access to an audio and/or video source has changed. */
    default void onMediaAccessChange(long browser, boolean hasVideoAccess, boolean hasAudioAccess) {}

    /**
     * Called when JavaScript is requesting new bounds via window.moveTo/By() or window.resizeTo/By(). |new_bounds| are
     * in DIP screen coordinates. With Views-hosted browsers |new_bounds| are the desired bounds for the containing
     * CefWindow and may be passed directly to CefWindow::SetBounds. With external (client-provided) parent on macOS and
     * Windows |new_bounds| are the desired frame bounds for the containing root window. With other non-Views browsers
     * |new_bounds| are the desired bounds for the browser content only unless the client implements either
     * CefDisplayHandler::GetRootWindowScreenRect for windowed browsers or CefRenderHandler::GetWindowScreenRect for
     * windowless browsers. Clients may expand browser content bounds to window bounds using OS-specific or CefDisplay
     * methods. Return true if this method was handled or false for default handling. Default move/resize behavior is
     * only provided with Views-hosted Chrome style browsers.
     *
     * <p>Added in CEF API version 13700.
     */
    default boolean onContentsBoundsChange(long browser, @Nonnull CefRect newBounds) {
        return false;
    }

    /**
     * Called to retrieve the external (client-provided) root window rectangle in screen DIP coordinates. Only called
     * for windowed browsers on Windows and Linux. Return true if the rectangle was provided. Return false to use the
     * root window bounds on Windows or the browser content bounds on Linux. For additional usage details see
     * CefBrowserHost::NotifyScreenInfoChanged.
     *
     * <p>Added in CEF API version 13700.
     */
    default boolean getRootWindowScreenRect(long browser, @Nonnull CefMutableRect rect) {
        return false;
    }
}
