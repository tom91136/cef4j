// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Implement this interface to handle printing on Linux. Each browser will have only one print job in progress at a
 * time. The methods of this class will be called on the browser process UI thread.
 */
public interface CefPrintHandler {

    /**
     * Called when printing has started for the specified |browser|. This method will be called before the other
     * OnPrint*() methods and irrespective of how printing was initiated (e.g. CefBrowserHost::Print(), JavaScript
     * window.print() or PDF extension print button).
     */
    default void onPrintStart(long browser) {}

    /**
     * Synchronize |settings| with client state. If |get_defaults| is true then populate |settings| with the default
     * print settings. Do not keep a reference to |settings| outside of this callback.
     */
    default void onPrintSettings(long browser, long settings, boolean getDefaults) {}

    /**
     * Show the print dialog. Execute |callback| once the dialog is dismissed. Return true if the dialog will be
     * displayed or false to cancel the printing immediately.
     */
    default boolean onPrintDialog(long browser, boolean hasSelection, long callback) {
        return false;
    }

    /**
     * Send the print job to the printer. Execute |callback| once the job is completed. Return true if the job will
     * proceed or false to cancel the job immediately.
     */
    default boolean onPrintJob(long browser, @Nonnull String documentName, @Nonnull String pdfFilePath, long callback) {
        return false;
    }

    /** Reset client state related to printing. */
    default void onPrintReset(long browser) {}

    /** Return the PDF paper size in device units. Used in combination with CefBrowserHost::PrintToPDF(). */
    default CefSize getPdfPaperSize(long browser, int deviceUnitsPerInch) {
        return null;
    }
}
