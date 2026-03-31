// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Class representing SSL information. */
public interface CefSslinfo {

    /**
     * Returns a bitmask containing any and all problems verifying the server certificate.
     *
     * @return the result, or {@code CERT_STATUS_NONE} for default handling
     */
    CefCertStatus getCertStatus();

    /** Returns the X.509 certificate. */
    long getX509Certificate();

    static class NativePeer implements CefSslinfo {
        private volatile long nativePtr;

        @Override
        public CefCertStatus getCertStatus() {
            return N_GetCertStatus(nativePtr);
        }

        @Override
        public long getX509Certificate() {
            return N_GetX509Certificate(nativePtr);
        }

        private native CefCertStatus N_GetCertStatus(long self);

        private native long N_GetX509Certificate(long self);

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
            return "CefSslinfo{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
