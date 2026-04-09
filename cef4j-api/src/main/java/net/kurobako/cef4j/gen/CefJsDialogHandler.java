// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle events related to JavaScript dialogs. The methods of this class will be called on the UI thread.
 * <p>Definition generated from cef_jsdialog_handler_capi.h
 * <pre>typedef struct _cef_jsdialog_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_jsdialog_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:59</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefJsDialogHandler extends CefClientHandler {

    /**
     * Called to run a JavaScript dialog. If {@code origin_url} is non-empty it can be passed to the CefFormatUrlForSecurityDisplay function to retrieve a secure and user-friendly display string. The {@code default_prompt_text} value will be specified for prompt dialogs only. Set {@code suppress_message} to {@code true} and return {@code false} to suppress the message (suppressing messages is preferable to immediately executing the callback as this is used to detect presumably malicious behavior like spamming alert messages in onbeforeunload). Set {@code suppress_message} to {@code false} and return {@code false} to use the default implementation (the default implementation will show one modal dialog at a time and suppress any additional dialog requests until the displayed dialog is dismissed). Return {@code true} if the application will use a custom dialog or if the callback has been executed immediately. Custom dialogs may be either modal or modeless. If a custom dialog is used the application must execute {@code callback} once the custom dialog is dismissed.
     * <p>Definition generated from cef_jsdialog_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_jsdialog)(struct _cef_jsdialog_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* origin_url, cef_jsdialog_type_t dialog_type, const cef_string_t* message_text, const cef_string_t* default_prompt_text, struct _cef_jsdialog_callback_t* callback, int* suppress_message);</pre>
     *
     * @param originUrl may be null
     * @param messageText may be null
     * @param defaultPromptText may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:68</a>
     */
    default boolean onJsDialog(@Nullable CefBrowser browser, @Nullable String originUrl, @Nonnull CefJsDialogType dialogType, @Nullable String messageText, @Nullable String defaultPromptText, @Nullable CefJsDialogCallback callback, int[] suppressMessage) {
        return false;
    }

    /**
     * Called to run a dialog asking the user if they want to leave a page. Return {@code false} to use the default dialog implementation. Return {@code true} if the application will use a custom dialog or if the callback has been executed immediately. Custom dialogs may be either modal or modeless. If a custom dialog is used the application must execute {@code callback} once the custom dialog is dismissed.
     * <p>Definition generated from cef_jsdialog_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_before_unload_dialog)(struct _cef_jsdialog_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* message_text, int is_reload, struct _cef_jsdialog_callback_t* callback);</pre>
     *
     * @param messageText may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:96</a>
     */
    default boolean onBeforeUnloadDialog(@Nullable CefBrowser browser, @Nullable String messageText, boolean isReload, @Nullable CefJsDialogCallback callback) {
        return false;
    }

    /**
     * Called to cancel any pending dialogs and reset any saved dialog state. Will be called due to events like page navigation irregardless of whether any dialogs are currently pending.
     * <p>Definition generated from cef_jsdialog_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_reset_dialog_state)(struct _cef_jsdialog_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:112</a>
     */
    default void onResetDialogState(@Nullable CefBrowser browser) {
    }

    /**
     * Called when the dialog is closed.
     * <p>Definition generated from cef_jsdialog_handler_capi.h
     * <pre>void (CEF_CALLBACK* on_dialog_closed)(struct _cef_jsdialog_handler_t* self, struct _cef_browser_t* browser);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__jsdialog__handler_8h.html">cef_jsdialog_handler.h:120</a>
     */
    default void onDialogClosed(@Nullable CefBrowser browser) {
    }
}
