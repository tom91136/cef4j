// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.List;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Callback interface for asynchronous continuation of file dialog requests.
 *
 * <p>Definition generated from cef_dialog_handler_capi.h
 *
 * <pre>typedef struct _cef_file_dialog_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_file_dialog_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dialog__handler_8h.html">cef_dialog_handler.h:44</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefFileDialogCallback extends CefLibraryObject {

    /**
     * Continue the file selection. {@code file_paths} should be a single value or a list of values depending on the
     * dialog mode. An empty {@code file_paths} value is treated the same as calling Cancel().
     *
     * <p>Definition generated from cef_dialog_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_file_dialog_callback_t* self, cef_string_list_t file_paths);</pre>
     *
     * @param filePaths may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dialog__handler_8h.html">cef_dialog_handler.h:50</a>
     */
    void cont(@Nullable List<String> filePaths);

    /**
     * Cancel the file selection.
     *
     * <p>Definition generated from cef_dialog_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_file_dialog_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__dialog__handler_8h.html">cef_dialog_handler.h:58</a>
     */
    void cancel();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefFileDialogCallback {
        NativePeer(long ptr) {
            super("CefFileDialogCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont(@Nullable List<String> filePaths) {
            checkNotClosed();
            cont0(nativePtr, filePaths);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            cancel0(nativePtr);
        }

        static native void cont0(long self, @Nullable List<String> filePaths);

        static native void cancel0(long self);
    }
}
