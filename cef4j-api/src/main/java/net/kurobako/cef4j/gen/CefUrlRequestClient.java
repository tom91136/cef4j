// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Interface that should be implemented by the CefURLRequest client. The methods of this class will be called on the
 * same thread that created the request unless otherwise documented.
 *
 * <p>Definition generated from cef_urlrequest_capi.h
 *
 * <pre>typedef struct _cef_urlrequest_client_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_urlrequest_client_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:131</a>
 */
public interface CefUrlRequestClient extends CefClientHandler {

    /**
     * Notifies the client that the request has completed. Use the CefURLRequest.getRequestStatus() method to determine
     * if the request was successful or not.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_request_complete)(struct _cef_urlrequest_client_t* self, struct _cef_urlrequest_t* request);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:139</a>
     */
    default void onRequestComplete(@Nullable CefUrlRequest request) {}

    /**
     * Notifies the client of upload progress. {@code current} denotes the number of bytes sent so far and {@code total}
     * is the total size of uploading data (or -1 if chunked upload is enabled). This method will only be called if the
     * UR_FLAG_REPORT_UPLOAD_PROGRESS flag is set on the request.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_upload_progress)(struct _cef_urlrequest_client_t* self, struct _cef_urlrequest_t* request, int64_t current, int64_t total);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:147</a>
     */
    default void onUploadProgress(@Nullable CefUrlRequest request, long current, long total) {}

    /**
     * Notifies the client of download progress. {@code current} denotes the number of bytes received up to the call and
     * {@code total} is the expected total size of the response (or -1 if not determined).
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_download_progress)(struct _cef_urlrequest_client_t* self, struct _cef_urlrequest_t* request, int64_t current, int64_t total);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:158</a>
     */
    default void onDownloadProgress(@Nullable CefUrlRequest request, long current, long total) {}

    /**
     * Called when some part of the response is read. {@code data} contains the current bytes received since the last
     * call. This method will not be called if the UR_FLAG_NO_DOWNLOAD_DATA flag is set on the request.
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code dataLength} parameter is derived from the buffer's capacity.</b>
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* on_download_data)(struct _cef_urlrequest_client_t* self, struct _cef_urlrequest_t* request, const void* data, size_t data_length);
     * </pre>
     *
     * @param data <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not
     *     reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to
     *     it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:168</a>
     */
    default void onDownloadData(@Nullable CefUrlRequest request, @Nonnull ByteBuffer data) {}

    /**
     * Called on the IO thread when the browser needs credentials from the user. {@code isProxy} indicates whether the
     * host is a proxy server. {@code host} contains the hostname and {@code port} contains the port number. Return
     * {@code true} to continue the request and call CefAuthCallback.continue() when the authentication information is
     * available. If the request has an associated browser/frame then returning {@code false} will result in a call to
     * GetAuthCredentials on the CefRequestHandler associated with that browser, if any. Otherwise, returning
     * {@code false} will cancel the request immediately. This method will only be called for requests initiated from
     * the browser process.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* get_auth_credentials)(struct _cef_urlrequest_client_t* self, int isProxy, const cef_string_t* host, int port, const cef_string_t* realm, const cef_string_t* scheme, struct _cef_auth_callback_t* callback);
     * </pre>
     *
     * @param realm may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:178</a>
     */
    default boolean getAuthCredentials(
            boolean isProxy,
            @Nullable String host,
            int port,
            @Nullable String realm,
            @Nullable String scheme,
            @Nullable CefAuthCallback callback) {
        return false;
    }
}
