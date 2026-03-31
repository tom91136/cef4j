// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

/**
 * Callback interface for CefBrowserHost::RunFileDialog. The methods of this class will be called on the browser process
 * UI thread.
 */
public interface CefRunFileDialogCallback {

    /**
     * Called asynchronously after the file dialog is dismissed. |file_paths| will be a single value or a list of values
     * depending on the dialog mode. If the selection was cancelled |file_paths| will be empty.
     *
     * @param filePaths may be null
     */
    default void onFileDialogDismissed(@Nullable java.util.List<String> filePaths) {}
}
