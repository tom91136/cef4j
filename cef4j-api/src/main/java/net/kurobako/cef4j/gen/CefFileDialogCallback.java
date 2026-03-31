// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/** Callback interface for asynchronous continuation of file dialog requests. */
public interface CefFileDialogCallback {

    /**
     * Call to continue the download. Set |download_path| to the full file path for the download including the file name
     * or leave blank to use the suggested name and the default temp directory. Set |show_dialog| to true if you do wish
     * to show the default "Save As" dialog.
     */
    void cont(@Nonnull java.util.List<String> filePaths);

    /** Call to cancel the download. */
    void cancel();

    static class NativePeer implements CefFileDialogCallback {
        private volatile long nativePtr;

        @Override
        public void cont(java.util.List<String> filePaths) {
            N_Cont(nativePtr, filePaths);
        }

        @Override
        public void cancel() {
            N_Cancel(nativePtr);
        }

        private native void N_Cont(long self, java.util.List<String> filePaths);

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
            return "CefFileDialogCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
