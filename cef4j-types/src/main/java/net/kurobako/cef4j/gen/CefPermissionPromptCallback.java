// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Callback interface used for asynchronous continuation of permission prompts.
 *
 * <p>Definition generated from cef_permission_handler_capi.h
 *
 * <pre>typedef struct _cef_permission_prompt_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_permission_prompt_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__permission__handler_8h.html">cef_permission_handler.h:69</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefPermissionPromptCallback extends CefLibraryObject {

    /**
     * Complete the permissions request with the specified {@code result}.
     *
     * <p>Definition generated from cef_permission_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* cont)(struct _cef_permission_prompt_callback_t* self, cef_permission_request_result_t result);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__permission__handler_8h.html">cef_permission_handler.h:75</a>
     */
    void cont(CefPermissionRequestResult result);

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefPermissionPromptCallback {
        NativePeer(long ptr) {
            super("CefPermissionPromptCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void cont(CefPermissionRequestResult result) {
            checkNotClosed();
            cont0(nativePtr, result);
        }

        static native void cont0(long self, CefPermissionRequestResult result);
    }
}
