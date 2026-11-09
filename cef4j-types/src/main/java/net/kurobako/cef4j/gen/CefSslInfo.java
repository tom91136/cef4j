// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class representing SSL information.
 *
 * <p>Definition generated from cef_ssl_info_capi.h
 *
 * <pre>typedef struct _cef_sslinfo_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_sslinfo_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__info_8h.html">cef_ssl_info.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefSslInfo extends CefLibraryObject {

    /**
     * Returns a bitmask containing any and all problems verifying the server certificate.
     *
     * <p>Definition generated from cef_ssl_info_capi.h
     *
     * <pre>cef_cert_status_t (CEF_CALLBACK* get_cert_status)(struct _cef_sslinfo_t* self);</pre>
     *
     * @return the result, or {@code CERT_STATUS_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__info_8h.html">cef_ssl_info.h:51</a>
     */
    CefCertStatus getCertStatus();

    /**
     * Returns the X.509 certificate.
     *
     * <p>Definition generated from cef_ssl_info_capi.h
     *
     * <pre>cef_x509_certificate_t* (CEF_CALLBACK* get_x509_certificate)(struct _cef_sslinfo_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__info_8h.html">cef_ssl_info.h:58</a>
     */
    Optional<CefX509Certificate> getX509Certificate();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefSslInfo {
        NativePeer(long ptr) {
            super("CefSslInfo", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public CefCertStatus getCertStatus() {
            checkNotClosed();
            return getCertStatus0(nativePtr);
        }

        @Override
        public Optional<CefX509Certificate> getX509Certificate() {
            checkNotClosed();
            return Optional.ofNullable(getX509Certificate0(nativePtr));
        }

        static native CefCertStatus getCertStatus0(long self);

        static native CefX509Certificate getX509Certificate0(long self);
    }
}
