// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Structure representing the issuer or subject field of an X.509 certificate. NOTE: This struct is allocated DLL-side.
 *
 * <p>Definition generated from cef_x509_certificate_capi.h
 *
 * <pre>typedef struct _cef_x509_cert_principal_t {
 *   cef_base_ref_counted_t base;
 *   ...
 * } cef_x509_cert_principal_t;</pre>
 *
 * @see <a
 *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate__capi_8h.html">cef_x509_certificate_capi.h:54</a>
 */
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
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:52</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:60</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:66</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:72</a>
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
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:78</a>
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
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:84</a>
     */
    void getOrganizationNames(@Nonnull List<String> names);

    /**
     * Retrieve the list of organization unit names.
     *
     * <p>Definition generated from cef_x509_certificate_capi.h
     *
     * <pre>
     * void (CEF_CALLBACK* get_organization_unit_names)(struct _cef_x509_cert_principal_t* self, cef_string_list_t names);
     * </pre>
     *
     * @see <a
     *     href="https://cef-builds.spotifycdn.com/docs/146.0/cef__x509__certificate_8h.html">cef_x509_certificate.h:90</a>
     */
    void getOrganizationUnitNames(@Nonnull List<String> names);

    final class NativePeer implements CefX509CertPrincipal, AutoCloseable {
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
            if (closed) throw new IllegalStateException("CefX509CertPrincipal has been closed");
        }

        private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory.getLogger(CefX509CertPrincipal.class);

        private static class Release implements Runnable {
            private final long ptr;

            Release(long ptr) {
                this.ptr = ptr;
            }

            @Override
            public void run() {
                if (_log.isTraceEnabled()) _log.trace("release CefX509CertPrincipal 0x{}", Long.toHexString(ptr));
                N_Release(ptr);
            }
        }

        private static native void N_Release(long ptr);

        @Override
        public Optional<String> getDisplayName() {
            checkNotClosed();
            return Optional.ofNullable(N_GetDisplayName(nativePtr));
        }

        @Override
        public Optional<String> getCommonName() {
            checkNotClosed();
            return Optional.ofNullable(N_GetCommonName(nativePtr));
        }

        @Override
        public Optional<String> getLocalityName() {
            checkNotClosed();
            return Optional.ofNullable(N_GetLocalityName(nativePtr));
        }

        @Override
        public Optional<String> getStateOrProvinceName() {
            checkNotClosed();
            return Optional.ofNullable(N_GetStateOrProvinceName(nativePtr));
        }

        @Override
        public Optional<String> getCountryName() {
            checkNotClosed();
            return Optional.ofNullable(N_GetCountryName(nativePtr));
        }

        @Override
        public void getOrganizationNames(@Nonnull List<String> names) {
            checkNotClosed();
            N_GetOrganizationNames(nativePtr, names);
        }

        @Override
        public void getOrganizationUnitNames(@Nonnull List<String> names) {
            checkNotClosed();
            N_GetOrganizationUnitNames(nativePtr, names);
        }

        private static native String N_GetDisplayName(long self);

        private static native String N_GetCommonName(long self);

        private static native String N_GetLocalityName(long self);

        private static native String N_GetStateOrProvinceName(long self);

        private static native String N_GetCountryName(long self);

        private static native void N_GetOrganizationNames(long self, List<String> names);

        private static native void N_GetOrganizationUnitNames(long self, List<String> names);

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
            return "CefX509CertPrincipal{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
