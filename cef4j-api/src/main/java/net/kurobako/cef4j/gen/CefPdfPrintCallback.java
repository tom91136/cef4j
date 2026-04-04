// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Callback interface for {@link net.kurobako.cef4j.gen.CefBrowserHost#printToPdf(String, CefPdfPrintSettings,
 * CefPdfPrintCallback)}. The methods of this class will be called on the browser process UI thread.
 *
 * <p>Definition generated from cef_browser_capi.h
 *
 * <pre>typedef struct _cef_pdf_print_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_pdf_print_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:240</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefPdfPrintCallback extends CefClientHandler {

    /**
     * Method that will be executed when the PDF printing has completed. {@code path} is the output path. {@code ok}
     * will be {@code true} if the printing completed successfully or {@code false} otherwise.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_pdf_print_finished)(struct _cef_pdf_print_callback_t* self, const cef_string_t* path, int ok);
     * </pre>
     *
     * @param path may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:247</a>
     */
    default void onPdfPrintFinished(@Nullable String path, boolean ok) {}
}
