// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle events related to permission requests. The methods of this class will be called on
 * the browser process UI thread.
 *
 * <p>Definition generated from cef_permission_handler_capi.h
 *
 * <pre>typedef struct _cef_permission_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_permission_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:82</a>
 */
public interface CefPermissionHandler extends CefClientHandler {

    /**
     * Called when a page requests permission to access media. {@code requesting_origin} is the URL origin requesting
     * permission. {@code requested_permissions} is a combination of values from cef_media_access_permission_types_t
     * that represent the requested permissions. Return {@code true} and call CefMediaAccessCallback methods either in
     * this method or at a later time to continue or cancel the request. Return {@code false} to proceed with default
     * handling. With Chrome style, default handling will display the permission request UI. With Alloy style, default
     * handling will deny the request. This method will not be called if the "--enable-media-stream" command-line switch
     * is used to grant all permissions.
     *
     * <p>Definition generated from cef_permission_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_request_media_access_permission)(struct _cef_permission_handler_t* self, struct _cef_browser_t* browser, struct _cef_frame_t* frame, const cef_string_t* requesting_origin, uint32_t requested_permissions, struct _cef_media_access_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:89</a>
     */
    default boolean onRequestMediaAccessPermission(
            @Nonnull CefBrowser browser,
            @Nonnull CefFrame frame,
            @Nonnull String requestingOrigin,
            int requestedPermissions,
            @Nonnull CefMediaAccessCallback callback) {
        return false;
    }

    /**
     * Called when a page should show a permission prompt. {@code prompt_id} uniquely identifies the prompt.
     * {@code requesting_origin} is the URL origin requesting permission. {@code requested_permissions} is a combination
     * of values from cef_permission_request_types_t that represent the requested permissions. Return {@code true} and
     * call CefPermissionPromptCallback.continue() either in this method or at a later time to continue or cancel the
     * request. Return {@code false} to proceed with default handling. With Chrome style, default handling will display
     * the permission prompt UI. With Alloy style, default handling is {@link CefPermissionRequestResult.Kind#IGNORE}.
     *
     * <p>Definition generated from cef_permission_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_show_permission_prompt)(struct _cef_permission_handler_t* self, struct _cef_browser_t* browser, uint64_t prompt_id, const cef_string_t* requesting_origin, uint32_t requested_permissions, struct _cef_permission_prompt_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:112</a>
     */
    default boolean onShowPermissionPrompt(
            @Nonnull CefBrowser browser,
            long promptId,
            @Nonnull String requestingOrigin,
            int requestedPermissions,
            @Nonnull CefPermissionPromptCallback callback) {
        return false;
    }

    /**
     * Called when a permission prompt handled via OnShowPermissionPrompt is dismissed. {@code prompt_id} will match the
     * value that was passed to OnShowPermissionPrompt. {@code result} will be the value passed to
     * CefPermissionPromptCallback.continue() or {@link CefPermissionRequestResult.Kind#IGNORE} if the dialog was
     * dismissed for other reasons such as navigation, browser closure, etc. This method will not be called if
     * OnShowPermissionPrompt returned {@code false} for {@code prompt_id}.
     *
     * <p>Definition generated from cef_permission_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_dismiss_permission_prompt)(struct _cef_permission_handler_t* self, struct _cef_browser_t* browser, uint64_t prompt_id, cef_permission_request_result_t result);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:133</a>
     */
    default void onDismissPermissionPrompt(
            @Nonnull CefBrowser browser, long promptId, @Nonnull CefPermissionRequestResult result) {}
}
