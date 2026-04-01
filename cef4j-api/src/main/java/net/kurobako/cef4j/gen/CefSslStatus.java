// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;

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
 * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__ssl__status_8h.html">cef_ssl_status.h:45</a>
 */
public interface CefSslStatus extends CefLibraryObject {

    /**
     * Returns {@code true} if the status is related to a secure SSL/TLS connection.
     *
     * <p>Definition generated from cef_ssl_status_capi.h
     *
     * <pre>int (CEF_CALLBACK* is_secure_connection)(struct _cef_sslstatus_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__ssl__status_8h.html">cef_ssl_status.h:51</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__ssl__info_8h.html">cef_ssl_info.h:51</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__ssl__status_8h.html">cef_ssl_status.h:64</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__ssl__status_8h.html">cef_ssl_status.h:70</a>
     */
    CefSslContentStatus getContentStatus();

    /**
     * Returns the X.509 certificate.
     *
     * <p>Definition generated from cef_ssl_status_capi.h
     *
     * <pre>cef_x509_certificate_t* (CEF_CALLBACK* get_x509_certificate)(struct _cef_sslstatus_t* self);</pre>
     *
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__ssl__info_8h.html">cef_ssl_info.h:58</a>
     */
    Optional<CefX509Certificate> getX509certificate();

    final class NativePeer implements CefSslStatus, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            cleanable.clean();
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefSslStatus.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefSslStatus 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public boolean isSecureConnection() {
            return N_IsSecureConnection(nativePtr);
        }

        @Override
        public CefCertStatus getCertStatus() {
            return N_GetCertStatus(nativePtr);
        }

        @Override
        public CefSslVersion getSslVersion() {
            return N_GetSslVersion(nativePtr);
        }

        @Override
        public CefSslContentStatus getContentStatus() {
            return N_GetContentStatus(nativePtr);
        }

        @Override
        public Optional<CefX509Certificate> getX509certificate() {
            return Optional.ofNullable(N_GetX509certificate(nativePtr));
        }

        private static native boolean N_IsSecureConnection(long self);

        private static native CefCertStatus N_GetCertStatus(long self);

        private static native CefSslVersion N_GetSslVersion(long self);

        private static native CefSslContentStatus N_GetContentStatus(long self);

        private static native CefX509Certificate N_GetX509certificate(long self);

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
            return "CefSslStatus{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
