// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Callback interface for CefBrowserHost::DownloadImage. The methods of this class will be called on the browser process
 * UI thread.
 */
public interface CefDownloadImageCallback {

    /**
     * Method that will be executed when the image download has completed. |image_url| is the URL that was downloaded
     * and |http_status_code| is the resulting HTTP status code. |image| is the resulting image, possibly at multiple
     * scale factors, or empty if the download failed.
     *
     * @param image may be null
     */
    default void onDownloadImageFinished(@Nonnull String imageUrl, int httpStatusCode, long image) {}
}
