// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Callback for asynchronous continuation of {@link CefResourceHandler#read(NativePointer, int, int[],
 * CefResourceReadCallback)}.
 *
 * <p>Definition generated from cef_resource_handler_capi.h
 *
 * <pre>typedef struct _cef_resource_read_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_resource_read_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__resource__handler_8h.html">cef_resource_handler.h:64</a>
 */
public interface CefResourceReadCallback extends CefLibraryObject {

    /**
     * Call to continue the download. Set {@code download_path} to the full file path for the download including the
     * file name or leave blank to use the suggested name and the default temp directory. Set {@code show_dialog} to
     * {@code true} if you do wish to show the default "Save As" dialog.
     *
     * <p>Definition generated from cef_resource_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_resource_read_callback_t* self, int bytes_read);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:51</a>
     */
    void cont(int bytesRead);

    final class NativePeer implements CefResourceReadCallback, AutoCloseable {
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

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefResourceReadCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefResourceReadCallback 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public void cont(int bytesRead) {
            N_Cont(nativePtr, bytesRead);
        }

        private static native void N_Cont(long self, int bytesRead);

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
