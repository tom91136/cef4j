// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/**
 * Class used to implement a custom request handler interface. The methods of this class will be called on the IO thread
 * unless otherwise indicated.
 */
public interface CefResourceHandler {

    /**
     * Open the response stream. To handle the request immediately set |handle_request| to true and return true. To
     * decide at a later time set |handle_request| to false, return true, and execute |callback| to continue or cancel
     * the request. To cancel the request immediately set |handle_request| to true and return false. This method will be
     * called in sequence but not from a dedicated thread. For backwards compatibility set |handle_request| to false and
     * return false and the ProcessRequest method will be called.
     */
    default boolean open(long request, int[] handleRequest, long callback) {
        return false;
    }

    /**
     * Begin processing the request. To handle the request return true and call CefCallback::Continue() once the
     * response header information is available (CefCallback::Continue() can also be called from inside this method if
     * header information is available immediately). To cancel the request return false. WARNING: This method is
     * deprecated. Use Open instead.
     */
    default boolean processRequest(long request, long callback) {
        return false;
    }

    /**
     * Retrieve response header information. If the response length is not known set |response_length| to -1 and
     * ReadResponse() will be called until it returns false. If the response length is known set |response_length| to a
     * positive value and ReadResponse() will be called until it returns false or the specified number of bytes have
     * been read. Use the |response| object to set the mime type, http status code and other optional header values. To
     * redirect the request to a new URL set |redirectUrl| to the new URL. |redirectUrl| can be either a relative or
     * fully qualified URL. It is also possible to set |response| to a redirect http status code and pass the new URL
     * via a Location header. Likewise with |redirectUrl| it is valid to set a relative or fully qualified URL as the
     * Location header value. If an error occured while setting up the request you can call SetError() on |response| to
     * indicate the error condition.
     */
    default void getResponseHeaders(long response, long responseLength, @Nonnull String redirectUrl) {}

    /**
     * Skip response data when requested by a Range header. Skip over and discard |bytes_to_skip| bytes of response
     * data. If data is available immediately set |bytes_skipped| to the number of bytes skipped and return true. To
     * read the data at a later time set |bytes_skipped| to 0, return true and execute |callback| when the data is
     * available. To indicate failure set |bytes_skipped| to < 0 (e.g. -2 for ERR_FAILED) and return false. This method
     * will be called in sequence but not from a dedicated thread.
     */
    default boolean skip(long bytesToSkip, long bytesSkipped, long callback) {
        return false;
    }

    /** Read raw binary data. */
    default int read(long dataOut, int bytesToRead, int[] bytesRead, long callback) {
        return 0;
    }

    /**
     * Read response data. If data is available immediately copy up to |bytes_to_read| bytes into |data_out|, set
     * |bytes_read| to the number of bytes copied, and return true. To read the data at a later time set |bytes_read| to
     * 0, return true and call CefCallback::Continue() when the data is available. To indicate response completion
     * return false. WARNING: This method is deprecated. Use Skip and Read instead.
     */
    default boolean readResponse(long dataOut, int bytesToRead, int[] bytesRead, long callback) {
        return false;
    }

    /** Call to cancel the download. */
    default void cancel() {}
}
