// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Generic callback interface used for asynchronous continuation. */
public interface CefCallback {

    /**
     * Call to continue the download. Set |download_path| to the full file path for the download including the file name
     * or leave blank to use the suggested name and the default temp directory. Set |show_dialog| to true if you do wish
     * to show the default "Save As" dialog.
     */
    void cont();

    /** Call to cancel the download. */
    void cancel();

    static class NativePeer implements CefCallback {
        private volatile long nativePtr;

        @Override
        public void cont() {
            N_Cont(nativePtr);
        }

        @Override
        public void cancel() {
            N_Cancel(nativePtr);
        }

        private native void N_Cont(long self);

        private native void N_Cancel(long self);

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
            return "CefCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
