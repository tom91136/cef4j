// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle events related to permission requests. The methods of this class will be called on
 * the browser process UI thread.
 */
public interface CefPermissionHandler {

    /**
     * Called when a page requests permission to access media. |requesting_origin| is the URL origin requesting
     * permission. |requested_permissions| is a combination of values from cef_media_access_permission_types_t that
     * represent the requested permissions. Return true and call CefMediaAccessCallback methods either in this method or
     * at a later time to continue or cancel the request. Return false to proceed with default handling. With Chrome
     * style, default handling will display the permission request UI. With Alloy style, default handling will deny the
     * request. This method will not be called if the "--enable-media-stream" command-line switch is used to grant all
     * permissions.
     */
    default boolean onRequestMediaAccessPermission(
            long browser, long frame, @Nonnull String requestingOrigin, int requestedPermissions, long callback) {
        return false;
    }

    /**
     * Called when a page should show a permission prompt. |prompt_id| uniquely identifies the prompt.
     * |requesting_origin| is the URL origin requesting permission. |requested_permissions| is a combination of values
     * from cef_permission_request_types_t that represent the requested permissions. Return true and call
     * CefPermissionPromptCallback::Continue either in this method or at a later time to continue or cancel the request.
     * Return false to proceed with default handling. With Chrome style, default handling will display the permission
     * prompt UI. With Alloy style, default handling is CEF_PERMISSION_RESULT_IGNORE.
     */
    default boolean onShowPermissionPrompt(
            long browser, long promptId, @Nonnull String requestingOrigin, int requestedPermissions, long callback) {
        return false;
    }

    /**
     * Called when a permission prompt handled via OnShowPermissionPrompt is dismissed. |prompt_id| will match the value
     * that was passed to OnShowPermissionPrompt. |result| will be the value passed to
     * CefPermissionPromptCallback::Continue or CEF_PERMISSION_RESULT_IGNORE if the dialog was dismissed for other
     * reasons such as navigation, browser closure, etc. This method will not be called if OnShowPermissionPrompt
     * returned false for |prompt_id|.
     */
    default void onDismissPermissionPrompt(long browser, long promptId, @Nonnull CefPermissionRequestResult result) {}
}
