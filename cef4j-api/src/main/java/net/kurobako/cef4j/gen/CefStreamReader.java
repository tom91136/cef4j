// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/** Class used to read data from a stream. The methods of this class may be called on any thread. */
public interface CefStreamReader {

    /** Read raw binary data. */
    long read(long ptr, long size, long n);

    /**
     * Seek to the specified offset position. |whence| may be any one of SEEK_CUR, SEEK_END or SEEK_SET. Returns zero on
     * success and non-zero on failure.
     */
    int seek(long offset, int whence);

    /** Return the current offset position. */
    long tell();

    /** Return non-zero if at end of file. */
    int eof();

    /**
     * Returns true if this writer performs work like accessing the file system which may block. Used as a hint for
     * determining the thread to access the writer from.
     */
    boolean mayBlock();

    static class NativePeer implements CefStreamReader {
        private volatile long nativePtr;

        @Override
        public long read(long ptr, long size, long n) {
            return N_Read(nativePtr, ptr, size, n);
        }

        @Override
        public int seek(long offset, int whence) {
            return N_Seek(nativePtr, offset, whence);
        }

        @Override
        public long tell() {
            return N_Tell(nativePtr);
        }

        @Override
        public int eof() {
            return N_Eof(nativePtr);
        }

        @Override
        public boolean mayBlock() {
            return N_MayBlock(nativePtr);
        }

        private native long N_Read(long self, long ptr, long size, long n);

        private native int N_Seek(long self, long offset, int whence);

        private native long N_Tell(long self);

        private native int N_Eof(long self);

        private native boolean N_MayBlock(long self);

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
            return "CefStreamReader{0x" + Long.toHexString(nativePtr) + "}";
        }
    }
}
