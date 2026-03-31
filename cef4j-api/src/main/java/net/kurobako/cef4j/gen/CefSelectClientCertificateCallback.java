// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Callback interface used to select a client certificate for authentication. */
public interface CefSelectClientCertificateCallback {

    /**
     * Chooses the specified certificate for client certificate authentication. NULL value means that no client
     * certificate should be used.
     *
     * @param cert may be null
     */
    void select(long cert);

    static class NativePeer implements CefSelectClientCertificateCallback {
        private volatile long nativePtr;

        @Override
        public void select(long cert) {
            N_Select(nativePtr, cert);
        }

        private native void N_Select(long self, long cert);

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
            return "CefSelectClientCertificateCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
