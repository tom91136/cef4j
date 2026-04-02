// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Callback for asynchronous continuation of {@link CefResourceHandler#skip(long, long[], CefResourceSkipCallback)}.
 *
 * <p>Definition generated from cef_resource_handler_capi.h
 *
 * <pre>typedef struct _cef_resource_skip_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_skip_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:48</a>
 */
public interface CefResourceSkipCallback extends CefLibraryObject {

    /**
     * Call to continue the download. Set {@code download_path} to the full file path for the download including the
     * file name or leave blank to use the suggested name and the default temp directory. Set {@code show_dialog} to
     * {@code true} if you do wish to show the default "Save As" dialog.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_resource_skip_callback_t* self, int64_t bytes_skipped);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:51</a>
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
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
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
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public void cont(long bytesSkipped) {
            checkNotClosed();
            N_Cont(nativePtr, bytesSkipped);
        }

        private static native void N_Cont(long self, long bytesSkipped);

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
