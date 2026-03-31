// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;

/**
 * Callback interface for CefBrowserHost::PrintToPDF. The methods of this class will be called on the browser process UI
 * thread.
 */
public interface CefPdfPrintCallback {

    /**
     * Method that will be executed when the PDF printing has completed. |path| is the output path. |ok| will be true if
     * the printing completed successfully or false otherwise.
     *
     * @param path may be null
     */
    default void onPdfPrintFinished(@Nullable String path, boolean ok) {}
}
