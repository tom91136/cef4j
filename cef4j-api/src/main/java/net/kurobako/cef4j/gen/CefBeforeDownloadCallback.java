// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nullable;

/**
 * Callback interface used to asynchronously continue a download.
 * <p>Definition generated from cef_download_handler_capi.h
 * <pre>typedef struct _cef_before_download_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_before_download_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:45</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefBeforeDownloadCallback extends CefLibraryObject {

    /**
     * Call to continue the download. Set {@code download_path} to the full file path for the download including the file name or leave blank to use the suggested name and the default temp directory. Set {@code show_dialog} to {@code true} if you do wish to show the default "Save As" dialog.
     * <p>Definition generated from cef_download_handler_capi.h
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_before_download_callback_t* self, const cef_string_t* download_path, int show_dialog);</pre>
     *
     * @param downloadPath may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:51</a>
     */
    void cont(@Nullable String downloadPath, int showDialog);
    final class NativePeer implements CefBeforeDownloadCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefBeforeDownloadCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefBeforeDownloadCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefBeforeDownloadCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public void cont(@Nullable String downloadPath, int showDialog) {
          checkNotClosed();
          cont0(nativePtr, downloadPath, showDialog);
      }


        static native void cont0(long self, String downloadPath, int showDialog);


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
            return "CefBeforeDownloadCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
