// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Structure representing a X.509 certificate. NOTE: This struct is allocated DLL-side.
 *
 * <p>Definition generated from cef_x509_certificate_capi.h
 *
 * <pre>typedef struct _cef_x509_certificate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_x509_certificate_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate__capi_8h.html">cef_x509_certificate_capi.h:117</a>
 */
public interface CefX509Certificate extends CefLibraryObject {

    /**
     * Returns the subject of the X.509 certificate. For HTTPS server certificates this represents the web server. The
     * common name of the subject should match the host name of the web server.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_x509_cert_principal_t* (CEF_CALLBACK* get_subject)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:105</a>
     */
    Optional<CefX509CertPrincipal> getSubject();

    /**
     * Returns the issuer of the X.509 certificate.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_x509_cert_principal_t* (CEF_CALLBACK* get_issuer)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:113</a>
     */
    Optional<CefX509CertPrincipal> getIssuer();

    /**
     * Returns the DER encoded serial number for the X.509 certificate. The value possibly includes a leading 00 byte.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_serial_number)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:119</a>
     */
    Optional<CefBinaryValue> getSerialNumber();

    /**
     * Returns the date before which the X.509 certificate is invalid. CefBaseTime.GetTimeT() will return 0 if no date
     * was specified.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_valid_start)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:126</a>
     */
    CefBasetime getValidStart();

    /**
     * Returns the date after which the X.509 certificate is invalid. CefBaseTime.GetTimeT() will return 0 if no date
     * was specified.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_valid_expiry)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:133</a>
     */
    CefBasetime getValidExpiry();

    /**
     * Returns the DER encoded data for the X.509 certificate.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_derencoded)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:140</a>
     */
    Optional<CefBinaryValue> getDerEncoded();

    /**
     * Returns the PEM encoded data for the X.509 certificate.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_pemencoded)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:146</a>
     */
    Optional<CefBinaryValue> getPemEncoded();

    /**
     * Returns the number of certificates in the issuer chain. If 0, the certificate is self-signed.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>size_t (CEF_CALLBACK* get_issuer_chain_size)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:152</a>
     */
    long getIssuerChainSize();

    /**
     * Returns the DER encoded data for the certificate issuer chain. If we failed to encode a certificate in the chain
     * it is still present in the array but is an empty string.
     *
     * <p><b>The C API exposes this as a two-pass pattern: first call {@link #getIssuerChainSize()} to obtain the count,
     * then allocate and populate the array/collection. This method performs both steps and returns the result
     * directly.</b>
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_binary_value_t** (CEF_CALLBACK* get_derencoded_issuer_chain)(struct _cef_x509_certificate_t* self);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:159</a>
     */
    List<CefBinaryValue> getDerEncodedIssuerChain();

    /**
     * Returns the PEM encoded data for the certificate issuer chain. If we failed to encode a certificate in the chain
     * it is still present in the array but is an empty string.
     *
     * <p><b>The C API exposes this as a two-pass pattern: first call {@link #getIssuerChainSize()} to obtain the count,
     * then allocate and populate the array/collection. This method performs both steps and returns the result
     * directly.</b>
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_binary_value_t** (CEF_CALLBACK* get_pemencoded_issuer_chain)(struct _cef_x509_certificate_t* self);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:167</a>
     */
    List<CefBinaryValue> getPemEncodedIssuerChain();

    final class NativePeer implements CefX509Certificate, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefX509Certificate has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefX509Certificate.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefX509Certificate 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<CefX509CertPrincipal> getSubject() {
            checkNotClosed();
            return Optional.ofNullable(N_GetSubject(nativePtr));
        }

        @Override
        public Optional<CefX509CertPrincipal> getIssuer() {
            checkNotClosed();
            return Optional.ofNullable(N_GetIssuer(nativePtr));
        }

        @Override
        public Optional<CefBinaryValue> getSerialNumber() {
            checkNotClosed();
            return Optional.ofNullable(N_GetSerialNumber(nativePtr));
        }

        @Override
        public CefBasetime getValidStart() {
            checkNotClosed();
            return N_GetValidStart(nativePtr);
        }

        @Override
        public CefBasetime getValidExpiry() {
            checkNotClosed();
            return N_GetValidExpiry(nativePtr);
        }

        @Override
        public Optional<CefBinaryValue> getDerEncoded() {
            checkNotClosed();
            return Optional.ofNullable(N_GetDerEncoded(nativePtr));
        }

        @Override
        public Optional<CefBinaryValue> getPemEncoded() {
            checkNotClosed();
            return Optional.ofNullable(N_GetPemEncoded(nativePtr));
        }

        @Override
        public long getIssuerChainSize() {
            checkNotClosed();
            return N_GetIssuerChainSize(nativePtr);
        }

        @Override
        public List<CefBinaryValue> getDerEncodedIssuerChain() {
            checkNotClosed();
            return Arrays.asList(N_GetDerEncodedIssuerChain(nativePtr));
        }

        @Override
        public List<CefBinaryValue> getPemEncodedIssuerChain() {
            checkNotClosed();
            return Arrays.asList(N_GetPemEncodedIssuerChain(nativePtr));
        }

        private static native CefX509CertPrincipal N_GetSubject(long self);

        private static native CefX509CertPrincipal N_GetIssuer(long self);

        private static native CefBinaryValue N_GetSerialNumber(long self);

        private static native CefBasetime N_GetValidStart(long self);

        private static native CefBasetime N_GetValidExpiry(long self);

        private static native CefBinaryValue N_GetDerEncoded(long self);

        private static native CefBinaryValue N_GetPemEncoded(long self);

        private static native long N_GetIssuerChainSize(long self);

        private static native CefBinaryValue[] N_GetDerEncodedIssuerChain(long self);

        private static native CefBinaryValue[] N_GetPemEncodedIssuerChain(long self);

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
            return "CefX509Certificate{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
