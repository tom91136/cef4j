// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:61</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefDownloadItemCallback extends CefLibraryObject {

    /**
     * Call to cancel the download.
     *
     * <p>Definition generated from cef_download_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_download_item_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:67</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:73</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:79</a>
     */
    void resume();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefDownloadItemCallback {
        NativePeer(long ptr) {
            super("CefDownloadItemCallback", ptr, NativePeer::release0);
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

        static native void cancel0(long self);

        static native void pause0(long self);

        static native void resume0(long self);
    }
}
