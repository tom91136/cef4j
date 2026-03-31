// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Callback for asynchronous continuation of CefResourceHandler::Read(). */
public interface CefResourceReadCallback {

    /**
     * Call to continue the download. Set |download_path| to the full file path for the download including the file name
     * or leave blank to use the suggested name and the default temp directory. Set |show_dialog| to true if you do wish
     * to show the default "Save As" dialog.
     */
    void cont(int bytesRead);

    static class NativePeer implements CefResourceReadCallback {
        private volatile long nativePtr;

        @Override
        public void cont(int bytesRead) {
            N_Cont(nativePtr, bytesRead);
        }

        private native void N_Cont(long self, int bytesRead);

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
            return "CefResourceReadCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
