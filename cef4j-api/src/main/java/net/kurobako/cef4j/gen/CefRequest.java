// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Class used to represent a web request. The methods of this class may be called on any thread. */
public interface CefRequest {

    /** Returns true if the values of this object are read-only. Some APIs may expose read-only objects. */
    boolean isReadOnly();

    Optional<String> getUrl();

    void setUrl(@Nonnull String url);

    /** Get the request method type. The value will default to POST if post data is provided and GET otherwise. */
    Optional<String> getMethod();

    /** Set the request method type. */
    void setMethod(@Nonnull String method);

    /**
     * Set the referrer URL and policy. If non-empty the referrer URL must be fully qualified with an HTTP or HTTPS
     * scheme component. Any username, password or ref component will be removed.
     *
     * @param referrerUrl may be null
     */
    void setReferrer(@Nullable String referrerUrl, @Nonnull CefReferrerPolicy policy);

    Optional<String> getReferrerUrl();

    /**
     * Get the referrer policy.
     *
     * @return the result, or {@code REFERRER_POLICY_DEFAULT} for default handling
     */
    CefReferrerPolicy getReferrerPolicy();

    /** Get the post data. */
    long getPostData();

    /** Set the post data. */
    void setPostData(long postData);

    /** Get all response header fields. */
    void getHeaderMap(@Nonnull java.util.Map<String, java.util.List<String>> headerMap);

    /** Set all response header fields. */
    void setHeaderMap(@Nonnull java.util.Map<String, java.util.List<String>> headerMap);

    /** Get the value for the specified response header field. */
    Optional<String> getHeaderByName(@Nonnull String name);

    /**
     * Set the header |name| to |value|. If |overwrite| is true any existing values will be replaced with the new value.
     * If |overwrite| is false any existing values will not be overwritten.
     *
     * @param value may be null
     */
    void setHeaderByName(@Nonnull String name, @Nullable String value, boolean overwrite);

    /**
     * Handle assignment of the interceptor value identified by |index|. |object| is the receiver ('this' object) of the
     * interceptor. |value| is the new value being assigned to the interceptor. If assignment fails, set |exception| to
     * the exception that will be thrown. Return true if interceptor assignment was handled, false otherwise.
     */
    void set(
            @Nonnull String url,
            @Nonnull String method,
            long postData,
            @Nonnull java.util.Map<String, java.util.List<String>> headerMap);

    /**
     * Get the flags used in combination with CefURLRequest. See cef_urlrequest_flags_t for supported values.
     *
     * @return the result, or {@code UR_FLAG_NONE} for default handling
     */
    int getFlags();

    /** Set the flags used in combination with CefURLRequest. See cef_urlrequest_flags_t for supported values. */
    void setFlags(int flags);

    /** Get the URL to the first party for cookies used in combination with CefURLRequest. */
    Optional<String> getFirstPartyForCookies();

    /**
     * Set the URL to the first party for cookies used in combination with CefURLRequest.
     *
     * @param url may be null
     */
    void setFirstPartyForCookies(@Nullable String url);

    /**
     * Get the resource type for this request. Only available in the browser process.
     *
     * @return the result, or {@code RT_SUB_RESOURCE} for default handling
     */
    CefResourceType getResourceType();

    /**
     * Returns the transition type which indicates what the user did to move to this page from the previous page.
     *
     * @return the result, or {@code TT_EXPLICIT} for default handling
     */
    CefTransitionType getTransitionType();

    /** Returns the globally unique identifier for this frame or empty if the underlying frame does not yet exist. */
    long getIdentifier();

    static class NativePeer implements CefRequest {
        private volatile long nativePtr;

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public Optional<String> getUrl() {
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public void setUrl(String url) {
            N_SetUrl(nativePtr, url);
        }

        @Override
        public Optional<String> getMethod() {
            return Optional.ofNullable(N_GetMethod(nativePtr));
        }

        @Override
        public void setMethod(String method) {
            N_SetMethod(nativePtr, method);
        }

        @Override
        public void setReferrer(String referrerUrl, CefReferrerPolicy policy) {
            N_SetReferrer(nativePtr, referrerUrl, policy);
        }

        @Override
        public Optional<String> getReferrerUrl() {
            return Optional.ofNullable(N_GetReferrerUrl(nativePtr));
        }

        @Override
        public CefReferrerPolicy getReferrerPolicy() {
            return N_GetReferrerPolicy(nativePtr);
        }

        @Override
        public long getPostData() {
            return N_GetPostData(nativePtr);
        }

        @Override
        public void setPostData(long postData) {
            N_SetPostData(nativePtr, postData);
        }

        @Override
        public void getHeaderMap(java.util.Map<String, java.util.List<String>> headerMap) {
            N_GetHeaderMap(nativePtr, headerMap);
        }

        @Override
        public void setHeaderMap(java.util.Map<String, java.util.List<String>> headerMap) {
            N_SetHeaderMap(nativePtr, headerMap);
        }

        @Override
        public Optional<String> getHeaderByName(String name) {
            return Optional.ofNullable(N_GetHeaderByName(nativePtr, name));
        }

        @Override
        public void setHeaderByName(String name, String value, boolean overwrite) {
            N_SetHeaderByName(nativePtr, name, value, overwrite);
        }

        @Override
        public void set(
                String url, String method, long postData, java.util.Map<String, java.util.List<String>> headerMap) {
            N_Set(nativePtr, url, method, postData, headerMap);
        }

        @Override
        public int getFlags() {
            return N_GetFlags(nativePtr);
        }

        @Override
        public void setFlags(int flags) {
            N_SetFlags(nativePtr, flags);
        }

        @Override
        public Optional<String> getFirstPartyForCookies() {
            return Optional.ofNullable(N_GetFirstPartyForCookies(nativePtr));
        }

        @Override
        public void setFirstPartyForCookies(String url) {
            N_SetFirstPartyForCookies(nativePtr, url);
        }

        @Override
        public CefResourceType getResourceType() {
            return N_GetResourceType(nativePtr);
        }

        @Override
        public CefTransitionType getTransitionType() {
            return N_GetTransitionType(nativePtr);
        }

        @Override
        public long getIdentifier() {
            return N_GetIdentifier(nativePtr);
        }

        private native boolean N_IsReadOnly(long self);

        private native String N_GetUrl(long self);

        private native void N_SetUrl(long self, String url);

        private native String N_GetMethod(long self);

        private native void N_SetMethod(long self, String method);

        private native void N_SetReferrer(long self, String referrerUrl, CefReferrerPolicy policy);

        private native String N_GetReferrerUrl(long self);

        private native CefReferrerPolicy N_GetReferrerPolicy(long self);

        private native long N_GetPostData(long self);

        private native void N_SetPostData(long self, long postData);

        private native void N_GetHeaderMap(long self, java.util.Map<String, java.util.List<String>> headerMap);

        private native void N_SetHeaderMap(long self, java.util.Map<String, java.util.List<String>> headerMap);

        private native String N_GetHeaderByName(long self, String name);

        private native void N_SetHeaderByName(long self, String name, String value, boolean overwrite);

        private native void N_Set(
                long self,
                String url,
                String method,
                long postData,
                java.util.Map<String, java.util.List<String>> headerMap);

        private native int N_GetFlags(long self);

        private native void N_SetFlags(long self, int flags);

        private native String N_GetFirstPartyForCookies(long self);

        private native void N_SetFirstPartyForCookies(long self, String url);

        private native CefResourceType N_GetResourceType(long self);

        private native CefTransitionType N_GetTransitionType(long self);

        private native long N_GetIdentifier(long self);

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
            return "CefRequest{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
