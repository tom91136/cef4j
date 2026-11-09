// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

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
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__permission__handler_8h.html">cef_permission_handler.h:44</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__permission__handler_8h.html">cef_permission_handler.h:51</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__permission__handler_8h.html">cef_permission_handler.h:62</a>
     */
    void cancel();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefMediaAccessCallback {
        NativePeer(long ptr) {
            super("CefMediaAccessCallback", ptr, NativePeer::release0);
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

        static native void cont0(long self, int allowedPermissions);

        static native void cancel0(long self);
    }
}
