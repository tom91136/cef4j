// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;

/**
 * Callback interface used for asynchronous continuation of media access permission requests.
 *
 * <p>Definition generated from cef_permission_handler_capi.h
 *
 * <pre>typedef struct _cef_media_access_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_media_access_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:44</a>
 */
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
public interface CefMediaAccessCallback extends CefLibraryObject {

    /**
     * Call to allow or deny media access. If this callback was initiated in response to a getUserMedia (indicated by
     * {@link net.kurobako.cef4j.gen.CefMediaAccessPermissionTypes.Kind#DEVICE_AUDIO_CAPTURE} and/or
     * {@link net.kurobako.cef4j.gen.CefMediaAccessPermissionTypes.Kind#DEVICE_VIDEO_CAPTURE} being set) then
     * {@code allowed_permissions} must match {@code required_permissions} passed to OnRequestMediaAccessPermission.
     *
     * <p>Definition generated from cef_permission_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cont)(struct _cef_media_access_callback_t* self, uint32_t allowed_permissions);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:51</a>
     */
    void cont(int allowedPermissions);

    /**
     * Cancel the media access request.
     *
     * <p>Definition generated from cef_permission_handler_capi.h
     *
     * <pre>void (CEF_CALLBACK* cancel)(struct _cef_media_access_callback_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__permission__handler_8h.html">cef_permission_handler.h:62</a>
     */
    void cancel();

    final class NativePeer implements CefMediaAccessCallback, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefMediaAccessCallback has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefMediaAccessCallback.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefMediaAccessCallback 0x{}", Long.toHexString(ptr));
                release0(ptr);
            }
        }

        private static native void release0(long ptr);

        @Override
        public void cont(int allowedPermissions) {
            checkNotClosed();
            cont0(nativePtr, allowedPermissions);
        }

        @Override
        public void cancel() {
            checkNotClosed();
            cancel0(nativePtr);
        }

        private static native void cont0(long self, int allowedPermissions);

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
            return "CefMediaAccessCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
