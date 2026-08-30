// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser_8h.html">cef_browser.h:240</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser_8h.html">cef_browser.h:247</a>
     */
    default void onPdfPrintFinished(@Nullable String path, boolean ok) {}
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all delegates in
     * order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning {@code Optional}s
     * collect every non-empty delegate and wrap them in the handler's own {@code Delegating} wrapper; other
     * {@code Optional}s pick the first non-empty; any other return type yields the first delegate's value.
     */
    class Delegating implements CefPdfPrintCallback {
        private final java.util.List<CefPdfPrintCallback> delegates;

        public Delegating(java.util.List<CefPdfPrintCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onPdfPrintFinished(@Nullable String path, boolean ok) {
            for (CefPdfPrintCallback d : delegates) d.onPdfPrintFinished(path, ok);
        }
    }
}
