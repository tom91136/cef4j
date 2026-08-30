// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-api
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Callback for asynchronous continuation of {@link net.kurobako.cef4j.gen.CefResourceHandler#read(java.nio.ByteBuffer,
 * int[], CefResourceReadCallback)}.
 *
 * <p>Definition generated from cef_resource_handler_capi.h
 *
 * <pre>typedef struct _cef_resource_read_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_read_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__handler_8h.html">cef_resource_handler.h:64</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-api -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
@NullableBoundary("generated external ABI")
public interface CefResourceReadCallback extends CefLibraryObject {

    /**
     * Callback for asynchronous continuation of Read(). If {@code bytes_read} == 0 the response will be considered
     * complete. If {@code bytes_read} > 0 then Read() will be called again until the request is complete (based on
     * either the result or the expected content length). If {@code bytes_read} &lt; 0 then the request will fail and
     * the {@code bytes_read} value will be treated as the error code.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_resource_read_callback_t* self, int bytes_read);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__handler_8h.html">cef_resource_handler.h:70</a>
     */
    void cont(int bytesRead);

    final class NativePeer implements CefResourceReadCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefResourceReadCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefResourceReadCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefResourceReadCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void cont(int bytesRead) {
            checkNotClosed();
            cont0(nativePtr, bytesRead);
        }

        static native void cont0(long self, int bytesRead);

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
            return "CefResourceReadCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
