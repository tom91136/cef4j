// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Generic callback interface used for asynchronous continuation.
 *
 * <p>Definition generated from cef_callback_capi.h
 *
 * <pre>typedef struct _cef_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__callback_8h.html">cef_callback.h:43</a>
 */
public interface CefCallback extends CefLibraryObject {

    /**
     * Call to continue the download. Set {@code download_path} to the full file path for the download including the
     * file name or leave blank to use the suggested name and the default temp directory. Set {@code show_dialog} to
     * {@code true} if you do wish to show the default "Save As" dialog.
     *
     * <p>Definition generated from cef_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:51</a>
     */
    void cont();

    /**
     * Cancel processing.
     *
     * <p>Definition generated from cef_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_callback_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__callback_8h.html">cef_callback.h:55</a>
     */
    void cancel();

    final class NativePeer implements CefCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefCallback 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public void cont() {
            checkNotClosed();
            N_Cont(nativePtr);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            N_Cancel(nativePtr);
        }

        private static native void N_Cont(long self);

        private static native void N_Cancel(long self);

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
            return "CefCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
