// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to browser display state. The methods of this class will be called on the UI thread.
 * <p>Definition generated from cef_display_handler_capi.h
 * <pre>typedef struct _cef_display_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_display_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:46</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefDisplayHandler extends CefClientHandler {

    /**
     * Called when a frame's address has changed.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_address_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* url);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:53</a>
     */
    default void onAddressChange(@Nullable CefBrowser browser, @Nullable CefFrame frame, @Nullable String url) {
    }

    /**
     * Called when the page title changes.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_title_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* title);</pre>
     *
     * @param title may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:61</a>
     */
    default void onTitleChange(@Nullable CefBrowser browser, @Nullable String title) {
    }

    /**
     * Called when the page icon changes.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_favicon_urlchange)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, cef_string_list_t icon_urls);</pre>
     *
     * @param iconUrls may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:68</a>
     */
    default void onFaviconUrlChange(@Nullable CefBrowser browser, @Nullable List<String> iconUrls) {
    }

    /**
     * Called when web content in the page has toggled fullscreen mode. If {@code fullscreen} is {@code true} the content will automatically be sized to fill the browser content area. If {@code fullscreen} is {@code false} the content will automatically return to its original size and position. With Alloy style the client is responsible for triggering the fullscreen transition (for example, by calling {@link net.kurobako.cef4j.gen.views.CefWindow#setFullscreen(boolean)} when using Views). With Chrome style the fullscreen transition will be triggered automatically. The {@link net.kurobako.cef4j.gen.views.CefWindowDelegate#onWindowFullscreenTransition(CefWindow, boolean)} method will be called during the fullscreen transition for notification purposes.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_fullscreen_mode_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, int fullscreen);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:75</a>
     */
    default void onFullscreenModeChange(@Nullable CefBrowser browser, boolean fullscreen) {
    }

    /**
     * Called when the browser is about to display a tooltip. {@code text} contains the text that will be displayed in the tooltip. To handle the display of the tooltip yourself return {@code true}. Otherwise, you can optionally modify {@code text} and then return {@code false} to allow the browser to display the tooltip. When window rendering is disabled the application is responsible for drawing tooltips and the return value is ignored.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_tooltip)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, cef_string_t* text);</pre>
     *
     * @param text may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:90</a>
     */
    default boolean onTooltip(@Nullable CefBrowser browser, @Nullable String text) {
        return false;
    }

    /**
     * Called when the browser receives a status message. {@code value} contains the text that will be displayed in the status message.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_status_message)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* value);</pre>
     *
     * @param value may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:103</a>
     */
    default void onStatusMessage(@Nullable CefBrowser browser, @Nullable String value) {
    }

    /**
     * Called to display a console message. Return {@code true} to stop the message from being output to the console.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_console_message)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, cef_log_severity_t level, const cef_string_t* message, const cef_string_t* source, int line);</pre>
     *
     * @param message may be null
     * @param source may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:111</a>
     */
    default boolean onConsoleMessage(@Nullable CefBrowser browser, @Nonnull CefLogSeverity level, @Nullable String message, @Nullable String source, int line) {
        return false;
    }

    /**
     * Called when auto-resize is enabled via {@link net.kurobako.cef4j.gen.CefBrowserHost#setAutoResizeEnabled(boolean, CefSize, CefSize)} and the contents have auto-resized. {@code new_size} will be the desired size in DIP coordinates. Return {@code true} if the resize was handled or {@code false} for default handling.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_auto_resize)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_size_t* new_size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:124</a>
     */
    default boolean onAutoResize(@Nullable CefBrowser browser, @Nonnull CefSize newSize) {
        return false;
    }

    /**
     * Called when the overall page loading progress has changed. {@code progress} ranges from 0.0 to 1.0.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_loading_progress_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, double progress);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:136</a>
     */
    default void onLoadingProgressChange(@Nullable CefBrowser browser, double progress) {
    }

    /**
     * Called when the browser's cursor has changed. If {@code type} is CT_CUSTOM then {@code custom_cursor_info} will be populated with the custom cursor information. Return {@code true} if the cursor change was handled or {@code false} for default handling.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_cursor_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, cef_cursor_handle_t cursor, cef_cursor_type_t type, const cef_cursor_info_t* custom_cursor_info);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:144</a>
     */
    default boolean onCursorChange(@Nullable CefBrowser browser, long cursor, @Nonnull CefCursorType type, @Nullable CefCursorInfo customCursorInfo) {
        return false;
    }

    /**
     * Called when the browser's access to an audio and/or video source has changed.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_media_access_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, int has_video_access, int has_audio_access);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:158</a>
     */
    default void onMediaAccessChange(@Nullable CefBrowser browser, boolean hasVideoAccess, boolean hasAudioAccess) {
    }

    /**
     * Called when JavaScript is requesting new bounds via window.moveTo/By() or window.resizeTo/By(). {@code new_bounds} are in DIP screen coordinates.
     * <p>
     * With Views-hosted browsers {@code new_bounds} are the desired bounds for the containing CefWindow and may be passed directly to net.kurobako.cef4j.gen.views.CefWindow.setBounds(). With external (client-provided) parent on macOS and Windows {@code new_bounds} are the desired frame bounds for the containing root window. With other non-Views browsers {@code new_bounds} are the desired bounds for the browser content only unless the client implements either {@link net.kurobako.cef4j.gen.CefDisplayHandler#getRootWindowScreenRect(CefBrowser, CefRect.Mutable)} for windowed browsers or net.kurobako.cef4j.gen.CefRenderHandler.getWindowScreenRect() for windowless browsers. Clients may expand browser content bounds to window bounds using OS-specific or CefDisplay methods.
     * <p>
     * Return {@code true} if this method was handled or {@code false} for default handling. Default move/resize behavior is only provided with Views-hosted Chrome style browsers.
     * <p>Added in CEF API version 13700.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_contents_bounds_change)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, const cef_rect_t* new_bounds);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:168</a>
     */
    default boolean onContentsBoundsChange(@Nullable CefBrowser browser, @Nonnull CefRect newBounds) {
        return false;
    }

    /**
     * Called to retrieve the external (client-provided) root window rectangle in screen DIP coordinates. Only called for windowed browsers on Windows and Linux. Return {@code true} if the rectangle was provided. Return {@code false} to use the root window bounds on Windows or the browser content bounds on Linux. For additional usage details see {@link net.kurobako.cef4j.gen.CefBrowserHost#notifyScreenInfoChanged()}.
     * <p>Added in CEF API version 13700.
     * <p>Definition generated from cef_display_handler_capi.h
     * <pre>int (CEF_CALLBACK* get_root_window_screen_rect)(struct _cef_display_handler_t* self, struct _cef_browser_t* browser, cef_rect_t* rect);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__display__handler_8h.html">cef_display_handler.h:193</a>
     */
    default boolean getRootWindowScreenRect(@Nullable CefBrowser browser, @Nonnull CefRect.Mutable rect) {
        return false;
    }
}
