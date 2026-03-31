// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Callback structure that is passed to cef_v8_value_t::CreateArrayBuffer. NOTE: This struct is allocated client-side.
 */
public interface CefV8ArrayBufferReleaseCallback {

    /**
     * Called to release |buffer| when the ArrayBuffer JS object is garbage collected. |buffer| is the value that was
     * passed to CreateArrayBuffer along with this object.
     */
    void releaseBuffer(long buffer);

    static class NativePeer implements CefV8ArrayBufferReleaseCallback {
        private volatile long nativePtr;

        @Override
        public void releaseBuffer(long buffer) {
            N_ReleaseBuffer(nativePtr, buffer);
        }

        private native void N_ReleaseBuffer(long self, long buffer);

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
            return "CefV8ArrayBufferReleaseCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
