// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import javax.annotation.Nonnull;

/**
 * Callback interface used for asynchronous continuation of permission prompts.
 * <p>Definition generated from cef_permission_handler_capi.h
 * <pre>typedef struct _cef_permission_prompt_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_permission_prompt_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:69</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
@SuppressWarnings({"SameReturnValue", "EmptyMethod", "UnusedReturnValue", "unused"})
public interface CefPermissionPromptCallback extends CefLibraryObject {

    /**
     * Complete the permissions request with the specified {@code result}.
     * <p>Definition generated from cef_permission_handler_capi.h
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_permission_prompt_callback_t* self, cef_permission_request_result_t result);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:75</a>
     */
    void cont(@Nonnull CefPermissionRequestResult result);
    final class NativePeer implements CefPermissionPromptCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefPermissionPromptCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefPermissionPromptCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefPermissionPromptCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
      public void cont(@Nonnull CefPermissionRequestResult result) {
          checkNotClosed();
          cont0(nativePtr, result);
      }


        static native void cont0(long self, @Nonnull CefPermissionRequestResult result);


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
            return "CefPermissionPromptCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }

}
