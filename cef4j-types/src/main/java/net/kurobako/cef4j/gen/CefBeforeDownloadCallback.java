// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Callback interface used to asynchronously continue a download.
 *
 * <p>Definition generated from cef_download_handler_capi.h
 *
 * <pre>typedef struct _cef_before_download_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_before_download_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefBeforeDownloadCallback extends CefLibraryObject {

    /**
     * Call to continue the download. Set {@code download_path} to the full file path for the download including the
     * file name or leave blank to use the suggested name and the default temp directory. Set {@code show_dialog} to
     * {@code true} if you do wish to show the default "Save As" dialog.
     *
     * <p>Definition generated from cef_download_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* cont)(struct _cef_before_download_callback_t* self, const cef_string_t* download_path, int show_dialog);
     * </pre>
     *
     * @param downloadPath may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__download__handler_8h.html">cef_download_handler.h:51</a>
     */
    void cont(@Nullable String downloadPath, int showDialog);

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefBeforeDownloadCallback {
        NativePeer(long ptr) {
            super("CefBeforeDownloadCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont(@Nullable String downloadPath, int showDialog) {
            checkNotClosed();
            cont0(nativePtr, downloadPath, showDialog);
        }

        static native void cont0(long self, @Nullable String downloadPath, int showDialog);
    }
}
