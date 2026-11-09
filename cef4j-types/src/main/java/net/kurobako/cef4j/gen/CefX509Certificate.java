// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class representing a X.509 certificate.
 *
 * <p>Definition generated from cef_x509_certificate_capi.h
 *
 * <pre>typedef struct _cef_x509_certificate_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_x509_certificate_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:97</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:105</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:113</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:119</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:126</a>
     */
    CefBaseTime getValidStart();

    /**
     * Returns the date after which the X.509 certificate is invalid. CefBaseTime.GetTimeT() will return 0 if no date
     * was specified.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_basetime_t* (CEF_CALLBACK* get_valid_expiry)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:133</a>
     */
    CefBaseTime getValidExpiry();

    /**
     * Returns the DER encoded data for the X.509 certificate.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_binary_value_t* (CEF_CALLBACK* get_derencoded)(struct _cef_x509_certificate_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:140</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:146</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:152</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:159</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:167</a>
     */
    List<CefBinaryValue> getPemEncodedIssuerChain();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefX509Certificate {
        NativePeer(long ptr) {
            super("CefX509Certificate", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public Optional<CefX509CertPrincipal> getSubject() {
            checkNotClosed();
            return Optional.ofNullable(getSubject0(nativePtr));
        }

        @Override
        public Optional<CefX509CertPrincipal> getIssuer() {
            checkNotClosed();
            return Optional.ofNullable(getIssuer0(nativePtr));
        }

        @Override
        public Optional<CefBinaryValue> getSerialNumber() {
            checkNotClosed();
            return Optional.ofNullable(getSerialNumber0(nativePtr));
        }

        @Override
        public CefBaseTime getValidStart() {
            checkNotClosed();
            return getValidStart0(nativePtr);
        }

        @Override
        public CefBaseTime getValidExpiry() {
            checkNotClosed();
            return getValidExpiry0(nativePtr);
        }

        @Override
        public Optional<CefBinaryValue> getDerEncoded() {
            checkNotClosed();
            return Optional.ofNullable(getDerEncoded0(nativePtr));
        }

        @Override
        public Optional<CefBinaryValue> getPemEncoded() {
            checkNotClosed();
            return Optional.ofNullable(getPemEncoded0(nativePtr));
        }

        @Override
        public long getIssuerChainSize() {
            checkNotClosed();
            return getIssuerChainSize0(nativePtr);
        }

        @Override
        public List<CefBinaryValue> getDerEncodedIssuerChain() {
            checkNotClosed();
            return Arrays.asList(getDerEncodedIssuerChain0(nativePtr));
        }

        @Override
        public List<CefBinaryValue> getPemEncodedIssuerChain() {
            checkNotClosed();
            return Arrays.asList(getPemEncodedIssuerChain0(nativePtr));
        }

        static native CefX509CertPrincipal getSubject0(long self);

        static native CefX509CertPrincipal getIssuer0(long self);

        static native CefBinaryValue getSerialNumber0(long self);

        static native CefBaseTime getValidStart0(long self);

        static native CefBaseTime getValidExpiry0(long self);

        static native CefBinaryValue getDerEncoded0(long self);

        static native CefBinaryValue getPemEncoded0(long self);

        static native long getIssuerChainSize0(long self);

        static native CefBinaryValue[] getDerEncodedIssuerChain0(long self);

        static native CefBinaryValue[] getPemEncodedIssuerChain0(long self);
    }
}
