// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Class used to make a URL request. URL requests are not associated with a browser instance so no CefClient callbacks
 * will be executed. URL requests can be created on any valid CEF thread in either the browser or render process. Once
 * created the methods of the URL request object must be accessed on the same thread that created it.
 */
public interface CefUrlrequest {

    /**
     * Returns the request object used to create this URL request. The returned object is read-only and should not be
     * modified.
     */
    long getRequest();

    /** Returns the client. */
    long getClient();

    /**
     * Returns the request status.
     *
     * @return the result, or {@code UR_UNKNOWN} for default handling
     */
    CefUrlrequestStatus getRequestStatus();

    /**
     * Returns the request error if status is UR_CANCELED or UR_FAILED, or 0 otherwise.
     *
     * @return the result, or {@code ERR_NONE} for default handling
     */
    CefErrorcode getRequestError();

    /**
     * Returns the response, or NULL if no response information is available. Response information will only be
     * available after the upload has completed. The returned object is read-only and should not be modified.
     */
    long getResponse();

    /**
     * Returns true if the response body was served from the cache. This includes responses for which revalidation was
     * required.
     */
    boolean responseWasCached();

    /** Call to cancel the download. */
    void cancel();

    static class NativePeer implements CefUrlrequest {
        private volatile long nativePtr;

        @Override
        public long getRequest() {
            return N_GetRequest(nativePtr);
        }

        @Override
        public long getClient() {
            return N_GetClient(nativePtr);
        }

        @Override
        public CefUrlrequestStatus getRequestStatus() {
            return N_GetRequestStatus(nativePtr);
        }

        @Override
        public CefErrorcode getRequestError() {
            return N_GetRequestError(nativePtr);
        }

        @Override
        public long getResponse() {
            return N_GetResponse(nativePtr);
        }

        @Override
        public boolean responseWasCached() {
            return N_ResponseWasCached(nativePtr);
        }

        @Override
        public void cancel() {
            N_Cancel(nativePtr);
        }

        private native long N_GetRequest(long self);

        private native long N_GetClient(long self);

        private native CefUrlrequestStatus N_GetRequestStatus(long self);

        private native CefErrorcode N_GetRequestError(long self);

        private native long N_GetResponse(long self);

        private native boolean N_ResponseWasCached(long self);

        private native void N_Cancel(long self);

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
            return "CefUrlrequest{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
