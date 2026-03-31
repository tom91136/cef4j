// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Class that supports the reading of zip archives via the zlib unzip API. The methods of this class should only be
 * called on the thread that creates the object.
 */
public interface CefZipReader {

    /** Moves the cursor to the first file in the archive. Returns true if the cursor position was set successfully. */
    boolean moveToFirstFile();

    /** Moves the cursor to the next file in the archive. Returns true if the cursor position was set successfully. */
    boolean moveToNextFile();

    /**
     * Moves the cursor to the specified file in the archive. If |caseSensitive| is true then the search will be case
     * sensitive. Returns true if the cursor position was set successfully.
     */
    boolean moveToFile(@Nonnull String fileName, boolean caseSensitive);

    /** Close the document. This should be called directly to ensure that cleanup occurs on the correct thread. */
    boolean close();

    /** Return the name of the file being dragged out of the browser window. */
    Optional<String> getFileName();

    /** Returns the uncompressed size of the file. */
    long getFileSize();

    /** Returns the last modified timestamp for the file. */
    CefBasetime getFileLastModified();

    /**
     * Opens the file for reading of uncompressed data. A read password may optionally be specified.
     *
     * @param password may be null
     */
    boolean openFile(@Nullable String password);

    /** Closes the file. */
    boolean closeFile();

    /**
     * Read uncompressed file contents into the specified buffer. Returns < 0 if an error occurred, 0 if at the end of
     * file, or the number of bytes read.
     */
    int readFile(long buffer, long bufferSize);

    /** Return the current offset position. */
    long tell();

    /** Return non-zero if at end of file. */
    int eof();

    static class NativePeer implements CefZipReader {
        private volatile long nativePtr;

        @Override
        public boolean moveToFirstFile() {
            return N_MoveToFirstFile(nativePtr);
        }

        @Override
        public boolean moveToNextFile() {
            return N_MoveToNextFile(nativePtr);
        }

        @Override
        public boolean moveToFile(String fileName, boolean caseSensitive) {
            return N_MoveToFile(nativePtr, fileName, caseSensitive);
        }

        @Override
        public boolean close() {
            return N_Close(nativePtr);
        }

        @Override
        public Optional<String> getFileName() {
            return Optional.ofNullable(N_GetFileName(nativePtr));
        }

        @Override
        public long getFileSize() {
            return N_GetFileSize(nativePtr);
        }

        @Override
        public CefBasetime getFileLastModified() {
            return N_GetFileLastModified(nativePtr);
        }

        @Override
        public boolean openFile(String password) {
            return N_OpenFile(nativePtr, password);
        }

        @Override
        public boolean closeFile() {
            return N_CloseFile(nativePtr);
        }

        @Override
        public int readFile(long buffer, long bufferSize) {
            return N_ReadFile(nativePtr, buffer, bufferSize);
        }

        @Override
        public long tell() {
            return N_Tell(nativePtr);
        }

        @Override
        public int eof() {
            return N_Eof(nativePtr);
        }

        private native boolean N_MoveToFirstFile(long self);

        private native boolean N_MoveToNextFile(long self);

        private native boolean N_MoveToFile(long self, String fileName, boolean caseSensitive);

        private native boolean N_Close(long self);

        private native String N_GetFileName(long self);

        private native long N_GetFileSize(long self);

        private native CefBasetime N_GetFileLastModified(long self);

        private native boolean N_OpenFile(long self, String password);

        private native boolean N_CloseFile(long self);

        private native int N_ReadFile(long self, long buffer, long bufferSize);

        private native long N_Tell(long self);

        private native int N_Eof(long self);

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
            return "CefZipReader{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
