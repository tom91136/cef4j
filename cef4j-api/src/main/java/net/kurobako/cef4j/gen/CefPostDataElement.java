// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Class used to represent a single element in the request post data. The methods of this class may be called on any
 * thread.
 *
 * <p>Definition generated from cef_request_capi.h
 *
 * <pre>typedef struct _cef_post_data_element_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_post_data_element_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:286</a>
 */
public interface CefPostDataElement extends CefLibraryObject {

    /**
     * Returns {@code true} if the values of this object are read-only. Some APIs may expose read-only objects.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_read_only)(struct _cef_post_data_element_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__print__settings_8h.html">cef_print_settings.h:68</a>
     */
    boolean isReadOnly();

    /**
     * Remove all contents from the post data element.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_to_empty)(struct _cef_post_data_element_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:310</a>
     */
    void setToEmpty();

    /**
     * The post data element will represent a file.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_to_file)(struct _cef_post_data_element_t* self, const cef_string_t* fileName);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:316</a>
     */
    void setToFile(@Nonnull String filename);

    /**
     * The post data element will represent bytes. The bytes passed in will be copied.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>void (CEF_CALLBACK* set_to_bytes)(struct _cef_post_data_element_t* self, size_t size, const void* bytes);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:322</a>
     */
    void setToBytes(@Nonnull ByteBuffer bytes);

    /**
     * Returns the item type for the specified {@code command_id}.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_postdataelement_type_t (CEF_CALLBACK* get_type)(struct _cef_post_data_element_t* self);</pre>
     *
     * @return the result, or {@code MENUITEMTYPE_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__menu__model_8h.html">cef_menu_model.h:215</a>
     */
    CefPostdataelementType getType();

    /**
     * Return the file name.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_file)(struct _cef_post_data_element_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:335</a>
     */
    Optional<String> getFile();

    /**
     * Return the number of bytes.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_bytes_count)(struct _cef_post_data_element_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:341</a>
     */
    long getBytesCount();

    /**
     * Read up to {@code size} bytes into {@code bytes} and return the number of bytes actually read.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_bytes)(struct _cef_post_data_element_t* self, size_t size, void* bytes);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__request_8h.html">cef_request.h:347</a>
     */
    long getBytes(@Nonnull ByteBuffer bytes);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_request_capi.h
     *
     * <pre>CEF_EXPORT cef_post_data_element_t* cef_post_data_element_create(void);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefPostDataElement> create() {
        return Optional.ofNullable(NativePeer.N_Create());
    }

    final class NativePeer implements CefPostDataElement, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPostDataElement.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPostDataElement 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isReadOnly() {
            return N_IsReadOnly(nativePtr);
        }

        @Override
        public void setToEmpty() {
            N_SetToEmpty(nativePtr);
        }

        @Override
        public void setToFile(@Nonnull String filename) {
            N_SetToFile(nativePtr, filename);
        }

        @Override
        public void setToBytes(@Nonnull ByteBuffer bytes) {
            N_SetToBytes(nativePtr, bytes);
        }

        @Override
        public CefPostdataelementType getType() {
            return N_GetType(nativePtr);
        }

        @Override
        public Optional<String> getFile() {
            return Optional.ofNullable(N_GetFile(nativePtr));
        }

        @Override
        public long getBytesCount() {
            return N_GetBytesCount(nativePtr);
        }

        @Override
        public long getBytes(@Nonnull ByteBuffer bytes) {
            return N_GetBytes(nativePtr, bytes);
        }

        private static native boolean N_IsReadOnly(long self);

        private static native void N_SetToEmpty(long self);

        private static native void N_SetToFile(long self, String filename);

        private static native void N_SetToBytes(long self, ByteBuffer bytes);

        private static native CefPostdataelementType N_GetType(long self);

        private static native String N_GetFile(long self);

        private static native long N_GetBytesCount(long self);

        private static native long N_GetBytes(long self, ByteBuffer bytes);

        static native CefPostDataElement N_Create();

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
