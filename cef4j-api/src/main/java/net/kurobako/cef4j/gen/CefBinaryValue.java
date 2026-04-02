// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.nio.ByteBuffer;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class representing a binary value. Can be used on any process and thread.
 *
 * <p>Definition generated from cef_values_capi.h
 *
 * <pre>typedef struct _cef_binary_value_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_binary_value_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:233</a>
 */
public interface CefBinaryValue extends CefLibraryObject {

    /**
     * Returns {@code true} if this object is valid. This object may become invalid if the underlying data is owned by
     * another object (e.g. list or dictionary) and that other object is then modified or destroyed. Do not call any
     * other methods if this method returns {@code false}.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_binary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:246</a>
     */
    boolean isValid();

    /**
     * Returns {@code true} if this object is currently owned by another object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_owned)(struct _cef_binary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:255</a>
     */
    boolean isOwned();

    /**
     * Returns {@code true} if this object and {@code that} object have the same underlying data.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_same)(struct _cef_binary_value_t* self, struct _cef_binary_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:261</a>
     */
    boolean isSame(@Nullable CefBinaryValue that);

    /**
     * Returns {@code true} if this object and {@code that} object have an equivalent underlying value but are not
     * necessarily the same object.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_equal)(struct _cef_binary_value_t* self, struct _cef_binary_value_t* that);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:268</a>
     */
    boolean isEqual(@Nullable CefBinaryValue that);

    /**
     * Returns a copy of this object. The data in this object will also be copied.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>cef_binary_value_t* (CEF_CALLBACK* copy)(struct _cef_binary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:275</a>
     */
    Optional<CefBinaryValue> copy();

    NativePointer getRawData();

    /**
     * Returns the data size.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_size)(struct _cef_binary_value_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:289</a>
     */
    long getSize();

    /**
     * Read up to {@code buffer_size} number of bytes into {@code buffer}. Reading begins at the specified byte
     * {@code data_offset}. Returns the number of bytes read.
     *
     * <p><b>The C API {@code void*} buffer parameter has been converted to {@link java.nio.ByteBuffer}; the hidden
     * {@code bufferSize} parameter is derived from the buffer's capacity.</b>
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>
     * size_t (CEF_CALLBACK* get_data)(struct _cef_binary_value_t* self, void* buffer, size_t buffer_size, size_t data_offset);
     * </pre>
     *
     * @param buffer <b>a direct {@link java.nio.ByteBuffer} whose capacity is the buffer size. This buffer is not
     *     reference-counted; its lifetime is not predictable beyond the scope of this callback. Storing a reference to
     *     it is unsafe unless explicitly permitted by the CEF documentation and may lead to native crashes.</b>
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__values_8h.html">cef_values.h:295</a>
     */
    long getData(@Nonnull ByteBuffer buffer, long dataOffset);
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_values_capi.h
     *
     * <pre>CEF_EXPORT cef_binary_value_t* cef_binary_value_create(const void* data, size_t data_size);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefBinaryValue> create(@Nonnull ByteBuffer data) {
        return Optional.ofNullable(NativePeer.N_Create(data));
    }

    /**
     * Decodes the base64 encoded string {@code data}. The returned value will be {@code null} if the decoding fails.
     *
     * <p>Definition generated from cef_parser_capi.h
     *
     * <pre>CEF_EXPORT cef_binary_value_t* cef_base64_decode(const cef_string_t* data);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__parser_8h.html">cef_parser.h:106</a>
     */
    static Optional<CefBinaryValue> base64Decode(@Nullable String data) {
        return Optional.ofNullable(NativePeer.N_Base64Decode(data));
    }

    final class NativePeer implements CefBinaryValue, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefBinaryValue has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefBinaryValue.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefBinaryValue 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isValid() {
            checkNotClosed();
            return N_IsValid(nativePtr);
        }

        @Override
        public boolean isOwned() {
            checkNotClosed();
            return N_IsOwned(nativePtr);
        }

        @Override
        public boolean isSame(@Nullable CefBinaryValue that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefBinaryValue");
            return N_IsSame(nativePtr, that);
        }

        @Override
        public boolean isEqual(@Nullable CefBinaryValue that) {
            checkNotClosed();
            CefLibraryObject.requireOpen(that, "CefBinaryValue");
            return N_IsEqual(nativePtr, that);
        }

        @Override
        public Optional<CefBinaryValue> copy() {
            checkNotClosed();
            return Optional.ofNullable(N_Copy(nativePtr));
        }

        @Override
        public NativePointer getRawData() {
            checkNotClosed();
            return N_GetRawData(nativePtr);
        }

        @Override
        public long getSize() {
            checkNotClosed();
            return N_GetSize(nativePtr);
        }

        @Override
        public long getData(@Nonnull ByteBuffer buffer, long dataOffset) {
            checkNotClosed();
            return N_GetData(nativePtr, buffer, dataOffset);
        }

        private static native boolean N_IsValid(long self);

        private static native boolean N_IsOwned(long self);

        private static native boolean N_IsSame(long self, CefBinaryValue that);

        private static native boolean N_IsEqual(long self, CefBinaryValue that);

        private static native CefBinaryValue N_Copy(long self);

        private static native NativePointer N_GetRawData(long self);

        private static native long N_GetSize(long self);

        private static native long N_GetData(long self, ByteBuffer buffer, long dataOffset);

        static native CefBinaryValue N_Create(ByteBuffer data);

        static native CefBinaryValue N_Base64Decode(String data);

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
            return "CefBinaryValue{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
