// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * Class used to make a URL request. URL requests are not associated with a browser instance so no CefClient callbacks will be executed. URL requests can be created on any valid CEF thread in either the browser or render process. Once created the methods of the URL request object must be accessed on the same thread that created it.
 * <p>Definition generated from cef_urlrequest_capi.h
 * <pre>typedef struct _cef_urlrequest_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_urlrequest_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:49</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefUrlRequest extends CefLibraryObject {

    /**
     * Returns the request object used to create this URL request. The returned object is read-only and should not be modified.
     * <p>Definition generated from cef_urlrequest_capi.h
     * <pre>cef_request_t* (CEF_CALLBACK* get_request)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:83</a>
     */
    Optional<CefRequest> getRequest();

    /**
     * Returns the client.
     * <p>Definition generated from cef_urlrequest_capi.h
     * <pre>cef_urlrequest_client_t* (CEF_CALLBACK* get_client)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:90</a>
     */
    Optional<CefUrlRequestClient> getClient();

    /**
     * Returns the request status.
     * <p>Definition generated from cef_urlrequest_capi.h
     * <pre>cef_urlrequest_status_t (CEF_CALLBACK* get_request_status)(struct _cef_urlrequest_t* self);</pre>
     *
     * @return the result, or {@code UR_UNKNOWN} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:96</a>
     */
    CefUrlRequestStatus getRequestStatus();

    /**
     * Returns the request error if status is UR_CANCELED or UR_FAILED, or 0 otherwise.
     * <p>Definition generated from cef_urlrequest_capi.h
     * <pre>cef_errorcode_t (CEF_CALLBACK* get_request_error)(struct _cef_urlrequest_t* self);</pre>
     *
     * @return the result, or {@code ERR_NONE} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:102</a>
     */
    CefErrorCode getRequestError();

    /**
     * Returns the response, or {@code null} if no response information is available. Response information will only be available after the upload has completed. The returned object is read-only and should not be modified.
     * <p>Definition generated from cef_urlrequest_capi.h
     * <pre>cef_response_t* (CEF_CALLBACK* get_response)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:109</a>
     */
    Optional<CefResponse> getResponse();

    /**
     * Returns {@code true} if the response body was served from the cache. This includes responses for which revalidation was required.
     * <p>Definition generated from cef_urlrequest_capi.h
     * <pre>int (CEF_CALLBACK* response_was_cached)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:117</a>
     */
    boolean responseWasCached();

    /**
     * Cancel the request.
     * <p>Definition generated from cef_urlrequest_capi.h
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_urlrequest_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:124</a>
     */
    void cancel();
    /**
     * Create a new URL request that is not associated with a specific browser or frame. Use {@link net.kurobako.cef4j.gen.CefFrame#createUrlRequest(CefRequest, CefUrlRequestClient)} instead if you want the request to have this association, in which case it may be handled differently (see documentation on that method). A request created with this method may only originate from the browser process, and will behave as follows:
     * <ul>
     * <li>It may be intercepted by the client via CefResourceRequestHandler or</li>
     * </ul>
     * CefSchemeHandlerFactory.
     * <ul>
     * <li>POST data may only contain only a single element of type PDE_TYPE_FILE</li>
     * </ul>
     * or PDE_TYPE_BYTES.
     * <ul>
     * <li>If {@code request_context} is empty the global request context will be used.</li>
     * </ul>
     * <p>
     * The {@code request} object will be marked as read-only after calling this method.
     * <p>Definition generated from cef_urlrequest_capi.h
     * <pre>CEF_EXPORT cef_urlrequest_t* cef_urlrequest_create(struct _cef_request_t* request, struct _cef_urlrequest_client_t* client, struct _cef_request_context_t* request_context);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__urlrequest_8h.html">cef_urlrequest.h:62</a>
     */
    static Optional<CefUrlRequest> create(@Nullable CefRequest request, @Nullable CefUrlRequestClient client, @Nullable CefRequestContext requestContext) {
      return Optional.ofNullable(NativePeer.create0(request, client, requestContext));
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
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
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
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public Optional<CefRequest> getRequest() {
          checkNotClosed();
          return Optional.ofNullable(getRequest0(nativePtr));
      }

        @Override
      public Optional<CefUrlRequestClient> getClient() {
          checkNotClosed();
          return Optional.ofNullable(getClient0(nativePtr));
      }

        @Override
      public CefUrlRequestStatus getRequestStatus() {
          checkNotClosed();
          return getRequestStatus0(nativePtr);
      }

        @Override
      public CefErrorCode getRequestError() {
          checkNotClosed();
          return getRequestError0(nativePtr);
      }

        @Override
      public Optional<CefResponse> getResponse() {
          checkNotClosed();
          return Optional.ofNullable(getResponse0(nativePtr));
      }

        @Override
      public boolean responseWasCached() {
          checkNotClosed();
          return responseWasCached0(nativePtr);
      }

        @Override
      public void cancel() {
          checkNotClosed();
          cancel0(nativePtr);
      }


        static native CefRequest getRequest0(long self);

        static native CefUrlRequestClient getClient0(long self);

        static native CefUrlRequestStatus getRequestStatus0(long self);

        static native CefErrorCode getRequestError0(long self);

        static native CefResponse getResponse0(long self);

        static native boolean responseWasCached0(long self);

        static native void cancel0(long self);

        static native CefUrlRequest create0(@Nullable CefRequest request, @Nullable CefUrlRequestClient client, @Nullable CefRequestContext requestContext);

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
