// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen.views;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.kurobako.cef4j.gen.CefBrowser;
import net.kurobako.cef4j.gen.CefBrowserSettings;
import net.kurobako.cef4j.gen.CefChromeToolbarType;
import net.kurobako.cef4j.gen.CefClient;
import net.kurobako.cef4j.gen.CefClientHandler;
import net.kurobako.cef4j.gen.CefGestureCommand;
import net.kurobako.cef4j.gen.CefRuntimeStyle;

/**
 * Implement this interface to handle BrowserView events. The methods of this class will be called on the browser process UI thread unless otherwise indicated.
 * <p>Definition generated from views/cef_browser_view_delegate_capi.h
 * <pre>typedef struct _cef_browser_view_delegate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_browser_view_delegate_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:48</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefBrowserViewDelegate extends CefClientHandler {

    /**
     * Called when {@code browser} associated with {@code browser_view} is created. This method will be called after {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onAfterCreated(CefBrowser)} is called for {@code browser} and before OnPopupBrowserViewCreated() is called for {@code browser}'s parent delegate if {@code browser} is a popup.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_browser_created)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:58</a>
     */
    default void onBrowserCreated(@Nullable CefBrowserView browserView, @Nullable CefBrowser browser) {
    }

    /**
     * Called when {@code browser} associated with {@code browser_view} is destroyed. Release all references to {@code browser} and do not attempt to execute any methods on {@code browser} after this callback returns. This method will be called before {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforeClose(CefBrowser)} is called for {@code browser}.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>void (CEF_CALLBACK* on_browser_destroyed)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:68</a>
     */
    default void onBrowserDestroyed(@Nullable CefBrowserView browserView, @Nullable CefBrowser browser) {
    }

    /**
     * Called before a new popup BrowserView is created. The popup originated from {@code browser_view}. {@code settings} and {@code client} are the values returned from {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onBeforePopup(CefBrowser, CefFrame, int, String, String, CefWindowOpenDisposition, boolean, CefPopupFeatures, CefWindowInfo.Mutable, java.util.concurrent.atomic.AtomicReference, CefBrowserSettings.Mutable, java.util.concurrent.atomic.AtomicReference, int[])}. {@code is_devtools} will be {@code true} if the popup will be a DevTools browser. Return the delegate that will be used for the new popup BrowserView.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>cef_browser_view_delegate_t* (CEF_CALLBACK* get_delegate_for_popup_browser_view)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, const struct _cef_browser_settings_t* settings, struct _cef_client_t* client, int is_devtools);</pre>
     *
     * @param client may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:78</a>
     */
    default Optional<CefBrowserViewDelegate> getDelegateForPopupBrowserView(@Nullable CefBrowserView browserView, @Nonnull CefBrowserSettings settings, @Nullable CefClient client, boolean isDevtools) {
        return Optional.empty();
    }

    /**
     * Called after {@code popup_browser_view} is created. This method will be called after {@link net.kurobako.cef4j.gen.CefLifeSpanHandler#onAfterCreated(CefBrowser)} and OnBrowserCreated() are called for the new popup browser. The popup originated from {@code browser_view}. {@code is_devtools} will be {@code true} if the popup is a DevTools browser. Optionally add {@code popup_browser_view} to the views hierarchy yourself and return {@code true}. Otherwise return {@code false} and a default CefWindow will be created for the popup.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>int (CEF_CALLBACK* on_popup_browser_view_created)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, struct _cef_browser_view_t* popup_browser_view, int is_devtools);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:94</a>
     */
    default boolean onPopupBrowserViewCreated(@Nullable CefBrowserView browserView, @Nullable CefBrowserView popupBrowserView, boolean isDevtools) {
        return false;
    }

    /**
     * Returns the Chrome toolbar type that will be available via {@link net.kurobako.cef4j.gen.views.CefBrowserView#getChromeToolbar()}. See that method for related documentation.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>cef_chrome_toolbar_type_t (CEF_CALLBACK* get_chrome_toolbar_type)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view);</pre>
     *
     * @return the result, or {@code CEF_CTT_NONE} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:111</a>
     */
    default CefChromeToolbarType getChromeToolbarType(@Nullable CefBrowserView browserView) {
        return CefChromeToolbarType.of(net.kurobako.cef4j.gen.CefChromeToolbarType.Kind.NONE);
    }

    /**
     * Return {@code true} to create frameless windows for Document picture-in-picture popups. Content in frameless windows should specify draggable regions using "-webkit-app-region: drag" CSS.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>int (CEF_CALLBACK* use_frameless_window_for_picture_in_picture)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:122</a>
     */
    default boolean useFramelessWindowForPictureInPicture(@Nullable CefBrowserView browserView) {
        return false;
    }

    /**
     * Called when {@code browser_view} receives a gesture command. Return {@code true} to handle (or disable) a {@code gesture_command} or {@code false} to propagate the gesture to the browser for default handling. With Chrome style these commands can also be handled via {@link net.kurobako.cef4j.gen.CefCommandHandler#onChromeCommand(CefBrowser, int, CefWindowOpenDisposition)}.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>int (CEF_CALLBACK* on_gesture_command)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view, cef_gesture_command_t gesture_command);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:157</a>
     */
    default boolean onGestureCommand(@Nullable CefBrowserView browserView, @Nonnull CefGestureCommand gestureCommand) {
        return false;
    }

    /**
     * Optionally change the runtime style for this BrowserView. See cef_runtime_style_t documentation for details.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>cef_runtime_style_t (CEF_CALLBACK* get_browser_runtime_style)(struct _cef_browser_view_delegate_t* self);</pre>
     *
     * @return the result, or {@code CEF_RUNTIME_STYLE_DEFAULT} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:169</a>
     */
    default CefRuntimeStyle getBrowserRuntimeStyle() {
        return CefRuntimeStyle.of(net.kurobako.cef4j.gen.CefRuntimeStyle.Kind.DEFAULT);
    }

    /**
     * Return {@code true} to allow the use of JavaScript moveTo/By() and resizeTo/By() (without user activation) with Document picture-in-picture popups.
     * <p>Added in CEF API version 13601.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>int (CEF_CALLBACK* allow_move_for_picture_in_picture)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:134</a>
     */
    default boolean allowMoveForPictureInPicture(@Nullable CefBrowserView browserView) {
        return false;
    }

    /**
     * Return {@code true} to allow opening Document picture-in-picture without user activation. Default is {@code false} (user activation required).
     * <p>Added in CEF API version 14400.
     * <p>Definition generated from views/cef_browser_view_delegate_capi.h
     * <pre>int (CEF_CALLBACK* allow_picture_in_picture_without_user_activation)(struct _cef_browser_view_delegate_t* self, struct _cef_browser_view_t* browser_view);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser__view__delegate_8h.html">views/cef_browser_view_delegate.h:146</a>
     */
    default boolean allowPictureInPictureWithoutUserActivation(@Nullable CefBrowserView browserView) {
        return false;
    }
}
