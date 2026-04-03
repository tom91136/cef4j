// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Callback structure that is passed to CefV8Value.createArrayBuffer(). NOTE: This struct is allocated client-side.
 *
 * <p>Definition generated from cef_v8_capi.h
 *
 * <pre>typedef struct _cef_v8_array_buffer_release_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_v8_array_buffer_release_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8__capi_8h.html">cef_v8_capi.h:375</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefV8ArrayBufferReleaseCallback extends CefLibraryObject {

    /**
     * Called to release {@code buffer} when the ArrayBuffer JS object is garbage collected. {@code buffer} is the value
     * that was passed to CreateArrayBuffer along with this object.
     *
     * <p>Definition generated from cef_v8_capi.h
     *
     * <pre>void (CEF_CALLBACK* release_buffer)(struct _cef_v8_array_buffer_release_callback_t* self, void* buffer);
     * </pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__v8_8h.html">cef_v8.h:420</a>
     */
    void releaseBuffer(@Nullable NativePointer buffer);

    final class NativePeer implements CefV8ArrayBufferReleaseCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefV8ArrayBufferReleaseCallback has been closed");
        }

        private static final org.slf4j.Logger _log =
                org.slf4j.LoggerFactory.getLogger(CefV8ArrayBufferReleaseCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled())
                    _log.trace("release CefV8ArrayBufferReleaseCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void releaseBuffer(@Nullable NativePointer buffer) {
            checkNotClosed();
            releaseBuffer0(nativePtr, buffer);
        }

        private static native void releaseBuffer0(long self, NativePointer buffer);

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
            return "CefV8ArrayBufferReleaseCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
