// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

/**
 * Callback interface used for asynchronous continuation of authentication requests.
 *
 * <p>Definition generated from cef_auth_callback_capi.h
 *
 * <pre>typedef struct _cef_auth_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_auth_callback_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__auth__callback_8h.html">cef_auth_callback.h:43</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefAuthCallback extends CefLibraryObject {

    /**
     * Continue the authentication request.
     *
     * <p>Definition generated from cef_auth_callback_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* cont)(struct _cef_auth_callback_t* self, const cef_string_t* username, const cef_string_t* password);
     * </pre>
     *
     * @param username may be null
     * @param password may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__auth__callback_8h.html">cef_auth_callback.h:50</a>
     */
    void cont(@Nullable String username, @Nullable String password);

    /**
     * Cancel the authentication request.
     *
     * <p>Definition generated from cef_auth_callback_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_auth_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__auth__callback_8h.html">cef_auth_callback.h:57</a>
     */
    void cancel();

    final class NativePeer implements CefAuthCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefAuthCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefAuthCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefAuthCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void cont(@Nullable String username, @Nullable String password) {
            checkNotClosed();
            cont0(nativePtr, username, password);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            cancel0(nativePtr);
        }

        private static native void cont0(long self, String username, String password);

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
            return "CefAuthCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
