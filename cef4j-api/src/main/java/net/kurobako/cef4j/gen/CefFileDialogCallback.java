// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import java.util.List;
import javax.annotation.Nullable;

/**
 * Callback interface for asynchronous continuation of file dialog requests.
 * <p>Definition generated from cef_dialog_handler_capi.h
 * <pre>typedef struct _cef_file_dialog_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_file_dialog_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dialog__handler_8h.html">cef_dialog_handler.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefFileDialogCallback extends CefLibraryObject {

    /**
     * Continue the file selection. {@code file_paths} should be a single value or a list of values depending on the dialog mode. An empty {@code file_paths} value is treated the same as calling Cancel().
     * <p>Definition generated from cef_dialog_handler_capi.h
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_file_dialog_callback_t* self, cef_string_list_t file_paths);</pre>
     *
     * @param filePaths may be null
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dialog__handler_8h.html">cef_dialog_handler.h:50</a>
     */
    void cont(@Nullable List<String> filePaths);

    /**
     * Cancel the file selection.
     * <p>Definition generated from cef_dialog_handler_capi.h
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_file_dialog_callback_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__dialog__handler_8h.html">cef_dialog_handler.h:58</a>
     */
    void cancel();
    final class NativePeer implements CefFileDialogCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefFileDialogCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefFileDialogCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefFileDialogCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
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


        static native void cont0(long self, List<String> filePaths);

        static native void cancel0(long self);


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
            return "CefFileDialogCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
