// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__auth__callback_8h.html">cef_auth_callback.h:43</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__auth__callback_8h.html">cef_auth_callback.h:50</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__auth__callback_8h.html">cef_auth_callback.h:57</a>
     */
    void cancel();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefAuthCallback {
        NativePeer(long ptr) {
            super("CefAuthCallback", ptr, NativePeer::release0);
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

        static native void cont0(long self, @Nullable String username, @Nullable String password);

        static native void cancel0(long self);
    }
}
