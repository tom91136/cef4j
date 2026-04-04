// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Class used to implement a custom request handler interface. The methods of this class will be called on the IO thread
 * unless otherwise indicated.
 *
 * <p>Definition generated from cef_resource_handler_capi.h
 *
 * <pre>typedef struct _cef_resource_handler_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_handler_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:82</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefResourceHandler extends CefClientHandler {

    /**
     * Open the response stream. To handle the request immediately set {@code handle_request} to {@code true} and return
     * {@code true}. To decide at a later time set {@code handle_request} to {@code false}, return {@code true}, and
     * execute {@code callback} to continue or cancel the request. To cancel the request immediately set
     * {@code handle_request} to {@code true} and return {@code false}. This method will be called in sequence but not
     * from a dedicated thread. For backwards compatibility set {@code handle_request} to {@code false} and return
     * {@code false} and the ProcessRequest method will be called.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* open)(struct _cef_resource_handler_t* self, struct _cef_request_t* request, int* handle_request, struct _cef_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:89</a>
     */
    default boolean open(@Nullable CefRequest request, int[] handleRequest, @Nullable CefCallback callback) {
        return false;
    }

    /**
     * Begin processing the request. To handle the request return {@code true} and call
     * net.kurobako.cef4j.gen.CefCallback.continue() once the response header information is available
     * (net.kurobako.cef4j.gen.CefCallback.continue() can also be called from inside this method if header information
     * is available immediately). To cancel the request return {@code false}.
     *
     * <p><b>WARNING:</b> This method is deprecated. Use Open instead.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* process_request)(struct _cef_resource_handler_t* self, struct _cef_request_t* request, struct _cef_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:107</a>
     */
    default boolean processRequest(@Nullable CefRequest request, @Nullable CefCallback callback) {
        return false;
    }

    /**
     * Retrieve response header information. If the response length is not known set {@code response_length} to -1 and
     * ReadResponse() will be called until it returns {@code false}. If the response length is known set
     * {@code response_length} to a positive value and ReadResponse() will be called until it returns {@code false} or
     * the specified number of bytes have been read. Use the {@code response} object to set the mime type, http status
     * code and other optional header values. To redirect the request to a new URL set {@code redirectUrl} to the new
     * URL. {@code redirectUrl} can be either a relative or fully qualified URL. It is also possible to set
     * {@code response} to a redirect http status code and pass the new URL via a Location header. Likewise with
     * {@code redirectUrl} it is valid to set a relative or fully qualified URL as the Location header value. If an
     * error occured while setting up the request you can call SetError() on {@code response} to indicate the error
     * condition.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* get_response_headers)(struct _cef_resource_handler_t* self, struct _cef_response_t* response, int64_t* response_length, cef_string_t* redirectUrl);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:122</a>
     */
    default void getResponseHeaders(
            @Nullable CefResponse response, long[] responseLength, @Nullable String redirectUrl) {}

    /**
     * Skip response data when requested by a Range header. Skip over and discard {@code bytes_to_skip} bytes of
     * response data. If data is available immediately set {@code bytes_skipped} to the number of bytes skipped and
     * return {@code true}. To read the data at a later time set {@code bytes_skipped} to 0, return {@code true} and
     * execute {@code callback} when the data is available. To indicate failure set {@code bytes_skipped} to &lt; 0
     * (e.g. -2 for {@code ERR_FAILED}) and return {@code false}. This method will be called in sequence but not from a
     * dedicated thread.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* skip)(struct _cef_resource_handler_t* self, int64_t bytes_to_skip, int64_t* bytes_skipped, struct _cef_resource_skip_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:142</a>
     */
    default boolean skip(long bytesToSkip, long[] bytesSkipped, @Nullable CefResourceSkipCallback callback) {
        return false;
    }

    /**
     * Read response data. If data is available immediately copy up to {@code bytes_to_read} bytes into
     * {@code data_out}, set {@code bytes_read} to the number of bytes copied, and return {@code true}. To read the data
     * at a later time keep a pointer to {@code data_out}, set {@code bytes_read} to 0, return {@code true} and execute
     * {@code callback} when the data is available ({@code data_out} will remain valid until the callback is executed).
     * To indicate response completion set {@code bytes_read} to 0 and return {@code false}. To indicate failure set
     * {@code bytes_read} to &lt; 0 (e.g. -2 for {@code ERR_FAILED}) and return {@code false}. This method will be
     * called in sequence but not from a dedicated thread. For backwards compatibility set {@code bytes_read} to -1 and
     * return {@code false} and the ReadResponse method will be called.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* read)(struct _cef_resource_handler_t* self, void* data_out, int bytes_to_read, int* bytes_read, struct _cef_resource_read_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:159</a>
     */
    default boolean read(
            @Nullable NativePointer dataOut,
            int bytesToRead,
            int[] bytesRead,
            @Nullable CefResourceReadCallback callback) {
        return false;
    }

    /**
     * Read response data. If data is available immediately copy up to {@code bytes_to_read} bytes into
     * {@code data_out}, set {@code bytes_read} to the number of bytes copied, and return {@code true}. To read the data
     * at a later time set {@code bytes_read} to 0, return {@code true} and call
     * net.kurobako.cef4j.gen.CefCallback.continue() when the data is available. To indicate response completion return
     * {@code false}.
     *
     * <p><b>WARNING:</b> This method is deprecated. Use Skip and Read instead.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>
     * int (CEF_CALLBACK* read_response)(struct _cef_resource_handler_t* self, void* data_out, int bytes_to_read, int* bytes_read, struct _cef_callback_t* callback);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:181</a>
     */
    default boolean readResponse(
            @Nullable NativePointer dataOut, int bytesToRead, int[] bytesRead, @Nullable CefCallback callback) {
        return false;
    }

    /**
     * Request processing has been canceled.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_resource_handler_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:199</a>
     */
    default void cancel() {}
}
