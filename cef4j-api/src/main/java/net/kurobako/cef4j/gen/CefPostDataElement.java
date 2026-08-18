// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class used to represent a single element in the request post data. The methods of this class may be called on any thread.
 * <p>Definition generated from cef_request_capi.h
 * <pre>typedef struct _cef_post_data_element_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_post_data_element_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:286</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused", "NullableForbidden"})
public interface CefPostDataElement extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is read-only.
     * <p>Definition generated from cef_request_capi.h
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_post_data_element_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:304</a>
     */
    boolean isReadOnly();

    /**
     * Remove all contents from the post data element.
     * <p>Definition generated from cef_request_capi.h
     * <pre>void (CEF_CALLBACK* set_to_empty)(struct _cef_post_data_element_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:310</a>
     */
    void setToEmpty();

    /**
     * The post data element will represent a file.
     * <p>Definition generated from cef_request_capi.h
     * <pre>void (CEF_CALLBACK* set_to_file)(struct _cef_post_data_element_t* self, const cef_string_t* fileName);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:316</a>
     */
    void setToFile(@Nullable String fileName);

    /**
     * The post data element will represent bytes.  The bytes passed in will be copied.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code size} parameter is derived from the buffer's capacity.</b>
     * <p>Definition generated from cef_request_capi.h
     * <pre>void (CEF_CALLBACK* set_to_bytes)(struct _cef_post_data_element_t* self, size_t size, const void* bytes);</pre>
     *
     * @param bytes <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:322</a>
     */
    void setToBytes(@Nonnull ByteBuffer bytes);

    /**
     * Return the type of this post data element.
     * <p>Definition generated from cef_request_capi.h
     * <pre>cef_postdataelement_type_t (CEF_CALLBACK* get_type)(struct _cef_post_data_element_t* self);</pre>
     *
     * @return the result, or {@code PDE_TYPE_EMPTY} for default handling
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:329</a>
     */
    CefPostdataelementType getType();

    /**
     * Return the file name.
     * <p>Definition generated from cef_request_capi.h
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_file)(struct _cef_post_data_element_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:335</a>
     */
    Optional<String> getFile();

    /**
     * Return the number of bytes.
     * <p>Definition generated from cef_request_capi.h
     * <pre>size_t (CEF_CALLBACK* get_bytes_count)(struct _cef_post_data_element_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:341</a>
     */
    long getBytesCount();

    /**
     * Read up to {@code size} bytes into {@code bytes} and return the number of bytes actually read.
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden {@code size} parameter is derived from the buffer's capacity.</b>
     * <p>Definition generated from cef_request_capi.h
     * <pre>size_t (CEF_CALLBACK* get_bytes)(struct _cef_post_data_element_t* self, size_t size, void* bytes);</pre>
     *
     * @param bytes <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:347</a>
     */
    long getBytes(@Nonnull ByteBuffer bytes);
    /**
     * Create a new CefPostDataElement object.
     * <p>Definition generated from cef_request_capi.h
     * <pre>CEF_EXPORT cef_post_data_element_t* cef_post_data_element_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request_8h.html">cef_request.h:298</a>
     */
    static Optional<CefPostDataElement> create() {
      return Optional.ofNullable(NativePeer.create0());
  }

    final class NativePeer implements CefPostDataElement, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefPostDataElement has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPostDataElement.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPostDataElement 0x{}", Long.toHexString(ptr));
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
      public void setToEmpty() {
          checkNotClosed();
          setToEmpty0(nativePtr);
      }

        @Override
      public void setToFile(@Nullable String fileName) {
          checkNotClosed();
          setToFile0(nativePtr, fileName);
      }

        @Override
      public void setToBytes(@Nonnull ByteBuffer bytes) {
          checkNotClosed();
          setToBytes0(nativePtr, bytes);
      }

        @Override
      public CefPostdataelementType getType() {
          checkNotClosed();
          return getType0(nativePtr);
      }

        @Override
      public Optional<String> getFile() {
          checkNotClosed();
          return Optional.ofNullable(getFile0(nativePtr));
      }

        @Override
      public long getBytesCount() {
          checkNotClosed();
          return getBytesCount0(nativePtr);
      }

        @Override
      public long getBytes(@Nonnull ByteBuffer bytes) {
          checkNotClosed();
          return getBytes0(nativePtr, bytes);
      }


        static native boolean isReadOnly0(long self);

        static native void setToEmpty0(long self);

        static native void setToFile0(long self, @Nullable String fileName);

        static native void setToBytes0(long self, @Nonnull ByteBuffer bytes);

        static native CefPostdataelementType getType0(long self);

        static native String getFile0(long self);

        static native long getBytesCount0(long self);

        static native long getBytes0(long self, @Nonnull ByteBuffer bytes);

        static native CefPostDataElement create0();

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
            return "CefPostDataElement{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
