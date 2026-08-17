// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Callback for asynchronous continuation of {@link net.kurobako.cef4j.gen.CefResourceHandler#skip(long, long[], CefResourceSkipCallback)}.
 * <p>Definition generated from cef_resource_handler_capi.h
 * <pre>typedef struct _cef_resource_skip_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_skip_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__handler_8h.html">cef_resource_handler.h:48</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefResourceSkipCallback extends CefLibraryObject {

    /**
     * Callback for asynchronous continuation of Skip(). If {@code bytes_skipped} > 0 then either Skip() will be called again until the requested number of bytes have been skipped or the request will proceed. If {@code bytes_skipped} &lt;= 0 the request will fail with {@code ERR_REQUEST_RANGE_NOT_SATISFIABLE}.
     * <p>Definition generated from cef_resource_handler_capi.h
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_resource_skip_callback_t* self, int64_t bytes_skipped);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__resource__handler_8h.html">cef_resource_handler.h:54</a>
     */
    void cont(long bytesSkipped);
    final class NativePeer implements CefResourceSkipCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefResourceSkipCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefResourceSkipCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefResourceSkipCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public void cont(long bytesSkipped) {
          checkNotClosed();
          cont0(nativePtr, bytesSkipped);
      }


        static native void cont0(long self, long bytesSkipped);


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
            return "CefResourceSkipCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
