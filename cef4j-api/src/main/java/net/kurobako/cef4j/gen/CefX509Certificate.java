// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Structure representing a X.509 certificate. NOTE: This struct is allocated DLL-side. */
public interface CefX509Certificate {

    /**
     * Returns the subject of the X.509 certificate. For HTTPS server certificates this represents the web server. The
     * common name of the subject should match the host name of the web server.
     */
    long getSubject();

    /** Returns the issuer of the X.509 certificate. */
    long getIssuer();

    /**
     * Returns the DER encoded serial number for the X.509 certificate. The value possibly includes a leading 00 byte.
     */
    long getSerialNumber();

    /**
     * Returns the date before which the X.509 certificate is invalid. CefBaseTime.GetTimeT() will return 0 if no date
     * was specified.
     */
    CefBasetime getValidStart();

    /**
     * Returns the date after which the X.509 certificate is invalid. CefBaseTime.GetTimeT() will return 0 if no date
     * was specified.
     */
    CefBasetime getValidExpiry();

    long getDerencoded();

    long getPemencoded();

    /** Returns the number of certificates in the issuer chain. If 0, the certificate is self-signed. */
    long getIssuerChainSize();

    void getDerencodedIssuerChain(long chainCount, long chain);

    void getPemencodedIssuerChain(long chainCount, long chain);

    static class NativePeer implements CefX509Certificate {
        private volatile long nativePtr;

        @Override
        public long getSubject() {
            return N_GetSubject(nativePtr);
        }

        @Override
        public long getIssuer() {
            return N_GetIssuer(nativePtr);
        }

        @Override
        public long getSerialNumber() {
            return N_GetSerialNumber(nativePtr);
        }

        @Override
        public CefBasetime getValidStart() {
            return N_GetValidStart(nativePtr);
        }

        @Override
        public CefBasetime getValidExpiry() {
            return N_GetValidExpiry(nativePtr);
        }

        @Override
        public long getDerencoded() {
            return N_GetDerencoded(nativePtr);
        }

        @Override
        public long getPemencoded() {
            return N_GetPemencoded(nativePtr);
        }

        @Override
        public long getIssuerChainSize() {
            return N_GetIssuerChainSize(nativePtr);
        }

        @Override
        public void getDerencodedIssuerChain(long chainCount, long chain) {
            N_GetDerencodedIssuerChain(nativePtr, chainCount, chain);
        }

        @Override
        public void getPemencodedIssuerChain(long chainCount, long chain) {
            N_GetPemencodedIssuerChain(nativePtr, chainCount, chain);
        }

        private native long N_GetSubject(long self);

        private native long N_GetIssuer(long self);

        private native long N_GetSerialNumber(long self);

        private native CefBasetime N_GetValidStart(long self);

        private native CefBasetime N_GetValidExpiry(long self);

        private native long N_GetDerencoded(long self);

        private native long N_GetPemencoded(long self);

        private native long N_GetIssuerChainSize(long self);

        private native void N_GetDerencodedIssuerChain(long self, long chainCount, long chain);

        private native void N_GetPemencodedIssuerChain(long self, long chainCount, long chain);

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
