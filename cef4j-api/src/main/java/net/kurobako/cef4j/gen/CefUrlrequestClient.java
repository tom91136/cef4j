// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Interface that should be implemented by the CefURLRequest client. The methods of this class will be called on the
 * same thread that created the request unless otherwise documented.
 */
public interface CefUrlrequestClient {

    /**
     * Notifies the client that the request has completed. Use the CefURLRequest::GetRequestStatus method to determine
     * if the request was successful or not.
     */
    default void onRequestComplete(long request) {}

    /**
     * Notifies the client of upload progress. |current| denotes the number of bytes sent so far and |total| is the
     * total size of uploading data (or -1 if chunked upload is enabled). This method will only be called if the
     * UR_FLAG_REPORT_UPLOAD_PROGRESS flag is set on the request.
     */
    default void onUploadProgress(long request, long current, long total) {}

    /**
     * Notifies the client of download progress. |current| denotes the number of bytes received up to the call and
     * |total| is the expected total size of the response (or -1 if not determined).
     */
    default void onDownloadProgress(long request, long current, long total) {}

    /**
     * Called when some part of the response is read. |data| contains the current bytes received since the last call.
     * This method will not be called if the UR_FLAG_NO_DOWNLOAD_DATA flag is set on the request.
     */
    default void onDownloadData(long request, long data, long dataLength) {}

    /**
     * Called on the IO thread when the browser needs credentials from the user. |isProxy| indicates whether the host is
     * a proxy server. |host| contains the hostname and |port| contains the port number. Return true to continue the
     * request and call CefAuthCallback::Continue() when the authentication information is available. If the request has
     * an associated browser/frame then returning false will result in a call to GetAuthCredentials on the
     * CefRequestHandler associated with that browser, if any. Otherwise, returning false will cancel the request
     * immediately. This method will only be called for requests initiated from the browser process.
     *
     * @param realm may be null
     */
    default boolean getAuthCredentials(
            boolean isProxy,
            @Nonnull String host,
            int port,
            @Nullable String realm,
            @Nonnull String scheme,
            long callback) {
        return false;
    }
}
