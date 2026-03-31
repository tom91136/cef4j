// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-native
package net.kurobako.cef4j.gen;

/**
 * Interface the client can implement to provide a custom stream reader. The methods of this class may be called on any
 * thread.
 */
public interface CefReadHandler {

    /** Read raw binary data. */
    default long read(long ptr, long size, long n) {
        return 0L;
    }

    /**
     * Seek to the specified offset position. |whence| may be any one of SEEK_CUR, SEEK_END or SEEK_SET. Returns zero on
     * success and non-zero on failure.
     */
    default int seek(long offset, int whence) {
        return 0;
    }

    /** Return the current offset position. */
    default long tell() {
        return 0L;
    }

    /** Return non-zero if at end of file. */
    default int eof() {
        return 0;
    }

    /**
     * Returns true if this writer performs work like accessing the file system which may block. Used as a hint for
     * determining the thread to access the writer from.
     */
    default boolean mayBlock() {
        return false;
    }
}
