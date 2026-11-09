// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class representing the SSL information for a navigation entry.
 *
 * <p>Definition generated from cef_ssl_status_capi.h
 *
 * <pre>typedef struct _cef_sslstatus_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_sslstatus_t;</pre>
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__status_8h.html">cef_ssl_status.h:45</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefSslStatus extends CefLibraryObject {

    /**
     * Returns {@code true} if the status is related to a secure SSL/TLS connection.
     *
     * <p>Definition generated from cef_ssl_status_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_secure_connection)(struct _cef_sslstatus_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__status_8h.html">cef_ssl_status.h:51</a>
     */
    boolean isSecureConnection();

    /**
     * Returns a bitmask containing any and all problems verifying the server certificate.
     *
     * <p>Definition generated from cef_ssl_status_capi.h
     *
     * <pre>cef_cert_status_t (CEF_CALLBACK* get_cert_status)(struct _cef_sslstatus_t* self);</pre>
     *
     * @return the result, or {@code CERT_STATUS_NONE} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__status_8h.html">cef_ssl_status.h:57</a>
     */
    CefCertStatus getCertStatus();

    /**
     * Returns the SSL version used for the SSL connection.
     *
     * <p>Definition generated from cef_ssl_status_capi.h
     *
     * <pre>cef_ssl_version_t (CEF_CALLBACK* get_sslversion)(struct _cef_sslstatus_t* self);</pre>
     *
     * @return the result, or {@code SSL_CONNECTION_VERSION_UNKNOWN} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__status_8h.html">cef_ssl_status.h:64</a>
     */
    CefSslVersion getSslVersion();

    /**
     * Returns a bitmask containing the page security content status.
     *
     * <p>Definition generated from cef_ssl_status_capi.h
     *
     * <pre>cef_ssl_content_status_t (CEF_CALLBACK* get_content_status)(struct _cef_sslstatus_t* self);</pre>
     *
     * @return the result, or {@code SSL_CONTENT_NORMAL_CONTENT} for default handling
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__status_8h.html">cef_ssl_status.h:70</a>
     */
    CefSslContentStatus getContentStatus();

    /**
     * Returns the X.509 certificate.
     *
     * <p>Definition generated from cef_ssl_status_capi.h
     *
     * <pre>cef_x509_certificate_t* (CEF_CALLBACK* get_x509_certificate)(struct _cef_sslstatus_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__ssl__status_8h.html">cef_ssl_status.h:76</a>
     */
    Optional<CefX509Certificate> getX509Certificate();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefSslStatus {
        NativePeer(long ptr) {
            super("CefSslStatus", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public boolean isSecureConnection() {
            checkNotClosed();
            return isSecureConnection0(nativePtr);
        }

        @Override
        public CefCertStatus getCertStatus() {
            checkNotClosed();
            return getCertStatus0(nativePtr);
        }

        @Override
        public CefSslVersion getSslVersion() {
            checkNotClosed();
            return getSslVersion0(nativePtr);
        }

        @Override
        public CefSslContentStatus getContentStatus() {
            checkNotClosed();
            return getContentStatus0(nativePtr);
        }

        @Override
        public Optional<CefX509Certificate> getX509Certificate() {
            checkNotClosed();
            return Optional.ofNullable(getX509Certificate0(nativePtr));
        }

        static native boolean isSecureConnection0(long self);

        static native CefCertStatus getCertStatus0(long self);

        static native CefSslVersion getSslVersion0(long self);

        static native CefSslContentStatus getContentStatus0(long self);

        static native CefX509Certificate getX509Certificate0(long self);
    }
}
