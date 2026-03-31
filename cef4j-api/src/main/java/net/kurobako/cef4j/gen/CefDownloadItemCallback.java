// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Callback interface used to asynchronously cancel a download. */
public interface CefDownloadItemCallback {

    /** Call to cancel the download. */
    void cancel();

    /** Call to pause the download. */
    void pause();

    /** Call to resume the download. */
    void resume();

    static class NativePeer implements CefDownloadItemCallback {
        private volatile long nativePtr;

        @Override
        public void cancel() {
            N_Cancel(nativePtr);
        }

        @Override
        public void pause() {
            N_Pause(nativePtr);
        }

        @Override
        public void resume() {
            N_Resume(nativePtr);
        }

        private native void N_Cancel(long self);

        private native void N_Pause(long self);

        private native void N_Resume(long self);

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
            return "CefDownloadItemCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
