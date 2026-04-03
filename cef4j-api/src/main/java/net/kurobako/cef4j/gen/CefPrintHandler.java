// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Implement this interface to handle printing on Linux. Each browser will have only one print job in progress at a
 * time. The methods of this class will be called on the browser process UI thread.
 *
 * <p>Definition generated from cef_print_handler_capi.h
 *
 * <pre>typedef struct _cef_print_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_print_handler_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:77</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefPrintHandler extends CefClientHandler {

    /**
     * Called when printing has started for the specified {@code browser}. This method will be called before the other
     * OnPrint*() methods and irrespective of how printing was initiated (e.g. {@link CefBrowserHost#print()},
     * JavaScript window.print() or PDF extension print button).
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_print_start)(struct _cef_print_handler_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:85</a>
     */
    default void onPrintStart(@Nullable CefBrowser browser) {}

    /**
     * Synchronize {@code settings} with client state. If {@code get_defaults} is {@code true} then populate
     * {@code settings} with the default print settings. Do not keep a reference to {@code settings} outside of this
     * callback.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_print_settings)(struct _cef_print_handler_t* self, struct _cef_browser_t* browser, struct _cef_print_settings_t* settings, int get_defaults);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:94</a>
     */
    default void onPrintSettings(
            @Nullable CefBrowser browser, @Nullable CefPrintSettings settings, boolean getDefaults) {}

    /**
     * Show the print dialog. Execute {@code callback} once the dialog is dismissed. Return {@code true} if the dialog
     * will be displayed or {@code false} to cancel the printing immediately.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_print_dialog)(struct _cef_print_handler_t* self, struct _cef_browser_t* browser, int has_selection, struct _cef_print_dialog_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:104</a>
     */
    default boolean onPrintDialog(
            @Nullable CefBrowser browser, boolean hasSelection, @Nullable CefPrintDialogCallback callback) {
        return false;
    }

    /**
     * Send the print job to the printer. Execute {@code callback} once the job is completed. Return {@code true} if the
     * job will proceed or {@code false} to cancel the job immediately.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* on_print_job)(struct _cef_print_handler_t* self, struct _cef_browser_t* browser, const cef_string_t* document_name, const cef_string_t* pdf_file_path, struct _cef_print_job_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:114</a>
     */
    default boolean onPrintJob(
            @Nullable CefBrowser browser,
            @Nullable String documentName,
            @Nullable String pdfFilePath,
            @Nullable CefPrintJobCallback callback) {
        return false;
    }

    /**
     * Reset client state related to printing.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* on_print_reset)(struct _cef_print_handler_t* self, struct _cef_browser_t* browser);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:125</a>
     */
    default void onPrintReset(@Nullable CefBrowser browser) {}

    /**
     * Return the PDF paper size in device units. Used in combination with {@link CefBrowserHost#printToPdf(String,
     * CefPdfPrintSettings, CefPdfPrintCallback)}.
     *
     * <p>Definition generated from cef_print_handler_capi.h
     *
     * <pre>
     * cef_size_t* (CEF_CALLBACK* get_pdf_paper_size)(struct _cef_print_handler_t* self, struct _cef_browser_t* browser, int device_units_per_inch);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__handler_8h.html">cef_print_handler.h:131</a>
     */
    default CefSize getPdfPaperSize(@Nullable CefBrowser browser, int deviceUnitsPerInch) {
        return null;
    }
}
