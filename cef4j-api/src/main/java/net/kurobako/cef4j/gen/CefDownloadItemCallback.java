// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Callback interface used to asynchronously cancel a download.
 *
 * <p>Definition generated from cef_download_handler_capi.h
 *
 * <pre>typedef struct _cef_download_item_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_download_item_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:61</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefDownloadItemCallback extends CefLibraryObject {

    /**
     * Call to cancel the download.
     *
     * <p>Definition generated from cef_download_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_download_item_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:67</a>
     */
    void cancel();

    /**
     * Call to pause the download.
     *
     * <p>Definition generated from cef_download_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* pause)(struct _cef_download_item_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:73</a>
     */
    void pause();

    /**
     * Call to resume the download.
     *
     * <p>Definition generated from cef_download_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* resume)(struct _cef_download_item_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:79</a>
     */
    void resume();

    final class NativePeer implements CefDownloadItemCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefDownloadItemCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefDownloadItemCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefDownloadItemCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void cancel() {
            checkNotClosed();
            cancel0(nativePtr);
        }

        @Override
        public void pause() {
            checkNotClosed();
            pause0(nativePtr);
        }

        @Override
        public void resume() {
            checkNotClosed();
            resume0(nativePtr);
        }

        private static native void cancel0(long self);

        private static native void pause0(long self);

        private static native void resume0(long self);

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
            return "CefDownloadItemCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
