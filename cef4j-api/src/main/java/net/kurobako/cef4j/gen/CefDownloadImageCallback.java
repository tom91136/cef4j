// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Callback interface for {@link net.kurobako.cef4j.gen.CefBrowserHost#downloadImage(String, boolean, int, boolean, CefDownloadImageCallback)}. The methods of this class will be called on the browser process UI thread.
 * <p>Definition generated from cef_browser_capi.h
 * <pre>typedef struct _cef_download_image_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_download_image_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser_8h.html">cef_browser.h:256</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefDownloadImageCallback extends CefClientHandler {

    /**
     * Method that will be executed when the image download has completed. {@code image_url} is the URL that was downloaded and {@code http_status_code} is the resulting HTTP status code. {@code image} is the resulting image, possibly at multiple scale factors, or empty if the download failed.
     * <p>Definition generated from cef_browser_capi.h
     * <pre>void (CEF_CALLBACK* on_download_image_finished)(struct _cef_download_image_callback_t* self, const cef_string_t* image_url, int http_status_code, struct _cef_image_t* image);</pre>
     *
     * @param image may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__browser_8h.html">cef_browser.h:263</a>
     */
    default void onDownloadImageFinished(@Nullable String imageUrl, int httpStatusCode, @Nullable CefImage image) {
    }
    /**
     * Composite that fans callbacks out to every registered delegate. {@code void} methods invoke all
     * delegates in order; {@code boolean} methods short-circuit on the first {@code true}; handler-returning
     * {@code Optional}s collect every non-empty delegate and wrap them in the handler's own {@code Delegating}
     * wrapper; other {@code Optional}s pick the first non-empty; any other return type yields the first
     * delegate's value.
     */
    class Delegating implements CefDownloadImageCallback {
        private final java.util.List<CefDownloadImageCallback> delegates;

        public Delegating(java.util.List<CefDownloadImageCallback> delegates) {
            this.delegates = java.util.List.copyOf(delegates);
        }

        @Override
        public void onDownloadImageFinished(@Nullable String imageUrl, int httpStatusCode, @Nullable CefImage image) {
            for (CefDownloadImageCallback d : delegates) d.onDownloadImageFinished(imageUrl, httpStatusCode, image);
        }
    }

}
