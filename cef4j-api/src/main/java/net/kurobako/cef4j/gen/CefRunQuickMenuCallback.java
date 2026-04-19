// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;

/**
 * Callback interface used for continuation of custom quick menu display.
 * <p>Definition generated from cef_context_menu_handler_capi.h
 * <pre>typedef struct _cef_run_quick_menu_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_run_quick_menu_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:68</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefRunQuickMenuCallback extends CefLibraryObject {

    /**
     * Complete quick menu display by selecting the specified {@code command_id} and {@code event_flags}.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_run_quick_menu_callback_t* self, int command_id, cef_event_flags_t event_flags);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:74</a>
     */
    void cont(int commandId, @Nonnull CefEventFlags eventFlags);

    /**
     * Cancel quick menu display.
     * <p>Definition generated from cef_context_menu_handler_capi.h
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_run_quick_menu_callback_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__context__menu__handler_8h.html">cef_context_menu_handler.h:81</a>
     */
    void cancel();
    final class NativePeer implements CefRunQuickMenuCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefRunQuickMenuCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefRunQuickMenuCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefRunQuickMenuCallback 0x{}", Long.toHexString(ptr));
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


        static native void cont0(long self, int commandId, @Nonnull CefEventFlags eventFlags);

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
            return "CefRunQuickMenuCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
