// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Callback interface for {@link CefBrowserHost#downloadImage(String, boolean, int, boolean, CefDownloadImageCallback)}.
 * The methods of this class will be called on the browser process UI thread.
 *
 * <p>Definition generated from cef_browser_capi.h
 *
 * <pre>typedef struct _cef_download_image_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_download_image_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:256</a>
 */
public interface CefDownloadImageCallback extends CefClientHandler {

    /**
     * Method that will be executed when the image download has completed. {@code image_url} is the URL that was
     * downloaded and {@code http_status_code} is the resulting HTTP status code. {@code image} is the resulting image,
     * possibly at multiple scale factors, or empty if the download failed.
     *
     * <p>Definition generated from cef_browser_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_download_image_finished)(struct _cef_download_image_callback_t* self, const cef_string_t* image_url, int http_status_code, struct _cef_image_t* image);
     * </pre>
     *
     * @param image may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__browser_8h.html">cef_browser.h:263</a>
     */
    default void onDownloadImageFinished(@Nonnull String imageUrl, int httpStatusCode, @Nullable CefImage image) {}
}
