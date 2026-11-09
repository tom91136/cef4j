// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class representing a V8 ArrayBuffer backing store. The backing store holds the memory that backs an ArrayBuffer. It
 * must be created on a thread with a valid V8 isolate (renderer main thread or WebWorker thread). Once created, the
 * Data() pointer can be safely read/written from any thread. This allows expensive operations like memcpy to be
 * performed on a background thread before creating the ArrayBuffer on the V8 thread. The backing store is consumed when
 * passed to net.kurobako.cef4j.gen.CefV8Value.createArrayBufferFromBackingStore(), after which IsValid() returns
 * {@code false}.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_backing_store_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_backing_store_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:430</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefV8BackingStore extends CefLibraryObject {

    NativePointer data();

    /**
     * Returns the size of the allocated memory in bytes, or 0 if the backing store has been consumed.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* byte_length)(struct _cef_v8_backing_store_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:466</a>
     */
    long byteLength();

    /**
     * Returns {@code true} if this backing store has not yet been consumed by CreateArrayBufferFromBackingStore().
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_valid)(struct _cef_v8_backing_store_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:473</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__v8_8h.html">cef_v8.h:445</a>
     */
    static Optional<CefV8BackingStore> create(long byteLength) {
        return Optional.ofNullable(NativePeer.create0(byteLength));
    }

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefV8BackingStore {
        NativePeer(long ptr) {
            super("CefV8BackingStore", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public NativePointer data() {
            checkNotClosed();
            return data0(nativePtr);
        }

        @Override
        public long byteLength() {
            checkNotClosed();
            return byteLength0(nativePtr);
        }

        @Override
        public boolean isValid() {
            checkNotClosed();
            return isValid0(nativePtr);
        }

        static native NativePointer data0(long self);

        static native long byteLength0(long self);

        static native boolean isValid0(long self);

        static native CefV8BackingStore create0(long byteLength);
    }
}
