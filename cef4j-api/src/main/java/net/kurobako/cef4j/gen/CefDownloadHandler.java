// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/** Class used to handle file downloads. The methods of this class will called on the browser process UI thread. */
public interface CefDownloadHandler {

    /**
     * Called before a download begins in response to a user-initiated action (e.g. alt + link click or link click that
     * returns a `Content-Disposition: attachment` response from the server). |url| is the target download URL and
     * |request_method| is the target method (GET, POST, etc). Return true to proceed with the download or false to
     * cancel the download.
     */
    default boolean canDownload(long browser, @Nonnull String url, @Nonnull String requestMethod) {
        return false;
    }

    /**
     * Called before a download begins. |suggested_name| is the suggested name for the download file. Return true and
     * execute |callback| either asynchronously or in this method to continue or cancel the download. Return false to
     * proceed with default handling (cancel with Alloy style, download shelf with Chrome style). Do not keep a
     * reference to |download_item| outside of this method.
     */
    default boolean onBeforeDownload(long browser, long downloadItem, @Nonnull String suggestedName, long callback) {
        return false;
    }

    /**
     * Called when a download's status or progress information has been updated. This may be called multiple times
     * before and after OnBeforeDownload(). Execute |callback| either asynchronously or in this method to cancel the
     * download if desired. Do not keep a reference to |download_item| outside of this method.
     */
    default void onDownloadUpdated(long browser, long downloadItem, long callback) {}
}
