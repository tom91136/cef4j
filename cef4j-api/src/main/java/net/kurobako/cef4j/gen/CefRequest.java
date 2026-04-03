// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Class used to represent a web request. The methods of this class may be called on any thread.
 *
 * <p>Definition generated from cef_request_capi.h
 *
 * <pre>typedef struct _cef_request_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_request_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:49</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefRequest extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is read-only.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_request_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:67</a>
     */
    boolean isReadOnly();

    /**
     * Get the fully qualified URL.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_request_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:73</a>
     */
    Optional<String> getUrl();

    /**
     * Set the fully qualified URL.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_url)(struct _cef_request_t* self, const cef_string_t* url);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:79</a>
     */
    void setUrl(@Nullable String url);

    /**
     * Get the request method type. The value will default to POST if post data is provided and GET otherwise.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_method)(struct _cef_request_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:85</a>
     */
    Optional<String> getMethod();

    /**
     * Set the request method type.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_method)(struct _cef_request_t* self, const cef_string_t* method);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:92</a>
     */
    void setMethod(@Nullable String method);

    /**
     * Set the referrer URL and policy. If non-empty the referrer URL must be fully qualified with an HTTP or HTTPS
     * scheme component. Any username, password or ref component will be removed.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_referrer)(struct _cef_request_t* self, const cef_string_t* referrer_url, cef_referrer_policy_t policy);
     * </pre>
     *
     * @param referrerUrl may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:98</a>
     */
    void setReferrer(@Nullable String referrerUrl, @Nonnull CefReferrerPolicy policy);

    /**
     * Get the referrer URL.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_referrer_url)(struct _cef_request_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:107</a>
     */
    Optional<String> getReferrerUrl();

    /**
     * Get the referrer policy.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_referrer_policy_t (CEF_CALLBACK* get_referrer_policy)(struct _cef_request_t* self);</pre>
     *
     * @return the result, or {@code REFERRER_POLICY_DEFAULT} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:113</a>
     */
    CefReferrerPolicy getReferrerPolicy();

    /**
     * Get the post data.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_post_data_t* (CEF_CALLBACK* get_post_data)(struct _cef_request_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:119</a>
     */
    Optional<CefPostData> getPostData();

    /**
     * Set the post data.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_post_data)(struct _cef_request_t* self, struct _cef_post_data_t* postData);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:125</a>
     */
    void setPostData(@Nullable CefPostData postData);

    /**
     * Get the header values. Will not include the Referer value if any.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_header_map)(struct _cef_request_t* self, cef_string_multimap_t headerMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:131</a>
     */
    void getHeaderMap(@Nonnull Map<String, List<String>> headerMap);

    /**
     * Set the header values. If a Referer value exists in the header map it will be removed and ignored.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_header_map)(struct _cef_request_t* self, cef_string_multimap_t headerMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:137</a>
     */
    void setHeaderMap(@Nonnull Map<String, List<String>> headerMap);

    /**
     * Returns the first header value for {@code name} or an empty string if not found. Will not return the Referer
     * value if any. Use GetHeaderMap instead if {@code name} might have multiple values.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_header_by_name)(struct _cef_request_t* self, const cef_string_t* name);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:144</a>
     */
    Optional<String> getHeaderByName(@Nullable String name);

    /**
     * Set the header {@code name} to {@code value}. If {@code overwrite} is {@code true} any existing values will be
     * replaced with the new value. If {@code overwrite} is {@code false} any existing values will not be overwritten.
     * The Referer value cannot be set using this method.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_header_by_name)(struct _cef_request_t* self, const cef_string_t* name, const cef_string_t* value, int overwrite);
     * </pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:152</a>
     */
    void setHeaderByName(@Nullable String name, @Nullable String value, boolean overwrite);

    /**
     * Set all values at one time.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set)(struct _cef_request_t* self, const cef_string_t* url, const cef_string_t* method, struct _cef_post_data_t* postData, cef_string_multimap_t headerMap);
     * </pre>
     *
     * @param postData may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:163</a>
     */
    void set(
            @Nullable String url,
            @Nullable String method,
            @Nullable CefPostData postData,
            @Nonnull Map<String, List<String>> headerMap);

    /**
     * Get the flags used in combination with CefURLRequest. See cef_urlrequest_flags_t for supported values.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_flags)(struct _cef_request_t* self);</pre>
     *
     * @return the result, or {@code UR_FLAG_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:172</a>
     */
    int getFlags();

    /**
     * Set the flags used in combination with CefURLRequest. See cef_urlrequest_flags_t for supported values.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_flags)(struct _cef_request_t* self, int flags);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:179</a>
     */
    void setFlags(int flags);

    /**
     * Get the URL to the first party for cookies used in combination with CefURLRequest.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_first_party_for_cookies)(struct _cef_request_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:186</a>
     */
    Optional<String> getFirstPartyForCookies();

    /**
     * Set the URL to the first party for cookies used in combination with CefURLRequest.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_first_party_for_cookies)(struct _cef_request_t* self, const cef_string_t* url);
     * </pre>
     *
     * @param url may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:193</a>
     */
    void setFirstPartyForCookies(@Nullable String url);

    /**
     * Get the resource type for this request. Only available in the browser process.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_resource_type_t (CEF_CALLBACK* get_resource_type)(struct _cef_request_t* self);</pre>
     *
     * @return the result, or {@code RT_SUB_RESOURCE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:200</a>
     */
    CefResourceType getResourceType();

    /**
     * Get the transition type for this request. Only available in the browser process and only applies to requests that
     * represent a main frame or sub-frame navigation.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_transition_type_t (CEF_CALLBACK* get_transition_type)(struct _cef_request_t* self);</pre>
     *
     * @return the result, or {@code TT_EXPLICIT} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:207</a>
     */
    CefTransitionType getTransitionType();

    /**
     * Returns the globally unique identifier for this request or 0 if not specified. Can be used by
     * CefResourceRequestHandler implementations in the browser process to track a single request across multiple
     * callbacks.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_identifier)(struct _cef_request_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:215</a>
     */
    long getIdentifier();
    /**
     * Create a new CefRequest object.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>CEF_EXPORT cef_request_t* cef_request_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:61</a>
     */
    static Optional<CefRequest> create() {
        return Optional.ofNullable(NativePeer.create0());
    }

    final class NativePeer implements CefRequest, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefRequest has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefRequest.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefRequest 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public boolean isReadOnly() {
            checkNotClosed();
            return isReadOnly0(nativePtr);
        }

        @Override
        public Optional<String> getUrl() {
            checkNotClosed();
            return Optional.ofNullable(getUrl0(nativePtr));
        }

        @Override
        public void setUrl(@Nullable String url) {
            checkNotClosed();
            setUrl0(nativePtr, url);
        }

        @Override
        public Optional<String> getMethod() {
            checkNotClosed();
            return Optional.ofNullable(getMethod0(nativePtr));
        }

        @Override
        public void setMethod(@Nullable String method) {
            checkNotClosed();
            setMethod0(nativePtr, method);
        }

        @Override
        public void setReferrer(@Nullable String referrerUrl, @Nonnull CefReferrerPolicy policy) {
            checkNotClosed();
            setReferrer0(nativePtr, referrerUrl, policy);
        }

        @Override
        public Optional<String> getReferrerUrl() {
            checkNotClosed();
            return Optional.ofNullable(getReferrerUrl0(nativePtr));
        }

        @Override
        public CefReferrerPolicy getReferrerPolicy() {
            checkNotClosed();
            return getReferrerPolicy0(nativePtr);
        }

        @Override
        public Optional<CefPostData> getPostData() {
            checkNotClosed();
            return Optional.ofNullable(getPostData0(nativePtr));
        }

        @Override
        public void setPostData(@Nullable CefPostData postData) {
            checkNotClosed();
            CefLibraryObject.requireOpen(postData, "CefPostData");
            setPostData0(nativePtr, postData);
        }

        @Override
        public void getHeaderMap(@Nonnull Map<String, List<String>> headerMap) {
            checkNotClosed();
            getHeaderMap0(nativePtr, headerMap);
        }

        @Override
        public void setHeaderMap(@Nonnull Map<String, List<String>> headerMap) {
            checkNotClosed();
            setHeaderMap0(nativePtr, headerMap);
        }

        @Override
        public Optional<String> getHeaderByName(@Nullable String name) {
            checkNotClosed();
            return Optional.ofNullable(getHeaderByName0(nativePtr, name));
        }

        @Override
        public void setHeaderByName(@Nullable String name, @Nullable String value, boolean overwrite) {
            checkNotClosed();
            setHeaderByName0(nativePtr, name, value, overwrite);
        }

        @Override
        public void set(
                @Nullable String url,
                @Nullable String method,
                @Nullable CefPostData postData,
                @Nonnull Map<String, List<String>> headerMap) {
            checkNotClosed();
            CefLibraryObject.requireOpen(postData, "CefPostData");
            set0(nativePtr, url, method, postData, headerMap);
        }

        @Override
        public int getFlags() {
            checkNotClosed();
            return getFlags0(nativePtr);
        }

        @Override
        public void setFlags(int flags) {
            checkNotClosed();
            setFlags0(nativePtr, flags);
        }

        @Override
        public Optional<String> getFirstPartyForCookies() {
            checkNotClosed();
            return Optional.ofNullable(getFirstPartyForCookies0(nativePtr));
        }

        @Override
        public void setFirstPartyForCookies(@Nullable String url) {
            checkNotClosed();
            setFirstPartyForCookies0(nativePtr, url);
        }

        @Override
        public CefResourceType getResourceType() {
            checkNotClosed();
            return getResourceType0(nativePtr);
        }

        @Override
        public CefTransitionType getTransitionType() {
            checkNotClosed();
            return getTransitionType0(nativePtr);
        }

        @Override
        public long getIdentifier() {
            checkNotClosed();
            return getIdentifier0(nativePtr);
        }

        private static native boolean isReadOnly0(long self);

        private static native String getUrl0(long self);

        private static native void setUrl0(long self, String url);

        private static native String getMethod0(long self);

        private static native void setMethod0(long self, String method);

        private static native void setReferrer0(long self, String referrerUrl, CefReferrerPolicy policy);

        private static native String getReferrerUrl0(long self);

        private static native CefReferrerPolicy getReferrerPolicy0(long self);

        private static native CefPostData getPostData0(long self);

        private static native void setPostData0(long self, CefPostData postData);

        private static native void getHeaderMap0(long self, Map<String, List<String>> headerMap);

        private static native void setHeaderMap0(long self, Map<String, List<String>> headerMap);

        private static native String getHeaderByName0(long self, String name);

        private static native void setHeaderByName0(long self, String name, String value, boolean overwrite);

        private static native void set0(
                long self, String url, String method, CefPostData postData, Map<String, List<String>> headerMap);

        private static native int getFlags0(long self);

        private static native void setFlags0(long self, int flags);

        private static native String getFirstPartyForCookies0(long self);

        private static native void setFirstPartyForCookies0(long self, String url);

        private static native CefResourceType getResourceType0(long self);

        private static native CefTransitionType getTransitionType0(long self);

        private static native long getIdentifier0(long self);

        static native CefRequest create0();

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
