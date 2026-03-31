// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;

/**
 * Structure representing the issuer or subject field of an X.509 certificate. NOTE: This struct is allocated DLL-side.
 */
public interface CefX509CertPrincipal {

    /**
     * Returns a name that can be used to represent the issuer. It tries in this order: Common Name (CN), Organization
     * Name (O) and Organizational Unit Name (OU) and returns the first non-empty one found.
     */
    Optional<String> getDisplayName();

    /** Returns the common name. */
    Optional<String> getCommonName();

    /** Returns the locality name. */
    Optional<String> getLocalityName();

    /** Returns the state or province name. */
    Optional<String> getStateOrProvinceName();

    /** Returns the country name. */
    Optional<String> getCountryName();

    /** Retrieve the list of organization names. */
    void getOrganizationNames(@Nonnull java.util.List<String> names);

    /** Retrieve the list of organization unit names. */
    void getOrganizationUnitNames(@Nonnull java.util.List<String> names);

    static class NativePeer implements CefX509CertPrincipal {
        private volatile long nativePtr;

        @Override
        public Optional<String> getDisplayName() {
            return Optional.ofNullable(N_GetDisplayName(nativePtr));
        }

        @Override
        public Optional<String> getCommonName() {
            return Optional.ofNullable(N_GetCommonName(nativePtr));
        }

        @Override
        public Optional<String> getLocalityName() {
            return Optional.ofNullable(N_GetLocalityName(nativePtr));
        }

        @Override
        public Optional<String> getStateOrProvinceName() {
            return Optional.ofNullable(N_GetStateOrProvinceName(nativePtr));
        }

        @Override
        public Optional<String> getCountryName() {
            return Optional.ofNullable(N_GetCountryName(nativePtr));
        }

        @Override
        public void getOrganizationNames(java.util.List<String> names) {
            N_GetOrganizationNames(nativePtr, names);
        }

        @Override
        public void getOrganizationUnitNames(java.util.List<String> names) {
            N_GetOrganizationUnitNames(nativePtr, names);
        }

        private native String N_GetDisplayName(long self);

        private native String N_GetCommonName(long self);

        private native String N_GetLocalityName(long self);

        private native String N_GetStateOrProvinceName(long self);

        private native String N_GetCountryName(long self);

        private native void N_GetOrganizationNames(long self, java.util.List<String> names);

        private native void N_GetOrganizationUnitNames(long self, java.util.List<String> names);

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
