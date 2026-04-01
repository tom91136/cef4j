// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
public interface CefRequest extends CefLibraryObject {

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_request_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:68</a>
     */
    boolean isReadOnly();

    /**
     * Returns the URL.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_request_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:143</a>
     */
    Optional<String> getUrl();

    /**
     * Set the resolved URL after redirects or changed as a result of HSTS.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_url)(struct _cef_request_t* self, const cef_string_t* url);</pre>
     *
     * @param url may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:161</a>
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
    void setMethod(@Nonnull String method);

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
    void setPostData(@Nonnull CefPostData postdata);

    /**
     * Get all response header fields.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_header_map)(struct _cef_request_t* self, cef_string_multimap_t headerMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:143</a>
     */
    void getHeaderMap(@Nonnull Map<String, List<String>> headermap);

    /**
     * Set all response header fields.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_header_map)(struct _cef_request_t* self, cef_string_multimap_t headerMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:149</a>
     */
    void setHeaderMap(@Nonnull Map<String, List<String>> headermap);

    /**
     * Get the value for the specified response header field.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_header_by_name)(struct _cef_request_t* self, const cef_string_t* name);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:127</a>
     */
    Optional<String> getHeaderByName(@Nonnull String name);

    /**
     * Set the header {@code name} to {@code value}. If {@code overwrite} is {@code true} any existing values will be
     * replaced with the new value. If {@code overwrite} is {@code false} any existing values will not be overwritten.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_header_by_name)(struct _cef_request_t* self, const cef_string_t* name, const cef_string_t* value, int overwrite);
     * </pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:133</a>
     */
    void setHeaderByName(@Nonnull String name, @Nullable String value, boolean overwrite);

    /**
     * Handle assignment of the interceptor value identified by {@code index}. {@code object} is the receiver ('this'
     * object) of the interceptor. {@code value} is the new value being assigned to the interceptor. If assignment
     * fails, set {@code exception} to the exception that will be thrown. Return {@code true} if interceptor assignment
     * was handled, {@code false} otherwise.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set)(struct _cef_request_t* self, const cef_string_t* url, const cef_string_t* method, struct _cef_post_data_t* postData, cef_string_multimap_t headerMap);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:338</a>
     */
    void set(
            @Nonnull String url,
            @Nonnull String method,
            @Nonnull CefPostData postdata,
            @Nonnull Map<String, List<String>> headermap);

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
     * Returns the transition type which indicates what the user did to move to this page from the previous page.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_transition_type_t (CEF_CALLBACK* get_transition_type)(struct _cef_request_t* self);</pre>
     *
     * @return the result, or {@code TT_EXPLICIT} for default handling
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__navigation__entry_8h.html">cef_navigation_entry.h:85</a>
     */
    CefTransitionType getTransitionType();

    /**
     * Returns the globally unique identifier for this frame or empty if the underlying frame does not yet exist.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int64_t (CEF_CALLBACK* get_identifier)(struct _cef_request_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__frame_8h.html">cef_frame.h:188</a>
     */
    long getIdentifier();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>CEF_EXPORT cef_request_t* cef_request_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefRequest> create() {
        return Optional.ofNullable(NativePeer.N_Create());
    }

    final class NativePeer implements CefRequest, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public Optional<String> getUrl() {
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public void setUrl(@Nullable String url) {
            N_SetUrl(nativePtr, url);
        }

        @Override
        public Optional<String> getMethod() {
            return Optional.ofNullable(N_GetMethod(nativePtr));
        }

        @Override
        public void setMethod(@Nonnull String method) {
            N_SetMethod(nativePtr, method);
        }

        @Override
        public void setReferrer(@Nullable String referrerUrl, @Nonnull CefReferrerPolicy policy) {
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
        public Optional<CefPostData> getPostData() {
            return Optional.ofNullable(N_GetPostData(nativePtr));
        }

        @Override
        public void setPostData(@Nonnull CefPostData postdata) {
            N_SetPostData(nativePtr, postdata);
        }

        @Override
        public void getHeaderMap(@Nonnull Map<String, List<String>> headermap) {
            N_GetHeaderMap(nativePtr, headermap);
        }

        @Override
        public void setHeaderMap(@Nonnull Map<String, List<String>> headermap) {
            N_SetHeaderMap(nativePtr, headermap);
        }

        @Override
        public Optional<String> getHeaderByName(@Nonnull String name) {
            return Optional.ofNullable(N_GetHeaderByName(nativePtr, name));
        }

        @Override
        public void setHeaderByName(@Nonnull String name, @Nullable String value, boolean overwrite) {
            N_SetHeaderByName(nativePtr, name, value, overwrite);
        }

        @Override
        public void set(
                @Nonnull String url,
                @Nonnull String method,
                @Nonnull CefPostData postdata,
                @Nonnull Map<String, List<String>> headermap) {
            N_Set(nativePtr, url, method, postdata, headermap);
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
        public void setFirstPartyForCookies(@Nullable String url) {
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

        private static native boolean N_IsReadOnly(long self);

        private static native String N_GetUrl(long self);

        private static native void N_SetUrl(long self, String url);

        private static native String N_GetMethod(long self);

        private static native void N_SetMethod(long self, String method);

        private static native void N_SetReferrer(long self, String referrerUrl, CefReferrerPolicy policy);

        private static native String N_GetReferrerUrl(long self);

        private static native CefReferrerPolicy N_GetReferrerPolicy(long self);

        private static native CefPostData N_GetPostData(long self);

        private static native void N_SetPostData(long self, CefPostData postdata);

        private static native void N_GetHeaderMap(long self, Map<String, List<String>> headermap);

        private static native void N_SetHeaderMap(long self, Map<String, List<String>> headermap);

        private static native String N_GetHeaderByName(long self, String name);

        private static native void N_SetHeaderByName(long self, String name, String value, boolean overwrite);

        private static native void N_Set(
                long self, String url, String method, CefPostData postdata, Map<String, List<String>> headermap);

        private static native int N_GetFlags(long self);

        private static native void N_SetFlags(long self, int flags);

        private static native String N_GetFirstPartyForCookies(long self);

        private static native void N_SetFirstPartyForCookies(long self, String url);

        private static native CefResourceType N_GetResourceType(long self);

        private static native CefTransitionType N_GetTransitionType(long self);

        private static native long N_GetIdentifier(long self);

        static native CefRequest N_Create();

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
