// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import javax.annotation.Nullable;

/**
 * Callback interface for {@link CefBrowserHost#runFileDialog(CefFileDialogMode, String, String, List<String>,
 * CefRunFileDialogCallback)}. The methods of this class will be called on the browser process UI thread.
 *
 * <p>Definition generated from cef_browser_capi.h
 *
 * <pre>typedef struct _cef_run_file_dialog_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_run_file_dialog_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:202</a>
 */
public interface CefRunFileDialogCallback extends CefClientHandler {

    /**
     * Called asynchronously after the file dialog is dismissed. {@code file_paths} will be a single value or a list of
     * values depending on the dialog mode. If the selection was cancelled {@code file_paths} will be empty.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_file_dialog_dismissed)(struct _cef_run_file_dialog_callback_t* self, cef_string_list_t file_paths);
     * </pre>
     *
     * @param filePaths may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:209</a>
     */
    default void onFileDialogDismissed(@Nullable List<String> filePaths) {}
}
