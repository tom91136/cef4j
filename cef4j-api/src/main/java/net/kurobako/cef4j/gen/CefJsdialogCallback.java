// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import javax.annotation.Nonnull;

/** Callback interface used for asynchronous continuation of JavaScript dialog requests. */
public interface CefJsdialogCallback {

    /**
     * Call to continue the download. Set |download_path| to the full file path for the download including the file name
     * or leave blank to use the suggested name and the default temp directory. Set |show_dialog| to true if you do wish
     * to show the default "Save As" dialog.
     */
    void cont(int success, @Nonnull String userInput);

    static class NativePeer implements CefJsdialogCallback {
        private volatile long nativePtr;

        @Override
        public void cont(int success, String userInput) {
            N_Cont(nativePtr, success, userInput);
        }

        private native void N_Cont(long self, int success, String userInput);

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
            return "CefJsdialogCallback{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
