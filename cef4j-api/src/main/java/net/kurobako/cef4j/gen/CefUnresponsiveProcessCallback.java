// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Callback interface for asynchronous handling of an unresponsive process. */
public interface CefUnresponsiveProcessCallback {

    /**
     * Wait indefinitely for the event to be signaled. This method will not return until after the call to Signal() has
     * completed. This method cannot be called on the browser process UI or IO threads.
     */
    void cefWait();

    /** Terminate the unresponsive process. */
    void terminate();

    static class NativePeer implements CefUnresponsiveProcessCallback {
        private volatile long nativePtr;

        @Override
        public void cefWait() {
            N_Wait(nativePtr);
        }

        @Override
        public void terminate() {
            N_Terminate(nativePtr);
        }

        private native void N_Wait(long self);

        private native void N_Terminate(long self);

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
            return "CefUnresponsiveProcessCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
