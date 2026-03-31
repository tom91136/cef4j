// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Class representing the SSL information for a navigation entry. */
public interface CefSslstatus {

    /** Returns true if the status is related to a secure SSL/TLS connection. */
    boolean isSecureConnection();

    /**
     * Returns a bitmask containing any and all problems verifying the server certificate.
     *
     * @return the result, or {@code CERT_STATUS_NONE} for default handling
     */
    CefCertStatus getCertStatus();

    CefSslVersion getSslversion();

    /**
     * Returns a bitmask containing the page security content status.
     *
     * @return the result, or {@code SSL_CONTENT_NORMAL_CONTENT} for default handling
     */
    CefSslContentStatus getContentStatus();

    /** Returns the X.509 certificate. */
    long getX509Certificate();

    static class NativePeer implements CefSslstatus {
        private volatile long nativePtr;

        @Override
        public boolean isSecureConnection() {
            return N_IsSecureConnection(nativePtr);
        }

        @Override
        public CefCertStatus getCertStatus() {
            return N_GetCertStatus(nativePtr);
        }

        @Override
        public CefSslVersion getSslversion() {
            return N_GetSslversion(nativePtr);
        }

        @Override
        public CefSslContentStatus getContentStatus() {
            return N_GetContentStatus(nativePtr);
        }

        @Override
        public long getX509Certificate() {
            return N_GetX509Certificate(nativePtr);
        }

        private native boolean N_IsSecureConnection(long self);

        private native CefCertStatus N_GetCertStatus(long self);

        private native CefSslVersion N_GetSslversion(long self);

        private native CefSslContentStatus N_GetContentStatus(long self);

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
            return "CefSslstatus{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
