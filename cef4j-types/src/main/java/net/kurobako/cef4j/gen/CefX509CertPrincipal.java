// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Optional;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Class representing the issuer or subject field of an X.509 certificate.
 *
 * <p>Definition generated from cef_x509_certificate_capi.h
 *
 * <pre>typedef struct _cef_x509_cert_principal_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_x509_cert_principal_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:46</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public interface CefX509CertPrincipal extends CefLibraryObject {

    /**
     * Returns a name that can be used to represent the issuer. It tries in this order: Common Name (CN), Organization
     * Name (O) and Organizational Unit Name (OU) and returns the first non-empty one found.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_display_name)(struct _cef_x509_cert_principal_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:52</a>
     */
    Optional<String> getDisplayName();

    /**
     * Returns the common name.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_common_name)(struct _cef_x509_cert_principal_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:60</a>
     */
    Optional<String> getCommonName();

    /**
     * Returns the locality name.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_locality_name)(struct _cef_x509_cert_principal_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:66</a>
     */
    Optional<String> getLocalityName();

    /**
     * Returns the state or province name.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_state_or_province_name)(struct _cef_x509_cert_principal_t* self);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:72</a>
     */
    Optional<String> getStateOrProvinceName();

    /**
     * Returns the country name.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>cef_string_userfree_t (CEF_CALLBACK* get_country_name)(struct _cef_x509_cert_principal_t* self);</pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:78</a>
     */
    Optional<String> getCountryName();

    /**
     * Retrieve the list of organization names.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* get_organization_names)(struct _cef_x509_cert_principal_t* self, cef_string_list_t names);
     * </pre>
     *
     * @return the list, populated by the C API
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:84</a>
     */
    List<String> getOrganizationNames();

    /**
     * Retrieve the list of organization unit names.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* get_organization_unit_names)(struct _cef_x509_cert_principal_t* self, cef_string_list_t names);
     * </pre>
     *
     * @return the list, populated by the C API
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/150.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:90</a>
     */
    List<String> getOrganizationUnitNames();

    final class NativePeer extends net.kurobako.cef4j.peer.NativeObject implements CefX509CertPrincipal {
        NativePeer(long ptr) {
            super("CefX509CertPrincipal", ptr, NativePeer::release0);
        }

        private static native void release0(long ptr);

        @Override
        public Optional<String> getDisplayName() {
            checkNotClosed();
            return Optional.ofNullable(getDisplayName0(nativePtr));
        }

        @Override
        public Optional<String> getCommonName() {
            checkNotClosed();
            return Optional.ofNullable(getCommonName0(nativePtr));
        }

        @Override
        public Optional<String> getLocalityName() {
            checkNotClosed();
            return Optional.ofNullable(getLocalityName0(nativePtr));
        }

        @Override
        public Optional<String> getStateOrProvinceName() {
            checkNotClosed();
            return Optional.ofNullable(getStateOrProvinceName0(nativePtr));
        }

        @Override
        public Optional<String> getCountryName() {
            checkNotClosed();
            return Optional.ofNullable(getCountryName0(nativePtr));
        }

        @Override
        public List<String> getOrganizationNames() {
            checkNotClosed();
            return getOrganizationNames0(nativePtr);
        }

        @Override
        public List<String> getOrganizationUnitNames() {
            checkNotClosed();
            return getOrganizationUnitNames0(nativePtr);
        }

        static native String getDisplayName0(long self);

        static native String getCommonName0(long self);

        static native String getLocalityName0(long self);

        static native String getStateOrProvinceName0(long self);

        static native String getCountryName0(long self);

        static native List<String> getOrganizationNames0(long self);

        static native List<String> getOrganizationUnitNames0(long self);
    }
}
