// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle dialog events. The methods of this class will be called on the browser process UI thread.
 * <p>Definition generated from cef_dialog_handler_capi.h
 * <pre>typedef struct _cef_dialog_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_dialog_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dialog__handler_8h.html">cef_dialog_handler.h:65</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefDialogHandler extends CefClientHandler {

    /**
     * Called to run a file chooser dialog. {@code mode} represents the type of dialog to display. {@code title} to the title to be used for the dialog and may be empty to show the default title ("Open" or "Save" depending on the mode). {@code default_file_path} is the path with optional directory and/or file name component that should be initially selected in the dialog. {@code accept_filters} are used to restrict the selectable file types and may be any combination of valid lower-cased MIME types (e.g. "text/*" or "image/*") and individual file extensions (e.g. ".txt" or ".png"). {@code accept_extensions} provides the semicolon-delimited expansion of MIME types to file extensions (if known, or empty string otherwise). {@code accept_descriptions} provides the descriptions for MIME types (if known, or empty string otherwise). For example, the "image/*" mime type might have extensions ".png;.jpg;.bmp;..." and description "Image Files". {@code accept_filters}, {@code accept_extensions} and {@code accept_descriptions} will all be the same size. To display a custom dialog return {@code true} and execute {@code callback} either inline or at a later time. To display the default dialog return {@code false}. If this method returns {@code false} it may be called an additional time for the same dialog (both before and after MIME type expansion).
     * <p>Definition generated from cef_dialog_handler_capi.h
     * <pre>int (CEF_CALLBACK* on_file_dialog)(struct _cef_dialog_handler_t* self, struct _cef_browser_t* browser, cef_file_dialog_mode_t mode, const cef_string_t* title, const cef_string_t* default_file_path, cef_string_list_t accept_filters, cef_string_list_t accept_extensions, cef_string_list_t accept_descriptions, struct _cef_file_dialog_callback_t* callback);</pre>
     *
     * @param title may be null
     * @param defaultFilePath may be null
     * @param acceptFilters may be null
     * @param acceptExtensions may be null
     * @param acceptDescriptions may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dialog__handler_8h.html">cef_dialog_handler.h:74</a>
     */
    default boolean onFileDialog(@Nullable CefBrowser browser, @Nonnull CefFileDialogMode mode, @Nullable String title, @Nullable String defaultFilePath, @Nullable List<String> acceptFilters, @Nullable List<String> acceptExtensions, @Nullable List<String> acceptDescriptions, @Nullable CefFileDialogCallback callback) {
        return false;
    }
}
