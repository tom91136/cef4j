// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
// -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.processing.Generated;

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
@Generated("mvn generate-sources -pl cef4j-native -Dcef.version=146.0.9+g3ca6a87+chromium-146.0.7680.165")
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__ssl__status_8h.html">cef_ssl_status.h:57</a>
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
     * @see <a href="https://cef-builds.spotifycdn.com/docs/146.0/cef__ssl__status_8h.html">cef_ssl_status.h:76</a>
     */
    Optional<CefX509Certificate> getX509certificate();

    final class NativePeer implements CefSslStatus, AutoCloseable {
        private final long nativePtr;
        private final java.lang.ref.Cleaner.Cleanable cleanable;
        private volatile boolean closed;

        NativePeer(long ptr) {
            this.nativePtr = ptr;
            this.cleanable = net.kurobako.cef4j.NativeCleaner.INSTANCE.register(this, new Release(ptr));
        }

        @Override
        public void close() {
            closed = true;
            cleanable.clean();
        }

        @Override
        public boolean isClosed() {
            return closed;
        }

        private void checkNotClosed() {
            if (closed) throw new IllegalStateException("CefSslStatus has been closed");
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
                release0(ptr);
            }
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
        public Optional<CefX509Certificate> getX509certificate() {
            checkNotClosed();
            return Optional.ofNullable(getX509certificate0(nativePtr));
        }

        private static native boolean isSecureConnection0(long self);

        private static native CefCertStatus getCertStatus0(long self);

        private static native CefSslVersion getSslVersion0(long self);

        private static native CefSslContentStatus getContentStatus0(long self);

        private static native CefX509Certificate getX509certificate0(long self);

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
