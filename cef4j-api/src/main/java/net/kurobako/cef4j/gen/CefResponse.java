// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a web response. The methods of this class may be called on any thread.
 *
 * <p>Definition generated from cef_response_capi.h
 *
 * <pre>typedef struct _cef_response_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_response_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:45</a>
 */
public interface CefResponse extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is read-only.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:60</a>
     */
    boolean isReadOnly();

    /**
     * Get the response error code. Returns {@code ERR_NONE} if there was no error.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>cef_errorcode_t (CEF_CALLBACK* get_error)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:66</a>
     */
    CefErrorCode getError();

    /**
     * Set the response error code. This can be used by custom scheme handlers to return errors during initial request
     * processing.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_error)(struct _cef_response_t* self, cef_errorcode_t error);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:72</a>
     */
    void setError(@Nonnull CefErrorCode error);

    /**
     * Get the response status code.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>int (CEF_CALLBACK* get_status)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:79</a>
     */
    int getStatus();

    /**
     * Set the response status code.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_status)(struct _cef_response_t* self, int status);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:85</a>
     */
    void setStatus(int status);

    /**
     * Get the response status text.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_status_text)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:91</a>
     */
    Optional<String> getStatusText();

    /**
     * Set the response status text.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_status_text)(struct _cef_response_t* self, const cef_string_t* statusText);</pre>
     *
     * @param statusText may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:97</a>
     */
    void setStatusText(@Nullable String statusText);

    /**
     * Get the response mime type.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_mime_type)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:103</a>
     */
    Optional<String> getMimeType();

    /**
     * Set the response mime type.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_mime_type)(struct _cef_response_t* self, const cef_string_t* mimeType);</pre>
     *
     * @param mimeType may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:109</a>
     */
    void setMimeType(@Nullable String mimeType);

    /**
     * Get the response charset.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_charset)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:115</a>
     */
    Optional<String> getCharset();

    /**
     * Set the response charset.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_charset)(struct _cef_response_t* self, const cef_string_t* charset);</pre>
     *
     * @param charset may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:121</a>
     */
    void setCharset(@Nullable String charset);

    /**
     * Get the value for the specified response header field.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>
     * cef_string_userfree_t (CEF_CALLBACK* get_header_by_name)(struct _cef_response_t* self, const cef_string_t* name);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:127</a>
     */
    Optional<String> getHeaderByName(@Nullable String name);

    /**
     * Set the header {@code name} to {@code value}. If {@code overwrite} is {@code true} any existing values will be
     * replaced with the new value. If {@code overwrite} is {@code false} any existing values will not be overwritten.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* set_header_by_name)(struct _cef_response_t* self, const cef_string_t* name, const cef_string_t* value, int overwrite);
     * </pre>
     *
     * @param value may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:133</a>
     */
    void setHeaderByName(@Nullable String name, @Nullable String value, boolean overwrite);

    /**
     * Get all response header fields.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>void (CEF_CALLBACK* get_header_map)(struct _cef_response_t* self, cef_string_multimap_t headerMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:143</a>
     */
    void getHeaderMap(@Nonnull Map<String, List<String>> headerMap);

    /**
     * Set all response header fields.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_header_map)(struct _cef_response_t* self, cef_string_multimap_t headerMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:149</a>
     */
    void setHeaderMap(@Nonnull Map<String, List<String>> headerMap);

    /**
     * Get the resolved URL after redirects or changed as a result of HSTS.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:155</a>
     */
    Optional<String> getUrl();

    /**
     * Set the resolved URL after redirects or changed as a result of HSTS.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_url)(struct _cef_response_t* self, const cef_string_t* url);</pre>
     *
     * @param url may be null
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:161</a>
     */
    void setUrl(@Nullable String url);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_response_capi.h
     *
     * <pre>CEF_EXPORT cef_response_t* cef_response_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefResponse> create() {
        return Optional.ofNullable(NativePeer.N_Create());
    }

    final class NativePeer implements CefResponse, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefResponse has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefResponse.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefResponse 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isReadOnly() {
            checkNotClosed();
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public CefErrorCode getError() {
            checkNotClosed();
            return N_GetError(nativePtr);
        }

        @Override
        public void setError(@Nonnull CefErrorCode error) {
            checkNotClosed();
            N_SetError(nativePtr, error);
        }

        @Override
        public int getStatus() {
            checkNotClosed();
            return N_GetStatus(nativePtr);
        }

        @Override
        public void setStatus(int status) {
            checkNotClosed();
            N_SetStatus(nativePtr, status);
        }

        @Override
        public Optional<String> getStatusText() {
            checkNotClosed();
            return Optional.ofNullable(N_GetStatusText(nativePtr));
        }

        @Override
        public void setStatusText(@Nullable String statusText) {
            checkNotClosed();
            N_SetStatusText(nativePtr, statusText);
        }

        @Override
        public Optional<String> getMimeType() {
            checkNotClosed();
            return Optional.ofNullable(N_GetMimeType(nativePtr));
        }

        @Override
        public void setMimeType(@Nullable String mimeType) {
            checkNotClosed();
            N_SetMimeType(nativePtr, mimeType);
        }

        @Override
        public Optional<String> getCharset() {
            checkNotClosed();
            return Optional.ofNullable(N_GetCharset(nativePtr));
        }

        @Override
        public void setCharset(@Nullable String charset) {
            checkNotClosed();
            N_SetCharset(nativePtr, charset);
        }

        @Override
        public Optional<String> getHeaderByName(@Nullable String name) {
            checkNotClosed();
            return Optional.ofNullable(N_GetHeaderByName(nativePtr, name));
        }

        @Override
        public void setHeaderByName(@Nullable String name, @Nullable String value, boolean overwrite) {
            checkNotClosed();
            N_SetHeaderByName(nativePtr, name, value, overwrite);
        }

        @Override
        public void getHeaderMap(@Nonnull Map<String, List<String>> headerMap) {
            checkNotClosed();
            N_GetHeaderMap(nativePtr, headerMap);
        }

        @Override
        public void setHeaderMap(@Nonnull Map<String, List<String>> headerMap) {
            checkNotClosed();
            N_SetHeaderMap(nativePtr, headerMap);
        }

        @Override
        public Optional<String> getUrl() {
            checkNotClosed();
            return Optional.ofNullable(N_GetUrl(nativePtr));
        }

        @Override
        public void setUrl(@Nullable String url) {
            checkNotClosed();
            N_SetUrl(nativePtr, url);
        }

        private static native boolean N_IsReadOnly(long self);

        private static native CefErrorCode N_GetError(long self);

        private static native void N_SetError(long self, CefErrorCode error);

        private static native int N_GetStatus(long self);

        private static native void N_SetStatus(long self, int status);

        private static native String N_GetStatusText(long self);

        private static native void N_SetStatusText(long self, String statusText);

        private static native String N_GetMimeType(long self);

        private static native void N_SetMimeType(long self, String mimeType);

        private static native String N_GetCharset(long self);

        private static native void N_SetCharset(long self, String charset);

        private static native String N_GetHeaderByName(long self, String name);

        private static native void N_SetHeaderByName(long self, String name, String value, boolean overwrite);

        private static native void N_GetHeaderMap(long self, Map<String, List<String>> headerMap);

        private static native void N_SetHeaderMap(long self, Map<String, List<String>> headerMap);

        private static native String N_GetUrl(long self);

        private static native void N_SetUrl(long self, String url);

        static native CefResponse N_Create();

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
            return "CefResponse{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
