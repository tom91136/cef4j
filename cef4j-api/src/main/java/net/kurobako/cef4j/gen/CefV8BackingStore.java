// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

/**
 * Structure representing a V8 ArrayBuffer backing store. The backing store holds the memory that backs an ArrayBuffer.
 * It must be created on a thread with a valid V8 isolate (renderer main thread or WebWorker thread). Once created, the
 * data() pointer can be safely read/written from any thread. This allows expensive operations like memcpy to be
 * performed on a background thread before creating the ArrayBuffer on the V8 thread. The backing store is consumed when
 * passed to CefV8Value.cefV8ValueCreateArrayBufferFromBackingStore(), after which is_valid() returns {@code false} (0).
 * NOTE: This struct is allocated DLL-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_backing_store_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_backing_store_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:398</a>
 */
public interface CefV8BackingStore extends CefLibraryObject {

    NativePointer data();

    /**
     * Returns the size of the allocated memory in bytes, or 0 if the backing store has been consumed.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* byte_length)(struct _cef_v8_backing_store_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:466</a>
     */
    long byteLength();

    /**
     * Returns {@code true} if this object is valid. Do not call any other methods if this function returns
     * {@code false}.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_backing_store_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__item_8h.html">cef_download_item.h:49</a>
     */
    boolean isValid();
    /**
     * Create a new backing store with allocated memory of {@code byte_length} bytes. The memory is uninitialized. This
     * method must be called on a thread with a valid V8 isolate. The returned object can safely be passed to other
     * threads. Returns {@code null} on failure.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>CEF_EXPORT cef_v8_backing_store_t* cef_v8_backing_store_create(size_t byte_length);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefV8BackingStore> create(long byteLength) {
        return Optional.ofNullable(NativePeer.N_Create(byteLength));
    }

    final class NativePeer implements CefV8BackingStore, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefV8BackingStore has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefV8BackingStore.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefV8BackingStore 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public NativePointer data() {
            checkNotClosed();
            return N_Data(nativePtr);
        }

        @Override
        public long byteLength() {
            checkNotClosed();
            return N_ByteLength(nativePtr);
        }

        @Override
        public boolean isValid() {
            checkNotClosed();
            return N_IsValid(nativePtr);
        }

        private static native NativePointer N_Data(long self);

        private static native long N_ByteLength(long self);

        private static native boolean N_IsValid(long self);

        static native CefV8BackingStore N_Create(long byteLength);

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
            return "CefV8BackingStore{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
