// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Implement this interface to handle dialog events. The methods of this class will be called on the browser process UI
 * thread.
 */
public interface CefDialogHandler {

    /**
     * Called to run a file chooser dialog. |mode| represents the type of dialog to display. |title| to the title to be
     * used for the dialog and may be empty to show the default title ("Open" or "Save" depending on the mode).
     * |default_file_path| is the path with optional directory and/or file name component that should be initially
     * selected in the dialog. |accept_filters| are used to restrict the selectable file types and may be any
     * combination of valid lower-cased MIME types (e.g. "text/*" or "image/*") and individual file extensions (e.g.
     * ".txt" or ".png"). |accept_extensions| provides the semicolon-delimited expansion of MIME types to file
     * extensions (if known, or empty string otherwise). |accept_descriptions| provides the descriptions for MIME types
     * (if known, or empty string otherwise). For example, the "image/*" mime type might have extensions
     * ".png;.jpg;.bmp;..." and description "Image Files". |accept_filters|, |accept_extensions| and
     * |accept_descriptions| will all be the same size. To display a custom dialog return true and execute |callback|
     * either inline or at a later time. To display the default dialog return false. If this method returns false it may
     * be called an additional time for the same dialog (both before and after MIME type expansion).
     *
     * @param title may be null
     * @param defaultFilePath may be null
     * @param acceptFilters may be null
     * @param acceptExtensions may be null
     * @param acceptDescriptions may be null
     */
    default boolean onFileDialog(
            long browser,
            @Nonnull CefFileDialogMode mode,
            @Nullable String title,
            @Nullable String defaultFilePath,
            @Nullable java.util.List<String> acceptFilters,
            @Nullable java.util.List<String> acceptExtensions,
            @Nullable java.util.List<String> acceptDescriptions,
            long callback) {
        return false;
    }
}
