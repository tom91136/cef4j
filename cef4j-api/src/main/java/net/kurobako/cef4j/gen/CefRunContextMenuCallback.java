// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;

/**
 * Callback interface used for continuation of custom context menu display.
 *
 * <p>Definition generated from cef_context_menu_handler_capi.h
 *
 * <pre>typedef struct _cef_run_context_menu_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_run_context_menu_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:48</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefRunContextMenuCallback extends CefLibraryObject {

    /**
     * Call to continue the download. Set {@code download_path} to the full file path for the download including the
     * file name or leave blank to use the suggested name and the default temp directory. Set {@code show_dialog} to
     * {@code true} if you do wish to show the default "Save As" dialog.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* cont)(struct _cef_run_context_menu_callback_t* self, int command_id, cef_event_flags_t event_flags);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__download__handler_8h.html">cef_download_handler.h:51</a>
     */
    void cont(int commandId, @Nonnull CefEventFlags eventFlags);

    /**
     * Cancel context menu display.
     *
     * <p>Definition generated from cef_context_menu_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_run_context_menu_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:61</a>
     */
    void cancel();

    final class NativePeer implements CefRunContextMenuCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefRunContextMenuCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefRunContextMenuCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefRunContextMenuCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void cont(int commandId, @Nonnull CefEventFlags eventFlags) {
            checkNotClosed();
            cont0(nativePtr, commandId, eventFlags);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            cancel0(nativePtr);
        }

        private static native void cont0(long self, int commandId, CefEventFlags eventFlags);

        private static native void cancel0(long self);

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
            return "CefRunContextMenuCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
