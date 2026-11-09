// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Callback interface used to select a client certificate for authentication.
 *
 * <p>Definition generated from cef_request_handler_capi.h
 *
 * <pre>typedef struct _cef_select_client_certificate_callback_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_select_client_certificate_callback_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request__handler_8h.html">cef_request_handler.h:54</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefSelectClientCertificateCallback extends CefLibraryObject {

    /**
     * Chooses the specified certificate for client certificate authentication. {@code null} value means that no client
     * certificate should be used.
     *
     * <p>Definition generated from cef_request_handler_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* select)(struct _cef_select_client_certificate_callback_t* self, struct _cef_x509_certificate_t* cert);
     * </pre>
     *
     * @param cert may be null
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__request__handler_8h.html">cef_request_handler.h:60</a>
     */
    void select(@Nullable CefX509Certificate cert);

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefSelectClientCertificateCallback {
        NativePeer(long ptr) {
            super("CefSelectClientCertificateCallback", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public void select(@Nullable CefX509Certificate cert) {
            checkNotClosed();
            CefLibraryObject.requireOpen(cert, "CefX509Certificate");
            select0(nativePtr, cert);
        }

        static native void select0(long self, @Nullable CefX509Certificate cert);
    }
}
