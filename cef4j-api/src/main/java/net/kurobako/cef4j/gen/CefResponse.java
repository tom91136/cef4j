// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a web response. The methods of this class may be called on any thread.
 * <p>Definition generated from cef_response_capi.h
 * <pre>typedef struct _cef_response_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_response_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefResponse extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is read-only.
     * <p>Definition generated from cef_response_capi.h
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:60</a>
     */
    boolean isReadOnly();

    /**
     * Get the response error code. Returns {@code ERR_NONE} if there was no error.
     * <p>Definition generated from cef_response_capi.h
     * <pre>cef_errorcode_t (CEF_CALLBACK* get_error)(struct _cef_response_t* self);</pre>
     *
     * @return the result, or {@code ERR_NONE} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:66</a>
     */
    CefErrorCode getError();

    /**
     * Set the response error code. This can be used by custom scheme handlers to return errors during initial request processing.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* set_error)(struct _cef_response_t* self, cef_errorcode_t error);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:72</a>
     */
    void setError(@Nonnull CefErrorCode error);

    /**
     * Get the response status code.
     * <p>Definition generated from cef_response_capi.h
     * <pre>int (CEF_CALLBACK* get_status)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:79</a>
     */
    int getStatus();

    /**
     * Set the response status code.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* set_status)(struct _cef_response_t* self, int status);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:85</a>
     */
    void setStatus(int status);

    /**
     * Get the response status text.
     * <p>Definition generated from cef_response_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_status_text)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:91</a>
     */
    Optional<String> getStatusText();

    /**
     * Set the response status text.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* set_status_text)(struct _cef_response_t* self, const cef_string_t* statusText);</pre>
     *
     * @param statusText may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:97</a>
     */
    void setStatusText(@Nullable String statusText);

    /**
     * Get the response mime type.
     * <p>Definition generated from cef_response_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_mime_type)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:103</a>
     */
    Optional<String> getMimeType();

    /**
     * Set the response mime type.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* set_mime_type)(struct _cef_response_t* self, const cef_string_t* mimeType);</pre>
     *
     * @param mimeType may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:109</a>
     */
    void setMimeType(@Nullable String mimeType);

    /**
     * Get the response charset.
     * <p>Definition generated from cef_response_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_charset)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:115</a>
     */
    Optional<String> getCharset();

    /**
     * Set the response charset.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* set_charset)(struct _cef_response_t* self, const cef_string_t* charset);</pre>
     *
     * @param charset may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:121</a>
     */
    void setCharset(@Nullable String charset);

    /**
     * Get the value for the specified response header field.
     * <p>Definition generated from cef_response_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_header_by_name)(struct _cef_response_t* self, const cef_string_t* name);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:127</a>
     */
    Optional<String> getHeaderByName(@Nullable String name);

    /**
     * Set the header {@code name} to {@code value}. If {@code overwrite} is {@code true} any existing values will be replaced with the new value. If {@code overwrite} is {@code false} any existing values will not be overwritten.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* set_header_by_name)(struct _cef_response_t* self, const cef_string_t* name, const cef_string_t* value, int overwrite);</pre>
     *
     * @param value may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:133</a>
     */
    void setHeaderByName(@Nullable String name, @Nullable String value, boolean overwrite);

    /**
     * Get all response header fields.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* get_header_map)(struct _cef_response_t* self, cef_string_multimap_t headerMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:143</a>
     */
    void getHeaderMap(@Nonnull Map<String, List<String>> headerMap);

    /**
     * Set all response header fields.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* set_header_map)(struct _cef_response_t* self, cef_string_multimap_t headerMap);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:149</a>
     */
    void setHeaderMap(@Nonnull Map<String, List<String>> headerMap);

    /**
     * Get the resolved URL after redirects or changed as a result of HSTS.
     * <p>Definition generated from cef_response_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_url)(struct _cef_response_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:155</a>
     */
    Optional<String> getUrl();

    /**
     * Set the resolved URL after redirects or changed as a result of HSTS.
     * <p>Definition generated from cef_response_capi.h
     * <pre>void (CEF_CALLBACK* set_url)(struct _cef_response_t* self, const cef_string_t* url);</pre>
     *
     * @param url may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:161</a>
     */
    void setUrl(@Nullable String url);
    /**
     * Create a new CefResponse object.
     * <p>Definition generated from cef_response_capi.h
     * <pre>CEF_EXPORT cef_response_t* cef_response_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__response_8h.html">cef_response.h:54</a>
     */
    static Optional<CefResponse> create() {
      return Optional.ofNullable(NativePeer.create0());
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
        public void peerClose() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean peerIsClosed() {
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
      public CefErrorCode getError() {
          checkNotClosed();
          return getError0(nativePtr);
      }

        @Override
      public void setError(@Nonnull CefErrorCode error) {
          checkNotClosed();
          setError0(nativePtr, error);
      }

        @Override
      public int getStatus() {
          checkNotClosed();
          return getStatus0(nativePtr);
      }

        @Override
      public void setStatus(int status) {
          checkNotClosed();
          setStatus0(nativePtr, status);
      }

        @Override
      public Optional<String> getStatusText() {
          checkNotClosed();
          return Optional.ofNullable(getStatusText0(nativePtr));
      }

        @Override
      public void setStatusText(@Nullable String statusText) {
          checkNotClosed();
          setStatusText0(nativePtr, statusText);
      }

        @Override
      public Optional<String> getMimeType() {
          checkNotClosed();
          return Optional.ofNullable(getMimeType0(nativePtr));
      }

        @Override
      public void setMimeType(@Nullable String mimeType) {
          checkNotClosed();
          setMimeType0(nativePtr, mimeType);
      }

        @Override
      public Optional<String> getCharset() {
          checkNotClosed();
          return Optional.ofNullable(getCharset0(nativePtr));
      }

        @Override
      public void setCharset(@Nullable String charset) {
          checkNotClosed();
          setCharset0(nativePtr, charset);
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
      public Optional<String> getUrl() {
          checkNotClosed();
          return Optional.ofNullable(getUrl0(nativePtr));
      }

        @Override
      public void setUrl(@Nullable String url) {
          checkNotClosed();
          setUrl0(nativePtr, url);
      }


        static native boolean isReadOnly0(long self);

        static native CefErrorCode getError0(long self);

        static native void setError0(long self, CefErrorCode error);

        static native int getStatus0(long self);

        static native void setStatus0(long self, int status);

        static native String getStatusText0(long self);

        static native void setStatusText0(long self, String statusText);

        static native String getMimeType0(long self);

        static native void setMimeType0(long self, String mimeType);

        static native String getCharset0(long self);

        static native void setCharset0(long self, String charset);

        static native String getHeaderByName0(long self, String name);

        static native void setHeaderByName0(long self, String name, String value, boolean overwrite);

        static native void getHeaderMap0(long self, Map<String, List<String>> headerMap);

        static native void setHeaderMap0(long self, Map<String, List<String>> headerMap);

        static native String getUrl0(long self);

        static native void setUrl0(long self, String url);

        static native CefResponse create0();

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
