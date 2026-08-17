// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import javax.annotation.Nullable;

/**
 * Callback interface for {@link net.kurobako.cef4j.gen.CefBrowserHost#runFileDialog(CefFileDialogMode, String, String, java.util.List, CefRunFileDialogCallback)}. The methods of this class will be called on the browser process UI thread.
 * <p>Definition generated from cef_browser_capi.h
 * <pre>typedef struct _cef_run_file_dialog_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_run_file_dialog_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser_8h.html">cef_browser.h:202</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefRunFileDialogCallback extends CefClientHandler {

    /**
     * Called asynchronously after the file dialog is dismissed. {@code file_paths} will be a single value or a list of values depending on the dialog mode. If the selection was cancelled {@code file_paths} will be empty.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* on_file_dialog_dismissed)(struct _cef_run_file_dialog_callback_t* self, cef_string_list_t file_paths);</pre>
     *
     * @param filePaths may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser_8h.html">cef_browser.h:209</a>
     */
    default void onFileDialogDismissed(@Nullable List<String> filePaths) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefRunFileDialogCallback {
        private final java.util.List<CefRunFileDialogCallback> delegates;

        public Delegating(java.util.List<CefRunFileDialogCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onFileDialogDismissed(@Nullable List<String> filePaths) {
            for (CefRunFileDialogCallback d : delegates) d.onFileDialogDismissed(filePaths);
        }
    }

}
