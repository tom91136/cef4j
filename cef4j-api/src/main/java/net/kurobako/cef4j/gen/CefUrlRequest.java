// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class used to make a URL request. URL requests are not associated with a browser instance so no CefClient callbacks
 * will be executed. URL requests can be created on any valid CEF thread in either the browser or render process. Once
 * created the methods of the URL request object must be accessed on the same thread that created it.
 *
 * <p>Definition generated from cef_urlrequest_capi.h
 *
 * <pre>typedef struct _cef_urlrequest_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_urlrequest_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:49</a>
 */
public interface CefUrlRequest extends CefLibraryObject {

    /**
     * Returns the request object used to create this URL request. The returned object is read-only and should not be
     * modified.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>cef_request_t* (CEF_CALLBACK* get_request)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:83</a>
     */
    Optional<CefRequest> getRequest();

    /**
     * Returns the client.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>cef_urlrequest_client_t* (CEF_CALLBACK* get_client)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:90</a>
     */
    Optional<CefUrlRequestClient> getClient();

    /**
     * Returns the request status.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>cef_urlrequest_status_t (CEF_CALLBACK* get_request_status)(struct _cef_urlrequest_t* self);</pre>
     *
     * @return the result, or {@code UR_UNKNOWN} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:96</a>
     */
    CefUrlRequestStatus getRequestStatus();

    /**
     * Returns the request error if status is UR_CANCELED or UR_FAILED, or 0 otherwise.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>cef_errorcode_t (CEF_CALLBACK* get_request_error)(struct _cef_urlrequest_t* self);</pre>
     *
     * @return the result, or {@code ERR_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:102</a>
     */
    CefErrorCode getRequestError();

    /**
     * Returns the response, or {@code null} if no response information is available. Response information will only be
     * available after the upload has completed. The returned object is read-only and should not be modified.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>cef_response_t* (CEF_CALLBACK* get_response)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:109</a>
     */
    Optional<CefResponse> getResponse();

    /**
     * Returns {@code true} if the response body was served from the cache. This includes responses for which
     * revalidation was required.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>int (CEF_CALLBACK* response_was_cached)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:117</a>
     */
    boolean responseWasCached();

    /**
     * Cancel the request.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__urlrequest_8h.html">cef_urlrequest.h:124</a>
     */
    void cancel();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_urlrequest_capi.h
     *
     * <pre>
     * CEF_EXPORT cef_urlrequest_t* cef_urlrequest_create(struct _cef_request_t* request, struct _cef_urlrequest_client_t* client, struct _cef_request_context_t* request_context);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefUrlRequest> create(
            @Nullable CefRequest request,
            @Nullable CefUrlRequestClient client,
            @Nullable CefRequestContext requestContext) {
        return Optional.ofNullable(NativePeer.N_Create(request, client, requestContext));
    }

    final class NativePeer implements CefUrlRequest, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefUrlRequest has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefUrlRequest.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefUrlRequest 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<CefRequest> getRequest() {
            checkNotClosed();
            return Optional.ofNullable(N_GetRequest(nativePtr));
        }

        @Override
        public Optional<CefUrlRequestClient> getClient() {
            checkNotClosed();
            return Optional.ofNullable(N_GetClient(nativePtr));
        }

        @Override
        public CefUrlRequestStatus getRequestStatus() {
            checkNotClosed();
            return N_GetRequestStatus(nativePtr);
        }

        @Override
        public CefErrorCode getRequestError() {
            checkNotClosed();
            return N_GetRequestError(nativePtr);
        }

        @Override
        public Optional<CefResponse> getResponse() {
            checkNotClosed();
            return Optional.ofNullable(N_GetResponse(nativePtr));
        }

        @Override
        public boolean responseWasCached() {
            checkNotClosed();
            return N_ResponseWasCached(nativePtr);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            N_Cancel(nativePtr);
        }

        private static native CefRequest N_GetRequest(long self);

        private static native CefUrlRequestClient N_GetClient(long self);

        private static native CefUrlRequestStatus N_GetRequestStatus(long self);

        private static native CefErrorCode N_GetRequestError(long self);

        private static native CefResponse N_GetResponse(long self);

        private static native boolean N_ResponseWasCached(long self);

        private static native void N_Cancel(long self);

        static native CefUrlRequest N_Create(
                CefRequest request, CefUrlRequestClient client, CefRequestContext requestContext);

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NativePeer)) return false;
            return this.nativePtr == ((NativePeer) obj).nativePtr;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(nativePtr);
        }

        @Override
        public String toString() {
            return "CefUrlRequest{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
